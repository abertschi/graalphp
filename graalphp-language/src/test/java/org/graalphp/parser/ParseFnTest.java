package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.error.BailoutErrorListener;
import org.eclipse.php.core.ast.error.ConsoleErrorListener;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author abertschi
 */
public class ParseFnTest {

    @Test
    public void parseSimpleFn() throws Exception {
        String src = toString(getClass().getResourceAsStream("ParseFnTest01.php"));
        System.out.println(src);
        PhpProgramVisitor v = new PhpProgramVisitor();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4);
        parser.setSource(src.toCharArray());
        parser.addErrorListener(new ConsoleErrorListener());
        parser.addErrorListener(new BailoutErrorListener());
        Program pgm = parser.parsePhpProgram();
        System.out.println(pgm);
    }

    private String toString(InputStream in ) {
        String text = null;
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }
        return text;
    }
}
