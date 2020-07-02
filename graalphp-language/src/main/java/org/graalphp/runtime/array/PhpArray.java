package org.graalphp.runtime.array;

import com.oracle.truffle.api.interop.TruffleObject;

/**
 * @author abertschi
 */
public final class PhpArray implements TruffleObject {

    private Object backend;

    private int capacity;

    public PhpArray(Object backend, int capacity) {
        this.backend = backend;
        this.capacity = capacity;
    }

    public PhpArray(){
    }

    public Object getBackend() {
        return backend;
    }

    public void setBackend(Object backend) {
        this.backend = backend;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        ArrayLibrary arrays = ArrayLibrary.getUncached();
        return "PhpArray{" +
                "backend=" + arrays.arrayToString(backend) +
                ", capacity=" + capacity +
                '}';
    }
}
