package org.codeanalyser.language;

import java.util.ArrayList;

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
     * @return ArrayList<String> the results.
     */
    public ArrayList<String> getResults();
    
}
