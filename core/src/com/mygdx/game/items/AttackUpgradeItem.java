package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class AttackUpgradeItem implements Item {
    private final double multiplier;

    public AttackUpgradeItem() {
        this.multiplier = 1.5;
    }

    @Override
    public ItemType getType() {
        return ItemType.ATTACK_UP;
    }

    @Override
    public void applyEffect(Player player) {
        player.setAtk((int)(player.getAtk() * this.multiplier));
    }
}
