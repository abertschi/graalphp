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
 * Represents a function formal parameter
 * 
 * <pre>
 * e.g.
 * 
 * $a, MyClass $a, $a = 3, int $a = 3
 * </pre>
 */
public class FormalParameter extends ASTNode {

	private Expression parameterType;
	private Expression parameterName;
	private Expression defaultValue;
	/**
	 * @deprecated
	 */
	private boolean isMandatory; // php4 "const" keyword
	private boolean isVariadic;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildPropertyDescriptor PARAMETER_TYPE_PROPERTY = new ChildPropertyDescriptor(
			FormalParameter.class, "parameterType", Expression.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor PARAMETER_NAME_PROPERTY = new ChildPropertyDescriptor(
			FormalParameter.class, "expression", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildPropertyDescriptor DEFAULT_VALUE_PROPERTY = new ChildPropertyDescriptor(
			FormalParameter.class, "defaultValue", Expression.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor IS_MANDATORY_PROPERTY = new SimplePropertyDescriptor(
			FormalParameter.class, "isMandatory", Boolean.class, OPTIONAL); //$NON-NLS-1$
	public static final SimplePropertyDescriptor IS_VARIADIC_PROPERTY = new SimplePropertyDescriptor(
			FormalParameter.class, "isVariadic", Boolean.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS_PHP5;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(4);
		properyList.add(PARAMETER_TYPE_PROPERTY);
		properyList.add(PARAMETER_NAME_PROPERTY);
		properyList.add(DEFAULT_VALUE_PROPERTY);
		properyList.add(IS_VARIADIC_PROPERTY);
		PROPERTY_DESCRIPTORS_PHP5 = Collections.unmodifiableList(properyList);
		properyList = new ArrayList<>(4);
		properyList.add(PARAMETER_TYPE_PROPERTY);
		properyList.add(PARAMETER_NAME_PROPERTY);
		properyList.add(DEFAULT_VALUE_PROPERTY);
		properyList.add(IS_MANDATORY_PROPERTY);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS_PHP5;
	}

	public FormalParameter(AST ast) {
		super(ast);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Expression parameterName,
			Expression defaultValue, boolean isMandatory, boolean isVariadic) {
		super(start, end, ast);

		if (parameterName == null) {
			throw new IllegalArgumentException();
		}
		setParameterName(parameterName);
		if (type != null) {
			setParameterType(type);
		}
		if (defaultValue != null) {
			setDefaultValue(defaultValue);
		}
		setIsMandatory(isMandatory);
		setIsVariadic(isVariadic);
	}

	private FormalParameter(int start, int end, AST ast, Expression type, final Expression parameterName,
			Expression defaultValue, boolean isMandatory) {
		this(start, end, ast, type, parameterName, defaultValue, isMandatory, false);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Variable parameterName,
			Expression defaultValue) {
		this(start, end, ast, type, parameterName, defaultValue, false);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Reference parameterName,
			Expression defaultValue) {
		this(start, end, ast, type, parameterName, defaultValue, false);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Variable parameterName) {
		this(start, end, ast, type, parameterName, null, false);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Variable parameterName,
			boolean isMandatory) {
		this(start, end, ast, type, parameterName, null, isMandatory);
	}

	public FormalParameter(int start, int end, AST ast, Expression type, final Reference parameterName) {
		this(start, end, ast, type, parameterName, null, false);
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
		if (parameterType != null) {
			parameterType.accept(visitor);
		}
		parameterName.accept(visitor);
		if (defaultValue != null) {
			defaultValue.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (parameterType != null) {
			parameterType.traverseTopDown(visitor);
		}
		parameterName.traverseTopDown(visitor);
		if (defaultValue != null) {
			defaultValue.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (parameterType != null) {
			parameterType.traverseBottomUp(visitor);
		}
		parameterName.traverseBottomUp(visitor);
		if (defaultValue != null) {
			defaultValue.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<FormalParameter"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" isMandatory='").append(isMandatory); //$NON-NLS-1$
		if (isVariadic()) {
			buffer.append(" isVariadic='").append(isVariadic);//$NON-NLS-1$
		}
		buffer.append("'>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Type>\n"); //$NON-NLS-1$
		if (parameterType != null) {
			parameterType.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Type>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<ParameterName>\n"); //$NON-NLS-1$
		parameterName.toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("</ParameterName>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<DefaultValue>\n"); //$NON-NLS-1$
		if (defaultValue != null) {
			defaultValue.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</DefaultValue>\n"); //$NON-NLS-1$
		buffer.append(tab).append("</FormalParameter>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FORMAL_PARAMETER;
	}

	/**
	 * @return default value of this parameter
	 */
	public Expression getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Sets the default value of this parameter
	 * 
	 * @param value
	 *            the type name of this parameter expression.
	 * @exception IllegalArgumentException
	 *                if:isOptional
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setDefaultValue(Expression value) {
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.defaultValue;
		preReplaceChild(oldChild, value, DEFAULT_VALUE_PROPERTY);
		this.defaultValue = value;
		postReplaceChild(oldChild, value, DEFAULT_VALUE_PROPERTY);
	}

	/**
	 * indicates if this parameter is mandatory when invoking the function
	 * 
	 * @deprecated
	 */
	public boolean isMandatory() {
		return isMandatory;
	}

	/**
	 * Sets the type of this cast expression.
	 * 
	 * @param castingType
	 *            the cast type
	 * @exception IllegalArgumentException
	 *                if the argument is incorrect
	 */
	public void setIsMandatory(boolean isMandatory) {
		preValueChange(IS_MANDATORY_PROPERTY);
		this.isMandatory = isMandatory;
		postValueChange(IS_MANDATORY_PROPERTY);
	}

	/**
	 * indicates if this parameter is variadic when invoking the function
	 */
	public boolean isVariadic() {
		return isVariadic;
	}

	public void setIsVariadic(boolean isVariadic) {
		preValueChange(IS_VARIADIC_PROPERTY);
		this.isVariadic = isVariadic;
		postValueChange(IS_VARIADIC_PROPERTY);
	}

	/**
	 * @return the name of this parameter
	 */
	public Expression getParameterName() {
		return parameterName;
	}

	/**
	 * Sets the name of this parameter
	 * 
	 * @param name
	 *            the type name of this parameter expression.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setParameterName(Expression name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		ASTNode oldChild = this.parameterName;
		preReplaceChild(oldChild, name, PARAMETER_NAME_PROPERTY);
		this.parameterName = name;
		postReplaceChild(oldChild, name, PARAMETER_NAME_PROPERTY);
	}

	/**
	 * @return the type of this parameter
	 */
	public Expression getParameterType() {
		return parameterType;
	}

	/**
	 * Sets the type of this parameter
	 * 
	 * @param id
	 *            the type name of this parameter expression.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setParameterType(Expression id) {
		if (id != null && !(id instanceof Identifier) && !(id instanceof NamespaceName)) {
			throw new IllegalArgumentException();
		}
		// // an Assignment may occur inside a Expression - must check cycles
		Expression oldChild = this.parameterType;
		preReplaceChild(oldChild, id, PARAMETER_TYPE_PROPERTY);
		this.parameterType = id;
		postReplaceChild(oldChild, id, PARAMETER_TYPE_PROPERTY);
	}

	@Override
	final boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		if (property == IS_MANDATORY_PROPERTY) {
			if (get) {
				return isMandatory();
			} else {
				setIsMandatory(value);
				return false;
			}
		} else if (property == IS_VARIADIC_PROPERTY) {
			if (get) {
				return isVariadic();
			} else {
				setIsVariadic(value);
				return false;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetBooleanProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == PARAMETER_NAME_PROPERTY) {
			if (get) {
				return getParameterName();
			} else {
				setParameterName((Expression) child);
				return null;
			}
		}
		if (property == PARAMETER_TYPE_PROPERTY) {
			if (get) {
				return getParameterType();
			} else {
				setParameterType((Expression) child);
				return null;
			}
		}
		if (property == DEFAULT_VALUE_PROPERTY) {
			if (get) {
				return getDefaultValue();
			} else {
				setDefaultValue((Expression) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	/**
	 * @return Identifier name of the formal parameter name
	 */
	public Identifier getParameterNameIdentifier() {
		Expression expression = parameterName;
		switch (parameterName.getType()) {
		case ASTNode.REFERENCE:
			expression = ((Reference) expression).getExpression();
			if (expression.getType() != ASTNode.VARIABLE) {
				throw new IllegalStateException();
			}
		case ASTNode.VARIABLE:
			final Identifier variableName = (Identifier) ((Variable) expression).getName();
			return variableName;
		}
		throw new IllegalStateException();
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
		final Expression name = ASTNode.copySubtree(target, this.getParameterName());
		final Expression type = ASTNode.copySubtree(target, this.getParameterType());
		final Expression value = ASTNode.copySubtree(target, this.getDefaultValue());
		final boolean isMandatory = this.isMandatory();
		final boolean isVariadic = this.isVariadic();
		final FormalParameter result = new FormalParameter(this.getStart(), this.getEnd(), target, type, name, value,
				isMandatory, isVariadic);
		return result;
	}

	/**
	 * Resolves and returns the binding for this formal parameter
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public final ITypeBinding resolveTypeBinding() {
		return this.ast.getBindingResolver().resolveTypeParameter(this);
	}

	/**
	 * Returns true if this FormalParemeter has a valid (non null and with
	 * value) default value.
	 * 
	 * @return True, iff the default value is valid.
	 */
	public boolean hasDefaultValue() {
		return defaultValue != null && defaultValue.getLength() > 0;
	}
}
