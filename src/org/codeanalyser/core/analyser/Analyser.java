package org.codeanalyser.core.analyser;
import java.io.File;
import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.metric.Result;

/**
 * This class is the one that analyses the file/directory that is the input.
 * It determines the supported listener, parser and lexer from a single file and then walks
 * that tree notifying the listener when events occur.
 * @author Jack Timblin - U1051575  
 */
public class Analyser {
    
    private File sourceCodeLocation;
    private ArrayList<ArrayList<Result>> results;
    private ArrayList<FileAnalyser> filesToAnalyse;
    
    /**
     * initialises the analyser object with a file/directory location.
     * @param sourceCodeLocation the file or directory to analyse.
     */
    public Analyser(String sourceCodeLocation) throws AnalyserException {
        this.sourceCodeLocation = new File(sourceCodeLocation);
        this.results = new ArrayList<ArrayList<Result>>();
        this.filesToAnalyse = new ArrayList<FileAnalyser>();
        if(!this.sourceCodeLocation.exists()) {
            throw new AnalyserException("file/directory provided does not exist.");
        }
        determineFiles(this.sourceCodeLocation);
        System.out.println(this.filesToAnalyse.toString());
    }
    
    /**
     * goes through the sourceCodeLocation and grabs all the of the files to analyse.
     * @param file the current file being determined.
     */
    private void determineFiles(File file) {
        if(file.isDirectory()) {
            for(File t : file.listFiles()) {
                if(t.isDirectory()) {
                    determineFiles(t);
                } else {
                    this.filesToAnalyse.add(new FileAnalyser(t.getAbsolutePath()));
                }
            }
        } else {
            this.filesToAnalyse.add(new FileAnalyser(file.getAbsolutePath()));
        }
    }
    
    
    /**
     * Analyses the source location provided in the constructor.
     */
    public void analyse() {
        for(FileAnalyser file : this.filesToAnalyse) {
            try {
                Lexer lexer = file.getSupportedLexer();
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ParserInterface parser = file.getSupportedParser(tokens);
                ParserRuleContext tree = parser.compilationUnit();
                ParseTreeListener listener = file.getSupportedListener(parser.getTokenNames());
                ParseTreeWalker walker = new ParseTreeWalker();
                walker.walk(listener, tree);

                this.results.add(((ListenerInterface) listener).getResults());

            } catch (FileAnalyser.UnsupportedLanguageException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(this.results.toString());
    }
    
    /**
     * returns the results from metric evaluation.
     * @return 
     */
    public ArrayList<ArrayList<Result>> collectResults() {
        return this.results;
    }
    
}
