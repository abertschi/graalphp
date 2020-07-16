package org.graalphp.runtime.assign;

import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.assign.AssignByReferenceNodeGen;
import org.graalphp.nodes.assign.AssignByValueNodeGen;

/**
 * Central Location to create byref/ byvalue forwarding nodes
 *
 * @see org.graalphp.nodes.assign.AssignByReferenceNode
 * @author abertschi
 */
public final class AssignRuntimeFactory {

    public static PhpExprNode createForwardByValueNode(PhpExprNode source) {
        return AssignByValueNodeGen.create(source);
    }

    public static PhpExprNode createForwardByReferenceNode(PhpExprNode source) {
        return AssignByReferenceNodeGen.create(source);
    }

    public static PhpExprNode createForwardValueNode(boolean byReference, PhpExprNode source) {
        if (byReference) {
            return AssignByReferenceNodeGen.create(source);
        } else {
            return AssignByValueNodeGen.create(source);
        }
    }
}
