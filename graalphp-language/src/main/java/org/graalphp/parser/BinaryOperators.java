package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.Assignment;

/**
 * Uniform enum for binary operators.
 * <p>
 * Values according to InfixExpression class form graalphp-parser
 *
 * @author abertschi
 */
public enum BinaryOperators {
    OP_IS_IDENTICAL(0),
    // '!=='
    OP_IS_NOT_IDENTICAL(1),
    // '=='
    OP_IS_EQUAL(2),
    // '!='
    OP_IS_NOT_EQUAL(3),
    // '<'
    OP_RGREATER(4),
    // '<='
    OP_IS_SMALLER_OR_EQUAL(5),
    // '>'
    OP_LGREATER(6),
    // '>='
    OP_IS_GREATER_OR_EQUAL(7),
    // '||'
    OP_BOOL_OR(8),
    // '&&'
    OP_BOOL_AND(9),
    // 'or'
    OP_STRING_OR(10),
    // 'and'
    OP_STRING_AND(11),
    // 'xor'
    OP_STRING_XOR(12),
    // '|'
    OP_OR(13),
    // '&'
    OP_AND(14),
    // '^'
    OP_XOR(15),
    // '.'
    OP_CONCAT(16),
    // '+'
    OP_PLUS(17),
    // '-'
    OP_MINUS(18),
    // '*'
    OP_MUL(19),
    // '/'
    OP_DIV(20),
    // '%'
    OP_MOD(21),
    // '<<'
    OP_SL(22),
    // '>>'
    OP_SR(23),
    // '**'
    OP_POW(24),
    // '<=>'
    OP_SPACESHIP(25),

    OP_NOT_IMPLEMENTED(1000);

    private int num;

    private BinaryOperators(int num) {
        this.num = num;
    }

    public static BinaryOperators fromInfixExpressionOperator(int op) {
        // We can use op 1:1 as enum values are based on InfixExpression
        BinaryOperators res = OP_NOT_IMPLEMENTED;
        for (BinaryOperators binOp : BinaryOperators.values()) {
            if (binOp.num == op) {
                res = binOp;
            }
        }
        return res;
    }

    public static BinaryOperators fromAssignmentOperator(int op) {
        switch (op) {
            case Assignment.OP_PLUS_EQUAL:
                return OP_PLUS;
            case Assignment.OP_MINUS_EQUAL:
                return OP_MINUS;
            case Assignment.OP_MUL_EQUAL:
                return OP_MUL;
            case Assignment.OP_DIV_EQUAL:
                return OP_DIV;
            case Assignment.OP_SL_EQUAL:
                return OP_SL;
            case Assignment.OP_SR_EQUAL:
                return OP_SR;
            default:
                return OP_NOT_IMPLEMENTED;
        }
    }
}
