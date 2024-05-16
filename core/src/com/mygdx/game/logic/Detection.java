package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Detection {
    private TiledMap map;

    public Detection() {
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public boolean rectangleToRectangle(Rectangle rect1, Rectangle rect2) {
        float rect1HalfWidth = rect1.width / 2;
        float rect1HalfHeight = rect1.height / 2;
        float rect2HalfWidth = rect2.width / 2;
        float rect2HalfHeight = rect2.height / 2;


        float rect1CenterX = rect1.x + rect1HalfWidth;
        float rect1CenterY = rect1.y + rect1HalfHeight;
        float rect2CenterX = rect2.x + rect2HalfWidth;
        float rect2CenterY = rect2.y + rect2HalfHeight;


        float minDistanceX = rect1HalfWidth + rect2HalfWidth;
        float minDistanceY = rect1HalfHeight + rect2HalfHeight;


        float distanceX = Math.abs(rect1CenterX - rect2CenterX);
        float distanceY = Math.abs(rect1CenterY - rect2CenterY);


        return distanceX < minDistanceX  && distanceY < minDistanceY;
    }

    public boolean wallCollision(Rectangle rectangle) {
        if (this.doesLayerExist("Collision")) {
            for (MapObject object : this.map.getLayers().get("Collision").getObjects()) {
                if (object instanceof RectangleMapObject) {
                    if (this.rectangleToRectangle(((RectangleMapObject)object).getRectangle(), rectangle)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean doorCollision(Rectangle rectangle) {
        if (this.doesLayerExist("Doors")) {
            for (MapObject object : this.map.getLayers().get("Doors").getObjects()) {
                if (object instanceof RectangleMapObject) {
                    if (this.rectangleToRectangle(((RectangleMapObject)object).getRectangle(), rectangle)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean dangerObjectCollision(Rectangle rectangle) {
        if (this.doesLayerExist("DangerObjects")) {
            for (MapObject object : this.map.getLayers().get("DangerObjects").getObjects()) {
                if (object instanceof RectangleMapObject) {
                    if (this.rectangleToRectangle(((RectangleMapObject)object).getRectangle(), rectangle)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean specialObjectCollision(Rectangle rectangle) {
        if (this.doesLayerExist("SpecialObjects")) {
            for (MapObject object : this.map.getLayers().get("SpecialObjects").getObjects()) {
                if (object instanceof RectangleMapObject) {
                    if (this.rectangleToRectangle(((RectangleMapObject)object).getRectangle(), rectangle)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean doesLayerExist(String layerName) {
        return this.map.getLayers().get(layerName) != null;
    }
}


