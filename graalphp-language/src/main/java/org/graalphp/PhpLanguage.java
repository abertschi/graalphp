/*
 * Copyright (c) 2012, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.graalphp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Scope;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.debug.DebuggerTags;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import org.graalphp.builtins.SLBuiltinNode;
import org.graalphp.nodes.SLEvalRootNode;
import org.graalphp.nodes.local.SLLexicalScope;
import org.graalphp.parser.SimpleLanguageParser;
import org.graalphp.runtime.SLBigNumber;
import org.graalphp.runtime.SLContext;
import org.graalphp.runtime.SLFunction;
import org.graalphp.runtime.SLNull;


@TruffleLanguage.Registration(
        id = PhpLanguage.ID,
        name = "graalphp",
        defaultMimeType = PhpLanguage.MIME_TYPE,
        characterMimeTypes = PhpLanguage.MIME_TYPE,
        contextPolicy = ContextPolicy.SHARED,
        fileTypeDetectors = SLFileDetector.class)


@ProvidedTags({
        StandardTags.CallTag.class,
        StandardTags.StatementTag.class,
        StandardTags.RootTag.class,
        StandardTags.RootBodyTag.class,
        StandardTags.ExpressionTag.class,
        DebuggerTags.AlwaysHalt.class,
        StandardTags.ReadVariableTag.class,
        StandardTags.WriteVariableTag.class
})

public final class PhpLanguage extends TruffleLanguage<SLContext> {
    public static volatile int counter;

    public static final String ID = "sl";
    public static final String MIME_TYPE = "application/x-sl";

    public PhpLanguage() {
        counter++;
    }

    @Override
    protected SLContext createContext(Env env) {
        return new SLContext(this, env, new ArrayList<>(EXTERNAL_BUILTINS));
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        Source source = request.getSource();
        Map<String, RootCallTarget> functions;
        /*
         * Parse the provided source. At this point, we do not have a SLContext yet. Registration of
         * the functions with the SLContext happens lazily in SLEvalRootNode.
         */
        if (request.getArgumentNames().isEmpty()) {
            functions = SimpleLanguageParser.parseSL(this, source);
        } else {
            Source requestedSource = request.getSource();
            StringBuilder sb = new StringBuilder();
            sb.append("function main(");
            String sep = "";
            for (String argumentName : request.getArgumentNames()) {
                sb.append(sep);
                sb.append(argumentName);
                sep = ",";
            }
            sb.append(") { return ");
            sb.append(request.getSource().getCharacters());
            sb.append(";}");
            String language = requestedSource.getLanguage() == null ? ID : requestedSource.getLanguage();
            Source decoratedSource = Source.newBuilder(language, sb.toString(), request.getSource().getName()).build();
            functions = SimpleLanguageParser.parseSL(this, decoratedSource);
        }

        RootCallTarget main = functions.get("main");
        RootNode evalMain;
        if (main != null) {
            /*
             * We have a main function, so "evaluating" the parsed source means invoking that main
             * function. However, we need to lazily register functions into the SLContext first, so
             * we cannot use the original SLRootNode for the main function. Instead, we create a new
             * SLEvalRootNode that does everything we need.
             */
            evalMain = new SLEvalRootNode(this, main, functions);
        } else {
            /*
             * Even without a main function, "evaluating" the parsed source needs to register the
             * functions into the SLContext.
             */
            evalMain = new SLEvalRootNode(this, null, functions);
        }
        return Truffle.getRuntime().createCallTarget(evalMain);
    }

    /*
     * Still necessary for the old SL TCK to pass. We should remove with the old TCK. New language
     * should not override this.
     */
    @SuppressWarnings("deprecation")
    @Override
    protected Object findExportedSymbol(SLContext context, String globalName, boolean onlyExplicit) {
        return context.getFunctionRegistry().lookup(globalName, false);
    }

    @Override
    protected boolean isVisible(SLContext context, Object value) {
        return !InteropLibrary.getFactory().getUncached(value).isNull(value);
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        if (!(object instanceof TruffleObject)) {
            return false;
        } else if (object instanceof SLBigNumber || object instanceof SLFunction || object instanceof SLNull) {
            return true;
        } else if (SLContext.isSLObject(object)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String toString(SLContext context, Object value) {
        return toString(value);
    }

    public static String toString(Object value) {
        try {
            if (value == null) {
                return "ANY";
            }
            InteropLibrary interop = InteropLibrary.getFactory().getUncached(value);
            if (interop.fitsInLong(value)) {
                return Long.toString(interop.asLong(value));
            } else if (interop.isBoolean(value)) {
                return Boolean.toString(interop.asBoolean(value));
            } else if (interop.isString(value)) {
                return interop.asString(value);
            } else if (interop.isNull(value)) {
                return "NULL";
            } else if (interop.isExecutable(value)) {
                if (value instanceof SLFunction) {
                    return ((SLFunction) value).getName();
                } else {
                    return "Function";
                }
            } else if (interop.hasMembers(value)) {
                return "Object";
            } else if (value instanceof SLBigNumber) {
                return value.toString();
            } else {
                return "Unsupported";
            }
        } catch (UnsupportedMessageException e) {
            CompilerDirectives.transferToInterpreter();
            throw new AssertionError();
        }
    }

    @Override
    protected Object findMetaObject(SLContext context, Object value) {
        return getMetaObject(value);
    }

    public static String getMetaObject(Object value) {
        if (value == null) {
            return "ANY";
        }
        InteropLibrary interop = InteropLibrary.getFactory().getUncached(value);
        if (interop.isNumber(value) || value instanceof SLBigNumber) {
            return "Number";
        } else if (interop.isBoolean(value)) {
            return "Boolean";
        } else if (interop.isString(value)) {
            return "String";
        } else if (interop.isNull(value)) {
            return "NULL";
        } else if (interop.isExecutable(value)) {
            return "Function";
        } else if (interop.hasMembers(value)) {
            return "Object";
        } else {
            return "Unsupported";
        }
    }

    @Override
    protected SourceSection findSourceLocation(SLContext context, Object value) {
        if (value instanceof SLFunction) {
            return ((SLFunction) value).getDeclaredLocation();
        }
        return null;
    }

    @Override
    public Iterable<Scope> findLocalScopes(SLContext context, Node node, Frame frame) {
        final SLLexicalScope scope = SLLexicalScope.createScope(node);
        return new Iterable<Scope>() {
            @Override
            public Iterator<Scope> iterator() {
                return new Iterator<Scope>() {
                    private SLLexicalScope previousScope;
                    private SLLexicalScope nextScope = scope;

                    @Override
                    public boolean hasNext() {
                        if (nextScope == null) {
                            nextScope = previousScope.findParent();
                        }
                        return nextScope != null;
                    }

                    @Override
                    public Scope next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Object functionObject = findFunctionObject();
                        Scope vscope = Scope.newBuilder(nextScope.getName(), nextScope.getVariables(frame)).node(nextScope.getNode()).arguments(nextScope.getArguments(frame)).rootInstance(
                                        functionObject).build();
                        previousScope = nextScope;
                        nextScope = null;
                        return vscope;
                    }

                    private Object findFunctionObject() {
                        String name = node.getRootNode().getName();
                        return context.getFunctionRegistry().getFunction(name);
                    }
                };
            }
        };
    }

    @Override
    protected Iterable<Scope> findTopScopes(SLContext context) {
        return context.getTopScopes();
    }

    public static SLContext getCurrentContext() {
        return getCurrentContext(PhpLanguage.class);
    }

    private static final List<NodeFactory<? extends SLBuiltinNode>> EXTERNAL_BUILTINS = Collections.synchronizedList(new ArrayList<>());

    public static void installBuiltin(NodeFactory<? extends SLBuiltinNode> builtin) {
        EXTERNAL_BUILTINS.add(builtin);
    }

}
