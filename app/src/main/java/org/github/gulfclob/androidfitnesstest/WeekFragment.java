package org.github.gulfclob.androidfitnesstest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class WeekFragment extends Fragment {
    private static final String ARG_WEEK_ID = "week_id";
    private ArrayList<ArrayList<Exercise>> mWeek;
    private TextView mWeekLabel;

    public static WeekFragment newInstance(UUID routineId, int position) {
        Bundle args = new Bundle();
        args.putSerializable(RoutineFragment.ARG_ROUTINE_ID, routineId);
        args.putSerializable(ARG_WEEK_ID, position);

        WeekFragment fragment = new WeekFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID routineId = (UUID) getArguments().getSerializable(RoutineFragment.ARG_ROUTINE_ID);
        int weekNum = (Integer) getArguments().getSerializable(ARG_WEEK_ID);
        mWeek = RoutineJournal.get(getActivity()).getRoutine(routineId)
                .getExercises().get(weekNum);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_routine_page, container, false);

        mWeekLabel = (TextView) v.findViewById(R.id.routine_week_label);
        mWeekLabel.setText("Week " + getArguments().getSerializable(ARG_WEEK_ID));

        return v;
    }

}
