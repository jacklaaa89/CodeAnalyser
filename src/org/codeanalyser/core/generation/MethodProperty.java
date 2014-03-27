package org.codeanalyser.core.generation;

/**
 * This is an encapsulation of the properties needed for a single
 * method. This class is used in Java source code generation.
 * @author Jack Timblin - U1051575
 */
public class MethodProperty {
    
    private String name;
    private String context;
    
    /**
     * Constructor - used constructor overloading to quickly populate variables.
     * @param name the name of method
     * @param context the object type of the context that is used as a parameter
     */
    public MethodProperty(String name, String context) {
        this.context = context;
        this.name = name;
    }
    
    /**
     * gets the name of this method.
     * @return the string name of this method.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * gets the object type for the context used as a parameter.
     * @return the string name of the contexts object type.
     */
    public String getContext() {
        return this.context;
    }
    
}
