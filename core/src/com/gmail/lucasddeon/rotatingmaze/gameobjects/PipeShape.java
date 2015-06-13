package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Tubulação Abstrata
 */
public abstract class PipeShape {

    /**
     * Tipo da tubulação
     */
    protected PipeType pipeType;

    /**
     * Largura total da tubulação
     */
    protected float width;
    protected float midWidth;

    /**
     * Altura total da tubulação
     */
    protected float height;
    protected float midHeight;

    /**
     * Espessura da tubulação
     */
    protected float thickness;
    protected float midThickness;

    /**
     * Posição central da tubulação
     */
    protected Vector2 position;

    /**
     * Bounds da tubulação
     */
    protected Rectangle boundsRectangle;


    /**
     * Retângulos da tubulação
     * Utilizados para a detecção de colisões
     */
    protected Rectangle[] boundingRectangles;

    /**
     * Tipos dos retângulos da tubulação
     */
    protected RectangleType[] boundingRectangleTypes;


    /**
     * Construtor da tubulação
     * @param pipeType Tipo da tubulação
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
     * Configura os retângulos da tubulação
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
     * Retorna o tipo da tubulação
     * @return
     */
    public PipeType getPipeType() {
        return pipeType;
    }

    /**
     * Retorna a largura total da tubulação
     * @return
     */
    public float getWidth() {
        return width;
    }

    /**
     * Retorna a altura total da tubulação
     * @return
     */
    public float getHeight() {
        return height;
    }

    /**
     * Retorna a espessura da tubulação
     * @return
     */
    public float getThickness() {
        return thickness;
    }

    /**
     * Retorna a posição central da tubulação
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Retorna a posição central da próxima tubulação
     * @return
     */
    public abstract Vector2 getNextPosition();

    /**
     * Retorna o boundsRectangle da tubulação
     * @return
     */
    public Rectangle getBoundsRectangle() {
        return boundsRectangle;
    }

    /**
     * Retorna os retângulos da tubulação
     * Utilizados para a detecção de colisões
     * @return
     */
    public Rectangle[] getBoundingRectangles() {
        return boundingRectangles;
    }

    /**
     * Retorna os tipos dos retângulos da tubulação
     * @return
     */
    public RectangleType[] getBoundingRectangleTypes() {
        return boundingRectangleTypes;
    }

}
