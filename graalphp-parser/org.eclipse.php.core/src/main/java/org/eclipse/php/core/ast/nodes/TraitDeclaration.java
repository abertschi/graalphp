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

import java.util.List;

import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.Visitor;

public class TraitDeclaration extends ClassDeclaration {

	public TraitDeclaration(int start, int end, AST ast, int modifier, Identifier className, Expression superClass,
			List<Identifier> interfaces, Block body) {
		super(start, end, ast, modifier, className, superClass, interfaces, body);
	}

	public TraitDeclaration(AST ast) {
		super(ast);
	}

	/*
	 * Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		if (!(other instanceof TraitDeclaration)) {
			return false;
		}
		return super.subtreeMatch(matcher, other);
	}

	@Override
	ASTNode clone0(AST target) {
		final Block body = ASTNode.copySubtree(target, getBody());
		final int modifier = getModifier();
		final Identifier name = ASTNode.copySubtree(target, getName());

		final TraitDeclaration result = new TraitDeclaration(getStart(), getEnd(), target, modifier, name, getName(),
				interfaces(), body);
		return result;
	}

	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append(tab).append("<TraitDeclaration"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n"); //$NON-NLS-1$
		buffer.append(tab).append(TAB).append("<TraitName>\n"); //$NON-NLS-1$
		getName().toString(buffer, TAB + TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append(TAB).append("</TraitName>\n"); //$NON-NLS-1$

		getBody().toString(buffer, TAB + tab);
		buffer.append("\n"); //$NON-NLS-1$
		buffer.append(tab).append("</TraitDeclaration>"); //$NON-NLS-1$
	}

	@Override
	public int getType() {
		return ASTNode.CLASS_DECLARATION;
	}

	@Override
	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}
}
