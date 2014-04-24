package org.codeanalyser.metric;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.ListenerInterface;
import org.codeanalyser.language.MetricException;
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
public class MetricInitialisationTest {
    
    private ParserInterface parser;
    private FileAnalyser file;
    
    @Before
    public void setUp() {
        try {
            this.file = new FileAnalyser("testData/Test2.java");
            Lexer l = file.getSupportedLexer();
            CommonTokenStream s = new CommonTokenStream(l);
            parser = file.getSupportedParser(s);
        } catch (FileAnalyser.UnsupportedLanguageException e) {
            fail();
        }
    }
    
    @After
    public void tearDown() {
    }
    
    public void testMetricInitialisation() {
        System.out.println("metricInitialisation");
        try {
            ParseTreeListener listener = file.getSupportedListener(parser);
            
            //attempt to cast this to ListenerInterface to call init, 
            //which initialises all of the installed metrics.
            ((ListenerInterface)listener).init(file, parser);
            
        } catch (FileAnalyser.UnsupportedLanguageException e) {
            fail(e.getMessage());
        } catch (ClassCastException e) {
            fail(e.getMessage());
        } catch (MetricException e) {
            fail(e.getMessage());
        }
    }
}
