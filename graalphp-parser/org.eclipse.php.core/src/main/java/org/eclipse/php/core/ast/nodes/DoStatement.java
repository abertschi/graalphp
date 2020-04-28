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
 * Represent do while statement.
 * 
 * <pre>
 * e.g.
 * 
 * do { echo $i; } while ($i > 0);
 * </pre>
 */
public class DoStatement extends Statement {

	private Expression condition;
	private Statement body;

	/**
	 * The "expression" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor CONDITION_PROPERTY = new ChildPropertyDescriptor(DoStatement.class,
			"condition", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor BODY_PROPERTY = new ChildPropertyDescriptor(DoStatement.class, "body", //$NON-NLS-1$
			Statement.class, MANDATORY, CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> list = new ArrayList<>(3);
		list.add(CONDITION_PROPERTY);
		list.add(BODY_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(list);
	}

	public DoStatement(int start, int end, AST ast, Expression condition, Statement body) {
		super(start, end, ast);

		if (condition == null || body == null) {
			throw new IllegalArgumentException();
		}
		setCondition(condition);
		setBody(body);
	}

	public DoStatement(AST ast) {
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
		condition.accept(visitor);
		body.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		condition.traverseTopDown(visitor);
		body.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		condition.traverseBottomUp(visitor);
		body.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<DoStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		body.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Condition>\n"); //$NON-NLS-1$
		condition.toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("</Condition>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</DoStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.DO_STATEMENT;
	}

	/**
	 * @return the body component of this do statement
	 */
	public Statement getBody() {
		return this.body;
	}

	/**
	 * Returns the condition expression of this do statement.
	 * 
	 * @return the expression node
	 */
	public Expression getCondition() {
		return this.condition;
	}

	/**
	 * Sets the condition of this do statement.
	 * 
	 * @param expression
	 *            the expression node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setCondition(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.condition;
		preReplaceChild(oldChild, expression, CONDITION_PROPERTY);
		this.condition = expression;
		postReplaceChild(oldChild, expression, CONDITION_PROPERTY);
	}

	/**
	 * Sets the body part of this whil e statement.
	 * <p>
	 * 
	 * @param body
	 *            the "then" statement node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setBody(Statement body) {
		if (body == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.body;
		preReplaceChild(oldChild, body, BODY_PROPERTY);
		this.body = body;
		postReplaceChild(oldChild, body, BODY_PROPERTY);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == CONDITION_PROPERTY) {
			if (get) {
				return getCondition();
			} else {
				setCondition((Expression) child);
				return null;
			}
		}
		if (property == BODY_PROPERTY) {
			if (get) {
				return getBody();
			} else {
				setBody((Statement) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
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
		final Statement body = ASTNode.copySubtree(target, getBody());
		final Expression condition = ASTNode.copySubtree(target, getCondition());
		final DoStatement result = new DoStatement(this.getStart(), this.getEnd(), target, condition, body);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
