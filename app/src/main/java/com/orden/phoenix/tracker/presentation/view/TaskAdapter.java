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
        TaskViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_view, parent, false);
            viewHolder = new TaskViewHolder(
                    convertView.findViewById(R.id.treeSpacerView),
                    (CheckBox) convertView.findViewById(R.id.taskState),
                    (TextView) convertView.findViewById(R.id.taskNameText),
                    (TextView) convertView.findViewById(R.id.timeSpent),
                    (TriangleView) convertView.findViewById(R.id.expandIcon));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TaskViewHolder) convertView.getTag();
        }
        viewHolder.getTreeSpacerView().getLayoutParams().width = item.getDepth() * treeNodeShiftValue;
        viewHolder.getStateBox().setChecked(item.isFinished());
        viewHolder.getNameText().setText(item.getName());
        viewHolder.getTimeSpentText().setText(convertMillsToString(item.getTimeSpent()));
        viewHolder.getExpandIcon().setVisibility(item.getChildren().isEmpty() ? View.INVISIBLE : View.VISIBLE);
        viewHolder.getExpandIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getViewState() == TaskViewItemState.COLLAPSED) {
                    item.expand();
                } else {
                    item.collapse();
                }
            }
        });
        viewHolder.getExpandIcon().setDirection(item.getViewState() == TaskViewItemState.COLLAPSED ? TriangleView.Direction.WEST : TriangleView.Direction.SOUTH);
        return convertView;
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

        public void setStateBox(CheckBox stateBox) {
            this.stateBox = stateBox;
        }

        public TextView getNameText() {
            return nameText;
        }

        public void setNameText(TextView nameText) {
            this.nameText = nameText;
        }

        public TextView getTimeSpentText() {
            return timeSpentText;
        }

        public void setTimeSpentText(TextView timeSpentText) {
            this.timeSpentText = timeSpentText;
        }

        public TriangleView getExpandIcon() {
            return expandIcon;
        }

        public void setExpandIcon(TriangleView expandIcon) {
            this.expandIcon = expandIcon;
        }

        public View getTreeSpacerView() {
            return treeSpacerView;
        }

        public void setTreeSpacerView(View treeSpacerView) {
            this.treeSpacerView = treeSpacerView;
        }
    }
}