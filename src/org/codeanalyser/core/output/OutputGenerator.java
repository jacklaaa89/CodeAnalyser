/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codeanalyser.core.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.codeanalyser.metric.Result;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 *
 * @author jack
 */
public class OutputGenerator {

    private File outputDestination;

    public OutputGenerator(String destination) {
        this.outputDestination = new File(destination);
    }

    private class OverallResult {

        private ArrayList<Result> results;
        private String fileName, sourceLanguage, simpleFileName, link;

        public OverallResult(ArrayList<Result> results, String fileName,
                String sourceLanguage, File outputDestination) throws TemplateNotFoundException {
            this.fileName = fileName;
            this.results = results;
            this.sourceLanguage = sourceLanguage;
            String[] nameparts = this.fileName.split("/");
            if(nameparts.length > 0) {
                this.simpleFileName = nameparts[nameparts.length-1];
            }
            this.generateSubPage(outputDestination);
        }

        public boolean isOverallSuccessful() {
            HashMap<Boolean, Integer> map = new HashMap<Boolean, Integer>();
            for (Result r : this.results) {
                Integer i = map.get(r.isSuccessful());
                map.put(r.isSuccessful(), (i != null) ? (int) i++ : 0);
            }

            boolean isOverallSuccessful = false;
            if (map.size() > 0) {
                isOverallSuccessful = Collections.max(map.entrySet(),
                    new Comparator<Map.Entry<Boolean, Integer>>() {
                            
                        @Override
                        public int compare(Entry<Boolean, Integer> o1, Entry<Boolean, Integer> o2) {
                            return o1.getValue().compareTo(o2.getValue());
                        }
                        
                    }
                ).getKey();
            }
            return isOverallSuccessful;
        }
        
        private void generateSubPage(File outputDestination)
            throws TemplateNotFoundException {
            
            
            File f = new File(outputDestination + "/output-"+this.simpleFileName+".html");
            
            if(f.exists()) {
                f.delete();
            }
            
            STGroupFile group = new STGroupFile("antlr/templates/OutputTemplate.stg");
            ST main = group.getInstanceOf("sub");
            
            StringBuilder builder = new StringBuilder();
            for(Result r : results) {
                builder.append("<tr>");
                builder.append("<td><span>Metric Name: </span>");
                builder.append(r.getMetricName()+"</td></tr>");
            }
            
            main.add("html", builder.toString());
            main.add("fileName", this.fileName);
            main.add("sourceLanguage", this.sourceLanguage);
            
            try {
                FileWriter writer = new FileWriter(f);
                writer.append(main.render());
                writer.flush();
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
                
            this.link = f.getAbsolutePath();
            
        }
        
        public String getResultLink() {
            return this.link;
        }
        
        public String getFileName() {
            return this.fileName;
        }
        
        public String getSourceLanguage() {
            return this.sourceLanguage;
        }
    }

    /**
     * Uses the a main template output file to push the HTML from the result
     * objects passed from the metrics and then saves the file to disc.
     *
     * @throws NoResultsDefinedException when there are no results to output.
     * @throws TemplateNotFoundException when the main HTML cannot be located.
     * @throws HtmlParserException when a result string cannot be parsed into
     * HTML.
     */
    public void generateOutput(ArrayList<ArrayList<Result>> results, ArrayList<String> unsupportedFiles) throws NoResultsDefinedException,
            TemplateNotFoundException,
            HtmlParserException {

        if (!this.outputDestination.exists() || !this.outputDestination.isDirectory()) {
            throw new TemplateNotFoundException("Output Location does not exist or is not a directory");
        }
        
        if(results.isEmpty()) {
            throw new NoResultsDefinedException("No Results Defined From Running The Metrics.");
        }
        
        HashMap<String, OverallResult> overallResults = new HashMap<String, OverallResult>();
        
        //create a overall result for each individual result.
        for(ArrayList<Result> r : results) {
            if(!r.isEmpty()) {
                Result re = r.get(0); //get the first entry.
                if(re != null) {
                    OverallResult or = overallResults.get(re.getFileName());
                    if(or == null) {
                        overallResults.put(re.getFileName(),
                                new OverallResult(r, re.getFileName(), re.getSourceLanguage(), this.outputDestination));
                    }
                }
            }
        }
        
        //foreach of the overallResult entries
        //make a single ResultProperty entry to pass to ST.
        
        ArrayList<ResultProperty> res = new ArrayList<ResultProperty>();
        
        for(Map.Entry<String, OverallResult> entry : overallResults.entrySet()) {
            OverallResult r = entry.getValue();
            res.add(new ResultProperty(r.getResultLink(), r.isOverallSuccessful(),
                    r.getFileName(), r.getSourceLanguage()));
        }

        STGroupFile group = new STGroupFile("antlr/templates/OutputTemplate.stg");
        ST main = group.getInstanceOf("main");

        main.add("unsupportedFiles", unsupportedFiles);
        main.add("hasUnsupported", (unsupportedFiles.size() > 0));
        main.add("results", res);

        File output = new File(this.outputDestination.getAbsolutePath() + "/output.html");

        if (output.exists()) {
            output.delete();
        }

        try {
            FileWriter writer = new FileWriter(output);
            writer.append(main.render());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new TemplateNotFoundException("Could not open, read or write output destination.");
        }
    }

}
