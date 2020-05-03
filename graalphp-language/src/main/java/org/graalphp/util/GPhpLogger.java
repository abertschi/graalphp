package org.graalphp.util;

/**
 * @author abertschi
 * Simple logging interface to use in graalphp
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
        System.out.println(msg);
    }

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void finest(String msg) {
        System.out.println(msg);
    }
}

