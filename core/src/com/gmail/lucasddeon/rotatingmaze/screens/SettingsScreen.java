package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class SettingsScreen extends AbstractScreen {

    /**
     *
     */
    public static final class Consts {

        public static final String ICON = "settings";
        public static final String TITLE = "settings";
        public static final String BACKGROUND = "others";

        public static final float BUTTON_SIZE_PERCENTAGE = 50 / 100f;
        public static final float BUTTON_SIZE_RATIO = 512 / 346f;

        public static final int ELEMENT_MARGIN = 30;

    }

    protected ImageButton buttonVisuals;
    protected ImageButton buttonAudio;


    public SettingsScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupElements() {

        float imageSize = MinWH() * Consts.BUTTON_SIZE_PERCENTAGE;

        // Visual

        buttonVisuals = new ImageButton(AssetLoader.skin, "visual__menu");
        buttonVisuals.setSize(imageSize * Consts.BUTTON_SIZE_RATIO, imageSize);
        buttonVisuals.setX((W() / 2) - buttonVisuals.getWidth() - Consts.ELEMENT_MARGIN);
        buttonVisuals.setY((spriteTitle.getY() / 2) - (buttonVisuals.getHeight() / 2));

        // / Visual

        // AudioOptions

        buttonAudio = new ImageButton(AssetLoader.skin, "audio__menu");
        buttonAudio.setSize(imageSize * Consts.BUTTON_SIZE_RATIO, imageSize);
        buttonAudio.setX((W() / 2) + Consts.ELEMENT_MARGIN);
        buttonAudio.setY((spriteTitle.getY() / 2) - (buttonVisuals.getHeight() / 2));

        // / AudioOptions

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonVisuals);
        groupElements.addActor(buttonAudio);

    }

    @Override
    protected void setupEvents() {

        final SettingsScreen instance = this;

        // Visuals
        buttonVisuals.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsVisualScreen(instance));
            }
        });

        // AudioOptions
        buttonAudio.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsAudioScreen(instance));
            }
        });

    }

}
