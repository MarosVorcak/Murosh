package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.dungeon.TreasureRoom;
import com.mygdx.game.entities.Player;

/**
 * Trieda UI je zodpovedná za vykreslenie užívateľského rozhrania na obrazovke.
 * Zobrazuje informácie o hráčovi, ako sú HP, ATK a SPEED.
 * Zobrazuje taktiež správu, ktorá sa zobrazí, ak hráč vstúpi do miestnosti s pokladom.
 *
 * Importy:
 * import com.badlogic.gdx.Gdx;
 * import com.badlogic.gdx.graphics.g2d.BitmapFont;
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 * import com.badlogic.gdx.utils.TimeUtils;
 * import com.mygdx.game.dungeon.TreasureRoom;
 * import com.mygdx.game.entities.Player;
 */
public class UI {
    private final Player player;
    private final BitmapFont font;
    private String message;
    private long messageDisplayStart;
    public static final long MESSAGE_DURATION = 2000;
    private TreasureRoom treasureRoom;

    /**
     * Konštruktor triedy UI.
     *
     * @param player Objekt hráča, ktorého informácie sa majú zobraziť.
     */
    public UI(Player player) {
        this.player = player;
        this.font = new BitmapFont(Gdx.files.internal("font/font.fnt"), false);
    }

    /**
     * Metóda na vykreslenie užívateľského rozhrania.
     *
     * @param batch Objekt SpriteBatch, ktorý sa používa na vykreslenie.
     */
    public void renderUI(SpriteBatch batch) {
        this.font.setColor(1, 1, 1, 1);
        this.font.getData().setScale(0.7F);

        int hp = this.player.getHp();
        int maxHp = this.player.getMaxHp();
        int speed = this.player.getSpeed();
        int atk = this.player.getAtk();

        this.font.draw(batch, "HP: " + hp + "/" + maxHp, 20, 680);
        this.font.draw(batch, "ATK: " + atk, 20, 640);
        this.font.draw(batch, "SPEED: " + speed, 20, 600);
        if (this.message != null) {
            long currentTime = TimeUtils.millis();
            if (currentTime - this.messageDisplayStart < MESSAGE_DURATION) {
                this.font.draw(batch, this.message, Gdx.graphics.getWidth() / 2 + 80, Gdx.graphics.getHeight() / 5 - 40);
            } else {
                this.message = null;
                this.messageDisplayStart = 0;
            }
        }

    }

    /**
     * Nastaví miesnot s pokladom, ktorá sa má zobraziť v užívateľskom rozhraní.
     *
     * @param treasureRoom Objekt pokladnice, ktorý sa má zobraziť.
     */
    public void setTreasureRoom(TreasureRoom treasureRoom) {
        if (this.treasureRoom == null || !this.treasureRoom.equals(treasureRoom)) {
            this.treasureRoom = treasureRoom;
            if (this.message == null) {
                this.getMessage();
            }
        }
    }
    private void getMessage() {
        if (this.treasureRoom.getChestItem() != null) {
            this.message = this.treasureRoom.getMessageText();
            this.messageDisplayStart = TimeUtils.millis();
        }
    }
}
