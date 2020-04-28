/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * Represents a method (or constructor) declared in a type.
 */
public interface IMethod extends IMember {

	/**
	 * Returns the parameters in this method. Returns an empty array if this
	 * method has no parameters.
	 * 
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 * @return the parameters in this method, an empty array if this method has
	 *         no parameters
	 */
	public IParameter[] getParameters() throws ModelException;

	/**
	 * Returns the names of parameters in this method. Returns an empty array if
	 * this method has no parameters.
	 * 
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 * @return the names of parameters in this method, an empty array if this
	 *         method has no parameters
	 */
	public String[] getParameterNames() throws ModelException;

	/**
	 * Returns whether this method is a constructor.
	 * 
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 * 
	 * @return true if this method is a constructor, false otherwise
	 */
	boolean isConstructor() throws ModelException;

	/**
	 * Returns the signature of this method. This includes the signatures for
	 * the parameter types and return type, but does not include the method
	 * name, exception types, or type parameters.
	 * <p>
	 * For example, a source method declared as
	 * <code>public void foo(String text, int length)</code> would return
	 * <code>"(QString;I)V"</code>.
	 * </p>
	 * <p>
	 * The type signatures embedded in the method signature may be either
	 * unresolved (for source types) or resolved (for binary types), and either
	 * basic (for basic types) or rich (for parameterized types).
	 * </p>
	 * 
	 * @return the signature of this method
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 */

	String getFullyQualifiedName(String enclosingTypeSeparator);

	String getFullyQualifiedName();

	String getTypeQualifiedName(String enclosingTypeSeparator,
			boolean showParameters) throws ModelException;

	/**
	 * Return language dependent type name
	 * 
	 * @return
	 * @since 2.0
	 */
	String getType() throws ModelException;
}