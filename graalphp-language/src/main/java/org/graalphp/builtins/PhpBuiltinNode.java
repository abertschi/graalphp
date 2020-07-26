package org.graalphp.builtins;

import com.oracle.truffle.api.dsl.GenerateNodeFactory;
import com.oracle.truffle.api.dsl.NodeChild;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@GenerateNodeFactory
@NodeChild(value = "arguments", type = PhpExprNode[].class)
public abstract class PhpBuiltinNode extends PhpExprNode {

    protected abstract PhpExprNode[] getArguments();

}
