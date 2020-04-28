/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * Represents a local variable declared in a method or an initializer.
 * <code>ILocalVariable</code> are pseudo-elements created as the result of a
 * <code>ICodeAssist.codeSelect(...)</code> operation. They are not part of the
 * Java model (<code>exists()</code> returns whether the parent exists rather
 * than whether the local variable exists in the parent) and they are not
 * included in the children of an <code>IMethod</code> or an
 * <code>IInitializer</code>.
 * <p>
 * In particular such a pseudo-element should not be used as a handle. For
 * example its name range won't be updated if the underlying source changes.
 * </p>
 * 
 * @since 3.0
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ILocalVariable extends IModelElement, ISourceReference {

	/**
	 * Returns the name of this local variable.
	 * 
	 * @return the name of this local variable.
	 */
	@Override
	String getElementName();

	/**
	 * Returns the source range of this local variable's name.
	 * 
	 * @return the source range of this local variable's name
	 */
	@Override
	ISourceRange getNameRange();

	/**
	 * Returns the type signature of this local variable.
	 * <p>
	 * The type signature may be either unresolved (for source types) or
	 * resolved (for binary types), and either basic (for basic types) or rich
	 * (for parameterized types).
	 * </p>
	 * 
	 * @return the type signature of this local variable.
	 * @see Signature
	 */
	String getType();
}
