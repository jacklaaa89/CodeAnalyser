package org.codeanalyser.metric;

import java.util.ArrayList;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.language.EventState;
import org.json.simple.JSONObject;

/**
 * this is the interface that all metrics should implement.
 * @author Jack Timblin - U1051575
 */
public abstract class MetricAbstract  {
    
    private final ArrayList<MetricError> errorContainer = new ArrayList<MetricError>();
    private MetricErrorAdapter errorHandler;
    private final MetricErrorAdapter defaultErrorHandler = new MetricErrorAdapter() {
        
        @Override
        public void onInitialisationError(MetricInitialisationException e, Logger logger,
                ParserInfo info) {
            errorContainer.add(new DefaultMetricError(e));
        }

        @Override
        public void onInvalidResultException(InvalidResultException e, Result result,
                Logger logger, ParserInfo info) {
            errorContainer.add(new DefaultMetricError(e));
        }
    };

    /**
     * returns the results from this metric, this will
     * be the results when this metric is run on a single class.
     * @return the results from running the metric.
     * @throws InvalidResultException if the returned result is invalid.
     */
    public abstract Result getResults() throws InvalidResultException;
    
    /**
     * Called when an event is triggered while walking the parse tree.
     * @param state the state of the event.
     */
    public abstract void onParserEvent(EventState state);  
    
    /**
     * give the metric initial information regarding the file
     * it is evaluating.
     * @param info all the initial information a metric needs.
     * @throws MetricInitialisationException when a fatal error occurs initialising the metric.
     */
    public abstract void init(ParserInfo info) throws MetricInitialisationException;
    
    /**
     * called after the analysis is completed on a single file, can be used
     * to reset variables etc.
     */
    public abstract void destroy();
    
    /**
     * returns the error adapter defined by the developer or the default one otherwise.
     * @return the ErrorAdapter to use for this metric.
     */
    public MetricErrorAdapter getErrorAdapter() {
        return (this.errorHandler != null) 
                ? this.errorHandler
                : this.defaultErrorHandler;
    }
    
    /**
     * sets an error adapter to use for this class.
     * @param adapter the adapter to use for this class.
     */
    public void setErrorAdapter(MetricErrorAdapter adapter) {
        this.errorHandler = adapter;
    }
    
    /**
     * gets the list of errors that were recorded.
     * @return the list of errors that were recorded.
     */
    public ArrayList<MetricError> getErrors() {
        return this.errorContainer;
    }
    
    /**
     * gets the absolute result for the metric.
     * @return the absolute result.
     * @throws InvalidResultException if the result is invalid. 
     */
    public Result getAbsoluteResult() throws InvalidResultException {
        Result r = this.getResults();
        return Result.newInstance(r.getMetricName(), r.getResult(), r.isSuccessful(), errorContainer);
    }
    
    /**
     * Default Metric Error class which just returns the Exception message
     * in both formats.
     */
    private class DefaultMetricError extends MetricError {
        
        private final Exception e;
        
        public DefaultMetricError(Exception e) {
            this.e = e;
        }

        @Override
        public String toHTML() {
            return e.getMessage();
        }

        @Override
        public JSONObject toJSON() {
            JSONObject o = new JSONObject();
            o.put("exceptionOccured", e);
            return o;
        }
        
    }
    
}
