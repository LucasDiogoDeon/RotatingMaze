package com.gmail.lucasddeon.rotatingmaze.preferences.enums;

/**
 *
 */
public enum AudioOptions {

    FAUSTAO("faustao", "Faustao"),
    GALVAO_BUENO("galvao_bueno", "Galvao Bueno"),
    SILVIO_SANTOS("silvio_santos", "Silvio Santos"),
    CAETANO_VELOSO("caetano_veloso", "Caetano Veloso");

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
    AudioOptions(String value, String display) {
        this.value = value;
        this.display = display;
    }

    /**
     *
     * @return
     */
    public static String[] getValues() {
        AudioOptions[] values = AudioOptions.values();
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
        AudioOptions[] values = AudioOptions.values();
        int l = values.length;
        String[] result = new String[l];
        for (int i = 0; i < l; i++) {
            result[i] = values[i].getDisplay();
        }
        return result;
    }

}
