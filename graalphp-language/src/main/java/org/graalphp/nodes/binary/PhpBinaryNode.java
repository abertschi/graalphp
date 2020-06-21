package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(description = "abstract class for all binary expressions")
@NodeChild(value = "leftNode", type = PhpExprNode.class)
@NodeChild(value = "rightNode", type = PhpExprNode.class)
public abstract class PhpBinaryNode extends PhpExprNode {


}
