package com.order.phoenix.tracker.presentation.viewmodel;

import com.order.phoenix.tracker.model.NoteModel;
import com.order.phoenix.tracker.model.TaskState;
import com.order.phoenix.tracker.model.TimeIntervalModel;
import com.order.phoenix.tracker.presentation.view.TaskAdapter;

import java.util.ArrayList;
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
    private TaskViewItemState viewState = TaskViewItemState.COLLAPSED;
    private List<TaskViewModel> children = new ArrayList<TaskViewModel>();
    private TaskAdapter adapter;

    public TaskViewModel() {
    }

    public long getTimeSpent() {
        long result = 0;
        for (TimeIntervalModel item : activityIntervals) {
            result += item.getDifference();
        }
        return result;
    }

    public void collapse(){
        viewState.onCollapse(this, adapter);
    }

    public void expand() {
        viewState.onExpand(this, adapter);
    }

    public String getStateText() {
        return viewState.getStateText(this);
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
        this.viewState = viewState;
    }

    public List<TaskViewModel> getChildren() {
        return children;
    }

    public void setChildren(List<TaskViewModel> children) {
        this.children = children;
    }

    public TaskAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(TaskAdapter adapter) {
        this.adapter = adapter;
    }
}
