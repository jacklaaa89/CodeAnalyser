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

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }
    
}
