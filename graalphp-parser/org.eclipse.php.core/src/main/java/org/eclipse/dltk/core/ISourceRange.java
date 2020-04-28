/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * A source range defines an element's source coordinates relative to
 * its source buffer.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
public interface ISourceRange {

/**
 * Returns the number of characters of the source code for this element,
 * relative to the source buffer in which this element is contained.
 * 
 * @return the number of characters of the source code for this element,
 * relative to the source buffer in which this element is contained
 */
int getLength();
/**
 * Returns the 0-based index of the first character of the source code for this element,
 * relative to the source buffer in which this element is contained.
 * 
 * @return the 0-based index of the first character of the source code for this element,
 * relative to the source buffer in which this element is contained
 */
int getOffset();
}
