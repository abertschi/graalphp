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

import java.util.LinkedList;

/**
 * Helper class to collect dim list [2][3]{4} for php < 7
 * 
 * @author zulus
 */
public class DimList extends LinkedList<DimList.Element> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1899798103075372098L;

	public class Element {
		public Element(Expression index, int type, int right) {
			this.index = index;
			this.type = type;
			this.right = right;
		}

		public Expression index;
		public int type;
		public int right;
	}

	public void add(Expression index, int type, int right) {
		this.add(new Element(index, type, right));
	}
}
