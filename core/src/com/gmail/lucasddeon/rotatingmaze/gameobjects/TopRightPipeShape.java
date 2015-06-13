package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubulação Cima-Direita
 */
public class TopRightPipeShape extends PipeShape {

    public TopRightPipeShape(float x, float y,
                             float width, float height, float thickness) {

        super(PipeType.TOP_RIGHT, x, y, width, height, thickness);

    }

    /**
     * Configura os retângulos da tubulação
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

        // Top
        i++;
        boundingRectangles[i] = RectangleCreator.getTop(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.TOP;

        // Corner Inner Right Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getInnerRightBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT_BOTTOM;

        // Corner Outer Right Bottom
        i++;
        boundingRectangles[i] = RectangleCreator.getOuterRightBottom(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT_BOTTOM;
        
    }

    /**
     * Retorna a posição central da próxima tubulação
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x + width;
        float y = position.y;
        return new Vector2(x, y);
    }

}
