package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Hitbox;

public abstract class Projectile {
    private float x;
    private float y;
    private final float speed;
    private final Hitbox hitbox;
    private final Texture texture;
    private float lifeTime;
    public Projectile(float x, float y, Texture texture, float speed) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.lifeTime = 0;
        this.speed = speed;
        this.hitbox = new Hitbox(this.x, this.y, this.texture.getWidth(), this.texture.getHeight());
    }
    public abstract void update(float deltaTime);
    public void render(SpriteBatch batch){
        batch.draw(this.texture,this.x,this.y);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return this.speed;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public float getLifeTime() {
        return this.lifeTime;
    }
    public void addLifeTime(float lifeTime) {
        this.lifeTime += lifeTime;
    }

}
