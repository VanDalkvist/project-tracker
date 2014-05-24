package com.orden.phoenix.tracker.presentation.behavior;

import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewItemState;

/**
 * Created by I_van on 24.05.2014.
 */
public class SwitchChangeStateBehavior implements ChangeStateBehavior {
    @Override
    public TaskViewItemState change(TaskViewItemState current) {
        return current == TaskViewItemState.COLLAPSED ? TaskViewItemState.EXPANDED : TaskViewItemState.COLLAPSED;
    }
}