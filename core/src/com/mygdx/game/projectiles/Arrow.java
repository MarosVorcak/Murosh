package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;

/**
 * Trieda Arrow je zodpovedná za vytvorenie a aktualizovanie šípov vo hre.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 */
public class Arrow extends Projectile {
    private final char direction;

    /**
     * Konštruktor triedy Arrow.
     *
     * @param x Počiatočná x-ová pozícia šípu.
     * @param y Počiatočná y-ová pozícia šípu.
     * @param texture Textúra šípu.
     * @param direction Smer šípu.
     * @param speed Rýchlosť šípu.
     */
    public Arrow(float x, float y, Texture texture, char direction, float speed) {
        super(x, y, texture, speed);
        this.direction = direction;
    }

    /**
     * Metóda update slúži na aktualizáciu pozície šípu.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    public void update(float deltaTime) {
        switch (this.direction) {
            case 'U':
                this.setY(this.getY() + this.getSpeed() * deltaTime);
                break;
            case 'D':
                this.setY(this.getY() - this.getSpeed() * deltaTime);
                break;
            case 'R':
                this.setX(this.getX() + this.getSpeed() * deltaTime);
                break;
            case 'L':
                this.setX(this.getX() - this.getSpeed() * deltaTime);
                break;
        }
    }

    /**
     * Metóda getDirection vráti smer šípu.
     *
     * @return Smer šípu.
     */
    public char getDirection() {
        return this.direction;
    }
}
