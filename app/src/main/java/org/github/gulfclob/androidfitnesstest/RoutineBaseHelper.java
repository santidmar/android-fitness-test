package org.github.gulfclob.androidfitnesstest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.github.gulfclob.androidfitnesstest.RoutineDbSchema.RoutineTable;

public class RoutineBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "routineBase.db";

    public RoutineBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RoutineTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RoutineTable.Cols.UUID + ", " +
                RoutineTable.Cols.TITLE + ", " +
                RoutineTable.Cols.TEMPLATE_ID + ", " +
                RoutineTable.Cols.DAYS_A_WEEK + ", " +
                RoutineTable.Cols.CYCLE_LENGTH + ", " +
                RoutineTable.Cols.EXERCISES + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
