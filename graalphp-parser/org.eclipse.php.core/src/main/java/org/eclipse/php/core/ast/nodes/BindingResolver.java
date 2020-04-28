/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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

import org.eclipse.dltk.core.*;
//import org.eclipse.dltk.internal.compiler.lookup.BlockScope;
//import org.eclipse.dltk.internal.compiler.lookup.LookupEnvironment;
//import org.eclipse.dltk.internal.compiler.lookup.SourceModuleScope;
import org.eclipse.dltk.ti.types.IEvaluatedType;
//import org.eclipse.php.internal.core.typeinference.IModelAccessCache;

/**
 * A binding resolver is an internal mechanism for figuring out the binding for
 * a major declaration, type, or name reference. This also handles the creation
 * and mapping between annotations and the ast nodes that define them.
 * <p>
 * The default implementation serves as the default binding resolver that does
 * no resolving whatsoever. Internal subclasses do all the real work.
 * </p>
 * 
 * @see AST#getBindingResolver
 */
@Deprecated
public class BindingResolver {

	/**
	 * Creates a binding resolver.
	 */
	BindingResolver() {
		// default implementation: do nothing
	}

	/**
	 * Finds the corresponding AST node from which the given binding originated.
	 * Returns <code>null</code> if the binding does not correspond to any node in
	 * the compilation unit.
	 * <p>
	 * The following table indicates the expected node type for the various
	 * different kinds of bindings:
	 * <ul>
	 * <li></li>
	 * <li>package - a <code>PackageDeclaration</code></li>
	 * <li>class or interface - a <code>TypeDeclaration</code> or a
	 * <code>ClassInstanceCreation</code> (for anonymous classes)</li>
	 * <li>primitive type - none</li>
	 * <li>array type - none</li>
	 * <li>field - a <code>VariableDeclarationFragment</code> in a
	 * <code>FieldDeclaration</code></li>
	 * <li>local variable - a <code>SingleVariableDeclaration</code>, or a
	 * <code>VariableDeclarationFragment</code> in a
	 * <code>VariableDeclarationStatement</code> or
	 * <code>VariableDeclarationExpression</code></li>
	 * <li>method - a <code>MethodDeclaration</code></li>
	 * <li>constructor - a <code>MethodDeclaration</code></li>
	 * <li>annotation type - an <code>AnnotationTypeDeclaration</code>
	 * <li>annotation type member - an <code>AnnotationTypeMemberDeclaration</code>
	 * </ul>
	 * </p>
	 * <p>
	 * The implementation of <code>CompilationUnit.findDeclaringNode</code> forwards
	 * to this method.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param binding
	 *            the binding
	 * @return the corresponding node where the bindings is declared, or
	 *         <code>null</code> if none
	 */
	ASTNode findDeclaringNode(IBinding binding) {
		return null;
	}

	/**
	 * Finds the corresponding AST node from which the given binding key originated.
	 * 
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param bindingKey
	 *            the binding key
	 * @return the corresponding node where the bindings is declared, or
	 *         <code>null</code> if none
	 */
	ASTNode findDeclaringNode(String bindingKey) {
		return null;
	}

	/**
	 * Allows the user to get information about the given old/new pair of AST nodes.
	 * <p>
	 * The default implementation of this method does nothing. Subclasses may
	 * reimplement.
	 * </p>
	 * 
	 * @param currentNode
	 *            the new node
	 * @return org.eclipse.dltk.compiler.ast.ASTNode
	 */
	org.eclipse.dltk.ast.ASTNode getCorrespondingNode(ASTNode currentNode) {
		return null;
	}

	/**
	 * Returns the new package binding corresponding to the given old package
	 * binding.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param packageBinding
	 *            the old package binding
	 * @return the new package binding IPackageBinding
	 *         getPackageBinding(IEvaluatedType packageBinding) { return null; }
	 */

	/**
	 * Returns the new type binding corresponding to the given old type binding.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param referenceBinding
	 *            the old type binding
	 * @return the new type binding
	 */
	ITypeBinding getTypeBinding(IEvaluatedType referenceBinding, ISourceModule sourceModule) {
		return null;
	}

	/**
	 * Returns the new type binding corresponding to the given type.
	 * 
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param type
	 *            the given type
	 * @return the new type binding
	 */
	ITypeBinding getTypeBinding(IType type) {
		return null;
	}

	/**
	 * Returns the new type binding corresponding to given types, resulting in an
	 * ambiguous type binding.
	 * 
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param types
	 *            given types
	 * @return the new type binding
	 */
	ITypeBinding getTypeBinding(IType[] types) {
		return null;
	}

	/**
	 * Returns the new variable binding corresponding to the given old variable
	 * binding.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param field
	 *            An {@link IField}
	 * @return the new variable binding
	 */
	IVariableBinding getVariableBinding(IField field) {
		return null;
	}

	/**
	 * Returns the new method binding corresponding to the given {@link IMethod} .
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            An {@link IMethod}
	 * @return the new method binding
	 */
	public IMethodBinding getMethodBinding(IMethod method) {
		return null;
	}

	/**
	 * Returns the new method return types binding corresponding to the given
	 * {@link IMethod} .
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            An {@link IMethod}
	 * @return the new method return types binding
	 */
	public ITypeBinding[] getMethodReturnTypeBinding(IMethod method) {
		return null;
	}

	/**
	 * Return the working copy owner for the receiver.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @return the working copy owner for the receiver
	 */
//	public WorkingCopyOwner getWorkingCopyOwner() {
//		return null;
//	}

	boolean isResolvedTypeInferredFromExpectedType(MethodInvocation methodInvocation) {
		return false;
	}

	/**
	 * Returns the compiler lookup environment used by this binding resolver.
	 * Returns <code>null</code> if none.
	 * 
	 * @return the lookup environment used by this resolver, or <code>null</code> if
	 *         none.
	 */
//	LookupEnvironment lookupEnvironment() {
//		return null;
//	}

	/**
	 * This method is used to record the scope and its corresponding node.
	 * <p>
	 * The default implementation of this method does nothing. Subclasses may
	 * reimplement.
	 * </p>
	 * 
	 * @param astNode
	 */
//	void recordScope(ASTNode astNode, BlockScope blockScope) {
//		// default implementation: do nothing
//	}

	/**
	 * Resolves and returns the compile-time constant expression value as specified
	 * in JLS2 15.28, if this expression has one. Constant expression values are
	 * unavailable unless bindings are requested when the AST is being built. If the
	 * type of the value is a primitive type, the result is the boxed equivalent
	 * (i.e., int returned as an <code>Integer</code>); if the type of the value is
	 * <code>String</code>, the result is the string itself. If the expression does
	 * not have a compile-time constant expression value, the result is
	 * <code>null</code>.
	 * <p>
	 * Resolving constant expressions takes into account the value of simple and
	 * qualified names that refer to constant variables (JLS2 4.12.4).
	 * </p>
	 * <p>
	 * Note 2: Compile-time constant expressions cannot denote <code>null</code> .
	 * So technically {@link NullLiteral} nodes are not constant expressions. The
	 * result is <code>null</code> for these nonetheless.
	 * </p>
	 * 
	 * @return the constant expression value, or <code>null</code> if this
	 *         expression has no constant expression value or if bindings were not
	 *         requested when the AST was created
	 * @since 3.1
	 */
	Object resolveConstantExpressionValue(Expression expression) {
		return null;
	}

	/**
	 * Resolves and returns the binding for the constructor being invoked.
	 * <p>
	 * The implementation of <code>ClassInstanceCreation.resolveConstructor</code>
	 * forwards to this method. Which constructor is invoked is often a function of
	 * the context in which the expression node is embedded as well as the
	 * expression subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param expression
	 *            the expression of interest
	 * @return the binding for the constructor being invoked, or <code>null</code>
	 *         if no binding is available
	 */
	IMethodBinding resolveConstructor(ClassInstanceCreation expression) {
		return null;
	}

	/**
	 * Resolves and returns the binding for the constructor being invoked.
	 * <p>
	 * The implementation of <code>ConstructorInvocation.resolveConstructor</code>
	 * forwards to this method. Which constructor is invoked is often a function of
	 * the context in which the expression node is embedded as well as the
	 * expression subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param expression
	 *            the expression of interest
	 * @return the binding for the constructor being invoked, or <code>null</code>
	 *         if no binding is available
	 */
	IMethodBinding resolveConstructor(MethodInvocation expression) {
		return null;
	}

	/**
	 * Resolves the type of the given expression and returns the type binding for
	 * it.
	 * <p>
	 * The implementation of <code>Expression.resolveTypeBinding</code> forwards to
	 * this method. The result is often a function of the context in which the
	 * expression node is embedded as well as the expression subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param expression
	 *            the expression whose type is of interest
	 * @return the binding for the type of the given expression, or
	 *         <code>null</code> if no binding is available
	 */
	ITypeBinding resolveExpressionType(Expression expression) {
		return null;
	}

	/**
	 * Resolves the given field access and returns the binding for it.
	 * <p>
	 * The implementation of <code>FieldAccess.resolveFieldBinding</code> forwards
	 * to this method. How the field resolves is often a function of the context in
	 * which the field access node is embedded as well as the field access subtree
	 * itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param fieldAccess
	 *            the field access of interest
	 * @return the binding for the given field access, or <code>null</code> if no
	 *         binding is available
	 */
	IVariableBinding resolveField(FieldAccess fieldAccess) {
		return null;
	}

	/**
	 * Resolves the given super field access and returns the binding for it.
	 * <p>
	 * The implementation of <code>SuperFieldAccess.resolveFieldBinding</code>
	 * forwards to this method. How the field resolves is often a function of the
	 * context in which the super field access node is embedded as well as the super
	 * field access subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param fieldAccess
	 *            the super field access of interest
	 * @return the binding for the given field access, or <code>null</code> if no
	 *         binding is available
	 */
	IVariableBinding resolveField(StaticFieldAccess fieldAccess) {
		return null;
	}

	/**
	 * Resolves the given constant access and returns the compile-time constant
	 * expression value.
	 * <p>
	 * The implementation of <code>StaticConstantAccess.resolveFieldBinding</code>
	 * forwards to this method. How the field resolves is often a function of the
	 * context in which the super field access node is embedded as well as the super
	 * field access subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param fieldAccess
	 *            the super field access of interest
	 * @return the binding for the given field access, or <code>null</code> if no
	 *         binding is available
	 */
	IVariableBinding resolveField(StaticConstantAccess constantAccess) {
		return null;
	}

	IVariableBinding resolveField(Scalar scalar) {
		return null;
	}

	IVariableBinding resolveField(Identifier name) {
		return null;
	}

	/**
	 * Resolves the given import declaration and returns the binding for it.
	 * <p>
	 * The implementation of <code>Include.resolveBinding</code> forwards to this
	 * method.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param importDeclaration
	 *            the import declaration of interest
	 * @return the binding for the given package declaration, or the package binding
	 *         (for on-demand imports) or type binding (for single-type imports), or
	 *         <code>null</code> if no binding is available
	 */
	IBinding resolveInclude(Include includeDeclaration) {
		return null;
	}

	/**
	 * Resolves the given function declaration and returns the binding for it.
	 * <p>
	 * The implementation of <code>FunctionDeclaration.resolveBinding</code>
	 * forwards to this method. How the method resolves is often a function of the
	 * context in which the method declaration node is embedded as well as the
	 * method declaration subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            the method or constructor declaration of interest
	 * @return the binding for the given method declaration, or <code>null</code> if
	 *         no binding is available
	 */
	IFunctionBinding resolveFunction(FunctionDeclaration function) {
		return null;
	}

	/**
	 * Resolves the given method declaration and returns the binding for it.
	 * <p>
	 * The implementation of <code>MethodDeclaration.resolveBinding</code> forwards
	 * to this method. How the method resolves is often a function of the context in
	 * which the method declaration node is embedded as well as the method
	 * declaration subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            the method or constructor declaration of interest
	 * @return the binding for the given method declaration, or <code>null</code> if
	 *         no binding is available
	 */
	IMethodBinding resolveMethod(MethodDeclaration method) {
		return null;
	}

	/**
	 * Resolves the given method invocation and returns the binding for it.
	 * <p>
	 * The implementation of <code>MethodInvocation.resolveMethodBinding</code>
	 * forwards to this method. How the method resolves is often a function of the
	 * context in which the method invocation node is embedded as well as the method
	 * invocation subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            the method invocation of interest
	 * @return the binding for the given method invocation, or <code>null</code> if
	 *         no binding is available
	 */
	IFunctionBinding resolveFunction(FunctionInvocation function) {
		return null;
	}

	/**
	 * Resolves the given function invocation and returns the binding for it.
	 * <p>
	 * The implementation of <code>MethodInvocation.resolveMethodBinding</code>
	 * forwards to this method. How the method resolves is often a function of the
	 * context in which the method invocation node is embedded as well as the method
	 * invocation subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            the method invocation of interest
	 * @return the binding for the given method invocation, or <code>null</code> if
	 *         no binding is available
	 */
	IMethodBinding resolveMethod(MethodInvocation method) {
		return null;
	}

	/**
	 * Resolves the given method invocation and returns the binding for it.
	 * <p>
	 * The implementation of <code>MethodInvocation.resolveMethodBinding</code>
	 * forwards to this method. How the method resolves is often a function of the
	 * context in which the method invocation node is embedded as well as the method
	 * invocation subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param method
	 *            the method invocation of interest
	 * @return the binding for the given method invocation, or <code>null</code> if
	 *         no binding is available
	 */
	IMethodBinding resolveMethod(StaticMethodInvocation method) {
		return null;
	}

	/**
	 * Resolves the given name and returns the type binding for it.
	 * <p>
	 * The implementation of <code>Identifier.resolveBinding</code> forwards to this
	 * method. How the name resolves is often a function of the context in which the
	 * name node is embedded as well as the name itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param name
	 *            the name of interest
	 * @return the binding for the name, or <code>null</code> if no binding is
	 *         available
	 */
	IBinding resolveName(Identifier name) {
		return null;
	}

	/**
	 * Resolves the given name and returns the type binding for it.
	 * <p>
	 * The implementation of <code>Name.resolveBinding</code> forwards to this
	 * method. How the name resolves is often a function of the context in which the
	 * name node is embedded as well as the name itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param name
	 *            the name of interest
	 * @return the binding for the name, or <code>null</code> if no binding is
	 *         available
	 */
	IVariableBinding resolveVariable(Variable variable) {
		return null;
	}

	/**
	 * Resolves the given fields and returns the type binding for it.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param the
	 *            field declaration
	 * @return the binding for the field declaration, or <code>null</code> if no
	 *         binding is available
	 */
	ITypeBinding getTypeBinding(SingleFieldDeclaration fieldDeclaration) {
		return null;
	}

	/**
	 * TODO : For PHP 5.3 ??? Resolves the given package declaration and returns the
	 * binding for it.
	 * <p>
	 * The implementation of <code>PackageDeclaration.resolveBinding</code> forwards
	 * to this method.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param pkg
	 *            the package declaration of interest
	 * @return the binding for the given package declaration, or <code>null</code>
	 *         if no binding is available IPackageBinding
	 *         resolvePackage(PackageDeclaration pkg) { return null; }
	 */

	/**
	 * TODO should add doc comment block Resolves the given reference and returns
	 * the binding for it.
	 * <p>
	 * The implementation of <code>MemberRef.resolveBinding</code> forwards to this
	 * method. How the name resolves is often a function of the context in which the
	 * name node is embedded as well as the name itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param ref
	 *            the reference of interest
	 * @return the binding for the reference, or <code>null</code> if no binding is
	 *         available
	 * @since 3.0 IBinding resolveReference(MemberRef ref) { return null; }
	 */

	/**
	 * TODO should add doc comment block Resolves the given reference and returns
	 * the binding for it.
	 * <p>
	 * The implementation of <code>MethodRef.resolveBinding</code> forwards to this
	 * method. How the name resolves is often a function of the context in which the
	 * name node is embedded as well as the name itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param ref
	 *            the reference of interest
	 * @return the binding for the reference, or <code>null</code> if no binding is
	 *         available
	 * @since 3.0 IBinding resolveReference(MethodRef ref) { return null; }
	 */

	/**
	 * Resolves the given class or interface declaration and returns the binding for
	 * it.
	 * <p>
	 * The implementation of <code>TypeDeclaration.resolveBinding</code> (and
	 * <code>TypeDeclarationStatement.resolveBinding</code>) forwards to this
	 * method. How the type declaration resolves is often a function of the context
	 * in which the type declaration node is embedded as well as the type
	 * declaration subtree itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param type
	 *            the class or interface declaration of interest
	 * @return the binding for the given type declaration, or <code>null</code> if
	 *         no binding is available
	 */
	ITypeBinding resolveType(TypeDeclaration type) {
		return null;
	}

	/**
	 * Resolves the given type parameter and returns the type binding for the type
	 * parameter.
	 * <p>
	 * The implementation of <code>TypeParameter.resolveBinding</code> forwards to
	 * this method. How the declaration resolves is often a function of the context
	 * in which the declaration node is embedded as well as the declaration subtree
	 * itself.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param typeParameter
	 *            the type paramter of interest
	 * @return the binding for the given type parameter, or <code>null</code> if no
	 *         binding is available
	 * @since 3.1
	 */
	ITypeBinding resolveTypeParameter(FormalParameter typeParameter) {
		return null;
	}

	/**
	 * Resolves the given variable declaration and returns the binding for it.
	 * <p>
	 * The implementation of <code>VariableDeclaration.resolveBinding</code>
	 * forwards to this method. How the variable declaration resolves is often a
	 * function of the context in which the variable declaration node is embedded as
	 * well as the variable declaration subtree itself. VariableDeclaration
	 * declarations used as local variable, formal parameter and exception variables
	 * resolve to local variable bindings; variable declarations used to declare
	 * fields resolve to field bindings.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param variable
	 *            the variable declaration of interest
	 * @return the binding for the given variable declaration, or <code>null</code>
	 *         if no binding is available
	 */
	IVariableBinding resolveVariable(FieldsDeclaration variable) {
		return null;
	}

	/**
	 * Resolves the given well known type by name and returns the type binding for
	 * it.
	 * <p>
	 * The implementation of <code>AST.resolveWellKnownType</code> forwards to this
	 * method.
	 * </p>
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param name
	 *            the name of a well known type
	 * @return the corresponding type binding, or <code>null<code> if the named type
	 *         is not considered well known or if no binding can be found for it
	 */
	ITypeBinding resolveWellKnownType(String name) {
		return null;
	}

	/**
	 * Answer an array type binding with the given type binding and the given
	 * dimensions.
	 * 
	 * <p>
	 * If the given type binding is an array binding, then the resulting dimensions
	 * is the given dimensions plus the existing dimensions of the array binding.
	 * Otherwise the resulting dimensions is the given dimensions.
	 * </p>
	 * 
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param typeBinding
	 *            the given type binding
	 * @param dimensions
	 *            the given dimensions
	 * @return an array type binding with the given type binding and the given
	 *         dimensions
	 * @throws IllegalArgumentException
	 *             if the type binding represents the <code>void</code> type binding
	 *             TODO : make sure we don't need it. ITypeBinding
	 *             resolveArrayType(ITypeBinding typeBinding, int dimensions) {
	 *             return null; }
	 */

	/**
	 * Returns the {@link IEvaluatedType} according to the offset and the length.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 */
	protected IEvaluatedType getEvaluatedType(int offset, int length) {
		return null;
	}

	/**
	 * Returns an {@link IModelElement} array according to the offset and the
	 * length. The result is filtered using the 'File-Network'.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param offset
	 * @param length
	 */
	public IModelElement[] getModelElements(int offset, int length) {
		return null;
	}

	/**
	 * Returns an {@link IModelElement} array according to the offset and the
	 * length. Use the filter flag to indicate whether the 'File-Network' should be
	 * used to filter the results.
	 * <p>
	 * The default implementation of this method returns <code>null</code>.
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @param offset
	 * @param length
	 * @param filter
	 *            Indicate whether to use the File-Network in order to filter the
	 *            results.
	 */
	public IModelElement[] getModelElements(int offset, int length, boolean filter) {
		return null;
	}

	/**
	 * Returns the compilation unit scope used by this binding resolver. Returns
	 * <code>null</code> if none.
	 * 
	 * @return the compilation unit scope by this resolver, or <code>null</code> if
	 *         none.
	 */
//	public SourceModuleScope scope() {
//		return null;
//	}

	/**
	 * Allows the user to store information about the given old/new pair of AST
	 * nodes.
	 * <p>
	 * The default implementation of this method does nothing. Subclasses may
	 * reimplement.
	 * </p>
	 * 
	 * @param newNode
	 *            the new AST node
	 * @param oldASTNode
	 *            the old AST node
	 */
	void store(ASTNode newNode, org.eclipse.dltk.ast.ASTNode oldASTNode) {
		// default implementation: do nothing
	}

	/**
	 * Allows the user to update information about the given old/new pair of AST
	 * nodes.
	 * <p>
	 * The default implementation of this method does nothing. Subclasses may
	 * reimplement.
	 * </p>
	 * 
	 * @param node
	 *            the old AST node
	 * @param newNode
	 *            the new AST node
	 */
	void updateKey(org.eclipse.dltk.ast.ASTNode node, ASTNode newNode) {
		// default implementation: do nothing
	}

//	/**
//	 * Returns model access cache for this binding resolver
//	 */
//	public IModelAccessCache getModelAccessCache() {
//		return null;
//	}

	/**
	 * This enables CachedTypeInferencer for binding resolver
	 */
	public void startBindingSession() {
	}

	/**
	 * Clean most caches!
	 */
	public void stopBindingSession() {
	}
}
