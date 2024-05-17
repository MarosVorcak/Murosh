package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

/**
 * Trieda DamageShield implementuje rozhranie Defensivetem a je zodpovedná za vytvorenie a správu štítu, ktorý znižuje poškodenie.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 */
public class DamageShield implements Defensivetem {

    /**
     * Metóda getName() vráti názov predmetu.
     *
     * @return názov predmetu
     */
    @Override
    public String getName() {
        return "Mythril cloak";
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
        return ItemType.DAMAGE_SHIELD;
    }

    /**
     * Metóda calculateDmg() vypočíta poškodenie po aplikovaní efektu predmetu.
     *
     * @param damage poškodenie pred aplikovaním efektu
     * @return poškodenie po aplikovaní efektu
     */
    @Override
    public int calculateDmg(int damage) {
        return damage / 2;
    }
}
