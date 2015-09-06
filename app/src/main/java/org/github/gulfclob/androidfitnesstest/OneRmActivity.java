package org.github.gulfclob.androidfitnesstest;

import android.support.v4.app.Fragment;

public class OneRmActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new OneRmFragment();
    }
}