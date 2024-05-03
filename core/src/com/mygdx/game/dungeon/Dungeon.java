package com.mygdx.game.dungeon;

import com.mygdx.game.logic.Detection;
import com.mygdx.game.logic.RoomSwitcher;

public class Dungeon {
    private Room currentRoom;
    private final RoomSwitcher roomSwitcher;
    private final Detection detector;

    public Dungeon(Detection detector) {
        DungeonGenerator generator = new DungeonGenerator(detector);
        this.detector = detector;
        this.currentRoom = generator.generateDungeon();
        this.roomSwitcher = new RoomSwitcher(this.currentRoom);
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    public boolean swithcedRooms() {
        if (this.currentRoom.checkIfRoomIsExitable()) {
            if (this.detector.doorCollision(this.currentRoom.getPlayer().getHitboxRectangle())) {
                this.currentRoom = this.roomSwitcher.switchRooms();
                return true;
            }
        }
        return false;
    }
    public void dangerObjects(float deltaTime) {
        this.currentRoom.objectInteractions(deltaTime, this.detector);
    }
}
