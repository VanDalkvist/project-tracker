package com.orden.phoenix.tracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeInterval;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.utils.ConsoleLogger;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class TaskListActivity extends Activity {

    ConsoleLogger Logger = new ConsoleLogger("Project-Tracker");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        setContentView(R.layout.activity_task_list);

        Logger.d("Creating Activity...");

        init();
    }

    private void init() {
        Logger.d("Activity initiate process started.");

        List<TaskViewModel> items = new ArrayList<TaskViewModel>();

        TaskAdapter itemAdapter = new TaskAdapter(this, R.layout.task_view, items);

        items.add(getDefaultTaskViewModel("name 1", itemAdapter, 3, 4));
        items.add(getDefaultTaskViewModel("name 2", itemAdapter, 3, 4));
        items.add(getDefaultTaskViewModel("name 3", itemAdapter, 3, 4));

        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(itemAdapter);

        Logger.d("Activity initiate process finished.");
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
        for (int i = 0; i < childCount && depth > 0; i++) {
            viewModel.addChild(getDefaultTaskViewModel(name + depth, adapter, childMax, depth - 1));
        }
        return viewModel;
    }

    /**
     * test method
     */
    private ArrayList<TimeInterval> getStartIntervals() {
        ArrayList<TimeInterval> activityIntervals = new ArrayList<TimeInterval>();
        TimeInterval interval = new TimeInterval();
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

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
