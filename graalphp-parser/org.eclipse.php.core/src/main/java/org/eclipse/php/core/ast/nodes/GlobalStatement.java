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
 * Represents a global statement
 * 
 * <pre>
 * e.g.
 * 
 * global $a global $a, $b global ${foo()->bar()}, global $$a
 * </pre>
 */
public class GlobalStatement extends Statement {

	private final ASTNode.NodeList<Variable> variables = new ASTNode.NodeList<>(VARIABLES_PROPERTY);

	/**
	 * The "variables" structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor VARIABLES_PROPERTY = new ChildListPropertyDescriptor(
			GlobalStatement.class, "variables", Variable.class, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(1);
		properyList.add(VARIABLES_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public GlobalStatement(int start, int end, AST ast, List<Variable> variables) {
		super(start, end, ast);

		if (variables == null) {
			throw new IllegalArgumentException();
		}

		this.variables.addAll(variables);
	}

	public GlobalStatement(AST ast) {
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
		for (ASTNode node : this.variables) {
			node.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			for (ASTNode node : this.variables) {
				node.traverseTopDown(visitor);
			}
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : this.variables) {
			node.traverseBottomUp(visitor);
		}
		visitor.visit(this);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<GlobalStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		for (ASTNode node : this.variables) {
			node.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</GlobalStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.GLOBAL_STATEMENT;
	}

	/**
	 * @return the variables component of the global statement
	 */
	public List<Variable> variables() {
		return this.variables;
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
		final List<Variable> variables = ASTNode.copySubtrees(target, variables());
		return new GlobalStatement(this.getStart(), this.getEnd(), target, variables);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == VARIABLES_PROPERTY) {
			return variables();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}
}
