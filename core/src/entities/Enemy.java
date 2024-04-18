package entities;

import com.badlogic.gdx.graphics.Texture;
import logic.Detection;

public class Enemy extends Entity {
    public Enemy(Texture texture, float x, float y, int hp, int atk, Detection detector, int speed) {
        super(texture, x, y, hp, atk, detector, speed);
    }

    @Override
    public void update(Player player, float deltaTime) {
        float deltaX = player.getX() - this.getX();
        float deltaY = player.getY() - this.getY();
        float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
        this.setX(this.getX() + (float)(Math.cos(angleToPlayer) * this.getSpeed() * deltaTime));
        this.setY(this.getY() + (float)(Math.sin(angleToPlayer) * this.getSpeed() * deltaTime));
        this.getHitboxRectangle().setPosition(this.getX() + 32, this.getY());
    }

    @Override
    public void takeDMG(int dmg, char directionOfAttack, float deltaTime) {
        this.setHp(this.getHp() - dmg);
        switch (directionOfAttack){
            case 'U':
                this.setY(this.getY() + 10);
                break;
            case 'D':
                this.setY(this.getY() - 10);
                break;
            case 'R':
                this.setX(this.getX() + 10);
                break;
            case 'L':
                this.setX(this.getX() - 10);
                break;
        }
    }

}
