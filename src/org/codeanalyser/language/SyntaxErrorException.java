package org.codeanalyser.language;

import org.codeanalyser.core.analyser.AnalyserException;

/**
 * An Exception that is thrown when A syntax error occured in the
 * parser to lexer while creating a parse tree.
 * @author Jack Timblin - U1051575
 */
public class SyntaxErrorException extends AnalyserException {
    
    /**
     * initialises a new SyntaxErrorException
     * @param message the message to pass to the Exception
     */
    public SyntaxErrorException(String message) {
        super(message);
    }
}
