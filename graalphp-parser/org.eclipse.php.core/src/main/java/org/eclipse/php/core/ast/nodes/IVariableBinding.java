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

/**
 * A variable binding represents either a field of a class or interface, or a
 * local variable declaration (including formal parameters, local variables, and
 * exception variables) or a global variable.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 *
 * @see ITypeBinding#getDeclaredFields()
 * @since 2.0
 */
@Deprecated
public interface IVariableBinding extends IBinding {

	/**
	 * Returns whether this binding is for a field. Note that this method returns
	 * <code>true</code> for constants. This method returns <code>false</code> for
	 * local variables.
	 * 
	 * @return <code>true</code> if this is the binding for a field, and
	 *         <code>false</code> otherwise
	 */
	public boolean isField();

	/**
	 * Returns whether this binding corresponds to a parameter.
	 * 
	 * @return <code>true</code> if this is the binding for a parameter, and
	 *         <code>false</code> otherwise
	 * @since 3.2
	 */
	public boolean isParameter();

	/**
	 * Returns whether this binding corresponds to a global.
	 * 
	 * @return <code>true</code> if this is the binding for a global, and
	 *         <code>false</code> otherwise
	 * @since 3.2
	 */
	public boolean isGlobal();

	/**
	 * Returns whether this binding corresponds to a local.
	 * 
	 * @return <code>true</code> if this is the binding for a local, and
	 *         <code>false</code> otherwise
	 * @since 3.2
	 */
	public boolean isLocal();

	/**
	 * Returns the name of the field or local variable declared in this binding. The
	 * name is always a simple identifier.
	 * 
	 * @return the name of this field or local variable
	 */
	@Override
	public String getName();

	/**
	 * Returns the binding for the type of this field or local variable.
	 * 
	 * @return the binding for the type of this field or local variable
	 */
	public ITypeBinding getType();

	/**
	 * Returns a small integer variable id for this variable binding.
	 * <p>
	 * <b>Local variables inside methods:</b> Local variables (and parameters)
	 * declared within a single method are assigned ascending ids in normal code
	 * reading order; var1.getVariableId()&lt;var2.getVariableId() means that var1
	 * is declared before var2.
	 * </p>
	 * <p>
	 * <b>Globals:</b> Global variables declared in a program's scope are assigned
	 * ascending ids in normal code reading order.
	 * </p>
	 * <p>
	 * <b>Fields:</b> Fields declared as members of a type are assigned ascending
	 * ids in normal code reading order;
	 * field1.getVariableId()&lt;field2.getVariableId() means that field1 is
	 * declared before field2.
	 * </p>
	 * 
	 * @return a small non-negative variable id
	 */
	public int getVariableId();

	/**
	 * Returns this binding's constant value if it has one. Some variables may have
	 * a value computed at compile-time. If the type of the value is a primitive
	 * type, the result is the boxed equivalent (i.e., int returned as an
	 * <code>Integer</code>). If the type of the value is <code>String</code> , the
	 * result is the string itself. If the variable has no compile-time computed
	 * value, the result is <code>null</code>. (Note: compile-time constant
	 * expressions cannot denote <code>null</code>; JLS2 15.28.). The result is
	 * always <code>null</code> for enum constants.
	 * 
	 * @return the constant value, or <code>null</code> if none
	 * @since 3.0
	 */
	public Object getConstantValue();

	/**
	 * Returns the method binding representing the method containing the scope in
	 * which this local variable is declared.
	 * <p>
	 * The declaring method of a method formal parameter is the method itself. For a
	 * local variable declared somewhere within the body of a method, the declaring
	 * method is the enclosing method. When local or anonymous classes are involved,
	 * the declaring method is the innermost such method. There is no declaring
	 * method for a field, or for a local variable declared in a static or instance
	 * initializer; this method returns <code>null</code> in those cases.
	 * </p>
	 * 
	 * @return the binding of the method or constructor that declares this local
	 *         variable, or <code>null</code> if none
	 * @since 3.1
	 */
	public IFunctionBinding getDeclaringFunction();

	/**
	 * Returns the type binding representing the class or interface that declares
	 * this field.
	 * <p>
	 * The declaring class of a field is the class or interface of which it is a
	 * member. Local variables have no declaring class. The field length of an array
	 * type has no declaring class.
	 * </p>
	 * 
	 * @return the binding of the class or interface that declares this field, or
	 *         <code>null</code> if none
	 */
	public ITypeBinding getDeclaringClass();
}
