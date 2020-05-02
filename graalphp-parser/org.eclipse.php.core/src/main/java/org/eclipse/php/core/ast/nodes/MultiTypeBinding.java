/*******************************************************************************
 * Copyright (c) 2018 Dawid Pakuła and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Dawid Pakuła - initial API and implementation
 *******************************************************************************/
package org.eclipse.php.core.ast.nodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MultiTypeBinding implements ITypeBinding {
	private final ITypeBinding[] subtypes;
	private final DefaultBindingResolver resolver;

	public MultiTypeBinding(DefaultBindingResolver resolver, ITypeBinding[] subtypes) {
		this.subtypes = subtypes;
		this.resolver = resolver;
	}

	@Override
	public int getKind() {
		return IBinding.TYPE;
	}

	@Override
	public boolean isDeprecated() {
		for (ITypeBinding binding : subtypes) {
			if (binding.isDeprecated()) {
				return true;
			}
		}
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
		List<IVariableBinding> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			list.addAll(Arrays.asList(t.getDeclaredFields()));
		}
		return list.toArray(new IVariableBinding[0]);
	}

	@Override
	public IMethodBinding[] getDeclaredMethods() {
		List<IMethodBinding> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			list.addAll(Arrays.asList(t.getDeclaredMethods()));
		}
		return list.toArray(new IMethodBinding[0]);
	}

	@Override
	public int getModifiers() {
		return -1;
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
		List<ITypeBinding> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			list.addAll(Arrays.asList(t.getInterfaces()));
		}
		return list.toArray(new ITypeBinding[0]);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public IEvaluatedType getEvaluatedType() {
		return subtypes[0].getEvaluatedType();
	}

	@Override
	public ITypeBinding getSuperclass() {
		List<ITypeBinding> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			ITypeBinding s = t.getSuperclass();
			if (s != null && !s.isUnknown()) {
				list.add(s);
			}
		}
		if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() > 1) {
			return new MultiTypeBinding(resolver, list.toArray(new ITypeBinding[0]));
		}
		return null;
	}

	@Override
	public ITypeBinding getTypeDeclaration() {
		return subtypes[0].getTypeDeclaration();
	}

	@Override
	public boolean isArray() {
		for (ITypeBinding t : subtypes) {
			if (t.isArray()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isClass() {
		for (ITypeBinding t : subtypes) {
			if (t.isClass()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isTrait() {
		for (ITypeBinding t : subtypes) {
			if (t.isTrait()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isInterface() {
		for (ITypeBinding t : subtypes) {
			if (t.isInterface()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isNullType() {
		for (ITypeBinding t : subtypes) {
			if (t.isNullType()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPrimitive() {
		for (ITypeBinding t : subtypes) {
			if (t.isPrimitive()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSubTypeCompatible(ITypeBinding type) {
		for (ITypeBinding t : subtypes) {
			if (t.isSubTypeCompatible(type)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isAmbiguous() {
		return true;
	}

	@Override
	public boolean isUnknown() {
		return false;
	}

	@Override
	public List<IType> getTraitList(boolean isMethod, String classMemberName, boolean includeSuper) {
		List<IType> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			list.addAll(t.getTraitList(isMethod, classMemberName, includeSuper));
		}
		return list;
	}

	@Override
	public IModelElement[] getPHPElements() {
		List<IModelElement> list = new LinkedList<>();
		for (ITypeBinding t : subtypes) {
			list.addAll(Arrays.asList(t.getPHPElements()));
		}
		return list.toArray(new IModelElement[0]);
	}
}
