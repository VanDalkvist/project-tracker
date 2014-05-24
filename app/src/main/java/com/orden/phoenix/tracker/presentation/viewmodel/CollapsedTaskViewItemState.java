package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 10.05.14.
 */
public class CollapsedTaskViewItemState implements TaskViewItemState {

    @Override
    public void onChangeState(TaskViewModel item, TaskAdapter adapter) {
        onExpand(item, adapter);
    }

    private void onExpand(TaskViewModel item, TaskAdapter adapter) {
        int pos = adapter.getPosition(item);
        for (TaskViewModel child : item.getChildren()) {
            adapter.insert(child, ++pos);
        }
    }
}