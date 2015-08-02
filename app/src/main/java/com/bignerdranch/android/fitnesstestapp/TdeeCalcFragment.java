package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class TdeeCalcFragment extends Fragment {
    private static final String TAG = "TdeeCalcFragment";
    private TextView mTdeeDisplay;
    private EditText mWeightField, mHeightField, mAgeField;
    private Spinner mActivitySpinner;
    private RadioButton mFemaleButton;
    private Button mEnterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tdee_calc, container, false);

        mTdeeDisplay = (TextView)view.findViewById(R.id.tdee_display);
        mWeightField = (EditText)view.findViewById(R.id.weight_field);
        mHeightField = (EditText)view.findViewById(R.id.height_field);
        mAgeField = (EditText)view.findViewById(R.id.age_field);
        mFemaleButton = (RadioButton)view.findViewById(R.id.female_button);

        mActivitySpinner = (Spinner)view.findViewById(R.id.activity_spinner);

        mEnterButton = (Button)view.findViewById(R.id.enter_button_tdee);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = mWeightField.getText().toString();
                String height = mHeightField.getText().toString();
                String age = mAgeField.getText().toString();

                if (weight.equals("") || age.equals("") || height.equals(""))
                    return;
                // True if male/unselected, False if female
                boolean gender = !mFemaleButton.isChecked();
                int bmr = FitnessCalc.calcBmr(Double.parseDouble(weight), Double.parseDouble(height),
                        Double.parseDouble(age), gender);
                int activityLevel = mActivitySpinner.getSelectedItemPosition();
                mTdeeDisplay.setText(Integer.toString(FitnessCalc.calcTdee(bmr, activityLevel)));
            }
        });

        return view;
    }
}
