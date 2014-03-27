package org.codeanalyser.metric;

import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.language.EventState;

/**
 * this is the interface that all metrics should implement.
 * @author Jack
 * @param <T> This is any class that is a context from the parser.
 */
public interface MetricInterface<T extends ParserRuleContext> {
    
    /**
     * returns the results from this metric, this will
     * be the results when this metric is run on a single class.
     * @return the results from running the metric.
     */
    public String getResults();
    
    /**
     * starts this metrics evaluation of a 
     * single class.
     * @param state the state of the event.
     */
    public void start(EventState state);
    
}
