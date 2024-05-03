package com.mygdx.game.dungeon;

import com.mygdx.game.items.AttackUpgradeItem;
import com.mygdx.game.items.HealthUpgradeItem;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.items.SpeedUpgradeItem;
import com.mygdx.game.logic.Detection;

import java.util.Random;

public class TreasureRoom extends Room {
    /**
     * V tejto triede bude random generacia itemu v roomke
     */
    private Item chestItem;
    public TreasureRoom() {
        super("Maps/treasure_room.tmx");
        this.chestItem = this.generateItem();
        System.out.println(this.chestItem.getName());
    }

    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (this.chestItem != null) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.getPlayer().getInventory().addItem(this.chestItem);
                this.getPlayer().getInventory().applyItem(this.chestItem.getName(), this.getPlayer());
                this.chestItem = null;
            }
        }

    }

    private Item generateItem() {
        ItemType randomItem = ItemType.values()[new Random().nextInt(ItemType.values().length)];
        switch (randomItem) {
            case HEALTH_UP:
                return new HealthUpgradeItem();
            case ATTACK_UP:
                return new AttackUpgradeItem();
            case SPEED_UP:
                return new SpeedUpgradeItem();
            default:
                return null;
        }
    }
}
