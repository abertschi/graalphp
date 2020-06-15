package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;

/**
 * Represents a php function which is callable
 *
 * @author abertschi
 */
public class PhpFnNode extends RootNode {

    @Node.Child
    private PhpExprNode body;

    private final String name;

    public PhpFnNode(PhpLanguage lang, FrameDescriptor desc, String name, PhpExprNode body) {
        super(lang, desc);
        this.name = name;
        this.body = body;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.executeGeneric(frame);
    }
}
