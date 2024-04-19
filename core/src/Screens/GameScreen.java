package Screens;

import com.badlogic.gdx.Input;
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
    private Player player;
    private Goblin goblin;
    private Detection detection;
    private ArrayList<Entity> entities;



    public GameScreen(MainGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.map = new TmxMapLoader().load("Maps/spawn.tmx");
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.map);
        this.detection = new Detection(this.map);
        this.entities = new ArrayList<Entity>();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.player = new Player(new Texture("Archer_M_Big.png"), 100, 100,  100, 10, this.detection, 200);
        this.goblin = new Goblin(new Texture("goblin.png"), 400, 400, 100, 10, this.detection, 100);
        this.entities.add(this.goblin);

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
        }
        this.game.getBatch().begin();
        if(this.player.isAlive()){
            this.player.update(this.player, deltaTime);
            this.player.render(this.game.getBatch());
            this.player.shoot(deltaTime, this.game.getBatch(), this.entities);
        }else{
            this.dispose();
        }
        Iterator<Entity> iterator = this.entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update(this.player, deltaTime);
            entity.render(this.game.getBatch());
            if (!entity.isAlive()) {
                iterator.remove();
            }
            if(entity.getDetector().rectangleToRectangle(entity.getHitboxRectangle(), this.player.getHitboxRectangle())){
                this.player.takeDMG(entity.getAtk(),Character.MIN_VALUE,deltaTime);
            }
        }
        this.game.getBatch().end();
//        this.player.getHitbox().render();
//        this.player.getArrowManager().renderArrowHitboxes();
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
