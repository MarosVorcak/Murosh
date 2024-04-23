package projectiles;

import com.badlogic.gdx.graphics.Texture;

public class Fireball extends Projectile {
    private final float angleToPlayer;
    public Fireball(float x, float y, Texture texture, float speed, float angleToPlayer) {
        super(x, y, texture, speed);
        this.angleToPlayer = angleToPlayer;
    }

    @Override
    public void update(float deltaTime) {
        this.setX(this.getX() + (float)(Math.cos(this.angleToPlayer) * this.getSpeed() * deltaTime));
        this.setY(this.getY() + (float)(Math.sin(this.angleToPlayer) * this.getSpeed() * deltaTime));
    }
}
