package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class SpeedUpgradeItem implements Item {
    private final int ammount;

    public SpeedUpgradeItem() {
        this.ammount = 40;
    }

    @Override
    public ItemType getType() {
        return ItemType.SPEED_UP;
    }

    @Override
    public String getName() {
        return "Cofee";
    }

    @Override
    public void applyEffect(Player player) {
        if (player.getSpeed() < 400) {
            player.setSpeed(player.getSpeed() + this.ammount);
        }
    }
}
