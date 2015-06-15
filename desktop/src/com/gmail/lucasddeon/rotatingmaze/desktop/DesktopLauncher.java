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

        // Gráfico de recursos
		//config.width = 1024;
		//config.height = 500;

        // Gráfico promocional
        //config.width = 180;
        //config.height = 120;

        // Banner de TV
        //config.width = 320;
        //config.height = 180;

        // Telefone
        //config.width = 960;
        //config.height = 640;

        // tablet de 7"
        //config.width = 1024;
        //config.height = 600;

        // tablet de 10"
        //config.width = 1280;
        //config.height = 800;

        config.resizable = RotatingMazeGame.DesktopConfig.RESIZABLE;
		config.samples = RotatingMazeGame.DesktopConfig.SAMPLES;
		config.title = RotatingMazeGame.DesktopConfig.TITLE;

		for (String icon : RotatingMazeGame.DesktopConfig.ICONS) {
			config.addIcon(icon, Files.FileType.Internal);
		}

		new LwjglApplication(new RotatingMazeGame(), config);
	}
}
