package org.codeanalyser.core.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.AnalyserResult;
import org.codeanalyser.core.utils.Logger;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * This class is used to generate output from the metric analysis this is done
 * by generating HTML files containing the output.
 *
 * @author Jack Timblin - U1051575
 */
public class OutputGenerator {

    private final File outputDestination;

    /**
     * initialise output generator.
     *
     * @param destination the string destination to save the output files.
     */
    public OutputGenerator(String destination) {
        this.outputDestination = new File(destination);
    }

    /**
     * Uses the a main template output file to push the HTML from the result
     * objects passed from the metrics and then saves the file to disc.
     *
     * @param result the overall result from the analyser.
     * @throws NoResultsDefinedException when there are no results to output.
     * @throws TemplateNotFoundException when the main HTML cannot be located.
     * @throws HtmlParserException when a result string cannot be parsed into
     * HTML.
     */
    public void generateOutput(AnalyserResult result) throws NoResultsDefinedException,
            TemplateNotFoundException,
            HtmlParserException {

        //check that the output directory exists.
        if (!this.outputDestination.exists() || !this.outputDestination.isDirectory()) {
            throw new TemplateNotFoundException("Output Location does not exist or is not a directory");
        }

        //check that there are some results to display.
        if (result.getResults().isEmpty()) {
            Application.getLogger().log("No Results Defined From Running The Metrics.");
        }

            //foreach of the overallResult entries
        //make a single ResultProperty entry to pass to ST.
        ArrayList<ResultProperty> res = new ArrayList<ResultProperty>();
        for (OverallResult re : result.getResults()) {
            res.add(re.toResultProperty(outputDestination));
        }
        //generate the HTML file using the template.
        
        if(Application.getInterface().equals(Logger.DEFAULT)) {

            STGroupFile group = new STGroupFile(Application.getSystemPath()+"antlr/templates/OutputTemplate.stg");
            ST main = group.getInstanceOf("main");

            main.add("unsupportedFiles", result.getUnsupportedFiles());
            main.add("hasUnsupported", (result.getUnsupportedFiles().size() > 0));
            main.add("results", res);
            main.add("hasResults", !res.isEmpty());
            main.add("hasSyntax", (!result.getSyntaxErrors().isEmpty()));
            main.add("syntaxErrors", result.getSyntaxErrors());
            main.add("overallResult", result.getResult());
            main.add("fileStats", result.getFileStats());

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
            
        } else {
            //print the JSON Object.
            System.out.println(result.toJSON().toJSONString());
        }

    }

}
