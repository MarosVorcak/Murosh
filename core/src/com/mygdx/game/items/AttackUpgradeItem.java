package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Trieda AttackUpgradeItem implementuje rozhranie Item a je zodpovedná za vytvorenie a správu predmetu, ktorý zvyšuje útok hráča.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public class AttackUpgradeItem implements Item {
    private final double multiplier;

    /**
     * Konštruktor triedy AttackUpgradeItem.
     */
    public AttackUpgradeItem() {
        this.multiplier = 1.5;
    }

    /**
     * Metóda getType() vráti typ predmetu.
     *
     * @return typ predmetu
     */
    @Override
    public ItemType getType() {
        return ItemType.ATTACK_UP;
    }

    /**
     * Metóda getName() vráti názov predmetu.
     *
     * @return názov predmetu
     */
    @Override
    public String getName() {
        return "Arrow sharpener";
    }

    /**
     * Metóda applyEffect() aplikuje efekt predmetu na hráča.
     *
     * @param player hráč, na ktorého sa aplikuje efekt
     */
    @Override
    public void applyEffect(Player player) {
        player.setAtk((int)(player.getAtk() * this.multiplier));
    }
}
