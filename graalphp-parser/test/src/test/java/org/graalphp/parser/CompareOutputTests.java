package org.graalphp.parser;//import org.eclipse.php.core.PHPVersion;
//import org.eclipse.php.core.ast.nodes.ASTNode;
//import org.eclipse.php.core.ast.nodes.ASTParser;
//import org.eclipse.php.core.ast.nodes.Program;
//import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CompareOutputTests {

    @Test
    public void test() throws Exception {
        String code = TestUtils.readFileInClassPath("arrays.phpt");
        code = code.replace("--EXPECT--", "?>");
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        Program ast = parser.createAST();
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
