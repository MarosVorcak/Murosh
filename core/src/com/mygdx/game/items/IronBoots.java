package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class IronBoots implements Defensivetem {
    @Override
    public String getName() {
        return "Mythril boots";
    }

    @Override
    public void applyEffect(Player player) {

    }

    @Override
    public ItemType getType() {
        return ItemType.IRON_BOOTS;
    }

    @Override
    public int calculateDmg(int damage) {
        return damage - 5;
    }
}
