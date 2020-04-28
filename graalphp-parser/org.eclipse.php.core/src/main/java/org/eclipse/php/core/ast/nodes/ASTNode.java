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

import java.util.*;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

/**
 * Abstract superclass of all Abstract Syntax Tree (AST) node types.
 * <p>
 * An AST node represents a PHP source code construct, such as a name, type,
 * expression, statement, or declaration.
 * <p>
 * ASTs do not contain cycles.
 * <p>
 * 
 * @see Visitable
 * @author Modhe S., Roy G. ,2007
 *         </p>
 */
public abstract class ASTNode implements Visitable {

	/**
	 * ASTNode Types
	 */
	public static final int ARRAY_ACCESS = 0;
	public static final int ARRAY_CREATION = 1;
	public static final int ARRAY_ELEMENT = 2;
	public static final int ASSIGNMENT = 3;
	public static final int AST_ERROR = 4;
	public static final int BACK_TICK_EXPRESSION = 5;
	public static final int BLOCK = 6;
	public static final int BREAK_STATEMENT = 7;
	public static final int CAST_EXPRESSION = 8;
	public static final int CATCH_CLAUSE = 9;
	public static final int STATIC_CONSTANT_ACCESS = 10;
	public static final int CONSTANT_DECLARATION = 11;
	public static final int CLASS_DECLARATION = 12;
	public static final int CLASS_INSTANCE_CREATION = 13;
	public static final int CLASS_NAME = 14;
	public static final int CLONE_EXPRESSION = 15;
	public static final int COMMENT = 16;
	public static final int CONDITIONAL_EXPRESSION = 17;
	public static final int CONTINUE_STATEMENT = 18;
	public static final int DECLARE_STATEMENT = 19;
	public static final int DO_STATEMENT = 20;
	public static final int ECHO_STATEMENT = 21;
	public static final int EMPTY_STATEMENT = 22;
	public static final int EXPRESSION_STATEMENT = 23;
	public static final int FIELD_ACCESS = 24;
	public static final int FIELD_DECLARATION = 25;
	public static final int FOR_EACH_STATEMENT = 26;
	public static final int FORMAL_PARAMETER = 27;
	public static final int FOR_STATEMENT = 28;
	public static final int FUNCTION_DECLARATION = 29;
	public static final int FUNCTION_INVOCATION = 30;
	public static final int FUNCTION_NAME = 31;
	public static final int GLOBAL_STATEMENT = 32;
	public static final int IDENTIFIER = 33;
	public static final int IF_STATEMENT = 34;
	public static final int IGNORE_ERROR = 35;
	public static final int INCLUDE = 36;
	public static final int INFIX_EXPRESSION = 37;
	public static final int IN_LINE_HTML = 38;
	public static final int INSTANCE_OF_EXPRESSION = 39;
	public static final int INTERFACE_DECLARATION = 40;
	public static final int LIST_VARIABLE = 41;
	public static final int METHOD_DECLARATION = 42;
	public static final int METHOD_INVOCATION = 43;
	public static final int POSTFIX_EXPRESSION = 44;
	public static final int PREFIX_EXPRESSION = 45;
	public static final int PROGRAM = 46;
	public static final int QUOTE = 47;
	public static final int REFERENCE = 48;
	public static final int REFLECTION_VARIABLE = 49;
	public static final int RETURN_STATEMENT = 50;
	public static final int SCALAR = 51;
	public static final int STATIC_FIELD_ACCESS = 52;
	public static final int STATIC_METHOD_INVOCATION = 53;
	public static final int STATIC_STATEMENT = 54;
	public static final int SWITCH_CASE = 55;
	public static final int SWITCH_STATEMENT = 56;
	public static final int THROW_STATEMENT = 57;
	public static final int TRY_STATEMENT = 58;
	public static final int UNARY_OPERATION = 59;
	public static final int VARIABLE = 60;
	public static final int WHILE_STATEMENT = 61;
	public static final int PARENTHESIS_EXPRESSION = 62;
	public static final int SINGLE_FIELD_DECLARATION = 63;
	public static final int NAMESPACE = 64;
	public static final int NAMESPACE_NAME = 65;
	public static final int USE_STATEMENT_PART = 66;
	public static final int USE_STATEMENT = 67;
	public static final int GOTO_LABEL = 68;
	public static final int GOTO_STATEMENT = 69;
	public static final int LAMBDA_FUNCTION_DECLARATION = 70;
	public static final int TRAIT_USE_STATEMENT = 71;
	/**
	 * @deprecated
	 */
	public static final int TRAIT_DECLARATION = 72;
	public static final int FULLY_QUALIFIED_TRAIT_METHOD_REFERENCE = 73;
	public static final int TRAIT_ALIAS = 74;
	public static final int YIELD_STATEMENT = 75;
	public static final int FINALLY_CLAUSE = 76;
	public static final int ANONYMOUS_CLASS_DECLARATION = 77;
	public static final int RETURN_TYPE = 78;
	public static final int EMPTY_EXPRESSION = 79;
	public static final int ARROW_FUNCTION_DECLARATION = 80;
	public static final int ARRAY_SPREAD_ELEMENT = 81;

	/**
	 * Internal convenience constant indicating that there is definite risk of
	 * cycles.
	 */
	static final boolean CYCLE_RISK = true;

	/**
	 * Internal convenience constant indicating that there is no risk of cycles.
	 */
	static final boolean NO_CYCLE_RISK = false;

	/**
	 * Internal convenience constant indicating that a structural property is
	 * mandatory.
	 */
	static final boolean MANDATORY = true;

	/**
	 * Internal convenience constant indicating that a structural property is
	 * optional.
	 */
	static final boolean OPTIONAL = false;

	/**
	 * Flag constant (bit mask, value 1) indicating that there is something not
	 * quite right with this AST node.
	 * <p>
	 * The standard parser (<code>ASTParser</code>) sets this flag on a node to
	 * indicate a syntax error detected in the vicinity.
	 * </p>
	 */
	public static final char MALFORMED = 1;

	/**
	 * Flag constant (bit mask, value 2) indicating that this is a node that was
	 * created by the parser (as opposed to one created by another party).
	 * <p>
	 * The standard parser (<code>ASTParser</code>) sets this flag on the nodes
	 * it creates.
	 * </p>
	 */
	public static final char ORIGINAL = 2;

	/**
	 * Flag constant (bit mask, value 4) indicating that this node is
	 * unmodifiable. When a node is marked unmodifiable, the following
	 * operations result in a runtime exception:
	 * <ul>
	 * <li>Change a simple property of this node.</li>
	 * <li>Add or remove a child node from this node.</li>
	 * <li>Parent (or re-parent) this node.</li>
	 * </ul>
	 * <p>
	 * The standard parser (<code>ASTParser</code>) does not set this flag on
	 * the nodes it creates. However, clients may set this flag on a node to
	 * prevent further modification of the its structural properties.
	 * </p>
	 */
	public static final char PROTECT = 4;

	/**
	 * Flag constant (bit mask, value 8) indicating that this node or a part of
	 * this node is recovered from source that contains a syntax error detected
	 * in the vicinity.
	 * <p>
	 * The standard parser (<code>ASTParser</code>) sets this flag on a node to
	 * indicate a recovered node.
	 * </p>
	 */
	public static final char RECOVERED = 8;

	/**
	 * Source range
	 */
	private int start = -1;
	private int length = 0;

	/**
	 * character containing flags; none set by default.
	 * <p>
	 * N.B. This is a private field, but declared as package-visible for more
	 * efficient access from inner classes.
	 * </p>
	 * 
	 * @see #MALFORMED, #PROTECT, #RECOVERED, #ORIGINAL
	 */
	int flags = 0;

	/**
	 * Property of parent in which this node is a child, or <code>null</code> if
	 * this node is a root. Initially <code>null</code>.
	 * 
	 * @see #getLocationInParent
	 */
	private StructuralPropertyDescriptor location = null;

	/**
	 * Owning AST.
	 * <p>
	 * N.B. This is a private field, but declared as package-visible for more
	 * efficient access from inner classes.
	 * </p>
	 */
	final AST ast;

	/**
	 * Parent AST node, or <code>null</code> if this node is a root. Initially
	 * <code>null</code>.
	 */
	private ASTNode parent = null;

	/**
	 * An unmodifiable empty map (used to implement <code>properties()</code>).
	 */
	private static final Map<String, Object> UNMODIFIABLE_EMPTY_MAP = Collections.unmodifiableMap(new HashMap<>(1));

	/**
	 * Primary field used in representing node properties efficiently. If
	 * <code>null</code>, this node has no properties. If a <code>String</code>,
	 * this is the name of this node's sole property, and <code>property2</code>
	 * contains its value. If a <code>HashMap</code>, this is the table of
	 * property name-value mappings; <code>property2</code>, if non-null is its
	 * unmodifiable equivalent. Initially <code>null</code>.
	 * 
	 * @see #property2
	 */
	private Object property1 = null;

	/**
	 * Auxiliary field used in representing node properties efficiently.
	 * 
	 * @see #property1
	 */
	private Object property2 = null;

	/**
	 * Construct an empty ASTNode and attach it with the given AST
	 * 
	 * @param ast
	 */
	public ASTNode(AST ast) {
		if (ast == null) {
			throw new IllegalArgumentException();
		}
		this.ast = ast;
		setFlags(ast.getDefaultNodeFlag());
	}

	/**
	 * Construct a ranged ASTNode and attach it with the given AST
	 * 
	 * @param ast
	 */
	public ASTNode(int start, int end, AST ast) {
		this(ast);

		this.start = start;
		this.length = end - start;
	}

	/**
	 * Accepts the given visitor on a visit of the current node.
	 * 
	 * @param visitor
	 *            the visitor object
	 * @exception IllegalArgumentException
	 *                if the visitor is null
	 */
	@Override
	public final void accept(Visitor visitor) {
		if (visitor == null) {
			throw new IllegalArgumentException();
		}
		// begin with the generic pre-visit
		visitor.preVisit(this);
		// dynamic dispatch to internal method for type-specific visit/endVisit
		accept0(visitor);
		// end with the generic post-visit
		visitor.postVisit(this);
	}

	/**
	 * Accepts the given visitor on a type-specific visit of the current node.
	 * This method must be implemented in all concrete AST node types.
	 * <p>
	 * General template for implementation on each concrete ASTNode class:
	 * 
	 * <pre>
	 * <code>
	 * boolean visitChildren = visitor.visit(this);
	 * if (visitChildren) {
	 *    // visit children in normal left to right reading order
	 *    ... acceptChild();
	 * }
	 * visitor.endVisit(this);
	 * </code>
	 * </pre>
	 * 
	 * Note that the caller (<code>accept</code>) take cares of invoking
	 * <code>visitor.preVisit(this)</code> and
	 * <code>visitor.postVisit(this)</code>.
	 * </p>
	 * 
	 * @param visitor
	 *            the visitor object
	 */
	abstract void accept0(Visitor visitor);

	/**
	 * Returns whether the subtree rooted at the given node matches the given
	 * other object as decided by the given matcher.
	 * <p>
	 * This internal method is implemented in each of the concrete node
	 * subclasses.
	 * </p>
	 * 
	 * @param matcher
	 *            the matcher
	 * @param other
	 *            the other object, or <code>null</code>
	 * @return <code>true</code> if the subtree matches, or <code>false</code>
	 *         if they do not match
	 */
	public abstract boolean subtreeMatch(ASTMatcher matcher, Object other);

	/**
	 * Returns an integer value identifying the type of this concrete AST node.
	 * The values are small positive integers, suitable for use in switch
	 * statements.
	 * <p>
	 * For each concrete node type there is a unique node type constant (name
	 * and value).
	 * </p>
	 * 
	 * @return one of the node type constants
	 */
	public abstract int getType();

	/**
	 * Returns the location of this node within its parent, or <code>null</code>
	 * if this is a root node.
	 * <p>
	 * 
	 * <pre>
	 * ASTNode node = ...;
	 * ASTNode parent = node.getParent();
	 * StructuralPropertyDescriptor location = node.getLocationInParent();
	 * assert (parent != null) == (location != null);
	 * if ((location != null) && location.isChildProperty())
	 *    assert parent.getStructuralProperty(location) == node;
	 * if ((location != null) && location.isChildListProperty())
	 *    assert ((List) parent.getStructuralProperty(location)).contains(node);
	 * </pre>
	 * 
	 * </p>
	 * <p>
	 * Note that the relationship between an AST node and its parent node may
	 * change over the lifetime of a node.
	 * </p>
	 * 
	 * @return the location of this node in its parent, or <code>null</code> if
	 *         this node has no parent
	 */
	public final StructuralPropertyDescriptor getLocationInParent() {
		return this.location;
	}

	/**
	 * Returns the value of the given structural property for this node. The
	 * value returned depends on the kind of property:
	 * <ul>
	 * <li>{@link SimplePropertyDescriptor} - the value of the given simple
	 * property, or <code>null</code> if none; primitive values are "boxed"</li>
	 * <li>{@link ChildPropertyDescriptor} - the child node (type
	 * <code>ASTNode</code>), or <code>null</code> if none</li>
	 * <li>{@link ChildListPropertyDescriptor} - the list (element type:
	 * {@link ASTNode})</li>
	 * </ul>
	 * 
	 * @param property
	 *            the property
	 * @return the value, or <code>null</code> if none
	 * @exception RuntimeException
	 *                if this node does not have the given property
	 */
	public final Object getStructuralProperty(StructuralPropertyDescriptor property) {
		if (property instanceof SimplePropertyDescriptor) {
			SimplePropertyDescriptor p = (SimplePropertyDescriptor) property;
			if (p.getValueType() == Integer.class) {
				int result = internalGetSetIntProperty(p, true, 0);
				return Integer.valueOf(result);
			} else if (p.getValueType() == Boolean.class) {
				boolean result = internalGetSetBooleanProperty(p, true, false);
				return Boolean.valueOf(result);
			} else {
				return internalGetSetObjectProperty(p, true, null);
			}
		}
		if (property instanceof ChildPropertyDescriptor) {
			return internalGetSetChildProperty((ChildPropertyDescriptor) property, true, null);
		}
		if (property instanceof ChildListPropertyDescriptor) {
			return internalGetChildListProperty((ChildListPropertyDescriptor) property);
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Sets the value of the given structural property for this node. The value
	 * passed depends on the kind of property:
	 * <ul>
	 * <li>{@link SimplePropertyDescriptor} - the new value of the given simple
	 * property, or <code>null</code> if none; primitive values are "boxed"</li>
	 * <li>{@link ChildPropertyDescriptor} - the new child node (type
	 * <code>ASTNode</code>), or <code>null</code> if none</li>
	 * <li>{@link ChildListPropertyDescriptor} - not allowed</li>
	 * </ul>
	 * 
	 * @param property
	 *            the property
	 * @param value
	 *            the property value
	 * @exception RuntimeException
	 *                if this node does not have the given property, or if the
	 *                given property cannot be set
	 */
	public final void setStructuralProperty(StructuralPropertyDescriptor property, Object value) {
		if (property instanceof SimplePropertyDescriptor) {
			SimplePropertyDescriptor p = (SimplePropertyDescriptor) property;
			if (p.getValueType() == int.class) {
				int arg = ((Integer) value).intValue();
				internalGetSetIntProperty(p, false, arg);
				return;
			} else if (p.getValueType() == boolean.class) {
				boolean arg = ((Boolean) value).booleanValue();
				internalGetSetBooleanProperty(p, false, arg);
				return;
			} else {
				if (value == null && p.isMandatory()) {
					throw new IllegalArgumentException();
				}
				internalGetSetObjectProperty(p, false, value);
				return;
			}
		}
		if (property instanceof ChildPropertyDescriptor) {
			ChildPropertyDescriptor p = (ChildPropertyDescriptor) property;
			ASTNode child = (ASTNode) value;
			if (child == null && p.isMandatory()) {
				throw new IllegalArgumentException();
			}
			internalGetSetChildProperty(p, false, child);
			return;
		}
		if (property instanceof ChildListPropertyDescriptor) {
			throw new IllegalArgumentException("Cannot set the list of child list property"); //$NON-NLS-1$
		}
	}

	/**
	 * Returns a list of structural property descriptors for nodes of the same
	 * type as this node. Clients must not modify the result.
	 * <p>
	 * Note that property descriptors are a meta-level mechanism for
	 * manipulating ASTNodes in a generic way. They are unrelated to
	 * <code>get/setProperty</code>.
	 * </p>
	 * 
	 * @return a list of property descriptors (element type:
	 *         {@link StructuralPropertyDescriptor})
	 */
	public final List<StructuralPropertyDescriptor> structuralPropertiesForType() {
		return internalStructuralPropertiesForType(this.ast.apiLevel);
	}

	/**
	 * Returns a list of property descriptors for this node type. Clients must
	 * not modify the result. This abstract method must be implemented in each
	 * concrete AST node type.
	 * <p>
	 * N.B. This method is package-private, so that the implementations of this
	 * method in each of the concrete AST node types do not clutter up the API
	 * doc.
	 * </p>
	 * 
	 * @param apiLevel
	 *            the API level; one of the <code>AST.JLS*</code> constants
	 * @return a list of property descriptors (element type:
	 *         {@link StructuralPropertyDescriptor})
	 */
	abstract List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel);

	/**
	 * @return the related AST
	 */
	public AST getAST() {
		return this.ast;
	}

	/**
	 * Returns this node's parent node, or <code>null</code> if this is the root
	 * node.
	 * <p>
	 * Note that the relationship between an AST node and its parent node may
	 * change over the lifetime of a node.
	 * </p>
	 * 
	 * @return the parent of this node, or <code>null</code> if none
	 */
	public ASTNode getParent() {
		return parent;
	}

	/**
	 * Sets or clears this node's parent node and location.
	 * <p>
	 * 
	 * @param parent
	 *            the new parent of this node, or <code>null</code> if none
	 * @see #getParent
	 */
	public void setParent(ASTNode parent, StructuralPropertyDescriptor location) {
		this.parent = parent;
		this.location = location;
	}

	public final int getLength() {
		return length;
	}

	public final int getStart() {
		return start;
	}

	public final int getEnd() {
		return start + length;
	}

	/**
	 * Returns the node class for the corresponding node type.
	 * 
	 * @param nodeType
	 *            AST node type
	 * @return the corresponding <code>ASTNode</code> subclass
	 * @exception IllegalArgumentException
	 *                if <code>nodeType</code> is not a legal AST node type
	 * @see #getNodeType()
	 */
	public static Class<? extends ASTNode> nodeClassForType(int nodeType) {
		switch (nodeType) {
		case ARRAY_ACCESS:
			return ArrayAccess.class;
		case ARRAY_CREATION:
			return ArrayCreation.class;
		case ARRAY_ELEMENT:
			return ArrayElement.class;
		case ARRAY_SPREAD_ELEMENT:
			return ArraySpreadElement.class;
		case ASSIGNMENT:
			return Assignment.class;
		case AST_ERROR:
			return ASTError.class;
		case BACK_TICK_EXPRESSION:
			return BackTickExpression.class;
		case BLOCK:
			return Block.class;
		case BREAK_STATEMENT:
			return BreakStatement.class;
		case CAST_EXPRESSION:
			return CastExpression.class;
		case CATCH_CLAUSE:
			return CatchClause.class;
		case STATIC_CONSTANT_ACCESS:
			return StaticConstantAccess.class;
		case CONSTANT_DECLARATION:
			return ConstantDeclaration.class;
		case CLASS_DECLARATION:
			return ClassDeclaration.class;
		case CLASS_INSTANCE_CREATION:
			return ClassInstanceCreation.class;
		case CLASS_NAME:
			return ClassName.class;
		case CLONE_EXPRESSION:
			return CloneExpression.class;
		case COMMENT:
			return Comment.class;
		case CONDITIONAL_EXPRESSION:
			return ConditionalExpression.class;
		case CONTINUE_STATEMENT:
			return ContinueStatement.class;
		case DECLARE_STATEMENT:
			return DeclareStatement.class;
		case DO_STATEMENT:
			return DoStatement.class;
		case ECHO_STATEMENT:
			return EchoStatement.class;
		case EMPTY_STATEMENT:
			return EmptyStatement.class;
		case EMPTY_EXPRESSION:
			return EmptyExpression.class;
		case EXPRESSION_STATEMENT:
			return ExpressionStatement.class;
		case FIELD_ACCESS:
			return FieldAccess.class;
		case FIELD_DECLARATION:
			return FieldsDeclaration.class;
		case FOR_EACH_STATEMENT:
			return ForEachStatement.class;
		case FORMAL_PARAMETER:
			return FormalParameter.class;
		case FOR_STATEMENT:
			return ForStatement.class;
		case FUNCTION_DECLARATION:
			return FunctionDeclaration.class;
		case FUNCTION_INVOCATION:
			return FunctionInvocation.class;
		case FUNCTION_NAME:
			return FunctionName.class;
		case GLOBAL_STATEMENT:
			return GlobalStatement.class;
		case GOTO_LABEL:
			return GotoLabel.class;
		case GOTO_STATEMENT:
			return GotoStatement.class;
		case IDENTIFIER:
			return Identifier.class;
		case IF_STATEMENT:
			return IfStatement.class;
		case IGNORE_ERROR:
			return IgnoreError.class;
		case INCLUDE:
			return Include.class;
		case INFIX_EXPRESSION:
			return InfixExpression.class;
		case IN_LINE_HTML:
			return InLineHtml.class;
		case INSTANCE_OF_EXPRESSION:
			return InstanceOfExpression.class;
		case INTERFACE_DECLARATION:
			return InterfaceDeclaration.class;
		case LAMBDA_FUNCTION_DECLARATION:
			return LambdaFunctionDeclaration.class;
		case ARROW_FUNCTION_DECLARATION:
			return ArrowFunctionDeclaration.class;
		case LIST_VARIABLE:
			return ListVariable.class;
		case METHOD_DECLARATION:
			return MethodDeclaration.class;
		case METHOD_INVOCATION:
			return MethodInvocation.class;
		case NAMESPACE:
			return NamespaceDeclaration.class;
		case NAMESPACE_NAME:
			return NamespaceName.class;
		case POSTFIX_EXPRESSION:
			return PostfixExpression.class;
		case PREFIX_EXPRESSION:
			return PrefixExpression.class;
		case PROGRAM:
			return Program.class;
		case QUOTE:
			return Quote.class;
		case REFERENCE:
			return Reference.class;
		case REFLECTION_VARIABLE:
			return ReflectionVariable.class;
		case RETURN_STATEMENT:
			return ReturnStatement.class;
		case YIELD_STATEMENT:
			return YieldExpression.class;
		case SCALAR:
			return Scalar.class;
		case STATIC_FIELD_ACCESS:
			return StaticFieldAccess.class;
		case STATIC_METHOD_INVOCATION:
			return StaticMethodInvocation.class;
		case STATIC_STATEMENT:
			return StaticStatement.class;
		case SWITCH_CASE:
			return SwitchCase.class;
		case SWITCH_STATEMENT:
			return SwitchStatement.class;
		case THROW_STATEMENT:
			return ThrowStatement.class;
		case TRY_STATEMENT:
			return TryStatement.class;
		case UNARY_OPERATION:
			return UnaryOperation.class;
		case USE_STATEMENT:
			return UseStatement.class;
		case USE_STATEMENT_PART:
			return UseStatementPart.class;
		case VARIABLE:
			return Variable.class;
		case WHILE_STATEMENT:
			return WhileStatement.class;
		case PARENTHESIS_EXPRESSION:
			return ParenthesisExpression.class;
		case FINALLY_CLAUSE:
			return FinallyClause.class;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		final StringBuilder buffer = new StringBuilder();
		toString(buffer, ""); //$NON-NLS-1$
		return buffer.toString();
	}

	/**
	 * Appends the start, length parameters to the buffer
	 */
	protected void appendInterval(StringBuilder buffer) {
		buffer.append(" start='").append(start).append("' length='") //$NON-NLS-1$ //$NON-NLS-2$
				.append(length).append("'"); //$NON-NLS-1$
	}

	/**
	 * Formats a given string to an XML file
	 * 
	 * @param input
	 * @return String the formatted string
	 */
	protected static String getXmlStringValue(String input) {
		String escapedString = input;
		escapedString = escapedString.replaceAll("&", "&amp;"); //$NON-NLS-1$ //$NON-NLS-2$
		escapedString = escapedString.replaceAll(">", "&gt;"); //$NON-NLS-1$ //$NON-NLS-2$
		escapedString = escapedString.replaceAll("<", "&lt;"); //$NON-NLS-1$ //$NON-NLS-2$
		escapedString = escapedString.replaceAll("'", "&apos;"); //$NON-NLS-1$ //$NON-NLS-2$
		return escapedString;
	}

	/**
	 * @return the Program's root for a given ASTNode
	 */
	public Program getProgramRoot() {
		ASTNode node = this;

		while (node != null) {
			if (node.getType() == ASTNode.PROGRAM) {
				return (Program) node;
			}
			node = node.getParent();
		}

		return null;
	}

	/**
	 * For a given node, returns the outer node that surrounds it
	 * 
	 * @return the enclosing node for this node
	 */
	public ASTNode getEnclosingBodyNode() {
		ASTNode node = this;
		do {
			switch (node.getType()) {
			case ASTNode.FUNCTION_DECLARATION:
				return node;

			case ASTNode.FIELD_DECLARATION:
				return null;

			case ASTNode.PROGRAM:
				return node;

			}
			node = node.getParent();
		} while (node != null);
		return null;
	}

	/**
	 * Returns the root node at or above this node; returns this node if it is a
	 * root.
	 * 
	 * @return the root node at or above this node
	 */
	public final ASTNode getRoot() {
		ASTNode candidate = this;
		while (true) {
			ASTNode p = candidate.getParent();
			if (p == null) {
				// candidate has no parent - that's the guy
				return candidate;
			}
			candidate = p;
		}
	}

	/**
	 * Sets the source range of the original source file where the source
	 * fragment corresponding to this node was found.
	 * <p>
	 * See {@link ASTParser#setKind(int)} for details on precisely where source
	 * ranges are supposed to begin and end.
	 * </p>
	 * 
	 * @param startPosition
	 *            a 0-based character index, or <code>-1</code> if no source
	 *            position information is available for this node
	 * @param length
	 *            a (possibly 0) length, or <code>0</code> if no source position
	 *            information is recorded for this node
	 * @see #getStartPosition()
	 * @see #getLength()
	 * @see ASTParser
	 */
	public final void setSourceRange(int startPosition, int length) {
		if (startPosition >= 0 && length < 0) {
			throw new IllegalArgumentException();
		}
		if (startPosition < 0 && length != 0) {
			throw new IllegalArgumentException();
		}
		this.start = startPosition;
		this.length = length;
	}

	/**
	 * Removes this node from its parent. Has no effect if this node is
	 * unparented. If this node appears as an element of a child list property
	 * of its parent, then this node is removed from the list using
	 * <code>List.remove</code>. If this node appears as the value of a child
	 * property of its parent, then this node is detached from its parent by
	 * passing <code>null</code> to the appropriate setter method; this
	 * operation fails if this node is in a mandatory property.
	 * 
	 */
	public final void delete() {
		StructuralPropertyDescriptor p = getLocationInParent();
		if (p == null) {
			// node is unparented
			return;
		}
		if (p.isChildProperty()) {
			getParent().setStructuralProperty(this.location, null);
			return;
		}
		if (p.isChildListProperty()) {
			List<?> l = (List<?>) getParent().getStructuralProperty(this.location);
			l.remove(this);
		}
	}

	/**
	 * Prelude portion of the "3 step program" for replacing the old child of
	 * this node with another node. Here is the code pattern found in all AST
	 * node subclasses:
	 * 
	 * <pre>
	 * ASTNode oldChild = this.foo;
	 * preReplaceChild(oldChild, newFoo, FOO_PROPERTY);
	 * this.foo = newFoo;
	 * postReplaceChild(oldChild, newFoo, FOO_PROPERTY);
	 * </pre>
	 * 
	 * The first part (preReplaceChild) does all the precondition checks,
	 * reports pre-delete events, and changes parent links. The old child is
	 * delinked from its parent (making it a root node), and the new child node
	 * is linked to its parent. The new child node must be a root node in the
	 * same AST as its new parent, and must not be an ancestor of this node. All
	 * three nodes must be modifiable (not PROTECTED). The replace operation
	 * must fail atomically; so it is crucial that all precondition checks be
	 * done before any linking and delinking happens. The final part
	 * (postReplaceChild )reports post-add events.
	 * <p>
	 * This method calls <code>ast.modifying()</code> for the nodes affected.
	 * </p>
	 * 
	 * @param oldChild
	 *            the old child of this node, or <code>null</code> if there was
	 *            no old child to replace
	 * @param newChild
	 *            the new child of this node, or <code>null</code> if there is
	 *            no replacement child
	 * @param property
	 *            the property descriptor of this node describing the
	 *            relationship between node and child
	 * @exception RuntimeException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                <li>any of the nodes involved are unmodifiable</li>
	 *                </ul>
	 */
	final void preReplaceChild(ASTNode oldChild, ASTNode newChild, ChildPropertyDescriptor property) {
		if ((this.flags & PROTECT) != 0) {
			// this node is protected => cannot gain or lose children
			throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
		}
		if (newChild != null) {
			checkNewChild(this, newChild, property.cycleRisk, null);
		}
		// delink old child from parent
		if (oldChild != null) {
			if ((oldChild.flags & PROTECT) != 0) {
				// old child node is protected => cannot be unparented
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}
			if (newChild != null) {
				this.ast.preReplaceChildEvent(this, oldChild, newChild, property);
			} else {
				this.ast.preRemoveChildEvent(this, oldChild, property);
			}
			oldChild.setParent(null, null);
		} else {
			if (newChild != null) {
				this.ast.preAddChildEvent(this, newChild, property);
			}
		}
		// link new child to parent
		if (newChild != null) {
			newChild.setParent(this, property);
			// cannot notify postAddChildEvent until parent is linked to child
			// too
		}
	}

	/**
	 * Postlude portion of the "3 step program" for replacing the old child of
	 * this node with another node. See
	 * {@link #preReplaceChild(ASTNode, ASTNode, ChildPropertyDescriptor)} for
	 * details.
	 */
	final void postReplaceChild(ASTNode oldChild, ASTNode newChild, ChildPropertyDescriptor property) {
		// link new child to parent
		if (newChild != null) {
			if (oldChild != null) {
				this.ast.postReplaceChildEvent(this, oldChild, newChild, property);
			} else {
				this.ast.postAddChildEvent(this, newChild, property);
			}
		} else {
			this.ast.postRemoveChildEvent(this, oldChild, property);
		}
	}

	/**
	 * Prelude portion of the "3 step program" for changing the value of a
	 * simple property of this node. Here is the code pattern found in all AST
	 * node subclasses:
	 * 
	 * <pre>
	 * preValueChange(FOO_PROPERTY);
	 * this.foo = newFoo;
	 * postValueChange(FOO_PROPERTY);
	 * </pre>
	 * 
	 * The first part (preValueChange) does the precondition check to make sure
	 * the node is modifiable (not PROTECTED). The change operation must fail
	 * atomically; so it is crucial that the precondition checks are done before
	 * the field is hammered. The final part (postValueChange)reports
	 * post-change events.
	 * <p>
	 * This method calls <code>ast.modifying()</code> for the node affected.
	 * </p>
	 * 
	 * @param property
	 *            the property descriptor of this node
	 * @exception RuntimeException
	 *                if:
	 *                <ul>
	 *                <li>this node is unmodifiable</li>
	 *                </ul>
	 */
	final void preValueChange(SimplePropertyDescriptor property) {
		if ((this.flags & PROTECT) != 0) {
			// this node is protected => cannot change valure of properties
			throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
		}
		this.ast.preValueChangeEvent(this, property);
		this.ast.modifying();
	}

	/**
	 * Postlude portion of the "3 step program" for replacing the old child of
	 * this node with another node. See
	 * {@link #preValueChange(SimplePropertyDescriptor)} for details.
	 */
	final void postValueChange(SimplePropertyDescriptor property) {
		this.ast.postValueChangeEvent(this, property);
	}

	/**
	 * Ensures that this node is modifiable (that is, not marked PROTECTED). If
	 * successful, calls ast.modifying().
	 * 
	 * @exception RuntimeException
	 *                is not modifiable
	 */
	final void checkModifiable() {
		if ((this.flags & PROTECT) != 0) {
			throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
		}
		this.ast.modifying();
	}

	/**
	 * Begin lazy initialization of this node. Here is the code pattern found in
	 * all AST node subclasses:
	 * 
	 * <pre>
	 * if (this.foo == null) {
	 *    // lazy init must be thread-safe for readers
	 *    synchronized (this) {
	 *       if (this.foo == null) {
	 *          preLazyInit();
	 *          this.foo = ...; // code to create new node
	 *          postLazyInit(this.foo, FOO_PROPERTY);
	 *       }
	 *    }
	 * }
	 * </pre>
	 * 
	 * @since 3.0
	 */
	final void preLazyInit() {
		// IMPORTANT: this method is called by readers
		// ASTNode.this is locked at this point
		this.ast.disableEvents();
		// will turn events back on in postLasyInit
	}

	/**
	 * End lazy initialization of this node.
	 * 
	 * @param newChild
	 *            the new child of this node, or <code>null</code> if there is
	 *            no replacement child
	 * @param property
	 *            the property descriptor of this node describing the
	 *            relationship between node and child
	 * @since 3.0
	 */
	final void postLazyInit(ASTNode newChild, ChildPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers
		// ASTNode.this is locked at this point
		// newChild is brand new (so no chance of concurrent access)
		newChild.setParent(this, property);
		// turn events back on (they were turned off in corresponding
		// preLazyInit)
		this.ast.reenableEvents();
	}

	/**
	 * Returns the named property of this node, or <code>null</code> if none.
	 * 
	 * @param propertyName
	 *            the property name
	 * @return the property value, or <code>null</code> if none
	 * @see #setProperty(String,Object)
	 */
	public final Object getProperty(String propertyName) {
		if (propertyName == null) {
			throw new IllegalArgumentException();
		}
		if (this.property1 == null) {
			// node has no properties at all
			return null;
		}
		if (this.property1 instanceof String) {
			// node has only a single property
			if (propertyName.equals(this.property1)) {
				return this.property2;
			} else {
				return null;
			}
		}
		// otherwise node has table of properties
		Map<?, ?> m = (Map<?, ?>) this.property1;
		return m.get(propertyName);
	}

	/**
	 * Sets the named property of this node to the given value, or to
	 * <code>null</code> to clear it.
	 * <p>
	 * Clients should employ property names that are sufficiently unique to
	 * avoid inadvertent conflicts with other clients that might also be setting
	 * properties on the same node.
	 * </p>
	 * <p>
	 * Note that modifying a property is not considered a modification to the
	 * AST itself. This is to allow clients to decorate existing nodes with
	 * their own properties without jeopardizing certain things (like the
	 * validity of bindings), which rely on the underlying tree remaining
	 * static.
	 * </p>
	 * 
	 * @param propertyName
	 *            the property name
	 * @param data
	 *            the new property value, or <code>null</code> if none
	 * @see #getProperty(String)
	 */
	public final void setProperty(String propertyName, Object data) {
		if (propertyName == null) {
			throw new IllegalArgumentException();
		}
		// N.B. DO NOT CALL ast.modifying();

		if (this.property1 == null) {
			// node has no properties at all
			if (data == null) {
				// we already know this
				return;
			}
			// node gets its first property
			this.property1 = propertyName;
			this.property2 = data;
			return;
		}

		if (this.property1 instanceof String) {
			// node has only a single property
			if (propertyName.equals(this.property1)) {
				// we're in luck
				this.property2 = data;
				if (data == null) {
					// just deleted last property
					this.property1 = null;
					this.property2 = null;
				}
				return;
			}
			if (data == null) {
				// we already know this
				return;
			}
			// node already has one property - getting its second
			// convert to more flexible representation
			HashMap<String, Object> m = new HashMap<>(2);
			m.put((String) this.property1, this.property2);
			m.put(propertyName, data);
			this.property1 = m;
			this.property2 = null;
			return;
		}

		// node has two or more properties
		HashMap<String, Object> m = (HashMap<String, Object>) this.property1;
		if (data == null) {
			m.remove(propertyName);
			// check for just one property left
			if (m.size() == 1) {
				// convert to more efficient representation
				Map.Entry<String, Object>[] entries = m.entrySet().toArray(new Map.Entry[1]);
				this.property1 = entries[0].getKey();
				this.property2 = entries[0].getValue();
			}
			return;
		} else {
			m.put(propertyName, data);
			// still has two or more properties
			return;
		}
	}

	/**
	 * Returns an unmodifiable table of the properties of this node with non-
	 * <code>null</code> values.
	 * 
	 * @return the table of property values keyed by property name (key type:
	 *         <code>String</code>; value type: <code>Object</code>)
	 */
	public final Map<String, Object> properties() {
		if (this.property1 == null) {
			// node has no properties at all
			return UNMODIFIABLE_EMPTY_MAP;
		}
		if (this.property1 instanceof String) {
			// node has a single property
			return Collections.singletonMap((String) this.property1, this.property2);
		}

		// node has two or more properties
		if (this.property2 == null) {
			this.property2 = Collections.unmodifiableMap((Map<String, Object>) this.property1);
		}
		// property2 is unmodifiable wrapper for map in property1
		return (Map<String, Object>) this.property2;
	}

	/**
	 * Returns the flags associated with this node.
	 * <p>
	 * No flags are associated with newly created nodes.
	 * </p>
	 * <p>
	 * The flags are the bitwise-or of individual flags. The following flags are
	 * currently defined:
	 * <ul>
	 * <li>{@link #MALFORMED} - indicates node is syntactically malformed</li>
	 * <li>{@link #ORIGINAL} - indicates original node created by ASTParser</li>
	 * <li>{@link #PROTECT} - indicates node is protected from further
	 * modification</li>
	 * <li>{@link #RECOVERED} - indicates node or a part of this node is
	 * recovered from source that contains a syntax error</li>
	 * </ul>
	 * Other bit positions are reserved for future use.
	 * </p>
	 * 
	 * @return the bitwise-or of individual flags
	 * @see #setFlags(int)
	 */
	public final int getFlags() {
		return this.flags & 0xFFFF;
	}

	/**
	 * Sets the flags associated with this node to the given value.
	 * <p>
	 * The flags are the bitwise-or of individual flags. The following flags are
	 * currently defined:
	 * <ul>
	 * <li>{@link #MALFORMED} - indicates node is syntactically malformed</li>
	 * <li>{@link #ORIGINAL} - indicates original node created by ASTParser</li>
	 * <li>{@link #PROTECT} - indicates node is protected from further
	 * modification</li>
	 * <li>{@link #RECOVERED} - indicates node or a part of this node is
	 * recovered from source that contains a syntax error</li>
	 * </ul>
	 * Other bit positions are reserved for future use.
	 * </p>
	 * <p>
	 * Note that the flags are <em>not</em> considered a structural property of
	 * the node, and can be changed even if the node is marked as protected.
	 * </p>
	 * 
	 * @param flags
	 *            the bitwise-or of individual flags
	 * @see #getFlags()
	 */
	public final void setFlags(int flags) {
		this.ast.modifying();
		this.flags |= flags;
	}

	/**
	 * Returns a deep copy of the subtree of AST nodes rooted at the given node.
	 * The resulting nodes are owned by the given AST, which may be different
	 * from the ASTs of the given node. Even if the given node has a parent, the
	 * result node will be unparented.
	 * <p>
	 * Source range information on the original nodes is automatically copied to
	 * the new nodes. Client properties (<code>properties</code>) are not
	 * carried over.
	 * </p>
	 * <p>
	 * The node's <code>AST</code> and the target <code>AST</code> must support
	 * the same API level.
	 * </p>
	 * 
	 * @param target
	 *            the AST that is to own the nodes in the result
	 * @param node
	 *            the node to copy, or <code>null</code> if none
	 * @return the copied node, or <code>null</code> if <code>node</code> is
	 *         <code>null</code>
	 */
	public static <T extends ASTNode> T copySubtree(AST target, T node) {
		if (node == null) {
			return null;
		}
		if (target == null) {
			throw new IllegalArgumentException();
		}
		if (target.apiLevel() != node.getAST().apiLevel()) {
			throw new UnsupportedOperationException();
		}
		@SuppressWarnings("unchecked")
		T newNode = (T) node.clone(target);
		return newNode;
	}

	/**
	 * Returns a deep copy of the subtrees of AST nodes rooted at the given list
	 * of nodes. The resulting nodes are owned by the given AST, which may be
	 * different from the ASTs of the nodes in the list. Even if the nodes in
	 * the list have parents, the nodes in the result will be unparented.
	 * <p>
	 * Source range information on the original nodes is automatically copied to
	 * the new nodes. Client properties (<code>properties</code>) are not
	 * carried over.
	 * </p>
	 * 
	 * @param target
	 *            the AST that is to own the nodes in the result
	 * @param nodes
	 *            the list of nodes to copy (element type: <code>ASTNode</code>)
	 * @return the list of copied subtrees (element type: <code>ASTNode</code>)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ASTNode> List<T> copySubtrees(AST target, List<? extends T> nodes) {
		List<T> result = new ArrayList<>(nodes.size());
		for (Iterator<? extends ASTNode> it = nodes.iterator(); it.hasNext();) {
			ASTNode oldNode = it.next();
			ASTNode newNode = oldNode.clone(target);
			result.add((T) newNode);
		}
		return result;
	}

	/**
	 * Returns a deep copy of the subtree of AST nodes rooted at this node. The
	 * resulting nodes are owned by the given AST, which may be different from
	 * the AST of this node. Even if this node has a parent, the result node
	 * will be unparented.
	 * <p>
	 * This method reports pre- and post-clone events, and dispatches to
	 * <code>clone0(AST)</code> which is reimplemented in node subclasses.
	 * </p>
	 * 
	 * @param target
	 *            the AST that is to own the nodes in the result
	 * @return the root node of the copies subtree
	 */
	final ASTNode clone(AST target) {
		this.ast.preCloneNodeEvent(this);
		ASTNode c = this.clone0(target);
		this.ast.postCloneNodeEvent(this, c);
		return c;
	}

	/**
	 * Returns a deep copy of the subtree of AST nodes rooted at this node. The
	 * resulting nodes are owned by the given AST, which may be different from
	 * the AST of this node. Even if this node has a parent, the result node
	 * will be unparented.
	 * <p>
	 * This method must be implemented in subclasses.
	 * </p>
	 * <p>
	 * This method does not report pre- and post-clone events. All callers
	 * should instead call <code>clone(AST)</code> to ensure that pre- and
	 * post-clone events are reported.
	 * </p>
	 * <p>
	 * N.B. This method is package-private, so that the implementations of this
	 * method in each of the concrete AST node types do not clutter up the API
	 * doc.
	 * </p>
	 * 
	 * @param target
	 *            the AST that is to own the nodes in the result
	 * @return the root node of the copies subtree
	 */
	abstract ASTNode clone0(AST target);

	/**
	 * Checks whether the given new child node is a node in a different AST from
	 * its parent-to-be, whether it is already has a parent, whether adding it
	 * to its parent-to-be would create a cycle, and whether the child is of the
	 * right type. The parent-to-be is the enclosing instance.
	 * 
	 * @param node
	 *            the parent-to-be node
	 * @param newChild
	 *            the new child of the parent
	 * @param cycleCheck
	 *            <code>true</code> if cycles are possible and need to be
	 *            checked, <code>false</code> if cycles are impossible and do
	 *            not need to be checked
	 * @param nodeType
	 *            a type constraint on child nodes, or <code>null</code> if no
	 *            special check is required
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the child is null</li>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the child has the incorrect node type</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	static void checkNewChild(ASTNode node, ASTNode newChild, boolean cycleCheck, Class<?> nodeType) {
		if (newChild.ast != node.ast) {
			// new child is from a different AST
			throw new IllegalArgumentException();
		}
		if (newChild.getParent() != null) {
			// new child currently has a different parent
			throw new IllegalArgumentException();
		}
		if (cycleCheck && newChild == node.getProgramRoot()) {
			// inserting new child would create a cycle
			throw new IllegalArgumentException();
		}
		Class<?> childClass = newChild.getClass();
		if (nodeType != null && !nodeType.isAssignableFrom(childClass)) {
			// new child is not of the right type
			throw new ClassCastException();
		}
		if ((newChild.flags & PROTECT) != 0) {
			// new child node is protected => cannot be parented
			throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
		}
	}

	/**
	 * Sets the value of the given int-valued property for this node. The
	 * default implementation of this method throws an exception explaining that
	 * this node does not have such a property. This method should be extended
	 * in subclasses that have at leasy one simple property whose value type is
	 * int.
	 * 
	 * @param property
	 *            the property
	 * @param get
	 *            <code>true</code> for a get operation, and <code>false</code>
	 *            for a set operation
	 * @param value
	 *            the new property value; ignored for get operations
	 * @return the value; always returns <code>0</code> for set operations
	 * @exception RuntimeException
	 *                if this node does not have the given property, or if the
	 *                given value cannot be set as specified
	 */
	int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		throw new RuntimeException("Node does not have this property"); //$NON-NLS-1$
	}

	/**
	 * Sets the value of the given boolean-valued property for this node. The
	 * default implementation of this method throws an exception explaining that
	 * this node does not have such a property. This method should be extended
	 * in subclasses that have at least one simple property whose value type is
	 * boolean.
	 * 
	 * @param property
	 *            the property
	 * @param get
	 *            <code>true</code> for a get operation, and <code>false</code>
	 *            for a set operation
	 * @param value
	 *            the new property value; ignored for get operations
	 * @return the value; always returns <code>false</code> for set operations
	 * @exception RuntimeException
	 *                if this node does not have the given property, or if the
	 *                given value cannot be set as specified
	 */
	boolean internalGetSetBooleanProperty(SimplePropertyDescriptor property, boolean get, boolean value) {
		throw new RuntimeException("Node does not have this property"); //$NON-NLS-1$
	}

	/**
	 * Sets the value of the given property for this node. The default
	 * implementation of this method throws an exception explaining that this
	 * node does not have such a property. This method should be extended in
	 * subclasses that have at leasy one simple property whose value type is a
	 * reference type.
	 * 
	 * @param property
	 *            the property
	 * @param get
	 *            <code>true</code> for a get operation, and <code>false</code>
	 *            for a set operation
	 * @param value
	 *            the new property value, or <code>null</code> if none; ignored
	 *            for get operations
	 * @return the value, or <code>null</code> if none; always returns
	 *         <code>null</code> for set operations
	 * @exception RuntimeException
	 *                if this node does not have the given property, or if the
	 *                given value cannot be set as specified
	 */
	Object internalGetSetObjectProperty(SimplePropertyDescriptor property, boolean get, Object value) {
		throw new RuntimeException("Node does not have this property"); //$NON-NLS-1$
	}

	/**
	 * Sets the child value of the given property for this node. The default
	 * implementation of this method throws an exception explaining that this
	 * node does not have such a property. This method should be extended in
	 * subclasses that have at leasy one child property.
	 * 
	 * @param property
	 *            the property
	 * @param get
	 *            <code>true</code> for a get operation, and <code>false</code>
	 *            for a set operation
	 * @param child
	 *            the new child value, or <code>null</code> if none; always
	 *            <code>null</code> for get operations
	 * @return the child, or <code>null</code> if none; always returns
	 *         <code>null</code> for set operations
	 * @exception RuntimeException
	 *                if this node does not have the given property, or if the
	 *                given child cannot be set as specified
	 */
	ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		throw new RuntimeException("Node does not have this property"); //$NON-NLS-1$
	}

	/**
	 * Returns the list value of the given property for this node. The default
	 * implementation of this method throws an exception explaining that this
	 * noed does not have such a property. This method should be extended in
	 * subclasses that have at leasy one child list property.
	 * 
	 * @param property
	 *            the property
	 * @return the list (element type: {@link ASTNode})
	 * @exception RuntimeException
	 *                if the given node does not have the given property
	 */
	List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		throw new RuntimeException("Node does not have this property"); //$NON-NLS-1$
	}

	/**
	 * A specialized implementation of a list of ASTNodes. The implementation is
	 * based on an ArrayList.
	 */
	class NodeList<T extends ASTNode> extends AbstractList<T> {

		/**
		 * The underlying list in which the nodes of this list are stored
		 * (element type: <code>ASTNode</code>).
		 * <p>
		 * Be stingy on storage - assume that list will be empty.
		 * </p>
		 * <p>
		 * This field declared default visibility (rather than private) so that
		 * accesses from <code>NodeList.Cursor</code> do not require a synthetic
		 * accessor method.
		 * </p>
		 */
		ArrayList<T> store = new ArrayList<>(0);

		/**
		 * The property descriptor for this list.
		 */
		ChildListPropertyDescriptor propertyDescriptor;

		/**
		 * A cursor for iterating over the elements of the list. Does not lose
		 * its position if the list is changed during the iteration.
		 */
		class Cursor implements Iterator<T> {
			/**
			 * The position of the cursor between elements. If the value is N,
			 * then the cursor sits between the element at positions N-1 and N.
			 * Initially just before the first element of the list.
			 */
			private int position = 0;

			/*
			 * (non-Javadoc) Method declared on <code>Iterator</code>.
			 */
			@Override
			public boolean hasNext() {
				return this.position < NodeList.this.store.size();
			}

			/*
			 * (non-Javadoc) Method declared on <code>Iterator</code>.
			 */
			@Override
			public T next() {
				T result = NodeList.this.store.get(this.position);
				this.position++;
				return result;
			}

			/*
			 * (non-Javadoc) Method declared on <code>Iterator</code>.
			 */
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

			/**
			 * Adjusts this cursor to accomodate an add/remove at the given
			 * index.
			 * 
			 * @param index
			 *            the position at which the element was added or removed
			 * @param delta
			 *            +1 for add, and -1 for remove
			 */
			void update(int index, int delta) {
				if (this.position > index) {
					// the cursor has passed the added or removed element
					this.position += delta;
				}
			}
		}

		/**
		 * A list of currently active cursors (element type: <code>Cursor</code>
		 * ), or <code>null</code> if there are no active cursors.
		 * <p>
		 * It is important for storage considerations to maintain the
		 * null-means-empty invariant; otherwise, every NodeList instance will
		 * waste a lot of space. A cursor is needed only for the duration of a
		 * visit to the child nodes. Under normal circumstances, only a single
		 * cursor is needed; multiple cursors are only required if there are
		 * multiple visits going on at the same time.
		 * </p>
		 */
		private List<Cursor> cursors = null;

		/**
		 * Creates a new empty list of nodes owned by this node. This node will
		 * be the common parent of all nodes added to this list.
		 * 
		 * @param property
		 *            the property descriptor
		 * @since 3.0
		 */
		NodeList(ChildListPropertyDescriptor property) {
			super();
			this.propertyDescriptor = property;
		}

		/*
		 * (non-javadoc)
		 * 
		 * @see java.util.AbstractCollection#size()
		 */
		@Override
		public int size() {
			return this.store.size();
		}

		/*
		 * (non-javadoc)
		 * 
		 * @see AbstractList#get(int)
		 */
		@Override
		public T get(int index) {
			return this.store.get(index);
		}

		/*
		 * (non-javadoc)
		 * 
		 * @see List#set(int, java.lang.Object)
		 */
		@Override
		public T set(int index, T element) {
			if (element == null) {
				throw new IllegalArgumentException();
			}
			if ((ASTNode.this.flags & PROTECT) != 0) {
				// this node is protected => cannot gain or lose children
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}
			// delink old child from parent, and link new child to parent
			ASTNode newChild = element;
			ASTNode oldChild = this.store.get(index);
			if (oldChild == newChild) {
				return (T) oldChild;
			}
			if ((oldChild.flags & PROTECT) != 0) {
				// old child is protected => cannot be unparented
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}
			ASTNode.checkNewChild(ASTNode.this, newChild, this.propertyDescriptor.cycleRisk,
					this.propertyDescriptor.elementType);
			ASTNode.this.ast.preReplaceChildEvent(ASTNode.this, oldChild, newChild, this.propertyDescriptor);

			T result = this.store.set(index, (T) newChild);
			// n.b. setParent will call ast.modifying()
			oldChild.setParent(null, null);
			newChild.setParent(ASTNode.this, this.propertyDescriptor);
			ASTNode.this.ast.postReplaceChildEvent(ASTNode.this, oldChild, newChild, this.propertyDescriptor);
			return result;
		}

		/*
		 * (non-javadoc)
		 * 
		 * @see List#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, T element) {
			if (element == null) {
				throw new IllegalArgumentException();
			}
			if ((ASTNode.this.flags & PROTECT) != 0) {
				// this node is protected => cannot gain or lose children
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}
			// link new child to parent
			ASTNode newChild = element;
			ASTNode.checkNewChild(ASTNode.this, newChild, this.propertyDescriptor.cycleRisk,
					this.propertyDescriptor.elementType);
			ASTNode.this.ast.preAddChildEvent(ASTNode.this, newChild, this.propertyDescriptor);

			this.store.add(index, element);
			updateCursors(index, +1);
			// n.b. setParent will call ast.modifying()
			newChild.setParent(ASTNode.this, this.propertyDescriptor);
			ASTNode.this.ast.postAddChildEvent(ASTNode.this, newChild, this.propertyDescriptor);
		}

		/*
		 * (non-javadoc)
		 * 
		 * @see List#remove(int)
		 */
		@Override
		public T remove(int index) {
			if ((ASTNode.this.flags & PROTECT) != 0) {
				// this node is protected => cannot gain or lose children
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}
			// delink old child from parent
			ASTNode oldChild = this.store.get(index);
			if ((oldChild.flags & PROTECT) != 0) {
				// old child is protected => cannot be unparented
				throw new IllegalArgumentException("AST node cannot be modified"); //$NON-NLS-1$
			}

			ASTNode.this.ast.preRemoveChildEvent(ASTNode.this, oldChild, this.propertyDescriptor);
			// n.b. setParent will call ast.modifying()
			oldChild.setParent(null, null);
			T result = this.store.remove(index);
			updateCursors(index, -1);
			ASTNode.this.ast.postRemoveChildEvent(ASTNode.this, oldChild, this.propertyDescriptor);
			return result;

		}

		/**
		 * Allocate a cursor to use for a visit. The client must call
		 * <code>releaseCursor</code> when done.
		 * <p>
		 * This method is internally synchronized on this NodeList. It is
		 * thread-safe to create a cursor.
		 * </p>
		 * 
		 * @return a new cursor positioned before the first element of the list
		 */
		Cursor newCursor() {
			synchronized (this) {
				// serialize cursor management on this NodeList
				if (this.cursors == null) {
					// convert null to empty list
					this.cursors = new ArrayList<>(1);
				}
				Cursor result = new Cursor();
				this.cursors.add(result);
				return result;
			}
		}

		/**
		 * Releases the given cursor at the end of a visit.
		 * <p>
		 * This method is internally synchronized on this NodeList. It is
		 * thread-safe to release a cursor.
		 * </p>
		 * 
		 * @param cursor
		 *            the cursor
		 */
		void releaseCursor(Cursor cursor) {
			synchronized (this) {
				// serialize cursor management on this NodeList
				this.cursors.remove(cursor);
				if (this.cursors.isEmpty()) {
					// important: convert empty list back to null
					// otherwise the node will hang on to needless junk
					this.cursors = null;
				}
			}
		}

		/**
		 * Adjusts all cursors to accomodate an add/remove at the given index.
		 * <p>
		 * This method is only used when the list is being modified. The AST is
		 * not thread-safe if any of the clients are modifying it.
		 * </p>
		 * 
		 * @param index
		 *            the position at which the element was added or removed
		 * @param delta
		 *            +1 for add, and -1 for remove
		 */
		private void updateCursors(int index, int delta) {
			if (this.cursors == null) {
				// there are no cursors to worry about
				return;
			}
			for (Iterator<Cursor> it = this.cursors.iterator(); it.hasNext();) {
				Cursor c = it.next();
				c.update(index, delta);
			}
		}
	}

}
