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
 * Common protocol for model elements that contain other model elements.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface IParent {
	/**
	 * Returns the immediate children of this element.
	 * Unless otherwise specified by the implementing element,
	 * the children are in no particular order.
	 *
	 * @exception ModelException if this element does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 * @return the immediate children of this element
	 */
	IModelElement[] getChildren() throws ModelException;
	/**
	 * Returns whether this element has one or more immediate children.
	 * This is a convenience method, and may be more efficient than
	 * testing whether <code>getChildren</code> is an empty array.
	 *
	 * @exception ModelException if this element does not exist or if an
	 *      exception occurs while accessing its corresponding resource
	 * @return true if the immediate children of this element, false otherwise
	 */
	boolean hasChildren() throws ModelException;
}
