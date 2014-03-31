package org.codeanalyser.language;

import java.util.ArrayList;
import org.codeanalyser.core.analyser.FileAnalyser;
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
     */
    public ArrayList<Result> getResults();
    
    /**
     * gives the listener initial values about the file it is processing.
     * @param file the file it is analysing.
     * @throws org.codeanalyser.language.MetricException if any of the metrics could
     * not be initialised.
     */
    public void init(FileAnalyser file) throws MetricException;
    
}
