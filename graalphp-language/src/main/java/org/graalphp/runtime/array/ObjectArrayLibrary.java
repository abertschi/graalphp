package org.graalphp.runtime.array;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.GenerateUncached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.library.CachedLibrary;
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
    protected static boolean acceptsValue(Object[] receiver, Object value) {
        return true;
    }

    @ExportMessage
    protected static ObjectArrayAllocator getArrayAllocator(Object[] receiver) {
        return ObjectArrayAllocator.INSTANCE;
    }

    @ExportMessage
    static class GeneralizeForValue {
        @Specialization
        protected static ArrayAllocator generalizeForValue(Object[] receiver, Object newValue) {
            return ObjectArrayAllocator.INSTANCE;
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

    @ExportMessage
    static class CopyContents {
        @Specialization(limit = ArrayLibrary.SPECIALIZATION_LIMIT)
        protected static void copyContentsObject(
                Object[] receiver,
                Object destination,
                int length,
                @CachedLibrary("destination") ArrayLibrary destinationLibrary) {

            for (int i = 0; i < length; i++) {
                destinationLibrary.write(destination, i, receiver[i]);
            }
        }
    }

    @ExportMessage
    static class CopyDeepContents {

        // XXX: We need to introduce a truffle boundary
        // otherwise we run into PermanentBailoutException if we try to copy
        // deeply nested arrays
        @TruffleBoundary(allowInlining = true)
        protected static void copyNested(
                PhpArray sourceArray,
                PhpArray targetArray,
                ArrayLibrary destinationLibrary) {

            destinationLibrary.copyDeepContents(
                    sourceArray.getBackend(),
                    targetArray.getBackend(),
                    sourceArray.getCapacity());
        }

        @Specialization(limit = ArrayLibrary.SPECIALIZATION_LIMIT)
        protected static void copyDeepContents(
                Object[] receiver,
                Object destination,
                int length,
                @CachedLibrary("destination") ArrayLibrary destinations,
                @CachedLibrary(limit = ArrayLibrary.SPECIALIZATION_LIMIT) ArrayLibrary helpers) {

            for (int i = 0; i < length; i++) {
                /*
                 * We need to deep copy nested arrays
                 * create a new backend and call copyDeepContents on nested array
                 * For a nested array an element stores a PhpArray itself
                 */
                if (receiver[i] instanceof PhpArray) {
                    final PhpArray array = (PhpArray) receiver[i];
                    final Object backendCopy =
                            helpers.getArrayAllocator(array.getBackend())
                                    .createArray(array.getCapacity());

                    final PhpArray arrayCopy = ArrayFactory
                            .newArray(backendCopy, array.getCapacity());

                    // XXX: Copy nested arrays
                    copyNested(array, arrayCopy, helpers);

                    destinations.write(destination, i, arrayCopy);
                } else {
                    destinations.write(destination, i, receiver[i]);
                }
            }
        }
    }
}
