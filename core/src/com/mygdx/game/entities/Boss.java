package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.Lunger;
import com.mygdx.game.projectiles.FireballManager;

public class Boss extends Enemy{
    private static final float BARRAGE_COOLDOWN = 3;
    private float barrageTimer;
    private final FireballManager fireballManager;
    private final Lunger lunger;
    private float lastPlayerX;
    private float lastPlayerY;


    public Boss(float x, float y, Detection detector) {
        super(new Texture("Entities/boss.png"), x, y, 300, 40, detector, 100);
        this.fireballManager = new FireballManager(this.getDetector(), 200);
        this.barrageTimer = 0;
        this.lunger = new Lunger(1,0.6f,300,12,this, this.getSpeed() * 16);
    }

    @Override
    public void update(Player player, float deltaTime) {
        if(!this.lunger.isLunging()){
            this.lastPlayerX = player.getX();
            this.lastPlayerY = player.getY();
        }
        if(this.lunger.isLungeOnCooldown()){
            super.update(player,deltaTime);
            this.lunger.addToTimeBetweenLunges(deltaTime);
        }else{
            this.lunger.update(deltaTime,this.lastPlayerX,this.lastPlayerY);
        }
    }

    public void fireballBarage(float deltaTime, Player player, SpriteBatch batch){
        if(this.lunger.isLungeOnCooldown()){
            this.barrageTimer += deltaTime;
            if(this.barrageTimer >= BARRAGE_COOLDOWN){
                float angleSepartion = (float)(Math.PI / 4);
                for (int i = 0; i < 8; i++) {
                    float fireballAngle = angleSepartion * i;
                    this.fireballManager.shootFireballs(this.getX() + (float) this.getTexture().getWidth() / 2, this.getY() + (float) this.getTexture().getHeight() / 4, fireballAngle,3);
                }
                this.barrageTimer=0;
            }
            this.fireballManager.renderAndUpdateFireballs(deltaTime, batch, player, this.getAtk());
        }
    }
}
