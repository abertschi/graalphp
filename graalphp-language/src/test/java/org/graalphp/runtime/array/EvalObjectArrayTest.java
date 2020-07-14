package org.graalphp.runtime.array;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.array.ArrayReadNode;
import org.graalphp.nodes.array.ArrayReadNodeGen;
import org.graalphp.nodes.array.ArrayWriteReturnArrayNode;
import org.graalphp.nodes.array.ArrayWriteReturnArrayNodeGen;
import org.graalphp.nodes.array.NewArrayNode;
import org.graalphp.nodes.literal.PhpLongNode;
import org.junit.Test;

/**
 * @author abertschi
 */
public class EvalObjectArrayTest {

    static final class ExampleRootNode extends RootNode {

        @Child
        private PhpExprNode node;

        ExampleRootNode(PhpExprNode node) {
            super(null);
            this.node = node;
        }

        @Override
        public Object execute(VirtualFrame frame) {
            return node.executeGeneric(frame);
        }

    }

    @Test
    public void evalArr() {
        NewArrayNode newArray = new NewArrayNode();
        ArrayWriteReturnArrayNode arrayWriteNode =
                ArrayWriteReturnArrayNodeGen
                        .create(newArray, new PhpLongNode(0), new PhpLongNode(1337));

        ArrayReadNode arrayReadNode = ArrayReadNodeGen.create(arrayWriteNode, new PhpLongNode(0));

        ExampleRootNode root = new ExampleRootNode(arrayReadNode);
        CallTarget target = Truffle.getRuntime().createCallTarget(root);
        Object res = target.call();
        System.out.println(res);

    }
}
