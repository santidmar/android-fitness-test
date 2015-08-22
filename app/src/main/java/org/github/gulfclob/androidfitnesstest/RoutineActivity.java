package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;

public class RoutineActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RoutineFragment();
    }
}
