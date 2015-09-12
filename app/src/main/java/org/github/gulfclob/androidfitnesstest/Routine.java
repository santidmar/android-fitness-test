package org.github.gulfclob.androidfitnesstest;


import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class Routine {
    private UUID mId;
    private String mTitle;

    private int mDaysAWeek;
    private int mCycleLength;
    /*
        Outmost ArrayList represents weeks.
        Next represents workouts.
        Next is a list of workouts
     */
    private ArrayList<ArrayList<ArrayList<Exercise>>> mExercises;

    public Routine() {
        mId = UUID.randomUUID();
        mExercises = new ArrayList<>();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getDaysAWeek() {
        return mDaysAWeek;
    }

    public void setDaysAWeek(int daysAWeek) {
        mDaysAWeek = daysAWeek;

        for (int i = 0; i < mExercises.size(); i++) {
            while (mExercises.get(i).size() < daysAWeek)
                mExercises.get(i).add(new ArrayList<Exercise>());
            while (mExercises.get(i).size() > daysAWeek)
                mExercises.get(i).remove(mExercises.get(i).size() - 1);

            Log.d("Routine Week " + i, "Week Length:" + mExercises.get(i).size()
                + ", daysAWeek: " + daysAWeek);
        }
    }

    public int getCycleLength() {
        return mCycleLength;
    }

    public void setCycleLength(int cycleLength) {
        mCycleLength = cycleLength;

        while (mExercises.size() < cycleLength) {
            ArrayList<ArrayList<Exercise>> newWeek = new ArrayList<>();
            for (int i = 0; i < mDaysAWeek; i++)
                newWeek.add(new ArrayList<Exercise>());
            mExercises.add(newWeek);
        }
        while (mExercises.size() < cycleLength)
            mExercises.add(new ArrayList<ArrayList<Exercise>>());
        while (mExercises.size() > cycleLength)
            mExercises.remove(mExercises.size() - 1);

        Log.d("Routine", "mCycleLength: " + mCycleLength
                + ", mExercises size: " + mExercises.size());
    }

    public ArrayList<ArrayList<ArrayList<Exercise>>> getExercises() {
        return mExercises;
    }
}
