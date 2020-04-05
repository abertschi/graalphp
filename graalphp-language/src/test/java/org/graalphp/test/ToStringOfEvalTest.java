//package org.graalphp.test;
//
//import org.graalvm.polyglot.Context;
//import org.graalvm.polyglot.Value;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class ToStringOfEvalTest {
//    Context context;
//
//    @Before
//    public void initialize() {
//        context = Context.create();
//    }
//
//    @After
//    public void dispose() {
//        context.close();
//    }
//
//    @Test
//    public void checkToStringOnAFunction() {
//        context.eval("php", "function checkName() {}");
//        Value value1 = context.getBindings("php").getMember("checkName");
//        Value value2 = context.getBindings("php").getMember("checkName");
//        assertNull(value1);
//        assertNull(value2);
//    }
//}
