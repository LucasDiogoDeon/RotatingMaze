package com.gmail.lucasddeon.rotatingmaze.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 *
 */
public class UserProfilePreferences {

    /**
     *
     */
    protected static final class Consts {
        public final static String _NAME = "user_profile";

        public final static String CURRENT_LEVEL = "current_level";
        public final static String FAILED_ATTEMPTS = "failed_attempts";
        public final static String SUCCESSFUL_ATTEMPTS = "successful_attempts";
        public final static String TOTAL_ATTEMPTS = "total_attempts";
        public final static String TOTAL_TIME = "total_time";

        public static final String CHOSEN_LEVEL = "chosen_level";
    }

    /**
     *
     */
    protected static final class Defaults {
        public final static int CURRENT_LEVEL = 0;
        public final static int FAILED_ATTEMPTS = 0;
        public final static int SUCCESSFUL_ATTEMPTS = 0;
        public final static int TOTAL_ATTEMPTS = 0;
        public final static int TOTAL_TIME = 0;

        public static final int CHOSEN_LEVEL = 1;
    }

    protected static Preferences preferences;
    static {
        preferences = Gdx.app.getPreferences(Consts._NAME);
    }


    public static void save() {
        preferences.flush();
    }

    public static void clear() {
        preferences.clear();
    }


    public static boolean contains(String key) {
        return preferences.contains(key);
    }


    public static int getCurrentLevel() {
        return preferences.getInteger(Consts.CURRENT_LEVEL);
    }

    public static void setCurrentLevel(int value) {
        preferences.putInteger(Consts.CURRENT_LEVEL, value);
    }


    public static int getFailedAttempts() {
        return preferences.getInteger(Consts.FAILED_ATTEMPTS);
    }

    public static void setFailedAttempts(int value) {
        preferences.putInteger(Consts.FAILED_ATTEMPTS, value);
    }

    public static void incFailedAttempts() {
        setFailedAttempts(getFailedAttempts() + 1);
    }


    public static int getSuccessfulAttempts() {
        return preferences.getInteger(Consts.SUCCESSFUL_ATTEMPTS);
    }

    public static void setSuccessfulAttempts(int value) {
        preferences.putInteger(Consts.SUCCESSFUL_ATTEMPTS, value);
    }

    public static void incSuccessfulAttempts() {
        setSuccessfulAttempts(getSuccessfulAttempts() + 1);
    }


    public static int getTotalAttempts() {
        return preferences.getInteger(Consts.TOTAL_ATTEMPTS);
    }

    public static void setTotalAttempts(int value) {
        preferences.putInteger(Consts.TOTAL_ATTEMPTS, value);
    }

    public static void incTotalAttempts() {
        setTotalAttempts(getTotalAttempts() + 1);
    }


    public static int getTotalTime() {
        return preferences.getInteger(Consts.TOTAL_TIME);
    }

    public static void setTotalTime(int value) {
        preferences.putInteger(Consts.TOTAL_TIME, value);
    }

    public static void incTotalTime(float value) {
        setTotalTime(getTotalTime() + (int) value);
    }


    public static int getChosenLevel() {
        return preferences.getInteger(Consts.CHOSEN_LEVEL);
    }

    public static void setChosenLevel(int chosenLevel) {
        preferences.putInteger(Consts.CHOSEN_LEVEL, chosenLevel);
    }


    public static void initialize() {

        if (!contains(Consts.CURRENT_LEVEL)) {
            setCurrentLevel(Defaults.CURRENT_LEVEL);
        }

        if (!contains(Consts.FAILED_ATTEMPTS)) {
            setFailedAttempts(Defaults.FAILED_ATTEMPTS);
        }

        if (!contains(Consts.SUCCESSFUL_ATTEMPTS)) {
            setSuccessfulAttempts(Defaults.SUCCESSFUL_ATTEMPTS);
        }

        if (!contains(Consts.TOTAL_ATTEMPTS)) {
            setTotalAttempts(Defaults.TOTAL_ATTEMPTS);
        }

        if (!contains(Consts.TOTAL_TIME)) {
            setTotalTime(Defaults.TOTAL_TIME);
        }


        if (!contains(Consts.CHOSEN_LEVEL)) {
            setChosenLevel(Defaults.CHOSEN_LEVEL);
        }

        save();

    }

}
