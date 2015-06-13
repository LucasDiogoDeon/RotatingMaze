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
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.AvatarOptions;
import com.gmail.lucasddeon.rotatingmaze.preferences.enums.EnvironmentOptions;

import java.util.Arrays;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class SettingsVisualScreen extends AbstractScreen {

    public static final class Consts {

        public static final String ICON = "settings";
        public static final String TITLE = "visuals";
        public static final String BACKGROUND = "others";

        public static final float IMAGE_SIZE_PERCENTAGE = 50 / 100f;

    }

    protected String[] itemsValueAvatar;
    protected String[] itemsDisplayAvatar;
    protected int itemIndexAvatar;
    protected Image imageAvatar;
    protected Label labelAvatar;
    protected Label labelAvatarShadow;
    protected ImageButton buttonAvatarUp;
    protected ImageButton buttonAvatarDown;

    protected String[] itemsValueEnvironment;
    protected String[] itemsDisplayEnvironment;
    protected int itemIndexEnvironment;
    protected Image imageEnvironment;
    protected Label labelEnvironment;
    protected Label labelEnvironmentShadow;
    protected ImageButton buttonEnvironmentUp;
    protected ImageButton buttonEnvironmentDown;


    public SettingsVisualScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupConfig() {

        itemsValueAvatar = AvatarOptions.getValues();
        itemsDisplayAvatar = AvatarOptions.getDisplays();

        itemsValueEnvironment = EnvironmentOptions.getValues();
        itemsDisplayEnvironment = EnvironmentOptions.getDisplays();

        itemIndexAvatar = Arrays.asList(itemsValueAvatar).indexOf(
                GameOptionsPreferences.getAvatar());

        itemIndexEnvironment = Arrays.asList(itemsValueEnvironment).indexOf(
                GameOptionsPreferences.getEnvironment());

    }

    @Override
    protected void setupElements() {

        float imageSize = MinWH() * Consts.IMAGE_SIZE_PERCENTAGE;

        // AvatarOptions

        labelAvatar = new Label("", AssetLoader.skin, "04b_19");
        labelAvatar.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelAvatar.setAlignment(Align.center, Align.bottom);
        labelAvatar.setY((labelAvatar.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        imageAvatar = new Image();
        imageAvatar.setSize(imageSize, imageSize);
        {
            float h2 = spriteTitle.getY() - labelAvatar.getTop();
            imageAvatar.setY((h2 / 2) + labelAvatar.getTop() - (imageAvatar.getHeight() / 2));
        }

        buttonAvatarUp = new ImageButton(AssetLoader.skin, "up");
        buttonAvatarUp.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonAvatarUp.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonAvatarUp.setX((W() / 2) - buttonAvatarUp.getWidth()
                - (DefaultConsts.ELEMENT_MARGIN / 2));
        {
            float h2 = spriteTitle.getY() - labelAvatar.getTop();
            buttonAvatarUp.setY((h2 / 2) + labelAvatar.getTop() + (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        buttonAvatarDown = new ImageButton(AssetLoader.skin, "down");
        buttonAvatarDown.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonAvatarDown.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonAvatarDown.setX((W() / 2) - buttonAvatarUp.getWidth()
                - (DefaultConsts.ELEMENT_MARGIN / 2));
        {
            float h2 = spriteTitle.getY() - labelAvatar.getTop();
            buttonAvatarDown.setY((h2 / 2) + labelAvatar.getTop() - buttonAvatarDown.getHeight() - (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        imageAvatar.setX(buttonAvatarUp.getX() - imageAvatar.getWidth()
                - DefaultConsts.ELEMENT_MARGIN);

        labelAvatar.setWidth(imageAvatar.getWidth());
        labelAvatar.setX(imageAvatar.getX());

        labelAvatarShadow = LabelHelper.getLabelShadow(labelAvatar);

        updateAvatarComponents();

        // / AvatarOptions

        // EnvironmentOptions

        labelEnvironment = new Label("", AssetLoader.skin, "04b_19");
        labelEnvironment.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelEnvironment.setAlignment(Align.center, Align.bottom);
        labelEnvironment.setY((labelEnvironment.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        imageEnvironment = new Image();
        imageEnvironment.setSize(imageSize, imageSize);
        {
            float h2 = spriteTitle.getY() - labelEnvironment.getTop();
            imageEnvironment.setY((h2 / 2) + labelEnvironment.getTop() - (imageEnvironment.getHeight() / 2));
        }

        labelEnvironment.setWidth(imageEnvironment.getWidth());
        labelEnvironment.setX(imageEnvironment.getX());

        buttonEnvironmentUp = new ImageButton(AssetLoader.skin, "up");
        buttonEnvironmentUp.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonEnvironmentUp.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonEnvironmentUp.setX((W() / 2)
                + (DefaultConsts.ELEMENT_MARGIN / 2));
        {
            float h2 = spriteTitle.getY() - labelEnvironment.getTop();
            buttonEnvironmentUp.setY((h2 / 2) + labelEnvironment.getTop() + (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        buttonEnvironmentDown = new ImageButton(AssetLoader.skin, "down");
        buttonEnvironmentDown.setWidth(DefaultConsts.ELEMENT_WIDTH);
        buttonEnvironmentDown.setHeight(DefaultConsts.ELEMENT_HEIGHT);
        buttonEnvironmentDown.setX((W() / 2)
                + (DefaultConsts.ELEMENT_MARGIN / 2));
        {
            float h2 = spriteTitle.getY() - labelEnvironment.getTop();
            buttonEnvironmentDown.setY((h2 / 2) + labelEnvironment.getTop() - buttonEnvironmentDown.getHeight() - (DefaultConsts.ELEMENT_MARGIN / 2));
        }

        imageEnvironment.setX(buttonEnvironmentUp.getX() + buttonEnvironmentUp.getWidth()
                + DefaultConsts.ELEMENT_MARGIN);

        labelEnvironment.setWidth(imageEnvironment.getWidth());
        labelEnvironment.setX(imageEnvironment.getX());

        labelEnvironmentShadow = LabelHelper.getLabelShadow(labelEnvironment);

        updateEnvironmentComponents();

        // / EnvironmentOptions

    }

    protected void updateAvatarComponents() {

        String text = itemsDisplayAvatar[
                Arrays.asList(itemsValueAvatar).indexOf(
                        GameOptionsPreferences.getAvatar())];

        labelAvatar.setText(text);
        labelAvatarShadow.setText(text);

        String valueAvatar = String.format("balls/%s",
                GameOptionsPreferences.getAvatar());

        imageAvatar.setDrawable(new SpriteDrawable(new Sprite(
                AssetLoader.atlas.findRegion(valueAvatar))));

    }

    protected void updateEnvironmentComponents() {

        String text = itemsDisplayEnvironment[
                Arrays.asList(itemsValueEnvironment).indexOf(
                        GameOptionsPreferences.getEnvironment())];

        labelEnvironment.setText(text);
        labelEnvironmentShadow.setText(text);

        String valueEnvironment = String.format("pipes/%s/all",
                GameOptionsPreferences.getEnvironment());

        imageEnvironment.setDrawable(new SpriteDrawable(new Sprite(
                AssetLoader.atlas.findRegion(valueEnvironment))));

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(buttonAvatarUp);
        groupElements.addActor(buttonAvatarDown);
        groupElements.addActor(imageAvatar);
        groupElements.addActor(labelAvatarShadow);
        groupElements.addActor(labelAvatar);

        groupElements.addActor(buttonEnvironmentUp);
        groupElements.addActor(buttonEnvironmentDown);
        groupElements.addActor(imageEnvironment);
        groupElements.addActor(labelEnvironmentShadow);
        groupElements.addActor(labelEnvironment);

    }

    @Override
    protected void setupEvents() {

        ClickListener onAvatarUpClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAvatarUp();
            }
        };

        ClickListener onAvatarDownClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onAvatarDown();
            }
        };

        ClickListener onEnvironmentUpClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onEnvironmentUp();
            }
        };

        ClickListener onEnvironmentDownClickListener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onEnvironmentDown();
            }
        };

        // AvatarOptions : UP
        buttonAvatarUp.addListener(onAvatarUpClickListener);

        // AvatarOptions : DOWN
        buttonAvatarDown.addListener(onAvatarDownClickListener);
        imageAvatar.addListener(onAvatarDownClickListener);
        labelAvatar.addListener(onAvatarDownClickListener);
        labelAvatarShadow.addListener(onAvatarDownClickListener);

        // EnvironmentOptions : UP
        buttonEnvironmentUp.addListener(onEnvironmentUpClickListener);

        // EnvironmentOptions : DOWN
        buttonEnvironmentDown.addListener(onEnvironmentDownClickListener);
        imageEnvironment.addListener(onEnvironmentDownClickListener);
        labelEnvironment.addListener(onEnvironmentDownClickListener);
        labelEnvironmentShadow.addListener(onEnvironmentDownClickListener);

    }


    protected void onAvatarUp() {
        itemIndexAvatar++;
        if (itemIndexAvatar >= itemsValueAvatar.length) {
            itemIndexAvatar = 0;
        }

        GameOptionsPreferences.setAvatar(itemsValueAvatar[itemIndexAvatar]);
        GameOptionsPreferences.save();

        updateAvatarComponents();
    }

    protected void onAvatarDown() {
        itemIndexAvatar--;
        if (itemIndexAvatar < 0) {
            itemIndexAvatar = itemsValueAvatar.length - 1;
        }

        GameOptionsPreferences.setAvatar(itemsValueAvatar[itemIndexAvatar]);
        GameOptionsPreferences.save();

        updateAvatarComponents();
    }

    protected void onEnvironmentUp() {
        itemIndexEnvironment++;
        if (itemIndexEnvironment >= itemsValueEnvironment.length) {
            itemIndexEnvironment = 0;
        }

        GameOptionsPreferences.setEnvironment(itemsValueEnvironment[itemIndexEnvironment]);
        GameOptionsPreferences.save();

        updateEnvironmentComponents();
    }

    protected void onEnvironmentDown() {
        itemIndexEnvironment--;
        if (itemIndexEnvironment < 0) {
            itemIndexEnvironment = itemsValueEnvironment.length - 1;
        }

        GameOptionsPreferences.setEnvironment(itemsValueEnvironment[itemIndexEnvironment]);
        GameOptionsPreferences.save();

        updateEnvironmentComponents();
    }

}
