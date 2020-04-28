/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.core.ast.nodes;

/**
 * Represents the information of the include path
 * 
 * @author Roy, May 2008
 *
 */
@Deprecated
public interface IIncludeBinding extends IBinding {

	/**
	 * Returns the name of the include represented by this binding. For named
	 * includes, this is the fully qualified package name (using "." for
	 * separators). For unnamed packages, this is an empty string.
	 * 
	 * @return the name of the include represented by this binding, or an empty
	 *         string for an unnamed include
	 */
	@Override
	public String getName();

	/**
	 * Returns the list of name component making up the name of the include
	 * represented by this binding. For example, for the include named
	 * "/com/example/tool", this method returns {"com", "example", "tool"}. Returns
	 * the empty list for unnamed packages.
	 * 
	 * @return the name of the package represented by this binding, or the empty
	 *         list for unnamed packages
	 */
	public String[] getNameComponents();
}
