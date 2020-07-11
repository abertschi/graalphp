package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.graalphp.util.Logger;
import org.graalphp.util.PhpLogger;

/**
 * Resolves an identifier in an AST.
 *
 * @author abertschi
 */
public class IdentifierVisitor extends HierarchicalVisitor {

    private Identifier id;

    private final static Logger LOG = PhpLogger.getLogger(IdentifierVisitor.class.getSimpleName());

    public Identifier getIdentifierName(Expression e) {
        e.accept(this);
        assert (id != null) : "No variable identifier found in " + e;
        return id;
    }

    @Override
    public boolean visit(Identifier identifier) {
        if (id != null) {
            String msg =
                    String.format("multiple identifiers in expression. " +
                            "Already visited: %s, new %s", id, identifier);
            LOG.info(msg);
            return false;
        }
        this.id = identifier;
        return false;
    }
}
