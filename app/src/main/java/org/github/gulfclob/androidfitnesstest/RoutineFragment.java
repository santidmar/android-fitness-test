package org.github.gulfclob.androidfitnesstest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

public class RoutineFragment extends Fragment {
    public static final String ARG_ROUTINE_ID = "routine_id";
    private Routine mRoutine;
    private ViewPager mViewPager;
    private EditText mTitleField, mCycleLengthField,
            mDaysWeekField;
    private Spinner mTemplateSpinner;

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

        mTemplateSpinner = (Spinner)v.findViewById(R.id.routine_template_spinner);
        mTemplateSpinner.setSelection(mRoutine.getTemplateId());
        mTemplateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // new SetTemplateTask().execute(position);
                Log.d("RoutineFragment", "onItemSelected called at position " + position);
                mRoutine.setTemplateId(position);
                if (position != 0) {
                    /* Here, we refresh the View Pager by simply giving it a new adapter.
                       This is pretty bad practice and should be avoided in the final build.
                     */
                    mViewPager.setAdapter(new RoutinePagerAdapter(getActivity()
                            .getSupportFragmentManager(), mRoutine));
                    mCycleLengthField.setText(Integer.toString(mRoutine.getCycleLength()));
                    mDaysWeekField.setText(Integer.toString(mRoutine.getDaysAWeek()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                    int cycleLength = Integer.parseInt(s.toString());
                    if (cycleLength != mRoutine.getCycleLength()) {
                        mRoutine.setCycleLength(cycleLength);
                        mViewPager.getAdapter().notifyDataSetChanged();
                    }
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
                if (!s.toString().equals("")) {
                    int daysAWeek = Integer.parseInt(s.toString());
                    if (daysAWeek != mRoutine.getDaysAWeek()) {
                        mRoutine.setDaysAWeek(Integer.parseInt(s.toString()));
                        mViewPager.getAdapter().notifyDataSetChanged();
                    }
                }
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
    /*
    private class SetTemplateTask extends AsyncTask<Integer, Void, Void> {
        protected Void doInBackground(Integer... position) {
            mRoutine.setTemplateId(position[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            mViewPager.getAdapter().notifyDataSetChanged();
            mCycleLengthField.setText(Integer.toString(mRoutine.getCycleLength()));
            mDaysWeekField.setText(Integer.toString(mRoutine.getDaysAWeek()));
        }
    }*/
}
