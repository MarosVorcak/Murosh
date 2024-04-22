package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import logic.Detection;

public class Slime extends Enemy{
    private static final float CHARGE_DURATION = 1f;
    private static final float SHAKE_AMMOUNT = 0.5f;
    private static final float LUNGE_DURATION = 0.1f;
    private static final float LUNGE_COOLDOWN = 3;
    private float timeBetweenLunges;
    private float chargeUpTimer;
    private float lastKnownPlayerX;
    private float lastKnownPlayerY;
    private boolean isSpeedBoosted;
    private float lungeTimer;
    private boolean isLunging;

    public Slime(float x, float y, Detection detector) {
        super(new Texture("Entities/slime.png"), x, y, 100, 20, detector, 30);
        this.getHitboxRectangle().set(x+8,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-16);
        this.chargeUpTimer = 0;
        this.timeBetweenLunges = 0;
        this.isLunging = false;
        this.isSpeedBoosted = false;
        this.lungeTimer = 0;


    }

    @Override
    public void update(Player player, float deltaTime) {
        if (!this.isLunging) {
            this.lastKnownPlayerX = player.getX();
            this.lastKnownPlayerY = player.getY();
        }
        if(this.isLungeOnCooldown()){
            super.update(player, deltaTime);
            this.timeBetweenLunges += deltaTime;
        } else {
            if (this.chargeUpLunge(deltaTime)){
                if (!this.isSpeedBoosted){
                   this.setSpeed(this.getSpeed() * 50);
                   this.isSpeedBoosted = true;
                }
                if (this.lunge(deltaTime)){
                    this.setSpeed(this.getSpeed()/50);
                    this.isLunging = false;
                    this.isSpeedBoosted = false;
                    this.timeBetweenLunges = 0;
                }
            }
        }
        this.getHitboxRectangle().setPosition(this.getX()+8, this.getY());
    }

    private boolean chargeUpLunge(float deltaTime){
        this.chargeUpTimer += deltaTime;
        if(this.chargeUpTimer <= CHARGE_DURATION){
            float shakeY = MathUtils.random(-SHAKE_AMMOUNT, SHAKE_AMMOUNT);
            float shakeX = MathUtils.random(-SHAKE_AMMOUNT, SHAKE_AMMOUNT);
            this.setX(this.getX() + shakeX);
            this.setY(this.getY() + shakeY);
            return false;
        } else {
            this.chargeUpTimer = 0;
            return true;
        }
    }

    private boolean lunge(float deltaTime) {
        this.isLunging = true;
        this.lungeTimer += deltaTime;
        if (this.lungeTimer <= LUNGE_DURATION){
            float deltaX = this.lastKnownPlayerX - this.getX();
            float deltaY = this.lastKnownPlayerX - this.getY();
            float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
            this.setX(this.getX() + (float)(Math.cos(angleToPlayer) * this.getSpeed() * deltaTime));
            this.setY(this.getY() + (float)(Math.sin(angleToPlayer) * this.getSpeed() * deltaTime));
            return false;
        }else{
            this.lungeTimer = 0;
            return true;
        }
    }





    private boolean isLungeOnCooldown(){
        return this.timeBetweenLunges < LUNGE_COOLDOWN;
    }
}
