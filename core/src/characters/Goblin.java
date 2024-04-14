package characters;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Goblin extends Enemy{
    public Goblin(Texture texture, float x, float y, int hp, int atk, Detection detector) {
        super(texture, x, y,hp,atk,detector);
    }
}
