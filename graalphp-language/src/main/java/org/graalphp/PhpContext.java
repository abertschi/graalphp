package org.graalphp;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.NodeFactory;
import com.oracle.truffle.api.frame.FrameDescriptor;
import org.graalphp.builtins.PhpBuiltinNode;
import org.graalphp.builtins.PrintBuiltinFactory;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.function.PhpFunctionRootNode;
import org.graalphp.nodes.localvar.PhpReadArgNode;
import org.graalphp.parser.ParseScope;
import org.graalphp.types.PhpFunction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class PhpContext {

    private final BufferedReader input;
    private final PrintWriter output;
    private final PhpLanguage language;
    private ParseScope rootScope;

    public PhpContext(PhpLanguage language, TruffleLanguage.Env env) {
        this.input = new BufferedReader(new InputStreamReader(env.in()));
        this.output = new PrintWriter(env.out(), true);
        this.language = language;
        this.rootScope = ParseScope.newGlobalScope();
        installBuiltins();
    }

    public BufferedReader getInput() {
        return input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    private void installBuiltins() {
        installBuiltin("print", PrintBuiltinFactory.getInstance());
    }

    public void installBuiltin(String name, NodeFactory<? extends PhpBuiltinNode> factory) {
        final int argCount = factory.getExecutionSignature().size();
        PhpExprNode[] arguments = new PhpExprNode[argCount];
        for (int i = 0; i < argCount; i++) {
            arguments[i] = new PhpReadArgNode(i);
        }
        PhpBuiltinNode builtin = factory.createNode((Object) arguments);
        FrameDescriptor functionDescriptor = new FrameDescriptor();
        PhpFunctionRootNode rootNode =
                new PhpFunctionRootNode(language, functionDescriptor, name, builtin);
        RootCallTarget callTarget = Truffle.getRuntime().createCallTarget(rootNode);
        PhpFunction function =
                new PhpFunction(name, new ParseScope(functionDescriptor, rootScope), callTarget);
        rootScope.getFunctions().register(name, function, false);
    }

    public ParseScope getRootScope() {
        return rootScope;
    }

    public void setRootScope(ParseScope rootScope) {
        this.rootScope = rootScope;
    }
}
