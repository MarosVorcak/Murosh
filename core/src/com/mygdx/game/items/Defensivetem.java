package com.mygdx.game.items;


/**
 * Rozhranie Defensivetem rozširuje rozhranie Item a definuje metódu pre výpočet poškodenia.
 *
 */
public interface Defensivetem extends Item {
    int calculateDmg(int damage);

}
