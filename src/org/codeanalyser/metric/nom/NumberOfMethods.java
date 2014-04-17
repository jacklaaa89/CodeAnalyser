package org.codeanalyser.metric.nom;

import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * Simple implementation of the metric that gauges how many methods are in a class.
 * @author Jack Timblin - U1051575
 */
public class NumberOfMethods implements MetricInterface {

    private String fileLocation, sourceLanguage;
    private int noOfMethods = 0;
    private int methodThreshold = 10;
    
    @Override
    public Result getResults() throws InvalidResultException {
        
       StringBuilder builder = new StringBuilder();
       builder.append("<table><tr><td>Number of Methods In Class: ");
       builder.append(this.noOfMethods);
       builder.append("</td></tr><tr><td>Method Threshold: ");
       builder.append(this.methodThreshold);
       builder.append("</td></tr></table>");
        
       return Result.newInstance(fileLocation, sourceLanguage, this.getClass().getSimpleName(),
               builder.toString(), (noOfMethods <= methodThreshold));
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType() == EventType.ENTER_METHOD_DECLARATION) {
            noOfMethods++;
        }
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        this.fileLocation = fileLocation;
        this.sourceLanguage = sourceLanguage;
    }

    @Override
    public void destroy() {
    }
    
}
