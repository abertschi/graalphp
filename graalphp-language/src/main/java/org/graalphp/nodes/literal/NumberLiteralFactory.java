package org.graalphp.nodes.literal;

import org.graalphp.nodes.PhpExprNode;

/**
 * Factory to create an number based node from a string
 * @author abertschi
 */
public class NumberLiteralFactory {

    public static PhpExprNode parseNumber(String str) {
        // TODO: handle hex representation
        // TODO: handle binary
        // TODO: handle octal
        try {
            long v = Long.parseLong(str);
            return new PhpLongNode(v);
        } catch (NumberFormatException e) {
            // TODO: add support for larger numbers
            // or impl float
            throw new UnsupportedOperationException("larger numbers not yet supported", e);
        }
    }
}
