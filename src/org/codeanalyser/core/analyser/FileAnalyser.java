package org.codeanalyser.core.analyser;

import java.io.File;
import java.lang.reflect.Constructor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
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
 */
public class FileAnalyser extends File {
    
    /**
     * Constructor - initialises this object.
     * @param fileLocation - the location of the file to analyse.
     */
    public FileAnalyser(String fileLocation) {
        super(fileLocation);
        
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
        
        if(!Application.getSupportedLanguages().contains(this.getFileExtension())) {
            throw new UnsupportedLanguageException("Unsupported Language");
        }
        
        try {
            String g = this.getFileExtension().toLowerCase();
            String ug = Character.toUpperCase(g.charAt(0)) + g.substring(1);
            Constructor c = Class.forName("org.codeanalyser.language."+g+"."+ug+"Parser").getConstructor(TokenStream.class);
            return (ParserInterface) c.newInstance(stream);
        } catch (Exception e) {
            throw new UnsupportedLanguageException(e.getMessage());
        }
        
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
        
        if(!Application.getSupportedLanguages().contains(this.getFileExtension())) {
            throw new UnsupportedLanguageException("Unsupported Language");
        }
        
        try {
            String g = this.getFileExtension().toLowerCase();
            String ug = Character.toUpperCase(g.charAt(0)) + g.substring(1);
            Constructor c = Class.forName("org.codeanalyser.language."+g+"."+ug+"Lexer").getConstructor(CharStream.class);
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
    public ParseTreeListener getSupportedListener() throws UnsupportedLanguageException
    {
        //check the file extension matches a supported language.
        if(!Application.getSupportedLanguages().contains(this.getFileExtension())) {
            throw new UnsupportedLanguageException(this.getFileExtension()+" is not supported");
        }
        try {
            ParseTreeListener listener = (ParseTreeListener) Class.forName("org.codeanalyser.language."+this.getFileExtension().toLowerCase()+".BaseListener").newInstance();
            ((ListenerInterface)listener).init(this);
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
