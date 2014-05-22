package org.codeanalyser.metric.tester;

import org.codeanalyser.core.Application;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricErrorAdapter;
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
public class TesterMetric implements MetricInterface, MetricErrorAdapter {
    
    @Override
    public Result getResults() throws InvalidResultException {
        throw new InvalidResultException("Hellppp");
    }
    
    @Override
    public void onParserEvent(EventState state) {
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
        //throw new MetricInitialisationException("Helllpppp-Again");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onInitialisationError(MetricInitialisationException e, Logger logger) {
        logger.log("onInitialisationError() called");
    }

    @Override
    public void onInvalidResultException(InvalidResultException e, Result result, Logger logger) {
        logger.log("onInvalidResultException() called");
    }
    
}
