package org.graalphp.parser;

import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RunSrcTests {

    private static final String TEST_FOLDER = "src/test/resources";

    @org.junit.Test
    public static Test suite() {

        TestSuite suite = new TestSuite(
                "compare output with upstream php parser");
        suite.addTest(new FileTestSuite(new File(getDirectory())));
        return suite;
    }

    private static String getDirectory() {
        String res = TEST_FOLDER;
        try (InputStream input =
                     Main.class.getClassLoader().getResourceAsStream("build.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            res = prop.getProperty("build.directory");
            res += "/../" + TEST_FOLDER;
        } catch (IOException ex) {
        }
        System.out.println(res);
        return res;
    }
}
