package org.codeanalyser.core.utils;

import org.json.simple.JSONObject;

/**
 * A simple interface which needs to be implemented by result output objects so we can
 * output in different formats.
 * @author Jack Timblin - U1051575
 */
public interface OutputInterface {
    
    public String toHTML();
    public JSONObject toJSON();
    
}
