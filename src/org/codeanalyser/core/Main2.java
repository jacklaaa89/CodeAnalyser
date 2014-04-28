package org.codeanalyser.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.analyser.Analyser;
import org.codeanalyser.core.analyser.AnalyserException;
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
        Analyser a = new Analyser("testData/Test.hello", "output");
        a.analyse("hello");
        } catch (AnalyserException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
