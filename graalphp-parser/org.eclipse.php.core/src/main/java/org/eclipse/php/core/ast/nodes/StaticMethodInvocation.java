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
 * Represents static function invocation. Holds the function invocation and the
 * class name.
 * 
 * <pre>
 * e.g.
 * 
 * MyClass::foo($a)
 * </pre>
 */
public class StaticMethodInvocation extends StaticDispatch {

	private FunctionInvocation method;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor CLASS_NAME_PROPERTY = new ChildPropertyDescriptor(
			StaticMethodInvocation.class, "className", Expression.class, MANDATORY, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor METHOD_PROPERTY = new ChildPropertyDescriptor(
			StaticMethodInvocation.class, "method", FunctionInvocation.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	@Override
	ChildPropertyDescriptor getClassNameProperty() {
		return StaticMethodInvocation.CLASS_NAME_PROPERTY;
	}

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(2);
		propertyList.add(METHOD_PROPERTY);
		propertyList.add(CLASS_NAME_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public StaticMethodInvocation(int start, int end, AST ast, Expression className, FunctionInvocation method) {
		super(start, end, ast, className);

		if (method == null) {
			throw new IllegalArgumentException();
		}
		setMethod(method);
	}

	public StaticMethodInvocation(AST ast) {
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
		method.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		getClassName().traverseTopDown(visitor);
		method.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		getClassName().traverseBottomUp(visitor);
		method.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<StaticMethodInvocation"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<ClassName>\n"); //$NON-NLS-1$
		getClassName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n").append(TAB).append(tab).append("</ClassName>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		method.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</StaticMethodInvocation>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.STATIC_METHOD_INVOCATION;
	}

	/**
	 * The function invocation node of this invocation
	 * 
	 * @return function invocation node of this invocation
	 */
	public FunctionInvocation getMethod() {
		return method;
	}

	/**
	 * @see #getMethod()
	 */

	@Override
	public ASTNode getMember() {
		return getMethod();
	}

	/**
	 * Sets the method component of this field access.
	 * 
	 * @param method
	 *            the new expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setMethod(FunctionInvocation method) {
		if (method == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.method;
		preReplaceChild(oldChild, method, METHOD_PROPERTY);
		this.method = method;
		postReplaceChild(oldChild, method, METHOD_PROPERTY);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == METHOD_PROPERTY) {
			if (get) {
				return getMethod();
			} else {
				setMethod((FunctionInvocation) child);
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
		final Expression className = ASTNode.copySubtree(target, getClassName());
		final FunctionInvocation method = ASTNode.copySubtree(target, getMethod());
		final StaticMethodInvocation result = new StaticMethodInvocation(getStart(), getEnd(), target, className,
				method);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for this method
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public IMethodBinding resolveMethodBinding() {
		return this.ast.getBindingResolver().resolveMethod(this);
	}
}
