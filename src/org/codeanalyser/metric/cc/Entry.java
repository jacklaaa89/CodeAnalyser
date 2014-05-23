package org.codeanalyser.metric.cc;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.codeanalyser.core.utils.OutputInterface;
import org.json.simple.JSONObject;

/**
 * An encapsulation of an Entry. An Entry is defined as an evaluation
 * of a single method based on the amount of complexity keywords.
 * @author Jack Timblin - u1051575
 */
public class Entry implements Comparable<Entry>, OutputInterface {
    
    private String type;
    private int amountOfComplexKeywords = -1;
    private ParserRuleContext context;
    private String methodName;
    private int complexityThreshold;
    private final HashMap<String, Integer> keywordOccurrences
            = new HashMap<String, Integer>();
    
    /**
     * gets the occurrences of different keywords that 
     * were found in this entry.
     * @return the keyword occurrences.
     */
    public HashMap<String, Integer> getKeywordOccurrences() {
        return keywordOccurrences;
    }
    
    /**
     * adds a new keyword instance to the instances.
     * @param keyword the keyword.
     */
    public void addKeywordOccurrence(String keyword) {
        Integer i = this.getKeywordOccurrences().get(keyword);
        this.getKeywordOccurrences().put(keyword, (i != null) ? (int)i+1 : 1);
    }
    
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
     * returns the Output Interface for this metric.
     * @return a HTML representation of this object.
     */
    public OutputInterface toResult() {
        return this;
    }
    
    /**
     * returns a string representation of this object.
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "methodName: " + this.getMethodName() + ", amount of complexity keywords: " + this.getAmountOfComplexKeywords();
    }
    
    /**
     * compares this Entry to another Entry object
     * based on the amount of complexity keywords.
     * @param o the Entry object to compare to this Object.
     * @return 1, 0 or -1 dependant on the comparison.
     */
    @Override
    public int compareTo(Entry o) {
        return new CompareToBuilder()
                .append(this.getAmountOfComplexKeywords(), o.getAmountOfComplexKeywords())
                .toComparison();
    }

    @Override
    public String toHTML() {
        String result =  "<table><tr><td>CyclomaticComplexity Results:</td></tr>"
                + "<tr><td>Method With Most Complexity: "+this.getMethodName()+"</td></tr>"
                + "<tr><td>Amount of Complexity Keywords Found: "+this.getAmountOfComplexKeywords()+"</td></tr>"
                + "<tr><td>Complexity Threshold: "+this.getComplexityThreshold()+"</td></tr>"
                + "<tr><td><span style='text-decoration:underline;'>Keyword Occurances:</span> </td></tr>";
        for(Map.Entry<String, Integer> entry : this.getKeywordOccurrences().entrySet()) {
            result += "<tr><td>"+entry.getKey()+": </td><td>"+entry.getValue()+"</td></tr>";
        }
        return result + "</table>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        o.put("methodWithMostComplexity", this.getMethodName());
        o.put("amountOfComplexityKeywordsFound", this.getAmountOfComplexKeywords());
        o.put("complexityThreshold", this.getComplexityThreshold());
        JSONObject k = new JSONObject();
        for(Map.Entry<String, Integer> entry : this.getKeywordOccurrences().entrySet()) {
            k.put(entry.getKey(), entry.getValue());
        }
        o.put("keywordOccurrences", k);
        return o;
    }
    
}
