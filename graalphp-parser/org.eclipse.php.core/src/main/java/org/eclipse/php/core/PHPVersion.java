/*******************************************************************************
 * Copyright (c) 2009-2018 IBM Corporation and others.
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
package org.eclipse.php.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This enumeration defines supported PHP versions.
 */
public enum PHPVersion {

	@Deprecated
    PHP5("php5"), //$NON-NLS-1$
    @Deprecated
    PHP5_3("php5.3"), //$NON-NLS-1$
	@Deprecated
    PHP5_4("php5.4"), //$NON-NLS-1$
	@Deprecated
    PHP5_5("php5.5"), //$NON-NLS-1$
	@Deprecated
    PHP5_6("php5.6"), //$NON-NLS-1$
	@Deprecated
    PHP7_0("php7.0"), //$NON-NLS-1$
	@Deprecated
    PHP7_1("php7.1"), //$NON-NLS-1$
	@Deprecated
    PHP7_2("php7.2"), //$NON-NLS-1$

    @Deprecated
    PHP7_3("php7.3"), //$NON-NLS-1$
	/**
	 * @since 7.0
	 */
	PHP7_4("php7.4"); //$NON-NLS-1$

	private String alias;
	private boolean isExperimentalSupport;

	private static class Aliases {
		private static Map<String, PHPVersion> map = new HashMap<>();
	}

	PHPVersion(String alias, boolean isExperimentalSupport) {
		this.alias = alias;
		this.isExperimentalSupport = isExperimentalSupport;
		Aliases.map.put(alias, this);
	}

	PHPVersion(String alias) {
		this(alias, false);
	}

	public String getAlias() {
		return alias;
	}

	public static PHPVersion byAlias(String alias) {
		return Aliases.map.get(alias);
	}

	public boolean isLessThan(PHPVersion phpVersion) {
		return ordinal() < phpVersion.ordinal();
	}

	public boolean isGreaterThan(PHPVersion phpVersion) {
		return ordinal() > phpVersion.ordinal();
	}

	public boolean isExperimentalSupport() {
		return isExperimentalSupport;
	}

	public static Collection<PHPVersion> getAllVersions() {
		return Aliases.map.values();
	}

	public static PHPVersion[] supportedVersions() {
		return new PHPVersion[] { PHP7_4, PHP7_3, PHP7_2, PHP7_1, PHP7_0, PHP5_6, PHP5_5, PHP5_4, PHP5_3, PHP5 };
	}

	/**
	 * 
	 * @return
	 */
	public static PHPVersion getLatestVersion() {
		return PHPVersion.PHP7_4;
	}

}
