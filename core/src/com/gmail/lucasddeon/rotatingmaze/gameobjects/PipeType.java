package com.gmail.lucasddeon.rotatingmaze.gameobjects;

/**
 * Tipos de Tubulação
 */
public enum PipeType {

    // Tubulação Horizontal
    // ----
    //
    // ----
    HORIZONTAL,

    // Tubulações Verticais
    // |  |
    // |  |
    // |  |
    VERTICAL_UP,
    VERTICAL_DOWN,

    // Tubulação Direita-Baixo
    // ---|
    //    |
    //    |
    RIGHT_BOTTOM,

    // Tubulação Cima-Direita
    // |---
    // |
    // |
    TOP_RIGHT,

    // Tubulação Baixo-Direita
    // |
    // |
    // |---
    BOTTOM_RIGHT,

    // Tubulação Direita-Cima
    //    |
    //    |
    // ---|
    RIGHT_TOP

}
