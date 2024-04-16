package Screens;

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
        camera = new OrthographicCamera();
        map = new TmxMapLoader().load("Maps/spawn.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        detection = new Detection(map);
        this.entities = new ArrayList<Entity>();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(new Texture("Archer_M_Big.png"),100,100,200,100,10, detection);
        goblin = new Goblin(new Texture("goblin.png"), 400, 400,100,10, detection);
        this.entities.add(goblin);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float deltaTime) {
        mapRenderer.setView(camera);
        mapRenderer.render();

        game.getBatch().begin();


        player.update(this.player, deltaTime);
        player.render(this.game.getBatch());
        player.shoot(deltaTime,this.game.getBatch(),this.entities);
        Iterator<Entity> iterator = this.entities.iterator();
        while(iterator.hasNext()){
            Entity entity = iterator.next();
            entity.update(this.player,deltaTime);
            entity.render(this.game.getBatch());
            if(!entity.isAlive()){
                iterator.remove();
            }
        }
        game.getBatch().end();

        System.out.println(this.detection.rectangleToRectangle(this.player.getHitboxRectangle(),this.goblin.getHitboxRectangle()));
//        this.player.getHitbox().render();
//        this.player.getArrowManager().renderArrowHitboxes();
//        this.goblin.getHitbox().render();
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
        game.getBatch().dispose();
    }
}
