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
import java.util.Iterator;
import java.util.List;


import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Represents a 'use' statement
 * 
 * e.g.
 * 
 * <pre>
 * use MyNamespace;
 * use MyNamespace as MyAlias;
 * use MyProject\Sub\Level as MyAlias;
 * use \MyProject\Sub\Level as MyAlias;
 * use \MyProject\Sub\Level as MyAlias, MyNamespace as OtherAlias, MyOtherNamespace;
 * use MyProject\Sub\Level\ { MyAlias, MyNamespace as OtherAlias, MyOtherNamespace, };
 * </pre>
 */
public class UseStatement extends Statement {

	// none
	public static final int T_NONE = 0;
	// 'function' keyword
	public static final int T_FUNCTION = 1;
	// 'const' keyword
	public static final int T_CONST = 2;

	private final ASTNode.NodeList<UseStatementPart> parts = new ASTNode.NodeList<>(PARTS_PROPERTY);
	private int statementType;
	private NamespaceName namespace;
	private EmptyExpression emptyPart;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor PARTS_PROPERTY = new ChildListPropertyDescriptor(UseStatement.class,
			"parts", UseStatementPart.class, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor STATEMENT_TYPE_PROPERTY = new SimplePropertyDescriptor(
			UseStatement.class, "statementType", Integer.class, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor NAMESPACE_PROPERTY = new ChildPropertyDescriptor(UseStatement.class,
			"namespace", NamespaceName.class, OPTIONAL, //$NON-NLS-1$
			CYCLE_RISK);
	public static final ChildPropertyDescriptor EMPTY_PART_PROPERTY = new ChildPropertyDescriptor(UseStatement.class,
			"emptyPart", EmptyExpression.class, OPTIONAL, //$NON-NLS-1$
			NO_CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(3);
		properyList.add(PARTS_PROPERTY);
		properyList.add(STATEMENT_TYPE_PROPERTY);
		properyList.add(NAMESPACE_PROPERTY);
		properyList.add(EMPTY_PART_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public UseStatement(AST ast) {
		super(ast);
	}

	public UseStatement(int start, int end, AST ast, List<UseStatementPart> parts) {
		this(start, end, ast, parts, T_NONE);
	}

	public UseStatement(int start, int end, AST ast, List<UseStatementPart> parts, int statementType) {
		this(start, end, ast, null, parts, null, statementType);
	}

	public UseStatement(int start, int end, AST ast, NamespaceName namespace, List<UseStatementPart> parts) {
		this(start, end, ast, namespace, parts, null, T_NONE);
	}

	public UseStatement(int start, int end, AST ast, NamespaceName namespace, List<UseStatementPart> parts,
			EmptyExpression emptyPart) {
		this(start, end, ast, namespace, parts, emptyPart, T_NONE);
	}

	public UseStatement(int start, int end, AST ast, NamespaceName namespace, List<UseStatementPart> parts,
			EmptyExpression emptyPart, int statementType) {
		super(start, end, ast);

		if (parts == null || parts.size() == 0) {
			throw new IllegalArgumentException();
		}
		setNamespace(namespace);
		Iterator<UseStatementPart> it = parts.iterator();
		while (it.hasNext()) {
			this.parts.add(it.next());
		}
		setEmptyPart(emptyPart);
		setStatementType(statementType);
	}

	public UseStatement(int start, int end, AST ast, UseStatementPart[] parts, int statementType) {
		super(start, end, ast);

		if (parts == null || parts.length == 0) {
			throw new IllegalArgumentException();
		}

		for (UseStatementPart part : parts) {
			this.parts.add(part);
		}
		setStatementType(statementType);
	}

	public UseStatement(int start, int end, AST ast, UseStatementPart[] parts) {
		this(start, end, ast, parts, T_NONE);
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
		if (getNamespace() != null) {
			getNamespace().accept(visitor);
		}
		for (ASTNode node : this.parts) {
			node.accept(visitor);
		}
		if (getEmptyPart() != null) {
			getEmptyPart().accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (getNamespace() != null) {
			getNamespace().traverseTopDown(visitor);
		}
		for (ASTNode node : this.parts) {
			node.traverseTopDown(visitor);
		}
		if (getEmptyPart() != null) {
			getEmptyPart().traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (getNamespace() != null) {
			getNamespace().traverseBottomUp(visitor);
		}
		for (ASTNode node : this.parts) {
			node.traverseBottomUp(visitor);
		}
		if (getEmptyPart() != null) {
			getEmptyPart().traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<UseStatement"); //$NON-NLS-1$
		appendInterval(buffer);
		if (getStatementType() != T_NONE) {
			buffer.append(" statementType='").append(getStatementType()) //$NON-NLS-1$
					.append("'"); //$NON-NLS-1$
		}
		buffer.append(">\n"); //$NON-NLS-1$
		if (getNamespace() != null) {
			getNamespace().toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		for (UseStatementPart part : this.parts) {
			part.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		if (getEmptyPart() != null) {
			getEmptyPart().toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</UseStatement>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.USE_STATEMENT;
	}

	/**
	 * The list of single parts of this 'use' statement
	 * 
	 * @return List of this statement parts
	 */
	public List<UseStatementPart> parts() {
		return this.parts;
	}

	/**
	 * <b>WARNING:</b> to get "full" use statement type, use
	 * UseStatementPart#getStatementType().
	 * 
	 * @see UseStatementPart#getStatementType()
	 */
	public int getStatementType() {
		return statementType;
	}

	public void setStatementType(int statementType) {
		preValueChange(STATEMENT_TYPE_PROPERTY);
		this.statementType = statementType;
		postValueChange(STATEMENT_TYPE_PROPERTY);
	}

	public NamespaceName getNamespace() {
		return namespace;
	}

	public void setNamespace(NamespaceName namespace) {
		ASTNode oldChild = this.namespace;
		preReplaceChild(oldChild, namespace, NAMESPACE_PROPERTY);
		this.namespace = namespace;
		postReplaceChild(oldChild, namespace, NAMESPACE_PROPERTY);
	}

	/**
	 * Returns empty node after trailing comma (if existing). <b>Since the new
	 * PHP 7.2 trailing comma feature, the last element of a group use statement
	 * can be an EmptyExpression object (whose length is 0). This element is
	 * stored separately from other regular UseStatementPart parts() elements
	 * and can be retrieved using this method.</b>
	 * 
	 * @return empty node after trailing comma, null otherwise
	 */
	public EmptyExpression getEmptyPart() {
		return emptyPart;
	}

	public void setEmptyPart(EmptyExpression emptyPart) {
		ASTNode oldChild = this.emptyPart;
		preReplaceChild(oldChild, emptyPart, EMPTY_PART_PROPERTY);
		this.emptyPart = emptyPart;
		postReplaceChild(oldChild, emptyPart, EMPTY_PART_PROPERTY);
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == PARTS_PROPERTY) {
			return parts();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == NAMESPACE_PROPERTY) {
			if (get) {
				return getNamespace();
			} else {
				setNamespace((NamespaceName) child);
				return null;
			}
		}
		if (property == EMPTY_PART_PROPERTY) {
			if (get) {
				return getEmptyPart();
			} else {
				setEmptyPart((EmptyExpression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == STATEMENT_TYPE_PROPERTY) {
			if (get) {
				return getStatementType();
			} else {
				setStatementType(value);
				return 0;
			}
		}
		return super.internalGetSetIntProperty(property, get, value);
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
		final List<UseStatementPart> parts = ASTNode.copySubtrees(target, parts());
		final NamespaceName namespace = ASTNode.copySubtree(target, getNamespace());
		return new UseStatement(getStart(), getEnd(), target, namespace, parts, getEmptyPart(), getStatementType());
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
