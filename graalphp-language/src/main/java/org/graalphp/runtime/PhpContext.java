package org.graalphp.runtime;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Scope;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.Env;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.instrumentation.AllocationReporter;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Layout;
import com.oracle.truffle.api.object.Shape;
import com.oracle.truffle.api.source.Source;
import org.graalphp.PhpLanguage;

public final class PhpContext {

    private final Env env;
    private final BufferedReader input;
    private final PrintWriter output;
    private final PhpLanguage language;
    private final AllocationReporter allocationReporter;

    public PhpContext(PhpLanguage language, TruffleLanguage.Env env) {
        this.env = env;
        this.input = new BufferedReader(new InputStreamReader(env.in()));
        this.output = new PrintWriter(env.out(), true);
        this.language = language;
        this.allocationReporter = env.lookup(AllocationReporter.class);
    }

    /**
     * Return the current Truffle environment.
     */
    public Env getEnv() {
        return env;
    }

    /**
     * Returns the default input, i.e., the source for the read builtin. To allow unit
     * testing, we do not use {@link System#in} directly.
     */
    public BufferedReader getInput() {
        return input;
    }

    /**
     * The default default, i.e., the output for the println. To allow unit
     * testing, we do not use {@link System#out} directly.
     */
    public PrintWriter getOutput() {
        return output;
    }


    public static NodeInfo lookupNodeInfo(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        NodeInfo info = clazz.getAnnotation(NodeInfo.class);
        if (info != null) {
            return info;
        } else {
            return lookupNodeInfo(clazz.getSuperclass());
        }
    }

    /*
     * Methods for object creation / object property access.
     */
    public AllocationReporter getAllocationReporter() {
        return allocationReporter;
    }

//    /**
//     * Allocate an empty object. All new objects initially have no properties. Properties are added
//     * when they are first stored, i.e., the store triggers a shape change of the object.
//     */
//    public DynamicObject createObject(AllocationReporter reporter) {
//        DynamicObject object = null;
//        reporter.onEnter(null, 0, AllocationReporter.SIZE_UNKNOWN);
//        object = emptyShape.newInstance();
//        reporter.onReturnValue(object, 0, AllocationReporter.SIZE_UNKNOWN);
//        return object;
//    }

//    public static boolean isSLObject(Object value) {
//        /*
//         * LAYOUT.getType() returns a concrete implementation class, i.e., a class that is more
//         * precise than the base class DynamicObject. This makes the type check faster.
//         */
//        return LAYOUT.getType().isInstance(value) && LAYOUT.getType().cast(value).getShape().getObjectType() == SLObjectType.SINGLETON;
//    }

    /*
     * Methods for language interoperability.
     */
//    public static Object fromForeignValue(Object a) {
//        if (a instanceof Long || a instanceof SLBigNumber || a instanceof String || a instanceof Boolean) {
//            return a;
//        } else if (a instanceof Character) {
//            return String.valueOf(a);
//        } else if (a instanceof Number) {
//            return fromForeignNumber(a);
//        } else if (a instanceof TruffleObject) {
//            return a;
//        } else if (a instanceof PhpContext) {
//            return a;
//        }
//        CompilerDirectives.transferToInterpreter();
//        throw new IllegalStateException(a + " is not a Truffle value");
//    }

//    @TruffleBoundary
//    private static long fromForeignNumber(Object a) {
//        return ((Number) a).longValue();
//    }

    public CallTarget parse(Source source) {
        return env.parsePublic(source);
    }

    public static PhpContext getCurrent() {
        return PhpLanguage.getCurrentContext();
    }

}
