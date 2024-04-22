package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import logic.Detection;

public class Slime extends Enemy{
    private static final float CHARGE_DURATION = 0.5f;
    private static final float SHAKE_AMMOUNT = 0.5f;
    private static final float LUNGE_DISTANCE = 40f;
    private static final float LUNGE_COOLDOWN = 3;
    private float timeBetweenLunges;
    private float chargeUpTimer;
    private float lastKnownPlayerX;
    private float lastKnownPlayerY;
    private boolean isSpeedBoosted;
    private float lungeDistanceTraveled;
    private boolean isLunging;

    public Slime(float x, float y, Detection detector) {
        super(new Texture("Entities/slime.png"), x, y, 100, 20, detector, 30);
        this.getHitboxRectangle().set(x+8,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-16);
        this.chargeUpTimer = 0;
        this.timeBetweenLunges = 0;
        this.isLunging = false;
        this.isSpeedBoosted = false;
        this.lungeDistanceTraveled = 0;
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
                   this.setSpeed(this.getSpeed() * 9);
                   this.isSpeedBoosted = true;
                }
                if (this.lunge(deltaTime)){
                    System.out.println();
                    this.setSpeed(this.getSpeed() / 9);
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
        if (this.lungeDistanceTraveled <= LUNGE_DISTANCE){
            float oldX = this.getX();
            float oldY = this.getY();
            float deltaX = this.lastKnownPlayerX - this.getX();
            float deltaY = this.lastKnownPlayerY - this.getY();
            float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
            this.setX(this.getX() + (float)(Math.cos(angleToPlayer) * this.getSpeed() * deltaTime));
            this.setY(this.getY() + (float)(Math.sin(angleToPlayer) * this.getSpeed() * deltaTime));
            this.lungeDistanceTraveled += (float) Math.sqrt(Math.pow(oldX - this.getX(),2) + Math.pow(oldY - this.getY(),2));
            System.out.println(this.lungeDistanceTraveled);
            return false;
        }else{
            this.lungeDistanceTraveled = 0;
            return true;
        }
    }





    private boolean isLungeOnCooldown(){
        return this.timeBetweenLunges < LUNGE_COOLDOWN;
    }
}
