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

public interface IModelStatusConstants {

	public static final int INTERNAL_ERROR = 10001;
	/**
	 * Status constant indicating a core exception occurred. Use
	 * <code>getException</code> to retrieve a <code>CoreException</code>.
	 */
	public static final int CORE_EXCEPTION = 966;

	/**
	 * Status constant indicating one or more of the elements supplied are not
	 * of a valid type for the operation to process. The element(s) can be
	 * retrieved using <code>getElements</code> on the status object.
	 */
	public static final int INVALID_ELEMENT_TYPES = 967;

	/**
	 * Status indicating that a script element could not be created because the
	 * underlying resource is invalid.
	 * 
	 * @see DLTKCore
	 */
	public static final int INVALID_RESOURCE = 995;

	/**
	 * Status constant indicating that a buildpath entry was invalid
	 */
	public static final int INVALID_BUILDPATH = 964;

	/**
	 * Status constant indicating that a container path was not resolvable
	 * indicating either the referred container is undefined, unbound.
	 * 
	 * 
	 */
	public static final int BP_CONTAINER_PATH_UNBOUND = 963;

	/**
	 * Status constant indicating that a variable path was not resolvable
	 * indicating either the referred variable is undefined, unbound or the
	 * resolved variable path does not correspond to an existing file or folder.
	 */
	public static final int BP_VARIABLE_PATH_UNBOUND = 965;

	/**
	 * Status constant indicating that a path provided to an operation is
	 * invalid. The path can be retrieved using <code>getPath</code> on the
	 * status object.
	 */
	public static final int INVALID_PATH = 979;

	/**
	 * Status constant indicating the given source position is out of bounds.
	 */
	public static final int INDEX_OUT_OF_BOUNDS = 980;

	/**
	 * Status constant indicating that the specified contents are not valid.
	 */
	public static final int INVALID_CONTENTS = 984;

	/**
	 * Status constant indicating there is an update conflict for a working
	 * copy. The compilation unit on which the working copy is based has changed
	 * since the working copy was created.
	 */
	public static final int UPDATE_CONFLICT = 981;

	/**
	 * Status constant indicating that a path specifying a device was supplied
	 * to the operation when a path with no device is required. The path can be
	 * retrieved using <code>getPath</code> on the status object.
	 */
	public static final int DEVICE_PATH = 973;

	/**
	 * Status constant indicating that no elements were provided to the
	 * operation for processing.
	 */
	public static final int NO_ELEMENTS_TO_PROCESS = 968;

	/**
	 * Status constant indicating that one or more elements supplied do not
	 * exist. The element(s) can be retrieved using <code>getElements</code> on
	 * the status object.
	 * 
	 *
	 */
	public static final int ELEMENT_DOES_NOT_EXIST = 969;

	/**
	 * Status constant indicating that the operation encountered a read-only
	 * element. The element(s) can be retrieved using <code>getElements</code>
	 * on the status object.
	 */
	public static final int READ_ONLY = 976;

	/**
	 * Status constant indicating that a naming collision would occur if the
	 * operation proceeded.
	 */
	public static final int NAME_COLLISION = 977;

	/**
	 * Status constant indicating that an <code>java.io.IOException</code>
	 * occurred.
	 */
	public static final int IO_EXCEPTION = 985;

	/**
	 * Status indicating that a .buildpath file is ill-formed, and thus cannot
	 * be read/written successfully.
	 */
	public static final int INVALID_BUILDPATH_FILE_FORMAT = 1000;

	/**
	 * Status indicating that a project is involved in a build path cycle.
	 */
	public static final int BUILDPATH_CYCLE = 1001;

	/**
	 * Status constant indicating that an element is not on its project's
	 * buildpath.
	 */
	public static final int ELEMENT_NOT_ON_BUILDPATH = 1006;

	/**
	 * Status constant indicating that a destination provided for a
	 * copy/move/rename operation is invalid. The destination element can be
	 * retrieved using <code>getElements</code> on the status object.
	 */
	public static final int INVALID_DESTINATION = 978;

	/**
	 * Status constant indicating that <code>null</code> was specified as a name
	 * argument.
	 */
	public static final int NULL_NAME = 982;

	/**
	 * Status constant indicating that a name provided is not syntactically
	 * correct. The name can be retrieved from <code>getString</code>.
	 */
	public static final int INVALID_NAME = 983;

	/**
	 * Status indicating that the package declaration in a
	 * <code>ISourceModule</code> does not correspond to the
	 * <code>IScriptFolder</code> it belongs to. The <code>getString</code>
	 * method of the associated status object gives the name of the package in
	 * which the <code>ISourceModule</code> is declared.
	 */
	public static final int INVALID_SCRIPT_FOLDER = 998;

	/**
	 * Status indicating that a script element could not be created because the
	 * project owning underlying resource does not have the script nature.
	 * 
	 */
	public static final int INVALID_PROJECT = 997;

	/**
	 * Status indicating that a script element could not be created because the
	 * underlying resource is not of an appropriate type.
	 */
	public static final int INVALID_RESOURCE_TYPE = 996;

	/**
	 * Status constant indicating that a sibling specified is not valid.
	 */
	public static final int INVALID_SIBLING = 993;

	/**
	 * Status constant indicating that a <code>null</code> path was supplied to
	 * the operation.
	 */
	public static final int NULL_PATH = 970;

	/**
	 * Status constant indicating that a string was supplied to the operation
	 * that was <code>null</code>.
	 */
	public static final int NULL_STRING = 974;

	/**
	 * Status constant indicating that a path outside of the project was
	 * supplied to the operation. The path can be retrieved using
	 * <code>getPath</code> on the status object.
	 */
	public static final int PATH_OUTSIDE_PROJECT = 971;

	/**
	 * Status constant indicating that a relative path was supplied to the
	 * operation when an absolute path is required. The path can be retrieved
	 * using <code>getPath</code> on the status object.
	 */
	public static final int RELATIVE_PATH = 972;

	/**
	 * Status constant indicating that a <code>TargetException</code> occurred.
	 */
	public static final int TARGET_EXCEPTION = 987;

	/**
	 * Status indicating that the corresponding resource has no local contents
	 * yet. This might happen when attempting to use a resource before its
	 * contents has been made locally available.
	 */
	public static final int NO_LOCAL_CONTENTS = 999;

	/**
	 * Status constant indicating that a container path was resolved to invalid
	 * entries (null or container).
	 * 
	 */
	public static final int INVALID_BP_CONTAINER_ENTRY = 962;

	/**
	 * Status constant indicating that an inclusion or an exclusion pattern got
	 * specified on a buildpath source entry, though it was explicitely disabled
	 * according to its project preference settings.
	 * 
	 * @see org.eclipse.dltk.core.IScriptProject#getOptions(boolean)
	 * 
	 */
	public static final int DISABLED_BP_EXCLUSION_PATTERNS = 1002;

	public static final int VALIDATE_EDIT_CHANGED_CONTENT = 10003;
}
