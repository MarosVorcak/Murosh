package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.projectiles.FireballManager;

/**
 * Trieda Shaman rozširuje triedu Enemy a je zodpovedná za vytvorenie a správu šamanov.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s dávkami sprajtov
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import com.mygdx.game.projectiles.FireballManager; // Pre prácu s manažérom ohnivých gulí
 */
public class Shaman extends Enemy {
    private final FireballManager fireballManager;
    private float angleToPlayer;

    /**
     * Konštruktor pre vytvorenie šamana.
     *
     * @param x float - x-ová pozícia šamana
     * @param y float - y-ová pozícia šamana
     * @param detector Detection - detektor pre detekciu kolízií
     */
    public Shaman(float x, float y, Detection detector) {
        super(new Texture("Entities/shaman.png"), x, y, 120, 20, detector, 80);
        this.getHitboxRectangle().set(x + 16, y, this.getTexture().getWidth() - 16, this.getTexture().getHeight() - 32);
        this.fireballManager = new FireballManager(this.getDetector(), 250, 3);

    }

    /**
     * Metóda update slúži na aktualizáciu šamana.
     *
     * @param player Player - hráč
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void update(Player player, float deltaTime) {
        super.update(player, deltaTime);
        this.getHitboxRectangle().setPosition(this.getX() + 16, this.getY());
    }

    /**
     * Metóda shootFireball slúži na vystrelenie ohnivej gule.
     *
     * @param player Player - hráč
     * @param batch SpriteBatch - dávka sprajtov
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    public void shootFireball(Player player, SpriteBatch batch, float deltaTime) {
        if (this.fireballManager.isFireballOnCD()) {
            this.angleToPlayer = this.calculateAngleToPlayer(player);
        }
        this.fireballManager.shootFireballs(this.getX() + (float)this.getTexture().getWidth() / 2, this.getY() + (float)this.getTexture().getHeight() / 4, this.angleToPlayer, deltaTime);
        this.fireballManager.renderAndUpdateFireballs(deltaTime, batch, player, this.getAtk());
    }

    /**
     * Metóda calculateAngleToPlayer slúži na výpočet uhlu medzi šamanom a hráčom.
     *
     * @param player Player - hráč
     * @return float - uhol medzi šamanom a hráčom
     */
    private float calculateAngleToPlayer(Player player) {
        float deltaX = player.getX() - this.getX();
        float deltaY = player.getY() - this.getX();
        return (float)Math.atan2(deltaY, deltaX);
    }
}
