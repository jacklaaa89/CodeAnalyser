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
 * An CustomANTLRErrorListener which count the amount of syntax errors
 * that occur in the parser or lexer. We can see if the errors array is empty to
 * determine if any syntax errors occurred.
 * @author Jack Timblin - U1051575
 */
public class SyntaxErrorAdapter implements ANTLRErrorListener {
    
    private final ArrayList<String> errors;
    private final FileAnalyser file;
    
    /**
     * initialises a new SyntaxErrorAdapter
     * @param file the file currently being analysed.
     */
    public SyntaxErrorAdapter(FileAnalyser file) {
        this.errors = new ArrayList<String>();
        this.file = file;
    }
    
    /**
     * gets the syntax errors that occurred while parsing 
     * the file currently being analysed.
     * @return the syntax error message strings.
     */
    public ArrayList<String> getSyntaxErrors() {
        return this.errors;
    }
    
    /**
     * triggered when a syntax error occurs.
     * @param rcgnzr
     * @param o
     * @param i
     * @param i1
     * @param string
     * @param re 
     */
    @Override
    public void syntaxError(Recognizer<?, ?> rcgnzr, Object o, int i, int i1, String string, RecognitionException re) {
        this.errors.add("File: "+file.getAbsolutePath() + ", Error: " + string);
    }
    
    /**
     * triggered when ambiguity occurs.
     * @param parser
     * @param dfa
     * @param i
     * @param i1
     * @param bln
     * @param bitset
     * @param atncs 
     */
    @Override
    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean bln, BitSet bitset, ATNConfigSet atncs) {
    }
    
    /**
     * triggered when attempting full context occurs.
     * @param parser
     * @param dfa
     * @param i
     * @param i1
     * @param bitset
     * @param atncs 
     */
    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitset, ATNConfigSet atncs) {
    }
    
    /**
     * triggered when context sensitivity occurs.
     * @param parser
     * @param dfa
     * @param i
     * @param i1
     * @param i2
     * @param atncs 
     */
    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atncs) {
    }
    
}
