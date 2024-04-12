package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Character{
    private int speed;

    public Player(Texture texture, float x, float y, int speed, int hp, int atk) {
        super(texture, x, y, hp, atk);
        this.speed = speed;

    }

    @Override
    public void update(Player player) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            this.y += speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            this.y -= speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.x += speed * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.x -= speed * Gdx.graphics.getDeltaTime();
        }
        this.getHitboxCircle().setPosition(this.x+texture.getWidth()/2, this.y+texture.getHeight()/3);
    }

}
