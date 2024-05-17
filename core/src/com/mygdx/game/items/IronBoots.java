package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Trieda IronBoots implementuje rozhranie Defensivetem a je zodpovedná za vytvorenie a správu železných topánok, ktoré znižujú poškodenie z hrotov.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public class IronBoots implements Defensivetem {

    /**
     * Metóda getName() vráti názov predmetu.
     *
     * @return názov predmetu
     */
    @Override
    public String getName() {
        return "Mythril boots";
    }

    /**
     * Metóda applyEffect() aplikuje efekt predmetu na hráča.
     *
     * @param player hráč, na ktorého sa aplikuje efekt
     */
    @Override
    public void applyEffect(Player player) {

    }

    /**
     * Metóda getType() vráti typ predmetu.
     *
     * @return typ predmetu
     */
    @Override
    public ItemType getType() {
        return ItemType.IRON_BOOTS;
    }

    /**
     * Metóda calculateDmg() vypočíta poškodenie po aplikovaní efektu predmetu.
     *
     * @param damage poškodenie pred aplikovaním efektu
     * @return poškodenie po aplikovaní efektu
     */
    @Override
    public int calculateDmg(int damage) {
        return damage - 5;
    }
}
