package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;

public class RoutineListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RoutineListFragment();
    }
}
