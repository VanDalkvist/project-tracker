package com.orden.phoenix.tracker.model;

import com.orden.phoenix.tracker.storage.Storable;

/**
 * Created by I_van on 16.06.2014.
 */
public class TaskIntervalRelation implements Storable {

    private String id;
    private String taskId;
    private String intervalId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getIntervalId() {
        return intervalId;
    }

    public void setIntervalId(String intervalId) {
        this.intervalId = intervalId;
    }
}