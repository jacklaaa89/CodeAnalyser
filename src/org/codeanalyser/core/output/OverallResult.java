package org.codeanalyser.core.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import org.codeanalyser.core.Application;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.core.utils.Logger;
import org.codeanalyser.metric.Result;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * An encapsulation on an Overall Result for a single file based on all of the
 * result objects from analysis.
 * @author u1051575
 */
public class OverallResult {

    private final ArrayList<Result> results;
    private final FileAnalyser file;
    private String link = "#";
    
    /**
     * initialises a new OverallResult Object.
     * @param results the results from metric analysis.
     * @param fileName the absolute file name of the file that was analysed.
     * @throws NoResultsDefinedException if there are not results from metric analysis.
     */
    public OverallResult(ArrayList<Result> results, String fileName) throws NoResultsDefinedException {
        this.file = new FileAnalyser(fileName);
        this.results = results;
        if(this.results.isEmpty()) {
            throw new NoResultsDefinedException("No Results defined.");
        }
    }
    
    /**
     * determines if the majority of the metrics passed.
     * @return boolean true if majority passed, false if not.
     */
    public boolean isOverallSuccessful() {
        HashMap<Boolean, Integer> map = new HashMap<Boolean, Integer>();
        for (Result r : this.results) {
            Integer i = map.get(r.isSuccessful());
            map.put(r.isSuccessful(), (i != null) ? (int) i + 1: 1);
        }

        boolean isOverallSuccessful = false;
        if (map.size() > 0) {
            isOverallSuccessful = Collections.max(map.entrySet(),
                    new ResultComparator()).getKey();
        }
        return isOverallSuccessful;
    }
    
    /**
     * generates the subpage for the single file associated with this object.
     * @param outputDestination the directory where output should be stored.
     * @throws TemplateNotFoundException if we could not read or write to the template file.
     */
    public void generateSubPage(File outputDestination)
            throws TemplateNotFoundException {
        String fileName = outputDestination.getAbsolutePath()+ "/" + this.file.getAbsolutePath() + ".html";
        String regex;
        if(File.separator.equals("\\")) {
            regex = "\\";
        } else {
            regex = File.separator;
        }
        fileName = fileName.replace(regex, "-");
        fileName = fileName.replaceAll("[^a-zA-Z0-9\\s-.]", "");
        File f = new File(outputDestination.getAbsolutePath()+"/"+fileName.toLowerCase());

        if (f.exists()) {
            f.delete();
        }

        STGroupFile group = new STGroupFile(Application.getSystemPath()+"antlr/templates/OutputTemplate.stg");
        ST main = group.getInstanceOf("sub");
        
        //build the HTML for each of the metric results.
        StringBuilder builder = new StringBuilder();
        for (Result r : results) {
            builder.append("<tr><td style='border-top:1px solid black;border-bottom:1px solid black;'><span>Metric Name: </span>");
            builder.append(r.getMetricName());
            builder.append("</td><td style='border-top:1px solid black;border-bottom:1px solid black;'><span>Was Successful: </span>");
            builder.append("<span style='color:");
            builder.append(r.isSuccessful() ? "green" : "red");
            builder.append(";'>");
            builder.append(r.isSuccessful() ? "Success" : "Failure");
            builder.append("</span></td></tr><tr><td><span>Comments: </span></td></tr><tr><td><div>");
            
            //clean the inputted HTML from the metric to remove tags which are not allowed.
            Whitelist list = Whitelist.relaxed();
            list.addTags("div", "style", "span");
            String doc = Jsoup.clean(r.getResult(), list);
            
            builder.append(doc);
            builder.append("</div></td></tr>");
            
        }
        
        //add the attributes to the template.
        main.add("html", builder.toString());
        main.add("fileName", this.file.getName());
        main.add("sourceLanguage", this.file.getFileExtension());
        
        //save the output file to disc.
        try (FileWriter writer = new FileWriter(f)) {
            writer.append(main.render());
            writer.flush();
        } catch (IOException e) {
            throw new TemplateNotFoundException("Could not read, or write to template file.");
        }
        
        //set the link of this sub page.
        this.link = f.getAbsolutePath();

    }
    
    /**
     * get the HTML href of the subpage to link to.
     * @return the href of the subpage.
     */
    public String getResultLink() {
        return this.link;
    }
    
    /**
     * gets the filename of the file associated with this object.
     * @return the file name.
     */
    public String getFileName() {
        return this.file.getName();
    }
    
    /**
     * gets the source language that the system interpreted it as.
     * @return the source language.
     */
    public String getSourceLanguage() {
        return this.file.getFileExtension();
    }
    
    /**
     * converts this OverallResult object into a ResultProperty object which is
     * used in the main HTML template file, this function also generates the subpage 
     * which needs to be completed in order to determine the ref link to that page.
     * @param outputDestination the directory where the output should be saved.
     * @return this OverallResult object as a ResultProperty
     * @throws TemplateNotFoundException if we could not generate a sub page.
     */
    public ResultProperty toResultProperty(File outputDestination) throws TemplateNotFoundException {
        this.generateSubPage(outputDestination);           
        return new ResultProperty(this.getResultLink(),
                this.isOverallSuccessful(), 
                this.getFileName(), 
                this.getSourceLanguage());
    }
    
    /**
     * returns this overall result as a JSON object.
     * @return the overall result as a JSON Object.
     */
    public JSONObject toJSON() {
        JSONObject root = new JSONObject();
        root.put("fileName", this.getFileName());
        root.put("sourceLanguage", this.getSourceLanguage());
        root.put("wasOverallSuccessful", this.isOverallSuccessful());
        JSONArray res = new JSONArray();
        for(Result r : this.results) {
            res.add(r.toJSON());
        }
        root.put("results", res);
        return root;
    }
}
