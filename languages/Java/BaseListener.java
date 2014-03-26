package Java;

import main.MetricInterface;
import main.Application;
import java.util.ArrayList;
import core.MetricException;

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
     * @throws core.MetricException
     */
    public BaseListener() throws MetricException {
        metrics = new ArrayList<MetricInterface>();
        //initialise the metrics.
        try {
            for(String metric : Application.getMetricList()) {
                metrics.add((MetricInterface) Class.forName("metrics."+metric).newInstance());
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
        //start the metrics.
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(context, "Java");
        }
     }
     
    /**
     * generated method to call enterMethodDeclaration while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterMethodDeclaration(JavaParser.MethodDeclarationContext context) {
        //start the metrics.
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(context, "Java");
        }
     }
     
    /**
     * generated method to call enterClassDeclaration while walking the parse tree.
     * @param context <p>The context/area of the parse tree that this rule applies to.</p>
     */
     @Override
     public void enterClassDeclaration(JavaParser.ClassDeclarationContext context) {
        //start the metrics.
        for(MetricInterface metric : metrics) {
            //start the metrics evaluation at this event.
            metric.start(context, "Java");
        }
     }
}