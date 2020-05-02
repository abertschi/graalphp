/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.internal.core;

public interface PHPCoreConstants {

	String PLUGIN_ID = "PHPCorePlugin.ID";

	/**
	 * Increase this value to force rebuilding PHP projects during startup
	 */
	String STRUCTURE_VERSION = "14"; //$NON-NLS-1$
	String STRUCTURE_VERSION_PREFERENCE = "pdt.structure.version"; //$NON-NLS-1$

	String FILE_PARENT = "<f>"; //$NON-NLS-1$
	String GLOBAL_NAMESPACE = "<g>"; //$NON-NLS-1$

	String IP_VARIABLE_INITIALIZER_EXTPOINT_ID = "includePathVariables"; //$NON-NLS-1$

	//
	// Project default folders names
	//
	String PROJECT_DEFAULT_SOURCE_FOLDER = "src"; //$NON-NLS-1$
	String PROJECT_DEFAULT_RESOURCES_FOLDER = "resources"; //$NON-NLS-1$

	//
	// Project Option names
	//
	String PHPOPTION_DEFAULT_ENCODING = PLUGIN_ID + ".defaultEncoding"; //$NON-NLS-1$
	String PHPOPTION_CONTEXT_ROOT = PLUGIN_ID + ".contextRoot"; //$NON-NLS-1$
	String PHPOPTION_INCLUDE_PATH = PLUGIN_ID + ".includePath"; //$NON-NLS-1$

	String ADD_JS_NATURE = PLUGIN_ID + ".addJsNature"; //$NON-NLS-1$
	String TASK_PRIORITIES = PLUGIN_ID + ".taskPriorities"; //$NON-NLS-1$
	String TASK_PRIORITY_HIGH = "HIGH"; //$NON-NLS-1$
	String TASK_PRIORITY_LOW = "LOW"; //$NON-NLS-1$
	String TASK_PRIORITY_NORMAL = "NORMAL"; //$NON-NLS-1$
	String TASK_TAGS = PLUGIN_ID + ".taskTags"; //$NON-NLS-1$
	String TASK_CASE_SENSITIVE = PLUGIN_ID + ".taskCaseSensitive"; //$NON-NLS-1$
	String DEFAULT_TASK_TAGS = "TODO,FIXME,XXX,@todo"; //$NON-NLS-1$
	String DEFAULT_TASK_PRIORITIES = "NORMAL,HIGH,NORMAL,NORMAL"; //$NON-NLS-1$
	String ENABLED = "enabled"; //$NON-NLS-1$
	String DISABLED = "disabled"; //$NON-NLS-1$
	String DEFAULT_INDENTATION_SIZE = "1"; //$NON-NLS-1$
	String DEFAULT_TAB_SIZE = "4"; //$NON-NLS-1$
	String DEFAULT_INDENTATION_WRAPPED_LINE_SIZE = "2"; //$NON-NLS-1$
	String DEFAULT_INDENTATION_ARRAY_INIT_SIZE = "2"; //$NON-NLS-1$

	String INCLUDE_PATH_VARIABLE_NAMES = PLUGIN_ID + ".includePathVariableNames"; //$NON-NLS-1$
	String INCLUDE_PATH_VARIABLE_PATHS = PLUGIN_ID + ".includePathVariablePaths"; //$NON-NLS-1$

	String RESERVED_INCLUDE_PATH_VARIABLE_NAMES = PLUGIN_ID + ".includePathReservedVariableNames"; //$NON-NLS-1$
	String RESERVED_INCLUDE_PATH_VARIABLE_PATHS = PLUGIN_ID + ".includePathReservedVariablePaths"; //$NON-NLS-1$

	String PHP_OPTIONS_PHP_VERSION = "phpVersion"; //$NON-NLS-1$

	// XXX: correct all misspelled "Foramtter" constants
	String FORMATTER_USE_TABS = PLUGIN_ID + ".phpForamtterUseTabs"; //$NON-NLS-1$
	String FORMATTER_INDENTATION_SIZE = PLUGIN_ID + ".phpForamtterIndentationSize"; //$NON-NLS-1$
	String FORMATTER_TAB_SIZE = PLUGIN_ID + ".phpForamtterTabSize"; //$NON-NLS-1$
	String FORMATTER_INDENTATION_WRAPPED_LINE_SIZE = PLUGIN_ID + ".phpForamtterIndentationWrappedLineSize"; //$NON-NLS-1$
	String FORMATTER_INDENTATION_ARRAY_INIT_SIZE = PLUGIN_ID + ".phpForamtterArrayInitSize"; //$NON-NLS-1$

	String CODEGEN_ADD_COMMENTS = PLUGIN_ID + ".phpDoc"; //$NON-NLS-1$

	// workspace locale and default local preferences identifiers
	String WORKSPACE_LOCALE = PLUGIN_ID + ".workspaceLocale"; //$NON-NLS-1$
	String WORKSPACE_DEFAULT_LOCALE = PLUGIN_ID + ".workspaceDefaultLocale"; //$NON-NLS-1$

	String RSE_TEMP_PROJECT_NATURE_ID = "org.eclipse.rse.ui.remoteSystemsTempNature"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the PHP code assist adds import
	 * statements.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_ADDIMPORT = "contentAssistAddImport"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the PHP code assist gets auto activated.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_AUTOACTIVATION = "contentAssistAutoactivation"; //$NON-NLS-1$

	/**
	 * A name preference that holds the auto activation delay time in milliseconds.
	 * <p>
	 * Value is of type <code>Integer</code>.
	 * </p>
	 */
	String CODEASSIST_AUTOACTIVATION_DELAY = "contentAssistAutoactivationDelay"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the php code assist inserts a proposal
	 * automatically if only one proposal is available.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_AUTOINSERT = "contentAssistAutoinsert"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether code assist proposals filtering is
	 * case sensitive or not.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_CASE_SENSITIVITY = "contentAssistCaseSensitivity"; //$NON-NLS-1$

	/**
	 * A named preference that controls if argument names are filled in when a
	 * method is selected from as list of code assist proposal.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_FILL_ARGUMENT_NAMES = "contentAssistFillMethodArguments"; //$NON-NLS-1$

	/**
	 * A named preference that controls if global prefix ("\") should be inserted
	 * for global function call inside namespace method is selected from as list of
	 * code assist proposal.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 * 
	 * @since 3.4.0
	 */
	String CODEASSIST_PREFIX_GLOBAL_FUNCTION_CALL = "contentAssistPrefixGlobalFunctionCall"; //$NON-NLS-1$

	/**
	 * A named preference that controls if code assist also contains proposals of
	 * class/method/field stub which does not exist,and when user choose it,a new
	 * class/method/field will be inserted
	 * <p>
	 * Value is of type <code>Boolean</code>. if
	 * <code>false<code> code assist does not contain stubs. If
	 * <code>true</code> code assist contains stubs.
	 * </p>
	 */
	String CODEASSIST_SHOW_STUB = "contentAssistShowStub"; //$NON-NLS-1$

	/**
	 * A named preference that controls if method arguments are guessed when a
	 * method is selected from as list of code assist proposal.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_GUESS_METHOD_ARGUMENTS = "contentAssistGuessMethodArguments"; //$NON-NLS-1$

	/**
	 * A named preference that controls if the PHP code assist only inserts
	 * completions. If set to false the proposals can also _replace_ code.
	 * <p>
	 * Value is of type <code>Boolean</code>.
	 * </p>
	 */
	String CODEASSIST_INSERT_COMPLETION = "contentAssistInsertCompletion"; //$NON-NLS-1$

	/**
	 * A named preference that controls whether to show options that are restricted
	 * by PHP
	 * <p>
	 * Value is of type <code>Boolean</code>. if
	 * <code>true<code> constant assist will be visible
	 * <code>false</code> invisible.
	 * </p>
	 */
	String CODEASSIST_SHOW_STRICT_OPTIONS = "contentAssistShowStrictOptions"; //$NON-NLS-1$

	/**
	 * A named preference that controls if code assist insert full qualified name
	 * for use statement.
	 * <p>
	 * Value is of type <code>Boolean</code>. if
	 * <code>false<code> code assist insert use statement only with the namespace name. If
	 * <code>true</code> code assist insert use statement with the full qualified
	 * name.
	 * </p>
	 */
	String CODEASSIST_INSERT_FULL_QUALIFIED_NAME_FOR_NAMESPACE = "contentAssistInsertFullyQualifiedNameForNamespaceCheckBox"; //$NON-NLS-1$

	/**
	 * A named preference that controls if code assist insert full qualified
	 * name in comments.
	 * <p>
	 * Value is of type <code>Boolean</code>. if
	 * <code>false<code> code assist may insert class names without a fully qualified class name. If
	 * <code>true</code> code assist always insert use statement with the full
	 * qualified name.
	 * </p>
	 */
	String CODEASSIST_INSERT_FULL_QUALIFIED_NAME_IN_COMMENTS = "contentAssistInsertFullyQualifiedNameInCommentsCheckBox"; //$NON-NLS-1$

	/**
	 * A named preference that controls if code assist also contains proposals from
	 * other files
	 * <p>
	 * Value is of type <code>Boolean</code>. if
	 * <code>false<code> code assist only contains visible members. If
	 * <code>true</code> all members are included.
	 * </p>
	 */
	String CODEASSIST_SHOW_VARIABLES_FROM_OTHER_FILES = "contentAssistShowVariablesFromOtherFiles"; //$NON-NLS-1$
	String CODEASSIST_SHOW_VARIABLES_FROM_REFERENCED_FILES = "contentAssistShowVariablesFromReferencedFiles"; //$NON-NLS-1$

	/**
	 * @since 4.1
	 */
	String CODEASSIST_AUTOINSERT_COMMON_PREFIX = "contentAssistAutoInsertCommonPrefix"; //$NON-NLS-1$

	/**
	 * @since 6.0
	 */
	String CODEASSIST_ASYNC = "contentAssistAsync"; //$NON-NLS-1$

	/**
	 * PHP Task Marker
	 */
	String PHP_MARKER_TYPE = "org.eclipse.php.core.phpTaskMarker"; //$NON-NLS-1$

	int AccClassField = (1 << 10);

	String ANONYMOUS = "__anonymous"; //$NON-NLS-1$

	String SLASH = "/"; //$NON-NLS-1$

	/**
	 * @since 5.2
	 */
	String VALIDATOR_PREFERENCES_NODE_ID = "pdt_validator"; //$NON-NLS-1$

	/**
	 * @since 5.2
	 */
	String SEVERITY = "severity"; //$NON-NLS-1$

}
