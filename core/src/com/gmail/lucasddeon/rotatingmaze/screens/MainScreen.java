package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.gameworld.GameMode;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper;
import com.gmail.lucasddeon.rotatingmaze.preferences.GameOptionsPreferences;
import com.gmail.lucasddeon.rotatingmaze.preferences.UserGameLevelsPreferences;
import com.gmail.lucasddeon.rotatingmaze.preferences.UserProfilePreferences;

import java.util.ArrayList;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class MainScreen implements Screen {

    /**
     *
     */
    protected static final class Assets {

        public static final Texture textureTitle =
                new Texture(Gdx.files.internal("img/texts/main__title.png"));

        public static final Texture textureBackground =
            new Texture(Gdx.files.internal("img/backgrounds/main_tpl.png"));

        static {

            textureTitle.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

            textureBackground.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

        }

    }

    /**
     *
     */
    protected static final class Consts {

        public static final float ELEMENT_MARGIN_PERCENTAGE = 1.5f / 100f;
        public static final float ELEMENT_SIZE_PERCENTAGE = 15f / 100f;

        public static final float ELEMENT_MARGIN;
        public static final float ELEMENT_HEIGHT;
        public static final float ELEMENT_WIDTH;

        public static final float ELEMENT_TOP_Y;
        public static final float ELEMENT_BOTTOM_Y;
        public static final float ELEMENT_LEFT_X;
        public static final float ELEMENT_RIGHT_X;

        static {
            ELEMENT_MARGIN = H() * ELEMENT_MARGIN_PERCENTAGE;

            float size = H() * ELEMENT_SIZE_PERCENTAGE;
            ELEMENT_HEIGHT = size;
            ELEMENT_WIDTH = size;

            ELEMENT_TOP_Y = ELEMENT_HEIGHT + ELEMENT_MARGIN;
            ELEMENT_BOTTOM_Y = ELEMENT_MARGIN;
            ELEMENT_LEFT_X = ELEMENT_MARGIN;
            ELEMENT_RIGHT_X = ELEMENT_WIDTH + ELEMENT_MARGIN;
        }

        public static final float BUTTON_PLAY_PERCENTAGE = 50 / 100f;

    }

    protected Stage stage;
    protected SpriteBatch spriteBatcher;

    protected TextButton buttonResetXXX;
    protected ImageButton buttonPlay;
    protected ImageButton buttonLevelEditor;
    protected ImageButton buttonSettings;
    protected ImageButton buttonStatistics;
    protected ImageButton buttonHelp;
    protected ImageButton buttonInfo;
    protected ImageButton buttonExit;

    protected Sprite spriteTitle;

    protected ArrayList<Sprite> spriteListBackground;


    public MainScreen() {

        spriteBatcher = new SpriteBatch();

        stage = new Stage();

        setupBackground();
        setupElements();
        placeElements();
        setupEvents();

    }

    public void show() {

        Gdx.input.setInputProcessor(stage);

    }

    protected void setupBackground() {

        spriteListBackground = ScreenHelper.getSpriteListBackground(
                Assets.textureBackground);

    }

    protected void setupElements() {

        // Componentes

        buttonResetXXX = new TextButton("Reset", AssetLoader.skin, "default");
        buttonResetXXX.setVisible(false); // remove line on debug
        buttonResetXXX.setX(0);
        buttonResetXXX.setY(H() / 2);


        buttonSettings = new ImageButton(AssetLoader.skin, "settings");
        buttonSettings.setX(Consts.ELEMENT_LEFT_X);
        buttonSettings.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonSettings.setWidth(Consts.ELEMENT_WIDTH);
        buttonSettings.setHeight(Consts.ELEMENT_HEIGHT);

        buttonLevelEditor = new ImageButton(AssetLoader.skin, "editor");
        buttonLevelEditor.setX(Consts.ELEMENT_MARGIN +
                buttonSettings.getX() + buttonSettings.getWidth());
        buttonLevelEditor.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonLevelEditor.setWidth(Consts.ELEMENT_WIDTH);
        buttonLevelEditor.setHeight(Consts.ELEMENT_HEIGHT);

        buttonStatistics = new ImageButton(AssetLoader.skin, "statistics");
        buttonStatistics.setX(Consts.ELEMENT_MARGIN +
                buttonLevelEditor.getX() + buttonLevelEditor.getWidth());
        buttonStatistics.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonStatistics.setWidth(Consts.ELEMENT_WIDTH);
        buttonStatistics.setHeight(Consts.ELEMENT_HEIGHT);

        buttonHelp = new ImageButton(AssetLoader.skin, "help");
        buttonHelp.setX(Consts.ELEMENT_MARGIN +
                buttonStatistics.getX() + buttonStatistics.getWidth());
        buttonHelp.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonHelp.setWidth(Consts.ELEMENT_WIDTH);
        buttonHelp.setHeight(Consts.ELEMENT_HEIGHT);

        buttonInfo = new ImageButton(AssetLoader.skin, "info");
        buttonInfo.setX(Consts.ELEMENT_MARGIN +
                buttonHelp.getX() + buttonHelp.getWidth());
        buttonInfo.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonInfo.setWidth(Consts.ELEMENT_WIDTH);
        buttonInfo.setHeight(Consts.ELEMENT_HEIGHT);

        buttonExit = new ImageButton(AssetLoader.skin, "exit");
        buttonExit.setX(W() - Consts.ELEMENT_RIGHT_X);
        buttonExit.setY(Consts.ELEMENT_BOTTOM_Y);
        buttonExit.setWidth(Consts.ELEMENT_WIDTH);
        buttonExit.setHeight(Consts.ELEMENT_HEIGHT);

        // / Componentes

        {   // Title
            spriteTitle = new Sprite(Assets.textureTitle);

            float scale = Consts.ELEMENT_HEIGHT / spriteTitle.getHeight();
            spriteTitle.setSize(
                    spriteTitle.getWidth() * scale,
                    Consts.ELEMENT_HEIGHT);

            spriteTitle.setX((W() / 2) - (spriteTitle.getWidth() / 2));
            spriteTitle.setY(H() - Consts.ELEMENT_TOP_Y);
        }   // / Title

        buttonPlay = new ImageButton(AssetLoader.skin, "play");
        buttonPlay.setSize(
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE,
                MinWH() * Consts.BUTTON_PLAY_PERCENTAGE);
        buttonPlay.setX((W() / 2) - (buttonPlay.getWidth() / 2));
        {
            float h2 = spriteTitle.getY() - buttonExit.getTop();
            buttonPlay.setY((h2 / 2) + buttonExit.getTop() - (buttonPlay.getHeight() / 2));
        }

    }

    protected void placeElements() {

        stage.addActor(buttonResetXXX);
        stage.addActor(buttonPlay);
        stage.addActor(buttonSettings);
        stage.addActor(buttonLevelEditor);
        stage.addActor(buttonStatistics);
        stage.addActor(buttonHelp);
        stage.addActor(buttonInfo);
        stage.addActor(buttonExit);

    }

    protected void setupEvents() {

        final MainScreen instance = this;

        //
        buttonResetXXX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                UserProfilePreferences.clear();
                UserProfilePreferences.save();
                GameOptionsPreferences.clear();
                GameOptionsPreferences.save();
                UserGameLevelsPreferences.clear();
                UserGameLevelsPreferences.save();
            }
        });

        // Play
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                int level = UserProfilePreferences.getCurrentLevel();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameScreen(instance, level, GameMode.SOBER));
                        //new GameScreen(instance, level, GameMode.DRUNK));
            }
        });

        // Settings
        buttonSettings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new SettingsScreen(instance));
            }
        });

        // Level Editor
        buttonLevelEditor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new LevelsScreen(instance));
            }
        });

        // Statistics
        buttonStatistics.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new StatisticsScreen(instance));
            }
        });

        // Help
        buttonHelp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new HelpScreen(instance));
            }
        });

        // Info
        buttonInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new InfoScreen(instance));
            }
        });

        // Exit
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    public void render(float delta) {

        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        {   // Sprite Batcher
            spriteBatcher.begin();

            for (Sprite sprite : spriteListBackground) {
                sprite.draw(spriteBatcher);
            }

            spriteTitle.draw(spriteBatcher);

            spriteBatcher.end();
        }   // / Sprite Batcher

        {   // Stage
            stage.act();
            stage.draw();
        }   // / Stage

    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {
        dispose();
    }

    public void dispose() {

    }

}
