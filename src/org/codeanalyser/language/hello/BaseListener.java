package org.codeanalyser.language.hello;

import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.core.analyser.UnsupportedLanguageException;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.language.SyntaxErrorException;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricErrorAdapter;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricAbstract;
import org.codeanalyser.metric.ParserInfo;
import org.codeanalyser.metric.Result;

/**
 * This class is auto generated when the parser is generated so that metrics
 * have a single class for each language that listens of all events that are
 * applicable for all metrics being tested.
 *
 * because all metrics implement the MetricInterface interface, this means that
 * we can just call the start() method in this to invoke all methods.
 *
 * {@link MetricInterface.start() }
 *
 * @author Jack Timblin - U1051575 (via StringTemplate4)
 */
public class BaseListener extends HelloBaseListener implements ListenerInterface {

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
            throw new MetricException("An error occured initialising all of the metrics, Error:" + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        for (MetricAbstract metric : metrics) {
            metric.destroy();
        }
    }

    /**
     * gathers the results from the metrics in this listener.
     *
     * @return an ArrayList<Result> of string results from each of the metrics.
     */
    @Override
    public ArrayList<Result> getResults() {
        ArrayList<Result> results = new ArrayList<Result>();
        for (int i = 0; i < metrics.size(); i++) {
            MetricAbstract mi = metrics.get(i);
            Result r = null;
            try {
                r = mi.attachErrors();
                if (r != null) {
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
     * generated method to call enterCompilationUnit while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterCompilationUnit(HelloParser.CompilationUnitContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_COMPILATION_UNIT").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterCatchClause while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterCatchClause(HelloParser.CatchClauseContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CATCH_CLAUSE").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterClassDeclaration while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterClassDeclaration(HelloParser.ClassDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_DECLARATION").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterClassBody while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterClassBody(HelloParser.ClassBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_BODY").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterMethodDeclaration while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterMethodDeclaration(HelloParser.MethodDeclarationContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_DECLARATION").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterMethodBody while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterMethodBody(HelloParser.MethodBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_BODY").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterStatement while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterStatement(HelloParser.StatementContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_STATEMENT").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterMethodCall while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterMethodCall(HelloParser.MethodCallContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_METHOD_CALL").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterInformalParameterList while walking the
     * parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterInformalParameterList(HelloParser.InformalParameterListContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_INFORMAL_PARAMETER_LIST").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterInformalParameters while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterInformalParameters(HelloParser.InformalParametersContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_INFORMAL_PARAMETERS").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterExpression while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterExpression(HelloParser.ExpressionContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_EXPRESSION").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterSwitchBlockStatementGroup while walking the
     * parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterSwitchBlockStatementGroup(HelloParser.SwitchBlockStatementGroupContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_SWITCH_BLOCK_STATEMENT_GROUP").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterSwitchLabel while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterSwitchLabel(HelloParser.SwitchLabelContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_SWITCH_LABEL").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterStringLiteral while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterStringLiteral(HelloParser.StringLiteralContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_STRING_LITERAL").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterClassIdentifier while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterClassIdentifier(HelloParser.ClassIdentifierContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_CLASS_IDENTIFIER").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterParExpression while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterParExpression(HelloParser.ParExpressionContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_PAR_EXPRESSION").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterOperator while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterOperator(HelloParser.OperatorContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_OPERATOR").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterInnerBlock while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterBlock(HelloParser.BlockContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_BLOCK").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call exitBlock while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void exitBlock(HelloParser.BlockContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("EXIT_BLOCK").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterFormalParameterList while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterFormalParameterList(HelloParser.FormalParameterListContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_FORMAL_PARAMETER_LIST").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterFormalParameters while walking the parse
     * tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterFormalParameters(HelloParser.FormalParametersContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_FORMAL_PARAMETERS").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterVariableDeclaratorId while walking the
     * parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterVariableDeclaratorId(HelloParser.VariableDeclaratorIdContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_VARIABLE_DECLARATOR_ID").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterIdentifier while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterIdentifier(HelloParser.IdentifierContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_IDENTIFIER").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

    /**
     * generated method to call enterNumber while walking the parse tree.
     *
     * @param context
     * <p>
     * The context/area of the parse tree that this rule applies to.</p>
     */
    @Override
    public void enterNumber(HelloParser.NumberContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType("ENTER_NUMBER").build();
        for (MetricAbstract metric : metrics) {
            //start the metrics evaluation at this event.
            metric.onParserEvent(state);
        }
    }

}
