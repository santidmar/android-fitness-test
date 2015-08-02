package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OneRmFragment extends Fragment {
    private static final String TAG = "OneRmFragment";
    private TextView mRmDisplay;
    private EditText mWeightField, mRepField;
    private Button mEnterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_rm, container, false);

        mRmDisplay = (TextView)view.findViewById(R.id.rm_display);

        mWeightField = (EditText)view.findViewById(R.id.weight_edit);
        mRepField = (EditText)view.findViewById(R.id.reps_edit);

        mEnterButton = (Button)view.findViewById(R.id.enter_button);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = mWeightField.getText().toString();
                String reps = mRepField.getText().toString();

                if (weight.equals("") || reps.equals(""))
                    return;

                mRmDisplay.setText(Integer.toString(FitnessCalc
                        .calcOneRm(Integer.parseInt(weight), Integer.parseInt(reps))));
            }
        });

        return view;
    }
}
