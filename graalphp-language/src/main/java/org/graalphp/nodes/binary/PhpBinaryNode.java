package org.graalphp.nodes.binary;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(description = "abstract class for all binary expressions")
@NodeChild("leftNode")
@NodeChild("rightNode")
public abstract class PhpBinaryNode extends PhpExprNode {


}
