/*******************************************************************************
 * Copyright (c) 2009, 2016 IBM Corporation and others.
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
package org.eclipse.php.core.compiler.ast.nodes;



import org.eclipse.php.core.ast.nodes.AST;
import org.eclipse.php.core.ast.nodes.Comment;



public class PHPDocBlock extends Comment {

    public PHPDocBlock(int start, int end, AST ast, int type) {
        super(start, end, ast, type);
    }

    public PHPDocBlock(AST ast) {
        super(ast);
    }
}
