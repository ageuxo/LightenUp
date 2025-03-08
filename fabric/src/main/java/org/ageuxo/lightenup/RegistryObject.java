package org.ageuxo.lightenup;

import java.util.function.Supplier;

public class RegistryObject<T> implements Supplier<T> {
    private final T object;

    public RegistryObject(T object) {
        this.object = object;
    }

    @Override
    public T get() {
        return object;
    }
}
