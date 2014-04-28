package org.codeanalyser.core;

/**
 * Simple Exception for when errors occur getting Application properties.
 * @author Jack Timblin - U1051575
 */
public class ApplicationException extends Exception {
    /**
     * initialises a new ApplicationException
     * @param message the message.
     */
    public ApplicationException(String message) {
        super(message);
    }
}
