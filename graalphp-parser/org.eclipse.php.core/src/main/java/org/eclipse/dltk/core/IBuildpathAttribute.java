/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * A buildpath attribute defines a name/value pair that can be persisted with a buildpath entry. Such an attribute
 * can be created using the factory method {@link DLTKCore#newBuildpathAttribute(String, String) newBuildpathAttribute(String name, String value)}.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see DLTKCore#newContainerEntry(
 *			org.eclipse.core.runtime.IPath containerPath, 
 *			IAccessRule[] accessRules, 
 *			IBuildpathAttribute[] extraAttributes,
 *			boolean isExported)
 * @see DLTKCore#newLibraryEntry(
 *			org.eclipse.core.runtime.IPath path,
 *			org.eclipse.core.runtime.IPath sourceAttachmentPath,
 *			org.eclipse.core.runtime.IPath sourceAttachmentRootPath,
 *			IAccessRule[] accessRules, 
 *			IBuildpathAttribute[] extraAttributes,
 *			boolean isExported)
 * @see DLTKCore#newProjectEntry(
 *			org.eclipse.core.runtime.IPath path, 
 *			IAccessRule[] accessRules, 
 *			boolean combineAccessRestrictions,
 *			IBuildpathAttribute[] extraAttributes,
 *			boolean isExported)	
 * @see DLTKCore#newSourceEntry(
 * 			org.eclipse.core.runtime.IPath path, 
 * 			org.eclipse.core.runtime.IPath[] inclusionPatterns, 
 * 			org.eclipse.core.runtime.IPath[] exclusionPatterns, 
 * 			org.eclipse.core.runtime.IPath specificOutputLocation, 
 * 			IBuildpathAttribute[] extraAttributes)
 * @see DLTKCore#newVariableEntry(
 *			org.eclipse.core.runtime.IPath variablePath,
 *			org.eclipse.core.runtime.IPath variableSourceAttachmentPath,
 *			org.eclipse.core.runtime.IPath variableSourceAttachmentRootPath,
 *			IAccessRule[] accessRules, 
 *			IBuildpathAttribute[] extraAttributes,
 *			boolean isExported)
	 *
 */
public interface IBuildpathAttribute {
	/**
	 * Constant for the name of the optional attribute. The possible values
	 * for this attribute are <code>"true"</code> or <code>"false"</code>. 
	 * When not present, <code>"false"</code> is assumed.
	 * If the value of this attribute is <code>"true"</code>, the buildpath entry
	 * is optional. If the underlying resource or archive file doesn't exist, no error
	 * is reported and the buildpath entry is ignored.
	 * 
	 *
	 */
	String OPTIONAL = "optional"; //$NON-NLS-1$
	
	/**
	 * Returns the name of this buildpath attribute.
	 * 
	 * @return the name of this buildpath attribute.
	 *
	 */
	String getName();
	
	/**
	 * Returns the value of this buildpath attribute.
	 * 
	 * @return the value of this buildpath attribute.
	 *
	 */
	String getValue();

}
