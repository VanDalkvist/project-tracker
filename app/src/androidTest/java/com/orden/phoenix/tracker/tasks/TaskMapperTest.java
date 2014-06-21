package com.orden.phoenix.tracker.tasks;

import android.test.InstrumentationTestCase;

import com.orden.phoenix.tracker.mapping.TaskMapper;
import com.orden.phoenix.tracker.mocks.TaskMock;
import com.orden.phoenix.tracker.mocks.TaskViewModelMock;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;

import org.modelmapper.ModelMapper;

/**
 * Created by I_van on 25.05.2014.
 */
public class TaskMapperTest extends InstrumentationTestCase {

    private TaskMapper taskMapper;

    public void setUp() throws Exception {
        super.setUp();

        taskMapper = new TaskMapper(new ModelMapper());
    }

    public void testMapToDto() {
        Task generatedTask = TaskMock.generate(1, "task 1", 2);
        TaskViewModel taskViewModel = taskMapper.fromDto(generatedTask);

        assertEquals(1, taskViewModel.getId());
        assertEquals("task 1", taskViewModel.getName());
        assertEquals(generatedTask.getDescription(), taskViewModel.getDescription());
        assertEquals(generatedTask.getEstimate(), taskViewModel.getEstimate());
        assertEquals(generatedTask.getState(), taskViewModel.getState());
    }

    public void testMapFromDto() {
        TaskViewModel generatedTaskViewModel = TaskViewModelMock.generate(1, 2, "task 1", 2, 1);
        Task task = taskMapper.toDto(generatedTaskViewModel);

        assertEquals(1, task.getId());
        assertEquals("task 1", task.getName());
        assertEquals(generatedTaskViewModel.getDescription(), task.getDescription());
        assertEquals(generatedTaskViewModel.getEstimate(), task.getEstimate());
        assertEquals(generatedTaskViewModel.getState(), task.getState());
    }
}