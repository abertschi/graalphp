/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
/*
 * (c) 2002, 2005 xored software and others all rights reserved. http://www.xored.com
 */

package org.eclipse.dltk.ast;

/**
 * Flags for declaration modifiers
 */
public interface Modifiers {

	public static final int AccDefault = 0;
	public static final int AccAbstract = (1 << 0);

	/**
	 * Constant declaration
	 */
	public static final int AccConstant = (1 << 1);
	public static final int AccFinal = (1 << 2);
	public static final int AccInterface = (1 << 3);
	public static final int AccPrivate = (1 << 4);
	public static final int AccProtected = (1 << 5);
	public static final int AccPublic = (1 << 6);
	public static final int AccStatic = (1 << 7);
	public final static int AccReference = (1 << 8);
	public final static int AccConst = (1 << 9);

	/**
	 * Module declaration (class and module are both types and can be
	 * distinguished by this flag)
	 */
	public static final int AccModule = (1 << 10);

	/**
	 * Namespace (type it TCL)
	 */
	public static final int AccNameSpace = (1 << 11);
	public static final int AccAnnotation = (1 << 12);
	public static final int AccGlobal = (1 << 13);
	public static final int AccUpVar = (1 << 14);

	public static final int AccTestCase = (1 << 15);
	public static final int AccTest = (1 << 16);

	/**
	 * @since 2.0
	 */
	public static final int AccSynthetic = (1 << 17);

	public static final int USER_MODIFIER = 18;
}
