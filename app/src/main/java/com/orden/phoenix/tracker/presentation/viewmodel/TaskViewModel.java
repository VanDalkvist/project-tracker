package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.model.NoteModel;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.model.TimeIntervalModel;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 4/19/14.
 */
public class TaskViewModel extends AbstractViewModel {
    protected String name;
    protected String description;
    protected long estimate;
    protected List<TimeIntervalModel> activityIntervals;
    protected List<String> tags;
    protected List<NoteModel> notes;
    protected TaskState state;
    protected TaskViewItemState viewState = TaskViewItemState.COLLAPSED;
    protected TaskAdapter adapter;
    protected TaskViewModel parent;
    protected List<TaskViewModel> children = new ArrayList<TaskViewModel>();

    public TaskViewModel(TaskAdapter adapter) {
        this.adapter = adapter;
    }

    public long getTimeSpent() {
        long result = 0;
        for (TimeIntervalModel item : activityIntervals) {
            result += item.getDifference();
        }
        return result;
    }

    public int getDepth() {
        return parent != null ? parent.getDepth() + 1 : 0;
    }

    public void changeState(TaskViewItemState state) {
        viewState.onChangeState(this, adapter);

        this.setViewState(state);
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

    public TaskViewItemState getViewState() {
        return viewState;
    }

    public void setViewState(TaskViewItemState viewState) {
        setIfChanged(viewState, "viewState");
    }

    public TaskAdapter getAdapter() {
        return adapter;
    }


    public void addChild(TaskViewModel child) {
        children.add(child);
        child.setParent(this);
    }

    public List<TaskViewModel> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public TaskViewModel getParent() {
        return parent;
    }

    public void setParent(TaskViewModel parent) {
        setIfChanged(parent, "parent");
    }
}
