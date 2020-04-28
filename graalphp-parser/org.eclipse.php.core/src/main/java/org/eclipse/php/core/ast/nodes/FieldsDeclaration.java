/*******************************************************************************
 * Copyright (c) 2009-2019 IBM Corporation and others.
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
 * Represents a fields declaration
 * 
 * <pre>
 * e.g.
 * 
 * var $a, $b; public $a = 3; final private static $var; final private ?\Exception $exception;
 * </pre>
 */
public class FieldsDeclaration extends BodyDeclaration {

	private Expression fieldsType;
	private final ASTNode.NodeList<SingleFieldDeclaration> fields = new ASTNode.NodeList<>(FIELDS_PROPERTY);

	/**
	 * The structural property of this node type.
	 */
	public static final SimplePropertyDescriptor MODIFIER_PROPERTY = new SimplePropertyDescriptor(
			FieldsDeclaration.class, "modifier", Integer.class, OPTIONAL); //$NON-NLS-1$
	public static final ChildPropertyDescriptor FIELDS_TYPE_PROPERTY = new ChildPropertyDescriptor(
			FieldsDeclaration.class, "fieldsType", Expression.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$
	public static final ChildListPropertyDescriptor FIELDS_PROPERTY = new ChildListPropertyDescriptor(
			FieldsDeclaration.class, "fields", SingleFieldDeclaration.class, CYCLE_RISK); //$NON-NLS-1$

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
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(1);
		properyList.add(MODIFIER_PROPERTY);
		properyList.add(FIELDS_TYPE_PROPERTY);
		properyList.add(FIELDS_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public FieldsDeclaration(int start, int end, AST ast, int modifier, Expression type,
			List<SingleFieldDeclaration> variablesAndDefaults) {
		super(start, end, ast, modifier);

		if (variablesAndDefaults == null || variablesAndDefaults.size() == 0) {
			throw new IllegalArgumentException();
		}

		if (type != null) {
			setFieldsType(type);
		}

		for (Iterator<SingleFieldDeclaration> iter = variablesAndDefaults.iterator(); iter.hasNext();) {
			final Object next = iter.next();
			if (next instanceof SingleFieldDeclaration) {
				this.fields.add((SingleFieldDeclaration) next);
			} else {
				ASTNode[] element = (ASTNode[]) next;
				SingleFieldDeclaration field = createField(ast, (Variable) element[0], (Expression) element[1]);
				this.fields.add(field);
			}
		}
	}

	/**
	 * @since PHP 7.4
	 */
	public FieldsDeclaration(int start, int end, AST ast, int modifier,
			List<SingleFieldDeclaration> variablesAndDefaults) {
		this(start, end, ast, modifier, null, variablesAndDefaults);
	}

	public FieldsDeclaration(AST ast) {
		super(ast);
	}

	private SingleFieldDeclaration createField(AST ast, Variable name, Expression value) {
		int start = name.getStart();
		int end = value == null ? name.getEnd() : value.getEnd();
		final SingleFieldDeclaration result = new SingleFieldDeclaration(start, end, ast, name, value);
		return result;
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
		if (fieldsType != null) {
			fieldsType.accept(visitor);
		}
		for (ASTNode node : this.fields) {
			node.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		if (fieldsType != null) {
			fieldsType.accept(visitor);
		}
		for (ASTNode node : this.fields) {
			node.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		if (fieldsType != null) {
			fieldsType.accept(visitor);
		}
		for (ASTNode node : this.fields) {
			node.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<FieldsDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" modifier='").append(getModifierString()); //$NON-NLS-1$
		buffer.append("'>\n"); //$NON-NLS-1$
		buffer.append(TAB).append(tab).append("<Type>\n"); //$NON-NLS-1$
		if (fieldsType != null) {
			fieldsType.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append(tab).append("</Type>\n"); //$NON-NLS-1$
		for (SingleFieldDeclaration node : this.fields) {
			buffer.append(tab).append(TAB).append("<VariableName>\n"); //$NON-NLS-1$
			node.getName().toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
			buffer.append(tab).append(TAB).append("</VariableName>\n"); //$NON-NLS-1$
			buffer.append(tab).append(TAB).append("<InitialValue>\n"); //$NON-NLS-1$
			Expression expr = node.getValue();
			if (expr != null) {
				expr.toString(buffer, TAB + TAB + tab);
				buffer.append("\n"); //$NON-NLS-1$
			}
			buffer.append(tab).append(TAB).append("</InitialValue>\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</FieldsDeclaration>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.FIELD_DECLARATION;
	}

	/**
	 * @return the type of this parameter
	 */
	public Expression getFieldsType() {
		return fieldsType;
	}

	/**
	 * Sets the type of these fields
	 * 
	 * @param id
	 *            the type name of this fields declaration.
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public void setFieldsType(Expression id) {
		if (id != null && !(id instanceof Identifier) && !(id instanceof NamespaceName)) {
			throw new IllegalArgumentException();
		}
		// an Assignment may occur inside a Expression - must check cycles
		Expression oldChild = this.fieldsType;
		preReplaceChild(oldChild, id, FIELDS_TYPE_PROPERTY);
		this.fieldsType = id;
		postReplaceChild(oldChild, id, FIELDS_TYPE_PROPERTY);
	}

	/**
	 * The list of single fields that are declared
	 * 
	 * @return List of single fields
	 */
	public List<SingleFieldDeclaration> fields() {
		return this.fields;
	}

	public Expression[] getInitialValues() {
		Expression[] result = new Expression[this.fields.size()];
		int i = 0;
		for (SingleFieldDeclaration field : this.fields) {
			result[i++] = field.getValue();
		}
		return result;
	}

	public Variable[] getVariableNames() {
		Variable[] result = new Variable[this.fields.size()];
		int i = 0;
		for (SingleFieldDeclaration field : this.fields) {
			result[i++] = field.getName();
		}
		return result;
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == FIELDS_PROPERTY) {
			return fields();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == FIELDS_TYPE_PROPERTY) {
			if (get) {
				return getFieldsType();
			} else {
				setFieldsType((Expression) child);
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
		final Expression type = ASTNode.copySubtree(target, this.getFieldsType());
		final List<SingleFieldDeclaration> fields = ASTNode.copySubtrees(target, fields());
		final int modifier = getModifier();
		return new FieldsDeclaration(getStart(), getEnd(), target, modifier, type, fields);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/**
	 * Resolves and returns the binding for this field
	 * 
	 * @return the binding, or <code>null</code> if the binding cannot be
	 *         resolved
	 */
	public final IVariableBinding resolveTypeBinding() {
		return this.ast.getBindingResolver().resolveVariable(this);
	}
}
