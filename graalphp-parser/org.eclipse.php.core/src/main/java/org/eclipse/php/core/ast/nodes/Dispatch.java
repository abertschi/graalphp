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

/**
 * Represents a base class for method invocation and field access
 * 
 * <pre>
 * e.g.
 * 
 * $a->$b, foo()->bar(), $myClass->foo()->bar(), A::$a->foo()
 * </pre>
 */
public abstract class Dispatch extends VariableBase {

	private VariableBase dispatcher;

	public Dispatch(int start, int end, AST ast, VariableBase dispatcher) {
		super(start, end, ast);

		if (dispatcher == null) {
			throw new IllegalArgumentException();
		}
		setDispatcher(dispatcher);
	}

	public Dispatch(AST ast) {
		super(ast);
	}

	/**
	 * The dispatcher component of this dispatch expression
	 * 
	 * @return dispatcher component of this dispatch expression
	 */
	public VariableBase getDispatcher() {
		return dispatcher;
	}

	/**
	 * Sets the dispatcher expression of this field access.
	 * 
	 * @param dispatcher
	 *            the new expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setDispatcher(VariableBase dispatcher) {
		if (dispatcher == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.dispatcher;
		preReplaceChild(oldChild, dispatcher, getDispatcherProperty());
		this.dispatcher = dispatcher;
		postReplaceChild(oldChild, dispatcher, getDispatcherProperty());
	}

	@Override
	ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == getDispatcherProperty()) {
			if (get) {
				return getDispatcher();
			} else {
				setDispatcher((VariableBase) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/**
	 * @return the property descriptor of the dispatcher variable
	 */
	abstract ChildPropertyDescriptor getDispatcherProperty();

	/**
	 * @return the property of the dispatch
	 */
	public abstract VariableBase getMember();
}
