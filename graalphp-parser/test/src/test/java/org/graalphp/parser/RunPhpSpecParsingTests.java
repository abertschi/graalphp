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
        // TODO: enable this on CI
//        suite.addTest(new FileTestSuite(new File(TEST_FOLDER)));
        return suite;
    }
}
