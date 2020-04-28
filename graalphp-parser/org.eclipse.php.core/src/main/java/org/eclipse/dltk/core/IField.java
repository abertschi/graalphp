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

public interface IField extends IMember {
	String getFullyQualifiedName(String enclosingTypeSeparator);
	
	String getFullyQualifiedName();
	
	public String getTypeQualifiedName(String enclosingTypeSeparator,
			boolean showParameters) throws Exception;

	/**
	 * Return language dependent type name
	 * 
	 * @return
	 * @since 2.0
	 */
	String getType() throws Exception;
}
