package org.graalphp.nodes.controlflow;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;
import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

import java.util.List;

/**
 * Represents a php function which is callable
 *
 * @author abertschi
 */
public class PhpFnRootNode extends RootNode {

    @Node.Child
    private PhpExprNode body;

    private final String name;

    public PhpFnRootNode(PhpLanguage lang, FrameDescriptor desc, String name, PhpExprNode body) {
        super(lang, desc);
        this.name = name;
        this.body = body;
    }


    public static PhpFnRootNode createFromStmts(PhpLanguage lang,
                                                FrameDescriptor desc,
                                                String name,
                                                List<PhpStmtNode> stmts) {
        PhpStmtListNode stmt = new PhpStmtListNode(stmts);
        PhpFnBodyNode fnBody = new PhpFnBodyNode(stmt);
        return new PhpFnRootNode(lang, desc, name, fnBody);
    }

    public static PhpFnRootNode createWithoutBody(PhpLanguage lang,
                                                FrameDescriptor desc,
                                                String name) {

        return new PhpFnRootNode(lang, desc, name, new PhpEmptyExprNode());
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.executeGeneric(frame);
    }
}
