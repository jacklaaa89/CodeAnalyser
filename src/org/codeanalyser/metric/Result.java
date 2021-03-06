package org.codeanalyser.metric;

import java.util.ArrayList;
import org.codeanalyser.core.utils.OutputInterface;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is an encapsulation of a result object which contains everything
 * we will need to gauge a result from analysis from a single metric.
 * @author Jack Timblin - U1051575
 */
public class Result {
    
    private String fileName;
    private String sourceLanguage;
    private String metricName;
    private OutputInterface result;
    private boolean passedMetric;
    private ArrayList<MetricError> error = new ArrayList<MetricError>();
    private final OutputInterface defaultOutput = new OutputInterface() {

        @Override
        public String toHTML() {
            StringBuilder builder = new StringBuilder();
            builder.append("<table><tr><td><span>");
            builder.append(metricName);
            builder.append(" Results: </span></td></tr><tr><td>No Information Could be Loaded For This Metric.</td></tr></table>");
            return builder.toString();
        }

        @Override
        public JSONObject toJSON() {
            JSONObject o = new JSONObject();
            return o;
        }
        
    };
    
    /**
     * force Result.newInstance use.
     */
    private Result() {}
    
    /**
     * get the metrics that were set by the metric.
     * @return the errors defined by the metric.
     */
    public ArrayList<MetricError> getMetricDefinedErrors() {
        return this.error;
    }
    
    /**
     * returns the file name of the file analysed.
     * @return the file name.
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * returns if the metric deemed that
     * the source file successfully passed its requirements.
     * @return true if success, false if not.
     */
    public boolean isSuccessful() {
        return this.passedMetric;
    }
    
    /**
     * returns the source language of the source file.
     * @return the source language.
     */
    public String getSourceLanguage() {
        return sourceLanguage;
    }
    
    /**
     * returns the name of the metric
     * that performed its analysis.
     * @return the name of the metric.
     */
    public String getMetricName() {
        return metricName;
    }
    
    /**
     * returns the interface to get different outputs of this result.
     * @return the output interface.
     */
    public OutputInterface getResult() {
        return (result == null) 
                ? this.defaultOutput
                : result;
    }
    
    /**
     * sets the file name that was analysed.
     * @param fileName the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * sets if this source file passed the metric or not.
     * @param isSuccessful if the file passed the metric.
     * @return this object for method chaining.
     */
    private Result setIsSuccessful(boolean isSuccessful) {
        this.passedMetric = isSuccessful;
        return this;
    }
    
    /**
     * sets any errors that are the metric handled.
     * @param errors the errors the metric handled.
     * @return this object for method chaining.
     */
    private Result setMetricDefinedErrors(ArrayList<MetricError> errors) {
        this.error = errors;
        return this;
    }
    
    /**
     * sets the source language of the file.
     * @param sourceLanguage the source language for the file
     */
    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }
    
    /**
     * sets the name of the metric that evaluated the file.
     * @param metricName the name of the metric.
     * @return this object for method chaining. 
     */
    private Result setMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }
    
    /**
     * sets the HTML result to inject into the template.
     * @param result the HTML result.
     * @return this object for method chaining.
     */
    private Result setResult(OutputInterface result) {
        this.result = result;
        return this;
    }
    
    /**
     * returns a string representation of this object.
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "[fileName : \"" + this.getFileName() +"\", sourceLanguage : \"" + this.getSourceLanguage() + "\", " +
               "metricName : \"" + this.getMetricName() + "\", result : \"" + this.getResult() + "\"]";
    }
    
    /**
     * returns this object as a JSONObject.
     * @return this object as a JSONObject.
     */
    public JSONObject toJSON() {
        JSONObject root = new JSONObject();
        root.put("metricName", this.getMetricName());
        root.put("passedMetric", this.isSuccessful());
        root.put("result", this.getResult().toJSON());
            JSONArray a = new JSONArray();
            for(MetricError e : this.getMetricDefinedErrors()) {
                a.add(e.toJSON());
            }
            if(a.size() != 0) {
                root.put("metricDefinedErrors", a);
            }
        return root;
    }
    
    /**
     * generates a new instance of a Result object.
     * @param metricName the name of the metric that carried out the analysis.
     * @param result the HTML result of that analysis to push into the output.
     * @param wasSuccessful if the metric was successful or it failed.
     * @return a new Result object.
     */
    public static Result newInstance(String metricName, OutputInterface result, boolean wasSuccessful) {
        
        return new Result()
        .setMetricName(metricName)
        .setResult(result)
        .setIsSuccessful(wasSuccessful);
        
    }
    
    /**
     * generates a new instance of a Result object.
     * @param metricName the name of the metric that carried out the analysis.
     * @param result the HTML result of that analysis to push into the output.
     * @param wasSuccessful if the metric was successful or it failed.
     * @param errors a list of errors to attach to this result if the metric was listening to errors.
     * @return a new Result object.
     */
    protected static Result newInstance(String metricName, OutputInterface result, boolean wasSuccessful,
            ArrayList<MetricError> errors) {
        return Result.newInstance(metricName, result, wasSuccessful)
                .setMetricDefinedErrors(errors);
    }
}
