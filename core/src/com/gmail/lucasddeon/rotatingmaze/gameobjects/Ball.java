package com.gmail.lucasddeon.rotatingmaze.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;

/**
 * Bola central do jogo
 */
public class Ball {

    /**
     * Raio da bola
     */
    protected float radius;

    /**
     * Posicao central da bola na tela
     */
    protected Vector2 position;

    /**
     * Circulo da bola
     * Utilizada para a detec��o de colis�es
     */
    protected Circle boundingCircle;

    /**
     * Construtor da bola
     * @param x coordenada x : horizontal
     * @param y coordenada y : vertical
     * @param radius raio da bola
     */
    public Ball(float x, float y, float radius) {

        this.position = new Vector2(x, y);
        this.radius = radius;

        this.boundingCircle = new Circle(
                this.position.x, this.position.y, this.radius);

    }

    /**
     * Retorna o raio da bola
     * @return
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Retorna a posicao da bola na tela
     * @return
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Retorna a coordenada x : horizontal
     * @return
     */
    public float getX() {
        return position.x;
    }

    /**
     * Retorna a coordenada y : vertical
     * @return
     */
    public float getY() {
        return position.y;
    }

    /**
     * Retorna o circulo da bola
     * @return
     */
    public Circle getBoundingCircle() {
        return boundingCircle;
    }

}
