package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Player;
import com.mygdx.game.logic.Detection;

import java.util.ArrayList;
import java.util.Iterator;

public class FireballManager {
    private final ArrayList<Fireball> fireballs;
    private final Detection detector;
    private static final float FIREBALL_COOLDOWN = 3;
    private float fireballTimer;
    private final float fireballSpeed;

    public FireballManager(Detection detector, float fireballSpeed) {
        this.detector = detector;
        this.fireballs = new ArrayList<Fireball>();
        this.fireballTimer = 1;
        this.fireballSpeed = fireballSpeed;
    }
    public void shootFireballs(float spawnX, float spawnY, float angle, float deltaTime) {
        this.fireballTimer += deltaTime;
        if (this.fireballTimer >= FIREBALL_COOLDOWN) {
            this.fireballTimer = 0;
            this.fireballs.add(new Fireball(spawnX, spawnY, new Texture("Projectiles/fireball.png"), this.fireballSpeed, angle));
        }
    }
    public void renderAndUpdateFireballs(float deltaTime, SpriteBatch batch, Player player, int dmg) {
        Iterator<Fireball> iterator = this.fireballs.iterator();
        while (iterator.hasNext()) {
            Fireball fireball = iterator.next();
            fireball.update(deltaTime);
            fireball.getHitbox().getRectangle().setPosition(fireball.getX(), fireball.getY());
            fireball.render(batch);
            if (this.detector.rectangleToRectangle(player.getHitboxRectangle(), fireball.getHitbox().getRectangle())) {
                player.takeDMG(dmg, Character.MIN_VALUE, 0.43f);
                iterator.remove();
            }
            if (this.detector.wallCollision(fireball.getHitbox().getRectangle()) || this.detector.doorCollision(fireball.getHitbox().getRectangle())) {
                iterator.remove();
            }
        }
    }
    public boolean isFireballOnCD() {
        return this.fireballTimer < FIREBALL_COOLDOWN;
    }
}
