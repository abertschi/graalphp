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
/**
 * 
 */
package org.eclipse.php.core.ast.nodes;

/**
 * An interface that should be implement by any node that defines an unary or
 * binary operation.
 * 
 * @author shalom
 */
public interface IOperationNode {

	/**
	 * Returns the string representation of the operation (e.g. +, -, ~, ++, etc.).
	 * 
	 * @return The string representation of the operation
	 */
	public String getOperationString();

	/**
	 * Translate the given operation id to the string operation that it describes.
	 * 
	 * @param op
	 *            The operation code.
	 * @return A string of the operation.
	 */
	public String getOperationString(int op);
}
