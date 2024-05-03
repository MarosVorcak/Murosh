package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

public interface Item {
    String getName();
    void applyEffect(Player player);

}
