package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import logic.Arrow;
import logic.Detection;

public class Player extends Character{
    private int speed;

    public Player(Texture texture, float x, float y, int speed, int hp, int atk, Detection detector) {
        super(texture, x, y, hp, atk, detector);
        this.speed = speed;

    }

    @Override
    public void update(Player player, float deltaTime) {
        float oldX = this.x;
        float oldY = this.y;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            this.y += speed * deltaTime;
            if(detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX;
                this.y = oldY-5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            this.y -= speed * deltaTime;
            if(detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX;
                this.y = oldY+5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.x += speed * deltaTime;
            if(detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX-5;
                this.y = oldY;
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.x -= speed * deltaTime;
            if(detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX+5;
                this.y = oldY;
            }
        }
        this.getHitboxRectangle().setPosition(this.x+32,this.y);
    }
    public void shoot(float deltaTime, SpriteBatch batch){
        if(Gdx.input.isButtonJustPressed(Input.Keys.RIGHT)){
            Arrow arrow = new Arrow(this.x+this.texture.getWidth(),this.y+this.texture.getHeight()/2,this.detector);
            arrow.update(deltaTime);
            arrow.render(batch);
        }
    }

}
