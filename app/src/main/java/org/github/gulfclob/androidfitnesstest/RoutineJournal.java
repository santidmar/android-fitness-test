package org.github.gulfclob.androidfitnesstest;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoutineJournal {
    private static RoutineJournal sRoutineJournal;
    private List<Routine> mRoutines;

    public static RoutineJournal get(Context context) {
        if (sRoutineJournal == null) {
            sRoutineJournal = new RoutineJournal(context);
        }
        return sRoutineJournal;
    }

    private RoutineJournal(Context context) {
        mRoutines = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            Routine routine = new Routine();
            routine.setTitle("Routine #" + i);
            routine.setCycleLength(i);
            routine.setDaysAWeek(i % 7 + 1);
            mRoutines.add(routine);
        }
    }

    public List<Routine> getRoutines() {
        return mRoutines;
    }

    public Routine getRoutine(UUID id) {
        for (Routine routine : mRoutines) {
            if (routine.getId().equals(id)) {
                return routine;
            }
        }
        return null;
    }

}
