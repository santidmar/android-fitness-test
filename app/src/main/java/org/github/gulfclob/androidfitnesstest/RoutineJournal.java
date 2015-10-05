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
    }

    public List<Routine> getRoutines() {
        return mRoutines;
    }

    public void addRoutine(Routine r) {
        mRoutines.add(r);
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
