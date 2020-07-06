package org.graalphp.runtime.array;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GenerateUncached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

import java.util.Arrays;

/**
 * Array backend for generic Object[] values
 *
 * @author abertschi
 */
@ExportLibrary(value = ArrayLibrary.class, receiverType = Object[].class)
@GenerateUncached
public class ObjectArrayLibrary {

    @ExportMessage
    protected static boolean isArray(Object[] receiver) {
        return true;
    }

    @ExportMessage
    protected static boolean acceptsValue(Object[] receiver, Object value) {
        return true;
    }

    @ExportMessage
    protected static Object read(Object[] receiver, int index) {
        return receiver[index];
    }

    @ExportMessage
    protected static void write(Object[] receiver, int index, Object value) {
        receiver[index] = value;
    }

    @ExportMessage
    @TruffleBoundary
    protected static String arrayToString(Object[] receiver) {
        return Arrays.toString(receiver);
    }

    @ExportMessage
    protected static ObjectArrayAllocator allocator(Object[] receiver) {
        return ObjectArrayAllocator.ALLOCATOR;
    }

    @ExportMessage
    static class GeneralizeForValue {
        @Specialization
        protected static ArrayAllocator generalizeForValue(Object[] receiver, Object newValue) {
            return ObjectArrayAllocator.ALLOCATOR;
        }
    }

    @ExportMessage
    protected static int capacity(Object[] receiver) {
        return receiver.length;
    }

    @ExportMessage
    protected static Object[] grow(Object[] receiver, int newSize) {
        return Arrays.copyOf(receiver, newSize);
    }

}