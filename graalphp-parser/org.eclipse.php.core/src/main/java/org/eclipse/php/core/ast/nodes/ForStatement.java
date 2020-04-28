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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Represents a for statement
 * 
 * <pre>
 * e.g.
 * 
 * for (expr1; expr2; expr3) statement;
 * 
 * for (expr1; expr2; expr3): statement ... endfor;
 * </pre>
 */
public class ForStatement extends Statement {

	private final ASTNode.NodeList<Expression> initializers = new ASTNode.NodeList<>(INITIALIZERS_PROPERTY);
	private final ASTNode.NodeList<Expression> conditions = new ASTNode.NodeList<>(EXPRESSION_PROPERTY);
	private final ASTNode.NodeList<Expression> updaters = new ASTNode.NodeList<>(UPDATERS_PROPERTY);
	private Statement body;

	/**
	 * The "initializers" structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor INITIALIZERS_PROPERTY = new ChildListPropertyDescriptor(
			ForStatement.class, "initializers", Expression.class, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildListPropertyDescriptor EXPRESSION_PROPERTY = new ChildListPropertyDescriptor(
			ForStatement.class, "conditions", Expression.class, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildListPropertyDescriptor UPDATERS_PROPERTY = new ChildListPropertyDescriptor(
			ForStatement.class, "updaters", Expression.class, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor BODY_PROPERTY = new ChildPropertyDescriptor(ForStatement.class, "body", //$NON-NLS-1$
			Statement.class, MANDATORY, CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(4);
		properyList.add(INITIALIZERS_PROPERTY);
		properyList.add(EXPRESSION_PROPERTY);
		properyList.add(UPDATERS_PROPERTY);
		properyList.add(BODY_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 * 
	 * @param apiLevel
	 *            the API level; one of the <code>AST.JLS*</code> constants
	 * 
	 * @return a list of property descriptors (element type:
	 *         {@link StructuralPropertyDescriptor})
	 * @since 3.0
	 */
	public static List<StructuralPropertyDescriptor> propertyDescriptors(int apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	public ForStatement(int start, int end, AST ast, List<Expression> initializers, List<Expression> conditions,
			List<Expression> increasements, Statement action) {
		super(start, end, ast);

		if (initializers == null || conditions == null || increasements == null || action == null) {
			throw new IllegalArgumentException();
		}
		this.initializers.addAll(initializers);
		this.conditions.addAll(conditions);
		this.updaters.addAll(increasements);
		setBody(action);
	}

	public ForStatement(AST ast) {
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
		for (ASTNode node : this.initializers) {
			node.accept(visitor);
		}
		for (ASTNode node : this.conditions) {
			node.accept(visitor);
		}
		for (ASTNode node : this.updaters) {
			node.accept(visitor);
		}
		body.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		for (ASTNode node : this.initializers) {
			node.traverseTopDown(visitor);
		}
		for (ASTNode node : this.conditions) {
			node.traverseTopDown(visitor);
		}
		for (ASTNode node : this.updaters) {
			node.traverseTopDown(visitor);
		}
		body.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : this.initializers) {
			node.traverseBottomUp(visitor);
		}
		for (ASTNode node : this.conditions) {
			node.traverseBottomUp(visitor);
		}
		for (ASTNode node : this.updaters) {
			node.traverseBottomUp(visitor);
		}
		body.traverseBottomUp(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ForStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Initializations>\n"); //$NON-NLS-1$
		for (ASTNode node : this.initializers) {
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Initializations>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Conditions>\n"); //$NON-NLS-1$
		for (ASTNode node : this.conditions) {
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Conditions>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Increasements>\n"); //$NON-NLS-1$
		for (ASTNode node : this.updaters) {
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Increasements>\n"); //$NON-NLS-1$
		body.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</ForStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FOR_STATEMENT;
	}

	/**
	 * Returns the live ordered list of initializer expressions in this for
	 * statement.
	 * <p>
	 * The list should consist of either a list of so called statement
	 * expressions (JLS2, 14.8), or a single
	 * <code>VariableDeclarationExpression</code>. Otherwise, the for statement
	 * would have no Java source equivalent.
	 * </p>
	 * 
	 * @return the live list of initializer expressions (element type:
	 *         <code>Expression</code>)
	 */
	public List<Expression> initializers() {
		return this.initializers;
	}

	/**
	 * Returns the condition expression of this for statement, or
	 * <code>null</code> if there is none.
	 * 
	 * @return the condition expression node, or <code>null</code> if there is
	 *         none
	 */
	public List<Expression> conditions() {
		return this.conditions;
	}

	/**
	 * Returns the live ordered list of update expressions in this for
	 * statement.
	 * <p>
	 * The list should consist of so called statement expressions. Otherwise,
	 * the for statement would have no Java source equivalent.
	 * </p>
	 * 
	 * @return the live list of update expressions (element type:
	 *         <code>Expression</code>)
	 */
	public List<Expression> updaters() {
		return this.updaters;
	}

	/**
	 * Returns the body of this for statement.
	 * 
	 * @return the body statement node
	 */
	public Statement getBody() {
		return this.body;
	}

	/**
	 * Sets the body of this for statement.
	 * <p>
	 * Special note: The Java language does not allow a local variable
	 * declaration to appear as the body of a for statement (they may only
	 * appear within a block). However, the AST will allow a
	 * <code>VariableDeclarationStatement</code> as the body of a
	 * <code>ForStatement</code>. To get something that will compile, be sure to
	 * embed the <code>VariableDeclarationStatement</code> inside a
	 * <code>Block</code>.
	 * </p>
	 * 
	 * @param statement
	 *            the body statement node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setBody(Statement statement) {
		if (statement == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.body;
		preReplaceChild(oldChild, statement, BODY_PROPERTY);
		this.body = statement;
		postReplaceChild(oldChild, statement, BODY_PROPERTY);
	}

	/*
	 * Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final List<Expression> inits = ASTNode.copySubtrees(target, initializers());
		final List<Expression> conds = ASTNode.copySubtrees(target, conditions());
		final List<Expression> updtaters = ASTNode.copySubtrees(target, updaters());
		final Statement body = ASTNode.copySubtree(target, getBody());
		return new ForStatement(this.getStart(), this.getEnd(), target, inits, conds, updtaters, body);
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
		if (property == INITIALIZERS_PROPERTY) {
			return initializers();
		}
		if (property == EXPRESSION_PROPERTY) {
			return conditions();
		}
		if (property == UPDATERS_PROPERTY) {
			return updaters();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == BODY_PROPERTY) {
			if (get) {
				return getBody();
			} else {
				setBody((Block) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}
}
