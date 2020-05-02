/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * An element changed listener receives notification of changes to script
 * elements maintained by the script model.
 * <p>
 * This interface may be implemented by clients.
 * </p>
 */
public interface IElementChangedListener {

	/**
	 * Notifies that one or more attributes of one or more script elements have
	 * changed. The specific details of the change are described by the given
	 * event.
	 * 
	 * @param event
	 *            the change event
	 */
	public void elementChanged(ElementChangedEvent event);
}
