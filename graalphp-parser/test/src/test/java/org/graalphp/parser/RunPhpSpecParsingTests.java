package org.graalphp.parser;

import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;

public class RunPhpSpecParsingTests {

    private static final String TEST_FOLDER = "target/php";

    @org.junit.Test
    public static Test suite() {
        TestSuite suite = new TestSuite(
                "compare output with upstream php parser");

        // run parsing tests only on travis
        String tests = System.getenv("CI_PARSING_TESTS");
        if (tests != null && tests.toLowerCase().equals("true")) {
            suite.addTest(new FileTestSuite(new File(TEST_FOLDER)));
        }

        return suite;
    }
}
