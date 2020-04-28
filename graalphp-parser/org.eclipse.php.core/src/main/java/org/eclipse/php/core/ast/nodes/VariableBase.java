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
 * This interface is base for all the PHP variables including simple variable,
 * function invocation, list, dispatch, etc.
 * 
 * This class is no longer used directly
 */
public abstract class VariableBase extends Expression {

	public VariableBase(int start, int end, AST ast) {
		super(start, end, ast);
	}

	public VariableBase(AST ast) {
		super(ast);
	}

}
