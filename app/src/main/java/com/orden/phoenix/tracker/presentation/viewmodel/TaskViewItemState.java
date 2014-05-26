package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

import java.io.Serializable;

/**
 * Created on 10.05.14.
 */
public interface TaskViewItemState extends Serializable {
    static final TaskViewItemState COLLAPSED = new CollapsedTaskViewItemState();
    static final TaskViewItemState EXPANDED = new ExpandedTaskViewItemState();

    void onChangeState(TaskViewModel item, TaskAdapter adapter);
}