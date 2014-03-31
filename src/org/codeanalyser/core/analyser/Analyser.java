package org.codeanalyser.core.analyser;
import java.util.ArrayList;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
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
    
    private String sourceCodeLocation;
    private ArrayList<ArrayList<Result>> results; 
    
    /**
     * initialises the analyser object with a file/directory location.
     * @param sourceCodeLocation the file or directory to analyse.
     */
    public Analyser(String sourceCodeLocation) {
        this.sourceCodeLocation = sourceCodeLocation;
        this.results = new ArrayList<ArrayList<Result>>();
    }
    
    /**
     * Analyses the source location provided in the constructor.
     */
    public void analyse() {
        FileAnalyser file = new FileAnalyser(this.sourceCodeLocation);
        try {
            ParseTreeListener listener = file.getSupportedListener();
            
            Lexer lexer = file.getSupportedLexer();
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ParserInterface parser = file.getSupportedParser(tokens);
            ParserRuleContext tree = parser.compilationUnit();

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, tree);
            
            this.results.add(((ListenerInterface)listener).getResults());
            
        } catch (FileAnalyser.UnsupportedLanguageException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(this.results.toString());
    }
    
    public ArrayList<ArrayList<Result>> collectResults() {
        return this.results;
    }
    
}
