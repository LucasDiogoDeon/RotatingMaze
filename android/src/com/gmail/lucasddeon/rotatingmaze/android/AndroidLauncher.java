package com.gmail.lucasddeon.rotatingmaze.android;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new RotatingMazeGame(), config);
	}
}
