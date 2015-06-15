package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.badlogic.gdx.math.Vector2;
import com.gmail.lucasddeon.rotatingmaze.gameobjects.*;

import java.util.Random;

/**
 *
 */
public class PipeShapeCreator {

    private static final Random r = new Random();

    private static int repetitionMax;
    private static float defaultPipeWidth;
    private static float defaultPipeHeight;
    private static float defaultPipeThickness;

    static {
        repetitionMax = 1;
    }

    public static PipeShape[] create(int length) {

        return create(length, 0, 0);

    }

    public static PipeShape[] create(int length,
                                     float width, float height, float thickness) {

        return create(length, 0, 0, width, height, thickness);

    }

    public static PipeShape[] create(int length, float x, float y) {

        return create(length, x, y,
                defaultPipeWidth, defaultPipeHeight, defaultPipeThickness);

    }

    public static PipeShape[] create(int length, float x, float y,
                                     float width, float height, float thickness) {

        PipeType[] allPts = PipeType.values();
        int allPtsLength = allPts.length;

        PipeType[] pts = new PipeType[length];

        // Tubulacao inicial
        pts[0] = PipeTypeHelper.starts;
        pts[1] = PipeTypeHelper.starts;

        int repetition = 1;
        boolean w;

        // Resto da tubulacao
        for (int i = 2; i < length; i++) {
            do {
                pts[i] = allPts[r.nextInt(allPtsLength)];

                w = false;
                if (!PipeTypeHelper.allows(pts[i - 1], pts[i])) {
                    w = true ;
                } else {
                    if (pts[i - 1] == pts[i]) {
                        if (repetition >= repetitionMax) {
                            w = true;
                        }
                    }
                }
            } while (w);
            if (pts[i] == pts[i - 1]) {
                repetition++;
            } else {
                repetition = 0;
            }
        }

        return create(pts, x, y, width, height, thickness);

    }



    public static PipeShape[] create(PipeType[] pts) {

        return create(pts, 0, 0);

    }


    public static PipeShape[] create(PipeType[] pts,
                                     float width, float height, float thickness) {

        return create(pts, 0, 0, width, height, thickness);

    }

    public static PipeShape[] create(PipeType[] pts, float x, float y) {

        return create(pts, x, y,
                defaultPipeWidth, defaultPipeHeight, defaultPipeThickness);

    }

    public static PipeShape[] create(PipeType[] pts, float x, float y,
                                     float width, float height, float thickness) {

        PipeShape[] pss = new PipeShape[pts.length];

        Vector2 position = new Vector2(x, y);

        int i = 0;
        for (PipeType pt : pts) {

            PipeShape ps = null;

            switch (pt) {
                case RIGHT_TOP:
                    ps = new RightTopPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case HORIZONTAL:
                    ps = new HorizontalPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case RIGHT_BOTTOM:
                    ps = new RightBottomPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case BOTTOM_RIGHT:
                    ps = new BottomRightPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case TOP_RIGHT:
                    ps = new TopRightPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case VERTICAL_DOWN:
                    ps = new VerticalDownPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;

                case VERTICAL_UP:
                    ps = new VerticalUpPipeShape(
                            position.x, position.y, width, height, thickness);
                    break;
            }

            pss[i++] = ps;
            position = ps.getNextPosition();

        }

        return pss;

    }

    public static int getRepetitionMax() {
        return repetitionMax;
    }

    public static void setDefaultPipeWidth(float defaultPipeWidth) {
        PipeShapeCreator.defaultPipeWidth = defaultPipeWidth;
    }

    public static void setDefaultPipeHeight(float defaultPipeHeight) {
        PipeShapeCreator.defaultPipeHeight = defaultPipeHeight;
    }

    public static void setDefaultPipeThickness(float defaultPipeThickness) {
        PipeShapeCreator.defaultPipeThickness = defaultPipeThickness;
    }

}
