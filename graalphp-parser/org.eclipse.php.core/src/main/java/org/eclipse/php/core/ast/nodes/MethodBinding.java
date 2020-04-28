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
/**
 * 
 */
package org.eclipse.php.core.ast.nodes;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IMethod;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.core.ModelException;

/**
 * PHP method binding implementation.
 * 
 * @author shalom
 */
@Deprecated
public class MethodBinding extends FunctionBinding implements IMethodBinding {

	private ITypeBinding declaringClassTypeBinding;

	/**
	 * Constructs a new MethodBinding.
	 * 
	 * @param resolver
	 * @param modelElement
	 */
	public MethodBinding(BindingResolver resolver, IMethod modelElement) {
		super(resolver, modelElement);
	}

    @Override
    public boolean isConstructor() {
        return false;
    }

    @Override
    public ITypeBinding getDeclaringClass() {
        return null;
    }

    @Override
    public boolean overrides(IMethodBinding method) {
        return false;
    }
}
