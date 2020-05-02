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
package org.eclipse.php.core.ast.nodes;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;

/**
 * @see IIncludeBinding
 * @author Roy 2008
 */
@Deprecated
public class IncludeBinding implements IIncludeBinding {

	public IncludeBinding(ISourceModule model, Include includeDeclaration) {
	}

    @Override
    public int getKind() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public boolean isDeprecated() {
        return false;
    }

    @Override
    public IModelElement getPHPElement() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String[] getNameComponents() {
        return new String[0];
    }
}
