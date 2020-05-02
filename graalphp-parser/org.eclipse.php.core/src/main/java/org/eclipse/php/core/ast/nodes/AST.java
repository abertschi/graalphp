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
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.internal.core.ast.scanner.AstLexer;
import org.eclipse.php.internal.core.search.Messages;

import java_cup.runtime.Scanner;
import java_cup.runtime.lr_parser;

/**
 * Umbrella owner and abstract syntax tree node factory. An <code>AST</code>
 * instance serves as the common owner of any number of AST nodes, and as the
 * factory for creating new AST nodes owned by that instance.
 * <p>
 * Abstract syntax trees may be hand constructed by clients, using the
 * <code>new<i>TYPE</i></code> factory methods to create new nodes, and the
 * various <code>set<i>CHILD</i></code> methods (see
 * {@link org.eclipse.php.core.ast.nodes.ASTNode} and its subclasses) to connect
 * them together.
 * </p>
 * <p>
 * Each AST node belongs to a unique AST instance, called the owning AST. The
 * children of an AST node always have the same owner as their parent node. If a
 * node from one AST is to be added to a different AST, the subtree must be
 * cloned first to ensures that the added nodes have the correct owning AST.
 * </p>
 * <p>
 * There can be any number of AST nodes owned by a single AST instance that are
 * unparented. Each of these nodes is the root of a separate little tree of
 * nodes. The method <code>ASTNode.getProgramRoot()</code> navigates from any
 * node to the root of the tree that it is contained in. Ordinarily, an AST
 * instance has one main tree (rooted at a <code>Program</code>), with
 * newly-created nodes appearing as additional roots until they are parented
 * somewhere under the main tree. One can navigate from any node to its AST
 * instance, but not conversely.
 * </p>
 * <p>
 * The class {@link ASTParser} parses a string containing a PHP source code and
 * returns an abstract syntax tree for it. The resulting nodes carry source
 * ranges relating the node back to the original source characters.
 * </p>
 * <p>
 * Programs created by <code>ASTParser</code> from a source document can be
 * serialized after arbitrary modifications with minimal loss of original
 * formatting. Here is an example:
 * 
 * <pre>
 * 
 * Document doc = new Document("<?\n class X {} \n echo 'hello world';\n  ?>");
 * ASTParser parser = ASTParser.newParser(AST.PHP5);
 * parser.setSource(doc.get().toCharArray());
 * Program program = parser.createAST(null);
 * program.recordModifications();
 * AST ast = program.getAST();
 * EchoStatement echo = ast.newEchoStatement();
 * echo.setExpression(ast.newScalar("hello world");
 * program.statements().add(echo);
 * TextEdit edits = program.rewrite(document, null);
 * UndoEdit undo = edits.apply(document);
 * 
 * </pre>
 * 
 * See also {@link ASTRewrite} for an alternative way to describe and serialize
 * changes to a read-only AST.
 * </p>
 * <p>
 * Clients may create instances of this class using {@link #newAST(int)}, but
 * this class is not intended to be subclassed.
 * </p>
 * 
 * @see ASTParser
 * @see ASTNode
 * @since 2.0
 */
public class AST {

	/**
	 * The scanner capabilities to the AST - all has package access to enable
	 * ASTParser access
	 */
	final AstLexer lexer;
	final lr_parser parser;
	final PHPVersion apiLevel;
	final boolean useASPTags;
	final boolean useShortTags;

	/**
	 * The event handler for this AST. Initially an event handler that does not
	 * nothing.
	 * 
	 * @since 3.0
	 */
	private NodeEventHandler eventHandler = new NodeEventHandler();

	/**
	 * Internal modification count; initially 0; increases monotonically <b>by
	 * one or more</b> as the AST is successively modified.
	 */
	private long modificationCount = 0;

	/**
	 * Internal original modification count; value is equals to <code>
	 * modificationCount</code> at the end of the parse (<code>ASTParser
	 * </code>). If this ast is not created with a parser then value is 0.
	 * 
	 * @since 3.0
	 */
	private long originalModificationCount = 0;

	/**
	 * When disableEvents > 0, events are not reported and the modification
	 * count stays fixed.
	 * <p>
	 * This mechanism is used in lazy initialization of a node to prevent events
	 * from being reported for the modification of the node as well as for the
	 * creation of the missing child.
	 * </p>
	 * 
	 * @since 3.0
	 */
	private int disableEvents = 0;

	/**
	 * Internal object unique to the AST instance. Readers must synchronize on
	 * this object when the modifying instance fields.
	 * 
	 * @since 3.0
	 */
	private final Object internalASTLock = new Object();

	/**
	 * Default value of <code>flag<code> when a new node is created.
	 */
	private int defaultNodeFlag = 0;

	/**
	 * Internal ast rewriter used to record ast modification when record mode is
	 * enabled.
	 */
//	InternalASTRewriter rewriter;

	/**
	 * The binding resolver for this AST. Initially a binding resolver that does
	 * not resolve names at all.
	 */
	private BindingResolver resolver = new BindingResolver();

	public AST(Reader reader, PHPVersion apiLevel, boolean aspTagsAsPhp, boolean useShortTags) throws IOException {
		this.useASPTags = aspTagsAsPhp;
		this.useShortTags = useShortTags;
		this.apiLevel = apiLevel;
		this.lexer = getLexerInstance(reader, apiLevel, aspTagsAsPhp, useShortTags);
		this.parser = getParserInstance(apiLevel, this.lexer);
	}

	/**
	 * Constructs a scanner from a given reader
	 * 
	 * @param reader
	 * @param phpVersion
	 * @param aspTagsAsPhp
	 * @return
	 * @throws IOException
	 */
	private AstLexer getLexerInstance(Reader reader, PHPVersion phpVersion, boolean aspTagsAsPhp, boolean useShortTags)
			throws IOException {
//		if (PHPVersion.PHP5 == phpVersion) {
//			final AstLexer lexer5 = getLexer5(reader);
//			lexer5.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer5.setUseShortTags(useShortTags);
//			return lexer5;
//		} else if (PHPVersion.PHP5_3 == phpVersion) {
//			final AstLexer lexer53 = getLexer53(reader);
//			lexer53.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer53.setUseShortTags(useShortTags);
//			return lexer53;
//		} else if (PHPVersion.PHP5_4 == phpVersion) {
//			final AstLexer lexer54 = getLexer54(reader);
//			lexer54.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer54.setUseShortTags(useShortTags);
//			return lexer54;
//		} else if (PHPVersion.PHP5_5 == phpVersion) {
//			final AstLexer lexer55 = getLexer55(reader);
//			lexer55.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer55.setUseShortTags(useShortTags);
//			return lexer55;
//		} else if (PHPVersion.PHP5_6 == phpVersion) {
//			final AstLexer lexer56 = getLexer56(reader);
//			lexer56.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer56.setUseShortTags(useShortTags);
//			return lexer56;
//		} else if (PHPVersion.PHP7_0 == phpVersion) {
//			final AstLexer lexer7 = getLexer7(reader);
//			lexer7.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer7.setUseShortTags(useShortTags);
//			return lexer7;
//		} else if (PHPVersion.PHP7_1 == phpVersion) {
//			final AstLexer lexer71 = getLexer71(reader);
//			lexer71.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer71.setUseShortTags(useShortTags);
//			return lexer71;
//		} else if (PHPVersion.PHP7_2 == phpVersion) {
//			final AstLexer lexer72 = getLexer72(reader);
//			lexer72.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer72.setUseShortTags(useShortTags);
//			return lexer72;
//		} else if (PHPVersion.PHP7_3 == phpVersion) {
//			final AstLexer lexer73 = getLexer73(reader);
//			lexer73.setUseAspTagsAsPHP(aspTagsAsPhp);
//			lexer73.setUseShortTags(useShortTags);
//			return lexer73;
//		}
		if (PHPVersion.PHP7_4 == phpVersion) {
			final AstLexer lexer74 = getLexer74(reader);
			lexer74.setUseAspTagsAsPHP(aspTagsAsPhp);
			lexer74.setUseShortTags(useShortTags);
			return lexer74;
		} else {
			if (phpVersion == null) {
				throw new IllegalArgumentException("Invalid PHP version");
			} else {
				throw new IllegalArgumentException(
						Messages.format("Unknown php version: %s", phpVersion));
			}
		}
	}

	private AstLexer getLexer74(Reader reader) throws IOException {
		final org.eclipse.php.core.ast.scanner.php74.PHPAstLexer phpAstLexer = new org.eclipse.php.core.ast.scanner.php74.PHPAstLexer(
				reader);
		phpAstLexer.setAST(this);
		return phpAstLexer;
	}

//	private AstLexer getLexer73(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php73.PHPAstLexer phpAstLexer = new org.eclipse.php.internal.core.ast.scanner.php73.PHPAstLexer(
//				reader);
//		phpAstLexer.setAST(this);
//		return phpAstLexer;
//	}
//
//	private AstLexer getLexer72(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php72.PHPAstLexer phpAstLexer = new org.eclipse.php.internal.core.ast.scanner.php72.PHPAstLexer(
//				reader);
//		phpAstLexer.setAST(this);
//		return phpAstLexer;
//	}
//
//	private AstLexer getLexer71(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php71.PHPAstLexer phpAstLexer = new org.eclipse.php.internal.core.ast.scanner.php71.PHPAstLexer(
//				reader);
//		phpAstLexer.setAST(this);
//		return phpAstLexer;
//	}
//
//	private AstLexer getLexer7(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php7.PHPAstLexer phpAstLexer = new org.eclipse.php.internal.core.ast.scanner.php7.PHPAstLexer(
//				reader);
//		phpAstLexer.setAST(this);
//		return phpAstLexer;
//	}
//
//	private AstLexer getLexer56(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php56.PHPAstLexer phpAstLexer5 = new org.eclipse.php.internal.core.ast.scanner.php56.PHPAstLexer(
//				reader);
//		phpAstLexer5.setAST(this);
//		return phpAstLexer5;
//	}
//
//	private AstLexer getLexer55(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php55.PHPAstLexer phpAstLexer5 = new org.eclipse.php.internal.core.ast.scanner.php55.PHPAstLexer(
//				reader);
//		phpAstLexer5.setAST(this);
//		return phpAstLexer5;
//	}
//
//	private AstLexer getLexer54(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php54.PHPAstLexer phpAstLexer5 = new org.eclipse.php.internal.core.ast.scanner.php54.PHPAstLexer(
//				reader);
//		phpAstLexer5.setAST(this);
//		return phpAstLexer5;
//	}
//
//	private AstLexer getLexer53(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php53.PHPAstLexer phpAstLexer5 = new org.eclipse.php.internal.core.ast.scanner.php53.PHPAstLexer(
//				reader);
//		phpAstLexer5.setAST(this);
//		return phpAstLexer5;
//	}
//
//	private AstLexer getLexer5(Reader reader) throws IOException {
//		final org.eclipse.php.internal.core.ast.scanner.php5.PHPAstLexer phpAstLexer5 = new org.eclipse.php.internal.core.ast.scanner.php5.PHPAstLexer(
//				reader);
//		phpAstLexer5.setAST(this);
//		return phpAstLexer5;
//	}

	private lr_parser getParserInstance(PHPVersion phpVersion, Scanner lexer) {
//		if (PHPVersion.PHP5 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php5.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php5.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP5_3 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php53.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php53.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP5_4 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php54.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php54.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP5_5 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php55.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php55.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP5_6 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php56.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php56.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP7_0 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php7.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php7.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP7_1 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php71.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php71.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP7_2 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php72.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php72.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		} else if (PHPVersion.PHP7_3 == phpVersion) {
//			final org.eclipse.php.internal.core.ast.scanner.php73.PHPAstParser parser = new org.eclipse.php.internal.core.ast.scanner.php73.PHPAstParser(
//					lexer);
//			parser.setAST(this);
//			return parser;
//		}
		if (PHPVersion.PHP7_4 == phpVersion) {
			final org.eclipse.php.core.ast.scanner.php74.PHPAstParser parser = new org.eclipse.php.core.ast.scanner.php74.PHPAstParser(
					lexer);
			parser.setAST(this);
			return parser;
		} else {
			if (phpVersion == null) {
				throw new IllegalArgumentException("invalid php version given"); //$NON-NLS-1$
			} else {
				throw new IllegalArgumentException(
						Messages.format("Unknown PHP version: %s", phpVersion));
			}
		}
	}

	/**
	 * Returns the modification count for this AST. The modification count is a
	 * non-negative value that increases (by 1 or perhaps by more) as this AST
	 * or its nodes are changed. The initial value is unspecified.
	 * <p>
	 * The following things count as modifying an AST:
	 * <ul>
	 * <li>creating a new node owned by this AST,</li>
	 * <li>adding a child to a node owned by this AST,</li>
	 * <li>removing a child from a node owned by this AST,</li>
	 * <li>setting a non-node attribute of a node owned by this AST.</li>
	 * </ul>
	 * </p>
	 * Operations which do not entail creating or modifying existing nodes do
	 * not increase the modification count.
	 * <p>
	 * N.B. This method may be called several times in the course of a single
	 * client operation. The only promise is that the modification count
	 * increases monotonically as the AST or its nodes change; there is no
	 * promise that a modifying operation increases the count by exactly 1.
	 * </p>
	 * 
	 * @return the current value (non-negative) of the modification counter of
	 *         this AST
	 */
	public long modificationCount() {
		return this.modificationCount;
	}

	/**
	 * Indicates that this AST is about to be modified.
	 * <p>
	 * The following things count as modifying an AST:
	 * <ul>
	 * <li>creating a new node owned by this AST</li>
	 * <li>adding a child to a node owned by this AST</li>
	 * <li>removing a child from a node owned by this AST</li>
	 * <li>setting a non-node attribute of a node owned by this AST</li>.
	 * </ul>
	 * </p>
	 * <p>
	 * N.B. This method may be called several times in the course of a single
	 * client operation.
	 * </p>
	 */
	void modifying() {
		// when this method is called during lazy init, events are disabled
		// and the modification count will not be increased
		if (this.disableEvents > 0) {
			return;
		}
		// increase the modification count
		this.modificationCount++;
	}

	/**
	 * Disable events. This method is thread-safe for AST readers.
	 * 
	 * @see #reenableEvents()
	 * @since 3.0
	 */
	final void disableEvents() {
		synchronized (this.internalASTLock) {
			// guard against concurrent access by another reader
			this.disableEvents++;
		}
		// while disableEvents > 0 no events will be reported, and mod count
		// will stay fixed
	}

	/**
	 * Reenable events. This method is thread-safe for AST readers.
	 * 
	 * @see #disableEvents()
	 * @since 3.0
	 */
	final void reenableEvents() {
		synchronized (this.internalASTLock) {
			// guard against concurrent access by another reader
			this.disableEvents--;
		}
	}

	/**
	 * Reports that the given node is about to lose a child.
	 * 
	 * @param node
	 *            the node about to be modified
	 * @param child
	 *            the node about to be removed
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void preRemoveChildEvent(ASTNode node, ASTNode child, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE DEL]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.preRemoveChildEvent(node, child, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has not been changed yet
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node jsut lost a child.
	 * 
	 * @param node
	 *            the node that was modified
	 * @param child
	 *            the child node that was removed
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void postRemoveChildEvent(ASTNode node, ASTNode child, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE DEL]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.postRemoveChildEvent(node, child, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has not been changed yet
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node is about have a child replaced.
	 * 
	 * @param node
	 *            the node about to be modified
	 * @param child
	 *            the child node about to be removed
	 * @param newChild
	 *            the replacement child
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void preReplaceChildEvent(ASTNode node, ASTNode child, ASTNode newChild, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE REP]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.preReplaceChildEvent(node, child, newChild, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has not been changed yet
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node has just had a child replaced.
	 * 
	 * @param node
	 *            the node modified
	 * @param child
	 *            the child removed
	 * @param newChild
	 *            the replacement child
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void postReplaceChildEvent(ASTNode node, ASTNode child, ASTNode newChild, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE REP]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.postReplaceChildEvent(node, child, newChild, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has not been changed yet
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node is about to gain a child.
	 * 
	 * @param node
	 *            the node that to be modified
	 * @param child
	 *            the node that to be added as a child
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void preAddChildEvent(ASTNode node, ASTNode child, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE ADD]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.preAddChildEvent(node, child, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node has just gained a child.
	 * 
	 * @param node
	 *            the node that was modified
	 * @param child
	 *            the node that was added as a child
	 * @param property
	 *            the child or child list property descriptor
	 * @since 3.0
	 */
	void postAddChildEvent(ASTNode node, ASTNode child, StructuralPropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE ADD]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.postAddChildEvent(node, child, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node is about to change the value of a non-child
	 * property.
	 * 
	 * @param node
	 *            the node to be modified
	 * @param property
	 *            the property descriptor
	 * @since 3.0
	 */
	void preValueChangeEvent(ASTNode node, SimplePropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE CHANGE]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.preValueChangeEvent(node, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node has just changed the value of a non-child
	 * property.
	 * 
	 * @param node
	 *            the node that was modified
	 * @param property
	 *            the property descriptor
	 * @since 3.0
	 */
	void postValueChangeEvent(ASTNode node, SimplePropertyDescriptor property) {
		// IMPORTANT: this method is called by readers during lazy init
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE CHANGE]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.postValueChangeEvent(node, property);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node is about to be cloned.
	 * 
	 * @param node
	 *            the node to be cloned
	 * @since 3.0
	 */
	void preCloneNodeEvent(ASTNode node) {
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE CLONE]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.preCloneNodeEvent(node);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	/**
	 * Reports that the given node has just been cloned.
	 * 
	 * @param node
	 *            the node that was cloned
	 * @param clone
	 *            the clone of <code>node</code>
	 * @since 3.0
	 */
	void postCloneNodeEvent(ASTNode node, ASTNode clone) {
		synchronized (this.internalASTLock) {
			// guard against concurrent access by a reader doing lazy init
			if (this.disableEvents > 0) {
				// doing lazy init OR already processing an event
				// System.out.println("[BOUNCE CLONE]");
				return;
			} else {
				disableEvents();
			}
		}
		try {
			this.eventHandler.postCloneNodeEvent(node, clone);
			// N.B. even if event handler blows up, the AST is not
			// corrupted since node has already been changed
		} finally {
			reenableEvents();
		}
	}

	public BindingResolver getBindingResolver() {
		return this.resolver;
	}

	/**
	 * Returns the event handler for this AST.
	 * 
	 * @return the event handler for this AST
	 * @since 3.0
	 */
	NodeEventHandler getEventHandler() {
		return this.eventHandler;
	}

	/**
	 * Sets the event handler for this AST.
	 * 
	 * @param eventHandler
	 *            the event handler for this AST
	 * @since 3.0
	 */
	void setEventHandler(NodeEventHandler eventHandler) {
		if (this.eventHandler == null) {
			throw new IllegalArgumentException();
		}
		this.eventHandler = eventHandler;
	}

	/**
	 * Returns default node flags of new nodes of this AST.
	 * 
	 * @return the default node flags of new nodes of this AST
	 * @since 3.0
	 */
	int getDefaultNodeFlag() {
		return this.defaultNodeFlag;
	}

	/**
	 * Sets default node flags of new nodes of this AST.
	 * 
	 * @param flag
	 *            node flags of new nodes of this AST
	 * @since 3.0
	 */
	void setDefaultNodeFlag(int flag) {
		this.defaultNodeFlag = flag;
	}

	/**
	 * Set <code>originalModificationCount</code> to the current modification
	 * count
	 * 
	 * @since 3.0
	 */
	void setOriginalModificationCount(long count) {
		this.originalModificationCount = count;
	}

	/**
	 * Returns the type binding for a "well known" type.
	 * <p>
	 * Note that bindings are generally unavailable unless requested when the
	 * AST is being built.
	 * </p>
	 * <p>
	 * The following type names are supported:
	 * <ul>
	 * <li><code>"boolean"</code></li>
	 * <li><code>"byte"</code></li>
	 * <li><code>"char"</code></li>
	 * <li><code>"double"</code></li>
	 * <li><code>"float"</code></li>
	 * <li><code>"int"</code></li>
	 * <li><code>"long"</code></li>
	 * <li><code>"short"</code></li>
	 * <li><code>"void"</code></li>
	 * </ul>
	 * </p>
	 * 
	 * @param name
	 *            the name of a well known type
	 * @return the corresponding type binding, or <code>null</code> if the named
	 *         type is not considered well known or if no binding can be found
	 *         for it
	 */
	public ITypeBinding resolveWellKnownType(String name) {
		if (name == null) {
			return null;
		}
		return getBindingResolver().resolveWellKnownType(name);
	}

	/**
	 * Sets the binding resolver for this AST.
	 * 
	 * @param resolver
	 *            the new binding resolver for this AST
	 */
	void setBindingResolver(BindingResolver resolver) {
		if (resolver == null) {
			throw new IllegalArgumentException();
		}
		this.resolver = resolver;
	}

	/**
	 * Checks that this AST operation is not used when building level JLS2 ASTs.
	 * 
	 * @exception UnsupportedOperationException
	 * @since 3.0
	 */
	void unsupportedIn2() {
	}

	/**
	 * Checks that this AST operation is only used when building level JLS2
	 * ASTs.
	 * 
	 * @exception UnsupportedOperationException
	 * @since 3.0
	 */
	void supportedOnlyIn2() {
	}

	/**
	 * new Class[] {AST.class}
	 * 
	 * @since 3.0
	 */
	private static final Class<?>[] AST_CLASS = new Class<?>[] { AST.class };

	/**
	 * new Object[] {this}
	 * 
	 * @since 3.0
	 */
	private final Object[] THIS_AST = new Object[] { this };

	/*
	 * Must not collide with a value for IProgram constants
	 */
	static final int RESOLVED_BINDINGS = 0x80000000;

	/**
	 * Tag bit value. This represents internal state of the tree.
	 */
	private int bits;

	/**
	 * Creates an unparented node of the given node class (non-abstract subclass
	 * of {@link ASTNode}).
	 * 
	 * @param nodeClass
	 *            AST node class
	 * @return a new unparented node owned by this AST
	 * @exception IllegalArgumentException
	 *                if <code>nodeClass</code> is <code>null</code> or is not a
	 *                concrete node type class
	 * @since 3.0
	 */
	public ASTNode createInstance(Class<?> nodeClass) {
		if (nodeClass == null) {
			throw new IllegalArgumentException();
		}
		try {
			// invoke constructor with signature Foo(AST)
			Constructor<?> c = nodeClass.getDeclaredConstructor(AST_CLASS);
			Object result = c.newInstance(this.THIS_AST);
			return (ASTNode) result;
		} catch (NoSuchMethodException e) {
			// all AST node classes have a Foo(AST) constructor
			// therefore nodeClass is not legit
			throw new IllegalArgumentException();
		} catch (InstantiationException e) {
			// all concrete AST node classes can be instantiated
			// therefore nodeClass is not legit
			throw new IllegalArgumentException();
		} catch (IllegalAccessException e) {
			// all AST node classes have an accessible Foo(AST) constructor
			// therefore nodeClass is not legit
			throw new IllegalArgumentException();
		} catch (InvocationTargetException e) {
			// concrete AST node classes do not die in the constructor
			// therefore nodeClass is not legit
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Creates an unparented node of the given node type. This convenience
	 * method is equivalent to:
	 * 
	 * <pre>
	 * createInstance(ASTNode.nodeClassForType(nodeType))
	 * </pre>
	 * 
	 * @param nodeType
	 *            AST node type, one of the node type constants declared on
	 *            {@link ASTNode}
	 * @return a new unparented node owned by this AST
	 * @exception IllegalArgumentException
	 *                if <code>nodeType</code> is not a legal AST node type
	 * @since 3.0
	 */
	public ASTNode createInstance(int nodeType) {
		// nodeClassForType throws IllegalArgumentException if nodeType is bogus
		Class<?> nodeClass = ASTNode.nodeClassForType(nodeType);
		return createInstance(nodeClass);
	}

	// =============================== TYPES ===========================

	/**
	 * Enables the recording of changes to the given compilation unit and its
	 * descendents. The compilation unit must have been created by
	 * <code>ASTParser</code> and still be in its original state. Once recording
	 * is on, arbitrary changes to the subtree rooted at the compilation unit
	 * are recorded internally. Once the modification has been completed, call
	 * <code>rewrite</code> to get an object representing the corresponding
	 * edits to the original source code string.
	 * 
	 * @exception IllegalArgumentException
	 *                if this compilation unit is marked as unmodifiable, or if
	 *                this compilation unit has already been tampered with, or
	 *                if recording has already been enabled, or if
	 *                <code>root</code> is not owned by this AST
	 * @see Program#recordModifications()
	 * @since 3.0
	 */
	void recordModifications(Program root) {
//		if (this.modificationCount != this.originalModificationCount) {
//			throw new IllegalArgumentException("AST is already modified"); //$NON-NLS-1$
//		} else if (this.rewriter != null) {
//			throw new IllegalArgumentException("AST modifications are already recorded"); //$NON-NLS-1$
//		} else if ((root.getFlags() & ASTNode.PROTECT) != 0) {
//			throw new IllegalArgumentException("Root node is unmodifiable"); //$NON-NLS-1$
//		} else if (root.getAST() != this) {
//			throw new IllegalArgumentException("Root node is not owned by this ast"); //$NON-NLS-1$
//		}
//
////		this.rewriter = new InternalASTRewriter(root);
////		this.setEventHandler(this.rewriter);
	}

	/**
	 * Converts all modifications recorded into an object representing the
	 * corresponding text edits to the given document containing the original
	 * source code for the compilation unit that gave rise to this AST.
	 * 
	 * @param document
	 *            original document containing source code for the compilation
	 *            unit
	 * @param options
	 *            the table of formatter options (key type: <code>String</code>;
	 *            value type: <code>String</code>); or <code>null</code> to use
	 *            the standard global options {@link PHPCore#getOptions()
	 *            PHPCore.getOptions()}.
	 * @return text edit object describing the changes to the document
	 *         corresponding to the recorded AST modifications
	 * @exception IllegalArgumentException
	 *                if the document passed is <code>null</code> or does not
	 *                correspond to this AST
	 * @exception IllegalStateException
	 *                if <code>recordModifications</code> was not called to
	 *                enable recording
	 * @see Program#rewrite(IDocument, Map)
	 * @since 3.0
	 */
//	TextEdit rewrite(IDocument document, Map<String, String> options) {
//		if (document == null) {
//			throw new IllegalArgumentException();
//		}
//		if (this.rewriter == null) {
//			throw new IllegalStateException("Modifications record is not enabled"); //$NON-NLS-1$
//		}
//		return this.rewriter.rewriteAST(document, options);
//	}

	/**
	 * Returns true if the ast tree was created with bindings, false otherwise
	 * 
	 * @return true if the ast tree was created with bindings, false otherwise
	 * @since 3.3
	 */
	public boolean hasResolvedBindings() {
		return (this.bits & RESOLVED_BINDINGS) != 0;
	}

	/**
	 * Returns true if the ast tree was created with statements recovery, false
	 * otherwise
	 * 
	 * @return true if the ast tree was created with statements recovery, false
	 *         otherwise
	 * @since 3.3
	 */
	/*
	 * public boolean hasStatementsRecovery() { return (this.bits &
	 * IProgram.ENABLE_STATEMENTS_RECOVERY) != 0; }
	 */
	/**
	 * Returns true if the ast tree was created with bindings recovery, false
	 * otherwise
	 * 
	 * @return true if the ast tree was created with bindings recovery, false
	 *         otherwise
	 * @since 3.3
	 */
	/*
	 * public boolean hasBindingsRecovery() { return (this.bits &
	 * IProgram.ENABLE_BINDINGS_RECOVERY) != 0; }
	 */
	void setFlag(int newValue) {
		this.bits |= newValue;
	}

	/**
	 * @return The lexer used by this AST
	 */
	public AstLexer lexer() {
		return lexer;
	}

	/**
	 * @return The parser used by this AST
	 */
	public lr_parser parser() {
		return parser;
	}

	/**
	 * @return The API level used by this AST
	 */
	public PHPVersion apiLevel() {
		return apiLevel;
	}

	/**
	 * @return true if this AST "permits" ASP tags
	 */
	public boolean useASPTags() {
		return useASPTags;
	}

	/**
	 * @return true if this AST "permits" short tags
	 */
	public boolean useShortTags() {
		return useShortTags;
	}

	/**
	 * @return true if this AST "permits" ASP tags
	 * @throws IOException
	 */
	public void setSource(Reader reader) throws IOException {
		if (reader == null) {
			throw new IllegalArgumentException();
		}
		this.lexer.yyreset(reader);
		this.lexer.resetCommentList();
		this.parser.setScanner(this.lexer);
	}

	/**
	 * Creates a new {@link ArrayAccess}.
	 * 
	 * @return a new ArrayAccess.
	 */
	public ArrayAccess newArrayAccess() {
		return new ArrayAccess(this);
	}

	/**
	 * Creates a new {@link ArrayAccess}.
	 * 
	 * @param variableName
	 * @param index
	 * @param arrayType
	 * @return a new ArrayAccess
	 */
	public ArrayAccess newArrayAccess(VariableBase variableName, Expression index, int arrayType) {
		ArrayAccess arrayAccess = new ArrayAccess(this);
		arrayAccess.setName(variableName);
		arrayAccess.setIndex(index);
		arrayAccess.setArrayType(arrayType);
		return arrayAccess;
	}

	/**
	 * Creates a new {@link ArrayAccess}. Default array type is VARIABLE_ARRAY
	 * 
	 * @param variableName
	 * @param index
	 * @return a new ArrayAccess
	 */
	public ArrayAccess newArrayAccess(VariableBase variableName, Expression index) {
		ArrayAccess arrayAccess = new ArrayAccess(this);
		arrayAccess.setName(variableName);
		arrayAccess.setIndex(index);
		arrayAccess.setArrayType(ArrayAccess.VARIABLE_ARRAY);
		return arrayAccess;
	}

	/**
	 * Creates a new {@link ArrayCreation}.
	 * 
	 * @return a new ArrayCreation.
	 */
	public ArrayCreation newArrayCreation() {
		return new ArrayCreation(this);
	}

	/**
	 * Creates a new {@link ArrayCreation}.
	 * 
	 * @param elements
	 *            - List of {@link ArrayElement}
	 * @return a new ArrayCreation.
	 */
	public ArrayCreation newArrayCreation(List<ArrayElement> elements) {
		ArrayCreation arrayCreation = new ArrayCreation(this);
		arrayCreation.elements().addAll(elements);
		return arrayCreation;
	}

	/**
	 * Creates a new {@link ArrayElement}.
	 * 
	 * @return a new ArrayElement.
	 */
	public ArrayElement newArrayElement() {
		return new ArrayElement(this);
	}

	/**
	 * Creates a new {@link ArrayElement}.
	 * 
	 * @param key
	 *            - an {@link Expression} rapresenting the element key
	 * @param value
	 *            - an {@link Expression} rapresenting the element value
	 * @return a new ArrayElement.
	 */
	public ArrayElement newArrayElement(Expression key, Expression value) {
		ArrayElement arrayElement = new ArrayElement(this);
		arrayElement.setKey(key);
		arrayElement.setValue(value);
		return arrayElement;
	}

	/**
	 * Creates a new {@link ArraySpreadElement}.
	 * 
	 * @return a new ArraySpreadElement.
	 */
	public ArraySpreadElement newArraySpreadElement() {
		return new ArraySpreadElement(this);
	}

	/**
	 * Creates a new {@link ArraySpreadElement}.
	 * 
	 * @param value
	 *            - an {@link Expression} rapresenting the element value
	 * @return a new ArrayElement.
	 */
	public ArraySpreadElement newArraySpreadElement(Expression value) {
		ArraySpreadElement arraySpreadElement = new ArraySpreadElement(this);
		arraySpreadElement.setValue(value);
		return arraySpreadElement;
	}

	/**
	 * Creates a new {@link Assignment}.
	 * 
	 * @return A new Assignment.
	 */
	public Assignment newAssignment() {
		return new Assignment(this);
	}

	/**
	 * Creates a new {@link Assignment}.
	 * 
	 * @param leftHandSide
	 *            A {@link VariableBase}
	 * @param operator
	 *            The assignment operator
	 * @param rightHandSide
	 *            An {@link Expression}
	 * @return A new Assignment.
	 */
	public Assignment newAssignment(VariableBase leftHandSide, int operator, Expression rightHandSide) {
		Assignment assignment = new Assignment(this);
		assignment.setLeftHandSide(leftHandSide);
		assignment.setOperator(operator);
		assignment.setRightHandSide(rightHandSide);
		return assignment;
	}

	/**
	 * Creates a new {@link ASTError}.
	 * 
	 * @return A new ASTError.
	 */
	public ASTError newASTError() {
		return new ASTError(this);
	}

	/**
	 * Creates a new {@link BackTickExpression}.
	 * 
	 * @return A new BackTickExpression.
	 */
	public BackTickExpression newBackTickExpression() {
		return new BackTickExpression(this);
	}

	/**
	 * Creates a new {@link BackTickExpression}.
	 * 
	 * @param expressions
	 *            - List of {@link Expression}
	 * @return A new BackTickExpression.
	 */
	public BackTickExpression newBackTickExpression(List<Expression> expressions) {
		BackTickExpression backTickExpression = new BackTickExpression(this);
		backTickExpression.expressions().addAll(expressions);
		return backTickExpression;
	}

	/**
	 * Creates an unparented block node owned by this AST, for an empty list of
	 * statements.
	 * 
	 * @return a new unparented, empty curly block node
	 */
	public Block newBlock() {
		Block block = new Block(this);
		block.setIsCurly(true);
		return block;
	}

	/**
	 * Creates an unparented block node owned by this AST, for an empty list of
	 * statements.
	 * 
	 * @param statements
	 *            - List of {@link Statement}
	 * @return a new unparented, empty block node
	 */
	public Block newBlock(List<Statement> statements) {
		Block block = new Block(this);
		block.statements().addAll(statements);
		block.setIsCurly(true);
		return block;

	}

	/**
	 * Creates a new {@link BreakStatement}.
	 * 
	 * @return A new BreakStatement.
	 */
	public BreakStatement newBreakStatement() {
		return new BreakStatement(this);
	}

	/**
	 * Creates a new {@link BreakStatement}.
	 * 
	 * @param expression
	 *            .
	 * @return A new BreakStatement.
	 */
	public BreakStatement newBreakStatement(Expression expression) {
		BreakStatement breakStatement = new BreakStatement(this);
		breakStatement.setExpression(expression);
		return breakStatement;
	}

	/**
	 * Creates a new {@link CastExpression}.
	 * 
	 * @return A new CastExpression.
	 */
	public CastExpression newCastExpression() {
		return new CastExpression(this);
	}

	/**
	 * Creates a new {@link CastExpression}.
	 * 
	 * @param expression
	 * @param castType
	 * @return A new CastExpression.
	 */
	public CastExpression newCastExpression(Expression expression, int castType) {
		CastExpression castExpression = new CastExpression(this);
		castExpression.setExpression(expression);
		castExpression.setCastingType(castType);
		return castExpression;
	}

	/**
	 * Creates a new {@link CatchClause}.
	 * 
	 * @return A new CatchClause.
	 */
	public CatchClause newCatchClause() {
		return new CatchClause(this);
	}

	/**
	 * Creates a new {@link CatchClause}.
	 * 
	 * @param className
	 * @param variable
	 * @param statement
	 * @return A new CatchClause.
	 */
	public CatchClause newCatchClause(Identifier className, Variable variable, Block statement) {
		CatchClause catchClause = new CatchClause(this);
		catchClause.setClassName(className);
		catchClause.setVariable(variable);
		catchClause.setBody(statement);
		return catchClause;
	}

	/**
	 * Creates a new {@link FinallyClause}.
	 * 
	 * @return A new FinallyClause.
	 */
	public FinallyClause newFinallyClause() {
		return new FinallyClause(this);
	}

	/**
	 * Creates a new {@link FinallyClause}.
	 * 
	 * @param statement
	 * @return A new FinallyClause.
	 */
	public FinallyClause newFinallyClause(Block statement) {
		FinallyClause finallyClause = new FinallyClause(this);
		finallyClause.setBody(statement);
		return finallyClause;
	}

	/**
	 * Creates a new {@link ConstantDeclaration}.
	 * 
	 * @return A new ClassConstantDeclaration.
	 */
	public ConstantDeclaration newClassConstantDeclaration() {
		return new ConstantDeclaration(this);
	}

	/**
	 * Creates a new {@link ConstantDeclaration}.
	 * 
	 * @param names
	 * @param initializers
	 * @return A new ClassConstantDeclaration.
	 */
	public ConstantDeclaration newClassConstantDeclaration(List<Identifier> names, List<Expression> initializers) {
		ConstantDeclaration classConstantDeclaration = new ConstantDeclaration(this);
		classConstantDeclaration.initializers().addAll(initializers);
		classConstantDeclaration.names().addAll(names);
		return classConstantDeclaration;
	}

	/**
	 * Creates a new {@link ClassDeclaration}.
	 * 
	 * @return A new ClassDeclaration.
	 */
	public ClassDeclaration newClassDeclaration() {
		return new ClassDeclaration(this);
	}

	/**
	 * Creates a new {@link ClassDeclaration}.
	 * 
	 * @param modifier
	 * @param className
	 * @param superClass
	 * @param interfaces
	 * @param body
	 * @return A new ClassDeclaration.
	 */
	public ClassDeclaration newClassDeclaration(int modifier, String className, String superClass,
			List<Identifier> interfaces, Block body) {
		ClassDeclaration classDeclaration = new ClassDeclaration(this);
		classDeclaration.setModifier(modifier);
		classDeclaration.setName(newIdentifier(className));
		if (superClass != null) {
			classDeclaration.setSuperClass(newIdentifier(superClass));
		} else {
			classDeclaration.setSuperClass(null);
		}
		classDeclaration.interfaces().addAll(interfaces);
		classDeclaration.setBody(body);
		return classDeclaration;
	}

	/**
	 * Creates a new {@link ClassInstanceCreation}.
	 * 
	 * @return A new ClassInstanceCreation.
	 */
	public ClassInstanceCreation newClassInstanceCreation() {
		return new ClassInstanceCreation(this);
	}

	/**
	 * Creates a new {@link ClassInstanceCreation}.
	 * 
	 * @param className
	 * @param ctorParams
	 * @return A new ClassInstanceCreation.
	 */
	public ClassInstanceCreation newClassInstanceCreation(ClassName className, List<Expression> ctorParams) {
		ClassInstanceCreation classInstanceCreation = new ClassInstanceCreation(this);
		classInstanceCreation.setClassName(className);
		classInstanceCreation.ctorParams().addAll(ctorParams);
		return classInstanceCreation;
	}

	/**
	 * Creates a new {@link ClassName}.
	 * 
	 * @return A new ClassName.
	 */
	public ClassName newClassName() {
		return new ClassName(this);
	}

	/**
	 * Creates a new {@link ClassName}.
	 * 
	 * @param name
	 * @return A new ClassName.
	 */
	public ClassName newClassName(Expression name) {
		ClassName className = new ClassName(this);
		className.setClassName(name);
		return className;
	}

	/**
	 * Creates a new {@link CloneExpression}.
	 * 
	 * @return A new CloneExpression.
	 */
	public CloneExpression newCloneExpression() {
		return new CloneExpression(this);
	}

	/**
	 * Creates a new {@link CloneExpression}.
	 * 
	 * @param expr
	 * @return A new CloneExpression.
	 */
	public CloneExpression newCloneExpression(Expression expr) {
		CloneExpression cloneExpression = new CloneExpression(this);
		cloneExpression.setExpression(expr);
		return cloneExpression;
	}

	/**
	 * Creates a new {@link Comment}.
	 * 
	 * @return A new Comment.
	 */
	public Comment newComment() {
		return new Comment(this);
	}

	/**
	 * Creates a new {@link Comment}.
	 * 
	 * @param commentType
	 * @return A new Comment.
	 */
	public Comment newComment(int commentType) {
		Comment comment = new Comment(this);
		comment.setCommentType(commentType);
		return comment;
	}

	/**
	 * Creates a new {@link ConditionalExpression}.
	 * 
	 * @return A new ConditionalExpression.
	 */
	public ConditionalExpression newConditionalExpression() {
		return new ConditionalExpression(this);
	}

	/**
	 * Creates a new {@link ConditionalExpression}.
	 * 
	 * @param condition
	 * @param ifTrue
	 * @param ifFalse
	 * @return A new ConditionalExpression.
	 */
	public ConditionalExpression newConditionalExpression(Expression condition, Expression ifTrue, Expression ifFalse) {
		ConditionalExpression conditionalExpression = new ConditionalExpression(this);
		conditionalExpression.setCondition(condition);
		conditionalExpression.setIfTrue(ifTrue);
		conditionalExpression.setIfFalse(ifFalse);
		return conditionalExpression;
	}

	/**
	 * Creates a new {@link ContinueStatement}.
	 * 
	 * @return A new ContinueStatement.
	 */
	public ContinueStatement newContinueStatement() {
		return new ContinueStatement(this);
	}

	/**
	 * Creates a new {@link ContinueStatement}.
	 * 
	 * @param expr
	 * @return A new ContinueStatement.
	 */
	public ContinueStatement newContinueStatement(Expression expr) {
		ContinueStatement continueStatement = new ContinueStatement(this);
		continueStatement.setExpression(expr);
		return continueStatement;
	}

	/**
	 * Creates a new {@link DeclareStatement}.
	 * 
	 * @param directiveNames
	 * @param directiveValues
	 * @param body
	 * @return A new DeclareStatement.
	 */
	public DeclareStatement newDeclareStatement(List<Identifier> directiveNames, List<Expression> directiveValues,
			Statement body) {
		DeclareStatement declareStatement = new DeclareStatement(this);
		declareStatement.directiveNames().addAll(directiveNames);
		declareStatement.directiveValues().addAll(directiveValues);
		declareStatement.setBody(body);
		return declareStatement;
	}

	/**
	 * Creates a new {@link DoStatement}.
	 * 
	 * @return A new DoStatement.
	 */
	public DoStatement newDoStatement() {
		return new DoStatement(this);
	}

	/**
	 * Creates a new {@link DoStatement}.
	 * 
	 * @param condition
	 * @param body
	 * @return A new DoStatement.
	 */
	public DoStatement newDoStatement(Expression condition, Statement body) {
		DoStatement doStatement = new DoStatement(this);
		doStatement.setCondition(condition);
		doStatement.setBody(body);
		return doStatement;
	}

	/**
	 * Creates a new {@link EchoStatement}.
	 * 
	 * @return A new EchoStatement.
	 */
	public EchoStatement newEchoStatement() {
		return new EchoStatement(this);
	}

	/**
	 * Creates a new {@link EchoStatement}.
	 * 
	 * @param expressions
	 * @return A new EchoStatement.
	 */
	public EchoStatement newEchoStatement(List<Expression> expressions) {
		EchoStatement echoStatement = new EchoStatement(this);
		echoStatement.expressions().addAll(expressions);
		return echoStatement;
	}

	/**
	 * Creates a new {@link EchoStatement} with a given {@link Expression}.
	 * 
	 * @param expression
	 *            An {@link Expression} to set into the returned
	 *            {@link EchoStatement}.
	 * @return A new EchoStatement with the given Expression.
	 */
	public EchoStatement newEchoStatement(Expression expression) {
		EchoStatement echoStatement = new EchoStatement(this);
		echoStatement.expressions().add(expression);
		return echoStatement;
	}

	/**
	 * Creates a new {@link EmptyStatement}.
	 * 
	 * @return A new EmptyStatement.
	 */
	public EmptyStatement newEmptyStatement() {
		return new EmptyStatement(this);
	}

	/**
	 * Creates a new {@link EmptyExpression}.
	 * 
	 * @return A new EmptyExpression.
	 */
	public EmptyExpression newEmptyExpression() {
		return new EmptyExpression(this);
	}

	/**
	 * Creates a new {@link ExpressionStatement}.
	 * 
	 * @return A new ExpressionStatement.
	 */
	public ExpressionStatement newExpressionStatement() {
		return new ExpressionStatement(this);
	}

	/**
	 * Creates a new {@link ExpressionStatement} with a given {@link Expression}
	 * as an expression.
	 * 
	 * @param identifier
	 *            The {@link Expression} that is the expression of the
	 *            statement.
	 * @return A new ExpressionStatement
	 */
	public ExpressionStatement newExpressionStatement(Expression expression) {
		ExpressionStatement statement = newExpressionStatement();
		statement.setExpression(expression);
		return statement;
	}

	/**
	 * Creates a new {@link FieldAccess}.
	 * 
	 * @return A new FieldAccess.
	 */
	public FieldAccess newFieldAccess() {
		return new FieldAccess(this);
	}

	/**
	 * Creates a new {@link FieldAccess}.
	 * 
	 * @param dispatcher
	 * @param field
	 * @return A new FieldAccess.
	 */
	public FieldAccess newFieldAccess(VariableBase dispatcher, Variable field) {
		FieldAccess fieldAccess = new FieldAccess(this);
		fieldAccess.setDispatcher(dispatcher);
		fieldAccess.setField(field);
		return fieldAccess;
	}

	/**
	 * Creates a new {@link FieldsDeclaration}.
	 * 
	 * @return A new FieldsDeclaration.
	 */
	public FieldsDeclaration newFieldsDeclaration() {
		return new FieldsDeclaration(this);
	}

	/**
	 * Creates a new {@link FieldsDeclaration}.
	 * 
	 * @param modifier
	 * @param variablesAndDefaults
	 * @return A new FieldsDeclaration.
	 */
	public FieldsDeclaration newFieldsDeclaration(int modifier, List<SingleFieldDeclaration> variablesAndDefaults) {
		FieldsDeclaration fieldsDeclaration = new FieldsDeclaration(this);
		fieldsDeclaration.setModifier(modifier);
		List<SingleFieldDeclaration> fields = fieldsDeclaration.fields();
		fields.addAll(variablesAndDefaults);
		return fieldsDeclaration;
	}

	/**
	 * Creates a new {@link ForEachStatement}.
	 * 
	 * @return A new ForEachStatement.
	 */
	public ForEachStatement newForEachStatement() {
		return new ForEachStatement(this);
	}

	/**
	 * Creates a new {@link ForEachStatement}.
	 * 
	 * @param expression
	 * @param key
	 * @param value
	 * @param statement
	 * @return A new ForEachStatement.
	 */
	public ForEachStatement newForEachStatement(Expression expression, Expression key, Expression value,
			Statement statement) {
		ForEachStatement forEachStatement = new ForEachStatement(this);
		forEachStatement.setExpression(expression);
		forEachStatement.setKey(key);
		forEachStatement.setValue(value);
		forEachStatement.setStatement(statement);
		return forEachStatement;
	}

	/**
	 * Creates a new {@link FormalParameter}.
	 * 
	 * @return A new FormalParameter.
	 */
	public FormalParameter newFormalParameter() {
		return new FormalParameter(this);
	}

	/**
	 * Creates a new {@link FormalParameter}.
	 * 
	 * @param type
	 * @param parameterName
	 * @param defaultValue
	 * @param isMandatory
	 * @return A new FormalParameter.
	 */
	public FormalParameter newFormalParameter(Identifier type, Expression parameterName, Expression defaultValue,
			boolean isMandatory) {
		FormalParameter formalParameter = new FormalParameter(this);
		formalParameter.setParameterType(type);
		formalParameter.setParameterName(parameterName);
		formalParameter.setDefaultValue(defaultValue);
		return formalParameter;
	}

	/**
	 * Creates a new {@link ForStatement}.
	 * 
	 * @return A new ForStatement.
	 */
	public ForStatement newForStatement() {
		return new ForStatement(this);
	}

	/**
	 * Creates a new {@link ForStatement}.
	 * 
	 * @param initializers
	 * @param conditions
	 * @param updaters
	 * @param body
	 * @return A new ForStatement.
	 */
	public ForStatement newForStatement(List<Expression> initializers, List<Expression> conditions,
			List<Expression> updaters, Statement body) {
		ForStatement forStatement = new ForStatement(this);
		forStatement.initializers().addAll(initializers);
		forStatement.updaters().addAll(updaters);
		forStatement.conditions().addAll(conditions);
		forStatement.setBody(body);
		return forStatement;
	}

	/**
	 * Creates a new {@link FunctionDeclaration}.
	 * 
	 * @return A new FunctionDeclaration.
	 */
	public FunctionDeclaration newFunctionDeclaration() {
		return new FunctionDeclaration(this);
	}

	/**
	 * Creates a new {@link FunctionDeclaration}.
	 * 
	 * @param functionName
	 * @param formalParameters
	 * @param returnType
	 * @param body
	 * @param isReference
	 * @return A new FunctionDeclaration.
	 */
	public FunctionDeclaration newFunctionDeclaration(final Identifier functionName,
			final List<FormalParameter> formalParameters, final Identifier returnType, final Block body,
			final boolean isReference) {
		FunctionDeclaration functionDeclaration = new FunctionDeclaration(this);
		functionDeclaration.setFunctionName(functionName);
		functionDeclaration.formalParameters().addAll(formalParameters);
		functionDeclaration.setBody(body);
		functionDeclaration.setIsReference(isReference);
		functionDeclaration.setReturnType(returnType);
		return functionDeclaration;
	}

	/**
	 * Creates a new {@link FunctionInvocation}.
	 * 
	 * @return A new FunctionInvocation.
	 */
	public FunctionInvocation newFunctionInvocation() {
		return new FunctionInvocation(this);
	}

	/**
	 * Creates a new {@link FunctionInvocation}.
	 * 
	 * @param functionName
	 * @param parameters
	 *            (can be null to indicate no parameters)
	 * @return A new FunctionInvocation.
	 */
	public FunctionInvocation newFunctionInvocation(FunctionName functionName, List<Expression> parameters) {
		FunctionInvocation functionInvocation = new FunctionInvocation(this);
		functionInvocation.setFunctionName(functionName);
		if (parameters != null) {
			functionInvocation.parameters().addAll(parameters);
		}
		return functionInvocation;
	}

	/**
	 * Creates a new {@link FunctionName}.
	 * 
	 * @return A new FunctionName.
	 */
	public FunctionName newFunctionName() {
		return new FunctionName(this);
	}

	/**
	 * Creates a new {@link FunctionName}.
	 * 
	 * @param functionName
	 * @return A new FunctionName.
	 */
	public FunctionName newFunctionName(Expression name) {
		FunctionName functionName = new FunctionName(this);
		functionName.setName(name);
		return functionName;
	}

	/**
	 * Creates a new {@link FieldsDeclaration}.
	 * 
	 * @return A new FieldsDeclaration.
	 */
	public GlobalStatement newGlobalStatement() {
		return new GlobalStatement(this);
	}

	/**
	 * Creates a new {@link FieldsDeclaration}.
	 * 
	 * @param variables
	 * @return A new FieldsDeclaration.
	 */
	public GlobalStatement newGlobalStatement(List<Variable> variables) {
		GlobalStatement globalStatement = new GlobalStatement(this);
		globalStatement.variables().addAll(variables);
		return globalStatement;
	}

	/**
	 * Creates a new {@link Identifier}.
	 * 
	 * @return A new Identifier.
	 */
	public Identifier newIdentifier() {
		return new Identifier(this);
	}

	/**
	 * Creates and returns a new unparented simple name node for the given
	 * identifier. The identifier should be a legal PHP identifier.
	 * 
	 * @param identifier
	 *            the identifier
	 * @return a new unparented simple name node
	 * @exception IllegalArgumentException
	 *                if the identifier is invalid
	 */
	public Identifier newIdentifier(String identifier) {

		if (identifier == null) {
			throw new IllegalArgumentException();
		}
		Identifier result = new Identifier(this);
		result.setName(identifier);
		return result;
	}

	/**
	 * Creates a new {@link IfStatement}.
	 * 
	 * @return A new IfStatement.
	 */
	public IfStatement newIfStatement() {
		return new IfStatement(this);
	}

	/**
	 * Creates a new {@link IfStatement}.
	 * 
	 * @param condition
	 * @param trueStatement
	 * @param falseStatement
	 * @return A new IfStatement.
	 */
	public IfStatement newIfStatement(Expression condition, Statement trueStatement, Statement falseStatement) {
		IfStatement ifStatement = new IfStatement(this);
		ifStatement.setCondition(condition);
		ifStatement.setTrueStatement(trueStatement);
		ifStatement.setFalseStatement(falseStatement);
		return ifStatement;
	}

	/**
	 * Creates a new {@link IgnoreError}.
	 * 
	 * @return A new IgnoreError.
	 */
	public IgnoreError newIgnoreError() {
		return new IgnoreError(this);
	}

	/**
	 * Creates a new {@link IgnoreError}.
	 * 
	 * @param expression
	 * @return A new IgnoreError.
	 */
	public IgnoreError newIgnoreError(Expression expression) {
		IgnoreError ignoreError = new IgnoreError(this);
		ignoreError.setExpression(expression);
		return ignoreError;
	}

	/**
	 * Creates a new {@link Include}.
	 * 
	 * @return A new Include.
	 */
	public Include newInclude() {
		return new Include(this);
	}

	/**
	 * Creates a new {@link Include}.
	 * 
	 * @param expression
	 * @param type
	 * @return A new Include.
	 */
	public Include newInclude(Expression expr, int type) {
		Include include = new Include(this);
		include.setExpression(expr);
		include.setIncludetype(type);
		return include;
	}

	/**
	 * Creates a new {@link InfixExpression}.
	 * 
	 * @return A new InfixExpression.
	 */
	public InfixExpression newInfixExpression() {
		return new InfixExpression(this);
	}

	/**
	 * Creates a new {@link InfixExpression}.
	 * 
	 * @param left
	 * @param operator
	 * @param right
	 * @return A new InfixExpression.
	 */
	public InfixExpression newInfixExpression(Expression left, int operator, Expression right) {
		InfixExpression infixExpression = new InfixExpression(this);
		infixExpression.setLeft(left);
		infixExpression.setOperator(operator);
		infixExpression.setRight(right);
		return infixExpression;
	}

	/**
	 * Creates a new {@link InLineHtml}.
	 * 
	 * @return A new InLineHtml.
	 */
	public InLineHtml newInLineHtml() {
		return new InLineHtml(this);
	}

	/**
	 * Creates a new {@link InstanceOfExpression}.
	 * 
	 * @return A new InstanceOfExpression.
	 */
	public InstanceOfExpression newInstanceOfExpression() {
		return new InstanceOfExpression(this);
	}

	/**
	 * Creates a new {@link InstanceOfExpression}.
	 * 
	 * @param expr
	 * @param className
	 * @return A new InstanceOfExpression.
	 */
	public InstanceOfExpression newInstanceOfExpression(Expression expr, ClassName className) {
		InstanceOfExpression instanceOfExpression = new InstanceOfExpression(this);
		instanceOfExpression.setClassName(className);
		instanceOfExpression.setExpression(expr);
		return instanceOfExpression;
	}

	/**
	 * Creates a new {@link InterfaceDeclaration}.
	 * 
	 * @return A new InterfaceDeclaration.
	 */
	public InterfaceDeclaration newInterfaceDeclaration() {
		return new InterfaceDeclaration(this);
	}

	/**
	 * Creates a new {@link InterfaceDeclaration}.
	 * 
	 * @param interfaceName
	 * @param interfaces
	 * @param body
	 * @return A new InterfaceDeclaration.
	 */
	public InterfaceDeclaration newInterfaceDeclaration(Identifier interfaceName, List<Identifier> interfaces,
			Block body) {
		InterfaceDeclaration interfaceDeclaration = new InterfaceDeclaration(this);
		interfaceDeclaration.setName(interfaceName);
		interfaceDeclaration.interfaces().addAll(interfaces);
		interfaceDeclaration.setBody(body);
		return interfaceDeclaration;
	}

	/**
	 * Creates a new {@link ListVariable}.
	 * 
	 * @return A new ListVariable.
	 */
	public ListVariable newListVariable() {
		return new ListVariable(this);
	}

	/**
	 * Creates a new {@link ListVariable}.
	 * 
	 * @param variables
	 * @return A new ListVariable.
	 */
	public ListVariable newListVariable(List<VariableBase> variables) {
		ListVariable listVariable = new ListVariable(this);
		listVariable.variables().addAll(variables);
		return listVariable;
	}

	/**
	 * Creates a new {@link MethodDeclaration}.
	 * 
	 * @return A new MethodDeclaration.
	 */
	public MethodDeclaration newMethodDeclaration() {
		return new MethodDeclaration(this);
	}

	/**
	 * Creates a new {@link MethodDeclaration}.
	 * 
	 * @param modifier
	 * @param function
	 * @return A new MethodDeclaration.
	 */
	public MethodDeclaration newMethodDeclaration(int modifier, FunctionDeclaration function) {
		MethodDeclaration methodDeclaration = new MethodDeclaration(this);
		methodDeclaration.setModifier(modifier);
		methodDeclaration.setFunction(function);
		return methodDeclaration;
	}

	/**
	 * Creates a new {@link MethodInvocation}.
	 * 
	 * @return A new MethodInvocation.
	 */
	public MethodInvocation newMethodInvocation() {
		return new MethodInvocation(this);
	}

	/**
	 * Creates a new {@link MethodInvocation}.
	 * 
	 * @param dispatcher
	 * @param method
	 * 
	 * @return A new MethodInvocation.
	 */
	public MethodInvocation newMethodInvocation(VariableBase dispatcher, FunctionInvocation method) {
		MethodInvocation methodInvocation = new MethodInvocation(this);
		methodInvocation.setDispatcher(dispatcher);
		methodInvocation.setMethod(method);
		return methodInvocation;
	}

	/**
	 * Creates a new {@link ParenthesisExpression}.
	 * 
	 * @return A new ParenthesisExpression.
	 */
	public ParenthesisExpression newParenthesisExpression() {
		return new ParenthesisExpression(this);
	}

	/**
	 * Creates a new {@link ParenthesisExpression}.
	 * 
	 * @param expression
	 * @return A new ParenthesisExpression.
	 */
	public ParenthesisExpression newParenthesisExpression(Expression expression) {
		ParenthesisExpression parenthesisExpression = new ParenthesisExpression(this);
		parenthesisExpression.setExpression(expression);
		return parenthesisExpression;
	}

	/**
	 * Creates a new {@link PostfixExpression}.
	 * 
	 * @return A new PostfixExpression.
	 */
	public PostfixExpression newPostfixExpression() {
		return new PostfixExpression(this);
	}

	/**
	 * Creates a new {@link PostfixExpression}.
	 * 
	 * @param variable
	 * @param operator
	 * @return A new PostfixExpression.
	 */
	public PostfixExpression newPostfixExpression(VariableBase variable, int operator) {
		PostfixExpression postfixExpression = new PostfixExpression(this);
		postfixExpression.setVariable(variable);
		postfixExpression.setOperator(operator);
		return postfixExpression;
	}

	/**
	 * Creates a new {@link PrefixExpression}.
	 * 
	 * @return A new PrefixExpression.
	 */
	public PrefixExpression newPrefixExpression() {
		return new PrefixExpression(this);
	}

	/**
	 * Creates a new {@link PrefixExpression}.
	 * 
	 * @param variable
	 * @param operator
	 * @return A new PrefixExpression.
	 */
	public PrefixExpression newPrefixExpression(VariableBase variable, int operator) {
		PrefixExpression prefixExpression = new PrefixExpression(this);
		prefixExpression.setVariable(variable);
		prefixExpression.setOperator(operator);
		return prefixExpression;
	}

	/**
	 * Creates a new {@link Program}.
	 * 
	 * @return A new Program.
	 */
	public Program newProgram() {
		return new Program(this);
	}

	/**
	 * Creates a new {@link Program}.
	 * 
	 * @return A new Program.
	 */
	public Program newProgram(List<Statement> statements, List<Comment> commentList) {
		Program program = new Program(this);
		program.statements().addAll(statements);
		program.comments().addAll(commentList);
		return program;
	}

	/**
	 * Creates a new {@link Quote}.
	 * 
	 * @return A new Quote.
	 */
	public Quote newQuote() {
		return new Quote(this);
	}

	/**
	 * Creates a new {@link Quote}.
	 * 
	 * @param expressions
	 * @param type
	 * @return A new Quote.
	 */
	public Quote newQuote(List<Expression> expressions, int type) {
		Quote quote = new Quote(this);
		quote.expressions().addAll(expressions);
		quote.setQuoteType(type);
		return quote;
	}

	/**
	 * Creates a new {@link Reference}.
	 * 
	 * @return A new Reference.
	 */
	public Reference newReference() {
		return new Reference(this);
	}

	/**
	 * Creates a new {@link Reference}.
	 * 
	 * @param expression
	 * @return A new Reference.
	 */
	public Reference newReference(Expression expression) {
		Reference reference = new Reference(this);
		reference.setExpression(expression);
		return reference;
	}

	/**
	 * Creates a new {@link ReflectionVariable}.
	 * 
	 * @return A new ReflectionVariable.
	 */
	public ReflectionVariable newReflectionVariable() {
		return new ReflectionVariable(this);
	}

	/**
	 * Creates a new {@link ReflectionVariable}.
	 * 
	 * @param expression
	 * @return A new ReflectionVariable.
	 */
	public ReflectionVariable newReflectionVariable(Expression expression) {
		ReflectionVariable reflectionVariable = new ReflectionVariable(this);
		reflectionVariable.setName(expression);
		return reflectionVariable;
	}

	/**
	 * Creates a new {@link ReturnStatement}.
	 * 
	 * @return A new ReturnStatement.
	 */
	public ReturnStatement newReturnStatement() {
		return new ReturnStatement(this);
	}

	/**
	 * Creates a new {@link ReturnStatement}.
	 * 
	 * @param expression
	 * @return A new ReturnStatement.
	 */
	public ReturnStatement newReturnStatement(Expression expression) {
		ReturnStatement returnStatement = new ReturnStatement(this);
		returnStatement.setExpression(expression);
		return returnStatement;
	}

	/**
	 * Creates a new {@link YieldExpression}.
	 * 
	 * @return A new YieldExpression.
	 */
	public YieldExpression newYieldExpression() {
		return new YieldExpression(this);
	}

	/**
	 * Creates a new {@link YieldExpression}.
	 * 
	 * @param expression
	 * @return A new YieldExpression.
	 */
	public YieldExpression newYieldExpression(Expression expression) {
		YieldExpression YieldExpression = new YieldExpression(this);
		YieldExpression.setExpression(expression);
		return YieldExpression;
	}

	/**
	 * Creates a new {@link YieldExpression}.
	 * 
	 * @param key
	 * @param expression
	 * @return A new YieldExpression.
	 */
	public YieldExpression newYieldExpression(Expression key, Expression expression) {
		YieldExpression YieldExpression = new YieldExpression(this);
		YieldExpression.setKey(key);
		YieldExpression.setExpression(expression);
		return YieldExpression;
	}

	/**
	 * Creates a new {@link Scalar}.
	 * 
	 * @return A new Scalar.
	 */
	public Scalar newScalar() {
		return new Scalar(this);
	}

	/**
	 * Creates a new scalar with a given type.
	 * 
	 * @param string
	 *            The scalar's value.
	 * @param scalarType
	 *            The scalar's type (e.g. Scalar.TYPE_STRING, Scalar.TYPE_INT
	 *            etc.).
	 * @return A new {@link Scalar}.
	 */
	public Scalar newScalar(String string, int scalarType) {
		Scalar scalar = newScalar(string);
		scalar.setScalarType(scalarType);
		return scalar;
	}

	/**
	 * Creates a new scalar with a default Scalar.TYPE_INT type.
	 * 
	 * @param string
	 *            The scalar's value.
	 * @return A new {@link Scalar}.
	 */
	public Scalar newScalar(String string) {
		Scalar scalar = new Scalar(this);
		scalar.setStringValue(string);
		return scalar;
	}

	/**
	 * Creates a new {@link SingleFieldDeclaration}.
	 * 
	 * @return A new SingleFieldDeclaration.
	 */
	public SingleFieldDeclaration newSingleFieldDeclaration() {
		return new SingleFieldDeclaration(this);
	}

	/**
	 * Creates a new {@link SingleFieldDeclaration}.
	 * 
	 * @param name
	 * @param value
	 * @return A new SingleFieldDeclaration.
	 */
	public SingleFieldDeclaration newSingleFieldDeclaration(Variable name, Expression value) {
		SingleFieldDeclaration singleFieldDeclaration = new SingleFieldDeclaration(this);
		singleFieldDeclaration.setName(name);
		singleFieldDeclaration.setValue(value);
		return singleFieldDeclaration;
	}

	/**
	 * Creates a new {@link StaticConstantAccess}.
	 * 
	 * @return A new StaticConstantAccess.
	 */
	public StaticConstantAccess newStaticConstantAccess() {
		return new StaticConstantAccess(this);
	}

	/**
	 * Creates a new {@link StaticConstantAccess}.
	 * 
	 * @param className
	 * @param constant
	 * @return A new StaticConstantAccess.
	 */
	public StaticConstantAccess newStaticConstantAccess(Identifier className, Identifier constant) {
		StaticConstantAccess staticConstantAccess = new StaticConstantAccess(this);
		staticConstantAccess.setClassName(className);
		staticConstantAccess.setConstant(constant);
		return staticConstantAccess;
	}

	/**
	 * Creates a new {@link StaticFieldAccess}.
	 * 
	 * @return A new StaticFieldAccess.
	 */
	public StaticFieldAccess newStaticFieldAccess() {
		return new StaticFieldAccess(this);
	}

	/**
	 * Creates a new {@link StaticFieldAccess}.
	 * 
	 * @param className
	 * @param field
	 * @return A new StaticFieldAccess.
	 */
	public StaticFieldAccess newStaticFieldAccess(Identifier className, Variable field) {
		StaticFieldAccess staticFieldAccess = new StaticFieldAccess(this);
		staticFieldAccess.setClassName(className);
		staticFieldAccess.setField(field);
		return staticFieldAccess;
	}

	/**
	 * Creates a new {@link StaticMethodInvocation}.
	 * 
	 * @return A new StaticMethodInvocation.
	 */
	public StaticMethodInvocation newStaticMethodInvocation() {
		return new StaticMethodInvocation(this);
	}

	/**
	 * Creates a new {@link StaticMethodInvocation}.
	 * 
	 * @param className
	 * @param method
	 * @return A new StaticMethodInvocation.
	 */
	public StaticMethodInvocation newStaticMethodInvocation(Identifier className, FunctionInvocation method) {
		StaticMethodInvocation staticMethodInvocation = new StaticMethodInvocation(this);
		staticMethodInvocation.setClassName(className);
		staticMethodInvocation.setMethod(method);
		return staticMethodInvocation;
	}

	/**
	 * Creates a new {@link StaticStatement}.
	 * 
	 * @return A new StaticStatement.
	 */
	public StaticStatement newStaticStatement() {
		return new StaticStatement(this);
	}

	/**
	 * Creates a new {@link StaticStatement}.
	 * 
	 * @param expressions
	 * @return A new StaticStatement.
	 */
	public StaticStatement newStaticStatement(List<Expression> expressions) {
		StaticStatement staticStatement = new StaticStatement(this);
		staticStatement.expressions().addAll(expressions);
		return staticStatement;
	}

	/**
	 * Creates a new {@link SwitchCase}.
	 * 
	 * @return A new SwitchCase.
	 */
	public SwitchCase newSwitchCase() {
		return new SwitchCase(this);
	}

	/**
	 * Creates a new {@link SwitchCase}.
	 * 
	 * @param value
	 * @param actions
	 * @param isDefault
	 * @return A new SwitchCase.
	 */
	public SwitchCase newSwitchCase(Expression value, List<Statement> actions, boolean isDefault) {
		SwitchCase switchCase = new SwitchCase(this);
		switchCase.setValue(value);
		switchCase.actions().addAll(actions);
		switchCase.setIsDefault(isDefault);
		return switchCase;
	}

	/**
	 * Creates a new {@link SwitchStatement}.
	 * 
	 * @return A new SwitchStatement.
	 */
	public SwitchStatement newSwitchStatement() {
		return new SwitchStatement(this);
	}

	/**
	 * Creates a new {@link SwitchStatement}.
	 * 
	 * @param expression
	 * @param body
	 * @return A new SwitchStatement.
	 */
	public SwitchStatement newSwitchStatement(Expression expression, Block body) {
		SwitchStatement switchStatement = new SwitchStatement(this);
		switchStatement.setExpression(expression);
		switchStatement.setBody(body);
		return switchStatement;
	}

	/**
	 * Creates a new {@link ThrowStatement}.
	 * 
	 * @return A new ThrowStatement.
	 */
	public ThrowStatement newThrowStatement() {
		return new ThrowStatement(this);
	}

	/**
	 * Creates a new {@link ThrowStatement}.
	 * 
	 * @param expression
	 * @return A new ThrowStatement.
	 */
	public ThrowStatement newThrowStatement(Expression expression) {
		ThrowStatement throwStatement = new ThrowStatement(this);
		throwStatement.setExpression(expression);
		return throwStatement;
	}

	/**
	 * Creates a new {@link TryStatement}.
	 * 
	 * @return A new TryStatement.
	 */
	public TryStatement newTryStatement() {
		return new TryStatement(this);
	}

	/**
	 * Creates a new {@link TryStatement}.
	 * 
	 * @param tryStatement
	 * @param catchClauses
	 * @return A new TryStatement.
	 */
	public TryStatement newTryStatement(Block block, List<CatchClause> catchClauses) {
		TryStatement tryStatement = new TryStatement(this);
		tryStatement.setBody(block);
		tryStatement.catchClauses().addAll(catchClauses);
		return tryStatement;
	}

	/**
	 * Creates a new {@link UnaryOperation}.
	 * 
	 * @return A new UnaryOperation.
	 */
	public UnaryOperation newUnaryOperation() {
		return new UnaryOperation(this);
	}

	/**
	 * Creates a new {@link UnaryOperation}.
	 * 
	 * @param expression
	 * @param operator
	 * @return A new UnaryOperation.
	 */
	public UnaryOperation newUnaryOperation(Expression expression, int operator) {
		UnaryOperation unaryOperation = new UnaryOperation(this);
		unaryOperation.setExpression(expression);
		unaryOperation.setOperator(operator);
		return unaryOperation;
	}

	/**
	 * Creates a new {@link Variable}.
	 * 
	 * The returned Variable is not dollared and does not have any name
	 * {@link Expression}.
	 * 
	 * @return A new {@link Variable}.
	 */
	public Variable newVariable() {
		return new Variable(this);
	}

	/**
	 * Creates a new {@link Variable} with a given name expression.
	 * 
	 * @param name
	 *            A name {@link Expression}
	 * @param isDollared
	 *            Indicate that this variable is dollared.
	 * @return A new {@link Variable}.
	 */
	public Variable newVariable(Expression name, boolean isDollared) {
		Variable variable = newVariable();
		variable.setIsDollared(isDollared);
		variable.setName(name);
		return variable;
	}

	/**
	 * Creates a new dollared {@link Variable} with a given name .
	 * 
	 * @param name
	 *            A name {@link String}
	 * @return A new {@link Variable}.
	 */
	public Variable newVariable(String name) {
		Variable variable = newVariable();
		variable.setIsDollared(true);
		variable.setName(newIdentifier(name));
		return variable;
	}

	/**
	 * Creates a new {@link WhileStatement}.
	 * 
	 * @return A new WhileStatement.
	 */
	public WhileStatement newWhileStatement() {
		return new WhileStatement(this);
	}

	/**
	 * Creates a new {@link WhileStatement}.
	 * 
	 * @param condition
	 * @param body
	 * @return A new WhileStatement.
	 */
	public WhileStatement newWhileStatement(Expression condition, Statement body) {
		WhileStatement whileStatement = new WhileStatement(this);
		whileStatement.setCondition(condition);
		whileStatement.setBody(body);
		return whileStatement;
	}

	/**
	 * Creates a new {@link NamespaceName}.
	 * 
	 * @param name
	 * @param isglobal
	 *            - Whether the namespace has a '\' prefix
	 * @param iscurrent
	 *            - Whether the namespace has a 'namespace' prefix
	 * @return A new NamespaceName.
	 */
	public NamespaceName newNamespaceName(final Collection<Identifier> segments, final boolean isglobal,
			final boolean iscurrent) {
		NamespaceName namespaceName = new NamespaceName(this);
		namespaceName.segments().addAll(segments);
		namespaceName.setGlobal(isglobal);
		namespaceName.setCurrent(iscurrent);
		return namespaceName;
	}

	/**
	 * Creates a new {@link NamespaceDeclaration}.
	 * 
	 * @param name
	 * @param body
	 * @return A new NamespaceDeclaration.
	 */
	public NamespaceDeclaration newNamespaceDeclaration(NamespaceName name, Block body) {
		NamespaceDeclaration namespaceDeclaration = new NamespaceDeclaration(this);
		namespaceDeclaration.setName(name);
		namespaceDeclaration.setBody(body);
		return namespaceDeclaration;
	}

	/**
	 * Creates a new {@link UseStatementPart}.
	 * 
	 * @param name
	 * @param alias
	 * @return A new UseStatementPart.
	 */
	public UseStatementPart newUseStatementPart(NamespaceName name, Identifier alias) {
		UseStatementPart usePart = new UseStatementPart(this);
		usePart.setName(name);
		usePart.setAlias(alias);
		return usePart;
	}

	/**
	 * Creates a new {@link UseStatement}.
	 * 
	 * @param parts
	 * @param statementType
	 * @return A new UseStatement.
	 */
	public UseStatement newUseStatement(Collection<UseStatementPart> parts, int statementType) {
		UseStatement useStatement = new UseStatement(this);
		useStatement.parts().addAll(parts);
		useStatement.setStatementType(statementType);
		return useStatement;
	}

	/**
	 * Creates a new {@link GotoLabel}.
	 * 
	 * @param label
	 * @return A new GotoLabel.
	 */
	public GotoLabel newGotoLabel(Identifier label) {
		GotoLabel gotoLabel = new GotoLabel(this);
		gotoLabel.setName(label);
		return gotoLabel;
	}

	/**
	 * Creates a new {@link GotoStatement}.
	 * 
	 * @param label
	 * @return A new GotoStatement.
	 */
	public GotoStatement newGotoStatement(Identifier label) {
		GotoStatement gotoStatement = new GotoStatement(this);
		gotoStatement.setLabel(label);
		return gotoStatement;
	}

	/**
	 * Creates a new {@link LambdaFunctionDeclaration}.
	 * 
	 * @param label
	 * @return A new LambdaFunctionDeclaration.
	 */
	public LambdaFunctionDeclaration newLambdaFunctionDeclaration(final Collection<FormalParameter> formalParameters,
			final Collection<Variable> lexicalVars, final Identifier returnType, final Block body,
			final boolean isReference, final boolean isStatic) {
		LambdaFunctionDeclaration lfDeclaration = new LambdaFunctionDeclaration(this);
		lfDeclaration.setBody(body);
		lfDeclaration.setIsReference(isReference);
		lfDeclaration.setStatic(isStatic);
		lfDeclaration.formalParameters().addAll(formalParameters);
		lfDeclaration.lexicalVariables().addAll(lexicalVars);
		lfDeclaration.setReturnType(returnType);
		return lfDeclaration;
	}

	/**
	 * Creates a new {@link ArrowFunctionDeclaration}.
	 * 
	 * @param label
	 * @return A new ArrowFunctionDeclaration.
	 */
	public ArrowFunctionDeclaration newArrowFunctionDeclaration(final Collection<FormalParameter> formalParameters,
			final Identifier returnType, final Expression body, final boolean isReference, final boolean isStatic) {
		ArrowFunctionDeclaration lfDeclaration = new ArrowFunctionDeclaration(this);
		lfDeclaration.setBody(body);
		lfDeclaration.setIsReference(isReference);
		lfDeclaration.setStatic(isStatic);
		lfDeclaration.formalParameters().addAll(formalParameters);
		lfDeclaration.setReturnType(returnType);
		return lfDeclaration;
	}

	/************************* php5.4 starts ***************************/

	public FullyQualifiedTraitMethodReference newFullyQualifiedTraitMethodReference(NamespaceName className,
			Identifier functionName) {
		FullyQualifiedTraitMethodReference lfDeclaration = new FullyQualifiedTraitMethodReference(this);
		lfDeclaration.setClassName(className);
		lfDeclaration.setFunctionName(functionName);
		return lfDeclaration;
	}

	public TraitAlias newTraitAlias(Expression traitMethod, int modifier, Identifier functionName) {
		TraitAlias lfDeclaration = new TraitAlias(this);
		lfDeclaration.setModifier(modifier);
		lfDeclaration.setTraitMethod(traitMethod);
		lfDeclaration.setFunctionName(functionName);
		return lfDeclaration;
	}

	public TraitAliasStatement newTraitAliasStatement(TraitAlias alias) {
		TraitAliasStatement lfDeclaration = new TraitAliasStatement(this);
		lfDeclaration.setAlias(alias);
		return lfDeclaration;
	}

	/**
	 * Creates a new {@link TraitDeclaration}.
	 * 
	 * @return A new TraitDeclaration.
	 */
	public TraitDeclaration newTraitDeclaration() {
		return new TraitDeclaration(this);
	}

	/**
	 * Creates a new {@link TraitDeclaration}.
	 * 
	 * @param modifier
	 * @param className
	 * @param superClass
	 * @param interfaces
	 * @param body
	 * @return A new TraitDeclaration.
	 */
	public TraitDeclaration newTraitDeclaration(int modifier, String className, String superClass,
			List<Identifier> interfaces, Block body) {
		TraitDeclaration traitDeclaration = new TraitDeclaration(this);
		traitDeclaration.setModifier(modifier);
		traitDeclaration.setName(newIdentifier(className));
		if (superClass != null) {
			traitDeclaration.setSuperClass(newIdentifier(superClass));
		} else {
			traitDeclaration.setSuperClass(null);
		}
		traitDeclaration.interfaces().addAll(interfaces);
		traitDeclaration.setBody(body);
		return traitDeclaration;
	}

	public TraitPrecedence newTraitPrecedence(FullyQualifiedTraitMethodReference methodReference,
			List<NamespaceName> trList) {
		TraitPrecedence lfDeclaration = new TraitPrecedence(this);
		lfDeclaration.setMethodReference(methodReference);
		lfDeclaration.setTrList(trList);
		return lfDeclaration;
	}

	public TraitPrecedenceStatement newTraitPrecedenceStatement(TraitPrecedence precedence) {
		TraitPrecedenceStatement lfDeclaration = new TraitPrecedenceStatement(this);
		lfDeclaration.setPrecedence(precedence);
		return lfDeclaration;
	}

	public TraitUseStatement newTraitUseStatement(List<NamespaceName> traitList, List<TraitStatement> tsList) {
		TraitUseStatement lfDeclaration = new TraitUseStatement(this);
		lfDeclaration.setTraitList(traitList);
		lfDeclaration.setTsList(tsList);
		return lfDeclaration;
	}

	/************************* php5.4 ends ***************************/

	public void setInsertUseStatement(boolean isInsertUseStatement) {
		// TODO Auto-generated method stub
//		this.rewriter.setInsertUseStatement(isInsertUseStatement);
	}
}
