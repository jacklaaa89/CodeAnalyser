/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.core.output;

/**
 *
 * @author jack
 */
public class ResultProperty {
    
    /**
     * the result HTML passed from the metric
     */
    private String resultHTML;
    /**
     * the color code to make the background of the result box
     * red if the file failed the metric, green if it passed.
     */
    private String colorCode;
    /**
     * the filename of the file analysed.
     */
    private String fileName;
    /**
     * the source language determined.
     */
    private String sourceLanguage;
    /**
     * whether this file overall succeeded or failed.
     */
    private String successMessage;
    
    public String getSuccessMessage() {
        return this.successMessage;
    }
    
    public String getResultHTML() {
        return this.resultHTML;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getSourceLanguage() {
        return this.sourceLanguage;
    }
    
    public String getColorCode() {
        return this.colorCode;
    }
    
    public ResultProperty(String resultsHTML, boolean isSuccessful, 
            String fileName, String sourceLanguage) {
        this.colorCode = isSuccessful ? "green" : "red";
        this.fileName = fileName;
        this.resultHTML = resultsHTML;
        this.sourceLanguage = sourceLanguage;
        this.successMessage = isSuccessful ? "Passed" : "Failed";
    }
    
}
