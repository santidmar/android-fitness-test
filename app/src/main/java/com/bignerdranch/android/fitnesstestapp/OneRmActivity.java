package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;

public class OneRmActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new OneRmFragment();
    }
}