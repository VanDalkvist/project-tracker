package com.orden.phoenix.tracker.mapping;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Created by I_van on 25.05.2014.
 */
public class TaskMapper implements Mapper<Task, TaskViewModel> {
    private ModelMapper modelMapper;

    public TaskMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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