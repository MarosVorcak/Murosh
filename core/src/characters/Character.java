package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
        this.hitbox = new Hitbox(this.x+32,this.y,this.texture.getWidth()-64,this.texture.getHeight()-32);
        this.detector = detector;

    }

    public abstract void update(Player player);

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


    public Rectangle getHitboxRectangle() {
        return this.hitbox.getRectangle();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

}


