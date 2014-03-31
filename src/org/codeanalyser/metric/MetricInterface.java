package org.codeanalyser.metric;

import org.codeanalyser.language.EventState;

/**
 * this is the interface that all metrics should implement.
 * @author Jack
 */
public interface MetricInterface {
    
    /**
     * returns the results from this metric, this will
     * be the results when this metric is run on a single class.
     * @return the results from running the metric.
     */
    public Result getResults();
    
    /**
     * starts this metrics evaluation of a 
     * single class.
     * @param state the state of the event.
     */
    public void start(EventState state);
    
    /**
     * give the metric initial information regarding the file
     * it is evaluating.
     * @param fileLocation the absolute file location for the file being evaluated.
     * @param sourceLanguage the source language of the file.
     */
    public void init(String fileLocation, String sourceLanguage);
    
}
