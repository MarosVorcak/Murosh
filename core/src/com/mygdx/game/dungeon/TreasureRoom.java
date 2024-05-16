package com.mygdx.game.dungeon;

import com.mygdx.game.items.AttackUpgradeItem;
import com.mygdx.game.items.DamageShield;
import com.mygdx.game.items.Defensivetem;
import com.mygdx.game.items.HealthUpgradeItem;
import com.mygdx.game.items.IronBoots;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.items.SpeedUpgradeItem;
import com.mygdx.game.logic.Detection;


import java.util.Random;

public class TreasureRoom extends Room {
    private Item chestItem;
    public TreasureRoom() {
        super("Maps/treasure_room.tmx");
        this.chestItem = this.generateItem();
    }

    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (this.chestItem != null) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.healPlayer();
                this.getPlayer().getInventory().addItem(this.chestItem);

                if (!(this.chestItem instanceof Defensivetem)) {
                    this.getPlayer().getInventory().applyItem(this.chestItem.getType(), this.getPlayer());
                }
                this.chestItem = null;
            }
        }
    }

    public String getMessageText() {
        return "This chest contains " + this.chestItem.getName();
    }

    public Item getChestItem() {
        return this.chestItem;
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
                if (this.isSpecial()) {
                    return new IronBoots();
                } else {
                    return this.generateStatItem();
                }
            case DAMAGE_SHIELD:
                if (this.isSpecial()) {
                    return new DamageShield();
                } else {
                    return this.generateStatItem();
                }
            default:
                return null;
        }
    }


    private Item generateStatItem() {
        ItemType randomItem = ItemType.values()[new Random().nextInt(3)];
        switch (randomItem) {
            case ATTACK_UP:
                return new AttackUpgradeItem();
            case HEALTH_UP:
                return new HealthUpgradeItem();
            case SPEED_UP:
                return new SpeedUpgradeItem();
            default:
                return null;
        }
    }

    private boolean isSpecial() {
        Random random = new Random();
        return random.nextInt(100 + 1) <= 60;
    }

    private void healPlayer() {
        if (this.getPlayer().getMaxHp() / 2 + this.getPlayer().getHp() < this.getPlayer().getMaxHp()) {
            this.getPlayer().setHp(this.getPlayer().getMaxHp() / 2 + this.getPlayer().getHp());
        } else {
            this.getPlayer().setHp(this.getPlayer().getMaxHp() - this.getPlayer().getHp() + this.getPlayer().getHp());
        }

    }
}
