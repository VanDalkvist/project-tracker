package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.orden.phoenix.tracker.model.TaskIntervalRelation;
import com.orden.phoenix.tracker.storage.sqlite.common.DBResources;
import com.orden.phoenix.tracker.storage.sqlite.common.StorableFactoryBase;

/**
 * Created on 6/1/2014.
 */
public class SQLiteTaskIntervalRelationFactory extends StorableFactoryBase<TaskIntervalRelation> {

    public SQLiteTaskIntervalRelationFactory(Context context) {
        super();
    }

    @Override
    protected ContentValues prepareEntity(TaskIntervalRelation relation) {
        ContentValues entity = new ContentValues();
        entity.put("intervalId", relation.getIntervalId());
        entity.put("taskId", relation.getTaskId());
        return entity;
    }

    @Override
    protected TaskIntervalRelation read(Cursor cursor) {
        TaskIntervalRelation result = new TaskIntervalRelation();

        result.setId(cursor.getString(cursor.getColumnIndex("_id")));
        result.setIntervalId(cursor.getString(cursor.getColumnIndex("intervalId")));
        result.setTaskId(cursor.getString(cursor.getColumnIndex("taskId")));

        return result;
    }

    @Override
    protected String getTableName() {
        return DBResources.TASKS_INTERVAL_RELATIONS_TABLE_NAME;
    }
}