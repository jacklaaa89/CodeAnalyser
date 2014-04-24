package org.codeanalyser.metric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.ParserInterface;

/**
 * A container class which hold all of the information
 * on the current file being analysed by the analyser.
 * @author Jack Timblin - U1051575
 */
public class ParserInfo {
    
    private String fileName, sourceLanguage;
    private ParserInterface parser;
    private Lexer lexer;
    private HashMap<Integer, String> tokenTypes;
    
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
        this.readTokenFile();
    }
    
    /**
     * reads the token file associated with the supported listener and parses
     * it to a HashMap.
     * @throws FileNotFoundException if the token file was not found.
     * @throws IOException if the token file could not be read.
     * @throws NumberFormatException if a type found in the file was not a valid number.
     */
    public void readTokenFile() throws FileNotFoundException, IOException, 
            NumberFormatException {
        String lcs = this.sourceLanguage.toLowerCase();
        String ucs = this.sourceLanguage.substring(0, 1).toUpperCase()
                +this.sourceLanguage.substring(1);
        
        //read the token file for the source language.
        File f = new File("src/org/codeanalyser/language/"+lcs+"/"+ucs+".tokens");
        if(f.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while((line = reader.readLine()) != null) {
                if(!line.startsWith("'")) {
                    String[] split = line.split("=");
                    this.tokenTypes.put(Integer.parseInt(split[1]), split[0]);
                }
            }
        }
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
