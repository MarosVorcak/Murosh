package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.logic.Detection;

/**
 * Trieda Goblin rozširuje triedu Enemy a je zodpovedná za vytvorenie a správu goblinov.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 */
public class Goblin extends Enemy {

    /**
     * Konštruktor pre vytvorenie goblina.
     *
     * @param x float - x-ová pozícia goblina
     * @param y float - y-ová pozícia goblina
     * @param detector Detection - detektor pre detekciu kolízií
     */
    public Goblin(float x, float y, Detection detector) {
        super(new Texture("Entities/goblin.png"), x, y, 60, 10, detector, 130);
        this.getHitboxRectangle().set(x + 16, y, this.getTexture().getWidth() - 32, this.getTexture().getHeight() - 32);
    }

    /**
     * Metóda update slúži na aktualizáciu goblina.
     *
     * @param player Player - hráč
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void update(Player player, float deltaTime) {
        super.update(player, deltaTime);
        this.getHitbox().getRectangle().setPosition(this.getX() + 16, this.getY());
    }
}
