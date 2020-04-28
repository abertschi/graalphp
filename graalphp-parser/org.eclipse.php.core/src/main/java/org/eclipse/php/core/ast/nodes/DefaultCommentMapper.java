///*******************************************************************************
// * Copyright (c) 2009-2019 IBM Corporation and others.
// *
// * This program and the accompanying materials are made
// * available under the terms of the Eclipse Public License 2.0
// * which is available at https://www.eclipse.org/legal/epl-2.0/
// *
// * SPDX-License-Identifier: EPL-2.0
// *
// * Contributors:
// *     IBM Corporation - initial API and implementation
// *     Zend Technologies
// *******************************************************************************/
//package org.eclipse.php.core.ast.nodes;
//
//import java.io.IOException;
//import java.io.Reader;
//import java.util.List;
//
//
//import org.eclipse.jface.text.BadLocationException;
//import org.eclipse.jface.text.IDocument;
//import org.eclipse.php.core.ast.visitor.AbstractVisitor;
//import org.eclipse.php.core.ast.visitor.ApplyAll;
//import org.eclipse.php.internal.core.ast.scanner.AstLexer;
//
///**
// * Internal class for associating comments with AST nodes.
// *
// * @since 3.0
// */
//@Deprecated
//public class DefaultCommentMapper {
//	Comment[] comments;
//	AstLexer scanner;
//
//	// extended nodes storage
//	int leadingPtr;
//	ASTNode[] leadingNodes;
//	long[] leadingIndexes;
//	int trailingPtr, lastTrailingPtr;
//	ASTNode[] trailingNodes;
//	long[] trailingIndexes;
//	private IDocument document;
//	static final int STORAGE_INCREMENT = 16;
//
//	/**
//	 * @param table
//	 *            the given table of comments
//	 */
//	DefaultCommentMapper(Comment[] table) {
//		this.comments = table;
//	}
//
//	boolean hasSameTable(Comment[] table) {
//		return this.comments == table;
//	}
//
//	/**
//	 * Get comment of the list which includes a given position
//	 *
//	 * @param position
//	 *            The position belonging to the looked up comment
//	 * @return comment which includes the given position or null if none was found
//	 */
//	Comment getComment(int position) {
//
//		if (this.comments == null) {
//			return null;
//		}
//		int size = this.comments.length;
//		if (size == 0) {
//			return null;
//		}
//		int index = getCommentIndex(0, position, 0);
//		if (index < 0) {
//			return null;
//		}
//		return this.comments[index];
//	}
//
//	/*
//	 * Get the index of comment which contains given position. If there's no
//	 * matching comment, then return depends on exact parameter: = 0: return -1 < 0:
//	 * return index of the comment before the given position > 0: return index of
//	 * the comment after the given position
//	 */
//	private int getCommentIndex(int start, int position, int exact) {
//		if (position == 0) {
//			if (this.comments.length > 0 && this.comments[0].getStart() == 0) {
//				return 0;
//			}
//			return -1;
//		}
//		int bottom = start, top = this.comments.length - 1;
//		int i = 0, index = -1;
//		Comment comment = null;
//		while (bottom <= top) {
//			i = bottom + (top - bottom) / 2;
//			comment = this.comments[i];
//			int commentStart = comment.getStart();
//			if (position < commentStart) {
//				top = i - 1;
//			} else if (position >= (commentStart + comment.getLength())) {
//				bottom = i + 1;
//			} else {
//				index = i;
//				break;
//			}
//		}
//		if (index < 0 && exact != 0) {
//			comment = this.comments[i];
//			if (position < comment.getStart()) {
//				return exact < 0 ? i - 1 : i;
//			} else {
//				return exact < 0 ? i : i + 1;
//			}
//		}
//		return index;
//	}
//
//	/**
//	 * Returns the extended start position of the given node. Unlike
//	 * {@link ASTNode#getStart()} and {@link ASTNode#getLength()}, the extended
//	 * source range may include comments and whitespace immediately before or after
//	 * the normal source range for the node.
//	 *
//	 * @param node
//	 *            the node
//	 * @return the 0-based character index, or <code>-1</code> if no source position
//	 *         information is recorded for this node
//	 * @see #getExtendedLength(ASTNode)
//	 * @since 3.0
//	 */
//	public int getExtendedStartPosition(ASTNode node) {
//		if (this.leadingPtr >= 0) {
//			long range = -1;
//			for (int i = 0; range < 0 && i <= this.leadingPtr; i++) {
//				if (this.leadingNodes[i] == node) {
//					range = this.leadingIndexes[i];
//				}
//			}
//			if (range >= 0) {
//				return this.comments[(int) (range >> 32)].getStart();
//			}
//		}
//		return node.getStart();
//	}
//
//	/*
//	 * Search the line number corresponding to a specific position between the given
//	 * line range (inclusive)
//	 *
//	 * @param position int
//	 *
//	 * @param lineRange size-2 int[]
//	 *
//	 * @return int
//	 */
//	public final int getLineNumber(int position, int[] lineRange) {
//		try {
//			return this.document.getLineOfOffset(position);
//		} catch (Exception e) {
//			throw new IllegalArgumentException("getLineNumber() in DefaultCommentMapper with " + position); //$NON-NLS-1$
//		}
//	}
//
//	/*
//	 * Returns the extended end position of the given node.
//	 */
//	public int getExtendedEnd(ASTNode node) {
//		int end = node.getEnd();
//		if (this.trailingPtr >= 0) {
//			long range = -1;
//			for (int i = 0; range < 0 && i <= this.trailingPtr; i++) {
//				if (this.trailingNodes[i] == node) {
//					range = this.trailingIndexes[i];
//				}
//			}
//			if (range >= 0) {
//				Comment lastComment = this.comments[(int) range];
//				end = lastComment.getEnd();
//			}
//		}
//		return end - 1;
//	}
//
//	/**
//	 * Returns the extended source length of the given node. Unlike
//	 * {@link ASTNode#getStart()} and {@link ASTNode#getLength()}, the extended
//	 * source range may include comments and whitespace immediately before or after
//	 * the normal source range for the node.
//	 *
//	 * @param node
//	 *            the node
//	 * @return a (possibly 0) length, or <code>0</code> if no source position
//	 *         information is recorded for this node
//	 * @see #getExtendedStartPosition(ASTNode)
//	 * @see #getExtendedEnd(ASTNode)
//	 * @since 3.0
//	 */
//	public int getExtendedLength(ASTNode node) {
//		return getExtendedEnd(node) - getExtendedStartPosition(node) + 1;
//	}
//
//	/**
//	 * Return index of first leading comment of a given node.
//	 *
//	 * @param node
//	 * @return index of first leading comment or -1 if node has no leading comment
//	 */
//	int firstLeadingCommentIndex(ASTNode node) {
//		if (this.leadingPtr >= 0) {
//			for (int i = 0; i <= this.leadingPtr; i++) {
//				if (this.leadingNodes[i] == node) {
//					return (int) (this.leadingIndexes[i] >> 32);
//				}
//			}
//		}
//		return -1;
//	}
//
//	/**
//	 * Return index of last trailing comment of a given node.
//	 *
//	 * @param node
//	 * @return index of last trailing comment or -1 if node has no trailing comment
//	 */
//	int lastTrailingCommentIndex(ASTNode node) {
//		if (this.trailingPtr >= 0) {
//			for (int i = 0; i <= this.trailingPtr; i++) {
//				if (this.trailingNodes[i] == node) {
//					return (int) this.trailingIndexes[i];
//				}
//			}
//		}
//		return -1;
//	}
//
//	/*
//	 * Initialize leading and trailing comments tables in whole nodes hierarchy of a
//	 * compilation unit. Scanner is necessary to scan between nodes and comments and
//	 * verify if there's nothing else than white spaces.
//	 */
//	void initialize(Program unit, IDocument document, Reader reader, AstLexer sc) throws IOException {
//
//		if (document == null) {
//			throw new IllegalArgumentException();
//		}
//		this.document = document;
//
//		// Init array pointers
//		this.leadingPtr = -1;
//		this.trailingPtr = -1;
//
//		// Init comments
//		final List<Comment> commentsCollection = unit.comments();
//		if (this.comments == null) {
//			return;
//		}
//		this.comments = commentsCollection.toArray(new Comment[commentsCollection.size()]);
//		int size = this.comments.length;
//		if (size == 0) {
//			return;
//		}
//
//		// Init scanner and start ranges computing
//		this.scanner = sc;
//		// TODO : this.scanner.tokenizeWhiteSpace = true;
//		this.scanner.yyreset(reader);
//		this.scanner.resetCommentList();
//
//		// Start unit visit
//		AbstractVisitor commentVisitor = new CommentMapperVisitor();
//		unit.accept(commentVisitor);
//
//		// Reduce leading arrays if necessary
//		int leadingCount = this.leadingPtr + 1;
//		if (leadingCount > 0 && leadingCount < this.leadingIndexes.length) {
//			System.arraycopy(this.leadingNodes, 0, this.leadingNodes = new ASTNode[leadingCount], 0, leadingCount);
//			System.arraycopy(this.leadingIndexes, 0, this.leadingIndexes = new long[leadingCount], 0, leadingCount);
//		}
//
//		// Reduce trailing arrays if necessary
//		if (this.trailingPtr >= 0) {
//			// remove last remaining unresolved nodes
//			while (this.trailingIndexes[this.trailingPtr] == -1) {
//				this.trailingPtr--;
//				if (this.trailingPtr < 0) {
//					this.trailingIndexes = null;
//					this.trailingNodes = null;
//					break;
//				}
//			}
//
//			// reduce array size
//			int trailingCount = this.trailingPtr + 1;
//			if (trailingCount > 0 && trailingCount < this.trailingIndexes.length) {
//				System.arraycopy(this.trailingNodes, 0, this.trailingNodes = new ASTNode[trailingCount], 0,
//						trailingCount);
//				System.arraycopy(this.trailingIndexes, 0, this.trailingIndexes = new long[trailingCount], 0,
//						trailingCount);
//			}
//		}
//
//		// Release scanner as it's only used during unit visit
//		this.scanner = null;
//	}
//
//	/**
//	 * Search and store node leading comments. Comments are searched in position
//	 * range from previous extended position to node start position. If one or
//	 * several comment are found, returns first comment start position, otherwise
//	 * returns node start position.
//	 * <p>
//	 * Starts to search for first comment before node start position and return if
//	 * none was found...
//	 * </p>
//	 * <p>
//	 * When first comment is found before node, goes up in comment list until one of
//	 * following conditions becomes true:
//	 * <ol>
//	 * <li>comment end is before previous end</li>
//	 * <li>comment start and previous end is on the same line but not on same line
//	 * of node start</li>
//	 * <li>there's other than white characters between current node and comment</li>
//	 * <li>TODO : there's more than 1 line between current node and comment</li>
//	 * </ol>
//	 * If some comment have been found, then no token should be on on the same line
//	 * before, so remove all comments which do not verify this assumption.
//	 * </p>
//	 * <p>
//	 * If finally there's leading still comments, then stores indexes of the first
//	 * and last one in leading comments table.
//	 */
//	int storeLeadingComments(ASTNode node, int previousEnd, int[] parentLineRange) {
//		// Init extended position
//		int nodeStart = node.getStart();
//		int extended = nodeStart;
//
//		// Get line of node start position
//		int previousEndLine = getLineNumber(previousEnd, parentLineRange);
//		int nodeStartLine = getLineNumber(nodeStart, parentLineRange);
//
//		// Find first comment index
//		int idx = getCommentIndex(0, nodeStart, -1);
//		if (idx == -1) {
//			return nodeStart;
//		}
//
//		// Look after potential comments
//		int startIdx = -1;
//		int endIdx = idx;
//		int previousStart = nodeStart;
//		while (idx >= 0 && previousStart >= previousEnd) {
//			// Verify for each comment that there's only white spaces between
//			// end and start of {following comment|node}
//			Comment comment = this.comments[idx];
//			int commentStart = comment.getStart();
//			int end = commentStart + comment.getLength() - 1;
//			int commentLine = getLineNumber(commentStart, parentLineRange);
//			if (end <= previousEnd || (commentLine == previousEndLine && commentLine != nodeStartLine)) {
//				// stop search on condition 1) and 2)
//				break;
//			} else if ((end + 1) < previousStart) { // may be equals => then no
//													// scan is necessary
//				try {
//					resetTo(end + 1, previousStart);
//					this.scanner.next_token();
//					String token = this.scanner.yytext();
//					if (token != null && StringUtils.isNotBlank(token)) {
//						// stop search on condition 3)
//						// if first comment fails, then there's no extended
//						// position in fact
//						if (idx == endIdx) {
//							return nodeStart;
//						}
//						break;
//					}
//				} catch (Exception e) {
//					// Should not happen, but return no extended position...
//					assert false;
//					return nodeStart;
//				}
//			}
//			// Store previous infos
//			previousStart = commentStart;
//			startIdx = idx--;
//		}
//		if (startIdx != -1) {
//			// Store leading comments indexes
//			if (startIdx <= endIdx) {
//				if (++this.leadingPtr == 0) {
//					this.leadingNodes = new ASTNode[STORAGE_INCREMENT];
//					this.leadingIndexes = new long[STORAGE_INCREMENT];
//				} else if (this.leadingPtr == this.leadingNodes.length) {
//					int newLength = (this.leadingPtr * 3 / 2) + STORAGE_INCREMENT;
//					System.arraycopy(this.leadingNodes, 0, this.leadingNodes = new ASTNode[newLength], 0,
//							this.leadingPtr);
//					System.arraycopy(this.leadingIndexes, 0, this.leadingIndexes = new long[newLength], 0,
//							this.leadingPtr);
//				}
//				this.leadingNodes[this.leadingPtr] = node;
//				this.leadingIndexes[this.leadingPtr] = (((long) startIdx) << 32) + endIdx;
//				extended = this.comments[endIdx].getStart();
//			}
//		}
//		return extended;
//	}
//
//	private void resetTo(int begin, int end) throws IOException {
//		if (scanner == null) {
//			throw new IllegalArgumentException("null at resetTo(int begin, int end)"); //$NON-NLS-1$
//		}
//		this.scanner.yyreset(new IntervalDocumentReader(this.document, begin, end));
//		this.scanner.setInScriptingState();
//	}
//
//	/**
//	 * Search and store node trailing comments. Comments are searched in position
//	 * range from node end position to specified next start. If one or several
//	 * comment are found, returns last comment end position, otherwise returns node
//	 * end position.
//	 * <p>
//	 * Starts to search for first comment after node end position and return if none
//	 * was found...
//	 * </p>
//	 * <p>
//	 * When first comment is found after node, goes down in comment list until one
//	 * of following conditions becomes true:
//	 * <ol>
//	 * <li>comment start is after next start</li>
//	 * <li>there's other than white characters between current node and comment</li>
//	 * <li>TODO there's more than 1 line between current node and comment</li>
//	 * </ol>
//	 * If at least potential comments have been found, then all of them has to be
//	 * separated from following node. So, remove all comments which do not verify
//	 * this assumption. Note that this verification is not applicable on last node.
//	 * </p>
//	 * <p>
//	 * If finally there's still trailing comments, then stores indexes of the first
//	 * and last one in trailing comments table.
//	 */
//	int storeTrailingComments(ASTNode node, int nextStart, boolean lastChild, int[] parentLineRange) {
//
//		// Init extended position
//		int nodeEnd = node.getEnd() - 1;
//		if (nodeEnd == nextStart) {
//			// special case for last child of its parent
//			if (++this.trailingPtr == 0) {
//				this.trailingNodes = new ASTNode[STORAGE_INCREMENT];
//				this.trailingIndexes = new long[STORAGE_INCREMENT];
//				this.lastTrailingPtr = -1;
//			} else if (this.trailingPtr == this.trailingNodes.length) {
//				int newLength = (this.trailingPtr * 3 / 2) + STORAGE_INCREMENT;
//				System.arraycopy(this.trailingNodes, 0, this.trailingNodes = new ASTNode[newLength], 0,
//						this.trailingPtr);
//				System.arraycopy(this.trailingIndexes, 0, this.trailingIndexes = new long[newLength], 0,
//						this.trailingPtr);
//			}
//			this.trailingNodes[this.trailingPtr] = node;
//			this.trailingIndexes[this.trailingPtr] = -1;
//			return nodeEnd;
//		}
//		int extended = nodeEnd;
//
//		// Get line number
//		int nodeEndLine = getLineNumber(nodeEnd, parentLineRange);
//
//		// Find comments range index
//		int idx = getCommentIndex(0, nodeEnd, 1);
//		if (idx == -1) {
//			return nodeEnd;
//		}
//
//		// Look after potential comments
//		int startIdx = idx;
//		int endIdx = -1;
//		int length = this.comments.length;
//		int commentStart = extended + 1;
//		int previousEnd = nodeEnd + 1;
//		int sameLineIdx = -1;
//		while (idx < length && commentStart < nextStart) {
//			// get comment and leave if next starting position has been reached
//			Comment comment = this.comments[idx];
//			commentStart = comment.getStart();
//			// verify that there's nothing else than white spaces between
//			// node/comments
//			if (commentStart >= nextStart) {
//				// stop search on condition 1)
//				break;
//			} else if (previousEnd < commentStart) {
//				try {
//					resetTo(previousEnd, commentStart);
//					this.scanner.next_token();
//					String token = this.scanner.yytext();
//					if (token != null && StringUtils.isNotBlank(token)) {
//						// stop search on condition 2)
//						// if first index fails, then there's no extended
//						// position in fact...
//						if (idx == startIdx) {
//							return nodeEnd;
//						}
//						// otherwise we get the last index of trailing comment
//						// => break
//						break;
//					}
//				} catch (Exception e) {
//					// Should not happen, but return no extended position...
//					assert false;
//					return nodeEnd;
//				}
//			}
//			// Store index if we're on the same line than node end
//			int commentLine = getLineNumber(commentStart, parentLineRange);
//			if (commentLine == nodeEndLine) {
//				sameLineIdx = idx;
//			}
//			// Store previous infos
//			previousEnd = commentStart + comment.getLength();
//			endIdx = idx++;
//		}
//		if (endIdx != -1) {
//			// Verify that following node start is separated
//			if (!lastChild) {
//				int nextLine = getLineNumber(nextStart, parentLineRange);
//				int previousLine = getLineNumber(previousEnd, parentLineRange);
//				if ((nextLine - previousLine) <= 1) {
//					if (sameLineIdx == -1) {
//						return nodeEnd;
//					}
//					endIdx = sameLineIdx;
//				}
//			}
//			// Store trailing comments indexes
//			if (++this.trailingPtr == 0) {
//				this.trailingNodes = new ASTNode[STORAGE_INCREMENT];
//				this.trailingIndexes = new long[STORAGE_INCREMENT];
//				this.lastTrailingPtr = -1;
//			} else if (this.trailingPtr == this.trailingNodes.length) {
//				int newLength = (this.trailingPtr * 3 / 2) + STORAGE_INCREMENT;
//				System.arraycopy(this.trailingNodes, 0, this.trailingNodes = new ASTNode[newLength], 0,
//						this.trailingPtr);
//				System.arraycopy(this.trailingIndexes, 0, this.trailingIndexes = new long[newLength], 0,
//						this.trailingPtr);
//			}
//			this.trailingNodes[this.trailingPtr] = node;
//			long nodeRange = (((long) startIdx) << 32) + endIdx;
//			this.trailingIndexes[this.trailingPtr] = nodeRange;
//			// Compute new extended end
//			extended = this.comments[endIdx].getEnd() - 1;
//			// Look for children unresolved extended end
//			ASTNode previousNode = node;
//			int ptr = this.trailingPtr - 1; // children extended end were stored
//											// before
//			while (ptr >= 0) {
//				long range = this.trailingIndexes[ptr];
//				if (range != -1) {
//					break; // there's no more unresolved nodes
//				}
//				ASTNode unresolved = this.trailingNodes[ptr];
//				if (previousNode != unresolved.getParent()) {
//					break; // we're no longer in node ancestor hierarchy
//				}
//				this.trailingIndexes[ptr] = nodeRange;
//				previousNode = unresolved;
//				ptr--; // get previous node
//			}
//			// Remove remaining unresolved nodes
//			if (ptr > this.lastTrailingPtr) {
//				int offset = ptr - this.lastTrailingPtr;
//				for (int i = ptr + 1; i <= this.trailingPtr; i++) {
//					this.trailingNodes[i - offset] = this.trailingNodes[i];
//					this.trailingIndexes[i - offset] = this.trailingIndexes[i];
//				}
//				this.trailingPtr -= offset;
//			}
//			this.lastTrailingPtr = this.trailingPtr;
//		}
//		return extended;
//	}
//
//	class CommentMapperVisitor extends ApplyAll {
//
//		ASTNode topSiblingParent = null;
//		ASTNode[] siblings = new ASTNode[10];
//		int[][] parentLineRange = new int[10][];
//		int siblingPtr = -1;
//
//		@Override
//		protected boolean apply(ASTNode node) {
//
//			// Get default previous end
//			ASTNode parent = node.getParent();
//			int previousEnd = parent.getStart();
//
//			// Look for sibling node
//			ASTNode sibling = parent == this.topSiblingParent ? (ASTNode) this.siblings[this.siblingPtr] : null;
//			if (sibling != null) {
//				// Found one previous sibling, so compute its trailing comments
//				// using current node start position
//				try {
//					previousEnd = storeTrailingComments(sibling, node.getStart(), false,
//							this.parentLineRange[this.siblingPtr]);
//				} catch (Exception ex) {
//					// Give up extended ranges at this level if unexpected
//					// exception happens...
//				}
//			}
//
//			// Stop visit for malformed node (see bug
//			// https://bugs.eclipse.org/bugs/show_bug.cgi?id=84049)
//			if (node.getType() == ASTNode.AST_ERROR) {
//				return false;
//			}
//
//			// Compute leading comments for current node
//			int[] previousLineRange = this.siblingPtr > -1 ? this.parentLineRange[this.siblingPtr]
//					: new int[] { 1, DefaultCommentMapper.this.document.getNumberOfLines() };
//			try {
//				storeLeadingComments(node, previousEnd, previousLineRange);
//			} catch (Exception ex) {
//				// Give up extended ranges at this level if unexpected exception
//				// happens...
//			}
//
//			// Store current node as waiting sibling for its parent
//			if (this.topSiblingParent != parent) {
//				if (this.siblings.length == ++this.siblingPtr) {
//					System.arraycopy(this.siblings, 0, this.siblings = new ASTNode[this.siblingPtr * 2], 0,
//							this.siblingPtr);
//					System.arraycopy(this.parentLineRange, 0, this.parentLineRange = new int[this.siblingPtr * 2][], 0,
//							this.siblingPtr);
//				}
//				if (this.topSiblingParent == null) {
//					// node is a CompilationUnit
//					this.parentLineRange[this.siblingPtr] = previousLineRange;
//				} else {
//					int parentStart = parent.getStart();
//					int firstLine = getLineNumber(parentStart, previousLineRange);
//					int lastLine = getLineNumber(parentStart + parent.getLength() - 1, previousLineRange);
//					if (this.parentLineRange[this.siblingPtr] == null) {
//						this.parentLineRange[this.siblingPtr] = new int[] { firstLine, lastLine };
//					} else {
//						int[] lineRange = this.parentLineRange[this.siblingPtr];
//						lineRange[0] = firstLine;
//						lineRange[1] = lastLine;
//					}
//				}
//				this.topSiblingParent = parent;
//			}
//			this.siblings[this.siblingPtr] = node;
//
//			// We're always ok to visit sub-levels
//			return true;
//		}
//
//		@Override
//		public void endVisitNode(ASTNode node) {
//
//			// Look if a child node is waiting for trailing comments computing
//			ASTNode sibling = this.topSiblingParent == node ? (ASTNode) this.siblings[this.siblingPtr] : null;
//			if (sibling != null) {
//				try {
//					storeTrailingComments(sibling, node.getEnd() - 1, true, this.parentLineRange[this.siblingPtr]);
//				} catch (Exception ex) {
//					// Give up extended ranges at this level if unexpected
//					// exception happens...
//				}
//			}
//			// Remove sibling if needed
//			if (this.topSiblingParent != null /* not a CompilationUnit */
//					&& this.topSiblingParent == node) {
//				this.siblingPtr--;
//				this.topSiblingParent = node.getParent();
//			}
//		}
//
//		@Override
//		public boolean visit(Program node) {
//			return true;
//		}
//
//		@Override
//		public boolean visit(Comment node) {
//			// don't visit comments
//			return false;
//		}
//	}
//
//	/**
//	 * Returns a stream that represents the document
//	 *
//	 * @param StructuredDocument
//	 * @param start
//	 * @param length
//	 */
//	public static class IntervalDocumentReader extends Reader {
//
//		private static final String BAD_LOCATION_ERROR = "Bad location error "; //$NON-NLS-1$
//
//		final private IDocument parent;
//		private int startPhpRegion;
//		final private int endPhpRegion;
//
//		public IntervalDocumentReader(final IDocument parent, final int startPhpRegion, final int endPhpRegion) {
//			this.parent = parent;
//			this.startPhpRegion = startPhpRegion;
//			this.endPhpRegion = endPhpRegion;
//		}
//
//		@Override
//		public int read() throws IOException {
//			try {
//				return startPhpRegion < endPhpRegion ? parent.getChar(startPhpRegion++) : -1;
//			} catch (BadLocationException e) {
//				throw new IOException(BAD_LOCATION_ERROR + startPhpRegion);
//			}
//		}
//
//		@Override
//		public int read(char[] b, int off, int len) throws IOException {
//			if (b == null) {
//				throw new NullPointerException();
//			} else if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) > b.length) || ((off + len) < 0)) {
//				throw new IndexOutOfBoundsException();
//			} else if (len == 0) {
//				return 0;
//			}
//
//			int c = read();
//			if (c == -1) {
//				return -1;
//			}
//			b[off] = (char) c;
//
//			int i = 1;
//			for (; i < len; i++) {
//				c = read();
//				if (c == -1) {
//					break;
//				}
//				b[off + i] = (char) c;
//			}
//			return i;
//		}
//
//		@Override
//		public void close() throws IOException {
//		}
//	}
//
//}
