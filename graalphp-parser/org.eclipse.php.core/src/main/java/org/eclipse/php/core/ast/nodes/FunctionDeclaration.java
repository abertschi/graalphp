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
 * Represents a function declaration
 * 
 * <pre>
 * e.g.
 * 
 * function foo() {}
 * 
 * function &foo() {}
 * 
 * function foo($a, int $b, $c = 5, int $d = 6) {}
 * 
 * function foo(); -abstract function in class declaration
 * 
 * function foo() : MyType {};
 * </pre>
 */
public class FunctionDeclaration extends Statement {

	private boolean isReference;
	private Identifier name;
	private final ASTNode.NodeList<FormalParameter> formalParameters = new ASTNode.NodeList<>(
			FORMAL_PARAMETERS_PROPERTY);
	private Block body;
	private ReturnType returnType;

	/**
	 * The structural property of this node type.
	 */
	public static final SimplePropertyDescriptor IS_REFERENCE_PROPERTY = new SimplePropertyDescriptor(
			FunctionDeclaration.class, "isReference", Boolean.class, OPTIONAL); //$NON-NLS-1$
	public static final ChildPropertyDescriptor NAME_PROPERTY = new ChildPropertyDescriptor(FunctionDeclaration.class,
			"name", Identifier.class, MANDATORY, //$NON-NLS-1$
			NO_CYCLE_RISK);
	public static final ChildListPropertyDescriptor FORMAL_PARAMETERS_PROPERTY = new ChildListPropertyDescriptor(
			FunctionDeclaration.class, "formalParameters", //$NON-NLS-1$
			FormalParameter.class, NO_CYCLE_RISK);
	public static final ChildPropertyDescriptor BODY_PROPERTY = new ChildPropertyDescriptor(FunctionDeclaration.class,
			"body", Block.class, OPTIONAL, //$NON-NLS-1$
			CYCLE_RISK);
	public static final ChildPropertyDescriptor RETURN_TYPE_PROPERTY = new ChildPropertyDescriptor(
			FunctionDeclaration.class, "returnType", ReturnType.class, //$NON-NLS-1$
			OPTIONAL, CYCLE_RISK);

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(4);
		propertyList.add(IS_REFERENCE_PROPERTY);
		propertyList.add(NAME_PROPERTY);
		propertyList.add(FORMAL_PARAMETERS_PROPERTY);
		propertyList.add(BODY_PROPERTY);
		propertyList.add(RETURN_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public FunctionDeclaration(int start, int end, AST ast, Identifier functionName,
			List<FormalParameter> formalParameters, Block body, final boolean isReference) {
		this(start, end, ast, functionName, formalParameters, body, isReference, null);
	}

	public FunctionDeclaration(int start, int end, AST ast, Identifier functionName,
			List<FormalParameter> formalParameters, Block body, final boolean isReference, Identifier returnType) {
		super(start, end, ast);

		if (functionName == null || formalParameters == null) {
			throw new IllegalArgumentException();
		}

		setIsReference(isReference);
		setFunctionName(functionName);
		for (Object obj : formalParameters) {
			FormalParameter formalParameter = (FormalParameter) obj;
			this.formalParameters.add(formalParameter);
		}
		if (returnType != null) {
			setReturnType(returnType);
		}
		if (body != null) {
			setBody(body);
		}
	}

	public FunctionDeclaration(AST ast) {
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
		name.accept(visitor);
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
		name.traverseTopDown(visitor);
		for (ASTNode node : this.formalParameters) {
			node.traverseTopDown(visitor);
		}
		if (returnType != null) {
			returnType.traverseTopDown(visitor);
		}
		if (body != null) {
			body.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		name.traverseBottomUp(visitor);
		for (ASTNode node : this.formalParameters) {
			node.traverseBottomUp(visitor);
		}
		if (returnType != null) {
			returnType.traverseBottomUp(visitor);
		}
		if (body != null) {
			body.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<FunctionDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isReference='").append(isReference).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.append(TAB).append(tab).append("<FunctionName>\n"); //$NON-NLS-1$
		name.toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("</FunctionName>\n"); //$NON-NLS-1$

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
		buffer.append(tab).append("</FunctionDeclaration>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FUNCTION_DECLARATION;
	}

	/**
	 * Body of this function declaration
	 * 
	 * @return Body of this function declaration
	 */
	public Block getBody() {
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
	public void setBody(Block body) {
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
	 * Function name of this declaration
	 * 
	 * @return Function name of this declaration
	 */
	public Identifier getFunctionName() {
		return name;
	}

	/**
	 * Sets the name of this function declaration
	 * 
	 * @param name
	 *            the name if this dunction declaration
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setFunctionName(Identifier name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.name;
		preReplaceChild(oldChild, name, NAME_PROPERTY);
		this.name = name;
		postReplaceChild(oldChild, name, NAME_PROPERTY);
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

	/**
	 * Gets function return type (PHP7)
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
	 * Sets if function declaration has defined return type (PHP7)
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
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == NAME_PROPERTY) {
			if (get) {
				return getFunctionName();
			} else {
				setFunctionName((Identifier) child);
				return null;
			}
		}
		if (property == BODY_PROPERTY) {
			if (get) {
				return getBody();
			} else {
				setBody((Block) child);
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
		final Block body = ASTNode.copySubtree(target, getBody());
		final Identifier function = ASTNode.copySubtree(target, getFunctionName());
		final List<FormalParameter> formalParams = ASTNode.copySubtrees(target, formalParameters());
		final boolean isRef = isReference();
		final Identifier returnType = ASTNode.copySubtree(target, getReturnType());
		return new FunctionDeclaration(getStart(), getEnd(), target, function, formalParams, body, isRef, returnType);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for this function
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public IFunctionBinding resolveFunctionBinding() {
		return this.ast.getBindingResolver().resolveFunction(this);
	}
}
