package Screens;

import com.badlogic.gdx.Input;
import dungeon.Dungeon;
import dungeon.Room;
import dungeon.SpawnRoom;
import entities.Enemy;
import entities.Entity;
import entities.Goblin;
import entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MainGame;
import logic.Detection;
import logic.RoomSwitcher;

import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer mapRenderer;
    private MainGame game;
    private Detection detection;
    private Dungeon dungeon;


    public GameScreen(MainGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.detection = new Detection();
        this.dungeon = new Dungeon(this.detection);
        this.detection.setMap(this.dungeon.getCurrentRoom().getMap());
        this.dungeon.getCurrentRoom().setPlayer(new Player(new Texture("Archer_M_Big.png"), 100, 100,  100, 30, this.detection, 200));
        this.dungeon.getCurrentRoom().addEnemy(new Goblin(new Texture("goblin.png"), 400, 400, 100, 10, this.detection, 100));
        this.dungeon.getCurrentRoom().connectRoom(new Room("Maps/blank_room.tmx"));
        this.dungeon.getCurrentRoom().getConnectedRooms().get(0).connectRoom(this.dungeon.getCurrentRoom());
        this.dungeon.getCurrentRoom().getConnectedRooms().get(0).addEnemy(new Goblin(new Texture("goblin.png"), 400, 400, 100, 10, this.detection, 100));
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.dungeon.getCurrentRoom().getMap());

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        this.mapRenderer.setView(this.camera);
        this.mapRenderer.render();
        this.game.getBatch().begin();
        this.dungeon.getCurrentRoom().renderPlayer(this.game.getBatch(), deltaTime);
        this.dungeon.getCurrentRoom().renderEnemies(this.game.getBatch(),deltaTime);
        this.game.getBatch().end();
        if (this.dungeon.swithcedRooms()){
            this.detection.setMap(this.dungeon.getCurrentRoom().getMap());
            this.mapRenderer.setMap(this.dungeon.getCurrentRoom().getMap());
        }
    }




    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        this.game.getBatch().dispose();
    }
}
