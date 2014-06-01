package com.orden.phoenix.tracker.model;

import com.orden.phoenix.tracker.storage.TreeStorable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 4/4/14.
 */
public class Task implements TreeStorable {
    private String id;
    private String parentId;
    private String name;
    private TaskState state;
    private String description;
    // TODO change to list of time interval ids
    private List<TimeInterval> activityIntervals = new ArrayList<TimeInterval>();
    private long estimate;
    private List<String> tags = new ArrayList<String>();
    // TODO change to list of node ids
    private List<Note> notes = new ArrayList<Note>();

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task taskModel = (Task) o;

        if (!id.equals(taskModel.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TimeInterval> getActivityIntervals() {
        return activityIntervals;
    }

    public void setActivityIntervals(List<TimeInterval> activityIntervals) {
        this.activityIntervals = activityIntervals;
    }

    public long getEstimate() {
        return estimate;
    }

    public void setEstimate(long estimate) {
        this.estimate = estimate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}
