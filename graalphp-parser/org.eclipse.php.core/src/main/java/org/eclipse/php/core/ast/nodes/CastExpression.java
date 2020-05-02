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
 * Represents a type casting expression
 * 
 * <pre>
 * e.g.
 * 
 * (int) $a, (string) $b->foo()
 * </pre>
 */
public class CastExpression extends Expression {

	// 'int'
	public static final int TYPE_INT = 0;
	// 'real'
	public static final int TYPE_REAL = 1;
	// 'string'
	public static final int TYPE_STRING = 2;
	// 'array'
	public static final int TYPE_ARRAY = 3;
	// 'object'
	public static final int TYPE_OBJECT = 4;
	// 'bool'
	public static final int TYPE_BOOL = 5;
	// 'unset'
	public static final int TYPE_UNSET = 6;

	private Expression expression;
	private int castingType;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = new ChildPropertyDescriptor(CastExpression.class,
			"expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor CASTING_TYPE_PROPERTY = new SimplePropertyDescriptor(
			CastExpression.class, "castingType", Integer.class, MANDATORY); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(EXPRESSION_PROPERTY);
		properyList.add(CASTING_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public CastExpression(int start, int end, AST ast, Expression expr, int castType) {
		super(start, end, ast);

		if (expr == null) {
			throw new IllegalArgumentException();
		}
		setExpression(expr);
		setCastingType(castType);
	}

	public CastExpression(AST ast) {
		super(ast);
	}

	@Override
	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	public static String getCastType(int type) {
		switch (type) {
		case TYPE_INT:
			return "int"; //$NON-NLS-1$
		case TYPE_REAL:
			return "real"; //$NON-NLS-1$
		case TYPE_STRING:
			return "string"; //$NON-NLS-1$
		case TYPE_ARRAY:
			return "array"; //$NON-NLS-1$
		case TYPE_OBJECT:
			return "object"; //$NON-NLS-1$
		case TYPE_BOOL:
			return "bool"; //$NON-NLS-1$
		case TYPE_UNSET:
			return "unset"; //$NON-NLS-1$
		default:
			throw new IllegalArgumentException();
		}
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
		buffer.append(tab).append("<CastExpression"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" castType='").append(getCastType(castingType)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		expression.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</CastExpression>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.CAST_EXPRESSION;
	}

	/**
	 * Returns the type of this cast expression.
	 * 
	 * @return the cast type
	 */
	public int getCastingType() {
		return this.castingType;
	}

	/**
	 * Sets the type of this cast expression.
	 * 
	 * @param castingType
	 *            the cast type
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setCastingType(int castingType) {
		if (getCastType(castingType) == null) {
			throw new IllegalArgumentException("Invalid type"); //$NON-NLS-1$
		}
		preValueChange(CASTING_TYPE_PROPERTY);
		this.castingType = castingType;
		postValueChange(CASTING_TYPE_PROPERTY);
	}

	@Override
	int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == CASTING_TYPE_PROPERTY) {
			if (get) {
				return getCastingType();
			} else {
				setCastingType(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
	}

	/**
	 * Returns the left hand side of this assignment expression.
	 * 
	 * @return the left hand side node
	 */
	public Expression getExpression() {
		return this.expression;
	}

	/**
	 * Sets the expression of this cast expression.
	 * 
	 * @param expression
	 *            of this cast expression.
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
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.expression;
		preReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
		this.expression = expression;
		postReplaceChild(oldChild, expression, EXPRESSION_PROPERTY);
	}

	@Override
	final Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		if (property == CASTING_TYPE_PROPERTY) {
			if (get) {
				return getCastingType();
			} else {
				setCastingType((Integer) value);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetObjectProperty(property, get, value);
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
		final Expression clone = ASTNode.copySubtree(target, this.getExpression());
		final CastExpression result = new CastExpression(this.getStart(), this.getEnd(), target, clone,
				this.getCastingType());
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
