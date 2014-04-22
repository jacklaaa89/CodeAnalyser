package org.codeanalyser.core.generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;
/**
 * this class will be used to generate a listener for a 
 * given language which uses all of the installed metrics.
 * @author Jack Timblin - U1051575
 * @version 1.1 Added rule names to constructor so that the generator generates
 * metric calls for all rules in the grammar.
 * @version 1.1.1 Updated bug in the way the grammar saved the new file.
 */
public class ClassGeneration {
    
    /**
     * Generates a BaseListener instance for a specified grammar.
     * @param grammarName the name of the grammar.
     * @param ruleNames the rules the grammar contains, these will be used
     * to generate enter{RULE_NAME} methods so that metrics get called at every point.
     * @throws IOException if the file does not exist or cannot be saved.
     */
    public static void generateBaseListener(String grammarName, String[] ruleNames) throws IOException {
        
        //check that a listener has not already been generated.
        File listenerName = new File("src/org/codeanalyser/language/"+grammarName.toLowerCase()+"/BaseListener.java");
        if(listenerName.exists()) {
            return;
        }
        
        //generate the java class.
        STGroupFile groupFile = new STGroupFile("antlr/templates/ListenerGenerator.stg");
        ST main = groupFile.getInstanceOf("main");
        
        //sort class properties.
        main.add("property", new Property(grammarName.toLowerCase(), grammarName+"BaseListener"));
        
        //sort class variables.
        ArrayList<VariableProperty> vars = new ArrayList<VariableProperty>();
        vars.add(new VariableProperty("metrics", "ArrayList<MetricInterface>"));
        vars.add(new VariableProperty("file", "FileAnalyser"));
        
        main.add("variables", vars);
        
        //sort the methods.
        ArrayList<MethodProperty> methodNames = new ArrayList<MethodProperty>();
        
        for(String m : ruleNames) {
            String ms = (m.startsWith("enter")) ? m.substring(5) : m;
            ms = ms.substring(0, 1).toUpperCase()+ms.substring(1);
            m = "enter"+m.substring(0, 1).toUpperCase()+m.substring(1);
            methodNames.add(new MethodProperty(m, grammarName+"Parser."+ms+"Context", MethodProperty.splitCamelCase(m, "_")));
        }
        
        main.add("methodProperties", methodNames);
        
        //save to a new file.
        FileWriter writer = new FileWriter(new File("src/org/codeanalyser/language/"+grammarName.toLowerCase(), "BaseListener.java"));
        writer.append(main.render());
        writer.flush();
        writer.close();
        
    }
    
}
