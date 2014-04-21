package org.codeanalyser.metric;

import org.codeanalyser.language.ParserInterface;

/**
 *
 * @author jack
 */
public class ParserInformation {
    
    private String fileName, sourceLanguage;
    private ParserInterface parser;
    
    public ParserInformation(ParserInterface parser, String fileName,
            String sourceLanguage) {
        this.fileName = fileName;
        this.parser = parser;
        this.sourceLanguage = sourceLanguage;
    }
    
    public String getFileName() {
        return fileName;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public ParserInterface getParser() {
        return parser;
    }
}
