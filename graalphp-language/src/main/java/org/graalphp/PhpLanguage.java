package org.graalphp;


import java.util.*;

import com.oracle.truffle.api.*;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.interop.UnsupportedMessageException;
import com.oracle.truffle.api.source.SourceSection;
import org.graalphp.nodes.PhpRootNode;
import org.graalphp.runtime.PhpContext;
import org.graalphp.types.PhpNull;


@TruffleLanguage.Registration(
        id = PhpLanguage.ID,
        name = "graalphp",
        defaultMimeType = PhpLanguage.MIME_TYPE,
        characterMimeTypes = PhpLanguage.MIME_TYPE,
        contextPolicy = ContextPolicy.SHARED,
        fileTypeDetectors = PhpFileDetector.class)


// No tags supported
@ProvidedTags({
//                StandardTags.CallTag.class,
//                StandardTags.StatementTag.class,
//                StandardTags.RootTag.class,
//                StandardTags.RootBodyTag.class,
//                StandardTags.ExpressionTag.class,
//                DebuggerTags.AlwaysHalt.class,
//                StandardTags.ReadVariableTag.class,
//                StandardTags.WriteVariableTag.class
})

public final class PhpLanguage extends TruffleLanguage<PhpContext> {

    public static final String ID = "php";
    public static final String MIME_TYPE = "application/x-php";

    public PhpLanguage() {
    }

    @Override
    protected PhpContext createContext(Env env) {
        return new PhpContext(this, env);
    }

    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {
        Map<String, RootCallTarget> functions = new HashMap<>();
        PhpRootNode evalMain = new PhpRootNode(this, null, functions);
        return Truffle.getRuntime().createCallTarget(evalMain);
    }

//    /*
//     * Still necessary for the old SL TCK to pass. We should remove with the old TCK. New language
//     * should not override this.
//     */
//    @SuppressWarnings("deprecation")
//    @Override
//    protected Object findExportedSymbol(PhpContext context, String globalName, boolean onlyExplicit) {
//        return context.getFunctionRegistry().lookup(globalName, false);
//    }

    @Override
    protected boolean isVisible(PhpContext context, Object value) {
        return !InteropLibrary.getFactory().getUncached(value).isNull(value);
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        if (!(object instanceof TruffleObject)) {
            return false;
        } else if (object instanceof PhpNull) {
            return true;
//        } else if (PhpContext.isSLObject(object)) {
//            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String toString(PhpContext context, Object value) {
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
//                if (value instanceof SLFunction) {
//                    return ((SLFunction) value).getName();
//                } else {
                    return "Function";
//                }
            } else if (interop.hasMembers(value)) {
                return "Object";
//            } else if (value instanceof SLBigNumber) {
//                return value.toString();
            } else {
                return "Unsupported";
            }
        } catch (UnsupportedMessageException e) {
            CompilerDirectives.transferToInterpreter();
            throw new AssertionError();
        }
    }

    @Override
    protected Object findMetaObject(PhpContext context, Object value) {
        return getMetaObject(value);
    }

    public static String getMetaObject(Object value) {
        if (value == null) {
            return "ANY";
        }
        InteropLibrary interop = InteropLibrary.getFactory().getUncached(value);
        if (interop.isNumber(value)) {
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
    protected SourceSection findSourceLocation(PhpContext context, Object value) {
//        if (value instanceof SLFunction) {
//            return ((SLFunction) value).getDeclaredLocation();
//        }
        return null;
    }

// TODO
//    @Override
//    public Iterable<Scope> findLocalScopes(SLContext context, Node node, Frame frame) {
//    }

//    @Override
//    protected Iterable<Scope> findTopScopes(PhpContext context) {
//        return context.getTopScopes();
//    }

    public static PhpContext getCurrentContext() {
        return getCurrentContext(PhpLanguage.class);
    }

    private static final List<NodeFactory<?>> EXTERNAL_BUILTINS = Collections.synchronizedList(new ArrayList<>());

    public static void installBuiltin(NodeFactory<? extends Object> builtin) {
        throw new UnsupportedOperationException();
//        EXTERNAL_BUILTINS.add(builtin);
    }
}


