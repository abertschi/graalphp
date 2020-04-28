/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     IBM Corporation - added J2SE 1.5 support
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * Represents an import declaration in Java compilation unit.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 2.0
 */
public interface IImportDeclaration extends IModelElement, ISourceReference {
	/**
	 * Returns the name that has been imported. For an on-demand import, this
	 * includes the trailing <code>".*"</code>. For example, for the statement
	 * <code>"import java.util.*"</code>, this returns
	 * <code>"java.util.*"</code>. For the statement
	 * <code>"import java.util.Hashtable"</code>, this returns
	 * <code>"java.util.Hashtable"</code>.
	 * 
	 * @return the name that has been imported
	 */
	@Override
	String getElementName();

	String getVersion();

	/**
	 * Alternate import name
	 * 
	 * @since 5.2
	 */
	String getAlias();

	/**
	 * Element type
	 * 
	 * @see {IModelElement}
	 * @since 5.2
	 */
	int getType();

	/**
	 * Import modifiers, if any
	 * 
	 * @since 5.2
	 */
	int getFlags();

}
