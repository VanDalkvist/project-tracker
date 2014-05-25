package com.orden.phoenix.tests.mocks;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeInterval;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by I_van on 25.05.2014.
 */
public class TaskMock {

    public static Task generate(int id, String name, int parentId) {
        return getDefaultTask(id, name, parentId);
    }

    private static Task getDefaultTask(int id, String name, int parentId) {
        Task model = new Task();
        model.setName(name);
        model.setState(TaskState.CREATED);
        model.setActivityIntervals(getStartIntervals());
        model.setEstimate(5);
        model.setDescription("description");
        model.setId(id);
        model.setParentId(parentId);
        return model;
    }

    private static ArrayList<TimeInterval> getStartIntervals() {
        ArrayList<TimeInterval> activityIntervals = new ArrayList<TimeInterval>();

        TimeInterval interval = new TimeInterval();
        interval.setFrom(new Date());
        activityIntervals.add(interval);

        return activityIntervals;
    }
}
