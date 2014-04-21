package org.codeanalyser.metric;

/**
 * This exception is thrown in any getResults() method call in a metric when the
 * result is not valid.
 * @author Jack Timblin - U1051575
 */
public class InvalidResultException extends Exception {
    public InvalidResultException(String message) {
        super(message);
    }
}
