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
 * Holds a variable. note that the variable name can be expression,
 * 
 * <pre>
 * e.g.
 * 
 * 		$a
 * </pre>
 *
 * Subclasses: {@link ArrayAccess}, {@link ReflectionVariable},
 * {@link StaticFieldAccess}
 */
public class Variable extends VariableBase {

	private Expression name;
	private boolean isDollared;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor NAME_PROPERTY = new ChildPropertyDescriptor(Variable.class, "name", //$NON-NLS-1$
			Expression.class, MANDATORY, CYCLE_RISK);
	public static final SimplePropertyDescriptor DOLLARED_PROPERTY = new SimplePropertyDescriptor(Variable.class,
			"isDollared", Boolean.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * @return the name PROPERTY
	 */
	public ChildPropertyDescriptor getNameProperty() {
		return Variable.NAME_PROPERTY;
	}

	/**
	 * @return the DOLLARED property
	 */
	public SimplePropertyDescriptor getDollaredProperty() {
		return Variable.DOLLARED_PROPERTY;
	}

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(2);
		propertyList.add(NAME_PROPERTY);
		propertyList.add(DOLLARED_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	protected Variable(int start, int end, AST ast, Expression variableName, boolean isDollared) {
		super(start, end, ast);

		if (variableName == null) {
			throw new IllegalArgumentException();
		}
		setName(variableName);
		setIsDollared(isDollared);
	}

	protected Variable(int start, int end, AST ast, Expression variableName) {
		this(start, end, ast, variableName, false);
	}

	/**
	 * A simple variable (like $a) can be constructed with a string The string
	 * is warped by an identifier
	 * 
	 * @param start
	 * @param end
	 * @param variableName
	 */
	public Variable(int start, int end, AST ast, String variableName) {
		this(start, end, ast, createIdentifier(start, end, ast, variableName), checkIsDollared(variableName));
	}

	public Variable(AST ast) {
		super(ast);
	}

	private static boolean checkIsDollared(String variableName) {
		return variableName.indexOf('$') == 0;
	}

	private static Identifier createIdentifier(int start, int end, AST ast, String idName) {
		if (checkIsDollared(idName)) {
			idName = idName.substring(1);
			// the start position move after the the dollar mark
			start++;
		}
		return new Identifier(start, end, ast, idName);
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
		name.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		name.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		name.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Variable"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isDollared='").append(isDollared).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		name.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</Variable>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.VARIABLE;
	}

	/**
	 * True this variable node is dollared
	 * 
	 * @return True if this variable node is dollared
	 */
	public boolean isDollared() {
		return isDollared;
	}

	/**
	 * Sets the dollared property of this variable (true - the variable is
	 * dollared)
	 * 
	 * @param value
	 *            new value for is this variable
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public final void setIsDollared(boolean value) {
		final SimplePropertyDescriptor dollaredProperty = getDollaredProperty();
		preValueChange(dollaredProperty);
		this.isDollared = value;
		postValueChange(dollaredProperty);
	}

	/**
	 * Returns the name (expression) of this variable
	 * 
	 * @return the expression name node
	 */
	public Expression getName() {
		return name;
	}

	/**
	 * Sets the name of this variable
	 * 
	 * @param expression
	 *            the new variable name
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setName(Expression expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.name;
		final ChildPropertyDescriptor nameProperty = getNameProperty();
		preReplaceChild(oldChild, expression, nameProperty);
		this.name = expression;
		postReplaceChild(oldChild, expression, nameProperty);
	}

	@Override
	ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == getNameProperty()) {
			if (get) {
				return getName();
			} else {
				setName((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == getDollaredProperty()) {
			if (get) {
				return isDollared();
			} else {
				setIsDollared(value);
				return false;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
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
		final boolean dollared = isDollared();
		final Expression name = ASTNode.copySubtree(target, getName());
		final Variable result = new Variable(getStart(), getEnd(), target, name, dollared);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for the field accessed by this
	 * expression.
	 * <p>
	 * Note that bindings are generally unavailable unless requested when the
	 * AST is being built.
	 * </p>
	 *
	 * @return the variable binding, or <code>null</code> if the binding cannot
	 *         be resolved
	 * @since 3.0
	 */
	public IVariableBinding resolveVariableBinding() {
		return this.ast.getBindingResolver().resolveVariable(this);
	}

}
