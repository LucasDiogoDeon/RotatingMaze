package com.gmail.lucasddeon.rotatingmaze.gameworld;

public abstract class GameRenderer {

    protected final GameMode gameMode;

    protected GameRenderer(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public abstract void render(float runTime, float delta);

    public abstract void tryPause();

    public abstract void tryResume();

}
