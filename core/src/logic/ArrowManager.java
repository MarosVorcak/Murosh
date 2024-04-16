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
    public void shootArrows(float deltaTime, char direction, float playerWidth, float playerHeigth, float playerX, float playerY, SpriteBatch batch){
        this.betweenShotTimer += deltaTime;
        this.arrowDirect = direction;
        switch (direction){
            case 'U':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX+playerHeigth/2,playerY+playerWidth/2,this.createTexture(direction),direction));
                }
                break;
            case 'D':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX+playerHeigth/2,playerY,this.createTexture(direction),direction));
                }
                break;
            case 'R':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX+playerHeigth/2,playerY+playerWidth/4,this.createTexture(direction),direction));
                }

                break;
            case 'L':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX,playerY+playerWidth/4,this.createTexture(direction),direction));
                }

                break;

        }
    }

    public void updateAndRenderArrows(float deltaTime, SpriteBatch batch, ArrayList<Entity>entities){
        Iterator<Arrow> iterator = arrows.iterator();
        while (iterator.hasNext()){
            Arrow arrow = iterator.next();
            arrow.addLifeTime(deltaTime);
            arrow.update(deltaTime);
            arrow.getHitbox().getRectangle().setPosition(arrow.getX(),arrow.getY());
            arrow.render(batch);
            for (Entity entity : entities) {
                if(entity instanceof Enemy){
                    if(detector.rectangleToRectangle(entity.getHitboxRectangle(),arrow.getHitbox().getRectangle())){
                        entity.takeDMG(20);
                        iterator.remove();
                    }
                }
            }
            if(arrow.getLifeTime()>=ARROW_LIFE_TIME || detector.wallCollision(arrow.getHitbox().getRectangle())){
                iterator.remove();
            }
        }
    }


    public void renderArrowHitboxes(){
        for (Arrow arrow : arrows) {
            arrow.getHitbox().render();
        }
    }
    private Texture createTexture(char direction){
        String path = "projectiles/arrows/arrow_"+direction+".png";
        return new Texture(path);
    }
}
