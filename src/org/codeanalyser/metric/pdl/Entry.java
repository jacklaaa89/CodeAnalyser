package org.codeanalyser.metric.pdl;

import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * A single method declaration instance.
 * @author Jack Timblin - U1051575
 */
public class Entry implements Comparable<Entry> {
    
    private String methodDeclaration = "Unknown Method";
    private int lengthOfMethodName = 0;
    private int noOfParameters = 0;
    private String methodName = "Unknown Method";
    private final int lengthThreshold = 20;
    private final int noOfParametersThreshold = 5;
    
    /**
     * get the method declaration.
     * @return the full method declaration.
     */
    public String getMethodDeclaration() {
        return methodDeclaration;
    }
    
    /**
     * sets the method declaration.
     * @param methodDeclaration  the method declaration.
     */
    public void setMethodDeclaration(String methodDeclaration) {
        //format the method declaration.
        this.methodDeclaration = methodDeclaration;
    }
    
    /**
     * gets the length of the method name
     * @return the length of the method name.
     */
    public int getLengthOfMethodName() {
        return lengthOfMethodName;
    }
    
    /**
     * gets the method name
     * @return the method name.
     */
    public String getMethodName() {
        return methodName;
    }
    
    /**
     * sets the method name
     * @param methodName the method name.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
        this.setLengthOfMethodName(methodName.length());
    }
    
    /**
     * sets the length of the method name.
     * @param lengthOfMethodName the length of the method name.
     */
    private void setLengthOfMethodName(int lengthOfMethodName) {
        this.lengthOfMethodName = lengthOfMethodName;
    }
    
    /**
     * gets the number of parameters defined.
     * @return the no of parameters.
     */
    public int getNoOfParameters() {
        return noOfParameters;
    }
    
    /**
     * sets the number of parameters defined.
     * @param noOfParameters the number of parameters.
     */
    public void setNoOfParameters(int noOfParameters) {
        this.noOfParameters = noOfParameters;
    }
    
    /**
     * gets the upper bound for the length of method name.
     * @return the upper bound for method name length.
     */
    public int getLengthThreshold() {
        return lengthThreshold;
    }
    
    /**
     * gets the upper bound for the amount of defined parameters.
     * @return the limit of amount of defined parameters.
     */
    public int getNoOfParametersThreshold() {
        return noOfParametersThreshold;
    }
    
    /**
     * if this entry is within the limits.
     * @return true if it is, false otherwise.
     */
    public boolean isWithinThreshold() {
        return (this.lengthOfMethodName <= this.lengthThreshold && this.noOfParameters <= this.noOfParametersThreshold);
    }
    
    /**
     * returns a string representation of this object
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return "[MethodDeclaration: " + this.getMethodDeclaration() + ", NoOfParameters: " + this.getNoOfParameters()
                 + ", MethodNameLength: " + this.getLengthOfMethodName() + "]";
    }
    
    /**
     * compares this Entry to another Entry object.
     * @param o the Entry to compare this object to.
     * @return 1, 0 or -1 dependant on the comparison.
     */
    @Override
    public int compareTo(Entry o) {
        return new CompareToBuilder()
                .append(this.getLengthOfMethodName(), o.getLengthOfMethodName())
                .append(this.getNoOfParameters(), o.getNoOfParameters())
                .toComparison();
    }
    
}
