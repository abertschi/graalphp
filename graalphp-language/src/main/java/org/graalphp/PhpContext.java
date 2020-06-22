package org.graalphp;

import com.oracle.truffle.api.TruffleLanguage;
import java.io.*;

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
