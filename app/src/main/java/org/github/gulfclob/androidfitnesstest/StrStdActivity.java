package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;

public class StrStdActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StrStdFragment();
    }
}
