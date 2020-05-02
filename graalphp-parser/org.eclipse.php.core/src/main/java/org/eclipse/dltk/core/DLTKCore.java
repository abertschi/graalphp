/*******************************************************************************
 * Copyright (c) 2005, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.core;

import java.io.File;
import java.util.Hashtable;

/**
 * The main plugin class to be used in the desktop.
 */
public class DLTKCore {

	// Debug/Verbose constants
	public static final boolean VERBOSE = false;
	public static final boolean VERBOSE_MODEL_MANAGER = false;
	public static final boolean VERBOSE_BP_RESOLVE = false;
	public static final boolean VERBOSE_ZIP_ACCESS = false;
	public static final boolean VERBOSE_EXTERNAL_FRAGMENT = false;
	public static final boolean VERBOSE_JOBMANAGER = false;
	public static final boolean VERBOSE_SEARCH = false;
	public static final boolean VERBOSE_SEARCH_NAMELOOKUP = false;
	public static final boolean VERBOSE_COMPLETION = false;
	public static final boolean VERBOSE_MIXIN = false;

	public static final boolean PERFOMANCE = false;

	public static final boolean DEBUG = true;

	// Log errors into log.
	public static final boolean DEBUG_LOG = false;

	// The shared instance.
	private static DLTKCore plugin;

	public final static String PLUGIN_ID = "org.eclipse.dltk.core"; //$NON-NLS-1$

	/**
	 * Name of the User Library Container id.
	 * 
	 */
	public static final String USER_LIBRARY_CONTAINER_ID = "org.eclipse.dltk.USER_LIBRARY"; //$NON-NLS-1$

	/**
	 * Possible configurable option value.
	 */
	public static final String ERROR = "error"; //$NON-NLS-1$

	public static final String BUILDER_ID = PLUGIN_ID + ".scriptbuilder"; //$NON-NLS-1$

	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String ABORT = "abort"; //$NON-NLS-1$	
	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 */
	public static final String WARNING = "warning"; //$NON-NLS-1$
	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 */
	public static final String IGNORE = "ignore"; //$NON-NLS-1$

	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String ENABLED = "enabled"; //$NON-NLS-1$
	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String DISABLED = "disabled"; //$NON-NLS-1$
	/**
	 * Possible configurable option value.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CLEAN = "clean"; //$NON-NLS-1$

	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String COMPILER_PB_FORBIDDEN_REFERENCE = PLUGIN_ID
			+ ".compiler.problem.forbiddenReference"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String COMPILER_PB_DISCOURAGED_REFERENCE = PLUGIN_ID
			+ ".compiler.problem.discouragedReference"; //$NON-NLS-1$	

	/**
	 * Possible configurable option ID.
	 */
	public static final String CORE_ENCODING = PLUGIN_ID + ".encoding"; //$NON-NLS-1$

	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 */
	public static final String CORE_ENABLE_BUILDPATH_EXCLUSION_PATTERNS = PLUGIN_ID
			+ ".buildpath.exclusionPatterns"; //$NON-NLS-1$

	/**
	 * Possible configurable option ID.
	 */
	public static final String CORE_CIRCULAR_BUILDPATH = PLUGIN_ID
			+ ".circularBuildpath"; //$NON-NLS-1$

	/**
	 * public static final boolean DEBUG_PRINT_MODEL = false; Possible
	 * configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CORE_INCOMPLETE_BUILDPATH = PLUGIN_ID
			+ ".incompleteBuildpath"; //$NON-NLS-1$

	/**
	 * Code assist option ID: Activate Visibility Sensitive Completion.
	 * <p>
	 * When active, completion doesn't show that you can not see (for example,
	 * you can not see private methods of a super class). This option is
	 * language specific.
	 * <dl>
	 * <dt>Option id:</dt>
	 * <dd><code>{@value #CODEASSIST_VISIBILITY_CHECK}</code></dd>
	 * <dt>Possible values:</dt>
	 * <dd><code>{ {@value #ENABLED}, {@value #DISABLED} }</code></dd>
	 * <dt>Default:</dt>
	 * <dd><code>{@value #DISABLED}</code></dd>
	 * </dl>
	 */
	public static final String CODEASSIST_VISIBILITY_CHECK = PLUGIN_ID
			+ ".codeComplete.visibilityCheck"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_DEPRECATION_CHECK = PLUGIN_ID
			+ ".codeComplete.deprecationCheck"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_CAMEL_CASE_MATCH = PLUGIN_ID
			+ ".codeComplete.camelCaseMatch"; //$NON-NLS-1$

	/**
	 * Code assist option ID: Activate Substring Code Completion.
	 * <p>
	 * When enabled, completion shows proposals in which the pattern can be
	 * found as a substring in a case-insensitive way.
	 * </p>
	 * <dl>
	 * <dt>Option id:</dt>
	 * <dd><code>"org.eclipse.jdt.core.codeComplete.substringMatch"</code></dd>
	 * <dt>Possible values:</dt>
	 * <dd><code>{ "enabled", "disabled" }</code></dd>
	 * <dt>Default:</dt>
	 * <dd><code>"enabled"</code></dd>
	 * </dl>
	 *
	 * @category CodeAssistOptionID
	 */
	public static final String CODEASSIST_SUBSTRING_MATCH = PLUGIN_ID
			+ ".codeComplete.substringMatch"; //$NON-NLS-1$

	/**
	 * Possible configurable option ID.public static final boolean DEBUG_PARSER
	 * = false;
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_IMPLICIT_QUALIFICATION = PLUGIN_ID
			+ ".codeComplete.forceImplicitQualification"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_FIELD_PREFIXES = PLUGIN_ID
			+ ".codeComplete.fieldPrefixes"; //$NON-NLS-1$	
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_LOCAL_PREFIXES = PLUGIN_ID
			+ ".codeComplete.localPrefixes"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_ARGUMENT_PREFIXES = PLUGIN_ID
			+ ".codeComplete.argumentPrefixes"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_FIELD_SUFFIXES = PLUGIN_ID
			+ ".codeComplete.fieldSuffixes"; //$NON-NLS-1$	
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_LOCAL_SUFFIXES = PLUGIN_ID
			+ ".codeComplete.localSuffixes"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_ARGUMENT_SUFFIXES = PLUGIN_ID
			+ ".codeComplete.argumentSuffixes"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_FORBIDDEN_REFERENCE_CHECK = PLUGIN_ID
			+ ".codeComplete.forbiddenReferenceCheck"; //$NON-NLS-1$
	/**
	 * Possible configurable option ID.
	 * 
	 * @see #getDefaultOptions()
	 * 
	 */
	public static final String CODEASSIST_DISCOURAGED_REFERENCE_CHECK = PLUGIN_ID
			+ ".codeComplete.discouragedReferenceCheck"; //$NON-NLS-1$					

	/**
	 * Possible configurable option ID.
	 * 
	 * @since 2.0
	 */
	public static final String LANGUAGE_FILENAME_ASSOCIATIONS = PLUGIN_ID
			+ ".filename.associations"; //$NON-NLS-1$					

	/**
	 * @since 2.0
	 */
	public static final char LANGUAGE_FILENAME_ASSOCIATION_SEPARATOR = ';';

	/**
	 * Possible configurable option ID.
	 * 
	 * @since 2.0
	 */
	public static final String LOGGING_OPTIONS = PLUGIN_ID + ".logging.options"; //$NON-NLS-1$					

	/**
	 * @since 2.0
	 */
	public static final char LOGGING_OPTION_SEPARATOR = ';';

	/**
	 * Possible configurable option ID. Value should be {@link #ENABLED} or
	 * {@link #DISABLED}.
	 */
	public static final String BUILDER_ENABLED = PLUGIN_ID + ".builder"; //$NON-NLS-1$					

	/**
	 * Possible configurable option ID. Value should be {@link #ENABLED} or
	 * {@link #DISABLED}.
	 */
	public static final String INDEXER_ENABLED = PLUGIN_ID + ".indexer"; //$NON-NLS-1$					

	/**
	 * Possible configurable option ID.
	 */
	public static final String INDEXER_ID = PLUGIN_ID + ".indexer.id"; //$NON-NLS-1$
	
	/**
	 * Possible project configurable option ID. Value is the identifier of
	 * contributed {@link org.eclipse.dltk.ast.parser.ISourceParser}.
	 */
	public static final String PROJECT_SOURCE_PARSER_ID = PLUGIN_ID
			+ ".sourceParserId"; //$NON-NLS-1$					

	/**
	 * The constructor.
	 */
	public DLTKCore() {
		plugin = this;
	}

//	/**
//	 * Returns the single instance of the DLTK core plug-in runtime class.
//	 *
//	 * @return the single instance of the DLTK core plug-in runtime class
//	 */
//	public static Plugin getPlugin() {
//		return plugin;
//	}
//
//	/**
//	 * Adds the given listener for changes to script elements. Has no effect if
//	 * an identical listener is already registered.
//	 *
//	 * This listener will only be notified during the POST_CHANGE resource
//	 * change notification and any reconcile operation (POST_RECONCILE). For
//	 * finer control of the notification, use
//	 * <code>addElementChangedListener(IElementChangedListener,int)</code>,
//	 * which allows to specify a different eventMask.
//	 *
//	 * @param listener
//	 *            the listener
//	 * @see ElementChangedEvent
//	 */
//	public static void addElementChangedListener(
//			IElementChangedListener listener) {
//		addElementChangedListener(listener, ElementChangedEvent.POST_CHANGE
//				| ElementChangedEvent.POST_RECONCILE);
//	}
//
//	/**
//	 * Adds the given listener for changes to script elements. Has no effect if
//	 * an identical listener is already registered. After completion of this
//	 * method, the given listener will be registered for exactly the specified
//	 * events. If they were previously registered for other events, they will be
//	 * deregistered.
//	 * <p>
//	 * Once registered, a listener starts receiving notification of changes to
//	 * elements in the model. The listener continues to receive notifications
//	 * until it is replaced or removed.
//	 * </p>
//	 * <p>
//	 * Listeners can listen for several types of event as defined in
//	 * <code>ElementChangeEvent</code>. Clients are free to register for any
//	 * number of event types however if they register for more than one, it is
//	 * their responsibility to ensure they correctly handle the case where the
//	 * same element change shows up in multiple notifications. Clients are
//	 * guaranteed to receive only the events for which they are registered.
//	 * </p>
//	 *
//	 * @param listener
//	 *            the listener
//	 * @param eventMask
//	 *            the bit-wise OR of all event types of interest to the listener
//	 * @see IElementChangedListener
//	 * @see ElementChangedEvent
//	 * @see #removeElementChangedListener(IElementChangedListener)
//	 *
//	 */
//	public static void addElementChangedListener(
//			IElementChangedListener listener, int eventMask) {
//		ModelManager.getModelManager().deltaState.addElementChangedListener(
//				listener, eventMask);
//	}
//
//	/**
//	 * Removes the given element changed listener. Has no affect if an identical
//	 * listener is not registered.
//	 *
//	 * @param listener
//	 *            the listener
//	 */
//	public static void removeElementChangedListener(
//			IElementChangedListener listener) {
//		ModelManager.getModelManager().deltaState
//				.removeElementChangedListener(listener);
//	}
//
//	/**
//	 * Adds the given listener for resource change events of the given types to
//	 * the DLTK core. The listener is guaranteed to be notified of the resource
//	 * change event before the DLTK core starts processing the resource change
//	 * event itself.
//	 * <p>
//	 * If an identical listener is already registered, the given event types are
//	 * added to the event types of interest to the listener.
//	 * </p>
//	 * <p>
//	 * Supported event types are:
//	 * <ul>
//	 * <li>{@link IResourceChangeEvent#PRE_BUILD}</li>
//	 * <li>{@link IResourceChangeEvent#POST_BUILD}</li>
//	 * <li>{@link IResourceChangeEvent#POST_CHANGE}</li>
//	 * <li>{@link IResourceChangeEvent#PRE_DELETE}</li>
//	 * <li>{@link IResourceChangeEvent#PRE_CLOSE}</li>
//	 * </ul>
//	 * This list may increase in the future.
//	 * </p>
//	 *
//	 * @param listener
//	 *            the listener
//	 * @param eventMask
//	 *            the bit-wise OR of all event types of interest to the listener
//	 * @see #removePreProcessingResourceChangedListener(IResourceChangeListener)
//	 * @see IResourceChangeEvent
//	 */
//	public static void addPreProcessingResourceChangedListener(
//			IResourceChangeListener listener, int eventMask) {
//		ModelManager.getModelManager().deltaState
//				.addPreResourceChangedListener(listener, eventMask);
//	}
//
//	/**
//	 * Removes the given pre-processing resource changed listener.
//	 * <p>
//	 * Has no affect if an identical listener is not registered.
//	 *
//	 * @param listener
//	 *            the listener
//	 */
//	public static void removePreProcessingResourceChangedListener(
//			IResourceChangeListener listener) {
//		ModelManager.getModelManager().deltaState
//				.removePreResourceChangedListener(listener);
//	}
//
//	/**
//	 * Returns the workspace root default charset encoding.
//	 *
//	 * @return the name of the default charset encoding for workspace root.
//	 * @see IContainer#getDefaultCharset()
//	 * @see ResourcesPlugin#getEncoding()
//	 *
//	 */
//	public static String getEncoding() {
//		// Verify that workspace is not shutting down (see bug
//		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=60687)
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		if (workspace != null) {
//			try {
//				return workspace.getRoot().getDefaultCharset();
//			} catch (CoreException e) {
//				// fails silently and return plugin global encoding if core
//				// exception occurs
//			}
//		}
//		return ResourcesPlugin.getEncoding();
//	}
//
//	/**
//	 * Returns the model element corresponding to the given file, or
//	 * <code>null</code> if unable to associate the given file with a model
//	 * element.
//	 *
//	 * Creating a model element has the side effect of creating and opening all
//	 * of the element's parents if they are not yet open.
//	 *
//	 * @param file
//	 *            the given file
//	 * @return the model element corresponding to the given file, or
//	 *         <code>null</code> if unable to associate the given file with a
//	 *         model element
//	 */
//	public static IModelElement create(IFile file) {
//		return ModelManager.create(file, null/* unknown dltk project */);
//	}
//
//	public static IModelElement create(IResource resource) {
//		return ModelManager.create(resource, null/* unknown dltk project */);
//	}
//
//	/**
//	 * Returns the model.
//	 *
//	 * @param root
//	 *            the given root
//	 * @return the model, or <code>null</code> if the root is null
//	 */
//	public static IScriptModel create(IWorkspaceRoot root) {
//		if (root == null) {
//			return null;
//		}
//		return ModelManager.getModelManager().getModel();
//	}
//
//	/**
//	 * Returns the dylan project corresponding to the given project.
//	 * <p>
//	 * Creating a Dylan Project has the side effect of creating and opening all
//	 * of the project's parents if they are not yet open.
//	 * <p>
//	 * Note that no check is done at this time on the existence or the nature of
//	 * this project.
//	 *
//	 * @param project
//	 *            the given project
//	 * @return the Dylan project corresponding to the given project, null if the
//	 *         given project is null
//	 */
//	public static IScriptProject create(IProject project) {
//		if (project == null) {
//			return null;
//		}
//		Model model = ModelManager.getModelManager().getModel();
//		return model.getScriptProject(project);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind <code>BPE_SOURCE</code>
//	 * for all files in the project's source folder identified by the given
//	 * absolute workspace-relative path.
//	 * <p>
//	 * The convenience method is fully equivalent to:
//	 *
//	 * <pre>
//	 * newSourceEntry(path, new IPath[] {}, new IPath[] {});
//	 * </pre>
//	 *
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute workspace-relative path of a source folder
//	 * @return a new source buildpath entry
//	 * @see #newSourceEntry(IPath, IPath[], IPath[])
//	 */
//	public static IBuildpathEntry newSourceEntry(IPath path) {
//
//		return newSourceEntry(path, BuildpathEntry.INCLUDE_ALL,
//				BuildpathEntry.EXCLUDE_NONE);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind <code>BPE_SOURCE</code>
//	 * for the project's source folder identified by the given absolute
//	 * workspace-relative path but excluding all source files with paths
//	 * matching any of the given patterns.
//	 * <p>
//	 * The convenience method is fully equivalent to:
//	 *
//	 * <pre>
//	 * newSourceEntry(path, new IPath[] {}, exclusionPatterns);
//	 * </pre>
//	 *
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute workspace-relative path of a source folder
//	 * @param exclusionPatterns
//	 *            the possibly empty list of exclusion patterns represented as
//	 *            relative paths
//	 * @return a new source buildpath entry
//	 * @see #newSourceEntry(IPath, IPath[], IPath[], IPath)
//	 *
//	 */
//	public static IBuildpathEntry newSourceEntry(IPath path,
//			IPath[] exclusionPatterns) {
//
//		return newSourceEntry(path, BuildpathEntry.INCLUDE_ALL,
//				exclusionPatterns);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind <code>CPE_SOURCE</code>
//	 * for the project's source folder identified by the given absolute
//	 * workspace-relative path but excluding all source files with paths
//	 * matching any of the given patterns, and associated with a specific output
//	 * location (that is, ".class" files are not going to the project default
//	 * output location).
//	 * <p>
//	 * The convenience method is fully equivalent to:
//	 *
//	 * <pre>
//	 * newSourceEntry(path, new IPath[] {}, exclusionPatterns, specificOutputLocation,
//	 * 		new IBuildpathAttribute[] {});
//	 * </pre>
//	 *
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute workspace-relative path of a source folder
//	 * @param inclusionPatterns
//	 *            the possibly empty list of inclusion patterns represented as
//	 *            relative paths
//	 * @param exclusionPatterns
//	 *            the possibly empty list of exclusion patterns represented as
//	 *            relative paths
//	 * @param specificOutputLocation
//	 *            the specific output location for this source entry (
//	 *            <code>null</code> if using project default ouput location)
//	 * @return a new source buildpath entry
//	 * @see #newSourceEntry(IPath, IPath[], IPath[], IPath,
//	 *      IBuildpathAttribute[])
//	 *
//	 */
//	public static IBuildpathEntry newSourceEntry(IPath path,
//			IPath[] inclusionPatterns, IPath[] exclusionPatterns) {
//		return newSourceEntry(path, inclusionPatterns, exclusionPatterns,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind <code>CPE_SOURCE</code>
//	 * for the project's source folder identified by the given absolute
//	 * workspace-relative path using the given inclusion and exclusion patterns
//	 * to determine which source files are included, and the given output path
//	 * to control the output location of generated files.
//	 * <p>
//	 * The source folder is referred to using an absolute path relative to the
//	 * workspace root, e.g. <code>/Project/src</code>. A project's source
//	 * folders are located with that project. That is, a source buildpath entry
//	 * specifying the path <code>/P1/src</code> is only usable for project
//	 * <code>P1</code>.
//	 * </p>
//	 * <p>
//	 * The inclusion patterns determines the initial set of source files that
//	 * are to be included; the exclusion patterns are then used to reduce this
//	 * set. When no inclusion patterns are specified, the initial file set
//	 * includes all relevent files in the resource tree rooted at the source
//	 * entry's path. On the other hand, specifying one or more inclusion
//	 * patterns means that all <b>and only</b> files matching at least one of
//	 * the specified patterns are to be included. If exclusion patterns are
//	 * specified, the initial set of files is then reduced by eliminating files
//	 * matched by at least one of the exclusion patterns. Inclusion and
//	 * exclusion patterns look like relative file paths with wildcards and are
//	 * interpreted relative to the source entry's path. File patterns are
//	 * case-sensitive can contain '**', '*' or '?' wildcards (see
//	 * {@link IBuildpathEntry#getExclusionPatterns()} for the full description
//	 * of their syntax and semantics). The resulting set of files are included
//	 * in the corresponding package fragment root; all package fragments within
//	 * the root will have children of type <code>ISourceModule</code>.
//	 * </p>
//	 * <p>
//	 * For example, if the source folder path is <code>/Project/src</code>,
//	 * there are no inclusion filters, and the exclusion pattern is
//	 * <code>com/xyz/tests/&#42;&#42;</code>, then source files like
//	 * <code>/Project/src/com/xyz/Foo.java</code> and
//	 * <code>/Project/src/com/xyz/utils/Bar.java</code> would be included,
//	 * whereas <code>/Project/src/com/xyz/tests/T1.java</code> and
//	 * <code>/Project/src/com/xyz/tests/quick/T2.java</code> would be excluded.
//	 * </p>
//	 * <p>
//	 * Additionally, a source entry can be associated with a specific output
//	 * location. By doing so, the script builder will ensure that the generated
//	 * ".class" files will be issued inside this output location, as opposed to
//	 * be generated into the project default output location (when output
//	 * location is <code>null</code>). Note that multiple source entries may
//	 * target the same output location. The output location is referred to using
//	 * an absolute path relative to the workspace root, e.g.
//	 * <code>"/Project/bin"</code>, it must be located inside the same project
//	 * as the source folder.
//	 * </p>
//	 * <p>
//	 * Also note that all sources/binaries inside a project are contributed as a
//	 * whole through a project entry (see <code>DLTKCore.newProjectEntry</code>
//	 * ). Particular source entries cannot be selectively exported.
//	 * </p>
//	 * <p>
//	 * The <code>extraAttributes</code> list contains name/value pairs that must
//	 * be persisted with this entry. If no extra attributes are provided, an
//	 * empty array must be passed in.<br>
//	 * Note that this list should not contain any duplicate name.
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute workspace-relative path of a source folder
//	 * @param inclusionPatterns
//	 *            the possibly empty list of inclusion patterns represented as
//	 *            relative paths
//	 * @param exclusionPatterns
//	 *            the possibly empty list of exclusion patterns represented as
//	 *            relative paths
//	 * @param specificOutputLocation
//	 *            the specific output location for this source entry (
//	 *            <code>null</code> if using project default ouput location)
//	 * @param extraAttributes
//	 *            the possibly empty list of extra attributes to persist with
//	 *            this entry
//	 * @return a new source buildpath entry with the given exclusion patterns
//	 * @see IBuildpoathEntry#getInclusionPatterns()
//	 * @see IBuildpoathEntry#getExclusionPatterns()
//	 * @see IBuildpoathEntry#getOutputLocation()
//	 *
//	 */
//	public static IBuildpathEntry newSourceEntry(IPath path,
//			IPath[] inclusionPatterns, IPath[] exclusionPatterns,
//			IBuildpathAttribute[] extraAttributes) {
//
//		Assert.isNotNull(path, "Source path cannot be null"); //$NON-NLS-1$
//		if (!path.isAbsolute())
//			Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$
//		if (exclusionPatterns == null)
//			Assert.isTrue(false, "Exclusion pattern set cannot be null"); //$NON-NLS-1$
//		if (inclusionPatterns == null)
//			Assert.isTrue(false, "Inclusion pattern set cannot be null"); //$NON-NLS-1$
//
//		return new BuildpathEntry(IProjectFragment.K_SOURCE,
//				IBuildpathEntry.BPE_SOURCE, path, false, inclusionPatterns,
//				exclusionPatterns, null, false, // no access rules to combine
//				extraAttributes, false);
//	}
//
//	/**
//	 * Creates and returns a new non-exported buildpath entry of kind
//	 * <code>BPE_PROJECT</code> for the project identified by the given absolute
//	 * path. This method is fully equivalent to calling
//	 * {@link #newProjectEntry(IPath, boolean, boolean) newProjectEntry(path,
//	 * true, false)}.
//	 *
//	 * @param path
//	 *            the absolute path of the binary archive
//	 * @return a new project buildpath entry
//	 */
//	public static IBuildpathEntry newProjectEntry(IPath path) {
//		return newProjectEntry(path, false);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_PROJECT</code> for the project identified by the given absolute
//	 * path. This method is fully equivalent to calling
//	 * {@link #newProjectEntry(IPath, IAccessRule[], boolean, IBuildpathAttribute[], boolean)
//	 * newProjectEntry(path, new IAccessRule[0], true, new
//	 * IBuildpathAttribute[0], isExported)}.
//	 *
//	 * @param path
//	 *            the absolute path of the prerequisite project
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @return a new project buildpath entry
//	 *
//	 */
//	public static IBuildpathEntry newProjectEntry(IPath path, boolean isExported) {
//
//		if (!path.isAbsolute())
//			Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$
//
//		return newProjectEntry(path, BuildpathEntry.NO_ACCESS_RULES, true,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, isExported);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_PROJECT</code> for the project identified by the given absolute
//	 * path.
//	 * <p>
//	 * A project entry is used to denote a prerequisite project on a buildpath.
//	 * The referenced project will be contributed as a whole, either as sources
//	 * (in the script Model, it contributes all its package fragment roots) or
//	 * as binaries (when building, it contributes its whole output location).
//	 * </p>
//	 * <p>
//	 * A project reference allows to indirect through another project,
//	 * independently from its internal layout.
//	 * </p>
//	 * <p>
//	 * The prerequisite project is referred to using an absolute path relative
//	 * to the workspace root.
//	 * </p>
//	 * <p>
//	 * The access rules determine the set of accessible source files in the
//	 * project. If the list of access rules is empty then all files in this
//	 * project are accessible. See {@link IAccessRule} for a detailed
//	 * description of access rules.
//	 * </p>
//	 * <p>
//	 * The <code>combineAccessRules</code> flag indicates whether access rules
//	 * of one (or more) exported entry of the project should be combined with
//	 * the given access rules. If they should be combined, the given access
//	 * rules are considered first, then the entry's access rules are considered.
//	 * </p>
//	 * <p>
//	 * The <code>extraAttributes</code> list contains name/value pairs that must
//	 * be persisted with this entry. If no extra attributes are provided, an
//	 * empty array must be passed in.<br>
//	 * Note that this list should not contain any duplicate name.
//	 * </p>
//	 * <p>
//	 * The <code>isExported</code> flag indicates whether this entry is
//	 * contributed to dependent projects. If not exported, dependent projects
//	 * will not see any of the classes from this entry. If exported, dependent
//	 * projects will concatenate the accessible files patterns of this entry
//	 * with the accessible files patterns of the projects, and they will
//	 * concatenate the non accessible files patterns of this entry with the non
//	 * accessible files patterns of the project.
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute path of the prerequisite project
//	 * @param accessRules
//	 *            the possibly empty list of access rules for this entry
//	 * @param combineAccessRules
//	 *            whether the access rules of the project's exported entries
//	 *            should be combined with the given access rules
//	 * @param extraAttributes
//	 *            the possibly empty list of extra attributes to persist with
//	 *            this entry
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @return a new project buildpath entry
//	 *
//	 */
//	public static IBuildpathEntry newProjectEntry(IPath path,
//			IAccessRule[] accessRules, boolean combineAccessRules,
//			IBuildpathAttribute[] extraAttributes, boolean isExported) {
//
//		if (!path.isAbsolute())
//			Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$
//
//		return new BuildpathEntry(IProjectFragment.K_SOURCE,
//				IBuildpathEntry.BPE_PROJECT, path, isExported,
//				BuildpathEntry.INCLUDE_ALL, // inclusion patterns
//				BuildpathEntry.EXCLUDE_NONE, // exclusion patterns
//				accessRules, combineAccessRules, extraAttributes, false);
//	}
//
//	/**
//	 * Creates and returns a new access rule with the given file pattern and
//	 * kind.
//	 * <p>
//	 * The rule kind is one of {@link IAccessRule#K_ACCESSIBLE},
//	 * {@link IAccessRule#K_DISCOURAGED}, or
//	 * {@link IAccessRule#K_NON_ACCESSIBLE}, optionally combined with
//	 * {@link IAccessRule#IGNORE_IF_BETTER}, e..g.
//	 * <code>IAccessRule.K_NON_ACCESSIBLE | IAccessRule.IGNORE_IF_BETTER</code>.
//	 * </p>
//	 *
//	 * @param filePattern
//	 *            the file pattern this access rule should match
//	 * @param kind
//	 *            one of {@link IAccessRule#K_ACCESSIBLE},
//	 *            {@link IAccessRule#K_DISCOURAGED}, or
//	 *            {@link IAccessRule#K_NON_ACCESSIBLE}, optionally combined with
//	 *            {@link IAccessRule#IGNORE_IF_BETTER}
//	 * @return a new access rule
//	 *
//	 */
//	public static IAccessRule newAccessRule(IPath filePattern, int kind) {
//		return new BuildpathAccessRule(filePattern, kind);
//	}
//
//	/**
//	 * Creates and returns a new buildpath attribute with the given name and the
//	 * given value.
//	 *
//	 * @return a new buildpath attribute
//	 *
//	 */
//	public static IBuildpathAttribute newBuildpathAttribute(String name,
//			String value) {
//		return new BuildpathAttribute(name, value);
//	}
//
//	/**
//	 * Creates and returns a new non-exported buildpath entry of kind
//	 * <code>CPE_VARIABLE</code> for the given path. This method is fully
//	 * equivalent to calling
//	 * {@link #newVariableEntry(IPath, IPath, IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newVariableEntry(variablePath, variableSourceAttachmentPath,
//	 * sourceAttachmentRootPath, new IAccessRule[0], new IBuildpathAttribute[0],
//	 * false)}.
//	 *
//	 * @param variablePath
//	 *            the path of the binary archive; first segment is the name of a
//	 *            buildpath variable
//	 * @param variableSourceAttachmentPath
//	 *            the path of the corresponding source archive, or
//	 *            <code>null</code> if none; if present, the first segment is
//	 *            the name of a buildpath variable (not necessarily the same
//	 *            variable as the one that begins <code>variablePath</code>)
//	 * @param sourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or <code>null</code> if
//	 *            <code>variableSourceAttachmentPath</code> is also
//	 *            <code>null</code>
//	 * @return a new library buildpath entry
//	 */
//	public static IBuildpathEntry newVariableEntry(IPath variablePath /*
//																	 * ,IPath
//																	 * variableSourceAttachmentPath
//																	 * , IPath
//																	 * sourceAttachmentRootPath
//																	 */) {
//
//		return newVariableEntry(variablePath, /*
//											 * variableSourceAttachmentPath,
//											 * sourceAttachmentRootPath,
//											 */false);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_VARIABLE</code> for the given path. This method is fully
//	 * equivalent to calling
//	 * {@link #newVariableEntry(IPath, IPath, IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newVariableEntry(variablePath, variableSourceAttachmentPath,
//	 * sourceAttachmentRootPath, new IAccessRule[0], new IBuildpathAttribute[0],
//	 * isExported)}.
//	 *
//	 * @param variablePath
//	 *            the path of the binary archive; first segment is the name of a
//	 *            buildpath variable
//	 * @param variableSourceAttachmentPath
//	 *            the path of the corresponding source archive, or
//	 *            <code>null</code> if none; if present, the first segment is
//	 *            the name of a buildpath variable (not necessarily the same
//	 *            variable as the one that begins <code>variablePath</code>)
//	 * @param variableSourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or <code>null</code> if
//	 *            <code>variableSourceAttachmentPath</code> is also
//	 *            <code>null</code>
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @return a new variable buildpath entry
//	 * @since 2.0
//	 */
//	public static IBuildpathEntry newVariableEntry(IPath variablePath,
//	/*
//	 * IPath variableSourceAttachmentPath, IPath
//	 * variableSourceAttachmentRootPath,
//	 */boolean isExported) {
//
//		return newVariableEntry(variablePath, /*
//											 * variableSourceAttachmentPath,
//											 * variableSourceAttachmentRootPath,
//											 */
//				BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, isExported);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_VARIABLE</code> for the given path. The first segment of the
//	 * path is the name of a buildpath variable. The trailing segments of the
//	 * path will be appended to resolved variable path.
//	 * <p>
//	 * A variable entry allows to express indirect references on a buildpath to
//	 * other projects or libraries, depending on what the buildpath variable is
//	 * referring.
//	 * <p>
//	 * It is possible to register an automatic initializer (
//	 * <code>BuildpathVariableInitializer</code>), which will be invoked through
//	 * the extension point "org.eclipse.dltk.core.buildpathVariableInitializer".
//	 * After resolution, a buildpath variable entry may either correspond to a
//	 * project or a library entry.
//	 * <p>
//	 * e.g. Here are some examples of variable path usage
//	 * <ul>
//	 * <li>"JDTCORE" where variable <code>JDTCORE</code> is bound to
//	 * "c:/jars/dltkcore.jar". The resolved buildpath entry is denoting the
//	 * library "c:\jars\dltkcore.jar"</li>
//	 * <li>"JDTCORE" where variable <code>JDTCORE</code> is bound to
//	 * "/Project_JDTCORE". The resolved buildpath entry is denoting the project
//	 * "/Project_JDTCORE"</li>
//	 * <li>"PLUGINS/com.example/example.jar" where variable <code>PLUGINS</code>
//	 * is bound to "c:/eclipse/plugins". The resolved buildpath entry is
//	 * denoting the library "c:\eclipse\plugins\com.example\example.jar"</li>
//	 * </ul>
//	 * <p>
//	 * The access rules determine the set of accessible class files in the
//	 * project or library. If the list of access rules is empty then all files
//	 * in this project or library are accessible. See {@link IAccessRule} for a
//	 * detailed description of access rules.
//	 * </p>
//	 * <p>
//	 * The <code>extraAttributes</code> list contains name/value pairs that must
//	 * be persisted with this entry. If no extra attributes are provided, an
//	 * empty array must be passed in.<br>
//	 * Note that this list should not contain any duplicate name.
//	 * </p>
//	 * <p>
//	 * The <code>isExported</code> flag indicates whether this entry is
//	 * contributed to dependent projects. If not exported, dependent projects
//	 * will not see any of the classes from this entry. If exported, dependent
//	 * projects will concatenate the accessible files patterns of this entry
//	 * with the accessible files patterns of the projects, and they will
//	 * concatenate the non accessible files patterns of this entry with the non
//	 * accessible files patterns of the project.
//	 * </p>
//	 * <p>
//	 * Note that this operation does not attempt to validate buildpath variables
//	 * or access the resources at the given paths.
//	 * </p>
//	 *
//	 * @param variablePath
//	 *            the path of the binary archive; first segment is the name of a
//	 *            buildpath variable
//	 * @param variableSourceAttachmentPath
//	 *            the path of the corresponding source archive, or
//	 *            <code>null</code> if none; if present, the first segment is
//	 *            the name of a buildpath variable (not necessarily the same
//	 *            variable as the one that begins <code>variablePath</code>)
//	 * @param variableSourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or <code>null</code> if
//	 *            <code>variableSourceAttachmentPath</code> is also
//	 *            <code>null</code>
//	 * @param accessRules
//	 *            the possibly empty list of access rules for this entry
//	 * @param extraAttributes
//	 *            the possibly empty list of extra attributes to persist with
//	 *            this entry
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @return a new variable buildpath entry
//	 * @since 3.1
//	 */
//	public static IBuildpathEntry newVariableEntry(IPath variablePath,
//	/*
//	 * IPath variableSourceAttachmentPath, IPath
//	 * variableSourceAttachmentRootPath,
//	 */IAccessRule[] accessRules, IBuildpathAttribute[] extraAttributes,
//			boolean isExported) {
//
//		if (variablePath == null)
//			Assert.isTrue(false, "Variable path cannot be null"); //$NON-NLS-1$
//		if (variablePath.segmentCount() < 1) {
//			Assert.isTrue(
//					false,
//					"Illegal buildpath variable path: \'" + variablePath.makeRelative().toString() + "\', must have at least one segment"); //$NON-NLS-1$//$NON-NLS-2$
//		}
//
//		return new BuildpathEntry(IProjectFragment.K_SOURCE,
//				IBuildpathEntry.BPE_VARIABLE, variablePath, isExported,
//				BuildpathEntry.INCLUDE_ALL, // inclusion patterns
//				BuildpathEntry.EXCLUDE_NONE, // exclusion patterns
//				accessRules, true, // comsbine access rules
//				extraAttributes, false);
//	}
//
//	/**
//	 * Removed the given buildpath variable. Does nothing if no value was set
//	 * for this buildpath variable.
//	 * <p>
//	 * This functionality cannot be used while the resource tree is locked.
//	 * <p>
//	 * Buildpath variable values are persisted locally to the workspace, and are
//	 * preserved from session to session.
//	 * <p>
//	 *
//	 * @param variableName
//	 *            the name of the buildpath variable
//	 * @param monitor
//	 *            the progress monitor to report progress
//	 * @see #setBuildpathVariable(String, IPath)
//	 */
//	public static void removeBuildpathVariable(String variableName,
//			IProgressMonitor monitor) {
//		try {
//			SetVariablesOperation operation = new SetVariablesOperation(
//					new String[] { variableName }, new IPath[] { null }, true/*
//																			 * update
//																			 * preferences
//																			 */);
//			operation.runOperation(monitor);
//		} catch (ModelException e) {
//			Util.log(e, "Exception while removing variable " + variableName); //$NON-NLS-1$
//		}
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_CONTAINER</code> for the given path. This method is fully
//	 * equivalent to calling
//	 * {@link #newContainerEntry(IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newContainerEntry(containerPath, new IAccessRule[0], new
//	 * IBuildpathAttribute[0], false)}.
//	 * <p>
//	 *
//	 * @param containerPath
//	 *            the path identifying the container, it must be formed of two
//	 *            segments
//	 * @return a new container buildpath entry
//	 *
//	 * @see DLTKCore#getBuildpathContainer(IPath, IScriptProject)
//	 *
//	 */
//	public static IBuildpathEntry newContainerEntry(IPath containerPath) {
//		return newContainerEntry(containerPath, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, false/* not exported */);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_CONTAINER</code> for the given path. This method is fully
//	 * equivalent to calling
//	 * {@link #newContainerEntry(IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newContainerEntry(containerPath, new IAccessRule[0], new
//	 * IBuildpathAttribute[0], isExported)}.
//	 *
//	 * @param containerPath
//	 *            the path identifying the container, it must be formed of at
//	 *            least one segment (ID+hints)
//	 * @param isExported
//	 *            a boolean indicating whether this entry is contributed to
//	 *            dependent projects in addition to the output location
//	 * @return a new container buildpath entry
//	 *
//	 * @see DLTKCore#getBuildpathContainer(IPath, IScriptProject)
//	 * @see DLTKCore#setBuildpathContainer(IPath, IScriptProject[],
//	 *      IBuildpathContainer[], IProgressMonitor)
//	 *
//	 */
//	public static IBuildpathEntry newContainerEntry(IPath containerPath,
//			boolean isExported) {
//		return newContainerEntry(containerPath, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, isExported);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_CONTAINER</code> for the given path. The path of the container
//	 * will be used during resolution so as to map this container entry to a set
//	 * of other buildpath entries the container is acting for.
//	 * <p>
//	 * A container entry allows to express indirect references to a set of
//	 * libraries, projects and variable entries, which can be interpreted
//	 * differently for each script project where it is used. A buildpath
//	 * container entry can be resolved using
//	 * <code>DLTKCore.getResolvedBuildpathContainer</code>, and updated with
//	 * <code>DLTKCore.buildpathContainerChanged</code>
//	 * <p>
//	 * A container is exclusively resolved by a
//	 * <code>BuildpathContainerInitializer</code> registered onto the extension
//	 * point "org.eclipse.dltk.core.buildpathContainerInitializer".
//	 * <p>
//	 * A container path must be formed of at least one segment, where:
//	 * <ul>
//	 * <li>the first segment is a unique ID identifying the target container,
//	 * there must be a container initializer registered onto this ID through the
//	 * extension point "org.eclipse.dltk.core.buildpathContainerInitializer".</li>
//	 * <li>the remaining segments will be passed onto the initializer, and can
//	 * be used as additional hints during the initialization phase.</li>
//	 * </ul>
//	 * The access rules determine the set of accessible source and source files
//	 * in the container. If the list of access rules is empty, then all files in
//	 * this container are accessible. See {@link IAccessRule} for a detailed
//	 * description of access rules. Note that if an entry defined by the
//	 * container defines access rules, then these access rules are combined with
//	 * the given access rules. The given access rules are considered first, then
//	 * the entry's access rules are considered.
//	 * </p>
//	 * <p>
//	 * The <code>extraAttributes</code> list contains name/value pairs that must
//	 * be persisted with this entry. If no extra attributes are provided, an
//	 * empty array must be passed in.<br>
//	 * Note that this list should not contain any duplicate name.
//	 * </p>
//	 * <p>
//	 * The <code>isExported</code> flag indicates whether this entry is
//	 * contributed to dependent projects. If not exported, dependent projects
//	 * will not see any of the classes from this entry. If exported, dependent
//	 * projects will concatenate the accessible files patterns of this entry
//	 * with the accessible files patterns of the projects, and they will
//	 * concatenate the non accessible files patterns of this entry with the non
//	 * accessible files patterns of the project.
//	 * </p>
//	 * <p>
//	 * Note that this operation does not attempt to validate buildpath
//	 * containers or access the resources at the given paths.
//	 * </p>
//	 *
//	 * @param containerPath
//	 *            the path identifying the container, it must be formed of at
//	 *            least one segment (ID+hints)
//	 * @param accessRules
//	 *            the possibly empty list of access rules for this entry
//	 * @param extraAttributes
//	 *            the possibly empty list of extra attributes to persist with
//	 *            this entry
//	 * @param isExported
//	 *            a boolean indicating whether this entry is contributed to
//	 *            dependent projects in addition to the output location
//	 * @return a new container buildpath entry
//	 *
//	 * @see DLTKCore#getBuildpathContainer(IPath, IScriptProject)
//	 * @see DLTKCore#setBuildpathContainer(IPath, IScriptProject[],
//	 *      IBuildpathContainer[], IProgressMonitor)
//	 * @see DLTKCore#newContainerEntry(IPath, boolean)
//	 * @see DLTKCore#newAccessRule(IPath, int)
//	 *
//	 */
//	public static IBuildpathEntry newContainerEntry(IPath containerPath,
//			IAccessRule[] accessRules, IBuildpathAttribute[] extraAttributes,
//			boolean isExported) {
//
//		if (containerPath == null) {
//			Assert.isTrue(false, "Container path cannot be null"); //$NON-NLS-1$
//		} else if (containerPath.segmentCount() < 1) {
//			Assert.isTrue(
//					false,
//					"Illegal buildpath container path: \'" + containerPath.makeRelative().toString() + "\', must have at least one segment (containerID+hints)"); //$NON-NLS-1$//$NON-NLS-2$
//		}
//		BuildpathEntry entry = new BuildpathEntry(IProjectFragment.K_SOURCE,
//				IBuildpathEntry.BPE_CONTAINER, containerPath, isExported,
//				BuildpathEntry.INCLUDE_ALL, // inclusion patterns
//				BuildpathEntry.EXCLUDE_NONE, // exclusion patterns
//				accessRules, true, // comsbine access rules
//				extraAttributes, false);
//
//		return entry;
//	}
//
//	/**
//	 * Helper method for returning one option value only. Equivalent to
//	 * <code>(String)ScriptCore.getOptions().get(optionName)</code> Note that it
//	 * may answer <code>null</code> if this option does not exist.
//	 * <p>
//	 * For a complete description of the configurable options, see
//	 * <code>getDefaultOptions</code>.
//	 * </p>
//	 *
//	 * @param optionName
//	 *            the name of an option
//	 * @return the String value of a given option
//	 * @see ScriptCore#getDefaultOptions()
//	 */
//	public static String getOption(String optionName) {
//		return ModelManager.getModelManager().getOption(optionName);
//	}
//
//	/**
//	 * Returns the table of the current options. Initially, all options have
//	 * their default values, and this method returns a table that includes all
//	 * known options.
//	 * <p>
//	 * For a complete description of the configurable options, see
//	 * <code>getDefaultOptions</code>.
//	 * </p>
//	 * <p>
//	 * Returns a default set of options even if the platform is not running.
//	 * </p>
//	 *
//	 * @return table of current settings of all options (key type:
//	 *         <code>String</code>; value type: <code>String</code>)
//	 * @see #getDefaultOptions()
//	 * @see DLTKCorePreferenceInitializer for changing default settings
//	 */
//	public static Hashtable<String, String> getOptions() {
//		return ModelManager.getModelManager().getOptions();
//	}
//
//	/**
//	 * This method is called upon plug-in activation
//	 */
//	@Override
//	public void start(BundleContext context) throws Exception {
//		super.start(context);
//		ModelManager.getModelManager().startup();
//	}
//
//	/**
//	 * This method is called when the plug-in is stopped
//	 */
//	@Override
//	public void stop(BundleContext context) throws Exception {
//		try {
//			ModelManager.getModelManager().shutdown();
//		} finally {
//			super.stop(context);
//			plugin = null;
//		}
//	}
//
//	/**
//	 * Returns the shared instance.
//	 */
//	public static DLTKCore getDefault() {
//		return plugin;
//	}
//
//	/**
//	 * Runs the given action as an atomic script model operation.
//	 * <p>
//	 * After running a method that modifies elements, registered listeners
//	 * receive after-the-fact notification of what just transpired, in the form
//	 * of a element changed event. This method allows clients to call a number
//	 * of methods that modify elements and only have element changed event
//	 * notifications reported at the end of the entire batch.
//	 * </p>
//	 * <p>
//	 * If this method is called outside the dynamic scope of another such call,
//	 * this method runs the action and then reports a single element changed
//	 * event describing the net effect of all changes done to elements by the
//	 * action.
//	 * </p>
//	 * <p>
//	 * If this method is called in the dynamic scope of another such call, this
//	 * method simply runs the action.
//	 * </p>
//	 *
//	 * @param action
//	 *            the action to perform
//	 * @param monitor
//	 *            a progress monitor, or <code>null</code> if progress reporting
//	 *            and cancellation are not desired
//	 * @exception CoreException
//	 *                if the operation failed.
//	 *
//	 */
//	public static void run(IWorkspaceRunnable action, IProgressMonitor monitor)
//			throws CoreException {
//		run(action, ResourcesPlugin.getWorkspace().getRoot(), monitor);
//	}
//
//	/**
//	 * Runs the given action as an atomic script model operation.
//	 * <p>
//	 * After running a method that modifies elements, registered listeners
//	 * receive after-the-fact notification of what just transpired, in the form
//	 * of a element changed event. This method allows clients to call a number
//	 * of methods that modify elements and only have element changed event
//	 * notifications reported at the end of the entire batch.
//	 * </p>
//	 * <p>
//	 * If this method is called outside the dynamic scope of another such call,
//	 * this method runs the action and then reports a single element changed
//	 * event describing the net effect of all changes done to elements by the
//	 * action.
//	 * </p>
//	 * <p>
//	 * If this method is called in the dynamic scope of another such call, this
//	 * method simply runs the action.
//	 * </p>
//	 * <p>
//	 * The supplied scheduling rule is used to determine whether this operation
//	 * can be run simultaneously with workspace changes in other threads. See
//	 * <code>IWorkspace.run(...)</code> for more details.
//	 * </p>
//	 *
//	 * @param action
//	 *            the action to perform
//	 * @param rule
//	 *            the scheduling rule to use when running this operation, or
//	 *            <code>null</code> if there are no scheduling restrictions for
//	 *            this operation.
//	 * @param monitor
//	 *            a progress monitor, or <code>null</code> if progress reporting
//	 *            and cancellation are not desired
//	 * @exception CoreException
//	 *                if the operation failed.
//	 *
//	 */
//	public static void run(IWorkspaceRunnable action, ISchedulingRule rule,
//			IProgressMonitor monitor) throws CoreException {
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		if (workspace.isTreeLocked()) {
//			new BatchOperation(action).run(monitor);
//		} else {
//			// use IWorkspace.run(...) to ensure that a build will be done in
//			// autobuild mode
//			workspace.run(new BatchOperation(action), rule,
//					IWorkspace.AVOID_UPDATE, monitor);
//		}
//	}
//
//	/**
//	 * Helper method finding the buildpath variable initializer registered for a
//	 * given buildpath variable name or <code>null</code> if none was found
//	 * while iterating over the contributions to extension point to the
//	 * extension point "org.eclipse.dltk.core.buildpathVariableInitializer".
//	 * <p>
//	 *
//	 * @param variable
//	 *            the given variable
//	 * @return BuildpathVariableInitializer - the registered buildpath variable
//	 *         initializer or <code>null</code> if none was found.
//	 * @since 2.1
//	 */
//	public static BuildpathVariableInitializer getBuildpathVariableInitializer(
//			String variable) {
//
//		Plugin dltkCorePlugin = DLTKCore.getPlugin();
//		if (dltkCorePlugin == null)
//			return null;
//
//		IExtensionPoint extension = Platform.getExtensionRegistry()
//				.getExtensionPoint(DLTKCore.PLUGIN_ID,
//						ModelManager.BPVARIABLE_INITIALIZER_EXTPOINT_ID);
//		if (extension != null) {
//			IExtension[] extensions = extension.getExtensions();
//			for (int i = 0; i < extensions.length; i++) {
//				IConfigurationElement[] configElements = extensions[i]
//						.getConfigurationElements();
//				for (int j = 0; j < configElements.length; j++) {
//					IConfigurationElement configElement = configElements[j];
//					try {
//						String varAttribute = configElement
//								.getAttribute("variable"); //$NON-NLS-1$
//						if (variable.equals(varAttribute)) {
//							Object execExt = configElement
//									.createExecutableExtension("class"); //$NON-NLS-1$
//							if (execExt instanceof BuildpathVariableInitializer) {
//								BuildpathVariableInitializer initializer = (BuildpathVariableInitializer) execExt;
//								String readOnlyAttribute = configElement
//										.getAttribute("readOnly"); //$NON-NLS-1$
//								if (ModelManager.TRUE.equals(readOnlyAttribute)) {
//									ModelManager.getModelManager().readOnlyVariables
//											.add(variable);
//								}
//								return initializer;
//							}
//						}
//					} catch (CoreException e) {
//						// executable extension could not be created: ignore
//						// this initializer
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * Returns the path held in the given buildpath variable. Returns
//	 * <code>null</code> if unable to bind.
//	 * <p>
//	 * Buildpath variable values are persisted locally to the workspace, and are
//	 * preserved from session to session.
//	 * <p>
//	 * Note that buildpath variables can be contributed registered initializers
//	 * for, using the extension point
//	 * "org.eclipse.dltk.core.buildpathVariableInitializer". If an initializer
//	 * is registered for a variable, its persisted value will be ignored: its
//	 * initializer will thus get the opportunity to rebind the variable
//	 * differently on each session.
//	 *
//	 * @param variableName
//	 *            the name of the buildpath variable
//	 * @return the path, or <code>null</code> if none
//	 * @see #setBuildpathVariable(String, IPath)
//	 */
//	public static IPath getBuildpathVariable(final String variableName) {
//
//		ModelManager manager = ModelManager.getModelManager();
//		IPath variablePath = manager.variableGet(variableName);
//		if (variablePath == ModelManager.VARIABLE_INITIALIZATION_IN_PROGRESS) {
//			return manager.getPreviousSessionVariable(variableName);
//		}
//
//		if (variablePath != null) {
//			if (variablePath == ModelManager.BP_ENTRY_IGNORE_PATH)
//				return null;
//			return variablePath;
//		}
//
//		// even if persisted value exists, initializer is given priority, only
//		// if no initializer is found the persisted value is reused
//		final BuildpathVariableInitializer initializer = DLTKCore
//				.getBuildpathVariableInitializer(variableName);
//		if (initializer != null) {
//			manager.variablePut(variableName,
//					ModelManager.VARIABLE_INITIALIZATION_IN_PROGRESS); // avoid
//			// initialization
//			// cycles
//			boolean ok = false;
//			try {
//				// let OperationCanceledException go through
//				// (see https://bugs.eclipse.org/bugs/show_bug.cgi?id=59363)
//				initializer.initialize(variableName);
//
//				variablePath = manager.variableGet(variableName); // initializer
//				// should
//				// have
//				// performed
//				// side
//				// -effect
//				if (variablePath == ModelManager.VARIABLE_INITIALIZATION_IN_PROGRESS)
//					return null; // break cycle (initializer did not init or
//				// reentering call)
//				manager.variablesWithInitializer.add(variableName);
//				ok = true;
//			} catch (RuntimeException e) {
//				if (ModelManager.BP_RESOLVE_VERBOSE
//				/* || ModelManager.BP_RESOLVE_VERBOSE_FAILURE */)
//					e.printStackTrace();
//				throw e;
//			} catch (Error e) {
//				if (ModelManager.BP_RESOLVE_VERBOSE
//				/* || ModelManager.BP_RESOLVE_VERBOSE_FAILURE */)
//					e.printStackTrace();
//				throw e;
//			} finally {
//				if (!ok)
//					ModelManager.getModelManager().variablePut(variableName,
//							null); // flush cache
//			}
//		} else {
//			// TODO: add verbose method for initializer not found
//		}
//		return variablePath;
//	}
//
//	/**
//	 * This is a helper method, which returns the resolved buildpath entry
//	 * denoted by a given entry (if it is a variable entry). It is obtained by
//	 * resolving the variable reference in the first segment. Returns
//	 * <code>null</code> if unable to resolve using the following algorithm:
//	 * <ul>
//	 * <li>if variable segment cannot be resolved, returns <code>null</code></li>
//	 * <li>finds a project, JAR or binary folder in the workspace at the
//	 * resolved path location</li>
//	 * <li>if none finds an external JAR file or folder outside the workspace at
//	 * the resolved path location</li>
//	 * <li>if none returns <code>null</code></li>
//	 * </ul>
//	 * <p>
//	 * Variable source attachment path and root path are also resolved and
//	 * recorded in the resulting buildpath entry.
//	 * <p>
//	 * NOTE: This helper method does not handle buildpath containers, for which
//	 * should rather be used
//	 * <code>DLTKCore#getBuildpathContainer(IPath, IScriptProject)</code>.
//	 * <p>
//	 *
//	 * @param entry
//	 *            the given variable entry
//	 * @return the resolved library or project buildpath entry, or
//	 *         <code>null</code> if the given variable entry could not be
//	 *         resolved to a valid buildpath entry
//	 */
//	public static IBuildpathEntry getResolvedBuildpathEntry(
//			IBuildpathEntry entry) {
//
//		if (entry.getEntryKind() != IBuildpathEntry.BPE_VARIABLE)
//			return entry;
//
//		IPath resolvedPath = DLTKCore.getResolvedVariablePath(entry.getPath());
//		if (resolvedPath == null)
//			return null;
//
//		Object target = Model.getTarget(ResourcesPlugin.getWorkspace()
//				.getRoot(), resolvedPath, false);
//		if (target == null)
//			return null;
//
//		// inside the workspace
//		if (target instanceof IResource) {
//			IResource resolvedResource = (IResource) target;
//			switch (resolvedResource.getType()) {
//
//			case IResource.PROJECT:
//				// internal project
//				return DLTKCore.newProjectEntry(resolvedPath,
//						entry.getAccessRules(), entry.combineAccessRules(),
//						entry.getExtraAttributes(), entry.isExported());
//			case IResource.FILE:
//				// internal binary archive
//				return DLTKCore.newLibraryEntry(resolvedPath,/*
//															 * getResolvedVariablePath
//															 * ( entry.
//															 * getSourceAttachmentPath
//															 * ()),
//															 * getResolvedVariablePath
//															 * (entry.
//															 * getSourceAttachmentRootPath
//															 * ()),
//															 */
//						entry.getAccessRules(), entry.getExtraAttributes(),
//						entry.isExported(), entry.isExternal());
//			case IResource.FOLDER:
//				// internal binary folder
//				return DLTKCore.newLibraryEntry(resolvedPath,/*
//															 * getResolvedVariablePath
//															 * ( entry.
//															 * getSourceAttachmentPath
//															 * ()),
//															 * getResolvedVariablePath
//															 * (entry.
//															 * getSourceAttachmentRootPath
//															 * ()),
//															 */
//						entry.getAccessRules(), entry.getExtraAttributes(),
//						entry.isExported(), entry.isExternal());
//			}
//		}
//		if (target instanceof File) {
//			IFileHandle externalFile = Model.getFile(target);
//			if (externalFile != null) {
//				// external binary archive
//				return DLTKCore.newLibraryEntry(resolvedPath,/*
//															 * getResolvedVariablePath
//															 * ( entry.
//															 * getSourceAttachmentPath
//															 * ()),
//															 * getResolvedVariablePath
//															 * (entry.
//															 * getSourceAttachmentRootPath
//															 * ()),
//															 */
//						entry.getAccessRules(), entry.getExtraAttributes(),
//						entry.isExported(), true);
//			} else {
//				// non-existing file
//				if (resolvedPath.isAbsolute()) {
//					return DLTKCore.newLibraryEntry(resolvedPath,/*
//																 * getResolvedVariablePath
//																 * ( entry.
//																 * getSourceAttachmentPath
//																 * ()),
//																 * getResolvedVariablePath
//																 * (entry.
//																 * getSourceAttachmentRootPath
//																 * ()),
//																 */
//							entry.getAccessRules(), entry.getExtraAttributes(),
//							entry.isExported(), true);
//				}
//			}
//		} else if (target instanceof IFileHandle) {
//			// external binary archive
//			return DLTKCore.newLibraryEntry(resolvedPath,/*
//														 * getResolvedVariablePath(
//														 * entry.
//														 * getSourceAttachmentPath
//														 * ()),
//														 * getResolvedVariablePath
//														 * (entry.
//														 * getSourceAttachmentRootPath
//														 * ()),
//														 */
//					entry.getAccessRules(), entry.getExtraAttributes(),
//					entry.isExported(), true);
//		}
//		return null;
//	}
//
//	/**
//	 * Resolve a variable path (helper method).
//	 *
//	 * @param variablePath
//	 *            the given variable path
//	 * @return the resolved variable path or <code>null</code> if none
//	 */
//	public static IPath getResolvedVariablePath(IPath variablePath) {
//
//		if (variablePath == null)
//			return null;
//		int count = variablePath.segmentCount();
//		if (count == 0)
//			return null;
//
//		// lookup variable
//		String variableName = variablePath.segment(0);
//		IPath resolvedPath = DLTKCore.getBuildpathVariable(variableName);
//		if (resolvedPath == null)
//			return null;
//
//		// append path suffix
//		if (count > 1) {
//			resolvedPath = resolvedPath.append(variablePath
//					.removeFirstSegments(1));
//		}
//		return resolvedPath;
//	}
//
//	/**
//	 * Answers the project specific value for a given buildpath container. In
//	 * case this container path could not be resolved, then will answer
//	 * <code>null</code>. Both the container path and the project context are
//	 * supposed to be non-null.
//	 * <p>
//	 * The containerPath is a formed by a first ID segment followed with extra
//	 * segments, which can be used as additional hints for resolution. If no
//	 * container was ever recorded for this container path onto this project
//	 * (using <code>setBuildpathContainer</code>, then a
//	 * <code>buildpathContainerInitializer</code> will be activated if any was
//	 * registered for this container ID onto the extension point
//	 * "org.eclipse.dltk.core.buildpathContainerInitializer".
//	 * <p>
//	 * There is no assumption that the returned container must answer the exact
//	 * same containerPath when requested
//	 * <code>IBuildpathContainer#getPath</code>. Indeed, the containerPath is
//	 * just an indication for resolving it to an actual container object.
//	 * <p>
//	 * buildpath container values are persisted locally to the workspace, but
//	 * are not preserved from a session to another. It is thus highly
//	 * recommended to register a <code>buildpathContainerInitializer</code> for
//	 * each referenced container (through the extension point
//	 * "org.eclipse.dltk.core.buildpathContainerInitializer").
//	 * <p>
//	 *
//	 * @param containerPath
//	 *            the name of the container, which needs to be resolved
//	 * @param project
//	 *            a specific project in which the container is being resolved
//	 * @return the corresponding buildpath container or <code>null</code> if
//	 *         unable to find one.
//	 *
//	 * @exception ModelException
//	 *                if an exception occurred while resolving the container, or
//	 *                if the resolved container contains illegal entries
//	 *                (contains BPE_CONTAINER entries or null entries).
//	 *
//	 * @see buildpathContainerInitializer
//	 * @see IBuildpathContainer
//	 * @see #setbuildpathContainer(IPath, IScriptProject[],
//	 *      IBuildpathContainer[], IProgressMonitor)
//	 *
//	 */
//	public static IBuildpathContainer getBuildpathContainer(
//			IPath containerPath, IScriptProject project) throws ModelException {
//		ModelManager manager = ModelManager.getModelManager();
//		IBuildpathContainer container = manager.getBuildpathContainer(
//				containerPath, project);
//		if (container == ModelManager.CONTAINER_INITIALIZATION_IN_PROGRESS) {
//			return manager.getPreviousSessionContainer(containerPath, project);
//		}
//		return container;
//	}
//
//	/**
//	 * Helper method finding the buildpath container initializer registered for
//	 * a given buildpath container ID or <code>null</code> if none was found
//	 * while iterating over the contributions to extension point to the
//	 * extension point "org.eclipse.dltk.core.buildpathContainerInitializer".
//	 * <p>
//	 * A containerID is the first segment of any container path, used to
//	 * identify the registered container initializer.
//	 * <p>
//	 *
//	 * @param containerID
//	 *            - a containerID identifying a registered initializer
//	 * @return BuildpathContainerInitializer - the registered buildpath
//	 *         container initializer or <code>null</code> if none was found.
//	 */
//	public static BuildpathContainerInitializer getBuildpathContainerInitializer(
//			String containerID) {
//		final Hashtable<String, BuildpathContainerInitializer> containerInitializersCache = ModelManager
//				.getModelManager().containerInitializersCache;
//		BuildpathContainerInitializer initializer = containerInitializersCache
//				.get(containerID);
//		if (initializer == null) {
//			initializer = computeBuildpathContainerInitializer(containerID);
//			if (initializer == null)
//				return null;
//			containerInitializersCache.put(containerID, initializer);
//		}
//		return initializer;
//	}
//
//	private static BuildpathContainerInitializer computeBuildpathContainerInitializer(
//			String containerID) {
//		Plugin dltkCorePlugin = DLTKCore.getPlugin();
//		if (dltkCorePlugin == null)
//			return null;
//
//		IExtensionPoint extension = Platform.getExtensionRegistry()
//				.getExtensionPoint(DLTKCore.PLUGIN_ID,
//						ModelManager.BPCONTAINER_INITIALIZER_EXTPOINT_ID);
//		if (extension != null) {
//			IExtension[] extensions = extension.getExtensions();
//			for (int i = 0; i < extensions.length; i++) {
//				IConfigurationElement[] configElements = extensions[i]
//						.getConfigurationElements();
//				for (int j = 0; j < configElements.length; j++) {
//					String initializerID = configElements[j].getAttribute("id"); //$NON-NLS-1$
//					if (initializerID != null
//							&& initializerID.equals(containerID)) {
//						if (ModelManager.BP_RESOLVE_VERBOSE) {
//							org.eclipse.dltk.internal.core.util.Util
//									.verbose("BPContainer INIT - found initializer\n" + //$NON-NLS-1$
//											"	container ID: " //$NON-NLS-1$
//											+ containerID
//											+ '\n'
//											+ "	class: " //$NON-NLS-1$
//											+ configElements[j]
//													.getAttribute("class")); //$NON-NLS-1$
//						}
//						try {
//							Object execExt = configElements[j]
//									.createExecutableExtension("class"); //$NON-NLS-1$
//							if (execExt instanceof BuildpathContainerInitializer) {
//								return (BuildpathContainerInitializer) execExt;
//							}
//						} catch (CoreException e) {
//							// executable extension could not be created: ignore
//							// this initializer
//							if (ModelManager.BP_RESOLVE_VERBOSE) {
//								Util.verbose(
//										"BPContainer INIT - failed to instanciate initializer\n" + //$NON-NLS-1$
//												"	container ID: " //$NON-NLS-1$
//												+ containerID
//												+ '\n'
//												+ "	class: " //$NON-NLS-1$
//												+ configElements[j]
//														.getAttribute("class"), //$NON-NLS-1$
//										System.err);
//								e.printStackTrace();
//							}
//						}
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * Bind a container reference path to some actual containers (
//	 * <code>IBuildpathContainer</code>). This API must be invoked whenever
//	 * changes in container need to be reflected onto the Model. Containers can
//	 * have distinct values in different projects, therefore this API considers
//	 * a set of projects with their respective containers.
//	 * <p>
//	 * <code>containerPath</code> is the path under which these values can be
//	 * referenced through container buildpath entries (
//	 * <code>IBuildpathEntry#BPE_CONTAINER</code>). A container path is formed
//	 * by a first ID segment followed with extra segments, which can be used as
//	 * additional hints for the resolution. The container ID is used to identify
//	 * a <code>BuildpathContainerInitializer</code> registered on the extension
//	 * point "org.eclipse.dltk.core.buildpathContainerInitializer".
//	 * <p>
//	 * There is no assumption that each individual container value passed in
//	 * argument (<code>respectiveContainers</code>) must answer the exact same
//	 * path when requested <code>IBuildpathContainer#getPath</code>. Indeed, the
//	 * containerPath is just an indication for resolving it to an actual
//	 * container object. It can be delegated to a
//	 * <code>BuildpathContainerInitializer</code>, which can be activated
//	 * through the extension point
//	 * "org.eclipse.dltk.core.buildpathContainerInitializer").
//	 * <p>
//	 * In reaction to changing container values, the Model will be updated to
//	 * reflect the new state of the updated container. A combined script element
//	 * delta will be notified to describe the corresponding buildpath changes
//	 * resulting from the container update. This operation is batched, and
//	 * automatically eliminates unnecessary updates (new container is same as
//	 * old one). This operation acquires a lock on the workspace's root.
//	 * <p>
//	 * This functionality cannot be used while the workspace is locked, since it
//	 * may create/remove some resource markers.
//	 * <p>
//	 * Buildpath container values are persisted locally to the workspace, but
//	 * are not preserved from a session to another. It is thus highly
//	 * recommended to register a <code>BuildpathContainerInitializer</code> for
//	 * each referenced container (through the extension point
//	 * "org.eclipse.dltk.core.BuildpathContainerInitializer").
//	 * <p>
//	 * Note: setting a container to <code>null</code> will cause it to be lazily
//	 * resolved again whenever its value is required. In particular, this will
//	 * cause a registered initializer to be invoked again.
//	 * <p>
//	 *
//	 * @param containerPath
//	 *            - the name of the container reference, which is being updated
//	 * @param affectedProjects
//	 *            - the set of projects for which this container is being bound
//	 * @param respectiveContainers
//	 *            - the set of respective containers for the affected projects
//	 * @param monitor
//	 *            a monitor to report progress
//	 * @throws ModelException
//	 * @see BuildpathContainerInitializer
//	 * @see #getBuildpathContainer(IPath, IScriptProject)
//	 * @see IBuildpathContainer
//	 *
//	 */
//	public static void setBuildpathContainer(final IPath containerPath,
//			IScriptProject[] affectedProjects,
//			IBuildpathContainer[] respectiveContainers, IProgressMonitor monitor)
//			throws ModelException {
//
//		if (affectedProjects.length != respectiveContainers.length)
//			Assert.isTrue(false,
//					"Projects and containers collections should have the same size"); //$NON-NLS-1$
//		final SetContainerOperation operation = new SetContainerOperation(
//				containerPath, affectedProjects, respectiveContainers);
//		operation.runOperation(monitor);
//	}
//
//	public static void refreshBuildpathContainers(IScriptProject project)
//			throws CoreException {
//		// re-bind all container entries
//		final IBuildpathEntry[] entries = project.getRawBuildpath();
//		for (int j = 0; j < entries.length; j++) {
//			final IBuildpathEntry entry = entries[j];
//			switch (entry.getEntryKind()) {
//			case IBuildpathEntry.BPE_CONTAINER:
//				final IPath reference = entry.getPath();
//				final BuildpathContainerInitializer initializer = getBuildpathContainerInitializer(reference
//						.segment(0));
//				if (initializer != null) {
//					initializer.initialize(reference, project);
//				}
//				break;
//			}
//		}
//	}
//
//	/**
//	 * Sets the value of the given buildpath variable. The path must not be
//	 * null.
//	 * <p>
//	 * This functionality cannot be used while the resource tree is locked.
//	 * <p>
//	 * Buildpath variable values are persisted locally to the workspace, and are
//	 * preserved from session to session.
//	 * <p>
//	 * Updating a variable with the same value has no effect.
//	 *
//	 * @param variableName
//	 *            the name of the buildpath variable
//	 * @param path
//	 *            the path
//	 * @param monitor
//	 *            a monitor to report progress
//	 * @throws ModelException
//	 * @see #getBuildpathVariable(String)
//	 */
//	public static void setBuildpathVariable(String variableName, IPath path,
//			IProgressMonitor monitor) throws ModelException {
//
//		if (path == null)
//			Assert.isTrue(false, "Variable path cannot be null"); //$NON-NLS-1$
//		setBuildpathVariables(new String[] { variableName },
//				new IPath[] { path }, monitor);
//	}
//
//	/**
//	 * Sets the values of all the given buildpath variables at once. Null paths
//	 * can be used to request corresponding variable removal.
//	 * <p>
//	 * A combined model element delta will be notified to describe the
//	 * corresponding buildpath changes resulting from the variables update. This
//	 * operation is batched, and automatically eliminates unnecessary updates
//	 * (new variable is same as old one). This operation acquires a lock on the
//	 * workspace's root.
//	 * <p>
//	 * This functionality cannot be used while the workspace is locked, since it
//	 * may create/remove some resource markers.
//	 * <p>
//	 * Buildpath variable values are persisted locally to the workspace, and are
//	 * preserved from session to session.
//	 * <p>
//	 * Updating a variable with the same value has no effect.
//	 *
//	 * @param variableNames
//	 *            an array of names for the updated buildpath variables
//	 * @param paths
//	 *            an array of path updates for the modified buildpath variables
//	 *            (null meaning that the corresponding value will be removed
//	 * @param monitor
//	 *            a monitor to report progress
//	 * @throws ModelException
//	 * @see #getBuildpathVariable(String)
//	 * @since 2.0
//	 */
//	public static void setBuildpathVariables(String[] variableNames,
//			IPath[] paths, IProgressMonitor monitor) throws ModelException {
//
//		if (variableNames.length != paths.length)
//			Assert.isTrue(false,
//					"Variable names and paths collections should have the same size"); //$NON-NLS-1$
//		SetVariablesOperation operation = new SetVariablesOperation(
//				variableNames, paths, true/* update preferences */);
//		operation.runOperation(monitor);
//	}
//
//	/**
//	 * Creates and returns a new non-exported buildpath entry of kind
//	 * <code>CPE_LIBRARY</code> for the ZIP or folder identified by the given
//	 * absolute path. This specifies that all package fragments within the root
//	 * will have children of type <code>IClassFile</code>. This method is fully
//	 * equivalent to calling
//	 * {@link #newLibraryEntry(IPath, IPath, IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, new
//	 * IAccessRule[0], new IBuildpathAttribute[0], false)}.
//	 *
//	 * @param path
//	 *            the absolute path of the binary archive
//	 * @param sourceAttachmentPath
//	 *            the absolute path of the corresponding source archive or
//	 *            folder, or <code>null</code> if none. Note an empty path is
//	 *            allowed to denote no source attachment. and will be
//	 *            automatically converted to <code>null</code>.
//	 * @param sourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or folder or <code>null</code> if this location should
//	 *            be automatically detected.
//	 * @return a new library buildpath entry
//	 */
//	public static IBuildpathEntry newLibraryEntry(IPath path) {
//
//		return newLibraryEntry(path, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, false/* not exported */,
//				false);
//	}
//
//	public static IBuildpathEntry newExtLibraryEntry(IPath path) {
//
//		return newLibraryEntry(path, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, false/* not exported */,
//				true);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>CPE_LIBRARY</code> for the ZIP or folder identified by the given
//	 * absolute path. This specifies that all package fragments within the root
//	 * will have children of type <code>IClassFile</code>. This method is fully
//	 * equivalent to calling
//	 * {@link #newLibraryEntry(IPath, IPath, IPath, IAccessRule[], IBuildpathAttribute[], boolean)
//	 * newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, new
//	 * IAccessRule[0], new IBuildpathAttribute[0], isExported)}.
//	 *
//	 * @param path
//	 *            the absolute path of the binary archive
//	 * @param sourceAttachmentPath
//	 *            the absolute path of the corresponding source archive or
//	 *            folder, or <code>null</code> if none. Note an empty path is
//	 *            allowed to denote no source attachment. and will be
//	 *            automatically converted to <code>null</code>.
//	 * @param sourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or folder or <code>null</code> if this location should
//	 *            be automatically detected.
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @return a new library buildpath entry
//	 *
//	 */
//	public static IBuildpathEntry newLibraryEntry(IPath path, boolean isExported) {
//
//		return newLibraryEntry(path, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, isExported, false);
//	}
//
//	public static IBuildpathEntry newLibraryEntry(IPath path,
//			boolean isExported, boolean externalLib) {
//
//		return newLibraryEntry(path, BuildpathEntry.NO_ACCESS_RULES,
//				BuildpathEntry.NO_EXTRA_ATTRIBUTES, isExported, externalLib);
//	}
//
//	/**
//	 * Creates and returns a new buildpath entry of kind
//	 * <code>BPE_LIBRARY</code> for the ZIp or folder identified by the given
//	 * absolute path. This specifies that all package fragments within the root
//	 * will have readonly children.
//	 * <p>
//	 * A library entry is used to denote a prerequisite ZIP or root folder
//	 * containing sources. The target ZIP can either be defined internally to
//	 * the workspace (absolute path relative to the workspace root) or
//	 * externally to the workspace (absolute path in the file system). The
//	 * target root folder can only be defined internally to the workspace
//	 * (absolute path relative to the workspace root). To use a binary folder
//	 * external to the workspace, it must first be linked (see
//	 * IFolder#createLink(...)). Note that on non-Windows platform, a path
//	 * <code>"/some/lib.zip"</code> is ambiguous. It can be a path to an
//	 * external ZIP (its file system path being <code>"/some/lib.jar"</code>) or
//	 * it can be a path to an internal ZIP (<code>"some"</code> being a project
//	 * in the workspace). Such an ambiguity is solved when the buildpath entry
//	 * is used (e.g. in {@link IScriptProject#getProjectFragments()}). If the
//	 * resource <code>"lib.jar"</code> exists in project <code>"some"</code>,
//	 * then it is considered an internal ZIP. Otherwise it is an external ZIP.
//	 * <p>
//	 * Also note that this operation does not attempt to validate or access the
//	 * resources at the given paths.
//	 * </p>
//	 * <p>
//	 * The access rules determine the set of accessible source files in the
//	 * library. If the list of access rules is empty then all files in this
//	 * library are accessible. See {@link IAccessRule} for a detailed
//	 * description of access rules.
//	 * </p>
//	 * <p>
//	 * The <code>extraAttributes</code> list contains name/value pairs that must
//	 * be persisted with this entry. If no extra attributes are provided, an
//	 * empty array must be passed in.<br>
//	 * Note that this list should not contain any duplicate name.
//	 * </p>
//	 * <p>
//	 * The <code>isExported</code> flag indicates whether this entry is
//	 * contributed to dependent projects. If not exported, dependent projects
//	 * will not see any of the classes from this entry. If exported, dependent
//	 * projects will concatenate the accessible files patterns of this entry
//	 * with the accessible files patterns of the projects, and they will
//	 * concatenate the non accessible files patterns of this entry with the non
//	 * accessible files patterns of the project.
//	 * </p>
//	 *
//	 * @param path
//	 *            the absolute path of the binary archive
//	 * @param accessRules
//	 *            the possibly empty list of access rules for this entry
//	 * @param extraAttributes
//	 *            the possibly empty list of extra attributes to persist with
//	 *            this entry
//	 * @param isExported
//	 *            indicates whether this entry is contributed to dependent
//	 *            projects in addition to the output location
//	 * @param externalLib
//	 *            TODO
//	 * @param sourceAttachmentPath
//	 *            the absolute path of the corresponding source archive or
//	 *            folder, or <code>null</code> if none. Note an empty path is
//	 *            allowed to denote no source attachment. and will be
//	 *            automatically converted to <code>null</code>.
//	 * @param sourceAttachmentRootPath
//	 *            the location of the root of the source files within the source
//	 *            archive or folder or <code>null</code> if this location should
//	 *            be automatically detected.
//	 * @return a new library buildpath entry
//	 *
//	 */
//	public static IBuildpathEntry newLibraryEntry(IPath path,
//			IAccessRule[] accessRules, IBuildpathAttribute[] extraAttributes,
//			boolean isExported, boolean externalLib) {
//
//		if (path == null)
//			Assert.isTrue(false, "Library path cannot be null"); //$NON-NLS-1$
//		if (!path.isAbsolute())
//			Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$
//		return new BuildpathEntry(IProjectFragment.K_BINARY,
//				IBuildpathEntry.BPE_LIBRARY,
//				ScriptProject.canonicalizedPath(path), isExported,
//				BuildpathEntry.INCLUDE_ALL, // inclusion patterns
//				BuildpathEntry.EXCLUDE_NONE, // exclusion patterns
//				accessRules, false, // no access rules to combine
//				extraAttributes, externalLib);
//	}
//
//	public static IBuildpathEntry newLibraryEntry(IPath path,
//			IAccessRule[] accessRules, IBuildpathAttribute[] extraAttributes,
//			IPath[] include, IPath[] exclude, boolean isExported,
//			boolean externalLib) {
//
//		if (path == null)
//			Assert.isTrue(false, "Library path cannot be null"); //$NON-NLS-1$
//		if (!path.isAbsolute())
//			Assert.isTrue(false, "Path for IBuildpathEntry must be absolute"); //$NON-NLS-1$
//		return new BuildpathEntry(IProjectFragment.K_BINARY,
//				IBuildpathEntry.BPE_LIBRARY,
//				ScriptProject.canonicalizedPath(path), isExported, include, // inclusion
//				// patterns
//				exclude, // exclusion patterns
//				accessRules, false, // no access rules to combine
//				extraAttributes, externalLib);
//	}
//
//	public static IBuildpathEntry newBuiltinEntry(IPath path,
//			IAccessRule[] accessRules, IBuildpathAttribute[] extraAttributes,
//			IPath[] include, IPath[] exclude, boolean isExported,
//			boolean externalLib) {
//
//		if (path == null)
//			Assert.isTrue(false, "Library path cannot be null"); //$NON-NLS-1$
//		return new BuildpathEntry(IProjectFragment.K_BINARY,
//				IBuildpathEntry.BPE_LIBRARY, path, isExported, include, // inclusion
//				// patterns
//				exclude, // exclusion patterns
//				accessRules, false, // no access rules to combine
//				extraAttributes, externalLib);
//	}
//
//	/**
//	 * Returns the script model element corresponding to the given handle
//	 * identifier generated by <code>IModelElement.getHandleIdentifier()</code>,
//	 * or <code>null</code> if unable to create the associated element.
//	 *
//	 * @param handleIdentifier
//	 *            the given handle identifier
//	 * @return the script element corresponding to the handle identifier
//	 */
//	public static IModelElement create(String handleIdentifier) {
//		return create(handleIdentifier, DefaultWorkingCopyOwner.PRIMARY);
//	}
//
//	/**
//	 * Returns the script model element corresponding to the given handle
//	 * identifier generated by <code>IModelElement.getHandleIdentifier()</code>,
//	 * or <code>null</code> if unable to create the associated element. If the
//	 * returned script element is an <code>ISourceModule</code>, its owner is
//	 * the given owner if such a working copy exists, otherwise the compilation
//	 * unit is a primary compilation unit.
//	 *
//	 * @param handleIdentifier
//	 *            the given handle identifier
//	 * @param owner
//	 *            the owner of the returned compilation unit, ignored if the
//	 *            returned element is not a compilation unit
//	 * @return the script element corresponding to the handle identifier
//	 *
//	 */
//	public static IModelElement create(String handleIdentifier,
//			WorkingCopyOwner owner) {
//		if (handleIdentifier == null) {
//			return null;
//		}
//		MementoTokenizer memento = new MementoTokenizer(handleIdentifier);
//		Model model = ModelManager.getModelManager().getModel();
//		return model.getHandleFromMemento(memento, owner);
//	}
//
//	public static ISourceModule createSourceModuleFrom(IFile file) {
//		return ModelManager.createSourceModuleFrom(file, null/*
//															 * unknown script
//															 * project
//															 */);
//	}
//
//	/**
//	 * Returns the names of all known buildpath variables.
//	 * <p>
//	 * Buildpath variable values are persisted locally to the workspace, and are
//	 * preserved from session to session.
//	 * <p>
//	 *
//	 * @return the list of buildpath variable names
//	 * @see #setBuildpathVariable(String, IPath)
//	 */
//	public static String[] getBuildpathVariableNames() {
//		return ModelManager.getModelManager().variableNames();
//	}
//
//	/**
//	 * Returns whether a given buildpath variable is read-only or not.
//	 *
//	 * @param variableName
//	 * @return <code>true</code> if the buildpath variable is read-only,
//	 *         <code>false</code> otherwise.
//	 * @since 3.3
//	 */
//	public static boolean isBuildpathVariableReadOnly(String variableName) {
//		return ModelManager.getModelManager().readOnlyVariables
//				.contains(variableName);
//	}
//
//	public static Hashtable getDefaultOptions() {
//		return ModelManager.getModelManager().getDefaultOptions();
//	}
//
//	public static void setOptions(Hashtable defaultOptions) {
//		ModelManager.getModelManager().setOptions(defaultOptions);
//	}
//
//	public static IRegion newRegion() {
//		return new Region();
//	}
//
//	public static String[] getUserLibraryNames(IDLTKLanguageToolkit toolkit) {
//		return ModelManager.getUserLibraryManager()
//				.getUserLibraryNames(toolkit);
//	}
//
//	public static void error(String message) {
//		plugin.getLog()
//				.log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message,
//						null));
//	}
//
//	/**
//	 * @since 2.0
//	 */
//	public static void error(Throwable e) {
//		plugin.getLog().log(
//				new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, e
//						.getLocalizedMessage(), e));
//	}
//
//	public static void error(String message, Throwable t) {
//		plugin.getLog().log(
//				new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, message, t));
//	}
//
//	public static void warn(String message) {
//		warn(message, null);
//	}
//
//	/**
//	 * @param message
//	 * @param t
//	 */
//	public static void warn(String message, Throwable t) {
//		plugin.getLog().log(
//				new Status(IStatus.WARNING, PLUGIN_ID, IStatus.OK, message, t));
//	}
//
}
