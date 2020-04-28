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
 * This is the base class for all statements in the PHP AST tree
 */
public abstract class Statement extends ASTNode {

	public Statement(int start, int end, AST ast) {
		super(start, end, ast);
	}

	public Statement(AST ast) {
		super(ast);
	}

}
