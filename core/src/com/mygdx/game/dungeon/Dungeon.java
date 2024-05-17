package com.mygdx.game.dungeon;

import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.RoomSwitcher;

/**
 * Trieda Dungeon je zodpovedná za správu a generovanie celého dungeonu.
 *
 * Importy:
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import com.mygdx.game.logic.RoomSwitcher; // Pre prácu s prepínačom miestností
 */
public class Dungeon {
    private Room currentRoom;
    private final RoomSwitcher roomSwitcher;
    private final Detection detector;

    /**
     * Konštruktor pre triedu Dungeon, ktorý inicializuje dungeon a jeho generátor.
     * @param detector detektor kolízií
     */
    public Dungeon(Detection detector) {
        DungeonGenerator generator = new DungeonGenerator(detector);
        this.detector = detector;
        this.currentRoom = generator.generateDungeon();
        this.roomSwitcher = new RoomSwitcher(this.currentRoom);
    }

    /**
     * Metóda getCurrentRoom vráti aktuálnu miestnosť.
     * @return aktuálna miestnosť
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Metóda swithcedRooms zistí, či je možné prejsť do inej miestnosti.
     * @return true, ak je možné prejsť do inej miestnosti, inak false
     */
    public boolean swithcedRooms() {
        if (this.currentRoom.checkIfRoomIsExitable()) {
            if (this.detector.doorCollision(this.currentRoom.getPlayer().getHitboxRectangle())) {
                this.currentRoom = this.roomSwitcher.switchRooms();
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda objectInteraction vykoná interakcie s objektami v miestnosti.
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    public void objectInteraction(float deltaTime) {
        this.currentRoom.objectInteractions(deltaTime, this.detector);
    }
}
