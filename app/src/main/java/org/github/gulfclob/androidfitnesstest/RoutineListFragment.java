package org.github.gulfclob.androidfitnesstest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RoutineListFragment extends Fragment {
    private RecyclerView mRoutineRecyclerView;
    private RoutineAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine_list, container, false);

        mRoutineRecyclerView = (RecyclerView) view
                .findViewById(R.id.routine_recycler_view);
        mRoutineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_routine_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_routine:
                Routine routine = new Routine();
                RoutineJournal.get(getActivity()).addRoutine(routine);
                Intent intent = RoutineActivity
                        .newIntent(getActivity(), routine.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        RoutineJournal routineJournal = RoutineJournal.get(getActivity());
        List<Routine> routines = routineJournal.getRoutines();

        if (mAdapter == null) {
            mAdapter = new RoutineAdapter(routines);
            mRoutineRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setRoutines(routines);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class RoutineHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        public TextView mTitleTextView, mDaysTextView,
                mLengthTextView;

        private Routine mRoutine;

        public RoutineHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView
                    .findViewById(R.id.list_item_routine_title_text_view);
            mDaysTextView = (TextView) itemView
                    .findViewById(R.id.list_item_routine_days_text_view);
            mLengthTextView = (TextView) itemView
                    .findViewById(R.id.list_item_routine_length_text_view);
        }

        public void bindRoutine(Routine routine) {
            mRoutine = routine;
            mTitleTextView.setText(mRoutine.getTitle());
            mDaysTextView.setText("Days a Week: " +
                    Integer.toString(mRoutine.getDaysAWeek()));
            mLengthTextView.setText("Cycle Length: " +
                    Integer.toString(mRoutine.getCycleLength())
                    + " weeks");
        }

        @Override
        public void onClick(View v) {
            Intent intent = RoutineActivity.newIntent(getActivity(), mRoutine.getId());
            startActivity(intent);
        }
    }

    private class RoutineAdapter extends RecyclerView.Adapter<RoutineHolder> {
        private List<Routine> mRoutines;

        public RoutineAdapter(List<Routine> routines) {
            mRoutines = routines;
        }

        @Override
        public RoutineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_routine, parent, false);
            return new RoutineHolder(view);
        }

        @Override
        public void onBindViewHolder(RoutineHolder holder, int position) {
            Routine routine = mRoutines.get(position);
            holder.bindRoutine(routine);
        }

        @Override
        public int getItemCount() {
            return mRoutines.size();
        }

        public void setRoutines(List<Routine> routines) {
            mRoutines = routines;
        }
    }
}
