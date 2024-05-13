package com.mygdx.game.dungeon;

import com.mygdx.game.items.*;
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
        System.out.println(this.chestItem.getType());
    }

    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (this.chestItem != null) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.getPlayer().getInventory().addItem(this.chestItem);
                if (!(this.chestItem instanceof SpecialItem)){
                    this.getPlayer().getInventory().applyItem(this.chestItem.getType(), this.getPlayer());
                }
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
            case IRON_BOOTS:
                return new IronBoots();
            case DAMAGE_SHIELD:
                return new DamageShield();
            default:
                return null;
        }
    }
}
