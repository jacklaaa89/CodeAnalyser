package org.codeanalyser.metric.vmc;

/**
 * an encapsulation of a result from detecting a variable names language
 * this is so if there are variables that are the same in the class 
 * is does not cause errors.
 * @author Jack Timblin - U1051575
 */
public class Variable {
    
    private String variableName;
    private String detectedLanguage; 
    
    /**
     * returns the variable name.
     * @return the variable name
     */
    public String getVariableName() {
        return variableName;
    }
    
    /**
     * sets the variable name.
     * @param variableName the variable name.
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
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
