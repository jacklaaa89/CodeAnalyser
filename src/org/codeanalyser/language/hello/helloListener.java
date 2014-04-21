package org.codeanalyser.language.hello;

// Generated from ../grammars/hello.g4 by ANTLR 4.2
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link helloParser}.
 */
public interface helloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link helloParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull helloParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link helloParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull helloParser.CompilationUnitContext ctx);
}