package org.github.gulfclob.androidfitnesstest;

public class Exercise {
    private String mTitle;
    private int mSets;
    private int mReps;
    private double mIntensity;

    public Exercise(String title, int sets, int reps,
                    double intensity) {
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

    public int getReps() {
        return mReps;
    }

    public void setReps(int reps) {
        mReps = reps;
    }

    public double getIntensity() {
        return mIntensity;
    }

    public void setIntensity(double intensity) {
        mIntensity = intensity;
    }
}
