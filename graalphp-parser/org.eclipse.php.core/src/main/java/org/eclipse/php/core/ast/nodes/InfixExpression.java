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
 * Represents an infix expression
 * 
 * <pre>
 * e.g.
 * 
 * <pre>
 * $a + 1, 3 - 2, foo() * $a->bar(), 'string'.$c
 */
public class InfixExpression extends Expression {

	// '==='
	public static final int OP_IS_IDENTICAL = 0;
	// '!=='
	public static final int OP_IS_NOT_IDENTICAL = 1;
	// '=='
	public static final int OP_IS_EQUAL = 2;
	// '!='
	public static final int OP_IS_NOT_EQUAL = 3;
	// '<'
	public static final int OP_RGREATER = 4;
	// '<='
	public static final int OP_IS_SMALLER_OR_EQUAL = 5;
	// '>'
	public static final int OP_LGREATER = 6;
	// '>='
	public static final int OP_IS_GREATER_OR_EQUAL = 7;
	// '||'
	public static final int OP_BOOL_OR = 8;
	// '&&'
	public static final int OP_BOOL_AND = 9;
	// 'or'
	public static final int OP_STRING_OR = 10;
	// 'and'
	public static final int OP_STRING_AND = 11;
	// 'xor'
	public static final int OP_STRING_XOR = 12;
	// '|'
	public static final int OP_OR = 13;
	// '&'
	public static final int OP_AND = 14;
	// '^'
	public static final int OP_XOR = 15;
	// '.'
	public static final int OP_CONCAT = 16;
	// '+'
	public static final int OP_PLUS = 17;
	// '-'
	public static final int OP_MINUS = 18;
	// '*'
	public static final int OP_MUL = 19;
	// '/'
	public static final int OP_DIV = 20;
	// '%'
	public static final int OP_MOD = 21;
	// '<<'
	public static final int OP_SL = 22;
	// '>>'
	public static final int OP_SR = 23;
	// '**'
	public static final int OP_POW = 24;
	// '<=>'
	public static final int OP_SPACESHIP = 25;

	private Expression left;
	private int operator;
	private Expression right;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor LEFT_OPERAND_PROPERTY = new ChildPropertyDescriptor(
			InfixExpression.class, "left", Expression.class, MANDATORY, //$NON-NLS-1$
			CYCLE_RISK);
	public static final SimplePropertyDescriptor OPERATOR_PROPERTY = new SimplePropertyDescriptor(InfixExpression.class,
			"operator", Integer.class, MANDATORY); //$NON-NLS-1$
	public static final ChildPropertyDescriptor RIGHT_OPERAND_PROPERTY = new ChildPropertyDescriptor(
			InfixExpression.class, "right", Expression.class, MANDATORY, //$NON-NLS-1$
			CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(LEFT_OPERAND_PROPERTY);
		properyList.add(OPERATOR_PROPERTY);
		properyList.add(RIGHT_OPERAND_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public InfixExpression(int start, int end, AST ast, Expression left, int operator, Expression right) {
		super(start, end, ast);

		if (right == null || left == null || getOperator(operator) == null) {
			throw new IllegalArgumentException();
		}

		setLeft(left);
		setOperator(operator);
		setRight(right);
	}

	public InfixExpression(AST ast) {
		super(ast);
	}

	public static String getOperator(int operator) {
		switch (operator) {
		case OP_IS_IDENTICAL:
			return "==="; //$NON-NLS-1$
		case OP_IS_NOT_IDENTICAL:
			return "!=="; //$NON-NLS-1$
		case OP_IS_EQUAL:
			return "=="; //$NON-NLS-1$
		case OP_IS_NOT_EQUAL:
			return "!="; //$NON-NLS-1$
		case OP_RGREATER:
			return "<"; //$NON-NLS-1$
		case OP_IS_SMALLER_OR_EQUAL:
			return "<="; //$NON-NLS-1$
		case OP_LGREATER:
			return ">"; //$NON-NLS-1$
		case OP_IS_GREATER_OR_EQUAL:
			return ">="; //$NON-NLS-1$
		case OP_BOOL_OR:
			return "||"; //$NON-NLS-1$
		case OP_BOOL_AND:
			return "&&"; //$NON-NLS-1$
		case OP_STRING_OR:
			return "or"; //$NON-NLS-1$
		case OP_STRING_AND:
			return "and"; //$NON-NLS-1$
		case OP_STRING_XOR:
			return "xor"; //$NON-NLS-1$
		case OP_OR:
			return "|"; //$NON-NLS-1$
		case OP_AND:
			return "&"; //$NON-NLS-1$
		case OP_XOR:
			return "^"; //$NON-NLS-1$
		case OP_CONCAT:
			return "."; //$NON-NLS-1$
		case OP_PLUS:
			return "+"; //$NON-NLS-1$
		case OP_MINUS:
			return "-"; //$NON-NLS-1$
		case OP_MUL:
			return "*"; //$NON-NLS-1$
		case OP_DIV:
			return "/"; //$NON-NLS-1$
		case OP_MOD:
			return "%"; //$NON-NLS-1$
		case OP_SL:
			return "<<"; //$NON-NLS-1$
		case OP_SR:
			return ">>"; //$NON-NLS-1$
		case OP_POW:
			return "**"; //$NON-NLS-1$
		case OP_SPACESHIP:
			return "<=>"; //$NON-NLS-1$
		default:
			throw new IllegalArgumentException();
		}
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
		left.accept(visitor);
		right.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		left.traverseTopDown(visitor);
		right.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		left.traverseBottomUp(visitor);
		right.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<InfixExpression"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" operator='") //$NON-NLS-1$
				.append(getXmlStringValue(getOperator(operator))).append("'>\n"); //$NON-NLS-1$
		left.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		right.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</InfixExpression>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.INFIX_EXPRESSION;
	}

	/**
	 * Returns the operator of this infix expression.
	 * 
	 * @return the infix operator
	 */
	public int getOperator() {
		return this.operator;
	}

	/**
	 * Sets the operator of this infix expression.
	 * 
	 * @param operator
	 *            the infix operator
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setOperator(int operator) {
		if (getOperator(operator) == null) {
			throw new IllegalArgumentException();
		}
		preValueChange(OPERATOR_PROPERTY);
		this.operator = operator;
		postValueChange(OPERATOR_PROPERTY);
	}

	/**
	 * Returns the left operand of this infix expression.
	 * 
	 * @return the left operand node
	 */
	public Expression getLeft() {
		return this.left;
	}

	/**
	 * Sets the left operand of this infix expression.
	 * 
	 * @param expression
	 *            the left operand node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setLeft(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.left;
		preReplaceChild(oldChild, expression, LEFT_OPERAND_PROPERTY);
		this.left = expression;
		postReplaceChild(oldChild, expression, LEFT_OPERAND_PROPERTY);
	}

	/**
	 * Returns the right operand of this infix expression.
	 * 
	 * @return the right operand node
	 */
	public Expression getRight() {
		return this.right;
	}

	/**
	 * Sets the right operand of this infix expression.
	 * 
	 * @param expression
	 *            the right operand node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setRight(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.right;
		preReplaceChild(oldChild, expression, RIGHT_OPERAND_PROPERTY);
		this.right = expression;
		postReplaceChild(oldChild, expression, RIGHT_OPERAND_PROPERTY);
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
		final Expression left = ASTNode.copySubtree(target, getLeft());
		final Expression right = ASTNode.copySubtree(target, getRight());
		final InfixExpression result = new InfixExpression(this.getStart(), this.getEnd(), target, left,
				this.getOperator(), right);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == LEFT_OPERAND_PROPERTY) {
			if (get) {
				return getLeft();
			} else {
				setLeft((Expression) child);
				return null;
			}
		}
		if (property == RIGHT_OPERAND_PROPERTY) {
			if (get) {
				return getRight();
			} else {
				setRight((Expression) child);
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
}
