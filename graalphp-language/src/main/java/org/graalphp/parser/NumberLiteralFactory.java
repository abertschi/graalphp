package org.graalphp.parser;

import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.literal.PhpDoubleNode;
import org.graalphp.nodes.literal.PhpLongNode;

/**
 * Factory to create an number based node from a string
 *
 * @author abertschi
 */
public class NumberLiteralFactory {

    public static PhpExprNode parseInteger(String str) {
        // TODO: handle hex representation
        // TODO: handle binary
        // TODO: handle octal
        try {
            return new PhpLongNode(Long.parseLong(str));
        } catch (NumberFormatException e) {
            return parseFloat(str);
        }
    }

    public static PhpExprNode parseFloat(String str) {
        double val = Double.parseDouble(str);
        return new PhpDoubleNode(val);
    }
}
