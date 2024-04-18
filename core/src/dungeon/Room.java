package dungeon;

import com.badlogic.gdx.maps.tiled.TiledMap;
import entities.Enemy;
import entities.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Room {
    public TiledMap map;
    public Player player;
    public ArrayList<Enemy> enemies;
    public ArrayList<Room> connectedRooms;

}
