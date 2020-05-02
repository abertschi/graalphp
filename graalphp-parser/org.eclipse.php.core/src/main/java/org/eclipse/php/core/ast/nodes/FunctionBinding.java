/*******************************************************************************
 * Copyright (c) 2009, 2015, 2016 IBM Corporation and others.
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
/**
 * 
 */
package org.eclipse.php.core.ast.nodes;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ModelException;

/**
 * A PHP function binding. This class is also the base class for the
 * {@link MethodBinding} implementation.
 * 
 * @author shalom
 */
@Deprecated
public class FunctionBinding implements IFunctionBinding {


	public FunctionBinding(BindingResolver resolver, IMethod modelElement) {
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
    public ITypeBinding[] getParameterTypes() {
        return new ITypeBinding[0];
    }

    @Override
    public ITypeBinding[] getReturnType() {
        return new ITypeBinding[0];
    }

    @Override
    public ITypeBinding[] getExceptionTypes() {
        return new ITypeBinding[0];
    }

    @Override
    public boolean isVarargs() {
        return false;
    }
}
