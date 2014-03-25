package main;

/**
 * this is the interface that all metrics should implement.
 * @author Jack
 */
public interface MetricAbstract {
    
    /**
     * returns the results from this metric, this will
     * be the results when this metric is run on a single class.
     */
    public void getResults();
    
}
