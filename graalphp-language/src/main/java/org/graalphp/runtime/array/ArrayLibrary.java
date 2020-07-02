package org.graalphp.runtime.array;

import com.oracle.truffle.api.library.GenerateLibrary;
import com.oracle.truffle.api.library.GenerateLibrary.DefaultExport;
import com.oracle.truffle.api.library.Library;
import com.oracle.truffle.api.library.LibraryFactory;

@GenerateLibrary
@DefaultExport(LongArrayLibrary.class)
@DefaultExport(ObjectArrayLibrary.class)
public abstract class ArrayLibrary extends Library {

    public static final String SPECIALIZATION_LIMIT = "2";

    // TODO:
    //  - isMap
    //  - use String in specialization as key
    //  - migrate
    //  - increase size

    private static final LibraryFactory<ArrayLibrary> FACTORY =
            LibraryFactory.resolve(ArrayLibrary.class);

    public static LibraryFactory<ArrayLibrary> getFactory() {
        return FACTORY;
    }

    public static ArrayLibrary getUncached() {
        return FACTORY.getUncached();
    }

    public abstract boolean isArray(Object receiver);

    public abstract Object read(Object receiver, int index);

    public abstract void write(Object receiver, int index, Object value);

    public abstract boolean acceptsValue(Object receiver, Object value);

    public abstract ArrayAllocator allocator(Object receiver);

    public abstract ArrayAllocator generalizeForValue(Object receiver, Object newValue);

    public abstract String arrayToString(Object receiver);

    public static ArrayAllocator getAllocatorForValue(Object obj) {
        if (obj instanceof Long) {
            return LongArrayAllocator.ALLOCATOR;
        } else {
            return ObjectArrayAllocator.ALLOCATOR;
        }
    }

}