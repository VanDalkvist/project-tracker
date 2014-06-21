package com.orden.phoenix.tracker.storage.sqlite.common;

import android.app.Application;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by I_van on 21.06.2014.
 */
public class DatabaseHelperProvider implements Provider<SQLiteDatabaseHelper> {

    @Inject
    Application application;

    @Override
    public SQLiteDatabaseHelper get() {
        return new SQLiteDatabaseHelper(
                application.getApplicationContext(),
                DBResources.DB_NAME,
                null,
                DBResources.DB_CURRENT_VERSION);
    }
}