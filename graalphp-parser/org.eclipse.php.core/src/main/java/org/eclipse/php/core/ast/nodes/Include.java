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
 * Represents include, include_once, require and require_once expressions
 * 
 * <pre>
 * e.g.
 * 
 * include('myFile.php'), include_once($myFile),
 * require($myClass->getFileName()), require_once(A::FILE_NAME)
 * </pre>
 */
public class Include extends Expression {

	public static final int IT_REQUIRE = 0;
	public static final int IT_REQUIRE_ONCE = 1;
	public static final int IT_INCLUDE = 2;
	public static final int IT_INCLUDE_ONCE = 3;

	private Expression expression;
	private int includeType;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor EXPRESSION_PROPERTY = new ChildPropertyDescriptor(Include.class,
			"expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor INCLUDE_TYPE_PROPERTY = new SimplePropertyDescriptor(Include.class,
			"includeType", Integer.class, MANDATORY); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(2);
		propertyList.add(EXPRESSION_PROPERTY);
		propertyList.add(INCLUDE_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public Include(int start, int end, AST ast, Expression expr, int type) {
		super(start, end, ast);

		if (expr == null) {
			throw new IllegalArgumentException();
		}
		setExpression(expr);
		setIncludetype(type);
	}

	public Include(AST ast) {
		super(ast);
	}

	public static String getType(int type) {
		switch (type) {
		case IT_REQUIRE:
			return "require"; //$NON-NLS-1$
		case IT_REQUIRE_ONCE:
			return "require_once"; //$NON-NLS-1$
		case IT_INCLUDE:
			return "include"; //$NON-NLS-1$
		case IT_INCLUDE_ONCE:
			return "include_once"; //$NON-NLS-1$
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
		buffer.append(tab).append("<Include"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" kind='").append(getType(includeType)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		expression.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</Include>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.INCLUDE;
	}

	/**
	 * Returns the expression of this include.
	 * 
	 * @return the expression node
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * Sets the expression of this include expression.
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

	/**
	 * the include type one of the following {@link #IT_INCLUDE_ONCE},
	 * {@link #IT_INCLUDE}, {@link #IT_REQUIRE_ONCE}, {@link #IT_REQUIRE}
	 * 
	 * @return include type
	 */
	public int getIncludeType() {
		return this.includeType;
	}

	/**
	 * Sets the operator of this unary operation
	 * 
	 * @param new
	 *            operator of this unary operation
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public final void setIncludetype(int value) {
		if (getType(value) == null) {
			throw new IllegalArgumentException();
		}

		preValueChange(INCLUDE_TYPE_PROPERTY);
		this.includeType = value;
		postValueChange(INCLUDE_TYPE_PROPERTY);
	}

	@Override
	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == INCLUDE_TYPE_PROPERTY) {
			if (get) {
				return getIncludeType();
			} else {
				setIncludetype(value);
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
		final int type = getIncludeType();
		final Expression expr = ASTNode.copySubtree(target, getExpression());
		final Include result = new Include(getStart(), getEnd(), target, expr, type);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for the source named in this include
	 * declaration.
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public IBinding resolveBinding() {
		return this.ast.getBindingResolver().resolveInclude(this);
	}

}
