package org.eclipse.php.core.ast.error;

/**
 * Error listener which throws exception on every occurring error
 *
 * @author abertschi
 */
public class BailoutErrorListener implements ParseErrorListener {

    @Override
    public void onError(ErrorEvent ev) {
        throw new RuntimeException(ev.toString());
    }
}
