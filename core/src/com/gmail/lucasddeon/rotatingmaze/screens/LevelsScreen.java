package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.gameworld.GameMode;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.preferences.UserProfilePreferences;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class LevelsScreen extends AbstractScreen {

    /**
     *
     */
    private static final class Consts {

        public static final String ICON = "editor";
        public static final String TITLE = "levels";
        public static final String BACKGROUND = "others";

        public static final float BUTTON_PLAY_PERCENTAGE = 50 / 100f;

    }


    protected int chosenLevelIndex;
    protected int chosenLevelIndexMax;
    protected final int chosenLevelIndexMin = 1;
    protected Label labelChosenLevel;
    protected ImageButton buttonChosenLevelUp;
    protected ImageButton buttonChosenLevelDown;

    protected ImageButton buttonPlay;


    public LevelsScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupConfig() {

        chosenLevelIndex = UserProfilePreferences.getChosenLevel();
        chosenLevelIndexMax = UserProfilePreferences.getCurrentLevel() + 1;

    }

    @Override
    protected void setupElements() {

        // ChosenLevel

        labelChosenLevel = new Label("", AssetLoader.skin, "candles_regular_216_beige_dark");
        labelChosenLevel.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelChosenLevel.setHeight(spriteTitle.getY());
        labelChosenLevel.setAlignment(Align.center, Align.center);
        labelChosenLevel.setX(0);
        labelChosenLevel.setY(0);

        updateChosenLevelComponents();

        buttonChosenLevelUp = new ImageButton(AssetLoader.skin, "up");
        buttonChosenLevelUp.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonChosenLevelUp.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonChosenLevelUp.setX((W() / 2) - buttonChosenLevelUp.getWidth());
        {
            buttonChosenLevelUp.setY((spriteTitle.getY() / 2) + (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        buttonChosenLevelDown = new ImageButton(AssetLoader.skin, "down");
        buttonChosenLevelDown.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonChosenLevelDown.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonChosenLevelDown.setX((W() / 2) - buttonChosenLevelDown.getWidth());
        {
            buttonChosenLevelDown.setY((spriteTitle.getY() / 2) - buttonChosenLevelDown.getHeight() - (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        labelChosenLevel.setWidth((W() / 2) - buttonChosenLevelUp.getWidth() - DefaultConsts.ELEMENT_MARGIN);

        // / ChosenLevel

        // Play

        buttonPlay = new ImageButton(AssetLoader.skin, "play");
        buttonPlay.setSize(
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE,
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE);
        buttonPlay.setX(((3 * W()) / 4) - (buttonPlay.getWidth() / 2));
        {
            float h2 = spriteTitle.getY();
            buttonPlay.setY((h2 / 2) - (buttonPlay.getHeight() / 2));
        }

        // / Play

    }

    protected void updateChosenLevelComponents() {

        String text = UserProfilePreferences.getChosenLevel() + "";
        labelChosenLevel.setText(text);

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonChosenLevelUp);
        groupElements.addActor(buttonChosenLevelDown);
        groupElements.addActor(labelChosenLevel);

        groupElements.addActor(buttonPlay);

    }

    @Override
    protected void setupEvents() {

        final LevelsScreen instance = this;

        ClickListener onChosenLevelUpClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onChosenLevelUp();
            }
        };

        ClickListener onChosenLevelDownClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onChosenLevelDown();
            }
        };

        // ChosenLevel : UP
        buttonChosenLevelUp.addListener(onChosenLevelUpClickListener);

        // ChosenLevel : DOWN
        buttonChosenLevelDown.addListener(onChosenLevelDownClickListener);
        labelChosenLevel.addListener(onChosenLevelDownClickListener);

        // Play

        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                int level = UserProfilePreferences.getChosenLevel();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameScreen(fromScreen, level - 1, GameMode.SOBER));
                        //new GameScreen(fromScreen, level - 1, GameMode.DRUNK));
            }
        });

        // / Play

    }

    protected void onChosenLevelUp() {
        chosenLevelIndex++;
        if (chosenLevelIndex > chosenLevelIndexMax) {
            chosenLevelIndex = chosenLevelIndexMin;
        }

        UserProfilePreferences.setChosenLevel(chosenLevelIndex);
        UserProfilePreferences.save();

        updateChosenLevelComponents();
    }

    protected void onChosenLevelDown() {
        chosenLevelIndex--;
        if (chosenLevelIndex < chosenLevelIndexMin) {
            chosenLevelIndex = chosenLevelIndexMax;
        }

        UserProfilePreferences.setChosenLevel(chosenLevelIndex);
        UserProfilePreferences.save();

        updateChosenLevelComponents();
    }

}
