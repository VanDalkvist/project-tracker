package com.orden.phoenix.tracker.storage.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.orden.phoenix.tracker.model.TimeInterval;
import com.orden.phoenix.tracker.storage.sqlite.common.DBResources;
import com.orden.phoenix.tracker.storage.sqlite.common.StorableFactoryBase;

import java.util.Date;

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
    protected ContentValues prepareEntity(TimeInterval interval) {
        ContentValues entity = new ContentValues();
        entity.put("from_time", interval.getFrom().getTime());
        entity.put("to_time", interval.getTo().getTime());
        return entity;
    }

    @Override
    protected TimeInterval read(Cursor cursor) {
        TimeInterval result = new TimeInterval();

        result.setId(cursor.getString(cursor.getColumnIndex("_id")));
        result.setFrom(new Date(cursor.getLong(cursor.getColumnIndex("from_time"))));
        result.setTo(new Date(cursor.getLong(cursor.getColumnIndex("to_time"))));

        return result;
    }

    @Override
    protected String getTableName() {
        return DBResources.TIME_INTERVALS_TABLE_NAME;
    }
}