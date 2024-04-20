package entities;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Slime extends Enemy{
    public Slime(float x, float y, Detection detector) {
        super(new Texture("slime.png"), x, y, 100, 20, detector, 30);
        this.getHitboxRectangle().set(x+8,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-16);
    }

    @Override
    public void update(Player player, float deltaTime) {
        super.update(player, deltaTime);
        this.getHitboxRectangle().setPosition(this.getX()+8, this.getY());
    }
}
