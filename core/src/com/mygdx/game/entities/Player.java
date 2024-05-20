package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.items.Defensivetem;
import com.mygdx.game.items.Inventory;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.projectiles.ArrowManager;
import com.mygdx.game.logic.Detection;

import java.util.ArrayList;

/**
 * Trieda Player rozširuje triedu Entity a je zodpovedná za vytvorenie a správu hráča.
 *
 * Importy:
 * import com.badlogic.gdx.Gdx;
 * import com.badlogic.gdx.Input;
 * import com.badlogic.gdx.graphics.Texture;
 * import com.badlogic.gdx.graphics.g2d.Sprite;
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 * import com.mygdx.game.items.Defensivetem;
 * import com.mygdx.game.items.Inventory;
 * import com.mygdx.game.items.ItemType;
 * import com.mygdx.game.projectiles.ArrowManager;
 * import com.mygdx.game.logic.Detection;
 * import java.util.ArrayList;
 */
public class Player extends Entity {
    private final ArrowManager arrowManager;
    private static final float INVINCIBLITY_TIME = 0.43f;
    private float timeBetweenHits;
    private final Sprite sprite;
    private boolean isFlipped;
    private int maxHp;
    private final Inventory inventory;

    /**
     * Konštruktor pre vytvorenie hráča.
     *
     * @param x float - x-ová pozícia hráča
     * @param y float - y-ová pozícia hráča
     * @param detector Detection - detektor pre detekciu kolízií
     */
    public Player(float x, float y, Detection detector) {
        super(new Texture("Entities/player.png"), x, y, 100, 25, detector, 200);
        this.maxHp = this.getHp();
        this.sprite = new Sprite(this.getTexture());
        this.arrowManager = new ArrowManager(detector);
        this.timeBetweenHits = 0;
        this.isFlipped = false;
        this.inventory = new Inventory();
        this.getHitbox().getRectangle().set(this.getX() + 32, this.getY(), this.getTexture().getWidth() - 64, this.getTexture().getHeight() - 42);
    }

    /**
     * Metóda update slúži na aktualizáciu hráča.
     *
     * @param player Player - hráč
     * @param deltaTime float - časový rozdiel od posledného vykresleného snímku
     */
    @Override
    public void update(Player player, float deltaTime) {
        this.timeBetweenHits += deltaTime;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.moveOnY(this.getSpeed() * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.moveOnY(-this.getSpeed() * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (this.isFlipped) {
                this.isFlipped = false;
                this.sprite.flip(true, false);
            }
            this.moveOnX(this.getSpeed() * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!this.isFlipped) {
                this.isFlipped = true;
                this.sprite.flip(true, false);
            }
            this.moveOnX(-this.getSpeed() * deltaTime);
        }
        this.getHitboxRectangle().setPosition(this.getX() + 32, this.getY());
    }


    private void moveOnX(float distance) {
        float newX = this.getX() + distance;
        this.getHitboxRectangle().setPosition(newX + 32, this.getY());
        if (!this.getDetector().wallCollision(this.getHitboxRectangle())) {
            this.setX(newX);
        }
    }
    private void moveOnY(float distance) {
        float newY = this.getY() + distance;
        this.getHitboxRectangle().setPosition(this.getX() + 32, newY);
        if (!this.getDetector().wallCollision(this.getHitboxRectangle())) {
            this.setY(newY);
        }
    }

    /**
     * Metóda takeDMG slúži na spracovanie útoku na hráča.
     *
     * @param dmg int - počet životov, ktoré hráč stratil
     * @param directionOfAttack char - smer útoku
     */
    @Override
    public void takeDMG(int dmg, char directionOfAttack) {
        if (this.timeBetweenHits >= INVINCIBLITY_TIME) {
            this.timeBetweenHits = 0;
            if (this.inventory.hasItem(ItemType.DAMAGE_SHIELD)) {
                Defensivetem cloak = (Defensivetem)this.inventory.getItem(ItemType.DAMAGE_SHIELD);
                this.setHp(this.getHp() - cloak.calculateDmg(dmg));
            } else {
                this.setHp(this.getHp() - dmg);
            }
        }
    }

    /**
     * Metóda shoot slúži na vystrelenie šípov.
     *
     * @param deltaTime float - časový rozdiel od posledného vykresleného snímku
     * @param batch SpriteBatch - dávka sprajtov
     * @param enemies ArrayList<Enemy> - zoznam nepriateľov
     */
    public void shoot(float deltaTime, SpriteBatch batch, ArrayList<Enemy> enemies) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.arrowManager.shootArrows(deltaTime, 'R', this.getTexture().getWidth(), this.getX(), this.getY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.arrowManager.shootArrows(deltaTime, 'L', this.getTexture().getWidth(), this.getX(), this.getY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.arrowManager.shootArrows(deltaTime, 'U', this.getTexture().getWidth(), this.getX(), this.getY());

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.arrowManager.shootArrows(deltaTime, 'D', this.getTexture().getWidth(), this.getX(), this.getY());
        }
        this.arrowManager.updateAndRenderArrows(deltaTime, batch, enemies, this.getAtk());
    }

    /**
     * Metóda render slúži na vykreslenie hráča.
     *
     * @param batch SpriteBatch - dávka sprajtov
     */
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(this.sprite, this.getX(), this.getY());
    }

    /**
     * Metóda addToMaxHp slúži na pridanie maximalnych životov hráčovi.
     *
     * @param ammount int - počet životov, ktoré sa pridajú
     */
    public void addToMaxHp(int ammount) {
        this.maxHp += ammount;
    }

    /**
     * Metóda getMaxHp slúži na získanie maximálneho počtu životov hráča.
     *
     * @return int - maximálny počet životov hráča
     */
    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * Metóda getArrowManager slúži na získanie manažéra šípov hráča.
     *
     * @return ArrowManager - manažér šípov hráča
     */
    public ArrowManager getArrowManager() {
        return this.arrowManager;
    }

    /**
     * Metóda getInventory slúži na získanie inventára hráča.
     *
     * @return Inventory - inventár hráča
     */
    public Inventory getInventory() {
        return this.inventory;
    }
}
