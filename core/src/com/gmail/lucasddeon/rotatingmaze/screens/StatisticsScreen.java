package com.gmail.lucasddeon.rotatingmaze.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.gmail.lucasddeon.rotatingmaze.RotatingMazeGame;
import com.gmail.lucasddeon.rotatingmaze.helpers.AssetLoader;
import com.gmail.lucasddeon.rotatingmaze.helpers.LabelHelper;
import com.gmail.lucasddeon.rotatingmaze.preferences.UserProfilePreferences;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 *
 */
public class StatisticsScreen extends AbstractScreen {

    public static final class Consts {

        public static final String ICON = "statistics";
        public static final String TITLE = "statistics";
        public static final String BACKGROUND = "others";

        public static final float IMAGE_SIZE_PERCENTAGE = 50 / 100f;

    }

    protected Image imageCurrentLevel;
    protected Label labelCurrentLevel;
    protected Label labelCurrentLevelShadow;

    protected Image imageTotalTime;
    protected Label labelTotalTime;
    protected Label labelTotalTimeShadow;


    public StatisticsScreen(Screen fromScreen) {
        super(fromScreen, Consts.ICON, Consts.TITLE, Consts.BACKGROUND);

    }


    @Override
    protected void setupElements() {

        float imageSize = MinWH() * Consts.IMAGE_SIZE_PERCENTAGE;

        // CurrentLevel

        labelCurrentLevel = new Label("", AssetLoader.skin, "04b_19");
        {
            String text;
            text = UserProfilePreferences.getCurrentLevel() + "";
            labelCurrentLevel.setText(text);
        }
        labelCurrentLevel.setWidth(W() / 2);
        labelCurrentLevel.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelCurrentLevel.setAlignment(Align.center, Align.bottom);
        labelCurrentLevel.setX(0);
        labelCurrentLevel.setY((labelCurrentLevel.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        labelCurrentLevelShadow = LabelHelper.getLabelShadow(labelCurrentLevel);

        imageCurrentLevel = new Image(AssetLoader.atlas.findRegion("buttons/podium"));
        imageCurrentLevel.setSize(imageSize, imageSize);
        imageCurrentLevel.setX(((W() / 4) - (imageSize / 2)));
        {
            float h2 = spriteTitle.getY() - labelCurrentLevel.getTop();
            imageCurrentLevel.setY((h2 / 2) + labelCurrentLevel.getTop() - (imageCurrentLevel.getHeight() / 2));
        }

        // / CurrentLevel

        // TotalTime

        labelTotalTime = new Label("", AssetLoader.skin, "04b_19");
        {
            int time, hours, minutes, seconds;
            time = UserProfilePreferences.getTotalTime();
            hours = (int)Math.floor(time / (60 * 60));
            time -= hours * 60 * 60;
            minutes = (int)Math.floor(time / 60);
            time -= minutes * 60;
            seconds = time;

            String text = String.format("%d:%02d:%02d", hours, minutes, seconds);
            labelTotalTime.setText(text);
        }
        labelTotalTime.setWidth(W() / 2);
        labelTotalTime.setFontScale(((float)W()) / RotatingMazeGame.ScreenConfig.WIDTH);
        labelTotalTime.setAlignment(Align.center, Align.bottom);
        labelTotalTime.setX(W() / 2);
        labelTotalTime.setY((labelTotalTime.getStyle().font.getData().lineHeight / 2) +
                DefaultConsts.ELEMENT_BOTTOM_Y);

        labelTotalTimeShadow = LabelHelper.getLabelShadow(labelTotalTime);

        imageTotalTime = new Image(AssetLoader.atlas.findRegion("buttons/clock"));
        imageTotalTime.setSize(imageSize, imageSize);
        imageTotalTime.setX(((3 * W()) / 4) - (imageSize / 2));
        {
            float h2 = spriteTitle.getY() - labelTotalTime.getTop();
            imageTotalTime.setY((h2 / 2) + labelTotalTime.getTop() - (imageTotalTime.getHeight() / 2));
        }

        // / TotalTime

    }

    @Override
    protected void placeElements() {

        groupElements.addActor(imageCurrentLevel);
        groupElements.addActor(labelCurrentLevelShadow);
        groupElements.addActor(labelCurrentLevel);

        groupElements.addActor(imageTotalTime);
        groupElements.addActor(labelTotalTimeShadow);
        groupElements.addActor(labelTotalTime);

    }

}
