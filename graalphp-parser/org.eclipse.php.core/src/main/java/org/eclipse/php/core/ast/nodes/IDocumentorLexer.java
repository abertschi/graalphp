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

import org.eclipse.php.core.compiler.ast.nodes.PHPDocBlock;

public interface IDocumentorLexer {

	public PHPDocBlock parse();

	public void reset(java.io.Reader reader, char[] buffer, int[] parameters);

	public int[] getParameters();

	public char[] getBuffer();
}