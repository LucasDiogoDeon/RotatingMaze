package com.gmail.lucasddeon.rotatingmaze.gameworld;

import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.*;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class GameWorld {

    protected GameOptions gameOptions;

    protected float distanceX;
    protected float distanceY;
    protected float rotation;

    protected Ball ball;
    protected PipeShapePath pipeShapePath;

    protected GameState gameState;

    protected boolean paused;


    public float getDistanceX() {
        return distanceX;
    }

    public float getDistanceY() {
        return distanceY;
    }

    public float getRotation() {
        return rotation;
    }

    public Ball getBall() {
        return ball;
    }

    public PipeShapePath getPipeShapePath() {
        return pipeShapePath;
    }

    public PipeShape[] getPipeShapes() {
        return pipeShapePath.getShapes();
    }

    public boolean isPaused() {
        return paused;
    }

    public GameWorld(GameOptions gameOptions) {

        this.gameOptions = gameOptions;

        {
            // AvatarOptions
            this.ball = new Ball(
                this.gameOptions.ballPosition.x,
                this.gameOptions.ballPosition.y,
                this.gameOptions.ballRadius);
            // / AvatarOptions
        }

        {
            // Pipes
            this.pipeShapePath = this.gameOptions.pipeShapePath;
        }

        //start();
        ready();

    }

    protected void ready() {
        gameState = GameState.READY;
    }

    public void start() {
        gameState = GameState.RUNNING;
    }

    public void update(float runTime, float delta) {

        switch (gameState) {
            case READY:
                updateReady(runTime, delta);
                break;

            case RUNNING:
            default:
                updateRunning(runTime, delta);
                break;
        }

    }

    private void updateReady(float runTime, float delta) {

    }

    public void updateRunning(float runTime, float delta) {

        updateDistance(delta);

    }

    public void pause() {
        this.paused = true;
        this.gameState = GameState.PAUSED;
    }

    public void resume() {
        this.paused = false;
        this.gameState = GameState.RUNNING;
    }

    public float rotate(float rotation) {

        float oldRotation = this.rotation;

        if (rotation < gameOptions.rotationMin) {
            this.rotation = gameOptions.rotationMin;
        } else if (rotation > gameOptions.rotationMax) {
            this.rotation = gameOptions.rotationMax;
        } else {
            this.rotation = rotation;
        }

        float deltaRotation = oldRotation - this.rotation;

        return deltaRotation;

    }

    protected void updateDistance(float delta) {

        double angle = (rotation / 180) * Math.PI;
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double scaleWidth = ((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH;
        double scaleHeight = ((float)H()) / RotatingMazeGame.ScreenConfig.HEIGHT;
        double scale = (scaleWidth + scaleHeight) / 2;

        distanceX = (float)(cos * scale * gameOptions.distanceVelocity * delta * 60);
        distanceY = (float)(sin * scale * gameOptions.distanceVelocity * delta * 60);

    }

}
