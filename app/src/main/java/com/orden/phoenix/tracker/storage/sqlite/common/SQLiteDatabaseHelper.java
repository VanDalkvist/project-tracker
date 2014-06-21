package com.orden.phoenix.tracker.storage.sqlite.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 5/28/2014.
 */
public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    public SQLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTasksQuery = "CREATE TABLE " + DBResources.TASKS_TABLE_NAME +
                "(_id INTEGER primary key autoincrement," +
                "name TEXT, " +
                "description TEXT," +
                "parentId INTEGER," +
                "estimate INTEGER);";
        db.execSQL(createTasksQuery);

        String createIntervalsQuery = "CREATE TABLE " + DBResources.TIME_INTERVALS_TABLE_NAME +
                "(_id INTEGER primary key autoincrement," +
                "toTime INTEGER, " +
                "fromTime INTEGER);";
        db.execSQL(createIntervalsQuery);

        String createTaskIntervalRelationsQuery = "CREATE TABLE " + DBResources.TASKS_INTERVAL_RELATIONS_TABLE_NAME +
                "(_id INTEGER primary key autoincrement," +
                "taskId INTEGER, " +
                "intervalId INTEGER);";
        db.execSQL(createTaskIntervalRelationsQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // use to update data base when current version is changed
    }
}