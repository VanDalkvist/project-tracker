package com.order.phoenix.tracker.presentation.viewmodel;

import com.order.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 5/15/14.
 */
public class CollapsedTaskViewItemState extends AbstractTaskViewItemState {
    @Override
    public void onExpand(TaskViewModel item, TaskAdapter adapter) {
        int pos = adapter.getPosition(item);
        for(TaskViewModel child : item.getChildren()) {
            adapter.insert(child, ++pos);
        }
        item.setViewState(TaskViewItemState.EXPANDED);
    }

    @Override
    public String getStateText(TaskViewModel item) {
        return item.getChildren().isEmpty() ? "" : Integer.toString(item.getChildren().size());
    }
}
