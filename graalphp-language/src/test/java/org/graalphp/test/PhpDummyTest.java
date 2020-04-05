package org.graalphp.test;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhpDummyTest {

    private Context ctx;
    private Value obj;

    @Before
    public void setUp() {
        this.ctx = Context.create("php");
//        this.obj = ctx.eval("php", "").execute();
    }

    @After
    public void tearDown() {
        this.ctx.close();
    }

    @Test
    public void testDummy() {
        assertTrue(true);
        assertNotNull(ctx);
//        assertNull(obj);
    }
}
