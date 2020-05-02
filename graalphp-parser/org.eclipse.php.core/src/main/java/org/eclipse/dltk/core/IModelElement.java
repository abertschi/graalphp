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

//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.IAdaptable;
//import org.eclipse.core.runtime.IPath;

/**
 * Common protocol for all elements provided by the script model. Script model
 * elements are exposed to clients as handles to the actual underlying element.
 * The model may hand out any number of handles for each element. Handles that
 * refer to the same element are guaranteed to be equal, but not necessarily
 * identical.
 * <p>
 * Methods annotated as "handle-only" do not require underlying elements to
 * exist. Methods that require underlying elements to exist throw a
 * <code>ModelException</code> when an underlying element is missing.
 * <code>ModelException.isDoesNotExist</code> can be used to recognize this
 * common special case.
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
@Deprecated
public interface IModelElement { // extends IAdaptable {

	/**
	 * Constant representing a script model (workspace level object). An element
	 * with this type can be safely cast to <code>IModel</code>.
	 */
	int SCRIPT_MODEL = 1;

	/**
	 * Constant representing a script project. An element with this type can be
	 * safely cast to <code>IScriptProject</code>.
	 */
	int SCRIPT_PROJECT = 2;

	/**
	 * Constant representing a project fragment. An element with this type can
	 * be safely cast to <code>IProjectFragment</code>.
	 */
	int PROJECT_FRAGMENT = 3;

	/**
	 * Constant representing a folder. An element with this type can be safely
	 * cast to <code>ISourceFolder</code>.
	 */
	int SCRIPT_FOLDER = 4;

	/**
	 * Constant representing a source module. An element with this type can be
	 * safely cast to <code>ISourceModule</code>.
	 */
	int SOURCE_MODULE = 5;

	/**
	 * Constant representing a binary module. An element with this type can be
	 * safely cast to <code>IBinaryModule</code>.
	 */
	int BINARY_MODULE = 6;

	/**
	 * Constant representing a type (a class or interface). An element with this
	 * type can be safely cast to <code>IType</code>.
	 */
	int TYPE = 7;

	/**
	 * Constant representing a field or variable. An element with this type can
	 * be safely cast to <code>IField</code>.
	 */
	int FIELD = 8;

	/**
	 * Constant representing a method or procedure. An element with this type
	 * can be safely cast to <code>IMethod</code>.
	 */
	int METHOD = 9;

	/**
	 * Constant representing a package declaration within a compilation unit. A
	 * model element with this type can be safely cast to
	 * <code>IPackageDeclaration</code>.
	 */
	int PACKAGE_DECLARATION = 10;

	/**
	 * Constant representing all import declarations within a compilation unit.
	 * A model element with this type can be safely cast to
	 * {@link IImportContainer}.
	 * 
	 * @since 2.0
	 */
	int IMPORT_CONTAINER = 11;

	/**
	 * Constant representing an import declaration within a compilation unit. A
	 * model element with this type can be safely cast to
	 * {@link IImportDeclaration}.
	 * 
	 * @since 2.0
	 */
	int IMPORT_DECLARATION = 12;

	/**
	 * Constant representing a local variable declaration. A model element with
	 * this type can be safely cast to {@link ILocalVariable}.
	 * 
	 * @since 2.0
	 */
	int LOCAL_VARIABLE = 13;

	/**
	 * Starting point for any kind of user elements.
	 */
	int USER_ELEMENT = 15;

	/**
	 * Returns this element's kind encoded as an integer. This is a handle-only
	 * method.
	 * 
	 * @return the kind of element; one of the constants declared in
	 *         <code>IModelElement</code>
	 */
	int getElementType();

	/**
	 * Returns the name of this element. This is a handle-only method.
	 * 
	 * @return the element name
	 */
	String getElementName();

	/**
	 * Returns the element directly containing this element, or
	 * <code>null</code> if this element has no parent. This is a handle-only
	 * method.
	 * 
	 * @return the parent element, or <code>null</code> if this element has no
	 *         parent
	 */
	IModelElement getParent();

	/**
	 * Returns whether this element is read-only. An element is read-only if its
	 * structure cannot be modified by the model.
	 * <p>
	 * Note this is different from IResource.isReadOnly(). For example, .zip
	 * files are read-only as the model doesn't know how to add/remove elements
	 * in this file, but the underlying IFile can be writable.
	 * <p>
	 * This is a handle-only method.
	 * 
	 * @return <code>true</code> if this element is read-only
	 */
	boolean isReadOnly();

	/**
	 * Returns the innermost resource enclosing this element. If this element is
	 * included in an archive and this archive is not external, this is the
	 * underlying resource corresponding to the archive. If this element is
	 * included in an external archive, <code>null</code> is returned. This is a
	 * handle-only method.
	 * 
	 * @return the innermost resource enclosing this element, <code>null</code>
	 *         if this element is included in an external archive
	 */
//	IResource getResource();

	/**
	 * Returns the path to the innermost resource enclosing this element. If
	 * this element is not included in an external archive, the path returned is
	 * the full, absolute path to the underlying resource, relative to the
	 * workbench. If this element is included in an external archive, the path
	 * returned is the absolute path to the archive in the file system. This is
	 * a handle-only method.
	 * 
	 * @return the path to the innermost resource enclosing this element
	 */
//	IPath getPath();

	/**
	 * Returns whether this element exists in the model.
	 * <p>
	 * Model elements are handle objects that may or may not be backed by an
	 * actual element. Model elements that are backed by an actual element are
	 * said to "exist", and this method returns <code>true</code>. For Model
	 * elements that are not working copies, it is always the case that if the
	 * element exists, then its parent also exists (provided it has one) and
	 * includes the element as one of its children. It is therefore possible to
	 * navigated to any existing model element from the root of the model along
	 * a chain of existing model elements. On the other hand, working copies are
	 * said to exist until they are destroyed (with
	 * <code>IWorkingCopy.destroy</code>). Unlike regular model elements, a
	 * working copy never shows up among the children of its parent element
	 * (which may or may not exist).
	 * </p>
	 * 
	 * @return <code>true</code> if this element exists in the model, and
	 *         <code>false</code> if this element does not exist
	 */
	boolean exists();

	/**
	 * Returns the first ancestor of this script element that has the given
	 * type. Returns <code>null</code> if no such an ancestor can be found. This
	 * is a handle-only method.
	 * 
	 * @param ancestorType
	 *            the given type
	 * @return the first ancestor of this script element that has the given
	 *         type, null if no such an ancestor can be found
	 * 
	 */
	IModelElement getAncestor(int ancestorType);

	/**
	 * Returns the first ancestor of this script element that has the given
	 * type. Returns <code>null</code> if no such an ancestor can be found. This
	 * is a handle-only method.
	 * 
	 * @param ancestorType
	 *            the given type
	 * @return the first ancestor of this script element that has the given
	 *         type, null if no such an ancestor can be found
	 */
	<E extends IModelElement> E getAncestor(Class<E> clazz);

	/**
	 * Returns the first openable parent. If this element is openable, the
	 * element itself is returned. Returns <code>null</code> if this element
	 * doesn't have an openable parent. This is a handle-only method.
	 * 
	 * @return the first openable parent or <code>null</code> if this element
	 *         doesn't have an openable parent.
	 */
	IOpenable getOpenable();

	/**
	 * Returns the script model. This is a handle-only method.
	 * 
	 * @return the script model
	 */
	IScriptModel getModel();

	/**
	 * Returns the Script project this element is contained in, or
	 * <code>null</code> if this element is not contained in any script project
	 * (for instance, the <code>IScriptModel</code> is not contained in any
	 * Script project). This is a handle-only method.
	 * 
	 * @return the containing Script project, or <code>null</code> if this
	 *         element is not contained in a Script project
	 */
	IScriptProject getScriptProject();

	/**
	 * Returns the smallest underlying resource that contains this element, or
	 * <code>null</code> if this element is not contained in a resource.
	 * 
	 * @return the underlying resource, or <code>null</code> if none
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its underlying resource
	 */
//	IResource getUnderlyingResource() throws ModelException;

	/**
	 * Returns the resource that corresponds directly to this element, or
	 * <code>null</code> if there is no resource that corresponds to this
	 * element.
	 * <p>
	 * For example, the corresponding resource for an <code>ISourceModule</code>
	 * is its underlying <code>IFile</code>. The corresponding resource for an
	 * <code>IScriptFolder</code> that is not contained in an archive is its
	 * underlying <code>IFolder</code>. An <code>IScriptFolder</code> contained
	 * in an archive has no corresponding resource. Similarly, there are no
	 * corresponding resources for <code>IMethods</code>, <code>IFields</code>,
	 * etc.
	 * <p>
	 * 
	 * @return the corresponding resource, or <code>null</code> if none
	 * @exception ModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource
	 */
//	IResource getCorrespondingResource() throws ModelException;

	/**
	 * Returns the primary element (whose compilation unit is the primary
	 * compilation unit) this working copy element was created from, or this
	 * element if it is a descendant of a primary compilation unit or if it is
	 * not a descendant of a working copy (e.g. it is a binary member). The
	 * returned element may or may not exist.
	 * 
	 * @return the primary element this working copy element was created from,
	 *         or this element.
	 * 
	 */
	IModelElement getPrimaryElement();

	/**
	 * Returns a string representation of this element handle. The format of the
	 * string is not specified; however, the identifier is stable across
	 * workspace sessions, and can be used to recreate this handle via the
	 * <code>DLTKCore.create(String)</code> method.
	 * 
	 * @return the string handle identifier
	 * @see DLTKCore#create(java.lang.String)
	 */
	String getHandleIdentifier();

	/**
	 * Returns whether the structure of this element is known. For example, for
	 * a soure module that could not be parsed, <code>false</code> is returned.
	 * If the structure of an element is unknown, navigations will return
	 * reasonable defaults. For example, <code>getChildren</code> will return an
	 * empty collection.
	 * <p>
	 * Note: This does not imply anything about consistency with the underlying
	 * resource/buffer contents.
	 * </p>
	 * 
	 * @return <code>true</code> if the structure of this element is known
	 * @exception ScriptModelException
	 *                if this element does not exist or if an exception occurs
	 *                while accessing its corresponding resource
	 */
	// TODO (philippe) predicate shouldn't throw an exception
	boolean isStructureKnown() throws ModelException;

	void accept(IModelElementVisitor visitor) throws ModelException;
}
