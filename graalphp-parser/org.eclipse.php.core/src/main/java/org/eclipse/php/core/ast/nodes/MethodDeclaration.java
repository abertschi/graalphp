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
 * Represents a function declaration in a class Holds the function modifier
 * 
 * @see {@link FunctionDeclaration}
 */
public class MethodDeclaration extends BodyDeclaration {

	private FunctionDeclaration function;
	private Comment comment;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor FUNCTION_PROPERTY = new ChildPropertyDescriptor(MethodDeclaration.class,
			"function", FunctionDeclaration.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor MODIFIER_PROPERTY = new SimplePropertyDescriptor(
			MethodDeclaration.class, "modifier", Integer.class, OPTIONAL); //$NON-NLS-1$
	public static final ChildPropertyDescriptor COMMENT_PROPERTY = new ChildPropertyDescriptor(Comment.class, "comment", //$NON-NLS-1$
			Comment.class, OPTIONAL, NO_CYCLE_RISK);

	@Override
	public final SimplePropertyDescriptor getModifierProperty() {
		return MODIFIER_PROPERTY;
	}

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(2);
		propertyList.add(FUNCTION_PROPERTY);
		propertyList.add(MODIFIER_PROPERTY);
		propertyList.add(COMMENT_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public MethodDeclaration(int start, int end, AST ast, int modifier, FunctionDeclaration function,
			boolean shouldComplete) {
		super(start, end, ast, modifier, shouldComplete);

		if (function == null) {
			throw new IllegalArgumentException();
		}
		setFunction(function);
	}

	public MethodDeclaration(int start, int end, AST ast, int modifier, FunctionDeclaration function) {
		this(start, end, ast, modifier, function, false);
	}

	public MethodDeclaration(AST ast) {
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
		if (comment != null) {
			comment.accept(visitor);
		}
		function.accept(visitor);
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (comment != null) {
			comment.traverseTopDown(visitor);
		}
		function.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		function.traverseBottomUp(visitor);
		if (comment != null) {
			comment.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<MethodDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifierString()).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		function.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</MethodDeclaration>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.METHOD_DECLARATION;
	}

	/**
	 * The function declaration component of this method
	 * 
	 * @return function declaration component of this method
	 */
	public FunctionDeclaration getFunction() {
		return function;
	}

	/**
	 * Sets the function of this declaration
	 * 
	 * @param expression
	 *            the new function declaration
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setFunction(FunctionDeclaration expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.function;
		preReplaceChild(oldChild, expression, FUNCTION_PROPERTY);
		this.function = expression;
		postReplaceChild(oldChild, expression, FUNCTION_PROPERTY);
	}

	public void setComment(Comment expression) {
		if (expression == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.comment;
		preReplaceChild(oldChild, expression, COMMENT_PROPERTY);
		this.comment = expression;
		postReplaceChild(oldChild, expression, COMMENT_PROPERTY);
	}

	public Comment getComment() {
		return comment;
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == FUNCTION_PROPERTY) {
			if (get) {
				return getFunction();
			} else {
				setFunction((FunctionDeclaration) child);
				return null;
			}
		}
		if (property == COMMENT_PROPERTY) {
			if (get) {
				return getComment();
			} else {
				setComment((Comment) child);
				return null;
			}

		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
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
		final FunctionDeclaration function = ASTNode.copySubtree(target, getFunction());
		final int modifier = getModifier();
		final MethodDeclaration result = new MethodDeclaration(getStart(), getEnd(), target, modifier, function, true);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for this method
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be resolved
	 */
	public IMethodBinding resolveMethodBinding() {
		return this.ast.getBindingResolver().resolveMethod(this);
	}
}
