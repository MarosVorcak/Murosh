package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    private final ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }
    public void addItem(Item item) {
        this.items.add(item);
    }
    public Item getItem(ItemType type) {
        for (Item item : this.items) {
            if (item.getType() == type) {
                return item;
            }
        }
        return null;
    }
    public boolean hasItem(ItemType type) {
        for (Item item : this.items) {
            if (item.getType() == type) {
                return true;
            }
        }
        return false;
    }
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

    public void applyItem(ItemType type, Player player) {
        this.getItem(type).applyEffect(player);
    }

}
