package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import logic.ArrowManager;
import logic.Detection;

import java.util.ArrayList;

public class Player extends Entity {
    private int speed;
    private final ArrowManager arrowManager;

    public Player(Texture texture, float x, float y, int speed, int hp, int atk, Detection detector) {
        super(texture, x, y, hp, atk, detector);
        this.speed = speed;
        this.arrowManager = new ArrowManager(detector);
    }

    @Override
    public void update(Player player, float deltaTime) {
        float oldX = this.x;
        float oldY = this.y;
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            this.y += speed * deltaTime;
            if(this.detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX;
                this.y = oldY-5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            this.y -= speed * deltaTime;
            if(this.detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX;
                this.y = oldY+5;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.x += speed * deltaTime;
            if(this.detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX-5;
                this.y = oldY;
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.x -= speed * deltaTime;
            if(this.detector.wallCollision(this.getHitboxRectangle())){
                this.x = oldX+5;
                this.y = oldY;
            }
        }
        this.getHitboxRectangle().setPosition(this.x+32,this.y);
    }

    public void shoot(float deltaTime, SpriteBatch batch, ArrayList<Entity> entities){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.arrowManager.shootArrows(deltaTime,'R',this.texture.getWidth(),this.texture.getHeight(),this.x,this.y,batch);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            this.arrowManager.shootArrows(deltaTime,'L',this.texture.getWidth(),this.texture.getHeight(),this.x,this.y,batch);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            this.arrowManager.shootArrows(deltaTime,'U',this.texture.getWidth(),this.texture.getHeight(),this.x,this.y,batch);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            this.arrowManager.shootArrows(deltaTime,'D',this.texture.getWidth(),this.texture.getHeight(),this.x,this.y,batch);
        }
        this.arrowManager.updateAndRenderArrows(deltaTime,batch,entities);
    }


    public ArrowManager getArrowManager() {
        return arrowManager;
    }
}
