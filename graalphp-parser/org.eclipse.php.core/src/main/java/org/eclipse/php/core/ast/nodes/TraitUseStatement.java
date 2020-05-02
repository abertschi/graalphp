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
 * Represent a 'use' statement.
 * 
 * <pre>
 * e.g.
 * 
 * use A; use A as B; use \A\B as C;
 * </pre>
 */
public class TraitUseStatement extends Statement {

	private ASTNode.NodeList<NamespaceName> traitList = new ASTNode.NodeList<>(TRAIT);
	private ASTNode.NodeList<TraitStatement> tsList = new ASTNode.NodeList<>(TRAIT_STATEMENT);

	public static final ChildListPropertyDescriptor TRAIT = new ChildListPropertyDescriptor(TraitUseStatement.class,
			"traitList", NamespaceName.class, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildListPropertyDescriptor TRAIT_STATEMENT = new ChildListPropertyDescriptor(
			TraitUseStatement.class, "tsList", TraitStatement.class, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(TRAIT);
		propertyList.add(TRAIT_STATEMENT);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public TraitUseStatement(int start, int end, AST ast, List<NamespaceName> traitList, List<TraitStatement> tsList) {
		super(start, end, ast);

		if (traitList != null) {
			this.traitList.addAll(traitList);
		}
		if (tsList != null) {
			this.tsList.addAll(tsList);
		}
	}

	public TraitUseStatement(AST ast) {
		super(ast);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		for (ASTNode node : traitList) {
			node.accept(visitor);
		}
		for (ASTNode node : tsList) {
			node.accept(visitor);
		}

	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		for (ASTNode node : traitList) {
			node.traverseTopDown(visitor);
		}
		for (ASTNode node : tsList) {
			node.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : traitList) {
			node.traverseBottomUp(visitor);
		}
		for (ASTNode node : tsList) {
			node.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<TraitUseStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		if (traitList != null && !traitList.isEmpty()) {
			buffer.append(TAB).append(tab).append("<TraitNameList>\n"); //$NON-NLS-1$
			for (NamespaceName name : traitList) {
				name.toString(buffer, TAB + TAB + tab);
				buffer.append("\n"); //$NON-NLS-1$
			}
			buffer.append(TAB).append(tab).append("</TraitNameList>\n"); //$NON-NLS-1$
		}
		if (tsList != null && !tsList.isEmpty()) {
			buffer.append(TAB).append(tab).append("<TraitStatementList>\n"); //$NON-NLS-1$
			for (TraitStatement name : tsList) {
				name.toString(buffer, TAB + TAB + tab);
				buffer.append("\n"); //$NON-NLS-1$
			}
			buffer.append(TAB).append(tab).append("</TraitStatementList>\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</TraitUseStatement>"); //$NON-NLS-1$
	}

	@Override
	void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		return matcher.match(this, other);
	}

	@Override
	public int getType() {
		return TRAIT_USE_STATEMENT;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	ASTNode clone0(AST target) {
		final List<NamespaceName> traitList = ASTNode.copySubtrees(target, getTraitList());
		List<TraitStatement> tsList = ASTNode.copySubtrees(target, getTsList());
		final TraitUseStatement result = new TraitUseStatement(this.getStart(), this.getEnd(), target, traitList,
				tsList);

		return result;
	}

	public List<NamespaceName> getTraitList() {
		return traitList;
	}

	public void setTraitList(List<NamespaceName> traitList) {
		this.traitList.clear();
		if (traitList != null) {
			this.traitList.addAll(traitList);
		}
	}

	public List<TraitStatement> getTsList() {
		return tsList;
	}

	public void setTsList(List<TraitStatement> tsList) {
		this.tsList.clear();
		if (tsList != null) {
			this.tsList.addAll(tsList);
		}
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == TRAIT) {
			return getTraitList();
		} else if (property == TRAIT_STATEMENT) {
			return getTsList();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

}
