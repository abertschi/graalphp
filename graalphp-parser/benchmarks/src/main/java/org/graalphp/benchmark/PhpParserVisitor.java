// Generated from PhpParser.g4 by ANTLR 4.8
package org.graalphp.benchmark;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PhpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PhpParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PhpParser#htmlDocument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlDocument(PhpParser.HtmlDocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#htmlElementOrPhpBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElementOrPhpBlock(PhpParser.HtmlElementOrPhpBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#htmlElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElements(PhpParser.HtmlElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#htmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHtmlElement(PhpParser.HtmlElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#scriptTextPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptTextPart(PhpParser.ScriptTextPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#phpBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhpBlock(PhpParser.PhpBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#importStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatement(PhpParser.ImportStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#topStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTopStatement(PhpParser.TopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#useDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseDeclaration(PhpParser.UseDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#useDeclarationContentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseDeclarationContentList(PhpParser.UseDeclarationContentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#useDeclarationContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseDeclarationContent(PhpParser.UseDeclarationContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceDeclaration(PhpParser.NamespaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#namespaceStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceStatement(PhpParser.NamespaceStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(PhpParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(PhpParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#classEntryType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassEntryType(PhpParser.ClassEntryTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#interfaceList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterfaceList(PhpParser.InterfaceListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeParameterListInBrackets}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterListInBrackets(PhpParser.TypeParameterListInBracketsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterList(PhpParser.TypeParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeParameterWithDefaultsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterWithDefaultsList(PhpParser.TypeParameterWithDefaultsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeParameterDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterDecl(PhpParser.TypeParameterDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeParameterWithDefaultDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeParameterWithDefaultDecl(PhpParser.TypeParameterWithDefaultDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#genericDynamicArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericDynamicArgs(PhpParser.GenericDynamicArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributes(PhpParser.AttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attributesGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributesGroup(PhpParser.AttributesGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(PhpParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attributeArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeArgList(PhpParser.AttributeArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attributeNamedArgList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeNamedArgList(PhpParser.AttributeNamedArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#attributeNamedArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeNamedArg(PhpParser.AttributeNamedArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#innerStatementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerStatementList(PhpParser.InnerStatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#innerStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInnerStatement(PhpParser.InnerStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PhpParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#emptyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(PhpParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(PhpParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(PhpParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#elseIfStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfStatement(PhpParser.ElseIfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#elseIfColonStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIfColonStatement(PhpParser.ElseIfColonStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#elseStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseStatement(PhpParser.ElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#elseColonStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseColonStatement(PhpParser.ElseColonStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(PhpParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#doWhileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileStatement(PhpParser.DoWhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(PhpParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(PhpParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(PhpParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#switchStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(PhpParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#switchBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBlock(PhpParser.SwitchBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(PhpParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(PhpParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(PhpParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(PhpParser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#unsetStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsetStatement(PhpParser.UnsetStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#foreachStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeachStatement(PhpParser.ForeachStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#tryCatchFinally}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryCatchFinally(PhpParser.TryCatchFinallyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#catchClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchClause(PhpParser.CatchClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#finallyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinallyStatement(PhpParser.FinallyStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#throwStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowStatement(PhpParser.ThrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#gotoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoStatement(PhpParser.GotoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#declareStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareStatement(PhpParser.DeclareStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#inlineHtmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineHtmlStatement(PhpParser.InlineHtmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#inlineHtml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInlineHtml(PhpParser.InlineHtmlContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#declareList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareList(PhpParser.DeclareListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(PhpParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#formalParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameter(PhpParser.FormalParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeHint(PhpParser.TypeHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#globalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalStatement(PhpParser.GlobalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#globalVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalVar(PhpParser.GlobalVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#echoStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEchoStatement(PhpParser.EchoStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#staticVariableStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaticVariableStatement(PhpParser.StaticVariableStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#classStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassStatement(PhpParser.ClassStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#traitAdaptations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitAdaptations(PhpParser.TraitAdaptationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#traitAdaptationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitAdaptationStatement(PhpParser.TraitAdaptationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#traitPrecedence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitPrecedence(PhpParser.TraitPrecedenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#traitAlias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitAlias(PhpParser.TraitAliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#traitMethodReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTraitMethodReference(PhpParser.TraitMethodReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#baseCtorCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseCtorCall(PhpParser.BaseCtorCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(PhpParser.MethodBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#propertyModifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyModifiers(PhpParser.PropertyModifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#memberModifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberModifiers(PhpParser.MemberModifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(PhpParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#identifierInititalizer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierInititalizer(PhpParser.IdentifierInititalizerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#globalConstantDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalConstantDeclaration(PhpParser.GlobalConstantDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(PhpParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#parentheses}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(PhpParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ChainExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainExpression(PhpParser.ChainExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperatorExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperatorExpression(PhpParser.UnaryOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SpecialWordExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecialWordExpression(PhpParser.SpecialWordExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayCreationExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreationExpression(PhpParser.ArrayCreationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpression(PhpParser.NewExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesisExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpression(PhpParser.ParenthesisExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SpaceshipExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceshipExpression(PhpParser.SpaceshipExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BackQuoteStringExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackQuoteStringExpression(PhpParser.BackQuoteStringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(PhpParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NullCoalescingExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullCoalescingExpression(PhpParser.NullCoalescingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithmeticExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(PhpParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IndexerExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexerExpression(PhpParser.IndexerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ScalarExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarExpression(PhpParser.ScalarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixIncDecExpression(PhpParser.PrefixIncDecExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(PhpParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(PhpParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintExpression(PhpParser.PrintExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(PhpParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixIncDecExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixIncDecExpression(PhpParser.PostfixIncDecExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(PhpParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InstanceOfExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceOfExpression(PhpParser.InstanceOfExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LambdaFunctionExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaFunctionExpression(PhpParser.LambdaFunctionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitwiseExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseExpression(PhpParser.BitwiseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CloneExpression}
	 * labeled alternative in {@link PhpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloneExpression(PhpParser.CloneExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#assignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignable(PhpParser.AssignableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#arrayCreation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreation(PhpParser.ArrayCreationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#lambdaFunctionExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaFunctionExpr(PhpParser.LambdaFunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#newExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(PhpParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(PhpParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#yieldExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYieldExpression(PhpParser.YieldExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#arrayItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayItemList(PhpParser.ArrayItemListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#arrayItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayItem(PhpParser.ArrayItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#lambdaFunctionUseVars}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaFunctionUseVars(PhpParser.LambdaFunctionUseVarsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#lambdaFunctionUseVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaFunctionUseVar(PhpParser.LambdaFunctionUseVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#qualifiedStaticTypeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedStaticTypeRef(PhpParser.QualifiedStaticTypeRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRef(PhpParser.TypeRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#anoymousClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnoymousClass(PhpParser.AnoymousClassContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#indirectTypeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectTypeRef(PhpParser.IndirectTypeRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#qualifiedNamespaceName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedNamespaceName(PhpParser.QualifiedNamespaceNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#namespaceNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceNameList(PhpParser.NamespaceNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#namespaceNameTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceNameTail(PhpParser.NamespaceNameTailContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#qualifiedNamespaceNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedNamespaceNameList(PhpParser.QualifiedNamespaceNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(PhpParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#actualArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualArgument(PhpParser.ActualArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#constantInititalizer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantInititalizer(PhpParser.ConstantInititalizerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#constantArrayItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantArrayItemList(PhpParser.ConstantArrayItemListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#constantArrayItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantArrayItem(PhpParser.ConstantArrayItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(PhpParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#literalConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralConstant(PhpParser.LiteralConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#numericConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericConstant(PhpParser.NumericConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#classConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassConstant(PhpParser.ClassConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#stringConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringConstant(PhpParser.StringConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(PhpParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#interpolatedStringPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterpolatedStringPart(PhpParser.InterpolatedStringPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#chainList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainList(PhpParser.ChainListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#chain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChain(PhpParser.ChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#chainOrigin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainOrigin(PhpParser.ChainOriginContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#memberAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberAccess(PhpParser.MemberAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(PhpParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#functionCallName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallName(PhpParser.FunctionCallNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#actualArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualArguments(PhpParser.ActualArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#chainBase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChainBase(PhpParser.ChainBaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#keyedFieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyedFieldName(PhpParser.KeyedFieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#keyedSimpleFieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyedSimpleFieldName(PhpParser.KeyedSimpleFieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#keyedVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyedVariable(PhpParser.KeyedVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#squareCurlyExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquareCurlyExpression(PhpParser.SquareCurlyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#assignmentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentList(PhpParser.AssignmentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#assignmentListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentListElement(PhpParser.AssignmentListElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#modifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModifier(PhpParser.ModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(PhpParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#memberModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberModifier(PhpParser.MemberModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#magicConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMagicConstant(PhpParser.MagicConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#magicMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMagicMethod(PhpParser.MagicMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(PhpParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PhpParser#castOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastOperation(PhpParser.CastOperationContext ctx);
}
