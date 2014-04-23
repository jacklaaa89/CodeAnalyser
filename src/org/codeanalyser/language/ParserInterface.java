package org.codeanalyser.language;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * This interface is essentially a dummy interface to spoof the system in
 * thinking that {LANGUAGE_NAME}Parser classes are this generic interface.
 * 
 * This is because the superclass Parser defined in ANTLR does not declare the method
 * compilationUnit() as this is generated in the Parser subclasses.
 * 
 * So if the subclasses implement this interface, we can cast all Parsers to this interface
 * and still call this method compilationUnit();
 * @author Jack Timblin - U1051575
 */
public interface ParserInterface {
    
    /**
     * calls the root rule in the parser and returns the parse tree.
     * @return the parse tree for the source file.
     */
    public ParserRuleContext compilationUnit();
    /**
     * gets the token names used in the parser.
     * @return the token names.
     */
    public String[] getTokenNames();
    /**
     * get the rule names that were defined in the grammar.
     * @return the rule names.
     */
    public String[] getRuleNames();
    /**
     * add a ANTLRErrorListener to the parser to listen
     * for errors that occur.
     * @param listener the error listener to attach to the parser
     */
    public void addErrorListener(ANTLRErrorListener listener);
    
}
