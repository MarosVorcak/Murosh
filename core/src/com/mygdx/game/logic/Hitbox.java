package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Trieda Hitbox je zodpovedná za vytvorenie a správu hitboxu.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Color; // Pre prácu s farbami
 * import com.badlogic.gdx.graphics.glutils.ShapeRenderer; // Pre prácu s vykresľovaním tvarov
 * import com.badlogic.gdx.math.Rectangle; // Pre prácu s obdĺžnikmi
 */
public class Hitbox {

    private final Rectangle rectangle;

    /**
     * Konštruktor triedy Hitbox.
     *
     * @param x X-ová pozícia hitboxu.
     * @param y Y-ová pozícia hitboxu.
     * @param width Šírka hitboxu.
     * @param height Výška hitboxu.
     */
    public Hitbox(float x, float y, float width, float height) {
        this.rectangle = new Rectangle();
        this.rectangle.set(x, y, width, height);
    }

    /**
     * Metóda render slúži na vykreslenie hitboxu.
     */
    public void render() {
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.GREEN);
        sr.rect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        sr.end();
    }

    /**
     * Metóda getRectangle vráti obdĺžnik hitboxu.
     *
     * @return Obdĺžnik hitboxu.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
