package com.gmail.lucasddeon.rotatingmaze.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 *
 */
public class VersionPreferences {

    /**
     *
     */
    protected static final class Consts {
        public final static String _NAME = "version";
        public final static String VERSION = "version";
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

    public static int getVersion() {
        return preferences.getInteger(Consts.VERSION);
    }

    public static void setVersion(int value) {
        preferences.putInteger(Consts.VERSION, value);
    }

    public static void incVersion() {
        setVersion(getVersion() + 1);
    }

    public static void initialize() {

        if (!contains(Consts.VERSION)) {
            setVersion(1);
        }

        save();

    }

}
