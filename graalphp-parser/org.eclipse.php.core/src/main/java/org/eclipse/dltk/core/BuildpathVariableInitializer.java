/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *     IBM Corporation - added support for requesting updates of a particular
 *                       container for generic container operations.
 * 						 - canUpdateBuildpathContainer(IPath, IScriptProject)
 * 						 - requestBuildpathContainerUpdate(IPath, IScriptProject, IBuildpathContainer)
 *     IBM Corporation - allow initializers to provide a readable description
 *                       of a container reference, ahead of actual resolution.
 * 						 - getDescription(IPath, IScriptProject)
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * Abstract base implementation of all buildpath variable initializers.
 * Buildpath variable initializers are used in conjunction with the
 * "org.eclipse.dltk.core.buildpathVariableInitializer" extension point.
 * <p>
 * Clients should subclass this class to implement a specific buildpath variable
 * initializer. The subclass must have a public 0-argument constructor and a
 * concrete implementation of <code>initialize</code>.
 * 
 * @see IBuildpathEntry
 * @since 2.0
 */
public abstract class BuildpathVariableInitializer {

	/**
	 * Creates a new buildpath variable initializer.
	 */
	public BuildpathVariableInitializer() {
		// a buildpath variable initializer must have a public 0-argument
		// constructor
	}

	/**
	 * Binds a value to the workspace buildpath variable with the given name, or
	 * fails silently if this cannot be done.
	 * <p>
	 * A variable initializer is automatically activated whenever a variable
	 * value is needed and none has been recorded so far. The implementation of
	 * the initializer can set the corresponding variable using
	 * <code>DLTKCore#setBuildpathVariable</code>.
	 * 
	 * @param variable
	 *            the name of the workspace buildpath variable that requires a
	 *            binding
	 * 
	 * @see DLTKCore#getBuildpathVariable(String)
	 * @see DLTKCore#setBuildpathVariable(String,
	 *      org.eclipse.core.runtime.IPath,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 * @see DLTKCore#setBuildpathVariables(String[],
	 *      org.eclipse.core.runtime.IPath[],
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public abstract void initialize(String variable);
}
