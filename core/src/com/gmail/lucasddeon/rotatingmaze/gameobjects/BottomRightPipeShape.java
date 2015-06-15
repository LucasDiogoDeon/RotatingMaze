package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubula��o Baixo-Direita
 */
public class BottomRightPipeShape extends PipeShape {

    public BottomRightPipeShape(float x, float y,
                                float width, float height, float thickness) {

        super(PipeType.BOTTOM_RIGHT, x, y, width, height, thickness);

    }

    /**
     * Configura os retangulos da tubulacao
     */
    @Override
    protected void configBoundingRectangles() {

        boundingRectangles = new Rectangle[4];
        boundingRectangleTypes = new RectangleType[4];

        int i = -1;

        // Left
        i++;
        boundingRectangles[i] = RectangleCreator.getLeft(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT;

        // Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.BOTTOM;

        // Corner Inner Right Top
        i++;
        boundingRectangles[i] = RectangleCreator.getInnerRightTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT_TOP;

        // Corner Outer Right Top
        i++;
        boundingRectangles[i] = RectangleCreator.getOuterRightTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT_TOP;

    }

    /**
     * Retorna a posicao central da proxima tubulacao
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x + width;
        float y = position.y;
        return new Vector2(x, y);
    }

}
