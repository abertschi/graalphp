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
 * Represents a constant class access
 * 
 * <pre>
 * e.g.
 * 
 * 		MyClass::CONST
 * </pre>
 */
public class StaticConstantAccess extends StaticDispatch {

	private Identifier constant;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor CLASS_NAME_PROPERTY = new ChildPropertyDescriptor(
			StaticConstantAccess.class, "className", Expression.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor CONSTANT_PROPERTY = new ChildPropertyDescriptor(
			StaticConstantAccess.class, "parameterType", Identifier.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$

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
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(2);
		properyList.add(CLASS_NAME_PROPERTY);
		properyList.add(CONSTANT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public StaticConstantAccess(int start, int end, AST ast, Expression className, Identifier constant) {
		super(start, end, ast, className);

		if (constant == null) {
			throw new IllegalArgumentException();
		}
		setConstant(constant);
	}

	public StaticConstantAccess(AST ast) {
		super(ast);
	}

	public StaticConstantAccess(int start, int end, AST ast, Identifier name) {
		this(start, end, ast, null, name);
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
		constant.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		getClassName().traverseTopDown(visitor);
		constant.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		getClassName().traverseTopDown(visitor);
		constant.traverseTopDown(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<StaticConstantAccess"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<ClassName>\n"); //$NON-NLS-1$
		getClassName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</ClassName>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(TAB).append(tab).append("<Constant>\n"); //$NON-NLS-1$
		constant.toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</Constant>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(tab).append("</StaticConstantAccess>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.STATIC_CONSTANT_ACCESS;
	}

	/**
	 * Constant name of this static dispatch
	 * 
	 * @return constant name of this static dispatch
	 */
	public Identifier getConstant() {
		return constant;
	}

	/**
	 * Sets the constant name identifier
	 * 
	 * @param name
	 *            the class name of this dispatch
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setConstant(Identifier name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.constant;
		preReplaceChild(oldChild, name, CONSTANT_PROPERTY);
		this.constant = name;
		postReplaceChild(oldChild, name, CONSTANT_PROPERTY);
	}

	@Override
	ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == CONSTANT_PROPERTY) {
			if (get) {
				return getConstant();
			} else {
				setConstant((Identifier) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	public ASTNode getMember() {
		return getConstant();
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
		final Expression className = ASTNode.copySubtree(target, getClassName());
		final Identifier constant = ASTNode.copySubtree(target, getConstant());
		final StaticConstantAccess result = new StaticConstantAccess(getStart(), getEnd(), target, className, constant);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for the static constant accessed by this
	 * expression.
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public IVariableBinding resolveFieldBinding() {
		return this.ast.getBindingResolver().resolveField(this);
	}
}
