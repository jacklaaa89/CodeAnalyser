package org.codeanalyser.metric;

/**
 * Exception thrown by init() in the metric classes when the metric
 * cannot be initialised.
 * @author Jack Timblin - U1051575
 */
public class MetricInitialisationException extends Exception {
    public MetricInitialisationException(String message) {
        super(message);
    }
}
