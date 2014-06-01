package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.TreeStorableFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/1/2014.
 */
public class SQLiteTaskFactory implements TreeStorableFactory<Task> {
    private static final String DB_NAME = "ProjectTrackerTasks";
    public static final String TASKS_TABLE_NAME = "tasks";
    private static final int DB_CURRENT_VERSION = 1;
    private SQLiteDatabase database;
    private SQLiteDatabaseHelper databaseOpenHelper;

    public SQLiteTaskFactory(Context context) {
        databaseOpenHelper = new SQLiteDatabaseHelper(context, DB_NAME, null, DB_CURRENT_VERSION);
    }

    @Override
    public void createInstance(Task instance) throws DatabaseException {
        try {
            open();
            instance.setId(Long.toString(database.insert(TASKS_TABLE_NAME, null, prepareEntity(instance))));
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public void updateInstance(Task instance) throws DatabaseException {
        try {
            open();
            database.update(TASKS_TABLE_NAME, prepareEntity(instance), "_id=" + instance.getId(), null);
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public Task findById(String id) throws DatabaseException{
        try {
            open();
            List<Task> result = readResult(database.query(TASKS_TABLE_NAME, null, "_id="+id, null, null, null, null));
            return result.isEmpty() ? null : result.get(0);
        } finally {
            close();
        }
    }

    @Override
    public List<Task> findAll() throws DatabaseException{
        try {
            open();
            return readResult(database.query(TASKS_TABLE_NAME, null, null, null, null, null, null));
        } finally {
            close();
        }
    }

    @Override
    public List<Task> findChildren(String parentId) throws DatabaseException{
        try {
            open();
            return readResult(database.query(TASKS_TABLE_NAME, null, parentId == null ? "parentId is null" : "parentId="+parentId, null, null, null, null));
        } finally {
            close();
        }
    }

    @Override
    public void delete(String id) throws DatabaseException {
        open();
        database.delete(TASKS_TABLE_NAME, "_id=" + id, null);
        close();
    }

    /**
     * Prepares DTO to be written to data base
     * @param task - task to write
     */
    private ContentValues prepareEntity(Task task) {
        ContentValues entity = new ContentValues();
        entity.put("name", task.getName());
        entity.put("parentId", task.getParentId());
        entity.put("description", task.getDescription());
        entity.put("estimate", task.getEstimate());
        return entity;
    }

    private void open() throws DatabaseException {
        try {
            database = databaseOpenHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        }
    }

    private void close() {
        if(database != null) {
            database.close();
        }
    }

    /**
     * Converts table row to DTO
     * @param cursor - cursor with set row
     * @return converted result
     */
    private Task readTask(Cursor cursor) {
        Task result = new Task();
        result.setId(cursor.getString(cursor.getColumnIndex("_id")));
        result.setName(cursor.getString(cursor.getColumnIndex("name")));
        result.setParentId(cursor.getString(cursor.getColumnIndex("parentId")));
        result.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        result.setEstimate(cursor.getLong(cursor.getColumnIndex("estimate")));

        return result;
    }

    private List<Task> readResult(Cursor resultSet) {
        List<Task> result = new ArrayList<Task>();
        if(!resultSet.isAfterLast()){
            while (resultSet.moveToNext()) {
                result.add(readTask(resultSet));
            }
        }
        return result;
    }
}
