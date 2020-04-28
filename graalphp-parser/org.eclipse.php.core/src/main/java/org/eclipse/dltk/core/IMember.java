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
 * Common protocol for model elements that can be members of modules.
 */
public interface IMember extends IModelElement, ISourceReference, IParent {

	/**
	 * Returns the modifier flags for this member.
	 * <p>
	 * Note that only flags as indicated in the source are returned. Thus if an
	 * interface defines a method <code>void myMethod();</code> the flags don't
	 * include the 'public' flag.
	 * 
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 * @return the modifier flags for this member
	 * @see Flags
	 */
	public int getFlags() throws ModelException;

	/**
	 * Returns the package name in which this member is declared, or
	 * <code>null</code> if this member is not declared in a package (for
	 * example, nested into another member). This is a handle-only method.
	 * 
	 * @return the package name if which this member is declared, or
	 *         <code>null</code> if this member is not declared in the package
	 * @throws ModelException
	 * @since 3.0
	 */
	INamespace getNamespace() throws ModelException;

	/**
	 * Returns the type in which this member is declared, or <code>null</code>
	 * if this member is not declared in a type (for example, a top-level type).
	 * This is a handle-only method.
	 * 
	 * @return the type in which this member is declared, or <code>null</code>
	 *         if this member is not declared in a type (for example, a
	 *         top-level type)
	 */
	IType getDeclaringType();

	/**
	 * Returns the source module in which this member is declared, or
	 * <code>null</code> if this member is not declared in a compilation unit
	 * (for example, a binary type). This is a handle-only method.
	 * 
	 * @return the source module in which this member is declared, or
	 *         <code>null</code> if this member is not declared in a compilation
	 *         unit (for example, a binary type)
	 */
	ISourceModule getSourceModule();

	/**
	 * Returns the local type declared in this source member with the given
	 * simple name and/or with the specified position relative to the order they
	 * are defined in the source. The name is empty if it is an anonymous type.
	 * Numbering starts at 1 (thus the first occurrence is occurrence 1, not
	 * occurrence 0). This is a handle-only method. The type may or may not
	 * exist. Throws a <code>RuntimeException</code> if this member is not a
	 * source member.
	 * 
	 * @param name
	 *            the given simple name
	 * @param occurrenceCount
	 *            the specified position
	 * @return the type with the given name and/or with the specified position
	 *         relative to the order they are defined in the source
	 * 
	 */
	IType getType(String name, int occurrenceCount);
}
