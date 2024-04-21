package entities;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Shaman extends Enemy{
    public Shaman(float x, float y,Detection detector) {
        super(new Texture("Entities/shaman.png"), x, y, 120, 20, detector, 150);
        this.getHitboxRectangle().set(x+16,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-32);
    }

    @Override
    public void update(Player player, float deltaTime) {
        super.update(player, deltaTime);
        this.getHitboxRectangle().setPosition(this.getX()+16,this.getY());
    }
}
