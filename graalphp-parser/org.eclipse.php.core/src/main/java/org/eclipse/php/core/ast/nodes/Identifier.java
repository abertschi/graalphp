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
 * Holds an identifier.<br>
 * uses for variable name, function name and class name.
 * 
 * <pre>
 * e.g.
 * 
 * $variableName - variableName is the identifier, foo() - foo is the
 * identifier, $myClass->fun() - myClass and fun are identifiers
 * </pre>
 */
public class Identifier extends VariableBase {

	private String name;
	private boolean nullable;

	/**
	 * The "identifier" structural property of this node type.
	 */
	public static final SimplePropertyDescriptor NAME_PROPERTY = new SimplePropertyDescriptor(Identifier.class, "name", //$NON-NLS-1$
			String.class, MANDATORY);
	public static final SimplePropertyDescriptor NULLABLE_PROPERTY = new SimplePropertyDescriptor(Identifier.class,
			"nullable", //$NON-NLS-1$
			Boolean.class, MANDATORY);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> list = new ArrayList<>(2);
		list.add(NAME_PROPERTY);
		list.add(NULLABLE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(list);
	}

	public Identifier(int start, int end, AST ast, String value) {
		super(start, end, ast);

		if (value == null) {
			throw new IllegalArgumentException();
		}
		// intern the string for fast equality check
		value.intern();
		setName(value);
	}

	public Identifier(AST ast) {
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
		buffer.append(tab).append("<Identifier"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" name='").append(name); //$NON-NLS-1$
		if (isNullable()) {
			buffer.append("' nullable='").append(nullable); //$NON-NLS-1$
		}
		buffer.append("'/>"); //$NON-NLS-1$
	}

	/**
	 * Equality check for Identifier.<br>
	 * Two identifiers are equal iff they have equal names (disregarding its
	 * offset and length) <br>
	 * Note: The equality is checked by == since we {@link String#intern()} the
	 * name field.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Identifier other = (Identifier) obj;
		return this.name == other.name && this.nullable == other.nullable;
	}

	@Override
	public int getType() {
		return ASTNode.IDENTIFIER;
	}

	public String getName() {
		return name;
	}

	public boolean isNullable() {
		return nullable;
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
	protected ASTNode clone0(AST target) {
		Identifier result = new Identifier(this.getStart(), this.getEnd(), target, this.getName());
		result.setNullable(this.isNullable());
		return result;
	}

	@Override
	protected List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		if (property == NAME_PROPERTY) {
			if (get) {
				return getName();
			} else {
				setName((String) value);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetObjectProperty(property, get, value);
	}

	@Override
	boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == NULLABLE_PROPERTY) {
			if (get) {
				return isNullable();
			} else {
				setNullable(value);
				return false;
			}
		}
		return super.internalGetSetBooleanProperty(property, get, value);
	}

	public final void setName(String value) {
		if (value == null/* || value.length() == 0 */) {
			throw new IllegalArgumentException();
		}

		preValueChange(NAME_PROPERTY);
		this.name = value;
		postValueChange(NAME_PROPERTY);
	}

	public final void setNullable(boolean value) {
		preValueChange(NULLABLE_PROPERTY);
		this.nullable = value;
		postValueChange(NULLABLE_PROPERTY);
	}

	/**
	 * Resolves and returns the binding for the entity referred to by this name.
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public final IBinding resolveBinding() {
		return this.ast.getBindingResolver().resolveName(this);
	}
}
