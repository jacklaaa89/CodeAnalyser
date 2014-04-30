package org.codeanalyser.core.analyser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.codeanalyser.core.Application;
import org.yaml.snakeyaml.Yaml;

/**
 * Used to detect the language of the file using the files extension.
 * This application uses the YAML file used in GitHub Flavoured Markdown to 
 * determine what language is being used.
 * @author Jack Timblin - U1051575
 */
public class LanguageDetect {
    
    private final ArrayList<Language> languages;
    
    /**
     * Initialises the list of languages in the YML file into Language objects.
     */
    @SuppressWarnings("unchecked")
    public LanguageDetect() {
        
        //Initialise variables.
        languages = new ArrayList<Language>();
        Yaml yaml = new Yaml();
        try {
            
            //load the YAML file.
            InputStream input = new FileInputStream(new File(Application.getSystemPath()+"config/languages.yml"));
            
            Map o = (Map) yaml.load(input);
            
            //iterate through the entries and parse each entry into a Language object.
            for(Map.Entry<String, Map> entry : (Set<Map.Entry<String, Map>>)o.entrySet()) {
                
                Map v = entry.getValue();
                ArrayList<String> e = new ArrayList<String>();
                if(v.containsKey("primary_extension")) {
                    e.add(((String)v.get("primary_extension")).substring(1));
                }
                if(v.containsKey("extensions")) {
                    List<String> ex = (List<String>) v.get("extensions");
                    for(String ext : ex) {
                        e.add(ext.substring(1));
                    }
                }
                
                if(!e.isEmpty()) {
                    languages.add(new Language(entry.getKey(), e));
                }
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * returns an array of the the detectable languages supported.
     * @return the detectable languages array.
     */
    public ArrayList<Language> getAllDetectableLanguages() {
        return this.languages;
    }
    
    /**
     * searches the supported languages to determine the source language based on the 
     * file extension of the file being analysed.
     * @param fileExtension the extension of the file being analysed.
     * @return the Language object that matches the file extension or null if
     * the language was not found.
     */
    public Language getSupportedLanguage(String fileExtension) {
        for(Language l : this.getAllDetectableLanguages()) {
            if(l.getExtensions().contains(fileExtension)) {
                return l;
            }
        }
        return null;
    }
    
}
