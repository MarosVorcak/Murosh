package com.mygdx.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Shaman;
import com.mygdx.game.items.Defensivetem;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.logic.Detection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Trieda Room je zodpovedná za správu a interakciu s miestnosťami.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s dávkami sprajtov
 * import com.badlogic.gdx.maps.tiled.TiledMap; // Pre prácu s tiled mapami
 * import com.badlogic.gdx.maps.tiled.TmxMapLoader; // Pre načítanie TMX máp
 * import com.mygdx.game.entities.Enemy; // Pre prácu s nepriateľmi
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 * import com.mygdx.game.entities.Shaman; // Pre prácu s Shaman
 * import com.mygdx.game.items.Defensivetem; // Pre prácu s obrannými predmetmi
 * import com.mygdx.game.items.ItemType; // Pre prácu s typmi predmetov
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import java.util.ArrayList; // Pre prácu so zoznamami
 * import java.util.Collections; // Pre prácu s kolekciami
 * import java.util.Iterator; // Pre prácu s iterátormi
 * import java.util.List; // Pre prácu so zoznamami
 */
public class Room {
    private TiledMap map;
    private Player player;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Room> connectedRooms;

    /**
     * Konštruktor pre triedu Room, ktorý inicializuje mapu a zoznamy nepriateľov a prepojených miestností.
     * @param path cesta k súboru s mapou
     */
    public Room(String path) {
        this.map = new TiledMap();
        this.loadMap(path);
        this.enemies = new ArrayList<Enemy>();
        this.connectedRooms = new ArrayList<Room>();
    }

    /**
     * Metóda setPlayer nastaví hráča do miestnosti.
     * @param player hráč
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Metóda addEnemy pridá nepriateľa do miestnosti.
     * @param enemy nepriateľ
     */
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    /**
     * Metóda loadMap načíta mapu z daného súboru.
     * @param pathToMap cesta k súboru s mapou
     */
    public void loadMap(String pathToMap) {
        this.map = new TmxMapLoader().load(pathToMap);
    }

    /**
     * Metóda getMap vráti mapu miestnosti.
     * @return mapa miestnosti
     */
    public TiledMap getMap() {
        return this.map;
    }

    /**
     * Metóda connectRoom pripojí miestnosť k miestnosti.
     * @param room miestnosť
     */
    public void connectRoom(Room room) {
        this.connectedRooms.add(room);
    }

    /**
     * Metóda getConnectedRooms vráti zoznam prepojených miestností.
     * @return zoznam prepojených miestností
     */
    public List<Room> getConnectedRooms() {
        return Collections.unmodifiableList(this.connectedRooms);
    }

    /**
     * Metóda getEnemies vráti zoznam nepriateľov v miestnosti.
     * @return zoznam nepriateľov
     */
    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

    /**
     * Metóda renderEnemies vykreslí nepriateľov, aktualizuje ich a zistí, či sú ešte nažive.
     * @param batch dávka sprajtov
     * @param deltaTime časový interval
     */
    public void renderEnemies(SpriteBatch batch, float deltaTime) {
        Iterator<Enemy> iterator = this.enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(this.player, deltaTime);
            enemy.render(batch);
            if (enemy instanceof Shaman) {
                ((Shaman)enemy).shootFireball(this.player, batch, deltaTime);
            }
            if (!enemy.isAlive()) {
                iterator.remove();
            }
            if (enemy.getDetector().rectangleToRectangle(enemy.getHitboxRectangle(), this.player.getHitboxRectangle())) {
                this.player.takeDMG(enemy.getAtk(), Character.MIN_VALUE, deltaTime);
            }
        }
    }

    /**
     * Metóda checkIfRoomIsExitable zistí, či je možné opustiť miestnosť.
     * @return true, ak je možné opustiť miestnosť, inak false
     */
    public boolean checkIfRoomIsExitable() {
        return this.enemies.isEmpty();
    }

    /**
     * Metóda getPlayer vráti hráča v miestnosti.
     * @return hráč
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Metóda renderPlayer vykreslí hráča, aktualizuje ho a zistí, či strieľa.
     * @param batch dávka sprajtov
     * @param deltaTime časový interval
     */
    public void renderPlayer(SpriteBatch batch, float deltaTime) {
        this.player.update(this.player, deltaTime);
        this.player.render(batch);
        this.player.shoot(deltaTime, batch, this.enemies);
    }

    /**
     * Metóda objectInteractions vykoná interakcie s objektami v miestnosti.
     * @param deltaTime časový interval
     * @param detector detektor kolízií
     */
    public void objectInteractions(float deltaTime, Detection detector) {
        if (detector.dangerObjectCollision(this.player.getHitboxRectangle())) {
            if (this.player.getInventory().hasItem(ItemType.IRON_BOOTS)) {
                Defensivetem item = (Defensivetem)this.player.getInventory().getItem(ItemType.IRON_BOOTS);
                this.player.takeDMG(item.calculateDmg(15), Character.MIN_VALUE, deltaTime);
            } else {
                this.player.takeDMG(15, Character.MIN_VALUE, deltaTime);
            }
        }
    }

    /**
     * Metóda renderHitboxes vykreslí hitboxy hráča a nepriateľov.
     */
    public void renderHitboxes() {
        for (Enemy enemy : this.enemies) {
            enemy.getHitbox().render();
        }
        this.player.getHitbox().render();
    }
}
