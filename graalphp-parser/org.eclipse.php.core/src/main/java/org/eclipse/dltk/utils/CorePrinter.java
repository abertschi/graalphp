/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.utils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class CorePrinter extends PrintWriter {
	private int fTabLevel = 0;
	private boolean fAfterNewLine = false;

	public CorePrinter(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public CorePrinter(OutputStream out) {
		super(out);
	}

	public CorePrinter(Writer out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public CorePrinter(Writer out) {
		super(out);
	}

	public void indent() {
		this.print("{"); //$NON-NLS-1$
		this.println(""); //$NON-NLS-1$
		this.formatPrint(" "); //$NON-NLS-1$
		fTabLevel += 1;
	}

	public void dedent() {
		if (fTabLevel > 0) {
			this.fTabLevel -= 1;
		}
		this.printTabs();
		this.print("}"); //$NON-NLS-1$
		this.fAfterNewLine = true;
	}

	private void printTabs() {
		String tabs = ""; //$NON-NLS-1$
		for (int i = 0; i < this.fTabLevel; ++i) {
			tabs += "\t"; //$NON-NLS-1$
		}
		this.print(tabs);
	}

	public void formatPrintLn(String text) {
		if (fAfterNewLine) {
			fAfterNewLine = false;
			this.printTabs();
		}
		this.print(text.replaceAll("\n", "/n")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void formatPrint(String text) {
		String strs[] = text.split("\n"); //$NON-NLS-1$
		int count = 0;
		for (int i = 0; i < strs.length; ++i) {
			this.printTabs();
			if (count != strs.length) {
				this.println(strs[i]);
			} else {
				this.print(strs[i]);
			}
			count += 1;
		}
		fAfterNewLine = true;
	}
}
