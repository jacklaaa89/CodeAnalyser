package org.codeanalyser.core.analyser;

import java.util.ArrayList;

/**
 * An encapsulation of a detected language.
 * @author Jack Timblin - U1051575
 */
public class Language {
    
    private String name;
    
    private ArrayList<String> extensions;
    
    /**
     * Initialises a language object.
     * @param name the name of the language
     * @param extensions a list of file extensions this language uses.
     */
    public Language(String name, ArrayList<String> extensions) {
        this.name = name;
        this.extensions = extensions;
    }
    
    /**
     * returns the name of this language
     * @return the name of this language.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * returns the list of extensions that this language uses
     * in its files.
     * @return the list of extensions.
     */
    public ArrayList<String> getExtensions() {
        return this.extensions;
    }
    
    /**
     * Overrides toString in Object, returns a human readable representation of this object.
     * @return a string version of this object.
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Extensions: " + this.getExtensions().toString();
    }
    
}
