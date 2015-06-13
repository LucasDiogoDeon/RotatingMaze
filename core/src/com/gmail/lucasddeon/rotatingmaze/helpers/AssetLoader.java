package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.gmail.lucasddeon.rotatingmaze.preferences.GameOptionsPreferences;

/**
 *
 */
public class AssetLoader {

    // TextureAtlas & Skins
    public static final TextureAtlas atlas =
            new TextureAtlas(Gdx.files.internal("skins/skin.pack"));

    public static final Skin skin =
            new Skin(Gdx.files.internal("skins/skin.json"), atlas);
    // / TextureAtlas & Skins

    static {
        
    }

    // Texturas
    public static TextureRegion textureRegionBackground;
    public static TextureRegion textureRegionBall;

    public static TextureRegion textureRegionPipeSquare;
    public static TextureRegion textureRegionPipeBorder;
    public static TextureRegion textureRegionPipeFinish;
    public static TextureRegion textureRegionPipeAll;

    public static TextureRegion textureRegionMaskReady;
    public static TextureRegion textureRegionMaskPaused;
    public static TextureRegion textureRegionMaskGameOver;
    public static TextureRegion textureRegionMaskGameWon;
    // / Texturas

    // Sons
    public static Sound soundGameOver;
    public static Sound soundGameWon;
    // Sons

    public static void loadTextures() {

        String _avatar = GameOptionsPreferences.getAvatar();
        String _environment = GameOptionsPreferences.getEnvironment();

        textureRegionBackground = atlas.findRegion(
                String.format("environments/%s", _environment));
        textureRegionBall = atlas.findRegion(
                String.format("balls/%s", _avatar));

        textureRegionPipeSquare = atlas.findRegion(
                String.format("pipes/%s/square", _environment));
        textureRegionPipeBorder = atlas.findRegion(
                String.format("pipes/%s/border", _environment));
        textureRegionPipeFinish = atlas.findRegion(
                String.format("pipes/%s/finish", _environment));
        textureRegionPipeAll = atlas.findRegion(
                String.format("pipes/%s/all", _environment));

        textureRegionMaskReady = atlas.findRegion("masks/ready");
        textureRegionMaskPaused = atlas.findRegion("masks/paused");
        textureRegionMaskGameOver = atlas.findRegion("masks/gameover");
        textureRegionMaskGameWon = atlas.findRegion("masks/gamewon");

    }

    public static void loadSounds() {

        String folder = GameOptionsPreferences.getAudio();

        soundGameOver = Gdx.audio.newSound(
                Gdx.files.internal("sounds/" + folder + "/game_over.wav"));
        soundGameWon = Gdx.audio.newSound(
                Gdx.files.internal("sounds/" + folder + "/game_won.wav"));

    }

    public static void dispose() {

        // Texturas & Atlas
        atlas.dispose();
        skin.dispose();
        // / Texturas & Atlas

        // Sons
        if (soundGameWon != null) {
            soundGameWon.dispose();
        }
        if (soundGameOver != null) {
            soundGameOver.dispose();
        }
        // Sons

    }

    public static void load() {

        loadTextures();
        loadSounds();

    }

}
