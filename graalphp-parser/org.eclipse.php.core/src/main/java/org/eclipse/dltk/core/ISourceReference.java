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
 * Common protocol for model elements that have associated source code.
 */
public interface ISourceReference {
	/**
	 * Returns whether this element exists in the model.
	 *
	 * @return <code>true</code> if this element exists in the script model
	 *
	 */
	boolean exists();
			
	/**
	 * Returns the source range associated with this element.
	 * <p>
	 * For class files, this returns the range of the entire compilation unit 
	 * associated with the class file (if there is one).
	 * </p>
	 *
	 * @return the source range, or <code>null</code> if this element has no 
	 *   associated source code
	 * @exception ModelException if an exception occurs while accessing its corresponding resource
	 */
	ISourceRange getSourceRange() throws ModelException;
	
	/**
	 * Returns the source code associated with this element.
	 * This extracts the substring from the source buffer containing this source
	 * element. This corresponds to the source range that would be returned by
	 * <code>getSourceRange</code>.
	 * <p>
	 * For class files, this returns the source of the entire compilation unit 
	 * associated with the class file (if there is one).
	 * </p>
	 *
	 * @return the source code, or <code>null</code> if this element has no 
	 *   associated source code
	 * @exception ModelException if an exception occurs while accessing its corresponding resource
	 */
	String getSource() throws ModelException;

	/**
	 * Returns the name range associated with this element.
	 * 
	 * <p>
	 * If the element is an {@link IMember}, it returns the source range of this
	 * member's simple name, or <code>null</code> if this member does not have a
	 * name (for example, an initializer), or if this member does not have
	 * associated source code (for example, a binary type).
	 * </p>
	 * 
	 * <p>
	 * If this element is an {@link IImportDeclaration}, the source range of
	 * this import declaration's name, or <code>null</code> if this import
	 * declaration does not have associated source code (for example, a binary
	 * type). <br>
	 * The source range for the name includes the trailing '*' if the call to
	 * {@link IImportDeclaration#isOnDemand()} returns true.
	 * </p>
	 * 
	 * <p>
	 * If this element is an {@link IPackageDeclaration}, the source range of
	 * this package declaration's name, or <code>null</code> if this package
	 * declaration does not have associated source code (for example, a binary
	 * type).
	 * </p>
	 * 
	 * <p>
	 * If this element is an {@link IImportContainer}, it returns null.
	 * </p>
	 * 
	 * @return the name range associated with this element, or <code>null</code>
	 *         if not available
	 * @since 5.0
	 */
	ISourceRange getNameRange() throws ModelException;
}
