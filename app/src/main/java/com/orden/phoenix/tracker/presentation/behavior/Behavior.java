package com.orden.phoenix.tracker.presentation.behavior;

/**
 * Created by I_van on 24.05.2014.
 */
public interface Behavior<TItem, TContext> {
    TItem change(TItem current, TContext context);
}