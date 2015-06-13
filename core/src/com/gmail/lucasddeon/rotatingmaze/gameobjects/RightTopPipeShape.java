package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubulação Direita-Cima
 */
public class RightTopPipeShape extends PipeShape {

    public RightTopPipeShape(float x, float y,
                             float width, float height, float thickness) {

        super(PipeType.RIGHT_TOP, x, y, width, height, thickness);

    }

    /**
     * Configura os retângulos da tubulação
     */
    @Override
    protected void configBoundingRectangles() {

        boundingRectangles = new Rectangle[4];
        boundingRectangleTypes = new RectangleType[4];

        int i = -1;

        // Right
        i++;
        boundingRectangles[i] = RectangleCreator.getRight(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT;

        // Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.BOTTOM;

        // Corner Inner Left Top
        i++;
        boundingRectangles[i] = RectangleCreator.getInnerLeftTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT_TOP;

        // Corner Outer Left Top
        i++;
        boundingRectangles[i] = RectangleCreator.getOuterLeftTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT_TOP;
        
    }

    /**
     * Retorna a posição central da próxima tubulação
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x;
        float y = position.y - height;
        return new Vector2(x, y);
    }

}
