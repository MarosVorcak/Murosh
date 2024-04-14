package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Player extends Character{
    private int speed;

    public Player(Texture texture, float x, float y, int speed, int hp, int atk, Detection detector) {
        super(texture, x, y, hp, atk, detector);
        this.speed = speed;

    }

    @Override
    public void update(Player player) {
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            float oldY = this.y;
            this.y += speed * Gdx.graphics.getDeltaTime();
            if(detector.wallCollsion(this.getHitboxCircle())){
                System.out.println("collision hapened");
                this.y = oldY;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            float oldY = this.y;
            this.y -= speed * Gdx.graphics.getDeltaTime();
            if(detector.wallCollsion(this.getHitboxCircle())){
                System.out.println("collision hapened");
                this.y = oldY;
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            float oldX = this.x;
            this.x += speed * Gdx.graphics.getDeltaTime();
            if(detector.wallCollsion(this.getHitboxCircle())){
                System.out.println("collision hapened");
                this.x = oldX;
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            float oldX = this.x;
            this.x -= speed * Gdx.graphics.getDeltaTime();
            if(detector.wallCollsion(this.getHitboxCircle())){
                System.out.println("collision hapened");
                this.x = oldX;
            }

        }
        this.getHitboxCircle().setPosition(this.x+texture.getWidth()/2, this.y+texture.getHeight()/3);
    }

}
