package org.codeanalyser.core.analyser;

import java.io.File;
import java.lang.reflect.Constructor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.apache.commons.io.FilenameUtils;
import org.codeanalyser.core.Application;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.ParserInterface;

/**
 * Adds extensions to the file class which are used to determine
 * additional information on source code files.
 * @author Jack Timblin - U1051575
 * @version 1.1 uses LanguageDetect to determine the source language from the file extension.
 */
public class FileAnalyser extends File {
    
    private LanguageDetect detect;
    private String forcedLanguage;
    
    /**
     * Constructor - initialises this object.
     * @param fileLocation - the location of the file to analyse.
     * @param forcedLanguage - the source language to force to use.
     */
    public FileAnalyser(String fileLocation, String forcedLanguage) {
        this(fileLocation);
        this.forcedLanguage = forcedLanguage;
    }
    
    /**
     * Constructor - initialises this object.
     * @param fileLocation - the location of the file to analyse.
     */
    public FileAnalyser(String fileLocation) {
        super(fileLocation);
        detect = new LanguageDetect();
    }
    
    /**
     * returns the detected source language of this File instance.
     * WARNING - this returns the forced language if it has been set, which
     * may NOT be the actual source language, but this class has to respect
     * arguments passed to the main method.
     * essentially this method gets the source language the analyser will be treating it as.
     * @return the source language of this file or null if not found.
     */
    public String getSourceLanguage() {
        
        if(this.forcedLanguage != null) {
            return this.forcedLanguage;
        }
        
        Language l = this.detect.getSupportedLanguage(this.getFileExtension());
        return (l != null) ? l.getName() : null;
        
    }
    
    /**
     * sets the language to force this analysis to use, 
     * i.e we can force all files to attempt to be parsed by
     * a specific parser, this is obviously faster because
     * auto-detecting a files source code doesn't need to take place.
     * @param forcedLang the source language to force this file to use.
     */
    public void setForcedLanguage(String forcedLang) {
        this.forcedLanguage = forcedLang;
    }
    
    /**
     * gets the basename without the file extension of path.
     * @return the base name or null if an error occured.
     */
    public String getBaseName() {
        //if this file does not exist or is a directory
        if(!this.exists() || this.isDirectory()) {
            return null;
        }
        
        //if this file has no name.
        if(this.getName().equals("")) {
            return null;
        }
        
        //return the basename
        return FilenameUtils.getBaseName(this.getName());
    }
    
    /**
     * returns the extension of this file.
     * @return the file extension or null if this file
     * is a directory or doesn't exist.
     */
    public String getFileExtension() {
        
        //if this file does not exist or is a directory
        if(!this.exists() || this.isDirectory()) {
            return null;
        }
        
        //if this file has no name.
        if(this.getName().equals("")) {
            return null;
        }
        
        //return this files extension.
        return FilenameUtils.getExtension(this.getName());
        
    }
    
    /**
     * returns the Parser instance that is supported by the language of this file.
     * This is done by checking the file extension type of the file and matching it against
     * supported the supported languages.
     * @param stream The stream of tokens provided after lexical analysis has been done.
     * @return a Parser instance in the generic form of a ParserInterface.
     * @throws org.codeanalyser.core.analyser.FileAnalyser.UnsupportedLanguageException if the language was not 
     * supported, the Parser could not be instantiated properly or did not implement ParserInterface. 
     */
    public ParserInterface getSupportedParser(TokenStream stream) throws UnsupportedLanguageException {
        
        String[] names = this.getClassNames();
        
        try {
            Constructor c = Class.forName("org.codeanalyser.language."+names[0]+"."+names[1]+"Parser").getConstructor(TokenStream.class);
            return (ParserInterface) c.newInstance(stream);
        } catch (Exception e) {
            throw new UnsupportedLanguageException(e.getMessage());
        }
        
    }
    
    /**
     * gets the class names of the package and start of the Class signiture for the supported language.
     * @return an array with the classnames prefix.
     * @throws org.codeanalyser.core.analyser.FileAnalyser.UnsupportedLanguageException if the language unknown or unsupported.
     */
    public String[] getClassNames() throws UnsupportedLanguageException {
        
        String g, ug;
        
        if(forcedLanguage == null) {
        
            Language supported = detect.getSupportedLanguage(this.getFileExtension());

            if(supported == null) {
                throw new UnsupportedLanguageException("Unknown Language");
            }

            if(!Application.getSupportedLanguages().contains(supported.getName().toLowerCase())) {
                throw new UnsupportedLanguageException("Unsupported Language");
            }
            
            g = supported.getName().toLowerCase();
            ug = Character.toUpperCase(g.charAt(0)) + g.substring(1);
            
        } else {
            
            g = forcedLanguage.toLowerCase();
            ug = Character.toUpperCase(g.charAt(0)) + g.substring(1);
            
        }
        
        return new String[] {g, ug};
        
    }
    
    /**
     * returns the Lexer instance that is supported by the language of this file.
     * This is done by checking the file extension type of the file and matching it against
     * supported the supported languages.
     * @return An instance of a lexer supported by this language cast to its superclass which can be
     * used generically.
     * @throws org.codeanalyser.core.analyser.FileAnalyser.UnsupportedLanguageException if the language was not 
     * supported or the Lexer could not be instantiated properly. 
     */
    public Lexer getSupportedLexer() throws UnsupportedLanguageException {
        
        String[] names = this.getClassNames();
        
        try {
            
            Constructor c = Class.forName("org.codeanalyser.language."+names[0]+"."+names[1]+"Lexer").getConstructor(CharStream.class);
            return (Lexer) c.newInstance(new ANTLRFileStream(this.getAbsolutePath()));
        } catch (Exception e) {
            throw new UnsupportedLanguageException(e.getMessage());
        }
        
    }
    
    /**
     * returns the parseTreeListener instance that supports this file. This is calculated by 
     * if the file extension matches a package.
     * @return a ParseTreeListener that supports this language
     * @throws org.codeanalyser.core.analyser.FileAnalyser.UnsupportedLanguageException if the language was not 
     * supported or the Listener could not be instantiated properly.
     */
    public ParseTreeListener getSupportedListener(ParserInterface parser) throws UnsupportedLanguageException
    {
        String[] names = this.getClassNames();
        
        try {
            ParseTreeListener listener = (ParseTreeListener) Class.forName("org.codeanalyser.language."+names[0]+".BaseListener").newInstance();
            ((ListenerInterface)listener).init(this, parser);
            return listener;
        } catch (Exception e) {
            throw new UnsupportedLanguageException(e.getMessage());
        }
    }
    
    /**
     * Exception class used when any of the language dependant Objects 
     * are attempted to be accessed but the language is not supported by the
     * system, i.e it does not have a generated Parser/Lexer from a grammar.
     * 
     * @author Jack Timblin - U1051575
     */
    public class UnsupportedLanguageException extends Exception {
        public UnsupportedLanguageException(String message) {
            super(message);
        }
    }
    
}
