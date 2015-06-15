package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Tubulacao Vertical Abstrata
 */
public abstract class VerticalPipeShape extends PipeShape {

    /**
     * Construtor da tubulacao
     * @param pipeType Tipo da tubulacao
     * @param x        Primeira coordenada x : horizontal
     * @param y        Primeira coordenada y : vertical
     */
    protected VerticalPipeShape(PipeType pipeType, float x, float y,
                                float width, float height, float thickness) {
        super(pipeType, x, y, width, height, thickness);
    }

    /**
     * Configura os retangulos da tubulacao
     */
    @Override
    protected void configBoundingRectangles() {

        boundingRectangles = new Rectangle[2];
        boundingRectangleTypes = new RectangleType[2];

        int i = -1;

        // Left
        i++;
        boundingRectangles[i] = RectangleCreator.getLeft(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.LEFT;

        // Right
        i++;
        boundingRectangles[i] = RectangleCreator.getRight(
                position, width, height, thickness);
        boundingRectangleTypes[i] = RectangleType.RIGHT;

    }

}
