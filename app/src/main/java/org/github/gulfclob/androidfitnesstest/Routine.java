package org.github.gulfclob.androidfitnesstest;


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
    }

    public int getCycleLength() {
        return mCycleLength;
    }

    public void setCycleLength(int cycleLength) {
        mCycleLength = cycleLength;
    }

    public ArrayList<ArrayList<ArrayList<Exercise>>> getExercises() {
        return mExercises;
    }

    public void setExercises(ArrayList<ArrayList<ArrayList<Exercise>>> exercises) {
        mExercises = exercises;
    }
}
