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
        this(UUID.randomUUID());
    }

    public Routine(UUID id) {
        mId = id;
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

    public void setExercises(ArrayList<ArrayList<ArrayList<Exercise>>> exercises) {
        mExercises = exercises;
    }

    public int getTemplateId() {
        return mTemplateId;
    }

    /*
        This function is an awful way to load/create routines from a template.
        Final build should load routine templates from a csv or something
     */
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
                ArrayList<Exercise> day5A = new ArrayList<>();
                day5A.add(new Exercise("Overhead Press", 1, 5, 65));
                day5A.add(new Exercise("Overhead Press", 1, 5, 75));
                day5A.add(new Exercise("Overhead Press", 1, 5, 85));
                day5A.add(new Exercise("Overhead Press", 5, 10, 50));
                day5A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day5B = new ArrayList<>();
                day5B.add(new Exercise("Deadlift", 1, 5, 65));
                day5B.add(new Exercise("Deadlift", 1, 5, 75));
                day5B.add(new Exercise("Deadlift", 1, 5, 85));
                day5B.add(new Exercise("Deadlift", 5, 10, 50));
                day5B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day5C = new ArrayList<>();
                day5C.add(new Exercise("Bench Press", 1, 5, 65));
                day5C.add(new Exercise("Bench Press", 1, 5, 75));
                day5C.add(new Exercise("Bench Press", 1, 5, 85));
                day5C.add(new Exercise("Bench Press", 5, 10, 50));
                day5C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day5D = new ArrayList<>();
                day5D.add(new Exercise("Squats", 1, 5, 65));
                day5D.add(new Exercise("Squats", 1, 5, 75));
                day5D.add(new Exercise("Squats", 1, 5, 85));
                day5D.add(new Exercise("Squats", 5, 10, 50));
                day5D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekA = new ArrayList<>();
                weekA.add(day5A);
                weekA.add(day5B);
                weekA.add(day5C);
                weekA.add(day5D);
                ArrayList<Exercise> day3A = new ArrayList<>();
                day3A.add(new Exercise("Overhead Press", 1, 3, 70));
                day3A.add(new Exercise("Overhead Press", 1, 3, 80));
                day3A.add(new Exercise("Overhead Press", 1, 3, 90));
                day3A.add(new Exercise("Overhead Press", 5, 10, 50));
                day3A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day3B = new ArrayList<>();
                day3B.add(new Exercise("Deadlift", 1, 3, 70));
                day3B.add(new Exercise("Deadlift", 1, 3, 80));
                day3B.add(new Exercise("Deadlift", 1, 3, 90));
                day3B.add(new Exercise("Deadlift", 5, 10, 50));
                day3B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day3C = new ArrayList<>();
                day3C.add(new Exercise("Bench Press", 1, 3, 70));
                day3C.add(new Exercise("Bench Press", 1, 3, 80));
                day3C.add(new Exercise("Bench Press", 1, 3, 90));
                day3C.add(new Exercise("Bench Press", 5, 10, 50));
                day3C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day3D = new ArrayList<>();
                day3D.add(new Exercise("Squats", 1, 3, 70));
                day3D.add(new Exercise("Squats", 1, 3, 80));
                day3D.add(new Exercise("Squats", 1, 3, 90));
                day3D.add(new Exercise("Squats", 5, 10, 50));
                day3D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekB = new ArrayList<>();
                weekB.add(day3A);
                weekB.add(day3B);
                weekB.add(day3C);
                weekB.add(day3D);
                ArrayList<Exercise> day1A = new ArrayList<>();
                day1A.add(new Exercise("Overhead Press", 1, 5, 75));
                day1A.add(new Exercise("Overhead Press", 1, 3, 85));
                day1A.add(new Exercise("Overhead Press", 1, 1, 95));
                day1A.add(new Exercise("Overhead Press", 5, 10, 50));
                day1A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day1B = new ArrayList<>();
                day1B.add(new Exercise("Deadlift", 1, 5, 75));
                day1B.add(new Exercise("Deadlift", 1, 3, 85));
                day1B.add(new Exercise("Deadlift", 1, 1, 95));
                day1B.add(new Exercise("Deadlift", 5, 10, 50));
                day1B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day1C = new ArrayList<>();
                day1C.add(new Exercise("Bench Press", 1, 5, 75));
                day1C.add(new Exercise("Bench Press", 1, 3, 85));
                day1C.add(new Exercise("Bench Press", 1, 1, 95));
                day1C.add(new Exercise("Bench Press", 5, 10, 50));
                day1C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day1D = new ArrayList<>();
                day1D.add(new Exercise("Squats", 1, 5, 75));
                day1D.add(new Exercise("Squats", 1, 3, 85));
                day1D.add(new Exercise("Squats", 1, 1, 95));
                day1D.add(new Exercise("Squats", 5, 10, 50));
                day1D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekC = new ArrayList<>();
                weekC.add(day1A);
                weekC.add(day1B);
                weekC.add(day1C);
                weekC.add(day1D);
                ArrayList<Exercise> dayLightA = new ArrayList<>();
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 40));
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 50));
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 60));
                dayLightA.add(new Exercise("Overhead Press", 5, 10, 50));
                dayLightA.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> dayLightB = new ArrayList<>();
                dayLightB.add(new Exercise("Deadlift", 1, 5, 40));
                dayLightB.add(new Exercise("Deadlift", 1, 5, 50));
                dayLightB.add(new Exercise("Deadlift", 1, 5, 60));
                dayLightB.add(new Exercise("Deadlift", 5, 10, 50));
                dayLightB.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> dayLightC = new ArrayList<>();
                dayLightC.add(new Exercise("Bench Press", 1, 5, 40));
                dayLightC.add(new Exercise("Bench Press", 1, 5, 50));
                dayLightC.add(new Exercise("Bench Press", 1, 5, 60));
                dayLightC.add(new Exercise("Bench Press", 5, 10, 50));
                dayLightC.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> dayLightD = new ArrayList<>();
                dayLightD.add(new Exercise("Squats", 1, 5, 40));
                dayLightD.add(new Exercise("Squats", 1, 5, 50));
                dayLightD.add(new Exercise("Squats", 1, 5, 60));
                dayLightD.add(new Exercise("Squats", 5, 10, 50));
                dayLightD.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekD = new ArrayList<>();
                weekD.add(dayLightA);
                weekD.add(dayLightB);
                weekD.add(dayLightC);
                weekD.add(dayLightD);

                mExercises.set(0, weekA);
                mExercises.set(1, weekB);
                mExercises.set(2, weekC);
                mExercises.set(3, weekD);
                break;
            }
            case 3:
                // 5/3/1 Triumverate
                Log.d("Routine " + getTitle(), "Setting up 5/3/1 Triumverate");
                setCycleLength(4);
                setDaysAWeek(4);
                ArrayList<Exercise> day5A = new ArrayList<>();
                day5A.add(new Exercise("Overhead Press", 1, 5, 65));
                day5A.add(new Exercise("Overhead Press", 1, 5, 75));
                day5A.add(new Exercise("Overhead Press", 1, 5, 85));
                day5A.add(new Exercise("Dips", 5, 10, 50));
                day5A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day5B = new ArrayList<>();
                day5B.add(new Exercise("Deadlift", 1, 5, 65));
                day5B.add(new Exercise("Deadlift", 1, 5, 75));
                day5B.add(new Exercise("Deadlift", 1, 5, 85));
                day5B.add(new Exercise("Good Mornings", 5, 10, 50));
                day5B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day5C = new ArrayList<>();
                day5C.add(new Exercise("Bench Press", 1, 5, 65));
                day5C.add(new Exercise("Bench Press", 1, 5, 75));
                day5C.add(new Exercise("Bench Press", 1, 5, 85));
                day5C.add(new Exercise("Dumbbell Bench Press", 5, 10, 50));
                day5C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day5D = new ArrayList<>();
                day5D.add(new Exercise("Squats", 1, 5, 65));
                day5D.add(new Exercise("Squats", 1, 5, 75));
                day5D.add(new Exercise("Squats", 1, 5, 85));
                day5D.add(new Exercise("Leg Press", 5, 10, 50));
                day5D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekA = new ArrayList<>();
                weekA.add(day5A);
                weekA.add(day5B);
                weekA.add(day5C);
                weekA.add(day5D);
                ArrayList<Exercise> day3A = new ArrayList<>();
                day3A.add(new Exercise("Overhead Press", 1, 3, 70));
                day3A.add(new Exercise("Overhead Press", 1, 3, 80));
                day3A.add(new Exercise("Overhead Press", 1, 3, 90));
                day3A.add(new Exercise("Dips", 5, 10, 50));
                day3A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day3B = new ArrayList<>();
                day3B.add(new Exercise("Deadlift", 1, 3, 70));
                day3B.add(new Exercise("Deadlift", 1, 3, 80));
                day3B.add(new Exercise("Deadlift", 1, 3, 90));
                day3B.add(new Exercise("Good Mornings", 5, 10, 50));
                day3B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day3C = new ArrayList<>();
                day3C.add(new Exercise("Bench Press", 1, 3, 70));
                day3C.add(new Exercise("Bench Press", 1, 3, 80));
                day3C.add(new Exercise("Bench Press", 1, 3, 90));
                day3C.add(new Exercise("Dumbbell Bench Press", 5, 10, 50));
                day3C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day3D = new ArrayList<>();
                day3D.add(new Exercise("Squats", 1, 3, 70));
                day3D.add(new Exercise("Squats", 1, 3, 80));
                day3D.add(new Exercise("Squats", 1, 3, 90));
                day3D.add(new Exercise("Leg Press", 5, 10, 50));
                day3D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekB = new ArrayList<>();
                weekB.add(day3A);
                weekB.add(day3B);
                weekB.add(day3C);
                weekB.add(day3D);
                ArrayList<Exercise> day1A = new ArrayList<>();
                day1A.add(new Exercise("Overhead Press", 1, 5, 75));
                day1A.add(new Exercise("Overhead Press", 1, 3, 85));
                day1A.add(new Exercise("Overhead Press", 1, 1, 95));
                day1A.add(new Exercise("Dips", 5, 10, 50));
                day1A.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> day1B = new ArrayList<>();
                day1B.add(new Exercise("Deadlift", 1, 5, 75));
                day1B.add(new Exercise("Deadlift", 1, 3, 85));
                day1B.add(new Exercise("Deadlift", 1, 1, 95));
                day1B.add(new Exercise("Good Mornings", 5, 10, 50));
                day1B.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> day1C = new ArrayList<>();
                day1C.add(new Exercise("Bench Press", 1, 5, 75));
                day1C.add(new Exercise("Bench Press", 1, 3, 85));
                day1C.add(new Exercise("Bench Press", 1, 1, 95));
                day1C.add(new Exercise("Dumbbell Bench Press", 5, 10, 50));
                day1C.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> day1D = new ArrayList<>();
                day1D.add(new Exercise("Squats", 1, 5, 75));
                day1D.add(new Exercise("Squats", 1, 3, 85));
                day1D.add(new Exercise("Squats", 1, 1, 95));
                day1D.add(new Exercise("Leg Press", 5, 10, 50));
                day1D.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekC = new ArrayList<>();
                weekC.add(day1A);
                weekC.add(day1B);
                weekC.add(day1C);
                weekC.add(day1D);
                ArrayList<Exercise> dayLightA = new ArrayList<>();
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 40));
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 50));
                dayLightA.add(new Exercise("Overhead Press", 1, 5, 60));
                dayLightA.add(new Exercise("Dips", 5, 10, 50));
                dayLightA.add(new Exercise("Chinups", 5, 10, 60));
                ArrayList<Exercise> dayLightB = new ArrayList<>();
                dayLightB.add(new Exercise("Deadlift", 1, 5, 40));
                dayLightB.add(new Exercise("Deadlift", 1, 5, 50));
                dayLightB.add(new Exercise("Deadlift", 1, 5, 60));
                dayLightB.add(new Exercise("Good Mornings", 5, 10, 50));
                dayLightB.add(new Exercise("Hanging Leg Raises", 5, 10, 60));
                ArrayList<Exercise> dayLightC = new ArrayList<>();
                dayLightC.add(new Exercise("Bench Press", 1, 5, 40));
                dayLightC.add(new Exercise("Bench Press", 1, 5, 50));
                dayLightC.add(new Exercise("Bench Press", 1, 5, 60));
                dayLightC.add(new Exercise("Dumbbell Bench Press", 5, 10, 50));
                dayLightC.add(new Exercise("Barbell Rows", 5, 10, 60));
                ArrayList<Exercise> dayLightD = new ArrayList<>();
                dayLightD.add(new Exercise("Squats", 1, 5, 40));
                dayLightD.add(new Exercise("Squats", 1, 5, 50));
                dayLightD.add(new Exercise("Squats", 1, 5, 60));
                dayLightD.add(new Exercise("Leg Press", 5, 10, 50));
                dayLightD.add(new Exercise("Leg Curls", 5, 10, 60));
                ArrayList<ArrayList<Exercise>> weekD = new ArrayList<>();
                weekD.add(dayLightA);
                weekD.add(dayLightB);
                weekD.add(dayLightC);
                weekD.add(dayLightD);

                mExercises.set(0, weekA);
                mExercises.set(1, weekB);
                mExercises.set(2, weekC);
                mExercises.set(3, weekD);
                break;
            case 4:
                // Smolov Jr.
                Log.d("Routine " + getTitle(), "Setting up Smolov Jr.");
                setCycleLength(4);
                setDaysAWeek(4);
                ArrayList<Exercise> dayA = new ArrayList<>();
                dayA.add(new Exercise("Squats", 6, 6, 70));
                ArrayList<Exercise> dayB = new ArrayList<>();
                dayB.add(new Exercise("Squats", 7, 5, 75));
                ArrayList<Exercise> dayC = new ArrayList<>();
                dayC.add(new Exercise("Squats", 8, 4, 80));
                ArrayList<Exercise> dayD = new ArrayList<>();
                dayD.add(new Exercise("Squats", 10, 3, 85));
                ArrayList<ArrayList<Exercise>> week = new ArrayList<>();
                week.add(dayA);
                week.add(dayB);
                week.add(dayC);
                week.add(dayD);

                for (int i = 0; i < 4; i++)
                    mExercises.set(i, week);
                break;
        }
    }
}
