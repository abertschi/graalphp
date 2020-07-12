package org.graalphp.runtime;

/**
 * If we unset a variable, it gets undefined.
 * We assign this value to the variable and throw an
 * exception if we try to read it again. This does not represent a type in PHP.
 * And is used to implement 'unset' behavior.
 *
 * @author abertschi
 */
public final class PhpUnset {
    public static final PhpUnset SINGLETON = new PhpUnset();

    private PhpUnset() {
    }

    @Override
    public String toString() {
        return "UNDEFINED, variable previously unset";
    }
}
