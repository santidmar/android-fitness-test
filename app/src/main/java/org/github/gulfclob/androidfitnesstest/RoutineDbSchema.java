package org.github.gulfclob.androidfitnesstest;

public class RoutineDbSchema {
    public static final class RoutineTable {
        public static final String NAME = "routines";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TEMPLATE_ID = "template_id";
            public static final String DAYS_A_WEEK = "days_a_week";
            public static final String CYCLE_LENGTH = "cycle_length";
            public static final String EXERCISES = "exercises";
        }
    }
}
