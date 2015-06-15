package com.gmail.lucasddeon.rotatingmaze.gameobjects;

/**
 * Tipos de Tubulacao
 */
public enum PipeType {

    // Tubulacao Horizontal
    // ----
    //
    // ----
    HORIZONTAL,

    // Tubulacoes Verticais
    // |  |
    // |  |
    // |  |
    VERTICAL_UP,
    VERTICAL_DOWN,

    // Tubulacao Direita-Baixo
    // ---|
    //    |
    //    |
    RIGHT_BOTTOM,

    // Tubulacao Cima-Direita
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
