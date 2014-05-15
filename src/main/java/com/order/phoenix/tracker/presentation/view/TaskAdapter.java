package com.order.phoenix.tracker.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.order.phoenix.tracker.R;
import com.order.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskViewModel> {
    private final int resourceID;

    public TaskAdapter(Context context, int resource, List<TaskViewModel> items) {
        super(context, resource, items);
        this.resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TaskViewModel item = getItem(position);
        View rowView = LayoutInflater.from(getContext()).inflate(resourceID, parent, false);
        if(rowView != null) {
//            ((CheckBox) rowView.findViewById(R.id.taskState)).setChecked(item.isFinished());
            ((TextView) rowView.findViewById(R.id.taskNameText)).setText(item.getName());
            ((TextView) rowView.findViewById(R.id.timeSpent)).setText(convertMillsToString(item.getTimeSpent()));
            ((TextView) rowView.findViewById(R.id.taskStateText)).setText(item.getStateText());
        }
        return rowView;
    }

    private String convertMillsToString(long millis) {
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}