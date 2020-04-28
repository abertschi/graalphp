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

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.php.core.compiler.PHPFlags;

/**
 * Base class for class member declarations
 */
public abstract class BodyDeclaration extends Statement {

	private int modifier;

	/**
	 * Should be implemented by concrete implementations of body
	 */
	public abstract SimplePropertyDescriptor getModifierProperty();

	public BodyDeclaration(int start, int end, AST ast, int modifier, boolean shouldComplete) {
		super(start, end, ast);

		setModifier(shouldComplete ? completeModifier(modifier) : modifier);
	}

	public BodyDeclaration(int start, int end, AST ast, int modifier) {
		this(start, end, ast, modifier, false);
	}

	public BodyDeclaration(AST ast) {
		super(ast);
	}

	/**
	 * Complets the modifier to public if needed
	 * 
	 * @param mod
	 */
	private static int completeModifier(int mod) {
		if (!PHPFlags.isPrivate(mod) && !PHPFlags.isProtected(mod)) {
			mod |= Modifiers.AccPublic;
		}
		return mod;
	}

	public String getModifierString() {
		return PHPFlags.toString(modifier);
	}

	public int getModifier() {
		return modifier;
	}

	/**
	 * Sets the operator of this assignment expression.
	 * 
	 * @param assignmentOperator
	 *            the assignment operator
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setModifier(int modifier) {
		if (PHPFlags.toString(modifier) == null) {
			throw new IllegalArgumentException("Invalid modifier"); //$NON-NLS-1$
		}
		preValueChange(getModifierProperty());
		this.modifier = modifier;
		postValueChange(getModifierProperty());
	}

	@Override
	int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == getModifierProperty()) {
			if (get) {
				return getModifier();
			} else {
				setModifier(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
	}

}
