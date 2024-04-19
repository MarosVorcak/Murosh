package dungeon;

import logic.Detection;
import logic.RoomSwitcher;

public class Dungeon {
    private Room currentRoom;
    private final DungeonGenerator generator;
    private final RoomSwitcher roomSwitcher;
    private final Detection detector;

    public Dungeon(Detection detector) {
        this.generator = new DungeonGenerator();
        this.detector = detector;
        this.currentRoom = this.generator.generateDungeon();
        this.roomSwitcher = new RoomSwitcher(this.currentRoom);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public boolean swithcedRooms(){
        if (this.currentRoom.checkIfEnemiesAreDead()){
            if (this.detector.doorCollision(this.currentRoom.getPlayer().getHitboxRectangle())){
                this.currentRoom = this.roomSwitcher.switchRooms();
                return true;
            }
        }
        return false;
    }
}
