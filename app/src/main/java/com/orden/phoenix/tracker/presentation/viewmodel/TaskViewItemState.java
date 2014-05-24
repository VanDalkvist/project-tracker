package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

/**
 * Created on 10.05.14.
 */
public interface TaskViewItemState {
    static final TaskViewItemState COLLAPSED = new CollapsedTaskViewItemState();
    static final TaskViewItemState EXPANDED = new ExpandedTaskViewItemState();

    void onChangeState(TaskViewModel item, TaskAdapter adapter);
}