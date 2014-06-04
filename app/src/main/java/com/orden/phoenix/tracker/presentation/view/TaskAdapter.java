package com.orden.phoenix.tracker.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.orden.phoenix.tracker.R;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewItemState;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskViewModel> {
    private int treeNodeShiftValue;

    public TaskAdapter(Context context, int resource, List<TaskViewModel> items) {
        super(context, resource, items);

        treeNodeShiftValue = context.getResources().getDimensionPixelSize(R.dimen.taskListItemNodeShift);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TaskViewModel item = getItem(position);

        if (convertView == null) convertView = createView(parent);

        prepareHolder((TaskViewHolder) convertView.getTag(), item);

        return convertView;
    }

    private View createView(ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.task_view, parent, false);
        view.setTag(createTaskViewHolder(view));
        return view;
    }

    private void prepareHolder(TaskViewHolder viewHolder, final TaskViewModel item) {
        viewHolder.getTreeSpacerView().getLayoutParams().width = item.getDepth() * treeNodeShiftValue;
        viewHolder.getStateBox().setChecked(item.isFinished());
        viewHolder.getNameText().setText(item.getName());
        viewHolder.getTimeSpentText().setText(convertMillsToString(item.getTimeSpent()));
        viewHolder.getExpandIcon().setVisibility(item.getChildren().isEmpty() ? View.INVISIBLE : View.VISIBLE);
        viewHolder.getExpandIcon().setDirection(item.getViewState() == TaskViewItemState.COLLAPSED ? TriangleView.Direction.WEST : TriangleView.Direction.SOUTH);
    }

    private TaskViewHolder createTaskViewHolder(View convertView) {
        return new TaskViewHolder(
                convertView.findViewById(R.id.treeSpacerView),
                (CheckBox) convertView.findViewById(R.id.taskState),
                (TextView) convertView.findViewById(R.id.taskNameText),
                (TextView) convertView.findViewById(R.id.timeSpent),
                (TriangleView) convertView.findViewById(R.id.expandIcon));
    }

    private String convertMillsToString(long millis) {
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * should not depend on model
     */
    static class TaskViewHolder {
        private View treeSpacerView;
        private CheckBox stateBox;
        private TextView nameText;
        private TextView timeSpentText;
        private TriangleView expandIcon;

        TaskViewHolder(View treeSpacerView, CheckBox stateBox, TextView nameText, TextView timeSpentText, TriangleView expandIcon) {
            this.treeSpacerView = treeSpacerView;
            this.stateBox = stateBox;
            this.nameText = nameText;
            this.timeSpentText = timeSpentText;
            this.expandIcon = expandIcon;
        }

        public CheckBox getStateBox() {
            return stateBox;
        }

        public TextView getNameText() {
            return nameText;
        }

        public TextView getTimeSpentText() {
            return timeSpentText;
        }

        public TriangleView getExpandIcon() {
            return expandIcon;
        }

        public View getTreeSpacerView() {
            return treeSpacerView;
        }
    }
}