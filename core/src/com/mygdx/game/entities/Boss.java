package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.Lunger;
import com.mygdx.game.projectiles.FireballManager;

/**
 * Trieda Boss rozširuje triedu Enemy a je zodpovedná za vytvorenie a správu bossa.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s dávkami sprajtov
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import com.mygdx.game.logic.Lunger; // Pre prácu s Lunger
 * import com.mygdx.game.projectiles.FireballManager; // Pre prácu s manažérom ohnivých gulí
 */
public class Boss extends Enemy {
    private static final float BARRAGE_COOLDOWN = 3;
    private float barrageTimer;
    private final FireballManager fireballManager;
    private final Lunger lunger;
    private float lastPlayerX;
    private float lastPlayerY;

    /**
     * Konštruktor pre vytvorenie bossa.
     *
     * @param x float - x-ová pozícia bossa
     * @param y float - y-ová pozícia bossa
     * @param detector Detection - detektor pre detekciu kolízií
     */
    public Boss(float x, float y, Detection detector) {
        super(new Texture("Entities/boss.png"), x, y, 300, 40, detector, 100);
        this.fireballManager = new FireballManager(this.getDetector(), 200, BARRAGE_COOLDOWN);
        this.barrageTimer = 0;
        this.lunger = new Lunger(1, 0.6f, 300, 12, this, this.getSpeed() * 16);
    }

    /**
     * Metóda update slúži na aktualizáciu bossa.
     *
     * @param player Player - hráč
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void update(Player player, float deltaTime) {
        if (!this.lunger.isLunging()) {
            this.lastPlayerX = player.getX();
            this.lastPlayerY = player.getY();
        }
        if (this.lunger.isLungeOnCooldown()) {
            super.update(player, deltaTime);
            this.lunger.addToTimeBetweenLunges(deltaTime);
        } else {
            this.lunger.update(deltaTime, this.lastPlayerX, this.lastPlayerY);
        }
    }

    /**
     * Metóda fireballBarage slúži na vystrelenie ohnivých gúľ.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     * @param player Player - hráč
     * @param batch SpriteBatch - dávka sprajtov
     */
    public void fireballBarage(float deltaTime, Player player, SpriteBatch batch) {
        if (this.lunger.isLungeOnCooldown()) {
            this.barrageTimer += deltaTime;
            if (this.barrageTimer >= BARRAGE_COOLDOWN) {
                float angleSepartion = (float)(Math.PI / 4);
                for (int i = 0; i < 8; i++) {
                    float fireballAngle = angleSepartion * i;
                    this.fireballManager.shootFireballs(this.getX() + (float)this.getTexture().getWidth() / 2, this.getY() + (float)this.getTexture().getHeight() / 4, fireballAngle, BARRAGE_COOLDOWN);
                }
                this.barrageTimer = 0;
            }
            this.fireballManager.renderAndUpdateFireballs(deltaTime, batch, player, this.getAtk());
        }
    }
}
