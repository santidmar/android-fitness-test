package org.github.gulfclob.androidfitnesstest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

public class RoutineFragment extends Fragment {
    public static final String ARG_ROUTINE_ID = "routine_id";
    private Routine mRoutine;
    private ViewPager mViewPager;
    private EditText mTitleField, mCycleLengthField,
            mDaysWeekField;

    public static RoutineFragment newInstance(UUID routineId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ROUTINE_ID, routineId);

        RoutineFragment fragment = new RoutineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID routineId = (UUID) getArguments().getSerializable(ARG_ROUTINE_ID);
        mRoutine = RoutineJournal.get(getActivity()).getRoutine(routineId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_routine, container, false);

        mTitleField = (EditText)v.findViewById(R.id.routine_title);
        mTitleField.setText(mRoutine.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRoutine.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCycleLengthField = (EditText)v.findViewById(R.id.cycle_length_field);
        mCycleLengthField.setText(Integer.toString(mRoutine.getCycleLength()));
        mCycleLengthField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    mRoutine.setCycleLength(Integer.parseInt(s.toString()));
                    mViewPager.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDaysWeekField = (EditText)v.findViewById(R.id.days_a_week_field);
        mDaysWeekField.setText(Integer.toString(mRoutine.getDaysAWeek()));
        mDaysWeekField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    mRoutine.setDaysAWeek(Integer.parseInt(s.toString()));
                    mViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mViewPager = (ViewPager)v.findViewById(R.id.routine_pager);
        mViewPager.setAdapter(new RoutinePagerAdapter(getActivity().getSupportFragmentManager(), mRoutine));

        return v;
    }

    public static class RoutinePagerAdapter extends FragmentPagerAdapter {
        private Routine mRoutine;

        public RoutinePagerAdapter(FragmentManager fragmentManager, Routine routine) {
            super(fragmentManager);
            mRoutine = routine;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mRoutine.getExercises().size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return WeekFragment.newInstance(mRoutine.getId(), position);
        }
    }
}
