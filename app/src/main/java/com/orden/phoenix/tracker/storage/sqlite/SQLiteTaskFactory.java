package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import com.orden.phoenix.tracker.model.Task;
import com.orden.phoenix.tracker.storage.sqlite.common.DBResources;
import com.orden.phoenix.tracker.storage.sqlite.common.TreeStorableFactoryBase;

/**
 * Created on 6/1/2014.
 */
public class SQLiteTaskFactory extends TreeStorableFactoryBase<Task> {

    public SQLiteTaskFactory() {
        super();
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

    @Override
    protected String getTableName() {
        return DBResources.TASKS_TABLE_NAME;
    }
}