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

import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Supporting the visitor pattern to the Abstract Syntax Tree (AST) nodes
 */
public interface Visitable {

	public static final String TAB = "\t"; //$NON-NLS-1$

	/**
	 * Visit only the actual node
	 * 
	 * @param visitor
	 */
	public void accept(Visitor visitor);

	/**
	 * Visit the children of the actual node (without visiting the actual)
	 * 
	 * @param visitor
	 */
	public void childrenAccept(Visitor visitor);

	/**
	 * Visit the actual node then go down to visit the children nodes
	 * 
	 * @param visitor
	 */
	public void traverseTopDown(Visitor visitor);

	/**
	 * Visit the children nodes then go up to the actual node
	 * 
	 * @param visitor
	 */
	public void traverseBottomUp(Visitor visitor);

	/**
	 * Buffers the actual node information
	 * 
	 * @param buffer
	 *            - buffer to write the content to
	 * @param tab
	 *            - indentation
	 */
	public void toString(StringBuilder buffer, String tab);
}
