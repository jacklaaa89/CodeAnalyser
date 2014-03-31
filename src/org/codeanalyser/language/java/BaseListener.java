package org.codeanalyser.language.java;

import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.core.Application;
import java.util.ArrayList;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.EventState;
import org.codeanalyser.language.EventType;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.metric.Result;
import org.codeanalyser.core.analyser.FileAnalyser;

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
    public void init(FileAnalyser file) throws MetricException {
        this.file = file;
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            for (String metric : Application.getMetricsList()) {
                MetricInterface m = (MetricInterface) Class.forName(metric).newInstance();
                m.init(file.getAbsolutePath(), file.getFileExtension());
                metrics.add(m);
            }
        } catch (Exception e) {
            throw new MetricException("An error occured initialising all of the metrics.");
        }
    }

    /**
     * gathers the results from the metrics in this listener.
     * @return an ArrayList<Result> of string results from each of the metrics.
     */
    @Override
    public ArrayList<Result> getResults() {
        ArrayList<Result> results = new ArrayList<Result>();
        for(MetricInterface metric : metrics) {
            results.add(metric.getResults());
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
        EventState state = builder.setContext(context)
        			      .setSourceLanguage("java")
        			      .setEventType(EventType.ENTER_METHOD_BODY)
                                  .setFileName(file.getAbsolutePath())
        			      .build();
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
        EventState state = builder.setContext(context)
        			      .setSourceLanguage("java")
        			      .setEventType(EventType.ENTER_METHOD_DECLARATION)
                                  .setFileName(file.getAbsolutePath())
        			      .build();
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
        EventState state = builder.setContext(context)
        			      .setSourceLanguage("java")
        			      .setEventType(EventType.ENTER_CLASS_DECLARATION)
                                  .setFileName(file.getAbsolutePath())
        			      .build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
        }
     }
     

}