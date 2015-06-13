package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.gmail.lucasddeon.rotatingmaze.helpers.RectangleCreator;

/**
 * Caminho formado por um conjunto de Tubulações
 */
public class PipeShapePath {

    protected PipeShape[] shapes;
    protected Rectangle finishRectangle;

    public PipeShapePath(PipeShape[] shapes) {

        this.shapes = shapes;

        PipeShape lastPipeShape = this.shapes[this.shapes.length - 1];
        finishRectangle = RectangleCreator.getFinishRectangle(lastPipeShape);

    }

    public PipeShape[] getShapes() {
        return shapes;
    }

    public Rectangle getFinishRectangle() {
        return finishRectangle;
    }

}
