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
 * Trivial (empty) implementation of {@link Visitor} This visitor traverses over
 * the AST by default
 * 
 * @see Visitor
 */
public abstract class AbstractVisitor implements Visitor {

	/**
	 * @see Visitor#preVisit(ASTNode)
	 */
	@Override
	public void preVisit(ASTNode node) {
		// default implementation: do nothing
	}

	/**
	 * @see Visitor#postVisit(ASTNode)
	 */
	@Override
	public void postVisit(ASTNode node) {
		// default implementation: do nothing
	}

	@Override
	public boolean visit(ASTNode node) {
		return true;
	}

	@Override
	public boolean visit(ArrayAccess arrayAccess) {
		return true;
	}

	@Override
	public boolean visit(ArrayCreation arrayCreation) {
		return true;
	}

	@Override
	public boolean visit(ArrayElement arrayElement) {
		return true;
	}

	@Override
	public boolean visit(ArraySpreadElement arraySpreadElement) {
		return true;
	}

	@Override
	public boolean visit(Assignment assignment) {
		return true;
	}

	@Override
	public boolean visit(ASTError astError) {
		return true;
	}

	@Override
	public boolean visit(BackTickExpression backTickExpression) {
		return true;
	}

	@Override
	public boolean visit(Block block) {
		return true;
	}

	@Override
	public boolean visit(BreakStatement breakStatement) {
		return true;
	}

	@Override
	public boolean visit(CastExpression castExpression) {
		return true;
	}

	@Override
	public boolean visit(CatchClause catchClause) {
		return true;
	}

	@Override
	public boolean visit(ConstantDeclaration classConstantDeclaration) {
		return true;
	}

	@Override
	public boolean visit(ClassDeclaration classDeclaration) {
		return true;
	}

	@Override
	public boolean visit(ClassInstanceCreation classInstanceCreation) {
		return true;
	}

	@Override
	public boolean visit(ClassName className) {
		return true;
	}

	@Override
	public boolean visit(CloneExpression cloneExpression) {
		return true;
	}

	@Override
	public boolean visit(Comment comment) {
		return true;
	}

	@Override
	public boolean visit(ConditionalExpression conditionalExpression) {
		return true;
	}

	@Override
	public boolean visit(ContinueStatement continueStatement) {
		return true;
	}

	@Override
	public boolean visit(DeclareStatement declareStatement) {
		return true;
	}

	@Override
	public boolean visit(DoStatement doStatement) {
		return true;
	}

	@Override
	public boolean visit(EchoStatement echoStatement) {
		return true;
	}

	@Override
	public boolean visit(EmptyStatement emptyStatement) {
		return true;
	}

	@Override
	public boolean visit(EmptyExpression emptyExpression) {
		return true;
	}

	@Override
	public boolean visit(ExpressionStatement expressionStatement) {
		return true;
	}

	@Override
	public boolean visit(FieldAccess fieldAccess) {
		return true;
	}

	@Override
	public boolean visit(FieldsDeclaration fieldsDeclaration) {
		return true;
	}

	@Override
	public boolean visit(ForEachStatement forEachStatement) {
		return true;
	}

	@Override
	public boolean visit(FormalParameter formalParameter) {
		return true;
	}

	@Override
	public boolean visit(ForStatement forStatement) {
		return true;
	}

	@Override
	public boolean visit(FunctionDeclaration functionDeclaration) {
		return true;
	}

	@Override
	public boolean visit(FunctionInvocation functionInvocation) {
		return true;
	}

	@Override
	public boolean visit(FunctionName functionName) {
		return true;
	}

	@Override
	public boolean visit(GlobalStatement globalStatement) {
		return true;
	}

	@Override
	public boolean visit(GotoLabel gotoLabel) {
		return true;
	}

	@Override
	public boolean visit(GotoStatement gotoStatement) {
		return true;
	}

	@Override
	public boolean visit(Identifier identifier) {
		return true;
	}

	@Override
	public boolean visit(IfStatement ifStatement) {
		return true;
	}

	@Override
	public boolean visit(IgnoreError ignoreError) {
		return true;
	}

	@Override
	public boolean visit(Include include) {
		return true;
	}

	@Override
	public boolean visit(InfixExpression infixExpression) {
		return true;
	}

	@Override
	public boolean visit(InLineHtml inLineHtml) {
		return true;
	}

	@Override
	public boolean visit(InstanceOfExpression instanceOfExpression) {
		return true;
	}

	@Override
	public boolean visit(InterfaceDeclaration interfaceDeclaration) {
		return true;
	}

	@Override
	public boolean visit(LambdaFunctionDeclaration lambdaFunctionDeclaration) {
		return true;
	}

	@Override
	public boolean visit(ArrowFunctionDeclaration arrowFunctionDeclaration) {
		return true;
	}

	@Override
	public boolean visit(AnonymousClassDeclaration anonymousClassDeclaration) {
		return true;
	}

	@Override
	public boolean visit(ListVariable listVariable) {
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration methodDeclaration) {
		return true;
	}

	@Override
	public boolean visit(MethodInvocation methodInvocation) {
		return true;
	}

	@Override
	public boolean visit(NamespaceDeclaration namespaceDeclaration) {
		return true;
	}

	@Override
	public boolean visit(NamespaceName namespaceName) {
		return true;
	}

	@Override
	public boolean visit(ParenthesisExpression parenthesisExpression) {
		return true;
	}

	@Override
	public boolean visit(PostfixExpression postfixExpression) {
		return true;
	}

	@Override
	public boolean visit(PrefixExpression prefixExpression) {
		return true;
	}

	@Override
	public boolean visit(Program program) {
		return true;
	}

	@Override
	public boolean visit(Quote quote) {
		return true;
	}

	@Override
	public boolean visit(Reference reference) {
		return true;
	}

	@Override
	public boolean visit(ReflectionVariable reflectionVariable) {
		return true;
	}

	@Override
	public boolean visit(ReturnStatement returnStatement) {
		return true;
	}

	@Override
	public boolean visit(ReturnType returnType) {
		return true;
	}

	@Override
	public boolean visit(YieldExpression YieldExpression) {
		return true;
	}

	@Override
	public boolean visit(Scalar scalar) {
		return true;
	}

	@Override
	public boolean visit(SingleFieldDeclaration singleFieldDeclaration) {
		return true;
	}

	@Override
	public boolean visit(StaticConstantAccess classConstantAccess) {
		return true;
	}

	@Override
	public boolean visit(StaticFieldAccess staticFieldAccess) {
		return true;
	}

	@Override
	public boolean visit(StaticMethodInvocation staticMethodInvocation) {
		return true;
	}

	@Override
	public boolean visit(StaticStatement staticStatement) {
		return true;
	}

	@Override
	public boolean visit(SwitchCase switchCase) {
		return true;
	}

	@Override
	public boolean visit(SwitchStatement switchStatement) {
		return true;
	}

	@Override
	public boolean visit(ThrowStatement throwStatement) {
		return true;
	}

	@Override
	public boolean visit(TryStatement tryStatement) {
		return true;
	}

	@Override
	public boolean visit(UnaryOperation unaryOperation) {
		return true;
	}

	@Override
	public boolean visit(UseStatement useStatement) {
		return true;
	}

	@Override
	public boolean visit(UseStatementPart useStatementPart) {
		return true;
	}

	@Override
	public boolean visit(Variable variable) {
		return true;
	}

	@Override
	public boolean visit(WhileStatement whileStatement) {
		return true;
	}

	@Override
	public void endVisit(ArrayAccess arrayAccess) {
	}

	@Override
	public void endVisit(ArrayCreation arrayCreation) {
	}

	@Override
	public void endVisit(ArrayElement arrayElement) {
	}

	@Override
	public void endVisit(ArraySpreadElement arraySpreadElement) {
	}

	@Override
	public void endVisit(ASTError astError) {
	}

	@Override
	public void endVisit(BackTickExpression backTickExpression) {
	}

	@Override
	public void endVisit(Block block) {
	}

	@Override
	public void endVisit(BreakStatement breakStatement) {
	}

	@Override
	public void endVisit(CastExpression castExpression) {
	}

	@Override
	public void endVisit(CatchClause catchClause) {
	}

	@Override
	public void endVisit(ConstantDeclaration classConstantDeclaration) {
	}

	@Override
	public void endVisit(ClassDeclaration classDeclaration) {
	}

	@Override
	public void endVisit(ClassInstanceCreation classInstanceCreation) {
	}

	@Override
	public void endVisit(ClassName className) {
	}

	@Override
	public void endVisit(CloneExpression cloneExpression) {
	}

	@Override
	public void endVisit(Comment comment) {
	}

	@Override
	public void endVisit(ConditionalExpression conditionalExpression) {
	}

	@Override
	public void endVisit(ContinueStatement continueStatement) {
	}

	@Override
	public void endVisit(DeclareStatement declareStatement) {
	}

	@Override
	public void endVisit(DoStatement doStatement) {
	}

	@Override
	public void endVisit(EchoStatement echoStatement) {
	}

	@Override
	public void endVisit(EmptyStatement emptyStatement) {
	}

	@Override
	public void endVisit(EmptyExpression emptyExpression) {
	}

	@Override
	public void endVisit(ExpressionStatement expressionStatement) {
	}

	@Override
	public void endVisit(FieldAccess fieldAccess) {
	}

	@Override
	public void endVisit(FieldsDeclaration fieldsDeclaration) {
	}

	@Override
	public void endVisit(ForEachStatement forEachStatement) {
	}

	@Override
	public void endVisit(FormalParameter formalParameter) {
	}

	@Override
	public void endVisit(ForStatement forStatement) {
	}

	@Override
	public void endVisit(FunctionDeclaration functionDeclaration) {
	}

	@Override
	public void endVisit(FunctionInvocation functionInvocation) {
	}

	@Override
	public void endVisit(FunctionName functionName) {
	}

	@Override
	public void endVisit(GlobalStatement globalStatement) {
	}

	@Override
	public void endVisit(GotoLabel gotoLabel) {
	}

	@Override
	public void endVisit(GotoStatement gotoStatement) {
	}

	@Override
	public void endVisit(Identifier identifier) {
	}

	@Override
	public void endVisit(IfStatement ifStatement) {
	}

	@Override
	public void endVisit(IgnoreError ignoreError) {
	}

	@Override
	public void endVisit(Include include) {
	}

	@Override
	public void endVisit(InfixExpression infixExpression) {
	}

	@Override
	public void endVisit(InLineHtml inLineHtml) {
	}

	@Override
	public void endVisit(InstanceOfExpression instanceOfExpression) {
	}

	@Override
	public void endVisit(InterfaceDeclaration interfaceDeclaration) {
	}

	@Override
	public void endVisit(LambdaFunctionDeclaration lambdaFunctionDeclaration) {
	}

	@Override
	public void endVisit(ArrowFunctionDeclaration arrowFunctionDeclaration) {
	}

	@Override
	public void endVisit(AnonymousClassDeclaration anonymousClassDeclaration) {
	}

	@Override
	public void endVisit(ListVariable listVariable) {
	}

	@Override
	public void endVisit(MethodDeclaration methodDeclaration) {
	}

	@Override
	public void endVisit(MethodInvocation methodInvocation) {
	}

	@Override
	public void endVisit(NamespaceDeclaration namespaceDeclaration) {
	}

	@Override
	public void endVisit(NamespaceName namespaceName) {
	}

	@Override
	public void endVisit(ParenthesisExpression parenthesisExpression) {
	}

	@Override
	public void endVisit(PostfixExpression postfixExpression) {
	}

	@Override
	public void endVisit(PrefixExpression prefixExpression) {
	}

	@Override
	public void endVisit(Program program) {
	}

	@Override
	public void endVisit(Quote quote) {
	}

	@Override
	public void endVisit(Reference reference) {
	}

	@Override
	public void endVisit(ReflectionVariable reflectionVariable) {
	}

	@Override
	public void endVisit(ReturnStatement returnStatement) {
	}

	@Override
	public void endVisit(ReturnType returnType) {
	}

	@Override
	public void endVisit(YieldExpression yieldExpression) {
	}

	@Override
	public void endVisit(Scalar scalar) {
	}

	@Override
	public void endVisit(SingleFieldDeclaration singleFieldDeclaration) {
	}

	@Override
	public void endVisit(StaticConstantAccess staticConstantAccess) {
	}

	@Override
	public void endVisit(StaticFieldAccess staticFieldAccess) {
	}

	@Override
	public void endVisit(StaticMethodInvocation staticMethodInvocation) {
	}

	@Override
	public void endVisit(StaticStatement staticStatement) {
	}

	@Override
	public void endVisit(SwitchCase switchCase) {
	}

	@Override
	public void endVisit(SwitchStatement switchStatement) {
	}

	@Override
	public void endVisit(ThrowStatement throwStatement) {
	}

	@Override
	public void endVisit(TryStatement tryStatement) {
	}

	@Override
	public void endVisit(UnaryOperation unaryOperation) {
	}

	@Override
	public void endVisit(UseStatement useStatement) {
	}

	@Override
	public void endVisit(UseStatementPart useStatementPart) {
	}

	@Override
	public void endVisit(Variable variable) {
	}

	@Override
	public void endVisit(WhileStatement whileStatement) {
	}

	@Override
	public void endVisit(Assignment assignment) {
	}

	@Override
	public void endVisit(ASTNode node) {
	}

	// php5.4 starts

	@Override
	public boolean visit(FullyQualifiedTraitMethodReference node) {
		return true;
	}

	@Override
	public void endVisit(FullyQualifiedTraitMethodReference node) {
	}

	@Override
	public boolean visit(TraitAlias node) {
		return true;
	}

	@Override
	public void endVisit(TraitAlias node) {
	}

	@Override
	public boolean visit(TraitAliasStatement node) {
		return true;
	}

	@Override
	public void endVisit(TraitAliasStatement node) {
	}

	@Override
	public boolean visit(TraitDeclaration node) {
		return true;
	}

	@Override
	public void endVisit(TraitDeclaration node) {
	}

	@Override
	public boolean visit(TraitPrecedence node) {
		return true;
	}

	@Override
	public void endVisit(TraitPrecedence node) {
	}

	@Override
	public boolean visit(TraitPrecedenceStatement node) {
		return true;
	}

	@Override
	public void endVisit(TraitPrecedenceStatement node) {
	}

	@Override
	public boolean visit(TraitUseStatement node) {
		return true;
	}

	@Override
	public void endVisit(TraitUseStatement node) {
	}

	// php5.4 ends

	@Override
	public boolean visit(FinallyClause node) {
		return true;
	}

	@Override
	public void endVisit(FinallyClause node) {
	}
}
