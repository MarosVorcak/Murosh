package com.mygdx.game.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Trieda SpawnRoom rozširuje triedu Room a je zodpovedná za vytvorenie a správu spawn miestnosti.
 *
 * Importy:
 * import com.mygdx.game.dungeon.Room; // Pre prácu s miestnosťami
 */
public class SpawnRoom extends Room {

    private Texture wasd;
    private Texture arrows;


    /**
     * Konštruktor pre triedu SpawnRoom, ktorý inicializuje mapu.
     */
    public SpawnRoom() {
        super("Maps/spawn.tmx");
        this.wasd = new Texture("UI/wasd.png");
        this.arrows = new Texture("UI/arrows.png");
    }

    /**
     * Metóda renderEnemies vykreslí obrázky klávesov pre pohyb a strelbu.
     * @param batch dávka sprajtov
     * @param deltaTime časový interval
     */
    @Override
    public void renderEnemies(SpriteBatch batch, float deltaTime) {
        batch.draw(this.arrows, Gdx.graphics.getWidth() / 2 - this.arrows.getWidth() + 400, Gdx.graphics.getHeight() / 2 - this.arrows.getHeight() / 2);
        batch.draw(this.wasd, Gdx.graphics.getWidth() / 2 - this.wasd.getWidth() / 2 * 3, Gdx.graphics.getHeight() / 2 - this.wasd.getHeight() / 2);
    }
}
