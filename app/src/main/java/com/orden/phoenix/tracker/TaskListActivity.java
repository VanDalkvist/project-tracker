package com.orden.phoenix.tracker;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeIntervalModel;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<TaskViewModel> items = new ArrayList<TaskViewModel>();

        items.add(getDefaultTaskViewModel("name 1"));
        items.add(getDefaultTaskViewModel("name 2"));
        items.add(getDefaultTaskViewModel("name 3"));

        ListAdapter itemAdapter = new TaskAdapter(this, R.layout.task_view, items);

        ListView taskListView = getListView();
        taskListView.setAdapter(itemAdapter);
    }

    private TaskViewModel getDefaultTaskViewModel(String name) {
        TaskViewModel viewModel = new TaskViewModel();
        viewModel.setName(name);
        viewModel.setState(TaskState.CREATED);
        viewModel.setActivityIntervals(GetStartIntervals());
        return viewModel;
    }

    private ArrayList<TimeIntervalModel> GetStartIntervals() {
        ArrayList<TimeIntervalModel> activityIntervals = new ArrayList<TimeIntervalModel>();
        TimeIntervalModel interval = new TimeIntervalModel();
        interval.setFrom(new Date());
        activityIntervals.add(interval);
        return activityIntervals;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);
            return rootView;
        }
    }
}
