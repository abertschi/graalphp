package org.graalphp;

;
import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import org.graalphp.nodes.PhpRootNode;
import org.graalphp.runtime.PhpContext;


@TruffleLanguage.Registration(
        id = PhpLanguage.ID,
        name = "php",
        defaultMimeType = PhpLanguage.MIME_TYPE,
        characterMimeTypes = PhpLanguage.MIME_TYPE,
        contextPolicy = ContextPolicy.SHARED,
        fileTypeDetectors = PhpFileDetector.class)


// No tags supported
@ProvidedTags({
        //        StandardTags.CallTag.class,
        //        StandardTags.StatementTag.class,
        //        StandardTags.RootTag.class,
        //        StandardTags.RootBodyTag.class,
        //        StandardTags.ExpressionTag.class,
        //        DebuggerTags.AlwaysHalt.class,
        //        StandardTags.ReadVariableTag.class,
        //        StandardTags.WriteVariableTag.class
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
        Source source = request.getSource();
        Map<String, RootCallTarget> functions = new HashMap<>();
        PhpRootNode evalMain = new PhpRootNode(this, null, functions);
        return Truffle.getRuntime().createCallTarget(evalMain);
    }

    @Override
    protected boolean isVisible(PhpContext context, Object value) {
        return !InteropLibrary.getFactory().getUncached(value).isNull(value);
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        return false;
    }

    @Override
    protected String toString(PhpContext context, Object value) {
        return toString(value);
    }

    public static String toString(Object value) {
        return "Unsupported";
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
        if (interop.isNumber(value)) { // more types
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
        return null;
    }

    public static PhpContext getCurrentContext() {
        return getCurrentContext(PhpLanguage.class);
    }
}
