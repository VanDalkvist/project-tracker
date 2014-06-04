package com.orden.phoenix.tracker.storage;

import java.util.List;

/**
 * Created on 6/1/2014.
 */
public interface TreeStorableFactory<T extends TreeStorable> extends StorableFactory<T> {

    List<T> findChildren(String parentId) throws DatabaseException;
}