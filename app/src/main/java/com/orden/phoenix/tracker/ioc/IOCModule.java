package com.orden.phoenix.tracker.ioc;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.storage.TreeStorable;

/**
 * Created by I_van on 01.06.2014.
 */
public class IOCModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(TreeStorable.class).to(Task.class);
    }
}
