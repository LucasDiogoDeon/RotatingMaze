package com.gmail.lucasddeon.rotatingmaze.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.AudioOptions;
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.AvatarOptions;
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.EnvironmentOptions;

/**
 *
 */
public class GameOptionsPreferences {

    /**
     *
     */
    protected static final class Consts {
        public final static String _NAME = "game_options";

        public final static String AVATAR = "avatar";
        public final static String ENVIRONMENT = "environment";
        public final static String AUDIO = "audio";

        public final static String SOUND_ENABLED = "sound_enabled";
        public final static String ACCELEROMETER_ENABLED = "accelerometer_enabled";
    }

    /**
     *
     */
    protected static final class Defaults {
        public final static String AVATAR;
        public final static String ENVIRONMENT;
        public final static String AUDIO;

        public final static boolean SOUND_ENABLED = true;
        public final static boolean ACCELEROMETER_ENABLED = true;

        static {
            AVATAR = AvatarOptions.ELEPHANT.getValue();
            ENVIRONMENT = EnvironmentOptions.CHOCO.getValue();
            AUDIO = AudioOptions.CAETANO_VELOSO.getValue();
        }
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


    public static String getAvatar() {
        return preferences.getString(Consts.AVATAR);
    }

    public static void setAvatar(String value) {
        preferences.putString(Consts.AVATAR, value);
    }


    public static String getEnvironment() {
        return preferences.getString(Consts.ENVIRONMENT);
    }

    public static void setEnvironment(String value) {
        preferences.putString(Consts.ENVIRONMENT, value);
    }


    public static String getAudio() {
        return preferences.getString(Consts.AUDIO);
    }

    public static void setAudio(String value) {
        preferences.putString(Consts.AUDIO, value);
    }


    public static boolean isSoundEnabled() {
        return preferences.getBoolean(Consts.SOUND_ENABLED);
    }

    public static void setSoundEnabled(boolean value) {
        preferences.putBoolean(Consts.SOUND_ENABLED, value);
    }


    public static boolean isAccelerometerEnabled() {
        return preferences.getBoolean(Consts.ACCELEROMETER_ENABLED);
    }

    public static void setAccelerometerEnabled(boolean value) {
        preferences.putBoolean(Consts.ACCELEROMETER_ENABLED, value);
    }


    public static void initialize() {

        if (!contains(Consts.AVATAR)) {
            setAvatar(Defaults.AVATAR);
        }

        if (!contains(Consts.ENVIRONMENT)) {
            setEnvironment(Defaults.ENVIRONMENT);
        }

        if (!contains(Consts.AUDIO)) {
            setAudio(Defaults.AUDIO);
        }

        if (!contains(Consts.SOUND_ENABLED)) {
            setSoundEnabled(Defaults.SOUND_ENABLED);
        }

        if (!contains(Consts.ACCELEROMETER_ENABLED)) {
            setAccelerometerEnabled(Defaults.ACCELEROMETER_ENABLED);
        }

        save();

    }

}
