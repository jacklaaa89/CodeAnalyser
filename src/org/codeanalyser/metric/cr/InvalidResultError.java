package org.codeanalyser.metric.cr;

import org.codeanalyser.metric.MetricError;
import org.json.simple.JSONObject;

/**
 * A simple error node for an Invalid Result.
 * @author Jack - U1051575
 */
public class InvalidResultError extends MetricError {

    @Override
    public String toHTML() {
        return "<div>The Result supplied was invalid.</div>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject root = new JSONObject();
        JSONObject o = new JSONObject();
        o.put("message", "The Result supplied was invalid.");
        root.put("invalidResult", o);
        return root;
    }
    
}
