package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Tubulacao Abstrata
 */
public abstract class PipeShape {

    /**
     * Tipo da tubulacao
     */
    protected PipeType pipeType;

    /**
     * Largura total da tubulacao
     */
    protected float width;
    protected float midWidth;

    /**
     * Altura total da tubulacao
     */
    protected float height;
    protected float midHeight;

    /**
     * Espessura da tubulacao
     */
    protected float thickness;
    protected float midThickness;

    /**
     * Posicao central da tubulacao
     */
    protected Vector2 position;

    /**
     * Bounds da tubulacao
     */
    protected Rectangle boundsRectangle;


    /**
     * Retangulos da tubulacao
     * Utilizados para a deteccao de colisoes
     */
    protected Rectangle[] boundingRectangles;

    /**
     * Tipos dos ret�ngulos da tubulacao
     */
    protected RectangleType[] boundingRectangleTypes;


    /**
     * Construtor da tubulacao
     * @param pipeType Tipo da tubulacao
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
     * Configura os retangulos da tubulacao
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
     * Retorna a posicao central da tubulacao
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Retorna a posicao central da proxima tubulacao
     * @return
     */
    public abstract Vector2 getNextPosition();

    /**
     * Retorna o boundsRectangle da tubulacao
     * @return
     */
    public Rectangle getBoundsRectangle() {
        return boundsRectangle;
    }

    /**
     * Retorna os ret�ngulos da tubulacao
     * Utilizados para a detec��o de colisoes
     * @return
     */
    public Rectangle[] getBoundingRectangles() {
        return boundingRectangles;
    }

    /**
     * Retorna os tipos dos retangulos da tubulacao
     * @return
     */
    public RectangleType[] getBoundingRectangleTypes() {
        return boundingRectangleTypes;
    }

}
