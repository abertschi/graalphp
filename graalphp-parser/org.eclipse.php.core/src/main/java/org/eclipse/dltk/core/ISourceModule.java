/*******************************************************************************
 * Copyright (c) 2005, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.core;



/**
 * Represents an entire source module (source file). Source module elements need
 * to be opened before they can be navigated or manipulated.
 * 
 * The children are of type {@link IPackageDeclaration},
 * {@link IImportContainer}, {@link IType}, {@link IMethod} and {@link IField}
 * and appear in the order in which they are declared in the source.
 * 
 * The children appear in the order in which they are declared in the source. If
 * a file cannot be parsed, its structure remains unknown. Use
 * {@link IModelElement#isStructureKnown} to determine whether this is the case.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 */
@Deprecated
public interface ISourceModule extends IModule, ISourceReference,
		ISourceManipulation {

}