package org.graalphp.util;

/**
 * @author abertschi
 */
public interface Logger {
    void fine(String msg);
    void info(String msg);
    void finest(String msg);
    void parserEnumerationError(String msg);
}
