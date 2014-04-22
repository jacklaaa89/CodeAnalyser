package org.codeanalyser.metric.cr;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;
import org.yaml.snakeyaml.Yaml;

/**
 * Determines the Comment to Code ratio in a piece of source code.
 * @author Jack Timblin - U1051575
 */
public class CommentRatio implements MetricInterface {
    
    private double commentCount, blankCount, codeCount;
    private String fileLocation, sourceLanguage;
    private final int[] thresholdRatio = {20, 40};

    @Override
    public Result getResults() throws InvalidResultException {
        
        //generate the result text.
        double[] percentages = this.getPercentages();
        String result = "<table><tr><td>CommentRatio Results: </td></tr>"
                + "<tr><td>Code Percentage: "+percentages[0]+"%</td></tr>"
                + "<tr><td>Comment Percentage: "+percentages[1]+"%</td></tr>"
                + "<tr><td>Blank Lines Percentage: "+percentages[2]+"%</td></tr>"
                + "<tr><td></td></tr>"
                + "<tr><td style='font-weight:bold;'>Thresholds: </td></tr>"
                + "<tr><td>Min: "+this.thresholdRatio[0]+"%</td></tr>"
                + "<tr><td>Max: "+this.thresholdRatio[1]+"%</td></tr></table>";
        
        //return the result object for the application to use.
        return Result.newInstance(fileLocation, sourceLanguage, this.getClass().getSimpleName()
                , result, this.isWithinThreshold());
    }

    @Override
    public void onParserEvent(EventState state) {}

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
        
        //determine command line args dependant on OS.
        File clocStart = new File("antlr/cloc");
        String[] args;
        
        if(System.getProperty("os.name").startsWith("Windows")) {
            args = new String[]{
                "cmd", "/c", clocStart.getAbsolutePath()+"/cloc-1.60.exe"
            };
        } else {
            args = new String[]{
                "bash", "-c", "perl " + clocStart.getAbsolutePath()+"/cloc-1.60.pl"
            };
        }
        
        //set initial variables about the file.
        this.fileLocation = initialInformation.getFileName();
        this.sourceLanguage = initialInformation.getSourceLanguage();
        
        File f = new File("antlr");
        
        //build a process to run cloc.
        ProcessBuilder builder = new ProcessBuilder(
                args[0], args[1], args[2]+" --yaml --report-file="+f.getAbsolutePath()+"/output.txt " + fileLocation
        );
        
        builder.redirectErrorStream(true);
        
        try {
            Process p = builder.start();
            p.waitFor();
            //load the generated YAML file.
            Yaml y = new Yaml();
            File fi = new File("antlr/output.txt");
            
            if(!fi.exists()) {
                //i.e cloc couldnt generate output.
                throw new MetricInitialisationException("CLOC couldnt evaluate file: " + this.fileLocation);
            }
            
            InputStream input = new FileInputStream(fi);
            
            //grab SUM element from file.
            Map o = (Map) y.load(input);
            Map sum = (Map)o.get("SUM");
            
            //assign results to variables.
            commentCount = (int)sum.get("comment");
            codeCount = (int)sum.get("code");
            blankCount = (int)sum.get("blank");
            
            //delete the output file.
            fi.delete();
        } catch (Exception e) {
            throw new MetricInitialisationException(e.getMessage());
        }
    }

    @Override
    public void destroy() {}
    
    /**
     * checks to see if the percentage of commenting in a file is
     * within the predefined thresholds.
     * @return true if this is the case, false otherwise.
     */
    private boolean isWithinThreshold() {
        
        double commentPercentage = this.getPercentages()[1];
                
        return (commentPercentage >= this.thresholdRatio[0] &&
                commentPercentage <= this.thresholdRatio[1]);
    }
    
    /**
     * takes the line counts calculated from CLOC and converts them into
     * the percentage equivalents of the total lines in the file.
     * @return an array of percentages.
     */
    private double[] getPercentages() {
        //calculate the percentages of each type.
        double totalLines = commentCount+codeCount+blankCount;
        double codePercentage = (codeCount/totalLines)*100;
        double commentPercentage = (commentCount/totalLines)*100;
        double blankPercentage = (blankCount/totalLines)*100;
        return new double[] {codePercentage, commentPercentage, blankPercentage};
    }
}
