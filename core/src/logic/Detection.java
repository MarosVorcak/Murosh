package logic;

import com.badlogic.gdx.math.Circle;

import java.awt.*;

public class Detection{
    private float distanceX;
    private float distanceY;
    public Detection() {
    }
    public boolean detectCollision(Circle hitbox1, Circle hitbox2){
        this.distanceX = Math.abs(hitbox1.x - hitbox2.x);
        this.distanceY = Math.abs(hitbox1.y - hitbox2.y);
        float distance = (float) Math.sqrt((distanceX*distanceX)+(distanceY*distanceY));
        if(distance + 10 <= hitbox1.radius+hitbox2.radius){
            System.out.println("Collision detected");
            return true;
        }else{
            return false;
        }
    }
}


