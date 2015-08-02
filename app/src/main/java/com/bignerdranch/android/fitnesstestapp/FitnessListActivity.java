package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;

public class FitnessListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FitnessListFragment();
    }
}
