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

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * @since 2.0
 */
public interface IArchive {

	/**
	 * Returns an enumeration of the archive file entries.
	 * 
	 * @return an enumeration of the archive file entries
	 * @throws IllegalStateException
	 *             if the archive file has been closed
	 */
	public Enumeration<? extends IArchiveEntry> getArchiveEntries();

	/**
	 * Returns the path name of the archive file.
	 * 
	 * @return the path name of the archive file
	 */
	public String getName();

	/**
	 * Closes the archive file.
	 * <p>
	 * Closing this archive file will close all of the input streams previously
	 * returned by invocations of the {@link #getInputStream getInputStream}
	 * method.
	 * 
	 * @throws IOException
	 *             if an I/O error has occurred
	 */
	public void close() throws IOException;

	/**
	 * Returns the archive file entry for the specified name, or
	 * <code>null</code> if not found.
	 * 
	 * @param name
	 * @return
	 */
	public IArchiveEntry getArchiveEntry(String name);

	/**
	 * Returns an input stream for reading the contents of the specified archive
	 * file entry.
	 * 
	 * <p>
	 * Closing this archive file will, in turn, close all input streams that
	 * have been returned by invocations of this method.
	 * 
	 * @param entry
	 *            the archive file entry
	 * @return the input stream for reading the contents of the specified
	 *         archive file entry.
	 * @throws IOException
	 *             if an I/O error has occurred
	 * @throws IllegalStateException
	 *             if the archive file has been closed
	 */
	public InputStream getInputStream(IArchiveEntry entry) throws IOException;
}
