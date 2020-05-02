package org.graalphp.parser.tmp;//import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.graalphp.parser.TestUtils;
import org.junit.Assert;

import java.io.*;

public class CompareOutputTests {

    public void test() throws Exception {
        String code = TestUtils.readFileInClassPath("arrays.phpt");
        code = code.replace("--EXPECT--", "?>");
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        Program ast = parser.parsePhpProgram();
        StringBuilder actualBui = new StringBuilder();
        ast.toString(actualBui,"");
        String expected = TestUtils.runFatJarParser(code);

        File file = File.createTempFile("actual", null);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(actualBui.toString());
        } finally {
            if (writer != null) writer.close();
        }

        String exec = String.format("diff -q -w %s %s", file.getAbsolutePath(), expected);
        System.out.println(exec);
        Process proc = Runtime.getRuntime().exec(new String[]{"bash","-c", exec});
        proc.waitFor();
        Assert.assertEquals("", TestUtils.toString(proc.getInputStream()));
//        System.out.println(actualBui.toString());
    }
}
