/*******************************************************************************
 * Copyright (c) 2008 xored software, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.core;

public final class DLTKFeatures {

	private DLTKFeatures() {
		// hidden constructor
	}

	protected static abstract class DLTKFeature {
		private final String name;

		public DLTKFeature(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	public static class BooleanFeature extends DLTKFeature {
		private final boolean defaultValue;

		public BooleanFeature(String name, boolean defaultValue) {
			super(name);
			this.defaultValue = defaultValue;
		}

		public boolean getDefaultValue() {
			return defaultValue;
		}

	}

	public static class IntegerFeature extends DLTKFeature {
		private final int defaultValue;

		public IntegerFeature(String name, int defaultValue) {
			super(name);
			this.defaultValue = defaultValue;
		}

		public int getDefaultValue() {
			return defaultValue;
		}

	}

	public static class StringFeature extends DLTKFeature {
		private final String defaultValue;

		public StringFeature(String name, String defaultValue) {
			super(name);
			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {
			return defaultValue;
		}

	}

	public static final BooleanFeature DELETE_MODULE_WITHOUT_TOP_LEVEL_TYPES = new BooleanFeature(
			"DELETE_MODULE_WITHOUT_TOP_LEVEL_TYPES", false); //$NON-NLS-1$

	/**
	 * Specifies if script files must have an extension
	 * 
	 * @since 2.0
	 */
	public static final BooleanFeature FILE_EXTENSION_REQUIRED = new BooleanFeature(
			"FILE_EXTENSION_REQUIRED", false); //$NON-NLS-1$

}
