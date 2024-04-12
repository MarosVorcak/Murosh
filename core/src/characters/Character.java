package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import logic.Hitbox;

public abstract class Character {
    protected Texture texture;
    protected float x;
    protected float y;
    private Hitbox hitbox;
    private int hp;
    private int atk;
    public Character(Texture texture, float x, float y, int hp, int atk) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.atk = atk;
        this.hitbox = new Hitbox(this.x+texture.getWidth()/2, this.y+texture.getHeight()/3,texture.getWidth()/3);
    }

    public abstract void update(Player player);

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
    public void takeDamage(int damage){
        this.hp -= damage;
        System.out.println("you took " + damage + " damage your hp is now " + hp);
    }

    public int getAtk() {
        return atk;
    }

    public Circle getHitboxCircle() {
        return this.hitbox.getCircle();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}


