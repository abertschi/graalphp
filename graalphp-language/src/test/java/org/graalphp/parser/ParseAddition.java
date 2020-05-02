package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Test;

import java.io.IOException;

public class ParseAddition {

    private static String assign1 =
            "<?php $a 5= 1; ?>";
    @Test
    public void parseAssign1() throws IOException {
        PhpParseVisitor v = new PhpParseVisitor();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(assign1.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        Program pgm = null;
        try {
            pgm = parser.parsePhpProgram();
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(pgm.toString());
        Object o = v.createGraalAst(pgm);

        //<Program start='0' length='16'>
        //	<Statements>
        //		<ExpressionStatement start='6' length='7'>
        //			<Assignment start='6' length='6' operator='='>
        //				<Variable start='6' length='2' isDollared='true'>
        //					<Identifier start='7' length='1' name='a'/>
        //				</Variable>
        //				<Value>
        //					<Scalar start='11' length='1' type='int' value='1'/>
        //				</Value>
        //			</Assignment>
        //		</ExpressionStatement>
        //		<EmptyStatement start='14' length='2'/>
        //	</Statements>
        //	<Comments>
        //	</Comments>
        //</Program>



    }
}
