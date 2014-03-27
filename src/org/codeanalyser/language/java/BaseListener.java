package org.codeanalyser.language.java;

import org.codeanalyser.metric.MetricInterface;
import org.codeanalyser.core.Application;
import java.util.ArrayList;
import org.codeanalyser.language.MetricException;
import org.codeanalyser.language.EventState;

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
public class BaseListener extends JavaBaseListener {

    private ArrayList<MetricInterface> metrics;

    /**
     * Initialises all of the metrics that will be used with this listener.
     * @throws org.codeanalyser.language.MetricException
     */
    public BaseListener() throws MetricException {
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            for(String metric : Application.getMetricsList()) {
                metrics.add((MetricInterface) Class.forName(metric).newInstance());
            }
         } catch (Exception e) {
             throw new MetricException("An error occured initialising all of the metrics.");
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
        EventState state = builder.setContext(context).setSourceLanguage("java").build();
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
        EventState state = builder.setContext(context).setSourceLanguage("java").build();
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
        EventState state = builder.setContext(context).setSourceLanguage("java").build();
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(state);
        }
     }
     

}