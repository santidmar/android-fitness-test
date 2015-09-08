package org.github.gulfclob.androidfitnesstest;


import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Routine {
    private UUID mId;
    private String mTitle;
    private Date mDateCreated;

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
        mDateCreated = new Date();
        mExercises = new ArrayList<ArrayList<ArrayList<Exercise>>>();
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

    public Date getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        mDateCreated = dateCreated;
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

    public void setExercises(ArrayList<ArrayList<ArrayList<Exercise>>> exercises) {
        mExercises = exercises;
    }
}
