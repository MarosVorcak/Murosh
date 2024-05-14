package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.dungeon.TreasureRoom;
import com.mygdx.game.entities.Player;

public class UI {
    private final Player player;
    private final BitmapFont font;
    private String message;
    private long messageDisplayStart;
    public static final long MESSAGE_DURATION = 3000;
    private TreasureRoom treasureRoom;

    public UI(Player player) {
        this.player = player;
        this.font = new BitmapFont(Gdx.files.internal("font/font.fnt"), false);
    }

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


    public void setTreasureRoom(TreasureRoom treasureRoom){
        if(this.treasureRoom == null || !this.treasureRoom.equals(treasureRoom)){
            this.treasureRoom = treasureRoom;
            if(this.message == null){
                this.getMessage();
            }
        }
    }
    private void getMessage(){
        if (this.treasureRoom.getChestItem() != null){
            this.message = this.treasureRoom.getMessageText();
            this.messageDisplayStart = TimeUtils.millis();
        }else{
            this.message = "This chest is empty";
            this.messageDisplayStart = TimeUtils.millis();
        }
    }
}
