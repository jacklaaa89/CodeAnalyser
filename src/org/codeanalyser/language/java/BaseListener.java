package org.codeanalyser.language.java;

import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.core.analyser.UnsupportedLanguageException;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricErrorAdapter;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricAbstract;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This class is auto generated when the parser is generated so that 
 * metrics have a single class for each language that listens of all
 * events that are applicable for all metrics being tested.
 *
 * because all metrics implement the MetricInterface interface, 
 * this means that we can just call the start() method in this to invoke 
 * all methods.
 * 
 * {@link MetricInterface.start() }
 * @author Jack Timblin - U1051575 (via StringTemplate4)
 */
public class BaseListener extends JavaBaseListener implements ListenerInterface {

    private ArrayList<MetricAbstract> metrics;
    private FileAnalyser file;


    @Override
    public void init(FileAnalyser file) throws MetricException {
        this.file = file;
        metrics = new ArrayList<MetricAbstract>();
        //initialise the metrics.
        try {
            ParserInfo info = new ParserInfo(file);
            for (String metric : Application.getMetricsList()) {
                MetricAbstract m = (MetricAbstract) Class.forName(metric).newInstance();
                
                try {
                    m.init(info);
                } catch (MetricInitialisationException e) {
                    m.getErrorAdapter().onInitialisationError(e, Application.getLogger(), info);
                    Application.getLogger().log(e);
                }
                metrics.add(m);
            }
        } catch (Exception e) {
            throw new MetricException(e.getMessage());
        }
    }

    /**
     * gathers the results from the metrics in this listener.
     * @return an ArrayList\<Result> of string results from each of the metrics.
     */
    @Override
    public ArrayList<Result> getResults() {
        ArrayList<Result> results = new ArrayList<Result>();
        for(MetricAbstract mi : metrics) {
            Result r = null;
            try {
                r = mi.attachErrors();
                if(r != null) {
                    //set system values.
                    r.setFileName(file.getName());
                    r.setSourceLanguage(file.getSourceLanguage());
                    results.add(r);
                }
            } catch (InvalidResultException e) {
                 try {
                    mi.getErrorAdapter().onInvalidResultException(e, r, Application.getLogger(), new ParserInfo(file));
                } catch (Exception ex) {}
                Application.getLogger().log(e);
            }
        }
        return results;
    }
    
    /**
     * generated method to call enterBlock while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterBlock(JavaParser.BlockContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_BLOCK").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
        
     }
     
    @Override
    public void destroy() {
        //do any more work before destruction.
        for(MetricAbstract metric : metrics) {
            metric.destroy();
        }
    }
     
    
    /**
     * generated method to call enterCatchClause while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterCatchClause(JavaParser.CatchClauseContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CATCH_CLAUSE").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
        
     }
     
     /**
     * generated method to call exitBlock while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void exitBlock(JavaParser.BlockContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("EXIT_BLOCK").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
    
    /**
     * generated method to call enterSwitchBlockStatementGroup while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterSwitchBlockStatementGroup(JavaParser.SwitchBlockStatementGroupContext context) {
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
     public void enterMethodBody(JavaParser.MethodBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_BODY").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
        
     }
     
     @Override
     public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext context) {
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
     public void enterStatement(JavaParser.StatementContext context) {
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
     public void enterMethodDeclaration(JavaParser.MethodDeclarationContext context) {
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
     public void enterClassDeclaration(JavaParser.ClassDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_DECLARATION").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterConstructorDeclaration while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterConstructorDeclaration(JavaParser.ConstructorDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CONSTRUCTOR_DECLARATION").build();
        for(MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }

}