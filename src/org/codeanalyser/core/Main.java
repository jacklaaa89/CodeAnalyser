package org.codeanalyser.core;

/**
 *
 * @author Jack
 */
public class Main {
    
    public static void main(String[] args) {
       try {
           System.out.println(Application.getMetricsList().toString());
       } catch (Exception e) {}
    }
    
}
