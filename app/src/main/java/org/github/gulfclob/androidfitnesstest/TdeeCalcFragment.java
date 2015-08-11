package org.github.gulfclob.androidfitnesstest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/* TODO:
 * Replace progress TextViews with EditTexts that
 * allow for macros to be inputted precisely
 *
 * Clean, optimize Slider algorithm
 */

public class TdeeCalcFragment extends Fragment {
    private TextView mTdeeDisplay, mCarbDisplay,
            mFatDisplay, mProteinDisplay, mCarbProgress, mFatProgress,
            mProteinProgress;
    private EditText mWeightField, mHeightField, mAgeField;
    private Spinner mActivitySpinner;
    private RadioButton mFemaleButton;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tdee_calc, container, false);

        mTdeeDisplay = (TextView)view.findViewById(R.id.tdee_display);
        mCarbDisplay = (TextView)view.findViewById(R.id.carb_display);
        mFatDisplay = (TextView)view.findViewById(R.id.fat_display);
        mProteinDisplay = (TextView)view.findViewById(R.id.protein_display);
        mWeightField = (EditText)view.findViewById(R.id.weight_field);
        mHeightField = (EditText)view.findViewById(R.id.height_field);
        mAgeField = (EditText)view.findViewById(R.id.age_field);
        mFemaleButton = (RadioButton)view.findViewById(R.id.female_button);
        mActivitySpinner = (Spinner)view.findViewById(R.id.activity_spinner);
        Button enterButton = (Button)view.findViewById(R.id.enter_button_tdee);
        enterButton.setOnClickListener(new View.OnClickListener() {
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
                int tdee = FitnessCalc.calcTdee(bmr, activityLevel);
                mTdeeDisplay.setText(Integer.toString(tdee));

                String carbString = mCarbProgress.getText().toString();
                int carbs = Integer.parseInt(carbString.substring(0, carbString.length() - 1));
                mCarbDisplay.setText(Integer.toString((carbs * tdee) / 400) + "g");
                String fatString = mFatProgress.getText().toString();
                int fat = Integer.parseInt(fatString.substring(0, fatString.length() - 1));
                mFatDisplay.setText(Integer.toString((fat * tdee) / 900) + "g");
                String proteinString = mProteinProgress.getText().toString();
                int protein = Integer.parseInt(proteinString.substring(0, proteinString.length() - 1));
                mProteinDisplay.setText(Integer.toString((protein * tdee) / 400) + "g");
            }
        });

        SeekBar carbBar = (SeekBar)view.findViewById(R.id.carb_seekbar);
        mCarbProgress = (TextView)view.findViewById(R.id.carb_label);
        SeekBar fatBar = (SeekBar)view.findViewById(R.id.fat_seekbar);
        mFatProgress = (TextView)view.findViewById(R.id.fat_label);
        SeekBar proteinBar = (SeekBar)view.findViewById(R.id.protein_seekbar);
        mProteinProgress = (TextView)view.findViewById(R.id.protein_label);

        carbBar.setOnSeekBarChangeListener(createSeekListener(
                mCarbProgress, fatBar, proteinBar));
        fatBar.setOnSeekBarChangeListener(createSeekListener(
                mFatProgress, proteinBar, carbBar));
        proteinBar.setOnSeekBarChangeListener(createSeekListener(
                mProteinProgress, carbBar, fatBar));
        return view;
    }

    /**
     * This function will create an OnSeekBarChangeListener
     * that will update the SeekBar's TextView, and it will
     * adjust the two other SeekBars in order to insure
     * each SeekBar will add up to 100%
     * 
     * Algorithm will check if the bars add to 100%. If so,
     * it will return. If not, it will find the amount of
     * progress that has changed, and distributes it to the
     * other two SeekBars
     * 
     * @param textView The corresponding TextView to the
     *                 SeekBar that has been changed
     * @param seekBarA One SeekBar to process
     * @param seekBarB Other SeekBar to process
     * @return new OnSeekBarChangeListener
     */
    private SeekBar.OnSeekBarChangeListener createSeekListener(final TextView textView,
            final SeekBar seekBarA, final SeekBar seekBarB) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(Integer.toString(progress) + "%");
                if (fromUser) {
                    int progressA = seekBarA.getProgress();
                    int progressB = seekBarB.getProgress();
                    if (progressA + progressB + progress == 100)
                        return;

                    int remainingProgress = 100 - progress;
                    int newProgressA = progressA, newProgressB = progressB;
                    int remainder = Math.abs(remainingProgress - (progressA + progressB));

                    if (progressA + progressB > remainingProgress) { // macro total has decreased
                        newProgressA = progressA - ((remainder / 2) + ((progressA > progressB) ? remainder % 2
                                                                                               : 0));
                        newProgressB = progressB - ((remainder / 2) + ((progressA <= progressB) ? remainder % 2
                                                                                                : 0));
                        if (newProgressB < 0)
                            newProgressA -= newProgressB * -1;
                        else if (newProgressA < 0)
                            newProgressB -= newProgressA * -1;
                    } else if (progressA + progressB < remainingProgress) { // macro total has increased
                        newProgressA = progressA + ((remainder / 2) + ((progressA > progressB) ? remainder % 2
                                : 0));
                        newProgressB = progressB + ((remainder / 2) + ((progressA <= progressB) ? remainder % 2
                                : 0));

                        if (newProgressB < 0)
                            newProgressA += newProgressB * -1;
                        else if (newProgressA < 0)
                            newProgressB += newProgressA * -1;
                    }
                    seekBarB.setProgress(newProgressB);
                    seekBarA.setProgress(newProgressA);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }
}