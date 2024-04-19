package Screens;

import com.badlogic.gdx.Input;
import dungeon.Room;
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
import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private MainGame game;
    private Detection detection;
    private Room testRoom;



    public GameScreen(MainGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.testRoom = new Room(new TiledMap());
        this.testRoom.loadMap("Maps/spawn.tmx");
        this.detection = new Detection(this.testRoom.getMap());
        this.testRoom.setPlayer(new Player(new Texture("Archer_M_Big.png"), 100, 100,  100, 10, this.detection, 200));
        this.testRoom.addEnemy(new Goblin(new Texture("goblin.png"), 400, 400, 100, 10, this.detection, 100));
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.testRoom.getMap());

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        this.mapRenderer.setView(this.camera);
        this.mapRenderer.render();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            this.map = new TmxMapLoader().load("Maps/blank_room.tmx");
            this.mapRenderer.setMap(this.map);
            this.detection.setMap(map);
        }
        this.game.getBatch().begin();
        this.testRoom.renderEnemies(this.game.getBatch(), deltaTime);
        this.testRoom.renderPlayer(this.game.getBatch(), deltaTime);
        this.game.getBatch().end();
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
