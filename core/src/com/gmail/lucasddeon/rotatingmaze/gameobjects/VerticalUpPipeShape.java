package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Tubula��o Vertical Cima
 */
public class VerticalUpPipeShape extends VerticalPipeShape {

    /**
     * Construtor da tubula��o
     * @param x        Primeira coordenada x : horizontal
     * @param y        Primeira coordenada y : vertical
     */
    public VerticalUpPipeShape(float x, float y,
                               float width, float height, float thickness) {

        super(PipeType.VERTICAL_UP, x, y, width, height, thickness);

    }

    /**
     * Retorna a posi��o central da pr�xima tubula��o
     * @return
     */
    @Override
    public Vector2 getNextPosition() {
        float x = position.x;
        float y = position.y - height;
        return new Vector2(x, y);
    }

}