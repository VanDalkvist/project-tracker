package com.orden.phoenix.tracker.mocks;

import com.orden.phoenix.tracker.mapping.TaskMapper;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeInterval;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by I_van on 25.05.2014.
 */
public class TaskViewModelMock {

    public static TaskViewModel generate(int id, int parentId, String name, int childMax, int depth) {
        return getDefaultTaskViewModel(id, parentId, name, childMax, depth);
    }

    private static TaskViewModel getDefaultTaskViewModel(int id, int parentId, String name, int childMax, int depth) {
        TaskViewModel viewModel = new TaskViewModel(new TaskMapper(new ModelMapper()));
        viewModel.setId(Integer.toString(id));
//        viewModel.setParent(name);
        viewModel.setName(name);
        viewModel.setState(TaskState.CREATED);
//        viewModel.setActivityIntervals(getStartIntervals());
        viewModel.setDescription("description");
        viewModel.setEstimate(5);
//        int childCount = new Random().nextInt(childMax);
//        for (int i = 0; i < childCount && depth > 0; i++) {
//            viewModel.addChild(getDefaultTaskViewModel(name + depth, null, childMax, depth - 1));
//        }
        return viewModel;
    }

    private static ArrayList<TimeInterval> getStartIntervals() {
        ArrayList<TimeInterval> activityIntervals = new ArrayList<TimeInterval>();
        TimeInterval interval = new TimeInterval();
        interval.setFrom(new Date());
        activityIntervals.add(interval);
        return activityIntervals;
    }
}