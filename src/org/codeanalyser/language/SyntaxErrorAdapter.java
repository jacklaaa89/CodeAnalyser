/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.codeanalyser.language;

import java.util.ArrayList;
import java.util.BitSet;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.codeanalyser.core.analyser.FileAnalyser;

/**
 *
 * @author jack
 */
public class SyntaxErrorAdapter implements ANTLRErrorListener {
    
    private ArrayList<String> errors;
    private FileAnalyser file;
    
    public SyntaxErrorAdapter(FileAnalyser file) {
        this.errors = new ArrayList<String>();
        this.file = file;
    }
    
    public ArrayList<String> getSyntaxErrors() {
        return this.errors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
        this.errors.add("File: "+file.getAbsolutePath() + ", Error: " + string);
    }

    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
    }
    
}
