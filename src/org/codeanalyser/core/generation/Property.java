package org.codeanalyser.core.generation;

import java.util.ArrayList;

/**
 * This class is an object representation of the properties needed for a single class
 * this class is used in Java source code generation.
 * @author Jack Timblin - U1051575
 */
public class Property {
    
    private String source;
    private String packageName;
    private String listenerName;
    
    /**
     * Constructor - used constructor overloading to quickly populate variables.
     * @param packageName the name of package.
     * @param imports a list of import statements this class needs.
     * @param listenerName the name of the listener this class extends.
     */
    public Property(String packageName, String listenerName) {
        this.listenerName = listenerName;
        this.packageName = packageName;
        this.source = packageName;
    }
    
    /**
     * get the package name.
     * @return the string package name
     */
    public String getPackageName() {
        return this.packageName;
    }
    
    /**
     * get the name this class should extend.
     * @return the string name of the listener this class should extend.
     */
    public String getListenerName() {
        return this.listenerName;
    }
    
    /**
     * gets the source language of the file being processed.
     * this is conveniently the same name as the package name
     * @return the language that this listener supports.
     */
    public String getSourceLanguage() {
        return this.source;
    }
    
}
