package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;

public class StrStdActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StrStdFragment();
    }
}
