package org.codeanalyser.core.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.codeanalyser.core.output.OverallResult;

/**
 * A container class used to inject the template with the overall
 * result of all of the metrics on all files.
 * @author Jack Timblin - U1051575
 */
public class AnalyserResult {
    
    private ArrayList<OverallResult> results;
    private ArrayList<FileAnalyser> filesToAnalyse;
    private ArrayList<String> unsupportedFiles, syntaxErrors;
    private int noSyntaxErrorFiles = 0;
    
    /**
     * determines the percentages of each file type that was passed to the
     * analyser.
     * @return the HashMap with the key as the source language and value as
     * the percentage.
     */
    private HashMap<String, Double> determineFileStats() {
        HashMap<String, Double> map = new HashMap<String, Double>();
        for(FileAnalyser f : this.filesToAnalyse) {
            String source = f.getSourceLanguage();
            source = (source == null) ? "Unknown" : source.substring(0, 1).toUpperCase()+source.substring(1);
            Double i = map.get(source);
            map.put(source, (i != null) ? i + 1 : 1);
        }
        
        HashMap<String, Double> percentages = new HashMap<String, Double>();
        for(Map.Entry<String, Double> entry : map.entrySet()) {
            double d = Math.round((entry.getValue()/this.filesToAnalyse.size())*100);
            percentages.put(entry.getKey(), d);
        }
        
        return percentages;
        
    }
        
    
    /**
     * get the amount of successfully analysed files.
     * @return the amount of successfully analysed files.
     */
    public int getSuccessCount() {
        return this.results.size();
    }
    
    /**
     * gets the number of files that have has one of more 
     * syntax errors occur.
     * @return the number of files as more than one syntax error
     * can occur per file.
     */
    public int getNoOfSyntaxErrorFiles() {
        return this.noSyntaxErrorFiles;
    }
    
    /**
     * increment the number of files by 1.
     */
    public void incrementNoOfSyntaxErrorFiles() {
        this.noSyntaxErrorFiles++;
    }
    
    /**
     * get the amount of files that failed analysis
     * due to having syntax errors or being unsupported.
     * @return the amount of files that failed analysis.
     */
    public int getFailedCount() {
        return this.syntaxErrors.size() + this.unsupportedFiles.size();
    }
    
    /**
     * gets the percentage of how many files were successfully analysed.
     * @return the percentage of successful files.
     */
    public double getSuccessPercentage() {
        return (this.getSuccessCount()/this.filesToAnalyse.size())*100;
    }
    
    /**
     * gets the percentage of how many files failed to be analysed.
     * @return the percentage of failed files.
     */
    public double getFailedPercentage() {
        return (this.getFailedCount()/this.filesToAnalyse.size())*100;
    }
    
    /**
     * determines if the amount of successful files was more than that
     * of what failed, this includes checking if the metric results were 
     * overall successful.
     * @return true if this analysis was overall successful, or false if not.
     */
    public boolean wasOverallSuccessful() {
        
       int[] figures = this.getSuccessFailFigures();
       return figures[0] >= figures[1];
    }
    
    /**
     * gets the amount of files that successfully passed and failed.
     * @return the amount of files that successfully passed and failed.
     */
    public int[] getSuccessFailFigures() {
        HashMap<Boolean, Integer> map = new HashMap<Boolean, Integer>();
        for (OverallResult r : this.results) {
            Integer i = map.get(r.isOverallSuccessful());
            map.put(r.isOverallSuccessful(), (i != null) ? (int) i + 1: 1);
        }
        
        Integer t = map.get(true); Integer f = map.get(false);
        f = (f == null) ? 0 : f;
        
        //include failures from syntax errors or if any files were unsupported.
        if(this.getNoOfSyntaxErrorFiles() != 0 || !this.unsupportedFiles.isEmpty()) {
            f = f + (this.getNoOfSyntaxErrorFiles() + this.unsupportedFiles.size());
        }
        return new int[] {(t == null) ? 0 : t, f};
    }
    
    /**
     * gets the OverallResult objects for all the files analysed.
     * @return the OverallResult for all files.
     */
    public ArrayList<OverallResult> getResults() {
        return results;
    }
    
    /**
     * gets the list of files that have been analysed.
     * @return the list of files that have been analysed.
     */
    public ArrayList<FileAnalyser> getFilesToAnalyse() {
        return filesToAnalyse;
    }
    
    /**
     * gets a list of all the files that were not supported for
     * analysis.
     * @return the list of unsupported files.
     */
    public ArrayList<String> getUnsupportedFiles() {
        return unsupportedFiles;
    }
    
    /**
     * gets the list of string syntax error messages from parsing
     * the files.
     * @return the list of string syntax error messages.
     */
    public ArrayList<String> getSyntaxErrors() {
        return syntaxErrors;
    }
    
    /**
     * adds a new result object.
     * @param result the result object
     */
    public void addResult(OverallResult result) {
        this.results.add(result);
    }
    
    /**
     * Adds a syntax error message.
     * @param syntaxErrorMessage the syntax error message
     */
    public void addSyntaxError(String syntaxErrorMessage) {
        this.syntaxErrors.add(syntaxErrorMessage);
    }
    
    /**
     * adds a new unsupported file.
     * @param fileAbsolutePath the path of the unsupported file.
     */
    public void addUnsupportedFile(String fileAbsolutePath) {
        this.unsupportedFiles.add(fileAbsolutePath);
    }
    
    /**
     * Initialises this AnalyserResult Object.
     * @param filesToAnalyse the files that will be analysed. 
     */
    public AnalyserResult(ArrayList<FileAnalyser> filesToAnalyse) {
        this.filesToAnalyse = filesToAnalyse;
        this.results = new ArrayList<OverallResult>();
        this.syntaxErrors = new ArrayList<String>();
        this.unsupportedFiles = new ArrayList<String>();
    }
    
    /**
     * gets the overall result of the all metric analysis on all files.
     * @return the string result.
     */
    public String getResult() {
        boolean wos = this.wasOverallSuccessful();
        String color = (wos) ? "green" : "red";
        String text = (wos) ? "SUCCESS" : "FAILED";
        int[] sf = this.getSuccessFailFigures();
        return "<table><tr><td style='font-weight:bold;'>Overall Result: </td></tr>"
             + "<tr><td>Amount of Files Analysed: </td><td>"+this.filesToAnalyse.size()+"</td></tr>"
             + "<tr><td>Was Overall Successful: </td><td><span style='color:"+color+";'>"+text+"</span></td></tr>"
             + "<tr><td>Successful/Failed: </td><td>"+sf[0]+"/"+sf[1]+"</td></tr></table>";
    }
    
    /**
     * gets a HTML string with the file stats
     * i.e percentages of each file type.
     * @return a HTML string with the file stats.
     */
    public String getFileStats() {
        HashMap<String, Double> stats = this.determineFileStats();
        StringBuilder builder = new StringBuilder();
        builder.append("<table><tr><td style='font-weight:bold;'>File Statistics: </<td></tr>");
        for(Map.Entry<String, Double> entry : stats.entrySet()) {
            builder.append("<tr><td>");
            builder.append(entry.getKey());
            builder.append("</td><td>");
            builder.append(entry.getValue());
            builder.append("%</td></tr>");
        }
        builder.append("</table>");
        return builder.toString();
    }
    
    /**
     * returns a string representation of this object.
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "AnalyserResult :[fileStats: " + this.determineFileStats().toString() + ", result: " + this.getResult() + "]";
    }
    
}
