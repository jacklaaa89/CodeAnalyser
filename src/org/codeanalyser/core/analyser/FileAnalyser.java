/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * @return a ParseTreeListener that supports this language or null if one was not not found.
     * @throws org.codeanalyser.core.analyser.FileAnalyser.UnsupportedLanguageException 
     */
    public ParseTreeListener getSupportedListener() throws UnsupportedLanguageException 
    {
        //check the file extension matches a supported language.
        if(!Application.getSupportedLanguages().contains(this.getFileExtension())) {
            throw new UnsupportedLanguageException(this.getFileExtension()+" is not supported");
        }
        
        //initialise the listener
        ParseTreeListener listener;
        try {
            listener = (ParseTreeListener) Class.forName("org.codeanalyser.language."+this.getFileExtension().toLowerCase()+".BaseListener").newInstance();
        } catch (ClassNotFoundException e) {
            throw new UnsupportedLanguageException("BaseListener was not found.");
        } catch (ClassCastException e) {
            throw new UnsupportedLanguageException("Found class could not be Cast to class ParseTreeListener");
        } catch(InstantiationException e) {
            throw new UnsupportedLanguageException("Could not initialise BaseListener");
        } catch (IllegalAccessException e) {
            throw new UnsupportedLanguageException(e.getMessage());
        }
        
        return listener;
    }
    
    public class UnsupportedLanguageException extends Exception {
        public UnsupportedLanguageException(String message) {
            super(message);
        }
    }
    
}
