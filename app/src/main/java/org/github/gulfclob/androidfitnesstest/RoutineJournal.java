package org.github.gulfclob.androidfitnesstest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.github.gulfclob.androidfitnesstest.RoutineDbSchema.RoutineTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoutineJournal {
    private static RoutineJournal sRoutineJournal;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static RoutineJournal get(Context context) {
        if (sRoutineJournal == null) {
            sRoutineJournal = new RoutineJournal(context);
        }
        return sRoutineJournal;
    }

    private RoutineJournal(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new RoutineBaseHelper(mContext)
                .getWritableDatabase();
    }

    public List<Routine> getRoutines() {
        List<Routine> routines = new ArrayList<>();

        RoutineCursorWrapper cursor = queryRoutines(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                routines.add(cursor.getRoutine());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return routines;
    }

    public void addRoutine(Routine r) {
        ContentValues values = getContentValues(r);

        mDatabase.insert(RoutineTable.NAME, null, values);
    }

    public Routine getRoutine(UUID id) {
        RoutineCursorWrapper cursor = queryRoutines(
                RoutineTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getRoutine();
        } finally {
            cursor.close();
        }
    }

    public void updateRoutine(Routine routine) {
        String uuidString = routine.getId().toString();
        ContentValues values = getContentValues(routine);

        mDatabase.update(RoutineTable.NAME, values,
                RoutineTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Routine routine) {
        ContentValues values = new ContentValues();
        values.put(RoutineTable.Cols.UUID, routine.getId().toString());
        values.put(RoutineTable.Cols.TITLE, routine.getTitle());
        values.put(RoutineTable.Cols.TEMPLATE_ID, routine.getTemplateId());
        values.put(RoutineTable.Cols.DAYS_A_WEEK, routine.getDaysAWeek());
        values.put(RoutineTable.Cols.CYCLE_LENGTH, routine.getCycleLength());
        values.put(RoutineTable.Cols.EXERCISES, serializeRoutine(routine.getExercises()));

        return values;
    }

    private static byte[] serializeRoutine(ArrayList<ArrayList<ArrayList<Exercise>>> exercises) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(exercises);
            out.close();

            return bos.toByteArray();
        } catch(IOException ioe) {
            Log.e("serializeObject", "error", ioe);

            return null;
        }
    }

    private RoutineCursorWrapper queryRoutines(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RoutineTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null
        );

        return new RoutineCursorWrapper(cursor);
    }
}
