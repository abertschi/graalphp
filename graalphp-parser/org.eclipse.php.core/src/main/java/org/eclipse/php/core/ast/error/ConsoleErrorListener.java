package org.eclipse.php.core.ast.error;

/**
 * Error Listener which prints to stderr
 *
 * @author abertschi
 */
public class ConsoleErrorListener implements ParseErrorListener {

    @Override
    public void onError(ErrorEvent ev) {
        System.err.println(ev.getMessage());
        System.err.println(ev.toString());
    }
}
