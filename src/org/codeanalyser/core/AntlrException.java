package org.codeanalyser.core;

/**
 * thrown when an Antlr error occurs.
 */
public class AntlrException extends Exception {
    
    /**
     * initialises a new AntlrException
     * @param message the message to pass to the Exception
     */
    public AntlrException(String message) {
        super(message);
    }

}
