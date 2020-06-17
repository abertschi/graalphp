package org.graalphp;

/**
 * A function invocation may appear above its function definition.
 * So during parsing of an invocation, we dont have a rootcalltarget
 * created yet for that function
 *
 * This registry stores function definitions and enriches them with call targets
 * once their implementations are available
 *
 * @author abertschi
 */
// TODO
public class PhpFunctionRegistry {

}
