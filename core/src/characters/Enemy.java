package characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Enemy extends Character{
    private Detection detector;
    public Enemy(Texture texture, float x, float y, int hp, int atk, Detection detector) {
        super(texture, x, y, hp, atk, detector);
        this.detector = detector;
    }

    @Override
    public void update(Player player) {
        float deltaX = player.x - this.x;
        float deltaY = player.y - this.y;
        float angleToPlayer = (float) Math.atan2(deltaY,deltaX);
        this.x += (float) (Math.cos(angleToPlayer) * 150 * Gdx.graphics.getDeltaTime());
        this.y += (float) (Math.sin(angleToPlayer) * 150 * Gdx.graphics.getDeltaTime());
        this.getHitboxCircle().setPosition(this.x+texture.getWidth()/2, this.y+texture.getHeight()/3);
//        if (detector.detectCollision(this.getHitboxCircle(),player.getHitboxCircle())){
//            player.takeDamage(this.getAtk());
//        }
    }

}
