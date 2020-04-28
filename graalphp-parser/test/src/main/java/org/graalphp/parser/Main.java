package org.graalphp.parser;

import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Program;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            return;
        }
        String code = readFileToString(args[0]);
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
        parser.setSource(code.toCharArray());
        Program ast = parser.createAST();
        System.out.println(ast.toString());
        System.out.flush();
    }

    private static String readFileToString(String filePath) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private static String readFileInClassPath(String path) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            return new Scanner(resource.openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }

}
