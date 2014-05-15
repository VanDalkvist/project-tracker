package com.order.phoenix.tracker.presentation.viewmodel;

import com.order.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 5/15/14.
 */
public class ExpandedTaskViewItemState extends AbstractTaskViewItemState {
    @Override
    public void onCollapse(TaskViewModel item, TaskAdapter adapter) {
        int pos = adapter.getPosition(item);
        for(TaskViewModel child : item.getChildren()) {
            child.collapse();
            adapter.remove(child);
        }
        item.setViewState(TaskViewItemState.COLLAPSED);
    }
}
