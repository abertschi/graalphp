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
 * Represents an assignment statement.
 * 
 * <pre>
 * e.g.
 * 
 * $a = 5, $a += 5, $a .= $b,
 * </pre>
 */
public class Assignment extends Expression implements IOperationNode {

	// '='
	public static final int OP_EQUAL = 0;
	// '+='
	public static final int OP_PLUS_EQUAL = 1;
	// '-='
	public static final int OP_MINUS_EQUAL = 2;
	// '*='
	public static final int OP_MUL_EQUAL = 3;
	// '/='
	public static final int OP_DIV_EQUAL = 4;
	// '.='
	public static final int OP_CONCAT_EQUAL = 5;
	// '%='
	public static final int OP_MOD_EQUAL = 6;
	// '&='
	public static final int OP_AND_EQUAL = 7;
	// '|='
	public static final int OP_OR_EQUAL = 8;
	// '^='
	public static final int OP_XOR_EQUAL = 9;
	// '<<='
	public static final int OP_SL_EQUAL = 10;
	// '>>='
	public static final int OP_SR_EQUAL = 11;
	// '**='
	public static final int OP_POW_EQUAL = 12;
	// '??='
	public static final int OP_COALESCE_EQUAL = 13;

	private VariableBase leftHandSide;
	private int operator;
	private Expression rightHandSide;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor LEFT_HAND_SIDE_PROPERTY = new ChildPropertyDescriptor(Assignment.class,
			"variable", VariableBase.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor OPERATOR_PROPERTY = new SimplePropertyDescriptor(Assignment.class,
			"operator", Integer.class, MANDATORY); //$NON-NLS-1$
	public static final ChildPropertyDescriptor RIGHT_HAND_SIDE_PROPERTY = new ChildPropertyDescriptor(Assignment.class,
			"value", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(LEFT_HAND_SIDE_PROPERTY);
		properyList.add(OPERATOR_PROPERTY);
		properyList.add(RIGHT_HAND_SIDE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public Assignment(int start, int end, AST ast, VariableBase leftHandSide, int operator, Expression rightHandSide) {
		super(start, end, ast);
		if (leftHandSide == null || rightHandSide == null || getOperator(operator) == null) {
			throw new IllegalArgumentException();
		}

		setLeftHandSide(leftHandSide);
		setOperator(operator);
		setRightHandSide(rightHandSide);
	}

	public Assignment(AST ast) {
		super(ast);
	}

	public static String getOperator(int operator) {
		switch (operator) {
		case OP_EQUAL:
			return "="; //$NON-NLS-1$
		case OP_PLUS_EQUAL:
			return "+="; //$NON-NLS-1$
		case OP_MINUS_EQUAL:
			return "-="; //$NON-NLS-1$
		case OP_MUL_EQUAL:
			return "*="; //$NON-NLS-1$
		case OP_DIV_EQUAL:
			return "/="; //$NON-NLS-1$
		case OP_MOD_EQUAL:
			return "%="; //$NON-NLS-1$
		case OP_CONCAT_EQUAL:
			return ".="; //$NON-NLS-1$
		case OP_AND_EQUAL:
			return "&="; //$NON-NLS-1$
		case OP_OR_EQUAL:
			return "|="; //$NON-NLS-1$
		case OP_XOR_EQUAL:
			return "^="; //$NON-NLS-1$
		case OP_SL_EQUAL:
			return "<<="; //$NON-NLS-1$
		case OP_SR_EQUAL:
			return ">>="; //$NON-NLS-1$
		case OP_POW_EQUAL:
			return "**="; //$NON-NLS-1$
		case OP_COALESCE_EQUAL:
			return "??="; //$NON-NLS-1$
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		leftHandSide.accept(visitor);
		rightHandSide.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		leftHandSide.traverseTopDown(visitor);
		rightHandSide.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		leftHandSide.traverseBottomUp(visitor);
		rightHandSide.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Assignment"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" operator='").append(getXmlStringValue(getOperator(operator))).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		leftHandSide.toString(buffer, TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("<Value>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		rightHandSide.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Value>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(tab).append("</Assignment>"); //$NON-NLS-1$
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
	public int getType() {
		return ASTNode.ASSIGNMENT;
	}

	/**
	 * Returns the operator of this assignment expression.
	 * 
	 * @return the assignment operator
	 */
	public int getOperator() {
		return this.operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.php.internal.core.ast.nodes.IOperationNode#getOperationString
	 * ()
	 */
	@Override
	public String getOperationString() {
		return getOperator(this.getOperator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.php.internal.core.ast.nodes.IOperationNode#getOperationString
	 * (int)
	 */
	@Override
	public String getOperationString(int op) {
		return getOperator(op);
	}

	/**
	 * Sets the operator of this assignment expression.
	 * 
	 * @param assignmentOperator
	 *            the assignment operator
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setOperator(int assignmentOperator) {
		if (getOperator(assignmentOperator) == null) {
			throw new IllegalArgumentException("Invalid operator"); //$NON-NLS-1$
		}
		preValueChange(OPERATOR_PROPERTY);
		this.operator = assignmentOperator;
		postValueChange(OPERATOR_PROPERTY);
	}

	/**
	 * Returns the left hand side of this assignment expression.
	 * 
	 * @return the left hand side node
	 */
	public VariableBase getLeftHandSide() {
		return this.leftHandSide;
	}

	/**
	 * Sets the left hand side of this assignment expression.
	 * 
	 * @param expression
	 *            the left hand side node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setLeftHandSide(VariableBase leftHandSide) {
		if (leftHandSide == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.leftHandSide;
		preReplaceChild(oldChild, leftHandSide, LEFT_HAND_SIDE_PROPERTY);
		this.leftHandSide = leftHandSide;
		postReplaceChild(oldChild, leftHandSide, LEFT_HAND_SIDE_PROPERTY);
	}

	/**
	 * Returns the right hand side of this assignment expression.
	 * 
	 * @return the right hand side node
	 */
	public Expression getRightHandSide() {
		return this.rightHandSide;
	}

	/**
	 * Sets the right hand side of this assignment expression.
	 * 
	 * @param expression
	 *            the right hand side node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setRightHandSide(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.rightHandSide;
		preReplaceChild(oldChild, expression, RIGHT_HAND_SIDE_PROPERTY);
		this.rightHandSide = expression;
		postReplaceChild(oldChild, expression, RIGHT_HAND_SIDE_PROPERTY);
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
		final VariableBase left = ASTNode.copySubtree(target, getLeftHandSide());
		final Expression right = ASTNode.copySubtree(target, getRightHandSide());
		final Assignment result = new Assignment(this.getStart(), this.getEnd(), target, left, this.getOperator(),
				right);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
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

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == LEFT_HAND_SIDE_PROPERTY) {
			if (get) {
				return getLeftHandSide();
			} else {
				setLeftHandSide((VariableBase) child);
				return null;
			}
		}
		if (property == RIGHT_HAND_SIDE_PROPERTY) {
			if (get) {
				return getRightHandSide();
			} else {
				setRightHandSide((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
