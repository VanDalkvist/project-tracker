package com.orden.phoenix.tracker.ioc;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.orden.phoenix.tracker.mapping.Mapper;
import com.orden.phoenix.tracker.mapping.TaskMapper;
import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.presentation.infrastructure.Infrastructure;
import com.orden.phoenix.tracker.presentation.viewmodel.TaskViewModel;
import com.orden.phoenix.tracker.storage.TreeStorable;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;
import com.orden.phoenix.tracker.storage.sqlite.SQLiteTaskFactory;
import com.orden.phoenix.tracker.storage.sqlite.common.DatabaseHelperProvider;
import com.orden.phoenix.tracker.storage.sqlite.common.StorableFactoryBase;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Created by I_van on 01.06.2014.
 */
public class IOCModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(TreeStorable.class).to(Task.class);
        binder.bind(DatabaseHelperProvider.class).toInstance(new DatabaseHelperProvider());

        binder.bind(ModelMapper.class).toInstance(configureMapper());

        binder.requestStaticInjection(StorableFactoryBase.class, TaskViewModel.class, Infrastructure.class);

        binder.bind(new TypeLiteral<TreeStorableFactory<Task>>() {
        }).to(SQLiteTaskFactory.class);
        binder.bind(new TypeLiteral<Mapper<Task, TaskViewModel>>() {
        }).to(TaskMapper.class);
    }

    private ModelMapper configureMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}