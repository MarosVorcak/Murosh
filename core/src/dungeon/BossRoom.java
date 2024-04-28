package dungeon;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Boss;
import entities.Enemy;

import java.util.Iterator;

public class BossRoom extends Room {
    /**
     * tu bude zrobena muzika pocas bossfightu
     */
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
                if (!boss.isAlive()) {
                    iterator.remove();
                }
                if (boss.getDetector().rectangleToRectangle(boss.getHitboxRectangle(), this.getPlayer().getHitboxRectangle())) {
                    this.getPlayer().takeDMG(boss.getAtk(), Character.MIN_VALUE, deltaTime);
                }
            }
        }
    }
}
