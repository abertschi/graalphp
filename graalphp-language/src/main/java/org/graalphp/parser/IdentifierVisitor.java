package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.Expression;
import org.eclipse.php.core.ast.nodes.Identifier;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;

/**
 * @author abertschi
 */
public class PhpIdentifierVisitor extends HierarchicalVisitor {

    private Identifier id;

    public Identifier getIdentifierName(Expression e) {
        e.accept(this);
        assert(id != null);
        return id;
    }

    @Override
    public boolean visit(Identifier identifier) {
        assert (id == null);
        this.id = identifier;
        return false;
    }
}
