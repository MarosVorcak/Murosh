package Screens;

import dungeon.Dungeon;
import dungeon.Room;

import entities.Goblin;
import entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MainGame;
import entities.Shaman;
import entities.Slime;
import logic.Detection;

public class GameScreen implements Screen {
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final MainGame game;
    private final Detection detection;
    private final Dungeon dungeon;


    public GameScreen(MainGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.detection = new Detection();
        this.dungeon = new Dungeon(this.detection);
        this.detection.setMap(this.dungeon.getCurrentRoom().getMap());
        this.dungeon.getCurrentRoom().setPlayer(new Player( 100, 100, this.detection));
        this.dungeon.getCurrentRoom().addEnemy(new Shaman(400, 400, this.detection));
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
        this.dungeon.dangerObjects(deltaTime);
        this.dungeon.getCurrentRoom().renderHitboxes();
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
