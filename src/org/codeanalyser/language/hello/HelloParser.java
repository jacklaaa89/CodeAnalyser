package org.codeanalyser.language.hello;
// Generated from Documents/University/CodeAnalyser/grammars/Hello.g4 by ANTLR 4.2
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import org.codeanalyser.language.ParserInterface;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HelloParser extends Parser implements ParserInterface {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__25=1, T__24=2, T__23=3, T__22=4, T__21=5, T__20=6, T__19=7, T__18=8, 
		T__17=9, T__16=10, T__15=11, T__14=12, T__13=13, T__12=14, T__11=15, T__10=16, 
		T__9=17, T__8=18, T__7=19, T__6=20, T__5=21, T__4=22, T__3=23, T__2=24, 
		T__1=25, T__0=26, ID=27, NUMBER=28, WS=29, COMMENT=30, LINE_COMMENT=31;
	public static final String[] tokenNames = {
		"<INVALID>", "'default'", "','", "'('", "'if'", "'<'", "'--'", "'false'", 
		"'<='", "'void'", "'{'", "'else'", "'}'", "'true'", "'\"'", "'++'", "'case'", 
		"'.'", "')'", "'='", "';'", "'>'", "'switch'", "'=='", "'>='", "'class'", 
		"'!'", "ID", "NUMBER", "WS", "COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_compilationUnit = 0, RULE_classDeclaration = 1, RULE_classBody = 2, 
		RULE_methodDeclaration = 3, RULE_methodBody = 4, RULE_statement = 5, RULE_methodCall = 6, 
		RULE_informalParameterList = 7, RULE_informalParameters = 8, RULE_expression = 9, 
		RULE_switchBlockStatementGroup = 10, RULE_switchLabel = 11, RULE_stringLiteral = 12, 
		RULE_classIdentifier = 13, RULE_parExpression = 14, RULE_operator = 15, 
		RULE_innerBlock = 16, RULE_formalParameterList = 17, RULE_formalParameters = 18, 
		RULE_variableDeclaratorId = 19, RULE_identifier = 20, RULE_number = 21;
	public static final String[] ruleNames = {
		"compilationUnit", "classDeclaration", "classBody", "methodDeclaration", 
		"methodBody", "statement", "methodCall", "informalParameterList", "informalParameters", 
		"expression", "switchBlockStatementGroup", "switchLabel", "stringLiteral", 
		"classIdentifier", "parExpression", "operator", "innerBlock", "formalParameterList", 
		"formalParameters", "variableDeclaratorId", "identifier", "number"
	};

	@Override
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HelloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HelloParser.EOF, 0); }
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_la = _input.LA(1);
			if (_la==25) {
				{
				setState(44); classDeclaration();
				}
			}

			setState(47); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(25);
			setState(50); identifier();
			setState(51); classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(10);
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==9) {
				{
				{
				setState(54); methodDeclaration();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_methodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(9);
			setState(63); identifier();
			setState(64); formalParameterList();
			setState(65); methodBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public InnerBlockContext innerBlock() {
			return getRuleContext(InnerBlockContext.class,0);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitMethodBody(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); innerBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<InnerBlockContext> innerBlock() {
			return getRuleContexts(InnerBlockContext.class);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public InnerBlockContext innerBlock(int i) {
			return getRuleContext(InnerBlockContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		int _la;
		try {
			setState(88);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(69); methodCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 2);
				{
				setState(70); match(4);
				setState(71); expression();
				setState(72); innerBlock();
				setState(75);
				_la = _input.LA(1);
				if (_la==11) {
					{
					setState(73); match(11);
					setState(74); innerBlock();
					}
				}

				}
				break;
			case 22:
				enterOuterAlt(_localctx, 3);
				{
				setState(77); match(22);
				setState(78); expression();
				setState(79); match(10);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==1 || _la==16) {
					{
					{
					setState(80); switchBlockStatementGroup();
					}
					}
					setState(85);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(86); match(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public List<ClassIdentifierContext> classIdentifier() {
			return getRuleContexts(ClassIdentifierContext.class);
		}
		public InformalParameterListContext informalParameterList() {
			return getRuleContext(InformalParameterListContext.class,0);
		}
		public ClassIdentifierContext classIdentifier(int i) {
			return getRuleContext(ClassIdentifierContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitMethodCall(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodCall);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(90); classIdentifier();
					setState(91); match(17);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(98); identifier();
			setState(99); informalParameterList();
			setState(100); match(20);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InformalParameterListContext extends ParserRuleContext {
		public InformalParametersContext informalParameters() {
			return getRuleContext(InformalParametersContext.class,0);
		}
		public InformalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_informalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterInformalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitInformalParameterList(this);
		}
	}

	public final InformalParameterListContext informalParameterList() throws RecognitionException {
		InformalParameterListContext _localctx = new InformalParameterListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_informalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(3);
			setState(104);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(103); informalParameters();
				}
			}

			setState(106); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InformalParametersContext extends ParserRuleContext {
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public InformalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_informalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterInformalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitInformalParameters(this);
		}
	}

	public final InformalParametersContext informalParameters() throws RecognitionException {
		InformalParametersContext _localctx = new InformalParametersContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_informalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); identifier();
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(109); match(2);
				setState(110); identifier();
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<ParExpressionContext> parExpression() {
			return getRuleContexts(ParExpressionContext.class);
		}
		public ParExpressionContext parExpression(int i) {
			return getRuleContext(ParExpressionContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expression);
		try {
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); match(3);
				setState(117); identifier();
				setState(118); match(18);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120); match(3);
				setState(121); parExpression();
				setState(122); operator();
				setState(123); parExpression();
				setState(124); match(18);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public InnerBlockContext innerBlock() {
			return getRuleContext(InnerBlockContext.class,0);
		}
		public SwitchLabelContext switchLabel() {
			return getRuleContext(SwitchLabelContext.class,0);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_switchBlockStatementGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); switchLabel();
			setState(129); innerBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchLabelContext extends ParserRuleContext {
		public StringLiteralContext stringLiteral() {
			return getRuleContext(StringLiteralContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_switchLabel);
		try {
			setState(139);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131); match(16);
				setState(132); identifier();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133); match(16);
				setState(136);
				switch (_input.LA(1)) {
				case 14:
					{
					setState(134); stringLiteral();
					}
					break;
				case ID:
					{
					setState(135); identifier();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138); match(1);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringLiteralContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public StringLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterStringLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitStringLiteral(this);
		}
	}

	public final StringLiteralContext stringLiteral() throws RecognitionException {
		StringLiteralContext _localctx = new StringLiteralContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stringLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); match(14);
			setState(142); match(ID);
			setState(143); match(14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterClassIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitClassIdentifier(this);
		}
	}

	public final ClassIdentifierContext classIdentifier() throws RecognitionException {
		ClassIdentifierContext _localctx = new ClassIdentifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_classIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitParExpression(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_parExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			switch (_input.LA(1)) {
			case 26:
			case ID:
				{
				setState(148);
				_la = _input.LA(1);
				if (_la==26) {
					{
					setState(147); match(26);
					}
				}

				setState(150); identifier();
				}
				break;
			case 13:
				{
				setState(151); match(13);
				}
				break;
			case 7:
				{
				setState(152); match(7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitOperator(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 6) | (1L << 8) | (1L << 15) | (1L << 19) | (1L << 21) | (1L << 23) | (1L << 24))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerBlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public InnerBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterInnerBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitInnerBlock(this);
		}
	}

	public final InnerBlockContext innerBlock() throws RecognitionException {
		InnerBlockContext _localctx = new InnerBlockContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_innerBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(10);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 4) | (1L << 22) | (1L << ID))) != 0)) {
				{
				{
				setState(158); statement();
				}
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(164); match(12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_formalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); match(3);
			setState(168);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(167); formalParameters();
				}
			}

			setState(170); match(18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersContext extends ParserRuleContext {
		public List<VariableDeclaratorIdContext> variableDeclaratorId() {
			return getRuleContexts(VariableDeclaratorIdContext.class);
		}
		public VariableDeclaratorIdContext variableDeclaratorId(int i) {
			return getRuleContext(VariableDeclaratorIdContext.class,i);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); variableDeclaratorId();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==2) {
				{
				{
				setState(173); match(2);
				setState(174); variableDeclaratorId();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitVariableDeclaratorId(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variableDeclaratorId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(HelloParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(HelloParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184); match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3!\u00bd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\5\2\60\n\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\7\4:\n\4\f\4\16\4=\13\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7N\n\7\3\7\3\7\3\7\3\7\7"+
		"\7T\n\7\f\7\16\7W\13\7\3\7\3\7\5\7[\n\7\3\b\3\b\3\b\7\b`\n\b\f\b\16\b"+
		"c\13\b\3\b\3\b\3\b\3\b\3\t\3\t\5\tk\n\t\3\t\3\t\3\n\3\n\3\n\7\nr\n\n\f"+
		"\n\16\nu\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0081"+
		"\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u008b\n\r\3\r\5\r\u008e\n\r"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\20\5\20\u0097\n\20\3\20\3\20\3\20\5\20"+
		"\u009c\n\20\3\21\3\21\3\22\3\22\7\22\u00a2\n\22\f\22\16\22\u00a5\13\22"+
		"\3\22\3\22\3\23\3\23\5\23\u00ab\n\23\3\23\3\23\3\24\3\24\3\24\7\24\u00b2"+
		"\n\24\f\24\16\24\u00b5\13\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\2\2\30"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\3\b\2\7\b\n\n\21\21"+
		"\25\25\27\27\31\32\u00b9\2/\3\2\2\2\4\63\3\2\2\2\6\67\3\2\2\2\b@\3\2\2"+
		"\2\nE\3\2\2\2\fZ\3\2\2\2\16a\3\2\2\2\20h\3\2\2\2\22n\3\2\2\2\24\u0080"+
		"\3\2\2\2\26\u0082\3\2\2\2\30\u008d\3\2\2\2\32\u008f\3\2\2\2\34\u0093\3"+
		"\2\2\2\36\u009b\3\2\2\2 \u009d\3\2\2\2\"\u009f\3\2\2\2$\u00a8\3\2\2\2"+
		"&\u00ae\3\2\2\2(\u00b6\3\2\2\2*\u00b8\3\2\2\2,\u00ba\3\2\2\2.\60\5\4\3"+
		"\2/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\2\2\3\62\3\3\2\2\2\63\64"+
		"\7\33\2\2\64\65\5*\26\2\65\66\5\6\4\2\66\5\3\2\2\2\67;\7\f\2\28:\5\b\5"+
		"\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\7\16"+
		"\2\2?\7\3\2\2\2@A\7\13\2\2AB\5*\26\2BC\5$\23\2CD\5\n\6\2D\t\3\2\2\2EF"+
		"\5\"\22\2F\13\3\2\2\2G[\5\16\b\2HI\7\6\2\2IJ\5\24\13\2JM\5\"\22\2KL\7"+
		"\r\2\2LN\5\"\22\2MK\3\2\2\2MN\3\2\2\2N[\3\2\2\2OP\7\30\2\2PQ\5\24\13\2"+
		"QU\7\f\2\2RT\5\26\f\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2"+
		"\2WU\3\2\2\2XY\7\16\2\2Y[\3\2\2\2ZG\3\2\2\2ZH\3\2\2\2ZO\3\2\2\2[\r\3\2"+
		"\2\2\\]\5\34\17\2]^\7\23\2\2^`\3\2\2\2_\\\3\2\2\2`c\3\2\2\2a_\3\2\2\2"+
		"ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\5*\26\2ef\5\20\t\2fg\7\26\2\2g\17\3\2"+
		"\2\2hj\7\5\2\2ik\5\22\n\2ji\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\24\2\2m\21"+
		"\3\2\2\2ns\5*\26\2op\7\4\2\2pr\5*\26\2qo\3\2\2\2ru\3\2\2\2sq\3\2\2\2s"+
		"t\3\2\2\2t\23\3\2\2\2us\3\2\2\2vw\7\5\2\2wx\5*\26\2xy\7\24\2\2y\u0081"+
		"\3\2\2\2z{\7\5\2\2{|\5\36\20\2|}\5 \21\2}~\5\36\20\2~\177\7\24\2\2\177"+
		"\u0081\3\2\2\2\u0080v\3\2\2\2\u0080z\3\2\2\2\u0081\25\3\2\2\2\u0082\u0083"+
		"\5\30\r\2\u0083\u0084\5\"\22\2\u0084\27\3\2\2\2\u0085\u0086\7\22\2\2\u0086"+
		"\u008e\5*\26\2\u0087\u008a\7\22\2\2\u0088\u008b\5\32\16\2\u0089\u008b"+
		"\5*\26\2\u008a\u0088\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c"+
		"\u008e\7\3\2\2\u008d\u0085\3\2\2\2\u008d\u0087\3\2\2\2\u008d\u008c\3\2"+
		"\2\2\u008e\31\3\2\2\2\u008f\u0090\7\20\2\2\u0090\u0091\7\35\2\2\u0091"+
		"\u0092\7\20\2\2\u0092\33\3\2\2\2\u0093\u0094\5*\26\2\u0094\35\3\2\2\2"+
		"\u0095\u0097\7\34\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u009c\5*\26\2\u0099\u009c\7\17\2\2\u009a\u009c\7\t\2\2"+
		"\u009b\u0096\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009a\3\2\2\2\u009c\37"+
		"\3\2\2\2\u009d\u009e\t\2\2\2\u009e!\3\2\2\2\u009f\u00a3\7\f\2\2\u00a0"+
		"\u00a2\5\f\7\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6"+
		"\u00a7\7\16\2\2\u00a7#\3\2\2\2\u00a8\u00aa\7\5\2\2\u00a9\u00ab\5&\24\2"+
		"\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad"+
		"\7\24\2\2\u00ad%\3\2\2\2\u00ae\u00b3\5(\25\2\u00af\u00b0\7\4\2\2\u00b0"+
		"\u00b2\5(\25\2\u00b1\u00af\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\'\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7"+
		"\7\35\2\2\u00b7)\3\2\2\2\u00b8\u00b9\7\35\2\2\u00b9+\3\2\2\2\u00ba\u00bb"+
		"\7\36\2\2\u00bb-\3\2\2\2\22/;MUZajs\u0080\u008a\u008d\u0096\u009b\u00a3"+
		"\u00aa\u00b3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}