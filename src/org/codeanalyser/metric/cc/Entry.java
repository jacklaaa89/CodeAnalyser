package org.codeanalyser.metric.cc;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * An encapsulation of an Entry. An Entry is defined as an evaluation
 * of a single method based on the amount of complexity keywords.
 * @author Jack Timblin - u1051575
 */
public class Entry {
    
    private String type;
    private int amountOfComplexKeywords = -1;
    private ParserRuleContext context;
    private String methodName;
    private int complexityThreshold;
    
    /**
     * gets the amount of complexity keywords permitted in this entry.
     * @return the amount of permitted keywords.
     */
    public int getComplexityThreshold() {
        return complexityThreshold;
    }
    
    /**
     * sets the limit of complexity keywords permitted in this entry.
     * @param complexityThreshold the amount of complexity keywords permitted.
     */
    public void setComplexityThreshold(int complexityThreshold) {
        this.complexityThreshold = complexityThreshold;
    }
    
    /**
     * gets the event that was triggered in the parser.
     * @return the event that was triggered.
     */
    public String getType() {
        return type;
    }
    
    /**
     * the event type that was triggered while walking the parse tree.
     * @param type the event that occurred.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * gets the amount of complexity keywords found
     * during analysis of this method.
     * @return the amount of keywords found.
     */
    public int getAmountOfComplexKeywords() {
        return amountOfComplexKeywords;
    }
    
    /**
     * sets the amount of complexity keywords found during analysis.
     * @param amountOfComplexKeywords the amount of keywords found.
     */
    public void setAmountOfComplexKeywords(int amountOfComplexKeywords) {
        this.amountOfComplexKeywords = amountOfComplexKeywords;
    }
    
    /**
     * gets the context that determined the evaluation result of this
     * entry.
     * @return the context from the parser. 
     */
    public ParserRuleContext getContext() {
        return context;
    }
    
    /**
     * sets the context used to determine its complexity.
     * @param context the context from the parser.
     */
    public void setContext(ParserRuleContext context) {
        this.context = context;
    }
    
    /**
     * gets the method name of this entry.
     * i.e the name of method that was evaluated.
     * @return the method name.
     */
    public String getMethodName() {
        return methodName;
    }
    
    /**
     * sets the method name of this entry.
     * @param methodName the method name.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    /**
     * returns this object as a HTML string to inject into the output template.
     * @return a HTML representation of this object.
     */
    public String toResult() {
        return "<table><tr><td>CyclomaticComplexity Results:</td></tr>"
                + "<tr><td>Method With Most Complexity: "+this.getMethodName()+"</td></tr>"
                + "<tr><td>Amount of Complexity Keywords Found: "+this.getAmountOfComplexKeywords()+"</td></tr>"
                + "<tr><td>Complexity Threshold: "+this.getComplexityThreshold()+"</td></tr></table>";
    }
    
    /**
     * returns a string representation of this object.
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "methodName: " + this.getMethodName() + ", amount of complexity keywords: " + this.getAmountOfComplexKeywords();
    }
    
}
