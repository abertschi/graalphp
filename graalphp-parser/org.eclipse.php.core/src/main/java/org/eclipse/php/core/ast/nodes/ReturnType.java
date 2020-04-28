/*******************************************************************************
 * Copyright (c) 2017 Alex Xu and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Alex Xu - initial API and implementation
 *******************************************************************************/
package org.eclipse.php.core.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

public class ReturnType extends ASTNode {

	private Identifier returnType;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor RETURN_TYPE_PROPERTY = new ChildPropertyDescriptor(ReturnType.class,
			"returnType", Identifier.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(4);
		properyList.add(RETURN_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public ReturnType(Identifier returnType) {
		super(returnType.getStart(), returnType.getEnd(), returnType.ast);
		setName(returnType);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		if (returnType != null) {
			returnType.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (returnType != null) {
			returnType.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (returnType != null) {
			returnType.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ReturnType>\n"); //$NON-NLS-1$
		returnType.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</ReturnType>"); //$NON-NLS-1$
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

	public Identifier getName() {
		return returnType;
	}

	public void setName(Identifier returnType) {
		this.returnType = returnType;
		this.returnType.setParent(this, RETURN_TYPE_PROPERTY);
	}

	@Override
	public int getType() {
		return ASTNode.RETURN_TYPE;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	@Override
	ASTNode clone0(AST target) {
		final Identifier name = ASTNode.copySubtree(target, this.getName());
		final ReturnType result = new ReturnType(name);
		return result;
	}

}
