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

import java.util.List;

import org.eclipse.php.core.ast.visitor.AbstractVisitor;

;

/**
 * Utilities used for Ast nodes
 * 
 * @author Eden
 *
 */
public class ASTNodes {

	public static ASTNode getParent(ASTNode node, Class<?> parentClass) {
		if (node == null) {
			return null;
		}

		do {
			node = node.getParent();
		} while (node != null && !parentClass.isInstance(node));
		return node;
	}

	public static ASTNode getParent(ASTNode node, int nodeType) {
		if (node == null) {
			return null;
		}

		do {
			node = node.getParent();
		} while (node != null && node.getType() != nodeType);
		return node;
	}

	/**
	 * @param node
	 * @return whether the given node is the only statement of a control
	 *         statement
	 */
	public static boolean isControlStatement(ASTNode node) {
		assert node != null;
		int type = node.getType();

		return (type == ASTNode.IF_STATEMENT || type == ASTNode.FOR_STATEMENT || type == ASTNode.FOR_EACH_STATEMENT
				|| type == ASTNode.WHILE_STATEMENT || type == ASTNode.DO_STATEMENT);
	}

	/**
	 * Aggregates the strings for a given node
	 * 
	 * @param node
	 * @return the aggregated strings for a given node
	 */
	public static String getScalars(ASTNode node) {
		final StringBuilder builder = new StringBuilder();
		node.accept(new AbstractVisitor() {

			@Override
			public boolean visit(Scalar scalar) {
				builder.append(scalar.getStringValue());
				return true;
			}

		});

		return builder.toString();
	}

	/**
	 * Tells if a variable is in the form of <code>${var}</code> or
	 * <code>${var[0]}</code> inside a back-quoted string, a double-quoted
	 * string or a heredoc section
	 * 
	 * @param variable
	 * @return true if the variable is in the form of <code>${var}</code> or
	 *         <code>${var[0]}</code> inside a back-quoted string, a
	 *         double-quoted string or a heredoc section, false otherwise
	 */
	public static boolean isQuotedDollaredCurlied(Variable variable) {
		if (variable.isDollared() || variable.getParent() == null) {
			return false;
		}

		ASTNode enclosing = null;

		if (variable.getParent().getType() == ASTNode.ARRAY_ACCESS) {
			enclosing = variable.getParent().getParent();
			if (enclosing != null && enclosing.getType() == ASTNode.REFLECTION_VARIABLE) {
				enclosing = enclosing.getParent();
			} else {
				enclosing = null;
			}
		} else if (variable.getParent().getType() == ASTNode.REFLECTION_VARIABLE) {
			enclosing = variable.getParent().getParent();
		}

		if (enclosing == null) {
			return false;
		}

		return enclosing.getType() == ASTNode.QUOTE || enclosing.getType() == ASTNode.BACK_TICK_EXPRESSION;
	}

	/**
	 * Variant of
	 * {@link ASTNode#getStructuralProperty(StructuralPropertyDescriptor)} that
	 * avoids unchecked casts in the caller.
	 * <p>
	 * To improve type-safety, callers can add the expected element type as
	 * explicit type argument, e.g.:
	 * <p>
	 * {@code ASTNodes.<BodyDeclaration>getChildListProperty(typeDecl, bodyDeclarationsProperty)}
	 *
	 * @param node
	 *            the node
	 * @param propertyDescriptor
	 *            the child list property to get
	 * @return the child list
	 * @exception RuntimeException
	 *                if this node does not have the given property
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ASTNode> List<T> getChildListProperty(ASTNode node,
			ChildListPropertyDescriptor propertyDescriptor) {
		return (List<T>) node.getStructuralProperty(propertyDescriptor);
	}
}
