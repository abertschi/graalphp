package org.graalphp.parser;

import junit.framework.*;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


/**
 * Testsuite which scanns given directory for php classes.
 * Runs graalphp parser and compares produced AST with upstream eclipse parser.
 * Eclipse parser is bundled as fatjar and invoked with exec
 * diff is used to compare ASTs
 *
 */
public class FileTestSuite extends TestSuite {

    public FileTestSuite(File directory) {
        super(directory.getName());
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) addTest(new FileTestSuite(file));
            else if (file.getName().endsWith(".php") || file.getName().endsWith(".phpt")) {
                addTest(new FileTestCase(file));
            }
        }
    }

    public static class FileTestCase extends TestCase {

        private File phpFile;

        public FileTestCase(File file) {
            setName(file.getAbsolutePath());
            phpFile = file;
        }

        @Override
        protected void runTest() throws Throwable {
            String code = TestUtils.readFileToString(phpFile.getAbsolutePath());

            // replace expect output in phpt files
            code = code.replace("--EXPECTF--", "?>");
            code = code.replace("--EXPECT--", "?>");

            System.out.println("== test start: " + getName());

            ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
            parser.setSource(code.toCharArray());
            Program ast = parser.createAST();
            StringBuilder actualResult = new StringBuilder();
            ast.toString(actualResult, "");
            String expectedResultFileName = TestUtils.runFatJarParser(code);

            File actualResultFile = File.createTempFile("graalphp-output", null);
            TestUtils.toFile(actualResultFile, actualResult.toString());


            String exec = String.format("diff -w -q %s %s", actualResultFile.getAbsolutePath(), expectedResultFileName);
            System.out.println(exec);

            Process proc = Runtime.getRuntime().exec(new String[]{"bash", "-c", exec});
            proc.waitFor();
            System.out.println("== test end: " + getName());

            Assert.assertEquals("", TestUtils.toString(proc.getInputStream()));

        }


    }
}
