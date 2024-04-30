package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;

public class Arrow extends Projectile {
    private final char direction;

    public Arrow(float x, float y, Texture texture, char direction, float speed) {
        super(x, y, texture, speed);
        this.direction = direction;
    }

    public void update(float deltaTime) {
        switch (this.direction) {
            case 'U':
                this.setY(this.getY() + this.getSpeed() * deltaTime);
                break;
            case 'D':
                this.setY(this.getY() - this.getSpeed() * deltaTime);
                break;
            case 'R':
                this.setX(this.getX() + this.getSpeed() * deltaTime);
                break;
            case 'L':
                this.setX(this.getX() - this.getSpeed() * deltaTime);
                break;
        }
    }


    public char getDirection() {
        return this.direction;
    }
}
