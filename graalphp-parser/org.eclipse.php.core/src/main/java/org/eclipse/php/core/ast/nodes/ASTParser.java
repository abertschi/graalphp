/*******************************************************************************
 * Copyright (c) 2009, 2018 IBM Corporation and others.
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

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


import org.eclipse.php.core.PHPVersion;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import java_cup.runtime.lr_parser;

/**
 * A PHP language parser for creating abstract syntax trees (ASTs).
 * <p>
 */
public class ASTParser {

	/**
	 * THREAD SAFE AST PARSER STARTS HERE
	 */
	private final AST ast;

	private ASTParser(Reader reader, PHPVersion phpVersion, boolean useASPTags, boolean useShortTags)
			throws IOException {
		this(reader, phpVersion, useASPTags, useShortTags, null);
	}

	private ASTParser(Reader reader, PHPVersion phpVersion, boolean useASPTags, boolean useShortTags,
			Object sourceModule) throws IOException {
		this.ast = new AST(reader, phpVersion, useASPTags, useShortTags);
		this.ast.setDefaultNodeFlag(ASTNode.ORIGINAL);
	}

	/**
	 * Factory methods for ASTParser
	 */
	public static ASTParser newParser(PHPVersion version, boolean useASPTags, boolean useShortTags) {
		try {
			return new ASTParser(new StringReader(""), version, useASPTags, //$NON-NLS-1$
					useShortTags);
		} catch (IOException e) {
			assert false;
			// Since we use empty reader we cannot have an IOException here
			return null;
		}
	}

	public static ASTParser newParser(Reader reader, PHPVersion version, boolean useASPTags, boolean useShortTags)
			throws IOException {
		return new ASTParser(reader, version, useASPTags, useShortTags);
	}

	/**
	 * Set the raw source that will be used on parsing
	 * 
	 * @throws IOException
	 */
	public void setSource(char[] source) throws IOException {
		final CharArrayReader charArrayReader = new CharArrayReader(source);
		setSource(charArrayReader);
	}

	/**
	 * Set source of the parser
	 * 
	 * @throws IOException
	 */
	public void setSource(Reader source) throws IOException {
		this.ast.setSource(source);
	}

    public Program createAST() throws Exception {
        final Scanner lexer = this.ast.lexer();
        final lr_parser phpParser = this.ast.parser();
        phpParser.setScanner(lexer);
        final Symbol symbol = phpParser.parse();
        if (symbol == null || !(symbol.value instanceof Program)) {
            return null;
        }
        Program p = (Program) symbol.value;
        return p;
    }
}
