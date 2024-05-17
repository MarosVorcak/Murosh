package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Trieda SpeedUpgradeItem je zodpovedná za vytvorenie a správu predmetu, ktorý zvyšuje rýchlosť hráča.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public class SpeedUpgradeItem implements Item {
    private final int ammount;

    /**
     * Konštruktor triedy SpeedUpgradeItem.
     */
    public SpeedUpgradeItem() {
        this.ammount = 40;
    }

    /**
     * Metóda pre získanie typu predmetu.
     *
     * @return Typ predmetu.
     */
    @Override
    public ItemType getType() {
        return ItemType.SPEED_UP;
    }

    /**
     * Metóda pre získanie názvu predmetu.
     *
     * @return Názov predmetu.
     */
    @Override
    public String getName() {
        return "Cofee";
    }

    /**
     * Metóda pre aplikovanie efektu predmetu na hráča.
     *
     * @param player Hráč, na ktorého sa má efekt aplikovať.
     */
    @Override
    public void applyEffect(Player player) {
        if (player.getSpeed() < 400) {
            player.setSpeed(player.getSpeed() + this.ammount);
        }
    }
}
