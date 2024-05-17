package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Hitbox;

/**
 * Abstraktná trieda Projectile je zodpovedná za vytvorenie a správu projektilov v hre.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s vykresľovaním
 * import com.mygdx.game.logic.Hitbox; // Pre prácu s hitboxom
 */
public abstract class Projectile {
    private float x;
    private float y;
    private final float speed;
    private final Hitbox hitbox;
    private final Texture texture;
    private float lifeTime;

    /**
     * Konštruktor triedy Projectile.
     *
     * @param x Pozícia x-ová projektilu.
     * @param y Pozícia y-ová projektilu.
     * @param texture Textúra projektilu.
     * @param speed Rýchlosť projektilu.
     */
    public Projectile(float x, float y, Texture texture, float speed) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.lifeTime = 0;
        this.speed = speed;
        this.hitbox = new Hitbox(this.x, this.y, this.texture.getWidth(), this.texture.getHeight());
    }
    /**
     * Metóda na aktualizáciu projektilu.
     *
     * @param deltaTime Čas od poslednej aktualizácie v sekundách.
     */
    public abstract void update(float deltaTime);

    /**
     * Metóda na vykreslenie projektilu.
     *
     * @param batch SpriteBatch pre vykreslenie.
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y);
    }

    /**
     * Metóda na získanie x-ovej pozície projektilu.
     *
     * @return x-ová pozícia projektilu.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Metóda na získanie y-ovej pozície projektilu.
     *
     * @return y-ová pozícia projektilu.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Metóda na zmenu x-ovej pozície projektilu.
     *
     * @param x Nová x-ová pozícia projektilu.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Metóda na zmenu y-ovej pozície projektilu.
     *
     * @param y Nová y-ová pozícia projektilu.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Metóda na získanie rýchlosti projektilu.
     *
     * @return Rýchlosť projektilu.
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Metóda na získanie hitboxu projektilu.
     *
     * @return Hitbox projektilu.
     */
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    /**
     * Metóda na získanie životnosti projektilu.
     *
     * @return Životnosť projektilu.
     */
    public float getLifeTime() {
        return this.lifeTime;
    }

    /**
     * Metóda na pridanie životnosti projektilu.
     *
     * @param lifeTime Čas, ktorý sa má pridať k životnosti projektilu.
     */
    public void addLifeTime(float lifeTime) {
        this.lifeTime += lifeTime;
    }

}
