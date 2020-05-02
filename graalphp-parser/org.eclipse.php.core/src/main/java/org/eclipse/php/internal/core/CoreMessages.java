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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class CoreMessages  {

    private static final String BUNDLE_NAME = "org.eclipse.php.internal.core.CoreMessages";//$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
    private static ResourceBundle fResourceBundle;

    private CoreMessages() {
    }

    public static ResourceBundle getResourceBundle() {
        try {
            if (fResourceBundle == null) {
                fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
            }
        } catch (MissingResourceException x) {
            fResourceBundle = null;
        }
        return fResourceBundle;
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return ""; //$NON-NLS-1$
        }
    }

    public static String PHPCorePlugin_initializingPHPToolkit;
    public static String PHPTodoTaskAstParser_0;
}
