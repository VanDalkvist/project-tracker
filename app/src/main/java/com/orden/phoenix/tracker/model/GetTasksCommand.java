package com.orden.phoenix.tracker.model;

import android.content.Context;
import android.os.AsyncTask;

import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.StorageProvider;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * Created on 5/28/2014.
 */
public class GetTasksCommand extends AsyncTask<Object, Object, List<Task>> {
    private Context context;
    private Callback callback;
    private String parentId;

    public GetTasksCommand(Context context, String parentId, Callback callback) {
        this.context = context;
        this.callback = callback;
        this.parentId = parentId;
    }

    @Override
    protected List<Task> doInBackground(Object... params) {
        try {
            return StorageProvider.getInstance().getTaskFactory(context).findChildren(parentId);
        } catch (DatabaseException e) {
            ExceptionHandler.logException(e, context.getPackageName());
            return Collections.emptyList();
        }
    }

    @Override
    protected void onPostExecute(List<Task> result) {
        callback.call(result);
    }

    public static interface Callback {
        void call(List<Task> result);
    }
}
