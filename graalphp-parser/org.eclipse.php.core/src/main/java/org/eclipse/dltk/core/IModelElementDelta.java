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



public interface IModelElementDelta {

	/**
	 * Status constant indicating that the element has been added. Note that an
	 * added model element delta has no children, as they are all implicitely
	 * added.
	 */
	public int ADDED = 1;

	/**
	 * Status constant indicating that the element has been removed. Note that a
	 * removed model element delta has no children, as they are all implicitely
	 * removed.
	 */
	public int REMOVED = 2;

	/**
	 * Status constant indicating that the element has been changed, as
	 * described by the change flags.
	 * 
	 * @see #getFlags()
	 */
	public int CHANGED = 4;

	/**
	 * Change flag indicating that the content of the element has changed. This
	 * flag is only valid for elements which correspond to files.
	 */
	public int F_CONTENT = 0x00001;

	/**
	 * Change flag indicating that the modifiers of the element have changed.
	 * This flag is only valid if the element is an <code>IMember</code>.
	 */
	public int F_MODIFIERS = 0x00002;

	/**
	 * Change flag indicating that there are changes to the children of the
	 * element. This flag is only valid if the element is an
	 * <code>IParent</code>.
	 */
	public int F_CHILDREN = 0x00008;

	/**
	 * Change flag indicating that the element was moved from another location.
	 * The location of the old element can be retrieved using
	 * <code>getMovedFromElement</code>.
	 */
	public int F_MOVED_FROM = 0x00010;

	/**
	 * Change flag indicating that the element was moved to another location.
	 * The location of the new element can be retrieved using
	 * <code>getMovedToElement</code>.
	 */
	public int F_MOVED_TO = 0x00020;

	/**
	 * Change flag indicating that a buildpath entry corresponding to the
	 * element has been added to the project's buildpath. This flag is only
	 * valid if the element is an <code>IProjectFragment</code>.
	 */
	public int F_ADDED_TO_BUILDPATH = 0x00040;

	/**
	 * Change flag indicating that a buildpath entry corresponding to the
	 * element has been removed from the project's buildpath. This flag is only
	 * valid if the element is an <code>IProjectFragment</code>.
	 */
	public int F_REMOVED_FROM_BUILDPATH = 0x00080;

	/**
	 * Change flag indicating that the element has changed position relatively
	 * to its siblings. If the element is an <code>IProjectFragment</code>, a
	 * buildpath entry corresponding to the element has changed position in the
	 * project's buildpath.
	 */
	public int F_REORDER = 0x00100;

	/**
	 * Change flag indicating that the underlying <code>IProject</code> has been
	 * opened. This flag is only valid if the element is an
	 * <code>IScriptProject</code>.
	 */
	public int F_OPENED = 0x000200;

	/**
	 * Change flag indicating that the underlying <code>IProject</code> has been
	 * closed. This flag is only valid if the element is an
	 * <code>IScriptProject</code>.
	 */
	public int F_CLOSED = 0x000400;

	/**
	 * Change flag indicating that one of the supertypes of an
	 * <code>IType</code> has changed.
	 */
	public int F_SUPER_TYPES = 0x00800;

	/**
	 * Change flag indicating that this is a fine-grained delta, that is, an
	 * analysis down to the members level was done to determine if there were
	 * structural changes to members.
	 * <p>
	 * Clients can use this flag to find out if a compilation unit that have a
	 * <code>F_CONTENT</code> change should assume that there are no finer
	 * grained changes (<code>F_FINE_GRAINED</code> is set) or if finer grained
	 * changes were not considered (<code>F_FINE_GRAINED</code> is not set).
	 */
	public int F_FINE_GRAINED = 0x04000;

	/**
	 * Change flag indicating that the element's archive content on the
	 * buildpath has changed. This flag is only valid if the element is an
	 * <code>IProjectFragment</code> which is an archive.
	 * 
	 * @see IProjectFragment#isArchive()
	 */
	public int F_ARCHIVE_CONTENT_CHANGED = 0x008000;

	/**
	 * Change flag indicating that a compilation unit has become a primary
	 * working copy, or that a primary working copy has reverted to a
	 * compilation unit. This flag is only valid if the element is an
	 * <code>ISourceModule</code>.
	 */
	public int F_PRIMARY_WORKING_COPY = 0x10000;

	/**
	 * Change flag indicating that the raw buildpath of a project has changed.
	 * This flag is only valid if the element is an <code>IScriptProject</code>.
	 */
	public int F_BUILDPATH_CHANGED = 0x20000;

	/**
	 * Change flag indicating that the resource of a primary compilation unit
	 * has changed. This flag is only valid if the element is a primary
	 * <code>ISourceModule</code>.
	 */
	public int F_PRIMARY_RESOURCE = 0x040000;

	/**
	 * Change flag indicating that the
	 * {@link IScriptProject#getResolvedBuildpath(boolean) resolved buildpath}
	 * of a project has changed. This flag is only valid if the element is an
	 * {@link IScriptProject}. Also see {@link #F_BUILDPATH_CHANGED}, which
	 * indicates that there is a change to the
	 * {@link IScriptProject#getRawBuildpath() raw class path}. The resolved
	 * buildpath can change without the raw buildpath changing (e.g. if a
	 * container resolves to a different set of buildpath entries). And
	 * conversely, it is possible to construct a case where the raw buildpath
	 * can change without the resolved buildpath changing.
	 * 
	 * @since 3.4
	 */
	public int F_RESOLVED_BUILDPATH_CHANGED = 0x200000;

	/**
	 * Returns the element that this delta describes a change to.
	 * 
	 * @return the element that this delta describes a change to
	 */
	public IModelElement getElement();

	/**
	 * Returns deltas for the children that have been added.
	 * 
	 * @return deltas for the children that have been added
	 */
	public IModelElementDelta[] getAddedChildren();

	/**
	 * Returns deltas for the affected (added, removed, or changed) children.
	 * 
	 * @return deltas for the affected (added, removed, or changed) children
	 */
	public IModelElementDelta[] getAffectedChildren();

	/**
	 * Returns the kind of this delta - one of <code>ADDED</code>,
	 * <code>REMOVED</code>, or <code>CHANGED</code>.
	 * 
	 * @return the kind of this delta
	 */
	public int getKind();

	/**
	 * Returns flags that describe how an element has changed. Such flags should
	 * be tested using the <code>&</code> operand. For example:
	 * 
	 * <pre>
	 * if ((delta.getFlags() &amp; IModelElementDelta.F_CONTENT) != 0) {
	 * 	// the delta indicates a content change
	 * }
	 * </pre>
	 * 
	 * @return flags that describe how an element has changed
	 */
	public int getFlags();

	/**
	 * Returns the collection of resource deltas.
	 * <p>
	 * Note that resource deltas, like Model element deltas, are generally only
	 * valid for the dynamic scope of an event notification. Clients must not
	 * hang on to these objects.
	 * </p>
	 * 
	 * @return the underlying resource deltas, or <code>null</code> if none
	 */
//	public IResourceDelta[] getResourceDeltas();

	/**
	 * Returns an element describing this element before it was moved to its
	 * current location, or <code>null</code> if the <code>F_MOVED_FROM</code>
	 * change flag is not set.
	 * 
	 * @return an element describing this element before it was moved to its
	 *         current location, or <code>null</code> if the
	 *         <code>F_MOVED_FROM</code> change flag is not set
	 */
	public IModelElement getMovedFromElement();

	/**
	 * Returns an element describing this element in its new location, or
	 * <code>null</code> if the <code>F_MOVED_TO</code> change flag is not set.
	 * 
	 * @return an element describing this element in its new location, or
	 *         <code>null</code> if the <code>F_MOVED_TO</code> change flag is
	 *         not set
	 */
	public IModelElement getMovedToElement();
}
