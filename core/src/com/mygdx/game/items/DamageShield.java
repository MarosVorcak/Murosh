package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public class DamageShield implements SpecialItem{
    @Override
    public void applyEffect(Player player) {

    }

    @Override
    public ItemType getType() {
        return ItemType.DAMAGE_SHIELD;
    }

    @Override
    public void speciallEffect(Player player) {

    }
}
