package org.codeanalyser.metric.vmc;

/**
 * an encapsulation of a result from detecting a variable names language
 * this is so if there are variables that are the same in the class 
 * is does not cause errors.
 * @author Jack Timblin - U1051575
 */
public class Detection {
    
    private String detectedLanguage;
    private double confidence;
    
    /**
     * gets the confidence percentage of
     * how confidently we can say the language detection
     * is reliable.
     * @return the confidence percentage.
     */
    public double getConfidence() {
        return confidence;
    }
    
    /**
     * sets the confidence percentage. 
     * @param confidence the confidence percentage.
     */
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
    
    /**
     * gets the detected language of the variable name.
     * @return the variable names detected natural language.
     */
    public String getDetectedLanguage() {
        return detectedLanguage;
    }
    
    /**
     * sets the language the variable name has been detected as.
     * @param detectedLanguage the detected language.
     */
    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }
    
}
