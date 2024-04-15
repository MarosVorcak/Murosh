package logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.Arrow;

import java.util.ArrayList;

public class ArrowManager {
    private ArrayList<Arrow> arrows;
    private Detection detector;
    private float betweenShotTimer;
    private float arrowLifeTimer;
    public static final float TIME_BETWEEN_SHOTS = 1;
    public static final float ARROW_LIFE_TIME = 2;


    public ArrowManager(Detection detector) {
        this.arrows = new ArrayList<Arrow>();
        this.detector = detector;
        this.betweenShotTimer = 1;
        this.arrowLifeTimer = 0;
    }
    public void shootArrows(float deltaTime, char direction, float playerWidth, float playerHeigth, float playerX, float playerY, SpriteBatch batch){
        this.betweenShotTimer += deltaTime;
        this.arrowLifeTimer += deltaTime;
        if(arrowLifeTimer > ARROW_LIFE_TIME){
            this.arrowLifeTimer = 0;
        }
        switch (direction){
            case 'U':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX+playerHeigth/2,playerY+playerWidth/2,this.createTexture(direction)));
                }
                this.updateArrows(deltaTime,direction);
                break;
            case 'D':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX-playerHeigth/2,playerY-playerWidth/2,this.createTexture(direction)));
                }
                this.updateArrows(deltaTime,direction);
                break;
            case 'R':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX+playerHeigth/2,playerY+playerWidth/4,this.createTexture(direction)));
                }
                this.updateArrows(deltaTime,direction);
                this.renderArrows(batch);
                break;
            case 'L':
                if(betweenShotTimer>=TIME_BETWEEN_SHOTS){
                    this.betweenShotTimer=0;
                    this.arrows.add(new Arrow(playerX-playerHeigth/2,playerY-playerWidth/4,this.createTexture(direction)));
                }
                this.updateArrows(deltaTime,direction);
                break;

        }
    }
    private void updateArrows(float deltaTime,char direction){
        while(arrowLifeTimer<=ARROW_LIFE_TIME){
            for (Arrow arrow : arrows) {
                arrow.update(deltaTime,direction);
                arrow.getHitbox().getRectangle().setPosition(arrow.getX(),arrow.getY());
            }
        }
    }
    private void renderArrows(SpriteBatch batch){
        while(arrowLifeTimer<=ARROW_LIFE_TIME){
            for (Arrow arrow : arrows) {
                arrow.render(batch);
            }
            this.checkForCollisionAndRemove();
        }
    }
    private void checkForCollisionAndRemove(){
        ArrayList<Arrow> removedArrows = new ArrayList<Arrow>();
        for (Arrow arrow : arrows) {
            if(detector.wallCollision(arrow.getHitbox().getRectangle())){
                removedArrows.add(arrow);
            }
        }
        this.arrows.removeAll(removedArrows);
    }
    public void renderArrowHitboxes(){
        for (Arrow arrow : arrows) {
            arrow.getHitbox().render();
        }
    }
    private Texture createTexture(char direction){
        Texture texture = null;
        switch (direction){
            case 'U':
                texture = new Texture("projectiles/arrows/arrow_U.png");
                break;
            case 'D':
                texture = new Texture("projectiles/arrows/arrow_D.png");
                break;
            case 'R':
                texture = new Texture("projectiles/arrows/arrow_R.png");
                break;
            case 'L':
                texture = new Texture("projectiles/arrows/arrow_L.png");
                break;
        }
        return texture;
    }
}
