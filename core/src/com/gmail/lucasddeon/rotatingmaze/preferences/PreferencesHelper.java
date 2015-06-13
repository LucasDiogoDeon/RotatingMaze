package com.gmail.lucasddeon.rotatingmaze.preferences;

/**
 *
 */
public class PreferencesHelper {

    public static void initialize() {

        VersionPreferences.initialize();
        GameOptionsPreferences.initialize();
        UserProfilePreferences.initialize();
        UserGameLevelsPreferences.initialize();

    }

}
