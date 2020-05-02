package org.eclipse.php.core.ast.error;

/**
 * @author abertschi
 */
public interface ParseErrorListener {

    void onError(ErrorEvent ev);
}
