package com.orden.phoenix.tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.inject.Inject;
import com.orden.phoenix.tracker.mapping.Mapper;
import com.orden.phoenix.tracker.model.GetTasksCommand;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.presentation.behavior.ChangeStateContext;
import com.orden.phoenix.tracker.presentation.behavior.SwitchChangeStateBehavior;
import com.orden.phoenix.tracker.presentation.view.BlankFragment;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.presentation.viewmodel.TimeIntervalViewModel;
import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;
import com.orden.phoenix.tracker.utils.ConsoleLogger;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import roboguice.activity.RoboFragmentActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_task_list)
public class TaskListActivity extends RoboFragmentActivity {
    private static final int CREATE_ACTION_CODE = 1;
    private static final int EDIT_ACTION_CODE = 2;
    private static ConsoleLogger logger = new ConsoleLogger("Project-Tracker");

    private TaskAdapter adapter;
    private TaskViewModel selectedItem;

    @InjectView(R.id.taskListView)
    private ListView taskListView;

    @Inject
    private Mapper<Task, TaskViewModel> taskMapper;

    @Inject
    private TreeStorableFactory<Task> taskFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.d("Creating Activity...");

        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        logger.d("Activity initiate process started.");

        adapter = new TaskAdapter(this, R.layout.activity_task_list, new ArrayList<TaskViewModel>());

        taskListView.setAdapter(adapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // todo: configure ChangeStateBehavior in config;
                new SwitchChangeStateBehavior().change(adapter.getItem(position), new ChangeStateContext(adapter));
            }
        });
        registerForContextMenu(taskListView);

        // fill fragment placeholder with empty fragment
        if (findViewById(R.id.modifyTaskListPanelContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.modifyTaskListPanelContainer, new BlankFragment()).commit();
        }

        logger.d("Activity initiate process finished.");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) return;

        switch (requestCode) {
            case CREATE_ACTION_CODE:
                addTask((TaskViewModel) data.getSerializableExtra(EditTaskActivity.TASK_EXTRA));
                break;
            case EDIT_ACTION_CODE:
                try {
                    TaskViewModel editedTask = (TaskViewModel)
                            data.getSerializableExtra(EditTaskActivity.TASK_EXTRA);
                    selectedItem.merge(editedTask);
                    taskFactory.update(
                            taskMapper.toDto(selectedItem));
                    adapter.notifyDataSetChanged();
                } catch (DatabaseException e) {
                    ExceptionHandler.logException(e, this.getPackageName());
                }
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
                goToEditTaskActivity(selectedItem, EDIT_ACTION_CODE);
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
                goToEditTaskActivity(new TaskViewModel(), CREATE_ACTION_CODE);
                break;
            // TODO remove, test menu
            case R.id.action_generate_test:
                addTask(getDefaultTaskViewModel("name 1", 3, 4));
                addTask(getDefaultTaskViewModel("name 2", 3, 4));
                addTask(getDefaultTaskViewModel("name 3", 3, 4));
                break;
            case R.id.action_modify_mode:
                enableModifyMode();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetTasksCommand(this, null, new GetTasksCommand.Callback() {
            @Override
            public void call(List<Task> result) {
                adapter.clear();
                for (Task dto : result) {
                    TaskViewModel item = taskMapper.fromDto(dto);
                    adapter.add(item);
                    item.loadChildren(adapter);
                }
            }
        }).execute((Object[]) null);
    }

    private void goToEditTaskActivity(TaskViewModel taskToEdit, int code) {
        Intent goToNew = new Intent(this, EditTaskActivity.class);
        goToNew.putExtra(EditTaskActivity.TASK_EXTRA, taskToEdit);
        startActivityForResult(goToNew, code);
    }

    private void addTask(TaskViewModel createdTask) {
        createdTask.createWithChildren(adapter);
    }

    private void removeTask(TaskViewModel task) {
        task.deleteWithChildren(adapter);
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
    private ArrayList<TimeIntervalViewModel> getStartIntervals() {
        ArrayList<TimeIntervalViewModel> activityIntervals = new ArrayList<TimeIntervalViewModel>();
        TimeIntervalViewModel interval = new TimeIntervalViewModel();
        interval.setFrom(new Date());
        activityIntervals.add(interval);
        return activityIntervals;
    }

    private void enableModifyMode() {
        ModifyTaskListFragment fragment = new ModifyTaskListFragment();
        fragment.setParentActivity(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.modifyTaskListPanelContainer, fragment)
                .addToBackStack(null).commit();
    }

    private void disableModifyMode() {
        getSupportFragmentManager().popBackStack();
    }

    public static class ModifyTaskListFragment extends RoboFragment {
        private TaskListActivity parentActivity;
        @InjectView(R.id.taskListModifyOK)
        private Button okButton;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.modify_task_list_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            init();
        }

        private void init() {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentActivity.disableModifyMode();
                }});
        }

        public TaskListActivity getParentActivity() {
            return parentActivity;
        }

        public void setParentActivity(TaskListActivity parentActivity) {
            this.parentActivity = parentActivity;
        }
    }
}
