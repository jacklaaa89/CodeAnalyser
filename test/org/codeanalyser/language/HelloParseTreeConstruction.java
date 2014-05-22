package org.codeanalyser.language;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.core.analyser.UnsupportedLanguageException;
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
public class HelloParseTreeConstruction {
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testHelloParseTreeConstruction() {
        System.out.println("testHelloParseTreeConstruction");
        
        try {
            
            FileAnalyser helloFile = new FileAnalyser("testData/Test.hello");
            Lexer l = helloFile.getSupportedLexer();
            CommonTokenStream s = new CommonTokenStream(l);
            ParserInterface p = helloFile.getSupportedParser(s);

            ParserRuleContext tree = p.compilationUnit();
            System.out.println(tree.toStringTree((Parser)p));
            
            //assert that there are 'nodes' on the tree and that
            //the string representation is not an empty string.
            assertTrue(!tree.toStringTree((Parser)p).isEmpty() && tree.getChildCount() != 0);
            
        } catch (UnsupportedLanguageException e) {
            fail(e.getMessage());
        }
    }
}
