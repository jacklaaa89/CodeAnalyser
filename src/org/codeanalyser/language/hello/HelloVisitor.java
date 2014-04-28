package org.codeanalyser.language.hello;

// Generated from CodeAnalyser/grammars/Hello.g4 by ANTLR 4.2
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#switchBlockStatementGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlockStatementGroup(@NotNull HelloParser.SwitchBlockStatementGroupContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull HelloParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(@NotNull HelloParser.MethodDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(@NotNull HelloParser.MethodBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull HelloParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#informalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInformalParameters(@NotNull HelloParser.InformalParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#informalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInformalParameterList(@NotNull HelloParser.InformalParameterListContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(@NotNull HelloParser.OperatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(@NotNull HelloParser.FormalParameterListContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(@NotNull HelloParser.ClassDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull HelloParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#catchClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchClause(@NotNull HelloParser.CatchClauseContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(@NotNull HelloParser.CompilationUnitContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(@NotNull HelloParser.StringLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#classIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassIdentifier(@NotNull HelloParser.ClassIdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#switchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchLabel(@NotNull HelloParser.SwitchLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull HelloParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(@NotNull HelloParser.ClassBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull HelloParser.BlockContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(@NotNull HelloParser.FormalParametersContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(@NotNull HelloParser.ParExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(@NotNull HelloParser.VariableDeclaratorIdContext ctx);

	/**
	 * Visit a parse tree produced by {@link HelloParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(@NotNull HelloParser.MethodCallContext ctx);
}