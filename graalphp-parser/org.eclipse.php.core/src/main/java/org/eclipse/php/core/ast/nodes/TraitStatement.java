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

public abstract class TraitStatement extends Statement {

	private Expression exp;

	public static final ChildPropertyDescriptor EXP = new ChildPropertyDescriptor(TraitStatement.class, "exp", //$NON-NLS-1$
			Expression.class, MANDATORY, CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(EXP);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public TraitStatement(int start, int end, AST ast, Expression exp) {
		super(start, end, ast);
		setExp(exp);
	}

	public TraitStatement(AST ast) {
		super(ast);
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		if (exp == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.exp;
		preReplaceChild(oldChild, exp, EXP);
		this.exp = exp;
		postReplaceChild(oldChild, exp, EXP);

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
		exp.accept(visitor);

	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		exp.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		exp.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<TraitStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		exp.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</TraitStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FUNCTION_NAME;
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
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == EXP) {
			if (get) {
				return getExp();
			} else {
				setExp((Expression) child);
				return null;
			}
		}

		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
