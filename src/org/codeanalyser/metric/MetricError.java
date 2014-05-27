package org.codeanalyser.metric;

import org.codeanalyser.core.utils.OutputInterface;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * A encapsulation of a metric error occurrence.
 * @author Jack Timblin - U1051575
 * @param <T> To allow for custom JSONObject implementations.
 */
public abstract class MetricError<T extends JSONObject> implements OutputInterface {
    
    private final Whitelist whitelist = Whitelist.relaxed();
    
    /**
     * sets the HTML that are allowed.
     */
    public MetricError() {
        whitelist.addTags("div", "style", "span");
    }
    
    /**
     * returns this metric error as cleaned HTML code.
     * @return the HTML after it has been cleaned.
     */
    public String cleanHTML() {
        return Jsoup.clean(this.toHTML(), whitelist);
    }
    
    /**
     * returns this error object as HTML.
     * @return this error object as HTML.
     */
    @Override
    public abstract String toHTML();
    
    /**
     * returns this Metric error as a JSONObject.
     * @return this metric error as a JSONObject.
     */
    @Override
    public abstract T toJSON();
    
}
