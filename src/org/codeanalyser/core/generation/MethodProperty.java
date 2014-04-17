package org.codeanalyser.core.generation;

/**
 * This is an encapsulation of the properties needed for a single
 * method. This class is used in Java source code generation.
 * @author Jack Timblin - U1051575
 */
public class MethodProperty {
    
    private String name;
    private String context;
    private String eventType;
    
    /**
     * Constructor - used constructor overloading to quickly populate variables.
     * @param name the name of method
     * @param context the object type of the context that is used as a parameter
     * @param type the type of event that occured.
     */
    public MethodProperty(String name, String context, String type) {
        this.context = context;
        this.name = name;
        this.eventType = type;
    }
    
    /**
     * gets the name of this method.
     * @return the string name of this method.
     */
    public String getName() {
        return this.name;
    }
    
    public String getEventType() {
        return this.eventType;
    }
    
    public static String splitCamelCase(String str, String replacement)
    {
         String string = str.replaceAll("((^[a-z]+)|([A-Z]{1}[a-z]+)|([A-Z]+(?=([A-Z][a-z])|($))))", "$1"+replacement).toUpperCase().trim();
         return string.substring(0, string.length()-1);
    }
    
    /**
     * gets the object type for the context used as a parameter.
     * @return the string name of the contexts object type.
     */
    public String getContext() {
        return this.context;
    }
    
}
