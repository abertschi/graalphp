/*******************************************************************************
 * Copyright (c) 2010 xored software, Inc.
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

public interface IParameter {

	/**
	 * Returns the parameter name
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Returns the parameter type as source code string or <code>null</code> if
	 * not specified
	 * 
	 * @return
	 */
	String getType();

	/**
	 * Returns the parameter default value as source code string or
	 * <code>null</code> if none
	 * 
	 * @return
	 */
	String getDefaultValue();

	/**
	 * Returns the modifier flags for this parameter. The flags can be examined
	 * using class <code>Flags</code>.
	 * 
	 * @return
	 * @since 5.6
	 */
	default int getFlags() {
		return 0;
	}
}
