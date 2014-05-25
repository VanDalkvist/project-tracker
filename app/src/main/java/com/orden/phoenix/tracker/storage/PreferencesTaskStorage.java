package com.orden.phoenix.tracker.storage;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.orden.phoenix.tracker.model.Task;

/**
 * Created by I_van on 25.05.2014.
 */
public class PreferencesTaskStorage implements TaskStorage {

    private Activity activity;

    public PreferencesTaskStorage(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Task read(int id) {
        SharedPreferences preferences = activity.getPreferences(activity.MODE_PRIVATE);

        String serialized = preferences.getString(String.valueOf(id), "");

        Object deserialized = new Gson().fromJson(serialized, Task.class);

        return (Task) deserialized;
    }

    @Override
    public void write(Task task) {
        SharedPreferences preferences = activity.getPreferences(activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        String serialized = new Gson().toJson(task);

        editor.putString(String.valueOf(task.getId()), serialized);

        editor.commit();
    }
}