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
 * Represents a for each statement
 * 
 * <pre>
 * e.g.
 * 
 * foreach (array_expression as $value) statement;
 * 
 * foreach (array_expression as $key => $value) statement;
 * 
 * foreach (array_expression as $key => $value): statement; ... endforeach;
 * </pre>
 */
public class ForEachStatement extends Statement {

	private Expression expression;
	private Expression key;
	private Expression value;
	private Statement statement;

	/**
	 * The "expression" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = new ChildPropertyDescriptor(
			ForEachStatement.class, "expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor KEY_PROPERTY = new ChildPropertyDescriptor(ForEachStatement.class,
			"key", Expression.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor VALUE_PROPERTY = new ChildPropertyDescriptor(ForEachStatement.class,
			"value", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor STATEMENT_PROPERTY = new ChildPropertyDescriptor(ForEachStatement.class,
			"statement", Statement.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(EXPRESSION_PROPERTY);
		propertyList.add(KEY_PROPERTY);
		propertyList.add(VALUE_PROPERTY);
		propertyList.add(STATEMENT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public ForEachStatement(int start, int end, AST ast, Expression expression, Expression key, Expression value,
			Statement statement) {
		super(start, end, ast);

		if (expression == null || value == null || statement == null) {
			throw new IllegalArgumentException();
		}

		setExpression(expression);
		setValue(value);
		setStatement(statement);
		if (key != null) {
			setKey(key);
		}
	}

	public ForEachStatement(AST ast) {
		super(ast);
	}

	public ForEachStatement(int start, int end, AST ast, Expression expression, Expression value, Statement statement) {
		this(start, end, ast, expression, null, value, statement);
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
		if (key != null) {
			key.accept(visitor);
		}
		value.accept(visitor);
		statement.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		expression.traverseTopDown(visitor);
		if (key != null) {
			key.traverseTopDown(visitor);
		}
		value.traverseTopDown(visitor);
		statement.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		expression.traverseBottomUp(visitor);
		if (key != null) {
			key.traverseBottomUp(visitor);
		}
		value.traverseBottomUp(visitor);
		statement.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ForEachStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Expression>\n"); //$NON-NLS-1$
		expression.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Expression>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(TAB).append(tab).append("<Key>\n"); //$NON-NLS-1$
		if (key != null) {
			key.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Key>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Value>\n"); //$NON-NLS-1$
		value.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Value>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		statement.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</ForEachStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FOR_EACH_STATEMENT;
	}

	/**
	 * Returns the expression of this for each statement.
	 * 
	 * @return the expression node
	 */
	public Expression getExpression() {
		return this.expression;
	}

	/**
	 * Sets the expression of this for each statement.
	 * 
	 * @param expression
	 *            the new expression of this for each statement.
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

	/**
	 * @return the key component of this for each statement
	 */
	public Expression getKey() {
		return key;
	}

	/**
	 * Sets the new key component of this for each statement
	 * 
	 * @param key
	 *            the new expression of this for each statement.
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

	/**
	 * @return the value component of this for each statement
	 */
	public Expression getValue() {
		return this.value;
	}

	/**
	 * Sets the new value component of this for each statement
	 * 
	 * @param value
	 *            the new value of this for each statement.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setValue(Expression value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.value;
		preReplaceChild(oldChild, value, VALUE_PROPERTY);
		this.value = value;
		postReplaceChild(oldChild, value, VALUE_PROPERTY);
	}

	/**
	 * @return the statement component of this for each statement
	 */
	public Statement getStatement() {
		return this.statement;
	}

	/**
	 * Sets the new statement component of this for each statement
	 * 
	 * @param statement
	 *            the new value of this for each statement.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setStatement(Statement statement) {
		if (statement == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.statement;
		preReplaceChild(oldChild, statement, STATEMENT_PROPERTY);
		this.statement = statement;
		postReplaceChild(oldChild, statement, STATEMENT_PROPERTY);
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
		if (property == KEY_PROPERTY) {
			if (get) {
				return getKey();
			} else {
				setKey((Expression) child);
				return null;
			}
		}
		if (property == VALUE_PROPERTY) {
			if (get) {
				return getValue();
			} else {
				setValue((Expression) child);
				return null;
			}
		}
		if (property == STATEMENT_PROPERTY) {
			if (get) {
				return getStatement();
			} else {
				setStatement((Statement) child);
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
		final Expression key = ASTNode.copySubtree(target, getKey());
		final Expression value = ASTNode.copySubtree(target, getValue());
		final Statement stm = ASTNode.copySubtree(target, getStatement());
		final ForEachStatement result = new ForEachStatement(this.getStart(), this.getEnd(), target, expr, key, value,
				stm);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
