package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubula��o Horizontal
 */
public class HorizontalPipeShape extends PipeShape {

    public HorizontalPipeShape(float x, float y,
                               float width, float height, float thickness) {

        super(PipeType.HORIZONTAL, x, y, width, height, thickness);

    }

    /**
     * Configura os ret�ngulos da tubula��o
     */
    @Override
    protected void configBoundingRectangles() {

        boundingRectangles = new Rectangle[2];
        boundingRectangleTypes = new RectangleType[2];

        int i = -1;

        // Top
        i++;
        boundingRectangles[i] = RectangleCreator.getTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.TOP;

        // Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.BOTTOM;

    }

    /**
     * Retorna a posi��o central da pr�xima tubula��o
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x + width;
        float y = position.y;
        return new Vector2(x, y);
    }

}
