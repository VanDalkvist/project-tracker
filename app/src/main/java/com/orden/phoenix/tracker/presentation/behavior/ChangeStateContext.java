package com.orden.phoenix.tracker.presentation.behavior;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 6/1/2014.
 */
public class ChangeStateContext  {
    private TaskAdapter adapter;

    public ChangeStateContext(TaskAdapter adapter) {
        this.adapter = adapter;
    }

    public TaskAdapter getAdapter() {
        return adapter;
    }
}
