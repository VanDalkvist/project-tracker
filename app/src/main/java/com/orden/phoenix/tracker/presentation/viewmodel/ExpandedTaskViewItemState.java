package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 10.05.14.
 */
public class ExpandedTaskViewItemState extends AbstractTaskViewItemState {
    @Override
    public void onCollapse(TaskViewModel item, TaskAdapter adapter) {
        for(TaskViewModel child : item.getChildren()) {
            child.collapse();
            adapter.remove(child);
        }
        item.setViewState(TaskViewItemState.COLLAPSED);
    }
}
