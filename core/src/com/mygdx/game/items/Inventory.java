package com.mygdx.game.items;

import com.mygdx.game.entities.Player;

import java.util.HashMap;
import java.util.Optional;

public class Inventory {
    private final HashMap<String, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }
    public void addItem(Item item) {
        this.items.put(item.getName(), item);
    }
    public Optional<Item> getItem(String name) {
        return Optional.ofNullable(this.items.get(name));
    }
    public void removeItem(String name) {
        this.items.remove(name);
    }

    public void applyItem(String name, Player player) {
        Optional<Item> item = this.getItem(name);
        item.ifPresent(value -> value.applyEffect(player));
    }
}
