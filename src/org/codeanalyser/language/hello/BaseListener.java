package org.codeanalyser.language.hello;

import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.core.Application;
import java.util.ArrayList;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.metric.Result;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.language.ParserInterface;

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
public class BaseListener extends HelloBaseListener implements ListenerInterface {

    private ArrayList<MetricInterface> metrics;
    private FileAnalyser file;


    @Override
    public void init(FileAnalyser file, ParserInterface parser) throws MetricException {
        this.file = file;
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            ParserInfo info = new ParserInfo(file);
            for (String metric : Application.getMetricsList()) {
                MetricInterface m = (MetricInterface) Class.forName(metric).newInstance();
                try {
                    m.init(info);
                } catch (MetricInitialisationException e) {
                    System.out.println("Failed to Initialise Metric: \"" + metric + "\", Error: " + e.getMessage());
                    continue;
                }
                metrics.add(m);
            }
        } catch (Exception e) {
            throw new MetricException("An error occured initialising all of the metrics, Error:" + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        for(MetricInterface metric : metrics) {
            metric.destroy();
        }
    }

    /**
     * gathers the results from the metrics in this listener.
     * @return an ArrayList<Result> of string results from each of the metrics.
     */
    @Override
    public ArrayList<Result> getResults() {
        ArrayList<Result> results = new ArrayList<Result>();
        for(int i = 0; i < metrics.size(); i++) {
            MetricInterface mi = metrics.get(i);
            try {
                Result r = mi.getResults();
                if(r != null) {
                    results.add(r);
                }
            } catch (InvalidResultException e) {
                System.out.println(e.getMessage());
            }
        }
        return results;
    }

    /**
     * generated method to call enterCompilationUnit while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterCompilationUnit(HelloParser.CompilationUnitContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_COMPILATION_UNIT").build();
        for(MetricInterface metric : metrics) {
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
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterClassBody while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterClassBody(HelloParser.ClassBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_BODY").build();
        for(MetricInterface metric : metrics) {
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
        for(MetricInterface metric : metrics) {
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
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterStatement while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterStatement(HelloParser.StatementContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_STATEMENT").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterMethodCall while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterMethodCall(HelloParser.MethodCallContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_CALL").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterInformalParameterList while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterInformalParameterList(HelloParser.InformalParameterListContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_INFORMAL_PARAMETER_LIST").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterInformalParameters while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterInformalParameters(HelloParser.InformalParametersContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_INFORMAL_PARAMETERS").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterExpression while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterExpression(HelloParser.ExpressionContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_EXPRESSION").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
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
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterSwitchLabel while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterSwitchLabel(HelloParser.SwitchLabelContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_SWITCH_LABEL").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterStringLiteral while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterStringLiteral(HelloParser.StringLiteralContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_STRING_LITERAL").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterClassIdentifier while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterClassIdentifier(HelloParser.ClassIdentifierContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_IDENTIFIER").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterParExpression while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterParExpression(HelloParser.ParExpressionContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_PAR_EXPRESSION").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterOperator while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterOperator(HelloParser.OperatorContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_OPERATOR").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterInnerBlock while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterInnerBlock(HelloParser.InnerBlockContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_INNER_BLOCK").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterFormalParameterList while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterFormalParameterList(HelloParser.FormalParameterListContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_FORMAL_PARAMETER_LIST").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterFormalParameters while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterFormalParameters(HelloParser.FormalParametersContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_FORMAL_PARAMETERS").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterVariableDeclaratorId while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterVariableDeclaratorId(HelloParser.VariableDeclaratorIdContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_VARIABLE_DECLARATOR_ID").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterIdentifier while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterIdentifier(HelloParser.IdentifierContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_IDENTIFIER").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     
    /**
     * generated method to call enterNumber while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterNumber(HelloParser.NumberContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_NUMBER").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
     }
     

}