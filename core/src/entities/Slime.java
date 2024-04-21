package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import logic.Detection;

public class Slime extends Enemy{
    private static final float CHARGE_DURATION = 1f;
    private static final float SHAKE_AMMOUNT = 0.5f;
    private static final float LUNGE_DURATION = 0.4f;
    private static final float LUNGE_COOLDOWN = 3;
    private float timeBetweenLunges;
    private float chargeUpTimer;
    private float lungeTimer;

    public Slime(float x, float y, Detection detector) {
        super(new Texture("Entities/slime.png"), x, y, 100, 20, detector, 30);
        this.getHitboxRectangle().set(x+8,y,this.getTexture().getWidth()-16,this.getTexture().getHeight()-16);
        this.chargeUpTimer = 0;
        this.timeBetweenLunges = 0;
        this.lungeTimer = 0;
    }

    @Override
    public void update(Player player, float deltaTime) {
        if(isLungeOnCooldown()){
            super.update(player, deltaTime);
            this.timeBetweenLunges += deltaTime;
        } else {
            if (this.chargeUpLunge(deltaTime,player)){
                if(lunge(player, deltaTime)){
                    System.out.println("I just lunged");
                    this.timeBetweenLunges = 0;
                }
            }
        }

        this.getHitboxRectangle().setPosition(this.getX()+8, this.getY());
    }

    private boolean chargeUpLunge(float deltaTime, Player player){
        this.chargeUpTimer += deltaTime;
        if(this.chargeUpTimer <= CHARGE_DURATION){
            float offsetY = MathUtils.random(-SHAKE_AMMOUNT, SHAKE_AMMOUNT);
            float offsetX = MathUtils.random(-SHAKE_AMMOUNT, SHAKE_AMMOUNT);
            this.setX(this.getX() + offsetX);
            this.setY(this.getY() + offsetY);
            return false;
        } else {
            this.chargeUpTimer = 0;
            return true;
        }
    }

    private boolean lunge(Player player, float deltaTime) {
        float deltaX = player.getX() - this.getX();
        float deltaY = player.getY() - this.getY();
        float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
        this.setSpeed(this.getSpeed() * 3); // Increase speed for the lunge
        float distanceTraveled = this.getSpeed() * LUNGE_DURATION;
        float finalX = (float) (this.getX() + distanceTraveled * Math.cos(angleToPlayer));
        float finalY = (float) (this.getY() + distanceTraveled * Math.sin(angleToPlayer));

        // Move towards the final destination gradually
        if (this.getX() < finalX && this.getY() < finalY) {
            this.setX(this.getX() + (float)(Math.cos(angleToPlayer) * this.getSpeed() * deltaTime));
            this.setY(this.getY() + (float)(Math.sin(angleToPlayer) * this.getSpeed() * deltaTime));
            System.out.println(getX());
            System.out.println(getY());
            return false;
        }
        // Reset speed after lunge
        this.setSpeed(this.getSpeed() / 3);
        return true;
    }




    private boolean isLungeOnCooldown(){
        return this.timeBetweenLunges < LUNGE_COOLDOWN;
    }
}
