package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class DamageShield implements Defensivetem {
    @Override
    public String getName() {
        return "Mythril cloak";
    }

    @Override
    public void applyEffect(Player player) {

    }

    @Override
    public ItemType getType() {
        return ItemType.DAMAGE_SHIELD;
    }

    @Override
    public int calculateDmg(int damage) {
        return damage / 2;
    }
}
