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
    
    /**
     * get the event that was occured.
     * @return the event that was triggered 
     * while walking the parse tree.
     */
    public String getEventType() {
        return this.eventType;
    }
    
    /**
     * Utility method, splits a camelCased string and returns
     * a replacement with the replacement. for example
     * if replacement was _:
     * enterConstructorBody -> ENTER_CONSTRUCTOR_BODY
     * @param str the string to split
     * @param replacement the replacement to use.
     * @return the string with the replacement based where a word is camelCased.
     * @version 1.1 - updated a bug where if no replacements are made the 'replacement'
     * wasn't appended to the end, meaning it was cutting part of the word.
     */
    public static String splitCamelCase(String str, String replacement)
    {
         String string = str.replaceAll("((^[a-z]+)|([A-Z]{1}[a-z]+)|([A-Z]+(?=([A-Z][a-z])|($))))", "$1"+replacement).toUpperCase().trim();
         if(string.endsWith(replacement)) {
             string = string.substring(0, string.length()-1);
         }
         return string;
    }
    
    /**
     * gets the object type for the context used as a parameter.
     * @return the string name of the contexts object type.
     */
    public String getContext() {
        return this.context;
    }
    
}
