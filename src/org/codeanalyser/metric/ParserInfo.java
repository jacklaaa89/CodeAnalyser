package org.codeanalyser.metric;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.ParserInterface;

/**
 * A container class which hold all of the information
 * on the current file being analysed by the analyser.
 * @author Jack Timblin - U1051575
 */
public class ParserInfo {
    
    private final String fileName, sourceLanguage;
    private final ParserInterface parser;
    private final Lexer lexer;
    private HashMap<Integer, String> tokenTypes;
    private final Logger logger;
    
    /**
     * initialises a new ParserInfo object.
     * @param file the file currently being evaluated.
     * @throws FileAnalyser.UnsupportedLanguageException if the supported lexer
     * or parser could not be initialised.
     * @throws FileNotFoundException if the tokens file could not be found.
     * @throws IOException if the token file could not be read.
     * @throws NumberFormatException if a number from the token file is not a valid number.
     */
    public ParserInfo(FileAnalyser file) throws FileAnalyser.UnsupportedLanguageException,
            FileNotFoundException, IOException, NumberFormatException {
        this.fileName = file.getAbsolutePath();
        this.sourceLanguage = file.getSourceLanguage();
        this.lexer = file.getSupportedLexer();
        CommonTokenStream s = new CommonTokenStream(this.lexer);
        this.parser = file.getSupportedParser(s);
        this.tokenTypes = new HashMap<Integer, String>();
        this.logger = Application.getLogger();
        this.getParserTokens();
    }
    
    /**
     * gets the tokens that have been defined by the grammar.
     * @version 1.1 updated to use reflection and read the final 
     * static int fields that are defined in the {SOURCE_LANGUAGE}Parser
     * classes when antlr generates them. This means that it no longer depends
     * on a certain file location.
     */
    public void getParserTokens() {
        Class<?> c = parser.getClass();
        HashMap<Integer, String> tokens = new HashMap<Integer, String>();
        for(Field f : c.getFields()) {
            if(f.getType().getName().equals("int")) {
                if(!f.getName().startsWith("RULE_")) {
                    try {
                        tokens.put(f.getInt(null), f.getName());
                    } catch (IllegalAccessException e) {}
                }
            }
        }
        this.tokenTypes = tokens;
    }
    
    /**
     * returns the generic logger being used in this application.
     * @return get the applications logger.
     */
    public Logger getLogger() {
        return this.logger;
    }
    
    /**
     * gets the absolute file name of the current
     * file being analysed.
     * @return the absolute file name.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * returns the token types for the supported lexer/parser.
     * @return the token types with type as value, int number as key.
     */
    public HashMap<Integer, String> getTokenTypes() {
        return this.tokenTypes;
    }
    
    /**
     * gets the detected source language of the 
     * current file being analysed.
     * @return the detected language.
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }
    
    /**
     * gets the parser that was used to parse the current file.
     * @return the parser.
     */
    public ParserInterface getParser() {
        return parser;
    }
    
    /**
     * gets the lexer that was used on the current file.
     * @return the lexer.
     */
    public Lexer getLexer() {
        return this.lexer;
    }
}
