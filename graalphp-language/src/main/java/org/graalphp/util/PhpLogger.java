package org.graalphp.util;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

/**
 * @author abertschi
 * <p>
 * Simple logging interface to use in graalphp.
 * As for now, we dont use java.logging classes to avoid issues with graal native.
 */
public class PhpLogger implements Logger {

    private final String name;

    public static boolean DISABLE = true;

    public static boolean DISABLE_FINEST = true;
    public static boolean DISABLE_FINE = true;

    public static synchronized void disable() {
        DISABLE = false;
    }

    public static synchronized void enable() {
        DISABLE = true;
    }

    private PhpLogger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        // XXX: to reduce object creation make logger static
        // fine for now, Logger is debug utility
        return new PhpLogger(name);
    }

    @TruffleBoundary
    private void doLog(String msg, String lvl) {
        System.err.println(format(msg, lvl));
    }

    @Override
    public void fine(String msg) {
        if (DISABLE || DISABLE_FINE) return;
        doLog(msg, "fine");
    }

    @Override
    public void info(String msg) {
        if (DISABLE) return;
        doLog(msg, "info");
    }

    @Override
    public void finest(String msg) {
        if (DISABLE || DISABLE_FINEST) return;
        doLog(msg, "finest");
    }

    @Override
    public void warn(String msg) {
        doLog(msg, "warn");
    }

    @Override
    public void parserEnumerationError(String msg) {
        doLog(msg, "error");
    }

    private String format(String msg, String lvl) {
        // [%1$tF %1$tT] [%2$-7s] %3$s",
        return String.format("[%1$-15s] [%2$-7s] %3$s",
                name,
                lvl,
                msg
        );
    }
}

