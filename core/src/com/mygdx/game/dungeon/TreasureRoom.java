package com.mygdx.game.dungeon;

import com.mygdx.game.items.AttackUpgradeItem;
import com.mygdx.game.items.DamageShield;
import com.mygdx.game.items.Defensivetem;
import com.mygdx.game.items.HealthUpgradeItem;
import com.mygdx.game.items.IronBoots;
import com.mygdx.game.items.Item;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.items.SpeedUpgradeItem;
import com.mygdx.game.logic.Detection;


import java.util.Random;

/**
 * Trieda TreasureRoom rozširuje triedu Room a je zodpovedná za vytvorenie a správu miestnosti s pokladom.
 *
 * Importy:
 * import com.mygdx.game.items.AttackUpgradeItem; // Pre prácu s útočnými vylepšovacími predmetmi
 * import com.mygdx.game.items.DamageShield; // Pre prácu so štítmi
 * import com.mygdx.game.items.Defensivetem; // Pre prácu s obrannými predmetmi
 * import com.mygdx.game.items.HealthUpgradeItem; // Pre prácu so zdravotnými vylepšovacími predmetmi
 * import com.mygdx.game.items.IronBoots; // Pre prácu s železnými čižmami
 * import com.mygdx.game.items.Item; // Pre prácu s predmetmi
 * import com.mygdx.game.items.ItemType; // Pre prácu s typmi predmetov
 * import com.mygdx.game.items.SpeedUpgradeItem; // Pre prácu s rýchlostnými vylepšovacími predmetmi
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import java.util.Random; // Pre prácu s náhodnými číslami
 */
public class TreasureRoom extends Room {
    private Item chestItem;

    /**
     * Konštruktor pre triedu TreasureRoom, ktorý inicializuje mapu a vygeneruje predmet v truhle.
     */
    public TreasureRoom() {
        super("Maps/treasure_room.tmx");
        this.chestItem = this.generateItem();
    }

    /**
     * Metóda objectInteractions vykonáva interakcie s predmetmi v miestnosti.
     * @param deltaTime časový interval
     * @param detector detektor
     */
    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (this.chestItem != null) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.healPlayer();
                this.getPlayer().getInventory().addItem(this.chestItem);
                if (!(this.chestItem instanceof Defensivetem)) {
                    this.getPlayer().getInventory().applyItem(this.chestItem.getType(), this.getPlayer());
                }
                this.chestItem = null;
            }
        }
    }

    /**
     * Metóda getMessageText vráti textovú správu o obsahu truhly.
     * @return textová správa
     */
    public String getMessageText() {
        return "This chest contains " + this.chestItem.getName();
    }


    /**
     * Metóda getChestItem vráti predmet v truhle.
     * @return predmet v truhle
     */
    public Item getChestItem() {
        return this.chestItem;
    }

    /**
     * Metóda generateItem vygeneruje náhodný predmet.
     * @return náhodný predmet
     */
    private Item generateItem() {
        ItemType randomItem = ItemType.values()[new Random().nextInt(ItemType.values().length)];
        switch (randomItem) {
            case HEALTH_UP:
                return new HealthUpgradeItem();
            case ATTACK_UP:
                return new AttackUpgradeItem();
            case SPEED_UP:
                return new SpeedUpgradeItem();
            case IRON_BOOTS:
                if (this.isSpecial()) {
                    return new IronBoots();
                } else {
                    return this.generateStatItem();
                }
            case DAMAGE_SHIELD:
                if (this.isSpecial()) {
                    return new DamageShield();
                } else {
                    return this.generateStatItem();
                }
            default:
                return null;
        }
    }


    /**
     * Metóda generateStatItem vygeneruje predmet na zlepšenie schopnosti.
     * @return stat predmet
     */
    private Item generateStatItem() {
        ItemType randomItem = ItemType.values()[new Random().nextInt(3)];
        switch (randomItem) {
            case ATTACK_UP:
                return new AttackUpgradeItem();
            case HEALTH_UP:
                return new HealthUpgradeItem();
            case SPEED_UP:
                return new SpeedUpgradeItem();
            default:
                return null;
        }
    }

    /**
     * Metóda isSpecial zistí, či je predmet špeciálny.
     * @return true, ak je predmet špeciálny, inak false
     */
    private boolean isSpecial() {
        Random random = new Random();
        return random.nextInt(100 + 1) <= 60;
    }

    /**
     * Metóda healPlayer vylieči hráča.
     */
    private void healPlayer() {
        if (this.getPlayer().getMaxHp() / 2 + this.getPlayer().getHp() < this.getPlayer().getMaxHp()) {
            this.getPlayer().setHp(this.getPlayer().getMaxHp() / 2 + this.getPlayer().getHp());
        } else {
            this.getPlayer().setHp(this.getPlayer().getMaxHp() - this.getPlayer().getHp() + this.getPlayer().getHp());
        }

    }

}
