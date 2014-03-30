package org.codeanalyser.language;

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
    
    public ParserRuleContext compilationUnit();
    
}
