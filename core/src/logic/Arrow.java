package logic;

import characters.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Arrow {
    private float x;
    private float y;
    private static final float SPEED = 200;
    private Hitbox hitbox;
    private Texture texture;
    private Detection detector;
    private boolean remove;

    public Arrow(float x, float y, Detection detector) {
        this.x = x;
        this.y = y;
        this.detector = detector;
        this.remove = false;
        this.texture = new Texture("arrow.png");
        this.hitbox = new Hitbox(this.x,this.y,this.texture.getWidth(),this.texture.getHeight());
    }

    public void update(float deltaTime){
        this.x += SPEED * deltaTime;
        if(this.detector.wallCollision(this.hitbox.getRectangle())){
            this.remove=true;
        }
    }
    public void render(SpriteBatch batch){
        batch.draw(this.texture,this.x,this.y);
    }
}
