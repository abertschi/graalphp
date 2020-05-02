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



public class ModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Exception nestedCoreException;
	
	/**
	 * Creates a model exception that wrappers the given <code>Throwable</code>.
	 * The exception contains a script-specific status object with severity
	 * <code>IStatus.ERROR</code> and the given status code.
	 *
	 * @param e the <code>Throwable</code>
	 * @param code one of the model-specific status codes declared in
	 *   <code>IScriptModelStatusConstants</code>
	 * @see IModelStatusConstants
	 * @see org.eclipse.core.runtime.IStatus#ERROR
	 */
	public ModelException(Throwable e, int code) {
	    super(e);
	}
}
