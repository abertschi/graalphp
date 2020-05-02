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
 * Holds a variable and an index that point to array or hashtable
 * 
 * <pre>
 * e.g.
 * 
 * $a[], $a[1], $a[$b], $a{'name'}
 * </pre>
 */
public class ArrayAccess extends Variable {

	public static final int VARIABLE_ARRAY = 1; // default
	public static final int VARIABLE_HASHTABLE = 2;

	/**
	 * In case of array / hashtable variable, the index expression is added
	 */
	private Expression index;
	private int arrayType;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor NAME_PROPERTY = new ChildPropertyDescriptor(ArrayAccess.class, "name", //$NON-NLS-1$
			VariableBase.class, MANDATORY, CYCLE_RISK);
	public static final SimplePropertyDescriptor DOLLARED_PROPERTY = new SimplePropertyDescriptor(ArrayAccess.class,
			"isDollared", Boolean.class, OPTIONAL); //$NON-NLS-1$
	public static final ChildPropertyDescriptor INDEX_PROPERTY = new ChildPropertyDescriptor(ArrayAccess.class, "index", //$NON-NLS-1$
			Expression.class, OPTIONAL, CYCLE_RISK);
	public static final SimplePropertyDescriptor ARRAY_TYPE_PROPERTY = new SimplePropertyDescriptor(ArrayAccess.class,
			"arrayType", Integer.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * @return the name PROPERTY
	 */
	@Override
	public ChildPropertyDescriptor getNameProperty() {
		return ArrayAccess.NAME_PROPERTY;
	}

	/**
	 * @return the DOLLARED property
	 */
	@Override
	public SimplePropertyDescriptor getDollaredProperty() {
		return ArrayAccess.DOLLARED_PROPERTY;
	}

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(4);
		propertyList.add(NAME_PROPERTY);
		propertyList.add(DOLLARED_PROPERTY);
		propertyList.add(INDEX_PROPERTY);
		propertyList.add(ARRAY_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public ArrayAccess(AST ast) {
		super(ast);
	}

	public ArrayAccess(int start, int end, AST ast, VariableBase variableName, Expression index, int arrayType) {
		super(start, end, ast, variableName);

		if (index != null) {
			setIndex(index);
		}
		setArrayType(arrayType);
	}

	/**
	 * Create a new ArrayAccess. Default array type is VARIABLE_ARRAY
	 * 
	 * @param start
	 * @param end
	 * @param ast
	 * @param variableName
	 * @param index
	 */
	public ArrayAccess(int start, int end, AST ast, VariableBase variableName, Expression index) {
		super(start, end, ast, variableName);

		if (index != null) {
			setIndex(index);
		}
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		getName().accept(visitor);
		if (index != null) {
			index.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		getName().traverseTopDown(visitor);
		if (index != null) {
			index.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		getName().traverseBottomUp(visitor);
		if (index != null) {
			index.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ArrayAccess"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" type='").append(getArrayType(arrayType)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		getName().toString(buffer, TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("<Index>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		if (index != null) {
			index.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Index>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</ArrayAccess>"); //$NON-NLS-1$
	}

	public static String getArrayType(int type) {
		switch (type) {
		case VARIABLE_ARRAY:
			return "array"; //$NON-NLS-1$
		case VARIABLE_HASHTABLE:
			return "hashtable"; //$NON-NLS-1$
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
	public int getType() {
		return ASTNode.ARRAY_ACCESS;
	}

	public Expression getIndex() {
		return index;
	}

	/**
	 * Sets the index expression of this array access
	 * 
	 * @param index
	 *            the new index expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setIndex(Expression index) {
		if (index == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.index;
		preReplaceChild(oldChild, index, INDEX_PROPERTY);
		this.index = index;
		postReplaceChild(oldChild, index, INDEX_PROPERTY);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == INDEX_PROPERTY) {
			if (get) {
				return getIndex();
			} else {
				setIndex((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	public int getArrayType() {
		return arrayType;
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
	public final void setArrayType(int value) {
		if (getArrayType(value) == null) {
			throw new IllegalArgumentException();
		}

		preValueChange(ARRAY_TYPE_PROPERTY);
		this.arrayType = value;
		postValueChange(ARRAY_TYPE_PROPERTY);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == ARRAY_TYPE_PROPERTY) {
			if (get) {
				return getArrayType();
			} else {
				setArrayType(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
	}

	/**
	 * Sets the name of this variable (this time with VariableBase instead of
	 * Expression)
	 * 
	 * @param expression
	 *            the new variable name
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setName(VariableBase variableName) {
		super.setName(variableName);
	}

	/**
	 * Returns the name (expression) of this variable
	 * 
	 * @return the expression name node
	 */
	@Override
	public VariableBase getName() {
		return (VariableBase) super.getName();
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final VariableBase expr = ASTNode.copySubtree(target, getName());
		final Expression index = ASTNode.copySubtree(target, getIndex());
		final int type = getArrayType();

		final ArrayAccess result = new ArrayAccess(getStart(), getEnd(), target, expr, index, type);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

}
