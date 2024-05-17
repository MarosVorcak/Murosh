package com.mygdx.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.entities.Enemy;

/**
 * Trieda BossRoom rozširuje triedu Room a je zodpovedná za vytvorenie a správu miestnosti s bosom.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s dávkami sprajtov
 * import com.mygdx.game.entities.Boss; // Pre prácu s Boss
 * import com.mygdx.game.entities.Enemy; // Pre prácu s Enemy
 */
public class BossRoom extends Room {
    private boolean bossDefeated;

    /**
     * Konštruktor pre triedu BossRoom, ktorý inicializuje mapu a nastaví bossDefeated na false.
     */
    public BossRoom() {
        super("Maps/boss_room.tmx");
        this.bossDefeated = false;
    }

    /**
     * Metóda renderEnemies vykreslí bossa a jeho strely, aktualizuje bossa a zistí, či je boss ešte nažive.
     * @param batch dávka sprajtov
     * @param deltaTime časový interval
     */
    @Override
    public void renderEnemies(SpriteBatch batch, float deltaTime) {
        if (!this.getEnemies().isEmpty()) {
            for (Enemy enemy : this.getEnemies()) {
                Boss boss = (Boss)enemy;
                boss.update(this.getPlayer(), deltaTime);
                boss.render(batch);
                boss.fireballBarage(deltaTime, this.getPlayer(), batch);
                if (!boss.isAlive()) {
                    this.bossDefeated = true;
                }
                if (boss.getDetector().rectangleToRectangle(boss.getHitboxRectangle(), this.getPlayer().getHitboxRectangle())) {
                    this.getPlayer().takeDMG(boss.getAtk(), Character.MIN_VALUE, deltaTime);
                }
            }
        }
    }

    /**
     * Metóda checkIfRoomIsExitable zistí, či je boss porazený a miestnosť je prechodná.
     * @return true, ak je boss porazený, inak false
     */
    @Override
    public boolean checkIfRoomIsExitable() {
        return this.bossDefeated;
    }
}
