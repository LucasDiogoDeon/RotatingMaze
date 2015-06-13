package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.LabelHelper;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class InfoScreen extends AbstractScreen {

    /**
     *
     */
    private static final class Consts {

        public static final String ICON = "info";
        public static final String TITLE = "info";
        public static final String BACKGROUND = "others";

        public static final float IMAGE_SIZE_PERCENTAGE = 50 / 100f;

    }

    protected Image imageProgrammer;
    protected Label labelProgrammer;
    protected Label labelProgrammerShadow;


    public InfoScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupElements() {

        float imageSize;
        imageSize = Math.min(W(), H()) * Consts.IMAGE_SIZE_PERCENTAGE;

        // Programmer

        labelProgrammer = new Label("", AssetLoader.skin, "04b_19");
        labelProgrammer.setText(RotatingMazeGame.Config.PROGRAMMER_NAME);
        labelProgrammer.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelProgrammer.setWidth(W());
        labelProgrammer.setAlignment(Align.center, Align.bottom);
        labelProgrammer.setX(0);
        labelProgrammer.setY((labelProgrammer.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        labelProgrammerShadow = LabelHelper.getLabelShadow(labelProgrammer);

        imageProgrammer = new Image(AssetLoader.atlas.findRegion("buttons/programmer"));
        imageProgrammer.setSize(imageSize, imageSize);
        imageProgrammer.setX((W() / 2) - (imageSize / 2));
        {
            float h2 = spriteTitle.getY() - labelProgrammer.getTop();
            imageProgrammer.setY((h2 / 2) + labelProgrammer.getTop() - (imageProgrammer.getHeight() / 2));
        }

        // / Programmer

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(imageProgrammer);
        groupElements.addActor(labelProgrammerShadow);
        groupElements.addActor(labelProgrammer);

    }

}
