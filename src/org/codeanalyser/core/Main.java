package org.codeanalyser.core;

import java.io.File;
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
           
           File file = new File("output");
           
           if(!file.exists()) {
               file.mkdir();
           }
           
           Analyser analyser = new Analyser("test", file.getAbsolutePath());
           analyser.analyse();
       } catch (Exception e) {}
    }
    
}
