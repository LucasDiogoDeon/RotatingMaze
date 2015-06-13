package com.gmail.lucasddeon.rotatingmaze.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 *
 */
public class ScreenHelper {

    static {
        reload();
    }

    public static void reload() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
    }

    private static int w;
    private static int h;

    public static int W() {
        return w;
    }

    public static int H() {
        return h;
    }

    public static int MinWH() {
        return Math.min(w, h);
    }

    public static int MaxWH() {
        return Math.max(w, h);
    }

    public static float getScaleHeight(float height) {
        float scaleY = h / height;
        return scaleY;
    }

    public static ArrayList<Sprite> getSpriteListBackground(Texture texture) {

        float scale = ScreenHelper.getScaleHeight(texture.getHeight());

        ArrayList<Sprite> spriteList = new ArrayList<Sprite>();

        float textureWidth = texture.getWidth() * scale;

        int textureCount = (int)Math.round(Math.ceil((w / 2) / textureWidth)) * 2;
        for (int i = 0; i < textureCount; i++) {
            Sprite sprite = new Sprite(texture);
            sprite.setSize(
                    sprite.getWidth() * scale,
                    sprite.getHeight() * scale);
            spriteList.add(sprite);
        }

        float softwareWidth = spriteList.get(0).getWidth();
        int x = (w / 2) - (int)(textureCount / 2 * softwareWidth);
        for (Sprite sprite : spriteList) {
            sprite.setX(x);
            sprite.setY(0);
            x += softwareWidth;
        }

        return spriteList;

    }

}
