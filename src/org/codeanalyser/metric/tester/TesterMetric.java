package org.codeanalyser.metric.tester;

import org.antlr.v4.runtime.RuleContext;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.MetricInterface;

/**
 * This class is a tester metric used to test that the generic autoloading of metrics
 * works correctly. It just prints the context to the console when an event is
 * triggered.
 * @author Jack Timblin - U1051575
 */
public class TesterMetric implements MetricInterface {
    
    @Override
    public String getResults() {
        return "";
    }
    
    /**
     * 
     * @param state
     */
    @Override
    public void start(EventState state) {}
    
}