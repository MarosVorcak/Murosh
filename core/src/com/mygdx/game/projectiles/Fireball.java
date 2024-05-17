package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Trieda Fireball je zodpovedná za vytvorenie a aktualizovanie ohňových gúľ vo hre.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 */
public class Fireball extends Projectile {
    private final float angle;

    /**
     * Konštruktor triedy Fireball.
     *
     * @param x Počiatočná x-ová pozícia ohňovej gule.
     * @param y Počiatočná y-ová pozícia ohňovej gule.
     * @param texture Textúra ohňovej gule.
     * @param speed Rýchlosť ohňovej gule.
     * @param angle Uhol, pod ktorým sa má ohňová gula vystreliť.
     */
    public Fireball(float x, float y, Texture texture, float speed, float angle) {
        super(x, y, texture, speed);
        this.angle = angle;
    }

    /**
     * Metóda na aktualizovanie ohňovej gule.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void update(float deltaTime) {
        this.setX(this.getX() + (float)(Math.cos(this.angle) * this.getSpeed() * deltaTime));
        this.setY(this.getY() + (float)(Math.sin(this.angle) * this.getSpeed() * deltaTime));
    }
}
