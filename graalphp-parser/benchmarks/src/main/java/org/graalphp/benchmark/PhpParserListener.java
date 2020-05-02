// Generated from PhpParser.g4 by ANTLR 4.8
package org.graalphp.benchmark;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PhpParser}.
 */
public interface PhpParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PhpParser#htmlDocument}.
	 * @param ctx the parse tree
	 */
	void enterHtmlDocument(PhpParser.HtmlDocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#htmlDocument}.
	 * @param ctx the parse tree
	 */
	void exitHtmlDocument(PhpParser.HtmlDocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#htmlElementOrPhpBlock}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElementOrPhpBlock(PhpParser.HtmlElementOrPhpBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#htmlElementOrPhpBlock}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElementOrPhpBlock(PhpParser.HtmlElementOrPhpBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#htmlElements}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElements(PhpParser.HtmlElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#htmlElements}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElements(PhpParser.HtmlElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void enterHtmlElement(PhpParser.HtmlElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#htmlElement}.
	 * @param ctx the parse tree
	 */
	void exitHtmlElement(PhpParser.HtmlElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#scriptTextPart}.
	 * @param ctx the parse tree
	 */
	void enterScriptTextPart(PhpParser.ScriptTextPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#scriptTextPart}.
	 * @param ctx the parse tree
	 */
	void exitScriptTextPart(PhpParser.ScriptTextPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#phpBlock}.
	 * @param ctx the parse tree
	 */
	void enterPhpBlock(PhpParser.PhpBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#phpBlock}.
	 * @param ctx the parse tree
	 */
	void exitPhpBlock(PhpParser.PhpBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(PhpParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(PhpParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#topStatement}.
	 * @param ctx the parse tree
	 */
	void enterTopStatement(PhpParser.TopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#topStatement}.
	 * @param ctx the parse tree
	 */
	void exitTopStatement(PhpParser.TopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#useDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterUseDeclaration(PhpParser.UseDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#useDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitUseDeclaration(PhpParser.UseDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#useDeclarationContentList}.
	 * @param ctx the parse tree
	 */
	void enterUseDeclarationContentList(PhpParser.UseDeclarationContentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#useDeclarationContentList}.
	 * @param ctx the parse tree
	 */
	void exitUseDeclarationContentList(PhpParser.UseDeclarationContentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#useDeclarationContent}.
	 * @param ctx the parse tree
	 */
	void enterUseDeclarationContent(PhpParser.UseDeclarationContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#useDeclarationContent}.
	 * @param ctx the parse tree
	 */
	void exitUseDeclarationContent(PhpParser.UseDeclarationContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDeclaration(PhpParser.NamespaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDeclaration(PhpParser.NamespaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#namespaceStatement}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceStatement(PhpParser.NamespaceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#namespaceStatement}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceStatement(PhpParser.NamespaceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(PhpParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(PhpParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(PhpParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(PhpParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#classEntryType}.
	 * @param ctx the parse tree
	 */
	void enterClassEntryType(PhpParser.ClassEntryTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#classEntryType}.
	 * @param ctx the parse tree
	 */
	void exitClassEntryType(PhpParser.ClassEntryTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#interfaceList}.
	 * @param ctx the parse tree
	 */
	void enterInterfaceList(PhpParser.InterfaceListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#interfaceList}.
	 * @param ctx the parse tree
	 */
	void exitInterfaceList(PhpParser.InterfaceListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeParameterListInBrackets}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterListInBrackets(PhpParser.TypeParameterListInBracketsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeParameterListInBrackets}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterListInBrackets(PhpParser.TypeParameterListInBracketsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterList(PhpParser.TypeParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeParameterList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterList(PhpParser.TypeParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeParameterWithDefaultsList}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterWithDefaultsList(PhpParser.TypeParameterWithDefaultsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeParameterWithDefaultsList}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterWithDefaultsList(PhpParser.TypeParameterWithDefaultsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeParameterDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterDecl(PhpParser.TypeParameterDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeParameterDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterDecl(PhpParser.TypeParameterDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeParameterWithDefaultDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeParameterWithDefaultDecl(PhpParser.TypeParameterWithDefaultDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeParameterWithDefaultDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeParameterWithDefaultDecl(PhpParser.TypeParameterWithDefaultDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#genericDynamicArgs}.
	 * @param ctx the parse tree
	 */
	void enterGenericDynamicArgs(PhpParser.GenericDynamicArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#genericDynamicArgs}.
	 * @param ctx the parse tree
	 */
	void exitGenericDynamicArgs(PhpParser.GenericDynamicArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attributes}.
	 * @param ctx the parse tree
	 */
	void enterAttributes(PhpParser.AttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attributes}.
	 * @param ctx the parse tree
	 */
	void exitAttributes(PhpParser.AttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attributesGroup}.
	 * @param ctx the parse tree
	 */
	void enterAttributesGroup(PhpParser.AttributesGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attributesGroup}.
	 * @param ctx the parse tree
	 */
	void exitAttributesGroup(PhpParser.AttributesGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(PhpParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(PhpParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attributeArgList}.
	 * @param ctx the parse tree
	 */
	void enterAttributeArgList(PhpParser.AttributeArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attributeArgList}.
	 * @param ctx the parse tree
	 */
	void exitAttributeArgList(PhpParser.AttributeArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attributeNamedArgList}.
	 * @param ctx the parse tree
	 */
	void enterAttributeNamedArgList(PhpParser.AttributeNamedArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attributeNamedArgList}.
	 * @param ctx the parse tree
	 */
	void exitAttributeNamedArgList(PhpParser.AttributeNamedArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#attributeNamedArg}.
	 * @param ctx the parse tree
	 */
	void enterAttributeNamedArg(PhpParser.AttributeNamedArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#attributeNamedArg}.
	 * @param ctx the parse tree
	 */
	void exitAttributeNamedArg(PhpParser.AttributeNamedArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#innerStatementList}.
	 * @param ctx the parse tree
	 */
	void enterInnerStatementList(PhpParser.InnerStatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#innerStatementList}.
	 * @param ctx the parse tree
	 */
	void exitInnerStatementList(PhpParser.InnerStatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#innerStatement}.
	 * @param ctx the parse tree
	 */
	void enterInnerStatement(PhpParser.InnerStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#innerStatement}.
	 * @param ctx the parse tree
	 */
	void exitInnerStatement(PhpParser.InnerStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PhpParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PhpParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(PhpParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(PhpParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(PhpParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(PhpParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(PhpParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(PhpParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseIfStatement(PhpParser.ElseIfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#elseIfStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseIfStatement(PhpParser.ElseIfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#elseIfColonStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseIfColonStatement(PhpParser.ElseIfColonStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#elseIfColonStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseIfColonStatement(PhpParser.ElseIfColonStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(PhpParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(PhpParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#elseColonStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseColonStatement(PhpParser.ElseColonStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#elseColonStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseColonStatement(PhpParser.ElseColonStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(PhpParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(PhpParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(PhpParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(PhpParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(PhpParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(PhpParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(PhpParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(PhpParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(PhpParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(PhpParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(PhpParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(PhpParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBlock(PhpParser.SwitchBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#switchBlock}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBlock(PhpParser.SwitchBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(PhpParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(PhpParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(PhpParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(PhpParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(PhpParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(PhpParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(PhpParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(PhpParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#unsetStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnsetStatement(PhpParser.UnsetStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#unsetStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnsetStatement(PhpParser.UnsetStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void enterForeachStatement(PhpParser.ForeachStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void exitForeachStatement(PhpParser.ForeachStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#tryCatchFinally}.
	 * @param ctx the parse tree
	 */
	void enterTryCatchFinally(PhpParser.TryCatchFinallyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#tryCatchFinally}.
	 * @param ctx the parse tree
	 */
	void exitTryCatchFinally(PhpParser.TryCatchFinallyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void enterCatchClause(PhpParser.CatchClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#catchClause}.
	 * @param ctx the parse tree
	 */
	void exitCatchClause(PhpParser.CatchClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#finallyStatement}.
	 * @param ctx the parse tree
	 */
	void enterFinallyStatement(PhpParser.FinallyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#finallyStatement}.
	 * @param ctx the parse tree
	 */
	void exitFinallyStatement(PhpParser.FinallyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(PhpParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#throwStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(PhpParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void enterGotoStatement(PhpParser.GotoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#gotoStatement}.
	 * @param ctx the parse tree
	 */
	void exitGotoStatement(PhpParser.GotoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#declareStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclareStatement(PhpParser.DeclareStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#declareStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclareStatement(PhpParser.DeclareStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#inlineHtmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterInlineHtmlStatement(PhpParser.InlineHtmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#inlineHtmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitInlineHtmlStatement(PhpParser.InlineHtmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#inlineHtml}.
	 * @param ctx the parse tree
	 */
	void enterInlineHtml(PhpParser.InlineHtmlContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#inlineHtml}.
	 * @param ctx the parse tree
	 */
	void exitInlineHtml(PhpParser.InlineHtmlContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#declareList}.
	 * @param ctx the parse tree
	 */
	void enterDeclareList(PhpParser.DeclareListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#declareList}.
	 * @param ctx the parse tree
	 */
	void exitDeclareList(PhpParser.DeclareListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameterList(PhpParser.FormalParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#formalParameterList}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameterList(PhpParser.FormalParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameter(PhpParser.FormalParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#formalParameter}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameter(PhpParser.FormalParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeHint}.
	 * @param ctx the parse tree
	 */
	void enterTypeHint(PhpParser.TypeHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeHint}.
	 * @param ctx the parse tree
	 */
	void exitTypeHint(PhpParser.TypeHintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatement(PhpParser.GlobalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatement(PhpParser.GlobalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#globalVar}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVar(PhpParser.GlobalVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#globalVar}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVar(PhpParser.GlobalVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#echoStatement}.
	 * @param ctx the parse tree
	 */
	void enterEchoStatement(PhpParser.EchoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#echoStatement}.
	 * @param ctx the parse tree
	 */
	void exitEchoStatement(PhpParser.EchoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#staticVariableStatement}.
	 * @param ctx the parse tree
	 */
	void enterStaticVariableStatement(PhpParser.StaticVariableStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#staticVariableStatement}.
	 * @param ctx the parse tree
	 */
	void exitStaticVariableStatement(PhpParser.StaticVariableStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#classStatement}.
	 * @param ctx the parse tree
	 */
	void enterClassStatement(PhpParser.ClassStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#classStatement}.
	 * @param ctx the parse tree
	 */
	void exitClassStatement(PhpParser.ClassStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#traitAdaptations}.
	 * @param ctx the parse tree
	 */
	void enterTraitAdaptations(PhpParser.TraitAdaptationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#traitAdaptations}.
	 * @param ctx the parse tree
	 */
	void exitTraitAdaptations(PhpParser.TraitAdaptationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#traitAdaptationStatement}.
	 * @param ctx the parse tree
	 */
	void enterTraitAdaptationStatement(PhpParser.TraitAdaptationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#traitAdaptationStatement}.
	 * @param ctx the parse tree
	 */
	void exitTraitAdaptationStatement(PhpParser.TraitAdaptationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#traitPrecedence}.
	 * @param ctx the parse tree
	 */
	void enterTraitPrecedence(PhpParser.TraitPrecedenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#traitPrecedence}.
	 * @param ctx the parse tree
	 */
	void exitTraitPrecedence(PhpParser.TraitPrecedenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#traitAlias}.
	 * @param ctx the parse tree
	 */
	void enterTraitAlias(PhpParser.TraitAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#traitAlias}.
	 * @param ctx the parse tree
	 */
	void exitTraitAlias(PhpParser.TraitAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#traitMethodReference}.
	 * @param ctx the parse tree
	 */
	void enterTraitMethodReference(PhpParser.TraitMethodReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#traitMethodReference}.
	 * @param ctx the parse tree
	 */
	void exitTraitMethodReference(PhpParser.TraitMethodReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#baseCtorCall}.
	 * @param ctx the parse tree
	 */
	void enterBaseCtorCall(PhpParser.BaseCtorCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#baseCtorCall}.
	 * @param ctx the parse tree
	 */
	void exitBaseCtorCall(PhpParser.BaseCtorCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(PhpParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(PhpParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#propertyModifiers}.
	 * @param ctx the parse tree
	 */
	void enterPropertyModifiers(PhpParser.PropertyModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#propertyModifiers}.
	 * @param ctx the parse tree
	 */
	void exitPropertyModifiers(PhpParser.PropertyModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#memberModifiers}.
	 * @param ctx the parse tree
	 */
	void enterMemberModifiers(PhpParser.MemberModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#memberModifiers}.
	 * @param ctx the parse tree
	 */
	void exitMemberModifiers(PhpParser.MemberModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(PhpParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(PhpParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#identifierInititalizer}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierInititalizer(PhpParser.IdentifierInititalizerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#identifierInititalizer}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierInititalizer(PhpParser.IdentifierInititalizerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#globalConstantDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGlobalConstantDeclaration(PhpParser.GlobalConstantDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#globalConstantDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGlobalConstantDeclaration(PhpParser.GlobalConstantDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(PhpParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(PhpParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(PhpParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#parentheses}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(PhpParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ChainExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterChainExpression(PhpParser.ChainExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ChainExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitChainExpression(PhpParser.ChainExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperatorExpression(PhpParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperatorExpression(PhpParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpecialWordExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSpecialWordExpression(PhpParser.SpecialWordExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpecialWordExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSpecialWordExpression(PhpParser.SpecialWordExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayCreationExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreationExpression(PhpParser.ArrayCreationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayCreationExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreationExpression(PhpParser.ArrayCreationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(PhpParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(PhpParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesisExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(PhpParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesisExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(PhpParser.ParenthesisExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpaceshipExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSpaceshipExpression(PhpParser.SpaceshipExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpaceshipExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSpaceshipExpression(PhpParser.SpaceshipExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BackQuoteStringExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBackQuoteStringExpression(PhpParser.BackQuoteStringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BackQuoteStringExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBackQuoteStringExpression(PhpParser.BackQuoteStringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(PhpParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(PhpParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullCoalescingExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNullCoalescingExpression(PhpParser.NullCoalescingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullCoalescingExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNullCoalescingExpression(PhpParser.NullCoalescingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(PhpParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(PhpParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IndexerExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIndexerExpression(PhpParser.IndexerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IndexerExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIndexerExpression(PhpParser.IndexerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ScalarExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterScalarExpression(PhpParser.ScalarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ScalarExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitScalarExpression(PhpParser.ScalarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixIncDecExpression(PhpParser.PrefixIncDecExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixIncDecExpression(PhpParser.PrefixIncDecExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(PhpParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(PhpParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(PhpParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(PhpParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpression(PhpParser.PrintExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpression(PhpParser.PrintExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(PhpParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(PhpParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixIncDecExpression(PhpParser.PostfixIncDecExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixIncDecExpression(PhpParser.PostfixIncDecExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(PhpParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(PhpParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InstanceOfExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInstanceOfExpression(PhpParser.InstanceOfExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InstanceOfExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInstanceOfExpression(PhpParser.InstanceOfExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LambdaFunctionExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaFunctionExpression(PhpParser.LambdaFunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LambdaFunctionExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaFunctionExpression(PhpParser.LambdaFunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitwiseExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseExpression(PhpParser.BitwiseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitwiseExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseExpression(PhpParser.BitwiseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CloneExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCloneExpression(PhpParser.CloneExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CloneExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCloneExpression(PhpParser.CloneExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#assignable}.
	 * @param ctx the parse tree
	 */
	void enterAssignable(PhpParser.AssignableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#assignable}.
	 * @param ctx the parse tree
	 */
	void exitAssignable(PhpParser.AssignableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#arrayCreation}.
	 * @param ctx the parse tree
	 */
	void enterArrayCreation(PhpParser.ArrayCreationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#arrayCreation}.
	 * @param ctx the parse tree
	 */
	void exitArrayCreation(PhpParser.ArrayCreationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#lambdaFunctionExpr}.
	 * @param ctx the parse tree
	 */
	void enterLambdaFunctionExpr(PhpParser.LambdaFunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#lambdaFunctionExpr}.
	 * @param ctx the parse tree
	 */
	void exitLambdaFunctionExpr(PhpParser.LambdaFunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#newExpr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(PhpParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#newExpr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(PhpParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(PhpParser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(PhpParser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#yieldExpression}.
	 * @param ctx the parse tree
	 */
	void enterYieldExpression(PhpParser.YieldExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#yieldExpression}.
	 * @param ctx the parse tree
	 */
	void exitYieldExpression(PhpParser.YieldExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#arrayItemList}.
	 * @param ctx the parse tree
	 */
	void enterArrayItemList(PhpParser.ArrayItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#arrayItemList}.
	 * @param ctx the parse tree
	 */
	void exitArrayItemList(PhpParser.ArrayItemListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#arrayItem}.
	 * @param ctx the parse tree
	 */
	void enterArrayItem(PhpParser.ArrayItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#arrayItem}.
	 * @param ctx the parse tree
	 */
	void exitArrayItem(PhpParser.ArrayItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#lambdaFunctionUseVars}.
	 * @param ctx the parse tree
	 */
	void enterLambdaFunctionUseVars(PhpParser.LambdaFunctionUseVarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#lambdaFunctionUseVars}.
	 * @param ctx the parse tree
	 */
	void exitLambdaFunctionUseVars(PhpParser.LambdaFunctionUseVarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#lambdaFunctionUseVar}.
	 * @param ctx the parse tree
	 */
	void enterLambdaFunctionUseVar(PhpParser.LambdaFunctionUseVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#lambdaFunctionUseVar}.
	 * @param ctx the parse tree
	 */
	void exitLambdaFunctionUseVar(PhpParser.LambdaFunctionUseVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#qualifiedStaticTypeRef}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedStaticTypeRef(PhpParser.QualifiedStaticTypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#qualifiedStaticTypeRef}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedStaticTypeRef(PhpParser.QualifiedStaticTypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterTypeRef(PhpParser.TypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitTypeRef(PhpParser.TypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#anoymousClass}.
	 * @param ctx the parse tree
	 */
	void enterAnoymousClass(PhpParser.AnoymousClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#anoymousClass}.
	 * @param ctx the parse tree
	 */
	void exitAnoymousClass(PhpParser.AnoymousClassContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#indirectTypeRef}.
	 * @param ctx the parse tree
	 */
	void enterIndirectTypeRef(PhpParser.IndirectTypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#indirectTypeRef}.
	 * @param ctx the parse tree
	 */
	void exitIndirectTypeRef(PhpParser.IndirectTypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#qualifiedNamespaceName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNamespaceName(PhpParser.QualifiedNamespaceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#qualifiedNamespaceName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNamespaceName(PhpParser.QualifiedNamespaceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#namespaceNameList}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceNameList(PhpParser.NamespaceNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#namespaceNameList}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceNameList(PhpParser.NamespaceNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#namespaceNameTail}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceNameTail(PhpParser.NamespaceNameTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#namespaceNameTail}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceNameTail(PhpParser.NamespaceNameTailContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#qualifiedNamespaceNameList}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedNamespaceNameList(PhpParser.QualifiedNamespaceNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#qualifiedNamespaceNameList}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedNamespaceNameList(PhpParser.QualifiedNamespaceNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(PhpParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(PhpParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#actualArgument}.
	 * @param ctx the parse tree
	 */
	void enterActualArgument(PhpParser.ActualArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#actualArgument}.
	 * @param ctx the parse tree
	 */
	void exitActualArgument(PhpParser.ActualArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#constantInititalizer}.
	 * @param ctx the parse tree
	 */
	void enterConstantInititalizer(PhpParser.ConstantInititalizerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#constantInititalizer}.
	 * @param ctx the parse tree
	 */
	void exitConstantInititalizer(PhpParser.ConstantInititalizerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#constantArrayItemList}.
	 * @param ctx the parse tree
	 */
	void enterConstantArrayItemList(PhpParser.ConstantArrayItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#constantArrayItemList}.
	 * @param ctx the parse tree
	 */
	void exitConstantArrayItemList(PhpParser.ConstantArrayItemListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#constantArrayItem}.
	 * @param ctx the parse tree
	 */
	void enterConstantArrayItem(PhpParser.ConstantArrayItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#constantArrayItem}.
	 * @param ctx the parse tree
	 */
	void exitConstantArrayItem(PhpParser.ConstantArrayItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(PhpParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(PhpParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#literalConstant}.
	 * @param ctx the parse tree
	 */
	void enterLiteralConstant(PhpParser.LiteralConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#literalConstant}.
	 * @param ctx the parse tree
	 */
	void exitLiteralConstant(PhpParser.LiteralConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#numericConstant}.
	 * @param ctx the parse tree
	 */
	void enterNumericConstant(PhpParser.NumericConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#numericConstant}.
	 * @param ctx the parse tree
	 */
	void exitNumericConstant(PhpParser.NumericConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#classConstant}.
	 * @param ctx the parse tree
	 */
	void enterClassConstant(PhpParser.ClassConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#classConstant}.
	 * @param ctx the parse tree
	 */
	void exitClassConstant(PhpParser.ClassConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#stringConstant}.
	 * @param ctx the parse tree
	 */
	void enterStringConstant(PhpParser.StringConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#stringConstant}.
	 * @param ctx the parse tree
	 */
	void exitStringConstant(PhpParser.StringConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(PhpParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(PhpParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#interpolatedStringPart}.
	 * @param ctx the parse tree
	 */
	void enterInterpolatedStringPart(PhpParser.InterpolatedStringPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#interpolatedStringPart}.
	 * @param ctx the parse tree
	 */
	void exitInterpolatedStringPart(PhpParser.InterpolatedStringPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#chainList}.
	 * @param ctx the parse tree
	 */
	void enterChainList(PhpParser.ChainListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#chainList}.
	 * @param ctx the parse tree
	 */
	void exitChainList(PhpParser.ChainListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#chain}.
	 * @param ctx the parse tree
	 */
	void enterChain(PhpParser.ChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#chain}.
	 * @param ctx the parse tree
	 */
	void exitChain(PhpParser.ChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#chainOrigin}.
	 * @param ctx the parse tree
	 */
	void enterChainOrigin(PhpParser.ChainOriginContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#chainOrigin}.
	 * @param ctx the parse tree
	 */
	void exitChainOrigin(PhpParser.ChainOriginContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#memberAccess}.
	 * @param ctx the parse tree
	 */
	void enterMemberAccess(PhpParser.MemberAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#memberAccess}.
	 * @param ctx the parse tree
	 */
	void exitMemberAccess(PhpParser.MemberAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(PhpParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(PhpParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#functionCallName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallName(PhpParser.FunctionCallNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#functionCallName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallName(PhpParser.FunctionCallNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#actualArguments}.
	 * @param ctx the parse tree
	 */
	void enterActualArguments(PhpParser.ActualArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#actualArguments}.
	 * @param ctx the parse tree
	 */
	void exitActualArguments(PhpParser.ActualArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#chainBase}.
	 * @param ctx the parse tree
	 */
	void enterChainBase(PhpParser.ChainBaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#chainBase}.
	 * @param ctx the parse tree
	 */
	void exitChainBase(PhpParser.ChainBaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#keyedFieldName}.
	 * @param ctx the parse tree
	 */
	void enterKeyedFieldName(PhpParser.KeyedFieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#keyedFieldName}.
	 * @param ctx the parse tree
	 */
	void exitKeyedFieldName(PhpParser.KeyedFieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#keyedSimpleFieldName}.
	 * @param ctx the parse tree
	 */
	void enterKeyedSimpleFieldName(PhpParser.KeyedSimpleFieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#keyedSimpleFieldName}.
	 * @param ctx the parse tree
	 */
	void exitKeyedSimpleFieldName(PhpParser.KeyedSimpleFieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#keyedVariable}.
	 * @param ctx the parse tree
	 */
	void enterKeyedVariable(PhpParser.KeyedVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#keyedVariable}.
	 * @param ctx the parse tree
	 */
	void exitKeyedVariable(PhpParser.KeyedVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#squareCurlyExpression}.
	 * @param ctx the parse tree
	 */
	void enterSquareCurlyExpression(PhpParser.SquareCurlyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#squareCurlyExpression}.
	 * @param ctx the parse tree
	 */
	void exitSquareCurlyExpression(PhpParser.SquareCurlyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#assignmentList}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentList(PhpParser.AssignmentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#assignmentList}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentList(PhpParser.AssignmentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#assignmentListElement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentListElement(PhpParser.AssignmentListElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#assignmentListElement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentListElement(PhpParser.AssignmentListElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#modifier}.
	 * @param ctx the parse tree
	 */
	void enterModifier(PhpParser.ModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#modifier}.
	 * @param ctx the parse tree
	 */
	void exitModifier(PhpParser.ModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(PhpParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(PhpParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#memberModifier}.
	 * @param ctx the parse tree
	 */
	void enterMemberModifier(PhpParser.MemberModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#memberModifier}.
	 * @param ctx the parse tree
	 */
	void exitMemberModifier(PhpParser.MemberModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#magicConstant}.
	 * @param ctx the parse tree
	 */
	void enterMagicConstant(PhpParser.MagicConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#magicConstant}.
	 * @param ctx the parse tree
	 */
	void exitMagicConstant(PhpParser.MagicConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#magicMethod}.
	 * @param ctx the parse tree
	 */
	void enterMagicMethod(PhpParser.MagicMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#magicMethod}.
	 * @param ctx the parse tree
	 */
	void exitMagicMethod(PhpParser.MagicMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(PhpParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(PhpParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PhpParser#castOperation}.
	 * @param ctx the parse tree
	 */
	void enterCastOperation(PhpParser.CastOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PhpParser#castOperation}.
	 * @param ctx the parse tree
	 */
	void exitCastOperation(PhpParser.CastOperationContext ctx);
}