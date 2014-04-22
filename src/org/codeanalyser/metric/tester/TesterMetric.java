package org.codeanalyser.metric.tester;

import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This class is a tester metric used to test that the generic autoloading of metrics
 * works correctly. It just prints the context to the console when an event is
 * triggered.
 * @author Jack Timblin - U1051575
 */
public class TesterMetric implements MetricInterface {
    
    @Override
    public Result getResults() throws InvalidResultException {
        return null;
    }
    
    @Override
    public void onParserEvent(EventState state) {}

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {}

    @Override
    public void destroy() {}
    
}
