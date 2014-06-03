package com.orden.phoenix.tracker.storage.sqlite.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.orden.phoenix.tracker.storage.DatabaseException;
import com.orden.phoenix.tracker.storage.Storable;
import com.orden.phoenix.tracker.storage.StorableFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I_van on 03.06.2014.
 *
 * @author I_van
 */
public abstract class StorableFactoryBase<T extends Storable> implements StorableFactory<T> {

    protected SQLiteDatabase database;
    protected SQLiteDatabaseHelper databaseOpenHelper;

    public StorableFactoryBase(Context context) {
        databaseOpenHelper = new SQLiteDatabaseHelper(context, DBResources.DB_NAME, null, DBResources.DB_CURRENT_VERSION);
    }

    protected void open() throws DatabaseException {
        try {
            database = databaseOpenHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            throw new DatabaseException(e);
        }
    }

    protected void close() {
        if (database == null) return;

        database.close();
    }

    /**
     * Prepares DTO to be written to database
     *
     * @param instance - instance to prepare
     */
    protected abstract ContentValues prepareEntity(T instance);

    /**
     * Read set from cursor on database table
     *
     * @param cursor - the cursor on table
     * @return converted result
     */
    protected List<T> readList(Cursor cursor) {
        List<T> result = new ArrayList<T>();
        if (cursor.isAfterLast()) return result;

        while (cursor.moveToNext()) {
            result.add(read(cursor));
        }
        return result;
    }

    /**
     * Converts table row to DTO
     *
     * @param cursor - cursor with set row
     * @return converted result
     */
    protected abstract T read(Cursor cursor);
}