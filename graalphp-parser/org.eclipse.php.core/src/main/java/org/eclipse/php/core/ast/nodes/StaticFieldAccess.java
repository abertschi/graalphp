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
 * Represents a static field access.
 * 
 * <pre>
 * e.g.
 * 
 * MyClass::$a MyClass::$$a[3]
 * </pre>
 */
public class StaticFieldAccess extends StaticDispatch {

	private Variable field;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor CLASS_NAME_PROPERTY = new ChildPropertyDescriptor(
			StaticFieldAccess.class, "className", Expression.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor FIELD_PROPERTY = new ChildPropertyDescriptor(StaticFieldAccess.class,
			"field", Variable.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	@Override
	ChildPropertyDescriptor getClassNameProperty() {
		return CLASS_NAME_PROPERTY;
	}

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(FIELD_PROPERTY);
		properyList.add(CLASS_NAME_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public StaticFieldAccess(int start, int end, AST ast, Expression className, Variable field) {
		super(start, end, ast, className);

		if (field == null) {
			throw new IllegalArgumentException();
		}
		setField(field);
	}

	public StaticFieldAccess(AST ast) {
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
		getClassName().accept(visitor);
		field.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		getClassName().traverseTopDown(visitor);
		field.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		getClassName().traverseBottomUp(visitor);
		field.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<StaticFieldAccess"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<ClassName>\n"); //$NON-NLS-1$
		getClassName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</ClassName>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		field.toString(buffer, TAB + tab);
		buffer.append("\n").append(tab).append("</StaticFieldAccess>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.STATIC_FIELD_ACCESS;
	}

	/**
	 * The field of this access
	 * 
	 * @return field of this access
	 */
	public Variable getField() {
		return field;
	}

	/**
	 * Sets the variable declaration of this catch clause.
	 * 
	 * @param exception
	 *            the exception variable declaration node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setField(Variable variable) {
		if (variable == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.field;
		preReplaceChild(oldChild, variable, FIELD_PROPERTY);
		this.field = variable;
		postReplaceChild(oldChild, variable, FIELD_PROPERTY);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == FIELD_PROPERTY) {
			if (get) {
				return getField();
			} else {
				setField((Variable) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	public ASTNode getMember() {
		return getField();
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
		final Expression name = ASTNode.copySubtree(target, getClassName());
		final Variable field = ASTNode.copySubtree(target, getField());
		final StaticFieldAccess result = new StaticFieldAccess(getStart(), getEnd(), target, name, field);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for the field accessed by this
	 * expression.
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public IVariableBinding resolveFieldBinding() {
		return this.ast.getBindingResolver().resolveField(this);
	}

}
