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
 * Markers used by the DLTK model.
 * <p>
 * This interface declares constants only; it is not intended to be implemented
 * or extended.
 * </p>
 * TODO (alex) merge into {@link IModelMarker}
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 */
@Deprecated
public interface IScriptModelMarker {

	/**
	 * DLTK model problem marker type (value
	 * <code>"org.eclipse.dltk.core.problem"</code>). This can be used to
	 * recognize those markers in the workspace that flag problems detected by
	 * the DLTK tooling during compilation.
	 */
	String DLTK_MODEL_PROBLEM_MARKER = DLTKCore.PLUGIN_ID + ".problem"; //$NON-NLS-1$

	/**
	 * DLTK model transient problem marker type (value
	 * <code>"org.eclipse.dltk.core.transient_problem"</code>). This can be
	 * used to recognize those markers in the workspace that flag transient
	 * problems detected by the DLTK tooling (such as a problem detected by the
	 * outliner, or a problem detected during a code completion). 
	 * Transient problems are reported as <code>IProblem</code> through
	 * various API. Only the evaluation API is still producing markers for
	 * transient problems.
	 * 
	 * @see org.eclipse.dltk.core.compiler.IProblem
	 * @see org.eclipse.dltk.core.eval.ICodeSnippetRequestor#acceptProblem(org.eclipse.core.resources.IMarker,String,
	 *      int)
	 */
	String TRANSIENT_PROBLEM = DLTKCore.PLUGIN_ID + ".transient_problem"; //$NON-NLS-1$

	/**
	 * DLTK model task marker type (value
	 * <code>"org.eclipse.dltk.core.task"</code>). This can be used to
	 * recognize task markers in the workspace that correspond to tasks
	 * specified in DLTK source comments and detected during compilation (for
	 * example, 'TO-DO: ...'). Tasks are identified by a task tag, which can be
	 * customized through <code>DLTKCore</code> option
	 * <code>"org.eclipse.dltk.core.compiler.taskTag"</code>.
	 * 
	 * 
	 */
	String TASK_MARKER = DLTKCore.PLUGIN_ID + ".task"; //$NON-NLS-1$

	/**
	 * Id marker attribute (value <code>"arguments"</code>). Arguments are
	 * concatenated into one String, prefixed with an argument count (followed
	 * with colon separator) and separated with '#' characters. For example: {
	 * "foo", "bar" } is encoded as "2:foo#bar", { } is encoded as "0: "
	 * 
	 * 
	 */
	String ARGUMENTS = "arguments"; //$NON-NLS-1$

	/**
	 * ID marker attribute (value <code>"id"</code>).
	 */
	String ID = "id"; //$NON-NLS-1$

	/**
	 * ID category marker attribute (value <code>"categoryId"</code>)
	 * 
	 */
	String CATEGORY_ID = "categoryId"; //$NON-NLS-1$

	/**
	 * Flags marker attribute (value <code>"flags"</code>). Reserved for
	 * future use.
	 */
	String FLAGS = "flags"; //$NON-NLS-1$

	/**
	 * Cycle detected marker attribute (value <code>"cycleDetected"</code>).
	 * Used only on buildpath problem markers. The value of this attribute is
	 * either "true" or "false".
	 */
	String CYCLE_DETECTED = "cycleDetected"; //$NON-NLS-1$

	/**
	 * Build path problem marker type (value
	 * <code>"org.eclipse.dltk.core.buildpath_problem"</code>). This can be
	 * used to recognize those markers in the workspace that flag problems
	 * detected by the DLTK tooling during buildpath setting.
	 */
	String BUILDPATH_PROBLEM_MARKER = DLTKCore.PLUGIN_ID
			+ ".buildpath_problem"; //$NON-NLS-1$

	/**
	 * Buildpath file format marker attribute (value
	 * <code>"buildpathFileFormat"</code>). Used only on buildpath problem
	 * markers. The value of this attribute is either "true" or "false".
	 * 
	 * 
	 */
	String BUILDPATH_FILE_FORMAT = "buildpathFileFormat"; //$NON-NLS-1$
}
