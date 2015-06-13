package com.gmail.lucasddeon.rotatingmaze.preferences.enums;

/**
 *
 */
public enum AvatarOptions {

    ELEPHANT("elephant", "Elephant"),
    GIRAFFE("giraffe", "Giraffe"),
    HIPPO("hippo", "Hippo"),
    MONKEY("monkey", "Monkey"),
    PANDA("panda", "Panda"),
    PARROT("parrot", "Parrot"),
    PENGUIN("penguin", "Penguin"),
    PIG("pig", "Pig"),
    RABBIT("rabbit", "Rabbit"),
    SNAKE("snake", "Snake");

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
    AvatarOptions(String value, String display) {
        this.value = value;
        this.display = display;
    }

    /**
     *
     * @return
     */
    public static String[] getValues() {
        AvatarOptions[] values = AvatarOptions.values();
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
        AvatarOptions[] values = AvatarOptions.values();
        int l = values.length;
        String[] result = new String[l];
        for (int i = 0; i < l; i++) {
            result[i] = values[i].getDisplay();
        }
        return result;
    }

}
