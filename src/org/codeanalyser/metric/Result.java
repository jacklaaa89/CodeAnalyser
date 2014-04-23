package org.codeanalyser.metric;

/**
 * This class is an encapsulation of a result object which contains everything
 * we will need to gauge a result from analysis from a single metric.
 * @author Jack Timblin - U1051575
 */
public class Result {
    
    private String fileName;
    private String sourceLanguage;
    private String metricName;
    private String result;
    private boolean passedMetric;
    
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
     * returns the HTML representation of this result.
     * @return the HTML representation of this result.
     */
    public String getResult() {
        return result;
    }
    
    /**
     * sets the file name that was analysed.
     * @param fileName the file name.
     * @return this object for method chaining.
     */
    private Result setFileName(String fileName) {
        this.fileName = fileName;
        return this;
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
     * sets the source language of the file.
     * @param sourceLanguage the source language for the file
     * @return this object for method chaining.
     */
    private Result setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
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
    private Result setResult(String result) {
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
     * generates a new instance of a Result object.
     * @param fileName the file being evaluated.
     * @param sourceLanguage the source language of the file.
     * @param metricName the name of the metric that carried out the analysis.
     * @param result the HTML result of that analysis to push into the output.
     * @param wasSuccessful if the metric was successful or it failed.
     * @return a new Result object.
     */
    public static Result newInstance(String fileName, String sourceLanguage, 
            String metricName, String result, boolean wasSuccessful) {
        
        return new Result()
        .setFileName(fileName)
        .setMetricName(metricName)
        .setResult(result)
        .setSourceLanguage(sourceLanguage)
        .setIsSuccessful(wasSuccessful);
        
    }
    
}
