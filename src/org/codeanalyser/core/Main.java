package org.codeanalyser.core;

/**
 *
 * @author Jack
 */
public class Main {
    
    public static void main(String[] args) {
       try {
           for(String metric : Application.getMetricsList()) {
               System.out.println(metric);
           }
       } catch (Exception e) {}
    }
    
}
