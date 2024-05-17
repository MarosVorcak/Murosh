package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.logic.Detection;

/**
 * Trieda Enemy rozširuje triedu Entity a je zodpovedná za vytvorenie a správu nepriateľov.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 */
public class Enemy extends Entity {

    /**
     * Konštruktor pre vytvorenie nepriateľa.
     *
     * @param texture Texture - textúra nepriateľa
     * @param x float - x-ová pozícia nepriateľa
     * @param y float - y-ová pozícia nepriateľa
     * @param hp int - životy nepriateľa
     * @param atk int - útok nepriateľa
     * @param detector Detection - detektor pre detekciu kolízií
     * @param speed int - rýchlosť nepriateľa
     */
    public Enemy(Texture texture, float x, float y, int hp, int atk, Detection detector, int speed) {
        super(texture, x, y, hp, atk, detector, speed);
    }

    /**
     * Metóda update slúži na aktualizáciu nepriateľa.
     *
     * @param player Player - hráč
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void update(Player player, float deltaTime) {
        float deltaX = player.getX() - this.getX();
        float deltaY = player.getY() - this.getY();
        float angleToPlayer = (float)Math.atan2(deltaY, deltaX);
        this.setX(this.getX() + (float)(Math.cos(angleToPlayer) * this.getSpeed() * deltaTime));
        this.setY(this.getY() + (float)(Math.sin(angleToPlayer) * this.getSpeed() * deltaTime));
        this.getHitboxRectangle().setPosition(this.getX() + 32, this.getY());
    }

    /**
     * Metóda takeDMG slúži na spracovanie útoku na nepriateľa.
     *
     * @param dmg int - počet životov, ktoré nepriateľ stratil
     * @param directionOfAttack char - smer útoku
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void takeDMG(int dmg, char directionOfAttack, float deltaTime) {
        this.setHp(this.getHp() - dmg);
        switch (directionOfAttack) {
            case 'U':
                this.setY(this.getY() + 10);
                break;
            case 'D':
                this.setY(this.getY() - 10);
                break;
            case 'R':
                this.setX(this.getX() + 10);
                break;
            case 'L':
                this.setX(this.getX() - 10);
                break;
        }
    }

}
