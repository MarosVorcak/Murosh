package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import logic.Hitbox;

public class Arrow {
    private float x;
    private float y;
    private static final float SPEED = 200;
    private Hitbox hitbox;
    private Texture texture;
    private float lifeTime;


    public Arrow(float x, float y, Texture texture) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.lifeTime = 0;
        this.hitbox = new Hitbox(this.x,this.y,this.texture.getWidth(),this.texture.getHeight());
    }

    public void update(float deltaTime,char direction){
        switch (direction){
            case 'U':
                this.y += SPEED * deltaTime;
                break;
            case 'D':
                this.y -= SPEED * deltaTime;
                break;
            case 'R':
                this.x += SPEED * deltaTime;
                break;
            case 'L':
                this.x -= SPEED * deltaTime;
                break;

        }
    }
    public void render(SpriteBatch batch){
        batch.draw(this.texture,this.x,this.y);
    }

    public void addLifeTime(float lifeTime) {
        this.lifeTime += lifeTime;
    }

    public float getLifeTime() {
        return lifeTime;
    }

    public Hitbox getHitbox() {
        return this.hitbox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
