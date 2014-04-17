package org.codeanalyser.metric.tester;

import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * This class is a tester metric used to test that the generic autoloading of metrics
 * works correctly. It just prints the context to the console when an event is
 * triggered.
 * @author Jack Timblin - U1051575
 */
public class TesterMetric implements MetricInterface {
    
    private String fileLocation, sourceLanguage;
    
    @Override
    public Result getResults() throws InvalidResultException {
        return null;
        //return Result.newInstance(this.fileLocation, this.sourceLanguage, "TesterMetric", "This is a tester result.", true);
    }
    
    @Override
    public void start(EventState state) {}

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        this.fileLocation = fileLocation;
        this.sourceLanguage = sourceLanguage;
    }

    @Override
    public void destroy() {
    }
    
}
