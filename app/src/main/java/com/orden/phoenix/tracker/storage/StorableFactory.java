package com.orden.phoenix.tracker.storage;

import java.util.List;

/**
 * Created on 5/28/2014.
 */
public interface StorableFactory<T extends Storable> {
    void createInstance(T instance) throws DatabaseException;
    void updateInstance(T instance)  throws DatabaseException;
    T findById(String id)  throws DatabaseException;
    List<T> findAll() throws DatabaseException;
    void delete(String id) throws DatabaseException;
}
