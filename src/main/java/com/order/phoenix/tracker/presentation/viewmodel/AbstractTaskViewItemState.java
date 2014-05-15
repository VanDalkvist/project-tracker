package com.order.phoenix.tracker.presentation.viewmodel;

import com.order.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 5/15/14.
 */
public class AbstractTaskViewItemState implements TaskViewItemState {
    @Override
    public void onExpand(TaskViewModel item, TaskAdapter adapter) {
    }

    @Override
    public void onCollapse(TaskViewModel item, TaskAdapter adapter) {
    }

    @Override
    public String getStateText(TaskViewModel item) {
        return "";
    }
}
