package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.projectiles.FireballManager;

public class Shaman extends Enemy{
    private final FireballManager fireballManager;
    private float angleToPlayer;

    public Shaman(float x, float y,Detection detector) {
        super(new Texture("Entities/shaman.png"), x, y, 120, 20, detector, 80);
        this.getHitboxRectangle().set(x+16,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-32);;
        this.fireballManager = new FireballManager(this.getDetector(),250);

    }

    @Override
    public void update(Player player, float deltaTime) {
        super.update(player,deltaTime);
        this.getHitboxRectangle().setPosition(this.getX()+16,this.getY());
    }

    public void shootFireball(Player player, SpriteBatch batch, float deltaTime){
        if(this.fireballManager.isFireballOnCD()){
            this.angleToPlayer = this.calculateAngleToPlayer(player);
        }
        this.fireballManager.shootFireballs(this.getX() + (float) this.getTexture().getWidth() / 2, this.getY() + (float) this.getTexture().getHeight() / 4, this.angleToPlayer,deltaTime);
        this.fireballManager.renderAndUpdateFireballs(deltaTime,batch,player,this.getAtk());
    }
    private float calculateAngleToPlayer(Player player){
        float deltaX = player.getX() - this.getX();
        float deltaY = player.getY() - this.getX();
        return (float)Math.atan2(deltaY,deltaX);
    }
}
