package com.orden.phoenix.tracker.presentation.behavior;

import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewItemState;

/**
 * Created by I_van on 24.05.2014.
 */
public interface ChangeStateBehavior {
    TaskViewItemState change(TaskViewItemState current);
}