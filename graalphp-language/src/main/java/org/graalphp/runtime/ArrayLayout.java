package org.graalphp.runtime;

import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.ObjectType;
import com.oracle.truffle.api.object.dsl.Layout;

/**
 * @author abertschi
 */
@Layout
public interface ArrayLayout {

    DynamicObject createArray(Object backend, int size);

    boolean isArray(DynamicObject object);

    boolean isArray(ObjectType objectType);

    int getSize(DynamicObject object);

    Object getBackend(DynamicObject object);

    void setBackend(DynamicObject object, Object value);
}
