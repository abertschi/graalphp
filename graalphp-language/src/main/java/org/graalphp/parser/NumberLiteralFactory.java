package org.graalphp.parser;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
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

    @TruffleBoundary
    public static boolean isBooleanLiteral(String val) {
        if (val == null) {
            return false;
        }
        String v = val.toLowerCase();
        return v.equals("true") || v.equals("false");
    }

    @TruffleBoundary
    public static boolean booleanLiteralToValue(String val) {
        String v = val.toLowerCase();
        if (v.equals("true")) {
            return true;
        }
        if (v.equals("false")) {
            return false;
        }
        throw new IllegalArgumentException("No boolean literal given");
    }
}
