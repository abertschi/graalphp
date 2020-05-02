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
 * Represent a yield expression
 * 
 * e.g.
 * 
 * <pre>
 * yield
 * yield $a
 * yield $k => $a
 * yield from $k
 * </pre>
 */
public class YieldExpression extends Expression {

	// yield $a or yield $k => $a
	public static final int OP_NONE = 0;
	// yield from $k
	public static final int OP_FROM = 1;

	private Expression key;
	private Expression expression;
	private int operator;

	/**
	 * The "expression" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = new ChildPropertyDescriptor(YieldExpression.class,
			"expression", Expression.class, OPTIONAL, //$NON-NLS-1$
			CYCLE_RISK);

	/**
	 * The "key" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor KEY_PROPERTY = new ChildPropertyDescriptor(YieldExpression.class, "key", //$NON-NLS-1$
			Expression.class, OPTIONAL, CYCLE_RISK);

	public static final SimplePropertyDescriptor OPERATOR_PROPERTY = new SimplePropertyDescriptor(YieldExpression.class,
			"operator", Integer.class, MANDATORY); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(2);
		propertyList.add(EXPRESSION_PROPERTY);
		propertyList.add(KEY_PROPERTY);
		propertyList.add(OPERATOR_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public YieldExpression(int start, int end, AST ast) {
		this(start, end, ast, null);
	}

	public YieldExpression(AST ast) {
		super(ast);
	}

	public YieldExpression(int start, int end, AST ast, Expression expr) {
		this(start, end, ast, expr, OP_NONE);
	}

	public YieldExpression(int start, int end, AST ast, Expression key, Expression expr) {
		super(start, end, ast);
		if (key != null) {
			setKey(key);
		}
		if (expr != null) {
			setExpression(expr);
		}
		this.operator = OP_NONE;
	}

	public YieldExpression(int start, int end, AST ast, Expression expr, int operator) {
		super(start, end, ast);

		if (expr != null) {
			setExpression(expr);
		}
		this.operator = operator;
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
		if (key != null) {
			key.accept(visitor);
		}
		if (expression != null) {
			expression.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (key != null) {
			key.traverseTopDown(visitor);
		}
		if (expression != null) {
			expression.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (key != null) {
			key.traverseBottomUp(visitor);
		}
		if (expression != null) {
			expression.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<YieldExpression"); //$NON-NLS-1$
		appendInterval(buffer);
		if (getOperator() != OP_NONE) {
			buffer.append(" operator='" + operator + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		buffer.append(">\n"); //$NON-NLS-1$
		if (key != null) {
			key.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		if (expression != null) {
			expression.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</YieldExpression>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.YIELD_STATEMENT;
	}

	/**
	 * Returns the expression of this yield statement.
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
		ASTNode oldChild = this.expression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.expression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	/**
	 * Sets the key of this expression statement.
	 * 
	 * @param key
	 *            the new expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setKey(Expression key) {
		ASTNode oldChild = this.key;
		preReplaceChild(oldChild, key, KEY_PROPERTY);
		this.key = key;
		postReplaceChild(oldChild, key, KEY_PROPERTY);
	}

	public Expression getKey() {
		return key;
	}

	public void setOperator(int operator) {
		preValueChange(OPERATOR_PROPERTY);
		this.operator = operator;
		postValueChange(OPERATOR_PROPERTY);
	}

	public int getOperator() {
		return operator;
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
		} else if (property == KEY_PROPERTY) {
			if (get) {
				return getKey();
			} else {
				setKey((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == OPERATOR_PROPERTY) {
			if (get) {
				return getOperator();
			} else {
				setOperator(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
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
		final Expression key = ASTNode.copySubtree(target, getKey());
		final Expression expr = ASTNode.copySubtree(target, getExpression());
		final YieldExpression result = new YieldExpression(this.getStart(), this.getEnd(), target, key, expr);
		result.setOperator(operator);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
