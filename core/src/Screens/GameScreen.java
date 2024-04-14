package Screens;

import characters.Goblin;
import characters.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.mygdx.game.MainGame;
import jdk.javadoc.internal.doclets.toolkit.taglets.snippet.MarkupParser;
import logic.Detection;

import java.awt.*;

public class GameScreen implements Screen {
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private MainGame game;
    private Player player;
    private Goblin goblin;
    private Detection detection;




    public GameScreen(MainGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        map = new TmxMapLoader().load("Maps/spawn.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        detection = new Detection(map);
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        player = new Player(new Texture("Archer_M_Big.png"),100,100,200,100,10, detection);
        goblin = new Goblin(new Texture("goblin.png"), 400, 400,100,10, detection);




    }
    @Override
    public void show() {
    }

    @Override
    public void render(float v) {
        mapRenderer.setView(camera);
        mapRenderer.render();

        game.getBatch().begin();


        player.update(player);
        player.render(this.game.getBatch());

        goblin.update(player);
        goblin.render(this.game.getBatch());



        game.getBatch().end();

        System.out.println(this.detection.rectangleToRectangle(this.player.getHitboxRectangle(),this.goblin.getHitboxRectangle()));
        this.player.getHitbox().render();
        this.goblin.getHitbox().render();
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
