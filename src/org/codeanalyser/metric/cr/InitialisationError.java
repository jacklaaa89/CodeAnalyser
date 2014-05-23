package org.codeanalyser.metric.cr;

import java.util.Map;
import org.codeanalyser.metric.MetricError;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.ParserInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A custom error node for when an initialisation exception occurs.
 * @author Jack Timblin - U1051575
 */
public class InitialisationError extends MetricError {
    
    private final ParserInfo info;
    private final MetricInitialisationException e; 
    private final String TYPE = this.getClass().getSimpleName();
    
    public InitialisationError(MetricInitialisationException e, ParserInfo info) {
        this.e = e;
        this.info = info;
    }

    @Override
    public String toHTML() {
        return "<div>We Could not correctly determine the Comment Ratio: "+e.getMessage()+"</div>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        JSONObject ex = new JSONObject();
        o.put("type", TYPE);
        ex.put("exceptionType", e.getClass().getSimpleName());
        ex.put("message", e.getMessage());
        o.put("exception", ex);
        o.put("message", "We Could not correctly determine the Comment Ratio");
        o.put("fileName", info.getFileName());
        o.put("sourceLangauge", info.getSourceLanguage());
        JSONArray a = new JSONArray();
        for(Map.Entry<Integer, String> entry : this.info.getTokenTypes().entrySet()) {
            a.add(entry.getValue());
        }
        o.put("lexicalTokensUsed", a);
        return o;
    }
    
}
