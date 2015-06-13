package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.gmail.lucasddeon.rotatingmaze.gameobjects.PipeType;

import java.util.*;

/**
 *
 */
public class PipeTypeHelper {

    public static final Map<PipeType, PipeType[]> dictionaryAllows;
    public static final List<PipeType> listCanStart;
    public static final PipeType starts;

    static {

        // Allows
        dictionaryAllows = new HashMap<PipeType, PipeType[]>();

        dictionaryAllows.put(PipeType.BOTTOM_RIGHT, new PipeType[]{
                PipeType.HORIZONTAL,
                PipeType.RIGHT_BOTTOM,
                PipeType.RIGHT_TOP
        });

        dictionaryAllows.put(PipeType.HORIZONTAL, new PipeType[]{
                PipeType.HORIZONTAL,
                PipeType.RIGHT_BOTTOM,
                PipeType.RIGHT_TOP
        });

        dictionaryAllows.put(PipeType.TOP_RIGHT, new PipeType[]{
                PipeType.HORIZONTAL,
                PipeType.RIGHT_BOTTOM,
                PipeType.RIGHT_TOP
        });

        dictionaryAllows.put(PipeType.RIGHT_TOP, new PipeType[]{
                PipeType.VERTICAL_UP,
                PipeType.TOP_RIGHT
        });

        dictionaryAllows.put(PipeType.RIGHT_BOTTOM, new PipeType[]{
                PipeType.VERTICAL_DOWN,
                PipeType.BOTTOM_RIGHT
        });

        dictionaryAllows.put(PipeType.VERTICAL_DOWN, new PipeType[]{
                PipeType.VERTICAL_DOWN,
                PipeType.BOTTOM_RIGHT
        });

        dictionaryAllows.put(PipeType.VERTICAL_UP, new PipeType[]{
                PipeType.VERTICAL_UP,
                PipeType.TOP_RIGHT
        });
        // / Allows

        // Starts
        starts = PipeType.HORIZONTAL;
        // / Starts

        // CanStart
        listCanStart = new ArrayList<PipeType>();
        listCanStart.add(PipeType.RIGHT_BOTTOM);
        listCanStart.add(PipeType.RIGHT_TOP);
        // / CanStart

    }

    /**
     *
     * @param pt1
     * @param pt2
     * @return
     */
    public static boolean allows(PipeType pt1, PipeType pt2) {

        return Arrays.asList(dictionaryAllows.get(pt1)).contains(pt2);

    }

    /**
     *
     * @param pt
     * @return
     */
    public static boolean canStart(PipeType pt) {

        return listCanStart.contains(pt);

    }

}
