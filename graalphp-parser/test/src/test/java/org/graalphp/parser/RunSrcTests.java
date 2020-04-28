package org.graalphp.parser;

import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;

public class RunSrcTests {

    private static final String TEST_FOLDER = "src/test/resources";

    @org.junit.Test
    public static Test suite() {
        TestSuite suite = new TestSuite(
                "compare output with upstream php parser");
        suite.addTest(new FileTestSuite(new File(TEST_FOLDER)));
        return suite;
    }
}
