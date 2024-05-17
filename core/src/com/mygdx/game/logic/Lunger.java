package com.mygdx.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entities.Enemy;

/**
 * Trieda Lunger je zodpovedná za mechaniku výpadu nepriateľov.
 *
 * Importy:
 * import com.badlogic.gdx.math.MathUtils; // Pre prácu s matematickými funkciemi
 * import com.mygdx.game.entities.Enemy; // Pre prácu s nepriateľmi
 */
public class Lunger {
    private final float chargeDuration;
    private final float shakeAmount;
    private final float lungeDistance;
    private final float lungeCooldown;
    private final Enemy enemy;
    private final float lungeSpeed;
    private float chargeUpTimer;
    private float lungeDistanceTraveled;
    private float timeBetweenLunges;
    private boolean isLunging;

    /**
     * Konštruktor triedy Lunger.
     *
     * @param chargeDuration Dĺžka nabíjania výpadu.
     * @param shakeAmount Množstvo trasenia počas nabíjania.
     * @param lungeDistance Dĺžka výpadu.
     * @param lungeCooldown Časový interval medzi výpadmi.
     * @param enemy Nepriateľ, ktorý vykonáva výpad.
     * @param lungeSpeed Rýchlosť výpadu.
     */
    public Lunger(float chargeDuration, float shakeAmount, float lungeDistance, float lungeCooldown, Enemy enemy, float lungeSpeed) {
        this.chargeDuration = chargeDuration;
        this.shakeAmount = shakeAmount;
        this.lungeDistance = lungeDistance;
        this.lungeCooldown = lungeCooldown;
        this.lungeSpeed = lungeSpeed;
        this.chargeUpTimer = 0;
        this.lungeDistanceTraveled = 0;
        this.enemy = enemy;
        this.isLunging = false;
    }

    /**
     * Metóda update slúži na aktualizáciu výpadu nepriateľa.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     * @param playerX X-ová pozícia hráča.
     * @param playerY Y-ová pozícia hráča.
     */
    public void update(float deltaTime, float playerX, float playerY) {
        if (!this.isLungeOnCooldown()) {
            if (this.chargeUp(deltaTime)) {
                if (this.lunge(deltaTime, playerX, playerY)) {
                    this.isLunging = false;
                    this.chargeUpTimer = 0;
                    this.timeBetweenLunges = 0;
                }
            }
        }
    }

    /**
     * Metóda chargeUp slúži na nabíjanie výpadu.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     * @return True, ak je nabíjanie dokončené, inak false.
     */
    private boolean chargeUp(float deltaTime) {
        this.chargeUpTimer += deltaTime;
        if (this.chargeUpTimer <= this.chargeDuration) {
            float shakeX = MathUtils.random(-this.shakeAmount, this.shakeAmount);
            float shakeY = MathUtils.random(-this.shakeAmount, this.shakeAmount);
            this.enemy.setX(this.enemy.getX() + shakeX);
            this.enemy.setY(this.enemy.getY() + shakeY);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Metóda lunge slúži na vykonanie výpadu.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     * @param lungeTargetX X-ová pozícia cieľa výpadu.
     * @param lungeTargetY Y-ová pozícia cieľa výpadu.
     * @return True, ak je výpad dokončený, inak false.
     */
    private boolean lunge(float deltaTime, float lungeTargetX, float lungeTargetY) {
        this.isLunging = true;
        float oldX = this.enemy.getX();
        float oldY = this.enemy.getY();
        if (this.lungeDistanceTraveled <= this.lungeDistance) {
            float deltaX = lungeTargetX - this.enemy.getX();
            float deltaY = lungeTargetY - this.enemy.getY();
            float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
            this.enemy.setX(this.enemy.getX() + (float)(Math.cos(angleToPlayer) * this.lungeSpeed * deltaTime));
            this.enemy.setY(this.enemy.getY() + (float)(Math.sin(angleToPlayer) * this.lungeSpeed * deltaTime));
            this.lungeDistanceTraveled += (float)Math.sqrt(Math.pow(oldX - this.enemy.getX(), 2) + Math.pow(oldY - this.enemy.getY(), 2));
            this.enemy.getHitbox().getRectangle().setPosition(this.enemy.getX(), this.enemy.getY());
            return false;
        } else {
            this.lungeDistanceTraveled = 0;
            return true;
        }
    }

    /**
     * Metóda isLungeOnCooldown slúži na zistenie, či je výpad na cooldowne.
     *
     * @return True, ak je výpad na cooldowne, inak false.
     */
    public boolean isLungeOnCooldown() {
        return this.timeBetweenLunges < this.lungeCooldown;
    }

    /**
     * Metóda addToTimeBetweenLunges slúži na pridanie času k času medzi výpadmi.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    public void addToTimeBetweenLunges(float deltaTime) {
        this.timeBetweenLunges += deltaTime;
    }

    /**
     * Metóda isLunging slúži na zistenie, či sa vykonáva výpad.
     *
     * @return True, ak sa vykonáva výpad, inak false.
     */
    public boolean isLunging() {
        return this.isLunging;
    }
}
