/*******************************************************************************
 * Copyright (c) 2018-2019 Zend Corporation and IBM Corporation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Zend and IBM - Initial implementation
 *******************************************************************************/

package org.eclipse.php.internal.core.ast.scanner.php73;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.AST;
import org.eclipse.php.core.ast.nodes.Comment;
import org.eclipse.php.core.ast.nodes.IDocumentorLexer;
import org.eclipse.php.internal.core.util.collections.StateStack;

import java_cup.sym;
import java_cup.runtime.Symbol;

@SuppressWarnings({"unused", "nls"})

%%

%class PHPAstLexer
%public
%unicode
%line

/* %cup */
%implements org.eclipse.php.internal.core.ast.scanner.AstLexer
%function next_token
%type java_cup.runtime.Symbol
%eofval{
	return createSymbol(sym.EOF);
%eofval}
%eofclose

%caseless

%standalone
%state ST_IN_SCRIPTING
%state ST_DOUBLE_QUOTES
%state ST_SINGLE_QUOTE
%state ST_BACKQUOTE
%state ST_HEREDOC
%state ST_NOWDOC
%state ST_START_HEREDOC
%state ST_START_NOWDOC
%state ST_END_HEREDOC
%state ST_LOOKING_FOR_PROPERTY
%state ST_LOOKING_FOR_VARNAME
%state ST_VAR_OFFSET
%state ST_COMMENT
%state ST_DOCBLOCK
%state ST_ONE_LINE_COMMENT
%{
	private final LinkedList<Object> commentList = new LinkedList<>();
	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=514632
	// stores nested HEREDOC and NOWDOC ids
	private final Stack<String> heredocIds = new Stack<>();
	private boolean asp_tags = false;
	private boolean short_tags_allowed = true;
	// XXX: "heredocIds" and "stack" are never reset
	private final StateStack stack = new StateStack();
	protected int commentStartPosition;
	protected String lastHeredocIndentation;

	private AST ast;

	public void setAST(AST ast) {
		this.ast = ast;
	}

	@Override
	public PHPVersion getPHPVersion() {
		return PHPVersion.PHP7_3;
	}

	@Override
	public void setInScriptingState() {
		yybegin(ST_IN_SCRIPTING);
	}

	@Override
	public void resetCommentList() {
		commentList.clear();
	}

	/**
	 * Will only be filled when ast != null
	 */
	@Override
	public LinkedList<Object> getCommentList() {
		return commentList;
	}

	/**
	 * Will only be added when ast != null
	 */
	protected void addComment(int type) {
		if (ast != null) {
			int leftPosition = getTokenStartPosition();
			Comment comment = new Comment(commentStartPosition, leftPosition + getTokenLength(), ast, type);
			commentList.add(comment);
		}
	}

	@Override
	public void setUseAspTagsAsPHP(boolean useAspTagsAsPhp) {
		asp_tags = useAspTagsAsPhp;
	}

	@Override
	public void setUseShortTags(boolean useShortTags) {
		short_tags_allowed = useShortTags;
	}

	private void pushState(int state) {
		stack.pushStack(zzLexicalState);
		yybegin(state);
	}

	private void popState() {
		yybegin(stack.popStack());
	}

	@Override
	public int getCurrentLine() {
		return yyline;
	}

	protected int getTokenStartPosition() {
		return zzStartRead - _zzPushbackPos;
	}

	protected int getTokenLength() {
		return zzMarkedPos - zzStartRead;
	}

	@Override
	public int getLength() {
		return zzEndRead - _zzPushbackPos;
	}

	private void handleCommentStart() {
		commentStartPosition = getTokenStartPosition();
	}

	private void handleLineCommentEnd() {
		addComment(Comment.TYPE_SINGLE_LINE);
	}

	private void handleMultilineCommentEnd() {
		addComment(Comment.TYPE_MULTILINE);
	}

	private void handlePHPDocEnd() {
		addComment(Comment.TYPE_PHPDOC);
	}

	protected void handleVarComment() {
		addComment(Comment.TYPE_MULTILINE);
	}

	protected Symbol createFullSymbol(int symbolNumber) {
		Symbol symbol = createSymbol(symbolNumber);
		symbol.value = yytext();
		return symbol;
	}

	protected Symbol createSymbol(int symbolNumber) {
		int leftPosition = getTokenStartPosition();
		return new Symbol(symbolNumber, leftPosition, leftPosition + getTokenLength());
	}

	public int[] getParameters() {
		return new int[] { zzMarkedPos, _zzPushbackPos, zzCurrentPos,
				zzStartRead, zzEndRead, yyline, zzAtBOL ? 1 : 0,
				zzAtEOF ? 1 : 0, zzEOFDone ? 1 : 0, zzFinalHighSurrogate };
	}

	/**
	 * Parses a PHPDoc block comment. Underlying reader (zzReader) can be closed
	 * in the process (when EOF is reached).
	 *
	 * @return true when PHPDoc was parsed, false otherwise (false also implies
	 *         that underlying lexer was unavailable)
	 */
	protected boolean parsePHPDoc() {
		final IDocumentorLexer documentorLexer = getDocumentorLexer(zzReader);
		if (documentorLexer == null) {
			return false;
		}
		yypushback(zzMarkedPos - zzStartRead);
		int[] parameters = getParameters();
		documentorLexer.reset(zzReader, zzBuffer, parameters);
		Object phpDocBlock = documentorLexer.parse();
		commentList.add(phpDocBlock);
		reset(zzReader, documentorLexer.getBuffer(), documentorLexer.getParameters());
		return true;
	}

	protected IDocumentorLexer getDocumentorLexer(java.io.Reader reader) {
		return null;
	}

	/**
	 * Resets the {@code PhpAstLexer} properties to previous values, but leaves
	 * the lexical state unchanged. Be careful, method {@link #next_token()}
	 * also caches those properties using internal variables (zzCurrentPosL,
	 * zzMarkedPosL, zzBufferL, zzEndReadL) that should be accordingly resetted
	 * by the lexical rules calling
	 * {@link #reset(java.io.Reader, char[], int[])}. Also be careful that those
	 * internal variables could change from one version of JFlex to another.
	 *
	 * @param reader
	 * @param buffer
	 * @param parameters
	 */
	public void reset(java.io.Reader reader, char[] buffer, int[] parameters) {
		this.zzReader = reader;
		this.zzBuffer = buffer;
		this.zzMarkedPos = parameters[0];
		this._zzPushbackPos = parameters[1];
		this.zzCurrentPos = parameters[2];
		this.zzStartRead = parameters[3];
		this.zzEndRead = parameters[4];
		this.yyline = parameters[5];
		this.yychar = this.zzStartRead - this._zzPushbackPos;
		// XXX: never used
		this.yycolumn = 0;
		this.zzAtEOF = parameters[7] != 0;
		this.zzEOFDone = parameters[8] != 0;
		this.zzFinalHighSurrogate = parameters[9];
		// XXX: check if there's no side-effect to reset zzAtBOL
		// when zzAtEOF is false and zzMarkedPos is equal to zzStartRead.
		// One possible case would be that IDocumentorLexer#parse() matches
		// no text at all without being at EOF (i.e. the document lexer returns
		// a zero-length token), which of course would be totally broken...
		this.zzAtBOL = this.zzAtEOF ? false : (parameters[6] != 0);
	}
%}

LNUM=[0-9]+
DNUM=([0-9]*"."[0-9]+)|([0-9]+"."[0-9]*)
EXPONENT_DNUM=(({LNUM}|{DNUM})[eE][+-]?{LNUM})
HNUM="0x"[0-9a-fA-F]+
BNUM="0b"[01]+
LABEL=[a-zA-Z_\u0080-\uffff][a-zA-Z0-9_\u0080-\uffff]*
WHITESPACES=[ \n\r\t]+
TABS_AND_SPACES=[ \t]*
ANY_CHAR=[^]
NEWLINE=("\r"|"\n"|"\r\n")
DOUBLE_QUOTES_LITERAL_DOLLAR=("$"+([^a-zA-Z_\u0080-\uffff$\"\\{]|("\\"{ANY_CHAR})))
BACKQUOTE_LITERAL_DOLLAR=("$"+([^a-zA-Z_\u0080-\uffff$`\\{]|("\\"{ANY_CHAR})))
HEREDOC_LITERAL_DOLLAR=("$"+([^a-zA-Z_\u0080-\uffff$\n\r\\{]|("\\"[^\n\r])))
HEREDOC_NEWLINE=((("{"*|"$"*)"\\"?){NEWLINE})
HEREDOC_CURLY_OR_ESCAPE_OR_DOLLAR=(("{"+[^$\n\r\\{])|("{"*"\\"[^\n\r])|{HEREDOC_LITERAL_DOLLAR})
HEREDOC_NON_LABEL=([^a-zA-Z_\u0080-\uffff$\n\r \t\\{]|{HEREDOC_CURLY_OR_ESCAPE_OR_DOLLAR})
DOUBLE_QUOTES_CHARS=("{"*([^$\"\\{]|("\\"{ANY_CHAR}))|{DOUBLE_QUOTES_LITERAL_DOLLAR})
BACKQUOTE_CHARS=("{"*([^$`\\{]|("\\"{ANY_CHAR}))|{BACKQUOTE_LITERAL_DOLLAR})
HEREDOC_CHARS=("{"*([^$\n\r\\{]|("\\"[^\n\r]))|{HEREDOC_LITERAL_DOLLAR}|(({HEREDOC_NEWLINE}{TABS_AND_SPACES})+{HEREDOC_NON_LABEL}))
NOWDOC_CHARS=([^\n\r]|({NEWLINE}{TABS_AND_SPACES})+[^a-zA-Z_\u0080-\uffff\n\r \t])

%%

<ST_IN_SCRIPTING>"exit" {
	return createFullSymbol(ParserConstants.T_EXIT);
}

<ST_IN_SCRIPTING>"die" {
	return createFullSymbol(ParserConstants.T_EXIT);
}

<ST_IN_SCRIPTING>"function" {
	return createFullSymbol(ParserConstants.T_FUNCTION);
}

<ST_IN_SCRIPTING>"const" {
	return createFullSymbol(ParserConstants.T_CONST);
}

<ST_IN_SCRIPTING>"return" {
	return createFullSymbol(ParserConstants.T_RETURN);
}

<ST_IN_SCRIPTING>"yield" {
	return createFullSymbol(ParserConstants.T_YIELD);
}

<ST_IN_SCRIPTING>"yield"{WHITESPACES}"from" {
	return createSymbol(ParserConstants.T_YIELD_FROM);
}

<ST_IN_SCRIPTING>"try" {
	return createFullSymbol(ParserConstants.T_TRY);
}

<ST_IN_SCRIPTING>"catch" {
	return createFullSymbol(ParserConstants.T_CATCH);
}

<ST_IN_SCRIPTING>"throw" {
	return createFullSymbol(ParserConstants.T_THROW);
}

<ST_IN_SCRIPTING>"finally" {
	return createFullSymbol(ParserConstants.T_FINALLY);
}

<ST_IN_SCRIPTING>"if" {
	return createFullSymbol(ParserConstants.T_IF);
}

<ST_IN_SCRIPTING>"elseif" {
	return createFullSymbol(ParserConstants.T_ELSEIF);
}

<ST_IN_SCRIPTING>"endif" {
	return createFullSymbol(ParserConstants.T_ENDIF);
}

<ST_IN_SCRIPTING>"else" {
	return createFullSymbol(ParserConstants.T_ELSE);
}

<ST_IN_SCRIPTING>"while" {
	return createFullSymbol(ParserConstants.T_WHILE);
}

<ST_IN_SCRIPTING>"endwhile" {
	return createFullSymbol(ParserConstants.T_ENDWHILE);
}

<ST_IN_SCRIPTING>"do" {
	return createFullSymbol(ParserConstants.T_DO);
}

<ST_IN_SCRIPTING>"for" {
	return createFullSymbol(ParserConstants.T_FOR);
}

<ST_IN_SCRIPTING>"endfor" {
	return createFullSymbol(ParserConstants.T_ENDFOR);
}

<ST_IN_SCRIPTING>"foreach" {
	return createFullSymbol(ParserConstants.T_FOREACH);
}

<ST_IN_SCRIPTING>"endforeach" {
	return createFullSymbol(ParserConstants.T_ENDFOREACH);
}

<ST_IN_SCRIPTING>"declare" {
	return createFullSymbol(ParserConstants.T_DECLARE);
}

<ST_IN_SCRIPTING>"enddeclare" {
	return createFullSymbol(ParserConstants.T_ENDDECLARE);
}

<ST_IN_SCRIPTING>"instanceof" {
	return createFullSymbol(ParserConstants.T_INSTANCEOF);
}

<ST_IN_SCRIPTING>"as" {
	return createFullSymbol(ParserConstants.T_AS);
}

<ST_IN_SCRIPTING>"switch" {
	return createFullSymbol(ParserConstants.T_SWITCH);
}

<ST_IN_SCRIPTING>"endswitch" {
	return createFullSymbol(ParserConstants.T_ENDSWITCH);
}

<ST_IN_SCRIPTING>"case" {
	return createFullSymbol(ParserConstants.T_CASE);
}

<ST_IN_SCRIPTING>"default" {
	return createFullSymbol(ParserConstants.T_DEFAULT);
}

<ST_IN_SCRIPTING>"break" {
	return createFullSymbol(ParserConstants.T_BREAK);
}

<ST_IN_SCRIPTING>"continue" {
	return createFullSymbol(ParserConstants.T_CONTINUE);
}

<ST_IN_SCRIPTING>"goto" {
	return createFullSymbol(ParserConstants.T_GOTO);
}

<ST_IN_SCRIPTING>"echo" {
	return createFullSymbol(ParserConstants.T_ECHO);
}

<ST_IN_SCRIPTING>"print" {
	return createFullSymbol(ParserConstants.T_PRINT);
}

<ST_IN_SCRIPTING>"class" {
	return createFullSymbol(ParserConstants.T_CLASS);
}

<ST_IN_SCRIPTING>"interface" {
	return createFullSymbol(ParserConstants.T_INTERFACE);
}

<ST_IN_SCRIPTING>"trait" {
	return createFullSymbol(ParserConstants.T_TRAIT);
}

<ST_IN_SCRIPTING>"insteadof" {
	return createFullSymbol(ParserConstants.T_INSTEADOF);
}

<ST_IN_SCRIPTING>"callable" {
	return createFullSymbol(ParserConstants.T_CALLABLE);
}

<ST_IN_SCRIPTING>"extends" {
	return createFullSymbol(ParserConstants.T_EXTENDS);
}

<ST_IN_SCRIPTING>"implements" {
	return createFullSymbol(ParserConstants.T_IMPLEMENTS);
}

<ST_IN_SCRIPTING>"->" {
	pushState(ST_LOOKING_FOR_PROPERTY);
	return createSymbol(ParserConstants.T_OBJECT_OPERATOR);
}

<ST_IN_SCRIPTING,ST_LOOKING_FOR_PROPERTY>{WHITESPACES} {
}

<ST_LOOKING_FOR_PROPERTY>"->" {
	return createSymbol(ParserConstants.T_OBJECT_OPERATOR);
}

<ST_LOOKING_FOR_PROPERTY>{LABEL} {
	popState();
	return createFullSymbol(ParserConstants.T_STRING);
}

<ST_LOOKING_FOR_PROPERTY>{ANY_CHAR} {
	yypushback(1);
	popState();
}

<ST_IN_SCRIPTING>"::" {
	return createSymbol(ParserConstants.T_PAAMAYIM_NEKUDOTAYIM);
}

<ST_IN_SCRIPTING>"\\" {
	return createSymbol(ParserConstants.T_NS_SEPARATOR);
}

<ST_IN_SCRIPTING>"new" {
	return createFullSymbol(ParserConstants.T_NEW);
}

<ST_IN_SCRIPTING>"clone" {
	return createFullSymbol(ParserConstants.T_CLONE);
}

<ST_IN_SCRIPTING>"var" {
	return createFullSymbol(ParserConstants.T_VAR);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("int"|"integer"){TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_INT_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("real"|"double"|"float"){TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_DOUBLE_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"string"{TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_STRING_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"binary"{TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_STRING_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"array"{TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_ARRAY_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}"object"{TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_OBJECT_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("bool"|"boolean"){TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_BOOL_CAST);
}

<ST_IN_SCRIPTING>"("{TABS_AND_SPACES}("unset"){TABS_AND_SPACES}")" {
	return createSymbol(ParserConstants.T_UNSET_CAST);
}

<ST_IN_SCRIPTING>"eval" {
	return createFullSymbol(ParserConstants.T_EVAL);
}

<ST_IN_SCRIPTING>"include" {
	return createFullSymbol(ParserConstants.T_INCLUDE);
}

<ST_IN_SCRIPTING>"include_once" {
	return createFullSymbol(ParserConstants.T_INCLUDE_ONCE);
}

<ST_IN_SCRIPTING>"require" {
	return createFullSymbol(ParserConstants.T_REQUIRE);
}

<ST_IN_SCRIPTING>"require_once" {
	return createFullSymbol(ParserConstants.T_REQUIRE_ONCE);
}

<ST_IN_SCRIPTING>"namespace" {
	return createFullSymbol(ParserConstants.T_NAMESPACE);
}

<ST_IN_SCRIPTING>"use" {
	return createFullSymbol(ParserConstants.T_USE);
}

<ST_IN_SCRIPTING>"global" {
	return createFullSymbol(ParserConstants.T_GLOBAL);
}

<ST_IN_SCRIPTING>"isset" {
	return createFullSymbol(ParserConstants.T_ISSET);
}

<ST_IN_SCRIPTING>"empty" {
	return createFullSymbol(ParserConstants.T_EMPTY);
}

<ST_IN_SCRIPTING>"__halt_compiler" {
	return createSymbol(ParserConstants.T_HALT_COMPILER);
}
<ST_IN_SCRIPTING>"static" {
	return createFullSymbol(ParserConstants.T_STATIC);
}

<ST_IN_SCRIPTING>"abstract" {
	return createFullSymbol(ParserConstants.T_ABSTRACT);
}

<ST_IN_SCRIPTING>"final" {
	return createFullSymbol(ParserConstants.T_FINAL);
}

<ST_IN_SCRIPTING>"private" {
	return createFullSymbol(ParserConstants.T_PRIVATE);
}

<ST_IN_SCRIPTING>"protected" {
	return createFullSymbol(ParserConstants.T_PROTECTED);
}

<ST_IN_SCRIPTING>"public" {
	return createFullSymbol(ParserConstants.T_PUBLIC);
}

<ST_IN_SCRIPTING>"unset" {
	return createFullSymbol(ParserConstants.T_UNSET);
}

<ST_IN_SCRIPTING>"=>" {
	return createSymbol(ParserConstants.T_DOUBLE_ARROW);
}

<ST_IN_SCRIPTING>"list" {
	return createFullSymbol(ParserConstants.T_LIST);
}

<ST_IN_SCRIPTING>"array" {
	return createFullSymbol(ParserConstants.T_ARRAY);
}

<ST_IN_SCRIPTING>"++" {
	return createSymbol(ParserConstants.T_INC);
}

<ST_IN_SCRIPTING>"--" {
	return createSymbol(ParserConstants.T_DEC);
}

<ST_IN_SCRIPTING>"===" {
	return createSymbol(ParserConstants.T_IS_IDENTICAL);
}

<ST_IN_SCRIPTING>"!==" {
	return createSymbol(ParserConstants.T_IS_NOT_IDENTICAL);
}

<ST_IN_SCRIPTING>"==" {
	return createSymbol(ParserConstants.T_IS_EQUAL);
}

<ST_IN_SCRIPTING>"!="|"<>" {
	return createSymbol(ParserConstants.T_IS_NOT_EQUAL);
}

<ST_IN_SCRIPTING>"<=" {
	return createSymbol(ParserConstants.T_IS_SMALLER_OR_EQUAL);
}

<ST_IN_SCRIPTING>">=" {
	return createSymbol(ParserConstants.T_IS_GREATER_OR_EQUAL);
}

<ST_IN_SCRIPTING>"+=" {
	return createSymbol(ParserConstants.T_PLUS_EQUAL);
}

<ST_IN_SCRIPTING>"-=" {
	return createSymbol(ParserConstants.T_MINUS_EQUAL);
}

<ST_IN_SCRIPTING>"**=" {
	return createSymbol(ParserConstants.T_POW_EQUAL);
}

<ST_IN_SCRIPTING>"*=" {
	return createSymbol(ParserConstants.T_MUL_EQUAL);
}

<ST_IN_SCRIPTING>"/=" {
	return createSymbol(ParserConstants.T_DIV_EQUAL);
}

<ST_IN_SCRIPTING>".=" {
	return createSymbol(ParserConstants.T_CONCAT_EQUAL);
}

<ST_IN_SCRIPTING>"%=" {
	return createSymbol(ParserConstants.T_MOD_EQUAL);
}

<ST_IN_SCRIPTING>"<<=" {
	return createSymbol(ParserConstants.T_SL_EQUAL);
}

<ST_IN_SCRIPTING>">>=" {
	return createSymbol(ParserConstants.T_SR_EQUAL);
}

<ST_IN_SCRIPTING>"&=" {
	return createSymbol(ParserConstants.T_AND_EQUAL);
}

<ST_IN_SCRIPTING>"|=" {
	return createSymbol(ParserConstants.T_OR_EQUAL);
}

<ST_IN_SCRIPTING>"^=" {
	return createSymbol(ParserConstants.T_XOR_EQUAL);
}

<ST_IN_SCRIPTING>"||" {
	return createSymbol(ParserConstants.T_BOOLEAN_OR);
}

<ST_IN_SCRIPTING>"&&" {
	return createSymbol(ParserConstants.T_BOOLEAN_AND);
}

<ST_IN_SCRIPTING>"OR" {
	return createFullSymbol(ParserConstants.T_LOGICAL_OR);
}

<ST_IN_SCRIPTING>"AND" {
	return createFullSymbol(ParserConstants.T_LOGICAL_AND);
}

<ST_IN_SCRIPTING>"XOR" {
	return createFullSymbol(ParserConstants.T_LOGICAL_XOR);
}

<ST_IN_SCRIPTING>"<<" {
	return createSymbol(ParserConstants.T_SL);
}

<ST_IN_SCRIPTING>">>" {
	return createSymbol(ParserConstants.T_SR);
}

<ST_IN_SCRIPTING>"..." {
	return createSymbol(ParserConstants.T_ELLIPSIS);
}

<ST_IN_SCRIPTING>"<=>" {
	return createSymbol(ParserConstants.T_SPACESHIP);
}

<ST_IN_SCRIPTING>"??" {
	return createSymbol(ParserConstants.T_COALESCE);
}

// TOKENS
<ST_IN_SCRIPTING> {
	";"                     {return createSymbol(ParserConstants.T_SEMICOLON);}
	":"                     {return createSymbol(ParserConstants.T_NEKUDOTAIM);}
	","                     {return createSymbol(ParserConstants.T_COMMA);}
	"."                     {return createSymbol(ParserConstants.T_NEKUDA);}
	"["                     {return createSymbol(ParserConstants.T_OPEN_RECT);}
	"]"                     {return createSymbol(ParserConstants.T_CLOSE_RECT);}
	"("                     {return createSymbol(ParserConstants.T_OPEN_PARENTHESE);}
	")"                     {return createSymbol(ParserConstants.T_CLOSE_PARENTHESE);}
	"|"                     {return createSymbol(ParserConstants.T_OR);}
	"^"                     {return createSymbol(ParserConstants.T_KOVA);}
	"&"                     {return createSymbol(ParserConstants.T_REFERENCE);}
	"+"                     {return createSymbol(ParserConstants.T_PLUS);}
	"-"                     {return createSymbol(ParserConstants.T_MINUS);}
	"/"                     {return createSymbol(ParserConstants.T_DIV);}
	"**"                    {return createSymbol(ParserConstants.T_POW);}
	"*"                     {return createSymbol(ParserConstants.T_TIMES);}
	"="                     {return createSymbol(ParserConstants.T_EQUAL);}
	"%"                     {return createSymbol(ParserConstants.T_PRECENT);}
	"!"                     {return createSymbol(ParserConstants.T_NOT);}
	"~"                     {return createSymbol(ParserConstants.T_TILDA);}
	"$"                     {return createSymbol(ParserConstants.T_DOLLAR);}
	"<"                     {return createSymbol(ParserConstants.T_RGREATER);}
	">"                     {return createSymbol(ParserConstants.T_LGREATER);}
	"?"                     {return createSymbol(ParserConstants.T_QUESTION_MARK);}
	"@"                     {return createSymbol(ParserConstants.T_AT);}
}

<ST_IN_SCRIPTING>"{" {
	pushState(ST_IN_SCRIPTING);
	return createSymbol(ParserConstants.T_CURLY_OPEN);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"${" {
	pushState(ST_LOOKING_FOR_VARNAME);
	return createSymbol(ParserConstants.T_DOLLAR_OPEN_CURLY_BRACES);
}

<ST_IN_SCRIPTING>"}" {
	if (!stack.isEmpty()) {
		popState();
	}
	return createSymbol(ParserConstants.T_CURLY_CLOSE);
}

<ST_LOOKING_FOR_VARNAME>{LABEL} {
	yybegin(ST_IN_SCRIPTING);
	return createFullSymbol(ParserConstants.T_STRING_VARNAME);
}

<ST_LOOKING_FOR_VARNAME>{ANY_CHAR} {
	yypushback(1);
	yybegin(ST_IN_SCRIPTING);
}

<ST_IN_SCRIPTING>{BNUM} {
	return createFullSymbol(ParserConstants.T_BNUMBER);
}

<ST_IN_SCRIPTING>{LNUM} {
	return createFullSymbol(ParserConstants.T_LNUMBER);
}

<ST_IN_SCRIPTING>{HNUM} {
	return createFullSymbol(ParserConstants.T_DNUMBER);
}

<ST_VAR_OFFSET>{LNUM}|{HNUM}|{BNUM} { /* treat numbers (almost) as strings inside encapsulated strings */
	return createFullSymbol(ParserConstants.T_NUM_STRING);
}

<ST_IN_SCRIPTING>{DNUM}|{EXPONENT_DNUM} {
	return createFullSymbol(ParserConstants.T_DNUMBER);
}

<ST_IN_SCRIPTING>"__CLASS__" {
	return createSymbol(ParserConstants.T_CLASS_C);
}

<ST_IN_SCRIPTING>"__FUNCTION__" {
	return createSymbol(ParserConstants.T_FUNC_C);
}

<ST_IN_SCRIPTING>"__METHOD__" {
	return createSymbol(ParserConstants.T_METHOD_C);
}

<ST_IN_SCRIPTING>"__LINE__" {
	return createSymbol(ParserConstants.T_LINE);
}

<ST_IN_SCRIPTING>"__FILE__" {
	return createSymbol(ParserConstants.T_FILE);
}

<ST_IN_SCRIPTING>"__DIR__" {
	return createSymbol(ParserConstants.T_DIR);
}

<ST_IN_SCRIPTING>"__NAMESPACE__" {
	return createSymbol(ParserConstants.T_NS_C);
}

<YYINITIAL>(([^<]|"<"[^?%<])+)|"<" {
	return createSymbol(ParserConstants.T_INLINE_HTML);
}

<YYINITIAL>"<?" {
	if (short_tags_allowed) {
		yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG;
	} else {
		return createSymbol(ParserConstants.T_INLINE_HTML);
	}
}

<YYINITIAL>"<%="|"<?=" {
	String yytext = yytext();
	if ((yytext.charAt(1) == '%' && asp_tags)
		|| (yytext.charAt(1) == '?')) {
		yybegin(ST_IN_SCRIPTING);
		return createSymbol(ParserConstants.T_OPEN_TAG_WITH_ECHO);
	} else {
		return createSymbol(ParserConstants.T_INLINE_HTML);
	}
}

<YYINITIAL>"<%" {
	if (asp_tags) {
		yybegin(ST_IN_SCRIPTING);
		//return T_OPEN_TAG;
	} else {
		return createSymbol(ParserConstants.T_INLINE_HTML);
	}
}

<YYINITIAL>"<?php"([ \t]|{NEWLINE}) {
	yybegin(ST_IN_SCRIPTING);
	//return T_OPEN_TAG;
}

<ST_IN_SCRIPTING,ST_DOUBLE_QUOTES,ST_HEREDOC,ST_BACKQUOTE,ST_VAR_OFFSET>"$"{LABEL} {
	return createFullSymbol(ParserConstants.T_VARIABLE);
}

<ST_DOUBLE_QUOTES,ST_HEREDOC,ST_BACKQUOTE>"$"{LABEL}"->"[a-zA-Z_\u0080-\uffff] {
	yypushback(3);
	pushState(ST_LOOKING_FOR_PROPERTY);
	return createFullSymbol(ParserConstants.T_VARIABLE);
}

<ST_DOUBLE_QUOTES,ST_HEREDOC,ST_BACKQUOTE>"$"{LABEL}"[" {
	yypushback(1);
	pushState(ST_VAR_OFFSET);
	return createFullSymbol(ParserConstants.T_VARIABLE);
}

<ST_VAR_OFFSET>"]" {
	popState();
	return createSymbol(ParserConstants.T_CLOSE_RECT);
}

//this is instead {TOKENS}|[{}"`]
<ST_VAR_OFFSET> {
	";"                     {return createSymbol(ParserConstants.T_SEMICOLON);}
	":"                     {return createSymbol(ParserConstants.T_NEKUDOTAIM);}
	","                     {return createSymbol(ParserConstants.T_COMMA);}
	"."                     {return createSymbol(ParserConstants.T_NEKUDA);}
	"["                     {return createSymbol(ParserConstants.T_OPEN_RECT);}
//	"]"                     {return createSymbol(ParserConstants.T_CLOSE_RECT);} //we dont need this line because the rule before deals with it
	"("                     {return createSymbol(ParserConstants.T_OPEN_PARENTHESE);}
	")"                     {return createSymbol(ParserConstants.T_CLOSE_PARENTHESE);}
	"|"                     {return createSymbol(ParserConstants.T_OR);}
	"^"                     {return createSymbol(ParserConstants.T_KOVA);}
	"&"                     {return createSymbol(ParserConstants.T_REFERENCE);}
	"+"                     {return createSymbol(ParserConstants.T_PLUS);}
	"-"                     {return createSymbol(ParserConstants.T_MINUS);}
	"/"                     {return createSymbol(ParserConstants.T_DIV);}
	"**"                    {return createSymbol(ParserConstants.T_POW);}
	"*"                     {return createSymbol(ParserConstants.T_TIMES);}
	"="                     {return createSymbol(ParserConstants.T_EQUAL);}
	"%"                     {return createSymbol(ParserConstants.T_PRECENT);}
	"!"                     {return createSymbol(ParserConstants.T_NOT);}
	"~"                     {return createSymbol(ParserConstants.T_TILDA);}
	"$"                     {return createSymbol(ParserConstants.T_DOLLAR);}
	"<"                     {return createSymbol(ParserConstants.T_RGREATER);}
	">"                     {return createSymbol(ParserConstants.T_LGREATER);}
	"?"                     {return createSymbol(ParserConstants.T_QUESTION_MARK);}
	"@"                     {return createSymbol(ParserConstants.T_AT);}
	"{"                     {return createSymbol(ParserConstants.T_CURLY_OPEN);}
	"}"                     {return createSymbol(ParserConstants.T_CURLY_CLOSE);}
	"\""                    {return createSymbol(ParserConstants.T_QUATE);}
	"`"                     {return createSymbol(ParserConstants.T_BACKQUATE);}
}

<ST_VAR_OFFSET>[ \n\r\t\\'#] {
	yypushback(1);
	popState();
	return createSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_IN_SCRIPTING>"define" {
	/* not a keyword, used to recognize constants.*/
	return createFullSymbol(ParserConstants.T_DEFINE);
}

<ST_IN_SCRIPTING,ST_VAR_OFFSET>{LABEL} {
	return createFullSymbol(ParserConstants.T_STRING);
}

<ST_IN_SCRIPTING>{WHITESPACES} {
}

<ST_IN_SCRIPTING>"#"|"//" {
	handleCommentStart();
	yybegin(ST_ONE_LINE_COMMENT);
	// yymore();
}

<ST_ONE_LINE_COMMENT>"?"|"%"|">" {
	// yymore();
}

<ST_ONE_LINE_COMMENT>[^\n\r?%>]*(.|{NEWLINE}) {
	String yytext = yytext();
	switch (yytext.charAt(yytext.length() - 1)) {
		case '?':
		case '%':
		case '>':
			yypushback(1);
			break;
		default:
			handleLineCommentEnd();
			yybegin(ST_IN_SCRIPTING);
	}
	// yymore();
}

<ST_ONE_LINE_COMMENT>"?>"|"%>" {
	if (asp_tags || yytext().charAt(0) != '%') { /* asp comment? */
		handleLineCommentEnd();
		yypushback(yylength());
		yybegin(ST_IN_SCRIPTING);
	}
}

<ST_IN_SCRIPTING>"/*"{WHITESPACES}?"@var"{WHITESPACES}"$"{LABEL}{WHITESPACES}("\\"|{LABEL}|"["|"]"|"|")+{WHITESPACES}?"*/" {
	handleCommentStart();
	handleVarComment();
	//return createFullSymbol(ParserConstants.T_VAR_COMMENT);
}

<ST_IN_SCRIPTING>"/*"{WHITESPACES}?"@var"{WHITESPACES}("\\"|{LABEL}|"["|"]"|"|")+{WHITESPACES}"$"{LABEL}{WHITESPACES}?"*/" {
	handleCommentStart();
	handleVarComment();
	//return createFullSymbol(ParserConstants.T_VAR_COMMENT);
}

<ST_IN_SCRIPTING>"/**/" {
	handleCommentStart();
	handleMultilineCommentEnd();
}

<ST_IN_SCRIPTING>"/**" {
if (parsePHPDoc()) {
	// https://bugs.eclipse.org/bugs/show_bug.cgi?id=316077
	// Reset the internal variables caching the values
	// previously resetted by parsePHPDoc().
	// Actually it would be enough to only reset zzEndReadL,
	// but do it cleanly...
	// Also be careful that those internal variables could
	// change from one version of JFlex to another.
	zzCurrentPosL = zzCurrentPos;
	zzMarkedPosL = zzMarkedPos;
	zzBufferL = zzBuffer;
	zzEndReadL = zzEndRead;
} else {
	handleCommentStart();
	yybegin(ST_DOCBLOCK);
}
}

<ST_DOCBLOCK>"*/" {
	handlePHPDocEnd();
	yybegin(ST_IN_SCRIPTING);
}

<ST_DOCBLOCK>{NEWLINE} {
}

<ST_DOCBLOCK>{ANY_CHAR} {
}

<ST_IN_SCRIPTING>"/*" {
	handleCommentStart();
	yybegin(ST_COMMENT);
}

<ST_COMMENT>[^*]+ {
}

<ST_COMMENT>"*/" {
	handleMultilineCommentEnd();
	yybegin(ST_IN_SCRIPTING);
}

<ST_COMMENT>"*" {
	// yymore();
}

<ST_IN_SCRIPTING>("?>"|"</script"{WHITESPACES}?">") {
	yybegin(YYINITIAL);
	return createSymbol(ParserConstants.T_SEMICOLON); /* implicit ';' at php-end tag */
}

<ST_IN_SCRIPTING>"%>" {
	if (asp_tags) {
		yybegin(YYINITIAL);
		return createSymbol(ParserConstants.T_SEMICOLON); /* implicit ';' at php-end tag */
	} else {
		return createSymbol(ParserConstants.T_INLINE_HTML);
	}
}

<ST_IN_SCRIPTING>(b?[\"]{DOUBLE_QUOTES_CHARS}*("{"*|"$"*)[\"]) {
	return createFullSymbol(ParserConstants.T_CONSTANT_ENCAPSED_STRING);
}

<ST_IN_SCRIPTING>(b?[']([^'\\]|("\\"{ANY_CHAR}))*[']) {
	return createFullSymbol(ParserConstants.T_CONSTANT_ENCAPSED_STRING);
}

<ST_IN_SCRIPTING>b?[\"] {
	yybegin(ST_DOUBLE_QUOTES);
	return createSymbol(ParserConstants.T_QUATE);
}

<ST_IN_SCRIPTING>b?"<<<"{TABS_AND_SPACES}({LABEL}|([']{LABEL}['])|([\"]{LABEL}[\"])){NEWLINE} {
	String yytext = yytext();
	int removeChars = (yytext.charAt(0) == 'b') ? 4 : 3;
	String hereOrNowDoc = yytext.substring(removeChars).trim();    // for 'b<<<' or '<<<'
	int heredoc_len = hereOrNowDoc.length();

	Symbol sym = createFullSymbol(ParserConstants.T_START_HEREDOC);
	sym.value = hereOrNowDoc;

	if (hereOrNowDoc.charAt(0) == '\'') {
		heredocIds.push(hereOrNowDoc.substring(1, heredoc_len - 1));
		yybegin(ST_START_NOWDOC);
	} else if (hereOrNowDoc.charAt(0) == '"') {
		heredocIds.push(hereOrNowDoc.substring(1, heredoc_len - 1));
		yybegin(ST_START_HEREDOC);
	} else {
		heredocIds.push(hereOrNowDoc);
		yybegin(ST_START_HEREDOC);
	}
	return sym;
}

<ST_IN_SCRIPTING>[`] {
	yybegin(ST_BACKQUOTE);
	return createSymbol(ParserConstants.T_BACKQUATE);
}

<ST_IN_SCRIPTING>['] {
	yybegin(ST_SINGLE_QUOTE);
	return createSymbol(ParserConstants.T_SINGLE_QUATE);
}

<ST_START_HEREDOC>{TABS_AND_SPACES}{LABEL} {
	String yytext = yytext();
	int textLength = yytext.length();
	String heredoc = heredocIds.peek();
	int heredocLength = heredoc.length();
	if (textLength > heredocLength && yytext.substring(textLength - heredocLength, textLength).equals(heredoc)) {
		char c = yytext.charAt(textLength - heredocLength - 1);
		if (c == ' ' || c == '\t') {
			lastHeredocIndentation = yytext.substring(0, textLength - heredocLength);
			// we need to remove the closing label from the symbol value
			yypushback(heredocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		}
	}
	if (textLength == heredocLength && yytext.equals(heredoc)) {
		lastHeredocIndentation = ""; //$NON-NLS-1$
		heredocIds.pop();
		yybegin(ST_IN_SCRIPTING);
		return createFullSymbol(ParserConstants.T_END_HEREDOC);
	}
	yybegin(ST_HEREDOC);
}

<ST_START_HEREDOC>{ANY_CHAR} {
	yypushback(1);
	yybegin(ST_HEREDOC);
}

<ST_HEREDOC>{HEREDOC_CHARS}*({HEREDOC_NEWLINE}{TABS_AND_SPACES})+{LABEL} {
	String yytext = yytext();
	int textLength = yytext.length();
	String heredoc = heredocIds.peek();
	int heredocLength = heredoc.length();
	if (textLength > heredocLength && yytext.substring(textLength - heredocLength, textLength).equals(heredoc)) {
		int pos = textLength - heredocLength - 1;
		char c = yytext.charAt(pos);
		if (c == ' ' || c == '\t') {
			while (pos > 0) {
				char c2 = yytext.charAt(pos - 1);
				if (c2 != ' ' && c2 != '\t') {
					break;
				}
				pos--;
			}
			lastHeredocIndentation = yytext.substring(pos, textLength - heredocLength);
			// we need to remove the closing label from the symbol value
			yypushback(heredocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		} else if (c == '\n' || c == '\r') {
			lastHeredocIndentation = ""; //$NON-NLS-1$
			// we need to remove the closing label from the symbol value
			yypushback(heredocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		}
	}
}

<ST_END_HEREDOC>{LABEL} {
	heredocIds.pop();
	yybegin(ST_IN_SCRIPTING);
	return createFullSymbol(ParserConstants.T_END_HEREDOC);
}

<ST_START_NOWDOC>{TABS_AND_SPACES}{LABEL} {
	String yytext = yytext();
	int textLength = yytext.length();
	String nowdoc = heredocIds.peek();
	int nowdocLength = nowdoc.length();
	if (textLength > nowdocLength && yytext.substring(textLength - nowdocLength, textLength).equals(nowdoc)) {
		char c = yytext.charAt(textLength - nowdocLength - 1);
		if (c == ' ' || c == '\t') {
			lastHeredocIndentation = yytext.substring(0, textLength - nowdocLength);
			// we need to remove the closing label from the symbol value
			yypushback(nowdocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		}
	}
	if (textLength == nowdocLength && yytext.equals(nowdoc)) {
		lastHeredocIndentation = ""; //$NON-NLS-1$
		heredocIds.pop();
		yybegin(ST_IN_SCRIPTING);
		return createFullSymbol(ParserConstants.T_END_HEREDOC);
	}
	yybegin(ST_NOWDOC);
}

<ST_START_NOWDOC>{ANY_CHAR} {
	yypushback(1);
	yybegin(ST_NOWDOC);
}

<ST_NOWDOC>{NOWDOC_CHARS}*({NEWLINE}{TABS_AND_SPACES})+{LABEL} {
	String yytext = yytext();
	int textLength = yytext.length();
	String nowdoc = heredocIds.peek();
	int nowdocLength = nowdoc.length();
	if (textLength > nowdocLength && yytext.substring(textLength - nowdocLength, textLength).equals(nowdoc)) {
		int pos = textLength - nowdocLength - 1;
		char c = yytext.charAt(pos);
		if (c == ' ' || c == '\t') {
			while (pos > 0) {
				char c2 = yytext.charAt(pos - 1);
				if (c2 != ' ' && c2 != '\t') {
					break;
				}
				pos--;
			}
			lastHeredocIndentation = yytext.substring(pos, textLength - nowdocLength);
			// we need to remove the closing label from the symbol value
			yypushback(nowdocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		} else if (c == '\n' || c == '\r') {
			lastHeredocIndentation = ""; //$NON-NLS-1$
			// we need to remove the closing label from the symbol value
			yypushback(nowdocLength);
			yybegin(ST_END_HEREDOC);
			return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
		}
	}
}

<ST_SINGLE_QUOTE>([^'\\]|\\[^'\\])+ {
	return createSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_SINGLE_QUOTE>"\\'" {
	return createSymbol(ParserConstants.T_CHARACTER);
}

<ST_SINGLE_QUOTE>"\\\\" {
	return createSymbol(ParserConstants.T_CHARACTER);
}

<ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_HEREDOC>"{$" {
	pushState(ST_IN_SCRIPTING);
	yypushback(yylength() - 1);
	return createSymbol(ParserConstants.T_CURLY_OPEN_WITH_DOLAR);
}

<ST_DOUBLE_QUOTES>{DOUBLE_QUOTES_CHARS}+ {
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

/*
The original parsing rule was {DOUBLE_QUOTES_CHARS}*("{"{2,}|"$"{2,}|(("{"+|"$"+)[\"]))
but jflex doesn't support a{n,} so we changed a{2,} to aa+
*/
<ST_DOUBLE_QUOTES>{DOUBLE_QUOTES_CHARS}*("{""{"+|"$""$"+|(("{"+|"$"+)[\"])) {
	yypushback(1);
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_BACKQUOTE>{BACKQUOTE_CHARS}+ {
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

/*
The original parsing rule was {BACKQUOTE_CHARS}*("{"{2,}|"$"{2,}|(("{"+|"$"+)[`]))
but jflex doesn't support a{n,} so we changed a{2,} to aa+
*/
<ST_BACKQUOTE>{BACKQUOTE_CHARS}*("{""{"+|"$""$"+|(("{"+|"$"+)[`])) {
	yypushback(1);
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_HEREDOC>{HEREDOC_CHARS}*(({HEREDOC_NEWLINE}{TABS_AND_SPACES})+)? {
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

/*
The original parsing rule was {HEREDOC_CHARS}*(({HEREDOC_NEWLINE}{TABS_AND_SPACES})+)?("{"{2,}|"$"{2,})
but jflex doesn't support a{n,} so we changed a{2,} to aa+
*/
<ST_HEREDOC>{HEREDOC_CHARS}*(({HEREDOC_NEWLINE}{TABS_AND_SPACES})+)?("{""{"+|"$""$"+) {
	yypushback(1);
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_NOWDOC>{NOWDOC_CHARS}*(({NEWLINE}{TABS_AND_SPACES})+)? {
	return createFullSymbol(ParserConstants.T_ENCAPSED_AND_WHITESPACE);
}

<ST_DOUBLE_QUOTES>[\"] {
	yybegin(ST_IN_SCRIPTING);
	return createSymbol(ParserConstants.T_QUATE);
}

<ST_BACKQUOTE>[`] {
	yybegin(ST_IN_SCRIPTING);
	return createSymbol(ParserConstants.T_BACKQUATE);
}

<ST_SINGLE_QUOTE>['] {
	yybegin(ST_IN_SCRIPTING);
	return createSymbol(ParserConstants.T_SINGLE_QUATE);
}

<ST_IN_SCRIPTING,YYINITIAL,ST_DOUBLE_QUOTES,ST_BACKQUOTE,ST_SINGLE_QUOTE,ST_HEREDOC,ST_START_HEREDOC,ST_END_HEREDOC,ST_START_NOWDOC,ST_NOWDOC,ST_VAR_OFFSET>{ANY_CHAR} {
	// do nothing
}