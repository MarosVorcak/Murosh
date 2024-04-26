package projectiles;

import com.badlogic.gdx.graphics.Texture;

public class Fireball extends Projectile {
    private final float angle;
    public Fireball(float x, float y, Texture texture, float speed, float angle) {
        super(x, y, texture, speed);
        this.angle = angle;
//        System.out.println(angle);
    }

    @Override
    public void update(float deltaTime) {
        this.setX(this.getX() + (float)(Math.cos(this.angle) * this.getSpeed() * deltaTime));
        this.setY(this.getY() + (float)(Math.sin(this.angle) * this.getSpeed() * deltaTime));
    }
}
