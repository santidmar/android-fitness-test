package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;

public class TdeeCalcActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TdeeCalcFragment();
    }
}
