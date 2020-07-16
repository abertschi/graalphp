package org.graalphp.language;

import org.eclipse.php.core.ast.nodes.Program;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author abertschi
 */
public class ReferenceTest {

    @Test
    public void refFunctionReturnParsing() throws Exception {
        // return type reference: FunctionDeclaration: isReference = true
        // assign reference: assignment(variable, value : wrapped in a reference)

        String code = "function &foo() { return array(1, array(1)); }"
                + " $A = &foo();";
        Program program = TestCommons.parseProgram(code, true);
        Assert.assertNotNull(program);
        System.out.println(program);
    }

    @Test
    public void refFunctionArgParsing() throws Exception {
        // parameter is wrapped within reference
        String code = "function foo(&$A) {  }";
        Program program = TestCommons.parseProgram(code, true);
        Assert.assertNotNull(program);
        System.out.println(program);
    }

    @Test
    public void createReferenceReturningFunction() {
        String code = "function &foo() { return array(1, array(1)); }"
                + "";
        TestCommons.compareStdout("", code, true);
    }

    @Test(expected = Exception.class)
    public void argumentByRefInteger() {
        // XXX: No Support for by reference for integer yet
        TestCommons.compareStdout("", "function foo(&$i) { $a = $i + 1; print($a); } foo(1);",
                true);
    }

    @Test(expected = Exception.class)
    public void argumentByRefDouble() {
        // XXX: No Support for by reference for double yet
        TestCommons.compareStdout("", "function foo(&$i) { $a = $i + 1; print($a); } foo(1.1);",
                true);
    }

    @Test(expected = Exception.class)
    public void argumentByRefBool() {
        // XXX: No Support for by reference for bool yet
        TestCommons.compareStdout("", "function foo(&$i) { $a = $i + 1; print($a); } foo(True);",
                true);
    }

    @Test
    public void argumentByRefArray() {
        String c = "function foo(&$i) { $i[0] = 1; } $A = array(2); print($A[0]); foo($A); print" +
                "($A[0]);";
        TestCommons.compareStdout("21", c, true);
    }

    @Test(expected = Exception.class)
    public void argumentByRefString() {
        // No support for strings in general
        String c = "function foo(&$i) {  } foo(\"hi\");";
        TestCommons.compareStdout("21", c, true);
    }
}
