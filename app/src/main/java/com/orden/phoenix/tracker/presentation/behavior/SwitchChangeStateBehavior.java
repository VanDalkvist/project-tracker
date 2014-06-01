package com.orden.phoenix.tracker.presentation.behavior;

import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewItemState;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

/**
 * Created by I_van on 24.05.2014.
 */
public class SwitchChangeStateBehavior implements ChangeStateBehavior {
    @Override
    public TaskViewModel change(TaskViewModel current, ChangeStateContext context) {
        TaskViewItemState viewState = current.getViewState();
        TaskAdapter adapter = context.getAdapter();

        if (viewState == TaskViewItemState.COLLAPSED) {
            viewState.onExpand(current, adapter);
        } else {
            viewState.onCollapse(current, adapter);
        }

        return current;
    }
}