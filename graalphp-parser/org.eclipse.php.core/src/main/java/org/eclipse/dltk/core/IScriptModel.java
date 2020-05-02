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


@Deprecated
public interface IScriptModel extends IModelElement, IParent, IOpenable {

	/**
	 * Returns the script project with the given name. This is a handle-only
	 * method. The project may or may not exist.
	 * 
	 * @param name
	 *            the name of the script project
	 * @return the script project with the given name
	 */
	IScriptProject getScriptProject(String name);

	/**
	 * Returns the script projects in this model, or an empty array if there are
	 * none.
	 * 
	 * @return the script projects in this model, or an empty array if there are
	 *         none
	 * @exception ModelException
	 *                if this request fails.
	 */
	IScriptProject[] getScriptProjects() throws ModelException;
	
	/**
	 * Returns the script projects in this model of the specified nature, or an
	 * empty array if there are none.
	 * 
	 * @return the script projects in this model of the specified nature, or an
	 *         empty array if there are none
	 * @exception ModelException
	 *                if this request fails.
	 */
	IScriptProject[] getScriptProjects(String nature) throws ModelException;

	/**
	 * Deletes the given elements, forcing the operation if necessary and
	 * specified.
	 * 
	 * @param elements
	 *            the elements to delete
	 * @param force
	 *            a flag controlling whether underlying resources that are not
	 *            in sync with the local file system will be tolerated
	 * @param monitor
	 *            a progress monitor
	 * @exception ModelException
	 *                if an element could not be deleted. Reasons include:
	 *                <ul>
	 *                <li> There is no element to process
	 *                (NO_ELEMENTS_TO_PROCESS). The given elements is null or
	 *                empty</li>
	 *                <li> A specified element does not exist
	 *                (ELEMENT_DOES_NOT_EXIST)</li>
	 *                <li> A <code>CoreException</code> occurred while
	 *                updating an underlying resource</li>
	 *                <li> An element is read-only (<code>READ_ONLY</code>)
	 *                </li>
	 *                </ul>
	 */
//	void delete(IModelElement[] elements, boolean force,
//			IProgressMonitor monitor) throws ModelException;

	/**
	 * Returns whether this script model contains an <code>IModelElement</code>
	 * whose resource is the given resource or a non-script resource which is
	 * the given resource.
	 * <p>
	 * Note: no existency check is performed on the argument resource. If it is
	 * not accessible (see <code>IResource.isAccessible()</code>) yet but
	 * would be located in script model range, then it will return
	 * <code>true</code>.
	 * </p>
	 * <p>
	 * If the resource is accessible, it can be reached by navigating the script
	 * model down using the <code>getChildren()</code> and/or
	 * <code>getNonScriptResources()</code> methods.
	 * </p>
	 * 
	 * @param resource
	 *            the resource to check
	 * @return true if the resource is accessible through the script model
	 * 
	 */
//	boolean contains(IResource resource);

	Object[] getForeignResources() throws ModelException;

	/**
	 * Returns the workspace associated with this script model.
	 * 
	 * @return the workspace associated with this script model
	 */
//	IWorkspace getWorkspace();

	/**
	 * Moves the given elements to the specified container(s). If one container
	 * is specified, all elements are moved to that container. If more than one
	 * container is specified, the number of elements and containers must match,
	 * and each element is moved to its associated container.
	 * <p>
	 * Optionally, each element can positioned before a sibling element. If
	 * <code>null</code> is specified for sibling, the element is inserted as
	 * the last child of its associated container.
	 * </p>
	 * <p>
	 * Optionally, each element can be renamed. If <code>null</code> is
	 * specified for the new name, the element is not renamed.
	 * </p>
	 * <p>
	 * Optionally, any existing child in the destination container with the same
	 * name can be replaced by specifying <code>true</code> for force.
	 * Otherwise an exception is thrown in the event that a name collision
	 * occurs.
	 * </p>
	 * 
	 * @param elements
	 *            the elements to move
	 * @param containers
	 *            the container, or list of containers
	 * @param siblings
	 *            the list of siblings element any of which may be
	 *            <code>null</code>; or <code>null</code>
	 * @param renamings
	 *            the list of new names any of which may be <code>null</code>;
	 *            or <code>null</code>
	 * @param replace
	 *            <code>true</code> if any existing child in a target
	 *            container with the target name should be replaced, and
	 *            <code>false</code> to throw an exception in the event of a
	 *            name collision
	 * @param monitor
	 *            a progress monitor
	 * @exception ModelException
	 *                if an element could not be moved. Reasons include:
	 *                <ul>
	 *                <li> There is no element to process
	 *                (NO_ELEMENTS_TO_PROCESS). The given elements is null or
	 *                empty</li>
	 *                <li> A specified element, container, or sibling does not
	 *                exist (ELEMENT_DOES_NOT_EXIST)</li>
	 *                <li> A <code>CoreException</code> occurred while
	 *                updating an underlying resource</li>
	 *                <li> A container is of an incompatible type (<code>INVALID_DESTINATION</code>)</li>
	 *                <li> A sibling is not a child of it associated container (<code>INVALID_SIBLING</code>)</li>
	 *                <li> A new name is invalid (<code>INVALID_NAME</code>)</li>
	 *                <li> A child in its associated container already exists
	 *                with the same name and <code>replace</code> has been
	 *                specified as <code>false</code> (<code>NAME_COLLISION</code>)</li>
	 *                <li> A container or element is read-only (<code>READ_ONLY</code>)
	 *                </li>
	 *                </ul>
	 * 
	 * @exception IllegalArgumentException
	 *                any element or container is <code>null</code>
	 */
//	void move(IModelElement[] elements, IModelElement[] containers,
//			IModelElement[] siblings, String[] renamings, boolean replace,
//			IProgressMonitor monitor) throws ModelException;

	/**
	 * Copies the given elements to the specified container(s). If one container
	 * is specified, all elements are copied to that container. If more than one
	 * container is specified, the number of elements and containers must match,
	 * and each element is copied to its associated container.
	 * <p>
	 * Optionally, each copy can positioned before a sibling element. If
	 * <code>null</code> is specified for a given sibling, the copy is
	 * inserted as the last child of its associated container.
	 * </p>
	 * <p>
	 * Optionally, each copy can be renamed. If <code>null</code> is specified
	 * for the new name, the copy is not renamed.
	 * </p>
	 * <p>
	 * Optionally, any existing child in the destination container with the same
	 * name can be replaced by specifying <code>true</code> for force.
	 * Otherwise an exception is thrown in the event that a name collision
	 * occurs.
	 * </p>
	 * 
	 * @param elements
	 *            the elements to copy
	 * @param containers
	 *            the container, or list of containers
	 * @param siblings
	 *            the list of siblings element any of which may be
	 *            <code>null</code>; or <code>null</code>
	 * @param renamings
	 *            the list of new names any of which may be <code>null</code>;
	 *            or <code>null</code>
	 * @param replace
	 *            <code>true</code> if any existing child in a target
	 *            container with the target name should be replaced, and
	 *            <code>false</code> to throw an exception in the event of a
	 *            name collision
	 * @param monitor
	 *            a progress monitor
	 * @exception ModelException
	 *                if an element could not be copied. Reasons include:
	 *                <ul>
	 *                <li> There is no element to process
	 *                (NO_ELEMENTS_TO_PROCESS). The given elements is null or
	 *                empty</li>
	 *                <li> A specified element, container, or sibling does not
	 *                exist (ELEMENT_DOES_NOT_EXIST)</li>
	 *                <li> A <code>CoreException</code> occurred while
	 *                updating an underlying resource</li>
	 *                <li> A container is of an incompatible type (<code>INVALID_DESTINATION</code>)</li>
	 *                <li> A sibling is not a child of it associated container (<code>INVALID_SIBLING</code>)</li>
	 *                <li> A new name is invalid (<code>INVALID_NAME</code>)</li>
	 *                <li> A child in its associated container already exists
	 *                with the same name and <code>replace</code> has been
	 *                specified as <code>false</code> (<code>NAME_COLLISION</code>)</li>
	 *                <li> A container or element is read-only (<code>READ_ONLY</code>)
	 *                </li>
	 *                </ul>
	 */
//	void copy(IModelElement[] elements, IModelElement[] containers,
//			IModelElement[] siblings, String[] renamings, boolean replace,
//			IProgressMonitor monitor) throws ModelException;

	/**
	 * Renames the given elements as specified. If one container is specified,
	 * all elements are renamed within that container. If more than one
	 * container is specified, the number of elements and containers must match,
	 * and each element is renamed within its associated container.
	 * 
	 * @param elements
	 *            the elements to rename
	 * @param destinations
	 *            the container, or list of containers
	 * @param names
	 *            the list of new names
	 * @param replace
	 *            <code>true</code> if an existing child in a target container
	 *            with the target name should be replaced, and
	 *            <code>false</code> to throw an exception in the event of a
	 *            name collision
	 * @param monitor
	 *            a progress monitor
	 * @exception ModelException
	 *                if an element could not be renamed. Reasons include:
	 *                <ul>
	 *                <li> There is no element to process
	 *                (NO_ELEMENTS_TO_PROCESS). The given elements is null or
	 *                empty</li>
	 *                <li> A specified element does not exist
	 *                (ELEMENT_DOES_NOT_EXIST)</li>
	 *                <li> A <code>CoreException</code> occurred while
	 *                updating an underlying resource
	 *                <li> A new name is invalid (<code>INVALID_NAME</code>)
	 *                <li> A child already exists with the same name and
	 *                <code>replace</code> has been specified as
	 *                <code>false</code> (<code>NAME_COLLISION</code>)
	 *                <li> An element is read-only (<code>READ_ONLY</code>)
	 *                </ul>
	 */
//	void rename(IModelElement[] elements, IModelElement[] destinations,
//			String[] names, boolean replace, IProgressMonitor monitor)
//			throws ModelException;
}
