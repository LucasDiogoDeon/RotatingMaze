package com.gmail.lucasddeon.rotatingmaze.preferences.enums;

/**
 *
 */
public enum EnvironmentOptions {

    CAKE("cake", "Cake"),
    CASTLE("castle", "Castle"),
    CHOCO("choco", "Choco"),
    DIRT("dirt", "Dirt"),
    GRASS("grass", "Grass"),
    PURPLE("purple", "Purple"),
    SAND("sand", "Sand"),
    SNOW("snow", "Snow"),
    TUNDRA("tundra", "Tundra");

    private String value;
    private String display;

    public String getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    /**
     *
     * @param value
     * @param display
     */
    EnvironmentOptions(String value, String display) {
        this.value = value;
        this.display = display;
    }

    /**
     *
     * @return
     */
    public static String[] getValues() {
        EnvironmentOptions[] values = EnvironmentOptions.values();
        int l = values.length;
        String[] result = new String[l];
        for (int i = 0; i < l; i++) {
            result[i] = values[i].getValue();
        }
        return result;
    }

    /**
     *
     * @return
     */
    public static String[] getDisplays() {
        EnvironmentOptions[] values = EnvironmentOptions.values();
        int l = values.length;
        String[] result = new String[l];
        for (int i = 0; i < l; i++) {
            result[i] = values[i].getDisplay();
        }
        return result;
    }

}
