package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import logic.Detection;
import logic.Hitbox;

public abstract class Entity {
    private final Texture texture;
    private float x;
    private float y;
    private final Hitbox hitbox;
    private int hp;
    private int atk;
    private final Detection detector;
    private int speed;
    public Entity(Texture texture, float x, float y, int hp, int atk, Detection detector, int speed) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.hitbox = new Hitbox(this.x + 32, this.y, this.texture.getWidth() - 64, this.texture.getHeight() - 32);
        this.detector = detector;
        this.speed = speed;

    }

    public abstract void update(Player player, float deltaTime);

    public abstract void takeDMG(int dmg, char directionOfAttack,float deltaTime);


    public boolean isAlive() {
        return (this.hp > 0);
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y);
    }


    public Rectangle getHitboxRectangle() {
        return this.hitbox.getRectangle();
    }

    public Texture getTexture() {
        return this.texture;
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return this.hp;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Detection getDetector() {
        return this.detector;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public int getSpeed() {
        return this.speed;
    }
}


