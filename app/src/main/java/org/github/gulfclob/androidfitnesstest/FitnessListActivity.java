package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;

public class FitnessListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FitnessListFragment();
    }
}
