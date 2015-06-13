package com.gmail.lucasddeon.rotatingmaze.gameobjects;

/**
 * Tipos de Tubula��o
 */
public enum PipeType {

    // Tubula��o Horizontal
    // ----
    //
    // ----
    HORIZONTAL,

    // Tubula��es Verticais
    // |  |
    // |  |
    // |  |
    VERTICAL_UP,
    VERTICAL_DOWN,

    // Tubula��o Direita-Baixo
    // ---|
    //    |
    //    |
    RIGHT_BOTTOM,

    // Tubula��o Cima-Direita
    // |---
    // |
    // |
    TOP_RIGHT,

    // Tubula��o Baixo-Direita
    // |
    // |
    // |---
    BOTTOM_RIGHT,

    // Tubula��o Direita-Cima
    //    |
    //    |
    // ---|
    RIGHT_TOP

}
