package org.codeanalyser.metric;

import org.codeanalyser.language.EventState;

/**
 * this is the interface that all metrics should implement.
 * @author Jack Timblin - U1051575
 */
public interface MetricInterface {
    
    /**
     * returns the results from this metric, this will
     * be the results when this metric is run on a single class.
     * @return the results from running the metric.
     * @throws InvalidResultException if the returned result is invalid.
     */
    public Result getResults() throws InvalidResultException;
    
    /**
     * Called when an event is triggered while walking the parse tree.
     * @param state the state of the event.
     */
    public void onParserEvent(EventState state);
    
    /**
     * give the metric initial information regarding the file
     * it is evaluating.
     * @param initialInformation all the initial information a metric needs.
     * @throws MetricInitialisationException when a fatal error occurs initialising the metric.
     */
    public void init(ParserInfo initialInformation) throws MetricInitialisationException;
    
    /**
     * called after the analysis is completed on a single file, can be used
     * to reset variables etc.
     */
    public void destroy();
    
}
