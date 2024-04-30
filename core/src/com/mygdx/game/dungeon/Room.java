package com.mygdx.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Player;
import com.mygdx.game.entities.Shaman;
import com.mygdx.game.logic.Detection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Room {
    private TiledMap map;
    private Player player;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Room> connectedRooms;

    public Room(String path) {
        this.map = new TiledMap();
        this.loadMap(path);
        this.enemies = new ArrayList<Enemy>();
        this.connectedRooms = new ArrayList<Room>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }
    public void loadMap(String pathToMap) {
        this.map = new TmxMapLoader().load(pathToMap);
    }
    public TiledMap getMap() {
        return this.map;
    }
    public void connectRoom(Room room) {
        this.connectedRooms.add(room);
    }
    public List<Room> getConnectedRooms() {
        return Collections.unmodifiableList(this.connectedRooms);
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(this.enemies);
    }

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
    public boolean checkIfRoomIsExitable() {
        return this.enemies.isEmpty();
    }
    public Player getPlayer() {
        return this.player;
    }
    public void renderPlayer(SpriteBatch batch, float deltaTime) {
        this.player.update(this.player, deltaTime);
        this.player.render(batch);
        this.player.shoot(deltaTime, batch, this.enemies);
    }

    public void objectInteractions(float deltaTime, Detection detector){
        if (detector.dangerObjectCollision(this.player.getHitboxRectangle())){
            this.player.takeDMG(10,Character.MIN_VALUE,deltaTime);
        }
    }

    public void renderHitboxes() {
        for (Enemy enemy : this.enemies) {
            enemy.getHitbox().render();
        }
        this.player.getHitbox().render();
    }
}
