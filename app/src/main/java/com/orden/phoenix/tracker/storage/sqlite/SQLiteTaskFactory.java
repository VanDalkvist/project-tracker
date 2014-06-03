package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.sqlite.common.DBResources;
import com.orden.phoenix.tracker.storage.sqlite.common.TreeStorableFactoryBase;

import java.util.List;

/**
 * Created on 6/1/2014.
 */
public class SQLiteTaskFactory extends TreeStorableFactoryBase<Task> {

    public SQLiteTaskFactory(Context context) {
        super(context);
    }

    @Override
    public long create(Task task) throws DatabaseException {
        // todo: move to base
        try {
            open();
            long insertedId = database.insert(DBResources.TASKS_TABLE_NAME, null, prepareEntity(task));
            task.setId(Long.toString(insertedId));
            return insertedId;
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public void update(Task task) throws DatabaseException {
        // todo: move to base
        try {
            open();
            database.update(DBResources.TASKS_TABLE_NAME, prepareEntity(task), "_id=" + task.getId(), null);
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public Task findById(String id) throws DatabaseException{
        // todo: move to base
        try {
            open();
            List<Task> result = readList(database.query(DBResources.TASKS_TABLE_NAME, null, "_id=" + id, null, null, null, null));
            return result.isEmpty() ? null : result.get(0);
        } finally {
            close();
        }
    }

    @Override
    public List<Task> findAll() throws DatabaseException{
        // todo: move to base
        try {
            open();
            return readList(database.query(DBResources.TASKS_TABLE_NAME, null, null, null, null, null, null));
        } finally {
            close();
        }
    }

    @Override
    public List<Task> findChildren(String parentId) throws DatabaseException{
        try {
            open();
            return readList(database.query(DBResources.TASKS_TABLE_NAME, null, parentId == null ? "parentId is null" : "parentId=" + parentId, null, null, null, null));
        } finally {
            close();
        }
    }

    @Override
    public void delete(String id) throws DatabaseException {
        // todo: move to base
        open();
        database.delete(DBResources.TASKS_TABLE_NAME, "_id=" + id, null);
        close();
    }

    /**
     * Prepares DTO to be written to database
     * @param task - task to write
     */
    @Override
    protected ContentValues prepareEntity(Task task) {
        ContentValues entity = new ContentValues();
        entity.put("name", task.getName());
        entity.put("parentId", task.getParentId());
        entity.put("description", task.getDescription());
        entity.put("estimate", task.getEstimate());
        return entity;
    }

    /**
     * Converts table row to DTO
     * @param cursor - cursor with set row
     * @return converted result
     */
    @Override
    protected Task read(Cursor cursor) {
        Task result = new Task();

        result.setId(cursor.getString(cursor.getColumnIndex("_id")));
        result.setName(cursor.getString(cursor.getColumnIndex("name")));
        result.setParentId(cursor.getString(cursor.getColumnIndex("parentId")));
        result.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        result.setEstimate(cursor.getLong(cursor.getColumnIndex("estimate")));

        return result;
    }
}