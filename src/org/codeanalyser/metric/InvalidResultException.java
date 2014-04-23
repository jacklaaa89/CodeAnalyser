package org.codeanalyser.metric;

/**
 * This exception is thrown in any getResults() method call in a metric when the
 * result is not valid.
 * @author Jack Timblin - U1051575
 */
public class InvalidResultException extends Exception {
    /**
     * intialises a new InvalidResultException
     * @param message the message to pass to the Exception
     */
    public InvalidResultException(String message) {
        super(message);
    }
}
