package org.codeanalyser.core.analyser;

/**
 * Exception that is thrown when Analyser errors occur.
 * @author Jack Timblin - U1051575
 */
public class AnalyserException extends Exception {
    
    /**
     * initialises a new AnalyserException
     * @param message the message to pass to the Exception
     */
    public AnalyserException(String message) {
        super(message);
    }
    
}
