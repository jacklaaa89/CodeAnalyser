package org.codeanalyser.metric;

import org.json.simple.JSONObject;

/**
 * A encapsulation of a metric error occurrence.
 * @author Jack Timblin - U1051575
 * @param <T> To allow for custom JSONObject implementations.
 */
public abstract class MetricError<T extends JSONObject> {
    
    /**
     * returns this error object as HTML.
     * @return this error object as HTML.
     */
    public abstract String toHTML();
    
    /**
     * returns this Metric error as a JSONObject.
     * @return this metric error as a JSONObject.
     */
    public abstract T toJSON();
    
}
