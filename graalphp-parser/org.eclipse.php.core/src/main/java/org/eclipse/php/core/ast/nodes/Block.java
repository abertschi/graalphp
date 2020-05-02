/*******************************************************************************
 * Copyright (c) 2009-2019 IBM Corporation and others.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Represents a block of statements
 * 
 * <pre>
 * e.g.
 * 
 * { statement1; statement2; }, : statement1; statement2; ,
 * </pre>
 */
public class Block extends Statement {

	private final ASTNode.NodeList<Statement> statements = new ASTNode.NodeList<>(STATEMENTS_PROPERTY);
	private boolean isCurly = true;

	/**
	 * The "statements" structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor STATEMENTS_PROPERTY = new ChildListPropertyDescriptor(Block.class,
			"statements", Statement.class, CYCLE_RISK); //$NON-NLS-1$

	public static final SimplePropertyDescriptor IS_CURLY_PROPERTY = new SimplePropertyDescriptor(Block.class,
			"isCurly", Boolean.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(2);
		properyList.add(STATEMENTS_PROPERTY);
		properyList.add(IS_CURLY_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public Block(int start, int end, AST ast, List<Statement> statements, boolean isCurly) {
		super(start, end, ast);

		if (statements == null) {
			throw new IllegalArgumentException();
		}

		setIsCurly(isCurly);
		// set the child nodes' parent
		this.statements.addAll(statements);
	}

	public Block(int start, int end, AST ast, List<Statement> statements) {
		this(start, end, ast, statements, true);
	}

	public Block(AST ast) {
		super(ast);
	}

	@Override
	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		for (ASTNode statement : statements) {
			statement.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		for (ASTNode statement : statements) {
			statement.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode statement : statements) {
			statement.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Block"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isCurly='").append(isCurly).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		for (ASTNode statement : statements) {
			statement.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</Block>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.BLOCK;
	}

	public boolean isCurly() {
		return isCurly;
	}

	/**
	 * is this a curly block or an old (ie - : endblock) style
	 * 
	 * @param isCurly
	 *            the assignment operator
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setIsCurly(boolean isCurly) {
		preValueChange(IS_CURLY_PROPERTY);
		this.isCurly = isCurly;
		postValueChange(IS_CURLY_PROPERTY);
	}

	/**
	 * Retrieves the statement parts of this block
	 * 
	 * @return statement parts of this block
	 */
	public List<Statement> statements() {
		return this.statements;
	}

	/*
	 * Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	ASTNode clone0(AST target) {
		final List<Statement> statements = ASTNode.copySubtrees(target, statements());
		return new Block(this.getStart(), this.getEnd(), target, statements, this.isCurly());
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == STATEMENTS_PROPERTY) {
			return statements();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == IS_CURLY_PROPERTY) {
			if (get) {
				return isCurly();
			} else {
				setIsCurly(value);
				return false;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
	}

}
