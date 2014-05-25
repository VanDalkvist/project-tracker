package com.orden.phoenix.tracker.mapping;

/**
 * Created by I_van on 25.05.2014.
 */
public interface Mapper<TDto, TModel> {
    TModel fromDto(TDto source);

    TDto toDto(TModel source);
}
