package org.codeanalyser.language;

/**
 * Used when any Exception occurs when any error occurs using metrics.
 * @author Jack Timblin - U1051575
 */
public class MetricException extends Exception {
    
    /**
     * initialises a new MetricException
     * @param message the message to pass to the Exception
     */
    public MetricException(String message) {
        super(message);
    }
    
}
