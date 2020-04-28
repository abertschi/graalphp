/*******************************************************************************
 * Copyright (c) 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Zend Technologies - initial API and implementation
 *******************************************************************************/
package org.eclipse.php.core.ast.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

public class AnonymousClassDeclaration extends Expression {

	private Expression superClass;
	protected ASTNode.NodeList<Identifier> interfaces = new ASTNode.NodeList<>(INTERFACES_PROPERTY);
	private Block body;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor SUPER_CLASS_PROPERTY = new ChildPropertyDescriptor(
			AnonymousClassDeclaration.class, "superClass", Expression.class, //$NON-NLS-1$
			OPTIONAL, NO_CYCLE_RISK);
	public static final ChildListPropertyDescriptor INTERFACES_PROPERTY = new ChildListPropertyDescriptor(
			AnonymousClassDeclaration.class, "interfaces", Identifier.class, //$NON-NLS-1$
			NO_CYCLE_RISK);
	public static final ChildPropertyDescriptor BODY_PROPERTY = new ChildPropertyDescriptor(
			AnonymousClassDeclaration.class, "body", Block.class, MANDATORY, //$NON-NLS-1$
			CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(3);
		propertyList.add(SUPER_CLASS_PROPERTY);
		propertyList.add(INTERFACES_PROPERTY);
		propertyList.add(BODY_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public AnonymousClassDeclaration(AST ast) {
		super(ast);
	}

	public AnonymousClassDeclaration(int start, int end, AST ast, Expression superClass, List<Identifier> interfaces,
			Block body) {
		super(start, end, ast);

		setSuperClass(superClass);
		if (interfaces != null) {
			Iterator<Identifier> iterator = interfaces.iterator();
			while (iterator.hasNext()) {
				this.interfaces.add(iterator.next());
			}
		}
		setBody(body);
	}

	public Block getBody() {
		return body;
	}

	public Expression getSuperClass() {
		return superClass;
	}

	public List<Identifier> getInterfaces() {
		return interfaces;
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		if (superClass != null) {
			superClass.accept(visitor);
		}
		if (interfaces != null) {
			for (Identifier identifier : interfaces) {
				identifier.accept(visitor);
			}
		}
		body.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		if (superClass != null) {
			superClass.traverseTopDown(visitor);
		}
		if (interfaces != null) {
			for (Identifier identifier : interfaces) {
				identifier.traverseTopDown(visitor);
			}
		}
		body.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (superClass != null) {
			superClass.traverseTopDown(visitor);
		}
		if (interfaces != null) {
			for (Identifier identifier : interfaces) {
				identifier.traverseTopDown(visitor);
			}
		}
		body.traverseTopDown(visitor);

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
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<AnonymousClassDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$

		buffer.append(TAB).append(tab).append("<SuperClass>\n"); //$NON-NLS-1$
		if (superClass != null) {
			superClass.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("<SuperClass>\n"); //$NON-NLS-1$

		buffer.append(TAB).append(tab).append("<Interfaces>\n"); //$NON-NLS-1$
		if (interfaces != null) {
			for (Identifier identifier : interfaces) {
				identifier.toString(buffer, TAB + TAB + tab);
				buffer.append("\n"); //$NON-NLS-1$
			}
		}
		buffer.append(TAB).append(tab).append("<Interfaces>\n"); //$NON-NLS-1$

		buffer.append(TAB).append(tab).append("<Body>\n"); //$NON-NLS-1$
		if (body != null) {
			body.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("<Body>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</AnonymousClassDeclaration>"); //$NON-NLS-1$
	}

	public void setBody(Block body) {
		if (body == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.body;
		preReplaceChild(oldChild, body, BODY_PROPERTY);
		this.body = body;
		postReplaceChild(oldChild, body, BODY_PROPERTY);
	}

	public void setSuperClass(Expression superClass) {
		ASTNode oldChild = this.superClass;
		preReplaceChild(oldChild, superClass, SUPER_CLASS_PROPERTY);
		this.superClass = superClass;
		postReplaceChild(oldChild, superClass, SUPER_CLASS_PROPERTY);
	}

	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final Expression superClass = ASTNode.copySubtree(target, getSuperClass());
		final List<Identifier> interfaces = ASTNode.copySubtrees(target, getInterfaces());
		final Block body = ASTNode.copySubtree(target, getBody());
		return new AnonymousClassDeclaration(getStart(), getEnd(), target, superClass, interfaces, body);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == BODY_PROPERTY) {
			if (get) {
				return getBody();
			} else {
				setBody((Block) child);
				return null;
			}
		}
		if (property == SUPER_CLASS_PROPERTY) {
			if (get) {
				return getSuperClass();
			} else {
				setSuperClass((Identifier) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == INTERFACES_PROPERTY) {
			return getInterfaces();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	public int getType() {
		return ASTNode.ANONYMOUS_CLASS_DECLARATION;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

}
