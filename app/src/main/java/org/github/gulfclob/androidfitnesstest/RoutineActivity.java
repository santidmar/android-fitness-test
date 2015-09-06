package org.github.gulfclob.androidfitnesstest;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class RoutineActivity extends SingleFragmentActivity {
    private static final String EXTRA_ROUTINE_ID =
            "org.github.gulfclob.androidfitnesstest.routine_id";

    public static Intent newIntent(Context packageContext, UUID routineId) {
        Intent intent = new Intent(packageContext, RoutineActivity.class);
        intent.putExtra(EXTRA_ROUTINE_ID, routineId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID routineId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_ROUTINE_ID);
        return RoutineFragment.newInstance(routineId);
    }
}
