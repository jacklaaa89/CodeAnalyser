package org.codeanalyser.core.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * This class is used to generate output from the metric analysis
 * this is done by generating HTML files containing the output.
 * @author Jack Timblin - U1051575
 */
public class OutputGenerator {

    private File outputDestination;
    
    /**
     * initialise output generator.
     * @param destination the string destination to save the output files.
     */
    public OutputGenerator(String destination) {
        this.outputDestination = new File(destination);
    }

    /**
     * Uses the a main template output file to push the HTML from the result
     * objects passed from the metrics and then saves the file to disc.
     *
     * @throws NoResultsDefinedException when there are no results to output.
     * @throws TemplateNotFoundException when the main HTML cannot be located.
     * @throws HtmlParserException when a result string cannot be parsed into
     * HTML.
     */
    public void generateOutput(ArrayList<OverallResult> results, ArrayList<String> unsupportedFiles) throws NoResultsDefinedException,
            TemplateNotFoundException,
            HtmlParserException {
        
        //check that the output directory exists.
        if (!this.outputDestination.exists() || !this.outputDestination.isDirectory()) {
            throw new TemplateNotFoundException("Output Location does not exist or is not a directory");
        }
        
        //check that there are some results to display.
        if(results.isEmpty()) {
            throw new NoResultsDefinedException("No Results Defined From Running The Metrics.");
        }
        
        //foreach of the overallResult entries
        //make a single ResultProperty entry to pass to ST.
        ArrayList<ResultProperty> res = new ArrayList<ResultProperty>();
        for(OverallResult re : results) {
            res.add(re.toResultProperty(outputDestination));
        }
        
        //generate the HTML file using the template.
        STGroupFile group = new STGroupFile("antlr/templates/OutputTemplate.stg");
        ST main = group.getInstanceOf("main");

        main.add("unsupportedFiles", unsupportedFiles);
        main.add("hasUnsupported", (unsupportedFiles.size() > 0));
        main.add("results", res);
        
        //save the rendered template to a file.
        File output = new File(this.outputDestination.getAbsolutePath() + "/output.html");

        if (output.exists()) {
            output.delete();
        }

        try {
            FileWriter writer = new FileWriter(output);
            writer.append(main.render());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new TemplateNotFoundException("Could not open, read or write output destination.");
        }
    }

}
