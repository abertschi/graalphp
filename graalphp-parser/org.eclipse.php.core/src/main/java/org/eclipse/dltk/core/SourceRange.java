/*******************************************************************************
 * Copyright (c) 2000, 2016 IBM Corporation and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 *******************************************************************************/
package org.eclipse.dltk.core;

public class SourceRange implements ISourceRange {

	public static final ISourceRange UNKNOWN_RANGE = new SourceRange(-1, 0);

	private final int offset, length;

	public SourceRange(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}

	public SourceRange(ISourceRange range) {
		this(range.getOffset(), range.getLength());
	}

	public SourceRange(ISourceNode node) {
		this.offset = node.start();
		this.length = node.end() - this.offset;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		int end = getOffset() + getLength() - 1;
		return "[" + getOffset() + "src/main" + end + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	@Override
	public int hashCode() {
		return length ^ offset;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ISourceRange) {
			final ISourceRange other = (ISourceRange) obj;
			return other.getOffset() == offset && other.getLength() == length;
		}
		return false;
	}

	/**
	 * Helper method that answers whether a valid source range is available in
	 * the given ISourceRange.
	 * 
	 * @param range
	 *            a source range, can be <code>null</code>
	 * @return <code>true</code> if range is not <code>null</code> and is not
	 *         {@link #UNKNOWN_RANGE}
	 */
	public static boolean isAvailable(ISourceRange range) {
		return range != null && range != UNKNOWN_RANGE;
	}

	/**
	 * Create a new range by adding offset to the specified range offset.
	 * 
	 * @param range
	 * @param offset
	 * @return
	 */
	public static ISourceRange offset(ISourceRange range, int offset) {
		if (isAvailable(range)) {
			return new SourceRange(range.getOffset() + offset,
					range.getLength());
		} else {
			return range;
		}
	}
}
