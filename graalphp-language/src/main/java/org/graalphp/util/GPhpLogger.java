package org.graalphp.util;

import java.util.Date;

/**
 * @author abertschi
 *
 * Simple logging interface to use in graalphp.
 * As for now, we dont use java.logging classes to avoid issues with graal native.
 */
public class GPhpLogger implements Logger {

    private static final GPhpLogger LOG = new GPhpLogger();

    private GPhpLogger() {
    }

    public static Logger getLogger(String name) {
        return LOG;
    }

    @Override
    public void fine(String msg) {
        System.err.println(format(msg, "fine"));
    }

    @Override
    public void info(String msg) {
        System.err.println(format(msg, "info"));
    }

    @Override
    public void finest(String msg) {
        System.err.println(format(msg, "finest"));
    }

    private String format(String msg, String lvl) {
        return String.format("[%1$tF %1$tT] [%2$-7s] %3$s",
                new Date(),
                lvl,
                msg
        );
    }
}

