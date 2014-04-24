package org.codeanalyser.core.analyser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.language.hello.BaseListener;
import org.codeanalyser.language.hello.HelloLexer;
import org.codeanalyser.language.hello.HelloParser;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class FileAnalyserTest {
    
    private FileAnalyser file;
    
    @Before
    public void setUp() {
        file = new FileAnalyser("testData/Test.hello");
    }
    
    @After
    public void tearDown() {
        file = null;
    }

    /**
     * Test of getSourceLanguage method, of class FileAnalyser.
     */
    @Test
    public void testGetSourceLanguage() {
        System.out.println("getSourceLanguage");
        String expResult = "hello";
        String result = file.getSourceLanguage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBaseName method, of class FileAnalyser.
     */
    @Test
    public void testGetBaseName() {
        System.out.println("getBaseName");
        String expResult = "Test";
        String result = file.getBaseName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileExtension method, of class FileAnalyser.
     */
    @Test
    public void testGetFileExtension() {
        System.out.println("getFileExtension");
        String expResult = "hello";
        String result = file.getFileExtension();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSupportedParser method, of class FileAnalyser.
     */
    @Test
    public void testGetSupportedParser() throws Exception {
        System.out.println("getSupportedParser");
        HelloLexer l = new HelloLexer(new ANTLRFileStream(file.getAbsolutePath()));
        CommonTokenStream stream = new CommonTokenStream(l);
        ParserInterface result = file.getSupportedParser(stream);
        assertTrue(result instanceof HelloParser);
    }

    /**
     * Test of getClassNames method, of class FileAnalyser.
     */
    @Test
    public void testGetClassNames() throws Exception {
        System.out.println("getClassNames");
        String[] expResult = {"hello", "Hello"};
        String[] result = file.getClassNames();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getSupportedLexer method, of class FileAnalyser.
     */
    @Test
    public void testGetSupportedLexer() throws Exception {
        System.out.println("getSupportedLexer");
        Lexer result = file.getSupportedLexer();
        assertTrue(result instanceof HelloLexer);
    }

    /**
     * Test of getSupportedListener method, of class FileAnalyser.
     */
    @Test
    public void testGetSupportedListener() throws Exception {
        System.out.println("getSupportedListener");
        HelloLexer l = new HelloLexer(new ANTLRFileStream(file.getAbsolutePath()));
        CommonTokenStream stream = new CommonTokenStream(l);
        HelloParser parser = new HelloParser(stream);
        ParseTreeListener result = file.getSupportedListener();
        assertTrue(result instanceof BaseListener);
    }
    
}
