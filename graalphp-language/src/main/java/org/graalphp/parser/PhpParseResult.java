package org.graalphp.parser;

import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

import java.util.List;
import java.util.Map;

/**
 * @author abertschi
 */
public class PhpParseResult {

    private final List<PhpStmtNode> rootNode;

    public PhpParseResult(List<PhpStmtNode> rootNode) {
        this.rootNode = rootNode;
    }

    public List<PhpStmtNode> getRootNode () {
        return this.rootNode;
    }
}
