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

public class FullyQualifiedTraitMethodReference extends Expression {

	private NamespaceName className;
	private Identifier functionName;

	public static final ChildPropertyDescriptor CLASS_NAME = new ChildPropertyDescriptor(
			FullyQualifiedTraitMethodReference.class, "className", NamespaceName.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor FUNCTION_NAME = new ChildPropertyDescriptor(
			FullyQualifiedTraitMethodReference.class, "functionName", Identifier.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(CLASS_NAME);
		propertyList.add(FUNCTION_NAME);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public FullyQualifiedTraitMethodReference(int start, int end, AST ast, NamespaceName className,
			Identifier functionName) {
		super(start, end, ast);
		setClassName(className);
		setFunctionName(functionName);
	}

	public FullyQualifiedTraitMethodReference(AST ast) {
		super(ast);
	}

	public NamespaceName getClassName() {
		return className;
	}

	public void setClassName(NamespaceName className) {
		if (className == null) {
			throw new IllegalArgumentException();
		}

		ASTNode oldChild = this.className;
		preReplaceChild(oldChild, className, CLASS_NAME);
		this.className = className;
		postReplaceChild(oldChild, className, CLASS_NAME);
	}

	public Identifier getFunctionName() {
		return functionName;
	}

	public void setFunctionName(Identifier functionName) {
		ASTNode oldChild = this.functionName;
		preReplaceChild(oldChild, functionName, FUNCTION_NAME);
		this.functionName = functionName;
		postReplaceChild(oldChild, functionName, FUNCTION_NAME);
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
		className.accept(visitor);
		functionName.accept(visitor);

	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		className.traverseTopDown(visitor);
		functionName.traverseTopDown(visitor);
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		className.traverseBottomUp(visitor);
		functionName.traverseBottomUp(visitor);
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<FunctionName"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" functionName='").append(functionName.getName()) //$NON-NLS-1$
				.append("'"); //$NON-NLS-1$
		buffer.append(">\n"); //$NON-NLS-1$
		className.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</FunctionName>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FULLY_QUALIFIED_TRAIT_METHOD_REFERENCE;
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
		NamespaceName className = ASTNode.copySubtree(target, getClassName());
		final FullyQualifiedTraitMethodReference result = new FullyQualifiedTraitMethodReference(this.getStart(),
				this.getEnd(), target, className, functionName);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	// @Override
	// Object internalGetSetObjectProperty(SimplePropertyDescriptor property,
	// boolean get, Object value) {
	// if (property == FUNCTION_NAME) {
	// if (get) {
	// return getFunctionName();
	// } else {
	// setFunctionName((String) value);
	// return null;
	// }
	// }
	// return super.internalGetSetObjectProperty(property, get, value);
	// }

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == CLASS_NAME) {
			if (get) {
				return getClassName();
			} else {
				setClassName((NamespaceName) child);
				return null;
			}
		} else if (property == FUNCTION_NAME) {
			if (get) {
				return getFunctionName();
			} else {
				setFunctionName((Identifier) child);
				return null;
			}
		}

		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

}
