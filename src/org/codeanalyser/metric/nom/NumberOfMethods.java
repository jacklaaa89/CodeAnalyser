package org.codeanalyser.metric.nom;

import org.codeanalyser.core.utils.OutputInterface;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;
import org.json.simple.JSONObject;

/**
 * Simple implementation of the metric that gauges how many methods are in a class.
 * @author Jack Timblin - U1051575
 */
public class NumberOfMethods implements MetricInterface, OutputInterface {

    private int noOfMethods = 0;
    private final int methodThreshold = 10;
    
    @Override
    public Result getResults() throws InvalidResultException {
        
       
        
       return Result.newInstance(this.getClass().getSimpleName(),
               this, (noOfMethods <= methodThreshold));
    }

    @Override
    public void onParserEvent(EventState state) {
        if(state.getEventType().equals("ENTER_METHOD_DECLARATION")) {
            noOfMethods++;
        }
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String toHTML() {
       StringBuilder builder = new StringBuilder();
       builder.append("<table><tr><td>Number of Methods In Class: ");
       builder.append(this.noOfMethods);
       builder.append("</td></tr><tr><td>Method Threshold: ");
       builder.append(this.methodThreshold);
       builder.append("</td></tr></table>");
       return builder.toString();
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        o.put("noOfMethods", this.noOfMethods);
        o.put("threshold", this.methodThreshold);
        return o;
    }
    
}
