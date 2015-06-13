package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Tubula��o Abstrata
 */
public abstract class PipeShape {

    /**
     * Tipo da tubula��o
     */
    protected PipeType pipeType;

    /**
     * Largura total da tubula��o
     */
    protected float width;
    protected float midWidth;

    /**
     * Altura total da tubula��o
     */
    protected float height;
    protected float midHeight;

    /**
     * Espessura da tubula��o
     */
    protected float thickness;
    protected float midThickness;

    /**
     * Posi��o central da tubula��o
     */
    protected Vector2 position;

    /**
     * Bounds da tubula��o
     */
    protected Rectangle boundsRectangle;


    /**
     * Ret�ngulos da tubula��o
     * Utilizados para a detec��o de colis�es
     */
    protected Rectangle[] boundingRectangles;

    /**
     * Tipos dos ret�ngulos da tubula��o
     */
    protected RectangleType[] boundingRectangleTypes;


    /**
     * Construtor da tubula��o
     * @param pipeType Tipo da tubula��o
     * @param x        Primeira coordenada x : horizontal
     * @param y        Primeira coordenada y : vertical
     */
    protected PipeShape(PipeType pipeType,
                        float x, float y,
                        float width, float height, float thickness) {

        this.pipeType = pipeType;
        this.position = new Vector2(x, y);

        // ??
        this.width = width;
        this.height = height;
        this.thickness = thickness;
        // ??

        this.midWidth = width / 2;
        this.midHeight = height / 2;
        this.midThickness = thickness / 2;

        {
            // boundsRectangle
            float x1, y1, x2, y2;
            x1 = position.x - midWidth;
            y1 = position.y - midHeight;
            x2 = position.x + midWidth;
            y2 = position.y + midHeight;

            //this.boundsRectangle = new Bounds(x1, y1, x2, y2);
            this.boundsRectangle = new Rectangle(x1, y1, x2 - x1, y2 - y1);
            // / boundsRectangle
        }

        {
            // boundingRectangles
            configBoundingRectangles();
            // / boundingRectangles
        }

    }

    /**
     * Configura os ret�ngulos da tubula��o
     */
    protected abstract void configBoundingRectangles();


    /**
     *
     * @param deltaX
     * @param deltaY
     */
    public void update(float deltaX, float deltaY) {

        {
            // position
            position.set(position.x - deltaX, position.y - deltaY);
            // / position
        }

        {
            // boundsRectangle
            boundsRectangle.x -= deltaX;
            boundsRectangle.y -= deltaY;
            // / boundsRectangle
        }

        {
            // boundingRectangles
            for (Rectangle rect : boundingRectangles) {
                rect.x -= deltaX;
                rect.y -= deltaY;
            }
            // / boundingRectangles
        }

    }

    /**
     * Retorna o tipo da tubula��o
     * @return
     */
    public PipeType getPipeType() {
        return pipeType;
    }

    /**
     * Retorna a largura total da tubula��o
     * @return
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retorna a altura total da tubula��o
     * @return
     */
    public float getHeight() {
        return height;
    }

    /**
     * Retorna a espessura da tubula��o
     * @return
     */
    public float getThickness() {
        return thickness;
    }

    /**
     * Retorna a posi��o central da tubula��o
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Retorna a posi��o central da pr�xima tubula��o
     * @return
     */
    public abstract Vector2 getNextPosition();

    /**
     * Retorna o boundsRectangle da tubula��o
     * @return
     */
    public Rectangle getBoundsRectangle() {
        return boundsRectangle;
    }

    /**
     * Retorna os ret�ngulos da tubula��o
     * Utilizados para a detec��o de colis�es
     * @return
     */
    public Rectangle[] getBoundingRectangles() {
        return boundingRectangles;
    }

    /**
     * Retorna os tipos dos ret�ngulos da tubula��o
     * @return
     */
    public RectangleType[] getBoundingRectangleTypes() {
        return boundingRectangleTypes;
    }

}
