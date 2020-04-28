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
 * Represents a scalar
 * 
 * <pre>
 * e.g.
 * 
 * 'string', 1, 1.3, __CLASS__
 * </pre>
 */
public class Scalar extends VariableBase {

	// 'int'
	public static final int TYPE_INT = 0;
	// 'real'
	public static final int TYPE_REAL = 1;
	// 'string'
	public static final int TYPE_STRING = 2;
	// unknown scalar in quote expression
	public static final int TYPE_UNKNOWN = 3;
	// system scalars (__CLASS__ / ...)
	public static final int TYPE_SYSTEM = 4;
	// 'binary' starts with "0b",e.g "0b"[01]+
	public static final int TYPE_BIN = 5;

	private String stringValue;
	private int scalarType;

	/**
	 * The structural property of this node type.
	 */
	public static final SimplePropertyDescriptor VALUE_PROPERTY = new SimplePropertyDescriptor(Scalar.class,
			"stringValue", String.class, MANDATORY); //$NON-NLS-1$
	public static final SimplePropertyDescriptor TYPE_PROPERTY = new SimplePropertyDescriptor(Scalar.class,
			"scalarType", Integer.class, MANDATORY); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(3);
		propertyList.add(VALUE_PROPERTY);
		propertyList.add(TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public Scalar(int start, int end, AST ast, String value, int type) {
		super(start, end, ast);

		if (value == null) {
			throw new IllegalArgumentException();
		}

		setScalarType(type);
		setStringValue(value);
	}

	public Scalar(AST ast) {
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

	@Override
	public void childrenAccept(Visitor visitor) {
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Scalar"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" type='").append(getType(scalarType)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
		if (stringValue != null) {
			buffer.append(" value='").append(getXmlStringValue(stringValue)).append("'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		buffer.append("/>"); //$NON-NLS-1$
	}

	public static String getType(int type) {
		switch (type) {
		case TYPE_INT:
			return "int"; //$NON-NLS-1$
		case TYPE_REAL:
			return "real"; //$NON-NLS-1$
		case TYPE_STRING:
			return "string"; //$NON-NLS-1$
		case TYPE_UNKNOWN:
			return "unknown"; //$NON-NLS-1$
		case TYPE_SYSTEM:
			return "system"; //$NON-NLS-1$
		case TYPE_BIN:
			return "bin"; //$NON-NLS-1$
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int getType() {
		return ASTNode.SCALAR;
	}

	/**
	 * the scalar type - one of {@link #TYPE_INT}, {@link #TYPE_REAL},
	 * {@link #TYPE_STRING}, {@link #TYPE_SYSTEM} {@link #TYPE_UNKNOWN}
	 * 
	 * @return scalar type
	 */
	public int getScalarType() {
		return scalarType;
	}

	/**
	 * Sets the type of this scalar
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
	public final void setScalarType(int type) {
		if (getType(type) == null) {
			throw new IllegalArgumentException();
		}

		preValueChange(TYPE_PROPERTY);
		this.scalarType = type;
		postValueChange(TYPE_PROPERTY);
	}

	@Override
	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == TYPE_PROPERTY) {
			if (get) {
				return getScalarType();
			} else {
				setScalarType(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
	}

	/**
	 * The scalar value. Note that it will always be empty when scalar type is
	 * {@link #TYPE_UNKNOWN}.
	 * 
	 * @return scalar value (never null but can be empty)
	 */
	public String getStringValue() {
		return this.stringValue;
	}

	/**
	 * Sets the value of this scalar
	 * 
	 * @param value
	 *            non-null value for this scalar
	 * @exception IllegalArgumentException
	 *                if value is null
	 */
	public final void setStringValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}

		preValueChange(VALUE_PROPERTY);
		this.stringValue = value;
		postValueChange(VALUE_PROPERTY);
	}

	@Override
	final Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		if (property == VALUE_PROPERTY) {
			if (get) {
				return getStringValue();
			} else {
				setStringValue((String) value);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetObjectProperty(property, get, value);
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
		final Scalar result = new Scalar(this.getStart(), this.getEnd(), target, getStringValue(), getScalarType());
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/**
	 * Resolves and returns the binding for the entity referred to by this name.
	 *
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public final IBinding resolveBinding() {
		return this.ast.getBindingResolver().resolveField(this);
	}

}
