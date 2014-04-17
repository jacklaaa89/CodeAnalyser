package org.codeanalyser.language;

import java.util.ArrayList;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.Result;

/**
 * This interface provides access to generic methods that should be
 * available in a BaseListener, but obviously because we are using the 
 * ParseTreeListener which is the superclass of all BaseListener implementations
 * and this class does not have our generic methods.
 * 
 * @author Jack Timblin - U1051575
 */
public interface ListenerInterface {
    
    /**
     * get the results from all of the attached metrics.
     * @return ArrayList<Result> the results.
     * @throws org.codeanalyser.metric.InvalidResultException if a supplied result from a metric is 
     * not valid.
     */
    public ArrayList<Result> getResults() throws InvalidResultException;
    
    /**
     * gives the listener initial values about the file it is processing.
     * @param file the file it is analysing.
     * @throws org.codeanalyser.language.MetricException if any of the metrics could
     * not be initialised.
     */
    public void init(FileAnalyser file, String[] tokens) throws MetricException;
    
    /**
     * called after this listener has finished analysing a single file, 
     * used so metrics can be reset.
     */
    public void destroy();
    
}
