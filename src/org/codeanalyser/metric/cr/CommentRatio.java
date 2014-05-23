package org.codeanalyser.metric.cr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.core.utils.OutputInterface;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricError;
import org.codeanalyser.metric.MetricErrorAdapter;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;
import org.json.simple.JSONObject;
import org.yaml.snakeyaml.Yaml;

/**
 * Determines the Comment to Code ratio in a piece of source code.
 *
 * @author Jack Timblin - U1051575
 */
public class CommentRatio implements MetricInterface, MetricErrorAdapter, OutputInterface {

    private double commentCount, blankCount, codeCount;
    private String fileLocation;
    private final int[] thresholdRatio = {20, 40};
    private final ArrayList<MetricError> errors = new ArrayList<MetricError>();

    @Override
    public Result getResults() throws InvalidResultException {

        

        //return the result object for the application to use.
        return Result.newInstance(this.getClass().getSimpleName(),
                this, this.isWithinThreshold(), errors);
    }

    @Override
    public void onParserEvent(EventState state) {
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {

        //determine command line args dependant on OS.
        File clocStart = new File(Application.getSystemPath()+"antlr/cloc");
        String[] args;
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");

        if (!isWindows) {
            args = new String[]{
                "bash", "-c", "perl " + clocStart.getAbsolutePath() + "/cloc-1.60.pl"
            };

            //set initial variables about the file.
            this.fileLocation = initialInformation.getFileName();

            File f = new File("antlr");

            //build a process to run cloc.
            ProcessBuilder builder = new ProcessBuilder(
                    args[0], args[1], args[2] + " --yaml --report-file=" + f.getAbsolutePath() + "/output.txt " + fileLocation
            );

            builder.redirectErrorStream(true);

            try {
                Process p = builder.start();
                p.waitFor();
                //load the generated YAML file.
                Yaml y = new Yaml();
                File fi = new File(Application.getSystemPath()+"antlr/output.txt");

                if (fi.exists()) {
                    InputStream input = new FileInputStream(fi);

                    //grab SUM element from file.
                    Map o = (Map) y.load(input);
                    Map sum = (Map) o.get("SUM");

                    //assign results to variables.
                    commentCount = (int) sum.get("comment");
                    codeCount = (int) sum.get("code");
                    blankCount = (int) sum.get("blank");

                    //delete the output file.
                    fi.delete();
                } else {
                    //if the language is not supported by CLOC
                    //i.e the hello language, use the lexer.
                    this.determineCommentRatioBackup(initialInformation);
                }

            } catch (Exception e) {
                throw new MetricInitialisationException(e.getMessage());
            }
        } else {
            //if its windows just use the lexer to determine the ratios.
            this.determineCommentRatioBackup(initialInformation);
        }
    }

    @Override
    public void destroy() {}

    /**
     * checks to see if the percentage of commenting in a file is within the
     * predefined thresholds.
     *
     * @return true if this is the case, false otherwise.
     */
    private boolean isWithinThreshold() {

        double commentPercentage = this.getPercentages()[1];

        return (commentPercentage >= this.thresholdRatio[0]
                && commentPercentage <= this.thresholdRatio[1]);
    }

    /**
     * takes the line counts calculated from CLOC and converts them into the
     * percentage equivalents of the total lines in the file.
     *
     * @return an array of percentages.
     */
    private double[] getPercentages() {
        //calculate the percentages of each type.
        double totalLines = commentCount + codeCount + blankCount;
        double codePercentage = (codeCount / totalLines) * 100;
        double commentPercentage = (commentCount / totalLines) * 100;
        double blankPercentage = (blankCount / totalLines) * 100;
        double[] pc = new double[]{codePercentage, commentPercentage, blankPercentage};
        //round the results.
        for(int i = 0; i < pc.length; i++) {
            pc[i] = Math.round(pc[i]);
        }
        return pc;
    }
    
    /**
     * determines the comment to code ratio using the supported lexer 
     * for the language, this is intended to be used as an alternative from CLOC.
     * @param info the ParserInfo object based to init, which i can get a copy of
     * the supported lexer from.
     * @throws MetricInitialisationException if required tokens could not be found or
     * the source file could not be found or read.
     */
    private void determineCommentRatioBackup(ParserInfo info) throws MetricInitialisationException {
        //get the tokens that were defined in the grammar.
        HashMap<Integer, String> tokenTypes = info.getTokenTypes();
        //check that the LINE_COMMENT|COMMENT token types exist.
        boolean lce = false, ce = false;
        for(Map.Entry<Integer, String> entry : tokenTypes.entrySet()) {
            if(entry.getValue().equals("LINE_COMMENT")) {
                lce = true;
            } else if(entry.getValue().equals("COMMENT")) {
                ce = true;
            }
        }
        
        if(!lce || !ce) {
            throw new MetricInitialisationException("Comment tokens could not be determined.");
        }
        
        //get the lexer.
        Lexer l = info.getLexer();
        int cmc = 0, bc = 0, to = 0;
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Integer> clines = new ArrayList<Integer>();
        
        //get the tokens that were found by performing lexical analusis 
        //on the file.
        List<? extends Token> tokens = l.getAllTokens();
        for (Token t : tokens) {
            
            //get the type type.
            String tt = tokenTypes.get(t.getType());
            if(!tt.equals("LINE_COMMENT") && !tt.equals("COMMENT")) {
                //this is not a token, just record the line.
                if(!lines.contains(t.getLine())) {
                    lines.add(t.getLine());
                }
                if(!clines.isEmpty()) {
                    //a previous multiline comment exists.
                    if(t.getLine() <= clines.get(0)) {
                        cmc--;
                    }
                    //if this was the last line the multicomment does up to.
                    if(t.getLine() == clines.get(0)) {
                        //remove this entry.
                        clines.clear();
                    }
                }
            } else {
                //if there is no code on the same line.
                if(tt.endsWith("COMMENT")) {
                    //get the amount of lines this comment rolls over.
                    int noLines = t.getText().split("\n").length;
                    clines.add(t.getLine()+noLines);
                    //check this doesnt have code on as well.
                    cmc = cmc+noLines;
                    if(lines.contains(t.getLine())) {
                        cmc = cmc-1;
                    }
                }
            }
        }
        
        //determine the number of blank lines, i.e a line that is empty.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(info.getFileName())));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.isEmpty()) {
                    bc++;
                }
                to++;
            }
            
        } catch (Exception e) {
            throw new MetricInitialisationException(e.getMessage());
        }
        
        //set the calculated values to the values used in the result.
        this.blankCount = bc;
        this.commentCount = cmc;
        this.codeCount = to-(bc+cmc);
    }

    @Override
    public void onInitialisationError(MetricInitialisationException e, Logger logger, ParserInfo info) {
        errors.add(new InitialisationError(e, info));
    }

    @Override
    public void onInvalidResultException(InvalidResultException e, Result result, Logger logger, ParserInfo info) {}

    @Override
    public String toHTML() {
        //generate the result text.
        double[] percentages = this.getPercentages();

        //generate output.
        String result = "<table><tr><td>CommentRatio Results: </td></tr>"
                + "<tr><td>Code Percentage: " + percentages[0] + "%</td></tr>"
                + "<tr><td>Comment Percentage: " + percentages[1] + "%</td></tr>"
                + "<tr><td>Blank Lines Percentage: " + percentages[2] + "%</td></tr>"
                + "<tr><td></td></tr>"
                + "<tr><td><span>Thresholds:</span> </td></tr>"
                + "<tr><td>Min: " + this.thresholdRatio[0] + "%</td></tr>"
                + "<tr><td>Max: " + this.thresholdRatio[1] + "%</td></tr></table>";
        return result;
    }

    @Override
    public JSONObject toJSON() {
        double[] percentages = this.getPercentages();
        JSONObject o = new JSONObject();
        o.put("codePercentage", percentages[0]);
        o.put("blankLinePercentage", percentages[2]);
        o.put("commentPercentage", percentages[1]);
        JSONObject t = new JSONObject();
        t.put("min", this.thresholdRatio[0]);
        t.put("max", this.thresholdRatio[1]);
        o.put("thresholds", t);
        return o;
    }
    
    
}
