/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
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
 * Represents an arrow function declaration e.g.
 * 
 * <pre>
 * fn & (parameters) => body
 * </pre>
 * 
 * @see https://wiki.php.net/rfc/arrow_functions
 * @since PHP 7.4
 */
public class ArrowFunctionDeclaration extends Expression {

	private boolean isReference;
	private boolean isStatic;
	private final ASTNode.NodeList<FormalParameter> formalParameters = new ASTNode.NodeList<>(
			FORMAL_PARAMETERS_PROPERTY);
	private Expression body;
	private ReturnType returnType;

	/**
	 * The structural property of this node type.
	 */
	public static final SimplePropertyDescriptor IS_REFERENCE_PROPERTY = new SimplePropertyDescriptor(
			ArrowFunctionDeclaration.class, "isReference", Boolean.class, //$NON-NLS-1$
			OPTIONAL);
	public static final SimplePropertyDescriptor IS_STATIC = new SimplePropertyDescriptor(
			ArrowFunctionDeclaration.class, "isStatic", Boolean.class, //$NON-NLS-1$
			OPTIONAL);
	public static final ChildListPropertyDescriptor FORMAL_PARAMETERS_PROPERTY = new ChildListPropertyDescriptor(
			ArrowFunctionDeclaration.class, "formalParameters", //$NON-NLS-1$
			FormalParameter.class, NO_CYCLE_RISK);
	public static final ChildPropertyDescriptor BODY_PROPERTY = new ChildPropertyDescriptor(
			ArrowFunctionDeclaration.class, "body", Expression.class, OPTIONAL, //$NON-NLS-1$
			CYCLE_RISK);
	public static final ChildPropertyDescriptor RETURN_TYPE_PROPERTY = new ChildPropertyDescriptor(
			ArrowFunctionDeclaration.class, "returnType", ReturnType.class, //$NON-NLS-1$
			OPTIONAL, CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(4);
		propertyList.add(IS_REFERENCE_PROPERTY);
		propertyList.add(IS_STATIC);
		propertyList.add(FORMAL_PARAMETERS_PROPERTY);
		propertyList.add(BODY_PROPERTY);
		propertyList.add(RETURN_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public ArrowFunctionDeclaration(int start, int end, AST ast, List<FormalParameter> formalParameters,
			Expression body, final boolean isReference, final boolean isStatic, Identifier returnType) {
		super(start, end, ast);

		if (formalParameters == null) {
			throw new IllegalArgumentException();
		}

		setIsReference(isReference);

		Iterator<FormalParameter> paramIt = formalParameters.iterator();
		while (paramIt.hasNext()) {
			this.formalParameters.add(paramIt.next());
		}
		if (returnType != null) {
			setReturnType(returnType);
		}
		if (body != null) {
			setBody(body);
		}
		setStatic(isStatic);
	}

	public ArrowFunctionDeclaration(AST ast) {
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
		for (ASTNode node : this.formalParameters) {
			node.accept(visitor);
		}
		if (returnType != null) {
			returnType.accept(visitor);
		}
		if (body != null) {
			body.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		for (ASTNode node : this.formalParameters) {
			node.traverseTopDown(visitor);
		}
		if (returnType != null) {
			returnType.accept(visitor);
		}
		if (body != null) {
			body.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : this.formalParameters) {
			node.traverseBottomUp(visitor);
		}
		if (returnType != null) {
			returnType.accept(visitor);
		}
		if (body != null) {
			body.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<ArrowFunctionDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isReference='").append(isReference); //$NON-NLS-1$
		if (isStatic) {
			buffer.append(" isStatic='").append(isStatic); //$NON-NLS-1$
		}
		buffer.append("'>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<FormalParameters>\n"); //$NON-NLS-1$
		for (ASTNode node : this.formalParameters) {
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</FormalParameters>\n"); //$NON-NLS-1$

		if (getReturnType() != null) {
			buffer.append(TAB).append(tab).append("<ReturnType>\n"); //$NON-NLS-1$
			getReturnType().toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
			buffer.append(TAB).append(tab).append("</ReturnType>\n"); //$NON-NLS-1$
		}

		buffer.append(TAB).append(tab).append("<FunctionBody>\n"); //$NON-NLS-1$
		if (body != null) {
			body.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</FunctionBody>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</ArrowFunctionDeclaration>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.ARROW_FUNCTION_DECLARATION;
	}

	/**
	 * Body of this function declaration
	 * 
	 * @return Expression of this function declaration
	 */
	public Expression getBody() {
		return body;
	}

	/**
	 * Sets the body part of this function declaration
	 * <p>
	 * 
	 * @param body
	 *            of this function declaration
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setBody(Expression body) {
		if (body == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.body;
		preReplaceChild(oldChild, body, BODY_PROPERTY);
		this.body = body;
		postReplaceChild(oldChild, body, BODY_PROPERTY);
	}

	/**
	 * List of the formal parameters of this function declaration
	 * 
	 * @return the parameters of this declaration
	 */
	public List<FormalParameter> formalParameters() {
		return this.formalParameters;
	}

	/**
	 * True if this function's return variable will be referenced
	 * 
	 * @return True if this function's return variable will be referenced
	 */
	public boolean isReference() {
		return isReference;
	}

	/**
	 * Sets to true if this function's return variable will be referenced
	 * 
	 * @param value
	 *            for referenced function return value
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public final void setIsReference(boolean value) {
		preValueChange(IS_REFERENCE_PROPERTY);
		this.isReference = value;
		postValueChange(IS_REFERENCE_PROPERTY);
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		preValueChange(IS_STATIC);
		this.isStatic = isStatic;
		postValueChange(IS_STATIC);
	}

	/**
	 * Gets arrow return type (PHP7)
	 * 
	 * @return return type Identifier, can be null
	 */
	public Identifier getReturnType() {
		if (returnType != null) {
			return returnType.getName();
		}
		return null;
	}

	/**
	 * Sets if arrow declaration has defined return type
	 * 
	 * @param returnType
	 *            return type Identifier, can be null
	 */
	public void setReturnType(Identifier returnType) {
		ASTNode oldChild = this.returnType;
		ReturnType newChild = null;
		if (returnType != null) {
			newChild = new ReturnType(returnType);
		}
		preReplaceChild(oldChild, newChild, RETURN_TYPE_PROPERTY);
		this.returnType = newChild;
		postReplaceChild(oldChild, newChild, RETURN_TYPE_PROPERTY);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == IS_REFERENCE_PROPERTY) {
			if (get) {
				return isReference();
			} else {
				setIsReference(value);
				return false;
			}
		} else if (property == IS_STATIC) {
			if (get) {
				return isStatic();
			} else {
				setStatic(value);
				return false;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == BODY_PROPERTY) {
			if (get) {
				return getBody();
			} else {
				setBody((Expression) child);
				return null;
			}
		}
		if (property == RETURN_TYPE_PROPERTY) {
			if (get) {
				return getReturnType();
			} else {
				setReturnType((Identifier) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == FORMAL_PARAMETERS_PROPERTY) {
			return formalParameters();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
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
		final Expression body = ASTNode.copySubtree(target, getBody());
		final List<FormalParameter> formalParams = ASTNode.copySubtrees(target, formalParameters());
		final boolean isRef = isReference();
		final Identifier returnType = ASTNode.copySubtree(target, getReturnType());
		return new ArrowFunctionDeclaration(getStart(), getEnd(), target, formalParams, body, isRef, isStatic(),
				returnType);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}
}
