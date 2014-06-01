package com.orden.phoenix.tracker.presentation.viewmodel;

import com.orden.phoenix.tracker.utils.Extensions;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created on 4/19/14.
 */
public abstract class AbstractViewModel implements Editable, Serializable{
    private String id;
    private boolean dirty;

    protected <T> void setIfChanged(T value, String propertyName) {
        try {
            Field field = ((Serializable)this).getClass().getDeclaredField(propertyName);
            if (Extensions.equals((T) field.get(this), value)) {
                return;
            }
            field.set(this, value);
            onChanged();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void onChanged() {
        dirty = true;
    }
}
