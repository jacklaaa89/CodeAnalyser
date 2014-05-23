package org.codeanalyser.metric.don;

import java.util.ArrayList;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.codeanalyser.core.utils.OutputInterface;
import org.json.simple.JSONObject;

/**
 * An encapsulation of a single methods analysis 
 * to get the deepest level of nesting that occurs.
 * 
 * @author Jack Timblin - U1051575
 */
public class Entry implements Comparable<Entry>, OutputInterface {
    
    private final ArrayList<NestingEntry> nestingEntries;
    private final String methodName;
    private NestingEntry deepestNestingOccurance = new NestingEntry();
    private final int nestingThreshold = 4;
    
    /**
     * Initialises a new Entry Object.
     * @param methodName the method that is being analysed.
     */
    public Entry(String methodName) {
        this.methodName = methodName;
        this.nestingEntries = new ArrayList<NestingEntry>();
    }
    
    /**
     * Helper constructor to contruct an Entry with the default 
     * 'Unknown Method' method name incase there was no methods analysed.
     */
    public Entry() {
        this("Unknown Method");
    }
    
    /**
     * returns the largest degree of nesting that 
     * occurred in a method.
     * @return the deepest level of nesting that occurred in the method. 
     */
    public int getDeepestNestingOccuranceAmount() {
        return deepestNestingOccurance.getAmountOfNesting();
    }
    
    public NestingEntry getDeepestNestingOccurance() {
        return deepestNestingOccurance;
    }
    
    /**
     * sets the largest degree of nesting that occurred in the class.
     * @param ne the current nesting entry.
     */
    public void setDeepestNestingOccurance(NestingEntry ne) {
        if(ne.getAmountOfNesting() > this.getDeepestNestingOccurance().getAmountOfNesting()) {
            this.deepestNestingOccurance = ne;
        }
    }
    
    /**
     * gets the last set nesting entry occurance.
     * as multiple events of nesting can occur in a single method.
     * @return the last entry.
     */
    public NestingEntry getLastNestingEntry() {
        NestingEntry ne = null;
        if(!this.nestingEntries.isEmpty()) {
            ne = this.nestingEntries.get(this.nestingEntries.size()-1);
            ne = (!ne.isClosed()) ? ne : null; 
        }
        return ne;
    }
    
    
    
    /**
     * removes the last nesting entry.
     */
    public void removeLastNestingEntry() {
        if(!this.nestingEntries.isEmpty()) {
            this.nestingEntries.remove(this.nestingEntries.size()-1);
        }
    }
    
    /**
     * gets the method being analysed.
     * @return the method name.
     */
    public String getMethodName() {
        return methodName;
    }
    
    /**
     * gets the set nesting threshold, i.e the maximum degree
     * of nesting that can occur in a method.
     * @return the nesting threshold.
     */
    public int getNestingThreshold() {
        return nestingThreshold;
    }
    
    /**
     * sets a new nesting entry for this method.
     * i.e a new occurrance has been found in this class.
     * @param ne the new nesting entry.
     */
    public void setNestingEntry(NestingEntry ne) {
        this.nestingEntries.add(ne);
    }
    
    /**
     * returns this entry as a HTML string.
     * @return this entry as a HTML string.
     */
    public OutputInterface toResult() {
        return this;
    }
    
    /**
     * returns a string representation of this object
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "[MethodName: " + this.getMethodName() + ", DeepestNestingOccurance: " + this.getDeepestNestingOccurance()
                + ", Nesting Threshold: " + this.getNestingThreshold() + ", NestingOccurances: " + this.nestingEntries + "]";
    }
    
    /**
     * compares this Entry to another Entry object
     * based on the largest degree of nesting that 
     * occurred in a method.
     * @param o the Entry object to compare to this Object.
     * @return 1, 0 or -1 dependant on the comparison.
     */
    @Override
    public int compareTo(Entry o) {
        return new CompareToBuilder()
                .append(this.getDeepestNestingOccuranceAmount(), o.getDeepestNestingOccuranceAmount())
                .toComparison();
    }

    @Override
    public String toHTML() {
        return "<table><tr><td>DepthOfNesting Results: </td></tr>"
                + "<tr><td>Method With Most Nesting: "+this.getMethodName()+"</td></tr>"
                + "<tr><td>Biggest Nesting That Occured: "+this.getDeepestNestingOccuranceAmount()+"</td></tr>"
                + "<tr><td>Nesting Threshold: "+this.getNestingThreshold()+"</td></tr>"
                + "<tr><td><span>Source Code Text:</span> </td></tr>"
                + "<tr><td>"+this.getDeepestNestingOccurance().getText()+"</td></tr></table>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        o.put("methodWithMostNesting", this.getMethodName());
        o.put("biggestNestingOccurrence", this.getDeepestNestingOccuranceAmount());
        o.put("nestingThreshold", this.getNestingThreshold());
        o.put("sourceCodeText", this.getDeepestNestingOccurance().getText());
        return o;
    }
    
}
