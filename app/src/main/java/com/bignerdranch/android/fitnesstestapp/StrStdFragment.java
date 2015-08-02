package com.bignerdranch.android.fitnesstestapp;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
/* TODO:
   Document everything
 */
public class StrStdFragment extends Fragment {
    private EditText mBodyWeight;
    private View mOhpDisplay, mDlDisplay, mBpDisplay,
        mSquatDisplay;
    private RadioGroup mGenderGroup;
    /* mStrStd stores Strength Standards. Indexes of the top-level array
       represent a lift. 0 is OHP, 1 is DL, BP is 2, Squat is 3. Indexes
       of the sub-arrays represent the category of the standard. 0 is
       untrained, 1 is beginner, 2 is intermediate, 3 is advanced, 4 is
       elite.                                                           */
    private int[][] mStrStd;
    // This ID Constants represent each lift's index in mStrStd
    private final int OHP_ID = 0, DL_ID = 1, BP_ID = 2,
            SQUAT_ID = 3;
    private int[] mOneRm = new int[4];

    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_str_std, container, false);

        // Setting up OHP oneRm listener
        mOhpDisplay = view.findViewById(R.id.ohp_display);
        EditText ohpWeight = (EditText)view.findViewById(R.id.ohp_weight_edit);
        EditText ohpReps = (EditText)view.findViewById(R.id.ohp_reps_edit);
        TextWatcher ohpTextWatcher = createStrTextWatcher(ohpWeight,
                ohpReps, mOhpDisplay, OHP_ID);
        ohpWeight.addTextChangedListener(ohpTextWatcher);
        ohpReps.addTextChangedListener(ohpTextWatcher);

        // Setting up DL oneRm listener
        mDlDisplay = view.findViewById(R.id.dl_display);
        EditText dlWeight = (EditText)view.findViewById(R.id.dl_weight_edit);
        EditText dlReps = (EditText)view.findViewById(R.id.dl_reps_edit);
        TextWatcher dlTextWatcher = createStrTextWatcher(dlWeight,
                dlReps, mDlDisplay, DL_ID);
        dlWeight.addTextChangedListener(dlTextWatcher);
        dlReps.addTextChangedListener(dlTextWatcher);

        // Setting up BP
        mBpDisplay = view.findViewById(R.id.bp_display);
        EditText bpWeight = (EditText)view.findViewById(R.id.bp_weight_edit);
        EditText bpReps = (EditText)view.findViewById(R.id.bp_reps_edit);
        TextWatcher bpTextWatcher = createStrTextWatcher(bpWeight,
                bpReps, mBpDisplay, BP_ID);
        bpWeight.addTextChangedListener(bpTextWatcher);
        bpReps.addTextChangedListener(bpTextWatcher);

        // Setting up Squat
        mSquatDisplay = view.findViewById(R.id.squat_display);
        EditText squatWeight = (EditText)view.findViewById(R.id.squat_weight_edit);
        EditText squatReps = (EditText)view.findViewById(R.id.squat_reps_edit);
        TextWatcher squatTextWatcher = createStrTextWatcher(squatWeight,
                squatReps, mSquatDisplay, SQUAT_ID);
        squatWeight.addTextChangedListener(squatTextWatcher);
        squatReps.addTextChangedListener(squatTextWatcher);

        // Setting up BW + Gender to Calculate Standards
        mBodyWeight = (EditText)view.findViewById(R.id.std_bodyweight_field);
        mGenderGroup = (RadioGroup)view.findViewById(R.id.gender_group);

        mGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateStrStd(mBodyWeight, mGenderGroup);
            }
        });

        mBodyWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                updateStrStd(mBodyWeight, mGenderGroup);
            }
        });

        return view;
    }

    private void updateStrStd(EditText bodyWeight, RadioGroup genderGroup) {
        String bwString = bodyWeight.getText().toString();
        // If mBodyWeight is empty, return;
        if (bwString.equals("")) return;
        RadioButton femaleButton = (RadioButton)genderGroup
                .findViewById(R.id.female_button);
        RadioButton maleButton = (RadioButton)genderGroup
                .findViewById(R.id.male_button);
        boolean isMale = maleButton.isChecked();
        // If neither are checked, return;
        if (!(isMale || femaleButton.isChecked())) return;

        mStrStd = FitnessCalc.calcStrStd(Integer.parseInt(bwString), isMale, true);

        // Store resources in a variable to avoid repeated function calls
        Resources resources = getResources();
        updateDisplay(OHP_ID, mOhpDisplay, resources);
        updateDisplay(DL_ID, mDlDisplay, resources);
        updateDisplay(BP_ID, mBpDisplay, resources);
        updateDisplay(SQUAT_ID, mSquatDisplay, resources);
    }

    private void updateDisplay(int liftId, View display, Resources resources) {
        int[] std = mStrStd[liftId];
        TextView untrainedLabel = (TextView)display.findViewById(R.id.untrained_label);
        String untrainedString = resources.getString(R.string.untrained_label);
        untrainedLabel.setText(untrainedString.substring(0, untrainedString.length() - 3)
                               + std[0] + "kg");
        TextView beginnerLabel = (TextView)display.findViewById(R.id.beginner_label);
        String beginnerString = resources.getString(R.string.beginner_label);
        beginnerLabel.setText(beginnerString.substring(0, beginnerString.length() - 3)
                + std[1] + "kg");
        TextView intermediateLabel = (TextView)display.findViewById(R.id.intermediate_label);
        String intermediateString = resources.getString(R.string.intermediate_label);
        intermediateLabel.setText(intermediateString.substring(0, intermediateString.length() - 3)
                + std[2] + "kg");
        TextView advancedLabel = (TextView)display.findViewById(R.id.advanced_label);
        String advancedString = resources.getString(R.string.advanced_label);
        advancedLabel.setText(advancedString.substring(0, advancedString.length() - 3)
                + std[3] + "kg");
        TextView eliteLabel = (TextView)display.findViewById(R.id.elite_label);
        String eliteString = resources.getString(R.string.elite_label);
        eliteLabel.setText(eliteString.substring(0, eliteString.length() - 3)
                + std[4] + "kg");

        ProgressBar progressBar = (ProgressBar) display.findViewById(R.id.std_progressbar);
        progressBar.setProgress(calcProgress(std, mOneRm[liftId]));
    }

    /**
     * Find progress for a progress bar.
     * Function will compare our one rep max to our set of
     * standards.
     * @param std the array of standards we're working with
     * @param oneRm the one rep max we're using to calculate
     *              progress
     * @return int from 0 to 100
     */
    private int calcProgress(int[] std, int oneRm) {
        if (oneRm <= std[0])
            return 0;
        else if (oneRm > std[0] && oneRm < std[1])
            return (int)((double)(oneRm - std[0])
                    / (double)(std[1] - std[0]) * 25f);
        else if (oneRm >= std[1] && oneRm < std[2])
            return (int)((double)(oneRm - std[1])
                    / (double)(std[2] - std[1]) * 25f) + 25;
        else if (oneRm >= std[2] && oneRm < std[3])
            return (int)((double)(oneRm - std[2])
                    / (double)(std[3] - std[2]) * 25f) + 50;
        else if (oneRm >= std[3] && oneRm < std[4])
            return (int)((double)(oneRm - std[3])
                    / (double)(std[4] - std[3]) * 25f) + 75;
        else
            return 100;
    }

    /**
     * This is a utility function meant to generate a
     * TextWatcher meant to update a strStd display.
     *
     * @param weightEdit, the weight EditText to get the weight from
     * @param repsEdit, the reps EditText to get the reps from
     * @return new TextWatcher, meant to
     */
    private TextWatcher createStrTextWatcher(final EditText weightEdit, final EditText repsEdit,
                                             final View display, final int liftId) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String weightString = weightEdit.getText().toString();
                String repsString = repsEdit.getText().toString();
                if (weightString.equals("") || repsString.equals("")) return;
                int weight = Integer.parseInt(weightString);
                int reps = Integer.parseInt(repsString);

                // Set One Rep Max
                mOneRm[liftId] = FitnessCalc.calcOneRm(weight, reps);
                TextView oneRmLabel = (TextView)display.findViewById(R.id.std_onermdisplay);
                oneRmLabel.setText(Integer.toString(mOneRm[liftId]) + "kg");

                // Update Display Progress Bar
                if (mStrStd != null) {
                    ProgressBar progressBar = (ProgressBar) display.findViewById(R.id.std_progressbar);
                    progressBar.setProgress(calcProgress(mStrStd[liftId], mOneRm[liftId]));
                }
            }
        };
    }

}
