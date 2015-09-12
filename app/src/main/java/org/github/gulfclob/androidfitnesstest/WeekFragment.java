package org.github.gulfclob.androidfitnesstest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeekFragment extends Fragment {
    private static final String ARG_WEEK_ID = "week_id";
    private ArrayList<ArrayList<Exercise>> mWeek;
    private TextView mWeekLabel;
    private RecyclerView mWorkoutRecyclerView;
    private WorkoutAdapter mWorkoutAdapter;

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

        mWorkoutRecyclerView = (RecyclerView) v.findViewById(R.id.workout_recycler_view);
        mWorkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWorkoutAdapter = new WorkoutAdapter(mWeek);
        mWorkoutRecyclerView.setAdapter(mWorkoutAdapter);

        return v;
    }

    private class WorkoutHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public Button mExerciseButton;
        public RecyclerView mExerciseRecyclerView;

        private ExerciseAdapter mExerciseAdapter;
        private List<Exercise> mWorkout;

        public WorkoutHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView
                    .findViewById(R.id.routine_workout_label);
            mExerciseButton = (Button) itemView
                    .findViewById(R.id.routine_new_exercise_button);
            mExerciseRecyclerView = (RecyclerView)
                    itemView.findViewById(R.id.workout_exercise_list);
        }

        public void bindWorkout(List<Exercise> workout) {
            mWorkout = workout;
            mTitleTextView.setText("Workout " + getLayoutPosition());

            mExerciseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWorkout.add(new Exercise());
                    Log.d("WeekFragment", "Adding new exercise...");
                    mExerciseAdapter.notifyDataSetChanged();
                }
            });

            mExerciseRecyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity(),
                    getResources().getConfiguration().orientation, false));
            mExerciseAdapter = new ExerciseAdapter(mWorkout);
            mExerciseRecyclerView.setAdapter(mExerciseAdapter);
        }

        private class ExerciseHolder extends RecyclerView.ViewHolder {
            public EditText mTitleEditText, mSetsEditText,
                    mRepsEditText, mIntensityEditText;

            private Exercise mExercise;

            public ExerciseHolder(View itemView) {
                super(itemView);
                mTitleEditText = (EditText) itemView
                        .findViewById(R.id.exercise_title);
                mSetsEditText = (EditText) itemView
                        .findViewById(R.id.exercise_sets);
                mRepsEditText = (EditText) itemView
                        .findViewById(R.id.exercise_reps);
                mIntensityEditText = (EditText) itemView
                        .findViewById(R.id.exercise_intensity);
            }

            public void bindExercise(Exercise exercise) {
                mExercise = exercise;

                mTitleEditText.setText(mExercise.getTitle());
                mSetsEditText.setText((mExercise.getSets() == 0) ? ""
                        : Integer.toString(mExercise.getSets()));
                mRepsEditText.setText((mExercise.getReps() == 0) ? ""
                        : Integer.toString(mExercise.getReps()));
                mIntensityEditText.setText((mExercise.getIntensity() == 0) ? ""
                        : Integer.toString(mExercise.getIntensity()));

                mTitleEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mExercise.setTitle(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                mSetsEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals(""))
                            mExercise.setSets(Integer.parseInt(s.toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                mRepsEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals(""))
                            mExercise.setReps(Integer.parseInt(s.toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                mIntensityEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (!s.toString().equals(""))
                            mExercise.setIntensity(Integer.parseInt(s.toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        }

        private class ExerciseAdapter extends RecyclerView.Adapter<ExerciseHolder> {
            private List<Exercise> mExercises;

            public ExerciseAdapter(List<Exercise> exercises) { mExercises = exercises; }

            @Override
            public ExerciseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater
                        .inflate(R.layout.list_item_exercise, parent, false);
                return new ExerciseHolder(view);
            }

            @Override
            public void onBindViewHolder(ExerciseHolder holder, int position) {
                Exercise exercise = mExercises.get(position);
                holder.bindExercise(exercise);
            }

            @Override
            public int getItemCount() { return mExercises.size(); }
        }
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutHolder> {
        private List<ArrayList<Exercise>> mWorkouts;

        public WorkoutAdapter(List<ArrayList<Exercise>> workouts) {
            mWorkouts = workouts;
        }

        @Override
        public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_workout, parent, false);
            return new WorkoutHolder(view);
        }

        @Override
        public void onBindViewHolder(WorkoutHolder holder, int position) {
            List<Exercise> workout = mWorkouts.get(position);
            holder.bindWorkout(workout);
        }

        @Override
        public int getItemCount() {
            return mWorkouts.size();
        }
    }
}
