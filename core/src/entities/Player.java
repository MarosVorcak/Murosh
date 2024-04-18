package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import logic.ArrowManager;
import logic.Detection;

import java.util.ArrayList;

public class Player extends Entity {
    private final ArrowManager arrowManager;
    private static final float INVINCIBLITY_TIME = 0.35f;
    private float timeBetweenHits;
    public Player(Texture texture, float x, float y, int hp, int atk, Detection detector, int speed) {
        super(texture, x, y, hp, atk, detector, speed);
        this.arrowManager = new ArrowManager(detector);
        this.timeBetweenHits = 0;
    }

    @Override
    public void update(Player player, float deltaTime) {
        float oldX = this.getX();
        float oldY = this.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.setY(oldY + this.getSpeed() * deltaTime);
            if (this.getDetector().wallCollision(this.getHitboxRectangle())) {
                this.setX(oldX);
                this.setY(oldY - 5);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.setY(oldY - (this.getSpeed() * deltaTime));
            if (this.getDetector().wallCollision(this.getHitboxRectangle())) {
                this.setX(oldX);
                this.setY(oldY + 5);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.setX(oldX + this.getSpeed() * deltaTime);
            if (this.getDetector().wallCollision(this.getHitboxRectangle())) {
                this.setX(oldX - 5);
                this.setY(oldY);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.setX(oldX - (this.getSpeed() * deltaTime));
            if (this.getDetector().wallCollision(this.getHitboxRectangle())) {
                this.setX(oldX + 5);
                this.setY(oldY);
            }
        }
        this.getHitboxRectangle().setPosition(this.getX() + 32, this.getY());
    }

    @Override
    public void takeDMG(int dmg, char directionOfAttack, float deltaTime) {
        this.timeBetweenHits += deltaTime;
        if (this.timeBetweenHits >= INVINCIBLITY_TIME){
           this.timeBetweenHits = 0;
           this.setHp(this.getHp() - dmg);
           System.out.println(this.getHp());
        }
    }

    public void shoot(float deltaTime, SpriteBatch batch, ArrayList<Entity> entities) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.arrowManager.shootArrows(deltaTime, 'R', this.getTexture().getWidth(), this.getX(), this.getY(), batch);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.arrowManager.shootArrows(deltaTime, 'L', this.getTexture().getWidth(), this.getX(), this.getY(), batch);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.arrowManager.shootArrows(deltaTime, 'U', this.getTexture().getWidth(), this.getX(), this.getY(), batch);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.arrowManager.shootArrows(deltaTime, 'D', this.getTexture().getWidth(), this.getX(), this.getY(), batch);
        }
        this.arrowManager.updateAndRenderArrows(deltaTime, batch, entities);
    }

    public ArrowManager getArrowManager() {
        return this.arrowManager;
    }
}
