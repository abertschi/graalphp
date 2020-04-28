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

public interface ISourceModuleInfoCache {
	/**
	 * Info hold any kind of information associated with source module. If
	 * source module are modified, information are deleted.
	 */
	interface ISourceModuleInfo {
		Object get(String key);

		void put(String key, Object value);

		void remove(String key);

		boolean isEmpty();
	}

	/**
	 * Return new cache instance for specified module.
	 */
	public ISourceModuleInfo get(ISourceModule module);

	/**
	 * Remove specified module from cache.
	 */
	public void remove(ISourceModule sourceModule);

	/**
	 * Remove all information from cache. Called then required reparse of all
	 * modules.
	 */
	public void clear();

	/**
	 * Returns number of the elements, currently contained in the cache.
	 */
	public int size();

	/**
	 * Returns the capacity of the cache, i.e. the number of elements it can
	 * contain.
	 */
	public int capacity();
}
