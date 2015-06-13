package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.gameworld.GameMode;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class LevelPassedScreen extends AbstractScreen {

    /**
     *
     */
    private static final class Consts {

        public static final String ICON = "thumbs_up";
        public static final String TITLE = "you_win";
        public static final String BACKGROUND = "others";

        public static final float BUTTON_PLAY_PERCENTAGE = 50 / 100f;

    }

    protected final int level;
    protected final GameMode gameMode;

    protected ImageButton buttonNextLevel;


    public LevelPassedScreen(Screen fromScreen, int level, GameMode gameMode) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

        this.level = level;
        this.gameMode = gameMode;
    }


    @Override
    protected void setupElements() {

        buttonNextLevel = new ImageButton(AssetLoader.skin, "play");
        buttonNextLevel.setSize(
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE,
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE);
        buttonNextLevel.setX((W() / 2) - (buttonNextLevel.getWidth() / 2));
        {
            float h2 = spriteTitle.getY();
            buttonNextLevel.setY((h2 / 2) - (buttonNextLevel.getHeight() / 2));
        }

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonNextLevel);

    }

    @Override
    protected void setupEvents() {

        final LevelPassedScreen instance = this;

        // NEXT LEVEL
        buttonNextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameScreen(fromScreen, instance.level, instance.gameMode));
            }
        });

    }

}
