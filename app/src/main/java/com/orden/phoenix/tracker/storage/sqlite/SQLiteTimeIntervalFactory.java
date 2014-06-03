package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import com.orden.phoenix.tracker.model.TimeInterval;
import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.sqlite.common.DBResources;
import com.orden.phoenix.tracker.storage.sqlite.common.StorableFactoryBase;

import java.util.List;

/**
 * Created by I_van on 03.06.2014.
 *
 * @author I_van
 */
public class SQLiteTimeIntervalFactory extends StorableFactoryBase<TimeInterval> {

    public SQLiteTimeIntervalFactory(Context context) {
        super(context);
    }

    @Override
    public long create(TimeInterval interval) throws DatabaseException {
        try {
            open();
            long insertedId = database.insert(DBResources.TIME_INTERVALS_TABLE_NAME, null, prepareEntity(interval));
            interval.setId(Long.toString(insertedId));
            return insertedId;
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public void update(TimeInterval interval) throws DatabaseException {
        try {
            open();
            database.update(DBResources.TIME_INTERVALS_TABLE_NAME, prepareEntity(interval), "_id=" + interval.getId(), null);
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        } finally {
            close();
        }
    }

    @Override
    public TimeInterval findById(String id) throws DatabaseException {
        try {
            open();
            List<TimeInterval> result = readList(database.query(DBResources.TIME_INTERVALS_TABLE_NAME, null, "_id=" + id, null, null, null, null));
            return result.isEmpty() ? null : result.get(0);
        } finally {
            close();
        }
    }

    @Override
    public List<TimeInterval> findAll() throws DatabaseException {
        try {
            open();
            return readList(database.query(DBResources.TIME_INTERVALS_TABLE_NAME, null, null, null, null, null, null));
        } finally {
            close();
        }
    }

    @Override
    public void delete(String id) throws DatabaseException {
        open();
        database.delete(DBResources.TIME_INTERVALS_TABLE_NAME, "_id=" + id, null);
        close();
    }

    @Override
    protected ContentValues prepareEntity(TimeInterval interval) {
        ContentValues entity = new ContentValues();
        entity.put("from", interval.getFrom().toString());
        entity.put("to", interval.getTo().toString());
        return entity;
    }

    @Override
    protected TimeInterval read(Cursor cursor) {
        return null;
    }
}