package org.graalphp.runtime.array;

import com.oracle.truffle.api.dsl.GenerateUncached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

/**
 * @author abertschi
 */
@ExportLibrary(value = ArrayLibrary.class, receiverType = long[].class)
@GenerateUncached
public class LongArrayLibrary {

    @ExportMessage
    protected static boolean isArray(long[] store) {
        return true;
    }

    @ExportMessage
    protected static boolean acceptsValue(long[] receiver, Object value) {
        return value instanceof Long;
    }

    @ExportMessage
    protected static long read(long[] store, int index) {
        return store[index];
    }

    @ExportMessage
    static class Write {
        @Specialization
        protected static void write(long[] store, int index, long value) {
            store[index] = value;
        }
    }

    @ExportMessage
    protected static LongArrayAllocator allocator(long[] receiver) {
        return LongArrayAllocator.ALLOCATOR;
    }

    @ExportMessage
    static class GeneralizeForValue {
        @Specialization
        protected static ArrayAllocator generalizeForValue(long[] receiver, long newValue) {
            return LongArrayAllocator.ALLOCATOR;
        }
    }
}
