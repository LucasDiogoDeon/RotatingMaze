package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.LabelHelper;
import com.gmail.lucasddeon.rotatingmaze.preferences.GameOptionsPreferences;
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.AudioOptions;

import java.util.Arrays;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class SettingsAudioScreen extends AbstractScreen {

    /**
     *
     */
    public static final class Consts {

        public static final String ICON = "settings";
        public static final String TITLE = "audio";
        public static final String BACKGROUND = "others";

        public static final float IMAGE_SIZE_PERCENTAGE = 50 / 100f;

    }

    protected String[] itemsValueAudio;
    protected String[] itemsDisplayAudio;
    protected int itemIndexAudio;
    protected Image imageAudio;
    protected Label labelAudio;
    protected Label labelAudioShadow;
    protected ImageButton buttonAudioUp;
    protected ImageButton buttonAudioDown;


    public SettingsAudioScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupConfig() {

        itemsValueAudio = AudioOptions.getValues();
        itemsDisplayAudio = AudioOptions.getDisplays();

        itemIndexAudio = Arrays.asList(itemsValueAudio).indexOf(
                GameOptionsPreferences.getAvatar());

    }

    @Override
    protected void setupElements() {

        float imageSize = MinWH() * Consts.IMAGE_SIZE_PERCENTAGE;

        // AudioOptions

        labelAudio = new Label("", AssetLoader.skin, "04b_19");
        labelAudio.setFontScale(((float) W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelAudio.setAlignment(Align.center, Align.bottom);
        labelAudio.setY((labelAudio.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        imageAudio = new Image();
        imageAudio.setSize(imageSize, imageSize);
        {
            float h2 = spriteTitle.getY() - labelAudio.getTop();
            imageAudio.setY((h2 / 2) + labelAudio.getTop() - (imageAudio.getHeight() / 2));
        }

        buttonAudioUp = new ImageButton(AssetLoader.skin, "up");
        buttonAudioUp.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonAudioUp.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonAudioUp.setX((W() / 2) -
                ((buttonAudioUp.getWidth() + DefaultConsts.ELEMENT_MARGIN + imageAudio.getWidth()) / 2) +
                DefaultConsts.ELEMENT_MARGIN + imageAudio.getWidth());
        {
            float h2 = spriteTitle.getY() - labelAudio.getTop();
            buttonAudioUp.setY((h2 / 2) + labelAudio.getTop() + (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        buttonAudioDown = new ImageButton(AssetLoader.skin, "down");
        buttonAudioDown.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonAudioDown.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonAudioDown.setX((W() / 2) -
                ((buttonAudioUp.getWidth() + DefaultConsts.ELEMENT_MARGIN + imageAudio.getWidth()) / 2) +
                DefaultConsts.ELEMENT_MARGIN + imageAudio.getWidth());
        {
            float h2 = spriteTitle.getY() - labelAudio.getTop();
            buttonAudioDown.setY((h2 / 2) + labelAudio.getTop() - buttonAudioDown.getHeight() - (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        imageAudio.setX(buttonAudioUp.getX() - imageAudio.getWidth()
                - DefaultConsts.ELEMENT_MARGIN);

        labelAudio.setWidth(imageAudio.getWidth());
        labelAudio.setX(imageAudio.getX());

        labelAudioShadow = LabelHelper.getLabelShadow(labelAudio);

        updateAudioComponents();

        // / AudioOptions

    }

    protected void updateAudioComponents() {

        String text = itemsDisplayAudio[
                Arrays.asList(itemsValueAudio).indexOf(
                        GameOptionsPreferences.getAudio())];

        labelAudio.setText(text);
        labelAudioShadow.setText(text);

        String valueAudio = String.format("audios/%s",
                GameOptionsPreferences.getAudio());

        imageAudio.setDrawable(new SpriteDrawable(new Sprite(
                AssetLoader.atlas.findRegion(valueAudio))));

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonAudioUp);
        groupElements.addActor(buttonAudioDown);
        groupElements.addActor(imageAudio);
        groupElements.addActor(labelAudioShadow);
        groupElements.addActor(labelAudio);

    }

    @Override
    protected void setupEvents() {

        ClickListener onAudioUpClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAudioUp();
            }
        };

        ClickListener onAudioDownClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAudioDown();
            }
        };

        // AudioOptions : UP
        buttonAudioUp.addListener(onAudioUpClickListener);

        // AudioOptions : DOWN
        buttonAudioDown.addListener(onAudioDownClickListener);
        imageAudio.addListener(onAudioDownClickListener);
        labelAudio.addListener(onAudioDownClickListener);
        labelAudioShadow.addListener(onAudioDownClickListener);

    }


    protected void onAudioUp() {
        itemIndexAudio++;
        if (itemIndexAudio >= itemsValueAudio.length) {
            itemIndexAudio = 0;
        }

        GameOptionsPreferences.setAudio(itemsValueAudio[itemIndexAudio]);
        GameOptionsPreferences.save();

        updateAudioComponents();
    }

    protected void onAudioDown() {
        itemIndexAudio--;
        if (itemIndexAudio < 0) {
            itemIndexAudio = itemsValueAudio.length - 1;
        }

        GameOptionsPreferences.setAudio(itemsValueAudio[itemIndexAudio]);
        GameOptionsPreferences.save();

        updateAudioComponents();
    }

}
