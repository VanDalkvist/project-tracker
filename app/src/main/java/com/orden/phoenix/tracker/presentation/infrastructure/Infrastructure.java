package com.orden.phoenix.tracker.presentation.infrastructure;

import com.google.inject.Inject;
import com.orden.phoenix.tracker.mapping.Mapper;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;

/**
 * Created on 6/1/2014.
 */
public class Infrastructure {
    private static Infrastructure instance;

    @Inject
    private static TreeStorableFactory<Task> taskFactory;

    @Inject
    private static Mapper<Task, TaskViewModel> taskMapper;

    public static Infrastructure getInstance() {
        if (instance == null) {
            instance = new Infrastructure();
        }
        return instance;
    }

    public TreeStorableFactory<Task> getTaskFactory() {
        return taskFactory;
    }

    public Mapper<Task, TaskViewModel> getTaskMapper() {
        return taskMapper;
    }
}