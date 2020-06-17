package org.graalphp;

import java.io.*;
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

    private final BufferedReader input;
    private final PrintWriter output;
    private final PhpLanguage language;

    PhpContext(PhpLanguage language, InputStream in, OutputStream out) {
        this.input = new BufferedReader(new InputStreamReader(in));
        this.output = new PrintWriter(out, true);
        this.language = language;
    }

    public PhpContext(PhpLanguage language, TruffleLanguage.Env env) {
        this.input = new BufferedReader(new InputStreamReader(env.in()));
        this.output = new PrintWriter(env.out(), true);
        this.language = language;
    }

    public BufferedReader getInput() {
        return input;
    }

    public PrintWriter getOutput() {
        return output;
    }

}
