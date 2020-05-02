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

import java.util.List;

/**
 * Represents base class for class declaration and interface declaration
 */
public abstract class TypeDeclaration extends Statement {

	private Identifier name;
	protected ASTNode.NodeList<Identifier> interfaces = new ASTNode.NodeList<>(getInterfacesProperty());;
	private Block body;

	/**
	 * The structural property of this node type.
	 */
	protected abstract ChildPropertyDescriptor getNameProperty();

	protected abstract ChildListPropertyDescriptor getInterfacesProperty();

	protected abstract ChildPropertyDescriptor getBodyProperty();

	public TypeDeclaration(int start, int end, AST ast, final Identifier name, final Identifier[] interfaces,
			final Block body) {
		super(start, end, ast);

		if (name == null || body == null) {
			throw new IllegalArgumentException();
		}

		setName(name);
		setBody(body);
		if (interfaces != null) {
			for (Identifier identifier : interfaces) {
				this.interfaces.add(identifier);
			}
		}
	}

	public TypeDeclaration(AST ast) {
		super(ast);
	}

	/**
	 * The body component of this type declaration node
	 * 
	 * @return body component of this type declaration node
	 */
	public Block getBody() {
		return body;
	}

	/**
	 * Sets the name of this parameter
	 * 
	 * @param name
	 *            of this type declaration.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setBody(Block block) {
		if (block == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.body;
		preReplaceChild(oldChild, block, getBodyProperty());
		this.body = block;
		postReplaceChild(oldChild, block, getBodyProperty());
	}

	/**
	 * List of interfaces that this type implements / extends
	 */
	public List<Identifier> interfaces() {
		return this.interfaces;
	}

	/**
	 * The name of the type declaration node
	 * 
	 * @return name of the type declaration node
	 */
	public Identifier getName() {
		return this.name;
	}

	/**
	 * Sets the name of this parameter
	 * 
	 * @param name
	 *            of this type declaration.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setName(Identifier id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.name;
		preReplaceChild(oldChild, id, getNameProperty());
		this.name = id;
		postReplaceChild(oldChild, id, getNameProperty());
	}

	@Override
	ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == getNameProperty()) {
			if (get) {
				return getName();
			} else {
				setName((Identifier) child);
				return null;
			}
		}
		if (property == getBodyProperty()) {
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

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == getInterfacesProperty()) {
			return interfaces();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/**
	 * Resolves and returns the binding for this type
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be resolved
	 */
	public final ITypeBinding resolveTypeBinding() {
		return this.ast.getBindingResolver().resolveType(this);
	}

}
