package org.eclipse.php.core.ast.nodes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

public class MultipleTypeBinding implements ITypeBinding {
	private final static IVariableBinding[] NO_VARIABLES = new IVariableBinding[0];
	private final static IMethodBinding[] NO_METHODS = new IMethodBinding[0];
	private ITypeBinding[] subTypes;
	private IVariableBinding[] fields;
	private IMethodBinding[] methods;
	private IEvaluatedType type;
	private int modifiers = -1;
	private ITypeBinding[] interfaces;

	public MultipleTypeBinding(IEvaluatedType type, ITypeBinding[] subTypes) {
		this.type = type;
		this.subTypes = subTypes;
	}

	@Override
	public int getKind() {
		return ITypeBinding.TYPE;
	}

	@Override
	public boolean isDeprecated() {
		for (ITypeBinding binding : subTypes) {
			if (binding.isDeprecated()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IModelElement getPHPElement() {
		IModelElement element;
		for (ITypeBinding binding : subTypes) {
			element = binding.getPHPElement();
			if (element != null) {
				return element;
			}
		}
		return null;
	}

	@Override
	public String getKey() {
		StringBuilder sb = new StringBuilder("mutli_");
		for (ITypeBinding binding : subTypes) {
			sb.append(binding.getKey());
		}
		return null;
	}

	@Override
	public ITypeBinding createArrayType(int dimension) {
		return null;
	}

	@Override
	public String getBinaryName() {
		if (isUnknown() || isAmbiguous()) {
			return null;
		}
		return getKey();
	}

	@Override
	public ITypeBinding getComponentType() {
		if (!isArray()) {
			return null;
		}
		// TODO - This should be implemented as soon as the we will be able to
		// identify the types that
		// the array is holding.
		// Once we have that, we can return a TypeBinding or a
		// CompositeTypeBinding for multiple types array.
		return null;
	}

	@Override
	public IVariableBinding[] getDeclaredFields() {

		if (fields == null) {
			if (isUnknown()) {
				fields = NO_VARIABLES;
			} else {
				Set<IVariableBinding> tmp = new HashSet<>();
				for (ITypeBinding binging : subTypes) {
					tmp.addAll(Arrays.asList(binging.getDeclaredFields()));
				}
				fields = tmp.toArray(NO_VARIABLES);
			}
		}

		return fields;
	}

	@Override
	public IMethodBinding[] getDeclaredMethods() {
		if (methods == null) {
			if (isUnknown()) {
				methods = NO_METHODS;
			} else {
				Set<IMethodBinding> tmp = new HashSet<>();
				for (ITypeBinding binging : subTypes) {
					tmp.addAll(Arrays.asList(binging.getDeclaredMethods()));
				}
				methods = tmp.toArray(NO_METHODS);
			}
		}

		return methods;
	}

	@Override
	public int getModifiers() {
		int mods = 0;
		for (ITypeBinding type : subTypes) {
			mods = mods | type.getModifiers();
		}
		return 0;
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
		return null;
	}

	@Override
	public String getName() {
		return subTypes[0].getName();
	}

	@Override
	public IEvaluatedType getEvaluatedType() {
		return null;
	}

	@Override
	public ITypeBinding getSuperclass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITypeBinding getTypeDeclaration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isArray() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClass() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTrait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInterface() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNullType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPrimitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubTypeCompatible(ITypeBinding type) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModelElement[] getPHPElements() {
		// TODO Auto-generated method stub
		return null;
	}

}
