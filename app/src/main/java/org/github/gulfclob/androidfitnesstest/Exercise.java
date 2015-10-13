package org.github.gulfclob.androidfitnesstest;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String mTitle;
    private int mSets;
    private int mReps;
    private int mIntensity;

    public Exercise() {
        mTitle = "";
        mSets = 0;
        mReps = 0;
        mIntensity = 0;
    }

    public Exercise(String title, int sets, int reps, int intensity) {
        mTitle = title;
        mSets = sets;
        mReps = reps;
        mIntensity = intensity;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getSets() {
        return mSets;
    }

    public void setSets(int sets) {
        mSets = sets;
    }

    public int getReps() { return mReps; }

    public void setReps(int reps) {
        mReps = reps;
    }

    public int getIntensity() {
        return mIntensity;
    }

    public void setIntensity(int intensity) {
        mIntensity = intensity;
    }
}
