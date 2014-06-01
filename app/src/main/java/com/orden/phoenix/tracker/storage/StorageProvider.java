package com.orden.phoenix.tracker.storage;

import android.content.Context;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.storage.sqlite.SQLiteTaskFactory;

/**
 * Created on 6/1/2014.
 */
public class StorageProvider {
    private static StorageProvider instance;

    public static StorageProvider getInstance() {
        if(instance == null) {
            instance = new StorageProvider();
        }
        return instance;
    }

    public TreeStorableFactory<Task> getTaskFactory(Context context) {
        return new SQLiteTaskFactory(context);
    }
}
