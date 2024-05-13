package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public interface Item {
    void applyEffect(Player player);
    ItemType getType();

}
