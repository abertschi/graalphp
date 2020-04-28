/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * A source element parser extracts structural and reference information from a
 * piece of source.
 * 
 * <p>
 * The structural investigation includes:
 * </p>
 * <ul>
 * <li>the package statement
 * <li>import statements
 * <li>types
 * <li>fields
 * <li>methods
 * </ul>
 * 
 * <p>
 * If reference information is requested, then all source constructs are
 * investigated and type, field & method references are provided as well.
 * </p>
 * 
 * <p>
 * Any (parsing) problem encountered is also provided.
 * </p>
 * 
 * <p>
 * Language specific implementation is contributed via
 * <code>org.eclipse.dltk.core.sourceElementParsers</code>.
 * </p>
 * 
 * @see ISourceElementRequestor
 */
@Deprecated
public interface ISourceElementParser {
}
