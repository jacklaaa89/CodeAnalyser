package org.codeanalyser.metric;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.ParserInterface;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class MetricParseTreeWalkTest {
    
    private FileAnalyser javaFile, helloFile;
    
    @Before
    public void setUp() {
        javaFile = new FileAnalyser("testData/Test2.java");
        helloFile = new FileAnalyser("testData/Test.hello");
    }
    
    @After
    public void tearDown() {
        javaFile = null; helloFile = null;
    }
    
    @Test
    public void testWalkJavaParseTree() {
        try {
            System.out.println("walkJavaParseTree");
            //construct the parser, lexer, listener and walker.
            Lexer l = javaFile.getSupportedLexer();
            CommonTokenStream s = new CommonTokenStream(l);
            ParserInterface p = javaFile.getSupportedParser(s);
            TesterJavaListener jl = new TesterJavaListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            
            //call init in the listener to initialise the metrics.
            jl.init(javaFile);
            
            //generate the parse tree.
            ParserRuleContext tree = p.compilationUnit();
            
            //walk the tree.
            walker.walk(jl, tree);
            
            //call destroy on the metrics.
            jl.destroy();
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testWalkHelloParseTree() {
        try {
            System.out.println("walkHelloParseTree");
            //construct the parser, lexer, listener and walker.
            Lexer l = helloFile.getSupportedLexer();
            CommonTokenStream s = new CommonTokenStream(l);
            ParserInterface p = helloFile.getSupportedParser(s);
            TesterHelloListener jl = new TesterHelloListener();
            ParseTreeWalker walker = new ParseTreeWalker();
            
            //call init in the listener to initialise the metrics.
            jl.init(helloFile);
            
            //generate the parse tree.
            ParserRuleContext tree = p.compilationUnit();
            
            //walk the tree.
            walker.walk(jl, tree);
            
            //call destroy on the metrics.
            jl.destroy();
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
