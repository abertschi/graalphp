/*******************************************************************************
 * Copyright (c) 2009-2019 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.core.ast.visitor;

import org.eclipse.php.core.ast.nodes.*;

/**
 * A visitor for abstract syntax trees.
 * <p>
 * For each different concrete AST node type there is a method that visits the
 * given node to perform some arbitrary operation.
 * <p>
 * Subclasses may implement this method as needed.
 * <p>
 * 
 * @author Moshe S., Roy G. ,2007
 */
public interface Visitor {

	/**
	 * Visits the given AST node prior to the type-specific visit. (before
	 * <code>visit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * 
	 * @param node
	 *            the node to visit
	 */
	void preVisit(ASTNode node);

	/**
	 * Visits the given AST node following the type-specific visit (after
	 * <code>endVisit</code>).
	 * <p>
	 * The default implementation does nothing. Subclasses may reimplement.
	 * </p>
	 * 
	 * @param node
	 *            the node to visit
	 */
	void postVisit(ASTNode node);

	boolean visit(ArrayAccess arrayAccess);

	void endVisit(ArrayAccess arrayAccess);

	boolean visit(ArrayCreation arrayCreation);

	void endVisit(ArrayCreation arrayCreation);

	boolean visit(ArrayElement arrayElement);

	void endVisit(ArrayElement arrayElement);

	boolean visit(ArraySpreadElement arraySpreadElement);

	void endVisit(ArraySpreadElement arraySpreadElement);

	boolean visit(Assignment assignment);

	void endVisit(Assignment assignment);

	boolean visit(ASTError astError);

	void endVisit(ASTError astError);

	boolean visit(BackTickExpression backTickExpression);

	void endVisit(BackTickExpression backTickExpression);

	boolean visit(Block block);

	void endVisit(Block block);

	boolean visit(BreakStatement breakStatement);

	void endVisit(BreakStatement breakStatement);

	boolean visit(CastExpression castExpression);

	void endVisit(CastExpression castExpression);

	boolean visit(CatchClause catchClause);

	void endVisit(CatchClause catchClause);

	boolean visit(ConstantDeclaration classConstantDeclaration);

	void endVisit(ConstantDeclaration classConstantDeclaration);

	boolean visit(ClassDeclaration classDeclaration);

	void endVisit(ClassDeclaration classDeclaration);

	boolean visit(ClassInstanceCreation classInstanceCreation);

	void endVisit(ClassInstanceCreation classInstanceCreation);

	boolean visit(ClassName className);

	void endVisit(ClassName className);

	boolean visit(CloneExpression cloneExpression);

	void endVisit(CloneExpression cloneExpression);

	boolean visit(Comment comment);

	void endVisit(Comment comment);

	boolean visit(ConditionalExpression conditionalExpression);

	void endVisit(ConditionalExpression conditionalExpression);

	boolean visit(ContinueStatement continueStatement);

	void endVisit(ContinueStatement continueStatement);

	boolean visit(DeclareStatement declareStatement);

	void endVisit(DeclareStatement declareStatement);

	boolean visit(DoStatement doStatement);

	void endVisit(DoStatement doStatement);

	boolean visit(EchoStatement echoStatement);

	void endVisit(EchoStatement echoStatement);

	default boolean visit(EmptyExpression emptyExpression) {
		return true;
	}

	boolean visit(EmptyStatement emptyStatement);

	default void endVisit(EmptyExpression emptyExpression) {
	}

	void endVisit(EmptyStatement emptyStatement);

	boolean visit(ExpressionStatement expressionStatement);

	void endVisit(ExpressionStatement expressionStatement);

	boolean visit(FieldAccess fieldAccess);

	void endVisit(FieldAccess fieldAccess);

	boolean visit(FieldsDeclaration fieldsDeclaration);

	void endVisit(FieldsDeclaration fieldsDeclaration);

	boolean visit(ForEachStatement forEachStatement);

	void endVisit(ForEachStatement forEachStatement);

	boolean visit(FormalParameter formalParameter);

	void endVisit(FormalParameter formalParameter);

	boolean visit(ForStatement forStatement);

	void endVisit(ForStatement forStatement);

	boolean visit(FunctionDeclaration functionDeclaration);

	void endVisit(FunctionDeclaration functionDeclaration);

	boolean visit(FunctionInvocation functionInvocation);

	void endVisit(FunctionInvocation functionInvocation);

	boolean visit(FunctionName functionName);

	void endVisit(FunctionName functionName);

	boolean visit(GlobalStatement globalStatement);

	void endVisit(GlobalStatement globalStatement);

	boolean visit(GotoLabel gotoLabel);

	void endVisit(GotoLabel gotoLabel);

	boolean visit(GotoStatement gotoStatement);

	void endVisit(GotoStatement gotoStatement);

	boolean visit(Identifier identifier);

	void endVisit(Identifier identifier);

	boolean visit(IfStatement ifStatement);

	void endVisit(IfStatement ifStatement);

	boolean visit(IgnoreError ignoreError);

	void endVisit(IgnoreError ignoreError);

	boolean visit(Include include);

	void endVisit(Include include);

	boolean visit(InfixExpression infixExpression);

	void endVisit(InfixExpression infixExpression);

	boolean visit(InLineHtml inLineHtml);

	void endVisit(InLineHtml inLineHtml);

	boolean visit(InstanceOfExpression instanceOfExpression);

	void endVisit(InstanceOfExpression instanceOfExpression);

	boolean visit(InterfaceDeclaration interfaceDeclaration);

	void endVisit(InterfaceDeclaration interfaceDeclaration);

	boolean visit(LambdaFunctionDeclaration lambdaFunctionDeclaration);

	void endVisit(LambdaFunctionDeclaration lambdaFunctionDeclaration);

	boolean visit(ArrowFunctionDeclaration arrowFunctionDeclaration);

	void endVisit(ArrowFunctionDeclaration arrowFunctionDeclaration);

	boolean visit(ListVariable listVariable);

	void endVisit(ListVariable listVariable);

	boolean visit(MethodDeclaration methodDeclaration);

	void endVisit(MethodDeclaration methodDeclaration);

	boolean visit(MethodInvocation methodInvocation);

	void endVisit(MethodInvocation methodInvocation);

	boolean visit(NamespaceName namespaceName);

	void endVisit(NamespaceName namespaceName);

	boolean visit(NamespaceDeclaration namespaceDeclaration);

	void endVisit(NamespaceDeclaration namespaceDeclaration);

	boolean visit(ParenthesisExpression parenthesisExpression);

	void endVisit(ParenthesisExpression parenthesisExpression);

	boolean visit(PostfixExpression postfixExpression);

	void endVisit(PostfixExpression postfixExpression);

	boolean visit(PrefixExpression prefixExpression);

	void endVisit(PrefixExpression prefixExpression);

	boolean visit(Program program);

	void endVisit(Program program);

	boolean visit(Quote quote);

	void endVisit(Quote quote);

	boolean visit(Reference reference);

	void endVisit(Reference reference);

	boolean visit(ReflectionVariable reflectionVariable);

	void endVisit(ReflectionVariable reflectionVariable);

	boolean visit(ReturnStatement returnStatement);

	void endVisit(ReturnStatement returnStatement);

	boolean visit(ReturnType returnType);

	void endVisit(ReturnType returnType);

	boolean visit(Scalar scalar);

	void endVisit(Scalar scalar);

	boolean visit(SingleFieldDeclaration singleFieldDeclaration);

	void endVisit(SingleFieldDeclaration singleFieldDeclaration);

	boolean visit(StaticConstantAccess classConstantAccess);

	void endVisit(StaticConstantAccess staticConstantAccess);

	boolean visit(StaticFieldAccess staticFieldAccess);

	void endVisit(StaticFieldAccess staticFieldAccess);

	boolean visit(StaticMethodInvocation staticMethodInvocation);

	void endVisit(StaticMethodInvocation staticMethodInvocation);

	boolean visit(StaticStatement staticStatement);

	void endVisit(StaticStatement staticStatement);

	boolean visit(SwitchCase switchCase);

	void endVisit(SwitchCase switchCase);

	boolean visit(SwitchStatement switchStatement);

	void endVisit(SwitchStatement switchStatement);

	boolean visit(ThrowStatement throwStatement);

	void endVisit(ThrowStatement throwStatement);

	boolean visit(TryStatement tryStatement);

	void endVisit(TryStatement tryStatement);

	boolean visit(UnaryOperation unaryOperation);

	void endVisit(UnaryOperation unaryOperation);

	boolean visit(Variable variable);

	void endVisit(Variable variable);

	boolean visit(UseStatement useStatement);

	void endVisit(UseStatement useStatement);

	boolean visit(UseStatementPart useStatementPart);

	void endVisit(UseStatementPart useStatementPart);

	boolean visit(WhileStatement whileStatement);

	void endVisit(WhileStatement whileStatement);

	boolean visit(ASTNode node);

	void endVisit(ASTNode node);

	// php5.4 starts

	boolean visit(FullyQualifiedTraitMethodReference node);

	void endVisit(FullyQualifiedTraitMethodReference node);

	boolean visit(TraitAlias node);

	void endVisit(TraitAlias node);

	boolean visit(TraitAliasStatement node);

	void endVisit(TraitAliasStatement node);

	boolean visit(TraitDeclaration node);

	void endVisit(TraitDeclaration node);

	boolean visit(TraitPrecedence node);

	void endVisit(TraitPrecedence node);

	boolean visit(TraitPrecedenceStatement node);

	void endVisit(TraitPrecedenceStatement node);

	boolean visit(TraitUseStatement node);

	void endVisit(TraitUseStatement node);

	// php5.4 ends

	// php5.5
	boolean visit(YieldExpression YieldExpression);

	void endVisit(YieldExpression YieldExpression);

	boolean visit(FinallyClause YieldExpression);

	void endVisit(FinallyClause YieldExpression);

	// php7.0
	boolean visit(AnonymousClassDeclaration anonymousClassDeclaration);

	void endVisit(AnonymousClassDeclaration anonymousClassDeclaration);
}
