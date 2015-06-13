package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gmail.lucasddeon.rotatingmaze.gameworld.*;

/**
 *
 */
public class GameScreen implements Screen {

    protected GameWorld world;
    protected GameRenderer renderer;
    protected float runTime;

    protected Screen fromScreen;

    public GameScreen(Screen fromScreen, int level, GameMode gameMode) {

        level++;

        this.fromScreen = fromScreen;

        GameOptions gameOptions = new GameOptions(level);

        world = new GameWorld(gameOptions);

        switch (gameMode) {
            case SOBER:
                renderer = new GameSoberModeRenderer(this, world, gameOptions);
                break;
            case DRUNK:
                renderer = new GameDrunkModeRenderer(this, world, gameOptions);
                break;
            default:
                // ?
        }

    }

    public void show() {

    }

    public void render(float delta) {
        runTime += delta;
        world.update(runTime, delta);
        renderer.render(runTime, delta);
    }

    public void resize(int width, int height) {

    }

    public void pause() {

        renderer.tryPause();

    }

    public void resume() {

        renderer.tryResume();

    }

    public void hide() {

    }

    public void dispose() {

        ((Game) Gdx.app.getApplicationListener()).setScreen(this.fromScreen);

    }

    public void showLevelPassed(int level, GameMode gameMode) {

        this.dispose();
        ((Game) Gdx.app.getApplicationListener()).setScreen(
                new LevelPassedScreen(fromScreen, level, gameMode));

    }

    public void showGameOver(int level, GameMode gameMode) {

        this.dispose();
        ((Game) Gdx.app.getApplicationListener()).setScreen(
                new GameOverScreen(fromScreen, level, gameMode));

    }

}
