package org.codeanalyser.language.java;

import java.util.ArrayList;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.metric.InvalidResultException;
import org.codeanalyser.metric.MetricInitialisationException;
import org.codeanalyser.metric.MetricInterface;
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

    private ArrayList<MetricInterface> metrics;
    private FileAnalyser file;


    @Override
    public void init(FileAnalyser file, String[] tokens) throws MetricException {
        this.file = file;
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            for (String metric : Application.getMetricsList()) {
                MetricInterface m = (MetricInterface) Class.forName(metric).newInstance();
                try {
                    m.init(file.getAbsolutePath(), file.getFileExtension(), tokens);
                } catch (MetricInitialisationException e) {
                    System.out.println("Failed to Initialise Metric: \"" + metric + "\"");
                    continue;
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
    public ArrayList<Result> getResults() throws InvalidResultException {
        ArrayList<Result> results = new ArrayList<Result>();
        for(MetricInterface mi : metrics) {
            try {
                Result r = mi.getResults();
                if(r == null) {
                    throw new InvalidResultException("Result from Metric was not valid.");
                }
                results.add(r);
            } catch (Exception e) {
                throw new InvalidResultException("Result from Metric was not valid.");
            }
        }
        return results;
    }

    /**
     * generated method to call enterMethodBody while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterMethodBody(JavaParser.MethodBodyContext context) {
        //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType(EventType.ENTER_METHOD_BODY).build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
        }
        
     }
     
     @Override
     public void enterVariableDeclaratorId(JavaParser.VariableDeclaratorIdContext context) {
         //build state object.
        EventState.EventStateBuilder builder = new EventState.EventStateBuilder();
        EventState state = builder.setContext(context).setEventType(EventType.ENTER_VARIABLE_DECLARATOR_ID).build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
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
        EventState state = builder.setContext(context).setEventType(EventType.ENTER_METHOD_DECLARATION).build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
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
        EventState state = builder.setContext(context).setEventType(EventType.ENTER_CLASS_DECLARATION).build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
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
        EventState state = builder.setContext(context).setEventType(EventType.ENTER_CONSTRUCTOR_DECLARATION).build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
        }
     }
     

}