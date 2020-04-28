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

import java.util.*;

import org.eclipse.dltk.core.*;
import org.eclipse.dltk.ti.types.IEvaluatedType;


public class TypeBinding implements ITypeBinding {


	public TypeBinding(BindingResolver resolver, IEvaluatedType type, IModelElement[] elements) {
	}

	/**
	 * Constructs a new TypeBinding.
	 * 
	 * @param resolver
	 * @param type
	 * @param element
	 */
	public TypeBinding(BindingResolver resolver, IEvaluatedType type, IModelElement element) {
	}

    @Override
    public ITypeBinding createArrayType(int dimension) {
        return null;
    }

    @Override
    public String getBinaryName() {
        return null;
    }

    @Override
    public ITypeBinding getComponentType() {
        return null;
    }

    @Override
    public IVariableBinding[] getDeclaredFields() {
        return new IVariableBinding[0];
    }

    @Override
    public IMethodBinding[] getDeclaredMethods() {
        return new IMethodBinding[0];
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
    public int getDimensions() {
        return 0;
    }

    @Override
    public ITypeBinding getElementType() {
        return null;
    }

    @Override
    public ITypeBinding[] getInterfaces() {
        return new ITypeBinding[0];
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
    public IEvaluatedType getEvaluatedType() {
        return null;
    }

    @Override
    public ITypeBinding getSuperclass() {
        return null;
    }

    @Override
    public ITypeBinding getTypeDeclaration() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isClass() {
        return false;
    }

    @Override
    public boolean isTrait() {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isNullType() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public boolean isSubTypeCompatible(ITypeBinding type) {
        return false;
    }

    @Override
    public boolean isAmbiguous() {
        return false;
    }

    @Override
    public boolean isUnknown() {
        return false;
    }

    @Override
    public List<IType> getTraitList(boolean isMethod, String classMemberName, boolean includeSuper) {
        return null;
    }

    @Override
    public IModelElement[] getPHPElements() {
        return new IModelElement[0];
    }
}
