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
package org.eclipse.php.internal.core.ast.locator;

import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.Program;
import org.eclipse.php.core.ast.nodes.Statement;
import org.eclipse.php.core.ast.visitor.ApplyAll;

/**
 * Helps finding an AST node for a specific offset
 * 
 * <pre>
 * USAGE: 
 *     Statement statement = NodeLocator.locateStatement(program, offset);
 * 	   ASTNode node = NodeLocator.locateNode(statement, offset).getLast();
 * </pre>
 * 
 * @author Roy, 2007
 */
public class Locator extends ApplyAll {

	/**
	 * @param program
	 * @param offset
	 * @return the statement at offset or null if not found
	 * @throws IllegalArgumentException
	 *             if out of bound
	 */
	public static Statement locateStatement(Program program, int offset) {

		// assert for validty
		if (program == null || offset < 0 || program.getLength() < offset) {
			throw new IllegalArgumentException();
		}

		// visit the nodes
		for (Statement statement : program.statements()) {
			if (inNode(statement, offset)) {
				return statement;
			}
		}

		return null;
	}

	/**
	 * returns the path to the needed node
	 * 
	 * @param program
	 * @param offset
	 * @return the statement at offset or null if not found
	 * @throws IllegalArgumentException
	 *             if out of bound
	 */
	public static synchronized ASTNode locateNode(Program program, int offset) {
		// assert for validty
		if (program == null || !inNode(program, offset)) {
			throw new IllegalArgumentException();
		}

		final Locator pathLocator = new Locator(offset);

		// do the search
		program.accept(pathLocator);

		// return the result node
		return pathLocator.currentNode;
	}

	private int offset;
	private ASTNode currentNode;

	private Locator(int offset) {
		this.offset = offset;
	}

	private static final boolean inNode(final ASTNode node, final int offset) {
		return offset >= node.getStart() && (node.getEnd() > offset);
	}

	/**
	 * @param ASTNode
	 *            the node Checks if the node is in the offset if true then assign
	 *            the node to the children' nodes
	 */
	@Override
	public boolean apply(ASTNode node) {
		assert node != null;

		if (inNode(node, offset)) {
			this.currentNode = node;
			return true;
		}

		return false;
	}

}
