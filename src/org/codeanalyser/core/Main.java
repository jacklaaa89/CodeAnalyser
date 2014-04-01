package org.codeanalyser.core;

import org.codeanalyser.core.analyser.Analyser;

/**
 *
 * @author Jack
 */
public class Main {
    
    public static void main(String[] args) {
       try {
           //LanguageHelper helper = new LanguageHelper();
           //helper.initLanguages();
           Analyser analyser = new Analyser("test");
           analyser.analyse();
       } catch (Exception e) {}
    }
    
}
