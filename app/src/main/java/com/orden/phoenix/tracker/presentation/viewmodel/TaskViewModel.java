package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.model.NoteModel;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeIntervalModel;

import java.util.List;

/**
 * Created on 4/19/14.
 */
public class TaskViewModel extends AbstractViewModel {
    private String name;
    private String description;
    private long estimate;
    private List<TimeIntervalModel> activityIntervals;
    private List<String> tags;
    private List<NoteModel> notes;
    private TaskState state;

    public TaskViewModel() {
    }

    public long getTimeSpent() {
        long result = 0;
        for (TimeIntervalModel item : activityIntervals) {
            result += item.getDifference();
        }
        return result;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public boolean isFinished() {
        return state == TaskState.FINISHED;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setIfChanged(name, "name");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        setIfChanged(description, "description");
    }

    public long getEstimate() {
        return estimate;
    }

    public void setEstimate(long estimate) {
        setIfChanged(estimate, "estimate");
    }

    public List<TimeIntervalModel> getActivityIntervals() {
        return activityIntervals;
    }

    public void setActivityIntervals(List<TimeIntervalModel> activityIntervals) {
        setIfChanged(activityIntervals, "activityIntervals");
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        setIfChanged(tags, "tags");
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        setIfChanged(notes, "notes");
    }
}
