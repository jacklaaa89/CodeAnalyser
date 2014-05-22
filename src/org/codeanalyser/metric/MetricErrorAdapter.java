package org.codeanalyser.metric;

import org.codeanalyser.core.utils.Logger;

/**
 * An Error Adapter that can be used for metrics to customise how they handle error.
 * @author Jack Timblin - U1051575
 */
public interface MetricErrorAdapter {
    
    /**
     * called when a metric cannot be initialised correctly.
     * @param e the exception that was thrown
     * @param logger an instance of the logger.
     */
    public void onInitialisationError(MetricInitialisationException e, Logger logger);
    
    /**
     * called when the result retrieved from a result was invalid.
     * @param e the exception that was thrown.
     * @param result the invalid result.
     * @param logger an instance of the logger.
     */
    public void onInvalidResultException(InvalidResultException e, Result result, Logger logger);
    
}
