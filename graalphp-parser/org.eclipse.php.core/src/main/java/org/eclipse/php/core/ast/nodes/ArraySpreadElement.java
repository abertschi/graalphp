/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
 * Represents a single spread element of array.
 * 
 * <pre>
 * e.g.
 * 
 * ...$parts, ...getArr(), ...new ArrayIterator(['a', 'b', 'c'])
 * </pre>
 * 
 * @since PHP 7.4
 */
public class ArraySpreadElement extends Expression {

	private Expression value;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor VALUE_PROPERTY = new ChildPropertyDescriptor(ArraySpreadElement.class,
			"value", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(VALUE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public ArraySpreadElement(AST ast) {
		super(ast);
	}

	public ArraySpreadElement(int start, int end, AST ast, Expression value) {
		super(start, end, ast);
		if (value == null) {
			throw new IllegalArgumentException();
		}

		setValue(value);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		value.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		value.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		value.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ArraySpreadElement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Value>\n"); //$NON-NLS-1$
		value.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Value>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(tab).append("</ArraySpreadElement>"); //$NON-NLS-1$
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
		return ASTNode.ARRAY_SPREAD_ELEMENT;
	}

	/**
	 * Returns the value expression of this array spread expression.
	 * 
	 * @return the value expression of this array spread expression
	 */
	public Expression getValue() {
		return this.value;
	}

	/**
	 * Sets the value of this spread expression.
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
	public void setValue(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.value;
		preReplaceChild(oldChild, expression, VALUE_PROPERTY);
		this.value = expression;
		postReplaceChild(oldChild, expression, VALUE_PROPERTY);
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
		final Expression value = ASTNode.copySubtree(target, getValue());
		final ArraySpreadElement result = new ArraySpreadElement(this.getStart(), this.getEnd(), target, value);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == VALUE_PROPERTY) {
			if (get) {
				return getValue();
			} else {
				setValue((Expression) child);
				return null;
			}
		}

		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
