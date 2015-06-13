package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class HelpScreen extends AbstractScreen {

    /**
     *
     */
    private static final class Assets {

        public static final Texture textureDevice =
                new Texture(Gdx.files.internal("img/others/device.png"));

        public static final Texture textureDeviceSlider =
                new Texture(Gdx.files.internal("img/others/device_slider.png"));

        public static final Texture textureDeviceSliderKnob =
                new Texture(Gdx.files.internal("img/others/device_slider_knob.png"));

        static {

            textureDevice.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

            textureDeviceSlider.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

            textureDeviceSliderKnob.setFilter(
                    Texture.TextureFilter.Nearest,
                    Texture.TextureFilter.Nearest);

        }

    }

    /**
     *
     */
    private static final class Consts {

        public static final String ICON = "help";
        public static final String TITLE = "help";
        public static final String BACKGROUND = "others";

        public static final float IMAGE_SIZE_PERCENTAGE = 50 / 100f;
        public static final float IMAGE_SIZE_RATIO = 352 / 231f;

        public static final float IMAGE_SLIDER_SIZE_RATIO_TO_KNOB = 154 / 20f;
        public static final float IMAGE_SLIDER_KNOB_LEFT_RATIO = 273 / 352f;
        public static final float IMAGE_SLIDER_KNOB_SIZE_RATIO = 20 / 231f;

    }

    protected Image imageDevice;
    protected Image imageDeviceSlider;
    protected Image imageDeviceSliderKnob;


    public HelpScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }

    @Override
    protected void setupElements() {

        float imageSize = MinWH() * Consts.IMAGE_SIZE_PERCENTAGE;

        // Device

        imageDevice = new Image(Assets.textureDevice);

        imageDevice.setSize(imageSize * Consts.IMAGE_SIZE_RATIO, imageSize);
        imageDevice.setX((W() / 2) - (imageDevice.getWidth() / 2));
        imageDevice.setY((spriteTitle.getY() / 2) - (imageDevice.getHeight() / 2));

        imageDevice.setOrigin(0, imageDevice.getHeight() / 2);

        // / Device

        // Device Slider

        imageDeviceSlider = new Image(Assets.textureDeviceSlider);

        imageDeviceSlider.setSize(imageSize * Consts.IMAGE_SIZE_RATIO, imageSize);
        imageDeviceSlider.setX((W() / 2) - (imageDeviceSlider.getWidth() / 2));
        imageDeviceSlider.setY((spriteTitle.getY() / 2) - (imageDeviceSlider.getHeight() / 2));

        imageDeviceSlider.setOrigin(0, imageDeviceSlider.getHeight() / 2);

        // Device Slider

        // Device Slider Knob

        imageDeviceSliderKnob = new Image(Assets.textureDeviceSliderKnob);

        imageDeviceSliderKnob.setSize(
                imageSize * Consts.IMAGE_SLIDER_KNOB_SIZE_RATIO,
                imageSize * Consts.IMAGE_SLIDER_KNOB_SIZE_RATIO);
        imageDeviceSliderKnob.setX(imageDeviceSlider.getX() +
                (imageDeviceSlider.getWidth() * Consts.IMAGE_SLIDER_KNOB_LEFT_RATIO) -
                (imageDeviceSliderKnob.getWidth() / 2));
        imageDeviceSliderKnob.setY((spriteTitle.getY() / 2) -
                (imageDeviceSliderKnob.getHeight() / 2));

        // / Device Slider Knob

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(imageDeviceSlider);
        groupElements.addActor(imageDeviceSliderKnob);
        groupElements.addActor(imageDevice);

    }

    @Override
    protected void setupActions() {

        final float degrees = 8f;
        final float duration = 0.5f;
        final float distance = (imageDeviceSliderKnob.getHeight() *
                Consts.IMAGE_SLIDER_SIZE_RATIO_TO_KNOB / 2);

        stage.addAction(
            Actions.forever(
                Actions.sequence(
                    Actions.run(new Runnable() {
                        public void run() {
                            imageDevice.setVisible(true);
                            imageDeviceSlider.setVisible(false);
                            imageDeviceSliderKnob.setVisible(false);
                        }
                    }),
                    Actions.run(new Runnable() {
                        public void run() {
                            imageDevice.addAction(
                                Actions.sequence(
                                    Actions.rotateBy(degrees, duration),
                                    Actions.rotateBy(-(degrees * 2), duration * 2),
                                    Actions.rotateBy(degrees, duration)
                                )
                            );
                        }
                    }),
                    Actions.delay(duration * 4),
                    Actions.run(new Runnable() {
                        public void run() {
                            imageDevice.setVisible(false);
                            imageDeviceSlider.setVisible(true);
                            imageDeviceSliderKnob.setVisible(true);

                            imageDevice.clearActions();
                            imageDeviceSlider.clearActions();
                            imageDeviceSliderKnob.clearActions();
                        }
                    }),
                    Actions.run(new Runnable() {
                        public void run() {
                            imageDeviceSliderKnob.addAction(
                                Actions.sequence(
                                    Actions.moveBy(0, distance, duration),
                                    Actions.moveBy(0, -(distance * 2), duration * 2),
                                    Actions.moveBy(0, distance, duration)
                                )
                            );
                        }
                    }),
                    Actions.delay(duration * 4),
                    Actions.run(new Runnable() {
                        public void run() {
                            imageDevice.clearActions();
                            imageDeviceSlider.clearActions();
                            imageDeviceSliderKnob.clearActions();
                        }
                    })
                )
            )
        );

    }

}
