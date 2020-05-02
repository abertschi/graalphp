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
 * Base class for all expression in PHP
 */
public abstract class Expression extends ASTNode {

	public Expression(int start, int end, AST ast) {
		super(start, end, ast);
	}

	public Expression(AST ast) {
		super(ast);
	}

	/**
	 * A static scalar expression is a "compile-time evaluated scalar" i.e.
	 * expression that can be evaluated before execution time.
	 * 
	 * In PHP static scalars can be scalars, unary operations or array creations
	 * nodes it can be used in declare statement, formal parameter list, static
	 * statement and class constant declaration
	 * 
	 * @return true if the expression is static scalar, false otherwise
	 */
	public boolean isStaticScalar() {
		final int type = getType();
		if (type != ASTNode.SCALAR && type != ASTNode.UNARY_OPERATION && type != ARRAY_CREATION) {
			return false;
		}

		// check the context of the expression
		final ASTNode parent = getParent();
		if (parent == null) {
			return false;
		}
		final int parentType = parent.getType();

		// check - formal parameter and declare statement
		if (parentType == ASTNode.FORMAL_PARAMETER || parentType == ASTNode.DECLARE_STATEMENT
				|| parentType == ASTNode.CONSTANT_DECLARATION) {
			return true;
		}
		// check - static statement
		if (parentType == ASTNode.ASSIGNMENT) {
			final ASTNode grandpa = parent.getParent();
			if (grandpa == null) {
				return false;
			}

			if (grandpa.getType() == ASTNode.STATIC_STATEMENT) {
				return true;
			}
			return false;
		}

		// array elements nodes are static scalars only if their parent array
		// creation
		// are static scalars. like static $a = array( '5' => 4 )
		if (parentType == ASTNode.ARRAY_ELEMENT) {
			final ASTNode grandpa = parent.getParent();
			if (grandpa == null || grandpa.getType() != ASTNode.ARRAY_CREATION) {
				return false;
			}

			Expression grandpaExpression = (Expression) grandpa;
			return grandpaExpression.isStaticScalar();
		}

		// check recursively for static scalars
		if (parentType == ASTNode.SCALAR || parentType == ASTNode.UNARY_OPERATION || parentType == ARRAY_CREATION) {
			final Expression parentExpression = (Expression) parent;
			return parentExpression.isStaticScalar();
		}

		return false;
	}

	/**
	 * @return true if this is expression is the "null" expression
	 */
	public boolean isNullExpression() {
		if (getType() == ASTNode.SCALAR) {
			final Scalar scalar = (Scalar) this;
			if (scalar.getScalarType() == Scalar.TYPE_STRING && scalar.getStringValue().equalsIgnoreCase("null")) { //$NON-NLS-1$
				return true;
			}
		}
		return false;
	}

	/**
	 * Resolves and returns the binding for the type of this expression.
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be resolved
	 */
	public ITypeBinding resolveTypeBinding() {
		return this.ast.getBindingResolver().resolveExpressionType(this);
	}

	/**
	 * Resolves and returns the constant expression value
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be resolved
	 */
	public Object resolveConstantExpressionValue() {
		return this.ast.getBindingResolver().resolveConstantExpressionValue(this);
	}
}
