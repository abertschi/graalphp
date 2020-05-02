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
 * Represents a case statement. A case statement is part of switch statement
 * 
 * <pre>
 * e.g.
 * 
 * case expr: statement1; break;,
 * 
 * default: statement2;
 * </pre>
 */
public class SwitchCase extends Statement {

	private Expression value;
	private ASTNode.NodeList<Statement> actions = new ASTNode.NodeList<>(ACTIONS_PROPERTY);
	private boolean isDefault;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor VALUE_PROPERTY = new ChildPropertyDescriptor(SwitchCase.class, "value", //$NON-NLS-1$
			Expression.class, OPTIONAL, CYCLE_RISK);
	public static final ChildListPropertyDescriptor ACTIONS_PROPERTY = new ChildListPropertyDescriptor(SwitchCase.class,
			"actions", Statement.class, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor IS_DEFAULT_PROPERTY = new SimplePropertyDescriptor(SwitchCase.class,
			"isDefault", Boolean.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(VALUE_PROPERTY);
		propertyList.add(ACTIONS_PROPERTY);
		propertyList.add(IS_DEFAULT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public SwitchCase(int start, int end, AST ast, Expression value, List<Statement> actions, boolean isDefault) {
		super(start, end, ast);

		if (actions == null) {
			throw new IllegalArgumentException();
		}

		if (value != null) {
			setValue(value);
		}
		this.actions.addAll(actions);
		setIsDefault(isDefault);
	}

	public SwitchCase(AST ast) {
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
		if (value != null) {
			value.accept(visitor);
		}
		for (ASTNode node : this.actions) {
			node.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (value != null) {
			value.traverseTopDown(visitor);
		}
		for (ASTNode node : this.actions) {
			node.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (value != null) {
			value.traverseBottomUp(visitor);
		}
		for (ASTNode node : this.actions) {
			node.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<SwitchCase"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isDefault='").append(isDefault).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(TAB).append(tab).append("<Value>\n"); //$NON-NLS-1$
		if (value != null) {
			value.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Value>\n"); //$NON-NLS-1$
		for (ASTNode node : this.actions) {
			node.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</SwitchCase>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.SWITCH_CASE;
	}

	/**
	 * The actions of this case statement
	 * 
	 * @return List of actions of this case statement
	 */
	public List<Statement> actions() {
		return this.actions;
	}

	/**
	 * True if this is a default case statement
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Set to true if this case statement represents a 'default' case
	 * 
	 * @param isDefault
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setIsDefault(boolean isDefault) {
		preValueChange(IS_DEFAULT_PROPERTY);
		this.isDefault = isDefault;
		postValueChange(IS_DEFAULT_PROPERTY);
	}

	/**
	 * The value (expression) of this case statement
	 * 
	 * @return value (expression) of this case statement
	 */
	public Expression getValue() {
		return value;
	}

	/**
	 * Sets the value of this case statement
	 * 
	 * @param value
	 *            the value of this case statement.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                <li>the SwitchCase is the default case</li>
	 *                </ul>
	 */
	public void setValue(Expression value) {
		if (isDefault || value == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.value;
		preReplaceChild(oldChild, value, VALUE_PROPERTY);
		this.value = value;
		postReplaceChild(oldChild, value, VALUE_PROPERTY);
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
	final boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == IS_DEFAULT_PROPERTY) {
			if (get) {
				return isDefault();
			} else {
				setIsDefault(value);
				return false;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == VALUE_PROPERTY) {
			if (get) {
				return getValue();
			} else {
				setValue((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.php.internal.core.ast.nodes.ASTNode#
	 * internalGetChildListProperty(org.eclipse.php.internal.core.ast.nodes.
	 * ChildListPropertyDescriptor)
	 */
	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == ACTIONS_PROPERTY) {
			return actions();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	ASTNode clone0(AST target) {
		final boolean isDefault = isDefault();
		final List<Statement> actions = ASTNode.copySubtrees(target, actions());
		final Expression value = ASTNode.copySubtree(target, getValue());
		return new SwitchCase(getStart(), getEnd(), target, value, actions, isDefault);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
