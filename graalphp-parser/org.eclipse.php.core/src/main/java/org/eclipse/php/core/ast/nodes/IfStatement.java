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
 * Represents if statement
 * 
 * <pre>
 * e.g.
 * 
 * if ($a > $b) { echo "a is bigger than b"; } elseif ($a == $b) { echo "a is
 * equal to b"; } else { echo "a is smaller than b"; },
 * 
 * if ($a): echo "a is bigger than b"; echo "a is NOT bigger than b"; endif;
 * </pre>
 */
public class IfStatement extends Statement {

	private Expression condition;
	private Statement trueStatement;
	private Statement falseStatement;

	/**
	 * The "expression" structural property of this node type.
	 */
	public static final ChildPropertyDescriptor CONDITION_PROPERTY = new ChildPropertyDescriptor(IfStatement.class,
			"condition", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor TRUE_STATEMENT_PROPERTY = new ChildPropertyDescriptor(IfStatement.class,
			"trueStatement", Statement.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor FALSE_STATEMENT_PROPERTY = new ChildPropertyDescriptor(
			IfStatement.class, "falseStatement", Statement.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> list = new ArrayList<>(3);
		list.add(CONDITION_PROPERTY);
		list.add(TRUE_STATEMENT_PROPERTY);
		list.add(FALSE_STATEMENT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(list);
	}

	public IfStatement(int start, int end, AST ast, Expression condition, Statement trueStatement,
			Statement falseStatement) {
		super(start, end, ast);

		if (condition == null || trueStatement == null) {
			throw new IllegalArgumentException();
		}
		setCondition(condition);
		setTrueStatement(trueStatement);
		if (falseStatement != null) {
			setFalseStatement(falseStatement);
		}
	}

	public IfStatement(AST ast) {
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
		trueStatement.accept(visitor);
		if (falseStatement != null) {
			falseStatement.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		condition.traverseTopDown(visitor);
		trueStatement.traverseTopDown(visitor);
		if (falseStatement != null) {
			falseStatement.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		condition.traverseBottomUp(visitor);
		trueStatement.traverseBottomUp(visitor);
		if (falseStatement != null) {
			falseStatement.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<IfStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Condition>\n"); //$NON-NLS-1$
		condition.toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("</Condition>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<TrueStatement>\n"); //$NON-NLS-1$
		trueStatement.toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("</TrueStatement>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<FalseStatement>\n"); //$NON-NLS-1$
		if (falseStatement != null) {
			falseStatement.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</FalseStatement>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</IfStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.IF_STATEMENT;
	}

	/**
	 * Returns the expression of this if statement.
	 * 
	 * @return the expression node
	 */
	public Expression getCondition() {
		return this.condition;
	}

	/**
	 * Sets the condition of this if statement.
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
	 * Returns the "then" part of this if statement.
	 * 
	 * @return the "then" statement node
	 */
	public Statement getTrueStatement() {
		return this.trueStatement;
	}

	/**
	 * Sets the "then" part of this if statement.
	 * <p>
	 * Special note: The Java language does not allow a local variable
	 * declaration to appear as the "then" part of an if statement (they may
	 * only appear within a block). However, the AST will allow a
	 * <code>VariableDeclarationStatement</code> as the thenStatement of a
	 * <code>IfStatement</code>. To get something that will compile, be sure to
	 * embed the <code>VariableDeclarationStatement</code> inside a
	 * <code>Block</code>.
	 * </p>
	 * 
	 * @param statement
	 *            the "then" statement node
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setTrueStatement(Statement statement) {
		if (statement == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.trueStatement;
		preReplaceChild(oldChild, statement, TRUE_STATEMENT_PROPERTY);
		this.trueStatement = statement;
		postReplaceChild(oldChild, statement, TRUE_STATEMENT_PROPERTY);
	}

	/**
	 * Returns the "else" part of this if statement, or <code>null</code> if
	 * this if statement has <b>no</b> "else" part.
	 * <p>
	 * Note that there is a subtle difference between having no else statement
	 * and having an empty statement ("{}") or null statement (";").
	 * </p>
	 * 
	 * @return the "else" statement node, or <code>null</code> if none
	 */
	public Statement getFalseStatement() {
		return this.falseStatement;
	}

	/**
	 * Sets or clears the "else" part of this if statement.
	 * <p>
	 * Note that there is a subtle difference between having no else part (as in
	 * <code>"if(true){}"</code>) and having an empty block (as in
	 * "if(true){}else{}") or null statement (as in "if(true){}else;").
	 * </p>
	 * <p>
	 * Special note: The Java language does not allow a local variable
	 * declaration to appear as the "else" part of an if statement (they may
	 * only appear within a block). However, the AST will allow a
	 * <code>VariableDeclarationStatement</code> as the elseStatement of a
	 * <code>IfStatement</code>. To get something that will compile, be sure to
	 * embed the <code>VariableDeclarationStatement</code> inside a
	 * <code>Block</code>.
	 * </p>
	 * 
	 * @param statement
	 *            the "else" statement node, or <code>null</code> if there is
	 *            none
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setFalseStatement(Statement statement) {
		ASTNode oldChild = this.falseStatement;
		preReplaceChild(oldChild, statement, FALSE_STATEMENT_PROPERTY);
		this.falseStatement = statement;
		postReplaceChild(oldChild, statement, FALSE_STATEMENT_PROPERTY);
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
		Expression condition = ASTNode.copySubtree(target, getCondition());
		Statement trueStatement = ASTNode.copySubtree(target, getTrueStatement());
		Statement falseStatement = ASTNode.copySubtree(target, getFalseStatement());

		final IfStatement result = new IfStatement(this.getStart(), this.getEnd(), target, condition, trueStatement,
				falseStatement);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
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
		if (property == TRUE_STATEMENT_PROPERTY) {
			if (get) {
				return getTrueStatement();
			} else {
				setTrueStatement((Statement) child);
				return null;
			}
		}
		if (property == FALSE_STATEMENT_PROPERTY) {
			if (get) {
				return getFalseStatement();
			} else {
				setFalseStatement((Statement) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
