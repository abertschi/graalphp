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
 * Abstract visitor to apply a single method over all AST nodes In order to
 * continue the traverse, one should call node.childrenAccept();
 */
public abstract class ApplyAll extends AbstractVisitor {

	/**
	 * Performs the apply method over each node
	 * 
	 * @param node
	 */
	protected abstract boolean apply(ASTNode node);

	/**
	 * Performs the end visit method over each node
	 * 
	 * @param node
	 */
	public void endVisitNode(ASTNode node) {
		return;
	}

	@Override
	public boolean visit(ArrayElement arrayElement) {
		return apply(arrayElement);
	}

	@Override
	public boolean visit(ArraySpreadElement arraySpreadElement) {
		return apply(arraySpreadElement);
	}

	@Override
	public boolean visit(ArrayCreation arrayExpression) {
		return apply(arrayExpression);
	}

	@Override
	public boolean visit(Assignment assignment) {
		return apply(assignment);
	}

	@Override
	public boolean visit(ASTError astError) {
		return apply(astError);
	}

	@Override
	public boolean visit(BackTickExpression backTickExpression) {
		return apply(backTickExpression);
	}

	@Override
	public boolean visit(InfixExpression binaryOperation) {
		return apply(binaryOperation);
	}

	@Override
	public boolean visit(Block blockStatement) {
		return apply(blockStatement);
	}

	@Override
	public boolean visit(BreakStatement breakStatement) {
		return apply(breakStatement);
	}

	@Override
	public boolean visit(SwitchCase caseStatement) {
		return apply(caseStatement);
	}

	@Override
	public boolean visit(CastExpression castExpression) {
		return apply(castExpression);
	}

	@Override
	public boolean visit(CatchClause catchStatement) {
		return apply(catchStatement);
	}

	@Override
	public boolean visit(StaticConstantAccess classConstant) {
		return apply(classConstant);
	}

	@Override
	public boolean visit(ConstantDeclaration classConstantDeclaratio) {
		return apply(classConstantDeclaratio);
	}

	@Override
	public boolean visit(ClassDeclaration classDeclaration) {
		return apply(classDeclaration);
	}

	@Override
	public boolean visit(ClassInstanceCreation classInstanciation) {
		return apply(classInstanciation);
	}

	@Override
	public boolean visit(ClassName className) {
		return apply(className);
	}

	@Override
	public boolean visit(FieldsDeclaration classVariableDeclaration) {
		return apply(classVariableDeclaration);
	}

	@Override
	public boolean visit(CloneExpression cloneExpression) {
		return apply(cloneExpression);
	}

	@Override
	public boolean visit(Comment comment) {
		return apply(comment);
	}

	@Override
	public boolean visit(ConditionalExpression conditionalExpression) {
		return apply(conditionalExpression);
	}

	@Override
	public boolean visit(ContinueStatement continueStatement) {
		return apply(continueStatement);
	}

	@Override
	public boolean visit(DeclareStatement declareStatement) {
		return apply(declareStatement);
	}

	public boolean visit(Dispatch dispatch) {
		return apply(dispatch);
	}

	@Override
	public boolean visit(DoStatement doStatement) {
		return apply(doStatement);
	}

	@Override
	public boolean visit(EchoStatement echoStatement) {
		return apply(echoStatement);
	}

	@Override
	public boolean visit(EmptyStatement emptyStatement) {
		return apply(emptyStatement);
	}

	@Override
	public boolean visit(EmptyExpression emptyExpression) {
		return apply(emptyExpression);
	}

	@Override
	public boolean visit(ExpressionStatement expressionStatement) {
		return apply(expressionStatement);
	}

	@Override
	public boolean visit(FieldAccess filedAccess) {
		return apply(filedAccess);
	}

	@Override
	public boolean visit(ForEachStatement forEachStatement) {
		return apply(forEachStatement);
	}

	@Override
	public boolean visit(FormalParameter formalParameter) {
		return apply(formalParameter);
	}

	@Override
	public boolean visit(ForStatement forStatement) {
		return apply(forStatement);
	}

	@Override
	public boolean visit(FunctionDeclaration functionDeclaration) {
		return apply(functionDeclaration);
	}

	@Override
	public boolean visit(FunctionInvocation functionInvocation) {
		return apply(functionInvocation);
	}

	@Override
	public boolean visit(FunctionName functionName) {
		return apply(functionName);
	}

	@Override
	public boolean visit(GlobalStatement globalStatement) {
		return apply(globalStatement);
	}

	@Override
	public boolean visit(Identifier identifier) {
		return apply(identifier);
	}

	@Override
	public boolean visit(IfStatement ifStatement) {
		return apply(ifStatement);
	}

	@Override
	public boolean visit(IgnoreError ignoreError) {
		return apply(ignoreError);
	}

	@Override
	public boolean visit(Include include) {
		return apply(include);
	}

	@Override
	public boolean visit(ArrayAccess indexedVariable) {
		return apply(indexedVariable);
	}

	@Override
	public boolean visit(InLineHtml inLineHtml) {
		return apply(inLineHtml);
	}

	@Override
	public boolean visit(InstanceOfExpression instanceOfExpression) {
		return apply(instanceOfExpression);
	}

	@Override
	public boolean visit(InterfaceDeclaration interfaceDeclaration) {
		return apply(interfaceDeclaration);
	}

	@Override
	public boolean visit(ListVariable listVariable) {
		return apply(listVariable);
	}

	@Override
	public boolean visit(MethodDeclaration classMethodDeclaration) {
		return apply(classMethodDeclaration);
	}

	@Override
	public boolean visit(MethodInvocation methodInvocation) {
		return apply(methodInvocation);
	}

	@Override
	public boolean visit(ParenthesisExpression parenthesisExpression) {
		return apply(parenthesisExpression);
	}

	@Override
	public boolean visit(PostfixExpression postfixExpressions) {
		return apply(postfixExpressions);
	}

	@Override
	public boolean visit(PrefixExpression prefixExpression) {
		return apply(prefixExpression);
	}

	@Override
	public boolean visit(Program program) {
		return apply(program);
	}

	@Override
	public boolean visit(Quote quote) {
		return apply(quote);
	}

	@Override
	public boolean visit(Reference reference) {
		return apply(reference);
	}

	@Override
	public boolean visit(ReflectionVariable reflectionVariable) {
		return apply(reflectionVariable);
	}

	@Override
	public boolean visit(ReturnStatement returnStatement) {
		return apply(returnStatement);
	}

	@Override
	public boolean visit(ReturnType returnType) {
		return apply(returnType);
	}

	@Override
	public boolean visit(Scalar scalar) {
		return apply(scalar);
	}

	@Override
	public boolean visit(SingleFieldDeclaration singleFieldDeclaration) {
		return apply(singleFieldDeclaration);
	}

	@Override
	public boolean visit(StaticFieldAccess staticMember) {
		return apply(staticMember);
	}

	@Override
	public boolean visit(StaticMethodInvocation staticMethodInvocation) {
		return apply(staticMethodInvocation);
	}

	@Override
	public boolean visit(StaticStatement staticStatement) {
		return apply(staticStatement);
	}

	@Override
	public boolean visit(SwitchStatement switchStatement) {
		return apply(switchStatement);
	}

	@Override
	public boolean visit(ThrowStatement throwStatement) {
		return apply(throwStatement);
	}

	@Override
	public boolean visit(TryStatement tryStatement) {
		return apply(tryStatement);
	}

	@Override
	public boolean visit(UnaryOperation unaryOperation) {
		return apply(unaryOperation);
	}

	@Override
	public boolean visit(Variable variable) {
		return apply(variable);
	}

	@Override
	public boolean visit(WhileStatement whileStatement) {
		return apply(whileStatement);
	}

	@Override
	public boolean visit(NamespaceName name) {
		return apply(name);
	}

	@Override
	public boolean visit(NamespaceDeclaration decl) {
		return apply(decl);
	}

	@Override
	public boolean visit(UseStatementPart usePart) {
		return apply(usePart);
	}

	@Override
	public boolean visit(UseStatement statement) {
		return apply(statement);
	}

	@Override
	public boolean visit(LambdaFunctionDeclaration func) {
		return apply(func);
	}

	@Override
	public boolean visit(ArrowFunctionDeclaration func) {
		return apply(func);
	}

	@Override
	public boolean visit(GotoLabel gotoLabel) {
		return apply(gotoLabel);
	}

	@Override
	public boolean visit(GotoStatement gotoStatement) {
		return apply(gotoStatement);
	}

	@Override
	public void endVisit(ArrayAccess arrayAccess) {
		endVisitNode(arrayAccess);
	}

	@Override
	public void endVisit(ArrayCreation arrayCreation) {
		endVisitNode(arrayCreation);
	}

	@Override
	public void endVisit(ArrayElement arrayElement) {
		endVisitNode(arrayElement);
	}

	@Override
	public void endVisit(ArraySpreadElement arraySpreadElement) {
		endVisitNode(arraySpreadElement);
	}

	@Override
	public void endVisit(ASTError astError) {
		endVisitNode(astError);
	}

	@Override
	public void endVisit(BackTickExpression backTickExpression) {
		endVisitNode(backTickExpression);
	}

	@Override
	public void endVisit(Block block) {
		endVisitNode(block);
	}

	@Override
	public void endVisit(BreakStatement breakStatement) {
		endVisitNode(breakStatement);
	}

	@Override
	public void endVisit(CastExpression castExpression) {
		endVisitNode(castExpression);
	}

	@Override
	public void endVisit(CatchClause catchClause) {
		endVisitNode(catchClause);
	}

	@Override
	public void endVisit(ConstantDeclaration classConstantDeclaration) {
		endVisitNode(classConstantDeclaration);
	}

	@Override
	public void endVisit(ClassDeclaration classDeclaration) {
		endVisitNode(classDeclaration);
	}

	@Override
	public void endVisit(ClassInstanceCreation classInstanceCreation) {
		endVisitNode(classInstanceCreation);
	}

	@Override
	public void endVisit(ClassName className) {
		endVisitNode(className);
	}

	@Override
	public void endVisit(CloneExpression cloneExpression) {
		endVisitNode(cloneExpression);
	}

	@Override
	public void endVisit(Comment comment) {
		endVisitNode(comment);
	}

	@Override
	public void endVisit(ConditionalExpression conditionalExpression) {
		endVisitNode(conditionalExpression);
	}

	@Override
	public void endVisit(ContinueStatement continueStatement) {
		endVisitNode(continueStatement);
	}

	@Override
	public void endVisit(DeclareStatement declareStatement) {
		endVisitNode(declareStatement);
	}

	@Override
	public void endVisit(DoStatement doStatement) {
		endVisitNode(doStatement);
	}

	@Override
	public void endVisit(EchoStatement echoStatement) {
		endVisitNode(echoStatement);
	}

	@Override
	public void endVisit(EmptyStatement emptyStatement) {
		endVisitNode(emptyStatement);
	}

	@Override
	public void endVisit(EmptyExpression emptyExpression) {
		endVisitNode(emptyExpression);
	}

	@Override
	public void endVisit(ExpressionStatement expressionStatement) {
		endVisitNode(expressionStatement);
	}

	@Override
	public void endVisit(FieldAccess fieldAccess) {
		endVisitNode(fieldAccess);
	}

	@Override
	public void endVisit(FieldsDeclaration fieldsDeclaration) {
		endVisitNode(fieldsDeclaration);
	}

	@Override
	public void endVisit(ForEachStatement forEachStatement) {
		endVisitNode(forEachStatement);
	}

	@Override
	public void endVisit(FormalParameter formalParameter) {
		endVisitNode(formalParameter);
	}

	@Override
	public void endVisit(ForStatement forStatement) {
		endVisitNode(forStatement);
	}

	@Override
	public void endVisit(FunctionDeclaration functionDeclaration) {
		endVisitNode(functionDeclaration);
	}

	@Override
	public void endVisit(FunctionInvocation functionInvocation) {
		endVisitNode(functionInvocation);
	}

	@Override
	public void endVisit(FunctionName functionName) {
		endVisitNode(functionName);
	}

	@Override
	public void endVisit(GlobalStatement globalStatement) {
		endVisitNode(globalStatement);
	}

	@Override
	public void endVisit(Identifier identifier) {
		endVisitNode(identifier);
	}

	@Override
	public void endVisit(IfStatement ifStatement) {
		endVisitNode(ifStatement);
	}

	@Override
	public void endVisit(IgnoreError ignoreError) {
		endVisitNode(ignoreError);
	}

	@Override
	public void endVisit(Include include) {
		endVisitNode(include);
	}

	@Override
	public void endVisit(InfixExpression infixExpression) {
		endVisitNode(infixExpression);
	}

	@Override
	public void endVisit(InLineHtml inLineHtml) {
		endVisitNode(inLineHtml);
	}

	@Override
	public void endVisit(InstanceOfExpression instanceOfExpression) {
		endVisitNode(instanceOfExpression);
	}

	@Override
	public void endVisit(InterfaceDeclaration interfaceDeclaration) {
		endVisitNode(interfaceDeclaration);
	}

	@Override
	public void endVisit(ListVariable listVariable) {
		endVisitNode(listVariable);
	}

	@Override
	public void endVisit(MethodDeclaration methodDeclaration) {
		endVisitNode(methodDeclaration);
	}

	@Override
	public void endVisit(MethodInvocation methodInvocation) {
		endVisitNode(methodInvocation);
	}

	@Override
	public void endVisit(ParenthesisExpression parenthesisExpression) {
		endVisitNode(parenthesisExpression);
	}

	@Override
	public void endVisit(PostfixExpression postfixExpression) {
		endVisitNode(postfixExpression);
	}

	@Override
	public void endVisit(PrefixExpression prefixExpression) {
		endVisitNode(prefixExpression);
	}

	@Override
	public void endVisit(Program program) {
		endVisitNode(program);
	}

	@Override
	public void endVisit(Quote quote) {
		endVisitNode(quote);
	}

	@Override
	public void endVisit(Reference reference) {
		endVisitNode(reference);
	}

	@Override
	public void endVisit(ReflectionVariable reflectionVariable) {
		endVisitNode(reflectionVariable);
	}

	@Override
	public void endVisit(ReturnStatement returnStatement) {
		endVisitNode(returnStatement);
	}

	@Override
	public void endVisit(ReturnType returnType) {
		endVisitNode(returnType);
	}

	@Override
	public void endVisit(Scalar scalar) {
		endVisitNode(scalar);
	}

	@Override
	public void endVisit(SingleFieldDeclaration singleFieldDeclaration) {
		endVisitNode(singleFieldDeclaration);
	}

	@Override
	public void endVisit(StaticConstantAccess staticConstantAccess) {
		endVisitNode(staticConstantAccess);
	}

	@Override
	public void endVisit(StaticFieldAccess staticFieldAccess) {
		endVisitNode(staticFieldAccess);
	}

	@Override
	public void endVisit(StaticMethodInvocation staticMethodInvocation) {
		endVisitNode(staticMethodInvocation);
	}

	@Override
	public void endVisit(StaticStatement staticStatement) {
		endVisitNode(staticStatement);
	}

	@Override
	public void endVisit(SwitchCase switchCase) {
		endVisitNode(switchCase);
	}

	@Override
	public void endVisit(SwitchStatement switchStatement) {
		endVisitNode(switchStatement);
	}

	@Override
	public void endVisit(ThrowStatement throwStatement) {
		endVisitNode(throwStatement);
	}

	@Override
	public void endVisit(TryStatement tryStatement) {
		endVisitNode(tryStatement);
	}

	@Override
	public void endVisit(UnaryOperation unaryOperation) {
		endVisitNode(unaryOperation);
	}

	@Override
	public void endVisit(Variable variable) {
		endVisitNode(variable);
	}

	@Override
	public void endVisit(WhileStatement whileStatement) {
		endVisitNode(whileStatement);
	}

	@Override
	public void endVisit(Assignment assignment) {
		endVisitNode(assignment);
	}

	@Override
	public void endVisit(NamespaceName name) {
		endVisitNode(name);
	}

	@Override
	public void endVisit(NamespaceDeclaration decl) {
		endVisitNode(decl);
	}

	@Override
	public void endVisit(UseStatementPart part) {
		endVisitNode(part);
	}

	@Override
	public void endVisit(UseStatement useStatement) {
		endVisitNode(useStatement);
	}

	@Override
	public void endVisit(LambdaFunctionDeclaration func) {
		endVisitNode(func);
	}

	@Override
	public void endVisit(ArrowFunctionDeclaration func) {
		endVisitNode(func);
	}

	@Override
	public void endVisit(GotoStatement gotoStatement) {
		endVisitNode(gotoStatement);
	}

	@Override
	public void endVisit(GotoLabel gotoLabel) {
		endVisitNode(gotoLabel);
	}

	// php5.4 starts

	@Override
	public boolean visit(FullyQualifiedTraitMethodReference node) {
		return apply(node);
	}

	@Override
	public void endVisit(FullyQualifiedTraitMethodReference node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitAlias node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitAlias node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitAliasStatement node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitAliasStatement node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitDeclaration node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitDeclaration node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitPrecedence node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitPrecedence node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitPrecedenceStatement node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitPrecedenceStatement node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(TraitUseStatement node) {
		return apply(node);
	}

	@Override
	public void endVisit(TraitUseStatement node) {
		endVisitNode(node);
	}

	// php5.4 ends

	// php5.5
	@Override
	public boolean visit(YieldExpression node) {
		return apply(node);
	}

	@Override
	public void endVisit(YieldExpression node) {
		endVisitNode(node);
	}

	@Override
	public boolean visit(FinallyClause node) {
		return apply(node);
	}

	@Override
	public void endVisit(FinallyClause node) {
		endVisitNode(node);
	}

}
