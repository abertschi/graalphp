package org.graalphp.parser;

import com.oracle.truffle.api.source.Source;
import com.oracle.truffle.api.source.SourceSection;
import org.eclipse.php.core.ast.nodes.*;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;

/**
 * @author abertschi
 */
public class PhpParseVisitor extends HierarchicalVisitor {

    private Source source;

    public PhpParseVisitor(Source source) {
        this.source = source;
    }

    public PhpParseVisitor() {
        this.source = null;
    }

    public Object createGraalAst(Program pgm) {
        pgm.accept(this);
        return null;
    }

    private SourceSection createSourceSection(ASTNode node) {
        int start = node.getStart();
        int len = node.getLength();
        if (len > 0) {
            return source.createSection(start, len);
        } else {
            return source.createSection(start);
        }
    }

    // --------------- visitor methods -------------------

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
