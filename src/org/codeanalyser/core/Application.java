package org.codeanalyser.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.language.java.JavaBaseListener;
import org.codeanalyser.language.java.JavaLexer;
import org.codeanalyser.language.java.JavaParser;

/**
 * This class will be initialised on startup and contain
 * global properties that can be accessed statically.
 * @author Jack Timblin - U1051575
 */
public class Application {
    
    private static ArrayList<String> metrics;
    private static ArrayList<String> supported;
    
    //declare static variables, i.e initialise system properties.
    static {
        metrics = new ArrayList<String>();
        try {
            metrics = Application.initMetricsList();
        } catch (AntlrException e) {
            System.err.println(e.getMessage());
        }
        supported = Application.initSupported();
    }
    
    /**
     * iterates over the packages in 'languages' to determine
     * which languages are currently supported by the system.
     * @return an ArrayList<String> of supported language names.
     */
    private static ArrayList<String> initSupported() {
        ArrayList<String> support = new ArrayList<String>();
        
        //open the languages directory and iterate over the packages in there.
        File languageDir = new File("src/org/codeanalyser/language");
        if(!languageDir.exists() || !languageDir.isDirectory()) {
            return support;
        }
        
        //iterate through the language packages and 
        //use the package name as a supported language
        //as that is how they are integrated into the system.
        for(File packageName : languageDir.listFiles()) {
            if(packageName.isDirectory()) {
                support.add(packageName.getName());
            }
        }
        
        return support;
    }
    
    /**
     * iterates through the metrics packages to find which classes are declared to 
     * be 'metrics'. A metric is defined as any class that implements MetricInterface.
     * @return an ArrayList<String> the class names of the found metrics.
     * @throws AntlrException when the file could not be opened or parsed into a parse tree.
     */
    private static ArrayList<String> initMetricsList() throws AntlrException {
        File metricsLocation = new File("src/org/codeanalyser/metric");
        if(!metricsLocation.exists()) {
            return new ArrayList<String>(); //could not find metrics location.
        }
        
        //iterate through the metrics package and treat subpackages as a single metric
        //this way a metric can have a main class, along with supporting classes in a single
        //package.
        Application.ApplicationListener listener = new Application.ApplicationListener();
        for(File metricPackage : metricsLocation.listFiles()) {
            if(metricPackage.isDirectory()) {
                listener.setPackagePath(metricPackage.getName());
                //parse the file using the generated Java Parser.
                for(File javaFile : metricPackage.listFiles()) {
                    if(javaFile.getName().endsWith(".java") || javaFile.getName().endsWith(".JAVA")) {
                        try {
                            //use ANTLR to determine if this file is a metric 'main' class.
                            JavaLexer lexer = new JavaLexer(new ANTLRFileStream(javaFile.getAbsolutePath()));
                            CommonTokenStream tokens = new CommonTokenStream(lexer);
                            JavaParser parser = new JavaParser(tokens);
                            ParserRuleContext tree = parser.compilationUnit();
                            
                            ParseTreeWalker walker = new ParseTreeWalker();
                            walker.walk(listener, tree);
                            
                        } catch (IOException e) {
                            throw new AntlrException("Could not open java file: "+javaFile.getName());
                        } catch (RecognitionException e) {
                            throw new AntlrException("Could not parse java file: "+javaFile.getName());
                        }
                    }
                }
            }
        }
        return listener.getClassNames();
    }
    
    /**
     * System Property - gets the metrics that are currently 
     * 'attached' to the application. This is the class names of
     * the class that implement MetricInterface.
     * @return an ArrayList<String> containing the class names of
     * the metrics in the system.
     */
    public static ArrayList<String> getMetricsList() {
        return Application.metrics;
    }
    
    /**
     * System Property - gets the languages that the application
     * currently supports.
     * @return an ArrayList<String> containing the supported languages.
     */
    public static ArrayList<String> getSupportedLanguages() {
        return Application.supported;
    }
    
    /**
     * This class is a small listener class to receive a notification
     * from ANTLR when a class declaration is found. This is used to determine
     * what classes in the 'metrics' package implement MetricInterface
     * i.e the metrics to initialise and include in analysis.
     * 
     * This class builds up a list of the valid classes.
     * 
     * @author Jack Timblin - U1051575
     */
    private static class ApplicationListener extends JavaBaseListener {
        
        private ArrayList<String> metrics;
        private String packagePath;
        
        /**
         * Constructor - initialises a new list. 
         */
        public ApplicationListener() {
            this.metrics = new ArrayList<String>();
            packagePath = null;
        }
        
        /**
         * receives a notification from the ANTLR parse tree walker when a class
         * declaration is found while traversing the tree.
         * @param ctx the 'piece' of the tree that matches the rule.
         */
        @Override
        public void enterClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
            //check to see if this class implements MetricInterface.
            if(ctx.typeList().getText().equalsIgnoreCase("MetricInterface")) {
                if(this.packagePath != null) {
                    String className = "org.codeanalyser.metric."+packagePath.toLowerCase()+"."+ctx.Identifier().getText();
                    this.metrics.add(className);
                }
            }
        }
        
        /**
         * get the names of the classes that implement MetricInterface
         * i.e the metrics that should be used when evaluating source code.
         * @return the list of string class names of the metrics in this application.
         */
        public ArrayList<String> getClassNames() {
            return this.metrics;
        }
        
        public void setPackagePath(String path) {
            this.packagePath = path;
        }
        
    } 
    
}
