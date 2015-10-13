package org.github.gulfclob.androidfitnesstest;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import org.github.gulfclob.androidfitnesstest.RoutineDbSchema.RoutineTable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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

        ArrayList<ArrayList<ArrayList<Exercise>>> exercises =
                deserializeRoutine(getBlob(getColumnIndex(RoutineTable.Cols.EXERCISES)));


        Routine routine = new Routine(UUID.fromString(uuidString));
        routine.setTitle(title);
        routine.setTemplateId(templateId);
        routine.setDaysAWeek(daysAWeek);
        routine.setCycleLength(cycleLength);
        routine.setExercises(exercises);

        return routine;
    }

    public static ArrayList<ArrayList<ArrayList<Exercise>>> deserializeRoutine(byte[] b) {
        try {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b));
            ArrayList<ArrayList<ArrayList<Exercise>>> object =
                    (ArrayList<ArrayList<ArrayList<Exercise>>>)in.readObject();
            in.close();

            return object;
        } catch (ClassNotFoundException cnfe) {
            Log.e("deserializeObject", "class not found error", cnfe);

            return null;
        } catch (IOException ioe) {
            Log.e("deserializeObject", "io error", ioe);

            return null;
        }
    }
}
