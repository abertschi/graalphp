package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Truffle idiomatic way to return values
 *
 * @author abertschi
 */
public final class PhpReturnException extends ControlFlowException {

    private final Object result;

    public PhpReturnException(Object result) {
        this.result = result;
    }

    public Object getReturnValue() {
        return this.result;
    }
}
