/*******************************************************************************
 * Copyright (c) 2009-2019 IBM Corporation and others.
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

import java.io.IOException;
import java.io.Reader;
import java.util.*;

//import org.eclipse.core.resources.IMarker;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.CoreException;
//import org.eclipse.dltk.compiler.problem.DefaultProblem;
//import org.eclipse.dltk.compiler.problem.DefaultProblemIdentifier;
//import org.eclipse.dltk.compiler.problem.IProblem;
//import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.core.ISourceModule;
//import org.eclipse.dltk.core.ISourceReference;
//import org.eclipse.dltk.core.ModelException;
//import org.eclipse.jface.text.IDocument;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.match.ASTMatcher;
import org.eclipse.php.core.ast.visitor.ApplyAll;
import org.eclipse.php.core.ast.visitor.Visitor;
//import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.core.ast.locator.Locator;
//import org.eclipse.php.internal.core.ast.scanner.AstLexer;
//import org.eclipse.php.internal.core.compiler.ast.parser.PHPProblemIdentifier;
//import org.eclipse.text.edits.TextEdit;

/**
 * The AST root node for PHP program (meaning a PHP file). The program holds
 * array of statements such as Class, Function and evaluation statement. The
 * program also holds the PHP file comments.
 * 
 * @author Moshe S. & Roy G. 2007
 */
public class Program extends ASTNode {

	/**
	 * Statements array of php program
	 */
	private final ASTNode.NodeList<Statement> statements = new ASTNode.NodeList<>(STATEMENTS_PROPERTY);

	/**
	 * Comments array of the php program
	 */
	private final ASTNode.NodeList<Comment> comments = new ASTNode.NodeList<>(COMMENTS_PROPERTY);

	private Map<NamespaceDeclaration, List<UseStatement>> fUseStatements;

	private List<NamespaceDeclaration> fNamespaceDeclarations;

	/**
	 * The structural property of this node type.
	 */
	public static final ChildListPropertyDescriptor STATEMENTS_PROPERTY = new ChildListPropertyDescriptor(Program.class,
			"statements", Statement.class, NO_CYCLE_RISK); //$NON-NLS-1$
	public static final ChildListPropertyDescriptor COMMENTS_PROPERTY = new ChildListPropertyDescriptor(Program.class,
			"comments", Comment.class, NO_CYCLE_RISK); //$NON-NLS-1$

	/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}), or null if uninitialized.
	 */
	private static final List<StructuralPropertyDescriptor> PROPERTY_DESCRIPTORS;

	static {
		List<StructuralPropertyDescriptor> properyList = new ArrayList<>(1);
		properyList.add(STATEMENTS_PROPERTY);
		properyList.add(COMMENTS_PROPERTY);
		PROPERTY_DESCRIPTORS = Collections.unmodifiableList(properyList);
	}

	/**
	 * The comment mapper, or <code>null</code> if none; initially
	 * <code>null</code>.
	 * 
	 * @since 3.0
	 */
//	private DefaultCommentMapper commentMapper = null;

	/**
	 * The php type root (an <code>org.eclipse.dltk.ISourceModule</code>) this
	 * source module was created from, or <code>null</code> if it was not created
	 * from a php type root.
	 */
	private ISourceModule sourceModule = null;

	/**
	 * Line end table. If <code>lineEndTable[i] == p</code> then the line number
	 * <code>i+1</code> ends at character position <code>p</code>. Except for the
	 * last line, the positions are that of the last character of the line
	 * delimiter. For example, the source string <code>A\nB\nC</code> has line end
	 * table {1, 3} (if \n is one character).
	 */
	private int[] lineEndTable = {};

	public Program(int start, int end, AST ast, List<Statement> statements, List<Comment> commentList) {
		super(start, end, ast);
		if (statements == null || comments == null) {
			throw new IllegalArgumentException();
		}
		this.statements.addAll(statements);
		this.comments.addAll(commentList);
	}

	public Program(AST ast) {
		super(ast);
	}

	/**
	 * Returns a list of the comments encountered while parsing this source program.
	 * <p>
	 * Since the PHP language allows comments to appear most anywhere in the source
	 * text, it is problematic to locate comments in relation to the structure of an
	 * AST. The one exception is doc comments which, by convention, immediately
	 * precede type, field, and method declarations; these comments are located in
	 * the AST by {@link BodyDeclaration#getPhpdoc BodyDeclaration.getPhpdoc}. Other
	 * comments do not show up in the AST. The table of comments is provided for
	 * clients that need to find the source ranges of all comments in the original
	 * source string. It includes entries for comments of all kinds (line, block,
	 * and doc), arranged in order of increasing source position.
	 * </p>
	 * <p>
	 * Note on comment parenting: The {@link ASTNode#getParent() getParent()} of a
	 * doc comment associated with a body declaration is the body declaration node;
	 * for these comment nodes {@link ASTNode#getRoot() getRoot()} will return the
	 * program (assuming an unmodified AST) reflecting the fact that these nodes are
	 * property located in the AST for the compilation unit. However, for other
	 * comment nodes, {@link ASTNode#getParent() getParent()} will return
	 * <code>null</code>, and {@link ASTNode#getRoot() getRoot()} will return the
	 * comment node itself, indicating that these comment nodes are not directly
	 * connected to the AST for the compilation unit. The
	 * {@link Comment#getAlternateRoot Comment.getAlternateRoot} method provides a
	 * way to navigate from a comment to its source program
	 * </p>
	 * <p>
	 * Clients cannot modify the resulting list.
	 * </p>
	 * 
	 * @return an unmodifiable list of comments in increasing order of source start
	 *         position, or <code>null</code> if comment information for this
	 *         compilation unit is not available
	 * @see ASTParser
	 * @since 3.0
	 */
	public List<Comment> comments() {
		return comments;
	}

	/**
	 * see {@link #sourceModule}
	 * 
	 * @param typeRoot
	 */
	public void setSourceModule(ISourceModule typeRoot) {
		this.sourceModule = typeRoot;
	}

	/**
	 * see {@link #sourceModule} return {@link #sourceModule}
	 */
	public ISourceModule getSourceModule() {
		return sourceModule;
	}

	@Override
	public void accept0(Visitor visitor) {
		final boolean visit = visitor.visit(this);
		if (visit) {
			childrenAccept(visitor);
		}
		visitor.endVisit(this);
	}

	@Override
	public void childrenAccept(Visitor visitor) {
		for (ASTNode node : this.statements) {
			node.accept(visitor);
		}
		for (ASTNode node : this.comments) {
			node.accept(visitor);
		}
	}

	@Override
	public void traverseTopDown(Visitor visitor) {
		accept(visitor);
		for (ASTNode node : this.statements) {
			node.traverseTopDown(visitor);
		}
		for (ASTNode node : this.comments) {
			node.traverseTopDown(visitor);
		}
	}

	@Override
	public void traverseBottomUp(Visitor visitor) {
		for (ASTNode node : this.statements) {
			node.traverseBottomUp(visitor);
		}
		for (ASTNode node : this.comments) {
			node.traverseBottomUp(visitor);
		}
		accept(visitor);
	}

	/**
	 * create program node in XML format.
	 */
	@Override
	public void toString(StringBuilder buffer, String tab) {
		buffer.append("<Program"); //$NON-NLS-1$
		appendInterval(buffer);
		buffer.append(">\n").append(TAB).append("<Statements>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		for (ASTNode node : this.statements) {
			node.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append("</Statements>\n").append(TAB).append("<Comments>\n"); //$NON-NLS-1$ //$NON-NLS-2$
		for (ASTNode comment : this.comments) {
			comment.toString(buffer, TAB + TAB + tab);
			buffer.append("\n"); //$NON-NLS-1$
		}
		buffer.append(TAB).append("</Comments>\n").append("</Program>"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public int getType() {
		return ASTNode.PROGRAM;
	}

	/**
	 * Retrieves the statement list of this program
	 * 
	 * @return statement parts of this program
	 */
	public List<Statement> statements() {
		return this.statements;
	}

	public ASTNode getElementAt(int offset) {
		return Locator.locateNode(this, offset);
	}

	/*
	 * Method declared on ASTNode.
	 */
	@Override
	public boolean subtreeMatch(ASTMatcher matcher, Object other) {
		// dispatch to correct overloaded match method
		return matcher.match(this, other);
	}

	/**
	 * Initializes the internal comment mapper with the given scanner.
	 * 
	 * @param scanner
	 *            the scanner
	 * @throws IOException
	 * @since 3.0
	 */
//	public void initCommentMapper(IDocument document, Reader reader, AstLexer scanner) throws IOException {
//		this.commentMapper = new DefaultCommentMapper(this.comments().toArray(new Comment[this.comments().size()]));
//		this.commentMapper.initialize(this, document, reader, scanner);
//	}

	/**
	 * Returns the internal comment mapper.
	 * 
	 * @return the comment mapper, or <code>null</code> if none.
	 * @since 3.0
	 */
//	DefaultCommentMapper getCommentMapper() {
//		return this.commentMapper;
//	}

	/**
	 * Sets the line end table for this compilation unit. If
	 * <code>lineEndTable[i] == p</code> then line number <code>i+1</code> ends at
	 * character position <code>p</code>. Except for the last line, the positions
	 * are that of (the last character of) the line delimiter. For example, the
	 * source string <code>A\nB\nC</code> has line end table {1, 3, 4}.
	 * 
	 * @param lineEndTable
	 *            the line end table
	 */
	public void setLineEndTable(int[] lineEndTable) {
		if (lineEndTable == null) {
			throw new NullPointerException();
		}
		// alternate root is *not* considered a structural property
		// but we protect them nevertheless
		// Disable the check for bug
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=291569
		// checkModifiable();
		this.lineEndTable = lineEndTable;
	}

	/**
	 * Returns the column number corresponding to the given source character
	 * position in the original source string. Column number are zero-based. Return
	 * <code>-1</code> if it is beyond the valid range or <code>-2</code> if the
	 * column number information is unknown.
	 * 
	 * @param position
	 *            a 0-based character position, possibly negative or out of range
	 * @return the 0-based column number, or <code>-1</code> if the character
	 *         position does not correspond to a source line in the original source
	 *         file or <code>-2</code> if column number information is unknown for
	 *         this compilation unit
	 * @see ASTParser
	 * @since 3.2
	 */
	public int getColumnNumber(final int position) {
		if (this.lineEndTable == null) {
			return -2;
		}
		final int line = getLineNumber(position);
		if (line == -1) {
			return -1;
		}
		if (line == 1) {
			if (position >= getStart() + getLength()) {
				return -1;
			}
			return position;
		}
		// length is different from 0
		int length = this.lineEndTable.length;
		// -1 to for one-based to zero-based conversion.
		// -1, again, to get previous line.
		final int previousLineOffset = this.lineEndTable[line - 2];
		// previousLineOffset + 1 is the first character of the current line
		final int offsetForLine = previousLineOffset + 1;
		final int currentLineEnd = line == length + 1 ? getStart() + getLength() - 1 : this.lineEndTable[line - 1];
		if (offsetForLine > currentLineEnd) {
			return -1;
		} else {
			return position - offsetForLine;
		}
	}

	/**
	 * Returns the line number corresponding to the given source character position
	 * in the original source string. The initial line of the compilation unit is
	 * numbered 1, and each line extends through the last character of the
	 * end-of-line delimiter. The very last line extends through the end of the
	 * source string and has no line delimiter. For example, the source string
	 * <code>class A\n{\n}</code> has 3 lines corresponding to inclusive character
	 * ranges [0,7], [8,9], and [10,10]. Returns -1 for a character position that
	 * does not correspond to any source line, or -2 if no line number information
	 * is available for this compilation unit.
	 * 
	 * @param position
	 *            a 0-based character position, possibly negative or out of range
	 * @return the 1-based line number, or <code>-1</code> if the character position
	 *         does not correspond to a source line in the original source file or
	 *         <code>-2</code> if line number information is not known for this
	 *         compilation unit
	 * @see ASTParser
	 * @since 3.2
	 */
	public int getLineNumber(int position) {
		if (this.lineEndTable == null) {
			return -2;
		}
		int length;
		if ((length = this.lineEndTable.length) == 0) {
			if (position >= getStart() + getLength()) {
				return -1;
			}
			return 1;
		}
		int low = 0;
		if (position < 0) {
			// position illegal
			return -1;
		}
		if (position <= this.lineEndTable[low]) {
			// before the first line delimiter
			return 1;
		}
		// assert position > lineEndTable[low+1] && low == 0
		int hi = length - 1;
		if (position > this.lineEndTable[hi]) {
			// position beyond the last line separator
			if (position >= getStart() + getLength()) {
				// this is beyond the end of the source length
				return -1;
			} else {
				return length + 1;
			}
		}
		// assert lineEndTable[low] < position <= lineEndTable[hi]
		// && low == 0 && hi == length - 1 && low < hi

		// binary search line end table
		while (true) {
			// invariant lineEndTable[low] < position <= lineEndTable[hi]
			// && 0 <= low < hi <= length - 1
			// reducing measure hi - low
			if (low + 1 == hi) {
				// assert lineEndTable[low] < position <= lineEndTable[low+1]
				// position is on line low+1 (line number is low+2)
				return low + 2;
			}
			// assert hi - low >= 2, so average is truly in between
			int mid = low + (hi - low) / 2;
			// assert 0 <= low < mid < hi <= length - 1
			if (position <= this.lineEndTable[mid]) {
				// assert lineEndTable[low] < position <= lineEndTable[mid]
				// && 0 <= low < mid < hi <= length - 1
				hi = mid;
			} else {
				// position > lineEndTable[mid]
				// assert lineEndTable[mid] < position <= lineEndTable[hi]
				// && 0 <= low < mid < hi <= length - 1
				low = mid;
			}
			// in both cases, invariant reachieved with reduced measure
		}
	}

	/**
	 * Given a line number and column number, returns the corresponding position in
	 * the original source string. Returns -2 if no line number information is
	 * available for this compilation unit. Returns the total size of the source
	 * string if <code>line</code> is greater than the actual number lines in the
	 * unit. Returns -1 if <code>column</code> is less than 0, or the position of
	 * the last character of the line if <code>column</code> is beyond the legal
	 * range, or the given line number is less than one.
	 * 
	 * @param line
	 *            the one-based line number
	 * @param column
	 *            the zero-based column number
	 * @return the 0-based character position in the source string; <code>-2</code>
	 *         if line/column number information is not known for this compilation
	 *         unit or <code>-1</code> the inputs are not valid
	 * @since 3.2
	 */
	public int getPosition(int line, int column) {
		if (this.lineEndTable == null) {
			return -2;
		}
		if (line < 1 || column < 0) {
			return -1;
		}
		int length;
		if ((length = this.lineEndTable.length) == 0) {
			if (line != 1) {
				return -1;
			}
			return column >= getStart() + getLength() ? -1 : column;
		}
		if (line == 1) {
			final int endOfLine = this.lineEndTable[0];
			return column > endOfLine ? -1 : column;
		} else if (line > length + 1) {
			// greater than the number of lines in the source string.
			return -1;
		}
		// -1 to for one-based to zero-based conversion.
		// -1, again, to get previous line.
		final int previousLineOffset = this.lineEndTable[line - 2];
		// previousLineOffset + 1 is the first character of the current line
		final int offsetForLine = previousLineOffset + 1;
		final int currentLineEnd = line == length + 1 ? getStart() + getLength() - 1 : this.lineEndTable[line - 1];
		if ((offsetForLine + column) > currentLineEnd) {
			return -1;
		} else {
			return offsetForLine + column;
		}
	}

	/**
	 * Returns the extended source length of the given node. Unlike
	 * {@link ASTNode#getStartPosition()} and {@link ASTNode#getLength()}, the
	 * extended source range may include comments and whitespace immediately before
	 * or after the normal source range for the node.
	 * 
	 * @param node
	 *            the node
	 * @return a (possibly 0) length, or <code>0</code> if no source position
	 *         information is recorded for this node
	 * @see #getExtendedStartPosition(ASTNode)
	 * @since 3.0
	 */
	public int getExtendedLength(ASTNode node) {
	    throw new UnsupportedOperationException();
//		if (node == null) {
//			throw new IllegalArgumentException();
//		}
//		if (this.commentMapper == null || node.getAST() != getAST()) {
//			// fall back: use best info available
//			return node.getLength();
//		} else {
//			return this.commentMapper.getExtendedLength(node);
//		}
	}

	/**
	 * Returns the extended start position of the given node. Unlike
	 * {@link ASTNode#getStartPosition()} and {@link ASTNode#getLength()}, the
	 * extended source range may include comments and whitespace immediately before
	 * or after the normal source range for the node.
	 * 
	 * @param node
	 *            the node
	 * @return the 0-based character index, or <code>-1</code> if no source position
	 *         information is recorded for this node
	 * @see #getExtendedLength(ASTNode)
	 * @since 3.0
	 */
	public int getExtendedStartPosition(ASTNode node) {
	    throw new UnsupportedOperationException();
//		if (node == null) {
//			throw new IllegalArgumentException();
//		}
//		if (this.commentMapper == null || node.getAST() != getAST()) {
//			// fall back: use best info available
//			return node.getStart();
//		} else {
//			return this.commentMapper.getExtendedStartPosition(node);
//		}
	}

	/**
	 * Return the index in the whole comments list {@link #comments() } of the first
	 * leading comments associated with the given node.
	 * 
	 * @param node
	 *            the node
	 * @return 0-based index of first leading comment or -1 if node has no
	 *         associated comment before its start position.
	 * @since 3.2
	 */
	public int firstLeadingCommentIndex(ASTNode node) {
	    throw new UnsupportedOperationException();
//		if (node == null) {
//			throw new IllegalArgumentException();
//		}
//		if (this.commentMapper == null || node.getAST() != getAST()) {
//			return -1;
//		}
//		return this.commentMapper.firstLeadingCommentIndex(node);
	}

	/**
	 * Return the index in the whole comments list {@link #comments() } of the last
	 * trailing comments associated with the given node.
	 * 
	 * @param node
	 *            the node
	 * @return 0-based index of last trailing comment or -1 if node has no
	 *         associated comment after its end position.
	 * @since 3.2
	 */
	public int lastTrailingCommentIndex(ASTNode node) {
	    throw new UnsupportedOperationException();
//		if (node == null) {
//			throw new IllegalArgumentException();
//		}
//		if (this.commentMapper == null || node.getAST() != getAST()) {
//			return -1;
//		}
//		return this.commentMapper.lastTrailingCommentIndex(node);
	}

	/**
	 * Enables the recording of changes to this program unit and its descendents.
	 * The program must have been created by <code>ASTParser</code> and still be in
	 * its original state. Once recording is on, arbitrary changes to the subtree
	 * rooted at this compilation unit are recorded internally. Once the
	 * modification has been completed, call <code>rewrite</code> to get an object
	 * representing the corresponding edits to the original source code string.
	 * 
	 * @exception IllegalArgumentException
	 *                if this program is marked as unmodifiable, or if this program
	 *                has already been tampered with, or recording has already been
	 *                enabled
	 * @since 3.0
	 */
	public void recordModifications() {
//		getAST().recordModifications(this);
	}

//	/**
//	 * Converts all modifications recorded for this program into an object
//	 * representing the corresponding text edits to the given document containing
//	 * the original source code for this compilation unit.
//	 * <p>
//	 * The program must have been created by <code>ASTParser</code> from the source
//	 * code string in the given document, and recording must have been turned on
//	 * with a prior call to <code>recordModifications</code> while the AST was still
//	 * in its original state.
//	 * </p>
//	 * <p>
//	 * Calling this methods does not discard the modifications on record.
//	 * Subsequence modifications made to the AST are added to the ones already on
//	 * record. If this method is called again later, the resulting text edit object
//	 * will accurately reflect the net cumulative affect of all those changes.
//	 * </p>
//	 *
//	 * @param document
//	 *            original document containing source code for this program
//	 * @param options
//	 *            the table of formatter options (key type: <code>String</code>;
//	 *            value type: <code>String</code>); or <code>null</code> to use the
//	 *            standard global options
//	 *            {@link org.eclipse.php.core.PhpPluginCore#getOptions()}.
//	 * @return text edit object describing the changes to the document corresponding
//	 *         to the recorded AST modifications
//	 * @exception IllegalArgumentException
//	 *                if the document passed is <code>null</code> or does not
//	 *                correspond to this AST
//	 * @exception IllegalStateException
//	 *                if <code>recordModifications</code> was not called to enable
//	 *                recording
//	 * @see #recordModifications()
//	 * @since 3.0
//	 */
//	public TextEdit rewrite(IDocument document, Map<String, String> options) {
//		return getAST().rewrite(document, options);
//	}

	@Override
	ASTNode clone0(AST target) {
		final List<Statement> statements = ASTNode.copySubtrees(target, statements());
		final List<Comment> comments = ASTNode.copySubtrees(target, comments());
		return new Program(this.getStart(), this.getEnd(), target, statements, comments);
	}

	@Override
	List<StructuralPropertyDescriptor> internalStructuralPropertiesForType(PHPVersion apiLevel) {
		return PROPERTY_DESCRIPTORS;
	}

	/*
	 * (omit javadoc for this method) Method declared on ASTNode.
	 */
	@Override
	final List<? extends ASTNode> internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == STATEMENTS_PROPERTY) {
			return statements();
		}
		if (property == COMMENTS_PROPERTY) {
			return comments();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/**
	 * Finds an AST node by specified binding
	 * 
	 * @param binding
	 * @return
	 */
	@Deprecated
	public ASTNode findDeclaringNode(IBinding binding) {
	    throw new UnsupportedOperationException();
//		ISourceReference phpElement = (ISourceReference) binding.getPHPElement();
//		try {
//			return getElementAt(phpElement.getSourceRange().getOffset());
//		} catch (ModelException e) {
//			PHPCorePlugin.log(e);
//		}
//		return null;
	}

	@Deprecated
//	public IProblem[] getProblems() {
//		try {
//			if (getSourceModule() == null) {
//				return new IProblem[0];
//			}
//
//			IResource resource = getSourceModule().getUnderlyingResource();
//			if (resource != null) {
//				IMarker[] markers = resource.findMarkers(PHPProblemIdentifier.MARKER_TYPE_ID, true,
//						IResource.DEPTH_ONE);
//				IProblem[] problems = new DefaultProblem[markers.length];
//				for (int i = 0; i < markers.length; ++i) {
//					IProblemIdentifier id = DefaultProblemIdentifier.decode(markers[i].getAttribute("id", "")); //$NON-NLS-1$ //$NON-NLS-2$
//					String message = markers[i].getAttribute(IMarker.MESSAGE, ""); //$NON-NLS-1$
//					int start = markers[i].getAttribute(IMarker.CHAR_START, 0);
//					int end = markers[i].getAttribute(IMarker.CHAR_END, 0);
//					int line = markers[i].getAttribute(IMarker.LINE_NUMBER, 0);
//					problems[i] = new DefaultProblem(resource.getName(), message, id, null, null, start, end, line, 0);
//				}
//				return problems;
//			}
//		} catch (CoreException e) {
//		}
//		return new IProblem[0];
//	}

	public Map<NamespaceDeclaration, List<UseStatement>> getUseStatements() {
		if (fUseStatements == null) {
			fUseStatements = new HashMap<>();
			List<NamespaceDeclaration> namespaceDeclarations = getNamespaceDeclarations();
			if (namespaceDeclarations.size() == 0) {
				UseStatementFinder visitor = new UseStatementFinder();
				this.accept(visitor);
				fUseStatements.put(null, visitor.getUseStatements());
			} else {
				for (NamespaceDeclaration namespace : namespaceDeclarations) {
					UseStatementFinder visitor = new UseStatementFinder();
					namespace.accept(visitor);
					fUseStatements.put(namespace, visitor.getUseStatements());
				}
			}
		}
		return fUseStatements;
	}

	public List<UseStatement> getUseStatements(NamespaceDeclaration namespace) {
		if (fUseStatements == null) {
			getUseStatements();
		}
		return fUseStatements.get(namespace);
	}

	public NamespaceDeclaration getNamespaceDeclaration(int position) {
		List<NamespaceDeclaration> namespaceDeclarations = getNamespaceDeclarations();
		for (NamespaceDeclaration namespace : namespaceDeclarations) {
			if (namespace.getStart() <= position && namespace.getEnd() >= position) {
				return namespace;
			}
		}
		return null;
	}

	public List<NamespaceDeclaration> getNamespaceDeclarations() {
		if (fNamespaceDeclarations == null) {
			NamespaceFinder finder = new NamespaceFinder();
			this.accept(finder);
			fNamespaceDeclarations = finder.getAllNamespaces();
		}
		return fNamespaceDeclarations;
	}

	private class NamespaceFinder extends ApplyAll {
		private List<NamespaceDeclaration> allNamespaces = new ArrayList<>();

		@Override
		protected boolean apply(ASTNode node) {
			if (node instanceof NamespaceDeclaration) {
				allNamespaces.add((NamespaceDeclaration) node);
				return false;
			}
			return true;
		}

		public List<NamespaceDeclaration> getAllNamespaces() {
			return allNamespaces;
		}

	}

	private class UseStatementFinder extends ApplyAll {

		private List<UseStatement> useStatements = new ArrayList<>();

		@Override
		protected boolean apply(ASTNode node) {
			if (node instanceof UseStatement) {
				useStatements.add((UseStatement) node);
				return false;
			}
			return true;
		}

		public List<UseStatement> getUseStatements() {
			return useStatements;
		}

	}

}
