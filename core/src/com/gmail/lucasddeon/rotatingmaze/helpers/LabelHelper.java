package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

/**
 *
 */
public class LabelHelper {

    public static final int LABEL_SHADOW_OFFSET_X = -1;
    public static final int LABEL_SHADOW_OFFSET_Y = -1;

    /**
     *
     * @param label
     * @return
     */
    public static Label getLabelShadow(Label label) {

        Label labelShadow = new Label("", AssetLoader.skin, "04b_19_shadow");

        labelShadow.setText(label.getText());
        labelShadow.setWidth(label.getWidth());
        labelShadow.setFontScaleX(label.getFontScaleX());
        labelShadow.setFontScaleY(label.getFontScaleY());
        labelShadow.setAlignment(Align.center);
        labelShadow.setX(label.getX() + LABEL_SHADOW_OFFSET_X);
        labelShadow.setY(label.getY() + LABEL_SHADOW_OFFSET_Y);

        return labelShadow;

    }

}
