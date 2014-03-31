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
 * @author Jack
 */
public class ClassGeneration {
   
    public static void generateBaseListener(String grammarName) throws IOException {
        
        //check that a listener has not already been generated.
        File listenerName = new File("src/org/codeanalyser/language/"+grammarName+"/BaseListener.java");
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
        String m1 = "enterMethodBody"; String m2 = "enterMethodDeclaration";
        String m3 = "enterClassDeclaration";
        methodNames.add(new MethodProperty(m1, grammarName+"Parser."+m1.substring(5)+"Context", MethodProperty.splitCamelCase(m1)));
        methodNames.add(new MethodProperty(m2, grammarName+"Parser."+m2.substring(5)+"Context", MethodProperty.splitCamelCase(m2)));
        methodNames.add(new MethodProperty(m3, grammarName+"Parser."+m3.substring(5)+"Context", MethodProperty.splitCamelCase(m3)));
        
        main.add("methodProperties", methodNames);
        
        //save to a new file.
        FileWriter writer = new FileWriter(new File("src/org/codeanalyser/language/"+grammarName, "BaseListener.java"));
        writer.append(main.render());
        writer.flush();
        writer.close();
        
    }
    
}
