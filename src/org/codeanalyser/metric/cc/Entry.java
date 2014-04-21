package org.codeanalyser.metric.cc;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 *
 * @author Jack
 */
public class Entry {
    
    private String type;
    private int amountOfComplexKeywords = -1;
    private ParserRuleContext context;
    private String methodName;
    private int complexityThreshold;

    public int getComplexityThreshold() {
        return complexityThreshold;
    }

    public void setComplexityThreshold(int complexityThreshold) {
        this.complexityThreshold = complexityThreshold;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmountOfComplexKeywords() {
        return amountOfComplexKeywords;
    }

    public void setAmountOfComplexKeywords(int amountOfComplexKeywords) {
        this.amountOfComplexKeywords = amountOfComplexKeywords;
    }

    public ParserRuleContext getContext() {
        return context;
    }

    public void setContext(ParserRuleContext context) {
        this.context = context;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    public String toResult() {
        return "<table><tr><td>CyclomaticComplexity Results:</td></tr>"
                + "<tr><td>Method With Most Complexity: "+this.getMethodName()+"</td></tr>"
                + "<tr><td>Amount of Complexity Keywords Found: "+this.getAmountOfComplexKeywords()+"</td></tr>"
                + "<tr><td>Complexity Threshold: "+this.getComplexityThreshold()+"</td></tr></table>";
    }
    
    @Override
    public String toString() {
        return "methodName: " + this.getMethodName() + ", amount of complexity keywords: " + this.getAmountOfComplexKeywords();
    }
    
}
