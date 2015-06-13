package com.gmail.lucasddeon.rotatingmaze.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.PipeShapePath;
import com.gmail.lucasddeon.rotatingmaze.helpers.PipeShapeCreator;

import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.H;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.MinWH;
import static com.gmail.lucasddeon.rotatingmaze.helpers.ScreenHelper.W;

/**
 * Opções de dificuldade do jogo
 */
public class GameOptions {

    /**
     * Classe estátita com constantes das opções
     */
    protected static final class Consts {

        // Velocidade
        public final static float DISTANCE_VELOCITY_INITIAL = 2.50f;
        public static final float DISTANCE_VELOCITY_STEP = 1 / 2f;
        // / Velocidade

        // Tamanho das tubulações
        public final static float PIPE_SIZE_SCALE_INITIAL = 3.00f;
        public final static float PIPE_SIZE_SCALE_MIN = 2.50f;
        public final static float PIPE_SIZE_SCALE_STEP = 0.01f;
        // / Tamanho das tubulações

        // Comprimento do caminho das tubulações
        public final static int PIPE_SHAPE_PATH_LENGTH_INITIAL = 5;
        public final static int PIPE_SHAPE_PATH_LENGTH_STEP = 2;
        // / Comprimento do caminho das tubulações

        // Rotação
        public final static float ROTATION_MIN = -90f;
        public final static float ROTATION_MAX = -ROTATION_MIN;
        public final static float ROTATION_VELOCITY = 1f;
        // / Rotação

    }

    /* Nível do jogo */
    public final int level;

    /* Posição central (normalmente, não é modificado) */
    public final Vector2 centerPosition;

    // Bola
    /* Raio da bola */
    public final float ballRadius;
    /* Posição central da bola */
    public final Vector2 ballPosition;
    // / Bola

    // Tubulação
    /* Tamanho da tubulação */
    public final float pipeSize;
    /* Espessura da tubulação */
    public final float pipeThickness;
    /* Posição inicial do caminho das tubulações */
    public final Vector2 pipePathPosition;
    /* Caminho das tubulações */
    public final PipeShapePath pipeShapePath;
    // / Tubulação

    // Rotação
    public final float rotationMin;
    public final float rotationMax;
    public final float rotationVelocity;
    // / Rotação

    // Distância
    public final float distanceVelocity;
    // / Distância

    /**
     *
     * @param level
     */
    public GameOptions(int level) {

        this.level = level;

        this.centerPosition = new Vector2(W() / 4, H() / 2);

        // Bola
        this.ballRadius = MinWH() / 8;
        this.ballPosition = new Vector2(0, 0);

        // Tubulação
        this.pipeSize = getPipeSizeByLevel(level);
        this.pipeThickness = ballRadius * 0.25f;
        this.pipePathPosition = new Vector2((ballRadius * 2) + (pipeThickness * 2), 0);
        this.pipeShapePath = getPipeShapePathByLevel(level);

        // Rotação
        this.rotationMin = Consts.ROTATION_MIN;
        this.rotationMax = Consts.ROTATION_MAX;
        this.rotationVelocity = Consts.ROTATION_VELOCITY;

        // Distância
        this.distanceVelocity = getDistanceVelocityByLevel(level);

    }

    /**
     *
     * @param level
     * @return
     */
    protected float getPipeSizeScaleByLevel(int level) {

        float scale = Consts.PIPE_SIZE_SCALE_INITIAL - ((level - 1) * Consts.PIPE_SIZE_SCALE_STEP);
        if (scale < Consts.PIPE_SIZE_SCALE_MIN) {
            scale = Consts.PIPE_SIZE_SCALE_MIN;
        }

        return scale;

    }

    /**
     *
     * @param level
     * @return
     */
    protected float getPipeSizeByLevel(int level) {

        float scale = getPipeSizeScaleByLevel(level);
        float pipeSize = ((ballRadius * 2) * scale) + (2 * pipeThickness);

        return pipeSize;

    }

    /**
     *
     * @param level
     * @return
     */
    protected int getPipeShapePathLengthByLevel(int level) {

        int initial = Consts.PIPE_SHAPE_PATH_LENGTH_INITIAL;
        int step = Consts.PIPE_SHAPE_PATH_LENGTH_STEP;

        int length = initial + (step * (level - 1));

        return length;

    }

    /**
     *
     * @param level
     * @return
     */
    protected PipeShapePath getPipeShapePathByLevel(int level) {

        int length = getPipeShapePathLengthByLevel(level);

        PipeShapeCreator.setDefaultPipeWidth(this.pipeSize);
        PipeShapeCreator.setDefaultPipeHeight(this.pipeSize);
        PipeShapeCreator.setDefaultPipeThickness(this.pipeThickness);

        return new PipeShapePath(
                PipeShapeCreator.create(length,
                        this.pipePathPosition.x,
                        this.pipePathPosition.y));

    }

    /**
     *
     * @param level
     * @return
     */
    protected float getDistanceVelocityByLevel(int level) {

        float distanceVelocity = Consts.DISTANCE_VELOCITY_INITIAL +
                ((level - 1) * Consts.DISTANCE_VELOCITY_STEP);

        return distanceVelocity;

    }

}
