package org.codeanalyser.core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.codeanalyser.core.generation.ClassGeneration;
import org.codeanalyser.language.hello.HelloLexer;
import org.codeanalyser.language.hello.HelloParser;


/**
 *
 * @author jack
 */
public class Main2 {
    
    public static void main(String[] args) {
        try {
            HelloLexer lexer = new HelloLexer(new ANTLRFileStream("test/Test.hello"));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            HelloParser parser = new HelloParser(stream);
            //ClassGeneration.generateBaseListener("Hello", parser.getRuleNames());
            ParserRuleContext tree = parser.compilationUnit();
            tree.inspect(parser);
        } catch (IOException ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
