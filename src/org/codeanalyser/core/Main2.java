package org.codeanalyser.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.analyser.Analyser;
import org.codeanalyser.core.analyser.AnalyserException;
import org.codeanalyser.core.analyser.FileAnalyser;
import org.codeanalyser.language.ParserInterface;
import org.codeanalyser.language.hello.HelloLexer;
import org.codeanalyser.language.hello.HelloParser;


/**
 * Tester Main class to run specific pieces of code.
 * @author Jack Timblin - U1051575
 */
public class Main2 {
    
    /**
     * runs tester code.
     * @param args the arguments from the VM.
     */
    public static void main(String[] args) {
        try {
        FileAnalyser a = new FileAnalyser("testData/Test.hello", "hello");
        Lexer l = a.getSupportedLexer();
        CommonTokenStream c = new CommonTokenStream(l);
        ParserInterface p = a.getSupportedParser(c);
        ParserRuleContext t = p.compilationUnit();
        
        t.inspect((Parser)p);
        
        } catch (Exception e) {
            Application.getLogger().log(e);
        }
    }
    
}
