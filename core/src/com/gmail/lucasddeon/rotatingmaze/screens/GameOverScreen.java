package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.gameworld.GameMode;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class GameOverScreen extends AbstractScreen {

    /**
     *
     */
    private static final class Consts {

        public static final String ICON = "thumbs_down";
        public static final String TITLE = "you_lose";
        public static final String BACKGROUND = "others";

        public static final float BUTTON_PLAY_PERCENTAGE = 50 / 100f;

    }

    protected final int level;
    protected final GameMode gameMode;

    protected ImageButton buttonRetryLevel;


    public GameOverScreen(Screen fromScreen, int level, GameMode gameMode) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

        this.level = level;
        this.gameMode = gameMode;
    }


    @Override
    protected void setupElements() {

        buttonRetryLevel = new ImageButton(AssetLoader.skin, "retry");
        buttonRetryLevel.setSize(
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE,
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE);
        buttonRetryLevel.setX((W() / 2) - (buttonRetryLevel.getWidth() / 2));
        {
            float h2 = spriteTitle.getY();
            buttonRetryLevel.setY((h2 / 2) - (buttonRetryLevel.getHeight() / 2));
        }

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonRetryLevel);

    }

    @Override
    protected void setupEvents() {

        final GameOverScreen instance = this;

        // RETRY LEVEL
        buttonRetryLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameScreen(fromScreen, instance.level - 1, instance.gameMode));
            }
        });

    }

}
