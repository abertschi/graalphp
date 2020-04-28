/*******************************************************************************
 * Copyright (c) 2008 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Andrei Sobolev)
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.util.List;

/**
 * This interface could be used to extend generic structure model building.
 * 
 * Model providers are registered using the "org.eclipse.dltk.core.model"
 * extension point.
 */
public interface IModelProvider {
	/**
	 * Called for each model element buildStructure.
	 * 
	 * Can remove some elements from children's set.
	 * 
	 * Any new elements need to implement @see:IModelElementMemento to handle
	 * inner element references.
	 */
	void provideModelChanges(IModelElement parentElement,
			List<IModelElement> children);

	/**
	 * Used for performance reasons.
	 * 
	 * Should return true if provider provides some elements at selected level.
	 */
	boolean isModelChangesProvidedFor(IModelElement modelElement, String name);
}
