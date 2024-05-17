package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Rozhranie Item definuje metódy, ktoré musia byť implementované všetkými predmetmi v hre.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public interface Item {
    String getName();
    void applyEffect(Player player);
    ItemType getType();

}
