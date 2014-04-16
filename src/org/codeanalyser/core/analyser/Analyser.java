package org.codeanalyser.core.analyser;
import java.io.File;
import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.core.output.NoResultsDefinedException;
import org.codeanalyser.core.output.OutputGenerator;
import org.codeanalyser.core.output.OverallResult;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.ParserInterface;

/**
 * This class is the one that analyses the file/directory that is the input.
 * It determines the supported listener, parser and lexer from a single file and then walks
 * that tree notifying the listener when events occur.
 * @author Jack Timblin - U1051575  
 * @version 1.1 - updated analyse to use OverallResult instead of Result for output generation.
 */
public class Analyser {
    
    private File sourceCodeLocation;
    private ArrayList<OverallResult> results;
    private ArrayList<FileAnalyser> filesToAnalyse;
    private OutputGenerator output;
    private ArrayList<String> unsupportedFiles;
    
    /**
     * initialises the analyser object with a file/directory location.
     * @param sourceCodeLocation the file or directory to analyse.
     * @throws AnalyserException if the given source code location does not exist.
     */
    public Analyser(String sourceCodeLocation, String outputLocation) throws AnalyserException {
        this.sourceCodeLocation = new File(sourceCodeLocation);
        this.results = new ArrayList<OverallResult>();
        this.filesToAnalyse = new ArrayList<FileAnalyser>();
        this.unsupportedFiles = new ArrayList<String>();
        if(!this.sourceCodeLocation.exists()) {
            throw new AnalyserException("file/directory provided does not exist.");
        }
        this.output = new OutputGenerator(outputLocation);
        determineFiles(this.sourceCodeLocation);
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
                
                //generate parse tree from source file.
                Lexer lexer = file.getSupportedLexer();
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ParserInterface parser = file.getSupportedParser(tokens);
                ParserRuleContext tree = parser.compilationUnit();
                ParseTreeListener listener = file.getSupportedListener(parser.getTokenNames());
                ParseTreeWalker walker = new ParseTreeWalker();
                
                //walk the parse tree calling methods in the metrics.
                walker.walk(listener, tree);
                
                //gather the results from the analysis.
                OverallResult result = new OverallResult(
                        ((ListenerInterface) listener).getResults(),
                        file.getAbsolutePath()
                );
                
                //add this to result array.
                this.results.add(result);

            } catch (FileAnalyser.UnsupportedLanguageException e) {
                this.unsupportedFiles.add(file.getAbsolutePath());
                System.out.println(e.getMessage());
            } catch (NoResultsDefinedException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            //render the output for this analysis.
            this.output.generateOutput(this.results, this.unsupportedFiles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
