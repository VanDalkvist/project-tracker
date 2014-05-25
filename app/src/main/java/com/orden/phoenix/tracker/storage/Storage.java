package com.orden.phoenix.tracker.storage;

/**
 * Created by I_van on 25.05.2014.
 */
public interface Storage<T> {

    T read(int id);

    void write(T obj);
}
