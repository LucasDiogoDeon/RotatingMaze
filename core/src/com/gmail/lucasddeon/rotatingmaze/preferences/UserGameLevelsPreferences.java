package com.gmail.lucasddeon.rotatingmaze.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 *
 */
@Deprecated
public class UserGameLevelsPreferences {

    /**
     *
     */
    protected static final class Consts {
        public final static String _NAME_TPL = "user_game_level_%s";

        public final static String LEVEL = "level";
        public final static String ENABLED = "enabled";
        public final static String CONQUEST_ATTEMPTS = "conquest_attempts";
        public final static String CONQUEST_TIME = "conquest_time";
        public final static String FAILED_ATTEMPTS = "failed_attempts";
        public final static String SUCCESSFUL_ATTEMPTS = "successful_attempts";
        public final static String TOTAL_ATTEMPTS = "total_attempts";
        public final static String TOTAL_TIME = "total_time";
    }

    /**
     *
     */
    protected static final class Defaults {

    }

    protected static final int levelCount;
    protected static Preferences[] preferences;
    static {

        levelCount = UserProfilePreferences.getCurrentLevel();
        preferences = new Preferences[levelCount];

        for (int i = 0; i < levelCount; i++) {
            String preferenceName = String.format(Consts._NAME_TPL, i + 1);
            preferences[i]= Gdx.app.getPreferences(preferenceName);
        }
    }

    public static void save() {
        for (int i = 0; i < levelCount; i++) {
            save(i);
        }
    }

    public static void clear() {
        for (int i = 0; i < levelCount; i++) {
            clear(i);
        }
    }

    public static void save(int level) {
        preferences[level].flush();
    }

    public static void clear(int level) {
        preferences[level].clear();
    }


    public static boolean contains(int level, String key) {
        return preferences[level].contains(key);
    }


    public static int getLevel(int level) {
        return preferences[level].getInteger(Consts.LEVEL);
    }

    public static void setLevel(int level, int value) {
        preferences[level].putInteger(Consts.LEVEL, value);
    }


    public static boolean isEnabled(int level) {
        return preferences[level].getBoolean(Consts.ENABLED);
    }

    public static void setEnabled(int level, boolean value) {
        preferences[level].putBoolean(Consts.ENABLED, value);
    }


    public static int getConquestAttempts(int level) {
        return preferences[level].getInteger(Consts.CONQUEST_ATTEMPTS);
    }

    public static void setConquestAttempts(int level, int value) {
        preferences[level].putInteger(Consts.CONQUEST_ATTEMPTS, value);
    }


    public static int getConquestTime(int level) {
        return preferences[level].getInteger(Consts.CONQUEST_TIME);
    }

    public static void setConquestTime(int level, int value) {
        preferences[level].putInteger(Consts.CONQUEST_TIME, value);
    }


    public static int getFailedAttempts(int level) {
        return preferences[level].getInteger(Consts.FAILED_ATTEMPTS);
    }

    public static void setFailedAttempts(int level, int value) {
        preferences[level].putInteger(Consts.FAILED_ATTEMPTS, value);
    }


    public static int getSuccessfulAttempts(int level) {
        return preferences[level].getInteger(Consts.SUCCESSFUL_ATTEMPTS);
    }

    public static void setSuccessfulAttempts(int level, int value) {
        preferences[level].putInteger(Consts.SUCCESSFUL_ATTEMPTS, value);
    }


    public static int getTotalAttempts(int level) {
        return preferences[level].getInteger(Consts.TOTAL_ATTEMPTS);
    }

    public static void setTotalAttempts(int level, int value) {
        preferences[level].putInteger(Consts.TOTAL_ATTEMPTS, value);
    }


    public static int getTotalTime(int level) {
        return preferences[level].getInteger(Consts.TOTAL_TIME);
    }

    public static void setTotalTime(int level, int value) {
        preferences[level].putInteger(Consts.TOTAL_TIME, value);
    }


    public static void initialize() {

        for (int i = 0; i < levelCount; i++) {

            if (!contains(i, Consts.LEVEL)) {
                setLevel(i, i + 1);
            }

            if (!contains(i, Consts.ENABLED)) {
                if (i == 0) {
                    // O primeiro nível sempre deve ser habilitado...
                    setEnabled(i, true);
                } else {
                    // ... os outros níveis, não
                    setEnabled(i, false);
                }
            }

            if (!contains(i, Consts.CONQUEST_ATTEMPTS)) {
                setConquestAttempts(i, 0);
            }

            if (!contains(i, Consts.CONQUEST_TIME)) {
                setConquestTime(i, 0);
            }

            if (!contains(i, Consts.FAILED_ATTEMPTS)) {
                setFailedAttempts(i, 0);
            }

            if (!contains(i, Consts.SUCCESSFUL_ATTEMPTS)) {
                setSuccessfulAttempts(i, 0);
            }

            if (!contains(i, Consts.TOTAL_ATTEMPTS)) {
                setTotalAttempts(i, 0);
            }

            if (!contains(i, Consts.TOTAL_TIME)) {
                setTotalTime(i, 0);
            }

        }

        save();

    }

}
