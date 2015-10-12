package org.github.gulfclob.androidfitnesstest;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.github.gulfclob.androidfitnesstest.RoutineDbSchema.RoutineTable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class RoutineCursorWrapper extends CursorWrapper {
    public RoutineCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Routine getRoutine() {
        String uuidString = getString(getColumnIndex(RoutineTable.Cols.UUID));
        String title = getString(getColumnIndex(RoutineTable.Cols.TITLE));
        int templateId = getInt(getColumnIndex(RoutineTable.Cols.TEMPLATE_ID));
        int daysAWeek = getInt(getColumnIndex(RoutineTable.Cols.DAYS_A_WEEK));
        int cycleLength = getInt(getColumnIndex(RoutineTable.Cols.CYCLE_LENGTH));

        // FIXME
        /*
        String exercises = getString(getColumnIndex(RoutineTable.Cols.EXERCISES));
        JSONArray jsonObject;
        try {
            jsonObject = new JSONArray(exercises);
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        */

        Routine routine = new Routine(UUID.fromString(uuidString));
        routine.setTitle(title);
        routine.setTemplateId(templateId);
        routine.setDaysAWeek(daysAWeek);
        routine.setCycleLength(cycleLength);

        return routine;
    }
}
