package org.codeanalyser.core.output;

/**
 * Exception class for when a Template gets given metric results for
 * a single file with no Result Objects.
 * @author Jack Timblin - U1051575
 */
public class NoResultsDefinedException extends Exception {
    
    /**
     * initialises a new NoResultsDefinedException
     * @param message the message to pass to the Exception
     */
    public NoResultsDefinedException(String message) {
        super(message);
    }
    
}
