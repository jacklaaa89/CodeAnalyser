package org.codeanalyser.language.hello;
// Generated from grammars/Hello.g4 by ANTLR 4.2
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull HelloParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull HelloParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(@NotNull HelloParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(@NotNull HelloParser.VariableDeclaratorIdContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(@NotNull HelloParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(@NotNull HelloParser.ParExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSwitchLabel(@NotNull HelloParser.SwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#switchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSwitchLabel(@NotNull HelloParser.SwitchLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#innerBlock}.
	 * @param ctx the parse tree
	 */
	void enterInnerBlock(@NotNull HelloParser.InnerBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#innerBlock}.
	 * @param ctx the parse tree
	 */
	void exitInnerBlock(@NotNull HelloParser.InnerBlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull HelloParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull HelloParser.CompilationUnitContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull HelloParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull HelloParser.MethodCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(@NotNull HelloParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(@NotNull HelloParser.ClassDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull HelloParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull HelloParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(@NotNull HelloParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(@NotNull HelloParser.StringLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(@NotNull HelloParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(@NotNull HelloParser.OperatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull HelloParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull HelloParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#informalParameters}.
	 * @param ctx the parse tree
	 */
	void enterInformalParameters(@NotNull HelloParser.InformalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#informalParameters}.
	 * @param ctx the parse tree
	 */
	void exitInformalParameters(@NotNull HelloParser.InformalParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#informalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterInformalParameterList(@NotNull HelloParser.InformalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#informalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitInformalParameterList(@NotNull HelloParser.InformalParameterListContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(@NotNull HelloParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(@NotNull HelloParser.MethodBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(@NotNull HelloParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(@NotNull HelloParser.FormalParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(@NotNull HelloParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(@NotNull HelloParser.ClassBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull HelloParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull HelloParser.IdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(@NotNull HelloParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(@NotNull HelloParser.FormalParameterListContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#classIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterClassIdentifier(@NotNull HelloParser.ClassIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#classIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitClassIdentifier(@NotNull HelloParser.ClassIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull HelloParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull HelloParser.MethodDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link HelloParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlockStatementGroup(@NotNull HelloParser.SwitchBlockStatementGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlockStatementGroup(@NotNull HelloParser.SwitchBlockStatementGroupContext ctx);
}