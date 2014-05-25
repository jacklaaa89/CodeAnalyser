package org.codeanalyser.metric.wmc;

import java.util.Arrays;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.utils.OutputInterface;
import org.codeanalyser.language.EventState;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricAbstract;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;
import org.json.simple.JSONObject;

/**
 * This metric is used to determine the total complexity keywords that are used
 * in a class.
 *
 * @author Jack Timblin - U1051575
 */
public class WeightedMethodCount extends MetricAbstract implements OutputInterface {

    private int totalClassComplexity = 0;
    private final int complexityThreshold = 50;
    
    //events and tokens.
    private final String[] statementEvents = {"ENTER_STATEMENT", "ENTER_SWITCH_BLOCK_STATEMENT_GROUP", "ENTER_CATCH_CLAUSE"};
    private final String[] complexityTokens = {"DEFAULT", "FOR", "WHILE", "IF", "CASE", "CONTINUE", "CATCH"};

    @Override
    public Result getResults() throws InvalidResultException {
        

        return Result.newInstance(this.getClass().getSimpleName(),
                this, totalClassComplexity <= complexityThreshold);
    }

    @Override
    public void onParserEvent(EventState state) {
        if (Arrays.asList(statementEvents).contains(state.getEventType())) {
            this.determineComplexity(state.getContext());
        }
    }

    @Override
    public void init(ParserInfo initialInformation) throws MetricInitialisationException {
    }

    @Override
    public void destroy() {
    }
    
    /**
     * determines the complexity of the class as a whole.
     * @param context the statement context.
     */
    private void determineComplexity(ParserRuleContext context) {
        for(String s : this.complexityTokens) {
            if(s.equalsIgnoreCase(context.getStart().getText().toUpperCase())) {
                this.totalClassComplexity++;
                break;
            }
        }
    }

    @Override
    public String toHTML() {
        return "<table><tr><td>WeightedMethodCount Result: </td></tr>"
                + "<tr><td>Total Amount of Complexity Keywords Found: " + this.totalClassComplexity + "</td></tr>"
                + "<tr><td>Complexity Threshold: " + this.complexityThreshold + "</td></tr></table>";
    }

    @Override
    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        o.put("totalComplexityKeywordsFound", this.totalClassComplexity);
        o.put("threshold", this.complexityThreshold);
        return o;
    }

}