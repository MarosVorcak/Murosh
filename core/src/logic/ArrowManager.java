package logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Arrow;
import entities.Enemy;
import entities.Entity;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrowManager {
    private ArrayList<Arrow> arrows;
    private Detection detector;
    private float betweenShotTimer;
    private char arrowDirect;
    public static final float TIME_BETWEEN_SHOTS = 0.8f;
    public static final float ARROW_LIFE_TIME = 2;


    public ArrowManager(Detection detector) {
        this.arrows = new ArrayList<Arrow>();
        this.detector = detector;
        this.betweenShotTimer = 1;
    }
    public void shootArrows(float deltaTime, char direction, float playerSize, float playerX, float playerY, SpriteBatch batch) {
        this.betweenShotTimer += deltaTime;
        this.arrowDirect = direction;
        switch (direction) {
            case 'U':
                if (this.betweenShotTimer >= TIME_BETWEEN_SHOTS) {
                    this.betweenShotTimer = 0;
                    this.arrows.add(new Arrow(playerX + playerSize / 2 , playerY + playerSize / 2 , this.createTexture(direction), direction));
                }
                break;
            case 'D':
                if (this.betweenShotTimer >= TIME_BETWEEN_SHOTS) {
                    this.betweenShotTimer = 0;
                    this.arrows.add(new Arrow(playerX + playerSize / 2, playerY, this.createTexture(direction), direction));
                }
                break;
            case 'R':
                if (this.betweenShotTimer >= TIME_BETWEEN_SHOTS) {
                    this.betweenShotTimer = 0;
                    this.arrows.add(new Arrow(playerX + playerSize / 2, playerY + playerSize / 4, this.createTexture(direction), direction));
                }

                break;
            case 'L':
                if (this.betweenShotTimer >= TIME_BETWEEN_SHOTS) {
                    this.betweenShotTimer = 0;
                    this.arrows.add(new Arrow(playerX, playerY + playerSize / 4, this.createTexture(direction), direction));
                }

                break;

        }
    }

    public void updateAndRenderArrows(float deltaTime, SpriteBatch batch, ArrayList<Enemy>enemies) {
        Iterator<Arrow> iterator = this.arrows.iterator();
        while (iterator.hasNext()) {
            Arrow arrow = iterator.next();
            arrow.addLifeTime(deltaTime);
            arrow.update(deltaTime);
            arrow.getHitbox().getRectangle().setPosition(arrow.getX(), arrow.getY());
            arrow.render(batch);
            for (Enemy enemy : enemies) {
                    if (this.detector.rectangleToRectangle(enemy.getHitboxRectangle(), arrow.getHitbox().getRectangle())) {
                        enemy.takeDMG(20, arrow.getDirection(), deltaTime);
                        iterator.remove();
                    }
            }
            if (arrow.getLifeTime() >= ARROW_LIFE_TIME || this.detector.wallCollision(arrow.getHitbox().getRectangle())) {
                iterator.remove();
            }
        }
    }


    public void renderArrowHitboxes() {
        for (Arrow arrow : this.arrows) {
            arrow.getHitbox().render();
        }
    }
    private Texture createTexture(char direction) {
        String path = "projectiles/arrows/arrow_" + direction + ".png";
        return new Texture(path);
    }
}
