package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 10.05.14.
 */
public class ExpandedTaskViewItemState implements TaskViewItemState {
    @Override
    public void onChangeState(TaskViewModel item, TaskAdapter adapter) {
        onCollapse(item, adapter);
    }

    private void onCollapse(TaskViewModel item, TaskAdapter adapter) {
        for (TaskViewModel child : item.getChildren()) {
            child.changeState(TaskViewItemState.COLLAPSED);
            adapter.remove(child);
        }
    }
}