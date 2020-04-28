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
package org.eclipse.php.core.ast.match;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.php.core.ast.nodes.*;

/**
 * Concrete superclass and default implementation of an AST subtree matcher.
 * <p>
 * For example, to compute whether two ASTs subtrees are structurally
 * isomorphic, use <code>n1.subtreeMatch(new ASTMatcher(), n2)</code> where
 * <code>n1</code> and <code>n2</code> are the AST root nodes of the subtrees.
 * </p>
 * <p>
 * For each different concrete AST node type <i>T</i> there is a
 * <code>public boolean match(<i>T</i> node, Object other)</code> method that
 * matches the given node against another object (typically another AST node,
 * although this is not essential). The default implementations provided by this
 * class tests whether the other object is a node of the same type with
 * structurally isomorphic child subtrees. For nodes with list-valued
 * properties, the child nodes within the list are compared in order. For nodes
 * with multiple properties, the child nodes are compared in the order that most
 * closely corresponds to the lexical reading order of the source program. For
 * instance, for a type declaration node, the child ordering is: name,
 * superclass, superinterfaces, and body declarations.
 * </p>
 * <p>
 * Subclasses may override (extend or reimplement) some or all of the
 * <code>match</code> methods in order to define more specialized subtree
 * matchers.
 * </p>
 * 
 * @see ASTNode#subtreeMatch(ASTMatcher, Object)
 * @since 2.0
 */
public class ASTMatcher {

	/**
	 * Creates a new AST matcher instance.
	 * <p>
	 * For backwards compatibility, the matcher ignores tag elements below doc
	 * comments by default. Use {@link #ASTMatcher(boolean) ASTMatcher(true)}
	 * for a matcher that compares doc tags by default.
	 * </p>
	 */
	public ASTMatcher() {
	}

	/**
	 * Returns whether the given lists of AST nodes match pair wise according to
	 * <code>ASTNode.subtreeMatch</code>.
	 * <p>
	 * Note that this is a convenience method, useful for writing recursive
	 * subtree matchers.
	 * </p>
	 * 
	 * @param list1
	 *            the first list of AST nodes (element type:
	 *            <code>ASTNode</code>)
	 * @param list2
	 *            the second list of AST nodes (element type:
	 *            <code>ASTNode</code>)
	 * @return <code>true</code> if the lists have the same number of elements
	 *         and match pair-wise according to
	 *         <code>ASTNode.subtreeMatch</code>
	 * @see ASTNode#subtreeMatch(ASTMatcher matcher, Object other)
	 */
	public final boolean safeSubtreeListMatch(Collection<?> list1, Collection<?> list2) {
		int size1 = list1.size();
		int size2 = list2.size();
		if (size1 != size2) {
			return false;
		}
		for (Iterator<?> it1 = list1.iterator(), it2 = list2.iterator(); it1.hasNext();) {
			ASTNode n1 = (ASTNode) it1.next();
			ASTNode n2 = (ASTNode) it2.next();
			if (n1 == null && n2 == null) {
				continue;
			}

			if (n1 == null || n2 == null) {
				return false;
			}

			if (!n1.subtreeMatch(this, n2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns whether the given lists of AST nodes match pair wise according to
	 * <code>ASTNode.subtreeMatch</code>.
	 * <p>
	 * Note that this is a convenience method, useful for writing recursive
	 * subtree matchers.
	 * </p>
	 * 
	 * @param list1
	 *            the first array of AST expressions (element type:
	 *            <code>ASTNode</code>)
	 * @param list2
	 *            the second array of AST expressions (element type:
	 *            <code>ASTNode</code>)
	 * @return <code>true</code> if the arrays have the same number of elements
	 *         and match pair-wise according to
	 *         <code>ASTNode.subtreeMatch</code>
	 * @see ASTNode#subtreeMatch(ASTMatcher matcher, Object other)
	 */
	public final boolean safeSubtreeListMatch(Expression[] list1, Expression[] list2) {
		return safeSubtreeListMatch(Arrays.asList(list1), Arrays.asList(list2));
	}

	/**
	 * Returns whether the given lists of AST nodes match pair wise according to
	 * <code>ASTNode.subtreeMatch</code>.
	 * <p>
	 * Note that this is a convenience method, useful for writing recursive
	 * subtree matchers.
	 * </p>
	 * 
	 * @param list1
	 *            the first array of AST statements (element type:
	 *            <code>ASTNode</code>)
	 * @param list2
	 *            the second array of AST statements (element type:
	 *            <code>ASTNode</code>)
	 * @return <code>true</code> if the arrays have the same number of elements
	 *         and match pair-wise according to
	 *         <code>ASTNode.subtreeMatch</code>
	 * @see ASTNode#subtreeMatch(ASTMatcher matcher, Object other)
	 */
	public final boolean safeSubtreeListMatch(Statement[] list1, Statement[] list2) {
		return safeSubtreeListMatch(Arrays.asList(list1), Arrays.asList(list2));
	}

	/**
	 * Returns whether the given lists of AST nodes match pair wise according to
	 * <code>ASTNode.subtreeMatch</code>.
	 * <p>
	 * Note that this is a convenience method, useful for writing recursive
	 * subtree matchers.
	 * </p>
	 * 
	 * @param list1
	 *            the first array of AST nodes (element type:
	 *            <code>ASTNode</code>)
	 * @param list2
	 *            the second array of AST nodes (element type:
	 *            <code>ASTNode</code>)
	 * @return <code>true</code> if the arrays have the same number of elements
	 *         and match pair-wise according to
	 *         <code>ASTNode.subtreeMatch</code>
	 * @see ASTNode#subtreeMatch(ASTMatcher matcher, Object other)
	 */
	public final boolean safeSubtreeListMatch(ASTNode[] list1, ASTNode[] list2) {
		return safeSubtreeListMatch(Arrays.asList(list1), Arrays.asList(list2));
	}

	/**
	 * Returns whether the given nodes match according to
	 * <code>AST.subtreeMatch</code>. Returns <code>false</code> if one or the
	 * other of the nodes are <code>null</code>. Returns <code>true</code> if
	 * both nodes are <code>null</code>.
	 * <p>
	 * Note that this is a convenience method, useful for writing recursive
	 * subtree matchers.
	 * </p>
	 * 
	 * @param node1
	 *            the first AST node, or <code>null</code>; must be an instance
	 *            of <code>ASTNode</code>
	 * @param node2
	 *            the second AST node, or <code>null</code>; must be an instance
	 *            of <code>ASTNode</code>
	 * @return <code>true</code> if the nodes match according to
	 *         <code>AST.subtreeMatch</code> or both are <code>null</code>, and
	 *         <code>false</code> otherwise
	 * @see ASTNode#subtreeMatch(ASTMatcher, Object)
	 */
	public final boolean safeSubtreeMatch(Object node1, Object node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null) {
			return false;
		}
		// N.B. call subtreeMatch even node1==node2!=null
		return ((ASTNode) node1).subtreeMatch(this, node2);
	}

	/**
	 * Returns whether the given objects are equal according to
	 * <code>equals</code>. Returns <code>false</code> if either node is
	 * <code>null</code>.
	 * 
	 * @param o1
	 *            the first object, or <code>null</code>
	 * @param o2
	 *            the second object, or <code>null</code>
	 * @return <code>true</code> if the nodes are equal according to
	 *         <code>equals</code> or both <code>null</code>, and
	 *         <code>false</code> otherwise
	 */
	public static boolean safeEquals(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1.equals(o2);
	}

	/**
	 * Returns whether the given node and the other object match.
	 * <p>
	 * The default implementation provided by this class tests whether the other
	 * object is a node of the same type with structurally isomorphic child
	 * subtrees. Subclasses may override this method as needed.
	 * </p>
	 * 
	 * @param node
	 *            the node
	 * @param other
	 *            the other object, or <code>null</code>
	 * @return <code>true</code> if the subtree matches, or <code>false</code>
	 *         if they do not match or the other object has a different node
	 *         type or is <code>null</code>
	 * @since 3.1
	 */

	public boolean match(ArrayAccess node, Object other) {
		if (!(other instanceof ArrayAccess)) {
			return false;
		}
		ArrayAccess o = (ArrayAccess) other;

		return (safeSubtreeMatch(node.getName(), o.getName()) && safeSubtreeMatch(node.getIndex(), o.getIndex())
				&& safeEquals(node.getArrayType(), o.getArrayType()));
	}

	public boolean match(ArrayCreation node, Object other) {
		if (!(other instanceof ArrayCreation)) {
			return false;
		}
		ArrayCreation o = (ArrayCreation) other;

		return safeSubtreeListMatch(node.elements(), o.elements());
	}

	public boolean match(ArrayElement node, Object other) {
		if (!(other instanceof ArrayElement)) {
			return false;
		}
		ArrayElement o = (ArrayElement) other;

		return (safeSubtreeMatch(node.getKey(), o.getKey()) && safeSubtreeMatch(node.getValue(), o.getValue()));
	}

	public boolean match(ArraySpreadElement node, Object other) {
		if (!(other instanceof ArraySpreadElement)) {
			return false;
		}
		ArraySpreadElement o = (ArraySpreadElement) other;

		return safeSubtreeMatch(node.getValue(), o.getValue());
	}

	public boolean match(Assignment node, Object other) {
		if (!(other instanceof Assignment)) {
			return false;
		}
		Assignment o = (Assignment) other;

		return (safeEquals(node.getOperator(), o.getOperator())
				&& safeSubtreeMatch(node.getRightHandSide(), o.getRightHandSide())
				&& safeSubtreeMatch(node.getLeftHandSide(), o.getLeftHandSide()));
	}

	public boolean match(ASTError node, Object other) {
		// always return false since there is no comparison between 2 errors
		return false;
	}

	public boolean match(BackTickExpression node, Object other) {
		if (!(other instanceof BackTickExpression)) {
			return false;
		}
		BackTickExpression o = (BackTickExpression) other;

		return safeSubtreeListMatch(node.expressions(), o.expressions());
	}

	public boolean match(Block node, Object other) {
		if (!(other instanceof Block)) {
			return false;
		}
		Block o = (Block) other;

		return (safeEquals(node.isCurly(), o.isCurly()) && safeSubtreeListMatch(node.statements(), o.statements()));
	}

	public boolean match(BreakStatement node, Object other) {
		if (!(other instanceof BreakStatement)) {
			return false;
		}
		BreakStatement o = (BreakStatement) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(CastExpression node, Object other) {
		if (!(other instanceof CastExpression)) {
			return false;
		}
		CastExpression o = (CastExpression) other;

		return (safeEquals(node.getCastingType(), o.getCastingType())
				&& safeSubtreeMatch(node.getExpression(), o.getExpression()));
	}

	public boolean match(CatchClause node, Object other) {
		if (!(other instanceof CatchClause)) {
			return false;
		}
		CatchClause o = (CatchClause) other;

		return (safeSubtreeListMatch(node.getClassNames(), o.getClassNames())
				&& safeSubtreeMatch(node.getVariable(), o.getVariable())
				&& safeSubtreeMatch(node.getBody(), o.getBody()));
	}

	public boolean match(ConstantDeclaration node, Object other) {
		if (!(other instanceof ConstantDeclaration)) {
			return false;
		}
		ConstantDeclaration o = (ConstantDeclaration) other;

		return (safeSubtreeListMatch(node.initializers(), o.initializers())
				&& safeSubtreeListMatch(node.names(), o.names()));
	}

	public boolean match(ClassDeclaration node, Object other) {
		if (!(other instanceof ClassDeclaration)) {
			return false;
		}
		ClassDeclaration o = (ClassDeclaration) other;

		return (safeEquals(node.getModifier(), o.getModifier())
				&& safeSubtreeMatch(node.getSuperClass(), o.getSuperClass())
				&& match((TypeDeclaration) node, (TypeDeclaration) o));
	}

	private boolean match(TypeDeclaration node, Object other) {
		if (!(other instanceof TypeDeclaration)) {
			return false;
		}
		TypeDeclaration o = (TypeDeclaration) other;

		return (safeSubtreeMatch(node.getName(), o.getName()) && safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.interfaces(), o.interfaces()));
	}

	public boolean match(ClassInstanceCreation node, Object other) {
		if (!(other instanceof ClassInstanceCreation)) {
			return false;
		}
		ClassInstanceCreation o = (ClassInstanceCreation) other;

		return (safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& safeSubtreeListMatch(node.ctorParams(), o.ctorParams())
				&& safeSubtreeMatch(node.getAnonymousClassDeclaration(), o.getAnonymousClassDeclaration()));
	}

	public boolean match(ClassName node, Object other) {
		if (!(other instanceof ClassName)) {
			return false;
		}
		ClassName o = (ClassName) other;

		return safeSubtreeMatch(node.getName(), o.getName());
	}

	public boolean match(CloneExpression node, Object other) {
		if (!(other instanceof CloneExpression)) {
			return false;
		}
		CloneExpression o = (CloneExpression) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	// TODO - will implement in the future
	public boolean match(Comment node, Object other) {
		return true;
	}

	public boolean match(ConditionalExpression node, Object other) {
		if (!(other instanceof ConditionalExpression)) {
			return false;
		}
		ConditionalExpression o = (ConditionalExpression) other;

		return (safeSubtreeMatch(node.getCondition(), o.getCondition())
				&& safeSubtreeMatch(node.getIfTrue(), o.getIfTrue())
				&& safeSubtreeMatch(node.getIfFalse(), o.getIfFalse()))
				&& node.getOperatorType() == o.getOperatorType();
	}

	public boolean match(ContinueStatement node, Object other) {
		if (!(other instanceof ContinueStatement)) {
			return false;
		}
		ContinueStatement o = (ContinueStatement) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(DeclareStatement node, Object other) {
		if (!(other instanceof DeclareStatement)) {
			return false;
		}
		DeclareStatement o = (DeclareStatement) other;

		return (safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.directiveNames(), o.directiveNames())
				&& safeSubtreeListMatch(node.directiveValues(), o.directiveValues()));
	}

	public boolean match(DoStatement node, Object other) {
		if (!(other instanceof DoStatement)) {
			return false;
		}
		DoStatement o = (DoStatement) other;

		return (safeSubtreeMatch(node.getCondition(), o.getCondition())
				&& safeSubtreeMatch(node.getBody(), o.getBody()));
	}

	public boolean match(EchoStatement node, Object other) {
		if (!(other instanceof EchoStatement)) {
			return false;
		}
		EchoStatement o = (EchoStatement) other;

		return safeSubtreeListMatch(node.expressions(), o.expressions());
	}

	public boolean match(EmptyStatement node, Object other) {
		if (!(other instanceof EmptyStatement)) {
			return false;
		}

		// 2 empty statements are equal by definition
		return true;
	}

	public boolean match(EmptyExpression node, Object other) {
		if (!(other instanceof EmptyExpression)) {
			return false;
		}

		// 2 empty expressions are equal by definition
		return true;
	}

	public boolean match(ExpressionStatement node, Object other) {
		if (!(other instanceof ExpressionStatement)) {
			return false;
		}
		ExpressionStatement o = (ExpressionStatement) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());

	}

	public boolean match(FieldAccess node, Object other) {
		if (!(other instanceof FieldAccess)) {
			return false;
		}
		FieldAccess o = (FieldAccess) other;

		return (safeSubtreeMatch(node.getDispatcher(), o.getDispatcher())
				&& safeSubtreeMatch(node.getField(), o.getField()));
	}

	public boolean match(FieldsDeclaration node, Object other) {
		if (!(other instanceof FieldsDeclaration)) {
			return false;
		}
		FieldsDeclaration o = (FieldsDeclaration) other;

		return (safeEquals(node.getModifier(), o.getModifier())
				&& safeSubtreeListMatch(node.getInitialValues(), o.getInitialValues())
				&& safeSubtreeListMatch(node.getVariableNames(), o.getVariableNames()));
	}

	public boolean match(ForEachStatement node, Object other) {
		if (!(other instanceof ForEachStatement)) {
			return false;
		}
		ForEachStatement o = (ForEachStatement) other;

		return (safeSubtreeMatch(node.getExpression(), o.getExpression()) && safeSubtreeMatch(node.getKey(), o.getKey())
				&& safeSubtreeMatch(node.getValue(), o.getValue())
				&& safeSubtreeMatch(node.getStatement(), o.getStatement()));
	}

	public boolean match(FormalParameter node, Object other) {
		if (!(other instanceof FormalParameter)) {
			return false;
		}
		FormalParameter o = (FormalParameter) other;

		return (safeEquals(node.isMandatory(), o.isMandatory()) && safeEquals(node.isVariadic(), o.isVariadic())
				&& safeSubtreeMatch(node.getParameterType(), o.getParameterType())
				&& safeSubtreeMatch(node.getParameterName(), o.getParameterName())
				&& safeSubtreeMatch(node.getDefaultValue(), o.getDefaultValue()));
	}

	public boolean match(ForStatement node, Object other) {
		if (!(other instanceof ForStatement)) {
			return false;
		}
		ForStatement o = (ForStatement) other;

		return (safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.initializers(), o.initializers())
				&& safeSubtreeListMatch(node.conditions(), o.conditions())
				&& safeSubtreeListMatch(node.updaters(), o.updaters()));
	}

	public boolean match(FunctionDeclaration node, Object other) {
		if (!(other instanceof FunctionDeclaration)) {
			return false;
		}
		FunctionDeclaration o = (FunctionDeclaration) other;

		return (safeEquals(node.isReference(), o.isReference()) && safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeMatch(node.getFunctionName(), o.getFunctionName())
				&& safeSubtreeListMatch(node.formalParameters(), o.formalParameters())
				&& safeSubtreeMatch(node.getReturnType(), o.getReturnType()));
	}

	public boolean match(FunctionInvocation node, Object other) {
		if (!(other instanceof FunctionInvocation)) {
			return false;
		}
		FunctionInvocation o = (FunctionInvocation) other;

		return (safeSubtreeMatch(node.getFunctionName(), o.getFunctionName())
				&& safeSubtreeListMatch(node.parameters(), o.parameters()));
	}

	public boolean match(FunctionName node, Object other) {
		if (!(other instanceof FunctionName)) {
			return false;
		}
		FunctionName o = (FunctionName) other;

		return safeSubtreeMatch(node.getName(), o.getName());
	}

	public boolean match(GlobalStatement node, Object other) {
		if (!(other instanceof GlobalStatement)) {
			return false;
		}
		GlobalStatement o = (GlobalStatement) other;

		return safeSubtreeListMatch(node.variables(), o.variables());
	}

	public boolean match(Identifier node, Object other) {
		if (!(other instanceof Identifier)) {
			return false;
		}
		Identifier o = (Identifier) other;

		return safeEquals(node.getName(), o.getName()) && safeEquals(node.isNullable(), o.isNullable());
	}

	public boolean match(IfStatement node, Object other) {
		if (!(other instanceof IfStatement)) {
			return false;
		}
		IfStatement o = (IfStatement) other;

		return (safeSubtreeMatch(node.getCondition(), o.getCondition())
				&& safeSubtreeMatch(node.getTrueStatement(), o.getTrueStatement())
				&& safeSubtreeMatch(node.getFalseStatement(), o.getFalseStatement()));
	}

	public boolean match(IgnoreError node, Object other) {
		if (!(other instanceof IgnoreError)) {
			return false;
		}
		IgnoreError o = (IgnoreError) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(Include node, Object other) {
		if (!(other instanceof Include)) {
			return false;
		}
		Include o = (Include) other;

		return (safeEquals(node.getIncludeType(), o.getIncludeType())
				&& safeSubtreeMatch(node.getExpression(), o.getExpression()));
	}

	public boolean match(InfixExpression node, Object other) {
		if (!(other instanceof InfixExpression)) {
			return false;
		}
		InfixExpression o = (InfixExpression) other;

		return (safeEquals(node.getOperator(), o.getOperator()) && safeSubtreeMatch(node.getRight(), o.getRight())
				&& safeSubtreeMatch(node.getLeft(), o.getLeft()));
	}

	// TODO - need to check the contents of the html
	public boolean match(InLineHtml node, Object other) {
		if (!(other instanceof InLineHtml)) {
			return false;
		}

		return false;
	}

	public boolean match(InstanceOfExpression node, Object other) {
		if (!(other instanceof InstanceOfExpression)) {
			return false;
		}
		InstanceOfExpression o = (InstanceOfExpression) other;

		return (safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& safeSubtreeMatch(node.getExpression(), o.getExpression()));
	}

	public boolean match(InterfaceDeclaration node, Object other) {
		if (!(other instanceof InterfaceDeclaration)) {
			return false;
		}
		InterfaceDeclaration o = (InterfaceDeclaration) other;

		return match((TypeDeclaration) node, (TypeDeclaration) o);
	}

	public boolean match(ListVariable node, Object other) {
		if (!(other instanceof ListVariable)) {
			return false;
		}
		ListVariable o = (ListVariable) other;

		return safeSubtreeListMatch(node.variables(), o.variables());
	}

	public boolean match(MethodDeclaration node, Object other) {
		if (!(other instanceof MethodDeclaration)) {
			return false;
		}
		MethodDeclaration o = (MethodDeclaration) other;
		return (safeEquals(node.getModifier(), o.getModifier())
				&& safeSubtreeMatch(node.getFunction(), o.getFunction()));
	}

	public boolean match(MethodInvocation node, Object other) {
		if (!(other instanceof MethodInvocation)) {
			return false;
		}
		MethodInvocation o = (MethodInvocation) other;

		return (safeSubtreeMatch(node.getDispatcher(), o.getDispatcher())
				&& safeSubtreeMatch(node.getMethod(), o.getMethod()));
	}

	public boolean match(ParenthesisExpression node, Object other) {
		if (!(other instanceof ParenthesisExpression)) {
			return false;
		}
		ParenthesisExpression o = (ParenthesisExpression) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(PostfixExpression node, Object other) {
		if (!(other instanceof PostfixExpression)) {
			return false;
		}
		PostfixExpression o = (PostfixExpression) other;

		return (safeEquals(node.getOperator(), o.getOperator())
				&& safeSubtreeMatch(node.getVariable(), o.getVariable()));
	}

	public boolean match(PrefixExpression node, Object other) {
		if (!(other instanceof PrefixExpression)) {
			return false;
		}
		PrefixExpression o = (PrefixExpression) other;

		return (safeEquals(node.getOperator(), o.getOperator())
				&& safeSubtreeMatch(node.getVariable(), o.getVariable()));
	}

	public boolean match(Program node, Object other) {
		if (!(other instanceof Program)) {
			return false;
		}
		Program o = (Program) other;

		return (safeSubtreeListMatch(node.statements(), o.statements())
				&& safeSubtreeListMatch(((Map<?, ?>) node.comments()).values(), ((Map<?, ?>) o.comments()).values()));
	}

	public boolean match(Quote node, Object other) {
		if (!(other instanceof Quote)) {
			return false;
		}
		Quote o = (Quote) other;

		return (safeEquals(node.getQuoteType(), o.getQuoteType())
				&& safeSubtreeListMatch(node.expressions(), o.expressions()));
	}

	public boolean match(Reference node, Object other) {
		if (!(other instanceof Reference)) {
			return false;
		}
		Reference o = (Reference) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(ReflectionVariable node, Object other) {
		if (!(other instanceof ReflectionVariable)) {
			return false;
		}
		ReflectionVariable o = (ReflectionVariable) other;

		return (match((Variable) node, (Variable) o));
	}

	public boolean match(ReturnStatement node, Object other) {
		if (!(other instanceof ReturnStatement)) {
			return false;
		}
		ReturnStatement o = (ReturnStatement) other;

		return safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(YieldExpression node, Object other) {
		if (!(other instanceof YieldExpression)) {
			return false;
		}
		YieldExpression o = (YieldExpression) other;

		return safeSubtreeMatch(node.getKey(), o.getKey()) && safeSubtreeMatch(node.getExpression(), o.getExpression());
	}

	public boolean match(Scalar node, Object other) {
		if (!(other instanceof Scalar)) {
			return false;
		}
		Scalar o = (Scalar) other;

		return (safeEquals(node.getStringValue(), o.getStringValue())
				&& safeEquals(node.getScalarType(), o.getScalarType()));
	}

	public boolean match(SingleFieldDeclaration node, Object other) {
		if (!(other instanceof SingleFieldDeclaration)) {
			return false;
		}
		SingleFieldDeclaration o = (SingleFieldDeclaration) other;

		return (safeSubtreeMatch(node.getName(), o.getName()) && safeSubtreeMatch(node.getValue(), o.getValue()));
	}

	public boolean match(StaticConstantAccess node, Object other) {
		if (!(other instanceof StaticConstantAccess)) {
			return false;
		}
		StaticConstantAccess o = (StaticConstantAccess) other;

		return (safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& safeSubtreeMatch(node.getConstant(), o.getConstant()));
	}

	public boolean match(StaticFieldAccess node, Object other) {

		if (!(other instanceof StaticFieldAccess)) {
			return false;
		}
		StaticFieldAccess o = (StaticFieldAccess) other;

		return (safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& safeSubtreeMatch(node.getField(), o.getField()));

	}

	public boolean match(StaticMethodInvocation node, Object other) {
		if (!(other instanceof StaticMethodInvocation)) {
			return false;
		}
		StaticMethodInvocation o = (StaticMethodInvocation) other;

		return (safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& safeSubtreeMatch(node.getMethod(), o.getMethod()));
	}

	public boolean match(StaticStatement node, Object other) {
		if (!(other instanceof StaticStatement)) {
			return false;
		}
		StaticStatement o = (StaticStatement) other;

		return safeSubtreeListMatch(node.expressions(), o.expressions());
	}

	public boolean match(SwitchCase node, Object other) {
		if (!(other instanceof SwitchCase)) {
			return false;
		}
		SwitchCase o = (SwitchCase) other;

		return (safeEquals(node.isDefault(), o.isDefault()) && safeSubtreeMatch(node.getValue(), o.getValue())
				&& safeSubtreeListMatch(node.actions(), o.actions()));
	}

	public boolean match(SwitchStatement node, Object other) {
		if (!(other instanceof SwitchStatement)) {
			return false;
		}
		SwitchStatement o = (SwitchStatement) other;

		return (safeSubtreeMatch(node.getExpression(), o.getExpression())
				&& safeSubtreeMatch(node.getBody(), o.getBody()));
	}

	public boolean match(ThrowStatement node, Object other) {
		if (!(other instanceof ThrowStatement)) {
			return false;
		}

		return false;
	}

	public boolean match(TryStatement node, Object other) {
		if (!(other instanceof TryStatement)) {
			return false;
		}
		TryStatement o = (TryStatement) other;

		return (safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.catchClauses(), o.catchClauses())
				&& safeSubtreeMatch(node.finallyClause(), o.finallyClause()));
	}

	public boolean match(FinallyClause node, Object other) {
		if (!(other instanceof FinallyClause)) {
			return false;
		}
		FinallyClause o = (FinallyClause) other;

		return (safeSubtreeMatch(node.getBody(), o.getBody()));
	}

	public boolean match(UnaryOperation node, Object other) {
		if (!(other instanceof UnaryOperation)) {
			return false;
		}
		UnaryOperation o = (UnaryOperation) other;

		return (safeEquals(node.getOperator(), o.getOperator())
				&& safeSubtreeMatch(node.getExpression(), o.getExpression()));
	}

	public boolean match(Variable node, Object other) {
		if (!(other instanceof Variable)) {
			return false;
		}
		Variable o = (Variable) other;

		return (safeSubtreeMatch(node.getName(), o.getName()) && safeEquals(node.isDollared(), o.isDollared()));
	}

	public boolean match(WhileStatement node, Object other) {
		if (!(other instanceof WhileStatement)) {
			return false;
		}
		WhileStatement o = (WhileStatement) other;

		return (safeSubtreeMatch(node.getCondition(), o.getCondition())
				&& safeSubtreeMatch(node.getBody(), o.getBody()));
	}

	public boolean match(NamespaceDeclaration node, Object other) {
		if (!(other instanceof NamespaceDeclaration)) {
			return false;
		}
		NamespaceDeclaration o = (NamespaceDeclaration) other;
		return safeSubtreeMatch(node.getName(), o.getName()) && safeSubtreeMatch(node.getBody(), o.getBody());
	}

	public boolean match(NamespaceName node, Object other) {
		if (!(other instanceof NamespaceName)) {
			return false;
		}
		NamespaceName o = (NamespaceName) other;
		return safeEquals(node.isGlobal(), o.isGlobal()) && safeEquals(node.isCurrent(), o.isCurrent())
				&& safeSubtreeListMatch(node.segments(), o.segments());
	}

	public boolean match(UseStatementPart node, Object other) {
		if (!(other instanceof UseStatementPart)) {
			return false;
		}
		UseStatementPart o = (UseStatementPart) other;
		return safeSubtreeMatch(node.getName(), o.getName()) && safeSubtreeMatch(node.getAlias(), o.getAlias())
				&& node.getStatementType() == o.getStatementType();
	}

	public boolean match(UseStatement node, Object other) {
		if (!(other instanceof UseStatement)) {
			return false;
		}
		UseStatement o = (UseStatement) other;
		return safeSubtreeListMatch(node.parts(), o.parts())
				&& safeEquals(node.getStatementType(), o.getStatementType())
				&& safeSubtreeMatch(node.getNamespace(), o.getNamespace());
	}

	public boolean match(GotoLabel node, Object other) {
		if (!(other instanceof GotoLabel)) {
			return false;
		}
		GotoLabel o = (GotoLabel) other;
		return safeSubtreeMatch(node.getName(), o.getName());
	}

	public boolean match(GotoStatement node, Object other) {
		if (!(other instanceof GotoStatement)) {
			return false;
		}
		GotoStatement o = (GotoStatement) other;
		return safeSubtreeMatch(node.getLabel(), o.getLabel());
	}

	public boolean match(LambdaFunctionDeclaration node, Object other) {
		if (!(other instanceof LambdaFunctionDeclaration)) {
			return false;
		}
		LambdaFunctionDeclaration o = (LambdaFunctionDeclaration) other;

		return (safeEquals(node.isReference(), o.isReference()) && safeEquals(node.isStatic(), o.isStatic())
				&& safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.formalParameters(), o.formalParameters())
				&& safeSubtreeListMatch(node.lexicalVariables(), o.lexicalVariables())
				&& safeSubtreeMatch(node.getReturnType(), o.getReturnType()));
	}

	public boolean match(ArrowFunctionDeclaration node, Object other) {
		if (!(other instanceof ArrowFunctionDeclaration)) {
			return false;
		}
		ArrowFunctionDeclaration o = (ArrowFunctionDeclaration) other;

		return (safeEquals(node.isReference(), o.isReference()) && safeEquals(node.isStatic(), o.isStatic())
				&& safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.formalParameters(), o.formalParameters())
				&& safeSubtreeMatch(node.getReturnType(), o.getReturnType()));
	}

	public boolean match(AnonymousClassDeclaration node, Object other) {
		if (!(other instanceof AnonymousClassDeclaration)) {
			return false;
		}
		AnonymousClassDeclaration o = (AnonymousClassDeclaration) other;

		return (safeSubtreeMatch(node.getBody(), o.getBody())
				&& safeSubtreeListMatch(node.getInterfaces(), o.getInterfaces())
				&& safeSubtreeMatch(node.getSuperClass(), o.getSuperClass()));
	}

	public boolean match(TraitUseStatement node, Object other) {
		if (!(other instanceof TraitUseStatement)) {
			return false;
		}
		TraitUseStatement o = (TraitUseStatement) other;

		return (safeSubtreeListMatch(node.getTraitList(), o.getTraitList()))
				&& (safeSubtreeListMatch(node.getTsList(), o.getTsList()));
	}

	public boolean match(TraitStatement node, Object other) {
		if (!(other instanceof TraitStatement)) {
			return false;
		}
		TraitStatement o = (TraitStatement) other;

		return safeSubtreeMatch(node.getExp(), o.getExp());
	}

	public boolean match(TraitPrecedence node, Object other) {
		if (!(other instanceof TraitPrecedence)) {
			return false;
		}
		TraitPrecedence o = (TraitPrecedence) other;

		return (safeSubtreeMatch(node.getMethodReference(), o.getMethodReference()))
				&& (safeSubtreeListMatch(node.getTrList(), o.getTrList()));
	}

	public boolean match(TraitAlias node, Object other) {
		if (!(other instanceof TraitAlias)) {
			return false;
		}
		TraitAlias o = (TraitAlias) other;

		return safeSubtreeMatch(node.getTraitMethod(), o.getTraitMethod()) && node.getModifier() == o.getModifier()
				&& node.getFunctionName().equals(o.getFunctionName());
	}

	public boolean match(FullyQualifiedTraitMethodReference node, Object other) {
		if (!(other instanceof FullyQualifiedTraitMethodReference)) {
			return false;
		}
		FullyQualifiedTraitMethodReference o = (FullyQualifiedTraitMethodReference) other;

		return safeSubtreeMatch(node.getClassName(), o.getClassName())
				&& node.getFunctionName().equals(o.getFunctionName());
	}

	public boolean match(ReturnType node, Object other) {
		if (!(other instanceof ReturnType)) {
			return false;
		}
		ReturnType o = (ReturnType) other;
		return node.getName().equals(o.getName());
	}

}
