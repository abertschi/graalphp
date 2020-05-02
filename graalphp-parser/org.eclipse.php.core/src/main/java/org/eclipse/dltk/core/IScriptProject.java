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
 * A script project represents a view of a project resource in terms of DLTK
 * elements such as script folders, types, methods and fields. A project may
 * contain several project fragments, which contain script folders. A project
 * fragment corresponds to an underlying folder or an archive (like JAR).
 * <p>
 * Each script project has a buildpath, defining which folders contain source
 * code and where required libraries are located. <s>Each script project also
 * has an output location, defining where the builder writes output files.</s> A
 * project that references folders in another project can access the folders by
 * including the required project in a buildpath entry. The DLTK model will
 * present the source elements in the required project; <s>when building, the
 * compiler will use the corresponding generated class files from the required
 * project's output location(s))</s>. The buildpath format is a sequence of
 * buildpath entries describing the location and contents of project fragments .
 * </p>
 * Script project elements need to be opened before they can be navigated or
 * manipulated. The children of a script project are the project fragments that
 * are defined by the buildpath and contained in this project (in other words,
 * it does not include project fragments for other projects).
 * 
 * The children (i.e. the project fragments) appear in the order they are
 * defined by the buildpath. </p>
 * <p>
 * An instance of one of these handles can be created via
 * <code>DLTKCore.create(project)</code>.
 * </p>
 * 
 * @see DLTKCore#create(org.eclipse.core.resources.IProject)
 * @see IBuildpathEntry
 * @noimplement This interface is not intended to be implemented by clients.
 */
@Deprecated
public interface IScriptProject extends IModelElement, IOpenable, IParent {

}
