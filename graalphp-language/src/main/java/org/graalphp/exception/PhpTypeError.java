package org.graalphp.exception;

import com.oracle.truffle.api.nodes.Node;

/**
 * @author abertschi
 */
public class PhpTypeError extends PhpException {
    public PhpTypeError(String msg, Node location) {
        super(msg, location);
    }
}
