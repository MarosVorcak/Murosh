package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

/**
 * Trieda Detection je zodpovedná za detekciu kolízií v hre.
 *
 * Importy:
 * import com.badlogic.gdx.maps.MapObject; // Pre prácu s objektami na mape
 * import com.badlogic.gdx.maps.objects.RectangleMapObject; // Pre prácu s obdĺžnikovými objektami na mape
 * import com.badlogic.gdx.maps.tiled.TiledMap; // Pre prácu s tiled mapami
 * import com.badlogic.gdx.math.Rectangle; // Pre prácu s obdĺžnikmi
 */
public class Detection {
    private TiledMap map;

    /**
     * Konštruktor triedy Detection.
     */
    public Detection() {
    }

    /**
     * Metóda setMap nastavuje tiled mapu.
     *
     * @param map Dlaždicová mapa.
     */
    public void setMap(TiledMap map) {
        this.map = map;
    }

    /**
     * Metóda rectangleToRectangle slúži na detekciu kolízií medzi dvoma obdĺžnikmi.
     *
     * @param rect1 Prvý obdĺžnik.
     * @param rect2 Druhý obdĺžnik.
     * @return True, ak sa obdĺžniky prekrývajú, inak false.
     */
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


    /**
     * Metóda wallCollision slúži na detekciu kolízií s koliznimi objektami na mape.
     *
     * @param rectangle Obdĺžnik kolízie.
     * @return True, ak sa obdĺžnik prekrýva s nejakým koliznim objektom, inak false.
     */
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

    /**
     * Metóda doorCollision slúži na detekciu kolízií s dverami na mape.
     *
     * @param rectangle Obdĺžnik kolízie.
     * @return True, ak sa obdĺžnik prekrýva s nejakým obdĺžnikovým objektom dverí, inak false.
     */
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

    /**
     * Metóda dangerObjectCollision slúži na detekciu kolízií s nebezpečnými objektami na mape.
     *
     * @param rectangle Obdĺžnik kolízie.
     * @return True, ak sa obdĺžnik prekrýva s nejakým obdĺžnikovým objektom nebezpečných objektov, inak false.
     */
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

    /**
     * Metóda specialObjectCollision slúži na detekciu kolízií s špeciálnymi objektami na mape.
     *
     * @param rectangle Obdĺžnik kolízie.
     * @return True, ak sa obdĺžnik prekrýva s nejakým obdĺžnikovým objektom špeciálnych objektov, inak false.
     */
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

    /**
     * Metóda doesLayerExist slúži na zistenie, či existuje vrstva s daným názvom.
     *
     * @param layerName Názov vrstvy.
     * @return True, ak vrstva existuje, inak false.
     */
    private boolean doesLayerExist(String layerName) {
        return this.map.getLayers().get(layerName) != null;
    }
}


