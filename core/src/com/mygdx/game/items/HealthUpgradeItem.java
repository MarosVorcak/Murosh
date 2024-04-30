package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class HealthUpgradeItem implements Item {

    private final int ammount;

    public HealthUpgradeItem() {
        this.ammount = 20;
    }

    @Override
    public String getName() {
        return "HealthUp";
    }

    @Override
    public void applyEffect(Player player) {
        player.setHp(player.getHp() + this.ammount/2);
        player.addToMaxHp(this.ammount);
    }
}
