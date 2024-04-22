package entities;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Boss extends Enemy{
    public Boss(float x, float y, Detection detector) {
        super(new Texture("Entities/boss.png"), x, y, 300, 40, detector, 100);
    }
}
