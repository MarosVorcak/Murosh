package com.mygdx.game.logic;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.entities.Enemy;

public class Lunger{
    private final float chargeDuration;
    private final float shakeAmount;
    private final float lungeDistance;
    private final float lungeCooldown;
    private final Enemy enemy;
    private final float lungeSpeed;
    private float chargeUpTimer;
    private float lungeDistanceTraveled;
    private float timeBetweenLunges;
    private boolean isLunging;


    public Lunger(float chargeDuration, float shakeAmount, float lungeDistance, float lungeCooldown, Enemy enemy, float lungeSpeed) {
        this.chargeDuration = chargeDuration;
        this.shakeAmount = shakeAmount;
        this.lungeDistance = lungeDistance;
        this.lungeCooldown = lungeCooldown;
        this.lungeSpeed = lungeSpeed;
        this.chargeUpTimer = 0;
        this.lungeDistanceTraveled = 0;
        this.enemy = enemy;
        this.isLunging = false;
    }

    public void update(float deltaTime, float playerX, float playerY) {
        if(!this.isLungeOnCooldown()){
            if(this.chargeUp(deltaTime)){
                if(this.lunge(deltaTime,playerX,playerY)){
                    this.isLunging = false;
                    this.chargeUpTimer = 0;
                    this.timeBetweenLunges = 0;
                }
            }
        }
    }

    private boolean chargeUp(float deltaTime) {
        this.chargeUpTimer += deltaTime;
        if (chargeUpTimer <= chargeDuration) {
            float shakeX = MathUtils.random(-this.shakeAmount, this.shakeAmount);
            float shakeY = MathUtils.random(-this.shakeAmount, this.shakeAmount);
            this.enemy.setX(this.enemy.getX() + shakeX);
            this.enemy.setY(this.enemy.getY() + shakeY);
            return false;
        } else {
            return true;
        }
    }

    private boolean lunge(float deltaTime, float lungeTargetX, float lungeTargetY) {
        this.isLunging = true;
        float oldX = this.enemy.getX();
        float oldY = this.enemy.getY();
        if (this.lungeDistanceTraveled <= this.lungeDistance) {
            float deltaX = lungeTargetX - this.enemy.getX();
            float deltaY = lungeTargetY - this.enemy.getY();
            float angleToPlayer = (float) Math.atan2(deltaY, deltaX);
            this.enemy.setX(this.enemy.getX() + (float)(Math.cos(angleToPlayer) * this.lungeSpeed * deltaTime));
            this.enemy.setY(this.enemy.getY() + (float)(Math.sin(angleToPlayer) * this.lungeSpeed * deltaTime));
            this.lungeDistanceTraveled += (float)Math.sqrt(Math.pow(oldX - this.enemy.getX(), 2) + Math.pow(oldY - this.enemy.getY(), 2));
            this.enemy.getHitbox().getRectangle().setPosition(this.enemy.getX(),this.enemy.getY());
            return false;
        } else {
            this.lungeDistanceTraveled = 0;
            return true;
        }
    }

    public boolean isLungeOnCooldown() {
        return this.timeBetweenLunges < this.lungeCooldown;
    }

    public void addToTimeBetweenLunges(float deltaTime){
        this.timeBetweenLunges += deltaTime;
    }

    public boolean isLunging(){
        return this.isLunging;
    }
}
