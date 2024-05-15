package com.mygdx.game.dungeon;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.entities.Enemy;

import java.util.Iterator;

public class BossRoom extends Room {
    boolean bossDefeated;

    public BossRoom() {
        super("Maps/boss_room.tmx");
    }

    @Override
    public void renderEnemies(SpriteBatch batch, float deltaTime) {
        if (!this.getEnemies().isEmpty()) {
            Iterator<Enemy> iterator = this.getEnemies().iterator();
            while (iterator.hasNext()) {
                Boss boss = (Boss)iterator.next();
                boss.update(this.getPlayer(), deltaTime);
                boss.render(batch);
                boss.fireballBarage(deltaTime, this.getPlayer(), batch);
                if(!boss.isAlive()){
                    this.bossDefeated = true;
                }
                if (boss.getDetector().rectangleToRectangle(boss.getHitboxRectangle(), this.getPlayer().getHitboxRectangle())) {
                    this.getPlayer().takeDMG(boss.getAtk(), Character.MIN_VALUE, deltaTime);
                }
            }
        }
    }

    @Override
    public boolean checkIfRoomIsExitable() {
        return this.bossDefeated;
    }
}
