package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import logic.Detection;
import logic.Hitbox;

public abstract class Character {
    protected Texture texture;
    protected float x;
    protected float y;
    private Hitbox hitbox;
    private int hp;
    private int atk;
    protected Detection detector;
    public Character(Texture texture, float x, float y, int hp, int atk, Detection detector) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.hitbox = new Hitbox(this.x+texture.getWidth()/2, this.y+texture.getHeight()/3,texture.getWidth()/3);
        this.detector = detector;

    }

    public abstract void update(Player player);

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    public Circle getHitboxCircle() {
        return this.hitbox.getCircle();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}


