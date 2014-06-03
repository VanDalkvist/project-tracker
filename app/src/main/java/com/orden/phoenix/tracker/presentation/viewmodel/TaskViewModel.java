package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.mapping.TaskMapper;
import com.orden.phoenix.tracker.model.GetTasksCommand;
import com.orden.phoenix.tracker.model.Note;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.model.TaskState;
import com.orden.phoenix.tracker.presentation.view.TaskAdapter;
import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.StorableFactory;
import com.orden.phoenix.tracker.storage.StorageProvider;
import com.orden.phoenix.tracker.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 4/19/14.
 *
 * @author Alex
 */
public class TaskViewModel extends AbstractViewModel {
    protected String name;
    protected String description;
    protected long estimate;
    protected List<TimeIntervalViewModel> activityIntervals = new ArrayList<TimeIntervalViewModel>();
    protected List<String> tags = new ArrayList<String>();
    protected List<Note> notes = new ArrayList<Note>();
    protected TaskState state;
    protected TaskViewItemState viewState = TaskViewItemState.COLLAPSED;
    protected TaskViewModel parent;
    protected List<TaskViewModel> children = Collections.synchronizedList(new ArrayList<TaskViewModel>());

    public TaskViewModel() {
    }

    private static void saveEntity(TaskViewModel node, TaskMapper mapper, StorableFactory<Task> taskFactory) throws DatabaseException {
        Task dto = mapper.toDto(node);
        taskFactory.create(dto);
        // after creation in db id will be set
        node.setId(dto.getId());
        for (TaskViewModel child : node.getChildren()) {
            saveEntity(child, mapper, taskFactory);
        }
    }

    private static void deleteEntity(TaskViewModel node, StorableFactory<Task> taskFactory) throws DatabaseException {
        taskFactory.delete(node.getId());
        for (TaskViewModel child : node.getChildren()) {
            deleteEntity(child, taskFactory);
        }
    }

    public long getTimeSpent() {
        long result = 0;
        for (TimeIntervalViewModel item : activityIntervals) {
            result += item.getDifference();
        }
        return result;
    }

    public int getDepth() {
        return parent != null ? parent.getDepth() + 1 : 0;
    }

    // TODO finish the method when new fields will be required to edit
    public void merge(TaskViewModel source) {
        setName(source.getName());
        setEstimate(source.getEstimate());
        setDescription(source.getDescription());
    }

    public void loadChildren(final TaskAdapter adapter) {
        new GetTasksCommand(adapter.getContext(), getId(), new GetTasksCommand.Callback() {
            @Override
            public void call(List<Task> result) {
                TaskMapper mapper = new TaskMapper();
                for (Task dto : result) {
                    TaskViewModel.this.addChild(mapper.fromDto(dto));
                }
                // update icon
                if (!result.isEmpty()) {
                    adapter.notifyDataSetChanged();
                }
            }
        }).execute((Object[]) null);
    }

    public void createWithChildren(TaskAdapter adapter) {
        try {
            saveEntity(this, new TaskMapper(), StorageProvider.getInstance().getTaskFactory(adapter.getContext()));
            if (isRoot()) {
                adapter.add(this);
            } else {
                adapter.notifyDataSetChanged();
            }
        } catch (DatabaseException e) {
            ExceptionHandler.logException(e, adapter.getContext().getPackageName());
        }
    }

    public void deleteWithChildren(TaskAdapter adapter) {
        try {
            deleteEntity(this, StorageProvider.getInstance().getTaskFactory(adapter.getContext()));
            viewState.onCollapse(this, adapter);
            adapter.remove(this);
        } catch (DatabaseException e) {
            ExceptionHandler.logException(e, adapter.getContext().getPackageName());
        }

    }

    public boolean isRoot() {
        return parent == null;
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

    public List<TimeIntervalViewModel> getActivityIntervals() {
        return activityIntervals;
    }

    public void setActivityIntervals(List<TimeIntervalViewModel> activityIntervals) {
        setIfChanged(activityIntervals, "activityIntervals");
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        setIfChanged(tags, "tags");
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        setIfChanged(notes, "notes");
    }

    public TaskViewItemState getViewState() {
        return viewState;
    }

    public void setViewState(TaskViewItemState viewState) {
        setIfChanged(viewState, "viewState");
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

    /* used by DTO mapper */
    public String getParentId() {
        return getParent() != null ? getParent().getId() : null;
    }
}
