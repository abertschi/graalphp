/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * Interface defining methods that should be exposed by an extension
 * implementation that may have more then one implemenation defined for it.
 * 
 * <p>Users will be able select which implemenation is used via an extension
 * specific preference/property page. Examples:
 * <ul>
 * <li>Source Parsers
 * <li>Debugging Engines
 * </ul>
 * </p>
 */
public interface IDLTKContributedExtension {

	static final String ID = "id"; //$NON-NLS-1$
	static final String NAME = "name"; //$NON-NLS-1$
	static final String DESCRIPTION = "description"; //$NON-NLS-1$
	static final String PREF_PAGE_ID = "preferencePageId"; //$NON-NLS-1$
	static final String PROP_PAGE_ID = "propertyPageId"; //$NON-NLS-1$
	static final String PRIORITY = "priority"; //$NON-NLS-1$
	static final String NATURE_ID = "natureId"; //$NON-NLS-1$

	/**
	 * Returns the contribution id
	 */
	String getId();

	/**
	 * Returns the contribution nature id
	 */
	String getNatureId();

	/**
	 * Returns the contribution name
	 */
	String getName();

	/**
	 * Returns the contribution description
	 */
	String getDescription();

	/**
	 * Returns the contribution's preference page id, or <code>null</code> if
	 * one has not been set.
	 */
	String getPreferencePageId();

	/**
	 * Returns the contributions property page id, or <code>null</code> if one 
	 * has not been set.
	 */
	String getPropertyPageId();
	
	/**
	 * Returns the contribution priority
	 */
	int getPriority();

}