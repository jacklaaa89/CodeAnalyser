package org.codeanalyser.core;

/**
 * thrown when an Antlr error occurs.
 */
public class AntlrException extends Exception {

    public AntlrException(String message) {
        super(message);
    }

}
