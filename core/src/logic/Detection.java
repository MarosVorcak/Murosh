package logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Detection{
    private TiledMap map;
    private MapObjects objects;
    public Detection(TiledMap map) {
        this.map = map;
        this.objects = map.getLayers().get("Collision").getObjects();
    }
    public boolean detectCollision(Circle hitbox1, Circle hitbox2){
        float distanceX = Math.abs(hitbox1.x - hitbox2.x);
        float distanceY = Math.abs(hitbox1.y - hitbox2.y);
        float distance = (float) Math.sqrt((distanceX*distanceX)+(distanceY*distanceY));

        if(distance + 10 <= hitbox1.radius+hitbox2.radius){
            System.out.println("Collision detected");
            return true;
        }else{
            return false;
        }
    }
    public boolean cirlceToRectngle(Rectangle rectangle, Circle circle){
        float closestX = Math.max(rectangle.x, Math.min(circle.x, rectangle.x + rectangle.width));
        float closestY = Math.max(rectangle.y, Math.min(circle.y, rectangle.y + rectangle.height));

        float distanceX = circle.x - closestX;
        float distanceY = circle.y - closestY;

        return (distanceX * distanceX + distanceY * distanceY) < (circle.radius * circle.radius);
    }

    public boolean wallCollsion(Circle circle){
        for (MapObject object : map.getLayers().get("Collision").getObjects()){
                if(object instanceof RectangleMapObject){
                    return cirlceToRectngle(((RectangleMapObject) object).getRectangle(),circle);
                }
        }
        return false;
    }
}


