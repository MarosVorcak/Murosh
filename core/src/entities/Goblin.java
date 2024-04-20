package entities;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Goblin extends Enemy {
    public Goblin(float x, float y, Detection detector) {
        super(new Texture("goblin.png"), x, y, 60, 10, detector, 100);
    }
}
