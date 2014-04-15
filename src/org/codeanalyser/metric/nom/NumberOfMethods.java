package org.codeanalyser.metric.nom;

import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * Simple implementation of the metric that gauges how many methods are in a class.
 * @author Jack Timblin - U1051575
 */
public class NumberOfMethods implements MetricInterface {

    private String fileLocation, sourceLanguage;
    private int noOfMethods;
    private int methodThreshold = 10;
    private String[] languageTokens;
    
    @Override
    public Result getResults() {
       return Result.newInstance(fileLocation, sourceLanguage, this.getClass().getSimpleName(),
               "No of Methods in class: " + noOfMethods, (noOfMethods <= methodThreshold));
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType() == EventType.ENTER_METHOD_DECLARATION) {
            noOfMethods++;
        }
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) {
        this.fileLocation = fileLocation;
        this.sourceLanguage = sourceLanguage;
        this.noOfMethods = 0;
        this.languageTokens = tokens;
    }
    
}
