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

import org.eclipse.php.core.compiler.PHPFlags;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

public class TraitAlias extends Expression {

	// TODO need modifier start offset,change functionName to scalar
	public TraitAlias(int start, int end, AST ast, Expression traitMethod, int modifier, int modifierOffset,
			Identifier functionName) {
		super(start, end, ast);
		setTraitMethod(traitMethod);
		setModifier(modifier);
		setModifierOffset(modifierOffset);
		setFunctionName(functionName);
	}

	public TraitAlias(AST ast) {
		super(ast);
	}

	private Expression traitMethod;
	private int modifier;
	private int modifierOffset;

	/**
	 * functionName could be null
	 */
	private Identifier functionName;

	public static final ChildPropertyDescriptor TRAIT_METHOD = new ChildPropertyDescriptor(TraitAlias.class,
			"traitMethod", Expression.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor MODIFIER = new SimplePropertyDescriptor(TraitAlias.class, "modifier", //$NON-NLS-1$
			Integer.class, OPTIONAL);
	public static final ChildPropertyDescriptor FUNCTION_NAME = new ChildPropertyDescriptor(TraitAlias.class,
			"functionName", Identifier.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> propertyList = new ArrayList<>(1);
		propertyList.add(TRAIT_METHOD);
		propertyList.add(MODIFIER);
		propertyList.add(FUNCTION_NAME);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(propertyList);
	}

	public Expression getTraitMethod() {
		return traitMethod;
	}

	public void setTraitMethod(Expression traitMethod) {
		if (traitMethod == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.traitMethod;
		preReplaceChild(oldChild, traitMethod, TRAIT_METHOD);
		this.traitMethod = traitMethod;
		postReplaceChild(oldChild, traitMethod, TRAIT_METHOD);
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		preValueChange(MODIFIER);
		this.modifier = modifier;
		postValueChange(MODIFIER);
	}

	public int getModifierOffset() {
		return modifierOffset;
	}

	public void setModifierOffset(int modifierOffset) {
		this.modifierOffset = modifierOffset;
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
		traitMethod.accept(visitor);
		if (functionName != null) {
			functionName.accept(visitor);
		}

	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		traitMethod.traverseTopDown(visitor);
		if (functionName != null) {
			functionName.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		traitMethod.traverseBottomUp(visitor);
		if (functionName != null) {
			functionName.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<TraitAlias"); //$NON-NLS-1$
		appendInterval(buffer);
		if (functionName != null) {
			buffer.append(" functionName='").append(functionName.getName()).append("' "); //$NON-NLS-1$ //$NON-NLS-2$
		}
		buffer.append(" modifier='").append(PHPFlags.toString(modifier)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		traitMethod.toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</TraitAlias>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.TRAIT_ALIAS;
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
		Expression traitMethod = ASTNode.copySubtree(target, getTraitMethod());
		Identifier functionName = ASTNode.copySubtree(target, getFunctionName());
		final TraitAlias result = new TraitAlias(this.getStart(), this.getEnd(), target, traitMethod, modifier,
				modifierOffset, functionName);
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
	int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == MODIFIER) {
			if (get) {
				return getModifier();
			} else {
				int oldValue = getModifier();
				setModifier(value);
				return oldValue;
			}
		}
		return super.internalGetSetIntProperty(property, get, value);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == TRAIT_METHOD) {
			if (get) {
				return getTraitMethod();
			} else {
				setTraitMethod((Expression) child);
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
