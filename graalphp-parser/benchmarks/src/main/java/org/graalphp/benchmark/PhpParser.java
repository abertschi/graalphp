// Generated from PhpParser.g4 by ANTLR 4.8
package org.graalphp.benchmark;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PhpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SeaWhitespace=1, HtmlText=2, XmlStart=3, PHPStart=4, HtmlScriptOpen=5, 
		HtmlStyleOpen=6, HtmlComment=7, HtmlDtd=8, HtmlOpen=9, Shebang=10, Error=11, 
		XmlText=12, XmlClose=13, PHPStartInside=14, HtmlClose=15, HtmlSlashClose=16, 
		HtmlSlash=17, HtmlEquals=18, HtmlStartQuoteString=19, HtmlStartDoubleQuoteString=20, 
		HtmlHex=21, HtmlDecimal=22, HtmlSpace=23, HtmlName=24, ErrorInside=25, 
		PHPStartInsideQuoteString=26, HtmlEndQuoteString=27, HtmlQuoteString=28, 
		ErrorHtmlQuote=29, PHPStartDoubleQuoteString=30, HtmlEndDoubleQuoteString=31, 
		HtmlDoubleQuoteString=32, ErrorHtmlDoubleQuote=33, ScriptText=34, ScriptClose=35, 
		PHPStartInsideScript=36, StyleBody=37, PHPEnd=38, Whitespace=39, MultiLineComment=40, 
		SingleLineComment=41, ShellStyleComment=42, Abstract=43, Array=44, As=45, 
		BinaryCast=46, BoolType=47, BooleanConstant=48, Break=49, Callable=50, 
		Case=51, Catch=52, Class=53, Clone=54, Const=55, Continue=56, Declare=57, 
		Default=58, Do=59, DoubleCast=60, DoubleType=61, Echo=62, Else=63, ElseIf=64, 
		Empty=65, EndDeclare=66, EndFor=67, EndForeach=68, EndIf=69, EndSwitch=70, 
		EndWhile=71, Eval=72, Exit=73, Extends=74, Final=75, Finally=76, FloatCast=77, 
		For=78, Foreach=79, Function=80, Global=81, Goto=82, If=83, Implements=84, 
		Import=85, Include=86, IncludeOnce=87, InstanceOf=88, InsteadOf=89, Int8Cast=90, 
		Int16Cast=91, Int64Type=92, IntType=93, Interface=94, IsSet=95, List=96, 
		LogicalAnd=97, LogicalOr=98, LogicalXor=99, Namespace=100, New=101, Null=102, 
		ObjectType=103, Parent_=104, Partial=105, Print=106, Private=107, Protected=108, 
		Public=109, Require=110, RequireOnce=111, Resource=112, Return=113, Static=114, 
		StringType=115, Switch=116, Throw=117, Trait=118, Try=119, Typeof=120, 
		UintCast=121, UnicodeCast=122, Unset=123, Use=124, Var=125, While=126, 
		Yield=127, From=128, LambdaFn=129, Get=130, Set=131, Call=132, CallStatic=133, 
		Constructor=134, Destruct=135, Wakeup=136, Sleep=137, Autoload=138, IsSet__=139, 
		Unset__=140, ToString__=141, Invoke=142, SetState=143, Clone__=144, DebugInfo=145, 
		Namespace__=146, Class__=147, Traic__=148, Function__=149, Method__=150, 
		Line__=151, File__=152, Dir__=153, Spaceship=154, Lgeneric=155, Rgeneric=156, 
		DoubleArrow=157, Inc=158, Dec=159, IsIdentical=160, IsNoidentical=161, 
		IsEqual=162, IsNotEq=163, IsSmallerOrEqual=164, IsGreaterOrEqual=165, 
		PlusEqual=166, MinusEqual=167, MulEqual=168, Pow=169, PowEqual=170, DivEqual=171, 
		Concaequal=172, ModEqual=173, ShiftLeftEqual=174, ShiftRightEqual=175, 
		AndEqual=176, OrEqual=177, XorEqual=178, BooleanOr=179, BooleanAnd=180, 
		NullCoalescing=181, NullCoalescingEqual=182, ShiftLeft=183, ShiftRight=184, 
		DoubleColon=185, ObjectOperator=186, NamespaceSeparator=187, Ellipsis=188, 
		Less=189, Greater=190, Ampersand=191, Pipe=192, Bang=193, Caret=194, Plus=195, 
		Minus=196, Asterisk=197, Percent=198, Divide=199, Tilde=200, SuppressWarnings=201, 
		Dollar=202, Dot=203, QuestionMark=204, OpenRoundBracket=205, CloseRoundBracket=206, 
		OpenSquareBracket=207, CloseSquareBracket=208, OpenCurlyBracket=209, CloseCurlyBracket=210, 
		Comma=211, Colon=212, SemiColon=213, Eq=214, Quote=215, BackQuote=216, 
		VarName=217, Label=218, Octal=219, Decimal=220, Real=221, Hex=222, Binary=223, 
		BackQuoteString=224, SingleQuoteString=225, DoubleQuote=226, StartNowDoc=227, 
		StartHereDoc=228, ErrorPhp=229, CurlyDollar=230, UnicodeEscape=231, StringPart=232, 
		Comment=233, PHPEndSingleLineComment=234, CommentEnd=235, HereDocText=236, 
		XmlText2=237;
	public static final int
		RULE_htmlDocument = 0, RULE_htmlElementOrPhpBlock = 1, RULE_htmlElements = 2, 
		RULE_htmlElement = 3, RULE_scriptTextPart = 4, RULE_phpBlock = 5, RULE_importStatement = 6, 
		RULE_topStatement = 7, RULE_useDeclaration = 8, RULE_useDeclarationContentList = 9, 
		RULE_useDeclarationContent = 10, RULE_namespaceDeclaration = 11, RULE_namespaceStatement = 12, 
		RULE_functionDeclaration = 13, RULE_classDeclaration = 14, RULE_classEntryType = 15, 
		RULE_interfaceList = 16, RULE_typeParameterListInBrackets = 17, RULE_typeParameterList = 18, 
		RULE_typeParameterWithDefaultsList = 19, RULE_typeParameterDecl = 20, 
		RULE_typeParameterWithDefaultDecl = 21, RULE_genericDynamicArgs = 22, 
		RULE_attributes = 23, RULE_attributesGroup = 24, RULE_attribute = 25, 
		RULE_attributeArgList = 26, RULE_attributeNamedArgList = 27, RULE_attributeNamedArg = 28, 
		RULE_innerStatementList = 29, RULE_innerStatement = 30, RULE_statement = 31, 
		RULE_emptyStatement = 32, RULE_blockStatement = 33, RULE_ifStatement = 34, 
		RULE_elseIfStatement = 35, RULE_elseIfColonStatement = 36, RULE_elseStatement = 37, 
		RULE_elseColonStatement = 38, RULE_whileStatement = 39, RULE_doWhileStatement = 40, 
		RULE_forStatement = 41, RULE_forInit = 42, RULE_forUpdate = 43, RULE_switchStatement = 44, 
		RULE_switchBlock = 45, RULE_breakStatement = 46, RULE_continueStatement = 47, 
		RULE_returnStatement = 48, RULE_expressionStatement = 49, RULE_unsetStatement = 50, 
		RULE_foreachStatement = 51, RULE_tryCatchFinally = 52, RULE_catchClause = 53, 
		RULE_finallyStatement = 54, RULE_throwStatement = 55, RULE_gotoStatement = 56, 
		RULE_declareStatement = 57, RULE_inlineHtmlStatement = 58, RULE_inlineHtml = 59, 
		RULE_declareList = 60, RULE_formalParameterList = 61, RULE_formalParameter = 62, 
		RULE_typeHint = 63, RULE_globalStatement = 64, RULE_globalVar = 65, RULE_echoStatement = 66, 
		RULE_staticVariableStatement = 67, RULE_classStatement = 68, RULE_traitAdaptations = 69, 
		RULE_traitAdaptationStatement = 70, RULE_traitPrecedence = 71, RULE_traitAlias = 72, 
		RULE_traitMethodReference = 73, RULE_baseCtorCall = 74, RULE_methodBody = 75, 
		RULE_propertyModifiers = 76, RULE_memberModifiers = 77, RULE_variableInitializer = 78, 
		RULE_identifierInititalizer = 79, RULE_globalConstantDeclaration = 80, 
		RULE_expressionList = 81, RULE_parentheses = 82, RULE_expression = 83, 
		RULE_assignable = 84, RULE_arrayCreation = 85, RULE_lambdaFunctionExpr = 86, 
		RULE_newExpr = 87, RULE_assignmentOperator = 88, RULE_yieldExpression = 89, 
		RULE_arrayItemList = 90, RULE_arrayItem = 91, RULE_lambdaFunctionUseVars = 92, 
		RULE_lambdaFunctionUseVar = 93, RULE_qualifiedStaticTypeRef = 94, RULE_typeRef = 95, 
		RULE_anoymousClass = 96, RULE_indirectTypeRef = 97, RULE_qualifiedNamespaceName = 98, 
		RULE_namespaceNameList = 99, RULE_namespaceNameTail = 100, RULE_qualifiedNamespaceNameList = 101, 
		RULE_arguments = 102, RULE_actualArgument = 103, RULE_constantInititalizer = 104, 
		RULE_constantArrayItemList = 105, RULE_constantArrayItem = 106, RULE_constant = 107, 
		RULE_literalConstant = 108, RULE_numericConstant = 109, RULE_classConstant = 110, 
		RULE_stringConstant = 111, RULE_string = 112, RULE_interpolatedStringPart = 113, 
		RULE_chainList = 114, RULE_chain = 115, RULE_chainOrigin = 116, RULE_memberAccess = 117, 
		RULE_functionCall = 118, RULE_functionCallName = 119, RULE_actualArguments = 120, 
		RULE_chainBase = 121, RULE_keyedFieldName = 122, RULE_keyedSimpleFieldName = 123, 
		RULE_keyedVariable = 124, RULE_squareCurlyExpression = 125, RULE_assignmentList = 126, 
		RULE_assignmentListElement = 127, RULE_modifier = 128, RULE_identifier = 129, 
		RULE_memberModifier = 130, RULE_magicConstant = 131, RULE_magicMethod = 132, 
		RULE_primitiveType = 133, RULE_castOperation = 134;
	private static String[] makeRuleNames() {
		return new String[] {
			"htmlDocument", "htmlElementOrPhpBlock", "htmlElements", "htmlElement", 
			"scriptTextPart", "phpBlock", "importStatement", "topStatement", "useDeclaration", 
			"useDeclarationContentList", "useDeclarationContent", "namespaceDeclaration", 
			"namespaceStatement", "functionDeclaration", "classDeclaration", "classEntryType", 
			"interfaceList", "typeParameterListInBrackets", "typeParameterList", 
			"typeParameterWithDefaultsList", "typeParameterDecl", "typeParameterWithDefaultDecl", 
			"genericDynamicArgs", "attributes", "attributesGroup", "attribute", "attributeArgList", 
			"attributeNamedArgList", "attributeNamedArg", "innerStatementList", "innerStatement", 
			"statement", "emptyStatement", "blockStatement", "ifStatement", "elseIfStatement", 
			"elseIfColonStatement", "elseStatement", "elseColonStatement", "whileStatement", 
			"doWhileStatement", "forStatement", "forInit", "forUpdate", "switchStatement", 
			"switchBlock", "breakStatement", "continueStatement", "returnStatement", 
			"expressionStatement", "unsetStatement", "foreachStatement", "tryCatchFinally", 
			"catchClause", "finallyStatement", "throwStatement", "gotoStatement", 
			"declareStatement", "inlineHtmlStatement", "inlineHtml", "declareList", 
			"formalParameterList", "formalParameter", "typeHint", "globalStatement", 
			"globalVar", "echoStatement", "staticVariableStatement", "classStatement", 
			"traitAdaptations", "traitAdaptationStatement", "traitPrecedence", "traitAlias", 
			"traitMethodReference", "baseCtorCall", "methodBody", "propertyModifiers", 
			"memberModifiers", "variableInitializer", "identifierInititalizer", "globalConstantDeclaration", 
			"expressionList", "parentheses", "expression", "assignable", "arrayCreation", 
			"lambdaFunctionExpr", "newExpr", "assignmentOperator", "yieldExpression", 
			"arrayItemList", "arrayItem", "lambdaFunctionUseVars", "lambdaFunctionUseVar", 
			"qualifiedStaticTypeRef", "typeRef", "anoymousClass", "indirectTypeRef", 
			"qualifiedNamespaceName", "namespaceNameList", "namespaceNameTail", "qualifiedNamespaceNameList", 
			"arguments", "actualArgument", "constantInititalizer", "constantArrayItemList", 
			"constantArrayItem", "constant", "literalConstant", "numericConstant", 
			"classConstant", "stringConstant", "string", "interpolatedStringPart", 
			"chainList", "chain", "chainOrigin", "memberAccess", "functionCall", 
			"functionCallName", "actualArguments", "chainBase", "keyedFieldName", 
			"keyedSimpleFieldName", "keyedVariable", "squareCurlyExpression", "assignmentList", 
			"assignmentListElement", "modifier", "identifier", "memberModifier", 
			"magicConstant", "magicMethod", "primitiveType", "castOperation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "'/>'", null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'abstract'", "'array'", "'as'", 
			"'binary'", null, null, "'break'", "'callable'", "'case'", "'catch'", 
			"'class'", "'clone'", "'const'", "'continue'", "'declare'", "'default'", 
			"'do'", "'real'", "'double'", "'echo'", "'else'", "'elseif'", "'empty'", 
			"'enddeclare'", "'endfor'", "'endforeach'", "'endif'", "'endswitch'", 
			"'endwhile'", "'eval'", "'die'", "'extends'", "'final'", "'finally'", 
			"'float'", "'for'", "'foreach'", "'function'", "'global'", "'goto'", 
			"'if'", "'implements'", "'import'", "'include'", "'include_once'", "'instanceof'", 
			"'insteadof'", "'int8'", "'int16'", "'int64'", null, "'interface'", "'isset'", 
			"'list'", "'and'", "'or'", "'xor'", "'namespace'", "'new'", "'null'", 
			"'object'", "'parent'", "'partial'", "'print'", "'private'", "'protected'", 
			"'public'", "'require'", "'require_once'", "'resource'", "'return'", 
			"'static'", "'string'", "'switch'", "'throw'", "'trait'", "'try'", "'clrtypeof'", 
			null, "'unicode'", "'unset'", "'use'", "'var'", "'while'", "'yield'", 
			"'from'", "'fn'", "'__get'", "'__set'", "'__call'", "'__callstatic'", 
			"'__construct'", "'__destruct'", "'__wakeup'", "'__sleep'", "'__autoload'", 
			"'__isset'", "'__unset'", "'__tostring'", "'__invoke'", "'__set_state'", 
			"'__clone'", "'__debuginfo'", "'__namespace__'", "'__class__'", "'__trait__'", 
			"'__function__'", "'__method__'", "'__line__'", "'__file__'", "'__dir__'", 
			"'<=>'", "'<:'", "':>'", "'=>'", "'++'", "'--'", "'==='", "'!=='", "'=='", 
			null, "'<='", "'>='", "'+='", "'-='", "'*='", "'**'", "'**='", "'/='", 
			"'.='", "'%='", "'<<='", "'>>='", "'&='", "'|='", "'^='", "'||'", "'&&'", 
			"'??'", "'??='", "'<<'", "'>>'", "'::'", "'->'", "'\\'", "'...'", null, 
			null, "'&'", "'|'", "'!'", "'^'", "'+'", "'-'", "'*'", "'%'", null, "'~'", 
			"'@'", null, "'.'", null, "'('", "')'", "'['", "']'", null, "'}'", "','", 
			"':'", "';'", null, "'''", "'`'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SeaWhitespace", "HtmlText", "XmlStart", "PHPStart", "HtmlScriptOpen", 
			"HtmlStyleOpen", "HtmlComment", "HtmlDtd", "HtmlOpen", "Shebang", "Error", 
			"XmlText", "XmlClose", "PHPStartInside", "HtmlClose", "HtmlSlashClose", 
			"HtmlSlash", "HtmlEquals", "HtmlStartQuoteString", "HtmlStartDoubleQuoteString", 
			"HtmlHex", "HtmlDecimal", "HtmlSpace", "HtmlName", "ErrorInside", "PHPStartInsideQuoteString", 
			"HtmlEndQuoteString", "HtmlQuoteString", "ErrorHtmlQuote", "PHPStartDoubleQuoteString", 
			"HtmlEndDoubleQuoteString", "HtmlDoubleQuoteString", "ErrorHtmlDoubleQuote", 
			"ScriptText", "ScriptClose", "PHPStartInsideScript", "StyleBody", "PHPEnd", 
			"Whitespace", "MultiLineComment", "SingleLineComment", "ShellStyleComment", 
			"Abstract", "Array", "As", "BinaryCast", "BoolType", "BooleanConstant", 
			"Break", "Callable", "Case", "Catch", "Class", "Clone", "Const", "Continue", 
			"Declare", "Default", "Do", "DoubleCast", "DoubleType", "Echo", "Else", 
			"ElseIf", "Empty", "EndDeclare", "EndFor", "EndForeach", "EndIf", "EndSwitch", 
			"EndWhile", "Eval", "Exit", "Extends", "Final", "Finally", "FloatCast", 
			"For", "Foreach", "Function", "Global", "Goto", "If", "Implements", "Import", 
			"Include", "IncludeOnce", "InstanceOf", "InsteadOf", "Int8Cast", "Int16Cast", 
			"Int64Type", "IntType", "Interface", "IsSet", "List", "LogicalAnd", "LogicalOr", 
			"LogicalXor", "Namespace", "New", "Null", "ObjectType", "Parent_", "Partial", 
			"Print", "Private", "Protected", "Public", "Require", "RequireOnce", 
			"Resource", "Return", "Static", "StringType", "Switch", "Throw", "Trait", 
			"Try", "Typeof", "UintCast", "UnicodeCast", "Unset", "Use", "Var", "While", 
			"Yield", "From", "LambdaFn", "Get", "Set", "Call", "CallStatic", "Constructor", 
			"Destruct", "Wakeup", "Sleep", "Autoload", "IsSet__", "Unset__", "ToString__", 
			"Invoke", "SetState", "Clone__", "DebugInfo", "Namespace__", "Class__", 
			"Traic__", "Function__", "Method__", "Line__", "File__", "Dir__", "Spaceship", 
			"Lgeneric", "Rgeneric", "DoubleArrow", "Inc", "Dec", "IsIdentical", "IsNoidentical", 
			"IsEqual", "IsNotEq", "IsSmallerOrEqual", "IsGreaterOrEqual", "PlusEqual", 
			"MinusEqual", "MulEqual", "Pow", "PowEqual", "DivEqual", "Concaequal", 
			"ModEqual", "ShiftLeftEqual", "ShiftRightEqual", "AndEqual", "OrEqual", 
			"XorEqual", "BooleanOr", "BooleanAnd", "NullCoalescing", "NullCoalescingEqual", 
			"ShiftLeft", "ShiftRight", "DoubleColon", "ObjectOperator", "NamespaceSeparator", 
			"Ellipsis", "Less", "Greater", "Ampersand", "Pipe", "Bang", "Caret", 
			"Plus", "Minus", "Asterisk", "Percent", "Divide", "Tilde", "SuppressWarnings", 
			"Dollar", "Dot", "QuestionMark", "OpenRoundBracket", "CloseRoundBracket", 
			"OpenSquareBracket", "CloseSquareBracket", "OpenCurlyBracket", "CloseCurlyBracket", 
			"Comma", "Colon", "SemiColon", "Eq", "Quote", "BackQuote", "VarName", 
			"Label", "Octal", "Decimal", "Real", "Hex", "Binary", "BackQuoteString", 
			"SingleQuoteString", "DoubleQuote", "StartNowDoc", "StartHereDoc", "ErrorPhp", 
			"CurlyDollar", "UnicodeEscape", "StringPart", "Comment", "PHPEndSingleLineComment", 
			"CommentEnd", "HereDocText", "XmlText2"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PhpParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PhpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class HtmlDocumentContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PhpParser.EOF, 0); }
		public TerminalNode Shebang() { return getToken(PhpParser.Shebang, 0); }
		public List<HtmlElementOrPhpBlockContext> htmlElementOrPhpBlock() {
			return getRuleContexts(HtmlElementOrPhpBlockContext.class);
		}
		public HtmlElementOrPhpBlockContext htmlElementOrPhpBlock(int i) {
			return getRuleContext(HtmlElementOrPhpBlockContext.class,i);
		}
		public HtmlDocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_htmlDocument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterHtmlDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitHtmlDocument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitHtmlDocument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlDocumentContext htmlDocument() throws RecognitionException {
		HtmlDocumentContext _localctx = new HtmlDocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_htmlDocument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Shebang) {
				{
				setState(270);
				match(Shebang);
				}
			}

			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HtmlText) | (1L << XmlStart) | (1L << HtmlScriptOpen) | (1L << HtmlStyleOpen) | (1L << HtmlDtd) | (1L << HtmlOpen) | (1L << HtmlClose) | (1L << HtmlSlashClose) | (1L << HtmlSlash) | (1L << HtmlEquals) | (1L << HtmlStartQuoteString) | (1L << HtmlStartDoubleQuoteString) | (1L << HtmlHex) | (1L << HtmlDecimal) | (1L << HtmlName) | (1L << HtmlEndQuoteString) | (1L << HtmlQuoteString) | (1L << HtmlEndDoubleQuoteString) | (1L << HtmlDoubleQuoteString) | (1L << ScriptText) | (1L << ScriptClose) | (1L << StyleBody) | (1L << Abstract) | (1L << Array) | (1L << As) | (1L << BinaryCast) | (1L << BoolType) | (1L << BooleanConstant) | (1L << Break) | (1L << Callable) | (1L << Case) | (1L << Catch) | (1L << Class) | (1L << Clone) | (1L << Const) | (1L << Continue) | (1L << Declare) | (1L << Default) | (1L << Do) | (1L << DoubleCast) | (1L << DoubleType) | (1L << Echo) | (1L << Else))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ElseIf - 64)) | (1L << (Empty - 64)) | (1L << (EndDeclare - 64)) | (1L << (EndFor - 64)) | (1L << (EndForeach - 64)) | (1L << (EndIf - 64)) | (1L << (EndSwitch - 64)) | (1L << (EndWhile - 64)) | (1L << (Eval - 64)) | (1L << (Exit - 64)) | (1L << (Extends - 64)) | (1L << (Final - 64)) | (1L << (Finally - 64)) | (1L << (FloatCast - 64)) | (1L << (For - 64)) | (1L << (Foreach - 64)) | (1L << (Function - 64)) | (1L << (Global - 64)) | (1L << (Goto - 64)) | (1L << (If - 64)) | (1L << (Implements - 64)) | (1L << (Import - 64)) | (1L << (Include - 64)) | (1L << (IncludeOnce - 64)) | (1L << (InstanceOf - 64)) | (1L << (InsteadOf - 64)) | (1L << (Int8Cast - 64)) | (1L << (Int16Cast - 64)) | (1L << (Int64Type - 64)) | (1L << (IntType - 64)) | (1L << (Interface - 64)) | (1L << (IsSet - 64)) | (1L << (List - 64)) | (1L << (LogicalAnd - 64)) | (1L << (LogicalOr - 64)) | (1L << (LogicalXor - 64)) | (1L << (Namespace - 64)) | (1L << (New - 64)) | (1L << (Null - 64)) | (1L << (ObjectType - 64)) | (1L << (Parent_ - 64)) | (1L << (Partial - 64)) | (1L << (Print - 64)) | (1L << (Private - 64)) | (1L << (Protected - 64)) | (1L << (Public - 64)) | (1L << (Require - 64)) | (1L << (RequireOnce - 64)) | (1L << (Resource - 64)) | (1L << (Return - 64)) | (1L << (Static - 64)) | (1L << (StringType - 64)) | (1L << (Switch - 64)) | (1L << (Throw - 64)) | (1L << (Trait - 64)) | (1L << (Try - 64)) | (1L << (Typeof - 64)) | (1L << (UintCast - 64)) | (1L << (UnicodeCast - 64)) | (1L << (Unset - 64)) | (1L << (Use - 64)) | (1L << (Var - 64)) | (1L << (While - 64)) | (1L << (Yield - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (From - 128)) | (1L << (LambdaFn - 128)) | (1L << (Get - 128)) | (1L << (Set - 128)) | (1L << (Call - 128)) | (1L << (CallStatic - 128)) | (1L << (Constructor - 128)) | (1L << (Destruct - 128)) | (1L << (Wakeup - 128)) | (1L << (Sleep - 128)) | (1L << (Autoload - 128)) | (1L << (IsSet__ - 128)) | (1L << (Unset__ - 128)) | (1L << (ToString__ - 128)) | (1L << (Invoke - 128)) | (1L << (SetState - 128)) | (1L << (Clone__ - 128)) | (1L << (DebugInfo - 128)) | (1L << (Namespace__ - 128)) | (1L << (Class__ - 128)) | (1L << (Traic__ - 128)) | (1L << (Function__ - 128)) | (1L << (Method__ - 128)) | (1L << (Line__ - 128)) | (1L << (File__ - 128)) | (1L << (Dir__ - 128)) | (1L << (Inc - 128)) | (1L << (Dec - 128)) | (1L << (NamespaceSeparator - 128)))) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & ((1L << (Bang - 193)) | (1L << (Plus - 193)) | (1L << (Minus - 193)) | (1L << (Tilde - 193)) | (1L << (SuppressWarnings - 193)) | (1L << (Dollar - 193)) | (1L << (OpenRoundBracket - 193)) | (1L << (OpenSquareBracket - 193)) | (1L << (OpenCurlyBracket - 193)) | (1L << (SemiColon - 193)) | (1L << (VarName - 193)) | (1L << (Label - 193)) | (1L << (Octal - 193)) | (1L << (Decimal - 193)) | (1L << (Real - 193)) | (1L << (Hex - 193)) | (1L << (Binary - 193)) | (1L << (BackQuoteString - 193)) | (1L << (SingleQuoteString - 193)) | (1L << (DoubleQuote - 193)) | (1L << (StartNowDoc - 193)) | (1L << (StartHereDoc - 193)))) != 0)) {
				{
				{
				setState(273);
				htmlElementOrPhpBlock();
				}
				}
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(279);
			match(EOF);
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

	public static class HtmlElementOrPhpBlockContext extends ParserRuleContext {
		public HtmlElementsContext htmlElements() {
			return getRuleContext(HtmlElementsContext.class,0);
		}
		public PhpBlockContext phpBlock() {
			return getRuleContext(PhpBlockContext.class,0);
		}
		public ScriptTextPartContext scriptTextPart() {
			return getRuleContext(ScriptTextPartContext.class,0);
		}
		public HtmlElementOrPhpBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_htmlElementOrPhpBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterHtmlElementOrPhpBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitHtmlElementOrPhpBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitHtmlElementOrPhpBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlElementOrPhpBlockContext htmlElementOrPhpBlock() throws RecognitionException {
		HtmlElementOrPhpBlockContext _localctx = new HtmlElementOrPhpBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_htmlElementOrPhpBlock);
		try {
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				htmlElements();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				phpBlock();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(283);
				scriptTextPart();
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

	public static class HtmlElementsContext extends ParserRuleContext {
		public List<HtmlElementContext> htmlElement() {
			return getRuleContexts(HtmlElementContext.class);
		}
		public HtmlElementContext htmlElement(int i) {
			return getRuleContext(HtmlElementContext.class,i);
		}
		public HtmlElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_htmlElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterHtmlElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitHtmlElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitHtmlElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlElementsContext htmlElements() throws RecognitionException {
		HtmlElementsContext _localctx = new HtmlElementsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_htmlElements);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(287); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(286);
					htmlElement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(289); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class HtmlElementContext extends ParserRuleContext {
		public TerminalNode HtmlDtd() { return getToken(PhpParser.HtmlDtd, 0); }
		public TerminalNode HtmlScriptOpen() { return getToken(PhpParser.HtmlScriptOpen, 0); }
		public TerminalNode HtmlClose() { return getToken(PhpParser.HtmlClose, 0); }
		public TerminalNode HtmlStyleOpen() { return getToken(PhpParser.HtmlStyleOpen, 0); }
		public TerminalNode HtmlOpen() { return getToken(PhpParser.HtmlOpen, 0); }
		public TerminalNode HtmlName() { return getToken(PhpParser.HtmlName, 0); }
		public TerminalNode HtmlSlashClose() { return getToken(PhpParser.HtmlSlashClose, 0); }
		public TerminalNode HtmlSlash() { return getToken(PhpParser.HtmlSlash, 0); }
		public TerminalNode HtmlText() { return getToken(PhpParser.HtmlText, 0); }
		public TerminalNode HtmlEquals() { return getToken(PhpParser.HtmlEquals, 0); }
		public TerminalNode HtmlStartQuoteString() { return getToken(PhpParser.HtmlStartQuoteString, 0); }
		public TerminalNode HtmlEndQuoteString() { return getToken(PhpParser.HtmlEndQuoteString, 0); }
		public TerminalNode HtmlStartDoubleQuoteString() { return getToken(PhpParser.HtmlStartDoubleQuoteString, 0); }
		public TerminalNode HtmlEndDoubleQuoteString() { return getToken(PhpParser.HtmlEndDoubleQuoteString, 0); }
		public TerminalNode HtmlHex() { return getToken(PhpParser.HtmlHex, 0); }
		public TerminalNode HtmlDecimal() { return getToken(PhpParser.HtmlDecimal, 0); }
		public TerminalNode HtmlQuoteString() { return getToken(PhpParser.HtmlQuoteString, 0); }
		public TerminalNode HtmlDoubleQuoteString() { return getToken(PhpParser.HtmlDoubleQuoteString, 0); }
		public TerminalNode StyleBody() { return getToken(PhpParser.StyleBody, 0); }
		public TerminalNode ScriptClose() { return getToken(PhpParser.ScriptClose, 0); }
		public TerminalNode XmlStart() { return getToken(PhpParser.XmlStart, 0); }
		public TerminalNode XmlClose() { return getToken(PhpParser.XmlClose, 0); }
		public List<TerminalNode> XmlText() { return getTokens(PhpParser.XmlText); }
		public TerminalNode XmlText(int i) {
			return getToken(PhpParser.XmlText, i);
		}
		public HtmlElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_htmlElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterHtmlElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitHtmlElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitHtmlElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtmlElementContext htmlElement() throws RecognitionException {
		HtmlElementContext _localctx = new HtmlElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_htmlElement);
		int _la;
		try {
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlDtd:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(HtmlDtd);
				}
				break;
			case HtmlScriptOpen:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				match(HtmlScriptOpen);
				}
				break;
			case HtmlClose:
				enterOuterAlt(_localctx, 3);
				{
				setState(293);
				match(HtmlClose);
				}
				break;
			case HtmlStyleOpen:
				enterOuterAlt(_localctx, 4);
				{
				setState(294);
				match(HtmlStyleOpen);
				}
				break;
			case HtmlOpen:
				enterOuterAlt(_localctx, 5);
				{
				setState(295);
				match(HtmlOpen);
				}
				break;
			case HtmlName:
				enterOuterAlt(_localctx, 6);
				{
				setState(296);
				match(HtmlName);
				}
				break;
			case HtmlSlashClose:
				enterOuterAlt(_localctx, 7);
				{
				setState(297);
				match(HtmlSlashClose);
				}
				break;
			case HtmlSlash:
				enterOuterAlt(_localctx, 8);
				{
				setState(298);
				match(HtmlSlash);
				}
				break;
			case HtmlText:
				enterOuterAlt(_localctx, 9);
				{
				setState(299);
				match(HtmlText);
				}
				break;
			case HtmlEquals:
				enterOuterAlt(_localctx, 10);
				{
				setState(300);
				match(HtmlEquals);
				}
				break;
			case HtmlStartQuoteString:
				enterOuterAlt(_localctx, 11);
				{
				setState(301);
				match(HtmlStartQuoteString);
				}
				break;
			case HtmlEndQuoteString:
				enterOuterAlt(_localctx, 12);
				{
				setState(302);
				match(HtmlEndQuoteString);
				}
				break;
			case HtmlStartDoubleQuoteString:
				enterOuterAlt(_localctx, 13);
				{
				setState(303);
				match(HtmlStartDoubleQuoteString);
				}
				break;
			case HtmlEndDoubleQuoteString:
				enterOuterAlt(_localctx, 14);
				{
				setState(304);
				match(HtmlEndDoubleQuoteString);
				}
				break;
			case HtmlHex:
				enterOuterAlt(_localctx, 15);
				{
				setState(305);
				match(HtmlHex);
				}
				break;
			case HtmlDecimal:
				enterOuterAlt(_localctx, 16);
				{
				setState(306);
				match(HtmlDecimal);
				}
				break;
			case HtmlQuoteString:
				enterOuterAlt(_localctx, 17);
				{
				setState(307);
				match(HtmlQuoteString);
				}
				break;
			case HtmlDoubleQuoteString:
				enterOuterAlt(_localctx, 18);
				{
				setState(308);
				match(HtmlDoubleQuoteString);
				}
				break;
			case StyleBody:
				enterOuterAlt(_localctx, 19);
				{
				setState(309);
				match(StyleBody);
				}
				break;
			case ScriptClose:
				enterOuterAlt(_localctx, 20);
				{
				setState(310);
				match(ScriptClose);
				}
				break;
			case XmlStart:
				enterOuterAlt(_localctx, 21);
				{
				setState(311);
				match(XmlStart);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==XmlText) {
					{
					{
					setState(312);
					match(XmlText);
					}
					}
					setState(317);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(318);
				match(XmlClose);
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

	public static class ScriptTextPartContext extends ParserRuleContext {
		public List<TerminalNode> ScriptText() { return getTokens(PhpParser.ScriptText); }
		public TerminalNode ScriptText(int i) {
			return getToken(PhpParser.ScriptText, i);
		}
		public ScriptTextPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scriptTextPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterScriptTextPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitScriptTextPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitScriptTextPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptTextPartContext scriptTextPart() throws RecognitionException {
		ScriptTextPartContext _localctx = new ScriptTextPartContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scriptTextPart);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(322); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(321);
					match(ScriptText);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(324); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class PhpBlockContext extends ParserRuleContext {
		public List<ImportStatementContext> importStatement() {
			return getRuleContexts(ImportStatementContext.class);
		}
		public ImportStatementContext importStatement(int i) {
			return getRuleContext(ImportStatementContext.class,i);
		}
		public List<TopStatementContext> topStatement() {
			return getRuleContexts(TopStatementContext.class);
		}
		public TopStatementContext topStatement(int i) {
			return getRuleContext(TopStatementContext.class,i);
		}
		public PhpBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_phpBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPhpBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPhpBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPhpBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PhpBlockContext phpBlock() throws RecognitionException {
		PhpBlockContext _localctx = new PhpBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_phpBlock);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(326);
					importStatement();
					}
					} 
				}
				setState(331);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(333); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(332);
					topStatement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(335); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ImportStatementContext extends ParserRuleContext {
		public TerminalNode Import() { return getToken(PhpParser.Import, 0); }
		public TerminalNode Namespace() { return getToken(PhpParser.Namespace, 0); }
		public NamespaceNameListContext namespaceNameList() {
			return getRuleContext(NamespaceNameListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_importStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(Import);
			setState(338);
			match(Namespace);
			setState(339);
			namespaceNameList();
			setState(340);
			match(SemiColon);
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

	public static class TopStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public UseDeclarationContext useDeclaration() {
			return getRuleContext(UseDeclarationContext.class,0);
		}
		public NamespaceDeclarationContext namespaceDeclaration() {
			return getRuleContext(NamespaceDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public GlobalConstantDeclarationContext globalConstantDeclaration() {
			return getRuleContext(GlobalConstantDeclarationContext.class,0);
		}
		public TopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTopStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTopStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTopStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TopStatementContext topStatement() throws RecognitionException {
		TopStatementContext _localctx = new TopStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_topStatement);
		try {
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				useDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				namespaceDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(345);
				functionDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(346);
				classDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(347);
				globalConstantDeclaration();
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

	public static class UseDeclarationContext extends ParserRuleContext {
		public TerminalNode Use() { return getToken(PhpParser.Use, 0); }
		public UseDeclarationContentListContext useDeclarationContentList() {
			return getRuleContext(UseDeclarationContentListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public TerminalNode Function() { return getToken(PhpParser.Function, 0); }
		public TerminalNode Const() { return getToken(PhpParser.Const, 0); }
		public UseDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterUseDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitUseDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitUseDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDeclarationContext useDeclaration() throws RecognitionException {
		UseDeclarationContext _localctx = new UseDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_useDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(Use);
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(351);
				_la = _input.LA(1);
				if ( !(_la==Const || _la==Function) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(354);
			useDeclarationContentList();
			setState(355);
			match(SemiColon);
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

	public static class UseDeclarationContentListContext extends ParserRuleContext {
		public List<UseDeclarationContentContext> useDeclarationContent() {
			return getRuleContexts(UseDeclarationContentContext.class);
		}
		public UseDeclarationContentContext useDeclarationContent(int i) {
			return getRuleContext(UseDeclarationContentContext.class,i);
		}
		public List<TerminalNode> NamespaceSeparator() { return getTokens(PhpParser.NamespaceSeparator); }
		public TerminalNode NamespaceSeparator(int i) {
			return getToken(PhpParser.NamespaceSeparator, i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public UseDeclarationContentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDeclarationContentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterUseDeclarationContentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitUseDeclarationContentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitUseDeclarationContentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDeclarationContentListContext useDeclarationContentList() throws RecognitionException {
		UseDeclarationContentListContext _localctx = new UseDeclarationContentListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_useDeclarationContentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NamespaceSeparator) {
				{
				setState(357);
				match(NamespaceSeparator);
				}
			}

			setState(360);
			useDeclarationContent();
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(361);
				match(Comma);
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NamespaceSeparator) {
					{
					setState(362);
					match(NamespaceSeparator);
					}
				}

				setState(365);
				useDeclarationContent();
				}
				}
				setState(370);
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

	public static class UseDeclarationContentContext extends ParserRuleContext {
		public NamespaceNameListContext namespaceNameList() {
			return getRuleContext(NamespaceNameListContext.class,0);
		}
		public UseDeclarationContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDeclarationContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterUseDeclarationContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitUseDeclarationContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitUseDeclarationContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDeclarationContentContext useDeclarationContent() throws RecognitionException {
		UseDeclarationContentContext _localctx = new UseDeclarationContentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_useDeclarationContent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			namespaceNameList();
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

	public static class NamespaceDeclarationContext extends ParserRuleContext {
		public TerminalNode Namespace() { return getToken(PhpParser.Namespace, 0); }
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public NamespaceNameListContext namespaceNameList() {
			return getRuleContext(NamespaceNameListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<NamespaceStatementContext> namespaceStatement() {
			return getRuleContexts(NamespaceStatementContext.class);
		}
		public NamespaceStatementContext namespaceStatement(int i) {
			return getRuleContext(NamespaceStatementContext.class,i);
		}
		public NamespaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNamespaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNamespaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNamespaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceDeclarationContext namespaceDeclaration() throws RecognitionException {
		NamespaceDeclarationContext _localctx = new NamespaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_namespaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(Namespace);
			setState(388);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || _la==Label) {
					{
					setState(374);
					namespaceNameList();
					}
				}

				setState(377);
				match(OpenCurlyBracket);
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HtmlText) | (1L << XmlStart) | (1L << HtmlScriptOpen) | (1L << HtmlStyleOpen) | (1L << HtmlDtd) | (1L << HtmlOpen) | (1L << HtmlClose) | (1L << HtmlSlashClose) | (1L << HtmlSlash) | (1L << HtmlEquals) | (1L << HtmlStartQuoteString) | (1L << HtmlStartDoubleQuoteString) | (1L << HtmlHex) | (1L << HtmlDecimal) | (1L << HtmlName) | (1L << HtmlEndQuoteString) | (1L << HtmlQuoteString) | (1L << HtmlEndDoubleQuoteString) | (1L << HtmlDoubleQuoteString) | (1L << ScriptText) | (1L << ScriptClose) | (1L << StyleBody) | (1L << Abstract) | (1L << Array) | (1L << As) | (1L << BinaryCast) | (1L << BoolType) | (1L << BooleanConstant) | (1L << Break) | (1L << Callable) | (1L << Case) | (1L << Catch) | (1L << Class) | (1L << Clone) | (1L << Const) | (1L << Continue) | (1L << Declare) | (1L << Default) | (1L << Do) | (1L << DoubleCast) | (1L << DoubleType) | (1L << Echo) | (1L << Else))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ElseIf - 64)) | (1L << (Empty - 64)) | (1L << (EndDeclare - 64)) | (1L << (EndFor - 64)) | (1L << (EndForeach - 64)) | (1L << (EndIf - 64)) | (1L << (EndSwitch - 64)) | (1L << (EndWhile - 64)) | (1L << (Eval - 64)) | (1L << (Exit - 64)) | (1L << (Extends - 64)) | (1L << (Final - 64)) | (1L << (Finally - 64)) | (1L << (FloatCast - 64)) | (1L << (For - 64)) | (1L << (Foreach - 64)) | (1L << (Function - 64)) | (1L << (Global - 64)) | (1L << (Goto - 64)) | (1L << (If - 64)) | (1L << (Implements - 64)) | (1L << (Import - 64)) | (1L << (Include - 64)) | (1L << (IncludeOnce - 64)) | (1L << (InstanceOf - 64)) | (1L << (InsteadOf - 64)) | (1L << (Int8Cast - 64)) | (1L << (Int16Cast - 64)) | (1L << (Int64Type - 64)) | (1L << (IntType - 64)) | (1L << (Interface - 64)) | (1L << (IsSet - 64)) | (1L << (List - 64)) | (1L << (LogicalAnd - 64)) | (1L << (LogicalOr - 64)) | (1L << (LogicalXor - 64)) | (1L << (Namespace - 64)) | (1L << (New - 64)) | (1L << (Null - 64)) | (1L << (ObjectType - 64)) | (1L << (Parent_ - 64)) | (1L << (Partial - 64)) | (1L << (Print - 64)) | (1L << (Private - 64)) | (1L << (Protected - 64)) | (1L << (Public - 64)) | (1L << (Require - 64)) | (1L << (RequireOnce - 64)) | (1L << (Resource - 64)) | (1L << (Return - 64)) | (1L << (Static - 64)) | (1L << (StringType - 64)) | (1L << (Switch - 64)) | (1L << (Throw - 64)) | (1L << (Trait - 64)) | (1L << (Try - 64)) | (1L << (Typeof - 64)) | (1L << (UintCast - 64)) | (1L << (UnicodeCast - 64)) | (1L << (Unset - 64)) | (1L << (Use - 64)) | (1L << (Var - 64)) | (1L << (While - 64)) | (1L << (Yield - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (From - 128)) | (1L << (LambdaFn - 128)) | (1L << (Get - 128)) | (1L << (Set - 128)) | (1L << (Call - 128)) | (1L << (CallStatic - 128)) | (1L << (Constructor - 128)) | (1L << (Destruct - 128)) | (1L << (Wakeup - 128)) | (1L << (Sleep - 128)) | (1L << (Autoload - 128)) | (1L << (IsSet__ - 128)) | (1L << (Unset__ - 128)) | (1L << (ToString__ - 128)) | (1L << (Invoke - 128)) | (1L << (SetState - 128)) | (1L << (Clone__ - 128)) | (1L << (DebugInfo - 128)) | (1L << (Namespace__ - 128)) | (1L << (Class__ - 128)) | (1L << (Traic__ - 128)) | (1L << (Function__ - 128)) | (1L << (Method__ - 128)) | (1L << (Line__ - 128)) | (1L << (File__ - 128)) | (1L << (Dir__ - 128)) | (1L << (Inc - 128)) | (1L << (Dec - 128)) | (1L << (NamespaceSeparator - 128)))) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & ((1L << (Bang - 193)) | (1L << (Plus - 193)) | (1L << (Minus - 193)) | (1L << (Tilde - 193)) | (1L << (SuppressWarnings - 193)) | (1L << (Dollar - 193)) | (1L << (OpenRoundBracket - 193)) | (1L << (OpenSquareBracket - 193)) | (1L << (OpenCurlyBracket - 193)) | (1L << (SemiColon - 193)) | (1L << (VarName - 193)) | (1L << (Label - 193)) | (1L << (Octal - 193)) | (1L << (Decimal - 193)) | (1L << (Real - 193)) | (1L << (Hex - 193)) | (1L << (Binary - 193)) | (1L << (BackQuoteString - 193)) | (1L << (SingleQuoteString - 193)) | (1L << (DoubleQuote - 193)) | (1L << (StartNowDoc - 193)) | (1L << (StartHereDoc - 193)))) != 0)) {
					{
					{
					setState(378);
					namespaceStatement();
					}
					}
					setState(383);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(384);
				match(CloseCurlyBracket);
				}
				break;
			case 2:
				{
				setState(385);
				namespaceNameList();
				setState(386);
				match(SemiColon);
				}
				break;
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

	public static class NamespaceStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public UseDeclarationContext useDeclaration() {
			return getRuleContext(UseDeclarationContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public GlobalConstantDeclarationContext globalConstantDeclaration() {
			return getRuleContext(GlobalConstantDeclarationContext.class,0);
		}
		public NamespaceStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNamespaceStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNamespaceStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNamespaceStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceStatementContext namespaceStatement() throws RecognitionException {
		NamespaceStatementContext _localctx = new NamespaceStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_namespaceStatement);
		try {
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(390);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				useDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(392);
				functionDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(393);
				classDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(394);
				globalConstantDeclaration();
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

	public static class FunctionDeclarationContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode Function() { return getToken(PhpParser.Function, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public TypeParameterListInBracketsContext typeParameterListInBrackets() {
			return getRuleContext(TypeParameterListInBracketsContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(PhpParser.QuestionMark, 0); }
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			attributes();
			setState(398);
			match(Function);
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ampersand) {
				{
				setState(399);
				match(Ampersand);
				}
			}

			setState(402);
			identifier();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Lgeneric) {
				{
				setState(403);
				typeParameterListInBrackets();
				}
			}

			setState(406);
			match(OpenRoundBracket);
			setState(407);
			formalParameterList();
			setState(408);
			match(CloseRoundBracket);
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Colon) {
				{
				setState(409);
				match(Colon);
				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==QuestionMark) {
					{
					setState(410);
					match(QuestionMark);
					}
				}

				setState(413);
				typeHint();
				}
			}

			setState(416);
			blockStatement();
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
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public ClassEntryTypeContext classEntryType() {
			return getRuleContext(ClassEntryTypeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Interface() { return getToken(PhpParser.Interface, 0); }
		public TerminalNode Private() { return getToken(PhpParser.Private, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode Partial() { return getToken(PhpParser.Partial, 0); }
		public List<ClassStatementContext> classStatement() {
			return getRuleContexts(ClassStatementContext.class);
		}
		public ClassStatementContext classStatement(int i) {
			return getRuleContext(ClassStatementContext.class,i);
		}
		public TypeParameterListInBracketsContext typeParameterListInBrackets() {
			return getRuleContext(TypeParameterListInBracketsContext.class,0);
		}
		public TerminalNode Extends() { return getToken(PhpParser.Extends, 0); }
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public TerminalNode Implements() { return getToken(PhpParser.Implements, 0); }
		public InterfaceListContext interfaceList() {
			return getRuleContext(InterfaceListContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			attributes();
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Private) {
				{
				setState(419);
				match(Private);
				}
			}

			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Abstract || _la==Final) {
				{
				setState(422);
				modifier();
				}
			}

			setState(426);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Partial) {
				{
				setState(425);
				match(Partial);
				}
			}

			setState(450);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Class:
			case Trait:
				{
				setState(428);
				classEntryType();
				setState(429);
				identifier();
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(430);
					typeParameterListInBrackets();
					}
				}

				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Extends) {
					{
					setState(433);
					match(Extends);
					setState(434);
					qualifiedStaticTypeRef();
					}
				}

				setState(439);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Implements) {
					{
					setState(437);
					match(Implements);
					setState(438);
					interfaceList();
					}
				}

				}
				break;
			case Interface:
				{
				setState(441);
				match(Interface);
				setState(442);
				identifier();
				setState(444);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(443);
					typeParameterListInBrackets();
					}
				}

				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Extends) {
					{
					setState(446);
					match(Extends);
					setState(447);
					interfaceList();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(452);
			match(OpenCurlyBracket);
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Abstract || _la==Const || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Final - 75)) | (1L << (Function - 75)) | (1L << (Private - 75)) | (1L << (Protected - 75)) | (1L << (Public - 75)) | (1L << (Static - 75)) | (1L << (Use - 75)) | (1L << (Var - 75)))) != 0) || _la==OpenSquareBracket) {
				{
				{
				setState(453);
				classStatement();
				}
				}
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(459);
			match(CloseCurlyBracket);
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

	public static class ClassEntryTypeContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(PhpParser.Class, 0); }
		public TerminalNode Trait() { return getToken(PhpParser.Trait, 0); }
		public ClassEntryTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classEntryType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterClassEntryType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitClassEntryType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitClassEntryType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassEntryTypeContext classEntryType() throws RecognitionException {
		ClassEntryTypeContext _localctx = new ClassEntryTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_classEntryType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			_la = _input.LA(1);
			if ( !(_la==Class || _la==Trait) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class InterfaceListContext extends ParserRuleContext {
		public List<QualifiedStaticTypeRefContext> qualifiedStaticTypeRef() {
			return getRuleContexts(QualifiedStaticTypeRefContext.class);
		}
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef(int i) {
			return getRuleContext(QualifiedStaticTypeRefContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public InterfaceListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInterfaceList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInterfaceList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInterfaceList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterfaceListContext interfaceList() throws RecognitionException {
		InterfaceListContext _localctx = new InterfaceListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_interfaceList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			qualifiedStaticTypeRef();
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(464);
				match(Comma);
				setState(465);
				qualifiedStaticTypeRef();
				}
				}
				setState(470);
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

	public static class TypeParameterListInBracketsContext extends ParserRuleContext {
		public TerminalNode Lgeneric() { return getToken(PhpParser.Lgeneric, 0); }
		public TypeParameterListContext typeParameterList() {
			return getRuleContext(TypeParameterListContext.class,0);
		}
		public TerminalNode Rgeneric() { return getToken(PhpParser.Rgeneric, 0); }
		public TypeParameterWithDefaultsListContext typeParameterWithDefaultsList() {
			return getRuleContext(TypeParameterWithDefaultsListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(PhpParser.Comma, 0); }
		public TypeParameterListInBracketsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterListInBrackets; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeParameterListInBrackets(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeParameterListInBrackets(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeParameterListInBrackets(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterListInBracketsContext typeParameterListInBrackets() throws RecognitionException {
		TypeParameterListInBracketsContext _localctx = new TypeParameterListInBracketsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeParameterListInBrackets);
		try {
			setState(485);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(471);
				match(Lgeneric);
				setState(472);
				typeParameterList();
				setState(473);
				match(Rgeneric);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				match(Lgeneric);
				setState(476);
				typeParameterWithDefaultsList();
				setState(477);
				match(Rgeneric);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(479);
				match(Lgeneric);
				setState(480);
				typeParameterList();
				setState(481);
				match(Comma);
				setState(482);
				typeParameterWithDefaultsList();
				setState(483);
				match(Rgeneric);
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

	public static class TypeParameterListContext extends ParserRuleContext {
		public List<TypeParameterDeclContext> typeParameterDecl() {
			return getRuleContexts(TypeParameterDeclContext.class);
		}
		public TypeParameterDeclContext typeParameterDecl(int i) {
			return getRuleContext(TypeParameterDeclContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public TypeParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterListContext typeParameterList() throws RecognitionException {
		TypeParameterListContext _localctx = new TypeParameterListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeParameterList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			typeParameterDecl();
			setState(492);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(488);
					match(Comma);
					setState(489);
					typeParameterDecl();
					}
					} 
				}
				setState(494);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
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

	public static class TypeParameterWithDefaultsListContext extends ParserRuleContext {
		public List<TypeParameterWithDefaultDeclContext> typeParameterWithDefaultDecl() {
			return getRuleContexts(TypeParameterWithDefaultDeclContext.class);
		}
		public TypeParameterWithDefaultDeclContext typeParameterWithDefaultDecl(int i) {
			return getRuleContext(TypeParameterWithDefaultDeclContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public TypeParameterWithDefaultsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterWithDefaultsList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeParameterWithDefaultsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeParameterWithDefaultsList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeParameterWithDefaultsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterWithDefaultsListContext typeParameterWithDefaultsList() throws RecognitionException {
		TypeParameterWithDefaultsListContext _localctx = new TypeParameterWithDefaultsListContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeParameterWithDefaultsList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			typeParameterWithDefaultDecl();
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(496);
				match(Comma);
				setState(497);
				typeParameterWithDefaultDecl();
				}
				}
				setState(502);
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

	public static class TypeParameterDeclContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeParameterDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeParameterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeParameterDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeParameterDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterDeclContext typeParameterDecl() throws RecognitionException {
		TypeParameterDeclContext _localctx = new TypeParameterDeclContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_typeParameterDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			attributes();
			setState(504);
			identifier();
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

	public static class TypeParameterWithDefaultDeclContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TypeParameterWithDefaultDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameterWithDefaultDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeParameterWithDefaultDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeParameterWithDefaultDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeParameterWithDefaultDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeParameterWithDefaultDeclContext typeParameterWithDefaultDecl() throws RecognitionException {
		TypeParameterWithDefaultDeclContext _localctx = new TypeParameterWithDefaultDeclContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeParameterWithDefaultDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
			attributes();
			setState(507);
			identifier();
			setState(508);
			match(Eq);
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(509);
				qualifiedStaticTypeRef();
				}
				break;
			case 2:
				{
				setState(510);
				primitiveType();
				}
				break;
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

	public static class GenericDynamicArgsContext extends ParserRuleContext {
		public TerminalNode Lgeneric() { return getToken(PhpParser.Lgeneric, 0); }
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public TerminalNode Rgeneric() { return getToken(PhpParser.Rgeneric, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public GenericDynamicArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericDynamicArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterGenericDynamicArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitGenericDynamicArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitGenericDynamicArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericDynamicArgsContext genericDynamicArgs() throws RecognitionException {
		GenericDynamicArgsContext _localctx = new GenericDynamicArgsContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_genericDynamicArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(Lgeneric);
			setState(514);
			typeRef();
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(515);
				match(Comma);
				setState(516);
				typeRef();
				}
				}
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(522);
			match(Rgeneric);
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

	public static class AttributesContext extends ParserRuleContext {
		public List<AttributesGroupContext> attributesGroup() {
			return getRuleContexts(AttributesGroupContext.class);
		}
		public AttributesGroupContext attributesGroup(int i) {
			return getRuleContext(AttributesGroupContext.class,i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttributes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttributes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OpenSquareBracket) {
				{
				{
				setState(524);
				attributesGroup();
				}
				}
				setState(529);
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

	public static class AttributesGroupContext extends ParserRuleContext {
		public TerminalNode OpenSquareBracket() { return getToken(PhpParser.OpenSquareBracket, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TerminalNode CloseSquareBracket() { return getToken(PhpParser.CloseSquareBracket, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public AttributesGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributesGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttributesGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttributesGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttributesGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributesGroupContext attributesGroup() throws RecognitionException {
		AttributesGroupContext _localctx = new AttributesGroupContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_attributesGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			match(OpenSquareBracket);
			setState(534);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(531);
				identifier();
				setState(532);
				match(Colon);
				}
				break;
			}
			setState(536);
			attribute();
			setState(541);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(537);
				match(Comma);
				setState(538);
				attribute();
				}
				}
				setState(543);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(544);
			match(CloseSquareBracket);
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

	public static class AttributeContext extends ParserRuleContext {
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public AttributeArgListContext attributeArgList() {
			return getRuleContext(AttributeArgListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public AttributeNamedArgListContext attributeNamedArgList() {
			return getRuleContext(AttributeNamedArgListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(PhpParser.Comma, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_attribute);
		try {
			setState(564);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(546);
				qualifiedNamespaceName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(547);
				qualifiedNamespaceName();
				setState(548);
				match(OpenRoundBracket);
				setState(549);
				attributeArgList();
				setState(550);
				match(CloseRoundBracket);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(552);
				qualifiedNamespaceName();
				setState(553);
				match(OpenRoundBracket);
				setState(554);
				attributeNamedArgList();
				setState(555);
				match(CloseRoundBracket);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(557);
				qualifiedNamespaceName();
				setState(558);
				match(OpenRoundBracket);
				setState(559);
				attributeArgList();
				setState(560);
				match(Comma);
				setState(561);
				attributeNamedArgList();
				setState(562);
				match(CloseRoundBracket);
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

	public static class AttributeArgListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public AttributeArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttributeArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttributeArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttributeArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeArgListContext attributeArgList() throws RecognitionException {
		AttributeArgListContext _localctx = new AttributeArgListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_attributeArgList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(566);
			expression(0);
			setState(571);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(567);
					match(Comma);
					setState(568);
					expression(0);
					}
					} 
				}
				setState(573);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
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

	public static class AttributeNamedArgListContext extends ParserRuleContext {
		public List<AttributeNamedArgContext> attributeNamedArg() {
			return getRuleContexts(AttributeNamedArgContext.class);
		}
		public AttributeNamedArgContext attributeNamedArg(int i) {
			return getRuleContext(AttributeNamedArgContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public AttributeNamedArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeNamedArgList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttributeNamedArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttributeNamedArgList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttributeNamedArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeNamedArgListContext attributeNamedArgList() throws RecognitionException {
		AttributeNamedArgListContext _localctx = new AttributeNamedArgListContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attributeNamedArgList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
			attributeNamedArg();
			setState(579);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(575);
				match(Comma);
				setState(576);
				attributeNamedArg();
				}
				}
				setState(581);
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

	public static class AttributeNamedArgContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AttributeNamedArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributeNamedArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAttributeNamedArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAttributeNamedArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAttributeNamedArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeNamedArgContext attributeNamedArg() throws RecognitionException {
		AttributeNamedArgContext _localctx = new AttributeNamedArgContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_attributeNamedArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(582);
			match(VarName);
			setState(583);
			match(DoubleArrow);
			setState(584);
			expression(0);
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

	public static class InnerStatementListContext extends ParserRuleContext {
		public List<InnerStatementContext> innerStatement() {
			return getRuleContexts(InnerStatementContext.class);
		}
		public InnerStatementContext innerStatement(int i) {
			return getRuleContext(InnerStatementContext.class,i);
		}
		public InnerStatementListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerStatementList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInnerStatementList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInnerStatementList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInnerStatementList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerStatementListContext innerStatementList() throws RecognitionException {
		InnerStatementListContext _localctx = new InnerStatementListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_innerStatementList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(586);
					innerStatement();
					}
					} 
				}
				setState(591);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
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

	public static class InnerStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InnerStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInnerStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInnerStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInnerStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InnerStatementContext innerStatement() throws RecognitionException {
		InnerStatementContext _localctx = new InnerStatementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_innerStatement);
		try {
			setState(595);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(592);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(593);
				functionDeclaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(594);
				classDeclaration();
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

	public static class StatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public DoWhileStatementContext doWhileStatement() {
			return getRuleContext(DoWhileStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public YieldExpressionContext yieldExpression() {
			return getRuleContext(YieldExpressionContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public GlobalStatementContext globalStatement() {
			return getRuleContext(GlobalStatementContext.class,0);
		}
		public StaticVariableStatementContext staticVariableStatement() {
			return getRuleContext(StaticVariableStatementContext.class,0);
		}
		public EchoStatementContext echoStatement() {
			return getRuleContext(EchoStatementContext.class,0);
		}
		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class,0);
		}
		public UnsetStatementContext unsetStatement() {
			return getRuleContext(UnsetStatementContext.class,0);
		}
		public ForeachStatementContext foreachStatement() {
			return getRuleContext(ForeachStatementContext.class,0);
		}
		public TryCatchFinallyContext tryCatchFinally() {
			return getRuleContext(TryCatchFinallyContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public GotoStatementContext gotoStatement() {
			return getRuleContext(GotoStatementContext.class,0);
		}
		public DeclareStatementContext declareStatement() {
			return getRuleContext(DeclareStatementContext.class,0);
		}
		public EmptyStatementContext emptyStatement() {
			return getRuleContext(EmptyStatementContext.class,0);
		}
		public InlineHtmlStatementContext inlineHtmlStatement() {
			return getRuleContext(InlineHtmlStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_statement);
		try {
			setState(624);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(597);
				identifier();
				setState(598);
				match(Colon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(600);
				blockStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(601);
				ifStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(602);
				whileStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(603);
				doWhileStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(604);
				forStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(605);
				switchStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(606);
				breakStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(607);
				continueStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(608);
				returnStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(609);
				yieldExpression();
				setState(610);
				match(SemiColon);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(612);
				globalStatement();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(613);
				staticVariableStatement();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(614);
				echoStatement();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(615);
				expressionStatement();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(616);
				unsetStatement();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(617);
				foreachStatement();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(618);
				tryCatchFinally();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(619);
				throwStatement();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(620);
				gotoStatement();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(621);
				declareStatement();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(622);
				emptyStatement();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(623);
				inlineHtmlStatement();
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

	public static class EmptyStatementContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public EmptyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatementContext emptyStatement() throws RecognitionException {
		EmptyStatementContext _localctx = new EmptyStatementContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_emptyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(626);
			match(SemiColon);
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

	public static class BlockStatementContext extends ParserRuleContext {
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_blockStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(OpenCurlyBracket);
			setState(629);
			innerStatementList();
			setState(630);
			match(CloseCurlyBracket);
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

	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(PhpParser.If, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ElseIfStatementContext> elseIfStatement() {
			return getRuleContexts(ElseIfStatementContext.class);
		}
		public ElseIfStatementContext elseIfStatement(int i) {
			return getRuleContext(ElseIfStatementContext.class,i);
		}
		public ElseStatementContext elseStatement() {
			return getRuleContext(ElseStatementContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode EndIf() { return getToken(PhpParser.EndIf, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<ElseIfColonStatementContext> elseIfColonStatement() {
			return getRuleContexts(ElseIfColonStatementContext.class);
		}
		public ElseIfColonStatementContext elseIfColonStatement(int i) {
			return getRuleContext(ElseIfColonStatementContext.class,i);
		}
		public ElseColonStatementContext elseColonStatement() {
			return getRuleContext(ElseColonStatementContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ifStatement);
		int _la;
		try {
			int _alt;
			setState(660);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(632);
				match(If);
				setState(633);
				parentheses();
				setState(634);
				statement();
				setState(638);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(635);
						elseIfStatement();
						}
						} 
					}
					setState(640);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
				}
				setState(642);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(641);
					elseStatement();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(644);
				match(If);
				setState(645);
				parentheses();
				setState(646);
				match(Colon);
				setState(647);
				innerStatementList();
				setState(651);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ElseIf) {
					{
					{
					setState(648);
					elseIfColonStatement();
					}
					}
					setState(653);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(655);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Else) {
					{
					setState(654);
					elseColonStatement();
					}
				}

				setState(657);
				match(EndIf);
				setState(658);
				match(SemiColon);
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

	public static class ElseIfStatementContext extends ParserRuleContext {
		public TerminalNode ElseIf() { return getToken(PhpParser.ElseIf, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseIfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterElseIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitElseIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitElseIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfStatementContext elseIfStatement() throws RecognitionException {
		ElseIfStatementContext _localctx = new ElseIfStatementContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_elseIfStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			match(ElseIf);
			setState(663);
			parentheses();
			setState(664);
			statement();
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

	public static class ElseIfColonStatementContext extends ParserRuleContext {
		public TerminalNode ElseIf() { return getToken(PhpParser.ElseIf, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public ElseIfColonStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseIfColonStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterElseIfColonStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitElseIfColonStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitElseIfColonStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseIfColonStatementContext elseIfColonStatement() throws RecognitionException {
		ElseIfColonStatementContext _localctx = new ElseIfColonStatementContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_elseIfColonStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			match(ElseIf);
			setState(667);
			parentheses();
			setState(668);
			match(Colon);
			setState(669);
			innerStatementList();
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

	public static class ElseStatementContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(PhpParser.Else, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterElseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitElseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitElseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseStatementContext elseStatement() throws RecognitionException {
		ElseStatementContext _localctx = new ElseStatementContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_elseStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(671);
			match(Else);
			setState(672);
			statement();
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

	public static class ElseColonStatementContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(PhpParser.Else, 0); }
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public ElseColonStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseColonStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterElseColonStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitElseColonStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitElseColonStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseColonStatementContext elseColonStatement() throws RecognitionException {
		ElseColonStatementContext _localctx = new ElseColonStatementContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_elseColonStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(674);
			match(Else);
			setState(675);
			match(Colon);
			setState(676);
			innerStatementList();
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

	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(PhpParser.While, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode EndWhile() { return getToken(PhpParser.EndWhile, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(678);
			match(While);
			setState(679);
			parentheses();
			setState(686);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlText:
			case XmlStart:
			case HtmlScriptOpen:
			case HtmlStyleOpen:
			case HtmlDtd:
			case HtmlOpen:
			case HtmlClose:
			case HtmlSlashClose:
			case HtmlSlash:
			case HtmlEquals:
			case HtmlStartQuoteString:
			case HtmlStartDoubleQuoteString:
			case HtmlHex:
			case HtmlDecimal:
			case HtmlName:
			case HtmlEndQuoteString:
			case HtmlQuoteString:
			case HtmlEndDoubleQuoteString:
			case HtmlDoubleQuoteString:
			case ScriptText:
			case ScriptClose:
			case StyleBody:
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case LambdaFn:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Inc:
			case Dec:
			case NamespaceSeparator:
			case Bang:
			case Plus:
			case Minus:
			case Tilde:
			case SuppressWarnings:
			case Dollar:
			case OpenRoundBracket:
			case OpenSquareBracket:
			case OpenCurlyBracket:
			case SemiColon:
			case VarName:
			case Label:
			case Octal:
			case Decimal:
			case Real:
			case Hex:
			case Binary:
			case BackQuoteString:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				{
				setState(680);
				statement();
				}
				break;
			case Colon:
				{
				setState(681);
				match(Colon);
				setState(682);
				innerStatementList();
				setState(683);
				match(EndWhile);
				setState(684);
				match(SemiColon);
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

	public static class DoWhileStatementContext extends ParserRuleContext {
		public TerminalNode Do() { return getToken(PhpParser.Do, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode While() { return getToken(PhpParser.While, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public DoWhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterDoWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitDoWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitDoWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileStatementContext doWhileStatement() throws RecognitionException {
		DoWhileStatementContext _localctx = new DoWhileStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_doWhileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(688);
			match(Do);
			setState(689);
			statement();
			setState(690);
			match(While);
			setState(691);
			parentheses();
			setState(692);
			match(SemiColon);
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

	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(PhpParser.For, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public List<TerminalNode> SemiColon() { return getTokens(PhpParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(PhpParser.SemiColon, i);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode EndFor() { return getToken(PhpParser.EndFor, 0); }
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			match(For);
			setState(695);
			match(OpenRoundBracket);
			setState(697);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(696);
				forInit();
				}
			}

			setState(699);
			match(SemiColon);
			setState(701);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(700);
				expressionList();
				}
			}

			setState(703);
			match(SemiColon);
			setState(705);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(704);
				forUpdate();
				}
			}

			setState(707);
			match(CloseRoundBracket);
			setState(714);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlText:
			case XmlStart:
			case HtmlScriptOpen:
			case HtmlStyleOpen:
			case HtmlDtd:
			case HtmlOpen:
			case HtmlClose:
			case HtmlSlashClose:
			case HtmlSlash:
			case HtmlEquals:
			case HtmlStartQuoteString:
			case HtmlStartDoubleQuoteString:
			case HtmlHex:
			case HtmlDecimal:
			case HtmlName:
			case HtmlEndQuoteString:
			case HtmlQuoteString:
			case HtmlEndDoubleQuoteString:
			case HtmlDoubleQuoteString:
			case ScriptText:
			case ScriptClose:
			case StyleBody:
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case LambdaFn:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Inc:
			case Dec:
			case NamespaceSeparator:
			case Bang:
			case Plus:
			case Minus:
			case Tilde:
			case SuppressWarnings:
			case Dollar:
			case OpenRoundBracket:
			case OpenSquareBracket:
			case OpenCurlyBracket:
			case SemiColon:
			case VarName:
			case Label:
			case Octal:
			case Decimal:
			case Real:
			case Hex:
			case Binary:
			case BackQuoteString:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				{
				setState(708);
				statement();
				}
				break;
			case Colon:
				{
				setState(709);
				match(Colon);
				setState(710);
				innerStatementList();
				setState(711);
				match(EndFor);
				setState(712);
				match(SemiColon);
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

	public static class ForInitContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_forInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			expressionList();
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

	public static class ForUpdateContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitForUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitForUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(718);
			expressionList();
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

	public static class SwitchStatementContext extends ParserRuleContext {
		public TerminalNode Switch() { return getToken(PhpParser.Switch, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public TerminalNode EndSwitch() { return getToken(PhpParser.EndSwitch, 0); }
		public List<TerminalNode> SemiColon() { return getTokens(PhpParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(PhpParser.SemiColon, i);
		}
		public List<SwitchBlockContext> switchBlock() {
			return getRuleContexts(SwitchBlockContext.class);
		}
		public SwitchBlockContext switchBlock(int i) {
			return getRuleContext(SwitchBlockContext.class,i);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitSwitchStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitSwitchStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(720);
			match(Switch);
			setState(721);
			parentheses();
			setState(745);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenCurlyBracket:
				{
				setState(722);
				match(OpenCurlyBracket);
				setState(724);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SemiColon) {
					{
					setState(723);
					match(SemiColon);
					}
				}

				setState(729);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Case || _la==Default) {
					{
					{
					setState(726);
					switchBlock();
					}
					}
					setState(731);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(732);
				match(CloseCurlyBracket);
				}
				break;
			case Colon:
				{
				setState(733);
				match(Colon);
				setState(735);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SemiColon) {
					{
					setState(734);
					match(SemiColon);
					}
				}

				setState(740);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Case || _la==Default) {
					{
					{
					setState(737);
					switchBlock();
					}
					}
					setState(742);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(743);
				match(EndSwitch);
				setState(744);
				match(SemiColon);
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

	public static class SwitchBlockContext extends ParserRuleContext {
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public List<TerminalNode> Colon() { return getTokens(PhpParser.Colon); }
		public TerminalNode Colon(int i) {
			return getToken(PhpParser.Colon, i);
		}
		public List<TerminalNode> SemiColon() { return getTokens(PhpParser.SemiColon); }
		public TerminalNode SemiColon(int i) {
			return getToken(PhpParser.SemiColon, i);
		}
		public List<TerminalNode> Case() { return getTokens(PhpParser.Case); }
		public TerminalNode Case(int i) {
			return getToken(PhpParser.Case, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Default() { return getTokens(PhpParser.Default); }
		public TerminalNode Default(int i) {
			return getToken(PhpParser.Default, i);
		}
		public SwitchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterSwitchBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitSwitchBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitSwitchBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SwitchBlockContext switchBlock() throws RecognitionException {
		SwitchBlockContext _localctx = new SwitchBlockContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_switchBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(753); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(750);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case Case:
						{
						setState(747);
						match(Case);
						setState(748);
						expression(0);
						}
						break;
					case Default:
						{
						setState(749);
						match(Default);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(752);
					_la = _input.LA(1);
					if ( !(_la==Colon || _la==SemiColon) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(755); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(757);
			innerStatementList();
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

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(PhpParser.Break, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_breakStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(759);
			match(Break);
			setState(761);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(760);
				expression(0);
				}
			}

			setState(763);
			match(SemiColon);
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

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(PhpParser.Continue, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_continueStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765);
			match(Continue);
			setState(767);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(766);
				expression(0);
				}
			}

			setState(769);
			match(SemiColon);
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

	public static class ReturnStatementContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(PhpParser.Return, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			match(Return);
			setState(773);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(772);
				expression(0);
				}
			}

			setState(775);
			match(SemiColon);
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

	public static class ExpressionStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitExpressionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitExpressionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(777);
			expression(0);
			setState(778);
			match(SemiColon);
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

	public static class UnsetStatementContext extends ParserRuleContext {
		public TerminalNode Unset() { return getToken(PhpParser.Unset, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public ChainListContext chainList() {
			return getRuleContext(ChainListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public UnsetStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsetStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterUnsetStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitUnsetStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitUnsetStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsetStatementContext unsetStatement() throws RecognitionException {
		UnsetStatementContext _localctx = new UnsetStatementContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_unsetStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(780);
			match(Unset);
			setState(781);
			match(OpenRoundBracket);
			setState(782);
			chainList();
			setState(783);
			match(CloseRoundBracket);
			setState(784);
			match(SemiColon);
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

	public static class ForeachStatementContext extends ParserRuleContext {
		public TerminalNode Foreach() { return getToken(PhpParser.Foreach, 0); }
		public List<TerminalNode> OpenRoundBracket() { return getTokens(PhpParser.OpenRoundBracket); }
		public TerminalNode OpenRoundBracket(int i) {
			return getToken(PhpParser.OpenRoundBracket, i);
		}
		public List<ChainContext> chain() {
			return getRuleContexts(ChainContext.class);
		}
		public ChainContext chain(int i) {
			return getRuleContext(ChainContext.class,i);
		}
		public TerminalNode As() { return getToken(PhpParser.As, 0); }
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public List<TerminalNode> CloseRoundBracket() { return getTokens(PhpParser.CloseRoundBracket); }
		public TerminalNode CloseRoundBracket(int i) {
			return getToken(PhpParser.CloseRoundBracket, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode List() { return getToken(PhpParser.List, 0); }
		public AssignmentListContext assignmentList() {
			return getRuleContext(AssignmentListContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode EndForeach() { return getToken(PhpParser.EndForeach, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<TerminalNode> Ampersand() { return getTokens(PhpParser.Ampersand); }
		public TerminalNode Ampersand(int i) {
			return getToken(PhpParser.Ampersand, i);
		}
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public ForeachStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitForeachStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitForeachStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachStatementContext foreachStatement() throws RecognitionException {
		ForeachStatementContext _localctx = new ForeachStatementContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_foreachStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(786);
			match(Foreach);
			setState(825);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(787);
				match(OpenRoundBracket);
				setState(788);
				chain();
				setState(789);
				match(As);
				setState(791);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ampersand) {
					{
					setState(790);
					match(Ampersand);
					}
				}

				setState(793);
				assignable();
				setState(799);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DoubleArrow) {
					{
					setState(794);
					match(DoubleArrow);
					setState(796);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Ampersand) {
						{
						setState(795);
						match(Ampersand);
						}
					}

					setState(798);
					chain();
					}
				}

				setState(801);
				match(CloseRoundBracket);
				}
				break;
			case 2:
				{
				setState(803);
				match(OpenRoundBracket);
				setState(804);
				expression(0);
				setState(805);
				match(As);
				setState(806);
				assignable();
				setState(812);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DoubleArrow) {
					{
					setState(807);
					match(DoubleArrow);
					setState(809);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Ampersand) {
						{
						setState(808);
						match(Ampersand);
						}
					}

					setState(811);
					chain();
					}
				}

				setState(814);
				match(CloseRoundBracket);
				}
				break;
			case 3:
				{
				setState(816);
				match(OpenRoundBracket);
				setState(817);
				chain();
				setState(818);
				match(As);
				setState(819);
				match(List);
				setState(820);
				match(OpenRoundBracket);
				setState(821);
				assignmentList();
				setState(822);
				match(CloseRoundBracket);
				setState(823);
				match(CloseRoundBracket);
				}
				break;
			}
			setState(833);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlText:
			case XmlStart:
			case HtmlScriptOpen:
			case HtmlStyleOpen:
			case HtmlDtd:
			case HtmlOpen:
			case HtmlClose:
			case HtmlSlashClose:
			case HtmlSlash:
			case HtmlEquals:
			case HtmlStartQuoteString:
			case HtmlStartDoubleQuoteString:
			case HtmlHex:
			case HtmlDecimal:
			case HtmlName:
			case HtmlEndQuoteString:
			case HtmlQuoteString:
			case HtmlEndDoubleQuoteString:
			case HtmlDoubleQuoteString:
			case ScriptText:
			case ScriptClose:
			case StyleBody:
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case LambdaFn:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Inc:
			case Dec:
			case NamespaceSeparator:
			case Bang:
			case Plus:
			case Minus:
			case Tilde:
			case SuppressWarnings:
			case Dollar:
			case OpenRoundBracket:
			case OpenSquareBracket:
			case OpenCurlyBracket:
			case SemiColon:
			case VarName:
			case Label:
			case Octal:
			case Decimal:
			case Real:
			case Hex:
			case Binary:
			case BackQuoteString:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				{
				setState(827);
				statement();
				}
				break;
			case Colon:
				{
				setState(828);
				match(Colon);
				setState(829);
				innerStatementList();
				setState(830);
				match(EndForeach);
				setState(831);
				match(SemiColon);
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

	public static class TryCatchFinallyContext extends ParserRuleContext {
		public TerminalNode Try() { return getToken(PhpParser.Try, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public FinallyStatementContext finallyStatement() {
			return getRuleContext(FinallyStatementContext.class,0);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public TryCatchFinallyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryCatchFinally; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTryCatchFinally(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTryCatchFinally(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTryCatchFinally(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TryCatchFinallyContext tryCatchFinally() throws RecognitionException {
		TryCatchFinallyContext _localctx = new TryCatchFinallyContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_tryCatchFinally);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(835);
			match(Try);
			setState(836);
			blockStatement();
			setState(852);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(838); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(837);
						catchClause();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(840); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(843);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(842);
					finallyStatement();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(848);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Catch) {
					{
					{
					setState(845);
					catchClause();
					}
					}
					setState(850);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(851);
				finallyStatement();
				}
				break;
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

	public static class CatchClauseContext extends ParserRuleContext {
		public TerminalNode Catch() { return getToken(PhpParser.Catch, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public List<QualifiedStaticTypeRefContext> qualifiedStaticTypeRef() {
			return getRuleContexts(QualifiedStaticTypeRefContext.class);
		}
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef(int i) {
			return getRuleContext(QualifiedStaticTypeRefContext.class,i);
		}
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public List<TerminalNode> Pipe() { return getTokens(PhpParser.Pipe); }
		public TerminalNode Pipe(int i) {
			return getToken(PhpParser.Pipe, i);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitCatchClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitCatchClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(854);
			match(Catch);
			setState(855);
			match(OpenRoundBracket);
			setState(856);
			qualifiedStaticTypeRef();
			setState(861);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Pipe) {
				{
				{
				setState(857);
				match(Pipe);
				setState(858);
				qualifiedStaticTypeRef();
				}
				}
				setState(863);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(864);
			match(VarName);
			setState(865);
			match(CloseRoundBracket);
			setState(866);
			blockStatement();
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

	public static class FinallyStatementContext extends ParserRuleContext {
		public TerminalNode Finally() { return getToken(PhpParser.Finally, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public FinallyStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFinallyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFinallyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFinallyStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinallyStatementContext finallyStatement() throws RecognitionException {
		FinallyStatementContext _localctx = new FinallyStatementContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_finallyStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(868);
			match(Finally);
			setState(869);
			blockStatement();
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

	public static class ThrowStatementContext extends ParserRuleContext {
		public TerminalNode Throw() { return getToken(PhpParser.Throw, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitThrowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitThrowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(871);
			match(Throw);
			setState(872);
			expression(0);
			setState(873);
			match(SemiColon);
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

	public static class GotoStatementContext extends ParserRuleContext {
		public TerminalNode Goto() { return getToken(PhpParser.Goto, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public GotoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gotoStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterGotoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitGotoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitGotoStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GotoStatementContext gotoStatement() throws RecognitionException {
		GotoStatementContext _localctx = new GotoStatementContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_gotoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(875);
			match(Goto);
			setState(876);
			identifier();
			setState(877);
			match(SemiColon);
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

	public static class DeclareStatementContext extends ParserRuleContext {
		public TerminalNode Declare() { return getToken(PhpParser.Declare, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public DeclareListContext declareList() {
			return getRuleContext(DeclareListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public InnerStatementListContext innerStatementList() {
			return getRuleContext(InnerStatementListContext.class,0);
		}
		public TerminalNode EndDeclare() { return getToken(PhpParser.EndDeclare, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public DeclareStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterDeclareStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitDeclareStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitDeclareStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareStatementContext declareStatement() throws RecognitionException {
		DeclareStatementContext _localctx = new DeclareStatementContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_declareStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(879);
			match(Declare);
			setState(880);
			match(OpenRoundBracket);
			setState(881);
			declareList();
			setState(882);
			match(CloseRoundBracket);
			setState(889);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlText:
			case XmlStart:
			case HtmlScriptOpen:
			case HtmlStyleOpen:
			case HtmlDtd:
			case HtmlOpen:
			case HtmlClose:
			case HtmlSlashClose:
			case HtmlSlash:
			case HtmlEquals:
			case HtmlStartQuoteString:
			case HtmlStartDoubleQuoteString:
			case HtmlHex:
			case HtmlDecimal:
			case HtmlName:
			case HtmlEndQuoteString:
			case HtmlQuoteString:
			case HtmlEndDoubleQuoteString:
			case HtmlDoubleQuoteString:
			case ScriptText:
			case ScriptClose:
			case StyleBody:
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case LambdaFn:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Inc:
			case Dec:
			case NamespaceSeparator:
			case Bang:
			case Plus:
			case Minus:
			case Tilde:
			case SuppressWarnings:
			case Dollar:
			case OpenRoundBracket:
			case OpenSquareBracket:
			case OpenCurlyBracket:
			case SemiColon:
			case VarName:
			case Label:
			case Octal:
			case Decimal:
			case Real:
			case Hex:
			case Binary:
			case BackQuoteString:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				{
				setState(883);
				statement();
				}
				break;
			case Colon:
				{
				setState(884);
				match(Colon);
				setState(885);
				innerStatementList();
				setState(886);
				match(EndDeclare);
				setState(887);
				match(SemiColon);
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

	public static class InlineHtmlStatementContext extends ParserRuleContext {
		public List<InlineHtmlContext> inlineHtml() {
			return getRuleContexts(InlineHtmlContext.class);
		}
		public InlineHtmlContext inlineHtml(int i) {
			return getRuleContext(InlineHtmlContext.class,i);
		}
		public InlineHtmlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineHtmlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInlineHtmlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInlineHtmlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInlineHtmlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineHtmlStatementContext inlineHtmlStatement() throws RecognitionException {
		InlineHtmlStatementContext _localctx = new InlineHtmlStatementContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_inlineHtmlStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(892); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(891);
					inlineHtml();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(894); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class InlineHtmlContext extends ParserRuleContext {
		public HtmlElementsContext htmlElements() {
			return getRuleContext(HtmlElementsContext.class,0);
		}
		public ScriptTextPartContext scriptTextPart() {
			return getRuleContext(ScriptTextPartContext.class,0);
		}
		public InlineHtmlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inlineHtml; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInlineHtml(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInlineHtml(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInlineHtml(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InlineHtmlContext inlineHtml() throws RecognitionException {
		InlineHtmlContext _localctx = new InlineHtmlContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_inlineHtml);
		try {
			setState(898);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HtmlText:
			case XmlStart:
			case HtmlScriptOpen:
			case HtmlStyleOpen:
			case HtmlDtd:
			case HtmlOpen:
			case HtmlClose:
			case HtmlSlashClose:
			case HtmlSlash:
			case HtmlEquals:
			case HtmlStartQuoteString:
			case HtmlStartDoubleQuoteString:
			case HtmlHex:
			case HtmlDecimal:
			case HtmlName:
			case HtmlEndQuoteString:
			case HtmlQuoteString:
			case HtmlEndDoubleQuoteString:
			case HtmlDoubleQuoteString:
			case ScriptClose:
			case StyleBody:
				enterOuterAlt(_localctx, 1);
				{
				setState(896);
				htmlElements();
				}
				break;
			case ScriptText:
				enterOuterAlt(_localctx, 2);
				{
				setState(897);
				scriptTextPart();
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

	public static class DeclareListContext extends ParserRuleContext {
		public List<IdentifierInititalizerContext> identifierInititalizer() {
			return getRuleContexts(IdentifierInititalizerContext.class);
		}
		public IdentifierInititalizerContext identifierInititalizer(int i) {
			return getRuleContext(IdentifierInititalizerContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public DeclareListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterDeclareList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitDeclareList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitDeclareList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareListContext declareList() throws RecognitionException {
		DeclareListContext _localctx = new DeclareListContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_declareList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(900);
			identifierInititalizer();
			setState(905);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(901);
				match(Comma);
				setState(902);
				identifierInititalizer();
				}
				}
				setState(907);
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

	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFormalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_formalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(909);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Ellipsis - 187)) | (1L << (Ampersand - 187)) | (1L << (QuestionMark - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)))) != 0)) {
				{
				setState(908);
				formalParameter();
				}
			}

			setState(915);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(911);
				match(Comma);
				setState(912);
				formalParameter();
				}
				}
				setState(917);
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

	public static class FormalParameterContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public TerminalNode QuestionMark() { return getToken(PhpParser.QuestionMark, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public TerminalNode Ellipsis() { return getToken(PhpParser.Ellipsis, 0); }
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFormalParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFormalParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_formalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(918);
			attributes();
			setState(920);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==QuestionMark) {
				{
				setState(919);
				match(QuestionMark);
				}
			}

			setState(923);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || _la==NamespaceSeparator || _la==Label) {
				{
				setState(922);
				typeHint();
				}
			}

			setState(926);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ampersand) {
				{
				setState(925);
				match(Ampersand);
				}
			}

			setState(929);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ellipsis) {
				{
				setState(928);
				match(Ellipsis);
				}
			}

			setState(931);
			variableInitializer();
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

	public static class TypeHintContext extends ParserRuleContext {
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public TerminalNode Callable() { return getToken(PhpParser.Callable, 0); }
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TypeHintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeHint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeHint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeHint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeHintContext typeHint() throws RecognitionException {
		TypeHintContext _localctx = new TypeHintContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_typeHint);
		try {
			setState(936);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(933);
				qualifiedStaticTypeRef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(934);
				match(Callable);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(935);
				primitiveType();
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

	public static class GlobalStatementContext extends ParserRuleContext {
		public TerminalNode Global() { return getToken(PhpParser.Global, 0); }
		public List<GlobalVarContext> globalVar() {
			return getRuleContexts(GlobalVarContext.class);
		}
		public GlobalVarContext globalVar(int i) {
			return getRuleContext(GlobalVarContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public GlobalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterGlobalStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitGlobalStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitGlobalStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalStatementContext globalStatement() throws RecognitionException {
		GlobalStatementContext _localctx = new GlobalStatementContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_globalStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(938);
			match(Global);
			setState(939);
			globalVar();
			setState(944);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(940);
				match(Comma);
				setState(941);
				globalVar();
				}
				}
				setState(946);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(947);
			match(SemiColon);
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

	public static class GlobalVarContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public TerminalNode Dollar() { return getToken(PhpParser.Dollar, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public GlobalVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterGlobalVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitGlobalVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitGlobalVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalVarContext globalVar() throws RecognitionException {
		GlobalVarContext _localctx = new GlobalVarContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_globalVar);
		try {
			setState(957);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(949);
				match(VarName);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(950);
				match(Dollar);
				setState(951);
				chain();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(952);
				match(Dollar);
				setState(953);
				match(OpenCurlyBracket);
				setState(954);
				expression(0);
				setState(955);
				match(CloseCurlyBracket);
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

	public static class EchoStatementContext extends ParserRuleContext {
		public TerminalNode Echo() { return getToken(PhpParser.Echo, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public EchoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_echoStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterEchoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitEchoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitEchoStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EchoStatementContext echoStatement() throws RecognitionException {
		EchoStatementContext _localctx = new EchoStatementContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_echoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(959);
			match(Echo);
			setState(960);
			expressionList();
			setState(961);
			match(SemiColon);
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

	public static class StaticVariableStatementContext extends ParserRuleContext {
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public StaticVariableStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticVariableStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterStaticVariableStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitStaticVariableStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitStaticVariableStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaticVariableStatementContext staticVariableStatement() throws RecognitionException {
		StaticVariableStatementContext _localctx = new StaticVariableStatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_staticVariableStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(963);
			match(Static);
			setState(964);
			variableInitializer();
			setState(969);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(965);
				match(Comma);
				setState(966);
				variableInitializer();
				}
				}
				setState(971);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(972);
			match(SemiColon);
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

	public static class ClassStatementContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public PropertyModifiersContext propertyModifiers() {
			return getRuleContext(PropertyModifiersContext.class,0);
		}
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public TerminalNode Const() { return getToken(PhpParser.Const, 0); }
		public List<IdentifierInititalizerContext> identifierInititalizer() {
			return getRuleContexts(IdentifierInititalizerContext.class);
		}
		public IdentifierInititalizerContext identifierInititalizer(int i) {
			return getRuleContext(IdentifierInititalizerContext.class,i);
		}
		public MemberModifiersContext memberModifiers() {
			return getRuleContext(MemberModifiersContext.class,0);
		}
		public TerminalNode Function() { return getToken(PhpParser.Function, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public TypeParameterListInBracketsContext typeParameterListInBrackets() {
			return getRuleContext(TypeParameterListInBracketsContext.class,0);
		}
		public BaseCtorCallContext baseCtorCall() {
			return getRuleContext(BaseCtorCallContext.class,0);
		}
		public TerminalNode Use() { return getToken(PhpParser.Use, 0); }
		public QualifiedNamespaceNameListContext qualifiedNamespaceNameList() {
			return getRuleContext(QualifiedNamespaceNameListContext.class,0);
		}
		public TraitAdaptationsContext traitAdaptations() {
			return getRuleContext(TraitAdaptationsContext.class,0);
		}
		public ClassStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterClassStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitClassStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitClassStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassStatementContext classStatement() throws RecognitionException {
		ClassStatementContext _localctx = new ClassStatementContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_classStatement);
		int _la;
		try {
			setState(1031);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(974);
				attributes();
				setState(975);
				propertyModifiers();
				setState(977);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || _la==NamespaceSeparator || _la==Label) {
					{
					setState(976);
					typeHint();
					}
				}

				setState(979);
				variableInitializer();
				setState(984);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(980);
					match(Comma);
					setState(981);
					variableInitializer();
					}
					}
					setState(986);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(987);
				match(SemiColon);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(989);
				attributes();
				setState(991);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Abstract || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Final - 75)) | (1L << (Private - 75)) | (1L << (Protected - 75)) | (1L << (Public - 75)) | (1L << (Static - 75)))) != 0)) {
					{
					setState(990);
					memberModifiers();
					}
				}

				setState(993);
				match(Const);
				setState(995);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
				case 1:
					{
					setState(994);
					typeHint();
					}
					break;
				}
				setState(997);
				identifierInititalizer();
				setState(1002);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(998);
					match(Comma);
					setState(999);
					identifierInititalizer();
					}
					}
					setState(1004);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1005);
				match(SemiColon);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1007);
				attributes();
				setState(1009);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Abstract || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Final - 75)) | (1L << (Private - 75)) | (1L << (Protected - 75)) | (1L << (Public - 75)) | (1L << (Static - 75)))) != 0)) {
					{
					setState(1008);
					memberModifiers();
					}
				}

				setState(1011);
				match(Function);
				setState(1013);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ampersand) {
					{
					setState(1012);
					match(Ampersand);
					}
				}

				setState(1015);
				identifier();
				setState(1017);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(1016);
					typeParameterListInBrackets();
					}
				}

				setState(1019);
				match(OpenRoundBracket);
				setState(1020);
				formalParameterList();
				setState(1021);
				match(CloseRoundBracket);
				setState(1023);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(1022);
					baseCtorCall();
					}
				}

				setState(1025);
				methodBody();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1027);
				match(Use);
				setState(1028);
				qualifiedNamespaceNameList();
				setState(1029);
				traitAdaptations();
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

	public static class TraitAdaptationsContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public List<TraitAdaptationStatementContext> traitAdaptationStatement() {
			return getRuleContexts(TraitAdaptationStatementContext.class);
		}
		public TraitAdaptationStatementContext traitAdaptationStatement(int i) {
			return getRuleContext(TraitAdaptationStatementContext.class,i);
		}
		public TraitAdaptationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitAdaptations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTraitAdaptations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTraitAdaptations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTraitAdaptations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitAdaptationsContext traitAdaptations() throws RecognitionException {
		TraitAdaptationsContext _localctx = new TraitAdaptationsContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_traitAdaptations);
		int _la;
		try {
			setState(1042);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SemiColon:
				enterOuterAlt(_localctx, 1);
				{
				setState(1033);
				match(SemiColon);
				}
				break;
			case OpenCurlyBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(1034);
				match(OpenCurlyBracket);
				setState(1038);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || _la==NamespaceSeparator || _la==Label) {
					{
					{
					setState(1035);
					traitAdaptationStatement();
					}
					}
					setState(1040);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1041);
				match(CloseCurlyBracket);
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

	public static class TraitAdaptationStatementContext extends ParserRuleContext {
		public TraitPrecedenceContext traitPrecedence() {
			return getRuleContext(TraitPrecedenceContext.class,0);
		}
		public TraitAliasContext traitAlias() {
			return getRuleContext(TraitAliasContext.class,0);
		}
		public TraitAdaptationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitAdaptationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTraitAdaptationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTraitAdaptationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTraitAdaptationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitAdaptationStatementContext traitAdaptationStatement() throws RecognitionException {
		TraitAdaptationStatementContext _localctx = new TraitAdaptationStatementContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_traitAdaptationStatement);
		try {
			setState(1046);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1044);
				traitPrecedence();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1045);
				traitAlias();
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

	public static class TraitPrecedenceContext extends ParserRuleContext {
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public TerminalNode DoubleColon() { return getToken(PhpParser.DoubleColon, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode InsteadOf() { return getToken(PhpParser.InsteadOf, 0); }
		public QualifiedNamespaceNameListContext qualifiedNamespaceNameList() {
			return getRuleContext(QualifiedNamespaceNameListContext.class,0);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public TraitPrecedenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitPrecedence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTraitPrecedence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTraitPrecedence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTraitPrecedence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitPrecedenceContext traitPrecedence() throws RecognitionException {
		TraitPrecedenceContext _localctx = new TraitPrecedenceContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_traitPrecedence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1048);
			qualifiedNamespaceName();
			setState(1049);
			match(DoubleColon);
			setState(1050);
			identifier();
			setState(1051);
			match(InsteadOf);
			setState(1052);
			qualifiedNamespaceNameList();
			setState(1053);
			match(SemiColon);
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

	public static class TraitAliasContext extends ParserRuleContext {
		public TraitMethodReferenceContext traitMethodReference() {
			return getRuleContext(TraitMethodReferenceContext.class,0);
		}
		public TerminalNode As() { return getToken(PhpParser.As, 0); }
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public MemberModifierContext memberModifier() {
			return getRuleContext(MemberModifierContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TraitAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitAlias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTraitAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTraitAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTraitAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitAliasContext traitAlias() throws RecognitionException {
		TraitAliasContext _localctx = new TraitAliasContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_traitAlias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1055);
			traitMethodReference();
			setState(1056);
			match(As);
			setState(1062);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				{
				setState(1057);
				memberModifier();
				}
				break;
			case 2:
				{
				setState(1059);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(1058);
					memberModifier();
					}
					break;
				}
				setState(1061);
				identifier();
				}
				break;
			}
			setState(1064);
			match(SemiColon);
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

	public static class TraitMethodReferenceContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public TerminalNode DoubleColon() { return getToken(PhpParser.DoubleColon, 0); }
		public TraitMethodReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_traitMethodReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTraitMethodReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTraitMethodReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTraitMethodReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TraitMethodReferenceContext traitMethodReference() throws RecognitionException {
		TraitMethodReferenceContext _localctx = new TraitMethodReferenceContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_traitMethodReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(1066);
				qualifiedNamespaceName();
				setState(1067);
				match(DoubleColon);
				}
				break;
			}
			setState(1071);
			identifier();
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

	public static class BaseCtorCallContext extends ParserRuleContext {
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public BaseCtorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseCtorCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterBaseCtorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitBaseCtorCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitBaseCtorCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseCtorCallContext baseCtorCall() throws RecognitionException {
		BaseCtorCallContext _localctx = new BaseCtorCallContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_baseCtorCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1073);
			match(Colon);
			setState(1074);
			identifier();
			setState(1076);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OpenRoundBracket) {
				{
				setState(1075);
				arguments();
				}
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

	public static class MethodBodyContext extends ParserRuleContext {
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMethodBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMethodBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_methodBody);
		try {
			setState(1080);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SemiColon:
				enterOuterAlt(_localctx, 1);
				{
				setState(1078);
				match(SemiColon);
				}
				break;
			case OpenCurlyBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(1079);
				blockStatement();
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

	public static class PropertyModifiersContext extends ParserRuleContext {
		public MemberModifiersContext memberModifiers() {
			return getRuleContext(MemberModifiersContext.class,0);
		}
		public TerminalNode Var() { return getToken(PhpParser.Var, 0); }
		public PropertyModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPropertyModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPropertyModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPropertyModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyModifiersContext propertyModifiers() throws RecognitionException {
		PropertyModifiersContext _localctx = new PropertyModifiersContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_propertyModifiers);
		try {
			setState(1084);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Abstract:
			case Final:
			case Private:
			case Protected:
			case Public:
			case Static:
				enterOuterAlt(_localctx, 1);
				{
				setState(1082);
				memberModifiers();
				}
				break;
			case Var:
				enterOuterAlt(_localctx, 2);
				{
				setState(1083);
				match(Var);
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

	public static class MemberModifiersContext extends ParserRuleContext {
		public List<MemberModifierContext> memberModifier() {
			return getRuleContexts(MemberModifierContext.class);
		}
		public MemberModifierContext memberModifier(int i) {
			return getRuleContext(MemberModifierContext.class,i);
		}
		public MemberModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMemberModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMemberModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMemberModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberModifiersContext memberModifiers() throws RecognitionException {
		MemberModifiersContext _localctx = new MemberModifiersContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_memberModifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1087); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1086);
					memberModifier();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1089); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,112,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class VariableInitializerContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public ConstantInititalizerContext constantInititalizer() {
			return getRuleContext(ConstantInititalizerContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitVariableInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitVariableInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_variableInitializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1091);
			match(VarName);
			setState(1094);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Eq) {
				{
				setState(1092);
				match(Eq);
				setState(1093);
				constantInititalizer();
				}
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

	public static class IdentifierInititalizerContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public ConstantInititalizerContext constantInititalizer() {
			return getRuleContext(ConstantInititalizerContext.class,0);
		}
		public IdentifierInititalizerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierInititalizer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterIdentifierInititalizer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitIdentifierInititalizer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitIdentifierInititalizer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierInititalizerContext identifierInititalizer() throws RecognitionException {
		IdentifierInititalizerContext _localctx = new IdentifierInititalizerContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_identifierInititalizer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1096);
			identifier();
			setState(1097);
			match(Eq);
			setState(1098);
			constantInititalizer();
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

	public static class GlobalConstantDeclarationContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode Const() { return getToken(PhpParser.Const, 0); }
		public List<IdentifierInititalizerContext> identifierInititalizer() {
			return getRuleContexts(IdentifierInititalizerContext.class);
		}
		public IdentifierInititalizerContext identifierInititalizer(int i) {
			return getRuleContext(IdentifierInititalizerContext.class,i);
		}
		public TerminalNode SemiColon() { return getToken(PhpParser.SemiColon, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public GlobalConstantDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalConstantDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterGlobalConstantDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitGlobalConstantDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitGlobalConstantDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalConstantDeclarationContext globalConstantDeclaration() throws RecognitionException {
		GlobalConstantDeclarationContext _localctx = new GlobalConstantDeclarationContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_globalConstantDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1100);
			attributes();
			setState(1101);
			match(Const);
			setState(1102);
			identifierInititalizer();
			setState(1107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1103);
				match(Comma);
				setState(1104);
				identifierInititalizer();
				}
				}
				setState(1109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1110);
			match(SemiColon);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1112);
			expression(0);
			setState(1117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1113);
				match(Comma);
				setState(1114);
				expression(0);
				}
				}
				setState(1119);
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

	public static class ParenthesesContext extends ParserRuleContext {
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public YieldExpressionContext yieldExpression() {
			return getRuleContext(YieldExpressionContext.class,0);
		}
		public ParenthesesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parentheses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitParentheses(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesesContext parentheses() throws RecognitionException {
		ParenthesesContext _localctx = new ParenthesesContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_parentheses);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1120);
			match(OpenRoundBracket);
			setState(1123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				{
				setState(1121);
				expression(0);
				}
				break;
			case 2:
				{
				setState(1122);
				yieldExpression();
				}
				break;
			}
			setState(1125);
			match(CloseRoundBracket);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ChainExpressionContext extends ExpressionContext {
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public ChainExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterChainExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitChainExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitChainExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Tilde() { return getToken(PhpParser.Tilde, 0); }
		public TerminalNode SuppressWarnings() { return getToken(PhpParser.SuppressWarnings, 0); }
		public TerminalNode Bang() { return getToken(PhpParser.Bang, 0); }
		public TerminalNode Plus() { return getToken(PhpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(PhpParser.Minus, 0); }
		public UnaryOperatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterUnaryOperatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitUnaryOperatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitUnaryOperatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpecialWordExpressionContext extends ExpressionContext {
		public TerminalNode Yield() { return getToken(PhpParser.Yield, 0); }
		public TerminalNode List() { return getToken(PhpParser.List, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public AssignmentListContext assignmentList() {
			return getRuleContext(AssignmentListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IsSet() { return getToken(PhpParser.IsSet, 0); }
		public ChainListContext chainList() {
			return getRuleContext(ChainListContext.class,0);
		}
		public TerminalNode Empty() { return getToken(PhpParser.Empty, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public TerminalNode Eval() { return getToken(PhpParser.Eval, 0); }
		public TerminalNode Exit() { return getToken(PhpParser.Exit, 0); }
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public TerminalNode Include() { return getToken(PhpParser.Include, 0); }
		public TerminalNode IncludeOnce() { return getToken(PhpParser.IncludeOnce, 0); }
		public TerminalNode Require() { return getToken(PhpParser.Require, 0); }
		public TerminalNode RequireOnce() { return getToken(PhpParser.RequireOnce, 0); }
		public SpecialWordExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterSpecialWordExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitSpecialWordExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitSpecialWordExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreationExpressionContext extends ExpressionContext {
		public ArrayCreationContext arrayCreation() {
			return getRuleContext(ArrayCreationContext.class,0);
		}
		public ArrayCreationExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArrayCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArrayCreationExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArrayCreationExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewExpressionContext extends ExpressionContext {
		public NewExprContext newExpr() {
			return getRuleContext(NewExprContext.class,0);
		}
		public NewExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenthesisExpressionContext extends ExpressionContext {
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public ParenthesisExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterParenthesisExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitParenthesisExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitParenthesisExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SpaceshipExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Spaceship() { return getToken(PhpParser.Spaceship, 0); }
		public SpaceshipExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterSpaceshipExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitSpaceshipExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitSpaceshipExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BackQuoteStringExpressionContext extends ExpressionContext {
		public TerminalNode BackQuoteString() { return getToken(PhpParser.BackQuoteString, 0); }
		public BackQuoteStringExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterBackQuoteStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitBackQuoteStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitBackQuoteStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionalExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public TerminalNode QuestionMark() { return getToken(PhpParser.QuestionMark, 0); }
		public ConditionalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterConditionalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitConditionalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitConditionalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullCoalescingExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NullCoalescing() { return getToken(PhpParser.NullCoalescing, 0); }
		public NullCoalescingExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNullCoalescingExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNullCoalescingExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNullCoalescingExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithmeticExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Pow() { return getToken(PhpParser.Pow, 0); }
		public TerminalNode Asterisk() { return getToken(PhpParser.Asterisk, 0); }
		public TerminalNode Divide() { return getToken(PhpParser.Divide, 0); }
		public TerminalNode Percent() { return getToken(PhpParser.Percent, 0); }
		public TerminalNode Plus() { return getToken(PhpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(PhpParser.Minus, 0); }
		public TerminalNode Dot() { return getToken(PhpParser.Dot, 0); }
		public ArithmeticExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArithmeticExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArithmeticExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexerExpressionContext extends ExpressionContext {
		public StringConstantContext stringConstant() {
			return getRuleContext(StringConstantContext.class,0);
		}
		public TerminalNode OpenSquareBracket() { return getToken(PhpParser.OpenSquareBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseSquareBracket() { return getToken(PhpParser.CloseSquareBracket, 0); }
		public IndexerExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterIndexerExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitIndexerExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitIndexerExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ScalarExpressionContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode Label() { return getToken(PhpParser.Label, 0); }
		public ScalarExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterScalarExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitScalarExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitScalarExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrefixIncDecExpressionContext extends ExpressionContext {
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public TerminalNode Inc() { return getToken(PhpParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(PhpParser.Dec, 0); }
		public PrefixIncDecExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPrefixIncDecExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPrefixIncDecExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPrefixIncDecExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparisonExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ShiftLeft() { return getToken(PhpParser.ShiftLeft, 0); }
		public TerminalNode ShiftRight() { return getToken(PhpParser.ShiftRight, 0); }
		public TerminalNode Less() { return getToken(PhpParser.Less, 0); }
		public TerminalNode IsSmallerOrEqual() { return getToken(PhpParser.IsSmallerOrEqual, 0); }
		public TerminalNode Greater() { return getToken(PhpParser.Greater, 0); }
		public TerminalNode IsGreaterOrEqual() { return getToken(PhpParser.IsGreaterOrEqual, 0); }
		public TerminalNode IsIdentical() { return getToken(PhpParser.IsIdentical, 0); }
		public TerminalNode IsNoidentical() { return getToken(PhpParser.IsNoidentical, 0); }
		public TerminalNode IsEqual() { return getToken(PhpParser.IsEqual, 0); }
		public TerminalNode IsNotEq() { return getToken(PhpParser.IsNotEq, 0); }
		public ComparisonExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicalExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LogicalAnd() { return getToken(PhpParser.LogicalAnd, 0); }
		public TerminalNode LogicalXor() { return getToken(PhpParser.LogicalXor, 0); }
		public TerminalNode LogicalOr() { return getToken(PhpParser.LogicalOr, 0); }
		public LogicalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLogicalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLogicalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintExpressionContext extends ExpressionContext {
		public TerminalNode Print() { return getToken(PhpParser.Print, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPrintExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPrintExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPrintExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignmentExpressionContext extends ExpressionContext {
		public AssignableContext assignable() {
			return getRuleContext(AssignableContext.class,0);
		}
		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public NewExprContext newExpr() {
			return getRuleContext(NewExprContext.class,0);
		}
		public AssignmentExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAssignmentExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAssignmentExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PostfixIncDecExpressionContext extends ExpressionContext {
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public TerminalNode Inc() { return getToken(PhpParser.Inc, 0); }
		public TerminalNode Dec() { return getToken(PhpParser.Dec, 0); }
		public PostfixIncDecExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPostfixIncDecExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPostfixIncDecExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPostfixIncDecExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CastExpressionContext extends ExpressionContext {
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public CastOperationContext castOperation() {
			return getRuleContext(CastOperationContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstanceOfExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode InstanceOf() { return getToken(PhpParser.InstanceOf, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public InstanceOfExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInstanceOfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInstanceOfExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInstanceOfExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaFunctionExpressionContext extends ExpressionContext {
		public LambdaFunctionExprContext lambdaFunctionExpr() {
			return getRuleContext(LambdaFunctionExprContext.class,0);
		}
		public LambdaFunctionExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLambdaFunctionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLambdaFunctionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLambdaFunctionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitwiseExpressionContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public TerminalNode Caret() { return getToken(PhpParser.Caret, 0); }
		public TerminalNode Pipe() { return getToken(PhpParser.Pipe, 0); }
		public TerminalNode BooleanAnd() { return getToken(PhpParser.BooleanAnd, 0); }
		public TerminalNode BooleanOr() { return getToken(PhpParser.BooleanOr, 0); }
		public BitwiseExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterBitwiseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitBitwiseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitBitwiseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CloneExpressionContext extends ExpressionContext {
		public TerminalNode Clone() { return getToken(PhpParser.Clone, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CloneExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterCloneExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitCloneExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitCloneExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 166;
		enterRecursionRule(_localctx, 166, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				{
				_localctx = new CloneExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1128);
				match(Clone);
				setState(1129);
				expression(45);
				}
				break;
			case 2:
				{
				_localctx = new NewExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1130);
				newExpr();
				}
				break;
			case 3:
				{
				_localctx = new IndexerExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1131);
				stringConstant();
				setState(1132);
				match(OpenSquareBracket);
				setState(1133);
				expression(0);
				setState(1134);
				match(CloseSquareBracket);
				}
				break;
			case 4:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1136);
				match(OpenRoundBracket);
				setState(1137);
				castOperation();
				setState(1138);
				match(CloseRoundBracket);
				setState(1139);
				expression(42);
				}
				break;
			case 5:
				{
				_localctx = new UnaryOperatorExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1141);
				_la = _input.LA(1);
				if ( !(_la==Tilde || _la==SuppressWarnings) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1142);
				expression(41);
				}
				break;
			case 6:
				{
				_localctx = new UnaryOperatorExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1143);
				_la = _input.LA(1);
				if ( !(((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & ((1L << (Bang - 193)) | (1L << (Plus - 193)) | (1L << (Minus - 193)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1144);
				expression(40);
				}
				break;
			case 7:
				{
				_localctx = new PrefixIncDecExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1145);
				_la = _input.LA(1);
				if ( !(_la==Inc || _la==Dec) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1146);
				chain();
				}
				break;
			case 8:
				{
				_localctx = new PostfixIncDecExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1147);
				chain();
				setState(1148);
				_la = _input.LA(1);
				if ( !(_la==Inc || _la==Dec) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 9:
				{
				_localctx = new PrintExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1150);
				match(Print);
				setState(1151);
				expression(37);
				}
				break;
			case 10:
				{
				_localctx = new ChainExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1152);
				chain();
				}
				break;
			case 11:
				{
				_localctx = new ScalarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1153);
				constant();
				}
				break;
			case 12:
				{
				_localctx = new ScalarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1154);
				string();
				}
				break;
			case 13:
				{
				_localctx = new ScalarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1155);
				match(Label);
				}
				break;
			case 14:
				{
				_localctx = new BackQuoteStringExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1156);
				match(BackQuoteString);
				}
				break;
			case 15:
				{
				_localctx = new ParenthesisExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1157);
				parentheses();
				}
				break;
			case 16:
				{
				_localctx = new ArrayCreationExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1158);
				arrayCreation();
				}
				break;
			case 17:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1159);
				match(Yield);
				}
				break;
			case 18:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1160);
				match(List);
				setState(1161);
				match(OpenRoundBracket);
				setState(1162);
				assignmentList();
				setState(1163);
				match(CloseRoundBracket);
				setState(1164);
				match(Eq);
				setState(1165);
				expression(28);
				}
				break;
			case 19:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1167);
				match(IsSet);
				setState(1168);
				match(OpenRoundBracket);
				setState(1169);
				chainList();
				setState(1170);
				match(CloseRoundBracket);
				}
				break;
			case 20:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1172);
				match(Empty);
				setState(1173);
				match(OpenRoundBracket);
				setState(1174);
				chain();
				setState(1175);
				match(CloseRoundBracket);
				}
				break;
			case 21:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1177);
				match(Eval);
				setState(1178);
				match(OpenRoundBracket);
				setState(1179);
				expression(0);
				setState(1180);
				match(CloseRoundBracket);
				}
				break;
			case 22:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1182);
				match(Exit);
				setState(1186);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
				case 1:
					{
					setState(1183);
					match(OpenRoundBracket);
					setState(1184);
					match(CloseRoundBracket);
					}
					break;
				case 2:
					{
					setState(1185);
					parentheses();
					}
					break;
				}
				}
				break;
			case 23:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1188);
				_la = _input.LA(1);
				if ( !(_la==Include || _la==IncludeOnce) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1189);
				expression(23);
				}
				break;
			case 24:
				{
				_localctx = new SpecialWordExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1190);
				_la = _input.LA(1);
				if ( !(_la==Require || _la==RequireOnce) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1191);
				expression(22);
				}
				break;
			case 25:
				{
				_localctx = new LambdaFunctionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1192);
				lambdaFunctionExpr();
				}
				break;
			case 26:
				{
				_localctx = new AssignmentExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1193);
				assignable();
				setState(1194);
				assignmentOperator();
				setState(1195);
				expression(5);
				}
				break;
			case 27:
				{
				_localctx = new AssignmentExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1197);
				assignable();
				setState(1198);
				match(Eq);
				setState(1199);
				match(Ampersand);
				setState(1202);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
				case 1:
					{
					setState(1200);
					chain();
					}
					break;
				case 2:
					{
					setState(1201);
					newExpr();
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,122,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1264);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1206);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1207);
						((ArithmeticExpressionContext)_localctx).op = match(Pow);
						setState(1208);
						expression(20);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1209);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(1210);
						((ArithmeticExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 197)) & ~0x3f) == 0 && ((1L << (_la - 197)) & ((1L << (Asterisk - 197)) | (1L << (Percent - 197)) | (1L << (Divide - 197)))) != 0)) ) {
							((ArithmeticExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1211);
						expression(19);
						}
						break;
					case 3:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1212);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(1213);
						((ArithmeticExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 195)) & ~0x3f) == 0 && ((1L << (_la - 195)) & ((1L << (Plus - 195)) | (1L << (Minus - 195)) | (1L << (Dot - 195)))) != 0)) ) {
							((ArithmeticExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1214);
						expression(18);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1215);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1216);
						((ComparisonExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ShiftLeft || _la==ShiftRight) ) {
							((ComparisonExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1217);
						expression(17);
						}
						break;
					case 5:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1218);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(1219);
						((ComparisonExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 164)) & ~0x3f) == 0 && ((1L << (_la - 164)) & ((1L << (IsSmallerOrEqual - 164)) | (1L << (IsGreaterOrEqual - 164)) | (1L << (Less - 164)) | (1L << (Greater - 164)))) != 0)) ) {
							((ComparisonExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1220);
						expression(16);
						}
						break;
					case 6:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1221);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(1222);
						((ComparisonExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 160)) & ~0x3f) == 0 && ((1L << (_la - 160)) & ((1L << (IsIdentical - 160)) | (1L << (IsNoidentical - 160)) | (1L << (IsEqual - 160)) | (1L << (IsNotEq - 160)))) != 0)) ) {
							((ComparisonExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1223);
						expression(15);
						}
						break;
					case 7:
						{
						_localctx = new BitwiseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1224);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1225);
						((BitwiseExpressionContext)_localctx).op = match(Ampersand);
						setState(1226);
						expression(14);
						}
						break;
					case 8:
						{
						_localctx = new BitwiseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1227);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1228);
						((BitwiseExpressionContext)_localctx).op = match(Caret);
						setState(1229);
						expression(13);
						}
						break;
					case 9:
						{
						_localctx = new BitwiseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1230);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1231);
						((BitwiseExpressionContext)_localctx).op = match(Pipe);
						setState(1232);
						expression(12);
						}
						break;
					case 10:
						{
						_localctx = new BitwiseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1233);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1234);
						((BitwiseExpressionContext)_localctx).op = match(BooleanAnd);
						setState(1235);
						expression(11);
						}
						break;
					case 11:
						{
						_localctx = new BitwiseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1236);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1237);
						((BitwiseExpressionContext)_localctx).op = match(BooleanOr);
						setState(1238);
						expression(10);
						}
						break;
					case 12:
						{
						_localctx = new ConditionalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1239);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1240);
						((ConditionalExpressionContext)_localctx).op = match(QuestionMark);
						setState(1242);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
							{
							setState(1241);
							expression(0);
							}
						}

						setState(1244);
						match(Colon);
						setState(1245);
						expression(9);
						}
						break;
					case 13:
						{
						_localctx = new NullCoalescingExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1246);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1247);
						((NullCoalescingExpressionContext)_localctx).op = match(NullCoalescing);
						setState(1248);
						expression(8);
						}
						break;
					case 14:
						{
						_localctx = new SpaceshipExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1249);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1250);
						((SpaceshipExpressionContext)_localctx).op = match(Spaceship);
						setState(1251);
						expression(7);
						}
						break;
					case 15:
						{
						_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1252);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1253);
						((LogicalExpressionContext)_localctx).op = match(LogicalAnd);
						setState(1254);
						expression(4);
						}
						break;
					case 16:
						{
						_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1255);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1256);
						((LogicalExpressionContext)_localctx).op = match(LogicalXor);
						setState(1257);
						expression(3);
						}
						break;
					case 17:
						{
						_localctx = new LogicalExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1258);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1259);
						((LogicalExpressionContext)_localctx).op = match(LogicalOr);
						setState(1260);
						expression(2);
						}
						break;
					case 18:
						{
						_localctx = new InstanceOfExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1261);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1262);
						match(InstanceOf);
						setState(1263);
						typeRef();
						}
						break;
					}
					} 
				}
				setState(1268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,122,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AssignableContext extends ParserRuleContext {
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public ArrayCreationContext arrayCreation() {
			return getRuleContext(ArrayCreationContext.class,0);
		}
		public AssignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAssignable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAssignable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAssignable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignableContext assignable() throws RecognitionException {
		AssignableContext _localctx = new AssignableContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_assignable);
		try {
			setState(1271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1269);
				chain();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1270);
				arrayCreation();
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

	public static class ArrayCreationContext extends ParserRuleContext {
		public TerminalNode Array() { return getToken(PhpParser.Array, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public List<TerminalNode> OpenSquareBracket() { return getTokens(PhpParser.OpenSquareBracket); }
		public TerminalNode OpenSquareBracket(int i) {
			return getToken(PhpParser.OpenSquareBracket, i);
		}
		public List<TerminalNode> CloseSquareBracket() { return getTokens(PhpParser.CloseSquareBracket); }
		public TerminalNode CloseSquareBracket(int i) {
			return getToken(PhpParser.CloseSquareBracket, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayItemListContext arrayItemList() {
			return getRuleContext(ArrayItemListContext.class,0);
		}
		public ArrayCreationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArrayCreation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArrayCreation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArrayCreation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayCreationContext arrayCreation() throws RecognitionException {
		ArrayCreationContext _localctx = new ArrayCreationContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_arrayCreation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1284);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Array:
				{
				setState(1273);
				match(Array);
				setState(1274);
				match(OpenRoundBracket);
				setState(1276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Ampersand - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1275);
					arrayItemList();
					}
				}

				setState(1278);
				match(CloseRoundBracket);
				}
				break;
			case OpenSquareBracket:
				{
				setState(1279);
				match(OpenSquareBracket);
				setState(1281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Ampersand - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1280);
					arrayItemList();
					}
				}

				setState(1283);
				match(CloseSquareBracket);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				{
				setState(1286);
				match(OpenSquareBracket);
				setState(1287);
				expression(0);
				setState(1288);
				match(CloseSquareBracket);
				}
				break;
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

	public static class LambdaFunctionExprContext extends ParserRuleContext {
		public TerminalNode Function() { return getToken(PhpParser.Function, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public BlockStatementContext blockStatement() {
			return getRuleContext(BlockStatementContext.class,0);
		}
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public LambdaFunctionUseVarsContext lambdaFunctionUseVars() {
			return getRuleContext(LambdaFunctionUseVarsContext.class,0);
		}
		public TerminalNode Colon() { return getToken(PhpParser.Colon, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode LambdaFn() { return getToken(PhpParser.LambdaFn, 0); }
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LambdaFunctionExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaFunctionExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLambdaFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLambdaFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLambdaFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaFunctionExprContext lambdaFunctionExpr() throws RecognitionException {
		LambdaFunctionExprContext _localctx = new LambdaFunctionExprContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_lambdaFunctionExpr);
		int _la;
		try {
			setState(1318);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Function:
			case Static:
				enterOuterAlt(_localctx, 1);
				{
				setState(1293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Static) {
					{
					setState(1292);
					match(Static);
					}
				}

				setState(1295);
				match(Function);
				setState(1297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ampersand) {
					{
					setState(1296);
					match(Ampersand);
					}
				}

				setState(1299);
				match(OpenRoundBracket);
				setState(1300);
				formalParameterList();
				setState(1301);
				match(CloseRoundBracket);
				setState(1303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Use) {
					{
					setState(1302);
					lambdaFunctionUseVars();
					}
				}

				setState(1307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Colon) {
					{
					setState(1305);
					match(Colon);
					setState(1306);
					typeHint();
					}
				}

				setState(1309);
				blockStatement();
				}
				break;
			case LambdaFn:
				enterOuterAlt(_localctx, 2);
				{
				setState(1311);
				match(LambdaFn);
				setState(1312);
				match(OpenRoundBracket);
				setState(1313);
				formalParameterList();
				setState(1314);
				match(CloseRoundBracket);
				setState(1315);
				match(DoubleArrow);
				setState(1316);
				expression(0);
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

	public static class NewExprContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(PhpParser.New, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public NewExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExprContext newExpr() throws RecognitionException {
		NewExprContext _localctx = new NewExprContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_newExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1320);
			match(New);
			setState(1321);
			typeRef();
			setState(1323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				{
				setState(1322);
				arguments();
				}
				break;
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

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public TerminalNode Eq() { return getToken(PhpParser.Eq, 0); }
		public TerminalNode PlusEqual() { return getToken(PhpParser.PlusEqual, 0); }
		public TerminalNode MinusEqual() { return getToken(PhpParser.MinusEqual, 0); }
		public TerminalNode MulEqual() { return getToken(PhpParser.MulEqual, 0); }
		public TerminalNode PowEqual() { return getToken(PhpParser.PowEqual, 0); }
		public TerminalNode DivEqual() { return getToken(PhpParser.DivEqual, 0); }
		public TerminalNode Concaequal() { return getToken(PhpParser.Concaequal, 0); }
		public TerminalNode ModEqual() { return getToken(PhpParser.ModEqual, 0); }
		public TerminalNode AndEqual() { return getToken(PhpParser.AndEqual, 0); }
		public TerminalNode OrEqual() { return getToken(PhpParser.OrEqual, 0); }
		public TerminalNode XorEqual() { return getToken(PhpParser.XorEqual, 0); }
		public TerminalNode ShiftLeftEqual() { return getToken(PhpParser.ShiftLeftEqual, 0); }
		public TerminalNode ShiftRightEqual() { return getToken(PhpParser.ShiftRightEqual, 0); }
		public TerminalNode NullCoalescingEqual() { return getToken(PhpParser.NullCoalescingEqual, 0); }
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAssignmentOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAssignmentOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1325);
			_la = _input.LA(1);
			if ( !(((((_la - 166)) & ~0x3f) == 0 && ((1L << (_la - 166)) & ((1L << (PlusEqual - 166)) | (1L << (MinusEqual - 166)) | (1L << (MulEqual - 166)) | (1L << (PowEqual - 166)) | (1L << (DivEqual - 166)) | (1L << (Concaequal - 166)) | (1L << (ModEqual - 166)) | (1L << (ShiftLeftEqual - 166)) | (1L << (ShiftRightEqual - 166)) | (1L << (AndEqual - 166)) | (1L << (OrEqual - 166)) | (1L << (XorEqual - 166)) | (1L << (NullCoalescingEqual - 166)) | (1L << (Eq - 166)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class YieldExpressionContext extends ParserRuleContext {
		public TerminalNode Yield() { return getToken(PhpParser.Yield, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode From() { return getToken(PhpParser.From, 0); }
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public YieldExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yieldExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterYieldExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitYieldExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitYieldExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YieldExpressionContext yieldExpression() throws RecognitionException {
		YieldExpressionContext _localctx = new YieldExpressionContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_yieldExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1327);
			match(Yield);
			setState(1335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
			case 1:
				{
				setState(1328);
				expression(0);
				setState(1331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DoubleArrow) {
					{
					setState(1329);
					match(DoubleArrow);
					setState(1330);
					expression(0);
					}
				}

				}
				break;
			case 2:
				{
				setState(1333);
				match(From);
				setState(1334);
				expression(0);
				}
				break;
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

	public static class ArrayItemListContext extends ParserRuleContext {
		public List<ArrayItemContext> arrayItem() {
			return getRuleContexts(ArrayItemContext.class);
		}
		public ArrayItemContext arrayItem(int i) {
			return getRuleContext(ArrayItemContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public ArrayItemListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayItemList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArrayItemList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArrayItemList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArrayItemList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayItemListContext arrayItemList() throws RecognitionException {
		ArrayItemListContext _localctx = new ArrayItemListContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_arrayItemList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1337);
			arrayItem();
			setState(1342);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,136,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1338);
					match(Comma);
					setState(1339);
					arrayItem();
					}
					} 
				}
				setState(1344);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,136,_ctx);
			}
			setState(1346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(1345);
				match(Comma);
				}
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

	public static class ArrayItemContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public ArrayItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArrayItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArrayItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArrayItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayItemContext arrayItem() throws RecognitionException {
		ArrayItemContext _localctx = new ArrayItemContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_arrayItem);
		int _la;
		try {
			setState(1360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,140,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1348);
				expression(0);
				setState(1351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DoubleArrow) {
					{
					setState(1349);
					match(DoubleArrow);
					setState(1350);
					expression(0);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1353);
					expression(0);
					setState(1354);
					match(DoubleArrow);
					}
				}

				setState(1358);
				match(Ampersand);
				setState(1359);
				chain();
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

	public static class LambdaFunctionUseVarsContext extends ParserRuleContext {
		public TerminalNode Use() { return getToken(PhpParser.Use, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public List<LambdaFunctionUseVarContext> lambdaFunctionUseVar() {
			return getRuleContexts(LambdaFunctionUseVarContext.class);
		}
		public LambdaFunctionUseVarContext lambdaFunctionUseVar(int i) {
			return getRuleContext(LambdaFunctionUseVarContext.class,i);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public LambdaFunctionUseVarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaFunctionUseVars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLambdaFunctionUseVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLambdaFunctionUseVars(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLambdaFunctionUseVars(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaFunctionUseVarsContext lambdaFunctionUseVars() throws RecognitionException {
		LambdaFunctionUseVarsContext _localctx = new LambdaFunctionUseVarsContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_lambdaFunctionUseVars);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1362);
			match(Use);
			setState(1363);
			match(OpenRoundBracket);
			setState(1364);
			lambdaFunctionUseVar();
			setState(1369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1365);
				match(Comma);
				setState(1366);
				lambdaFunctionUseVar();
				}
				}
				setState(1371);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1372);
			match(CloseRoundBracket);
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

	public static class LambdaFunctionUseVarContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public LambdaFunctionUseVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaFunctionUseVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLambdaFunctionUseVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLambdaFunctionUseVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLambdaFunctionUseVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaFunctionUseVarContext lambdaFunctionUseVar() throws RecognitionException {
		LambdaFunctionUseVarContext _localctx = new LambdaFunctionUseVarContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_lambdaFunctionUseVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Ampersand) {
				{
				setState(1374);
				match(Ampersand);
				}
			}

			setState(1377);
			match(VarName);
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

	public static class QualifiedStaticTypeRefContext extends ParserRuleContext {
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public GenericDynamicArgsContext genericDynamicArgs() {
			return getRuleContext(GenericDynamicArgsContext.class,0);
		}
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public QualifiedStaticTypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedStaticTypeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterQualifiedStaticTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitQualifiedStaticTypeRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitQualifiedStaticTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedStaticTypeRefContext qualifiedStaticTypeRef() throws RecognitionException {
		QualifiedStaticTypeRefContext _localctx = new QualifiedStaticTypeRefContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_qualifiedStaticTypeRef);
		int _la;
		try {
			setState(1384);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1379);
				qualifiedNamespaceName();
				setState(1381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(1380);
					genericDynamicArgs();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1383);
				match(Static);
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

	public static class TypeRefContext extends ParserRuleContext {
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public IndirectTypeRefContext indirectTypeRef() {
			return getRuleContext(IndirectTypeRefContext.class,0);
		}
		public GenericDynamicArgsContext genericDynamicArgs() {
			return getRuleContext(GenericDynamicArgsContext.class,0);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public AnoymousClassContext anoymousClass() {
			return getRuleContext(AnoymousClassContext.class,0);
		}
		public TypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitTypeRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_typeRef);
		try {
			setState(1396);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1388);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,145,_ctx) ) {
				case 1:
					{
					setState(1386);
					qualifiedNamespaceName();
					}
					break;
				case 2:
					{
					setState(1387);
					indirectTypeRef();
					}
					break;
				}
				setState(1391);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,146,_ctx) ) {
				case 1:
					{
					setState(1390);
					genericDynamicArgs();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1393);
				primitiveType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1394);
				match(Static);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1395);
				anoymousClass();
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

	public static class AnoymousClassContext extends ParserRuleContext {
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public ClassEntryTypeContext classEntryType() {
			return getRuleContext(ClassEntryTypeContext.class,0);
		}
		public TerminalNode Interface() { return getToken(PhpParser.Interface, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Private() { return getToken(PhpParser.Private, 0); }
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public TerminalNode Partial() { return getToken(PhpParser.Partial, 0); }
		public List<ClassStatementContext> classStatement() {
			return getRuleContexts(ClassStatementContext.class);
		}
		public ClassStatementContext classStatement(int i) {
			return getRuleContext(ClassStatementContext.class,i);
		}
		public TypeParameterListInBracketsContext typeParameterListInBrackets() {
			return getRuleContext(TypeParameterListInBracketsContext.class,0);
		}
		public TerminalNode Extends() { return getToken(PhpParser.Extends, 0); }
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public TerminalNode Implements() { return getToken(PhpParser.Implements, 0); }
		public InterfaceListContext interfaceList() {
			return getRuleContext(InterfaceListContext.class,0);
		}
		public AnoymousClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anoymousClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAnoymousClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAnoymousClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAnoymousClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnoymousClassContext anoymousClass() throws RecognitionException {
		AnoymousClassContext _localctx = new AnoymousClassContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_anoymousClass);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1398);
			attributes();
			setState(1400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Private) {
				{
				setState(1399);
				match(Private);
				}
			}

			setState(1403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Abstract || _la==Final) {
				{
				setState(1402);
				modifier();
				}
			}

			setState(1406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Partial) {
				{
				setState(1405);
				match(Partial);
				}
			}

			setState(1429);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Class:
			case Trait:
				{
				setState(1408);
				classEntryType();
				setState(1410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(1409);
					typeParameterListInBrackets();
					}
				}

				setState(1414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Extends) {
					{
					setState(1412);
					match(Extends);
					setState(1413);
					qualifiedStaticTypeRef();
					}
				}

				setState(1418);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Implements) {
					{
					setState(1416);
					match(Implements);
					setState(1417);
					interfaceList();
					}
				}

				}
				break;
			case Interface:
				{
				setState(1420);
				match(Interface);
				setState(1421);
				identifier();
				setState(1423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Lgeneric) {
					{
					setState(1422);
					typeParameterListInBrackets();
					}
				}

				setState(1427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Extends) {
					{
					setState(1425);
					match(Extends);
					setState(1426);
					interfaceList();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1431);
			match(OpenCurlyBracket);
			setState(1435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Abstract || _la==Const || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Final - 75)) | (1L << (Function - 75)) | (1L << (Private - 75)) | (1L << (Protected - 75)) | (1L << (Public - 75)) | (1L << (Static - 75)) | (1L << (Use - 75)) | (1L << (Var - 75)))) != 0) || _la==OpenSquareBracket) {
				{
				{
				setState(1432);
				classStatement();
				}
				}
				setState(1437);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1438);
			match(CloseCurlyBracket);
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

	public static class IndirectTypeRefContext extends ParserRuleContext {
		public ChainBaseContext chainBase() {
			return getRuleContext(ChainBaseContext.class,0);
		}
		public List<TerminalNode> ObjectOperator() { return getTokens(PhpParser.ObjectOperator); }
		public TerminalNode ObjectOperator(int i) {
			return getToken(PhpParser.ObjectOperator, i);
		}
		public List<KeyedFieldNameContext> keyedFieldName() {
			return getRuleContexts(KeyedFieldNameContext.class);
		}
		public KeyedFieldNameContext keyedFieldName(int i) {
			return getRuleContext(KeyedFieldNameContext.class,i);
		}
		public IndirectTypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indirectTypeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterIndirectTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitIndirectTypeRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitIndirectTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndirectTypeRefContext indirectTypeRef() throws RecognitionException {
		IndirectTypeRefContext _localctx = new IndirectTypeRefContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_indirectTypeRef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1440);
			chainBase();
			setState(1445);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,158,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1441);
					match(ObjectOperator);
					setState(1442);
					keyedFieldName();
					}
					} 
				}
				setState(1447);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,158,_ctx);
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

	public static class QualifiedNamespaceNameContext extends ParserRuleContext {
		public NamespaceNameListContext namespaceNameList() {
			return getRuleContext(NamespaceNameListContext.class,0);
		}
		public TerminalNode Namespace() { return getToken(PhpParser.Namespace, 0); }
		public TerminalNode NamespaceSeparator() { return getToken(PhpParser.NamespaceSeparator, 0); }
		public QualifiedNamespaceNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNamespaceName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterQualifiedNamespaceName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitQualifiedNamespaceName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitQualifiedNamespaceName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNamespaceNameContext qualifiedNamespaceName() throws RecognitionException {
		QualifiedNamespaceNameContext _localctx = new QualifiedNamespaceNameContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_qualifiedNamespaceName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1449);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
			case 1:
				{
				setState(1448);
				match(Namespace);
				}
				break;
			}
			setState(1452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NamespaceSeparator) {
				{
				setState(1451);
				match(NamespaceSeparator);
				}
			}

			setState(1454);
			namespaceNameList();
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

	public static class NamespaceNameListContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> NamespaceSeparator() { return getTokens(PhpParser.NamespaceSeparator); }
		public TerminalNode NamespaceSeparator(int i) {
			return getToken(PhpParser.NamespaceSeparator, i);
		}
		public NamespaceNameTailContext namespaceNameTail() {
			return getRuleContext(NamespaceNameTailContext.class,0);
		}
		public NamespaceNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNamespaceNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNamespaceNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNamespaceNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceNameListContext namespaceNameList() throws RecognitionException {
		NamespaceNameListContext _localctx = new NamespaceNameListContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_namespaceNameList);
		try {
			int _alt;
			setState(1469);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,163,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1456);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1457);
				identifier();
				setState(1462);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,161,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1458);
						match(NamespaceSeparator);
						setState(1459);
						identifier();
						}
						} 
					}
					setState(1464);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,161,_ctx);
				}
				setState(1467);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,162,_ctx) ) {
				case 1:
					{
					setState(1465);
					match(NamespaceSeparator);
					setState(1466);
					namespaceNameTail();
					}
					break;
				}
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

	public static class NamespaceNameTailContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode As() { return getToken(PhpParser.As, 0); }
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public List<NamespaceNameTailContext> namespaceNameTail() {
			return getRuleContexts(NamespaceNameTailContext.class);
		}
		public NamespaceNameTailContext namespaceNameTail(int i) {
			return getRuleContext(NamespaceNameTailContext.class,i);
		}
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public NamespaceNameTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceNameTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNamespaceNameTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNamespaceNameTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNamespaceNameTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceNameTailContext namespaceNameTail() throws RecognitionException {
		NamespaceNameTailContext _localctx = new NamespaceNameTailContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_namespaceNameTail);
		int _la;
		try {
			int _alt;
			setState(1490);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Label:
				enterOuterAlt(_localctx, 1);
				{
				setState(1471);
				identifier();
				setState(1474);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,164,_ctx) ) {
				case 1:
					{
					setState(1472);
					match(As);
					setState(1473);
					identifier();
					}
					break;
				}
				}
				break;
			case OpenCurlyBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(1476);
				match(OpenCurlyBracket);
				setState(1477);
				namespaceNameTail();
				setState(1482);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,165,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1478);
						match(Comma);
						setState(1479);
						namespaceNameTail();
						}
						} 
					}
					setState(1484);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,165,_ctx);
				}
				setState(1486);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(1485);
					match(Comma);
					}
				}

				setState(1488);
				match(CloseCurlyBracket);
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

	public static class QualifiedNamespaceNameListContext extends ParserRuleContext {
		public List<QualifiedNamespaceNameContext> qualifiedNamespaceName() {
			return getRuleContexts(QualifiedNamespaceNameContext.class);
		}
		public QualifiedNamespaceNameContext qualifiedNamespaceName(int i) {
			return getRuleContext(QualifiedNamespaceNameContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public QualifiedNamespaceNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNamespaceNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterQualifiedNamespaceNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitQualifiedNamespaceNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitQualifiedNamespaceNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNamespaceNameListContext qualifiedNamespaceNameList() throws RecognitionException {
		QualifiedNamespaceNameListContext _localctx = new QualifiedNamespaceNameListContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_qualifiedNamespaceNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1492);
			qualifiedNamespaceName();
			setState(1497);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1493);
				match(Comma);
				setState(1494);
				qualifiedNamespaceName();
				}
				}
				setState(1499);
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

	public static class ArgumentsContext extends ParserRuleContext {
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public List<ActualArgumentContext> actualArgument() {
			return getRuleContexts(ActualArgumentContext.class);
		}
		public ActualArgumentContext actualArgument(int i) {
			return getRuleContext(ActualArgumentContext.class,i);
		}
		public YieldExpressionContext yieldExpression() {
			return getRuleContext(YieldExpressionContext.class,0);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_arguments);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1500);
			match(OpenRoundBracket);
			setState(1510);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
			case 1:
				{
				setState(1501);
				actualArgument();
				setState(1506);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1502);
						match(Comma);
						setState(1503);
						actualArgument();
						}
						} 
					}
					setState(1508);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,169,_ctx);
				}
				}
				break;
			case 2:
				{
				setState(1509);
				yieldExpression();
				}
				break;
			}
			setState(1513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Comma) {
				{
				setState(1512);
				match(Comma);
				}
			}

			setState(1515);
			match(CloseRoundBracket);
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

	public static class ActualArgumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Ellipsis() { return getToken(PhpParser.Ellipsis, 0); }
		public TerminalNode Ampersand() { return getToken(PhpParser.Ampersand, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public ActualArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterActualArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitActualArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitActualArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualArgumentContext actualArgument() throws RecognitionException {
		ActualArgumentContext _localctx = new ActualArgumentContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_actualArgument);
		int _la;
		try {
			setState(1523);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case LambdaFn:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Inc:
			case Dec:
			case NamespaceSeparator:
			case Ellipsis:
			case Bang:
			case Plus:
			case Minus:
			case Tilde:
			case SuppressWarnings:
			case Dollar:
			case OpenRoundBracket:
			case OpenSquareBracket:
			case VarName:
			case Label:
			case Octal:
			case Decimal:
			case Real:
			case Hex:
			case Binary:
			case BackQuoteString:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				enterOuterAlt(_localctx, 1);
				{
				setState(1518);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Ellipsis) {
					{
					setState(1517);
					match(Ellipsis);
					}
				}

				setState(1520);
				expression(0);
				}
				break;
			case Ampersand:
				enterOuterAlt(_localctx, 2);
				{
				setState(1521);
				match(Ampersand);
				setState(1522);
				chain();
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

	public static class ConstantInititalizerContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode Array() { return getToken(PhpParser.Array, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public ConstantArrayItemListContext constantArrayItemList() {
			return getRuleContext(ConstantArrayItemListContext.class,0);
		}
		public TerminalNode Comma() { return getToken(PhpParser.Comma, 0); }
		public TerminalNode OpenSquareBracket() { return getToken(PhpParser.OpenSquareBracket, 0); }
		public TerminalNode CloseSquareBracket() { return getToken(PhpParser.CloseSquareBracket, 0); }
		public ConstantInititalizerContext constantInititalizer() {
			return getRuleContext(ConstantInititalizerContext.class,0);
		}
		public TerminalNode Plus() { return getToken(PhpParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(PhpParser.Minus, 0); }
		public ConstantInititalizerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantInititalizer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterConstantInititalizer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitConstantInititalizer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitConstantInititalizer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantInititalizerContext constantInititalizer() throws RecognitionException {
		ConstantInititalizerContext _localctx = new ConstantInititalizerContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_constantInititalizer);
		int _la;
		try {
			setState(1546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,178,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1525);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1526);
				string();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1527);
				match(Array);
				setState(1528);
				match(OpenRoundBracket);
				setState(1533);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Dollar - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1529);
					constantArrayItemList();
					setState(1531);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Comma) {
						{
						setState(1530);
						match(Comma);
						}
					}

					}
				}

				setState(1535);
				match(CloseRoundBracket);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1536);
				match(OpenSquareBracket);
				setState(1541);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Dollar - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1537);
					constantArrayItemList();
					setState(1539);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Comma) {
						{
						setState(1538);
						match(Comma);
						}
					}

					}
				}

				setState(1543);
				match(CloseSquareBracket);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1544);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1545);
				constantInititalizer();
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

	public static class ConstantArrayItemListContext extends ParserRuleContext {
		public List<ConstantArrayItemContext> constantArrayItem() {
			return getRuleContexts(ConstantArrayItemContext.class);
		}
		public ConstantArrayItemContext constantArrayItem(int i) {
			return getRuleContext(ConstantArrayItemContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public ConstantArrayItemListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantArrayItemList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterConstantArrayItemList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitConstantArrayItemList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitConstantArrayItemList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantArrayItemListContext constantArrayItemList() throws RecognitionException {
		ConstantArrayItemListContext _localctx = new ConstantArrayItemListContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_constantArrayItemList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1548);
			constantArrayItem();
			setState(1553);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,179,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1549);
					match(Comma);
					setState(1550);
					constantArrayItem();
					}
					} 
				}
				setState(1555);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,179,_ctx);
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

	public static class ConstantArrayItemContext extends ParserRuleContext {
		public List<ConstantInititalizerContext> constantInititalizer() {
			return getRuleContexts(ConstantInititalizerContext.class);
		}
		public ConstantInititalizerContext constantInititalizer(int i) {
			return getRuleContext(ConstantInititalizerContext.class,i);
		}
		public TerminalNode DoubleArrow() { return getToken(PhpParser.DoubleArrow, 0); }
		public ConstantArrayItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantArrayItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterConstantArrayItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitConstantArrayItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitConstantArrayItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantArrayItemContext constantArrayItem() throws RecognitionException {
		ConstantArrayItemContext _localctx = new ConstantArrayItemContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_constantArrayItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1556);
			constantInititalizer();
			setState(1559);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DoubleArrow) {
				{
				setState(1557);
				match(DoubleArrow);
				setState(1558);
				constantInititalizer();
				}
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

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode Null() { return getToken(PhpParser.Null, 0); }
		public LiteralConstantContext literalConstant() {
			return getRuleContext(LiteralConstantContext.class,0);
		}
		public MagicConstantContext magicConstant() {
			return getRuleContext(MagicConstantContext.class,0);
		}
		public ClassConstantContext classConstant() {
			return getRuleContext(ClassConstantContext.class,0);
		}
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_constant);
		try {
			setState(1566);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,181,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1561);
				match(Null);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1562);
				literalConstant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1563);
				magicConstant();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1564);
				classConstant();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1565);
				qualifiedNamespaceName();
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

	public static class LiteralConstantContext extends ParserRuleContext {
		public TerminalNode Real() { return getToken(PhpParser.Real, 0); }
		public TerminalNode BooleanConstant() { return getToken(PhpParser.BooleanConstant, 0); }
		public NumericConstantContext numericConstant() {
			return getRuleContext(NumericConstantContext.class,0);
		}
		public StringConstantContext stringConstant() {
			return getRuleContext(StringConstantContext.class,0);
		}
		public LiteralConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterLiteralConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitLiteralConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitLiteralConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralConstantContext literalConstant() throws RecognitionException {
		LiteralConstantContext _localctx = new LiteralConstantContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_literalConstant);
		try {
			setState(1572);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Real:
				enterOuterAlt(_localctx, 1);
				{
				setState(1568);
				match(Real);
				}
				break;
			case BooleanConstant:
				enterOuterAlt(_localctx, 2);
				{
				setState(1569);
				match(BooleanConstant);
				}
				break;
			case Octal:
			case Decimal:
			case Hex:
			case Binary:
				enterOuterAlt(_localctx, 3);
				{
				setState(1570);
				numericConstant();
				}
				break;
			case Label:
				enterOuterAlt(_localctx, 4);
				{
				setState(1571);
				stringConstant();
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

	public static class NumericConstantContext extends ParserRuleContext {
		public TerminalNode Octal() { return getToken(PhpParser.Octal, 0); }
		public TerminalNode Decimal() { return getToken(PhpParser.Decimal, 0); }
		public TerminalNode Hex() { return getToken(PhpParser.Hex, 0); }
		public TerminalNode Binary() { return getToken(PhpParser.Binary, 0); }
		public NumericConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterNumericConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitNumericConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitNumericConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericConstantContext numericConstant() throws RecognitionException {
		NumericConstantContext _localctx = new NumericConstantContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_numericConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1574);
			_la = _input.LA(1);
			if ( !(((((_la - 219)) & ~0x3f) == 0 && ((1L << (_la - 219)) & ((1L << (Octal - 219)) | (1L << (Decimal - 219)) | (1L << (Hex - 219)) | (1L << (Binary - 219)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ClassConstantContext extends ParserRuleContext {
		public TerminalNode DoubleColon() { return getToken(PhpParser.DoubleColon, 0); }
		public TerminalNode Class() { return getToken(PhpParser.Class, 0); }
		public TerminalNode Parent_() { return getToken(PhpParser.Parent_, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Constructor() { return getToken(PhpParser.Constructor, 0); }
		public TerminalNode Get() { return getToken(PhpParser.Get, 0); }
		public TerminalNode Set() { return getToken(PhpParser.Set, 0); }
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public List<KeyedVariableContext> keyedVariable() {
			return getRuleContexts(KeyedVariableContext.class);
		}
		public KeyedVariableContext keyedVariable(int i) {
			return getRuleContext(KeyedVariableContext.class,i);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ClassConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterClassConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitClassConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitClassConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassConstantContext classConstant() throws RecognitionException {
		ClassConstantContext _localctx = new ClassConstantContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_classConstant);
		int _la;
		try {
			setState(1594);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,186,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1576);
				_la = _input.LA(1);
				if ( !(_la==Class || _la==Parent_) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1577);
				match(DoubleColon);
				setState(1582);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
				case 1:
					{
					setState(1578);
					identifier();
					}
					break;
				case 2:
					{
					setState(1579);
					match(Constructor);
					}
					break;
				case 3:
					{
					setState(1580);
					match(Get);
					}
					break;
				case 4:
					{
					setState(1581);
					match(Set);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1587);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Abstract:
				case Array:
				case As:
				case BinaryCast:
				case BoolType:
				case BooleanConstant:
				case Break:
				case Callable:
				case Case:
				case Catch:
				case Class:
				case Clone:
				case Const:
				case Continue:
				case Declare:
				case Default:
				case Do:
				case DoubleCast:
				case DoubleType:
				case Echo:
				case Else:
				case ElseIf:
				case Empty:
				case EndDeclare:
				case EndFor:
				case EndForeach:
				case EndIf:
				case EndSwitch:
				case EndWhile:
				case Eval:
				case Exit:
				case Extends:
				case Final:
				case Finally:
				case FloatCast:
				case For:
				case Foreach:
				case Function:
				case Global:
				case Goto:
				case If:
				case Implements:
				case Import:
				case Include:
				case IncludeOnce:
				case InstanceOf:
				case InsteadOf:
				case Int8Cast:
				case Int16Cast:
				case Int64Type:
				case IntType:
				case Interface:
				case IsSet:
				case List:
				case LogicalAnd:
				case LogicalOr:
				case LogicalXor:
				case Namespace:
				case New:
				case Null:
				case ObjectType:
				case Parent_:
				case Partial:
				case Print:
				case Private:
				case Protected:
				case Public:
				case Require:
				case RequireOnce:
				case Resource:
				case Return:
				case Static:
				case StringType:
				case Switch:
				case Throw:
				case Trait:
				case Try:
				case Typeof:
				case UintCast:
				case UnicodeCast:
				case Unset:
				case Use:
				case Var:
				case While:
				case Yield:
				case From:
				case Get:
				case Set:
				case Call:
				case CallStatic:
				case Constructor:
				case Destruct:
				case Wakeup:
				case Sleep:
				case Autoload:
				case IsSet__:
				case Unset__:
				case ToString__:
				case Invoke:
				case SetState:
				case Clone__:
				case DebugInfo:
				case Namespace__:
				case Class__:
				case Traic__:
				case Function__:
				case Method__:
				case Line__:
				case File__:
				case Dir__:
				case NamespaceSeparator:
				case Label:
					{
					setState(1584);
					qualifiedStaticTypeRef();
					}
					break;
				case Dollar:
				case VarName:
					{
					setState(1585);
					keyedVariable();
					}
					break;
				case SingleQuoteString:
				case DoubleQuote:
				case StartNowDoc:
				case StartHereDoc:
					{
					setState(1586);
					string();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1589);
				match(DoubleColon);
				setState(1592);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Abstract:
				case Array:
				case As:
				case BinaryCast:
				case BoolType:
				case BooleanConstant:
				case Break:
				case Callable:
				case Case:
				case Catch:
				case Class:
				case Clone:
				case Const:
				case Continue:
				case Declare:
				case Default:
				case Do:
				case DoubleCast:
				case DoubleType:
				case Echo:
				case Else:
				case ElseIf:
				case Empty:
				case EndDeclare:
				case EndFor:
				case EndForeach:
				case EndIf:
				case EndSwitch:
				case EndWhile:
				case Eval:
				case Exit:
				case Extends:
				case Final:
				case Finally:
				case FloatCast:
				case For:
				case Foreach:
				case Function:
				case Global:
				case Goto:
				case If:
				case Implements:
				case Import:
				case Include:
				case IncludeOnce:
				case InstanceOf:
				case InsteadOf:
				case Int8Cast:
				case Int16Cast:
				case Int64Type:
				case IntType:
				case Interface:
				case IsSet:
				case List:
				case LogicalAnd:
				case LogicalOr:
				case LogicalXor:
				case Namespace:
				case New:
				case Null:
				case ObjectType:
				case Parent_:
				case Partial:
				case Print:
				case Private:
				case Protected:
				case Public:
				case Require:
				case RequireOnce:
				case Resource:
				case Return:
				case Static:
				case StringType:
				case Switch:
				case Throw:
				case Trait:
				case Try:
				case Typeof:
				case UintCast:
				case UnicodeCast:
				case Unset:
				case Use:
				case Var:
				case While:
				case Yield:
				case From:
				case Get:
				case Set:
				case Call:
				case CallStatic:
				case Constructor:
				case Destruct:
				case Wakeup:
				case Sleep:
				case Autoload:
				case IsSet__:
				case Unset__:
				case ToString__:
				case Invoke:
				case SetState:
				case Clone__:
				case DebugInfo:
				case Namespace__:
				case Class__:
				case Traic__:
				case Function__:
				case Method__:
				case Line__:
				case File__:
				case Dir__:
				case Label:
					{
					setState(1590);
					identifier();
					}
					break;
				case Dollar:
				case VarName:
					{
					setState(1591);
					keyedVariable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	public static class StringConstantContext extends ParserRuleContext {
		public TerminalNode Label() { return getToken(PhpParser.Label, 0); }
		public StringConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterStringConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitStringConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitStringConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringConstantContext stringConstant() throws RecognitionException {
		StringConstantContext _localctx = new StringConstantContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_stringConstant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1596);
			match(Label);
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode StartHereDoc() { return getToken(PhpParser.StartHereDoc, 0); }
		public List<TerminalNode> HereDocText() { return getTokens(PhpParser.HereDocText); }
		public TerminalNode HereDocText(int i) {
			return getToken(PhpParser.HereDocText, i);
		}
		public TerminalNode StartNowDoc() { return getToken(PhpParser.StartNowDoc, 0); }
		public TerminalNode SingleQuoteString() { return getToken(PhpParser.SingleQuoteString, 0); }
		public List<TerminalNode> DoubleQuote() { return getTokens(PhpParser.DoubleQuote); }
		public TerminalNode DoubleQuote(int i) {
			return getToken(PhpParser.DoubleQuote, i);
		}
		public List<InterpolatedStringPartContext> interpolatedStringPart() {
			return getRuleContexts(InterpolatedStringPartContext.class);
		}
		public InterpolatedStringPartContext interpolatedStringPart(int i) {
			return getRuleContext(InterpolatedStringPartContext.class,i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_string);
		try {
			int _alt;
			setState(1619);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StartHereDoc:
				enterOuterAlt(_localctx, 1);
				{
				setState(1598);
				match(StartHereDoc);
				setState(1600); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1599);
						match(HereDocText);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1602); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,187,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case StartNowDoc:
				enterOuterAlt(_localctx, 2);
				{
				setState(1604);
				match(StartNowDoc);
				setState(1606); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(1605);
						match(HereDocText);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(1608); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,188,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case SingleQuoteString:
				enterOuterAlt(_localctx, 3);
				{
				setState(1610);
				match(SingleQuoteString);
				}
				break;
			case DoubleQuote:
				enterOuterAlt(_localctx, 4);
				{
				setState(1611);
				match(DoubleQuote);
				setState(1615);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1612);
						interpolatedStringPart();
						}
						} 
					}
					setState(1617);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,189,_ctx);
				}
				setState(1618);
				match(DoubleQuote);
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

	public static class InterpolatedStringPartContext extends ParserRuleContext {
		public TerminalNode StringPart() { return getToken(PhpParser.StringPart, 0); }
		public TerminalNode UnicodeEscape() { return getToken(PhpParser.UnicodeEscape, 0); }
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public InterpolatedStringPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolatedStringPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterInterpolatedStringPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitInterpolatedStringPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitInterpolatedStringPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InterpolatedStringPartContext interpolatedStringPart() throws RecognitionException {
		InterpolatedStringPartContext _localctx = new InterpolatedStringPartContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_interpolatedStringPart);
		try {
			setState(1624);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case StringPart:
				enterOuterAlt(_localctx, 1);
				{
				setState(1621);
				match(StringPart);
				}
				break;
			case UnicodeEscape:
				enterOuterAlt(_localctx, 2);
				{
				setState(1622);
				match(UnicodeEscape);
				}
				break;
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case NamespaceSeparator:
			case Dollar:
			case OpenRoundBracket:
			case VarName:
			case Label:
			case SingleQuoteString:
			case DoubleQuote:
			case StartNowDoc:
			case StartHereDoc:
				enterOuterAlt(_localctx, 3);
				{
				setState(1623);
				chain();
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

	public static class ChainListContext extends ParserRuleContext {
		public List<ChainContext> chain() {
			return getRuleContexts(ChainContext.class);
		}
		public ChainContext chain(int i) {
			return getRuleContext(ChainContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public ChainListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chainList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterChainList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitChainList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitChainList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChainListContext chainList() throws RecognitionException {
		ChainListContext _localctx = new ChainListContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_chainList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1626);
			chain();
			setState(1631);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1627);
				match(Comma);
				setState(1628);
				chain();
				}
				}
				setState(1633);
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

	public static class ChainContext extends ParserRuleContext {
		public ChainOriginContext chainOrigin() {
			return getRuleContext(ChainOriginContext.class,0);
		}
		public List<MemberAccessContext> memberAccess() {
			return getRuleContexts(MemberAccessContext.class);
		}
		public MemberAccessContext memberAccess(int i) {
			return getRuleContext(MemberAccessContext.class,i);
		}
		public ChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChainContext chain() throws RecognitionException {
		ChainContext _localctx = new ChainContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_chain);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1634);
			chainOrigin();
			setState(1638);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1635);
					memberAccess();
					}
					} 
				}
				setState(1640);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,193,_ctx);
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

	public static class ChainOriginContext extends ParserRuleContext {
		public ChainBaseContext chainBase() {
			return getRuleContext(ChainBaseContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public NewExprContext newExpr() {
			return getRuleContext(NewExprContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public ChainOriginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chainOrigin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterChainOrigin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitChainOrigin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitChainOrigin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChainOriginContext chainOrigin() throws RecognitionException {
		ChainOriginContext _localctx = new ChainOriginContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_chainOrigin);
		try {
			setState(1647);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,194,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1641);
				chainBase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1642);
				functionCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1643);
				match(OpenRoundBracket);
				setState(1644);
				newExpr();
				setState(1645);
				match(CloseRoundBracket);
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

	public static class MemberAccessContext extends ParserRuleContext {
		public TerminalNode ObjectOperator() { return getToken(PhpParser.ObjectOperator, 0); }
		public KeyedFieldNameContext keyedFieldName() {
			return getRuleContext(KeyedFieldNameContext.class,0);
		}
		public ActualArgumentsContext actualArguments() {
			return getRuleContext(ActualArgumentsContext.class,0);
		}
		public MemberAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMemberAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMemberAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMemberAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberAccessContext memberAccess() throws RecognitionException {
		MemberAccessContext _localctx = new MemberAccessContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_memberAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1649);
			match(ObjectOperator);
			setState(1650);
			keyedFieldName();
			setState(1652);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				{
				setState(1651);
				actualArguments();
				}
				break;
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

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionCallNameContext functionCallName() {
			return getRuleContext(FunctionCallNameContext.class,0);
		}
		public ActualArgumentsContext actualArguments() {
			return getRuleContext(ActualArgumentsContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_functionCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1654);
			functionCallName();
			setState(1655);
			actualArguments();
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

	public static class FunctionCallNameContext extends ParserRuleContext {
		public QualifiedNamespaceNameContext qualifiedNamespaceName() {
			return getRuleContext(QualifiedNamespaceNameContext.class,0);
		}
		public ClassConstantContext classConstant() {
			return getRuleContext(ClassConstantContext.class,0);
		}
		public ChainBaseContext chainBase() {
			return getRuleContext(ChainBaseContext.class,0);
		}
		public ParenthesesContext parentheses() {
			return getRuleContext(ParenthesesContext.class,0);
		}
		public FunctionCallNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCallName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterFunctionCallName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitFunctionCallName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitFunctionCallName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallNameContext functionCallName() throws RecognitionException {
		FunctionCallNameContext _localctx = new FunctionCallNameContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_functionCallName);
		try {
			setState(1661);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,196,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1657);
				qualifiedNamespaceName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1658);
				classConstant();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1659);
				chainBase();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1660);
				parentheses();
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

	public static class ActualArgumentsContext extends ParserRuleContext {
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public GenericDynamicArgsContext genericDynamicArgs() {
			return getRuleContext(GenericDynamicArgsContext.class,0);
		}
		public List<SquareCurlyExpressionContext> squareCurlyExpression() {
			return getRuleContexts(SquareCurlyExpressionContext.class);
		}
		public SquareCurlyExpressionContext squareCurlyExpression(int i) {
			return getRuleContext(SquareCurlyExpressionContext.class,i);
		}
		public ActualArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterActualArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitActualArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitActualArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualArgumentsContext actualArguments() throws RecognitionException {
		ActualArgumentsContext _localctx = new ActualArgumentsContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_actualArguments);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1664);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Lgeneric) {
				{
				setState(1663);
				genericDynamicArgs();
				}
			}

			setState(1666);
			arguments();
			setState(1670);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1667);
					squareCurlyExpression();
					}
					} 
				}
				setState(1672);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,198,_ctx);
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

	public static class ChainBaseContext extends ParserRuleContext {
		public List<KeyedVariableContext> keyedVariable() {
			return getRuleContexts(KeyedVariableContext.class);
		}
		public KeyedVariableContext keyedVariable(int i) {
			return getRuleContext(KeyedVariableContext.class,i);
		}
		public TerminalNode DoubleColon() { return getToken(PhpParser.DoubleColon, 0); }
		public QualifiedStaticTypeRefContext qualifiedStaticTypeRef() {
			return getRuleContext(QualifiedStaticTypeRefContext.class,0);
		}
		public ChainBaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chainBase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterChainBase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitChainBase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitChainBase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChainBaseContext chainBase() throws RecognitionException {
		ChainBaseContext _localctx = new ChainBaseContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_chainBase);
		try {
			setState(1682);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Dollar:
			case VarName:
				enterOuterAlt(_localctx, 1);
				{
				setState(1673);
				keyedVariable();
				setState(1676);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
				case 1:
					{
					setState(1674);
					match(DoubleColon);
					setState(1675);
					keyedVariable();
					}
					break;
				}
				}
				break;
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case NamespaceSeparator:
			case Label:
				enterOuterAlt(_localctx, 2);
				{
				setState(1678);
				qualifiedStaticTypeRef();
				setState(1679);
				match(DoubleColon);
				setState(1680);
				keyedVariable();
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

	public static class KeyedFieldNameContext extends ParserRuleContext {
		public KeyedSimpleFieldNameContext keyedSimpleFieldName() {
			return getRuleContext(KeyedSimpleFieldNameContext.class,0);
		}
		public KeyedVariableContext keyedVariable() {
			return getRuleContext(KeyedVariableContext.class,0);
		}
		public KeyedFieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyedFieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterKeyedFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitKeyedFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitKeyedFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyedFieldNameContext keyedFieldName() throws RecognitionException {
		KeyedFieldNameContext _localctx = new KeyedFieldNameContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_keyedFieldName);
		try {
			setState(1686);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case OpenCurlyBracket:
			case Label:
				enterOuterAlt(_localctx, 1);
				{
				setState(1684);
				keyedSimpleFieldName();
				}
				break;
			case Dollar:
			case VarName:
				enterOuterAlt(_localctx, 2);
				{
				setState(1685);
				keyedVariable();
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

	public static class KeyedSimpleFieldNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public List<SquareCurlyExpressionContext> squareCurlyExpression() {
			return getRuleContexts(SquareCurlyExpressionContext.class);
		}
		public SquareCurlyExpressionContext squareCurlyExpression(int i) {
			return getRuleContext(SquareCurlyExpressionContext.class,i);
		}
		public KeyedSimpleFieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyedSimpleFieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterKeyedSimpleFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitKeyedSimpleFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitKeyedSimpleFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyedSimpleFieldNameContext keyedSimpleFieldName() throws RecognitionException {
		KeyedSimpleFieldNameContext _localctx = new KeyedSimpleFieldNameContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_keyedSimpleFieldName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1693);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Abstract:
			case Array:
			case As:
			case BinaryCast:
			case BoolType:
			case BooleanConstant:
			case Break:
			case Callable:
			case Case:
			case Catch:
			case Class:
			case Clone:
			case Const:
			case Continue:
			case Declare:
			case Default:
			case Do:
			case DoubleCast:
			case DoubleType:
			case Echo:
			case Else:
			case ElseIf:
			case Empty:
			case EndDeclare:
			case EndFor:
			case EndForeach:
			case EndIf:
			case EndSwitch:
			case EndWhile:
			case Eval:
			case Exit:
			case Extends:
			case Final:
			case Finally:
			case FloatCast:
			case For:
			case Foreach:
			case Function:
			case Global:
			case Goto:
			case If:
			case Implements:
			case Import:
			case Include:
			case IncludeOnce:
			case InstanceOf:
			case InsteadOf:
			case Int8Cast:
			case Int16Cast:
			case Int64Type:
			case IntType:
			case Interface:
			case IsSet:
			case List:
			case LogicalAnd:
			case LogicalOr:
			case LogicalXor:
			case Namespace:
			case New:
			case Null:
			case ObjectType:
			case Parent_:
			case Partial:
			case Print:
			case Private:
			case Protected:
			case Public:
			case Require:
			case RequireOnce:
			case Resource:
			case Return:
			case Static:
			case StringType:
			case Switch:
			case Throw:
			case Trait:
			case Try:
			case Typeof:
			case UintCast:
			case UnicodeCast:
			case Unset:
			case Use:
			case Var:
			case While:
			case Yield:
			case From:
			case Get:
			case Set:
			case Call:
			case CallStatic:
			case Constructor:
			case Destruct:
			case Wakeup:
			case Sleep:
			case Autoload:
			case IsSet__:
			case Unset__:
			case ToString__:
			case Invoke:
			case SetState:
			case Clone__:
			case DebugInfo:
			case Namespace__:
			case Class__:
			case Traic__:
			case Function__:
			case Method__:
			case Line__:
			case File__:
			case Dir__:
			case Label:
				{
				setState(1688);
				identifier();
				}
				break;
			case OpenCurlyBracket:
				{
				setState(1689);
				match(OpenCurlyBracket);
				setState(1690);
				expression(0);
				setState(1691);
				match(CloseCurlyBracket);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1698);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,203,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1695);
					squareCurlyExpression();
					}
					} 
				}
				setState(1700);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,203,_ctx);
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

	public static class KeyedVariableContext extends ParserRuleContext {
		public TerminalNode VarName() { return getToken(PhpParser.VarName, 0); }
		public List<TerminalNode> Dollar() { return getTokens(PhpParser.Dollar); }
		public TerminalNode Dollar(int i) {
			return getToken(PhpParser.Dollar, i);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public List<SquareCurlyExpressionContext> squareCurlyExpression() {
			return getRuleContexts(SquareCurlyExpressionContext.class);
		}
		public SquareCurlyExpressionContext squareCurlyExpression(int i) {
			return getRuleContext(SquareCurlyExpressionContext.class,i);
		}
		public KeyedVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyedVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterKeyedVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitKeyedVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitKeyedVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyedVariableContext keyedVariable() throws RecognitionException {
		KeyedVariableContext _localctx = new KeyedVariableContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_keyedVariable);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1704);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,204,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1701);
					match(Dollar);
					}
					} 
				}
				setState(1706);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,204,_ctx);
			}
			setState(1713);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VarName:
				{
				setState(1707);
				match(VarName);
				}
				break;
			case Dollar:
				{
				setState(1708);
				match(Dollar);
				setState(1709);
				match(OpenCurlyBracket);
				setState(1710);
				expression(0);
				setState(1711);
				match(CloseCurlyBracket);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1718);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1715);
					squareCurlyExpression();
					}
					} 
				}
				setState(1720);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,206,_ctx);
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

	public static class SquareCurlyExpressionContext extends ParserRuleContext {
		public TerminalNode OpenSquareBracket() { return getToken(PhpParser.OpenSquareBracket, 0); }
		public TerminalNode CloseSquareBracket() { return getToken(PhpParser.CloseSquareBracket, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OpenCurlyBracket() { return getToken(PhpParser.OpenCurlyBracket, 0); }
		public TerminalNode CloseCurlyBracket() { return getToken(PhpParser.CloseCurlyBracket, 0); }
		public SquareCurlyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareCurlyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterSquareCurlyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitSquareCurlyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitSquareCurlyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SquareCurlyExpressionContext squareCurlyExpression() throws RecognitionException {
		SquareCurlyExpressionContext _localctx = new SquareCurlyExpressionContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_squareCurlyExpression);
		int _la;
		try {
			setState(1730);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OpenSquareBracket:
				enterOuterAlt(_localctx, 1);
				{
				setState(1721);
				match(OpenSquareBracket);
				setState(1723);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1722);
					expression(0);
					}
				}

				setState(1725);
				match(CloseSquareBracket);
				}
				break;
			case OpenCurlyBracket:
				enterOuterAlt(_localctx, 2);
				{
				setState(1726);
				match(OpenCurlyBracket);
				setState(1727);
				expression(0);
				setState(1728);
				match(CloseCurlyBracket);
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

	public static class AssignmentListContext extends ParserRuleContext {
		public List<AssignmentListElementContext> assignmentListElement() {
			return getRuleContexts(AssignmentListElementContext.class);
		}
		public AssignmentListElementContext assignmentListElement(int i) {
			return getRuleContext(AssignmentListElementContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(PhpParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(PhpParser.Comma, i);
		}
		public AssignmentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAssignmentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAssignmentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAssignmentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentListContext assignmentList() throws RecognitionException {
		AssignmentListContext _localctx = new AssignmentListContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_assignmentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1733);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Ampersand - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
				{
				setState(1732);
				assignmentListElement();
				}
			}

			setState(1741);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(1735);
				match(Comma);
				setState(1737);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (LambdaFn - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)) | (1L << (Inc - 107)) | (1L << (Dec - 107)))) != 0) || ((((_la - 187)) & ~0x3f) == 0 && ((1L << (_la - 187)) & ((1L << (NamespaceSeparator - 187)) | (1L << (Ampersand - 187)) | (1L << (Bang - 187)) | (1L << (Plus - 187)) | (1L << (Minus - 187)) | (1L << (Tilde - 187)) | (1L << (SuppressWarnings - 187)) | (1L << (Dollar - 187)) | (1L << (OpenRoundBracket - 187)) | (1L << (OpenSquareBracket - 187)) | (1L << (VarName - 187)) | (1L << (Label - 187)) | (1L << (Octal - 187)) | (1L << (Decimal - 187)) | (1L << (Real - 187)) | (1L << (Hex - 187)) | (1L << (Binary - 187)) | (1L << (BackQuoteString - 187)) | (1L << (SingleQuoteString - 187)) | (1L << (DoubleQuote - 187)) | (1L << (StartNowDoc - 187)) | (1L << (StartHereDoc - 187)))) != 0)) {
					{
					setState(1736);
					assignmentListElement();
					}
				}

				}
				}
				setState(1743);
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

	public static class AssignmentListElementContext extends ParserRuleContext {
		public ChainContext chain() {
			return getRuleContext(ChainContext.class,0);
		}
		public TerminalNode List() { return getToken(PhpParser.List, 0); }
		public TerminalNode OpenRoundBracket() { return getToken(PhpParser.OpenRoundBracket, 0); }
		public AssignmentListContext assignmentList() {
			return getRuleContext(AssignmentListContext.class,0);
		}
		public TerminalNode CloseRoundBracket() { return getToken(PhpParser.CloseRoundBracket, 0); }
		public ArrayItemContext arrayItem() {
			return getRuleContext(ArrayItemContext.class,0);
		}
		public AssignmentListElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentListElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterAssignmentListElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitAssignmentListElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitAssignmentListElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentListElementContext assignmentListElement() throws RecognitionException {
		AssignmentListElementContext _localctx = new AssignmentListElementContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_assignmentListElement);
		try {
			setState(1751);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,212,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1744);
				chain();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1745);
				match(List);
				setState(1746);
				match(OpenRoundBracket);
				setState(1747);
				assignmentList();
				setState(1748);
				match(CloseRoundBracket);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1750);
				arrayItem();
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

	public static class ModifierContext extends ParserRuleContext {
		public TerminalNode Abstract() { return getToken(PhpParser.Abstract, 0); }
		public TerminalNode Final() { return getToken(PhpParser.Final, 0); }
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1753);
			_la = _input.LA(1);
			if ( !(_la==Abstract || _la==Final) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode Label() { return getToken(PhpParser.Label, 0); }
		public TerminalNode Abstract() { return getToken(PhpParser.Abstract, 0); }
		public TerminalNode Array() { return getToken(PhpParser.Array, 0); }
		public TerminalNode As() { return getToken(PhpParser.As, 0); }
		public TerminalNode BinaryCast() { return getToken(PhpParser.BinaryCast, 0); }
		public TerminalNode BoolType() { return getToken(PhpParser.BoolType, 0); }
		public TerminalNode BooleanConstant() { return getToken(PhpParser.BooleanConstant, 0); }
		public TerminalNode Break() { return getToken(PhpParser.Break, 0); }
		public TerminalNode Callable() { return getToken(PhpParser.Callable, 0); }
		public TerminalNode Case() { return getToken(PhpParser.Case, 0); }
		public TerminalNode Catch() { return getToken(PhpParser.Catch, 0); }
		public TerminalNode Class() { return getToken(PhpParser.Class, 0); }
		public TerminalNode Clone() { return getToken(PhpParser.Clone, 0); }
		public TerminalNode Const() { return getToken(PhpParser.Const, 0); }
		public TerminalNode Continue() { return getToken(PhpParser.Continue, 0); }
		public TerminalNode Declare() { return getToken(PhpParser.Declare, 0); }
		public TerminalNode Default() { return getToken(PhpParser.Default, 0); }
		public TerminalNode Do() { return getToken(PhpParser.Do, 0); }
		public TerminalNode DoubleCast() { return getToken(PhpParser.DoubleCast, 0); }
		public TerminalNode DoubleType() { return getToken(PhpParser.DoubleType, 0); }
		public TerminalNode Echo() { return getToken(PhpParser.Echo, 0); }
		public TerminalNode Else() { return getToken(PhpParser.Else, 0); }
		public TerminalNode ElseIf() { return getToken(PhpParser.ElseIf, 0); }
		public TerminalNode Empty() { return getToken(PhpParser.Empty, 0); }
		public TerminalNode EndDeclare() { return getToken(PhpParser.EndDeclare, 0); }
		public TerminalNode EndFor() { return getToken(PhpParser.EndFor, 0); }
		public TerminalNode EndForeach() { return getToken(PhpParser.EndForeach, 0); }
		public TerminalNode EndIf() { return getToken(PhpParser.EndIf, 0); }
		public TerminalNode EndSwitch() { return getToken(PhpParser.EndSwitch, 0); }
		public TerminalNode EndWhile() { return getToken(PhpParser.EndWhile, 0); }
		public TerminalNode Eval() { return getToken(PhpParser.Eval, 0); }
		public TerminalNode Exit() { return getToken(PhpParser.Exit, 0); }
		public TerminalNode Extends() { return getToken(PhpParser.Extends, 0); }
		public TerminalNode Final() { return getToken(PhpParser.Final, 0); }
		public TerminalNode Finally() { return getToken(PhpParser.Finally, 0); }
		public TerminalNode FloatCast() { return getToken(PhpParser.FloatCast, 0); }
		public TerminalNode For() { return getToken(PhpParser.For, 0); }
		public TerminalNode Foreach() { return getToken(PhpParser.Foreach, 0); }
		public TerminalNode Function() { return getToken(PhpParser.Function, 0); }
		public TerminalNode Global() { return getToken(PhpParser.Global, 0); }
		public TerminalNode Goto() { return getToken(PhpParser.Goto, 0); }
		public TerminalNode If() { return getToken(PhpParser.If, 0); }
		public TerminalNode Implements() { return getToken(PhpParser.Implements, 0); }
		public TerminalNode Import() { return getToken(PhpParser.Import, 0); }
		public TerminalNode Include() { return getToken(PhpParser.Include, 0); }
		public TerminalNode IncludeOnce() { return getToken(PhpParser.IncludeOnce, 0); }
		public TerminalNode InstanceOf() { return getToken(PhpParser.InstanceOf, 0); }
		public TerminalNode InsteadOf() { return getToken(PhpParser.InsteadOf, 0); }
		public TerminalNode Int16Cast() { return getToken(PhpParser.Int16Cast, 0); }
		public TerminalNode Int64Type() { return getToken(PhpParser.Int64Type, 0); }
		public TerminalNode Int8Cast() { return getToken(PhpParser.Int8Cast, 0); }
		public TerminalNode Interface() { return getToken(PhpParser.Interface, 0); }
		public TerminalNode IntType() { return getToken(PhpParser.IntType, 0); }
		public TerminalNode IsSet() { return getToken(PhpParser.IsSet, 0); }
		public TerminalNode List() { return getToken(PhpParser.List, 0); }
		public TerminalNode LogicalAnd() { return getToken(PhpParser.LogicalAnd, 0); }
		public TerminalNode LogicalOr() { return getToken(PhpParser.LogicalOr, 0); }
		public TerminalNode LogicalXor() { return getToken(PhpParser.LogicalXor, 0); }
		public TerminalNode Namespace() { return getToken(PhpParser.Namespace, 0); }
		public TerminalNode New() { return getToken(PhpParser.New, 0); }
		public TerminalNode Null() { return getToken(PhpParser.Null, 0); }
		public TerminalNode ObjectType() { return getToken(PhpParser.ObjectType, 0); }
		public TerminalNode Parent_() { return getToken(PhpParser.Parent_, 0); }
		public TerminalNode Partial() { return getToken(PhpParser.Partial, 0); }
		public TerminalNode Print() { return getToken(PhpParser.Print, 0); }
		public TerminalNode Private() { return getToken(PhpParser.Private, 0); }
		public TerminalNode Protected() { return getToken(PhpParser.Protected, 0); }
		public TerminalNode Public() { return getToken(PhpParser.Public, 0); }
		public TerminalNode Require() { return getToken(PhpParser.Require, 0); }
		public TerminalNode RequireOnce() { return getToken(PhpParser.RequireOnce, 0); }
		public TerminalNode Resource() { return getToken(PhpParser.Resource, 0); }
		public TerminalNode Return() { return getToken(PhpParser.Return, 0); }
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public TerminalNode StringType() { return getToken(PhpParser.StringType, 0); }
		public TerminalNode Switch() { return getToken(PhpParser.Switch, 0); }
		public TerminalNode Throw() { return getToken(PhpParser.Throw, 0); }
		public TerminalNode Trait() { return getToken(PhpParser.Trait, 0); }
		public TerminalNode Try() { return getToken(PhpParser.Try, 0); }
		public TerminalNode Typeof() { return getToken(PhpParser.Typeof, 0); }
		public TerminalNode UintCast() { return getToken(PhpParser.UintCast, 0); }
		public TerminalNode UnicodeCast() { return getToken(PhpParser.UnicodeCast, 0); }
		public TerminalNode Unset() { return getToken(PhpParser.Unset, 0); }
		public TerminalNode Use() { return getToken(PhpParser.Use, 0); }
		public TerminalNode Var() { return getToken(PhpParser.Var, 0); }
		public TerminalNode While() { return getToken(PhpParser.While, 0); }
		public TerminalNode Yield() { return getToken(PhpParser.Yield, 0); }
		public TerminalNode From() { return getToken(PhpParser.From, 0); }
		public TerminalNode Get() { return getToken(PhpParser.Get, 0); }
		public TerminalNode Set() { return getToken(PhpParser.Set, 0); }
		public TerminalNode Call() { return getToken(PhpParser.Call, 0); }
		public TerminalNode CallStatic() { return getToken(PhpParser.CallStatic, 0); }
		public TerminalNode Constructor() { return getToken(PhpParser.Constructor, 0); }
		public TerminalNode Destruct() { return getToken(PhpParser.Destruct, 0); }
		public TerminalNode Wakeup() { return getToken(PhpParser.Wakeup, 0); }
		public TerminalNode Sleep() { return getToken(PhpParser.Sleep, 0); }
		public TerminalNode Autoload() { return getToken(PhpParser.Autoload, 0); }
		public TerminalNode IsSet__() { return getToken(PhpParser.IsSet__, 0); }
		public TerminalNode Unset__() { return getToken(PhpParser.Unset__, 0); }
		public TerminalNode ToString__() { return getToken(PhpParser.ToString__, 0); }
		public TerminalNode Invoke() { return getToken(PhpParser.Invoke, 0); }
		public TerminalNode SetState() { return getToken(PhpParser.SetState, 0); }
		public TerminalNode Clone__() { return getToken(PhpParser.Clone__, 0); }
		public TerminalNode DebugInfo() { return getToken(PhpParser.DebugInfo, 0); }
		public TerminalNode Namespace__() { return getToken(PhpParser.Namespace__, 0); }
		public TerminalNode Class__() { return getToken(PhpParser.Class__, 0); }
		public TerminalNode Traic__() { return getToken(PhpParser.Traic__, 0); }
		public TerminalNode Function__() { return getToken(PhpParser.Function__, 0); }
		public TerminalNode Method__() { return getToken(PhpParser.Method__, 0); }
		public TerminalNode Line__() { return getToken(PhpParser.Line__, 0); }
		public TerminalNode File__() { return getToken(PhpParser.File__, 0); }
		public TerminalNode Dir__() { return getToken(PhpParser.Dir__, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1755);
			_la = _input.LA(1);
			if ( !(((((_la - 43)) & ~0x3f) == 0 && ((1L << (_la - 43)) & ((1L << (Abstract - 43)) | (1L << (Array - 43)) | (1L << (As - 43)) | (1L << (BinaryCast - 43)) | (1L << (BoolType - 43)) | (1L << (BooleanConstant - 43)) | (1L << (Break - 43)) | (1L << (Callable - 43)) | (1L << (Case - 43)) | (1L << (Catch - 43)) | (1L << (Class - 43)) | (1L << (Clone - 43)) | (1L << (Const - 43)) | (1L << (Continue - 43)) | (1L << (Declare - 43)) | (1L << (Default - 43)) | (1L << (Do - 43)) | (1L << (DoubleCast - 43)) | (1L << (DoubleType - 43)) | (1L << (Echo - 43)) | (1L << (Else - 43)) | (1L << (ElseIf - 43)) | (1L << (Empty - 43)) | (1L << (EndDeclare - 43)) | (1L << (EndFor - 43)) | (1L << (EndForeach - 43)) | (1L << (EndIf - 43)) | (1L << (EndSwitch - 43)) | (1L << (EndWhile - 43)) | (1L << (Eval - 43)) | (1L << (Exit - 43)) | (1L << (Extends - 43)) | (1L << (Final - 43)) | (1L << (Finally - 43)) | (1L << (FloatCast - 43)) | (1L << (For - 43)) | (1L << (Foreach - 43)) | (1L << (Function - 43)) | (1L << (Global - 43)) | (1L << (Goto - 43)) | (1L << (If - 43)) | (1L << (Implements - 43)) | (1L << (Import - 43)) | (1L << (Include - 43)) | (1L << (IncludeOnce - 43)) | (1L << (InstanceOf - 43)) | (1L << (InsteadOf - 43)) | (1L << (Int8Cast - 43)) | (1L << (Int16Cast - 43)) | (1L << (Int64Type - 43)) | (1L << (IntType - 43)) | (1L << (Interface - 43)) | (1L << (IsSet - 43)) | (1L << (List - 43)) | (1L << (LogicalAnd - 43)) | (1L << (LogicalOr - 43)) | (1L << (LogicalXor - 43)) | (1L << (Namespace - 43)) | (1L << (New - 43)) | (1L << (Null - 43)) | (1L << (ObjectType - 43)) | (1L << (Parent_ - 43)) | (1L << (Partial - 43)) | (1L << (Print - 43)))) != 0) || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & ((1L << (Private - 107)) | (1L << (Protected - 107)) | (1L << (Public - 107)) | (1L << (Require - 107)) | (1L << (RequireOnce - 107)) | (1L << (Resource - 107)) | (1L << (Return - 107)) | (1L << (Static - 107)) | (1L << (StringType - 107)) | (1L << (Switch - 107)) | (1L << (Throw - 107)) | (1L << (Trait - 107)) | (1L << (Try - 107)) | (1L << (Typeof - 107)) | (1L << (UintCast - 107)) | (1L << (UnicodeCast - 107)) | (1L << (Unset - 107)) | (1L << (Use - 107)) | (1L << (Var - 107)) | (1L << (While - 107)) | (1L << (Yield - 107)) | (1L << (From - 107)) | (1L << (Get - 107)) | (1L << (Set - 107)) | (1L << (Call - 107)) | (1L << (CallStatic - 107)) | (1L << (Constructor - 107)) | (1L << (Destruct - 107)) | (1L << (Wakeup - 107)) | (1L << (Sleep - 107)) | (1L << (Autoload - 107)) | (1L << (IsSet__ - 107)) | (1L << (Unset__ - 107)) | (1L << (ToString__ - 107)) | (1L << (Invoke - 107)) | (1L << (SetState - 107)) | (1L << (Clone__ - 107)) | (1L << (DebugInfo - 107)) | (1L << (Namespace__ - 107)) | (1L << (Class__ - 107)) | (1L << (Traic__ - 107)) | (1L << (Function__ - 107)) | (1L << (Method__ - 107)) | (1L << (Line__ - 107)) | (1L << (File__ - 107)) | (1L << (Dir__ - 107)))) != 0) || _la==Label) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MemberModifierContext extends ParserRuleContext {
		public TerminalNode Public() { return getToken(PhpParser.Public, 0); }
		public TerminalNode Protected() { return getToken(PhpParser.Protected, 0); }
		public TerminalNode Private() { return getToken(PhpParser.Private, 0); }
		public TerminalNode Static() { return getToken(PhpParser.Static, 0); }
		public TerminalNode Abstract() { return getToken(PhpParser.Abstract, 0); }
		public TerminalNode Final() { return getToken(PhpParser.Final, 0); }
		public MemberModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMemberModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMemberModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMemberModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberModifierContext memberModifier() throws RecognitionException {
		MemberModifierContext _localctx = new MemberModifierContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_memberModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1757);
			_la = _input.LA(1);
			if ( !(_la==Abstract || ((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & ((1L << (Final - 75)) | (1L << (Private - 75)) | (1L << (Protected - 75)) | (1L << (Public - 75)) | (1L << (Static - 75)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MagicConstantContext extends ParserRuleContext {
		public TerminalNode Namespace__() { return getToken(PhpParser.Namespace__, 0); }
		public TerminalNode Class__() { return getToken(PhpParser.Class__, 0); }
		public TerminalNode Traic__() { return getToken(PhpParser.Traic__, 0); }
		public TerminalNode Function__() { return getToken(PhpParser.Function__, 0); }
		public TerminalNode Method__() { return getToken(PhpParser.Method__, 0); }
		public TerminalNode Line__() { return getToken(PhpParser.Line__, 0); }
		public TerminalNode File__() { return getToken(PhpParser.File__, 0); }
		public TerminalNode Dir__() { return getToken(PhpParser.Dir__, 0); }
		public MagicConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_magicConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMagicConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMagicConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMagicConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MagicConstantContext magicConstant() throws RecognitionException {
		MagicConstantContext _localctx = new MagicConstantContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_magicConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1759);
			_la = _input.LA(1);
			if ( !(((((_la - 146)) & ~0x3f) == 0 && ((1L << (_la - 146)) & ((1L << (Namespace__ - 146)) | (1L << (Class__ - 146)) | (1L << (Traic__ - 146)) | (1L << (Function__ - 146)) | (1L << (Method__ - 146)) | (1L << (Line__ - 146)) | (1L << (File__ - 146)) | (1L << (Dir__ - 146)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MagicMethodContext extends ParserRuleContext {
		public TerminalNode Get() { return getToken(PhpParser.Get, 0); }
		public TerminalNode Set() { return getToken(PhpParser.Set, 0); }
		public TerminalNode Call() { return getToken(PhpParser.Call, 0); }
		public TerminalNode CallStatic() { return getToken(PhpParser.CallStatic, 0); }
		public TerminalNode Constructor() { return getToken(PhpParser.Constructor, 0); }
		public TerminalNode Destruct() { return getToken(PhpParser.Destruct, 0); }
		public TerminalNode Wakeup() { return getToken(PhpParser.Wakeup, 0); }
		public TerminalNode Sleep() { return getToken(PhpParser.Sleep, 0); }
		public TerminalNode Autoload() { return getToken(PhpParser.Autoload, 0); }
		public TerminalNode IsSet__() { return getToken(PhpParser.IsSet__, 0); }
		public TerminalNode Unset__() { return getToken(PhpParser.Unset__, 0); }
		public TerminalNode ToString__() { return getToken(PhpParser.ToString__, 0); }
		public TerminalNode Invoke() { return getToken(PhpParser.Invoke, 0); }
		public TerminalNode SetState() { return getToken(PhpParser.SetState, 0); }
		public TerminalNode Clone__() { return getToken(PhpParser.Clone__, 0); }
		public TerminalNode DebugInfo() { return getToken(PhpParser.DebugInfo, 0); }
		public MagicMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_magicMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterMagicMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitMagicMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitMagicMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MagicMethodContext magicMethod() throws RecognitionException {
		MagicMethodContext _localctx = new MagicMethodContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_magicMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1761);
			_la = _input.LA(1);
			if ( !(((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & ((1L << (Get - 130)) | (1L << (Set - 130)) | (1L << (Call - 130)) | (1L << (CallStatic - 130)) | (1L << (Constructor - 130)) | (1L << (Destruct - 130)) | (1L << (Wakeup - 130)) | (1L << (Sleep - 130)) | (1L << (Autoload - 130)) | (1L << (IsSet__ - 130)) | (1L << (Unset__ - 130)) | (1L << (ToString__ - 130)) | (1L << (Invoke - 130)) | (1L << (SetState - 130)) | (1L << (Clone__ - 130)) | (1L << (DebugInfo - 130)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public TerminalNode BoolType() { return getToken(PhpParser.BoolType, 0); }
		public TerminalNode IntType() { return getToken(PhpParser.IntType, 0); }
		public TerminalNode Int64Type() { return getToken(PhpParser.Int64Type, 0); }
		public TerminalNode DoubleType() { return getToken(PhpParser.DoubleType, 0); }
		public TerminalNode StringType() { return getToken(PhpParser.StringType, 0); }
		public TerminalNode Resource() { return getToken(PhpParser.Resource, 0); }
		public TerminalNode ObjectType() { return getToken(PhpParser.ObjectType, 0); }
		public TerminalNode Array() { return getToken(PhpParser.Array, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1763);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Array) | (1L << BoolType) | (1L << DoubleType))) != 0) || ((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (Int64Type - 92)) | (1L << (IntType - 92)) | (1L << (ObjectType - 92)) | (1L << (Resource - 92)) | (1L << (StringType - 92)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class CastOperationContext extends ParserRuleContext {
		public TerminalNode BoolType() { return getToken(PhpParser.BoolType, 0); }
		public TerminalNode Int8Cast() { return getToken(PhpParser.Int8Cast, 0); }
		public TerminalNode Int16Cast() { return getToken(PhpParser.Int16Cast, 0); }
		public TerminalNode IntType() { return getToken(PhpParser.IntType, 0); }
		public TerminalNode Int64Type() { return getToken(PhpParser.Int64Type, 0); }
		public TerminalNode UintCast() { return getToken(PhpParser.UintCast, 0); }
		public TerminalNode DoubleCast() { return getToken(PhpParser.DoubleCast, 0); }
		public TerminalNode DoubleType() { return getToken(PhpParser.DoubleType, 0); }
		public TerminalNode FloatCast() { return getToken(PhpParser.FloatCast, 0); }
		public TerminalNode StringType() { return getToken(PhpParser.StringType, 0); }
		public TerminalNode BinaryCast() { return getToken(PhpParser.BinaryCast, 0); }
		public TerminalNode UnicodeCast() { return getToken(PhpParser.UnicodeCast, 0); }
		public TerminalNode Array() { return getToken(PhpParser.Array, 0); }
		public TerminalNode ObjectType() { return getToken(PhpParser.ObjectType, 0); }
		public TerminalNode Resource() { return getToken(PhpParser.Resource, 0); }
		public TerminalNode Unset() { return getToken(PhpParser.Unset, 0); }
		public CastOperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castOperation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).enterCastOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PhpParserListener ) ((PhpParserListener)listener).exitCastOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhpParserVisitor ) return ((PhpParserVisitor<? extends T>)visitor).visitCastOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastOperationContext castOperation() throws RecognitionException {
		CastOperationContext _localctx = new CastOperationContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_castOperation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1765);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Array) | (1L << BinaryCast) | (1L << BoolType) | (1L << DoubleCast) | (1L << DoubleType))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (FloatCast - 77)) | (1L << (Int8Cast - 77)) | (1L << (Int16Cast - 77)) | (1L << (Int64Type - 77)) | (1L << (IntType - 77)) | (1L << (ObjectType - 77)) | (1L << (Resource - 77)) | (1L << (StringType - 77)) | (1L << (UintCast - 77)) | (1L << (UnicodeCast - 77)) | (1L << (Unset - 77)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 83:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 20);
		case 1:
			return precpred(_ctx, 18);
		case 2:
			return precpred(_ctx, 17);
		case 3:
			return precpred(_ctx, 16);
		case 4:
			return precpred(_ctx, 15);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 11);
		case 9:
			return precpred(_ctx, 10);
		case 10:
			return precpred(_ctx, 9);
		case 11:
			return precpred(_ctx, 8);
		case 12:
			return precpred(_ctx, 7);
		case 13:
			return precpred(_ctx, 6);
		case 14:
			return precpred(_ctx, 3);
		case 15:
			return precpred(_ctx, 2);
		case 16:
			return precpred(_ctx, 1);
		case 17:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00ef\u06ea\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\3\2\5\2\u0112"+
		"\n\2\3\2\7\2\u0115\n\2\f\2\16\2\u0118\13\2\3\2\3\2\3\3\3\3\3\3\5\3\u011f"+
		"\n\3\3\4\6\4\u0122\n\4\r\4\16\4\u0123\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u013c\n\5"+
		"\f\5\16\5\u013f\13\5\3\5\5\5\u0142\n\5\3\6\6\6\u0145\n\6\r\6\16\6\u0146"+
		"\3\7\7\7\u014a\n\7\f\7\16\7\u014d\13\7\3\7\6\7\u0150\n\7\r\7\16\7\u0151"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u015f\n\t\3\n\3\n\5\n"+
		"\u0163\n\n\3\n\3\n\3\n\3\13\5\13\u0169\n\13\3\13\3\13\3\13\5\13\u016e"+
		"\n\13\3\13\7\13\u0171\n\13\f\13\16\13\u0174\13\13\3\f\3\f\3\r\3\r\5\r"+
		"\u017a\n\r\3\r\3\r\7\r\u017e\n\r\f\r\16\r\u0181\13\r\3\r\3\r\3\r\3\r\5"+
		"\r\u0187\n\r\3\16\3\16\3\16\3\16\3\16\5\16\u018e\n\16\3\17\3\17\3\17\5"+
		"\17\u0193\n\17\3\17\3\17\5\17\u0197\n\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u019e\n\17\3\17\5\17\u01a1\n\17\3\17\3\17\3\20\3\20\5\20\u01a7\n\20\3"+
		"\20\5\20\u01aa\n\20\3\20\5\20\u01ad\n\20\3\20\3\20\3\20\5\20\u01b2\n\20"+
		"\3\20\3\20\5\20\u01b6\n\20\3\20\3\20\5\20\u01ba\n\20\3\20\3\20\3\20\5"+
		"\20\u01bf\n\20\3\20\3\20\5\20\u01c3\n\20\5\20\u01c5\n\20\3\20\3\20\7\20"+
		"\u01c9\n\20\f\20\16\20\u01cc\13\20\3\20\3\20\3\21\3\21\3\22\3\22\3\22"+
		"\7\22\u01d5\n\22\f\22\16\22\u01d8\13\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01e8\n\23\3\24\3\24\3\24"+
		"\7\24\u01ed\n\24\f\24\16\24\u01f0\13\24\3\25\3\25\3\25\7\25\u01f5\n\25"+
		"\f\25\16\25\u01f8\13\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27\u0202"+
		"\n\27\3\30\3\30\3\30\3\30\7\30\u0208\n\30\f\30\16\30\u020b\13\30\3\30"+
		"\3\30\3\31\7\31\u0210\n\31\f\31\16\31\u0213\13\31\3\32\3\32\3\32\3\32"+
		"\5\32\u0219\n\32\3\32\3\32\3\32\7\32\u021e\n\32\f\32\16\32\u0221\13\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0237\n\33\3\34\3\34\3\34\7\34\u023c"+
		"\n\34\f\34\16\34\u023f\13\34\3\35\3\35\3\35\7\35\u0244\n\35\f\35\16\35"+
		"\u0247\13\35\3\36\3\36\3\36\3\36\3\37\7\37\u024e\n\37\f\37\16\37\u0251"+
		"\13\37\3 \3 \3 \5 \u0256\n \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0273\n!\3\"\3\"\3#\3#\3#\3"+
		"#\3$\3$\3$\3$\7$\u027f\n$\f$\16$\u0282\13$\3$\5$\u0285\n$\3$\3$\3$\3$"+
		"\3$\7$\u028c\n$\f$\16$\u028f\13$\3$\5$\u0292\n$\3$\3$\3$\5$\u0297\n$\3"+
		"%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3"+
		")\3)\5)\u02b1\n)\3*\3*\3*\3*\3*\3*\3+\3+\3+\5+\u02bc\n+\3+\3+\5+\u02c0"+
		"\n+\3+\3+\5+\u02c4\n+\3+\3+\3+\3+\3+\3+\3+\5+\u02cd\n+\3,\3,\3-\3-\3."+
		"\3.\3.\3.\5.\u02d7\n.\3.\7.\u02da\n.\f.\16.\u02dd\13.\3.\3.\3.\5.\u02e2"+
		"\n.\3.\7.\u02e5\n.\f.\16.\u02e8\13.\3.\3.\5.\u02ec\n.\3/\3/\3/\5/\u02f1"+
		"\n/\3/\6/\u02f4\n/\r/\16/\u02f5\3/\3/\3\60\3\60\5\60\u02fc\n\60\3\60\3"+
		"\60\3\61\3\61\5\61\u0302\n\61\3\61\3\61\3\62\3\62\5\62\u0308\n\62\3\62"+
		"\3\62\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65"+
		"\3\65\5\65\u031a\n\65\3\65\3\65\3\65\5\65\u031f\n\65\3\65\5\65\u0322\n"+
		"\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u032c\n\65\3\65\5\65"+
		"\u032f\n\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65"+
		"\u033c\n\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u0344\n\65\3\66\3\66\3"+
		"\66\6\66\u0349\n\66\r\66\16\66\u034a\3\66\5\66\u034e\n\66\3\66\7\66\u0351"+
		"\n\66\f\66\16\66\u0354\13\66\3\66\5\66\u0357\n\66\3\67\3\67\3\67\3\67"+
		"\3\67\7\67\u035e\n\67\f\67\16\67\u0361\13\67\3\67\3\67\3\67\3\67\38\3"+
		"8\38\39\39\39\39\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3;\3;\3;\5;\u037c\n"+
		";\3<\6<\u037f\n<\r<\16<\u0380\3=\3=\5=\u0385\n=\3>\3>\3>\7>\u038a\n>\f"+
		">\16>\u038d\13>\3?\5?\u0390\n?\3?\3?\7?\u0394\n?\f?\16?\u0397\13?\3@\3"+
		"@\5@\u039b\n@\3@\5@\u039e\n@\3@\5@\u03a1\n@\3@\5@\u03a4\n@\3@\3@\3A\3"+
		"A\3A\5A\u03ab\nA\3B\3B\3B\3B\7B\u03b1\nB\fB\16B\u03b4\13B\3B\3B\3C\3C"+
		"\3C\3C\3C\3C\3C\3C\5C\u03c0\nC\3D\3D\3D\3D\3E\3E\3E\3E\7E\u03ca\nE\fE"+
		"\16E\u03cd\13E\3E\3E\3F\3F\3F\5F\u03d4\nF\3F\3F\3F\7F\u03d9\nF\fF\16F"+
		"\u03dc\13F\3F\3F\3F\3F\5F\u03e2\nF\3F\3F\5F\u03e6\nF\3F\3F\3F\7F\u03eb"+
		"\nF\fF\16F\u03ee\13F\3F\3F\3F\3F\5F\u03f4\nF\3F\3F\5F\u03f8\nF\3F\3F\5"+
		"F\u03fc\nF\3F\3F\3F\3F\5F\u0402\nF\3F\3F\3F\3F\3F\3F\5F\u040a\nF\3G\3"+
		"G\3G\7G\u040f\nG\fG\16G\u0412\13G\3G\5G\u0415\nG\3H\3H\5H\u0419\nH\3I"+
		"\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\5J\u0426\nJ\3J\5J\u0429\nJ\3J\3J\3K\3K"+
		"\3K\5K\u0430\nK\3K\3K\3L\3L\3L\5L\u0437\nL\3M\3M\5M\u043b\nM\3N\3N\5N"+
		"\u043f\nN\3O\6O\u0442\nO\rO\16O\u0443\3P\3P\3P\5P\u0449\nP\3Q\3Q\3Q\3"+
		"Q\3R\3R\3R\3R\3R\7R\u0454\nR\fR\16R\u0457\13R\3R\3R\3S\3S\3S\7S\u045e"+
		"\nS\fS\16S\u0461\13S\3T\3T\3T\5T\u0466\nT\3T\3T\3U\3U\3U\3U\3U\3U\3U\3"+
		"U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"U\3U\3U\3U\3U\3U\5U\u04a5\nU\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"U\5U\u04b5\nU\5U\u04b7\nU\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\5U\u04dd"+
		"\nU\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\7U\u04f3"+
		"\nU\fU\16U\u04f6\13U\3V\3V\5V\u04fa\nV\3W\3W\3W\5W\u04ff\nW\3W\3W\3W\5"+
		"W\u0504\nW\3W\5W\u0507\nW\3W\3W\3W\3W\5W\u050d\nW\3X\5X\u0510\nX\3X\3"+
		"X\5X\u0514\nX\3X\3X\3X\3X\5X\u051a\nX\3X\3X\5X\u051e\nX\3X\3X\3X\3X\3"+
		"X\3X\3X\3X\3X\5X\u0529\nX\3Y\3Y\3Y\5Y\u052e\nY\3Z\3Z\3[\3[\3[\3[\5[\u0536"+
		"\n[\3[\3[\5[\u053a\n[\3\\\3\\\3\\\7\\\u053f\n\\\f\\\16\\\u0542\13\\\3"+
		"\\\5\\\u0545\n\\\3]\3]\3]\5]\u054a\n]\3]\3]\3]\5]\u054f\n]\3]\3]\5]\u0553"+
		"\n]\3^\3^\3^\3^\3^\7^\u055a\n^\f^\16^\u055d\13^\3^\3^\3_\5_\u0562\n_\3"+
		"_\3_\3`\3`\5`\u0568\n`\3`\5`\u056b\n`\3a\3a\5a\u056f\na\3a\5a\u0572\n"+
		"a\3a\3a\3a\5a\u0577\na\3b\3b\5b\u057b\nb\3b\5b\u057e\nb\3b\5b\u0581\n"+
		"b\3b\3b\5b\u0585\nb\3b\3b\5b\u0589\nb\3b\3b\5b\u058d\nb\3b\3b\3b\5b\u0592"+
		"\nb\3b\3b\5b\u0596\nb\5b\u0598\nb\3b\3b\7b\u059c\nb\fb\16b\u059f\13b\3"+
		"b\3b\3c\3c\3c\7c\u05a6\nc\fc\16c\u05a9\13c\3d\5d\u05ac\nd\3d\5d\u05af"+
		"\nd\3d\3d\3e\3e\3e\3e\7e\u05b7\ne\fe\16e\u05ba\13e\3e\3e\5e\u05be\ne\5"+
		"e\u05c0\ne\3f\3f\3f\5f\u05c5\nf\3f\3f\3f\3f\7f\u05cb\nf\ff\16f\u05ce\13"+
		"f\3f\5f\u05d1\nf\3f\3f\5f\u05d5\nf\3g\3g\3g\7g\u05da\ng\fg\16g\u05dd\13"+
		"g\3h\3h\3h\3h\7h\u05e3\nh\fh\16h\u05e6\13h\3h\5h\u05e9\nh\3h\5h\u05ec"+
		"\nh\3h\3h\3i\5i\u05f1\ni\3i\3i\3i\5i\u05f6\ni\3j\3j\3j\3j\3j\3j\5j\u05fe"+
		"\nj\5j\u0600\nj\3j\3j\3j\3j\5j\u0606\nj\5j\u0608\nj\3j\3j\3j\5j\u060d"+
		"\nj\3k\3k\3k\7k\u0612\nk\fk\16k\u0615\13k\3l\3l\3l\5l\u061a\nl\3m\3m\3"+
		"m\3m\3m\5m\u0621\nm\3n\3n\3n\3n\5n\u0627\nn\3o\3o\3p\3p\3p\3p\3p\3p\5"+
		"p\u0631\np\3p\3p\3p\5p\u0636\np\3p\3p\3p\5p\u063b\np\5p\u063d\np\3q\3"+
		"q\3r\3r\6r\u0643\nr\rr\16r\u0644\3r\3r\6r\u0649\nr\rr\16r\u064a\3r\3r"+
		"\3r\7r\u0650\nr\fr\16r\u0653\13r\3r\5r\u0656\nr\3s\3s\3s\5s\u065b\ns\3"+
		"t\3t\3t\7t\u0660\nt\ft\16t\u0663\13t\3u\3u\7u\u0667\nu\fu\16u\u066a\13"+
		"u\3v\3v\3v\3v\3v\3v\5v\u0672\nv\3w\3w\3w\5w\u0677\nw\3x\3x\3x\3y\3y\3"+
		"y\3y\5y\u0680\ny\3z\5z\u0683\nz\3z\3z\7z\u0687\nz\fz\16z\u068a\13z\3{"+
		"\3{\3{\5{\u068f\n{\3{\3{\3{\3{\5{\u0695\n{\3|\3|\5|\u0699\n|\3}\3}\3}"+
		"\3}\3}\5}\u06a0\n}\3}\7}\u06a3\n}\f}\16}\u06a6\13}\3~\7~\u06a9\n~\f~\16"+
		"~\u06ac\13~\3~\3~\3~\3~\3~\3~\5~\u06b4\n~\3~\7~\u06b7\n~\f~\16~\u06ba"+
		"\13~\3\177\3\177\5\177\u06be\n\177\3\177\3\177\3\177\3\177\3\177\5\177"+
		"\u06c5\n\177\3\u0080\5\u0080\u06c8\n\u0080\3\u0080\3\u0080\5\u0080\u06cc"+
		"\n\u0080\7\u0080\u06ce\n\u0080\f\u0080\16\u0080\u06d1\13\u0080\3\u0081"+
		"\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\5\u0081\u06da\n\u0081"+
		"\3\u0082\3\u0082\3\u0083\3\u0083\3\u0084\3\u0084\3\u0085\3\u0085\3\u0086"+
		"\3\u0086\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\2\3\u00a8\u0089\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT"+
		"VXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be"+
		"\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6"+
		"\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec\u00ee"+
		"\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104\u0106"+
		"\u0108\u010a\u010c\u010e\2\32\4\299RR\4\2\67\67xx\3\2\u00d6\u00d7\3\2"+
		"\u00ca\u00cb\4\2\u00c3\u00c3\u00c5\u00c6\3\2\u00a0\u00a1\3\2XY\3\2pq\3"+
		"\2\u00c7\u00c9\4\2\u00c5\u00c6\u00cd\u00cd\3\2\u00b9\u00ba\4\2\u00a6\u00a7"+
		"\u00bf\u00c0\3\2\u00a2\u00a5\6\2\u00a8\u00aa\u00ac\u00b4\u00b8\u00b8\u00d8"+
		"\u00d8\3\2\u00c5\u00c6\4\2\u00dd\u00de\u00e0\u00e1\4\2\67\67jj\4\2--M"+
		"M\5\2-\u0082\u0084\u009b\u00dc\u00dc\6\2--MMmott\3\2\u0094\u009b\3\2\u0084"+
		"\u0093\t\2..\61\61??^_iirruu\13\2..\60\61>?OO\\_iirruu{}\2\u07af\2\u0111"+
		"\3\2\2\2\4\u011e\3\2\2\2\6\u0121\3\2\2\2\b\u0141\3\2\2\2\n\u0144\3\2\2"+
		"\2\f\u014b\3\2\2\2\16\u0153\3\2\2\2\20\u015e\3\2\2\2\22\u0160\3\2\2\2"+
		"\24\u0168\3\2\2\2\26\u0175\3\2\2\2\30\u0177\3\2\2\2\32\u018d\3\2\2\2\34"+
		"\u018f\3\2\2\2\36\u01a4\3\2\2\2 \u01cf\3\2\2\2\"\u01d1\3\2\2\2$\u01e7"+
		"\3\2\2\2&\u01e9\3\2\2\2(\u01f1\3\2\2\2*\u01f9\3\2\2\2,\u01fc\3\2\2\2."+
		"\u0203\3\2\2\2\60\u0211\3\2\2\2\62\u0214\3\2\2\2\64\u0236\3\2\2\2\66\u0238"+
		"\3\2\2\28\u0240\3\2\2\2:\u0248\3\2\2\2<\u024f\3\2\2\2>\u0255\3\2\2\2@"+
		"\u0272\3\2\2\2B\u0274\3\2\2\2D\u0276\3\2\2\2F\u0296\3\2\2\2H\u0298\3\2"+
		"\2\2J\u029c\3\2\2\2L\u02a1\3\2\2\2N\u02a4\3\2\2\2P\u02a8\3\2\2\2R\u02b2"+
		"\3\2\2\2T\u02b8\3\2\2\2V\u02ce\3\2\2\2X\u02d0\3\2\2\2Z\u02d2\3\2\2\2\\"+
		"\u02f3\3\2\2\2^\u02f9\3\2\2\2`\u02ff\3\2\2\2b\u0305\3\2\2\2d\u030b\3\2"+
		"\2\2f\u030e\3\2\2\2h\u0314\3\2\2\2j\u0345\3\2\2\2l\u0358\3\2\2\2n\u0366"+
		"\3\2\2\2p\u0369\3\2\2\2r\u036d\3\2\2\2t\u0371\3\2\2\2v\u037e\3\2\2\2x"+
		"\u0384\3\2\2\2z\u0386\3\2\2\2|\u038f\3\2\2\2~\u0398\3\2\2\2\u0080\u03aa"+
		"\3\2\2\2\u0082\u03ac\3\2\2\2\u0084\u03bf\3\2\2\2\u0086\u03c1\3\2\2\2\u0088"+
		"\u03c5\3\2\2\2\u008a\u0409\3\2\2\2\u008c\u0414\3\2\2\2\u008e\u0418\3\2"+
		"\2\2\u0090\u041a\3\2\2\2\u0092\u0421\3\2\2\2\u0094\u042f\3\2\2\2\u0096"+
		"\u0433\3\2\2\2\u0098\u043a\3\2\2\2\u009a\u043e\3\2\2\2\u009c\u0441\3\2"+
		"\2\2\u009e\u0445\3\2\2\2\u00a0\u044a\3\2\2\2\u00a2\u044e\3\2\2\2\u00a4"+
		"\u045a\3\2\2\2\u00a6\u0462\3\2\2\2\u00a8\u04b6\3\2\2\2\u00aa\u04f9\3\2"+
		"\2\2\u00ac\u0506\3\2\2\2\u00ae\u0528\3\2\2\2\u00b0\u052a\3\2\2\2\u00b2"+
		"\u052f\3\2\2\2\u00b4\u0531\3\2\2\2\u00b6\u053b\3\2\2\2\u00b8\u0552\3\2"+
		"\2\2\u00ba\u0554\3\2\2\2\u00bc\u0561\3\2\2\2\u00be\u056a\3\2\2\2\u00c0"+
		"\u0576\3\2\2\2\u00c2\u0578\3\2\2\2\u00c4\u05a2\3\2\2\2\u00c6\u05ab\3\2"+
		"\2\2\u00c8\u05bf\3\2\2\2\u00ca\u05d4\3\2\2\2\u00cc\u05d6\3\2\2\2\u00ce"+
		"\u05de\3\2\2\2\u00d0\u05f5\3\2\2\2\u00d2\u060c\3\2\2\2\u00d4\u060e\3\2"+
		"\2\2\u00d6\u0616\3\2\2\2\u00d8\u0620\3\2\2\2\u00da\u0626\3\2\2\2\u00dc"+
		"\u0628\3\2\2\2\u00de\u063c\3\2\2\2\u00e0\u063e\3\2\2\2\u00e2\u0655\3\2"+
		"\2\2\u00e4\u065a\3\2\2\2\u00e6\u065c\3\2\2\2\u00e8\u0664\3\2\2\2\u00ea"+
		"\u0671\3\2\2\2\u00ec\u0673\3\2\2\2\u00ee\u0678\3\2\2\2\u00f0\u067f\3\2"+
		"\2\2\u00f2\u0682\3\2\2\2\u00f4\u0694\3\2\2\2\u00f6\u0698\3\2\2\2\u00f8"+
		"\u069f\3\2\2\2\u00fa\u06aa\3\2\2\2\u00fc\u06c4\3\2\2\2\u00fe\u06c7\3\2"+
		"\2\2\u0100\u06d9\3\2\2\2\u0102\u06db\3\2\2\2\u0104\u06dd\3\2\2\2\u0106"+
		"\u06df\3\2\2\2\u0108\u06e1\3\2\2\2\u010a\u06e3\3\2\2\2\u010c\u06e5\3\2"+
		"\2\2\u010e\u06e7\3\2\2\2\u0110\u0112\7\f\2\2\u0111\u0110\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\u0116\3\2\2\2\u0113\u0115\5\4\3\2\u0114\u0113\3\2"+
		"\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117"+
		"\u0119\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u011a\7\2\2\3\u011a\3\3\2\2\2"+
		"\u011b\u011f\5\6\4\2\u011c\u011f\5\f\7\2\u011d\u011f\5\n\6\2\u011e\u011b"+
		"\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011d\3\2\2\2\u011f\5\3\2\2\2\u0120"+
		"\u0122\5\b\5\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0123\u0124\3\2\2\2\u0124\7\3\2\2\2\u0125\u0142\7\n\2\2\u0126\u0142"+
		"\7\7\2\2\u0127\u0142\7\21\2\2\u0128\u0142\7\b\2\2\u0129\u0142\7\13\2\2"+
		"\u012a\u0142\7\32\2\2\u012b\u0142\7\22\2\2\u012c\u0142\7\23\2\2\u012d"+
		"\u0142\7\4\2\2\u012e\u0142\7\24\2\2\u012f\u0142\7\25\2\2\u0130\u0142\7"+
		"\35\2\2\u0131\u0142\7\26\2\2\u0132\u0142\7!\2\2\u0133\u0142\7\27\2\2\u0134"+
		"\u0142\7\30\2\2\u0135\u0142\7\36\2\2\u0136\u0142\7\"\2\2\u0137\u0142\7"+
		"\'\2\2\u0138\u0142\7%\2\2\u0139\u013d\7\5\2\2\u013a\u013c\7\16\2\2\u013b"+
		"\u013a\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0142\7\17\2\2\u0141"+
		"\u0125\3\2\2\2\u0141\u0126\3\2\2\2\u0141\u0127\3\2\2\2\u0141\u0128\3\2"+
		"\2\2\u0141\u0129\3\2\2\2\u0141\u012a\3\2\2\2\u0141\u012b\3\2\2\2\u0141"+
		"\u012c\3\2\2\2\u0141\u012d\3\2\2\2\u0141\u012e\3\2\2\2\u0141\u012f\3\2"+
		"\2\2\u0141\u0130\3\2\2\2\u0141\u0131\3\2\2\2\u0141\u0132\3\2\2\2\u0141"+
		"\u0133\3\2\2\2\u0141\u0134\3\2\2\2\u0141\u0135\3\2\2\2\u0141\u0136\3\2"+
		"\2\2\u0141\u0137\3\2\2\2\u0141\u0138\3\2\2\2\u0141\u0139\3\2\2\2\u0142"+
		"\t\3\2\2\2\u0143\u0145\7$\2\2\u0144\u0143\3\2\2\2\u0145\u0146\3\2\2\2"+
		"\u0146\u0144\3\2\2\2\u0146\u0147\3\2\2\2\u0147\13\3\2\2\2\u0148\u014a"+
		"\5\16\b\2\u0149\u0148\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2"+
		"\u014b\u014c\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u0150"+
		"\5\20\t\2\u014f\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2\2\2"+
		"\u0151\u0152\3\2\2\2\u0152\r\3\2\2\2\u0153\u0154\7W\2\2\u0154\u0155\7"+
		"f\2\2\u0155\u0156\5\u00c8e\2\u0156\u0157\7\u00d7\2\2\u0157\17\3\2\2\2"+
		"\u0158\u015f\5@!\2\u0159\u015f\5\22\n\2\u015a\u015f\5\30\r\2\u015b\u015f"+
		"\5\34\17\2\u015c\u015f\5\36\20\2\u015d\u015f\5\u00a2R\2\u015e\u0158\3"+
		"\2\2\2\u015e\u0159\3\2\2\2\u015e\u015a\3\2\2\2\u015e\u015b\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015e\u015d\3\2\2\2\u015f\21\3\2\2\2\u0160\u0162\7~\2\2"+
		"\u0161\u0163\t\2\2\2\u0162\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0165\5\24\13\2\u0165\u0166\7\u00d7\2\2\u0166\23\3\2\2"+
		"\2\u0167\u0169\7\u00bd\2\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016a\3\2\2\2\u016a\u0172\5\26\f\2\u016b\u016d\7\u00d5\2\2\u016c\u016e"+
		"\7\u00bd\2\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2\2"+
		"\2\u016f\u0171\5\26\f\2\u0170\u016b\3\2\2\2\u0171\u0174\3\2\2\2\u0172"+
		"\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\25\3\2\2\2\u0174\u0172\3\2\2"+
		"\2\u0175\u0176\5\u00c8e\2\u0176\27\3\2\2\2\u0177\u0186\7f\2\2\u0178\u017a"+
		"\5\u00c8e\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\3\2\2"+
		"\2\u017b\u017f\7\u00d3\2\2\u017c\u017e\5\32\16\2\u017d\u017c\3\2\2\2\u017e"+
		"\u0181\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2"+
		"\2\2\u0181\u017f\3\2\2\2\u0182\u0187\7\u00d4\2\2\u0183\u0184\5\u00c8e"+
		"\2\u0184\u0185\7\u00d7\2\2\u0185\u0187\3\2\2\2\u0186\u0179\3\2\2\2\u0186"+
		"\u0183\3\2\2\2\u0187\31\3\2\2\2\u0188\u018e\5@!\2\u0189\u018e\5\22\n\2"+
		"\u018a\u018e\5\34\17\2\u018b\u018e\5\36\20\2\u018c\u018e\5\u00a2R\2\u018d"+
		"\u0188\3\2\2\2\u018d\u0189\3\2\2\2\u018d\u018a\3\2\2\2\u018d\u018b\3\2"+
		"\2\2\u018d\u018c\3\2\2\2\u018e\33\3\2\2\2\u018f\u0190\5\60\31\2\u0190"+
		"\u0192\7R\2\2\u0191\u0193\7\u00c1\2\2\u0192\u0191\3\2\2\2\u0192\u0193"+
		"\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\5\u0104\u0083\2\u0195\u0197\5"+
		"$\23\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u0198\3\2\2\2\u0198"+
		"\u0199\7\u00cf\2\2\u0199\u019a\5|?\2\u019a\u01a0\7\u00d0\2\2\u019b\u019d"+
		"\7\u00d6\2\2\u019c\u019e\7\u00ce\2\2\u019d\u019c\3\2\2\2\u019d\u019e\3"+
		"\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a1\5\u0080A\2\u01a0\u019b\3\2\2\2"+
		"\u01a0\u01a1\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\5D#\2\u01a3\35\3"+
		"\2\2\2\u01a4\u01a6\5\60\31\2\u01a5\u01a7\7m\2\2\u01a6\u01a5\3\2\2\2\u01a6"+
		"\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01aa\5\u0102\u0082\2\u01a9\u01a8"+
		"\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01ad\7k\2\2\u01ac"+
		"\u01ab\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01c4\3\2\2\2\u01ae\u01af\5 "+
		"\21\2\u01af\u01b1\5\u0104\u0083\2\u01b0\u01b2\5$\23\2\u01b1\u01b0\3\2"+
		"\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b4\7L\2\2\u01b4"+
		"\u01b6\5\u00be`\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b9"+
		"\3\2\2\2\u01b7\u01b8\7V\2\2\u01b8\u01ba\5\"\22\2\u01b9\u01b7\3\2\2\2\u01b9"+
		"\u01ba\3\2\2\2\u01ba\u01c5\3\2\2\2\u01bb\u01bc\7`\2\2\u01bc\u01be\5\u0104"+
		"\u0083\2\u01bd\u01bf\5$\23\2\u01be\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf"+
		"\u01c2\3\2\2\2\u01c0\u01c1\7L\2\2\u01c1\u01c3\5\"\22\2\u01c2\u01c0\3\2"+
		"\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c5\3\2\2\2\u01c4\u01ae\3\2\2\2\u01c4"+
		"\u01bb\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01ca\7\u00d3\2\2\u01c7\u01c9"+
		"\5\u008aF\2\u01c8\u01c7\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01c8\3\2\2"+
		"\2\u01ca\u01cb\3\2\2\2\u01cb\u01cd\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01ce"+
		"\7\u00d4\2\2\u01ce\37\3\2\2\2\u01cf\u01d0\t\3\2\2\u01d0!\3\2\2\2\u01d1"+
		"\u01d6\5\u00be`\2\u01d2\u01d3\7\u00d5\2\2\u01d3\u01d5\5\u00be`\2\u01d4"+
		"\u01d2\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6\u01d7\3\2"+
		"\2\2\u01d7#\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9\u01da\7\u009d\2\2\u01da"+
		"\u01db\5&\24\2\u01db\u01dc\7\u009e\2\2\u01dc\u01e8\3\2\2\2\u01dd\u01de"+
		"\7\u009d\2\2\u01de\u01df\5(\25\2\u01df\u01e0\7\u009e\2\2\u01e0\u01e8\3"+
		"\2\2\2\u01e1\u01e2\7\u009d\2\2\u01e2\u01e3\5&\24\2\u01e3\u01e4\7\u00d5"+
		"\2\2\u01e4\u01e5\5(\25\2\u01e5\u01e6\7\u009e\2\2\u01e6\u01e8\3\2\2\2\u01e7"+
		"\u01d9\3\2\2\2\u01e7\u01dd\3\2\2\2\u01e7\u01e1\3\2\2\2\u01e8%\3\2\2\2"+
		"\u01e9\u01ee\5*\26\2\u01ea\u01eb\7\u00d5\2\2\u01eb\u01ed\5*\26\2\u01ec"+
		"\u01ea\3\2\2\2\u01ed\u01f0\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ee\u01ef\3\2"+
		"\2\2\u01ef\'\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f1\u01f6\5,\27\2\u01f2\u01f3"+
		"\7\u00d5\2\2\u01f3\u01f5\5,\27\2\u01f4\u01f2\3\2\2\2\u01f5\u01f8\3\2\2"+
		"\2\u01f6\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7)\3\2\2\2\u01f8\u01f6"+
		"\3\2\2\2\u01f9\u01fa\5\60\31\2\u01fa\u01fb\5\u0104\u0083\2\u01fb+\3\2"+
		"\2\2\u01fc\u01fd\5\60\31\2\u01fd\u01fe\5\u0104\u0083\2\u01fe\u0201\7\u00d8"+
		"\2\2\u01ff\u0202\5\u00be`\2\u0200\u0202\5\u010c\u0087\2\u0201\u01ff\3"+
		"\2\2\2\u0201\u0200\3\2\2\2\u0202-\3\2\2\2\u0203\u0204\7\u009d\2\2\u0204"+
		"\u0209\5\u00c0a\2\u0205\u0206\7\u00d5\2\2\u0206\u0208\5\u00c0a\2\u0207"+
		"\u0205\3\2\2\2\u0208\u020b\3\2\2\2\u0209\u0207\3\2\2\2\u0209\u020a\3\2"+
		"\2\2\u020a\u020c\3\2\2\2\u020b\u0209\3\2\2\2\u020c\u020d\7\u009e\2\2\u020d"+
		"/\3\2\2\2\u020e\u0210\5\62\32\2\u020f\u020e\3\2\2\2\u0210\u0213\3\2\2"+
		"\2\u0211\u020f\3\2\2\2\u0211\u0212\3\2\2\2\u0212\61\3\2\2\2\u0213\u0211"+
		"\3\2\2\2\u0214\u0218\7\u00d1\2\2\u0215\u0216\5\u0104\u0083\2\u0216\u0217"+
		"\7\u00d6\2\2\u0217\u0219\3\2\2\2\u0218\u0215\3\2\2\2\u0218\u0219\3\2\2"+
		"\2\u0219\u021a\3\2\2\2\u021a\u021f\5\64\33\2\u021b\u021c\7\u00d5\2\2\u021c"+
		"\u021e\5\64\33\2\u021d\u021b\3\2\2\2\u021e\u0221\3\2\2\2\u021f\u021d\3"+
		"\2\2\2\u021f\u0220\3\2\2\2\u0220\u0222\3\2\2\2\u0221\u021f\3\2\2\2\u0222"+
		"\u0223\7\u00d2\2\2\u0223\63\3\2\2\2\u0224\u0237\5\u00c6d\2\u0225\u0226"+
		"\5\u00c6d\2\u0226\u0227\7\u00cf\2\2\u0227\u0228\5\66\34\2\u0228\u0229"+
		"\7\u00d0\2\2\u0229\u0237\3\2\2\2\u022a\u022b\5\u00c6d\2\u022b\u022c\7"+
		"\u00cf\2\2\u022c\u022d\58\35\2\u022d\u022e\7\u00d0\2\2\u022e\u0237\3\2"+
		"\2\2\u022f\u0230\5\u00c6d\2\u0230\u0231\7\u00cf\2\2\u0231\u0232\5\66\34"+
		"\2\u0232\u0233\7\u00d5\2\2\u0233\u0234\58\35\2\u0234\u0235\7\u00d0\2\2"+
		"\u0235\u0237\3\2\2\2\u0236\u0224\3\2\2\2\u0236\u0225\3\2\2\2\u0236\u022a"+
		"\3\2\2\2\u0236\u022f\3\2\2\2\u0237\65\3\2\2\2\u0238\u023d\5\u00a8U\2\u0239"+
		"\u023a\7\u00d5\2\2\u023a\u023c\5\u00a8U\2\u023b\u0239\3\2\2\2\u023c\u023f"+
		"\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\67\3\2\2\2\u023f"+
		"\u023d\3\2\2\2\u0240\u0245\5:\36\2\u0241\u0242\7\u00d5\2\2\u0242\u0244"+
		"\5:\36\2\u0243\u0241\3\2\2\2\u0244\u0247\3\2\2\2\u0245\u0243\3\2\2\2\u0245"+
		"\u0246\3\2\2\2\u02469\3\2\2\2\u0247\u0245\3\2\2\2\u0248\u0249\7\u00db"+
		"\2\2\u0249\u024a\7\u009f\2\2\u024a\u024b\5\u00a8U\2\u024b;\3\2\2\2\u024c"+
		"\u024e\5> \2\u024d\u024c\3\2\2\2\u024e\u0251\3\2\2\2\u024f\u024d\3\2\2"+
		"\2\u024f\u0250\3\2\2\2\u0250=\3\2\2\2\u0251\u024f\3\2\2\2\u0252\u0256"+
		"\5@!\2\u0253\u0256\5\34\17\2\u0254\u0256\5\36\20\2\u0255\u0252\3\2\2\2"+
		"\u0255\u0253\3\2\2\2\u0255\u0254\3\2\2\2\u0256?\3\2\2\2\u0257\u0258\5"+
		"\u0104\u0083\2\u0258\u0259\7\u00d6\2\2\u0259\u0273\3\2\2\2\u025a\u0273"+
		"\5D#\2\u025b\u0273\5F$\2\u025c\u0273\5P)\2\u025d\u0273\5R*\2\u025e\u0273"+
		"\5T+\2\u025f\u0273\5Z.\2\u0260\u0273\5^\60\2\u0261\u0273\5`\61\2\u0262"+
		"\u0273\5b\62\2\u0263\u0264\5\u00b4[\2\u0264\u0265\7\u00d7\2\2\u0265\u0273"+
		"\3\2\2\2\u0266\u0273\5\u0082B\2\u0267\u0273\5\u0088E\2\u0268\u0273\5\u0086"+
		"D\2\u0269\u0273\5d\63\2\u026a\u0273\5f\64\2\u026b\u0273\5h\65\2\u026c"+
		"\u0273\5j\66\2\u026d\u0273\5p9\2\u026e\u0273\5r:\2\u026f\u0273\5t;\2\u0270"+
		"\u0273\5B\"\2\u0271\u0273\5v<\2\u0272\u0257\3\2\2\2\u0272\u025a\3\2\2"+
		"\2\u0272\u025b\3\2\2\2\u0272\u025c\3\2\2\2\u0272\u025d\3\2\2\2\u0272\u025e"+
		"\3\2\2\2\u0272\u025f\3\2\2\2\u0272\u0260\3\2\2\2\u0272\u0261\3\2\2\2\u0272"+
		"\u0262\3\2\2\2\u0272\u0263\3\2\2\2\u0272\u0266\3\2\2\2\u0272\u0267\3\2"+
		"\2\2\u0272\u0268\3\2\2\2\u0272\u0269\3\2\2\2\u0272\u026a\3\2\2\2\u0272"+
		"\u026b\3\2\2\2\u0272\u026c\3\2\2\2\u0272\u026d\3\2\2\2\u0272\u026e\3\2"+
		"\2\2\u0272\u026f\3\2\2\2\u0272\u0270\3\2\2\2\u0272\u0271\3\2\2\2\u0273"+
		"A\3\2\2\2\u0274\u0275\7\u00d7\2\2\u0275C\3\2\2\2\u0276\u0277\7\u00d3\2"+
		"\2\u0277\u0278\5<\37\2\u0278\u0279\7\u00d4\2\2\u0279E\3\2\2\2\u027a\u027b"+
		"\7U\2\2\u027b\u027c\5\u00a6T\2\u027c\u0280\5@!\2\u027d\u027f\5H%\2\u027e"+
		"\u027d\3\2\2\2\u027f\u0282\3\2\2\2\u0280\u027e\3\2\2\2\u0280\u0281\3\2"+
		"\2\2\u0281\u0284\3\2\2\2\u0282\u0280\3\2\2\2\u0283\u0285\5L\'\2\u0284"+
		"\u0283\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0297\3\2\2\2\u0286\u0287\7U"+
		"\2\2\u0287\u0288\5\u00a6T\2\u0288\u0289\7\u00d6\2\2\u0289\u028d\5<\37"+
		"\2\u028a\u028c\5J&\2\u028b\u028a\3\2\2\2\u028c\u028f\3\2\2\2\u028d\u028b"+
		"\3\2\2\2\u028d\u028e\3\2\2\2\u028e\u0291\3\2\2\2\u028f\u028d\3\2\2\2\u0290"+
		"\u0292\5N(\2\u0291\u0290\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0293\3\2\2"+
		"\2\u0293\u0294\7G\2\2\u0294\u0295\7\u00d7\2\2\u0295\u0297\3\2\2\2\u0296"+
		"\u027a\3\2\2\2\u0296\u0286\3\2\2\2\u0297G\3\2\2\2\u0298\u0299\7B\2\2\u0299"+
		"\u029a\5\u00a6T\2\u029a\u029b\5@!\2\u029bI\3\2\2\2\u029c\u029d\7B\2\2"+
		"\u029d\u029e\5\u00a6T\2\u029e\u029f\7\u00d6\2\2\u029f\u02a0\5<\37\2\u02a0"+
		"K\3\2\2\2\u02a1\u02a2\7A\2\2\u02a2\u02a3\5@!\2\u02a3M\3\2\2\2\u02a4\u02a5"+
		"\7A\2\2\u02a5\u02a6\7\u00d6\2\2\u02a6\u02a7\5<\37\2\u02a7O\3\2\2\2\u02a8"+
		"\u02a9\7\u0080\2\2\u02a9\u02b0\5\u00a6T\2\u02aa\u02b1\5@!\2\u02ab\u02ac"+
		"\7\u00d6\2\2\u02ac\u02ad\5<\37\2\u02ad\u02ae\7I\2\2\u02ae\u02af\7\u00d7"+
		"\2\2\u02af\u02b1\3\2\2\2\u02b0\u02aa\3\2\2\2\u02b0\u02ab\3\2\2\2\u02b1"+
		"Q\3\2\2\2\u02b2\u02b3\7=\2\2\u02b3\u02b4\5@!\2\u02b4\u02b5\7\u0080\2\2"+
		"\u02b5\u02b6\5\u00a6T\2\u02b6\u02b7\7\u00d7\2\2\u02b7S\3\2\2\2\u02b8\u02b9"+
		"\7P\2\2\u02b9\u02bb\7\u00cf\2\2\u02ba\u02bc\5V,\2\u02bb\u02ba\3\2\2\2"+
		"\u02bb\u02bc\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02bf\7\u00d7\2\2\u02be"+
		"\u02c0\5\u00a4S\2\u02bf\u02be\3\2\2\2\u02bf\u02c0\3\2\2\2\u02c0\u02c1"+
		"\3\2\2\2\u02c1\u02c3\7\u00d7\2\2\u02c2\u02c4\5X-\2\u02c3\u02c2\3\2\2\2"+
		"\u02c3\u02c4\3\2\2\2\u02c4\u02c5\3\2\2\2\u02c5\u02cc\7\u00d0\2\2\u02c6"+
		"\u02cd\5@!\2\u02c7\u02c8\7\u00d6\2\2\u02c8\u02c9\5<\37\2\u02c9\u02ca\7"+
		"E\2\2\u02ca\u02cb\7\u00d7\2\2\u02cb\u02cd\3\2\2\2\u02cc\u02c6\3\2\2\2"+
		"\u02cc\u02c7\3\2\2\2\u02cdU\3\2\2\2\u02ce\u02cf\5\u00a4S\2\u02cfW\3\2"+
		"\2\2\u02d0\u02d1\5\u00a4S\2\u02d1Y\3\2\2\2\u02d2\u02d3\7v\2\2\u02d3\u02eb"+
		"\5\u00a6T\2\u02d4\u02d6\7\u00d3\2\2\u02d5\u02d7\7\u00d7\2\2\u02d6\u02d5"+
		"\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02db\3\2\2\2\u02d8\u02da\5\\/\2\u02d9"+
		"\u02d8\3\2\2\2\u02da\u02dd\3\2\2\2\u02db\u02d9\3\2\2\2\u02db\u02dc\3\2"+
		"\2\2\u02dc\u02de\3\2\2\2\u02dd\u02db\3\2\2\2\u02de\u02ec\7\u00d4\2\2\u02df"+
		"\u02e1\7\u00d6\2\2\u02e0\u02e2\7\u00d7\2\2\u02e1\u02e0\3\2\2\2\u02e1\u02e2"+
		"\3\2\2\2\u02e2\u02e6\3\2\2\2\u02e3\u02e5\5\\/\2\u02e4\u02e3\3\2\2\2\u02e5"+
		"\u02e8\3\2\2\2\u02e6\u02e4\3\2\2\2\u02e6\u02e7\3\2\2\2\u02e7\u02e9\3\2"+
		"\2\2\u02e8\u02e6\3\2\2\2\u02e9\u02ea\7H\2\2\u02ea\u02ec\7\u00d7\2\2\u02eb"+
		"\u02d4\3\2\2\2\u02eb\u02df\3\2\2\2\u02ec[\3\2\2\2\u02ed\u02ee\7\65\2\2"+
		"\u02ee\u02f1\5\u00a8U\2\u02ef\u02f1\7<\2\2\u02f0\u02ed\3\2\2\2\u02f0\u02ef"+
		"\3\2\2\2\u02f1\u02f2\3\2\2\2\u02f2\u02f4\t\4\2\2\u02f3\u02f0\3\2\2\2\u02f4"+
		"\u02f5\3\2\2\2\u02f5\u02f3\3\2\2\2\u02f5\u02f6\3\2\2\2\u02f6\u02f7\3\2"+
		"\2\2\u02f7\u02f8\5<\37\2\u02f8]\3\2\2\2\u02f9\u02fb\7\63\2\2\u02fa\u02fc"+
		"\5\u00a8U\2\u02fb\u02fa\3\2\2\2\u02fb\u02fc\3\2\2\2\u02fc\u02fd\3\2\2"+
		"\2\u02fd\u02fe\7\u00d7\2\2\u02fe_\3\2\2\2\u02ff\u0301\7:\2\2\u0300\u0302"+
		"\5\u00a8U\2\u0301\u0300\3\2\2\2\u0301\u0302\3\2\2\2\u0302\u0303\3\2\2"+
		"\2\u0303\u0304\7\u00d7\2\2\u0304a\3\2\2\2\u0305\u0307\7s\2\2\u0306\u0308"+
		"\5\u00a8U\2\u0307\u0306\3\2\2\2\u0307\u0308\3\2\2\2\u0308\u0309\3\2\2"+
		"\2\u0309\u030a\7\u00d7\2\2\u030ac\3\2\2\2\u030b\u030c\5\u00a8U\2\u030c"+
		"\u030d\7\u00d7\2\2\u030de\3\2\2\2\u030e\u030f\7}\2\2\u030f\u0310\7\u00cf"+
		"\2\2\u0310\u0311\5\u00e6t\2\u0311\u0312\7\u00d0\2\2\u0312\u0313\7\u00d7"+
		"\2\2\u0313g\3\2\2\2\u0314\u033b\7Q\2\2\u0315\u0316\7\u00cf\2\2\u0316\u0317"+
		"\5\u00e8u\2\u0317\u0319\7/\2\2\u0318\u031a\7\u00c1\2\2\u0319\u0318\3\2"+
		"\2\2\u0319\u031a\3\2\2\2\u031a\u031b\3\2\2\2\u031b\u0321\5\u00aaV\2\u031c"+
		"\u031e\7\u009f\2\2\u031d\u031f\7\u00c1\2\2\u031e\u031d\3\2\2\2\u031e\u031f"+
		"\3\2\2\2\u031f\u0320\3\2\2\2\u0320\u0322\5\u00e8u\2\u0321\u031c\3\2\2"+
		"\2\u0321\u0322\3\2\2\2\u0322\u0323\3\2\2\2\u0323\u0324\7\u00d0\2\2\u0324"+
		"\u033c\3\2\2\2\u0325\u0326\7\u00cf\2\2\u0326\u0327\5\u00a8U\2\u0327\u0328"+
		"\7/\2\2\u0328\u032e\5\u00aaV\2\u0329\u032b\7\u009f\2\2\u032a\u032c\7\u00c1"+
		"\2\2\u032b\u032a\3\2\2\2\u032b\u032c\3\2\2\2\u032c\u032d\3\2\2\2\u032d"+
		"\u032f\5\u00e8u\2\u032e\u0329\3\2\2\2\u032e\u032f\3\2\2\2\u032f\u0330"+
		"\3\2\2\2\u0330\u0331\7\u00d0\2\2\u0331\u033c\3\2\2\2\u0332\u0333\7\u00cf"+
		"\2\2\u0333\u0334\5\u00e8u\2\u0334\u0335\7/\2\2\u0335\u0336\7b\2\2\u0336"+
		"\u0337\7\u00cf\2\2\u0337\u0338\5\u00fe\u0080\2\u0338\u0339\7\u00d0\2\2"+
		"\u0339\u033a\7\u00d0\2\2\u033a\u033c\3\2\2\2\u033b\u0315\3\2\2\2\u033b"+
		"\u0325\3\2\2\2\u033b\u0332\3\2\2\2\u033c\u0343\3\2\2\2\u033d\u0344\5@"+
		"!\2\u033e\u033f\7\u00d6\2\2\u033f\u0340\5<\37\2\u0340\u0341\7F\2\2\u0341"+
		"\u0342\7\u00d7\2\2\u0342\u0344\3\2\2\2\u0343\u033d\3\2\2\2\u0343\u033e"+
		"\3\2\2\2\u0344i\3\2\2\2\u0345\u0346\7y\2\2\u0346\u0356\5D#\2\u0347\u0349"+
		"\5l\67\2\u0348\u0347\3\2\2\2\u0349\u034a\3\2\2\2\u034a\u0348\3\2\2\2\u034a"+
		"\u034b\3\2\2\2\u034b\u034d\3\2\2\2\u034c\u034e\5n8\2\u034d\u034c\3\2\2"+
		"\2\u034d\u034e\3\2\2\2\u034e\u0357\3\2\2\2\u034f\u0351\5l\67\2\u0350\u034f"+
		"\3\2\2\2\u0351\u0354\3\2\2\2\u0352\u0350\3\2\2\2\u0352\u0353\3\2\2\2\u0353"+
		"\u0355\3\2\2\2\u0354\u0352\3\2\2\2\u0355\u0357\5n8\2\u0356\u0348\3\2\2"+
		"\2\u0356\u0352\3\2\2\2\u0357k\3\2\2\2\u0358\u0359\7\66\2\2\u0359\u035a"+
		"\7\u00cf\2\2\u035a\u035f\5\u00be`\2\u035b\u035c\7\u00c2\2\2\u035c\u035e"+
		"\5\u00be`\2\u035d\u035b\3\2\2\2\u035e\u0361\3\2\2\2\u035f\u035d\3\2\2"+
		"\2\u035f\u0360\3\2\2\2\u0360\u0362\3\2\2\2\u0361\u035f\3\2\2\2\u0362\u0363"+
		"\7\u00db\2\2\u0363\u0364\7\u00d0\2\2\u0364\u0365\5D#\2\u0365m\3\2\2\2"+
		"\u0366\u0367\7N\2\2\u0367\u0368\5D#\2\u0368o\3\2\2\2\u0369\u036a\7w\2"+
		"\2\u036a\u036b\5\u00a8U\2\u036b\u036c\7\u00d7\2\2\u036cq\3\2\2\2\u036d"+
		"\u036e\7T\2\2\u036e\u036f\5\u0104\u0083\2\u036f\u0370\7\u00d7\2\2\u0370"+
		"s\3\2\2\2\u0371\u0372\7;\2\2\u0372\u0373\7\u00cf\2\2\u0373\u0374\5z>\2"+
		"\u0374\u037b\7\u00d0\2\2\u0375\u037c\5@!\2\u0376\u0377\7\u00d6\2\2\u0377"+
		"\u0378\5<\37\2\u0378\u0379\7D\2\2\u0379\u037a\7\u00d7\2\2\u037a\u037c"+
		"\3\2\2\2\u037b\u0375\3\2\2\2\u037b\u0376\3\2\2\2\u037cu\3\2\2\2\u037d"+
		"\u037f\5x=\2\u037e\u037d\3\2\2\2\u037f\u0380\3\2\2\2\u0380\u037e\3\2\2"+
		"\2\u0380\u0381\3\2\2\2\u0381w\3\2\2\2\u0382\u0385\5\6\4\2\u0383\u0385"+
		"\5\n\6\2\u0384\u0382\3\2\2\2\u0384\u0383\3\2\2\2\u0385y\3\2\2\2\u0386"+
		"\u038b\5\u00a0Q\2\u0387\u0388\7\u00d5\2\2\u0388\u038a\5\u00a0Q\2\u0389"+
		"\u0387\3\2\2\2\u038a\u038d\3\2\2\2\u038b\u0389\3\2\2\2\u038b\u038c\3\2"+
		"\2\2\u038c{\3\2\2\2\u038d\u038b\3\2\2\2\u038e\u0390\5~@\2\u038f\u038e"+
		"\3\2\2\2\u038f\u0390\3\2\2\2\u0390\u0395\3\2\2\2\u0391\u0392\7\u00d5\2"+
		"\2\u0392\u0394\5~@\2\u0393\u0391\3\2\2\2\u0394\u0397\3\2\2\2\u0395\u0393"+
		"\3\2\2\2\u0395\u0396\3\2\2\2\u0396}\3\2\2\2\u0397\u0395\3\2\2\2\u0398"+
		"\u039a\5\60\31\2\u0399\u039b\7\u00ce\2\2\u039a\u0399\3\2\2\2\u039a\u039b"+
		"\3\2\2\2\u039b\u039d\3\2\2\2\u039c\u039e\5\u0080A\2\u039d\u039c\3\2\2"+
		"\2\u039d\u039e\3\2\2\2\u039e\u03a0\3\2\2\2\u039f\u03a1\7\u00c1\2\2\u03a0"+
		"\u039f\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1\u03a3\3\2\2\2\u03a2\u03a4\7\u00be"+
		"\2\2\u03a3\u03a2\3\2\2\2\u03a3\u03a4\3\2\2\2\u03a4\u03a5\3\2\2\2\u03a5"+
		"\u03a6\5\u009eP\2\u03a6\177\3\2\2\2\u03a7\u03ab\5\u00be`\2\u03a8\u03ab"+
		"\7\64\2\2\u03a9\u03ab\5\u010c\u0087\2\u03aa\u03a7\3\2\2\2\u03aa\u03a8"+
		"\3\2\2\2\u03aa\u03a9\3\2\2\2\u03ab\u0081\3\2\2\2\u03ac\u03ad\7S\2\2\u03ad"+
		"\u03b2\5\u0084C\2\u03ae\u03af\7\u00d5\2\2\u03af\u03b1\5\u0084C\2\u03b0"+
		"\u03ae\3\2\2\2\u03b1\u03b4\3\2\2\2\u03b2\u03b0\3\2\2\2\u03b2\u03b3\3\2"+
		"\2\2\u03b3\u03b5\3\2\2\2\u03b4\u03b2\3\2\2\2\u03b5\u03b6\7\u00d7\2\2\u03b6"+
		"\u0083\3\2\2\2\u03b7\u03c0\7\u00db\2\2\u03b8\u03b9\7\u00cc\2\2\u03b9\u03c0"+
		"\5\u00e8u\2\u03ba\u03bb\7\u00cc\2\2\u03bb\u03bc\7\u00d3\2\2\u03bc\u03bd"+
		"\5\u00a8U\2\u03bd\u03be\7\u00d4\2\2\u03be\u03c0\3\2\2\2\u03bf\u03b7\3"+
		"\2\2\2\u03bf\u03b8\3\2\2\2\u03bf\u03ba\3\2\2\2\u03c0\u0085\3\2\2\2\u03c1"+
		"\u03c2\7@\2\2\u03c2\u03c3\5\u00a4S\2\u03c3\u03c4\7\u00d7\2\2\u03c4\u0087"+
		"\3\2\2\2\u03c5\u03c6\7t\2\2\u03c6\u03cb\5\u009eP\2\u03c7\u03c8\7\u00d5"+
		"\2\2\u03c8\u03ca\5\u009eP\2\u03c9\u03c7\3\2\2\2\u03ca\u03cd\3\2\2\2\u03cb"+
		"\u03c9\3\2\2\2\u03cb\u03cc\3\2\2\2\u03cc\u03ce\3\2\2\2\u03cd\u03cb\3\2"+
		"\2\2\u03ce\u03cf\7\u00d7\2\2\u03cf\u0089\3\2\2\2\u03d0\u03d1\5\60\31\2"+
		"\u03d1\u03d3\5\u009aN\2\u03d2\u03d4\5\u0080A\2\u03d3\u03d2\3\2\2\2\u03d3"+
		"\u03d4\3\2\2\2\u03d4\u03d5\3\2\2\2\u03d5\u03da\5\u009eP\2\u03d6\u03d7"+
		"\7\u00d5\2\2\u03d7\u03d9\5\u009eP\2\u03d8\u03d6\3\2\2\2\u03d9\u03dc\3"+
		"\2\2\2\u03da\u03d8\3\2\2\2\u03da\u03db\3\2\2\2\u03db\u03dd\3\2\2\2\u03dc"+
		"\u03da\3\2\2\2\u03dd\u03de\7\u00d7\2\2\u03de\u040a\3\2\2\2\u03df\u03e1"+
		"\5\60\31\2\u03e0\u03e2\5\u009cO\2\u03e1\u03e0\3\2\2\2\u03e1\u03e2\3\2"+
		"\2\2\u03e2\u03e3\3\2\2\2\u03e3\u03e5\79\2\2\u03e4\u03e6\5\u0080A\2\u03e5"+
		"\u03e4\3\2\2\2\u03e5\u03e6\3\2\2\2\u03e6\u03e7\3\2\2\2\u03e7\u03ec\5\u00a0"+
		"Q\2\u03e8\u03e9\7\u00d5\2\2\u03e9\u03eb\5\u00a0Q\2\u03ea\u03e8\3\2\2\2"+
		"\u03eb\u03ee\3\2\2\2\u03ec\u03ea\3\2\2\2\u03ec\u03ed\3\2\2\2\u03ed\u03ef"+
		"\3\2\2\2\u03ee\u03ec\3\2\2\2\u03ef\u03f0\7\u00d7\2\2\u03f0\u040a\3\2\2"+
		"\2\u03f1\u03f3\5\60\31\2\u03f2\u03f4\5\u009cO\2\u03f3\u03f2\3\2\2\2\u03f3"+
		"\u03f4\3\2\2\2\u03f4\u03f5\3\2\2\2\u03f5\u03f7\7R\2\2\u03f6\u03f8\7\u00c1"+
		"\2\2\u03f7\u03f6\3\2\2\2\u03f7\u03f8\3\2\2\2\u03f8\u03f9\3\2\2\2\u03f9"+
		"\u03fb\5\u0104\u0083\2\u03fa\u03fc\5$\23\2\u03fb\u03fa\3\2\2\2\u03fb\u03fc"+
		"\3\2\2\2\u03fc\u03fd\3\2\2\2\u03fd\u03fe\7\u00cf\2\2\u03fe\u03ff\5|?\2"+
		"\u03ff\u0401\7\u00d0\2\2\u0400\u0402\5\u0096L\2\u0401\u0400\3\2\2\2\u0401"+
		"\u0402\3\2\2\2\u0402\u0403\3\2\2\2\u0403\u0404\5\u0098M\2\u0404\u040a"+
		"\3\2\2\2\u0405\u0406\7~\2\2\u0406\u0407\5\u00ccg\2\u0407\u0408\5\u008c"+
		"G\2\u0408\u040a\3\2\2\2\u0409\u03d0\3\2\2\2\u0409\u03df\3\2\2\2\u0409"+
		"\u03f1\3\2\2\2\u0409\u0405\3\2\2\2\u040a\u008b\3\2\2\2\u040b\u0415\7\u00d7"+
		"\2\2\u040c\u0410\7\u00d3\2\2\u040d\u040f\5\u008eH\2\u040e\u040d\3\2\2"+
		"\2\u040f\u0412\3\2\2\2\u0410\u040e\3\2\2\2\u0410\u0411\3\2\2\2\u0411\u0413"+
		"\3\2\2\2\u0412\u0410\3\2\2\2\u0413\u0415\7\u00d4\2\2\u0414\u040b\3\2\2"+
		"\2\u0414\u040c\3\2\2\2\u0415\u008d\3\2\2\2\u0416\u0419\5\u0090I\2\u0417"+
		"\u0419\5\u0092J\2\u0418\u0416\3\2\2\2\u0418\u0417\3\2\2\2\u0419\u008f"+
		"\3\2\2\2\u041a\u041b\5\u00c6d\2\u041b\u041c\7\u00bb\2\2\u041c\u041d\5"+
		"\u0104\u0083\2\u041d\u041e\7[\2\2\u041e\u041f\5\u00ccg\2\u041f\u0420\7"+
		"\u00d7\2\2\u0420\u0091\3\2\2\2\u0421\u0422\5\u0094K\2\u0422\u0428\7/\2"+
		"\2\u0423\u0429\5\u0106\u0084\2\u0424\u0426\5\u0106\u0084\2\u0425\u0424"+
		"\3\2\2\2\u0425\u0426\3\2\2\2\u0426\u0427\3\2\2\2\u0427\u0429\5\u0104\u0083"+
		"\2\u0428\u0423\3\2\2\2\u0428\u0425\3\2\2\2\u0429\u042a\3\2\2\2\u042a\u042b"+
		"\7\u00d7\2\2\u042b\u0093\3\2\2\2\u042c\u042d\5\u00c6d\2\u042d\u042e\7"+
		"\u00bb\2\2\u042e\u0430\3\2\2\2\u042f\u042c\3\2\2\2\u042f\u0430\3\2\2\2"+
		"\u0430\u0431\3\2\2\2\u0431\u0432\5\u0104\u0083\2\u0432\u0095\3\2\2\2\u0433"+
		"\u0434\7\u00d6\2\2\u0434\u0436\5\u0104\u0083\2\u0435\u0437\5\u00ceh\2"+
		"\u0436\u0435\3\2\2\2\u0436\u0437\3\2\2\2\u0437\u0097\3\2\2\2\u0438\u043b"+
		"\7\u00d7\2\2\u0439\u043b\5D#\2\u043a\u0438\3\2\2\2\u043a\u0439\3\2\2\2"+
		"\u043b\u0099\3\2\2\2\u043c\u043f\5\u009cO\2\u043d\u043f\7\177\2\2\u043e"+
		"\u043c\3\2\2\2\u043e\u043d\3\2\2\2\u043f\u009b\3\2\2\2\u0440\u0442\5\u0106"+
		"\u0084\2\u0441\u0440\3\2\2\2\u0442\u0443\3\2\2\2\u0443\u0441\3\2\2\2\u0443"+
		"\u0444\3\2\2\2\u0444\u009d\3\2\2\2\u0445\u0448\7\u00db\2\2\u0446\u0447"+
		"\7\u00d8\2\2\u0447\u0449\5\u00d2j\2\u0448\u0446\3\2\2\2\u0448\u0449\3"+
		"\2\2\2\u0449\u009f\3\2\2\2\u044a\u044b\5\u0104\u0083\2\u044b\u044c\7\u00d8"+
		"\2\2\u044c\u044d\5\u00d2j\2\u044d\u00a1\3\2\2\2\u044e\u044f\5\60\31\2"+
		"\u044f\u0450\79\2\2\u0450\u0455\5\u00a0Q\2\u0451\u0452\7\u00d5\2\2\u0452"+
		"\u0454\5\u00a0Q\2\u0453\u0451\3\2\2\2\u0454\u0457\3\2\2\2\u0455\u0453"+
		"\3\2\2\2\u0455\u0456\3\2\2\2\u0456\u0458\3\2\2\2\u0457\u0455\3\2\2\2\u0458"+
		"\u0459\7\u00d7\2\2\u0459\u00a3\3\2\2\2\u045a\u045f\5\u00a8U\2\u045b\u045c"+
		"\7\u00d5\2\2\u045c\u045e\5\u00a8U\2\u045d\u045b\3\2\2\2\u045e\u0461\3"+
		"\2\2\2\u045f\u045d\3\2\2\2\u045f\u0460\3\2\2\2\u0460\u00a5\3\2\2\2\u0461"+
		"\u045f\3\2\2\2\u0462\u0465\7\u00cf\2\2\u0463\u0466\5\u00a8U\2\u0464\u0466"+
		"\5\u00b4[\2\u0465\u0463\3\2\2\2\u0465\u0464\3\2\2\2\u0466\u0467\3\2\2"+
		"\2\u0467\u0468\7\u00d0\2\2\u0468\u00a7\3\2\2\2\u0469\u046a\bU\1\2\u046a"+
		"\u046b\78\2\2\u046b\u04b7\5\u00a8U/\u046c\u04b7\5\u00b0Y\2\u046d\u046e"+
		"\5\u00e0q\2\u046e\u046f\7\u00d1\2\2\u046f\u0470\5\u00a8U\2\u0470\u0471"+
		"\7\u00d2\2\2\u0471\u04b7\3\2\2\2\u0472\u0473\7\u00cf\2\2\u0473\u0474\5"+
		"\u010e\u0088\2\u0474\u0475\7\u00d0\2\2\u0475\u0476\5\u00a8U,\u0476\u04b7"+
		"\3\2\2\2\u0477\u0478\t\5\2\2\u0478\u04b7\5\u00a8U+\u0479\u047a\t\6\2\2"+
		"\u047a\u04b7\5\u00a8U*\u047b\u047c\t\7\2\2\u047c\u04b7\5\u00e8u\2\u047d"+
		"\u047e\5\u00e8u\2\u047e\u047f\t\7\2\2\u047f\u04b7\3\2\2\2\u0480\u0481"+
		"\7l\2\2\u0481\u04b7\5\u00a8U\'\u0482\u04b7\5\u00e8u\2\u0483\u04b7\5\u00d8"+
		"m\2\u0484\u04b7\5\u00e2r\2\u0485\u04b7\7\u00dc\2\2\u0486\u04b7\7\u00e2"+
		"\2\2\u0487\u04b7\5\u00a6T\2\u0488\u04b7\5\u00acW\2\u0489\u04b7\7\u0081"+
		"\2\2\u048a\u048b\7b\2\2\u048b\u048c\7\u00cf\2\2\u048c\u048d\5\u00fe\u0080"+
		"\2\u048d\u048e\7\u00d0\2\2\u048e\u048f\7\u00d8\2\2\u048f\u0490\5\u00a8"+
		"U\36\u0490\u04b7\3\2\2\2\u0491\u0492\7a\2\2\u0492\u0493\7\u00cf\2\2\u0493"+
		"\u0494\5\u00e6t\2\u0494\u0495\7\u00d0\2\2\u0495\u04b7\3\2\2\2\u0496\u0497"+
		"\7C\2\2\u0497\u0498\7\u00cf\2\2\u0498\u0499\5\u00e8u\2\u0499\u049a\7\u00d0"+
		"\2\2\u049a\u04b7\3\2\2\2\u049b\u049c\7J\2\2\u049c\u049d\7\u00cf\2\2\u049d"+
		"\u049e\5\u00a8U\2\u049e\u049f\7\u00d0\2\2\u049f\u04b7\3\2\2\2\u04a0\u04a4"+
		"\7K\2\2\u04a1\u04a2\7\u00cf\2\2\u04a2\u04a5\7\u00d0\2\2\u04a3\u04a5\5"+
		"\u00a6T\2\u04a4\u04a1\3\2\2\2\u04a4\u04a3\3\2\2\2\u04a4\u04a5\3\2\2\2"+
		"\u04a5\u04b7\3\2\2\2\u04a6\u04a7\t\b\2\2\u04a7\u04b7\5\u00a8U\31\u04a8"+
		"\u04a9\t\t\2\2\u04a9\u04b7\5\u00a8U\30\u04aa\u04b7\5\u00aeX\2\u04ab\u04ac"+
		"\5\u00aaV\2\u04ac\u04ad\5\u00b2Z\2\u04ad\u04ae\5\u00a8U\7\u04ae\u04b7"+
		"\3\2\2\2\u04af\u04b0\5\u00aaV\2\u04b0\u04b1\7\u00d8\2\2\u04b1\u04b4\7"+
		"\u00c1\2\2\u04b2\u04b5\5\u00e8u\2\u04b3\u04b5\5\u00b0Y\2\u04b4\u04b2\3"+
		"\2\2\2\u04b4\u04b3\3\2\2\2\u04b5\u04b7\3\2\2\2\u04b6\u0469\3\2\2\2\u04b6"+
		"\u046c\3\2\2\2\u04b6\u046d\3\2\2\2\u04b6\u0472\3\2\2\2\u04b6\u0477\3\2"+
		"\2\2\u04b6\u0479\3\2\2\2\u04b6\u047b\3\2\2\2\u04b6\u047d\3\2\2\2\u04b6"+
		"\u0480\3\2\2\2\u04b6\u0482\3\2\2\2\u04b6\u0483\3\2\2\2\u04b6\u0484\3\2"+
		"\2\2\u04b6\u0485\3\2\2\2\u04b6\u0486\3\2\2\2\u04b6\u0487\3\2\2\2\u04b6"+
		"\u0488\3\2\2\2\u04b6\u0489\3\2\2\2\u04b6\u048a\3\2\2\2\u04b6\u0491\3\2"+
		"\2\2\u04b6\u0496\3\2\2\2\u04b6\u049b\3\2\2\2\u04b6\u04a0\3\2\2\2\u04b6"+
		"\u04a6\3\2\2\2\u04b6\u04a8\3\2\2\2\u04b6\u04aa\3\2\2\2\u04b6\u04ab\3\2"+
		"\2\2\u04b6\u04af\3\2\2\2\u04b7\u04f4\3\2\2\2\u04b8\u04b9\f\26\2\2\u04b9"+
		"\u04ba\7\u00ab\2\2\u04ba\u04f3\5\u00a8U\26\u04bb\u04bc\f\24\2\2\u04bc"+
		"\u04bd\t\n\2\2\u04bd\u04f3\5\u00a8U\25\u04be\u04bf\f\23\2\2\u04bf\u04c0"+
		"\t\13\2\2\u04c0\u04f3\5\u00a8U\24\u04c1\u04c2\f\22\2\2\u04c2\u04c3\t\f"+
		"\2\2\u04c3\u04f3\5\u00a8U\23\u04c4\u04c5\f\21\2\2\u04c5\u04c6\t\r\2\2"+
		"\u04c6\u04f3\5\u00a8U\22\u04c7\u04c8\f\20\2\2\u04c8\u04c9\t\16\2\2\u04c9"+
		"\u04f3\5\u00a8U\21\u04ca\u04cb\f\17\2\2\u04cb\u04cc\7\u00c1\2\2\u04cc"+
		"\u04f3\5\u00a8U\20\u04cd\u04ce\f\16\2\2\u04ce\u04cf\7\u00c4\2\2\u04cf"+
		"\u04f3\5\u00a8U\17\u04d0\u04d1\f\r\2\2\u04d1\u04d2\7\u00c2\2\2\u04d2\u04f3"+
		"\5\u00a8U\16\u04d3\u04d4\f\f\2\2\u04d4\u04d5\7\u00b6\2\2\u04d5\u04f3\5"+
		"\u00a8U\r\u04d6\u04d7\f\13\2\2\u04d7\u04d8\7\u00b5\2\2\u04d8\u04f3\5\u00a8"+
		"U\f\u04d9\u04da\f\n\2\2\u04da\u04dc\7\u00ce\2\2\u04db\u04dd\5\u00a8U\2"+
		"\u04dc\u04db\3\2\2\2\u04dc\u04dd\3\2\2\2\u04dd\u04de\3\2\2\2\u04de\u04df"+
		"\7\u00d6\2\2\u04df\u04f3\5\u00a8U\13\u04e0\u04e1\f\t\2\2\u04e1\u04e2\7"+
		"\u00b7\2\2\u04e2\u04f3\5\u00a8U\n\u04e3\u04e4\f\b\2\2\u04e4\u04e5\7\u009c"+
		"\2\2\u04e5\u04f3\5\u00a8U\t\u04e6\u04e7\f\5\2\2\u04e7\u04e8\7c\2\2\u04e8"+
		"\u04f3\5\u00a8U\6\u04e9\u04ea\f\4\2\2\u04ea\u04eb\7e\2\2\u04eb\u04f3\5"+
		"\u00a8U\5\u04ec\u04ed\f\3\2\2\u04ed\u04ee\7d\2\2\u04ee\u04f3\5\u00a8U"+
		"\4\u04ef\u04f0\f\25\2\2\u04f0\u04f1\7Z\2\2\u04f1\u04f3\5\u00c0a\2\u04f2"+
		"\u04b8\3\2\2\2\u04f2\u04bb\3\2\2\2\u04f2\u04be\3\2\2\2\u04f2\u04c1\3\2"+
		"\2\2\u04f2\u04c4\3\2\2\2\u04f2\u04c7\3\2\2\2\u04f2\u04ca\3\2\2\2\u04f2"+
		"\u04cd\3\2\2\2\u04f2\u04d0\3\2\2\2\u04f2\u04d3\3\2\2\2\u04f2\u04d6\3\2"+
		"\2\2\u04f2\u04d9\3\2\2\2\u04f2\u04e0\3\2\2\2\u04f2\u04e3\3\2\2\2\u04f2"+
		"\u04e6\3\2\2\2\u04f2\u04e9\3\2\2\2\u04f2\u04ec\3\2\2\2\u04f2\u04ef\3\2"+
		"\2\2\u04f3\u04f6\3\2\2\2\u04f4\u04f2\3\2\2\2\u04f4\u04f5\3\2\2\2\u04f5"+
		"\u00a9\3\2\2\2\u04f6\u04f4\3\2\2\2\u04f7\u04fa\5\u00e8u\2\u04f8\u04fa"+
		"\5\u00acW\2\u04f9\u04f7\3\2\2\2\u04f9\u04f8\3\2\2\2\u04fa\u00ab\3\2\2"+
		"\2\u04fb\u04fc\7.\2\2\u04fc\u04fe\7\u00cf\2\2\u04fd\u04ff\5\u00b6\\\2"+
		"\u04fe\u04fd\3\2\2\2\u04fe\u04ff\3\2\2\2\u04ff\u0500\3\2\2\2\u0500\u0507"+
		"\7\u00d0\2\2\u0501\u0503\7\u00d1\2\2\u0502\u0504\5\u00b6\\\2\u0503\u0502"+
		"\3\2\2\2\u0503\u0504\3\2\2\2\u0504\u0505\3\2\2\2\u0505\u0507\7\u00d2\2"+
		"\2\u0506\u04fb\3\2\2\2\u0506\u0501\3\2\2\2\u0507\u050c\3\2\2\2\u0508\u0509"+
		"\7\u00d1\2\2\u0509\u050a\5\u00a8U\2\u050a\u050b\7\u00d2\2\2\u050b\u050d"+
		"\3\2\2\2\u050c\u0508\3\2\2\2\u050c\u050d\3\2\2\2\u050d\u00ad\3\2\2\2\u050e"+
		"\u0510\7t\2\2\u050f\u050e\3\2\2\2\u050f\u0510\3\2\2\2\u0510\u0511\3\2"+
		"\2\2\u0511\u0513\7R\2\2\u0512\u0514\7\u00c1\2\2\u0513\u0512\3\2\2\2\u0513"+
		"\u0514\3\2\2\2\u0514\u0515\3\2\2\2\u0515\u0516\7\u00cf\2\2\u0516\u0517"+
		"\5|?\2\u0517\u0519\7\u00d0\2\2\u0518\u051a\5\u00ba^\2\u0519\u0518\3\2"+
		"\2\2\u0519\u051a\3\2\2\2\u051a\u051d\3\2\2\2\u051b\u051c\7\u00d6\2\2\u051c"+
		"\u051e\5\u0080A\2\u051d\u051b\3\2\2\2\u051d\u051e\3\2\2\2\u051e\u051f"+
		"\3\2\2\2\u051f\u0520\5D#\2\u0520\u0529\3\2\2\2\u0521\u0522\7\u0083\2\2"+
		"\u0522\u0523\7\u00cf\2\2\u0523\u0524\5|?\2\u0524\u0525\7\u00d0\2\2\u0525"+
		"\u0526\7\u009f\2\2\u0526\u0527\5\u00a8U\2\u0527\u0529\3\2\2\2\u0528\u050f"+
		"\3\2\2\2\u0528\u0521\3\2\2\2\u0529\u00af\3\2\2\2\u052a\u052b\7g\2\2\u052b"+
		"\u052d\5\u00c0a\2\u052c\u052e\5\u00ceh\2\u052d\u052c\3\2\2\2\u052d\u052e"+
		"\3\2\2\2\u052e\u00b1\3\2\2\2\u052f\u0530\t\17\2\2\u0530\u00b3\3\2\2\2"+
		"\u0531\u0539\7\u0081\2\2\u0532\u0535\5\u00a8U\2\u0533\u0534\7\u009f\2"+
		"\2\u0534\u0536\5\u00a8U\2\u0535\u0533\3\2\2\2\u0535\u0536\3\2\2\2\u0536"+
		"\u053a\3\2\2\2\u0537\u0538\7\u0082\2\2\u0538\u053a\5\u00a8U\2\u0539\u0532"+
		"\3\2\2\2\u0539\u0537\3\2\2\2\u053a\u00b5\3\2\2\2\u053b\u0540\5\u00b8]"+
		"\2\u053c\u053d\7\u00d5\2\2\u053d\u053f\5\u00b8]\2\u053e\u053c\3\2\2\2"+
		"\u053f\u0542\3\2\2\2\u0540\u053e\3\2\2\2\u0540\u0541\3\2\2\2\u0541\u0544"+
		"\3\2\2\2\u0542\u0540\3\2\2\2\u0543\u0545\7\u00d5\2\2\u0544\u0543\3\2\2"+
		"\2\u0544\u0545\3\2\2\2\u0545\u00b7\3\2\2\2\u0546\u0549\5\u00a8U\2\u0547"+
		"\u0548\7\u009f\2\2\u0548\u054a\5\u00a8U\2\u0549\u0547\3\2\2\2\u0549\u054a"+
		"\3\2\2\2\u054a\u0553\3\2\2\2\u054b\u054c\5\u00a8U\2\u054c\u054d\7\u009f"+
		"\2\2\u054d\u054f\3\2\2\2\u054e\u054b\3\2\2\2\u054e\u054f\3\2\2\2\u054f"+
		"\u0550\3\2\2\2\u0550\u0551\7\u00c1\2\2\u0551\u0553\5\u00e8u\2\u0552\u0546"+
		"\3\2\2\2\u0552\u054e\3\2\2\2\u0553\u00b9\3\2\2\2\u0554\u0555\7~\2\2\u0555"+
		"\u0556\7\u00cf\2\2\u0556\u055b\5\u00bc_\2\u0557\u0558\7\u00d5\2\2\u0558"+
		"\u055a\5\u00bc_\2\u0559\u0557\3\2\2\2\u055a\u055d\3\2\2\2\u055b\u0559"+
		"\3\2\2\2\u055b\u055c\3\2\2\2\u055c\u055e\3\2\2\2\u055d\u055b\3\2\2\2\u055e"+
		"\u055f\7\u00d0\2\2\u055f\u00bb\3\2\2\2\u0560\u0562\7\u00c1\2\2\u0561\u0560"+
		"\3\2\2\2\u0561\u0562\3\2\2\2\u0562\u0563\3\2\2\2\u0563\u0564\7\u00db\2"+
		"\2\u0564\u00bd\3\2\2\2\u0565\u0567\5\u00c6d\2\u0566\u0568\5.\30\2\u0567"+
		"\u0566\3\2\2\2\u0567\u0568\3\2\2\2\u0568\u056b\3\2\2\2\u0569\u056b\7t"+
		"\2\2\u056a\u0565\3\2\2\2\u056a\u0569\3\2\2\2\u056b\u00bf\3\2\2\2\u056c"+
		"\u056f\5\u00c6d\2\u056d\u056f\5\u00c4c\2\u056e\u056c\3\2\2\2\u056e\u056d"+
		"\3\2\2\2\u056f\u0571\3\2\2\2\u0570\u0572\5.\30\2\u0571\u0570\3\2\2\2\u0571"+
		"\u0572\3\2\2\2\u0572\u0577\3\2\2\2\u0573\u0577\5\u010c\u0087\2\u0574\u0577"+
		"\7t\2\2\u0575\u0577\5\u00c2b\2\u0576\u056e\3\2\2\2\u0576\u0573\3\2\2\2"+
		"\u0576\u0574\3\2\2\2\u0576\u0575\3\2\2\2\u0577\u00c1\3\2\2\2\u0578\u057a"+
		"\5\60\31\2\u0579\u057b\7m\2\2\u057a\u0579\3\2\2\2\u057a\u057b\3\2\2\2"+
		"\u057b\u057d\3\2\2\2\u057c\u057e\5\u0102\u0082\2\u057d\u057c\3\2\2\2\u057d"+
		"\u057e\3\2\2\2\u057e\u0580\3\2\2\2\u057f\u0581\7k\2\2\u0580\u057f\3\2"+
		"\2\2\u0580\u0581\3\2\2\2\u0581\u0597\3\2\2\2\u0582\u0584\5 \21\2\u0583"+
		"\u0585\5$\23\2\u0584\u0583\3\2\2\2\u0584\u0585\3\2\2\2\u0585\u0588\3\2"+
		"\2\2\u0586\u0587\7L\2\2\u0587\u0589\5\u00be`\2\u0588\u0586\3\2\2\2\u0588"+
		"\u0589\3\2\2\2\u0589\u058c\3\2\2\2\u058a\u058b\7V\2\2\u058b\u058d\5\""+
		"\22\2\u058c\u058a\3\2\2\2\u058c\u058d\3\2\2\2\u058d\u0598\3\2\2\2\u058e"+
		"\u058f\7`\2\2\u058f\u0591\5\u0104\u0083\2\u0590\u0592\5$\23\2\u0591\u0590"+
		"\3\2\2\2\u0591\u0592\3\2\2\2\u0592\u0595\3\2\2\2\u0593\u0594\7L\2\2\u0594"+
		"\u0596\5\"\22\2\u0595\u0593\3\2\2\2\u0595\u0596\3\2\2\2\u0596\u0598\3"+
		"\2\2\2\u0597\u0582\3\2\2\2\u0597\u058e\3\2\2\2\u0598\u0599\3\2\2\2\u0599"+
		"\u059d\7\u00d3\2\2\u059a\u059c\5\u008aF\2\u059b\u059a\3\2\2\2\u059c\u059f"+
		"\3\2\2\2\u059d\u059b\3\2\2\2\u059d\u059e\3\2\2\2\u059e\u05a0\3\2\2\2\u059f"+
		"\u059d\3\2\2\2\u05a0\u05a1\7\u00d4\2\2\u05a1\u00c3\3\2\2\2\u05a2\u05a7"+
		"\5\u00f4{\2\u05a3\u05a4\7\u00bc\2\2\u05a4\u05a6\5\u00f6|\2\u05a5\u05a3"+
		"\3\2\2\2\u05a6\u05a9\3\2\2\2\u05a7\u05a5\3\2\2\2\u05a7\u05a8\3\2\2\2\u05a8"+
		"\u00c5\3\2\2\2\u05a9\u05a7\3\2\2\2\u05aa\u05ac\7f\2\2\u05ab\u05aa\3\2"+
		"\2\2\u05ab\u05ac\3\2\2\2\u05ac\u05ae\3\2\2\2\u05ad\u05af\7\u00bd\2\2\u05ae"+
		"\u05ad\3\2\2\2\u05ae\u05af\3\2\2\2\u05af\u05b0\3\2\2\2\u05b0\u05b1\5\u00c8"+
		"e\2\u05b1\u00c7\3\2\2\2\u05b2\u05c0\5\u0104\u0083\2\u05b3\u05b8\5\u0104"+
		"\u0083\2\u05b4\u05b5\7\u00bd\2\2\u05b5\u05b7\5\u0104\u0083\2\u05b6\u05b4"+
		"\3\2\2\2\u05b7\u05ba\3\2\2\2\u05b8\u05b6\3\2\2\2\u05b8\u05b9\3\2\2\2\u05b9"+
		"\u05bd\3\2\2\2\u05ba\u05b8\3\2\2\2\u05bb\u05bc\7\u00bd\2\2\u05bc\u05be"+
		"\5\u00caf\2\u05bd\u05bb\3\2\2\2\u05bd\u05be\3\2\2\2\u05be\u05c0\3\2\2"+
		"\2\u05bf\u05b2\3\2\2\2\u05bf\u05b3\3\2\2\2\u05c0\u00c9\3\2\2\2\u05c1\u05c4"+
		"\5\u0104\u0083\2\u05c2\u05c3\7/\2\2\u05c3\u05c5\5\u0104\u0083\2\u05c4"+
		"\u05c2\3\2\2\2\u05c4\u05c5\3\2\2\2\u05c5\u05d5\3\2\2\2\u05c6\u05c7\7\u00d3"+
		"\2\2\u05c7\u05cc\5\u00caf\2\u05c8\u05c9\7\u00d5\2\2\u05c9\u05cb\5\u00ca"+
		"f\2\u05ca\u05c8\3\2\2\2\u05cb\u05ce\3\2\2\2\u05cc\u05ca\3\2\2\2\u05cc"+
		"\u05cd\3\2\2\2\u05cd\u05d0\3\2\2\2\u05ce\u05cc\3\2\2\2\u05cf\u05d1\7\u00d5"+
		"\2\2\u05d0\u05cf\3\2\2\2\u05d0\u05d1\3\2\2\2\u05d1\u05d2\3\2\2\2\u05d2"+
		"\u05d3\7\u00d4\2\2\u05d3\u05d5\3\2\2\2\u05d4\u05c1\3\2\2\2\u05d4\u05c6"+
		"\3\2\2\2\u05d5\u00cb\3\2\2\2\u05d6\u05db\5\u00c6d\2\u05d7\u05d8\7\u00d5"+
		"\2\2\u05d8\u05da\5\u00c6d\2\u05d9\u05d7\3\2\2\2\u05da\u05dd\3\2\2\2\u05db"+
		"\u05d9\3\2\2\2\u05db\u05dc\3\2\2\2\u05dc\u00cd\3\2\2\2\u05dd\u05db\3\2"+
		"\2\2\u05de\u05e8\7\u00cf\2\2\u05df\u05e4\5\u00d0i\2\u05e0\u05e1\7\u00d5"+
		"\2\2\u05e1\u05e3\5\u00d0i\2\u05e2\u05e0\3\2\2\2\u05e3\u05e6\3\2\2\2\u05e4"+
		"\u05e2\3\2\2\2\u05e4\u05e5\3\2\2\2\u05e5\u05e9\3\2\2\2\u05e6\u05e4\3\2"+
		"\2\2\u05e7\u05e9\5\u00b4[\2\u05e8\u05df\3\2\2\2\u05e8\u05e7\3\2\2\2\u05e8"+
		"\u05e9\3\2\2\2\u05e9\u05eb\3\2\2\2\u05ea\u05ec\7\u00d5\2\2\u05eb\u05ea"+
		"\3\2\2\2\u05eb\u05ec\3\2\2\2\u05ec\u05ed\3\2\2\2\u05ed\u05ee\7\u00d0\2"+
		"\2\u05ee\u00cf\3\2\2\2\u05ef\u05f1\7\u00be\2\2\u05f0\u05ef\3\2\2\2\u05f0"+
		"\u05f1\3\2\2\2\u05f1\u05f2\3\2\2\2\u05f2\u05f6\5\u00a8U\2\u05f3\u05f4"+
		"\7\u00c1\2\2\u05f4\u05f6\5\u00e8u\2\u05f5\u05f0\3\2\2\2\u05f5\u05f3\3"+
		"\2\2\2\u05f6\u00d1\3\2\2\2\u05f7\u060d\5\u00d8m\2\u05f8\u060d\5\u00e2"+
		"r\2\u05f9\u05fa\7.\2\2\u05fa\u05ff\7\u00cf\2\2\u05fb\u05fd\5\u00d4k\2"+
		"\u05fc\u05fe\7\u00d5\2\2\u05fd\u05fc\3\2\2\2\u05fd\u05fe\3\2\2\2\u05fe"+
		"\u0600\3\2\2\2\u05ff\u05fb\3\2\2\2\u05ff\u0600\3\2\2\2\u0600\u0601\3\2"+
		"\2\2\u0601\u060d\7\u00d0\2\2\u0602\u0607\7\u00d1\2\2\u0603\u0605\5\u00d4"+
		"k\2\u0604\u0606\7\u00d5\2\2\u0605\u0604\3\2\2\2\u0605\u0606\3\2\2\2\u0606"+
		"\u0608\3\2\2\2\u0607\u0603\3\2\2\2\u0607\u0608\3\2\2\2\u0608\u0609\3\2"+
		"\2\2\u0609\u060d\7\u00d2\2\2\u060a\u060b\t\20\2\2\u060b\u060d\5\u00d2"+
		"j\2\u060c\u05f7\3\2\2\2\u060c\u05f8\3\2\2\2\u060c\u05f9\3\2\2\2\u060c"+
		"\u0602\3\2\2\2\u060c\u060a\3\2\2\2\u060d\u00d3\3\2\2\2\u060e\u0613\5\u00d6"+
		"l\2\u060f\u0610\7\u00d5\2\2\u0610\u0612\5\u00d6l\2\u0611\u060f\3\2\2\2"+
		"\u0612\u0615\3\2\2\2\u0613\u0611\3\2\2\2\u0613\u0614\3\2\2\2\u0614\u00d5"+
		"\3\2\2\2\u0615\u0613\3\2\2\2\u0616\u0619\5\u00d2j\2\u0617\u0618\7\u009f"+
		"\2\2\u0618\u061a\5\u00d2j\2\u0619\u0617\3\2\2\2\u0619\u061a\3\2\2\2\u061a"+
		"\u00d7\3\2\2\2\u061b\u0621\7h\2\2\u061c\u0621\5\u00dan\2\u061d\u0621\5"+
		"\u0108\u0085\2\u061e\u0621\5\u00dep\2\u061f\u0621\5\u00c6d\2\u0620\u061b"+
		"\3\2\2\2\u0620\u061c\3\2\2\2\u0620\u061d\3\2\2\2\u0620\u061e\3\2\2\2\u0620"+
		"\u061f\3\2\2\2\u0621\u00d9\3\2\2\2\u0622\u0627\7\u00df\2\2\u0623\u0627"+
		"\7\62\2\2\u0624\u0627\5\u00dco\2\u0625\u0627\5\u00e0q\2\u0626\u0622\3"+
		"\2\2\2\u0626\u0623\3\2\2\2\u0626\u0624\3\2\2\2\u0626\u0625\3\2\2\2\u0627"+
		"\u00db\3\2\2\2\u0628\u0629\t\21\2\2\u0629\u00dd\3\2\2\2\u062a\u062b\t"+
		"\22\2\2\u062b\u0630\7\u00bb\2\2\u062c\u0631\5\u0104\u0083\2\u062d\u0631"+
		"\7\u0088\2\2\u062e\u0631\7\u0084\2\2\u062f\u0631\7\u0085\2\2\u0630\u062c"+
		"\3\2\2\2\u0630\u062d\3\2\2\2\u0630\u062e\3\2\2\2\u0630\u062f\3\2\2\2\u0631"+
		"\u063d\3\2\2\2\u0632\u0636\5\u00be`\2\u0633\u0636\5\u00fa~\2\u0634\u0636"+
		"\5\u00e2r\2\u0635\u0632\3\2\2\2\u0635\u0633\3\2\2\2\u0635\u0634\3\2\2"+
		"\2\u0636\u0637\3\2\2\2\u0637\u063a\7\u00bb\2\2\u0638\u063b\5\u0104\u0083"+
		"\2\u0639\u063b\5\u00fa~\2\u063a\u0638\3\2\2\2\u063a\u0639\3\2\2\2\u063b"+
		"\u063d\3\2\2\2\u063c\u062a\3\2\2\2\u063c\u0635\3\2\2\2\u063d\u00df\3\2"+
		"\2\2\u063e\u063f\7\u00dc\2\2\u063f\u00e1\3\2\2\2\u0640\u0642\7\u00e6\2"+
		"\2\u0641\u0643\7\u00ee\2\2\u0642\u0641\3\2\2\2\u0643\u0644\3\2\2\2\u0644"+
		"\u0642\3\2\2\2\u0644\u0645\3\2\2\2\u0645\u0656\3\2\2\2\u0646\u0648\7\u00e5"+
		"\2\2\u0647\u0649\7\u00ee\2\2\u0648\u0647\3\2\2\2\u0649\u064a\3\2\2\2\u064a"+
		"\u0648\3\2\2\2\u064a\u064b\3\2\2\2\u064b\u0656\3\2\2\2\u064c\u0656\7\u00e3"+
		"\2\2\u064d\u0651\7\u00e4\2\2\u064e\u0650\5\u00e4s\2\u064f\u064e\3\2\2"+
		"\2\u0650\u0653\3\2\2\2\u0651\u064f\3\2\2\2\u0651\u0652\3\2\2\2\u0652\u0654"+
		"\3\2\2\2\u0653\u0651\3\2\2\2\u0654\u0656\7\u00e4\2\2\u0655\u0640\3\2\2"+
		"\2\u0655\u0646\3\2\2\2\u0655\u064c\3\2\2\2\u0655\u064d\3\2\2\2\u0656\u00e3"+
		"\3\2\2\2\u0657\u065b\7\u00ea\2\2\u0658\u065b\7\u00e9\2\2\u0659\u065b\5"+
		"\u00e8u\2\u065a\u0657\3\2\2\2\u065a\u0658\3\2\2\2\u065a\u0659\3\2\2\2"+
		"\u065b\u00e5\3\2\2\2\u065c\u0661\5\u00e8u\2\u065d\u065e\7\u00d5\2\2\u065e"+
		"\u0660\5\u00e8u\2\u065f\u065d\3\2\2\2\u0660\u0663\3\2\2\2\u0661\u065f"+
		"\3\2\2\2\u0661\u0662\3\2\2\2\u0662\u00e7\3\2\2\2\u0663\u0661\3\2\2\2\u0664"+
		"\u0668\5\u00eav\2\u0665\u0667\5\u00ecw\2\u0666\u0665\3\2\2\2\u0667\u066a"+
		"\3\2\2\2\u0668\u0666\3\2\2\2\u0668\u0669\3\2\2\2\u0669\u00e9\3\2\2\2\u066a"+
		"\u0668\3\2\2\2\u066b\u0672\5\u00f4{\2\u066c\u0672\5\u00eex\2\u066d\u066e"+
		"\7\u00cf\2\2\u066e\u066f\5\u00b0Y\2\u066f\u0670\7\u00d0\2\2\u0670\u0672"+
		"\3\2\2\2\u0671\u066b\3\2\2\2\u0671\u066c\3\2\2\2\u0671\u066d\3\2\2\2\u0672"+
		"\u00eb\3\2\2\2\u0673\u0674\7\u00bc\2\2\u0674\u0676\5\u00f6|\2\u0675\u0677"+
		"\5\u00f2z\2\u0676\u0675\3\2\2\2\u0676\u0677\3\2\2\2\u0677\u00ed\3\2\2"+
		"\2\u0678\u0679\5\u00f0y\2\u0679\u067a\5\u00f2z\2\u067a\u00ef\3\2\2\2\u067b"+
		"\u0680\5\u00c6d\2\u067c\u0680\5\u00dep\2\u067d\u0680\5\u00f4{\2\u067e"+
		"\u0680\5\u00a6T\2\u067f\u067b\3\2\2\2\u067f\u067c\3\2\2\2\u067f\u067d"+
		"\3\2\2\2\u067f\u067e\3\2\2\2\u0680\u00f1\3\2\2\2\u0681\u0683\5.\30\2\u0682"+
		"\u0681\3\2\2\2\u0682\u0683\3\2\2\2\u0683\u0684\3\2\2\2\u0684\u0688\5\u00ce"+
		"h\2\u0685\u0687\5\u00fc\177\2\u0686\u0685\3\2\2\2\u0687\u068a\3\2\2\2"+
		"\u0688\u0686\3\2\2\2\u0688\u0689\3\2\2\2\u0689\u00f3\3\2\2\2\u068a\u0688"+
		"\3\2\2\2\u068b\u068e\5\u00fa~\2\u068c\u068d\7\u00bb\2\2\u068d\u068f\5"+
		"\u00fa~\2\u068e\u068c\3\2\2\2\u068e\u068f\3\2\2\2\u068f\u0695\3\2\2\2"+
		"\u0690\u0691\5\u00be`\2\u0691\u0692\7\u00bb\2\2\u0692\u0693\5\u00fa~\2"+
		"\u0693\u0695\3\2\2\2\u0694\u068b\3\2\2\2\u0694\u0690\3\2\2\2\u0695\u00f5"+
		"\3\2\2\2\u0696\u0699\5\u00f8}\2\u0697\u0699\5\u00fa~\2\u0698\u0696\3\2"+
		"\2\2\u0698\u0697\3\2\2\2\u0699\u00f7\3\2\2\2\u069a\u06a0\5\u0104\u0083"+
		"\2\u069b\u069c\7\u00d3\2\2\u069c\u069d\5\u00a8U\2\u069d\u069e\7\u00d4"+
		"\2\2\u069e\u06a0\3\2\2\2\u069f\u069a\3\2\2\2\u069f\u069b\3\2\2\2\u06a0"+
		"\u06a4\3\2\2\2\u06a1\u06a3\5\u00fc\177\2\u06a2\u06a1\3\2\2\2\u06a3\u06a6"+
		"\3\2\2\2\u06a4\u06a2\3\2\2\2\u06a4\u06a5\3\2\2\2\u06a5\u00f9\3\2\2\2\u06a6"+
		"\u06a4\3\2\2\2\u06a7\u06a9\7\u00cc\2\2\u06a8\u06a7\3\2\2\2\u06a9\u06ac"+
		"\3\2\2\2\u06aa\u06a8\3\2\2\2\u06aa\u06ab\3\2\2\2\u06ab\u06b3\3\2\2\2\u06ac"+
		"\u06aa\3\2\2\2\u06ad\u06b4\7\u00db\2\2\u06ae\u06af\7\u00cc\2\2\u06af\u06b0"+
		"\7\u00d3\2\2\u06b0\u06b1\5\u00a8U\2\u06b1\u06b2\7\u00d4\2\2\u06b2\u06b4"+
		"\3\2\2\2\u06b3\u06ad\3\2\2\2\u06b3\u06ae\3\2\2\2\u06b4\u06b8\3\2\2\2\u06b5"+
		"\u06b7\5\u00fc\177\2\u06b6\u06b5\3\2\2\2\u06b7\u06ba\3\2\2\2\u06b8\u06b6"+
		"\3\2\2\2\u06b8\u06b9\3\2\2\2\u06b9\u00fb\3\2\2\2\u06ba\u06b8\3\2\2\2\u06bb"+
		"\u06bd\7\u00d1\2\2\u06bc\u06be\5\u00a8U\2\u06bd\u06bc\3\2\2\2\u06bd\u06be"+
		"\3\2\2\2\u06be\u06bf\3\2\2\2\u06bf\u06c5\7\u00d2\2\2\u06c0\u06c1\7\u00d3"+
		"\2\2\u06c1\u06c2\5\u00a8U\2\u06c2\u06c3\7\u00d4\2\2\u06c3\u06c5\3\2\2"+
		"\2\u06c4\u06bb\3\2\2\2\u06c4\u06c0\3\2\2\2\u06c5\u00fd\3\2\2\2\u06c6\u06c8"+
		"\5\u0100\u0081\2\u06c7\u06c6\3\2\2\2\u06c7\u06c8\3\2\2\2\u06c8\u06cf\3"+
		"\2\2\2\u06c9\u06cb\7\u00d5\2\2\u06ca\u06cc\5\u0100\u0081\2\u06cb\u06ca"+
		"\3\2\2\2\u06cb\u06cc\3\2\2\2\u06cc\u06ce\3\2\2\2\u06cd\u06c9\3\2\2\2\u06ce"+
		"\u06d1\3\2\2\2\u06cf\u06cd\3\2\2\2\u06cf\u06d0\3\2\2\2\u06d0\u00ff\3\2"+
		"\2\2\u06d1\u06cf\3\2\2\2\u06d2\u06da\5\u00e8u\2\u06d3\u06d4\7b\2\2\u06d4"+
		"\u06d5\7\u00cf\2\2\u06d5\u06d6\5\u00fe\u0080\2\u06d6\u06d7\7\u00d0\2\2"+
		"\u06d7\u06da\3\2\2\2\u06d8\u06da\5\u00b8]\2\u06d9\u06d2\3\2\2\2\u06d9"+
		"\u06d3\3\2\2\2\u06d9\u06d8\3\2\2\2\u06da\u0101\3\2\2\2\u06db\u06dc\t\23"+
		"\2\2\u06dc\u0103\3\2\2\2\u06dd\u06de\t\24\2\2\u06de\u0105\3\2\2\2\u06df"+
		"\u06e0\t\25\2\2\u06e0\u0107\3\2\2\2\u06e1\u06e2\t\26\2\2\u06e2\u0109\3"+
		"\2\2\2\u06e3\u06e4\t\27\2\2\u06e4\u010b\3\2\2\2\u06e5\u06e6\t\30\2\2\u06e6"+
		"\u010d\3\2\2\2\u06e7\u06e8\t\31\2\2\u06e8\u010f\3\2\2\2\u00d7\u0111\u0116"+
		"\u011e\u0123\u013d\u0141\u0146\u014b\u0151\u015e\u0162\u0168\u016d\u0172"+
		"\u0179\u017f\u0186\u018d\u0192\u0196\u019d\u01a0\u01a6\u01a9\u01ac\u01b1"+
		"\u01b5\u01b9\u01be\u01c2\u01c4\u01ca\u01d6\u01e7\u01ee\u01f6\u0201\u0209"+
		"\u0211\u0218\u021f\u0236\u023d\u0245\u024f\u0255\u0272\u0280\u0284\u028d"+
		"\u0291\u0296\u02b0\u02bb\u02bf\u02c3\u02cc\u02d6\u02db\u02e1\u02e6\u02eb"+
		"\u02f0\u02f5\u02fb\u0301\u0307\u0319\u031e\u0321\u032b\u032e\u033b\u0343"+
		"\u034a\u034d\u0352\u0356\u035f\u037b\u0380\u0384\u038b\u038f\u0395\u039a"+
		"\u039d\u03a0\u03a3\u03aa\u03b2\u03bf\u03cb\u03d3\u03da\u03e1\u03e5\u03ec"+
		"\u03f3\u03f7\u03fb\u0401\u0409\u0410\u0414\u0418\u0425\u0428\u042f\u0436"+
		"\u043a\u043e\u0443\u0448\u0455\u045f\u0465\u04a4\u04b4\u04b6\u04dc\u04f2"+
		"\u04f4\u04f9\u04fe\u0503\u0506\u050c\u050f\u0513\u0519\u051d\u0528\u052d"+
		"\u0535\u0539\u0540\u0544\u0549\u054e\u0552\u055b\u0561\u0567\u056a\u056e"+
		"\u0571\u0576\u057a\u057d\u0580\u0584\u0588\u058c\u0591\u0595\u0597\u059d"+
		"\u05a7\u05ab\u05ae\u05b8\u05bd\u05bf\u05c4\u05cc\u05d0\u05d4\u05db\u05e4"+
		"\u05e8\u05eb\u05f0\u05f5\u05fd\u05ff\u0605\u0607\u060c\u0613\u0619\u0620"+
		"\u0626\u0630\u0635\u063a\u063c\u0644\u064a\u0651\u0655\u065a\u0661\u0668"+
		"\u0671\u0676\u067f\u0682\u0688\u068e\u0694\u0698\u069f\u06a4\u06aa\u06b3"+
		"\u06b8\u06bd\u06c4\u06c7\u06cb\u06cf\u06d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}