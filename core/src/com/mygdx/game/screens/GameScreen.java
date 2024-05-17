package com.mygdx.game.screens;

import com.mygdx.game.dungeon.BossRoom;
import com.mygdx.game.dungeon.Dungeon;
import com.mygdx.game.dungeon.TreasureRoom;
import com.mygdx.game.entities.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.MainGame;
import com.mygdx.game.logic.Detection;

/**
 * Trieda GameScreen je zodpovedná za vytvorenie a správu hernej obrazovky.
 *
 * Importy:
 * import com.mygdx.game.dungeon.BossRoom; // Pre prácu s BossRoom
 * import com.mygdx.game.dungeon.Dungeon; // Pre prácu s Dungeon
 * import com.mygdx.game.dungeon.TreasureRoom; // Pre prácu s TreasureRoom
 * import com.mygdx.game.entities.Player; // Pre prácu s Player
 * import com.badlogic.gdx.Gdx; // Pre prácu s libGDX knižnicou
 * import com.badlogic.gdx.Screen; // Pre vytvorenie obrazovky hry
 * import com.badlogic.gdx.graphics.OrthographicCamera; // Pre prácu s OrthographicCamera
 * import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer; // Pre prácu s OrthogonalTiledMapRenderer
 * import com.mygdx.game.MainGame; // Pre prácu s hlavnou hrou
 * import com.mygdx.game.logic.Detection; // Pre prácu s Detection
 */
public class GameScreen implements Screen {
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final MainGame game;
    private final Detection detection;
    private final Dungeon dungeon;
    private final UI ui;

    /**
     * Konštruktor triedy GameScreen.
     *
     * @param game Hlavná hra.
     */
    public GameScreen(MainGame game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.detection = new Detection();
        this.dungeon = new Dungeon(this.detection);
        this.detection.setMap(this.dungeon.getCurrentRoom().getMap());
        this.dungeon.getCurrentRoom().setPlayer(new Player( 500, 100, this.detection));
        this.ui = new UI(this.dungeon.getCurrentRoom().getPlayer());
//        this.dungeon.getCurrentRoom().addEnemy(new Shaman(400, 400, this.detection));
        this.mapRenderer = new OrthogonalTiledMapRenderer(this.dungeon.getCurrentRoom().getMap());
    }
    @Override
    public void show() {
    }

    /**
     * Metóda na vykreslenie hernej obrazovky.
     *
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void render(float deltaTime) {
        if (!this.dungeon.getCurrentRoom().getPlayer().isAlive()) {
            this.game.setScreen(new EndGameScreen(this.game, false));
            return;
        }
        if (this.dungeon.getCurrentRoom() instanceof BossRoom && this.dungeon.getCurrentRoom().checkIfRoomIsExitable()) {
            this.game.setScreen(new EndGameScreen(this.game, true));
            return;
        }
        this.mapRenderer.setView(this.camera);
        this.mapRenderer.render();
        this.game.getBatch().begin();
        this.dungeon.getCurrentRoom().renderPlayer(this.game.getBatch(), deltaTime);
        this.dungeon.getCurrentRoom().renderEnemies(this.game.getBatch(), deltaTime);
        this.ui.renderUI(this.game.getBatch());
        this.game.getBatch().end();
        if (this.dungeon.swithcedRooms()) {
            this.dungeon.getCurrentRoom().getPlayer().getArrowManager().clearArrowManager();
            this.detection.setMap(this.dungeon.getCurrentRoom().getMap());
            this.mapRenderer.setMap(this.dungeon.getCurrentRoom().getMap());
        }
        this.dungeon.objectInteraction(deltaTime);
        if (this.dungeon.getCurrentRoom() instanceof TreasureRoom) {
            this.ui.setTreasureRoom((TreasureRoom)this.dungeon.getCurrentRoom());
        }
//        this.dungeon.getCurrentRoom().renderHitboxes();
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

    }
}
