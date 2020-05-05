package org.graalphp.parser;

import org.graalphp.nodes.PhpExprNode;
import org.graalphp.nodes.PhpStmtNode;

import java.util.List;
import java.util.Map;

/**
 * @author abertschi
 */
public class PhpParseResult {

    private List<PhpExprNode> globalStmts;

    public PhpParseResult(List<PhpExprNode> globalStmts) {
        this.globalStmts = globalStmts;
    }

    public List<PhpExprNode> getGlobalStmts() {
        return globalStmts;
    }

    public void setGlobalStmts(List<PhpExprNode> globalStmts) {
        this.globalStmts = globalStmts;
    }
}
