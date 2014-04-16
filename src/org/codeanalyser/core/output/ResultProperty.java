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
    private String link;
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
    
    /**
     * gets the message SUCCESS or FAILED dependant on 
     * how well this file performed.
     * @return the success message.
     */
    public String getSuccessMessage() {
        return this.successMessage;
    }
    
    /**
     * gets the HTML href link to link to the subpage.
     * @return the href link.
     */
    public String getLink() {
        return this.link;
    }
    
    /**
     * gets the absolute file name for the file that was analysed.
     * @return the filename.
     */
    public String getFileName() {
        return this.fileName;
    }
    
    /**
     * gets the source language of the file.
     * @return the source language.
     */
    public String getSourceLanguage() {
        return this.sourceLanguage;
    }
    
    /**
     * gets the color code to use dependant on 
     * how well this file performed.
     * @return the color code to use.
     */
    public String getColorCode() {
        return this.colorCode;
    }
    
    /**
     * initialises a ResultProperty and uses contructor overloading.
     * @param link the href link.
     * @param isSuccessful if this file was mainly successful or not.
     * @param fileName the absolute file name of this file.
     * @param sourceLanguage the source language of this file.
     */
    public ResultProperty(String link, boolean isSuccessful, 
            String fileName, String sourceLanguage) {
        this.colorCode = isSuccessful ? "green" : "red";
        this.fileName = fileName;
        this.link = link;
        this.sourceLanguage = sourceLanguage;
        this.successMessage = isSuccessful ? "Passed" : "Failed";
    }
    
}
