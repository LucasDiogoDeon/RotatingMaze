package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper;

import java.util.ArrayList;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public abstract class AbstractScreen implements Screen {

    /**
     *
     */
    protected static class DefaultAssets {

        public static TextureRegion textureRegionIcon;
        public static TextureRegion textureRegionTitle;
        public static Texture textureBackground;

        public static void initialize(String icon, String title, String background) {

            textureRegionIcon = AssetLoader.atlas.findRegion(
                    "buttons/" + icon + "__icon");

            textureRegionTitle = AssetLoader.atlas.findRegion(
                    "texts/" + title + "__title");

            textureBackground = new Texture(Gdx.files.internal(
                    "img/backgrounds/" + background + "_tpl.png"));
            textureBackground.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

        }

    }

    /**
     *
     */
    protected static final class DefaultConsts {

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

    }

    protected Stage stage = new Stage();
    protected SpriteBatch spriteBatcher;
    protected Group groupBackground;
    protected Group groupElements;

    protected ArrayList<Sprite> spriteListBackground;

    protected Sprite spriteIcon;
    protected Sprite spriteTitle;
    protected ImageButton buttonReturn;

    protected Screen fromScreen;

    protected AbstractScreen(Screen fromScreen,
                             String icon, String title, String background) {

        DefaultAssets.initialize(icon, title, background);

        this.fromScreen = fromScreen;

        setupDefaultConfig();
        setupConfig();

        setupDefaultBackground();
        setupBackground();

        setupDefaultElements();
        setupElements();

        placeDefaultElements();
        placeElements();

        setupDefaultEvents();
        setupEvents();

        setupDefaultActions();
        setupActions();

    }

    public final void show() {

        Gdx.input.setInputProcessor(stage);

    }

    // Config

    protected final void setupDefaultConfig() {

    }

    protected void setupConfig() {
        // TODO
    }

    // / Config


    // Background

    protected final void setupDefaultBackground() {

        spriteBatcher = new SpriteBatch();

        spriteListBackground = ScreenHelper.getSpriteListBackground(
                DefaultAssets.textureBackground);

    }

    protected void setupBackground() {
        // TODO
    }

    // / Background


    // Elements

    protected final void setupDefaultElements() {

        {   // Icon
            spriteIcon = new Sprite(DefaultAssets.textureRegionIcon);
            spriteIcon.setX(DefaultConsts.ELEMENT_LEFT_X);
            spriteIcon.setY(H() - DefaultConsts.ELEMENT_TOP_Y);
            spriteIcon.setSize(DefaultConsts.ELEMENT_WIDTH, DefaultConsts.ELEMENT_HEIGHT);
        }   // / Icon

        {   // Title
            spriteTitle = new Sprite(DefaultAssets.textureRegionTitle);

            float scale = DefaultConsts.ELEMENT_HEIGHT / spriteTitle.getHeight();
            spriteTitle.setSize(
                    spriteTitle.getWidth() * scale,
                    DefaultConsts.ELEMENT_HEIGHT);

            spriteTitle.setX((W() / 2) - (spriteTitle.getWidth() / 2));
            spriteTitle.setY(H() - DefaultConsts.ELEMENT_TOP_Y);
        }   // / Title

        {   // Return
            buttonReturn = new ImageButton(AssetLoader.skin, "return");
            buttonReturn.setSize(DefaultConsts.ELEMENT_WIDTH, DefaultConsts.ELEMENT_HEIGHT);
            buttonReturn.setX(W() - DefaultConsts.ELEMENT_RIGHT_X);
            buttonReturn.setY(H() - DefaultConsts.ELEMENT_TOP_Y);
        }   // / Return

    }

    protected void setupElements() {
        // TODO
    }

    // / Elements


    // Elements

    protected final void placeDefaultElements() {

        groupBackground = new Group();
        stage.addActor(groupBackground);

        groupElements = new Group();
        stage.addActor(groupElements);

        groupElements.addActor(buttonReturn);

    }

    protected void placeElements() {
        // TODO
    }

    // / Elements


    // Events

    protected void setupDefaultEvents() {

        final AbstractScreen instance = this;

        // RETURN
        buttonReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                instance.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(instance.fromScreen);
            }
        });

    }

    protected void setupEvents() {
        // TODO
    }

    // / Events


    // Actions

    protected void setupDefaultActions() {

    }

    protected void setupActions() {
        // TODO
    }

    // / Actions


    public void render(float delta) {

        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        {   // Sprite Batcher
            spriteBatcher.begin();

            for (Sprite sprite : spriteListBackground) {
                sprite.draw(spriteBatcher);
            }

            spriteIcon.draw(spriteBatcher);
            spriteTitle.draw(spriteBatcher);

            spriteBatcher.end();
        }   // / Sprite Batcher

        {   // Stage
            stage.act();
            stage.draw();
        }   // / Stage

    }

    public void resize(int width, int height) {
        ScreenHelper.reload();
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
