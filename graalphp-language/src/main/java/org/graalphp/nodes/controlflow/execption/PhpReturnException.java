package org.graalphp.nodes.controlflow.execption;

import com.oracle.truffle.api.nodes.ControlFlowException;

/**
 * Truffle idiomatic way to return values
 *
 * @author abertschi
 */
public class PhpReturnException extends ControlFlowException {

    private final Object result;

    public PhpReturnException(Object result) {
        this.result = result;
    }

    public Object getReturnValue() {
        return this.result;
    }
}
