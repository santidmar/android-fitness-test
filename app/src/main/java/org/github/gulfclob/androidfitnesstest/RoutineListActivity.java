package org.github.gulfclob.androidfitnesstest;

import android.support.v4.app.Fragment;

public class RoutineListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RoutineListFragment();
    }
}
