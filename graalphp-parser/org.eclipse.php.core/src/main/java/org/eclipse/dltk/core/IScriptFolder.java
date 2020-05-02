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
 * A script fragment is a portion of the workspace corresponding to an entire
 * package, or to a portion thereof.
 * <p>
 * Script folder elements need to be opened before they can be navigated or
 * manipulated. The children are of type <code>ISourceModule</code>
 * (representing a source file) or <code>IBinaryModule</code> (representing a
 * binary class file). The children are listed in no particular order.
 * </p>
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IScriptFolder extends IOpenable, IParent, IModelElement,
		ISourceManipulation {

	/**
	 * <p>
	 * The name of the root folder (value: the empty string, <code>""</code>).
	 * </p>
	 */
	public static final String ROOT_FOLDER_NAME = ""; //$NON-NLS-1$
	public static final String DEFAULT_FOLDER_NAME = ""; //$NON-NLS-1$
	public static final char PACKAGE_DELIMITER = '/';
	public static final String PACKAGE_DELIMETER_STR = "/"; //$NON-NLS-1$

	/**
	 * Returns the source module with the specified name in this folder. The
	 * name has to be a valid module name. This is a handle-only method. The
	 * module may or may not be present.
	 * 
	 * @param name
	 *            the given name
	 * @return the source module with the specified name in this folder
	 */
	ISourceModule getSourceModule(String name);

	boolean isRootFolder();

	ISourceModule[] getSourceModules() throws ModelException;

	public Object[] getForeignResources() throws ModelException;

	/**
	 * Returns whether this package fragment's name is a prefix of other package
	 * fragments in this package fragment's root.
	 * 
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 * @return true if this package fragment's name is a prefix of other package
	 *         fragments in this package fragment's root, false otherwise
	 */
	boolean hasSubfolders() throws ModelException;

	/**
	 * Returns whether this fragment contains at least one script resource.
	 * 
	 * @return true if this fragment contains at least one script resource,
	 *         false otherwise
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource.
	 */
	boolean containsScriptResources() throws ModelException;

	/**
	 * Creates and returns a compilation unit in this package fragment with the
	 * specified name and contents. No verification is performed on the
	 * contents.
	 * 
	 * <p>
	 * It is possible that a compilation unit with the same name already exists
	 * in this package fragment. The value of the <code>force</code> parameter
	 * effects the resolution of such a conflict:
	 * <ul>
	 * <li> <code>true</code> - in this case the compilation is created with the
	 * new contents</li>
	 * <li> <code>false</code> - in this case a <code>ModelException</code> is
	 * thrown</li>
	 * </ul>
	 * 
	 * @param contents
	 *            the given contents
	 * @param force
	 *            specify how to handle conflict is the same name already exists
	 * @param monitor
	 *            the given progress monitor
	 * @param name
	 *            the given name
	 * @exception ModelException
	 *                if the element could not be created. Reasons include:
	 *                <ul>
	 *                <li> This script element does not exist
	 *                (ELEMENT_DOES_NOT_EXIST)</li> <li> A <code>CoreException
	 *                </code> occurred while creating an underlying resource 
	 *                <li> The name is not a valid compilation unit name
	 *                (INVALID_NAME) <li> The contents are <code>null</code>
	 *                (INVALID_CONTENTS)
	 *                </ul>
	 * @return a compilation unit in this package fragment with the specified
	 *         name and contents
	 */

}
