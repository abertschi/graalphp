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
 * Represents a single element of array. Holds the key and the value both can be
 * any expression The key can be null
 * 
 * <pre>
 * e.g.
 * 
 * 1, 'Dodo'=>'Golo', $a, $b=>foo(), 1=>$myClass->getFirst()
 * </pre>
 */
public class ArrayElement extends Expression {

	private Expression key;
	private Expression value;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor KEY_PROPERTY = new ChildPropertyDescriptor(ArrayElement.class, "key", //$NON-NLS-1$
			Expression.class, OPTIONAL, CYCLE_RISK);
	public static final ChildPropertyDescriptor VALUE_PROPERTY = new ChildPropertyDescriptor(ArrayElement.class,
			"value", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(KEY_PROPERTY);
		properyList.add(VALUE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public ArrayElement(AST ast) {
		super(ast);
	}

	public ArrayElement(int start, int end, AST ast, Expression key, Expression value) {
		super(start, end, ast);
		if (value == null) {
			throw new IllegalArgumentException();
		}

		setValue(value);
		if (key != null) {
			setKey(key);
		}
	}

	public ArrayElement(int start, int end, AST ast, Expression value) {
		this(start, end, ast, null, value);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		if (key != null) {
			key.accept(visitor);
		}
		value.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (key != null) {
			key.traverseTopDown(visitor);
		}
		value.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (key != null) {
			key.traverseBottomUp(visitor);
		}
		value.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ArrayElement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Key>\n"); //$NON-NLS-1$
		if (key != null) {
			key.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Key>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Value>\n"); //$NON-NLS-1$
		value.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Value>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(tab).append("</ArrayElement>"); //$NON-NLS-1$
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
		return ASTNode.ARRAY_ELEMENT;
	}

	/**
	 * Returns the key of this array element(null if missing).
	 * 
	 * @return the key of the array element
	 */
	public Expression getKey() {
		return key;
	}

	/**
	 * Sets the key of this array element.
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
	public void setKey(Expression expression) {
		ASTNode oldChild = this.key;
		preReplaceChild(oldChild, expression, KEY_PROPERTY);
		this.key = expression;
		postReplaceChild(oldChild, expression, KEY_PROPERTY);
	}

	/**
	 * Returns the value expression of this array element.
	 * 
	 * @return the value expression of this array element
	 */
	public Expression getValue() {
		return this.value;
	}

	/**
	 * Sets the value of this array expression.
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
		final Expression key = ASTNode.copySubtree(target, getKey());
		final Expression value = ASTNode.copySubtree(target, getValue());
		final ArrayElement result = new ArrayElement(this.getStart(), this.getEnd(), target, key, value);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
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

		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
