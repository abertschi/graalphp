package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.nodes.Variable;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;

/**
 * Resolves a (dollared) variable in an AST tree.
 *
 * @author abertschi
 */
public class ExpectsSingleVariable extends HierarchicalVisitor {

    private Variable var;
    private Identifier id;

    public String resolveName(ASTNode node) {
        node.accept(this);
        if (var != null && var.isDollared()) {
            return id.getName();
        }
        return null;
    }

    @Override
    public boolean visit(Variable variable) {
        if (variable.getName() instanceof Identifier) {
            var = variable;
            id = (Identifier) variable.getName();
        }
        return false;
    }
}
