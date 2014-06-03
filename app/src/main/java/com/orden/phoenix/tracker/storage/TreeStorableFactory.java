package com.orden.phoenix.tracker.storage;

import com.orden.phoenix.tracker.model.Task;

import java.util.List;

/**
 * Created on 6/1/2014.
 */
public interface TreeStorableFactory<T extends TreeStorable> extends StorableFactory<T> {

    List<Task> findChildren(String parentId) throws DatabaseException;
}