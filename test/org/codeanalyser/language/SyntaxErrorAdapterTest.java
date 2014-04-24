package org.codeanalyser.language;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class SyntaxErrorAdapterTest {
    
    private FileAnalyser fileSyntaxError;
    
    @Before
    public void setUp() {
        this.fileSyntaxError = new FileAnalyser("testData/TestSyntaxError.java");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSyntaxErrors method, of class SyntaxErrorAdapter.
     */
    @Test
    public void testHasSyntaxErrors() {
        System.out.println("hasSyntaxErrors");
        try {
            
            //set up a test parse with a file that has syntax errors.
            SyntaxErrorAdapter instance = new SyntaxErrorAdapter(fileSyntaxError);
            Lexer lexer = fileSyntaxError.getSupportedLexer();
            CommonTokenStream s = new CommonTokenStream(lexer);
            ParserInterface parser = fileSyntaxError.getSupportedParser(s);
            lexer.addErrorListener(instance);
            parser.addErrorListener(instance);
            
            //generate parse tree.
            ParserRuleContext context = parser.compilationUnit();
            //check that a syntax error was recorded.
            assertTrue(instance.getSyntaxErrors().size() > 0);
        } catch (FileAnalyser.UnsupportedLanguageException e) {
            fail("Could not initialise parser or lexer.");
        }
    }
    
}
