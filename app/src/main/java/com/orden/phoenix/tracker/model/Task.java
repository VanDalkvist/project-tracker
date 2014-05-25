package com.orden.phoenix.tracker.model;

import java.util.List;

/**
 * Created on 4/4/14.
 */
public class Task {
    private int id;
    private int parentId;
    private String name;
    private TaskState state;
    private String description;
    private List<TimeInterval> activityIntervals;
    private long estimate;
    private List<String> tags;
    private List<Note> notes;

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task taskModel = (Task) o;

        if (id != taskModel.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
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
