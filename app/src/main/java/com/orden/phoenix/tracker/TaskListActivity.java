package com.orden.phoenix.tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeInterval;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.utils.ConsoleLogger;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class TaskListActivity extends Activity {
    private static ConsoleLogger logger = new ConsoleLogger("Project-Tracker");

    private TaskAdapter adapter;
    private TaskViewModel selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.d("Creating Activity...");
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_task_list);

        init();
    }

    private void init() {
        logger.d("Activity initiate process started.");

        adapter = new TaskAdapter(this, R.layout.task_view, new ArrayList<TaskViewModel>());

        addTask(getDefaultTaskViewModel("name 1", 3, 4));
        addTask(getDefaultTaskViewModel("name 2", 3, 4));
        addTask(getDefaultTaskViewModel("name 3", 3, 4));
        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(adapter);
        registerForContextMenu(taskListView);

        logger.d("Activity initiate process finished.");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }
        switch (requestCode) {
            case R.id.action_new:
                adapter.add((TaskViewModel) data.getSerializableExtra(EditTaskActivity.TASK_EXTRA));
                break;
            case R.id.action_edit:
                selectedItem.merge((TaskViewModel) data.getSerializableExtra(EditTaskActivity.TASK_EXTRA));
                adapter.notifyDataSetChanged();
                selectedItem = null;
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                goToEditTaskActivity(selectedItem, R.id.action_edit);
                break;
            case R.id.action_remove:
                removeTask(selectedItem);
            default:
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        selectedItem = adapter.getItem(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
        getMenuInflater().inflate(R.menu.task_list_item_context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_new:
                goToEditTaskActivity(new TaskViewModel(), R.id.action_new);
                return true;
            case R.id.action_settings:
                // TODO create settings dialog
                return true;
            default:
                return true;
        }
    }

    private void goToEditTaskActivity(TaskViewModel taskToEdit, int code) {
        Intent goToNew = new Intent(this, EditTaskActivity.class);
        goToNew.putExtra(EditTaskActivity.TASK_EXTRA, taskToEdit);
        startActivityForResult(goToNew, code);
    }

    private void addTask(TaskViewModel task) {
        adapter.add(task);
    }

    private void removeTask(TaskViewModel task) {
        task.getViewState().onCollapse(task, adapter);
        adapter.remove(task);
    }

    /**
     * test method
     */
    private TaskViewModel getDefaultTaskViewModel(String name, int childMax, int depth) {
        TaskViewModel viewModel = new TaskViewModel();
        viewModel.setName(name);
        viewModel.setState(TaskState.CREATED);
        viewModel.setActivityIntervals(getStartIntervals());
        int childCount = new Random().nextInt(childMax);
        for (int i = 0; i < childCount && depth > 0; i++) {
            viewModel.addChild(getDefaultTaskViewModel(name + depth, childMax, depth - 1));
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
}
