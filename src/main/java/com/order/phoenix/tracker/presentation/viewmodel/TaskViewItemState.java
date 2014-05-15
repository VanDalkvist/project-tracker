package com.order.phoenix.tracker.presentation.viewmodel;

import com.order.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 5/15/14.
 */
public interface TaskViewItemState {
    static final TaskViewItemState COLLAPSED = new CollapsedTaskViewItemState();
    static final TaskViewItemState EXPANDED = new ExpandedTaskViewItemState();

    void onExpand(TaskViewModel item, TaskAdapter adapter);
    void onCollapse(TaskViewModel item, TaskAdapter adapter);

    String getStateText(TaskViewModel item);
}
