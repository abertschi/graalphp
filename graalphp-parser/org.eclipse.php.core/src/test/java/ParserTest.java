import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.ASTParser;
import org.eclipse.php.core.ast.nodes.Block;
import org.eclipse.php.core.ast.nodes.Program;
import org.eclipse.php.core.ast.visitor.HierarchicalVisitor;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class ParserTest {

    private static String readFile(String path) {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        try {
            return new Scanner(resource.openStream(), "UTF-8").useDelimiter("\\A").next();
        } catch (IOException e) {
            return null;
        }
    }

    @Test
    public void test() throws Exception {
        long a, b = 0;
        a = System.currentTimeMillis();
        ASTParser parser = ASTParser.newParser(PHPVersion.PHP7_4, false, true);
//        parser.setSource(readFile("arrays.phpt").toCharArray());
//        Program p = parser.createAST();
//        b = System.currentTimeMillis();
//        HierarchicalVisitor v = new HierarchicalVisitor() {
//            @Override
//            public boolean visit(ASTNode node) {
////                System.out.println(node.toString());
//                return super.visit(node);
//            }
//        };
//        p.accept(v);

    }
}
