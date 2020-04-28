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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.php.core.compiler.PHPFlags;

@Deprecated
public class Bindings {

	public static final String ARRAY_LENGTH_FIELD_BINDING_STRING = "(array type):length";//$NON-NLS-1$

	private Bindings() {
		// No instance
	}

	public static int hashCode(IBinding binding) {
		Assert.isNotNull(binding);
		String key = binding.getKey();
		if (key == null) {
			return binding.hashCode();
		}
		return key.hashCode();
	}

	/**
	 * Note: this method is for debugging and testing purposes only. There are tests
	 * whose pre-computed test results rely on the returned String's format.
	 * 
	 * @param binding
	 *            the binding
	 * @return a string representation of given binding
	 * @see org.eclipse.jdt.internal.ui.viewsupport.BindingLabelProvider
	 */
	public static String asString(IBinding binding) {
		if (binding instanceof IMethodBinding) {
			return asString((IMethodBinding) binding);
		} else if (binding instanceof ITypeBinding) {
			return ((ITypeBinding) binding).getBinaryName(); // getQualifiedName()
		} else if (binding instanceof IVariableBinding) {
			return asString((IVariableBinding) binding);
		}
		return binding.toString();
	}

	private static String asString(IVariableBinding variableBinding) {
		if (!variableBinding.isField()) {
			return variableBinding.toString();
		}
		if (variableBinding.getDeclaringClass() == null) {
			Assert.isTrue(variableBinding.getName().equals("length"));//$NON-NLS-1$
			return ARRAY_LENGTH_FIELD_BINDING_STRING;
		}
		StringBuilder result = new StringBuilder();
		result.append(variableBinding.getDeclaringClass().getName());
		result.append(':');
		result.append(variableBinding.getName());
		return result.toString();
	}

	private static String asString(IMethodBinding method) {
		StringBuilder result = new StringBuilder();
		result.append(method.getDeclaringClass().getName());
		result.append(':');
		result.append(method.getName());
		result.append('(');
		ITypeBinding[] parameters = method.getParameterTypes();
		int lastComma = parameters.length - 1;
		for (int i = 0; i < parameters.length; i++) {
			ITypeBinding parameter = parameters[i];
			result.append(parameter.getName());
			if (i < lastComma) {
				result.append(", "); //$NON-NLS-1$
			}
		}
		result.append(')');
		return result.toString();
	}

	public static ITypeBinding getTopLevelType(ITypeBinding type) {
		ITypeBinding parent = type.getSuperclass();
		while (parent != null) {
			type = parent;
			parent = type.getSuperclass();
		}
		return type;
	}

	/**
	 * Checks whether the passed type binding is a runtime exception.
	 * 
	 * @param thrownException
	 *            the type binding
	 * 
	 * @return <code>true</code> if the passed type binding is a runtime exception;
	 *         otherwise <code>false</code> is returned
	 */
	public static boolean isRuntimeException(ITypeBinding thrownException) {
		if (thrownException == null || thrownException.isPrimitive() || thrownException.isArray()) {
			return false;
		}
		return findTypeInHierarchy(thrownException, "java.lang.RuntimeException") != null; //$NON-NLS-1$
	}

	/**
	 * Finds the field specified by <code>fieldName<code> in
	 * the given <code>type</code>. Returns <code>null</code> if no such field
	 * exits.
	 * 
	 * @param type
	 *            the type to search the field in
	 * @param fieldName
	 *            the field name
	 * @return the binding representing the field or <code>null</code>
	 */
	public static IVariableBinding findFieldInType(ITypeBinding type, String fieldName) {
		if (type.isPrimitive()) {
			return null;
		}
		IVariableBinding[] fields = type.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			IVariableBinding field = fields[i];
			// TODO see if we can remove the dollar sign from here
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * Finds the field specified by <code>fieldName</code> in the type hierarchy
	 * denoted by the given type. Returns <code>null</code> if no such field exists.
	 * If the field is defined in more than one super type only the first match is
	 * returned. First the super class is examined and than the implemented
	 * interfaces.
	 * 
	 * @param type
	 *            The type to search the field in
	 * @param fieldName
	 *            The name of the field to find
	 * @return the variable binding representing the field
	 */
	public static IVariableBinding findFieldInHierarchy(ITypeBinding type, String fieldName) {
		IVariableBinding field = findFieldInType(type, fieldName);
		if (field != null) {
			return field;
		}
		ITypeBinding superClass = type.getSuperclass();
		if (superClass != null) {
			field = findFieldInHierarchy(superClass, fieldName);
			if (field != null) {
				return field;
			}
		}
		ITypeBinding[] interfaces = type.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			field = findFieldInHierarchy(interfaces[i], fieldName);
			if (field != null) {
				return field;
			}
		}
		return null;
	}

	/**
	 * Finds the method specified by <code>methodName<code> and </code>
	 * parameters</code> in the given <code>type</code>. Returns <code>null</code>
	 * if no such method exits.
	 * 
	 * @param type
	 *            The type to search the method in
	 * @param methodName
	 *            The name of the method to find
	 * @return the method binding representing the method
	 */
	public static IMethodBinding findMethodInType(ITypeBinding type, String methodName) {
		if (type.isPrimitive()) {
			return null;
		}
		IMethodBinding[] methods = type.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equalsIgnoreCase(methods[i].getName())) {
				return methods[i];
			}

		}
		return null;
	}

	/**
	 * Finds the method specified by <code>methodName</code> and </code>
	 * parameters</code> in the type hierarchy denoted by the given type. Returns
	 * <code>null</code> if no such method exists. If the method is defined in more
	 * than one super type only the first match is returned. First the super class
	 * is examined and than the implemented interfaces.
	 * 
	 * @param type
	 *            The type to search the method in
	 * @param methodName
	 *            The name of the method to find
	 * @return the method binding representing the method
	 */
	public static IMethodBinding findMethodInHierarchy(ITypeBinding type, String methodName) {
		IMethodBinding method = findMethodInType(type, methodName);
		if (method != null) {
			return method;
		}
		ITypeBinding superClass = type.getSuperclass();
		if (superClass != null) {
			method = findMethodInHierarchy(superClass, methodName);
			if (method != null) {
				return method;
			}
		}
		ITypeBinding[] interfaces = type.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			method = findMethodInHierarchy(interfaces[i], methodName);
			if (method != null) {
				return method;
			}
		}
		return null;
	}

	/**
	 * Finds the method specified by <code>methodName</code> and </code>
	 * parameters</code> in the type hierarchy denoted by the given type. Returns
	 * <code>null</code> if no such method exists. If the method is defined in more
	 * than one super type only the first match is returned. First the super class
	 * is examined and than the implemented interfaces.
	 * 
	 * @param type
	 *            The type to search the method in
	 * @param methodName
	 *            The name of the method to find
	 * @return the method binding representing the method
	 */
	public static IMethodBinding[] findAbstractMethodsInHierarchy(ITypeBinding type) {

		List<IMethodBinding> methodsToOVerride = new ArrayList<>();
		Set<String> overridenMethodsNames = new HashSet<>();
		collectAbstractMethodsInHierarchy(type, methodsToOVerride, overridenMethodsNames);

		return methodsToOVerride.toArray(new IMethodBinding[methodsToOVerride.size()]);
	}

	private static void collectAbstractMethodsInHierarchy(ITypeBinding curr, List<IMethodBinding> methodsToOverride,
			Set<String> overridenMethodsNames) {
		// start of current IType method pass
		if (curr != null) {
			if (PHPFlags.isInterface(curr.getModifiers())) {
				for (IMethodBinding methodBinding : curr.getDeclaredMethods()) {
					if (!overridenMethodsNames.contains(methodBinding.getName())) {
						methodsToOverride.add(methodBinding);
						overridenMethodsNames.add(methodBinding.getName());
					}
				}
			}

			// an abstract class
			else if (PHPFlags.isAbstract(curr.getModifiers())) {
				for (IMethodBinding methodBinding : curr.getDeclaredMethods()) {
					if (!PHPFlags.isAbstract(methodBinding.getModifiers())) {
						overridenMethodsNames.add(methodBinding.getName());
					} else if (!overridenMethodsNames.contains(methodBinding.getName())) {
						methodsToOverride.add(methodBinding);
						overridenMethodsNames.add(methodBinding.getName());
					}
				}
			}

			else {// add existing methods to exclude list
				for (IMethodBinding methodBinding : curr.getDeclaredMethods()) {
					if (!overridenMethodsNames.contains(methodBinding.getName())) {
						overridenMethodsNames.add(methodBinding.getName());
					}
				}
			}

			// end of current IType method pass

			// this class has a superclass
			ITypeBinding superClassBinding = curr.getSuperclass();
			if (superClassBinding != null && superClassBinding.getName() != null) {
				collectAbstractMethodsInHierarchy(superClassBinding, methodsToOverride, overridenMethodsNames);
				;
			}
			// this class has interfaces
			// ITypeBinding[] interfaceBindings = curr.getInterfaces();
			// for (ITypeBinding interfaceBinding : interfaceBindings) {
			// collectAbstractMethodsInHierarchy(superClassBinding,
			// methodsToOverride, overridenMethodsNames);
			// ;
			// }
		}
	}

	/**
	 * Finds the method in the given <code>type</code> that is overridden by the
	 * specified <code>method<code>.
	 * Returns <code>null</code> if no such method exits.
	 * 
	 * @param type
	 *            The type to search the method in
	 * @param method
	 *            The specified method that would override the result
	 * @return the method binding of the method that is overridden by the specified
	 *         <code>method<code>, or <code>null</code>
	 */
	public static IMethodBinding findOverriddenMethodInType(ITypeBinding type, IMethodBinding method) {
		IMethodBinding[] methods = type.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (isSubsignature(method, methods[i])) {
				return methods[i];
			}
		}
		return null;
	}

	/**
	 * Finds a method in the hierarchy of <code>type</code> that is overridden by
	 * </code>binding</code>. Returns <code>null</code> if no such method exists. If
	 * the method is defined in more than one super type only the first match is
	 * returned. First the super class is examined and than the implemented
	 * interfaces.
	 * 
	 * @param type
	 *            The type to search the method in
	 * @param binding
	 *            The method that overrides
	 * @return the method binding overridden the method
	 */
	public static IMethodBinding findOverriddenMethodInHierarchy(ITypeBinding type, IMethodBinding binding) {
		return innerFindOverriddenMethodInHierarchy(type, binding, new HashSet<ITypeBinding>());
	}

	public static IMethodBinding innerFindOverriddenMethodInHierarchy(ITypeBinding type, IMethodBinding binding,
			Set<ITypeBinding> processedTypes) {
		if (!processedTypes.add(type)) {
			return null;
		}

		IMethodBinding method = findOverriddenMethodInType(type, binding);
		if (method != null) {
			return method;
		}
		ITypeBinding superClass = type.getSuperclass();
		if (superClass != null) {
			method = innerFindOverriddenMethodInHierarchy(superClass, binding, processedTypes);
			if (method != null) {
				return method;
			}
		}
		ITypeBinding[] interfaces = type.getInterfaces();
		if (interfaces != null) {
			for (int i = 0; i < interfaces.length; i++) {
				method = innerFindOverriddenMethodInHierarchy(interfaces[i], binding, processedTypes);
				if (method != null) {
					return method;
				}
			}
		}
		return null;
	}

	/**
	 * Finds the method that is overridden by the given method. The search is
	 * bottom-up, so this returns the nearest defining/declaring method.
	 * 
	 * @param overriding
	 *            overriding method
	 * @param testVisibility
	 *            If true the result is tested on visibility. Null is returned if
	 *            the method is not visible.
	 * @return the method binding representing the method
	 */
	public static IMethodBinding findOverriddenMethod(IMethodBinding overriding, boolean testVisibility) {
		int modifiers = overriding.getModifiers();
		if (testVisibility
				&& (PHPFlags.isPrivate(modifiers) /*
													 * || PHPFlags.isStatic(modifiers) || overriding.isConstructor()
													 */)) {
			return null;
		}

		ITypeBinding type = overriding.getDeclaringClass();
		if (type == null) {
			return null;
		}
		if (type.getSuperclass() != null) {
			IMethodBinding res = findOverriddenMethodInHierarchy(type.getSuperclass(), overriding);
			if (res != null && !PHPFlags.isPrivate(res.getModifiers())) {
				if (!testVisibility || isVisibleInHierarchy(res/* , overriding.getDeclaringClass().getPackage() */)) {
					return res;
				}
			}
		}
		ITypeBinding[] interfaces = type.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			IMethodBinding res = findOverriddenMethodInHierarchy(interfaces[i], overriding);
			if (res != null) {
				return res; // methods from interfaces are always public and
							// therefore visible
			}
		}
		return null;
	}

	public static boolean isVisibleInHierarchy(IMethodBinding member/* , IPackageBinding pack */) {
		int otherflags = member.getModifiers();
		ITypeBinding declaringType = member.getDeclaringClass();
		if (PHPFlags.isPublic(otherflags) || PHPFlags.isProtected(otherflags)
				|| (declaringType != null && declaringType.isInterface())) {
			return true;
		} else if (PHPFlags.isPrivate(otherflags)) {
			return false;
		}
		return declaringType != null /* && pack == declaringType.getPackage() */;
	}

	/**
	 * Returns all super types (classes and interfaces) for the given type.
	 * 
	 * @param type
	 *            The type to get the supertypes of.
	 * @return all super types (excluding <code>type</code>)
	 */
	public static ITypeBinding[] getAllSuperTypes(ITypeBinding type) {
		Set<ITypeBinding> result = new HashSet<>();
		collectSuperTypes(type, result);
		result.remove(type);
		return result.toArray(new ITypeBinding[result.size()]);
	}

	private static void collectSuperTypes(ITypeBinding curr, Set<ITypeBinding> collection) {
		if (collection.add(curr)) {
			ITypeBinding[] interfaces = curr.getInterfaces();
			for (int i = 0; i < interfaces.length; i++) {
				collectSuperTypes(interfaces[i], collection);
			}
			ITypeBinding superClass = curr.getSuperclass();
			if (superClass != null) {
				collectSuperTypes(superClass, collection);
			}
		}
	}

	/**
	 * @param overriding
	 *            overriding method (m1)
	 * @param overridden
	 *            overridden method (m2)
	 * @return <code>true</code> iff the method <code>m1</code> is a subsignature of
	 *         the method <code>m2</code>. This is one of the requirements for m1 to
	 *         override m2. Accessibility and return types are not taken into
	 *         account. Note that subsignature is <em>not</em> symmetric! TODO - PHP
	 *         handling (shalom)
	 */
	public static boolean isSubsignature(IMethodBinding overriding, IMethodBinding overridden) {
		// TODO: use IMethodBinding#isSubsignature(..) once it is tested and
		// fixed (only erasure of m1's parameter types, considering type
		// variable counts, doing type variable substitution
		if (!overriding.getName().equalsIgnoreCase(overridden.getName())) {
			return false;
		}
		return true;
	}

	/**
	 * Finds a type binding for a given fully qualified type in the hierarchy of a
	 * type. Returns <code>null</code> if no type binding is found.
	 * 
	 * @param hierarchyType
	 *            the binding representing the hierarchy
	 * @param fullyQualifiedTypeName
	 *            the fully qualified name to search for
	 * @return the type binding
	 */
	public static ITypeBinding findTypeInHierarchy(ITypeBinding hierarchyType, String fullyQualifiedTypeName) {
		if (hierarchyType.isArray() || hierarchyType.isPrimitive()) {
			return null;
		}
		if (fullyQualifiedTypeName.equals(hierarchyType.getBinaryName())) { // hierarchyType.getQualifiedName()
			return hierarchyType;
		}
		ITypeBinding superClass = hierarchyType.getSuperclass();
		if (superClass != null) {
			ITypeBinding res = findTypeInHierarchy(superClass, fullyQualifiedTypeName);
			if (res != null) {
				return res;
			}
		}
		ITypeBinding[] superInterfaces = hierarchyType.getInterfaces();
		for (int i = 0; i < superInterfaces.length; i++) {
			ITypeBinding res = findTypeInHierarchy(superInterfaces[i], fullyQualifiedTypeName);
			if (res != null) {
				return res;
			}
		}
		return null;
	}

	public static ITypeBinding getBindingOfParentType(ASTNode node) {
		while (node != null) {
			if (node instanceof TypeDeclaration) {
				return ((TypeDeclaration) node).resolveTypeBinding();
			} else if (node instanceof AnonymousClassDeclaration) {
				return ((AnonymousClassDeclaration) node).resolveTypeBinding();
			}
			node = node.getParent();
		}
		return null;
	}

}
