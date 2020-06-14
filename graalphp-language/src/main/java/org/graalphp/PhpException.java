package org.graalphp;

import com.oracle.truffle.api.TruffleException;
import com.oracle.truffle.api.nodes.Node;

/**
 * Represents an Exception throw by the Graalphp Runtime
 * during evaluation of the AST (after parsing).
 *
 * @author abertschi
 */
public class PhpException extends RuntimeException implements TruffleException {

    private final Node location;

    public PhpException(String msg, Node location) {
        super(msg);
        this.location = location;
    }

    @Override
    public final Throwable fillInStackTrace() {
        /* In order to be efficient, a {@link TruffleException} should override
         * {@link Throwable#fillInStackTrace()} without the {@code synchronized} modifier, so that it
         * returns {@code this}: */
        return this;
    }

    @Override
    public Node getLocation() {
        return location;
    }
}
