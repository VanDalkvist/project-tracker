package com.order.phoenix.tracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.order.phoenix.tracker.model.TaskState;
import com.order.phoenix.tracker.model.TimeIntervalModel;
import com.order.phoenix.tracker.presentation.view.TaskAdapter;
import com.order.phoenix.tracker.presentation.viewmodel.TaskViewItemState;
import com.order.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.order.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TaskListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        setContentView(R.layout.activity_task_list);
        Init();
    }

    private void Init() {
        List<TaskViewModel> items = new ArrayList<TaskViewModel>();
        TaskAdapter itemAdapter = new TaskAdapter(this, R.layout.task_view, items);

        items.add(getDefaultTaskViewModel("name 1", 3, 4, itemAdapter));
        items.add(getDefaultTaskViewModel("name 2", 3, 4, itemAdapter));
        items.add(getDefaultTaskViewModel("name 3", 3, 4, itemAdapter));

        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(itemAdapter);
        taskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskAdapter adapter = (TaskAdapter) adapterView.getAdapter();
                TaskViewModel item = adapter.getItem(i);
                if (item.getViewState() == TaskViewItemState.COLLAPSED) {
                    item.expand();
                } else {
                    item.collapse();
                }
                return true;
            }
        });
    }

    /**
     * test method
     * @param name
     * @return
     */
    private TaskViewModel getDefaultTaskViewModel(String name, int depth, int childrenMax, TaskAdapter adapter) {
        TaskViewModel viewModel = new TaskViewModel();
        viewModel.setName(name);
        viewModel.setState(TaskState.CREATED);
        viewModel.setActivityIntervals(GetStartIntervals());
        viewModel.setAdapter(adapter);
        int childrenCount = new Random().nextInt(childrenMax);
        for(int i = 0; i<childrenCount && depth > 0; i++) {
            viewModel.getChildren().add(getDefaultTaskViewModel(name, depth - 1, childrenMax, adapter));
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
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

    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_task_list, container, false);
//            return rootView;
//        }
//    }
}
