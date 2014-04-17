package org.codeanalyser.metric.wmc;

import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.metric.Result;

/**
 * This metric is used to determine the total
 * @author Jack
 */
public class WeightedMethodCount implements MetricInterface {
    
    private int totalClassComplexity = 0;
    private final int complexityThreshold = 50;
    private String fileName, sourceLang;
    private String[] tokens;
    private final String[] complexityTokens = {"DEFAULT", "FOR", "WHILE", "IF", "CASE", "CONTINUE", "CATCH"};

    @Override
    public Result getResults() throws InvalidResultException {
        
        String result = "<table><tr><td>WeightedMethodCount Result: </td></tr>"
                + "<tr><td>Total Amount of Complexity Keywords Found: "+this.totalClassComplexity+"</td></tr>"
                + "<tr><td>Complexity Threshold: "+this.complexityThreshold+"</td></tr></table>";
        
        return Result.newInstance(fileName, sourceLang, this.getClass().getSimpleName(),
                result, totalClassComplexity <= complexityThreshold);
    }

    @Override
    public void start(EventState state) {
        if(state.getEventType().equals(EventType.ENTER_CONSTRUCTOR_DECLARATION) ||
                state.getEventType().equals(EventType.ENTER_METHOD_DECLARATION)) {
            
            //TODO - determine complexity.
            int complexity = 0;
            totalClassComplexity += complexity;
            
        }
    }

    @Override
    public void init(String fileLocation, String sourceLanguage, String[] tokens) throws MetricInitialisationException {
        this.fileName = fileLocation;
        this.sourceLang = sourceLanguage;
        this.tokens = tokens;
    }

    @Override
    public void destroy() {
    }
    
}
