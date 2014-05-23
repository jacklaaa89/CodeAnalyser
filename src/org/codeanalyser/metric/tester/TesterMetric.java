package org.codeanalyser.metric.tester;

import java.util.ArrayList;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricError;
import org.codeanalyser.metric.MetricErrorAdapter;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This class is a tester metric used to test that the generic autoloading of metrics
 * works correctly. It just prints the context to the console when an event is
 * triggered.
 * @version 1.1 - This tester metric now implements the MetricErrorAdapter class to 
 * demonstrate that metrics can how handle their own errors.
 * @author Jack Timblin - U1051575
 */
public class TesterMetric implements MetricInterface, MetricErrorAdapter {
    
    @Override
    public Result getResults() throws InvalidResultException {
        TesterMetricError tme = new TesterMetricError();
        ArrayList<MetricError> me = new ArrayList<MetricError>();
        me.add(tme);
        //use the default outputadapter.
        return Result.newInstance(this.getClass().getSimpleName(), null, true, me);
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
    public void onInitialisationError(MetricInitialisationException e, Logger logger, 
            ParserInfo info) {
        logger.log("onInitialisationError() called");
    }

    @Override
    public void onInvalidResultException(InvalidResultException e, Result result,
            Logger logger, ParserInfo info) {
        logger.log("onInvalidResultException() called");
    }
    
}
