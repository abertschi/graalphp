package org.graalphp.types;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

/**
 * The PHP type for null.
 */
@ExportLibrary(InteropLibrary.class)
public final class PhpNull implements TruffleObject {

    /**
     * The canonical value to represent {@code null}
     */
    public static final PhpNull SINGLETON = new PhpNull();

    private PhpNull() {
    }

    /**
     * This method is, e.g., called when using the {@code null} value in a string concatenation. So
     * changing it has an effect on PHP programs
     */
    @Override
    // TODO: php null echoed is '' ?
    public String toString() {
        return "NULL";
    }

    @ExportMessage
    boolean isNull() {
        return true;
    }
}
