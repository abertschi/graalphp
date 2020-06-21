package org.graalphp.exception;

import com.oracle.truffle.api.nodes.Node;

/**
 * @author abertschi
 */
public class PhpUndefFunctionException extends PhpException {

    public PhpUndefFunctionException(String functionName, Node location) {
        super(functionName, location);
    }
}
