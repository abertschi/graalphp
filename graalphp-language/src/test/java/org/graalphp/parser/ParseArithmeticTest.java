package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author abertschi
 */
public class ParseArithmeticTest {


    @Test
    public void parseSubSimple() {
        TestCommons.evalLong(0, "1 - 1");
        TestCommons.evalLong(0, "0 - 0");
        TestCommons.evalLong(-8, "-1 - 10 - (-1 -2)");
        TestCommons.evalLong(-7, "(-1 - 10) - -1 - (-1 -2)");

        // test infix
        TestCommons.evalLong(2, "1 - (-1)");
    }

    // @Test
    // TODO
    public void parseSubBoundaryExceed() {
        TestCommons.evalLong(0, String.format("%d - %d", Long.MIN_VALUE, - 1));
    }

    @Test
    public void parseAst() throws Exception {
        String code = TestCommons.php("1 - (-1)");
        PhpParseVisitor v = new PhpParseVisitor();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(code.toCharArray());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
    }
}
