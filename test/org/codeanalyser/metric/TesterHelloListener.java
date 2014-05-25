package org.codeanalyser.metric;

import java.util.ArrayList;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.language.hello.HelloBaseListener;
import org.codeanalyser.language.hello.HelloParser;

/**
 * These are replicas of the original BaseListener implementations
 * but this Listener only initialises the tester metric.
 * @author Jack Timblin - U1051575
 */
public class TesterHelloListener extends HelloBaseListener implements ListenerInterface {

    private String fullyQualifiedName = "org.codeanalyser.metric.tester.TesterMetric";
    private ArrayList<MetricAbstract> metrics;
    
    @Override
    public ArrayList<Result> getResults() throws InvalidResultException {
        ArrayList<Result> results = new ArrayList<Result>();
        for(MetricAbstract m : this.metrics) {
            Result r = m.getResults();
            if(r != null) {
                results.add(r);
            }
        }
        return results;
    }

    @Override
    public void init(FileAnalyser file) throws MetricException {
        this.metrics = new ArrayList<MetricAbstract>();
        try {
            //we shall initialise the metric the same way the tool does
            //by using Java's reflection API.
            ParserInfo info = new ParserInfo(file);
            MetricAbstract m = (MetricAbstract) Class.forName(fullyQualifiedName).newInstance();
            
            //call init on the metric.
            m.init(info);
            
            //add it to this listeners metrics.
            metrics.add(m);
        } catch (Exception e) {
            throw new MetricException(e.getMessage());
        }
    }
    
    /**
     * generated method to call enterSwitchBlockStatementGroup while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterSwitchBlockStatementGroup(HelloParser.SwitchBlockStatementGroupContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_SWITCH_BLOCK_STATEMENT_GROUP").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
        
     }

    /**
     * generated method to call enterMethodBody while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterMethodBody(HelloParser.MethodBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_BODY").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
        
     }
     
     @Override
     public void enterVariableDeclaratorId(HelloParser.VariableDeclaratorIdContext context) {
         //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_VARIABLE_DECLARATOR_ID").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
     /**
     * generated method to call enterStatment while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterStatement(HelloParser.StatementContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_STATEMENT").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterMethodDeclaration while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterMethodDeclaration(HelloParser.MethodDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_DECLARATION").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterClassDeclaration while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterClassDeclaration(HelloParser.ClassDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_DECLARATION").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }

    @Override
    public void destroy() {
        //call destroy on all the metrics
        for(MetricAbstract m : this.metrics) {
            m.destroy();
        }
    }
    
}
