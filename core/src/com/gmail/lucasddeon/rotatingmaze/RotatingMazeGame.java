package com.gmail.lucasddeon.rotatingmaze;

import com.badlogic.gdx.Game;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.preferences.PreferencesHelper;
import com.gmail.lucasddeon.rotatingmaze.screens.MainScreen;

/**
 *
 */
public class RotatingMazeGame extends Game {

    /**
     *
     */
    public static final class Config {
        public static final String TITLE = "rotating maze";
        public static final String PROGRAMMER_NAME = "Lucas Diogo Deon";
        public static final String VERSION = "0.0.1";
    }

    /**
     *
     */
    public static final class ScreenConfig {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
    }

    /**
     *
     */
    public static final class DesktopConfig {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 720;
        public static final boolean RESIZABLE = false;
        public static final int SAMPLES = 8;
        public static final String TITLE = "Rotating Maze";
        public static final String[] ICONS = {
                "img/logos/logo_16x16.png",
                "img/logos/logo_32x32.png",
                "img/logos/logo_128x128.png"
        };
    }

	public void create () {
		PreferencesHelper.initialize();
		setScreen(new MainScreen());
	}

	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}
