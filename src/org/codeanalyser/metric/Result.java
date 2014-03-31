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

    public String getFileName() {
        return fileName;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getResult() {
        return result;
    }
    
    public Result setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Result setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
        return this;
    }

    public Result setMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }

    public Result setResult(String result) {
        this.result = result;
        return this;
    }
    
    @Override
    public String toString() {
        return "[fileName : \"" + this.getFileName() +"\", sourceLanguage : \"" + this.getSourceLanguage() + "\", " +
               "metricName : \"" + this.getMetricName() + "\", result : \"" + this.getResult() + "\"]";
    }
    
    public static Result newInstance(String fileName, String sourceLanguage, 
            String metricName, String result) {
        
        return new Result()
        .setFileName(fileName)
        .setMetricName(metricName)
        .setResult(result)
        .setSourceLanguage(sourceLanguage);
        
    }
    
}
