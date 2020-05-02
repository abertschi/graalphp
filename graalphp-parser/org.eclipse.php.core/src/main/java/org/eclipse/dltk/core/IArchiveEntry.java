/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * @since 2.0
 */
public interface IArchiveEntry {

	/**
	 * Returns the name of the entry.
	 * 
	 * @return the name of the entry
	 */
	public String getName();

	/**
	 * Returns true if this is a directory entry. (For
	 * {@link java.util.zip.ZipFile} directory entry is defined to be one whose
	 * name ends with a '/').
	 * 
	 * @return true if this is a directory entry
	 */
	public boolean isDirectory();

	/**
	 * Returns the uncompressed size of the entry data, or -1 if not known.
	 * 
	 * @return the uncompressed size of the entry data, or -1 if not known
	 */
	public long getSize();

}
