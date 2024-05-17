package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.Hitbox;

/**
 * Abstraktná trieda Entity je základom pre všetky entity v hre.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s vykreslovaním sprajtov
 * import com.badlogic.gdx.math.Rectangle; // Pre prácu s obdĺžnikmi
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import com.mygdx.game.logic.Hitbox; // Pre prácu s hitboxom
 */
public abstract class Entity {
    private final Texture texture;
    private float x;
    private float y;
    private final Hitbox hitbox;
    private int hp;
    private int atk;
    private final Detection detector;
    private int speed;

    /**
     * Konštruktor pre vytvorenie entity.
     *
     * @param texture Texture - textúra entity
     * @param x float - x-ová pozícia entity
     * @param y float - y-ová pozícia entity
     * @param hp int - životy entity
     * @param atk int - útok entity
     * @param detector Detection - detektor pre detekciu kolízií
     * @param speed int - rýchlosť entity
     */
    public Entity(Texture texture, float x, float y, int hp, int atk, Detection detector, int speed) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.hitbox = new Hitbox(this.x + 32, this.y, this.texture.getWidth() - 64, this.texture.getHeight() - 32);
        this.detector = detector;
        this.speed = speed;

    }

    /**
     * Metóda update slúži na aktualizáciu entity.
     *
     * @param player Player - hráč
     * @param deltaTime float - časový rozdiel od posledného vykresleného snímku
     */
    public abstract void update(Player player, float deltaTime);

    /**
     * Metóda takeDMG slúži na spracovanie útoku na entity.
     *
     * @param dmg int - počet životov, ktoré entity stratila
     * @param directionOfAttack char - smer útoku
     * @param deltaTime float - časový rozdiel od posledného vykresleného snímku
     */
    public abstract void takeDMG(int dmg, char directionOfAttack, float deltaTime);

    /**
     * Metóda isAlive slúži na zistenie, či je entita ešte nažive.
     *
     * @return boolean - true, ak je entita nažive, inak false
     */
    public boolean isAlive() {
        return (this.hp > 0);
    }

    /**
     * Metóda render slúži na vykreslenie entity.
     *
     * @param batch SpriteBatch - dávka sprajtov
     */
    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y);
    }

    /**
     * Metóda getHitboxRectangle slúži na získanie obdĺžnika hitboxu entity.
     *
     * @return Rectangle - obdĺžnik hitboxu entity
     */
    public Rectangle getHitboxRectangle() {
        return this.hitbox.getRectangle();
    }

    /**
     * Metóda getTexture slúži na získanie textúry entity.
     *
     * @return Texture - textúra entity
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Metóda getX slúži na získanie x-ovej pozície entity.
     *
     * @return float - x-ová pozícia entity
     */
    public float getX() {
        return this.x;
    }

    /**
     * Metóda getY slúži na získanie y-ovej pozície entity.
     *
     * @return float - y-ová pozícia entity
     */
    public float getY() {
        return this.y;
    }

    /**
     * Metóda setX slúži na nastavenie x-ovej pozície entity.
     *
     * @param x float - x-ová pozícia entity
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Metóda setY slúži na nastavenie y-ovej pozície entity.
     *
     * @param y float - y-ová pozícia entity
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Metóda setHp slúži na nastavenie životov entity.
     *
     * @param hp int - životy entity
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Metóda getHp slúži na získanie životov entity.
     *
     * @return int - životy entity
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Metóda getAtk slúži na získanie útoku entity.
     *
     * @return int - útok entity
     */
    public int getAtk() {
        return this.atk;
    }

    /**
     * Metóda setAtk slúži na nastavenie útoku entity.
     *
     * @param atk int - útok entity
     */
    public void setAtk(int atk) {
        this.atk = atk;
    }

    /**
     * Metóda setSpeed sluzi na nastavenie rýchlosti entity.
     *
     * @param speed int - nová rýchlosť entity
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Metóda getDetector slúži na získanie detektoru entity.
     *
     * @return Detection - detektor entity
     */
    public Detection getDetector() {
        return this.detector;
    }

    /**
     * Metóda getHitbox slúži na získanie hitboxu entity.
     *
     * @return Hitbox - hitbox entity
     */
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    /**
     * Metóda getSpeed slúži na získanie rýchlosti entity.
     *
     * @return int - rýchlosť entity
     */
    public int getSpeed() {
        return this.speed;
    }
}


