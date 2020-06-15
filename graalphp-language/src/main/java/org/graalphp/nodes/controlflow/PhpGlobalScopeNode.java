package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;

/**
 * @author abertschi
 */
@NodeInfo(language = PhpLanguage.ID, description = "entry point to execute source code")
public class PhpGlobalScopeNode extends RootNode {

    @Child
    private PhpExprNode body;

    public PhpGlobalScopeNode(PhpLanguage language, FrameDescriptor globalDescriptor, PhpExprNode body) {
        super(language, globalDescriptor);
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        // TODO: integrate arguments
        return body.executeGeneric(frame);
    }
}
