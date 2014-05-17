package com.orden.phoenix.tracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeIntervalModel;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TaskListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        init();
    }

    private void init() {
        List<TaskViewModel> items = new ArrayList<TaskViewModel>();

        TaskAdapter itemAdapter = new TaskAdapter(this, R.layout.task_view, items);

        items.add(getDefaultTaskViewModel("name 1", itemAdapter, 3, 4));
        items.add(getDefaultTaskViewModel("name 2", itemAdapter, 3, 4));
        items.add(getDefaultTaskViewModel("name 3", itemAdapter, 3, 4));

        ListView taskListView = (ListView)findViewById(R.id.taskListView);
        taskListView.setAdapter(itemAdapter);
    }

    /**
     * test method
     */
    private TaskViewModel getDefaultTaskViewModel(String name, TaskAdapter adapter, int childMax, int depth) {
        TaskViewModel viewModel = new TaskViewModel(adapter);
        viewModel.setName(name);
        viewModel.setState(TaskState.CREATED);
        viewModel.setActivityIntervals(getStartIntervals());
        int childCount = new Random().nextInt(childMax);
        for(int i = 0; i < childCount && depth > 0; i++) {
            viewModel.addChild(getDefaultTaskViewModel(name + depth, adapter, childMax, depth - 1));
        }
        return viewModel;
    }

    /**
     * test method
     */
    private ArrayList<TimeIntervalModel> getStartIntervals() {
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
}
