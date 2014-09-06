package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 10.05.14.
 */
public class CollapsedTaskViewItemState extends AbstractTaskViewItemState {

    @Override
    public void onExpand(TaskViewModel item, TaskAdapter adapter) {
        int pos = adapter.getPosition(item);
        for (TaskViewModel child : item.getChildren()) {
            child.loadChildren(adapter);
            adapter.insert(child, ++pos);
        }
        item.setViewState(TaskViewItemState.EXPANDED);
    }
}