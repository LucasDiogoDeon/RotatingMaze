package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.PipeShape;

/**
 *
 */
public class RectangleCreator {

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getTop(Vector2 position,
        float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y - ( height / 2 );
        w = width;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getRight(Vector2 position,
         float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x + ( width / 2 ) - thickness;
        y = position.y - ( height / 2 );
        w = thickness;
        h = width;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getBottom(Vector2 position,
        float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y + ( height / 2 ) - thickness;
        w = width;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getLeft(Vector2 position,
        float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y - ( height / 2 );
        w = thickness;
        h = width;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getInnerRightTop(Vector2 position,
                                             float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x + ( width / 2 ) - thickness;
        y = position.y - ( height / 2 );
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getOuterRightTop(Vector2 position,
                                             float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y + ( height / 2 ) - thickness;
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getInnerLeftTop(Vector2 position,
                                            float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y - ( height / 2 );
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getOuterLeftTop(Vector2 position,
                                            float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x + ( width / 2 ) - thickness;
        y = position.y + ( height / 2 ) - thickness;
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getInnerRightBottom(Vector2 position,
                                                float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x + ( width / 2 ) - thickness;
        y = position.y + ( height / 2 ) - thickness;
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getOuterRightBottom(Vector2 position,
                                                float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y - ( height / 2 );
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getInnerLeftBottom(Vector2 position,
                                               float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x - ( width / 2 );
        y = position.y + ( height / 2 ) - thickness;
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param position
     * @param width
     * @param height
     * @param thickness
     * @return
     */
    public static Rectangle getOuterLeftBottom(Vector2 position,
                                               float width, float height, float thickness) {

        float x, y, w, h;
        x = position.x + ( width / 2 ) - thickness;
        y = position.y - ( height / 2 );
        w = thickness;
        h = thickness;

        return new Rectangle(x, y, w, h);

    }

    /**
     *
     * @param lastPipeShape
     * @return
     */
    public static Rectangle getFinishRectangle(PipeShape lastPipeShape) {

        Vector2 position = lastPipeShape.getNextPosition();

        float width, height, thickness;
        width = lastPipeShape.getWidth();
        height = lastPipeShape.getHeight();
        thickness = lastPipeShape.getThickness();

        switch (lastPipeShape.getPipeType()) {
            case HORIZONTAL:
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                position.x -= lastPipeShape.getThickness();
                return getLeft(position, width, height, thickness);

            case VERTICAL_UP:
            case RIGHT_TOP:
                position.y += lastPipeShape.getThickness();
                return getBottom(position, width, height, thickness);

            case VERTICAL_DOWN:
            case RIGHT_BOTTOM:
                position.y -= lastPipeShape.getHeight();
                return getBottom(position, width, height, thickness);
        }

        return null;

    }

}
