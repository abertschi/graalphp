/*******************************************************************************
 * Copyright (c) 2013 NumberFour AG
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     NumberFour AG - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.core;

/**
 * The marker interface for the custom {@link CompletionProposal} flags, the
 * recommended way to create instances of this interface is {@code enum}.
 * 
 * @see CompletionProposal#setFlag(CompletionProposalFlag)
 * @see CompletionProposal#hasFlag(CompletionProposalFlag)
 */
public interface CompletionProposalFlag {
}
