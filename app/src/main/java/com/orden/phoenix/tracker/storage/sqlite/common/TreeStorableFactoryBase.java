package com.orden.phoenix.tracker.storage.sqlite.common;

import android.content.Context;

import com.orden.phoenix.tracker.storage.TreeStorable;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;

/**
 * Created by I_van on 03.06.2014.
 *
 * @author I_van
 */
public abstract class TreeStorableFactoryBase<T extends TreeStorable>
        extends StorableFactoryBase<T>
        implements TreeStorableFactory<T> {

    public TreeStorableFactoryBase(Context context) {
        super(context);
    }
}