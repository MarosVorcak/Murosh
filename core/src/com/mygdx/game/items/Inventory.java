package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Trieda Inventory je zodpovedná za správu inventára hráča.
 *
 * Importy:
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 * import java.util.ArrayList; // Pre prácu so zoznamom
 * import java.util.Iterator; // Pre prácu s iterátorom
 */
public class Inventory {
    private final ArrayList<Item> items;

    /**
     * Konštruktor triedy Inventory.
     */
    public Inventory() {
        this.items = new ArrayList<>();
    }

    /**
     * Metóda addItem() pridá predmet do inventára.
     *
     * @param item predmet, ktorý sa pridáva
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Metóda getItem() vráti predmet podľa typu.
     *
     * @param type typ predmetu
     * @return predmet
     */
    public Item getItem(ItemType type) {
        for (Item item : this.items) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }

    /**
     * Metóda hasItem() zistí, či hráč má predmet daného typu.
     *
     * @param type typ predmetu
     * @return true, ak hráč má predmet, inak false
     */
    public boolean hasItem(ItemType type) {
        for (Item item : this.items) {
            if (item.getType() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda removeItem() odstráni predmet z inventára.
     *
     * @param type typ predmetu
     */
    public void removeItem(ItemType type) {
        Iterator<Item> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getType() == type) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Metóda applyItem() aplikuje efekt predmetu na hráča.
     *
     * @param type typ predmetu
     * @param player hráč, na ktorého sa aplikuje efekt
     */
    public void applyItem(ItemType type, Player player) {
        this.getItem(type).applyEffect(player);
    }

}
