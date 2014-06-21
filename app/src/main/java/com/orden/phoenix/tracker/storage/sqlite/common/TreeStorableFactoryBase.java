package com.orden.phoenix.tracker.storage.sqlite.common;

import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.TreeStorable;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;

import java.util.List;

/**
 * Created by I_van on 03.06.2014.
 *
 * @author I_van
 */
public abstract class TreeStorableFactoryBase<T extends TreeStorable>
        extends StorableFactoryBase<T>
        implements TreeStorableFactory<T> {

    public TreeStorableFactoryBase() {
        super();
    }

    @Override
    public List<T> findChildren(String parentId) throws DatabaseException {
        try {
            open();
            return readList(getDatabase().query(getTableName(), null, parentId == null ? "parentId is null" : "parentId=" + parentId, null, null, null, null));
        } finally {
            close();
        }
    }
}