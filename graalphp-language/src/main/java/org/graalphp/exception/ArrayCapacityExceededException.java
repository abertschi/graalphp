package org.graalphp.exception;

import com.oracle.truffle.api.nodes.Node;

/**
 * @author abertschi
 */
public class ArrayCapacityExceededException extends PhpException {

    public ArrayCapacityExceededException(String msg, Node node) {
        super(msg, node);
    }
}
