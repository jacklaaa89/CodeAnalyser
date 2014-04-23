package org.codeanalyser.metric;

import org.codeanalyser.language.ParserInterface;

/**
 * A container class which hold all of the information
 * on the current file being analysed by the analyser.
 * @author Jack Timblin - U1051575
 */
public class ParserInfo {
    
    private String fileName, sourceLanguage;
    private ParserInterface parser;
    
    /**
     * initialises a new ParserInfo object.
     * @param parser the parser that was used to parse the file.
     * @param fileName the absolute filename of the file being analysed.
     * @param sourceLanguage the detected source language of the current file.
     */
    public ParserInfo(ParserInterface parser, String fileName,
            String sourceLanguage) {
        this.fileName = fileName;
        this.parser = parser;
        this.sourceLanguage = sourceLanguage;
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
}
