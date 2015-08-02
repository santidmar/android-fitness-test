package com.bignerdranch.android.fitnesstestapp;

// This is a utility class storing helpful formulas
public class FitnessCalc {
    /**
     * Function to find TDEE given BMR and activity level.
     * TODO: Cite and verify this function's accuracy
     * I forgot where I got this data from. According to this function,
     * TDEE is simply BMR multiplied by a coefficient correspondent to
     * activity level.
     *
     * @param bmr Basal Metabolic Rate. Typically found through calcBmr()
     * @param activityLevel int to describe activity level. int corresponds
     *                      to index of the Activity Level spinner. Higher
     *                      value means higher activity level
     * @return Client's TDEE.
     */
    public static int calcTdee(int bmr, int activityLevel) {
        switch (activityLevel) {
            case 1:
                return (int)(bmr * 1.2);
            case 2:
                return (int)(bmr * 1.375);
            case 3:
                return (int)(bmr * 1.55);
            case 4:
                return (int)(bmr * 1.725);
            case 5:
                return (int)(bmr * 1.9);
            default:
                return bmr;
        }
    }

    /**
     * Mifflin-St Jeor formula to calculate Basal Metabolic Rate (BMR).
     *
     * @param weight, bodyweight in kg
     * @param height, height in cm
     * @param age, age in years
     * @param isMale, gender boolean. True if male, false if not
     * @return Client's BMR in Calories
     */
    public static int calcBmr(double weight, double height, double age, boolean isMale) {
        return (int)((10f * weight) + (6.25 * height) - (5f * age) + ((isMale) ? 5f
                                                                               : -161f));
    }

    /**
     * Formula to calculate one rep max.
     * TODO: Replace this formula
     * Formula is inaccurate after 20 reps.
     * Also, divide by 0 exception at 37 reps.
     *
     * @param weight weight lifted. either lb or kg
     * @param reps the number of repetitions
     * @return one rep max
     */
    public static int calcOneRm(int weight, int reps) {
        return (36 * weight) / (37 - reps);
    }

    /**
     * This function is used to create Strength Standards.
     * The formula to calculate standards was created by
     * finding polynomials that correlate with each standard
     * from the standards in the back of Rippetoe's Starting
     * Strength book.
     *
     * The formula will return pounds by default
     *
     * @param bodyWeight, Body Weight in lb.
     * @param isMale, True if gender is male, false otherwise
     * @param isMetric True if metric output desired, false otherwise
     * @return strStd, a 2D Array. It is important to note,
     *         the first array layer is of size 4; 0 means
     *         overhead press, 1 means Deadlift, 2 means
     *         benchpress, 3 means squat. The sub array is
     *         size 5, and indicates the standard category.
     *         0 is untrained, 4 is elite, etc.
     */
    public static int[][] calcStrStd(int bodyWeight, boolean isMale, boolean isMetric) {
        int[][] strStd = new int[4][5];
        double conversionFactor = (isMetric) ? 2.0462262
                                             : 1.0;
        bodyWeight = (int) (bodyWeight * conversionFactor);
        if (isMale) {
            // OHP Setup
            strStd[0][0] = (int) (polynomial(-0.00131214, 0.78219762,
                    -18.83132153, bodyWeight) / conversionFactor);
            strStd[0][1] = (int) (polynomial(-0.00182165, 1.07851433,
                    -26.60583941, bodyWeight) / conversionFactor);
            strStd[0][2] = (int) (polynomial(-0.00236410, 1.38604913,
                    -36.1883086, bodyWeight) / conversionFactor);
            strStd[0][3] = (int) (polynomial(-0.00273760, 1.61797647,
                    -40.69239817, bodyWeight) / conversionFactor);
            strStd[0][4] = (int) (polynomial(-0.00430298, 2.61306511,
                    -117.6587797, bodyWeight) / conversionFactor);
            // Deadlift Setup
            strStd[1][0] = (int) (polynomial(-0.00240945, 1.43771369,
                    -34.81781247, bodyWeight) / conversionFactor);
            strStd[1][1] = (int) (polynomial(-0.00454089, 2.69646124,
                    -67.77253236, bodyWeight) / conversionFactor);
            strStd[1][2] = (int) (polynomial(-0.00538799, 3.16526546,
                    -84.35656602, bodyWeight) / conversionFactor);
            strStd[1][3] = (int) (polynomial(-0.00710564, 4.04155785,
                    -66.39872786, bodyWeight) / conversionFactor);
            strStd[1][4] = (int) (polynomial(-0.00846172, 4.66444984,
                    -28.40578385, bodyWeight) / conversionFactor);
            // Bench Setup
            strStd[2][0] = (int) (polynomial(-0.00207688, 1.23538497,
                    -28.78633203, bodyWeight) / conversionFactor);
            strStd[2][1] = (int) (polynomial(-0.00274544, 1.62494016,
                    -41.62033546, bodyWeight) / conversionFactor);
            strStd[2][2] = (int) (polynomial(-0.00343555, 2.02690168,
                    -54.95841218, bodyWeight) / conversionFactor);
            strStd[2][3] = (int) (polynomial(-0.00457452, 2.70656518,
                    -68.33822471, bodyWeight) / conversionFactor);
            strStd[2][4] = (int) (polynomial(-0.00575770, 3.40650129,
                    -88.80729297, bodyWeight) / conversionFactor);
            // Squat Setup
            strStd[3][0] = (int) (polynomial(-0.00194073, 1.15378267,
                    -27.75454670, bodyWeight) / conversionFactor);
            strStd[3][1] = (int) (polynomial(-0.00363756, 2.15835200,
                    -53.70171105, bodyWeight) / conversionFactor);
            strStd[3][2] = (int) (polynomial(-0.00462702, 2.71699714,
                    -73.19340959, bodyWeight) / conversionFactor);
            strStd[3][3] = (int) (polynomial(-0.00613536, 3.62785267,
                    -92.28727998, bodyWeight) / conversionFactor);
            strStd[3][4] = (int) (polynomial(-0.00747067, 4.46842177,
                    -89.76608883, bodyWeight) / conversionFactor);
        } else {
            // OHP Setup
            strStd[0][0] = (int) (polynomial(-0.00036929, 0.34397179,
                    1.18340679, bodyWeight) / conversionFactor);
            strStd[0][1] = (int) (polynomial(-0.00048920, 0.46271897,
                    2.34494354, bodyWeight) / conversionFactor);
            strStd[0][2] = (int) (polynomial(-0.00190059, 0.90805682,
                    -21.03549835, bodyWeight) / conversionFactor);
            strStd[0][3] = (int) (polynomial(-0.00074753, 0.72506314,
                    2.89892526, bodyWeight) / conversionFactor);
            strStd[0][4] = (int) (polynomial(-0.00124898, 1.02334245,
                    -2.87106671, bodyWeight) / conversionFactor);
            // Deadlift Setup
            strStd[1][0] = (int) (polynomial(-0.00072814, 0.64522574,
                    1.47753428, bodyWeight) / conversionFactor);
            strStd[1][1] = (int) (polynomial(-0.00136555, 1.19819238,
                    2.57182021, bodyWeight) / conversionFactor);
            strStd[1][2] = (int) (polynomial(-0.00189872, 1.49121556,
                    -4.02200319, bodyWeight) / conversionFactor);
            strStd[1][3] = (int) (polynomial(-0.00375267, 2.18594944,
                    -0.86839125, bodyWeight) / conversionFactor);
            strStd[1][4] = (int) (polynomial(-0.00154564, 1.62621094,
                    -87.80545556, bodyWeight) / conversionFactor);
            // Bench Setup
            strStd[2][0] = (int) (polynomial(-0.00047331, 0.51600651,
                    3.86987891, bodyWeight) / conversionFactor);
            strStd[2][1] = (int) (polynomial(-0.00067801, 0.70958143,
                    0.51530902, bodyWeight) / conversionFactor);
            strStd[2][2] = (int) (polynomial(-0.00100364, 0.85418414,
                    0.10257741, bodyWeight) / conversionFactor);
            strStd[2][3] = (int) (polynomial(-0.00133012, 1.11378412,
                    -0.98603779, bodyWeight) / conversionFactor);
            strStd[2][4] = (int) (polynomial(-0.00191910, 1.45635903,
                    -7.81788658, bodyWeight) / conversionFactor);
            // Squat Setup
            strStd[3][0] = (int) (polynomial(-0.00042104, 0.46546315,
                    4.98754046, bodyWeight) / conversionFactor);
            strStd[3][1] = (int) (polynomial(-0.00113966, 0.97474487,
                    0.90016512, bodyWeight) / conversionFactor);
            strStd[3][2] = (int) (polynomial(-0.00133373, 1.13947946,
                    0.70523230, bodyWeight) / conversionFactor);
            strStd[3][3] = (int) (polynomial(-0.00192773, 1.54567833,
                    -1.72870885, bodyWeight) / conversionFactor);
            strStd[3][4] = (int) (polynomial(-0.00262646, 2.01662637,
                    -8.62772994, bodyWeight) / conversionFactor);
        }
        return strStd;
    }

    // Helper function used in calculating Standards
    private static int polynomial(double a, double b, double c, int x) {
        return (int) ((a * x * x) + (b * x) + c);
    }
}
