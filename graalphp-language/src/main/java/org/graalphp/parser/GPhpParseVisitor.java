package org.graalphp.parser;

import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;

/**
 * @author abertschi
 */
public class GPhpParseVisitor extends HierarchicalVisitor {

    public Object createGraalAst(Program pgm) {
        pgm.accept(this);
        return null;
    }

    @Override
    public boolean visit(Program program) {
        return super.visit(program);
    }

    @Override
    public void endVisit(Program program) {
    }


    @Override
    public boolean visit(Statement statement) {
        boolean cont = super.visit(statement);

        return true;
    }

    @Override
    public boolean visit(Assignment assignment) {
        assignment.getLeftHandSide().accept(this);
        assignment.getRightHandSide().accept(this);
        return super.visit(assignment);
    }
}
