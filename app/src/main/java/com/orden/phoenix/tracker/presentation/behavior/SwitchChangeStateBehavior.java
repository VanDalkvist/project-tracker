package com.orden.phoenix.tracker.presentation.behavior;

import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewItemState;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

/**
 * Created by I_van on 24.05.2014.
 */
public class SwitchChangeStateBehavior implements ChangeStateBehavior {
    @Override
    public TaskViewModel change(TaskViewModel current, ChangeStateContext context) {
        if(current.getViewState() == TaskViewItemState.COLLAPSED) {
            current.getViewState().onExpand(current, context.getAdapter());
        } else {
            current.getViewState().onCollapse(current, context.getAdapter());
        }
        return current;
    }
}