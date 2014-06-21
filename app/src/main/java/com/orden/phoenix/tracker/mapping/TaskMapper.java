package com.orden.phoenix.tracker.mapping;

import com.google.inject.Inject;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import org.modelmapper.ModelMapper;

/**
 * Created by I_van on 25.05.2014.
 */
public class TaskMapper implements Mapper<Task, TaskViewModel> {

    private ModelMapper modelMapper;

    @Inject
    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskViewModel fromDto(Task source) {
        return modelMapper.map(source, TaskViewModel.class);
    }

    @Override
    public Task toDto(TaskViewModel source) {
        return modelMapper.map(source, Task.class);
    }
}