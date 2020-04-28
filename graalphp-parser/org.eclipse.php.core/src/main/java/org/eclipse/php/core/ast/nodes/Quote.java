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
 * Represents complex qoute(i.e. qoute that includes string and variables). Also
 * represents heredoc and nowdoc.
 * 
 * <pre>
 * e.g.
 * 
 * "this is $a quote", "'single ${$complex->quote()}'" <<<Heredoc\n This is here
 * documents \nHeredoc;\n
 * 
 * Note: "This is".$not." a quote node", 'This is $not a quote too'
 * </pre>
 */
public class Quote extends VariableBase {

	public static final int QT_QUOTE = 0;
	public static final int QT_SINGLE = 1;
	public static final int QT_HEREDOC = 2;
	public static final int QT_NOWDOC = 3;

	private final ASTNode.NodeList<Expression> expressions = new ASTNode.NodeList<>(EXPRESSIONS_PROPERTY);
	private int quoteType;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor EXPRESSIONS_PROPERTY = new ChildListPropertyDescriptor(Quote.class,
			"expressions", Expression.class, CYCLE_RISK); //$NON-NLS-1$
	public static final SimplePropertyDescriptor QUOTE_TYPE_PROPERTY = new SimplePropertyDescriptor(Quote.class,
			"quoteType", Integer.class, OPTIONAL); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(2);
		properyList.add(EXPRESSIONS_PROPERTY);
		properyList.add(QUOTE_TYPE_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	public Quote(AST ast) {
		super(ast);
	}

	public Quote(int start, int end, AST ast, List<Expression> expressions, int type) {
		super(start, end, ast);
		this.expressions.addAll(expressions);
		setQuoteType(type);
	}

	public static String getType(int type) {
		switch (type) {
		case QT_QUOTE:
			return "quote"; //$NON-NLS-1$
		case QT_SINGLE:
			return "single"; //$NON-NLS-1$
		case QT_HEREDOC:
			return "heredoc"; //$NON-NLS-1$
		case QT_NOWDOC:
			return "nowdoc"; //$NON-NLS-1$
		default:
			throw new IllegalArgumentException();
		}
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
		for (ASTNode node : this.expressions) {
			node.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		for (ASTNode node : this.expressions) {
			node.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : this.expressions) {
			node.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<Quote"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(" type='").append(getType(quoteType)).append("'>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		for (ASTNode node : this.expressions) {
			node.toString(buffer, TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(tab).append("</Quote>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.QUOTE;
	}

	/**
	 * @return expression list of the echo statement
	 */
	public List<Expression> expressions() {
		return this.expressions;
	}

	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == EXPRESSIONS_PROPERTY) {
			return expressions();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/**
	 * The quote type see {@link #QT_HEREDOC}, {@link #QT_QUOTE},
	 * {@link #QT_SINGLE}, {@link #QT_NOWDOC}
	 * 
	 * @return quote type
	 */
	public int getQuoteType() {
		return quoteType;
	}

	/**
	 * Sets the operator of this unary operation
	 * 
	 * @param new
	 *            operator of this unary operation
	 * @exception IllegalArgumentException
	 *                if:
	 *                <ul>
	 *                <li>the node belongs to a different AST</li>
	 *                <li>the node already has a parent</li>
	 *                <li>a cycle in would be created</li>
	 *                </ul>
	 */
	public final void setQuoteType(int value) {
		if (value != QT_HEREDOC && value != QT_QUOTE && value != QT_SINGLE && value != QT_NOWDOC) {
			throw new IllegalArgumentException();
		}

		preValueChange(QUOTE_TYPE_PROPERTY);
		this.quoteType = value;
		postValueChange(QUOTE_TYPE_PROPERTY);
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final int internalGetSetIntProperty(SimplePropertyDescriptor property, boolean get, int value) {
		if (property == QUOTE_TYPE_PROPERTY) {
			if (get) {
				return getQuoteType();
			} else {
				setQuoteType(value);
				return 0;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetIntProperty(property, get, value);
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
		final int type = getQuoteType();
		final List<Expression> expressions = ASTNode.copySubtrees(target, expressions());
		final Quote result = new Quote(this.getStart(), this.getEnd(), target, expressions, type);
		return result;
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

}
