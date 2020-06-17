package org.graalphp.nodes;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RootNode;
import org.graalphp.PhpLanguage;

import java.util.List;

/**
 * Represents a function node. A function is a root node.
 *
 * A function needs to be converted to a call target to be callable
 *
 * @author abertschi
 */
public class PhpFunctionRootNode extends RootNode {

    @Node.Child
    private PhpExprNode body;

    private final String name;

    public PhpFunctionRootNode(PhpLanguage lang, FrameDescriptor desc, String name, PhpExprNode body) {
        super(lang, desc);
        this.name = name;
        this.body = body;
    }


    public static PhpFunctionRootNode createFromStmts(PhpLanguage lang,
                                                      FrameDescriptor desc,
                                                      String name,
                                                      List<PhpStmtNode> stmts) {
        PhpStmtListNode stmt = new PhpStmtListNode(stmts);
        PhpReturnBodyNode fnBody = new PhpReturnBodyNode(stmt);
        return new PhpFunctionRootNode(lang, desc, name, fnBody);
    }

    public static PhpFunctionRootNode createWithoutBody(PhpLanguage lang,
                                                        FrameDescriptor desc,
                                                        String name) {

        return new PhpFunctionRootNode(lang, desc, name, new PhpEmptyExprNode());
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return body.executeGeneric(frame);
    }
}
