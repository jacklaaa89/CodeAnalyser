/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import generation.ClassGeneration;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack
 */
public class Main {
    
    public static void main(String[] args) {
       for(String metricClassName : Application.getMetricsList()) {
           System.out.println(metricClassName);
       }
       for(String lang : Application.getSupportedLanguages()) {
           System.out.println(lang);
       }
    }
    
}
