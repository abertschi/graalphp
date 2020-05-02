/*******************************************************************************
 * Copyright (c) 2005, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.ast;

@Deprecated
public class DLTKToken {
	public static final int INVALID_TYPE = 0;
	public static final int EOF_TYPE = 1;
	public static final int SKIP = -1;

	protected int line;
	protected String text;
	protected int col;

	// each Token has at least a token type
	protected int type = INVALID_TYPE;

	// the illegal token object
	public static DLTKToken badToken = new DLTKToken(INVALID_TYPE, "<no text>"); //$NON-NLS-1$

	public DLTKToken() {
		line = 0;
		col = 0;
	}

	public DLTKToken(int t) {
		type = t;
	}

	public DLTKToken(int t, String txt) {
		type = t;
		setText(txt);
		line = 0;
		col = 0;
	}

	public int getColumn() {
		return this.col;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String t) {
		this.text = t;
	}

	public void setColumn(int c) {
		this.col = c;
	}

	public int getType() {
		return type;
	}

	public void setType(int t) {
		type = t;
	}

	@Override
	public String toString() {
		return "[\"" + getText() + "\",<" + getType() + ">]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public void setLine(int line) {
		this.line = line;
	}
}