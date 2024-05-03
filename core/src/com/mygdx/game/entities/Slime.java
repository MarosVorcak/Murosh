package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.Lunger;

public class Slime extends Enemy {
    private float lastKnownPlayerX;
    private float lastKnownPlayerY;
    private final Lunger lunger;

    public Slime(float x, float y, Detection detector) {
        super(new Texture("Entities/slime.png"), x, y, 100, 20, detector, 30);
        this.getHitboxRectangle().set(x + 8, y, this.getTexture().getWidth() - 16, this.getTexture().getHeight() - 16);
        this.lunger = new Lunger(0.85f, 0.5f, 180, 3, this, this.getSpeed() * 15);
    }

    @Override
    public void update(Player player, float deltaTime) {
        if (!this.lunger.isLunging()) {
            this.lastKnownPlayerX = player.getX();
            this.lastKnownPlayerY = player.getY();
        } else {
            this.getHitboxRectangle().setPosition(this.getX() + 8, this.getY());
        }
        if (this.lunger.isLungeOnCooldown()) {
            super.update(player, deltaTime);
            this.lunger.addToTimeBetweenLunges(deltaTime);
        } else {
            this.lunger.update(deltaTime, this.lastKnownPlayerX, this.lastKnownPlayerY);
        }
        this.getHitboxRectangle().setPosition(this.getX() + 8, this.getY());
    }


}
