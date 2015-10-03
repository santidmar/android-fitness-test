package org.github.gulfclob.androidfitnesstest;


import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class Routine {
    private UUID mId;
    private String mTitle;

    /*
        0 represents Custom
        1 represents Texas Method
        2 represents 5/3/1 Big but Boring
        3 represents 5/3/1 Triumverate
        4 represents Smolov Jr.
        5 represents Smolov
     */
    private int mTemplateId;
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
        mTemplateId = 0;
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
        while (mExercises.size() > cycleLength)
            mExercises.remove(mExercises.size() - 1);

        Log.d("Routine", "mCycleLength: " + mCycleLength
                + ", mExercises size: " + mExercises.size());
    }

    public ArrayList<ArrayList<ArrayList<Exercise>>> getExercises() {
        return mExercises;
    }

    public int getTemplateId() {
        return mTemplateId;
    }

    public void setTemplateId(int templateId) {
        mTemplateId = templateId;

        switch (templateId) {
            case 1: {
                // Texas Method
                Log.d("Routine " + getTitle(), "Setting up Texas Method");
                setCycleLength(1);
                setDaysAWeek(3);
                ArrayList<Exercise> dayA = new ArrayList<>();
                dayA.add(new Exercise("Squats", 5, 5, 80));
                dayA.add(new Exercise("Bench Press", 5, 5, 80));
                dayA.add(new Exercise("Romanian Deadlift", 3, 8, 70));
                dayA.add(new Exercise("Chinups", 3, 10, 60));
                ArrayList<Exercise> dayB = new ArrayList<>();
                dayB.add(new Exercise("Front Squats", 2, 5, 50));
                dayB.add(new Exercise("Overhead Press", 3, 5, 50));
                dayB.add(new Exercise("Power Cleans", 3, 3, 60));
                ArrayList<Exercise> dayC = new ArrayList<>();
                dayC.add(new Exercise("Bench Press", 1, 5, 90));
                dayC.add(new Exercise("Deadlift", 1, 3, 92));
                dayC.add(new Exercise("Barbell Rows", 5, 5, 75));
                mExercises.get(0).set(0, dayA);
                mExercises.get(0).set(1, dayB);
                mExercises.get(0).set(2, dayC);
                break;
            }
            case 2: {
                // 5/3/1 Big but Boring
                Log.d("Routine " + getTitle(), "Setting up 5/3/1 Big but Boring");
                setCycleLength(4);
                setDaysAWeek(4);
                ArrayList<Exercise> dayA = new ArrayList<>();
                dayA.add(new Exercise("Overhead Press", 1, 5, 65));
                dayA.add(new Exercise("Overhead Press", 1, 5, 75));
                dayA.add(new Exercise("Overhead Press", 1, 5, 85));
                dayA.add(new Exercise("Overhead Press", 5, 10, 50));
                dayA.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> dayB = new ArrayList<>();
                dayB.add(new Exercise("Deadlift", 1, 5, 65));
                dayB.add(new Exercise("Deadlift", 1, 5, 75));
                dayB.add(new Exercise("Deadlift", 1, 5, 85));
                dayB.add(new Exercise("Deadlift", 5, 10, 50));
                dayB.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> dayC = new ArrayList<>();
                dayC.add(new Exercise("Bench Press", 1, 5, 65));
                break;
            }
            case 3:
                // 5/3/1 Triumverate
                Log.d("Routine " + getTitle(), "Setting up 5/3/1 Triumverate");
                break;
            case 4:
                // Smolov Jr.
                Log.d("Routine " + getTitle(), "Setting up Smolov Jr.");
                break;
            case 5:
                // Smolov
                Log.d("Routine " + getTitle(), "Setting up Smolov");
                break;
        }
    }
}
