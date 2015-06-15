package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubulacao Direita-Baixo
 */
public class RightBottomPipeShape extends PipeShape {

    public RightBottomPipeShape(float x, float y,
                                float width, float height, float thickness) {

        super(PipeType.RIGHT_BOTTOM, x, y, width, height, thickness);

    }

    /**
     * Configura os retangulos da tubulacao
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

        // Top
        i++;
        boundingRectangles[i] = RectangleCreator.getTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.TOP;

        // Corner Inner Left Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getInnerLeftBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT_BOTTOM;

        // Corner Outer Left Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getOuterLeftBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT_BOTTOM;

        
    }

    /**
     * Retorna a posicao central da proxima tubulacao
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x;
        float y = position.y + height;
        return new Vector2(x, y);
    }

}
