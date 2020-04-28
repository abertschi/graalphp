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

import java.util.StringTokenizer;
/**
 * Small convenience class to log messages to plugin's log file and also, if
 * desired, the console. This class should only be used by classes in this
 * plugin. Other plugins should make their own copy, with appropriate ID.
 */

public class Logger {
	private static final String PLUGIN_ID = "PHPCorePlugin.ID";

	private static final String TRACEFILTER_LOCATION = "/debug/tracefilter"; //$NON-NLS-1$

	public static final int OK = 0;
	public static final int INFO = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 4;

	public static final int OK_DEBUG = 200 + OK;
	public static final int INFO_DEBUG = 200 + INFO;
	public static final int WARNING_DEBUG = 200 + WARNING;
	public static final int ERROR_DEBUG = 200 + ERROR;

	/**
	 * Adds message to log.
	 * 
	 * @param level
	 *            severity level of the message (OK, INFO, WARNING, ERROR, OK_DEBUG,
	 *            INFO_DEBUG, WARNING_DEBUG, ERROR_DEBUG)
	 * @param message
	 *            text to add to the log
	 * @param exception
	 *            exception thrown
	 */
	protected static void _log(int level, String message, Throwable exception) {
		if (level == OK_DEBUG || level == INFO_DEBUG || level == WARNING_DEBUG || level == ERROR_DEBUG) {
			if (!isDebugging()) {
				return;
			}
		}
		debugMSG(message);

	}

	/**
	 * Prints message to log if category matches /debug/tracefilter option.
	 * 
	 * @param message
	 *            text to print
	 * @param category
	 *            category of the message, to be compared with /debug/tracefilter
	 */
	protected static void _trace(String category, String message, Throwable exception) {
	}

	/**
	 * @return true if the platform is debugging
	 */
	public static boolean isDebugging() {
		return true;
	}

	/**
	 * Determines if currently tracing a category
	 * 
	 * @param category
	 * @return true if tracing category, false otherwise
	 */
	public static boolean isTracing(String category) {
		if (!isDebugging()) {
			return false;
		}

		return false;
	}

	public static void log(int level, String message) {
		_log(level, message, null);
	}

	public static void log(int level, String message, Throwable exception) {
		_log(level, message, exception);
	}

	public static void logException(String message, Throwable exception) {
		_log(ERROR, message, exception);
	}

	public static void logException(Throwable exception) {
		_log(ERROR, exception.getMessage(), exception);
	}

	public static void traceException(String category, String message, Throwable exception) {
		_trace(category, message, exception);
	}

	public static void traceException(String category, Throwable exception) {
		_trace(category, exception.getMessage(), exception);
	}

	public static void trace(String category, String message) {
		_trace(category, message, null);
	}

	public static void debugMSG(String msg) {
//		if (PHPCorePlugin.isDebugMode) {
			System.out.println(msg);
//		}
	}

}
