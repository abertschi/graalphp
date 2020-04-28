/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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
package org.eclipse.php.core.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Represents an reference to a variable or class instantiation.
 * 
 * <pre>
 * e.g.
 * 
 * &$a, &new MyClass() &foo()
 * </pre>
 */
public class Reference extends Expression {

	/**
	 * the expressions can be either variable or class instantiation note that
	 * other expressions can not be assigned to this field
	 */
	private Expression expression;

	/**
	 * The "expression" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = new ChildPropertyDescriptor(ReturnStatement.class,
			"expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(EXPRESSION_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	private Reference(int start, int end, AST ast, Expression expression) {
		super(start, end, ast);

		if (expression == null) {
			throw new IllegalArgumentException();
		}
		setExpression(expression);
	}

	public Reference(int start, int end, AST ast, VariableBase variable) {
		this(start, end, ast, (Expression) variable);
	}

	public Reference(AST ast) {
		super(ast);
	}

	public Reference(int start, int end, AST ast, ClassInstanceCreation classInstanciation) {
		this(start, end, ast, (Expression) classInstanciation);
	}

	@Override
	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		expression.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		expression.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		expression.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Reference"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		expression.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</Reference>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.REFERENCE;
	}

	/**
	 * Returns the expression of this expression statement.
	 * 
	 * @return the expression node
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * Sets the expression of this expression statement.
	 * 
	 * @param expression
	 *            the new expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setExpression(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.expression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.expression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == EXPRESSION_PROPERTY) {
			if (get) {
				return getExpression();
			} else {
				setExpression((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/*
	 * Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final Expression expr = ASTNode.copySubtree(target, getExpression());
		final Reference result = new Reference(this.getStart(), this.getEnd(), target, expr);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
