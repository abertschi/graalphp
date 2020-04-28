/*******************************************************************************
 * Copyright (c) 2009 xored software, Inc.  
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0  
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * @since 2.0
 */
@Deprecated
public interface IScriptProjectFilenames {

	public static final String PROJECT_FILENAME = "";

	/**
	 * Name of file containing project buildpath
	 */
	public static final String BUILDPATH_FILENAME = ".buildpath"; //$NON-NLS-1$

	/**
	 * Name of the folder containing project specific options
	 */
	public static final String SETTINGS_FOLDER_NAME = ".settings"; //$NON-NLS-1$

}
