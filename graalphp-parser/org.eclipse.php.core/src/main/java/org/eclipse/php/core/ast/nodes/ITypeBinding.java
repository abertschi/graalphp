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

import java.util.List;

import org.eclipse.dltk.ast.Modifiers;
import org.eclipse.dltk.core.IType;
import org.eclipse.dltk.ti.types.IEvaluatedType;

/**
 * A type binding represents fully-resolved type. There are a number of
 * different kinds of type bindings:
 * <ul>
 * <li>a class - represents the class declaration; possibly with type parameters
 * </li>
 * <li>an interface - represents the class declaration; possibly with type
 * parameters</li>
 * <li>an annotation - represents the annotation type declaration (annotation
 * types do not have have type parameters)</li>
 * <li>an array type - array types are referenced but not explicitly declared
 * </li>
 * <li>a primitive type (including the special return type <code>void</code>) -
 * primitive types are referenced but not explicitly declared</li>
 * <li>the null type - this is the special type of <code>null</code></li>
 * <li>a type variable - represents the declaration of a type variable; possibly
 * with type bounds</li>
 * </ul>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see ITypeBinding#getDeclaredTypes()
 * @since 2.0
 */
public interface ITypeBinding extends IBinding {

	/**
	 * Answer an array type binding using the receiver and the given dimension.
	 * 
	 * <p>
	 * If the receiver is an array binding, then the resulting dimension is the
	 * given dimension plus the dimension of the receiver. Otherwise the resulting
	 * dimension is the given dimension.
	 * </p>
	 * 
	 * @param dimension
	 *            the given dimension
	 * @return an array type binding
	 * @throws IllegalArgumentException
	 *             :
	 *             <ul>
	 *             <li>if the receiver represents the void type</li>
	 *             <li>if the resulting dimensions is lower than one or greater than
	 *             255</li>
	 *             </ul>
	 * @since 3.3
	 */
	ITypeBinding createArrayType(int dimension);

	/**
	 * Returns the binary name of this type binding. The binary name of a class is
	 * defined in the Java Language Specification 3rd edition, section 13.1.
	 * <p>
	 * Note that in some cases, the binary name may be unavailable. This may happen,
	 * for example, for a local type declared in unreachable code.
	 * </p>
	 * 
	 * @return the binary name of this type, or <code>null</code> if the binary name
	 *         is unknown
	 * @since 3.0
	 */
	String getBinaryName();

	/**
	 * Returns the binding representing the component type of this array type, or
	 * <code>null</code> if this is not an array type binding. The component type of
	 * an array might be an array type.
	 * <p>
	 * This is subject to change before 3.2 release.
	 * </p>
	 * 
	 * @return the component type binding, or <code>null</code> if this is not an
	 *         array type
	 * @since 3.2
	 */
	ITypeBinding getComponentType();

	/**
	 * Returns a list of bindings representing all the fields declared as members of
	 * this class or interface type.
	 * 
	 * <p>
	 * These include public, protected, default (package-private) access, and
	 * private fields declared by the class, but excludes inherited fields. Fields
	 * from binary types that reference unresolvable types may not be included.
	 * </p>
	 * 
	 * <p>
	 * Returns an empty list if the class or interface declares no fields, and for
	 * other kinds of type bindings that do not directly have members.
	 * </p>
	 * 
	 * <p>
	 * The resulting bindings are in no particular order.
	 * </p>
	 * 
	 * @return the list of bindings for the field members of this type, or the empty
	 *         list if this type does not have field members
	 */
	IVariableBinding[] getDeclaredFields();

	/**
	 * Returns a list of method bindings representing all the methods and
	 * constructors declared for this class, interface or annotation type.
	 * <p>
	 * These include public, protected, default (package-private) access, and
	 * private methods Synthetic methods and constructors may or may not be
	 * included. Returns an empty list if the class or interface type declares no
	 * methods or constructors, if the annotation type declares no members, or if
	 * this type binding represents some other kind of type binding. Methods from
	 * binary types that reference unresolvable types may not be included.
	 * </p>
	 * <p>
	 * The resulting bindings are in no particular order.
	 * </p>
	 * 
	 * @return the list of method bindings for the methods and constructors declared
	 *         by this class, interface, or annotation type, or the empty list if
	 *         this type does not declare any methods or constructors
	 */
	IMethodBinding[] getDeclaredMethods();

	/**
	 * Returns the declared modifiers for this class or interface binding as
	 * specified in the original source declaration of the class or interface. The
	 * result may not correspond to the modifiers in the compiled binary, since the
	 * compiler may change them (in particular, for inner class emulation). The
	 * <code>getModifiers</code> method should be used if the compiled modifiers are
	 * needed. Returns -1 if this type does not represent a class or interface.
	 * 
	 * @return the bit-wise or of <code>Modifiers</code> constants
	 * @see Modifiers
	 */
	@Override
	int getModifiers();

	/**
	 * Returns the dimensionality of this array type, or <code>0</code> if this is
	 * not an array type binding.
	 * 
	 * @return the number of dimension of this array type binding, or <code>0</code>
	 *         if this is not an array type
	 */
	int getDimensions();

	/**
	 * Returns the binding representing the element type of this array type, or
	 * <code>null</code> if this is not an array type binding. The element type of
	 * an array is never itself an array type.
	 * 
	 * @return the element type binding, or <code>null</code> if this is not an
	 *         array type
	 */
	ITypeBinding getElementType();

	/**
	 * Returns a list of type bindings representing the direct superinterfaces of
	 * the class, interface, or enum type represented by this type binding.
	 * <p>
	 * If this type binding represents a class or enum type, the return value is an
	 * array containing type bindings representing all interfaces directly
	 * implemented by this class. The number and order of the interface objects in
	 * the array corresponds to the number and order of the interface names in the
	 * <code>implements</code> clause of the original declaration of this type.
	 * </p>
	 * <p>
	 * If this type binding represents an interface, the array contains type
	 * bindings representing all interfaces directly extended by this interface. The
	 * number and order of the interface objects in the array corresponds to the
	 * number and order of the interface names in the <code>extends</code> clause of
	 * the original declaration of this interface.
	 * </p>
	 * <p>
	 * If the class or enum implements no interfaces, or the interface extends no
	 * interfaces, or if this type binding represents an array type, a primitive
	 * type, the null type, a type variable, an annotation type, a wildcard type, or
	 * a capture binding, this method returns an array of length 0.
	 * </p>
	 * 
	 * @return the list of type bindings for the interfaces extended by this class
	 *         or enum, or interfaces extended by this interface, or otherwise the
	 *         empty list
	 */
	ITypeBinding[] getInterfaces();

	/**
	 * Returns the unqualified name of the type represented by this binding if it
	 * has one.
	 * <ul>
	 * <li>For top-level types, member types, and local types, the name is the
	 * simple name of the type. Example: <code>"String"</code> or
	 * <code>"Collection"</code>. Note that the type parameters of a generic type
	 * are not included.</li>
	 * <li>For primitive types, the name is the keyword for the primitive type.
	 * Example: <code>"int"</code>.</li>
	 * <li>For the null type, the name is the string "null".</li>
	 * <li>For anonymous classes, which do not have a name, this method returns an
	 * empty string.</li>
	 * <li>For array types, the name is the unqualified name of the component type
	 * (as computed by this method) followed by "[]". Example:
	 * <code>"String[]"</code>. Note that the component type is never an an
	 * anonymous class.</li>
	 * <li>For type variables, the name is just the simple name of the type variable
	 * (type bounds are not included). Example: <code>"X"</code>.</li>
	 * <li>For type bindings that correspond to particular instances of a generic
	 * type arising from a parameterized type reference, the name is the unqualified
	 * name of the erasure type (as computed by this method) followed by the names
	 * (again, as computed by this method) of the type arguments surrounded by
	 * "&lt;&gt;" and separated by ",". Example:
	 * <code>"Collection&lt;String&gt;"</code>.</li>
	 * <li>For type bindings that correspond to particular instances of a generic
	 * type arising from a raw type reference, the name is the unqualified name of
	 * the erasure type (as computed by this method). Example:
	 * <code>"Collection"</code>.</li>
	 * <li>For wildcard types, the name is "?" optionally followed by a single space
	 * followed by the keyword "extends" or "super" followed a single space followed
	 * by the name of the bound (as computed by this method) when present. Example:
	 * <code>"? extends InputStream"</code>.</li>
	 * <li>Capture types do not have a name. For these types, and array types
	 * thereof, this method returns an empty string.</li>
	 * </ul>
	 * 
	 * @return the unqualified name of the type represented by this binding, or the
	 *         empty string if it has none
	 * @see #getQualifiedName()
	 */
	@Override
	String getName();

	/**
	 * Returns the type associated with this binding.
	 * 
	 * @return the type
	 */
	IEvaluatedType getEvaluatedType();

	/**
	 * Returns the binding for the package in which this type is declared.
	 * 
	 * <p>
	 * The package of a recovered type reference binding is either the package of
	 * the enclosing type, or, if the type name is the name of a
	 * {@linkplain AST#resolveWellKnownType(String) well-known type}, the package of
	 * the matching well-known type.
	 * </p>
	 * 
	 * @return the binding for the package in which this class, interface, enum, or
	 *         annotation type is declared, or <code>null</code> if this type
	 *         binding represents a primitive type, an array type, the null type, a
	 *         type variable, a wildcard type, a capture binding. public
	 *         IPackageBinding getPackage();
	 */

	/**
	 * Returns the type binding for the superclass of the type represented by this
	 * class binding.
	 * <p>
	 * If this type binding represents any class other than the class
	 * <code>java.lang.Object</code>, then the type binding for the direct
	 * superclass of this class is returned. If this type binding represents the
	 * class <code>java.lang.Object</code>, then <code>null</code> is returned.
	 * <p>
	 * Loops that ascend the class hierarchy need a suitable termination test.
	 * Rather than test the superclass for <code>null</code>, it is more transparent
	 * to check whether the class is <code>Object</code>, by comparing whether the
	 * class binding is identical to
	 * <code>ast.resolveWellKnownType("java.lang.Object")</code>.
	 * </p>
	 * <p>
	 * If this type binding represents an interface, an array type, a primitive
	 * type, the null type, a type variable, an enum type, an annotation type, a
	 * wildcard type, or a capture binding then <code>null</code> is returned.
	 * </p>
	 * 
	 * @return the superclass of the class represented by this type binding, or
	 *         <code>null</code> if none
	 * @see AST#resolveWellKnownType(String)
	 */
	ITypeBinding getSuperclass();

	/**
	 * Returns the binding for the type declaration corresponding to this type
	 * binding.
	 * <p>
	 * For parameterized types ({@link #isParameterizedType()}) and most raw types
	 * ({@link #isRawType()}), this method returns the binding for the corresponding
	 * generic type.
	 * </p>
	 * <p>
	 * For raw member types ({@link #isRawType()}, {@link #isMember()}) of a raw
	 * declaring class, the type declaration is a generic or a non-generic type.
	 * </p>
	 * <p>
	 * A different non-generic binding will be returned when one of the declaring
	 * types/methods was parameterized.
	 * </p>
	 * <p>
	 * For other type bindings, this returns the same binding.
	 * </p>
	 * 
	 * @return the type binding
	 * @since 3.1
	 */
	ITypeBinding getTypeDeclaration();

	/**
	 * Returns whether this type binding represents an annotation type.
	 * <p>
	 * Note that an annotation type is always an interface.
	 * </p>
	 * 
	 * @return <code>true</code> if this object represents an annotation type, and
	 *         <code>false</code> otherwise
	 * @since 3.1
	 */
	// boolean isAnnotation();
	/**
	 * Returns whether this type binding represents an array type.
	 * 
	 * @return <code>true</code> if this type binding is for an array type, and
	 *         <code>false</code> otherwise
	 * @see #getElementType()
	 * @see #getDimensions()
	 */
	boolean isArray();

	/**
	 * Returns whether this type binding represents a class type or a recovered
	 * binding.
	 * 
	 * @return <code>true</code> if this object represents a class or a recovered
	 *         binding, and <code>false</code> otherwise
	 */
	boolean isClass();

	/**
	 * Returns whether this type binding represents a class type or a recovered
	 * binding.
	 * 
	 * @return <code>true</code> if this object represents a trait or a recovered
	 *         binding, and <code>false</code> otherwise
	 */
	boolean isTrait();

	/**
	 * Returns whether this type binding represents an interface type.
	 * <p>
	 * Note that an interface can also be an annotation type.
	 * </p>
	 * 
	 * @return <code>true</code> if this object represents an interface, and
	 *         <code>false</code> otherwise
	 */
	boolean isInterface();

	/**
	 * Returns whether this type binding represents the null type.
	 * <p>
	 * The null type is the type of a <code>NullLiteral</code> node.
	 * </p>
	 * 
	 * @return <code>true</code> if this type binding is for the null type, and
	 *         <code>false</code> otherwise
	 */
	boolean isNullType();

	/**
	 * Returns whether this type binding represents a primitive type.
	 * <p>
	 * There are nine predefined type bindings to represent the eight primitive
	 * types and <code>void</code>. These have the same names as the primitive types
	 * that they represent, namely boolean, byte, char, short, int, long, float, and
	 * double, and void.
	 * </p>
	 * 
	 * @return <code>true</code> if this type binding is for a primitive type, and
	 *         <code>false</code> otherwise
	 */
	boolean isPrimitive();

	/**
	 * Returns whether this type is subtype compatible with the given type.
	 * 
	 * @param type
	 *            the type to check compatibility against
	 * @return <code>true</code> if this type is subtype compatible with the given
	 *         type, and <code>false</code> otherwise
	 */
	boolean isSubTypeCompatible(ITypeBinding type);

	/**
	 * Returns whether this type is exact or ambiguous (may relate to two or more
	 * types) i.e. if there are
	 * 
	 * @return <code>true</code> if this type is ambiguous and <code>false</code>
	 *         otherwise
	 */
	boolean isAmbiguous();

	/**
	 * Returns whether this type is known (found) by our binding engine
	 * 
	 * @return <code>true</code> if this type is known and <code>false</code>
	 *         otherwise
	 */
	boolean isUnknown();

	List<IType> getTraitList(boolean isMethod, String classMemberName, boolean includeSuper);

	org.eclipse.dltk.core.IModelElement[] getPHPElements();
}
