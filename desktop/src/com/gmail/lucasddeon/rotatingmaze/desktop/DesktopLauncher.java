package com.gmail.lucasddeon.rotatingmaze.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = RotatingMazeGame.DesktopConfig.WIDTH;
		config.height = RotatingMazeGame.DesktopConfig.HEIGHT;
		config.resizable = RotatingMazeGame.DesktopConfig.RESIZABLE;
		config.samples = RotatingMazeGame.DesktopConfig.SAMPLES;
		config.title = RotatingMazeGame.DesktopConfig.TITLE;

		for (String icon : RotatingMazeGame.DesktopConfig.ICONS) {
			config.addIcon(icon, Files.FileType.Internal);
		}

		new LwjglApplication(new RotatingMazeGame(), config);
	}
}
