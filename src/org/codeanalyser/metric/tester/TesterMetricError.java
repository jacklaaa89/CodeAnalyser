package org.codeanalyser.metric.tester;

import org.codeanalyser.metric.MetricError;
import org.json.simple.JSONObject;

/**
 * Tester metric error implementation.
 * @author Jack Timblin - U1051575
 */
public class TesterMetricError extends MetricError {

    @Override
    public String toHTML() {
        return "<div>Tester123</div>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject root = new JSONObject();
        root.put("Tester", "123");
        return root;
    }
    
}
