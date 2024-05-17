package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Trieda HealthUpgradeItem implementuje rozhranie Item a je zodpovedná za vytvorenie a správu predmetu, ktorý zvyšuje zdravie hráča.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public class HealthUpgradeItem implements Item {

    private final int ammount;

    /**
     * Konštruktor triedy HealthUpgradeItem.
     */
    public HealthUpgradeItem() {
        this.ammount = 40;
    }

    /**
     * Metóda getType() vráti typ predmetu.
     *
     * @return typ predmetu
     */
    @Override
    public ItemType getType() {
        return ItemType.HEALTH_UP;
    }

    /**
     * Metóda getName() vráti názov predmetu.
     *
     * @return názov predmetu
     */
    @Override
    public String getName() {
        return "Magical bread";
    }

    /**
     * Metóda applyEffect() aplikuje efekt predmetu na hráča.
     *
     * @param player hráč, na ktorého sa aplikuje efekt
     */
    @Override
    public void applyEffect(Player player) {
        player.setHp(player.getHp() + this.ammount / 2);
        player.addToMaxHp(this.ammount);
    }
}
